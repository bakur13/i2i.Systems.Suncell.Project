package com.suncell.rest.api.controllers;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.voltdb.client.ProcCallException;

import com.suncell.rest.api.repository.SubscriberRepository;
import com.suncell.rest.api.resources.NewSubscriber;
import com.suncell.rest.api.resources.Subscriber;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/subscribers")
public class SubscriberController {

    SubscriberRepository subscriberRepository=new SubscriberRepository();
    
	
	@GetMapping(value = "/getSubcribers")
    public List<Subscriber> getSubscribers() throws SQLException {
        return subscriberRepository.getSubscribers();
    }
	 @GetMapping(value = "/getSubscriber")
	    public List<Subscriber> getSubscriber(String MSISDN) throws IOException, ProcCallException {
	        return subscriberRepository.getSubscriber(MSISDN);
	    }

	    @PostMapping(value = "/register")
	    public NewSubscriber addSubscriber(@RequestBody NewSubscriber newSubscriber) throws SQLException, IOException, ProcCallException {
	        subscriberRepository.addSubscriberVoltDb(newSubscriber);
	        subscriberRepository.addSubscriberOracleDb(newSubscriber);

	        return newSubscriber;
	    }

	    @PostMapping("/web/register")
	    public NewSubscriber addSubscriberBody(@RequestBody NewSubscriber newSubscriber) throws SQLException, IOException, ProcCallException {
	        subscriberRepository.addSubscriberVoltDb(newSubscriber);
	        subscriberRepository.addSubscriberOracleDb(newSubscriber);

	        return newSubscriber;
	    }
}
