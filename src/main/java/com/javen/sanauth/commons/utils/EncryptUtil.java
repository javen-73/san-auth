package com.javen.sanauth.commons.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @author javen73
 * @date 2019/3/6 16:52
 */
public class EncryptUtil {
    private static final String NAMES_DELIMETER = ",";

    /**
     * 散列算法
     */
    public final static String HASH_ALGORITHM_NAME = "MD5";

    /**
     * 循环次数
     */
    public final static int HASH_ITERATIONS = 2;

    /**
     * shiro密码加密工具类
     *
     * @param credentials 密码
     * @param saltSource  密码盐
     * @return
     */
    public static String md5(String credentials, String saltSource) {
        return new SimpleHash(HASH_ALGORITHM_NAME, credentials, saltSource, HASH_ITERATIONS).toHex();
    }

    public static void main(String[] args) {
        String s = md5("123123", "1");
        System.out.println(s);
    }
}
