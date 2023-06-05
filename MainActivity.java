package com.addition_game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.viewmodel.CreationExtras;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ArrayList<Integer> NumList1 = new ArrayList<Integer>();
    private Button[] buttons = new Button[10];
    private TextView textView_input;
    private Button button_back;
    private String total_num = "";
    int count = 0;
    int timenum = 0;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView1 = findViewById(R.id.textView);
        TextView textView2 = findViewById(R.id.textView2);
        TextView textView_result = findViewById(R.id.button_result);
        TextView textview_timer = findViewById(R.id.textView_timer);
        TextView textView_score = findViewById(R.id.textView_score);
        EditText editTextnum = findViewById(R.id.editTextNumber);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button_result = findViewById(R.id.button_result);
        textView_input = findViewById(R.id.textView_input);
        button_back = findViewById(R.id.button_back);

        for(int i=0; i<10;i++){
            String buttonID = "button_" +i;
            int resID = getResources().getIdentifier(buttonID,"id",getPackageName());
            buttons[i] = findViewById(resID);
            buttons[i].setOnClickListener(this);
        }

        for (int i = 1; i<100; i++){
            NumList1.add(i);
        }


        /*CountDownTimer countDownTimer = new CountDownTimer(timenum*1000, 1000) {
            @Override
            public void onTick(long l) {
                textview_timer.setText(""+l/1000);
            }

            @Override
            public void onFinish() {

                textView1.setText(""); textView2.setText(""); textView_input.setText("");
                button1.setVisibility(View.VISIBLE);
                button_result.setVisibility(View.INVISIBLE);
                //textview_timer.setText("Finish");
            }
        };*/
        //gamestart
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //カウントダウンタイム値取得
                timenum = Integer.parseInt(editTextnum.getText().toString());
                //初期化
                textView_input.setText(""); textView_score.setText("0");
                total_num = ""; count = 0;
                button_result.setVisibility(View.VISIBLE);
                button1.setVisibility(View.INVISIBLE);
                //カウントダウン処理
                countDownTimer = new CountDownTimer(timenum*1000, 1000) {
                    @Override
                    public void onTick(long l) {
                        textview_timer.setText(""+l/1000);
                    }

                    @Override
                    public void onFinish() {

                        textView1.setText(""); textView2.setText(""); textView_input.setText("");
                        button1.setVisibility(View.VISIBLE);
                        button_result.setVisibility(View.INVISIBLE);
                        //textview_timer.setText("Finish");
                    }
                }.start();//カウントダウン開始
                //countDownTimer.start();
                textView1.setText(ListShuffle(NumList1)+"");
                textView2.setText(ListShuffle(NumList1)+"");
            }
        });
        //キャンセルボタン押下
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //カウントダウンキャンセル
                countDownTimer.cancel();
                textView1.setText(""); textView2.setText("");
                button1.setVisibility(View.VISIBLE);
                textview_timer.setText("");
            }
        });

        button_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //テキストボックスから値を取得
                int num1 = Integer.parseInt(textView1.getText().toString());
                int num2 = Integer.parseInt(textView2.getText().toString());
                int totalnum = Integer.parseInt(textView_input.getText().toString());
                //計算結果が正
                if((num1+num2) == totalnum){
                    count += 1;
                    textView_score.setText(""+count);
                    total_num = "";
                    textView1.setText(ListShuffle(NumList1)+"");
                    textView2.setText(ListShuffle(NumList1)+"");
                //計算結果が負
                }else{
                    Toast toast = Toast.makeText(MainActivity.this,"×",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        //×ボタン押下
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //後ろ一文字を削除
                    String get_nums = textView_input.getText().toString();
                if (total_num.length() > 0 ){ //get_nums.length() > 0){
                    total_num = total_num.substring(0,get_nums.length()-1);
                    textView_input.setText(total_num);
                }
            }
        });
    }
    //配列の中身をシャッフルしてその値を返す
    private int ListShuffle(ArrayList<Integer> NumList){
        Collections.shuffle(NumList);
        int num = NumList.get(0);
        return num;
    }

    @Override
    public void onClick(View view){
        //数字ボードから値を取得
        String get_num = ((Button) view).getText().toString();
        int buttons_num = Integer.parseInt(get_num);//数値型に変換
        //入力された数字を文字列として連結
        total_num += get_num;
        textView_input.setText(total_num);
    }

}