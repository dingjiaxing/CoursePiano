package com.edu.shu.wy.tools;

import java.util.Random;

/**
 * ************************************************
 * 
 * @author yang_yueyue
 * 
 *         �ַ������ò����Ĺ�����
 * 
 */
public class StringTools {

	/** ************************************************************
	 * 
	 * ��ȡ����ַ���
	 * @param len �ַ����ĳ���
	 * @return
	 */
	public static String getRandomString(int len) {
		String returnStr = "";
		char[] ch = new char[len];
		Random rd = new Random();
		for (int i = 0; i < len; i++) {
			ch[i] = (char) (rd.nextInt(9)+97);
		}
		returnStr = new String(ch);
		return returnStr;
	}

}
