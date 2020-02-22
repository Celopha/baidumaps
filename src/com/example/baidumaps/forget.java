package com.example.baidumaps;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class forget extends Activity {

	private View sendbutton;
	private EditText usertext;
	private EditText eamiltext;
	private SharedPreferences sp;
	private Editor editor;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);   //��ʼ��
		setContentView(R.layout.forget);	
		sp=getSharedPreferences("baidumap",Context.MODE_PRIVATE);
		editor =sp.edit();
		
		eamiltext=(EditText) findViewById(R.id.sendemail);
		sendbutton = findViewById(R.id.sendbutton999);
		usertext=(EditText) findViewById(R.id.user444);
		
		
		sendbutton.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0){
				String user=eamiltext.getText().toString();
				String info=usertext.getText().toString();
				String userinfo=sp.getString(info, "");
				if(info.length()<1){
					Toast.makeText(forget.this, "�û�������Ϊ�գ�", Toast.LENGTH_SHORT).show();
					return;
				}
				
				if("".equals(userinfo)){
					Toast.makeText(forget.this, "�û��������ڣ�", Toast.LENGTH_SHORT).show();
					return;
				}
				String[] key =userinfo.split("_");
				if(!user.equals(key[7])){
					Toast.makeText(forget.this, "�������", Toast.LENGTH_SHORT).show();
					return;
				}
				
				Intent intent=new Intent();
				intent.setClass(forget.this, loginactivity.class);  //Ҫ��ת��activity.class ��loginactivity��Maina
				startActivity(intent);
				finish();
			}
		});
	}
}
