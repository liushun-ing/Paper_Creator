package com.shun.utils;

import java.util.ArrayList;

public class CheckUtils {
    /**
     * 根据字符串的hashcode进行查重
     *
     * @return 如果重复，返回true；不重复，返回false
     */
    public static boolean checkRepeated(String username, String hashcode) {
        ArrayList<String> hashCodes = FileUtils.readHashcode(username);
        for (String hashCode : hashCodes) {
            if (hashCode.contains(hashcode)) {
                return true;
            }
        }
        return false;
    }
}
