package com.mycompany.springframework;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)

@SpringJUnitWebConfig(locations= {
		"classpath:spring/root/*.xml",
		"classpath:spring/dispatcher/*.xml"
})

@Transactional	//테스트 메소드가 실행후 트랜잭션을 롤백해서 DB에 영향을 주지 않도록함
public abstract class WebAppTest {
	
}
