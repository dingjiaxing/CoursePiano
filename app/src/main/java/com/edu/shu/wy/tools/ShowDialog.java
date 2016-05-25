package com.edu.shu.wy.tools;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.ListView;

/**
 * 
 * @author yang_yueyue
 * ��ʾ�Ի������
 */
public class ShowDialog {
	
	/** ***************************************************
	 * 
	 * �Ƿ񱣳�¼��
	 */
	public static void showSaveAudioDialog(Context context){
		AlertDialog dialog = new AlertDialog.Builder(context).create();
		dialog.setTitle("��ʾ");
		dialog.setMessage("�Ƿ񱣴��¼���ļ�");
		dialog.setButton("����", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		dialog.setButton("������", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		dialog.show();
	}
	
	
	/** ***************************************************
	 * 
	 * ���ѶԻ���
	 */
	public static void showTheAlertDialog(Context context,String text){
		AlertDialog dialog = new AlertDialog.Builder(context).create();
		dialog.setTitle("��ʾ");
		dialog.setMessage(text);
		dialog.setButton("ȷ��", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
		dialog.show();
	}
}
