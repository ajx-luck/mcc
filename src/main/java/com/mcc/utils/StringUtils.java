package com.mcc.utils;

/**
 * Created by B04e on 2018/3/20.
 */
public class StringUtils {

    /**
     * 获取新增的联系人Id
     * @param contactIds
     * @param id
     * @return
     */
    public static String addContactIds(String contactIds, String id) {
        String ids;
        if (isEmpty(contactIds)) {
            ids = id;
        } else if (contactIds.contains(id)) {
            ids = contactIds;
        } else {
            ids = contactIds + "," + id;
        }
        return ids;
    }

    public static boolean isEmpty(String str) {
        return org.thymeleaf.util.StringUtils.isEmptyOrWhitespace(str);
    }

}
