package com.bishe.zyf.fireextinguisherselling.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: zhangyuanfang
 * @CreateTime: 2026-09-30
 * @Description: 订单号创建工具
 */

@Component
public class CreateOrderSN {
    @Value("${order.worker-id}")
    private String WORKER_ID;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    // 同秒内自增序列，原子类保证线程安全
    private final AtomicInteger SEQ = new AtomicInteger(0);
    private String lastSecond = "";

    public synchronized String generateOrderNo() {
        String nowSecond = LocalDateTime.now().format(FORMATTER);
        // 如果进入新的一秒，序列号重置
        if (!nowSecond.equals(lastSecond)) {
            SEQ.set(0);
            lastSecond = nowSecond;
        }
        int seqVal = SEQ.incrementAndGet();
        // 序列固定4位，不足前面补0，最大9999，每秒最多9999个订单
        String seqStr = String.format("%04d", seqVal);
        return nowSecond + WORKER_ID + seqStr;
    }
}
