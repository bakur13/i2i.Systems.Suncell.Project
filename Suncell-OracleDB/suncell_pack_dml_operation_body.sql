create or replace PACKAGE BODY pack_dml_operation IS

    PROCEDURE update_voice(p_msisdn in SUBSCRIBER.MSISDN%type, p_subsc_id in SUBSCRIBER.SUBSC_ID%type, amount in number) IS
    BEGIN
    COMMIT;
      MERGE INTO BALANCE B
        USING (SELECT SUBSC_ID FROM SUBSCRIBER WHERE MSISDN=p_msisdn AND SUBSC_ID=p_subsc_id) S
    ON (B.SUBSC_ID = S.SUBSC_ID)
        WHEN MATCHED THEN
        UPDATE SET B.BAL_LVL_VOICE = B.BAL_LVL_VOICE - amount ;
        COMMIT;
    END;

     PROCEDURE update_data( p_msisdn in SUBSCRIBER.MSISDN%type, p_subsc_id in SUBSCRIBER.SUBSC_ID%type, amount in number) IS
    BEGIN
    COMMIT;
        MERGE INTO BALANCE B
            USING (SELECT SUBSC_ID FROM SUBSCRIBER WHERE MSISDN = p_msisdn AND SUBSC_ID = p_subsc_id) S
            ON (B.SUBSC_ID = S.SUBSC_ID)
        WHEN MATCHED THEN
        UPDATE SET B.BAL_LVL_DATA = B.BAL_LVL_DATA - amount ;
        COMMIT;
    END;

     PROCEDURE update_sms( p_msisdn in SUBSCRIBER.MSISDN%type, p_subsc_id in SUBSCRIBER.SUBSC_ID%type, amount in number) IS
    BEGIN
    COMMIT;
        MERGE INTO BALANCE B
            USING (SELECT SUBSC_ID FROM SUBSCRIBER WHERE MSISDN = p_msisdn AND SUBSC_ID = p_subsc_id ) S
            ON (B.SUBSC_ID = S.SUBSC_ID)
        WHEN MATCHED THEN
        UPDATE SET  B.BAL_LVL_SMS = B.BAL_LVL_SMS - amount ;
        COMMIT;
    END;

END pack_dml_operation;