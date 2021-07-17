package com.ticketsystem.net;

public class CookieUtil {

	public static String getLoginCookie(String session) {
		String accountCheckCookie = 
				"_ga=GA1.2.1942100065.1625058587; " + 
				"_gid=GA1.2.2104287381.1625058587; " + 
				"flycua_user_cookie=true; " + 
				"Hm_lvt_e9561bcd959a298bc80237056e060dc7=1625058634; " + 
				"Hm_lpvt_e9561bcd959a298bc80237056e060dc7=1625381282; " + 
				"session="+session+"; " + 
				" ";
		return accountCheckCookie;
	}
	
	public static String getBookCookie(String tokenId, String tokenUUID, String session) {
		String accountCheckCookie = 
				"_ga=GA1.2.1942100065.1625058587; " + 
				"_gid=GA1.2.2104287381.1625058587; " + 
				"flycua_user_cookie=true; " + 
				"Hm_lvt_e9561bcd959a298bc80237056e060dc7=1625058634; " + 
				"Hm_lpvt_e9561bcd959a298bc80237056e060dc7=1625381282; " + 
				"tokenId="+tokenId+"; " + 
				"tokenUUID="+tokenUUID+"; " +
				"session="+session+"; " + 
				" ";
		return accountCheckCookie;
	}

	public static String getOrderInfoCookie(String session) {
		String orderCookie = 
				"_ga=GA1.2.1942100065.1625058587; " + 
				"_gid=GA1.2.2104287381.1625058587; " + 
				"flycua_user_cookie=true; " + 
				"Hm_lvt_e9561bcd959a298bc80237056e060dc7=1625058634; " + 
				"Hm_lpvt_e9561bcd959a298bc80237056e060dc7=1625381282; " + 
				"session="+session+"; " + 
				" ";
		return orderCookie;
	}

	public static String getBookCookie2(String tokenId, String tokenUUID, String session) {
		String accountCheckCookie = 
				"X-LB=2.728.fc1ba9b5.50; " + 
				"session="+session+"; " + 
				"flycua_user_cookie=true; " + 
				"tokenId="+tokenId+"; " + 
				"tokenUUID="+tokenUUID+"; " +
				" ";
		return accountCheckCookie;
	}
}
