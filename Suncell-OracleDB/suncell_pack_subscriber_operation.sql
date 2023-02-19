create or replace PACKAGE        pack_subscriber_operation IS

    FUNCTION login (u_msisdn IN SUBSCRIBER.MSISDN%TYPE, u_password IN SUBSCRIBER.PASSWORD%TYPE) RETURN NUMBER;

    FUNCTION get_subscriber_id RETURN NUMBER;

    FUNCTION get_user_package (p_msisdn SUBSCRIBER.MSISDN%type) RETURN PACKAGE.PACKAGE_NAME%type;

    FUNCTION get_remaining_voice (p_msisdn SUBSCRIBER.MSISDN%type) RETURN NUMBER;

    FUNCTION get_remaining_data (p_msisdn SUBSCRIBER.MSISDN%type) RETURN NUMBER;

    FUNCTION get_remaining_sms (p_msisdn SUBSCRIBER.MSISDN%type) RETURN NUMBER;

    FUNCTION forget_password(p_email IN SUBSCRIBER.EMAIL%TYPE, p_security_question IN SUBSCRIBER.SECURITY_QUESTION%TYPE) RETURN NVARCHAR2;

    PROCEDURE create_subscriber(s_subsc_id IN SUBSCRIBER.SUBSC_ID%TYPE,s_msisdn IN SUBSCRIBER.MSISDN%TYPE, s_name IN SUBSCRIBER.NAME%TYPE, s_surname IN SUBSCRIBER.SURNAME%TYPE, 
                                s_email IN SUBSCRIBER.EMAIL%TYPE, s_password IN SUBSCRIBER.PASSWORD%TYPE,
                                p_security_question IN SUBSCRIBER.SECURITY_QUESTION%TYPE,p_package_id IN PACKAGE_LIST.PACKAGE_ID%TYPE);

END pack_subscriber_operation;