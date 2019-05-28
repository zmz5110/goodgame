package org.lastsurprise.goodgame.util;

import org.springframework.util.DigestUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    public static String getMd5(String password,String salt){
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(password).append(salt.substring(8,14));
        String md5= DigestUtils.md5DigestAsHex(stringBuffer.toString().getBytes());
        return md5;
    }

}
