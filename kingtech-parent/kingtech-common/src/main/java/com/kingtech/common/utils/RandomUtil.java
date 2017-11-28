package com.kingtech.common.utils;

public class RandomUtil {

    public static int randomNum(int min, int max){
        return (int)(min+Math.random()*(max-min+1));
    }

    public static int randomNumDefault(){
        return randomNum(1000, 4000);
    }

    public static void main(String[] args){
        for(int i=0; i<1000; i++){
            System.out.println(randomNumDefault());
        }
    }
    
    public static String random8Len(){
    	return String.format("%08d", randomNumDefault());
    }
}
