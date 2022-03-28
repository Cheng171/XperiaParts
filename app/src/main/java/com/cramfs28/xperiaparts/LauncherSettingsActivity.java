package com.cramfs28.xperiaparts;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;


public class LauncherSettingsActivity extends Activity implements OnClickListener
{
	//private TextView tv;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		init();
	}

	private void init(){
		//初始化按钮控件
		// 壁纸
		Button disable_wallpaperscale = (Button) findViewById(R.id.disable_wallpaperscale);
		Button enable_wallpaperscale = (Button) findViewById(R.id.enable_wallpaperscale);
		// tv=(TextView) findViewById(R.id.xingnengTextView);
		//设置按钮监听
		disable_wallpaperscale.setOnClickListener(this);
		enable_wallpaperscale.setOnClickListener(this);

	}

	@SuppressLint("NonConstantResourceId")
	@Override
	public void onClick(View view){
		switch(view.getId()){
			case R.id.disable_wallpaperscale:
				//点击禁用壁纸缩放
				execShell("rm /data/adb/modules/guangai_at52_12/system/product/overlay/WallpaperMaxScale/WallpaperMaxScale.apk");
				execShell("cp /data/adb/modules/guangai_at52_12/system/product/overlay/WallpaperMaxScale/WallpaperMaxScale.disable /data/adb/modules/guangai_at52_12/system/product/overlay/WallpaperMaxScale/WallpaperMaxScale.apk");
				Toast.makeText(getApplicationContext(), "禁用完成，手动重启后生效",
						Toast.LENGTH_SHORT).show();
			break;
			case R.id.enable_wallpaperscale:
				//点击启用壁纸缩放
				execShell("rm /data/adb/modules/guangai_at52_12/system/product/overlay/WallpaperMaxScale/WallpaperMaxScale.apk");
				execShell("cp /data/adb/modules/guangai_at52_12/system/product/overlay/WallpaperMaxScale/WallpaperMaxScale.enable /data/adb/modules/guangai_at52_12/system/product/overlay/WallpaperMaxScale/WallpaperMaxScale.apk");
				Toast.makeText(getApplicationContext(), "启用完成，手动重启后生效",
						Toast.LENGTH_SHORT).show();
			break;

		}
	}


	public void execShell(String cmd){
    	try{  
            //权限设置
            Process p = Runtime.getRuntime().exec("su");  //开始执行shell脚本
            //获取输出流
            OutputStream outputStream = p.getOutputStream();
            DataOutputStream dataOutputStream=new DataOutputStream(outputStream);
            //将命令写入
            dataOutputStream.writeBytes(cmd);
            //提交命令
            dataOutputStream.flush();
            //关闭流操作
            dataOutputStream.close();
            outputStream.close();
				InputStream inputstream = p.getInputStream();
	InputStreamReader inputstreamreader = new InputStreamReader(inputstream);       
	BufferedReader bufferedreader = new BufferedReader(inputstreamreader);     
	// read the ls output     
	String line = "";       
	StringBuilder sb = new StringBuilder(line);   
	while ((line = bufferedreader.readLine()) != null) {    
	//System.out.println(line);      
	sb.append(line);           
	sb.append('\n');        }    
   // tv.setText(sb.toString());   
	//使用exec执行不会等执行成功以后才返回,它会立即返回    
    //所以在某些情况下是很要命的(比如复制文件的时候)      
	//使用wairFor()可以等待命令执行完成以后才返回    
		}  
		catch(Throwable t)  
        {  
			t.printStackTrace();  
		} 
    }
}
