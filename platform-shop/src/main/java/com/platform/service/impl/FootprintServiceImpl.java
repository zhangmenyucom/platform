package com.platform.service.impl;

import com.platform.dao.FootprintDao;
import com.platform.entity.FootprintEntity;
import com.platform.service.FootprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("footprintService")
public class FootprintServiceImpl extends BaseServiceImpl<FootprintEntity,FootprintDao> implements FootprintService {

}
