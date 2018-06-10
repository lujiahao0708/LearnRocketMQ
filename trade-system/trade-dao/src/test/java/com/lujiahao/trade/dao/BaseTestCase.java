package com.lujiahao.trade.dao;

import com.lujiahao.trade.dao.entity.TradeUser;
import com.lujiahao.trade.dao.mapper.TradeUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BaseTestCase {

    @Autowired
    private TradeUserMapper userMapper;

    @Test
    public void test() {
        TradeUser tradeUser = userMapper.selectByPrimaryKey(1);
        System.out.println(tradeUser);
    }
}
