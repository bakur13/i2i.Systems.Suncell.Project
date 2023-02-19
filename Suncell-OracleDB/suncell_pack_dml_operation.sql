create or replace PACKAGE pack_dml_operation IS

    PROCEDURE update_voice(p_msisdn in SUBSCRIBER.MSISDN%type, p_subsc_id in SUBSCRIBER.SUBSC_ID%type, amount in NUMBER);

    PROCEDURE update_data(p_msisdn in SUBSCRIBER.MSISDN%type, p_subsc_id in SUBSCRIBER.SUBSC_ID%type, amount in NUMBER);

    PROCEDURE update_sms(p_msisdn in SUBSCRIBER.MSISDN%type, p_subsc_id in SUBSCRIBER.SUBSC_ID%type, amount in NUMBER);

END pack_dml_operation;