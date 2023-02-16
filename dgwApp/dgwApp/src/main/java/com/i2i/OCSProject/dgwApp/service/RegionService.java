package com.i2i.OCSProject.dgwApp.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class RegionService {

    List<String> dataList=new ArrayList<>();

    Random random;

    public RegionService() {
        random=new Random();
    }

    public String getRegion(){
        dataList.add("TURKEY");
        dataList.add("NORTHAMERICA");
        dataList.add("EUROPE");
        dataList.add("ASSIA");
        dataList.add("OTHER");

        int number=random.nextInt(dataList.size()+5);
        String data;
        if(number == 0){
            data=dataList.get(0);
        }else if(number == 1){
            data=dataList.get(1);
        }else if(number == 2){
            data=dataList.get(2);
        }else if(number == 3){
            data=dataList.get(3);
        }else if(number == 4){
            data=dataList.get(4);
        }else{
            data=dataList.get(0);
        }
        return data.toString();
    }
}
