package com.hztx.util.util;

import java.util.Stack;

/**
 * This class provides convenient functions to convert hex string to byte array
 * and vice versa.
 *
 * @author 99bill
 */
public class HexUtil {


    private static final String HEX_CHARS = "0123456789abcdef";

    private HexUtil() {
    }

    /**
     * Converts a byte array to hex string.
     *
     * @param b
     *            - the input byte array
     * @return hex string representation of b.
     */

    public static String toHexString(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            sb.append(HexUtil.HEX_CHARS.charAt(b[i] >>> 4 & 0x0F));
            sb.append(HexUtil.HEX_CHARS.charAt(b[i] & 0x0F));
        }
        return sb.toString();
    }

    /**
     * Converts a hex string into a byte array.
     *
     * @param s
     *            - string to be converted
     * @return byte array converted from s
     */
    public static byte[] toByteArray(String s) {
        byte[] buf = new byte[s.length() / 2];
        int j = 0;
        for (int i = 0; i < buf.length; i++) {
            buf[i] = (byte) ((Character.digit(s.charAt(j++), 16) << 4) | Character
                    .digit(s.charAt(j++), 16));
        }
        return buf;
    }


    //进制标准
    private static final String C_CODES_STRING = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";

    /***
     * 将10进制转换为最大进制
     * @param intVal
     * @return
     */
    public static String int2CodeString(int intVal)  {
        return int2CodeString(intVal, C_CODES_STRING.length());
    }
    /***
     * 将10进制转换为任意进制
     * @param intVal
     * @param base <=42
     * @return
     */
    public static String int2CodeString(double intVal, int base)  {

        int w_code_len = C_CODES_STRING.length();
        if (base >w_code_len){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Stack<String> s=new Stack<String>();
        while (intVal!=0){
            s.push(C_CODES_STRING.charAt((int)(intVal%base))+"");
            intVal/=base;
            //System.out.println(s);
        }
        while (!s.empty()){
            sb.append(s.pop());
        }
        return sb.length()==0?"0":sb.toString();
    }
    /***
     *  任何进制转换,
     * @param s
     * @param srcBase s的进制
     * @param destBase 要转换为的进制
     * @return
     */
    public static String BaseConvert(String s, int srcBase, int destBase){
        int point = (int)(Math.random()*28)+1;
        //String front =

        if(srcBase == destBase){
            return s;
        }
        char[] chars = s.toCharArray();
        int len = chars.length;
        if(destBase != 10){//目标进制不是十进制 先转化为十进制
            s = BaseConvert(s,srcBase,10);
        }else{
            long n = 0;
            for(int i = len - 1; i >=0; i--){
                n+=C_CODES_STRING.indexOf(chars[i])* Math.pow(srcBase, len - i - 1);
            }
            return String.valueOf(n);
        }
        return int2CodeString(Double.parseDouble(s),destBase);
    }


}
