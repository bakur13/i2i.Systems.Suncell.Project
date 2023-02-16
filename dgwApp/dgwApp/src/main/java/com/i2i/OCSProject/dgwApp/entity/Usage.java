package com.i2i.OCSProject.dgwApp.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hazelcast.org.json.JSONPropertyIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Getter
@Setter

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Service
public class Usage implements Serializable {

    private String msisdn;
    private int partKey;
    private String dataType;
    private int amount;

    public Usage() {
    }

    public Usage(String msiSdn, int subId, String data, int amount) {
        this.msisdn = msiSdn;
        this.partKey = subId;
        this.dataType = data;
        this.amount = amount;
    }
}
