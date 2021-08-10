package com.iwantunlimited.utils;

import java.util.Random;

public class StringHelper {

    public static String createRandomCode(int codeLength){
        char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < codeLength; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        return output.toUpperCase();
    }
    public static void main(String[] args){
        String random = createRandomCode(6);
        System.out.println(random);
    }
}
