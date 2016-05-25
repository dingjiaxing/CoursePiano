package com.edu.shu.wy;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.shu.wy.tools.GetSystemDateTime;
import com.edu.shu.wy.tools.SDcardTools;
import com.edu.shu.wy.tools.ShowDialog;
import com.edu.shu.wy.tools.StringTools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by admin on 2016/4/23.
 */
public class ScoreActivity extends Activity {
    MediaPlayer bgPlayer;

    private Button button[];// 按钮数组
    private Button setting;//设置按钮
    private TextView textView;//简谱
    private MyMusicUtils utils;// 工具类
    private View parent;// 父视图
    private int buttonId[];// 按钮id
    private boolean havePlayed[];// 是否已经播放了声音，当手指在同一个按钮内滑动，且已经发声，就为true
    private View keys;// 按钮们所在的视图
    private int pressedkey[];

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

    protected static final String TAG = "ScoreActivity";
    private ImageView mBlueBall;
    private ImageView mBlueBall2;
    private ImageView mBlueBall3;
    private ImageView mBlueBall4;
    private ImageView mBlueBall5;
    private ImageView mBlueBall6;
    private ImageView mBlueBall7;
    private ImageView mBlueBall8;
    private ImageView mBlueBall9;
    private ImageView mBlueBall10;
    private ImageView mBlueBall11;
    private ImageView mBlueBall12;
    private ImageView mBlueBall13;
    private ImageView mBlueBall14;
    private ImageView mBlueBall15;

    private ImageView mGreenBall;
    private ImageView mGreenBall2;
    private ImageView mGreenBall3;
    private ImageView mGreenBall4;
    private ImageView mGreenBall5;
    private ImageView mGreenBall6;
    private ImageView mGreenBall7;
    private ImageView mGreenBall8;
    private ImageView mGreenBall9;
    private ImageView mGreenBall10;
    private ImageView mBlueBall26;
    private float mScreenHeight;
    private List<String> list = new ArrayList<String>();
    private List<String> list2 = new ArrayList<String>();
    private TextView myTextView;
    private Spinner mySpinner;
    private TextView myTextView2;
    private Spinner mySpinner2;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        mBlueBall = (ImageView) findViewById(R.id.id_ball);
        mBlueBall2 = (ImageView) findViewById(R.id.id_ball2);
        mBlueBall3 = (ImageView) findViewById(R.id.id_ball3);
        mBlueBall4 = (ImageView) findViewById(R.id.id_ball4);
        mBlueBall5 = (ImageView) findViewById(R.id.id_ball5);
        mBlueBall6 = (ImageView) findViewById(R.id.id_ball6);
        mBlueBall7 = (ImageView) findViewById(R.id.id_ball7);
        mBlueBall8 = (ImageView) findViewById(R.id.id_ball8);
        mBlueBall9 = (ImageView) findViewById(R.id.id_ball9);
        mBlueBall10 = (ImageView) findViewById(R.id.id_ball10);
        mBlueBall11 = (ImageView) findViewById(R.id.id_ball11);
        mBlueBall12 = (ImageView) findViewById(R.id.id_ball12);
        mBlueBall13 = (ImageView) findViewById(R.id.id_ball13);
        mBlueBall14 = (ImageView) findViewById(R.id.id_ball14);
        mBlueBall15 = (ImageView) findViewById(R.id.id_ball15);
        mBlueBall26 = (ImageView) findViewById(R.id.id_ball26);
        mGreenBall  = (ImageView) findViewById(R.id.id_ball16);
        mGreenBall2 = (ImageView) findViewById(R.id.id_ball17);
        mGreenBall3 = (ImageView) findViewById(R.id.id_ball18);
        mGreenBall4 = (ImageView) findViewById(R.id.id_ball19);
        mGreenBall5 = (ImageView) findViewById(R.id.id_ball20);
        mGreenBall6 = (ImageView) findViewById(R.id.id_ball21);
        mGreenBall7 = (ImageView) findViewById(R.id.id_ball22);
        mGreenBall8 = (ImageView) findViewById(R.id.id_ball23);
        mGreenBall9 = (ImageView) findViewById(R.id.id_ball24);
        mGreenBall10 = (ImageView) findViewById(R.id.id_ball25);

        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        mScreenHeight = outMetrics.heightPixels;
// 初始化组件
        initView();
        // 初始化数据
        initData();
        // 设置组件
        setView();
        // 设置事件
        setEvent();

        // 新建工具类
        utils = new MyMusicUtils(getApplicationContext());

        // 按钮资源Id
        buttonId = new int[25];
        buttonId[0] = R.id.button1;
        buttonId[1] = R.id.button2;
        buttonId[2] = R.id.button3;
        buttonId[3] = R.id.button4;
        buttonId[4] = R.id.button5;
        buttonId[5] = R.id.button6;
        buttonId[6] = R.id.button7;
        buttonId[7] = R.id.button8;
        buttonId[8] = R.id.button9;
        buttonId[9] = R.id.button10;
        buttonId[10] = R.id.button11;
        buttonId[11] = R.id.button12;
        buttonId[12] = R.id.button13;
        buttonId[13] = R.id.button14;
        buttonId[14] = R.id.button15;
        //16-24为黑键对应的按钮
        buttonId[15] = R.id.button16;
        buttonId[16] = R.id.button17;
        buttonId[17] = R.id.button18;
        buttonId[18] = R.id.button19;
        buttonId[19] = R.id.button20;
        buttonId[20] = R.id.button21;
        buttonId[21] = R.id.button22;
        buttonId[22] = R.id.button23;
        buttonId[23] = R.id.button24;
        buttonId[24] = R.id.button25;
        button = new Button[25];
        havePlayed = new boolean[25];

        button = new Button[25];
        havePlayed = new boolean[25];

        // 获取按钮对象
        for (int i = 0; i < 15; i++) {
            button[i] = (Button) findViewById(buttonId[i]);
            button[i].setClickable(false);
            havePlayed[i] = false;
        }

        pressedkey = new int[5];
        for (int j = 0; j < pressedkey.length; j++) {
            pressedkey[j] = -1;
        }

        parent = (View) findViewById(R.id.parent);
        parent.setClickable(true);

        // 不知道为什么有时候会卡键，可能是由于接触点大于5个
        parent.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int temp;
                int tempIndex;
                int pointercount;

                pointercount = event.getPointerCount();
                for (int count = 0; count < pointercount; count++) {
                    boolean moveflag = false;// 标记是否是在按键上移动
                    temp = isInAnyScale(event.getX(count), event.getY(count),
                            button);
                    if (temp != -1) {// 事件对应的是当前点
                        switch (event.getActionMasked()) {
                            case MotionEvent.ACTION_DOWN:
                                // // 单独一根手指或最先按下的那个
                                // pressedkey = temp;
                            case MotionEvent.ACTION_POINTER_DOWN:
                                Log.i("--", "count" + count);
                                pressedkey[count] = temp;
                                if (!havePlayed[temp]) {// 在某个按键范围内
                                    button[temp]
                                            .setBackgroundResource(R.drawable.button_pressed);
                                    // 播放音阶
                                    utils.soundPlay(temp);
                                    Log.i("--", "sound" + temp);
                                    havePlayed[temp] = true;
                                }
                                break;
                            case MotionEvent.ACTION_MOVE:
                                // 事件与点对应
                                // if (temp!=pressedkey[count])
                                // {//移动的按钮和当前响应事件的按钮不匹配
                                // break;
                                // }
                                // Log.i("--", "currentkey" + temp + "move");
                                temp = pressedkey[count];
                                // Log.i("--", "KEY" + temp + "move");
                                for (int i = temp + 1; i >= temp - 1; i--) {
                                    // 当在两端的按钮时，会有一边越界
                                    if (i < 0 || i >= button.length) {
                                        continue;
                                    }
                                    if (isInScale(event.getX(count),
                                            event.getY(count), button[i])) {// 在某个按键内
                                        moveflag = true;
                                        if (i != temp) {// 在相邻按键内
                                            boolean laststill = false;
                                            boolean nextstill = false;
                                            // 假设手指已经从上一个位置抬起，但是没有真的抬起，所以不移位
                                            pressedkey[count] = -1;
                                            for (int j = 0; j < pointercount; j++) {
                                                if (pressedkey[j] == temp) {
                                                    laststill = true;
                                                }
                                                if (pressedkey[j] == i) {
                                                    nextstill = true;
                                                }
                                            }

                                            if (!nextstill) {// 移入的按键没有按下
                                                // 设置当前按键
                                                button[i]
                                                        .setBackgroundResource(R.drawable.button_pressed);
                                                // 发音
                                                utils.soundPlay(i);
                                                Log.i("--", "soundmove" + temp
                                                        + "to" + i);
                                                havePlayed[i] = true;
                                            }

                                            pressedkey[count] = i;

                                            if (!laststill) {// 没有手指按在上面
                                                // 设置上一个按键
                                                button[temp]
                                                        .setBackgroundResource(R.drawable.button);
                                                havePlayed[temp] = false;
                                            }

                                            break;
                                        }
                                    }
                                }
                                break;
                            case MotionEvent.ACTION_UP:
                            case MotionEvent.ACTION_POINTER_UP:
                                // 事件与点对应
                                tempIndex = event.getActionIndex();
                                if (tempIndex == count) {
                                    Log.i("--", "index" + tempIndex);
                                    boolean still = false;
                                    // 当前点已抬起
                                    for (int t = count; t < 5; t++) {
                                        if (t != 4) {
                                            if (pressedkey[t + 1] >= 0) {
                                                pressedkey[t] = pressedkey[t + 1];
                                            } else {
                                                pressedkey[t] = -1;
                                            }
                                        } else {
                                            pressedkey[t] = -1;
                                        }

                                    }
                                    for (int i = 0; i < pressedkey.length; i++) {// 是否还有其他点
                                        if (pressedkey[i] == temp) {
                                            still = true;
                                            break;
                                        }
                                    }

                                    // String string = "";
                                    // for (int i = 0; i < pressedkey.length; i++) {
                                    // string += pressedkey[i];
                                    // }
                                    // Log.i("--", "pressedkey" + string);
                                    if (!still) {// 已经没有手指按在该键上
                                        button[temp]
                                                .setBackgroundResource(R.drawable.button);
                                        havePlayed[temp] = false;
                                        Log.i("--", "button" + temp + "up");
                                    }
                                    break;
                                }
                        }
                    }
                    //
                    if (event.getActionMasked() == MotionEvent.ACTION_MOVE
                            && !moveflag) {
                        if (pressedkey[count] != -1) {
                            button[pressedkey[count]]
                                    .setBackgroundResource(R.drawable.button);
                            havePlayed[pressedkey[count]] = false;
                            // Log.i("--", "button" + pressedkey[count] +
                            // "ddup");
                        }
                    }
                }
                return false;
            }
        });

        keys = (View) findViewById(R.id.Keys);

        //设置界面
        setting = (Button)findViewById(R.id.button);

        setting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

            }
        });

        //第一步：添加一个下拉列表项的list，这里添加的项就是下拉列表的菜单项
        //第一步：添加一个下拉列表项的list，这里添加的项就是下拉列表的菜单项
        list2.add("请选择歌曲");
        list2.add("小星星");
        list2.add("铃儿响叮当");
        list2.add("我是一个粉刷匠");
        myTextView2 = (TextView)findViewById(R.id.TextView_Show2);
        mySpinner2 = (Spinner)findViewById(R.id.spinner_City2);
        //第二步：为下拉列表定义一个适配器，这里就用到里前面定义的list。
        adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list2);
        //第三步：为适配器设置下拉列表下拉时的菜单样式。
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        mySpinner2.setAdapter(adapter2);
        //第五步：为下拉列表设置各种事件的响应，这个事响应菜单被选中
        mySpinner2.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                    /* 将所选mySpinner 的值带入myTextView 中*/
                myTextView2.setText(""+ adapter2.getItem(arg2));
                if(adapter2.getItem(arg2)=="小星星")
                {
                    stars();
                }
                if(adapter2.getItem(arg2)=="铃儿响叮当")
                {
                    bells();
                }
                if(adapter2.getItem(arg2)=="我是一个粉刷匠")
                {
                    brush();
                }
                    /* 将mySpinner 显示*/
                arg0.setVisibility(View.VISIBLE);
            }
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                myTextView2.setText("NONE");
                arg0.setVisibility(View.VISIBLE);
            }
        });
            /*下拉菜单弹出的内容选项触屏事件处理*/
        mySpinner2.setOnTouchListener(new Spinner.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                    /* 将mySpinner 隐藏，不隐藏也可以，看自己爱好*/
                v.setVisibility(View.INVISIBLE);
                return false;
            }
        });
            /*下拉菜单弹出的内容选项焦点改变事件处理*/
        mySpinner2.setOnFocusChangeListener(new Spinner.OnFocusChangeListener(){
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                v.setVisibility(View.VISIBLE);
            }
        });

    }



    /**
     * 判断某个点是否在某个按钮的范围内
     *
     * @param x
     *            横坐标
     * @param y
     *            纵坐标
     * @param button
     *            按钮对象
     * @return 在：true；不在：false
     */
    private boolean isInScale(float x, float y, Button button) {
        // keys.getTop()是获取按钮所在父视图相对其父视图的右上角纵坐标
        try{
            if (x > button.getLeft() && x < button.getRight()
                    && y > button.getTop() + keys.getTop()
                    && y < button.getBottom() + keys.getTop()) {
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;

    }

    /**
     * 判断某个点是否在一个按钮集合中的某个按钮内
     *
     * @param x
     *            横坐标
     * @param y
     *            纵坐标
     * @param button
     *            按钮数组
     * @return
     */
    private int isInAnyScale(float x, float y, Button[] button) {
        // keys.getTop()是获取按钮所在父视图相对其父视图的右上角纵坐标
        try{
            for (int i = 0; i < button.length; i++) {
                if (x > button[i].getLeft() && x < button[i].getRight()
                        && y > button[i].getTop() + keys.getTop()
                        && y < button[i].getBottom() + keys.getTop()) {
                    return i;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return -1;
    }
    public void backClick(View v){
        this.finish();
    }

    /* **********************************************************************
	 *
	 * 初始化组件
	 */
    private void initView() {
        // 开始
        buttonStart = (Button) findViewById(R.id.button_start);
        // 停止
        buttonStop = (Button) findViewById(R.id.button_stop);
        // 删除
        buttonDeleted = (Button) findViewById(R.id.button_delete);
        // 录音状态
        textViewLuYinState = (TextView) findViewById(R.id.text_luyin_state);
        // 显示录音文件的列表
//        listViewAudio = (ListView) findViewById(R.id.listViewAudioFile);

    }

    /* ******************************************************************
     *
     * 初始化数据
     */
    private void initData() {
        if (!SDcardTools.isHaveSDcard()) {
            Toast.makeText(ScoreActivity.this, "请插入SD卡以便存储录音",
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
        adaperListAudio = new ArrayAdapter<String>(ScoreActivity.this,
                android.R.layout.simple_list_item_1, listAudioFileName);
    }

    /* **************************************************************
     *
     * 设置组件
     */
    private void setView() {
        buttonStart.setEnabled(true);
        buttonStop.setEnabled(false);
        buttonDeleted.setEnabled(false);
//        listViewAudio.setAdapter(adaperListAudio);

    }

    /* ***********************************************************************
     *
     * 设置事件
     */
    private void setEvent() {

        // 开始按钮
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startAudio();
            }
        });

        // 停止按钮
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                stopAudion();

            }
        });


        // 删除按钮
        buttonDeleted.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (fileAudio != null) {
                    showDeleteAudioDialog("是否删除" + fileAudioName + "文件", "不删除",
                            "删除", false);
                } else {
                    ShowDialog.showTheAlertDialog(ScoreActivity.this, "该文件不存在");
                }
            }
        });

        //文件列表点击事件
//        listViewAudio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//                                    long arg3) {
//                // TODO Auto-generated method stub
//                String fileAudioNameList=((TextView)arg1).getText().toString();
//                fileAudioList = new File(filePath + "/" + fileAudioNameList);
//                openFile(fileAudioList);
//            }
//        });
        //文件列表的长按删除事件
//        listViewAudio.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//
//            @Override
//            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
//                                           int arg2, long arg3) {
//                // TODO Auto-generated method stub
//                Log.i("test", "长按事件执行了");
//                String fileAudioNameList=((TextView)arg1).getText().toString();
//                fileAudioList = new File(filePath + "/" + fileAudioNameList);
//                openFile(fileAudioList);
//                if (fileAudioList != null) {
//                    fileAudio = fileAudioList;
//                    fileAudioName = fileAudioNameList;
//                    showDeleteAudioDialog("是否删除" + fileAudioName + "文件", "不删除",
//                            "删除", false);
//                } else {
//                    ShowDialog.showTheAlertDialog(LearnActivity.this, "该文件不存在");
//                }
//                return false;
//            }
//        });




    }

    /* ****************************************************************
     *
     * 开始录音
     */
    private void startAudio() {
        // 创建录音频文件
        // 这种创建方式生成的文件名是随机的
        fileAudioName = "audio" + GetSystemDateTime.now()
                + StringTools.getRandomString(2) + ".mp3";
        mediaRecorder = new MediaRecorder();
        // 设置录音的来源为麦克风
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        mediaRecorder.setOutputFile(filePath + "/" + fileAudioName);
        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
            textViewLuYinState.setText("录音中。。。");

            fileAudio = new File(filePath + "/" + fileAudioName);
            buttonStart.setEnabled(false);
            buttonStop.setEnabled(true);
            buttonDeleted.setEnabled(false);
//            listViewAudio.setEnabled(false);
            isLuYin = true;
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if (null != adaperListAudio) {
            adaperListAudio.notifyDataSetChanged();
        }
    }

    /* ******************************************************
     *
     * 停止录制
     */
    private void stopAudion() {
        if (null != mediaRecorder) {
            // 停止录音
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            textViewLuYinState.setText("录音停止了");

            // 开始键能够按下
            buttonStart.setEnabled(true);
            buttonStop.setEnabled(false);
//            listViewAudio.setEnabled(true);
            // 删除键能按下
            buttonDeleted.setEnabled(true);
            adaperListAudio.add(fileAudioName);

        }
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
        AlertDialog dialog = new AlertDialog.Builder(ScoreActivity.this)
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
                fileAudio.delete();
                adaperListAudio.remove(fileAudioName);
                fileAudio = null;
                buttonDeleted.setEnabled(false);

                if (isExit) {
                    dialog.dismiss();
                    System.exit(0);
                }
            }
        });

        dialog.show();
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



    /**
     * ********************************************************
     *
     * 当程序停止的时候
     */
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        if (null != mediaRecorder && isLuYin) {
            // 停止录音
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;

            buttonStart.setEnabled(true);
            buttonStop.setEnabled(false);
//            listViewAudio.setEnabled(true);
            buttonDeleted.setEnabled(false);
        }
        super.onStop();
    }
    /**
     *
     *
     ***********************************************************************
     * 点击退出按钮时
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (null != mediaRecorder && isLuYin) {
                if (fileAudio != null) {
                    showDeleteAudioDialog("是否保存" + fileAudioName + "文件", "保存",
                            "不保存", true);
                } else {
                    ShowDialog.showTheAlertDialog(ScoreActivity.this, "该文件不存在");
                }
            }else{
                System.exit(0);
            }
        }
        return true;
    }
    public void doClick(View v){
        int BlackVoice[]={R.raw.black1,R.raw.black2,R.raw.black3,R.raw.black4,R.raw.black5,R.raw.black6,R.raw.black7,R.raw.black8,R.raw.black9,R.raw.black10};
        for(int i=15;i<25;i++){
            if(buttonId[i]==v.getId()){
                MediaPlayer mp=MediaPlayer.create(ScoreActivity.this,BlackVoice[i-15]);
                mp.start();
            }
        }
    }

    /**
     * 自由落体
     *
     * @param
     */
    public void bells()
    {
        //float cx = mBlueBall.getX();
        //float cy = mBlueBall.getX();
        //float cy2 = mBlueBall2.getX();
        //float cy3 = mBlueBall3.getX();
        //float cy4 = mBlueBall4.getX();
        //float cy5 = mBlueBall5.getX();

        //ObjectAnimator anim1 = ObjectAnimator.ofFloat(mBlueBall, "scaleX",
        //1.0f, 2f);
        //ObjectAnimator anim2 = ObjectAnimator.ofFloat(mBlueBall, "scaleY",
        //1.0f, 2f);333/333/35123/444/433/322125
//mBlueBall26为空动画
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000 );
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000 );
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(mBlueBall26,
                "y", 0,  1000 );
        ObjectAnimator anim5 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000 );
        ObjectAnimator anim6 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000 );
        ObjectAnimator anim7 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000 );
        ObjectAnimator anim8 = ObjectAnimator.ofFloat(mBlueBall26,
                "y", 0,  1000 );
        ObjectAnimator anim9 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000 );
        ObjectAnimator anim10 = ObjectAnimator.ofFloat(mBlueBall12,
                "y", 0,  1000 );
        ObjectAnimator anim11 = ObjectAnimator.ofFloat(mBlueBall8,
                "y", 0,  1000);
        ObjectAnimator anim12 = ObjectAnimator.ofFloat(mBlueBall9,
                "y", 0,  1000 );
        ObjectAnimator anim13 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000 );
        ObjectAnimator anim14 = ObjectAnimator.ofFloat(mBlueBall26,
                "y", 0,  1000 );
        ObjectAnimator anim15 = ObjectAnimator.ofFloat(mBlueBall26,
                "y", 0,  1000 );
        ObjectAnimator anim16 = ObjectAnimator.ofFloat(mBlueBall26,
                "y", 0,  1000 );
        ObjectAnimator anim17 = ObjectAnimator.ofFloat(mBlueBall11,
                "y", 0,  1000 );
        ObjectAnimator anim18 = ObjectAnimator.ofFloat(mBlueBall11,
                "y", 0,  1000 );
        ObjectAnimator anim19 = ObjectAnimator.ofFloat(mBlueBall11,
                "y", 0,  1000 );
        ObjectAnimator anim20 = ObjectAnimator.ofFloat(mBlueBall11,
                "y", 0,  1000 );
        ObjectAnimator anim21 = ObjectAnimator.ofFloat(mBlueBall11,
                "y", 0,  1000 );
        ObjectAnimator anim22 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000 );
        ObjectAnimator anim23 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000 );
        ObjectAnimator anim24 = ObjectAnimator.ofFloat(mBlueBall26,
                "y", 0,  1000 );
        ObjectAnimator anim25 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000 );
        ObjectAnimator anim26 = ObjectAnimator.ofFloat(mBlueBall9,
                "y", 0,  1000);
        ObjectAnimator anim27 = ObjectAnimator.ofFloat(mBlueBall9,
                "y", 0,  1000 );
        ObjectAnimator anim28 = ObjectAnimator.ofFloat(mBlueBall8,
                "y", 0,  1000 );
        ObjectAnimator anim29 = ObjectAnimator.ofFloat(mBlueBall9,
                "y", 0,  1000 );
        ObjectAnimator anim30 = ObjectAnimator.ofFloat(mBlueBall26,
                "y", 0,  1000 );
        ObjectAnimator anim31 = ObjectAnimator.ofFloat(mBlueBall12,
                "y", 0,  1000 );
        ObjectAnimator anim32 = ObjectAnimator.ofFloat(mBlueBall26,
                "y", 0,  1000 );
        ObjectAnimator anim33 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000 );
        ObjectAnimator anim34 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000 );
        ObjectAnimator anim35 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000);
        ObjectAnimator anim36 = ObjectAnimator.ofFloat(mBlueBall26,
                "y", 0,  1000 );
        ObjectAnimator anim37 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000 );
        ObjectAnimator anim38 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000 );
        ObjectAnimator anim39 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000 );
        ObjectAnimator anim40 = ObjectAnimator.ofFloat(mBlueBall26,
                "y", 0,  1000 );
        ObjectAnimator anim41 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000 );
        ObjectAnimator anim42 = ObjectAnimator.ofFloat(mBlueBall12,
                "y", 0,  1000 );
        ObjectAnimator anim43 = ObjectAnimator.ofFloat(mBlueBall8,
                "y", 0,  1000);
        ObjectAnimator anim44 = ObjectAnimator.ofFloat(mBlueBall9,
                "y", 0,  1000 );
        ObjectAnimator anim45 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000 );
        ObjectAnimator anim46 = ObjectAnimator.ofFloat(mBlueBall26,
                "y", 0,  1000 );
        ObjectAnimator anim47 = ObjectAnimator.ofFloat(mBlueBall26,
                "y", 0,  1000 );
        ObjectAnimator anim48 = ObjectAnimator.ofFloat(mBlueBall26,
                "y", 0,  1000 );
        ObjectAnimator anim49 = ObjectAnimator.ofFloat(mBlueBall11,
                "y", 0,  1000 );
        ObjectAnimator anim50 = ObjectAnimator.ofFloat(mBlueBall11,
                "y", 0,  1000 );
        ObjectAnimator anim51 = ObjectAnimator.ofFloat(mBlueBall11,
                "y", 0,  1000 );
        ObjectAnimator anim52 = ObjectAnimator.ofFloat(mBlueBall11,
                "y", 0,  1000 );
        ObjectAnimator anim53 = ObjectAnimator.ofFloat(mBlueBall11,
                "y", 0,  1000 );
        ObjectAnimator anim54 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000 );
        ObjectAnimator anim55 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000 );
        ObjectAnimator anim56 = ObjectAnimator.ofFloat(mBlueBall26,
                "y", 0,  1000 );
        ObjectAnimator anim57 = ObjectAnimator.ofFloat(mBlueBall12,
                "y", 0,  1000 );
        ObjectAnimator anim58 = ObjectAnimator.ofFloat(mBlueBall12,
                "y", 0,  1000);
        ObjectAnimator anim59 = ObjectAnimator.ofFloat(mBlueBall11,
                "y", 0,  1000 );
        ObjectAnimator anim60 = ObjectAnimator.ofFloat(mBlueBall9,
                "y", 0,  1000 );
        ObjectAnimator anim61 = ObjectAnimator.ofFloat(mBlueBall8,
                "y", 0,  1000 );
        ObjectAnimator anim62 = ObjectAnimator.ofFloat(mBlueBall26,
                "y", 0,  1000 );
        ObjectAnimator anim63 = ObjectAnimator.ofFloat(mBlueBall26,
                "y", 0,  1000 );
        ObjectAnimator anim64 = ObjectAnimator.ofFloat(mBlueBall26,
                "y", 0,  1000 );



        /**
         * anim1，anim2,anim3同时执行
         * anim4接着执行
         */
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(anim2).after(anim1);
        animSet.play(anim3).after(anim2);
        animSet.play(anim4).after(anim3);
        animSet.play(anim5).after(anim4);
        animSet.play(anim6).after(anim5);
        animSet.play(anim7).after(anim6);
        animSet.play(anim8).after(anim7);
        animSet.play(anim9).after(anim8);
        animSet.play(anim10).after(anim9);
        animSet.play(anim11).after(anim10);
        animSet.play(anim12).after(anim11);
        animSet.play(anim13).after(anim12);
        animSet.play(anim14).after(anim13);
        animSet.play(anim15).after(anim14);;
        animSet.play(anim16).after(anim15);;
        animSet.play(anim17).after(anim16);
        animSet.play(anim18).after(anim17);
        animSet.play(anim19).after(anim18);
        animSet.play(anim20).after(anim19);
        animSet.play(anim21).after(anim20);;
        animSet.play(anim22).after(anim21);
        animSet.play(anim23).after(anim22);
        animSet.play(anim24).after(anim23);
        animSet.play(anim25).after(anim24);
        animSet.play(anim26).after(anim25);
        animSet.play(anim27).after(anim26);
        animSet.play(anim28).after(anim27);
        animSet.play(anim29).after(anim28);
        animSet.play(anim30).after(anim29);
        animSet.play(anim31).after(anim30);
        animSet.play(anim32).after(anim31);
        animSet.play(anim33).after(anim32);
        animSet.play(anim34).after(anim33);
        animSet.play(anim35).after(anim34);
        animSet.play(anim36).after(anim35);
        animSet.play(anim37).after(anim36);
        animSet.play(anim38).after(anim37);
        animSet.play(anim39).after(anim38);
        animSet.play(anim40).after(anim39);
        animSet.play(anim41).after(anim40);
        animSet.play(anim42).after(anim41);
        animSet.play(anim43).after(anim42);
        animSet.play(anim44).after(anim43);
        animSet.play(anim45).after(anim44);
        animSet.play(anim46).after(anim45);
        animSet.play(anim47).after(anim46);
        animSet.play(anim48).after(anim47);
        animSet.play(anim49).after(anim48);
        animSet.play(anim50).after(anim49);
        animSet.play(anim51).after(anim50);
        animSet.play(anim52).after(anim51);
        animSet.play(anim53).after(anim52);
        animSet.play(anim54).after(anim53);
        animSet.play(anim55).after(anim54);
        animSet.play(anim56).after(anim55);
        animSet.play(anim57).after(anim56);
        animSet.play(anim58).after(anim57);
        animSet.play(anim59).after(anim58);
        animSet.play(anim60).after(anim59);
        animSet.play(anim61).after(anim60);
        animSet.play(anim62).after(anim61);
        animSet.play(anim63).after(anim62);
        animSet.play(anim64).after(anim63);
        animSet.setDuration(1000);
        animSet.start();
    }

    /**
     * 我是一个粉刷匠
     *
     * @param
     */
    public void brush()
    {
        //float cx = mBlueBall.getX();
        //float cy = mBlueBall.getX();
        //float cy2 = mBlueBall2.getX();
        //float cy3 = mBlueBall3.getX();
        //float cy4 = mBlueBall4.getX();
        //float cy5 = mBlueBall5.getX();

        //ObjectAnimator anim1 = ObjectAnimator.ofFloat(mBlueBall, "scaleX",
        //1.0f, 2f);
        //ObjectAnimator anim2 = ObjectAnimator.ofFloat(mBlueBall, "scaleY",
        //1.0f, 2f);333/333/35123/444/433/322125
//mBlueBall26为空动画
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(mBlueBall12,
                "y", 0,  1000 );
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000 );
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(mBlueBall12,
                "y", 0,  1000);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000 );
        ObjectAnimator anim5 = ObjectAnimator.ofFloat(mBlueBall12,
                "y", 0,  1000 );
        ObjectAnimator anim6 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000 );
        ObjectAnimator anim7 = ObjectAnimator.ofFloat(mBlueBall8,
                "y", 0,  1000 );
        ObjectAnimator anim8 = ObjectAnimator.ofFloat(mBlueBall26,
                "y", 0,  1000 );
        ObjectAnimator anim9 = ObjectAnimator.ofFloat(mBlueBall9,
                "y", 0,  1000 );
        ObjectAnimator anim10 = ObjectAnimator.ofFloat(mBlueBall11,
                "y", 0,  1000 );
        ObjectAnimator anim11 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000);
        ObjectAnimator anim12 = ObjectAnimator.ofFloat(mBlueBall8,
                "y", 0,  1000 );
        ObjectAnimator anim13 = ObjectAnimator.ofFloat(mBlueBall12,
                "y", 0,  1000 );
        ObjectAnimator anim14 = ObjectAnimator.ofFloat(mBlueBall26,
                "y", 0,  1000 );
        ObjectAnimator anim15 = ObjectAnimator.ofFloat(mBlueBall26,
                "y", 0,  1000 );
        ObjectAnimator anim16 = ObjectAnimator.ofFloat(mBlueBall26,
                "y", 0,  1000 );
        ObjectAnimator anim17 = ObjectAnimator.ofFloat(mBlueBall12,
                "y", 0,  1000 );
        ObjectAnimator anim18 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000 );
        ObjectAnimator anim19 = ObjectAnimator.ofFloat(mBlueBall12,
                "y", 0,  1000 );
        ObjectAnimator anim20 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000 );
        ObjectAnimator anim21 = ObjectAnimator.ofFloat(mBlueBall12,
                "y", 0,  1000 );
        ObjectAnimator anim22 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000 );
        ObjectAnimator anim23 = ObjectAnimator.ofFloat(mBlueBall8,
                "y", 0,  1000 );
        ObjectAnimator anim24 = ObjectAnimator.ofFloat(mBlueBall26,
                "y", 0,  1000 );
        ObjectAnimator anim25 = ObjectAnimator.ofFloat(mBlueBall9,
                "y", 0,  1000 );
        ObjectAnimator anim26 = ObjectAnimator.ofFloat(mBlueBall11,
                "y", 0,  1000);
        ObjectAnimator anim27 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000 );
        ObjectAnimator anim28 = ObjectAnimator.ofFloat(mBlueBall9,
                "y", 0,  1000 );
        ObjectAnimator anim29 = ObjectAnimator.ofFloat(mBlueBall8,
                "y", 0,  1000 );
        ObjectAnimator anim30 = ObjectAnimator.ofFloat(mBlueBall26,
                "y", 0,  1000 );
        ObjectAnimator anim31 = ObjectAnimator.ofFloat(mBlueBall26,
                "y", 0,  1000 );
        ObjectAnimator anim32 = ObjectAnimator.ofFloat(mBlueBall26,
                "y", 0,  1000 );


        /**
         * anim1，anim2,anim3同时执行
         * anim4接着执行
         */
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(anim2).after(anim1);
        animSet.play(anim3).after(anim2);
        animSet.play(anim4).after(anim3);
        animSet.play(anim5).after(anim4);
        animSet.play(anim6).after(anim5);
        animSet.play(anim7).after(anim6);
        animSet.play(anim8).after(anim7);
        animSet.play(anim9).after(anim8);
        animSet.play(anim10).after(anim9);
        animSet.play(anim11).after(anim10);
        animSet.play(anim12).after(anim11);
        animSet.play(anim13).after(anim12);
        animSet.play(anim14).after(anim13);
        animSet.play(anim15).after(anim14);;
        animSet.play(anim16).after(anim15);;
        animSet.play(anim17).after(anim16);
        animSet.play(anim18).after(anim17);
        animSet.play(anim19).after(anim18);
        animSet.play(anim20).after(anim19);
        animSet.play(anim21).after(anim20);;
        animSet.play(anim22).after(anim21);
        animSet.play(anim23).after(anim22);
        animSet.play(anim24).after(anim23);
        animSet.play(anim25).after(anim24);
        animSet.play(anim26).after(anim25);
        animSet.play(anim27).after(anim26);
        animSet.play(anim28).after(anim27);
        animSet.play(anim29).after(anim28);
        animSet.play(anim30).after(anim29);
        animSet.play(anim31).after(anim30);
        animSet.play(anim32).after(anim31);

        animSet.setDuration(1000);
        animSet.start();
    }

    /**
     * 小星星
     *
     * @param
     */
    public void stars()
    {
        //float cx = mBlueBall.getX();
        //float cy = mBlueBall.getX();
        //float cy2 = mBlueBall2.getX();
        //float cy3 = mBlueBall3.getX();
        //float cy4 = mBlueBall4.getX();
        //float cy5 = mBlueBall5.getX();

        //ObjectAnimator anim1 = ObjectAnimator.ofFloat(mBlueBall, "scaleX",
        //1.0f, 2f);
        //ObjectAnimator anim2 = ObjectAnimator.ofFloat(mBlueBall, "scaleY",
        //1.0f, 2f);333/333/35123/444/433/322125
        //mBlueBall26为空动画
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(mBlueBall8,
                "y", 0,  1000 );
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(mBlueBall8,
                "y", 0,  1000 );
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(mBlueBall12,
                "y", 0,  1000);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(mBlueBall12,
                "y", 0,  1000 );
        ObjectAnimator anim5 = ObjectAnimator.ofFloat(mBlueBall13,
                "y", 0,  1000 );
        ObjectAnimator anim6 = ObjectAnimator.ofFloat(mBlueBall13,
                "y", 0,  1000 );
        ObjectAnimator anim7 = ObjectAnimator.ofFloat(mBlueBall12,
                "y", 0,  1000 );
        ObjectAnimator anim8 = ObjectAnimator.ofFloat(mBlueBall26,
                "y", 0,  1000 );
        ObjectAnimator anim9 = ObjectAnimator.ofFloat(mBlueBall11,
                "y", 0,  1000 );
        ObjectAnimator anim10 = ObjectAnimator.ofFloat(mBlueBall11,
                "y", 0,  1000 );
        ObjectAnimator anim11 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000);
        ObjectAnimator anim12 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000 );
        ObjectAnimator anim13 = ObjectAnimator.ofFloat(mBlueBall9,
                "y", 0,  1000 );
        ObjectAnimator anim14 = ObjectAnimator.ofFloat(mBlueBall9,
                "y", 0,  1000 );
        ObjectAnimator anim15 = ObjectAnimator.ofFloat(mBlueBall8,
                "y", 0,  1000 );
        ObjectAnimator anim16 = ObjectAnimator.ofFloat(mBlueBall26,
                "y", 0,  1000 );
        ObjectAnimator anim17 = ObjectAnimator.ofFloat(mBlueBall12,
                "y", 0,  1000 );
        ObjectAnimator anim18 = ObjectAnimator.ofFloat(mBlueBall12,
                "y", 0,  1000 );
        ObjectAnimator anim19 = ObjectAnimator.ofFloat(mBlueBall11,
                "y", 0,  1000 );
        ObjectAnimator anim20 = ObjectAnimator.ofFloat(mBlueBall11,
                "y", 0,  1000 );
        ObjectAnimator anim21 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000 );
        ObjectAnimator anim22 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000 );
        ObjectAnimator anim23 = ObjectAnimator.ofFloat(mBlueBall9,
                "y", 0,  1000 );
        ObjectAnimator anim24 = ObjectAnimator.ofFloat(mBlueBall26,
                "y", 0,  1000 );
        ObjectAnimator anim25 = ObjectAnimator.ofFloat(mBlueBall12,
                "y", 0,  1000 );
        ObjectAnimator anim26 = ObjectAnimator.ofFloat(mBlueBall12,
                "y", 0,  1000);
        ObjectAnimator anim27 = ObjectAnimator.ofFloat(mBlueBall11,
                "y", 0,  1000 );
        ObjectAnimator anim28 = ObjectAnimator.ofFloat(mBlueBall11,
                "y", 0,  1000 );
        ObjectAnimator anim29 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000 );
        ObjectAnimator anim30 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000 );
        ObjectAnimator anim31 = ObjectAnimator.ofFloat(mBlueBall9,
                "y", 0,  1000 );
        ObjectAnimator anim32 = ObjectAnimator.ofFloat(mBlueBall26,
                "y", 0,  1000 );
        ObjectAnimator anim33 = ObjectAnimator.ofFloat(mBlueBall8,
                "y", 0,  1000 );
        ObjectAnimator anim34 = ObjectAnimator.ofFloat(mBlueBall8,
                "y", 0,  1000 );
        ObjectAnimator anim35 = ObjectAnimator.ofFloat(mBlueBall12,
                "y", 0,  1000);
        ObjectAnimator anim36 = ObjectAnimator.ofFloat(mBlueBall12,
                "y", 0,  1000 );
        ObjectAnimator anim37 = ObjectAnimator.ofFloat(mBlueBall13,
                "y", 0,  1000 );
        ObjectAnimator anim38 = ObjectAnimator.ofFloat(mBlueBall13,
                "y", 0,  1000 );
        ObjectAnimator anim39 = ObjectAnimator.ofFloat(mBlueBall12,
                "y", 0,  1000 );
        ObjectAnimator anim40 = ObjectAnimator.ofFloat(mBlueBall26,
                "y", 0,  1000 );
        ObjectAnimator anim41 = ObjectAnimator.ofFloat(mBlueBall11,
                "y", 0,  1000 );
        ObjectAnimator anim42 = ObjectAnimator.ofFloat(mBlueBall11,
                "y", 0,  1000 );
        ObjectAnimator anim43 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000);
        ObjectAnimator anim44 = ObjectAnimator.ofFloat(mBlueBall10,
                "y", 0,  1000 );
        ObjectAnimator anim45 = ObjectAnimator.ofFloat(mBlueBall9,
                "y", 0,  1000 );
        ObjectAnimator anim46 = ObjectAnimator.ofFloat(mBlueBall9,
                "y", 0,  1000 );
        ObjectAnimator anim47 = ObjectAnimator.ofFloat(mBlueBall8,
                "y", 0,  1000 );
        ObjectAnimator anim48 = ObjectAnimator.ofFloat(mBlueBall26,
                "y", 0,  1000 );


        /**
         * anim1，anim2,anim3同时执行
         * anim4接着执行
         */
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(anim2).after(anim1);
        animSet.play(anim3).after(anim2);
        animSet.play(anim4).after(anim3);
        animSet.play(anim5).after(anim4);
        animSet.play(anim6).after(anim5);
        animSet.play(anim7).after(anim6);
        animSet.play(anim8).after(anim7);
        animSet.play(anim9).after(anim8);
        animSet.play(anim10).after(anim9);
        animSet.play(anim11).after(anim10);
        animSet.play(anim12).after(anim11);
        animSet.play(anim13).after(anim12);
        animSet.play(anim14).after(anim13);
        animSet.play(anim15).after(anim14);;
        animSet.play(anim16).after(anim15);;
        animSet.play(anim17).after(anim16);
        animSet.play(anim18).after(anim17);
        animSet.play(anim19).after(anim18);
        animSet.play(anim20).after(anim19);
        animSet.play(anim21).after(anim20);;
        animSet.play(anim22).after(anim21);
        animSet.play(anim23).after(anim22);
        animSet.play(anim24).after(anim23);
        animSet.play(anim25).after(anim24);
        animSet.play(anim26).after(anim25);
        animSet.play(anim27).after(anim26);
        animSet.play(anim28).after(anim27);
        animSet.play(anim29).after(anim28);
        animSet.play(anim30).after(anim29);
        animSet.play(anim31).after(anim30);
        animSet.play(anim32).after(anim31);
        animSet.play(anim33).after(anim32);
        animSet.play(anim34).after(anim33);
        animSet.play(anim35).after(anim34);
        animSet.play(anim36).after(anim35);
        animSet.play(anim37).after(anim36);
        animSet.play(anim38).after(anim37);
        animSet.play(anim39).after(anim38);
        animSet.play(anim40).after(anim39);
        animSet.play(anim41).after(anim40);
        animSet.play(anim42).after(anim41);
        animSet.play(anim43).after(anim42);
        animSet.play(anim44).after(anim43);
        animSet.play(anim45).after(anim44);
        animSet.play(anim46).after(anim45);
        animSet.play(anim47).after(anim46);
        animSet.play(anim48).after(anim47);

        animSet.setDuration(1000);
        animSet.start();
    }
}

