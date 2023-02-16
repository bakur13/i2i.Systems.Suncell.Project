package com.i2i.OCSProject.dgwApp.service;

import com.i2i.project.hzoperations.HzOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MSISDNService {

    public List<String> msisdnList=new ArrayList<>();

    HzOperations hzOperations;

    @Autowired
    public MSISDNService() {
        this.hzOperations = new HzOperations();
        hzOperations.Connection("18.193.84.234:5701","MSISDN","dev");

    }

    public ArrayList<String> getMsisdn(){

        return hzOperations.getMsisdnList();
    }
    public int getKey(String msisdn){
        return hzOperations.getPartKeyWithMsisdn(msisdn);
    }

}
