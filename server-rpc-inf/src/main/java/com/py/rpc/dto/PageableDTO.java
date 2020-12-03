package com.py.rpc.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * description：分页对象
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/12/2
 */
@Data
public class PageableDTO<T> implements Serializable {

    private static final long serialVersionUID = 6133982298932079359L;

    /**
     * 起始页
     */
    private Integer pageNum;

    /**
     * 一页大小
     */
    private Integer pageSize;

    /**
     * 数据库中总大小
     */
    private Long total;

    /**
     * 分页数据
     */
    private T data;
}
