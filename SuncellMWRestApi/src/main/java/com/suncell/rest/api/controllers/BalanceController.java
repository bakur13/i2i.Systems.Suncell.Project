package com.suncell.rest.api.controllers;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.voltdb.client.ProcCallException;

import com.suncell.rest.api.repository.BalanceRepository;
import com.suncell.rest.api.resources.RemainingBalanceForUser;
import com.suncell.rest.api.resources.TotalBalanceForAllUsers;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api/balances/")
public class BalanceController
{
	 Logger logger =LoggerFactory.getLogger(this.getClass());


    BalanceRepository balanceRepository = new BalanceRepository();

	@ApiOperation(value = "get all Subcriber's balances")
    @GetMapping(value = "AllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TotalBalanceForAllUsers> getBalances() throws SQLException {
		logger.info("Balance ları listeleme istegi atıldı.");
    return balanceRepository.getBalances();
    }
    @GetMapping(value = "balanceByMSISDNinList", produces = MediaType.APPLICATION_JSON_VALUE)
    public RemainingBalanceForUser getBalanceForUser(String MSISDN) throws IOException, ProcCallException {
        BalanceRepository balanceForUserRepository = new BalanceRepository();
        return balanceForUserRepository.getBalanceByMSISDNinList(MSISDN);
    }
   
	
}
