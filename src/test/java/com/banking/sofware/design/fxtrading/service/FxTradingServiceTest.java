package com.banking.sofware.design.fxtrading.service;

import com.banking.sofware.design.fxtrading.dto.QuoteResponse;
import com.banking.sofware.design.fxtrading.entity.Transaction;
import com.banking.sofware.design.fxtrading.repository.FxTradingRepository;
import com.banking.sofware.design.fxtrading.response.TransactionResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FxTradingServiceTest {

    @Mock
    private QuoteProxyService quoteMock;

    @Mock
    private FxTradingRepository repositoryMock;

    @InjectMocks
    private FxTradingService service;

    @Test
    public void makeTransaction() throws Exception {


        //setup
        TransactionResponse vo = new TransactionResponse();
        vo.setAction("BUY");
        vo.setNotional(BigDecimal.valueOf(1000));
        vo.setTenor("SP");
        vo.setPrimaryCcy("EUR");
        vo.setSecondaryCcy("RON");
        when(quoteMock.getRate(vo.getPrimaryCcy(), vo.getSecondaryCcy()))
                .thenReturn(new QuoteResponse(BigDecimal.valueOf(1.1234), BigDecimal.valueOf(1.4321)));

        //method under test
        service.makeTransaction(vo);

        //assert
        ArgumentCaptor<Transaction> capturedTransaction = ArgumentCaptor.forClass(Transaction.class);
        verify(repositoryMock).save(capturedTransaction.capture());
        assertEquals(BigDecimal.valueOf(11234), capturedTransaction.getValue().getRate());
        assertEquals(BigDecimal.valueOf(1000), capturedTransaction.getValue().getNotional());
    }

    @Test
    void getTransactions() {
    }
}