package com.suncell.rest.api.dbHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class HzOperations implements Serializable{
	
	private ClientConfig clientConfig = new ClientConfig();
	private HazelcastInstance client;
	private IMap<String, ObjectValue> map;
	
	public void Connection(String ipAddress,String mapName,String clusterName) {
		this.clientConfig.setClusterName(clusterName);
		this.clientConfig.getNetworkConfig().addAddress(ipAddress);
		this.client = HazelcastClient.newHazelcastClient(clientConfig);
		this.map = client.getMap(mapName);
	}
	
	public void insertMsisdn(String msisdn,int partKey,String actDate) {
		ObjectValue objectValue = new ObjectValue(msisdn,partKey,actDate);
		this.map.put(msisdn, objectValue);
		
	}
	
	public ArrayList<String> getMsisdnList(){
		Set<String> msisdnList = map.keySet();
		
		
        ArrayList<String> arrayList = new ArrayList<String>();
        
        for(String listObject : msisdnList) {
        	arrayList.add(listObject);
        }
        
        return arrayList;

	}
	
	public ObjectValue getValueWithMsisdn(String msisdn){
		Set<Entry<String,ObjectValue>> objectValues = map.entrySet();
		for(Map.Entry<String, ObjectValue> listItem : objectValues) {
			if(msisdn==listItem.getValue().getMsisdn()) {
				return listItem.getValue();
			}
		}
		return null;
		
	}
	
	public int getPartKeyWithMsisdn(String msisdn) {
		Set<Entry<String,ObjectValue>> objectValues = map.entrySet();
		for(Map.Entry<String, ObjectValue> listItem : objectValues) {
			if(msisdn.equals(listItem.getValue().getMsisdn())) {
				return listItem.getValue().getPartKey();
			}
		}
		return 0;
	}
	
	public void closeConnection() {
		client.shutdown();
	}

}
