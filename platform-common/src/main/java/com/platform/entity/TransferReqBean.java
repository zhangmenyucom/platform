package com.platform.entity;


import lombok.Data;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/10/8 22:03
 */
@Data
public class TransferReqBean {
    private Long withdrawOrderId;
    private Long merchantId;
    private String openId;
    private String realName;
    private int amount;
    private String desc;
    private boolean needCheckName;
}
