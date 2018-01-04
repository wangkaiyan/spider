package com.spider.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author heyunxia.
 * @Description
 * @time 2015/6/21 14:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntityBaseResponse<T> extends BaseResponse {

    private T entity;
}
