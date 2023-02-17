package com.i2i.btk.suncell.sf.SfOperations.Kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.i2i.btk.suncell.sf.SfOperations.UpdateDb.UpdateOracledb;

@RestController
@RequestMapping("/api/producer")
@Component
public class KafkaController {
	
	
	UpdateOracledb update;
	
	@Autowired
	public KafkaController(UpdateOracledb update) {
		super();
		this.update = update;
	}



	@KafkaListener(topics = "DenemeTopic3",groupId = "4",containerFactory = "KafkaModelListener")
	public String publish(KafkaModel kafkaModel) {
		System.out.println(kafkaModel.getMsisdn().toString() + " " + kafkaModel.getPartKey()+ " " + kafkaModel.getDataType()+ " " + kafkaModel.getAmount());
		update.update(kafkaModel.getMsisdn(),kafkaModel.getPartKey(), kafkaModel.getDataType(), kafkaModel.getAmount());
		return kafkaModel.getMsisdn();
	}


}
