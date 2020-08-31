package com.hztx.admin.filter;

/**
 * @auther xukai
 * @date 2020/3/20.
 */
public class AuthContext {

    private static final ThreadLocal<Certificate> threadLocal=new ThreadLocal<>();

    static void put(Certificate certificate) {
        threadLocal.set(certificate);
    }

    public static Certificate get() {
        return threadLocal.get();
    }

    public static void clear() {
        threadLocal.remove();
    }


}
