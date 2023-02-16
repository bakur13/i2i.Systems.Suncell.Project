package com.i2i.OCSProject.dgwApp.controller;

import com.i2i.OCSProject.dgwApp.entity.Usage;
import com.i2i.OCSProject.dgwApp.logger.Logs;
import com.i2i.OCSProject.dgwApp.service.*;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api")
@ResponseBody
public class DgwController {

    Logs logger =new Logs();
    MSISDNService service;

    DataService dataService;
    AmountService amountService;
    ArrayList<String> msisdnList;
    Random random;

    Usage usage;


    @Autowired
    public DgwController(MSISDNService service, DataService dataService, AmountService amountService,Usage usage) {
        this.service = service;

        this.dataService = dataService;
        this.amountService = amountService;
        this.msisdnList=service.getMsisdn();
        this.usage=usage;
        this.random=new Random();
    }


    @GetMapping("/get")
    public void create() throws URISyntaxException, InterruptedException {

        for(int i=0;i<10;i++){

            HttpHeaders headers=new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            URI uri=new URI("http://ec2-43-206-110-20.ap-northeast-1.compute.amazonaws.com:8080/ocs/usage");
            usage.setMsisdn(msisdnList.get(random.nextInt(msisdnList.size())));
            usage.setPartKey(service.getKey(service.getMsisdn().get(random.nextInt(service.getMsisdn().size()))));
            usage.setDataType(dataService.getData());
            usage.setAmount(amountService.getAmount(dataService.getData()));

            logger.info("MSISDN: "+usage.getMsisdn());
            logger.info("Key: "+usage.getPartKey());
            logger.info("Data: "+usage.getDataType());
            logger.info("Amount: "+usage.getAmount());

            HttpEntity<Usage> httpEntity=new HttpEntity<>(usage,headers);
            RestTemplate restTemplate=new RestTemplate();

            String response=restTemplate.postForObject(uri,httpEntity,String.class);
            logger.info(response);
            Thread.sleep(5000);


        }



    }

}
