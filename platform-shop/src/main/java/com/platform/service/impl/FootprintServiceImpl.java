package com.platform.service.impl;

import com.platform.dao.FootprintDao;
import com.platform.entity.FootprintEntity;
import com.platform.service.FootprintService;
import org.springframework.stereotype.Service;


@Service("footprintService")
public class FootprintServiceImpl extends BaseServiceImpl<FootprintEntity,FootprintDao> implements FootprintService {

}
