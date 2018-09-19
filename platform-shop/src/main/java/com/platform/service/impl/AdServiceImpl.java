package com.platform.service.impl;

import com.platform.dao.AdDao;
import com.platform.entity.AdEntity;
import com.platform.service.AdService;
import org.jgroups.blocks.cs.BaseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-19 09:37:35
 */
@Service("adService")
public class AdServiceImpl extends BaseServiceImpl<AdEntity,AdDao> implements AdService {
}
