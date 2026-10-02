package com.bishe.zyf.fireextinguisherselling.utils;

public class LoginUserContext {
    // ThreadLocal 存当前线程的userId或者User对象
    private static final ThreadLocal<Long> USER_ID_TL = new ThreadLocal<>();

    public static void setUserId(Long userId) {
        USER_ID_TL.set(userId);
    }

    public static Long getUserId() {
        return USER_ID_TL.get();
    }

    public static void clear() {
        USER_ID_TL.remove();
    }
}
