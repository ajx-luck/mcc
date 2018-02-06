package com.mcc.utils;

/**
 * Created by B04e on 2018/2/5.
 */
public class AwardUtils {
    /**
     * 10 < 400 <= 13% < 1200 <= 16 < 3000 <= 19
     */
    static Long UNIT = 1000000L;
    static Long GRADE_ONE = 400 * UNIT;
    static Long GRADE_TWO = 1200 * UNIT;
    static Long GRADE_THREE = 3000 * UNIT;
    static Long PRECENT_BASE = 10L;
    static Long PRECENT_ONE = 3L;
    static Long PRECENT_TWO = 3L;
    static Long PRECENT_THREE = 3L;

    /**
     * 推荐奖励
     * @param price
     * @param referrerCoin
     * @return
     */
    public static long getRecommendAward(long price, long referrerCoin) {
        long awardCoin = price / 100 * PRECENT_BASE;
        long temp = price + referrerCoin;
        if (temp >= GRADE_ONE ) {
            if(referrerCoin < GRADE_ONE) {
                awardCoin += (price + referrerCoin - GRADE_ONE) / 100 * PRECENT_ONE;
            }else{
                awardCoin += price / 100 * PRECENT_ONE;
            }
        }
        if(temp >= GRADE_TWO ){
            if(referrerCoin < GRADE_TWO) {
                awardCoin += (price + referrerCoin - GRADE_TWO) / 100 * PRECENT_TWO;
            }else{
                awardCoin += price / 100 * PRECENT_TWO;
            }
        }
        if(temp >= GRADE_THREE){
            if(referrerCoin < GRADE_THREE) {
                awardCoin += (price + referrerCoin - GRADE_THREE) / 100 * PRECENT_THREE;
            }else{
                awardCoin += price / 100 * PRECENT_THREE;
            }
        }
        return awardCoin;

    }



}
