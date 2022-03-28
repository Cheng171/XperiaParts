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


public class QSSettingsActivity extends Activity implements OnClickListener
{
	private Button qs2c,qs3c,qs3r,qs4r,qs5r,textsmall,textbig;
	//private TextView tv;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qs);
		init();
	}

	private void init(){
		//初始化按钮控件
		qs2c=(Button) findViewById(R.id.qs2c);
		qs3c=(Button) findViewById(R.id.qs3c);
		qs3r=(Button) findViewById(R.id.qs3r);
		qs4r=(Button) findViewById(R.id.qs4r);
		qs5r=(Button) findViewById(R.id.qs5r);
		textsmall=(Button) findViewById(R.id.textsmall);
		textbig=(Button) findViewById(R.id.textbig);
		// tv=(TextView) findViewById(R.id.xingnengTextView);
		//设置按钮监听
	    qs2c.setOnClickListener(this);
		qs3c.setOnClickListener(this);
		qs3r.setOnClickListener(this);
		qs4r.setOnClickListener(this);
		qs5r.setOnClickListener(this);
		textsmall.setOnClickListener(this);
		textbig.setOnClickListener(this);
	}

	@Override
	public void onClick(View view){
		switch(view.getId()){
			case R.id.textbig:
				//点击禁用状态栏小字
				execShell("cmd overlay disable com.android.systemui.qstile.smalltext.overlay");
				execShell("cmd overlay disable com.android.systemui.qstile.twotext.overlay");
				break;
			case R.id.textsmall:
				//点击开启状态栏小字
				execShell("cmd overlay enable com.android.systemui.qstile.smalltext.overlay");
				break;

			case R.id.qs2c:
				//点击状态栏2行
				execShell("cmd overlay disable com.android.systemui.qstiles3c");
			break;
			case R.id.qs3c:
				//点击状态栏3行
				execShell("cmd overlay enable com.android.systemui.qstiles3c");
				execShell("cmd overlay enable com.android.systemui.qstile.twotext.overlay");
				break;
			case R.id.qs3r:
				//点击状态栏3列
				execShell("cmd overlay disable com.android.systemui.qstiles5r");
				execShell("cmd overlay enable com.android.systemui.qstiles3r");
				break;
			case R.id.qs4r:
				//点击状态栏4列
				execShell("cmd overlay disable com.android.systemui.qstiles3r");
				execShell("cmd overlay disable com.android.systemui.qstiles5r");
				break;
			case R.id.qs5r:
				//点击状态栏5列
				execShell("cmd overlay disable com.android.systemui.qstiles3r");
				execShell("cmd overlay enable com.android.systemui.qstiles5r");
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
