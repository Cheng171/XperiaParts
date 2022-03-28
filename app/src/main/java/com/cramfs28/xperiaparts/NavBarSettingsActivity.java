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


public class NavBarSettingsActivity extends Activity implements OnClickListener
{
	private Button enable_nougatnav,disable_nougatnav,disable_line_height,enable_line_height,disable_line;
	//private TextView tv;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_navbar);
		init();
	}

	private void init(){
		//初始化按钮控件
		// NOUGAT导航键
		enable_nougatnav=(Button) findViewById(R.id.enable_nougatnav);
		disable_nougatnav=(Button) findViewById(R.id.disable_nougatnav);
		// 小白条隐藏
		disable_line_height=(Button) findViewById(R.id.disable_line_height);
		enable_line_height=(Button) findViewById(R.id.enable_line_height);
		disable_line=(Button) findViewById(R.id.disable_line);
		// tv=(TextView) findViewById(R.id.xingnengTextView);
		//设置按钮监听
		enable_nougatnav.setOnClickListener(this);
		disable_nougatnav.setOnClickListener(this);

		disable_line_height.setOnClickListener(this);
		enable_line_height.setOnClickListener(this);
		disable_line.setOnClickListener(this);
	}

	@Override
	public void onClick(View view){
		switch(view.getId()){
			case R.id.enable_nougatnav:
				//点击启用NOUGAT导航键
				execShell("cmd overlay enable com.android.systemui.nougatnav");
				execShell("killall com.android.systemui");
				break;
			case R.id.disable_nougatnav:
				//点击还原导航键样式
				execShell("cmd overlay disable com.android.systemui.nougatnav");
				execShell("killall com.android.systemui");
				break;

			case R.id.disable_line_height:
				//点击隐藏白条调低输入法高度
				execShell("rm -rf /data/adb/modules/hide_bar");
				execShell("mkdir -p /data/adb/modules/hide_bar");
				execShell("cp -p -R -f /system/etc/xe/hide_bar /data/adb/modules");
				Toast.makeText(getApplicationContext(), "隐藏完成，手动重启后生效",
						Toast.LENGTH_SHORT).show();
				break;
			case R.id.enable_line_height:
				//点击保留白条恢复输入法高度
				execShell("rm -rf /data/adb/modules/hide_bar");
				Toast.makeText(getApplicationContext(), "恢复完成，手动重启后生效",
						Toast.LENGTH_SHORT).show();
				break;
			case R.id.disable_line:
				//点击保留白条调低输入法高度
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
