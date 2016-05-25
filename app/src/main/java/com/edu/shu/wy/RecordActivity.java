package com.edu.shu.wy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.shu.wy.tools.SDcardTools;
import com.edu.shu.wy.tools.ShowDialog;

import java.io.File;
import java.util.List;

/**
 * Created by admin on 2016/4/23.
 */
public class RecordActivity extends Activity{
    private Button buttonStart; // 开始按钮
    private Button buttonStop; // 停止按钮
    private Button buttonDeleted; // 删除按钮
    private TextView textViewLuYinState; // 录音状态
    private ListView listViewAudio; // 显示录音文件的list
    private ArrayAdapter<String> adaperListAudio; // 列表

    private String fileAudioName; // 保存的音频文件的名字
    private MediaRecorder mediaRecorder; // 录音控制
    private String filePath; // 音频保存的文件路径
    private List<String> listAudioFileName; // 音频文件列表
    private boolean isLuYin; // 是否在录音 true 是 false否
    private File fileAudio; // 录音文件
    private File fileAudioList; //列表中的 录音文件
    File dir; //录音文件
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        // 显示录音文件的列表
        listViewAudio = (ListView) findViewById(R.id.listViewAudioFile);

        initData();
        setView();
        setEvent();

    }
    public void backClick(View v){
        this.finish();
    }
    /* ******************************************************************
	 *
	 * 初始化数据
	 */
    private void initData() {
        if (!SDcardTools.isHaveSDcard()) {
            Toast.makeText(RecordActivity.this, "请插入SD卡以便存储录音",
                    Toast.LENGTH_LONG).show();
            return;
        }


        // 要保存的文件的路径
        filePath = SDcardTools.getSDPath() + "/" + "myAudio";
        // 实例化文件夹
        dir = new File(filePath);
        if (!dir.exists()) {
            // 如果文件夹不存在 则创建文件夹
            dir.mkdir();
        }
        Log.i("test", "要保存的录音的文件名为" + fileAudioName + "路径为" + filePath);
        listAudioFileName = SDcardTools.getFileFormSDcard(dir, ".mp3");
//        Toast.makeText(RecordActivity.this,listAudioFileName.size()+"",Toast.LENGTH_LONG).show();
        adaperListAudio = new ArrayAdapter<String>(RecordActivity.this,
                android.R.layout.simple_list_item_1, listAudioFileName);
    }
    /* **************************************************************
	 *
	 * 设置组件
	 */
    private void setView() {
//        buttonStart.setEnabled(true);
//        buttonStop.setEnabled(false);
//        buttonDeleted.setEnabled(false);
        listViewAudio.setAdapter(adaperListAudio);

    }
    /* ***********************************************************************
	 *
	 * 设置事件
	 */
    private void setEvent() {







        //文件列表点击事件
        listViewAudio.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                String fileAudioNameList=((TextView)arg1).getText().toString();
                fileAudioList = new File(filePath + "/" + fileAudioNameList);
                openFile(fileAudioList);
            }
        });
        //文件列表的长按删除事件
        listViewAudio.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int arg2, long arg3) {
                // TODO Auto-generated method stub
                Log.i("test", "长按事件执行了");
                String fileAudioNameList=((TextView)arg1).getText().toString();
                fileAudioList = new File(filePath + "/" + fileAudioNameList);
//                openFile(fileAudioList);
                if (fileAudioList != null) {
                    fileAudio = fileAudioList;
                    fileAudioName = fileAudioNameList;
                    showDeleteAudioDialog("是否删除" + fileAudioName + "文件", "不删除",
                            "删除", false);
                } else {
                    ShowDialog.showTheAlertDialog(RecordActivity.this, "该文件不存在");
                }
                return true;
            }
        });




    }
    /*** *************************************************************************************
     *
     * 打开播放录音文件的程序
     * @param f
     */
    private void openFile(File f)
    {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        String type = getMIMEType(f);
        intent.setDataAndType(Uri.fromFile(f), type);
        startActivity(intent);
    }
    private String getMIMEType(File f)
    {
        String end = f
                .getName()
                .substring(f.getName().lastIndexOf(".") + 1,
                        f.getName().length()).toLowerCase();
        String type = "";
        if (end.equals("mp3") || end.equals("aac") || end.equals("aac")
                || end.equals("amr") || end.equals("mpeg")
                || end.equals("mp4"))
        {
            type = "audio";
        } else if (end.equals("jpg") || end.equals("gif")
                || end.equals("png") || end.equals("jpeg"))
        {
            type = "image";
        } else
        {
            type = "*";
        }
        type += "/*";
        return type;
    }
    /*******************************************************************************************************
     *
     * 是否删除录音文件
     *
     * @param messageString
     *            //对话框标题
     * @param button1Title
     *            //第一个按钮的内容
     * @param button2Title
     *            //第二个按钮的内容
     * @param isExit
     *            //是否是退出程序
     */
    public void showDeleteAudioDialog(String messageString,
                                      String button1Title, String button2Title, final boolean isExit) {
        AlertDialog dialog = new AlertDialog.Builder(RecordActivity.this)
                .create();
        dialog.setTitle("提示");
        dialog.setMessage(messageString);
        dialog.setButton(button1Title, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                if (isExit) {
                    dialog.dismiss();
                    System.exit(0);
                }
            }
        });
        dialog.setButton2(button2Title, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                try{
                    fileAudio.delete();
                    adaperListAudio.remove(fileAudioName);
                    fileAudio = null;
//                    buttonDeleted.setEnabled(false);

                    if (isExit) {
                        dialog.dismiss();
                        System.exit(0);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        dialog.show();
    }
}
