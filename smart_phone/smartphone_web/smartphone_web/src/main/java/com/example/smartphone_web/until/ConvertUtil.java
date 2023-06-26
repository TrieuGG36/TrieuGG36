package com.example.smartphone_web.until;

import com.example.smartphone_web.constant.ConstansErrorCode;
import com.example.smartphone_web.exception.SmartPhoneExp;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

@Component
public class ConvertUtil {
    private static ConvertUtil convertUtil;

    private ConvertUtil() {
    }

    public static ConvertUtil get() {
        if (convertUtil == null) {
            convertUtil = new ConvertUtil();
        }
        return convertUtil;
    }

    public Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
        return java.sql.Timestamp.valueOf(dateToConvert);
    }


    public String moneyToStringFormat(Long price){
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        return currencyVN.format(price);
    }

    public Date strToDate(String startDate, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new SmartPhoneExp(ConstansErrorCode.ERROR_DATA_REQUEST);
        }
    }




}
