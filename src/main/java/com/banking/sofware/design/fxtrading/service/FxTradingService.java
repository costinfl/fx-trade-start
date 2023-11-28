package com.banking.sofware.design.fxtrading.service;

import com.banking.sofware.design.fxtrading.dto.QuoteResponse;
import com.banking.sofware.design.fxtrading.entity.Transaction;
import com.banking.sofware.design.fxtrading.mapper.TransactionMapper;
import com.banking.sofware.design.fxtrading.repository.FxTradingRepository;
import com.banking.sofware.design.fxtrading.response.TransactionResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FxTradingService {

    private final Logger logger = LoggerFactory.getLogger(FxTradingService.class);


    private final FxTradingRepository repository;


    private final QuoteProxyService proxyRatesService;

    public FxTradingService(FxTradingRepository repository, QuoteProxyService proxyRatesService) {
        this.repository = repository;
        this.proxyRatesService = proxyRatesService;
    }

    @SuppressWarnings("unchecked")
    public List<TransactionResponse> getTransactions() {
        return repository.findAll().stream().map(TransactionMapper.INSTANCE::transactionToTransactionResponse).collect(Collectors.toList());
    }

    @Transactional
    public void makeTransaction(TransactionResponse dto) {
        // Important: in a real application validations should be made - here for example
        String action = dto.getAction();
        if (StringUtils.isBlank(action) || !Arrays.asList("BUY", "SELL").contains(action.toUpperCase())) {
            throw new IllegalArgumentException("Action not supported!");
        }

        QuoteResponse ratePair = getCurrentRate(dto.getPrimaryCcy(), dto.getSecondaryCcy());
        BigDecimal rate = "BUY".equalsIgnoreCase(action) ? ratePair.getBuyRate() : ratePair.getSellRate();

        Transaction transaction = TransactionMapper.INSTANCE.transactionResponseToTransaction(dto, rate);
        repository.save(transaction);
    }

    private QuoteResponse getCurrentRate(String primaryCcy, String secondaryCcy) {
        try {
            return proxyRatesService.getRate(primaryCcy, secondaryCcy);
        } catch (Exception e) {
            logger.error("Could not obtain response from quote service!", e);
            throw e;
        }
    }

}
