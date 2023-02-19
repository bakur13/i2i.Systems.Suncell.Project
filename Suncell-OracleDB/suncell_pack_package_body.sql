create or replace PACKAGE BODY pack_package IS
    PROCEDURE get_all_packs(recordset OUT SYS_REFCURSOR) IS 
        BEGIN
        open recordset for
            SELECT P.package_id , P.package_name FROM PACKAGE P;
            COMMIT;
        END;

    PROCEDURE insert_pack(p_pack_name IN PACKAGE.PACKAGE_NAME%TYPE, p_amount_voice IN PACKAGE.AMOUNT_VOICE%TYPE, p_amount_data IN PACKAGE.AMOUNT_DATA%TYPE, 
                            p_amount_sms IN PACKAGE.AMOUNT_SMS%TYPE, p_duration IN PACKAGE.DURATION%TYPE) IS
        BEGIN
            INSERT INTO PACKAGE (PACKAGE_ID,PACKAGE_NAME,AMOUNT_VOICE,AMOUNT_DATA,AMOUNT_SMS,DURATION) 
                    VALUES (package_id_sequence.nextval, p_pack_name, p_amount_voice, p_amount_data * 1024, p_amount_sms, p_duration);
            COMMIT;

            INSERT INTO PACKAGE_LIST (PACKAGE_ID,PACKAGE_NAME) VALUES(package_id_sequence.nextval, p_pack_name);
            COMMIT;
        END;
END pack_package;