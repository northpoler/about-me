package pro.jianbing.aboutme.common.util;

/**
 * 加密/解密的工具
 *
 * @author 李建兵
 */
public final class EncryptionUtil {
    /**
     * 加密
     * @param str
     * @return
     */
    public static String encrypt(String str) {
        str = cnToUnicode(str);
        str = str.replaceAll("\\\\u","q");
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if ('1'==chars[i]){
                chars[i] = 'm';
            } else if ('2' == chars[i]){
                chars[i] = 'n';
            } else if ('3' == chars[i]){
                chars[i] = 'b';
            } else if ('4' == chars[i]){
                chars[i] = 'v';
            } else if ('5' == chars[i]){
                chars[i] = 'c';
            } else if ('6' == chars[i]){
                chars[i] = 'x';
            } else if ('7' == chars[i]){
                chars[i] = 'z';
            } else if ('8' == chars[i]){
                chars[i] = 'l';
            } else if ('9' == chars[i]){
                chars[i] = 'k';
            } else if ('0' == chars[i]){
                chars[i] = 'h';
            } else if ('a' == chars[i]){
                chars[i] = '0';
            } else if ('b' == chars[i]){
                chars[i] = '9';
            } else if ('c' == chars[i]){
                chars[i] = '8';
            } else if ('d' == chars[i]){
                chars[i] = '7';
            } else if ('e' == chars[i]){
                chars[i] = '6';
            } else if ('f' == chars[i]){
                chars[i] = '5';
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (char e:chars){
            stringBuilder.append(e);
        }
        return stringBuilder.toString();
    }

    /**
     * 解密
     * @param str
     * @return
     */
    public static String decrypt(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if ('m' == chars[i]){
                chars[i] = '1';
            } else if ('n' == chars[i]){
                chars[i] = '2';
            } else if ('b' == chars[i]){
                chars[i] = '3';
            } else if ('v' == chars[i]){
                chars[i] = '4';
            } else if ('c' == chars[i]){
                chars[i] = '5';
            } else if ('x' == chars[i]){
                chars[i] = '6';
            } else if ('z' == chars[i]){
                chars[i] = '7';
            } else if ('l' == chars[i]){
                chars[i] = '8';
            } else if ('k' == chars[i]){
                chars[i] = '9';
            } else if ('h' == chars[i]){
                chars[i] = '0';
            } else if ('0' == chars[i]){
                chars[i] = 'a';
            } else if ('9' == chars[i]){
                chars[i] = 'b';
            } else if ('8' == chars[i]){
                chars[i] = 'c';
            } else if ('7' == chars[i]){
                chars[i] = 'd';
            } else if ('6' == chars[i]){
                chars[i] = 'e';
            } else if ('5' == chars[i]){
                chars[i] = 'f';
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (char e:chars){
            stringBuilder.append(e);
        }
        str = stringBuilder.toString().replaceAll("q","\\\\u");
        return unicodeToCn(str);
    }

    /**
     * 字符转Unicode
     * @param cn
     * @return
     */
    private static String cnToUnicode(String cn) {
        char[] chars = cn.toCharArray();
        String returnStr = "";
        for (int i = 0; i < chars.length; i++) {
            returnStr += "\\u" + Integer.toString(chars[i], 16);
        }
        return returnStr;
    }

    /**
     * Unicode转字符
     * @param unicode
     * @return
     */
    private static String unicodeToCn(String unicode) {
        // 以 \ u 分割，因为java注释也能识别unicode，因此中间加了一个空格
        String[] strs = unicode.split("\\\\u");
        StringBuilder returnStr = new StringBuilder();
        // 由于unicode字符串以 \ u 开头，因此分割出的第一个字符是""。
        for (int i = 1; i < strs.length; i++) {
            returnStr.append((char) Integer.valueOf(strs[i], 16).intValue());
        }
        return returnStr.toString();
    }

}
