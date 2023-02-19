create or replace PACKAGE BODY pack_subscriber_operation IS
   
   FUNCTION login (u_msisdn IN SUBSCRIBER.MSISDN%TYPE, u_password IN SUBSCRIBER.PASSWORD%TYPE) RETURN NUMBER
    AS
        match_subsc NUMBER;
    BEGIN
        SELECT COUNT(*) INTO match_subsc FROM SUBSCRIBER WHERE MSISDN = u_msisdn AND password = u_password;
        COMMIT;
        IF match_subsc = 0 THEN
            RETURN 0;
        ELSIF match_subsc >= 1 THEN
            RETURN 1;
        END IF;
        EXCEPTION
        WHEN CASE_NOT_FOUND
        THEN RETURN 0;
    END;

    FUNCTION get_subscriber_id RETURN NUMBER 
    AS
        u_id NUMBER;
    BEGIN 
        u_id := subscriber_id_sequencee.nextval;
        COMMIT;
    RETURN u_id ;
    END get_subscriber_id;


    FUNCTION get_user_package(p_msisdn SUBSCRIBER.MSISDN%TYPE) RETURN PACKAGE.PACKAGE_NAME%TYPE
    AS
        v_pack_name PACKAGE.PACKAGE_NAME%TYPE;
    BEGIN
        SELECT (P.PACKAGE_NAME) INTO v_pack_name FROM PACKAGE P INNER JOIN BALANCE B ON P.PACKAGE_ID = B.PACKAGE_ID
                                                    INNER JOIN SUBSCRIBER S ON B.SUBSC_ID = S.SUBSC_ID WHERE S.MSISDN = p_msisdn;
                                                    COMMIT;
        return v_pack_name;
    END;

    FUNCTION get_remaining_voice(p_msisdn SUBSCRIBER.MSISDN%TYPE) RETURN NUMBER
    AS
        remaining_voice number;
    BEGIN
        SELECT (B.BAL_LVL_VOICE) INTO remaining_voice FROM BALANCE B INNER JOIN SUBSCRIBER S ON S.subsc_id = B.subsc_id
                                                    INNER JOIN PACKAGE P ON B.package_id = P.package_id WHERE S.msisdn = p_msisdn;
                                                    COMMIT;
        return remaining_voice;
    END;

    FUNCTION get_remaining_data(p_msisdn SUBSCRIBER.MSISDN%TYPE) RETURN NUMBER
    AS
        remaining_data number;
    BEGIN
        SELECT (B.BAL_LVL_DATA) INTO remaining_data FROM BALANCE B INNER JOIN SUBSCRIBER S ON S.subsc_id = B.subsc_id
                                                    INNER JOIN PACKAGE P ON B.package_id = P.package_id WHERE S.msisdn = p_msisdn;
                                                    COMMIT;
        return remaining_data;
    END;

   FUNCTION get_remaining_sms(p_msisdn SUBSCRIBER.MSISDN%TYPE) RETURN NUMBER
    AS
        remaining_sms number;
    BEGIN
        SELECT (B.BAL_LVL_SMS) INTO remaining_sms FROM BALANCE B INNER JOIN SUBSCRIBER S ON S.subsc_id = B.subsc_id
                                                    INNER JOIN PACKAGE P ON B.package_id = P.package_id WHERE S.msisdn = p_msisdn;
                                                    COMMIT;
        return remaining_sms;
    END;

   FUNCTION forget_password (p_email IN SUBSCRIBER.EMAIL%TYPE, p_security_question IN SUBSCRIBER.SECURITY_QUESTION%TYPE) RETURN NVARCHAR2
    AS
        p_password SUBSCRIBER.PASSWORD%TYPE;
    BEGIN
        SELECT (S.PASSWORD) into p_password FROM SUBSCRIBER S  WHERE  EMAIL = p_email AND SECURITY_QUESTION = p_security_question;
        COMMIT;
        IF p_password IS NULL THEN
            RETURN 'Invalid phone number';
        ELSIF p_password IS NOT NULL THEN
            RETURN p_password;
        END IF;
    END;

     PROCEDURE create_subscriber(s_subsc_id IN SUBSCRIBER.SUBSC_ID%TYPE,s_msisdn IN SUBSCRIBER.MSISDN%TYPE, s_name IN SUBSCRIBER.NAME%TYPE, s_surname IN SUBSCRIBER.SURNAME%TYPE, 
                                s_email IN SUBSCRIBER.EMAIL%TYPE, s_password IN SUBSCRIBER.PASSWORD%TYPE,
                                p_security_question IN SUBSCRIBER.SECURITY_QUESTION%TYPE,p_package_id IN PACKAGE_LIST.PACKAGE_ID%TYPE) AS
        v_package_id number;
        v_package_name nvarchar2(200);
        b_amount_data number;
        b_amount_voice number;
        b_amount_sms number;
        BEGIN

        SELECT package.package_id, package.package_name INTO v_package_id, v_package_name FROM package where package.package_id =  p_package_id; 

        select amount_voice,amount_data,amount_sms into b_amount_voice,b_amount_data,b_amount_sms from package P inner join package_list pl on p.package_id=pl.package_id where pl.package_id=p_package_id;


            INSERT INTO SUBSCRIBER (subsc_id,msisdn,name,surname,email,password,sdate,status,security_question) 
               VALUES(s_subsc_id,s_msisdn,s_name,s_surname,s_email,s_password,SYSDATE,default,p_security_question);
            COMMIT;
            INSERT INTO BALANCE (subsc_id,package_id,bal_lvl_voice, bal_lvl_sms, bal_lvl_data,sdate,edate) 
               VALUES(s_subsc_id, v_package_id, b_amount_voice, b_amount_data, b_amount_sms, SYSDATE, SYSDATE);
            COMMIT;
            INSERT INTO LOGIN (msisdn,password) 
            VALUES(s_msisdn,s_password);
            COMMIT;
        END;

END pack_subscriber_operation;