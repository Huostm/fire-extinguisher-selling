package com.bishe.zyf.fireextinguisherselling.vo;

import lombok.Data;

/**
 * @Author: zhangyuanfang
 * @CreateTime: 2026-08-24
 * @Description: 统一返回格式
 */

@Data
public class ResultVO<T> {
    private Integer code;
    private String msg;
    private T data;

    private ResultVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<>(200, "成功", data);
    }

    public static <T> ResultVO<T> success(String msg, T data) {
        return new ResultVO<>(200, msg, data);
    }

    public static <T> ResultVO<T> error(Integer code, String msg) {
        return new ResultVO<>(code, msg, null);
    }

    public static <T> ResultVO<T> error(String msg) {
        return new ResultVO<>(500, msg, null);
    }
}