package com.andy.baselibrary.utils;

import android.util.Log;

public class LogUtil {

    private final static String DEFAULT_TAG = "LogTag";

    public final static int LOG_LEVEL = LogLevel.getLevel("VERBOSE");

    private final static int LOG_LENGTH = 3500;

    protected enum LogLevel {

        VERBOSE(0, "VERBOSE"), DEBUG(1, "DEBUG"), INFO(2, "INFO"), WARN(3, "WARN"), ERROR(4, "ERROR");

        private int value;

        private String name;

        public int getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        LogLevel(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public static int getLevel(String name) {
            int v = 1;
            for (LogLevel l : LogLevel.values()) {
                if (name.equals(l.getName())) {
                    v = l.value;
                    break;
                }
            }
            return v;
        }
    }

    private static void level(LogLevel level, String tag, String msg, Throwable tr) {
        switch (level) {
            case DEBUG:
                if (tr == null) Log.d(tag, msg);
                else Log.d(tag, msg, tr);
                break;
            case WARN:
                if (tr == null) Log.w(tag, msg);
                else Log.w(tag, msg, tr);
                break;
            case ERROR:
                if (tr == null) Log.e(tag, msg);
                else Log.e(tag, msg, tr);
                break;
            default:
                if (tr == null) Log.i(tag, msg);
                else Log.i(tag, msg, tr);
        }
    }

    private static void log(LogLevel level, String tag, String msg, Throwable tr) {
        if(level.value >= LOG_LEVEL){
            int len = msg.length();
            if (len < LOG_LENGTH) {
                level(level, tag, msg, tr);
            } else {
                int i = 0;
                while (i <= len) {
                    if ((i + LOG_LENGTH) <= len) {
                        level(level, tag, msg.substring(i, i + LOG_LENGTH), tr);
                    } else {
                        level(level, tag, msg.substring(i, len), tr);
                    }
                    i += LOG_LENGTH;
                }
            }
        }
    }

    public static void v(String msg) {
        log(LogLevel.VERBOSE, DEFAULT_TAG, msg, null);
    }

    public static void d(String msg) {
        log(LogLevel.DEBUG, DEFAULT_TAG, msg, null);
    }

    public static void i(String msg) {
        log(LogLevel.INFO, DEFAULT_TAG, msg, null);

    }

    public static void w(String msg) {
        log(LogLevel.WARN, DEFAULT_TAG, msg, null);
    }

    public static void e(String msg) {
        log(LogLevel.ERROR, DEFAULT_TAG, msg, null);
    }

    public static void e(String msg, Throwable tr) {
        log(LogLevel.ERROR, DEFAULT_TAG, msg, tr);
    }


    public static void i(String tag, String msg) {
        log(LogLevel.INFO, tag, msg, null);
    }

    public static void i(String tag, String msg, Throwable tr) {
        log(LogLevel.INFO, DEFAULT_TAG + tag, msg, tr);
    }

    public static void d(String tag, String msg) {
        log(LogLevel.DEBUG, DEFAULT_TAG + tag, msg, null);
    }

    public static void d(String tag, String msg, Throwable tr) {
        log(LogLevel.DEBUG, tag, msg, tr);
    }

    public static void w(String tag, String msg) {
        log(LogLevel.WARN, DEFAULT_TAG + tag, msg, null);
    }

    public static void e(String tag, String msg) {
        log(LogLevel.ERROR, DEFAULT_TAG + tag, msg, null);
    }

    public static void e(String tag, String msg, Throwable tr) {
        log(LogLevel.ERROR, DEFAULT_TAG + tag, msg, tr);
    }


}