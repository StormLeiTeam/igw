package com.igw.igw.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by leilei on 2019/3/8.
 */

public class ConfigUtil {

    static private final String m_configFile = "/assets/config.properties";

    static private ConfigUtil m_instance = null;

    public static ConfigUtil getInstance() {
        if (m_instance == null) {
            m_instance = new ConfigUtil(m_configFile);
        }
        return m_instance;
    }

    private Properties prop;

    public ConfigUtil(final String name) {
        prop = new Properties();
        InputStream in = null;
        try {
            in = ConfigUtil.class.getResourceAsStream(name);
            prop.load(in);
        } catch (IOException e) {
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getProperty(String name) {
        return getProperty(name, null);
    }

    private String getProperty(String name, String fallbackValue) {
        String value = null;
        try {
            value = System.getProperty(name);
        } catch (Exception e) {
        }
        try {
            if (value == null) {
                value = prop.getProperty(name);
                if (value != null) {
                    value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
                }
            }
        } catch (Exception e) {
        }
        if (value == null) {
            value = fallbackValue;
        }
        return replace(value);
    }

    public boolean getBoolean(String name) {
        return getBoolean(name, false);
    }

    private boolean getBoolean(String name, boolean fallbackValue) {
        String value = getProperty(name);
        if (value == null) {
            return fallbackValue;
        }
        return Boolean.valueOf(value);
    }

    public long getNumber(String name) {
        return getNumber(name, -1);
    }

    private long getNumber(String name, long fallbackValue) {
        String value = getProperty(name, String.valueOf(fallbackValue));
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }

    private String replace(String value) {
        if (value == null) {
            return null;
        }

        int openBrace = value.indexOf("{");
        if (openBrace == -1) {
            return value;
        }
        int closeBrace = value.indexOf("}", openBrace);
        if (closeBrace == -1) {
            return value;
        }

        String name = value.substring(openBrace + 1, closeBrace);
        value = value.substring(0, openBrace) + getProperty(name, "") + value.substring(closeBrace + 1);
        return replace(value);
    }

}
