package com.mycompany.springframework.dao;

import java.util.List;

import org.junit.Assert;	// import 경로 주의
import org.junit.Test;		// import 경로 주의
import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.springframework.WebAppTest;
import com.mycompany.springframework.dao.mybatis.Ch13AccountDao;
import com.mycompany.springframework.dto.Ch13Account;

public class Ch16AccountDaoTest extends WebAppTest {
	@Autowired
	private Ch13AccountDao accountDao;	// 테스트 할 객체 주입
	
	@Test
	public void testSelectAll() {
		List<Ch13Account> list = accountDao.selectAll();
		Assert.assertNotNull(list);
		Assert.assertEquals(2, list.size());
	}
	
	@Test
	public void testSelectByAno() {
		Ch13Account account = accountDao.selectByAno(1);
		Assert.assertNotNull(account);
		//Assert.assertEquals(1, account.getAno());
		Assert.assertNotNull(account.getAno());
		Assert.assertNotNull(account.getOwner());
		Assert.assertNotNull(account.getBalance());
	}
	
	@Test
	public void testUpdate() {
		Ch13Account account1 = accountDao.selectByAno(1);
		account1.setBalance(account1.getBalance() + 1000);
		accountDao.update(account1);
		
		Ch13Account account2 = accountDao.selectByAno(1);
		
		// account1.getBalance() = 기대값(예측값) || account2.getBalance() = 실제값
		Assert.assertEquals(account1.getBalance(), account2.getBalance());
	}
}
