package com.edu.shu.wy.tools;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.os.Environment;
import android.util.Log;

/**
 * 
 * @author yang_yueyue
 * SD���ೣ�ùز����Ĺ�����
 *
 */
public class SDcardTools {
	
	/****************************************************************
	 * 
	 * �ж�SD���Ƿ����
	 * @return
	 */
	public static boolean isHaveSDcard(){
		//�ж�SD���Ƿ���� ���ڷ���true �����ڷ���false
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}
	
	/*****************************************************************
	 *
	 * ���ļ����浽SD����
	 * @param data
	 * @param pathName
	 * @param fileName
	 * @throws IOException 
	 */
	public static void saveFileToSDcard(byte[] data,String pathName,String fileName) throws IOException{
		//Ҫ������ļ���·��
		String filePath = getSDPath()+"/"+pathName;
		Log.i("test", "SDcard·���� =��"+filePath+fileName);
		//ʵ�����ļ���
		File dir = new File(filePath);
		if(!dir.exists()){
			//����ļ��в����� �򴴽��ļ���
			dir.mkdir();
		}
		Log.i("test", filePath);
		File file = new File(filePath+"/"+fileName);
		if(!file.exists()){
			//����ļ������� ����
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			bos.write(data);
			bos.flush();
			bos.close();
			fos.close();
		}
	}
	
	/*****************************************************************
	 *  
	 * ���SD����·��
	 */
	public static String getSDPath(){
		String sdDir=null;
		if(isHaveSDcard()){
			sdDir = Environment.getExternalStorageDirectory().toString();//��ø�Ŀ¼
		}
		return sdDir;
	}
	
	/** *************************************************************************
	 * 
	 * ��SD����dirĿ¼�µõ�type���͵��ļ�
	 * @param path
	 * @param type
	 * @return
	 */
	public static List<String> getFileFormSDcard(File dir,String type){
		List<String> listFilesName = new ArrayList<String>();
		if(isHaveSDcard()){
			File[] files = dir.listFiles();
			if(files !=null){
				for(int i=0; i<files.length; i++){
					if(files[i].getName().indexOf(".")>=0){
						// ֻȡType���͵��ļ�
						String filesResult = files[i].getName()
						.substring(files[i].getName().indexOf("."));
						if(filesResult.toLowerCase().equals(type.toLowerCase())){
							listFilesName.add(files[i].getName());
						}
						
					}
				}
			}
		}
		return listFilesName;
	}
	
}
