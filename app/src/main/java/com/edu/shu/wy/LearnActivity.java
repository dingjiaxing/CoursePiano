package com.edu.shu.wy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
public class LearnActivity extends Activity {
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

    private Button buttonStart,buttonStart2; // 开始按钮
    private Button buttonStop,buttonStop2; // 停止按钮
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
        setContentView(R.layout.activity_learn);


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
        list.add("请选择要弹奏的谱子");
        list.add("小星星：1155665~  4433221~ 5544332~ 5544332~ 1155665~ 4433221~");
        list.add("生日快乐：5-5-6-5-17-~ 5-5-6-5-21~ 5-5-5317-6- 443121~~");
        list.add("两只老虎：1231 1231 345~ 345~ 565431 565431 35-1 35-1");
        list.add("铃儿响叮当：333~ 333~ 3512 3~ 4444 433~ 3221 2~5~\n" +
                "           333~ 333~ 3512 3~ 4444 433~ 5542 1~");
        list.add("世上只有妈妈好：6535 1+656~ 35653 16-532~ 23556 321~ 53216-1 5-~");
        list.add("我是一个粉刷匠：5353 531~ 2431 5~ 5353 531~ 2432 1~ 5353 531~ 2431 5~ 5353 531~ 2432 1~ ");
        list.add("上学歌：1231 5~ 661+6 5~ 661+~ 563~ 6535 3123 1~");
        myTextView = (TextView)findViewById(R.id.TextView_Show);
        mySpinner = (Spinner)findViewById(R.id.spinner_City);
        //第二步：为下拉列表定义一个适配器，这里就用到里前面定义的list。
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
        //第三步：为适配器设置下拉列表下拉时的菜单样式。
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        mySpinner.setAdapter(adapter);
        //第五步：为下拉列表设置各种事件的响应，这个事响应菜单被选中
        mySpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                    /* 将所选mySpinner 的值带入myTextView 中*/
                myTextView.setText(""+ adapter.getItem(arg2));
                    /* 将mySpinner 显示*/
                arg0.setVisibility(View.VISIBLE);
            }
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                myTextView.setText("NONE");
                arg0.setVisibility(View.VISIBLE);
            }
        });
            /*下拉菜单弹出的内容选项触屏事件处理*/
        mySpinner.setOnTouchListener(new Spinner.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                    /* 将mySpinner 隐藏，不隐藏也可以，看自己爱好*/
                v.setVisibility(View.INVISIBLE);
                return false;
            }
        });
            /*下拉菜单弹出的内容选项焦点改变事件处理*/
        mySpinner.setOnFocusChangeListener(new Spinner.OnFocusChangeListener(){
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                v.setVisibility(View.VISIBLE);
            }
        });

        //第一步：添加一个下拉列表项的list，这里添加的项就是下拉列表的菜单项
        list2.add("请选择播放歌曲");
        list2.add("小星星");
        list2.add("生日快乐");
        list2.add("两只老虎");
        list2.add("铃儿响叮当");
        list2.add("世上只有妈妈好");
        list2.add("我是一个粉刷匠");
        list2.add("上学歌");
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
                    bgPlayer=MediaPlayer.create(LearnActivity.this,R.raw.star);
                    bgPlayer.start();
                }
                if(adapter2.getItem(arg2)=="生日快乐")
                {
                    bgPlayer=MediaPlayer.create(LearnActivity.this,R.raw.birthday);
                    bgPlayer.start();
                }
                if(adapter2.getItem(arg2)=="两只老虎")
                {
                    bgPlayer=MediaPlayer.create(LearnActivity.this,R.raw.twotigers);
                    bgPlayer.start();
                }
                if(adapter2.getItem(arg2)=="铃儿响叮当")
                {
                    bgPlayer=MediaPlayer.create(LearnActivity.this,R.raw.bells);
                    bgPlayer.start();
                }
                if(adapter2.getItem(arg2)=="世上只有妈妈好")
                {
                    bgPlayer=MediaPlayer.create(LearnActivity.this,R.raw.mom);
                    bgPlayer.start();
                }
                if(adapter2.getItem(arg2)=="我是一个粉刷匠")
                {
                    bgPlayer=MediaPlayer.create(LearnActivity.this,R.raw.brush);
                    bgPlayer.start();
                }
                if(adapter2.getItem(arg2)=="上学歌")
                {
                    bgPlayer=MediaPlayer.create(LearnActivity.this,R.raw.school);
                    bgPlayer.start();
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
        // 开始
        buttonStart2 = (Button) findViewById(R.id.button_start2);
        // 停止
        buttonStop2 = (Button) findViewById(R.id.button_stop2);
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
            Toast.makeText(LearnActivity.this, "请插入SD卡以便存储录音",
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
        adaperListAudio = new ArrayAdapter<String>(LearnActivity.this,
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

        // 开始按钮
        buttonStart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                try{
                    bgPlayer=MediaPlayer.create(LearnActivity.this,R.raw.bg);
                    bgPlayer.start();
                    buttonStart2.setEnabled(false);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        // 停止按钮
        buttonStop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                try{
//                    bgPlayer=MediaPlayer.create(KeyboardActivity.this,R.raw.bg);
                    bgPlayer.stop();
                    buttonStart2.setEnabled(true);
                }catch (Exception e){
                    e.printStackTrace();
                }

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
                    ShowDialog.showTheAlertDialog(LearnActivity.this, "该文件不存在");
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
        AlertDialog dialog = new AlertDialog.Builder(LearnActivity.this)
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
                    ShowDialog.showTheAlertDialog(LearnActivity.this, "该文件不存在");
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
                MediaPlayer mp=MediaPlayer.create(LearnActivity.this,BlackVoice[i-15]);
                mp.start();
            }
        }
    }

}
