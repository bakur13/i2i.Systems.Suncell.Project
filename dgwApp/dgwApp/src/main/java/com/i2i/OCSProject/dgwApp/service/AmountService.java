package com.i2i.OCSProject.dgwApp.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AmountService {

    Random random;

    final int voiceUsage=360;
    final int smsUsage=1;
    final int netUsage=1024;

    public AmountService() {
        random=new Random();
    }

    public int getAmount(String data){
        if(data.equals("VOICE")){
            int number=random.nextInt(voiceUsage);
            return number;
        }else if(data.equals("DATA")){
            int number=random.nextInt(netUsage);
            return number;
        }else if(data.equals("SMS")){
            return smsUsage;
        }
        return -1;


    }
}
