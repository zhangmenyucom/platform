package com.platform.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
public class EnterpriceToCustomerEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String return_code;
	private String return_msg;
	private String mchid;
	private String nonce_str;
	private String result_code;
	private String partner_trade_no;
	private String payment_no;
	private String payment_time;
	private String err_code;
	private String err_code_des;
}
