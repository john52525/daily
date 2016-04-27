package com.kidoo.daily.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class StringUtils {

    /**
     * @param s
     * @return boolean
     */
    public static boolean isEmpty(String s) {
        if (s == null)
            return true;
        if (s.trim().equals(""))
            return true;
        if (s.trim().toLowerCase().equals("null"))
            return true;
        return false;
    }

    public static boolean isEmptys(String... strs) {
        for (String str : strs) {
            if (isEmpty(str))
                return true;
        }
        return false;
    }

    /**
     * URLEncoder
     */
    public static String URLEncode(String s) {
        try {
            return java.net.URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * URLDecoder
     */
    public static String URLDecode(String s) {
        try {
            return java.net.URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }


    /**
     * MD5加码。32位
     * @param inStr
     * @return
     */
    public static String MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];

        byte[] md5Bytes = md5.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();

        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }

        return hexValue.toString();
    }
}
