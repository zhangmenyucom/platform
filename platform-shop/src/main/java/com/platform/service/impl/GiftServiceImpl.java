package com.platform.service.impl;

import com.platform.dao.GiftDao;
import com.platform.entity.GiftEntity;
import com.platform.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-09-12 18:50:18
 */
@Service("giftService")
public class GiftServiceImpl extends BaseServiceImpl<GiftEntity,GiftDao> implements GiftService {

}
