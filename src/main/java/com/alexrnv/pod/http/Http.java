package com.alexrnv.pod.http;

/**
 * Http related constants and utilities
 */
public class Http {
    public static final int HTTP_CODE_INTERNAL_SERVER_ERROR = 500;
    public static final int HTTP_CODE_BAD_REQUEST = 400;
    public static final int HTTP_CODE_METHOD_NOT_ALLOWED = 405;
    public static final int HTTP_CODE_OK = 200;
    public static final int HTTP_CODE_REDIRECT = 300;

    public static final int HTTP_DEFAULT_PORT = 80;
    public static final int HTTPS_DEFAULT_PORT = 443;

    public static boolean isCodeOk(int code) {
        return code >= HTTP_CODE_OK && code < HTTP_CODE_REDIRECT;
    }
}
