package com.platform.config;

import com.platform.entity.CommissionOrderVo;
import com.platform.entity.OrderVo;
import com.platform.enums.UserLevelEnum;
import com.platform.entity.UserVo;
import com.platform.service.ApiUserService;
import com.platform.utils.JsonUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

import static com.platform.enums.UserLevelEnum.LEVEL_MAP;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/8/28 22:20
 */
@Data
@Component
public class CommissionRule {

    @Autowired
    private ApiUserService apiUserService;

    public CommissionOrderVo getCommition(UserVo userVo, OrderVo orderVo, Integer grade) {
        CommissionOrderVo commissionOrderVo = new CommissionOrderVo();
        commissionOrderVo.setActualPrice(orderVo.getActual_price())
                .setCreateTime(new Date())
                .setOrderSn(orderVo.getOrder_sn())
                .setUserId(userVo.getUserId())
                .setSourceUserId(orderVo.getUser_id());
        return this.processGainBanlance(commissionOrderVo, grade);
    }

    public CommissionOrderVo processGainBanlance(CommissionOrderVo commissionOrderVo, Integer grade) {
        UserVo userSelf = apiUserService.queryObject(commissionOrderVo.getUserId());
        UserVo userSource = apiUserService.queryObject(commissionOrderVo.getSourceUserId());

        UserLevelEnum userSourceLevelEnum = LEVEL_MAP.get(userSource.getUser_level_id());
        UserLevelEnum userSelfLevelEnum = LEVEL_MAP.get(userSelf.getUser_level_id());
        System.out.println("源"+ JsonUtil.getJsonByObj(userSourceLevelEnum));
        System.out.println("本"+ JsonUtil.getJsonByObj(userSelfLevelEnum));
        switch (userSourceLevelEnum) {
            case NORMAL:
                commissionOrderVo.setGainBalance(BigDecimal.ZERO);
                commissionOrderVo.setDetail("普通会员无佣金");
                return commissionOrderVo;
            case VIP:
                switch (userSelfLevelEnum) {
                    case NORMAL:
                        commissionOrderVo.setGainBalance(BigDecimal.ZERO);
                        commissionOrderVo.setDetail("普通会员发展会员无佣金");
                        return commissionOrderVo;
                    case VIP:
                        if (grade == 1) {
                            commissionOrderVo.setGainBalance(BigDecimal.valueOf(5.4d));
                            commissionOrderVo.setDetail("会员发展会员佣金奖励5.4元");
                            return commissionOrderVo;
                        }
                        if (grade == 2) {
                            commissionOrderVo.setGainBalance(BigDecimal.valueOf(3.6d));
                            commissionOrderVo.setDetail("会员下级发展会员奖励佣金3.6元");
                            return commissionOrderVo;
                        }
                        break;
                    case CHUANGKE:
                        if (grade == 1) {
                            commissionOrderVo.setGainBalance(BigDecimal.valueOf(5.4d));
                            commissionOrderVo.setDetail("创客发展会员奖励佣金5.4元");
                            return commissionOrderVo;
                        }
                        if (grade == 2) {
                            commissionOrderVo.setGainBalance(BigDecimal.valueOf(3.6d));
                            commissionOrderVo.setDetail("创客下级发展会员佣金3.6元");
                            return commissionOrderVo;
                        }
                        break;
                    case HEHUOREN:
                        if (grade == 1) {
                            commissionOrderVo.setGainBalance(BigDecimal.valueOf(5.4d));
                            commissionOrderVo.setDetail("合伙人发展会员奖励佣金5.4元");
                            return commissionOrderVo;
                        }
                        if (grade == 2) {
                            commissionOrderVo.setGainBalance(BigDecimal.valueOf(3.6d));
                            commissionOrderVo.setDetail("合伙人下级发展会员奖励佣金3.6元");
                            return commissionOrderVo;
                        }
                    default:
                        break;
                }
            case CHUANGKE:
                switch (userSelfLevelEnum) {
                    case NORMAL:
                        commissionOrderVo.setGainBalance(BigDecimal.ZERO);
                        commissionOrderVo.setDetail("普通会员发展创客无佣金");
                        return commissionOrderVo;
                    case VIP:
                        if (grade == 1) {
                            commissionOrderVo.setGainBalance(BigDecimal.valueOf(5400d));
                            commissionOrderVo.setDetail("会员发展创客奖励佣金5400元");
                            return commissionOrderVo;
                        }
                        if (grade == 2) {
                            commissionOrderVo.setGainBalance(BigDecimal.valueOf(3600d));
                            commissionOrderVo.setDetail("会员下级发展创客奖励佣金3600元");
                            return commissionOrderVo;
                        }
                        break;
                    case CHUANGKE:
                        if (grade == 1) {
                            commissionOrderVo.setGainBalance(BigDecimal.valueOf(5400d));
                            commissionOrderVo.setDetail("创客发展创客奖励佣金5400元");
                            return commissionOrderVo;
                        }
                        if (grade == 2) {
                            commissionOrderVo.setGainBalance(BigDecimal.valueOf(3600d));
                            commissionOrderVo.setDetail("创客下级发展创客奖励佣金3600元");
                            return commissionOrderVo;
                        }
                        break;
                    case HEHUOREN:
                        if (grade == 1) {
                            commissionOrderVo.setGainBalance(BigDecimal.valueOf(5400d));
                            commissionOrderVo.setDetail("合伙人发展创客奖励佣金5400元");
                            return commissionOrderVo;
                        }
                        if (grade == 2) {
                            commissionOrderVo.setGainBalance(BigDecimal.valueOf(3600d));
                            commissionOrderVo.setDetail("合伙人下级发展创客奖励佣金3600元");
                            return commissionOrderVo;
                        }
                    default:
                        break;
                }
            case HEHUOREN:
                switch (userSelfLevelEnum) {
                    case NORMAL:
                        commissionOrderVo.setGainBalance(BigDecimal.ZERO);
                        commissionOrderVo.setDetail("普通会员发展合伙人无佣金");
                        return commissionOrderVo;
                    case VIP:
                        if (grade == 1) {
                            commissionOrderVo.setGainBalance(BigDecimal.valueOf(20000d));
                            commissionOrderVo.setDetail("会员发展合伙人奖励佣金20000元");
                            return commissionOrderVo;
                        }
                        if (grade == 2) {
                            commissionOrderVo.setGainBalance(BigDecimal.ZERO);
                            commissionOrderVo.setDetail("会员下级发展合伙人无奖励");
                            return commissionOrderVo;
                        }
                        break;
                    case CHUANGKE:
                        if (grade == 1) {
                            commissionOrderVo.setGainBalance(BigDecimal.valueOf(20000d));
                            commissionOrderVo.setDetail("创客发展合伙人奖励佣金20000元");
                            return commissionOrderVo;
                        }
                        if (grade == 2) {
                            commissionOrderVo.setGainBalance(BigDecimal.ZERO);
                            commissionOrderVo.setDetail("创客下级发展合伙人无奖励");
                            return commissionOrderVo;
                        }
                        break;
                    case HEHUOREN:
                        if (grade == 1) {
                            commissionOrderVo.setGainBalance(BigDecimal.valueOf(20000d));
                            commissionOrderVo.setDetail("合伙人发展合伙人奖励佣金20000元");
                            return commissionOrderVo;
                        }
                        if (grade == 2) {
                            commissionOrderVo.setGainBalance(BigDecimal.ZERO);
                            commissionOrderVo.setDetail("合伙人下级发展合伙人无奖励");
                            return commissionOrderVo;
                        }
                    default:
                        break;
                }
            default:
                break;
        }
        return commissionOrderVo;
    }

    public static void main(String[] args) {
        CommissionOrderVo commissionOrderVo=new CommissionOrderVo().setOrderSn("!23").setGainBalance(BigDecimal.valueOf(12.3));
        commissionOrderVo.setGainBalance(commissionOrderVo.getGainBalance().add(new BigDecimal(""+12.4)));
        System.out.println(JsonUtil.getJsonByObj(commissionOrderVo));
    }
}
