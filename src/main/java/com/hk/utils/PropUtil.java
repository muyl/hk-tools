package com.hk.utils;

import com.hk.core.Const;
import com.hk.core.Prop;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User: hk
 * Date: 2017/8/12 上午9:48
 * version: 1.0
 */
public final class PropUtil {

    private static Prop prop = null;
    private static final ConcurrentHashMap<String, Prop> map = new ConcurrentHashMap<>();

    private PropUtil() {
    }

    /**
     * Using the properties file. It will loading the properties file if not loading.
     * @see #use(String, String)
     * @param fileName fileName loading
     * @return the properties instance
     */
    public static Prop use(String fileName) {
        return use(fileName, Const.DEFAULT_ENCODING);
    }

    /**
     * Using the properties file. It will loading the properties file if not loading.
     * Example:<br>
     * PropKit.use("config.txt", "UTF-8");<br>
     * PropKit.use("other_config.txt", "UTF-8");<br>
     * String userName = PropKit.get("userName");<br>
     * String password = PropKit.get("password");<br>
     * userName = PropKit.use("other_config.txt").get("userName");<br>
     * password = PropKit.use("other_config.txt").get("password");<br>
     *
     * @param fileName loading the properties file
     * @param encoding file encoding
     * @return the properties instance
     */
    public static Prop use(String fileName, String encoding) {
        Prop result = map.get(fileName);
        if (result == null) {
            result = new Prop(fileName, encoding);
            map.put(fileName, result);
            if (PropUtil.prop == null) {
                PropUtil.prop = result;
            }
        }
        return result;
    }

    /**
     * Using the properties file bye File object. It will loading the properties file if not loading.
     *
     * @see #use(File, String)
     * @param file the properties File object
     * @return the properties instance
     */
    public static Prop use(File file) {
        return use(file, Const.DEFAULT_ENCODING);
    }

    /**
     * Using the properties file bye File object. It will loading the properties file if not loading.
     * Example:<br>
     * PropKit.use(new File("/var/config/my_config.txt"), "UTF-8");<br>
     * Strig userName = PropKit.use("my_config.txt").get("userName");
     *
     * @param file     the properties File object
     * @param encoding the encoding
     * @return the properties instance
     */
    public static Prop use(File file, String encoding) {
        Prop result = map.get(file.getName());
        if (result == null) {
            result = new Prop(file, encoding);
            map.put(file.getName(), result);
            if (PropUtil.prop == null) {
                PropUtil.prop = result;
            }
        }
        return result;
    }

    public static Prop useless(String fileName) {
        Prop previous = map.remove(fileName);
        if (PropUtil.prop == previous) {
            PropUtil.prop = null;
        }
        return previous;
    }

    public static void clear() {
        prop = null;
        map.clear();
    }

    public static Prop getProp() {
        if (prop == null) {
            throw new IllegalStateException("Load propties file by invoking PropKit.use(String fileName) method first.");
        }
        return prop;
    }

    public static Prop getProp(String fileName) {
        return map.get(fileName);
    }

    public static String get(String key) {
        return getProp().get(key);
    }

    public static String get(String key, String defaultValue) {
        return getProp().get(key, defaultValue);
    }

    public static Integer getInt(String key) {
        return getProp().getInt(key);
    }

    public static Integer getInt(String key, Integer defaultValue) {
        return getProp().getInt(key, defaultValue);
    }

    public static Long getLong(String key) {
        return getProp().getLong(key);
    }

    public static Long getLong(String key, Long defaultValue) {
        return getProp().getLong(key, defaultValue);
    }

    public static Boolean getBoolean(String key) {
        return getProp().getBoolean(key);
    }

    public static Boolean getBoolean(String key, Boolean defaultValue) {
        return getProp().getBoolean(key, defaultValue);
    }

    public static boolean containsKey(String key) {
        return getProp().containsKey(key);
    }
}
