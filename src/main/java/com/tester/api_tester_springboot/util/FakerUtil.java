package com.tester.api_tester_springboot.util;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Locale;
import java.util.UUID;

/**
 * @description: 生成一些假的测试数据
 * @author: 王海虹
 * @create: 2022-08-02 11:17
 */

public class FakerUtil {
    private static final Faker faker = new Faker(Locale.CHINA);

    private UUID uuid;

    public static String generateName(){
        return faker.name().fullName();
    }

    public static String generateStreetAddress(){
        return faker.address().streetAddress();
    }

    public static String generateCityName(){
        // 返回城市名
        return faker.address().cityName();
    }

    public static String generateIpV4(){
        return faker.internet().ipV4Address();
    }

    public static String generateIpV6(){
        return faker.internet().ipV6Address();
    }

    public static String generateEmail(){
        return faker.internet().emailAddress();
    }

    public static String generateUrl(){
        return faker.internet().url();
    }

    public static String generateMacAddress(){
        return faker.internet().macAddress();
    }

    public static String generatePhone(){
        return faker.phoneNumber().cellPhone();
    }

    public static int getRandomNumber(int min, int max) {
        return faker.number().numberBetween(min, max);
    }


    /**
    *
    * @param: baseNumber: 基础数值， length: 数值长度，
    * @return: 例如：getRandomNumber(240,11), 返回 24002677329691
    * @date: 2022/9/20
    */
    public static String getRandomNumber(String baseNumber, int length) {
        StringBuilder baseNumberBuilder = new StringBuilder(baseNumber);
        for (int i = 0; i < length; i++) {
            int randomNumber = getRandomNumber(0, 10);
            baseNumberBuilder.append(randomNumber);
        }
        baseNumber = baseNumberBuilder.toString();

        return baseNumber;
    }

    public static String getRandomString(String baseString, int length){
        String randomString = RandomStringUtils.randomAlphanumeric(length);
        return baseString+randomString;
    }

    /**
    * 生成订单号
    * @param:
    * @return:
    * @date: 2023/7/26
    */
    public static String generateOrderId(){
        return getRandomString("18", 9);
    }

    public static String generateUUID(){
        return UUID.randomUUID().toString();
    }

    public static void main(String[] args) {
        FakerUtil fakerUtil = new FakerUtil();
        System.out.println(FakerUtil.generatePhone());
        System.out.println(FakerUtil.generateIpV4());

        //System.out.println(fakerUtil.getSubscriberNumber());
        //System.out.println(fakerUtil.getNumberBetween());
        System.out.println(fakerUtil.getRandomNumber("240", 11));
        System.out.println(FakerUtil.generateCityName());
    }
}
