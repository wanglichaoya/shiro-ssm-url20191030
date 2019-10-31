package com.wlc.shiroSSM.util;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * describe:
 *
 * @author 王立朝
 * @date 2019/10/31
 */
public class EncodeSaltPasswordUtil {

    public static String  encodedPassword(int times,String algorithmName,String password){
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        String encodedPassword = new SimpleHash(algorithmName,password,salt,times).toString();
        return encodedPassword;
    }
    public static String getSalt(){
        return new SecureRandomNumberGenerator().nextBytes().toString();
    }
}
