package com.platform.annotation;

import java.lang.annotation.*;

/**
 * 数据过滤
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017年10月23日 下午13:13:23
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MerchantFilter {
}
