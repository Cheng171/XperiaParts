package com.cramfs28.xperiaparts;
import android.app.*;
import android.widget.*;
import android.os.*;
import android.view.View.*;
import android.view.*;
import java.io.*;
import android.content.*;
import android.net.*;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.Process;


public class StatusBarSettingsActivity extends Activity implements OnClickListener
{
	private Button volteon,volteoff,inouton,inoutoff,voltelogo1,voltelogo2,voltelogo3,revoltelogo;
	//private TextView tv;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statusbar);
		init();
	}

	private void init(){
		//初始化按钮控件
		volteoff=(Button) findViewById(R.id.volteoff);
		volteon=(Button) findViewById(R.id.volteon);
		voltelogo1=(Button) findViewById(R.id.voltelogo1);
		voltelogo2=(Button) findViewById(R.id.voltelogo2);
		voltelogo3=(Button) findViewById(R.id.voltelogo3);
		revoltelogo=(Button) findViewById(R.id.revoltelogo);
		inoutoff=(Button) findViewById(R.id.inoutoff);
		inouton=(Button) findViewById(R.id.inouton);
		// tv=(TextView) findViewById(R.id.xingnengTextView);
		//设置按钮监听
	    volteon.setOnClickListener(this);
	    volteoff.setOnClickListener(this);
	    voltelogo1.setOnClickListener(this);
		voltelogo2.setOnClickListener(this);
		voltelogo3.setOnClickListener(this);
		revoltelogo.setOnClickListener(this);
		inoutoff.setOnClickListener(this);
		inouton.setOnClickListener(this);
	}

	@Override
	public void onClick(View view){
		switch(view.getId()){
			case R.id.volteoff:
				//点击关闭VOLTE显示
				execShell("cmd overlay enable com.android.systemui.voltehide");
			break;
			case R.id.volteon:
				//点击开启VOLTE显示
				execShell("cmd overlay disable com.android.systemui.voltehide");
			break;

			case R.id.voltelogo1:
				//点击切换样式1-长方形
				execShell("cmd overlay disable com.android.systemui.voltebt");
				execShell("cmd overlay disable com.android.systemui.voltesquare");
				execShell("cmd overlay disable com.android.systemui.voltehd");
				execShell("cmd overlay enable com.android.systemui.voltebt");
				break;
			case R.id.voltelogo2:
				//点击切换样式2-正方形
				execShell("cmd overlay disable com.android.systemui.voltebt");
				execShell("cmd overlay disable com.android.systemui.voltesquare");
				execShell("cmd overlay disable com.android.systemui.voltehd");
				execShell("cmd overlay enable com.android.systemui.voltesquare");
				break;
			case R.id.voltelogo3:
				//点击切换样式3-HD
				execShell("cmd overlay disable com.android.systemui.voltebt");
				execShell("cmd overlay disable com.android.systemui.voltesquare");
				execShell("cmd overlay disable com.android.systemui.voltehd");
				execShell("cmd overlay enable com.android.systemui.voltehd");
				break;
			case R.id.revoltelogo:
				//点击还原
				execShell("cmd overlay disable com.android.systemui.voltebt");
				execShell("cmd overlay disable com.android.systemui.voltesquare");
				execShell("cmd overlay disable com.android.systemui.voltehd");
				break;




			case R.id.inouton:
				//点击启用inout
				execShell("mv /data/adb/modules/guangai_at52_12/system/system_ext/priv-app/SystemUI/SystemUI.apk.disinout /data/adb/modules/guangai_at52_12/system/system_ext/priv-app/SystemUI/SystemUI.apk");
				Toast.makeText(getApplicationContext(), "完成，手动重启后生效",
						Toast.LENGTH_SHORT).show();
			break;
			case R.id.inoutoff:
				//点击禁用inout
				execShell("mv /data/adb/modules/guangai_at52_12/system/system_ext/priv-app/SystemUI/SystemUI.apk /data/adb/modules/guangai_at52_12/system/system_ext/priv-app/SystemUI/SystemUI.apk.disinout");
				Toast.makeText(getApplicationContext(), "完成，手动重启后生效",
						Toast.LENGTH_SHORT).show();
				break;

		}
	}
	
	
//	public void execCommand(String command) throws IOException {   
//	// start the ls command running    //String[] args =  new String[]{"sh", "-c", command};    
//	Runtime runtime = Runtime.getRuntime();   
//		Process proc = runtime.exec(command);       
//	//这句话就是shell与高级语言间的调用   
//	//如果有参数的话可以用另外一个被重载的exec方法   
//	//实际上这样执行时启动了一个子进程,它没有父进程的控制台    
//    //也就看不到输出,所以需要用输出流来得到shell执行后的输出   
//	InputStream inputstream = proc.getInputStream();
//	InputStreamReader inputstreamreader = new InputStreamReader(inputstream);       
//	BufferedReader bufferedreader = new BufferedReader(inputstreamreader);     
//	// read the ls output     
//	String line = "";       
//	StringBuilder sb = new StringBuilder(line);   
//	while ((line = bufferedreader.readLine()) != null) {    
//	//System.out.println(line);      
//	sb.append(line);           
//	sb.append('\n');        }    
//    //tv.setText(sb.toString());   
//	//使用exec执行不会等执行成功以后才返回,它会立即返回    
//    //所以在某些情况下是很要命的(比如复制文件的时候)      
//	//使用wairFor()可以等待命令执行完成以后才返回    
//    try {         
//	if (proc.waitFor() != 0) {     
//	System.err.println("exit value = " + proc.exitValue());           
//	}       
//	}     
//	catch (InterruptedException e) { 
//	System.err.println(e);   
//	}    
//	}
	
	
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
