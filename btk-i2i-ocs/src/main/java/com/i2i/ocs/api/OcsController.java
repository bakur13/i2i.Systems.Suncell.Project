package com.i2i.ocs.api;

import com.i2i.btk.suncell.sf.SfOperations.Kafka.KafkaModel;
import com.i2i.ocs.model.Usage;
import com.i2i.ocs.service.OcsService;
import jdk.jpackage.internal.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/ocs")
@Component
public class OcsController {

    @Autowired
    OcsService ocsService;
    @Autowired
    KafkaTemplate<String,KafkaModel> KafkaJsontemplate;
    String TOPIC_NAME = "DenemeTopic3";

    @PostMapping(value = "/usage",consumes = {"application/json"},produces = {"application/json"})
    public String userUsage(@RequestBody KafkaModel kafkaModel) {
        ocsService.userUsage(kafkaModel);
        KafkaJsontemplate.send(TOPIC_NAME,new KafkaModel(kafkaModel.getMsisdn(),kafkaModel.getPartKey(),kafkaModel.getDataType(),kafkaModel.getAmount()));
        KafkaJsontemplate.flush();
        Log.info("Kafkaya post isteği atıldı.");
        return "Transaction successful";
    }

}


