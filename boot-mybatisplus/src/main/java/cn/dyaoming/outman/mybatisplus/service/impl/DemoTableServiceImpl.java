package cn.dyaoming.outman.mybatisplus.service.impl;

import cn.dyaoming.outman.mybatisplus.entity.DemoTable;
import cn.dyaoming.outman.mybatisplus.mapper.DemoTableMapper;
import cn.dyaoming.outman.mybatisplus.service.DemoTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 测试表结构生产 服务实现类
 * </p>
 *
 * @author dyaoming
 * @since 2020-03-23
 */
@Service
public class DemoTableServiceImpl extends ServiceImpl<DemoTableMapper, DemoTable> implements DemoTableService {

}
