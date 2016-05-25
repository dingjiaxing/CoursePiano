package com.edu.shu.wy.tools;

import android.text.format.Time;

/**
 * �õ�ϵͳʱ��
 * @author yang_yueyue
 *
 */
public class GetSystemDateTime {
	
	/** *******************************************
	 * �õ�ϵͳʱ�� 
	 */
	public static String now()
	  {
	    Time localTime = new Time();
	    localTime.setToNow();
	    return localTime.format("%Y%m%d%H%M%S");
	  }
}
