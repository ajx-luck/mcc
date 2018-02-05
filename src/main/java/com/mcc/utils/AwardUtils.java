package com.mcc.utils;

/**
 * Created by B04e on 2018/2/5.
 */
public class AwardUtils {
    /**
     * 15 < 400 <= 18% < 1200 <= 21 < 3000
     */
    static Long UNIT = 1000000L;
    static Long GRADE_ONE =  400 * UNIT;
    static Long GRADE_TWO =  1200 * UNIT;
    static Long GRADE_THREE =  3000 * UNIT;
    static Long PRECENT_ONE = 15L;
    static Long PRECENT_TWO = 18L;
    static Long PRECENT_THREE = 21L;


    public static long getBaseAward(long price, long referrerCoin) {
        long awardCoin = 0;
        if (referrerCoin < GRADE_ONE) {
            awardCoin = price / 100 * PRECENT_ONE;
            if(price + referrerCoin > GRADE_ONE){
                awardCoin += getUpGradeAward(price + referrerCoin - GRADE_ONE,GRADE_ONE);
            }
        } else if (referrerCoin < GRADE_TWO) {
            awardCoin = price / 100 * PRECENT_TWO;
        } else if (referrerCoin < GRADE_THREE) {
            awardCoin = price / 100 * PRECENT_THREE;
        } else {
            awardCoin = price / 100 * 21;
        }
        return awardCoin;

    }

    public static long getUpGradeAward(long price,long referrerCoin){
        long awardCoin = 0;
        if (referrerCoin < GRADE_TWO) {
            awardCoin = price / 100 * PRECENT_TWO;
        } else if (referrerCoin < GRADE_THREE) {
            awardCoin = price / 100 * PRECENT_THREE;
        } else {
            awardCoin = price / 100 * 21;
        }
        return awardCoin;
    }


}
