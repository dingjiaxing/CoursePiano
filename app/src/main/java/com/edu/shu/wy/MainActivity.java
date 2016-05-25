package com.edu.shu.wy;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void doClick(View v){
        switch (v.getId()){
            case R.id.main_keyboard:
                startActivity(new Intent(MainActivity.this,KeyboardActivity.class));
                break;
            case R.id.main_record:
                startActivity(new Intent(MainActivity.this,RecordActivity.class));
                break;
            case R.id.main_learn:
                startActivity(new Intent(MainActivity.this,LearnActivity.class));
                break;
            case R.id.main_score:
                startActivity(new Intent(MainActivity.this,ScoreActivity.class));
                break;
            default:
                break;

        }
    }
}
