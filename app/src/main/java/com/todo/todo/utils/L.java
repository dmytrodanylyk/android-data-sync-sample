package com.todo.todo.utils;

import android.util.Log;

public final class L {

    private static final String LOG_TAG = "TODO";
    
    private static final boolean DEBUG = true;

    public static void e(String message, Throwable cause) {
        if (DEBUG) {
            Log.e(LOG_TAG, "[" + message + "]", cause);
        }
    }

    public static void e(String msg) {
        if (DEBUG) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();

            String callerClassName = elements[1].getFileName();
            Log.e(LOG_TAG, "[" + callerClassName + "] " + msg);
        }
    }

    public static void w(String message, Throwable cause) {
        if (DEBUG) {
            Log.w(LOG_TAG, "[" + message + "]", cause);
        }
    }

    public static void w(String msg) {
        if (DEBUG) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();

            String callerClassName = elements[1].getFileName();
            Log.w(LOG_TAG, "[" + callerClassName + "] " + msg);
        }
    }

    public static void i(String message, Throwable cause) {
        if (DEBUG) {
            Log.i(LOG_TAG, "[" + message + "]", cause);
        }
    }

    public static void i(String msg) {
        if (DEBUG) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();

            String callerClassName = elements[1].getFileName();
            Log.i(LOG_TAG, "[" + callerClassName + "] " + msg);
        }
    }

    public static void i(String msg, Object... args) {
        if (DEBUG) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();

            String callerClassName = elements[1].getFileName();
            Log.i(LOG_TAG, "[" + callerClassName + "] " + String.format(msg, args));
        }
    }

    public static void d(String msg, Throwable cause) {
        if (DEBUG) {
            Log.d(LOG_TAG, msg, cause);
        }
    }

    public static void d(String msg) {
        if (DEBUG) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();

            String callerClassName = elements[1].getFileName();
            Log.d(LOG_TAG, "[" + callerClassName + "] " + msg);
        }
    }

    public static void v(String msg, Throwable cause) {
        if (DEBUG) {
            Log.v(LOG_TAG, msg, cause);
        }
    }

    public static void v(String msg) {
        if (DEBUG) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();

            String callerClassName = elements[1].getFileName();
            Log.v(LOG_TAG, "[" + callerClassName + "] " + msg);
        }
    }

    public static void v(String format, Object... args) {
        if (DEBUG) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();

            String callerClassName = elements[1].getFileName();
            Log.v(LOG_TAG, "[" + callerClassName + "] " + String.format(format, args));
        }
    }
}
