package com.platform.service.impl;

import com.platform.dao.CollectDao;
import com.platform.entity.CollectEntity;
import com.platform.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("collectService")
public class CollectServiceImpl extends BaseServiceImpl<CollectEntity,CollectDao> implements CollectService {

}
