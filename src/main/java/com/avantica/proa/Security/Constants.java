package com.avantica.proa.Security;

public class Constants {
    //JWT
    public static final long TOKEN_EXPIRATION_TIME = 864_000_000;
    public static final String SECRET_KEY = "AvanticaIsTheBestSomeRandomStuffToMakeItLonger";

    //Spring security
    public static final String HEADER_AUTHORIZATION_KEY =  "Authorization";
    public static final String TOKEN_BEARER_PREFIX = "Bearer";
    public static final String LOGIN_URL = "/login";
}
