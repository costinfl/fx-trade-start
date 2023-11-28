package com.banking.sofware.design.fxtrading.rest;

import com.banking.sofware.design.fxtrading.response.TransactionResponse;
import com.banking.sofware.design.fxtrading.service.FxTradingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class FxTradingRestController {

    @Autowired
    private FxTradingService tradingService;

    @CrossOrigin
    @GetMapping(produces = "application/json")
    public List<TransactionResponse> getTransactions(HttpServletResponse response) {
        try {
            return tradingService.getTransactions();
        } catch (Exception e) {
            response.setStatus(500);
            return null;
        }
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public void makeTransaction(@RequestBody TransactionResponse transaction, HttpServletResponse response) {
        try {
            tradingService.makeTransaction(transaction);
        } catch (Exception e) {
            response.setStatus(500);
        }
    }

}
