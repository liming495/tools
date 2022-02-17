package com.githup.liming495.utils;

import java.text.SimpleDateFormat;

/**
 * 时间格式
 *
 * @author Guppy
 */
public enum DateFormat {
    CT_M(DateStyle.CT_M),
    CT_D(DateStyle.CT_D),
    CT_H(DateStyle.CT_H),
    CT_MM(DateStyle.CT_MM),
    CT_S(DateStyle.CT_S),
    CT_SS(DateStyle.CT_SS),
    CT_SSS(DateStyle.CT_SSS),

    CP_M(DateStyle.CP_M),
    CP_D(DateStyle.CP_D),
    CP_H(DateStyle.CP_H),
    CP_MM(DateStyle.CP_MM),
    CP_S(DateStyle.CP_S),
    CP_SS(DateStyle.CP_SS),
    CP_SSS(DateStyle.CP_SSS),

    CN_M(DateStyle.CN_M),
    CN_D(DateStyle.CN_D),
    CN_H(DateStyle.CN_H),
    CN_MM(DateStyle.CN_MM),
    CN_S(DateStyle.CN_S),
    CN_SS(DateStyle.CN_SS),
    CN_SSS(DateStyle.CN_SSS);

    private SimpleDateFormat sdf;

    private DateFormat(String sdfString){
        this.sdf = new SimpleDateFormat(sdfString);
    }
    public SimpleDateFormat getSdf() {
        return this.sdf;
    }
}
