package com.i2i.btk.suncell.sf.SfOperations.Kafka;

import java.time.Duration;

import org.apache.kafka.clients.consumer.ConsumerRecords;

public class KafkaModel {
	
	private String MSISDN;
	private int partKey;
	private String dataType;
	private int amount;
	
	public KafkaModel() {
		
	}
	
	public KafkaModel(String MSISDN, int partKey, String dataType, int amount) {
		super();
		this.MSISDN = MSISDN;
		this.partKey = partKey;
		this.dataType = dataType;
		this.amount = amount;
	}

	public String getMsisdn() {
		return MSISDN;
	}

	public void setMsisdn(String MSISDN) {
		this.MSISDN = MSISDN;
	}

	public int getPartKey() {
		return partKey;
	}

	public void setPartKey(int partKey) {
		this.partKey = partKey;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}


	public void commitAsync() {

		
	}

	public ConsumerRecords<Long, KafkaModel> poll(Duration ofSeconds) {
		// TODO Auto-generated method stub
		return null;
	}

}
