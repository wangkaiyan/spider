package com.spider.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author heyunxia.
 * @Description
 * @time 2015/6/30 14:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptUser implements Serializable{

    private String userId;

    private String userName;
}
