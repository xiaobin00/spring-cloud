package com.scrm.message;

import com.scrm.message.entity.WtUserDetailInfo;
import com.scrm.message.mapper.WtUserDetailInfoDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScrmMessageApplicationTests {
	@Autowired
	WtUserDetailInfoDao wtUserDetailInfoDao;

	@Test
	public void contextLoads() {
		WtUserDetailInfo w = new WtUserDetailInfo();
		w.setOpenId("121231231");
		wtUserDetailInfoDao.insert(w);
	}

}
