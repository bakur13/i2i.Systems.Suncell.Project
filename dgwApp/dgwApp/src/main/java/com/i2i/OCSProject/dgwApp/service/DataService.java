package com.i2i.OCSProject.dgwApp.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class DataService {

    List<String> dataList=new ArrayList<>();
    Random random;

    public DataService() {
        random=new Random();
    }


    public String getData(){

        dataList.add("VOICE");
        dataList.add("DATA");
        dataList.add("SMS");

        String data;
        int number=random.nextInt(dataList.size());
        if(number==0){
            data=dataList.get(0);
        }else if(number == 1){
            data=dataList.get(1);
        }
        else if(number == 2){
            data=dataList.get(2);
        }else{
            return dataList.get(random.nextInt(dataList.size()));
        }
        return data.toString();


    }
}
