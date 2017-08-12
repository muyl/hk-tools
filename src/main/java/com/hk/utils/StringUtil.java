package com.hk.utils;

/**
 * User: hk
 * Date: 2017/8/12 上午7:11
 * version: 1.0
 */
public class StringUtil {

    public static final String EMPTY = "";

    /**
     * <p>删除空格/空值转换为空字符串</p>
     *    <pre>
     *         StringUtils.trimToEmpty(null)          = ""
     *         StringUtils.trimToEmpty("")            = ""
     *         StringUtils.trimToEmpty("     ")       = ""
     *         StringUtils.trimToEmpty("abc")         = "abc"
     *         StringUtils.trimToEmpty("    abc    ") = "abc"
     *    </pre>
     * @param str the String to be trimmed, may be null
     * @return the trimmed String, or an empty String if {@code null} input
     */
    public static String trimToEmpty(final String str){
        return str == null ? EMPTY : str.trim();
    }

    /**
     * <p>Checks if a CharSequence is empty ("") or null.</p>
     * <pre>
     *    StringUtils.isEmpty(null)      = true
     *    StringUtils.isEmpty("")        = true
     *    StringUtils.isEmpty(" ")       = false
     *    StringUtils.isEmpty("bob")     = false
     *    StringUtils.isEmpty("  bob  ") = false
     * </pre>
     * @param cs the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is empty or null
     */
    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * <p>Checks if a CharSequence is empty (""), null or whitespace only.</p>
     * <pre>
     *  StringUtils.isBlank(null)      = true
     *  StringUtils.isBlank("")        = true
     *  StringUtils.isBlank(" ")       = true
     *  StringUtils.isBlank("bob")     = false
     *  StringUtils.isBlank("  bob  ") = false
     * </pre>
     * @param cs the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is null, empty or whitespace only
     */
    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }


}
