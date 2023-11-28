package com.banking.sofware.design.fxtrading.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.GsonTester;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FxTradingRestControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void getTransactions() throws Exception {
        String expectedJson = "[{\"id\":1,\"username\":\"ana_maria\",\"primaryCcy\":\"EUR\",\"secondaryCcy\":\"USD\",\"rate\":2,\"action\":\"BUY\",\"notional\":10000,\"tenor\":\"SP\",\"date\":1542135371},{\"id\":2,\"username\":\"alexandru\",\"primaryCcy\":\"USD\",\"secondaryCcy\":\"GBP\",\"rate\":1,\"action\":\"BUY\",\"notional\":15000,\"tenor\":\"1M\",\"date\":1542648722},{\"id\":3,\"username\":\"mihaela\",\"primaryCcy\":\"EUR\",\"secondaryCcy\":\"GBP\",\"rate\":1,\"action\":\"BUY\",\"notional\":100000,\"tenor\":\"1M\",\"date\":1542649190},{\"id\":4,\"username\":\"andreea\",\"primaryCcy\":\"EUR\",\"secondaryCcy\":\"GBP\",\"rate\":1,\"action\":\"BUY\",\"notional\":22000,\"tenor\":\"1M\",\"date\":1542649243},{\"id\":5,\"username\":\"elena\",\"primaryCcy\":\"EUR\",\"secondaryCcy\":\"GBP\",\"rate\":1,\"action\":\"BUY\",\"notional\":50000,\"tenor\":\"1M\",\"date\":1542649275},{\"id\":6,\"username\":\"adrian\",\"primaryCcy\":\"USD\",\"secondaryCcy\":\"RON\",\"rate\":6,\"action\":\"BUY\",\"notional\":35000,\"tenor\":\"SP\",\"date\":1542649309},{\"id\":7,\"username\":\"andrei\",\"primaryCcy\":\"GBP\",\"secondaryCcy\":\"RON\",\"rate\":6,\"action\":\"BUY\",\"notional\":10000,\"tenor\":\"3M\",\"date\":1542651003},{\"id\":8,\"username\":\"alexandra\",\"primaryCcy\":\"EUR\",\"secondaryCcy\":\"USD\",\"rate\":1,\"action\":\"BUY\",\"notional\":25000,\"tenor\":\"1M\",\"date\":1542651110},{\"id\":9,\"username\":\"ana_maria22222\",\"primaryCcy\":\"EUR\",\"secondaryCcy\":\"USD\",\"rate\":2,\"action\":\"BUY\",\"notional\":10000,\"tenor\":\"SP\",\"date\":1701160823}]";
        this.mockMvc.perform(get("/transaction"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));

    }

    @Test
    void makeTransaction() {
    }
}