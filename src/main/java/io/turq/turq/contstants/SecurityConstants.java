package io.turq.turq.contstants;

public class SecurityConstants {
    public static final String HEADER_NAME = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final long EXPIRATION_TIME = 60 * 60 * 24 * 7 * 1000; // 7 days
}
