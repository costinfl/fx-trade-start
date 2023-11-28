package com.banking.sofware.design.fxtrading.repository;

import com.banking.sofware.design.fxtrading.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface FxTradingRepository extends JpaRepository<Transaction, BigDecimal> {

}
