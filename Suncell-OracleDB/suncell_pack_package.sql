create or replace PACKAGE pack_package IS
    PROCEDURE get_all_packs(recordset OUT SYS_REFCURSOR);
    PROCEDURE insert_pack(p_pack_name IN PACKAGE.PACKAGE_NAME%TYPE, p_amount_voice IN PACKAGE.AMOUNT_VOICE%TYPE, p_amount_data IN PACKAGE.AMOUNT_DATA%TYPE, 
                            p_amount_sms IN PACKAGE.AMOUNT_SMS%TYPE, p_duration IN PACKAGE.DURATION%TYPE);
END pack_package;