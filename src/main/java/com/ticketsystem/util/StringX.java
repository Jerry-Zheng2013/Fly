package com.ticketsystem.util;

public class StringX {
	public static boolean empty(String str) {
		boolean b = false;
		if (str==null || str.length()<1) {
			b = true;
		}
		return b;
	}
}
