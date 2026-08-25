package com.bishe.zyf.fireextinguisherselling.vo;

import lombok.Data;
import java.util.List;

@Data
public class PageResultVO<T> {
    private Long total;      // 总记录数
    private Long pages;      // 总页数
    private Long current;    // 当前页
    private Long size;       // 每页大小
    private List<T> list;    // 数据列表
}