package com.spider.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author heyunxia.
 * @Description
 * @time 2015/7/1 10:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCouponRequest implements Serializable {
    /**
     * 用户mid
     */
    private String mid;
    /**
     * 属性社区
     */
    private Long projectId;
}
