package com.hebo.authDemo;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class AuthDemoApplicationTests {


//	@Autowired

	@Test
	void contextLoads() {
	}


	@Test
	public void test1() {
		 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("1234"));
	}
	@Test
	public void test(){


		Map<String, Object> map = new HashMap<String, Object>() {
			private static final long serialVersionUID = 1L;
			{
				put("uid", Integer.parseInt("123"));
				put("expire_time", System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 15);
			}
		};

		String token = JWTUtil.createToken(map, "1".getBytes());

		System.out.println(token);


		boolean verify = JWTUtil.verify(token, "1".getBytes());
		System.out.println(verify);
	}
}
