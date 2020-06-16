/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jenkins.jkstest.security.utils;

import com.alibaba.fastjson.JSON;
import com.jenkins.jkstest.security.config.JWTConfig;
import com.jenkins.jkstest.security.beans.SelfUserEntityVO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * 认证工具类
 *
 * @author Chill
 */
public class JWTTokenUtil {

	/**
	 * 创建认证token
	 *
	 * @param selfUserEntity 用户信息
	 * @return token
	 */
	public static String createAccessToken(SelfUserEntityVO selfUserEntity) {
		// 登陆成功生成JWT
		String token = Jwts.builder()
				// 放入用户名和用户ID
				.setId(selfUserEntity.getUserId() + "")
				// 主题
				.setSubject(selfUserEntity.getUsername())
				// 签发时间
				.setIssuedAt(new Date())
				// 签发者
				.setIssuer("sans")
				// 自定义属性 放入用户拥有权限
				.claim("authorities", JSON.toJSONString(selfUserEntity.getAuthorities()))

				//参数测试
				.claim("gg",selfUserEntity.getPassword())
				// 失效时间
				.setExpiration(new Date(System.currentTimeMillis() + JWTConfig.expiration))
				// 签名算法和密钥
				.signWith(SignatureAlgorithm.HS512, JWTConfig.secret)
				.compact();
		return token;
	}

	/*public int hashCode() {

	}*/
}
