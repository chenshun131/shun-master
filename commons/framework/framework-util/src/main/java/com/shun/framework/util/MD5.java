package com.shun.framework.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * User: mew <p />
 * Time: 17/11/6 16:39  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class MD5 {

    private static final String[] strDigits = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a",
            "b", "c", "d", "e", "f"};

    public MD5() {
    }

    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (bByte < 0) {
            iRet = bByte + 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    private static String byteToNum(byte bByte) {
        int iRet = bByte;
        System.out.println("iRet1=" + bByte);
        if (bByte < 0) {
            iRet = bByte + 256;
        }
        return String.valueOf(iRet);
    }

    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; ++i) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    public static String encrypt(String strObj) {
        String resultString = null;
        try {
            new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteToString(md.digest(strObj.getBytes()));
        } catch (NoSuchAlgorithmException var3) {
            var3.printStackTrace();
        }
        return resultString;
    }

    public static void main(String[] args) {
        System.out.println(encrypt("administrator"));
    }

}
