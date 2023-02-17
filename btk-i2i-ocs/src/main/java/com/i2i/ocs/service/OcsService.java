package com.i2i.ocs.service;

import com.i2i.btk.suncell.sf.SfOperations.Kafka.KafkaModel;
import com.i2i.ocs.repo.VoltDBRepo;
import jdk.jpackage.internal.Log;
import org.springframework.stereotype.Service;

@Service
public class OcsService {
    private VoltDBRepo voltDBRepo;
    //private VoltDBRepo voltDBRepo;

    public OcsService(){
        voltDBRepo = new VoltDBRepo();
    }
    public void userUsage(KafkaModel kafkaModel){
        if(kafkaModel.getDataType().equalsIgnoreCase("voice")){
            voltDBRepo.sendVoiceAmount(kafkaModel.getMsisdn(), kafkaModel.getAmount());
        } else if (kafkaModel.getDataType().equalsIgnoreCase("sms")) {
            voltDBRepo.sendSmsAmount(kafkaModel.getMsisdn(), kafkaModel.getAmount());
        }else if(kafkaModel.getDataType().equalsIgnoreCase("data")){
            voltDBRepo.sendDataAmount(kafkaModel.getMsisdn(), kafkaModel.getAmount());
        }else{
            Log.info("Beklenmeyen bir servis ismi geldi.");
        }



    }
}
