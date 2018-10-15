package com.platform.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 实体
 * 表名 feedback
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-23 15:03:25
 */

@Data
public class FeedbackEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer msgId;
    //父节点
//    private Integer parentId;
    //会员Id
    private Integer userId;
    //会员名称
    private String userName;
    //会员名称
    private String nickName;

    //移动电话
    private String mobile;
    //邮件
//    private String userEmail;
    //标题
//    private String msgTitle;
    //类型
    private Integer feedType;
    //状态
    private Integer status;
    //详细内容
    private String content;
    //反馈时间
    private Date addTime;
    //图片
}
