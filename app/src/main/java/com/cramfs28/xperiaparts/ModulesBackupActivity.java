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


public class ModulesBackupActivity extends Activity implements OnClickListener
{
	private Button backup_modules,restore_modules,delete_backup;
	//private TextView tv;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modules_backup);
		init();
	}

	private void init(){
		//初始化按钮控件
		backup_modules=(Button) findViewById(R.id.backup_modules);
		restore_modules=(Button) findViewById(R.id.restore_modules);
		delete_backup=(Button) findViewById(R.id.delete_backup);
		// tv=(TextView) findViewById(R.id.xingnengTextView);

		//设置按钮监听
		backup_modules.setOnClickListener(this);
		restore_modules.setOnClickListener(this);
		delete_backup.setOnClickListener(this);
	}

	@Override
	public void onClick(View view){
		switch(view.getId()){
			case R.id.backup_modules:
				//点击备份所有模块
				execShell("mkdir -p /sdcard/_MODULES/modules");
				execShell("mkdir -p /sdcard/_MODULES/riru");
				execShell("cp -p -R -f /data/adb/modules /sdcard/_MODULES");
				execShell("cp -p -R -f /data/adb/riru /sdcard/_MODULES");
				execShell("rm -rf /sdcard/_MODULES/modules/SomcUI_inout");
				execShell("rm -rf /sdcard/_MODULES/modules/wallpaper_scale");
				execShell("rm -rf /sdcard/_MODULES/modules/hide_bar");
				Toast.makeText(getApplicationContext(), "备份成功，已备份到_MODULES文件夹",
						Toast.LENGTH_SHORT).show();
			break;
			case R.id.restore_modules:
				//点击恢复已备份的模块
				execShell("mkdir -p /data/adb/modules");
				execShell("mkdir -p /data/adb/riru");
				execShell("cp -p -R -f /sdcard/_MODULES/modules /data/adb");
				execShell("cp -p -R -f /sdcard/_BACKUP_MODULES/riru /data/adb");
				Toast.makeText(getApplicationContext(), "恢复完成，手动重启后生效",
						Toast.LENGTH_SHORT).show();
			break;

			case R.id.delete_backup:
				//点击删除备份文件
				execShell("rm -rf /sdcard/_BACKUP_MODULES");
				Toast.makeText(getApplicationContext(), "删除成功",
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
