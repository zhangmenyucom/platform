package com.platform.common.vo;

import lombok.Data;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/9/16 12:53
 */
@Data
public class EncryptedDataBean {
 private String phoneNumber;
 private String purePhoneNumber;
 private String countryCode;
 private WatermarkBean watermark;
}
