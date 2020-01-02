package com.jhbim.bimvr.utils;

/**
 * @author shuihu
 * @create 2020-01-02 16:37
 */
/**
 * 随机生成群号号
 *
 */
public class PhoneRandom {
    /**
     * 返回手机号码
     */
    private static String[] telFirst = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");

    public static int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }

    public  String getTel() {
        int index = getNum(0, telFirst.length - 1);
        String first = telFirst[index];
        String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
        String third = String.valueOf(getNum(1, 9100) + 10000).substring(1);
        return first + second + third;
    }

    public  void main(String[] args) {
        for (int i = 0; i < 10; i++) { ;
            String tel = getTel();
            System.out.println(tel);
        }
    }
}

