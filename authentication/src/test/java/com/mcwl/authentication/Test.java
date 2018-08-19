package com.mcwl.authentication;


/**
 * @author Jerry
 * @date 2018/7/25
 * 描述：
 * @description
 */
public class Test {
    public static void main(String[] args) throws Exception {
        String content = "{'repairPhone':'18547854787','customPhone':'12365478965','captchav':'58m7'}";
        System.out.println("加密前：" + content);
        System.out.println("加密密钥和解密密钥：" + AESUtil.KEY);
        String encrypt = AESUtil.encrypt(content, AESUtil.KEY);
        System.out.println("加密后：" + encrypt);
        String decrypt = AESUtil.decrypt(encrypt,AESUtil.KEY);
        System.out.println("解密后：" + decrypt);
    }
}
