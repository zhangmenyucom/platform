package com.platform.service.impl;

import com.platform.dao.CollectDao;
import com.platform.entity.CollectEntity;
import com.platform.service.CollectService;
import org.springframework.stereotype.Service;


@Service("collectService")
public class CollectServiceImpl extends BaseServiceImpl<CollectEntity,CollectDao> implements CollectService {

}
