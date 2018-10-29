package com.g.cn.combtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "f22faad4328db80ce680513ff8d2220a");


        Button button_login = (Button) findViewById(R.id.button2);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取输入框内容
                TextView text_user = (TextView) findViewById(R.id.editText);
                TextView text_password = (TextView) findViewById(R.id.editText2);
                //判断非空
               final String user = text_user.getText().toString();
                final String password = text_password.getText().toString();
                if (user.equals("") || user.isEmpty() || password.equals("") || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "请输入用户名和密码", LENGTH_SHORT).show();
                    return;
                }
                //实例化用户表更新或则添加时打开，查询时关闭
               //  MyUsers us = new MyUsers();
               //us.setUsername(user);
                //us.setPassword(password);













                BmobQuery bmobQuery = new BmobQuery("MyUsers");
                bmobQuery.addWhereEqualTo("username", user);
                bmobQuery.addWhereEqualTo("password", password);
                bmobQuery.findObjectsByTable(new QueryListener<JSONArray>() {
                    @Override
                    public void done(JSONArray jsonArray, BmobException e) {
                        if (e == null)
                        {
                                Log.d("登录", "查询成功：" + jsonArray.toString());
                                if (jsonArray.length() > 0) {
                                    try {
                                        JSONObject object = jsonArray.getJSONObject(0);
                                        Toast.makeText(MainActivity.this, "用户名或则密码正确", LENGTH_SHORT).show();
                                    } catch (JSONException e1) {
                                        e1.printStackTrace();
                                    }

                                }
                                else{
                                    Toast.makeText(MainActivity.this, "用户名或则密码不正确", LENGTH_SHORT).show();

                                }
                        }
                        else {
                            Log.d("登录", "查询失败：" + e.getMessage() + "," + e.getErrorCode());
                        }
                    }
                });














//                BmobQuery <MyUsers>bmobQuery = new BmobQuery("MyUsers");//根据objectID查询是否在表里面
//                bmobQuery.addWhereEqualTo("username",user );
//                bmobQuery.addWhereEqualTo("password",password);
//                bmobQuery.getObject("66fe9f5a01", new QueryListener<MyUsers>() {
//                    @Override
//                    public void done(MyUsers myUsers, BmobException e) {
//                        if(e==null){
//                            Toast.makeText(MainActivity.this, "查询成功", LENGTH_SHORT).show();
//                        }else{
//                            Toast.makeText(MainActivity.this, "查询失败", LENGTH_SHORT).show();
//                        }
//                    }
//                });



//                us.setUsername(user);//更新
//                us.update("e843dc7b92", new UpdateListener() {
//
//                    @Override
//                    public void done(BmobException e) {
//                        if(e==null){
//                            Toast.makeText(MainActivity.this, "更新成功", LENGTH_SHORT).show();;
//                        }else{
//                            Toast.makeText(MainActivity.this, "更新失败", LENGTH_SHORT).show();
//                        }
//                    }
//
//                });






//                us.save(new SaveListener<String>() {//添加
//                    @Override
//                    public void done(String s, BmobException e) {
//                        if (e == null) {
//                            //Intent intent=new Intent(LoginActivity.this,MainActivity.class);
//                            //startActivity(intent);
//                            //finish();
//                            Toast.makeText(MainActivity.this, "添加成功"+user+" "+password, LENGTH_SHORT).show();
//                        } else {
////                            Toast.makeText(LoginActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
//                            Toast.makeText(MainActivity.this, "添加失败", LENGTH_SHORT).show();
//                        }
//                    }
//                });


            }
        });
    }
}

