package com.ticketsystem.util;

import java.io.UnsupportedEncodingException;

import org.springframework.util.DigestUtils;

public class MD5Util {
	/*
	public static void main(String[] args) {
		System.out.println(MD5Util.getMD5("4c4d668e890976a42ff5c3e9e76076a7CTUPKX2021-07-244c4d668e890976a42ff5c3e9e76076a7"));
	}
	*/
	/**
	 * 生成md5
	 * @param seckillId
	 * @return
	 */
	public static String getMD5(String str) {
		String base = str;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}
}
