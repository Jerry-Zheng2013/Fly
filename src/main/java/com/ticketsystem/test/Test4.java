package com.ticketsystem.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Test4 {
	public static void main(String[] args) {

		try {
			System.out.println(URLEncoder.encode("张三", "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
