package com.banking.sofware.design.fxtrading.mapper;


import com.banking.sofware.design.fxtrading.entity.Transaction;
import com.banking.sofware.design.fxtrading.response.TransactionResponse;
import com.banking.sofware.design.fxtrading.util.RateUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@Mapper(imports = {RateUtil.class, Date.class})
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(qualifiedByName = "convertRateForTransfer", target = "rate")
    @Mapping(qualifiedByName = "convertDateToLong", target = "date")
    TransactionResponse transactionToTransactionResponse(Transaction transaction);

    @Named("convertRateForTransfer")
    default BigDecimal convertRateForTransfer(BigDecimal rate) {
        return rate.divide(RateUtil.RATE_MULTIPLIER, RoundingMode.HALF_DOWN);
    }

    @Named("convertDateToLong")
    default Long convertDateToLong(Date date) {
        //TODO: get date field from transaction and convert it to long using method from Date API
        /**
         * Notice: the date object can't be null as it is a mandatory database field.
         * but if an entity field can be null we need to take care at conversion to
         * avoid Null Pointer Exception
         **/
        //return date.
        if (date != null) {
            return date.toInstant().getEpochSecond();
        }
        return 0L;
    }

    @Named("transactionResponseToTransaction")
    @Mapping(qualifiedByName = "convertRateForPersisting", target = "rate", source = "rate")
    @Mapping(expression = "java(new Date())", target = "date")
    Transaction transactionResponseToTransaction(TransactionResponse dto, BigDecimal rate);

    @Named("convertRateForPersisting")
    default BigDecimal convertRateForPersisting(BigDecimal rate) {
        return rate.multiply(RateUtil.RATE_MULTIPLIER).setScale(0, RoundingMode.HALF_UP);
    }
}
