package com.example.baidumaps;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

public class loginactivity extends Activity {
	
	//����һ����ť
	private View registerbutton;  //������ע�ᰴť
	private View forgetbutton;
	private View LoginButton;
	private EditText usernameTextView;
	private EditText passwordTextView;
	private SharedPreferences sp;
	private Editor editor;
	private CheckBox remUnameCheckBox;
	private CheckBox remPwdCheckBox;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);   //��ʼ��
		setContentView(R.layout.loginin);	
		//����Ҫ��ʾ���ļ�
		sp=getSharedPreferences("baidumap",Context.MODE_PRIVATE);
		editor =sp.edit();
		LoginButton=findViewById(R.id.loginbutton777);
		usernameTextView=(EditText) findViewById(R.id.loginusername);
		remUnameCheckBox=(CheckBox) findViewById(R.id.remunamecheckBox666);
		passwordTextView=(EditText) findViewById(R.id.loginuserpassword);
		remPwdCheckBox=(CheckBox) findViewById(R.id.remupasswdcheckBox777);
		registerbutton = findViewById(R.id.registerbutton3);
	
		if(sp.getBoolean("remUnameIsChecked", false)){
			usernameTextView.setText(sp.getString("remName", ""));
			remUnameCheckBox.setChecked(true);
			
		}
		if(sp.getBoolean("remPwdIsChecked", false)){
			passwordTextView.setText(sp.getString("remPwd", ""));
			remPwdCheckBox.setChecked(true);
			
		}
		
		registerbutton.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0){
				Intent intent=new Intent();
				intent.setClass(loginactivity.this, MainA.class);  //Ҫ��ת��activity.class ��loginactivity��Maina
				startActivity(intent);
				finish();
			}
		});
		
		forgetbutton=findViewById(R.id.forgetbutton999);
		forgetbutton.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0){
				Intent intent=new Intent();
				intent.setClass(loginactivity.this, forget.class);  //Ҫ��ת��activity.class ��loginactivity��Maina
				startActivity(intent);
				finish();
			}
		});
		
		
		LoginButton.setOnClickListener(new View.OnClickListener() {

			
			//����Ϸ����ж�
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String username = usernameTextView.getText().toString();
				String password = passwordTextView.getText().toString();
				String userinfo=sp.getString(username, "");//û��ѯ�����ؿ��ַ���
				if(username.length()<1){
					Toast.makeText(loginactivity.this, "�û�������Ϊ�գ�", Toast.LENGTH_SHORT).show();
					return;
				}
				
				if("".equals(userinfo)){
					Toast.makeText(loginactivity.this, "�û��������ڣ�", Toast.LENGTH_SHORT).show();
					return;
				}
				//�鵽����,���������ҵ�����
				String[] user =userinfo.split("_");  //�����»����и������
				if(password.length()<1){
					Toast.makeText(loginactivity.this, "���벻��Ϊ�գ�", Toast.LENGTH_SHORT).show();
					return;
				}
				if(!password.equals(user[1])){
					Toast.makeText(loginactivity.this, "�������", Toast.LENGTH_SHORT).show();
					return;
				}
				
				
				
				
				//��½�ɹ�
				//ҳ����ת����ͼҳ
			
				
				
				boolean uNameIsChecked = remUnameCheckBox.isChecked();
				boolean uPwdIsChecked = remPwdCheckBox.isChecked();
				if(uNameIsChecked){
					editor.putString("remName", username);
				}
				if(uPwdIsChecked){
					editor.putString("remPwd", password);
				}
				editor.putBoolean("remUnameIsChecked", uNameIsChecked);
				editor.putBoolean("remPwdIsChecked", uPwdIsChecked);
				editor.commit();
				
				
				Intent intent =new Intent();
				intent.setClass(loginactivity.this, BaiduMapActivity.class);
				startActivity(intent);
				finish();
				
			
				
				
				
				
				
				
				
			}
		});
		
		//����ס�������ü�����
		remPwdCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton cb, boolean ischecked) {
				// TODO Auto-generated method stub
				if(ischecked==true){
					remUnameCheckBox.setChecked(true);
				}
			}
		});
		
		remUnameCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean ischecked) {
				// TODO Auto-generated method stub
				if(ischecked==false){
					remPwdCheckBox.setChecked(false);
			}
			}
		});
		
	}
}