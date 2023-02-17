package com.i2i.ocs.repo;

import org.voltdb.VoltTable;
import org.voltdb.client.*;

import java.io.IOException;

public class VoltDBRepo {
    private String id = "3.85.141.41";
    private int port = 49190;
    private ClientResponse response = null;
    private Client client;
    //Logs logger = new Logs();

    public VoltDBRepo() {
        try {
            this.client = this.getClientVoltDB();
        } catch (IOException var2) {
            var2.printStackTrace();
        } catch (ProcCallException var3) {
            var3.printStackTrace();
        }

    }

    public Client getClientVoltDB() throws IOException, ProcCallException {
        ClientConfig config = new ClientConfig("admin", "admin");
        Client client = ClientFactory.createClient(config);
        client.createConnection(this.id, this.port);
        //this.logger.info("VoltDB Client Granted");
        return client;
    }

    public long getSubscIdByMSISDN(String MSISDN) {
        try {
            this.response = this.client.callProcedure("getMSISDNWithId", new Object[]{MSISDN});
            //this.logger.info("Getting SUBSC_ID By MSISDN");
        } catch (IOException var3) {
            //this.logger.error(var3.getMessage());
            var3.printStackTrace();
        } catch (ProcCallException var4) {
            //his.logger.error(var4.getMessage());
            var4.printStackTrace();
        }

        VoltTable table = this.response.getResults()[0];
        table.advanceRow();
        return table.getLong(0);
    }

    public void sendVoiceAmount(String MSISDN, int usedAmount) {
        try {
            int subscId = (int)this.getSubscIdByMSISDN(MSISDN);
            this.client.callProcedure("updateBalanceVoice", new Object[]{usedAmount, subscId});
            //this.logger.info("Send amount of voice used to VoltDB");
        } catch (IOException var4) {
            //this.logger.error(var4.getMessage());
            var4.printStackTrace();
        } catch (ProcCallException var5) {
            //this.logger.error(var5.getMessage());
            var5.printStackTrace();
        }

    }

    public void sendDataAmount(String MSISDN, int usedAmount) {
        try {
            int subscId = (int)this.getSubscIdByMSISDN(MSISDN);
            this.client.callProcedure("updateBalanceData", new Object[]{usedAmount, subscId});
            //this.logger.info("Send amount of gb used to VoltDB");
        } catch (IOException var4) {
            //this.logger.error(var4.getMessage());
            var4.printStackTrace();
        } catch (ProcCallException var5) {
            //this.logger.error(var5.getMessage());
            var5.printStackTrace();
        }

    }

    public void sendSmsAmount(String MSISDN, int usedAmount) {
        try {
            int subscId = (int)this.getSubscIdByMSISDN(MSISDN);
            this.client.callProcedure("updateBalanceSms", new Object[]{usedAmount, subscId});
            //this.logger.info("Send amount of sms used to VoltDB");
        } catch (IOException var4) {
            //this.logger.error(var4.getMessage());
            var4.printStackTrace();
        } catch (ProcCallException var5) {
            //this.logger.error(var5.getMessage());
            var5.printStackTrace();
        }

    }
}
