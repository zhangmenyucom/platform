package com.platform.entity;/**
 * ${author} on 2018/8/30.
 */

import lombok.Data;

import java.util.List;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/8/30 9:30
 */
@Data
public class MailCompanyResponse {
    private String comCode;
    private String num;
    private List<MailCompany> auto;
}
