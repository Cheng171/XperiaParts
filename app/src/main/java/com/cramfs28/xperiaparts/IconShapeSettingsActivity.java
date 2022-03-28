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


public class IconShapeSettingsActivity extends Activity implements OnClickListener
{
	private Button restoreiconbt,cloudybt,cylinderbt,flowerbt,heartbt,hexagonbt,leafbt,mallowbt,pebblebt,roundedhexagonbt,roundedrectbt,squarebt,squirclebt,stretchedbt,taperedrectbt,teardropbt;
	//private TextView tv;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_icons);
		init();
	}

	private void init(){
		//初始化按钮控件
		restoreiconbt=(Button) findViewById(R.id.restoreicon);
		cloudybt=(Button) findViewById(R.id.cloudy);
		cylinderbt=(Button) findViewById(R.id.cylinder);
		flowerbt=(Button) findViewById(R.id.flower);
		heartbt=(Button) findViewById(R.id.heart);
		hexagonbt=(Button) findViewById(R.id.hexagon);
		leafbt=(Button) findViewById(R.id.leaf);
		mallowbt=(Button) findViewById(R.id.mallow);
		pebblebt=(Button) findViewById(R.id.pebble);
		roundedhexagonbt=(Button) findViewById(R.id.roundedhexagon);
		roundedrectbt=(Button) findViewById(R.id.roundedrect);
		squarebt=(Button) findViewById(R.id.square);
		squirclebt=(Button) findViewById(R.id.squircle);
		stretchedbt=(Button) findViewById(R.id.stretched);
		taperedrectbt=(Button) findViewById(R.id.taperedrect);
		teardropbt=(Button) findViewById(R.id.teardrop);
		// tv=(TextView) findViewById(R.id.xingnengTextView);
		//设置按钮监听
		restoreiconbt.setOnClickListener(this);
		cloudybt.setOnClickListener(this);
		cylinderbt.setOnClickListener(this);
		flowerbt.setOnClickListener(this);
		heartbt.setOnClickListener(this);
		hexagonbt.setOnClickListener(this);
		leafbt.setOnClickListener(this);
		mallowbt.setOnClickListener(this);
		pebblebt.setOnClickListener(this);
		roundedhexagonbt.setOnClickListener(this);
		roundedrectbt.setOnClickListener(this);
		squarebt.setOnClickListener(this);
		squirclebt.setOnClickListener(this);
		stretchedbt.setOnClickListener(this);
		taperedrectbt.setOnClickListener(this);
		teardropbt.setOnClickListener(this);

	}

	@Override
	public void onClick(View view){
		switch(view.getId()){
			case R.id.cloudy:
				//点击切换云
				execShell("cmd overlay disable com.android.theme.icon.cloudy");
				execShell("cmd overlay enable com.android.theme.icon.cloudy");
			break;
			case R.id.cylinder:
				//点击切换圆柱
				execShell("cmd overlay disable com.android.theme.icon.cylinder");
				execShell("cmd overlay enable com.android.theme.icon.cylinder");

			break;
			case R.id.flower:
				//点击切换花
				execShell("cmd overlay disable com.android.theme.icon.flower");
				execShell("cmd overlay enable com.android.theme.icon.flower");

			break;
			case R.id.heart:
				//点击切换心
				execShell("cmd overlay disable com.android.theme.icon.heart");
				execShell("cmd overlay enable com.android.theme.icon.heart");
				break;
			case R.id.hexagon:
				//点击切换六边形
				execShell("cmd overlay disable com.android.theme.icon.hexagon");
				execShell("cmd overlay enable com.android.theme.icon.hexagon");
				break;
			case R.id.leaf:
				//点击切换叶
				execShell("cmd overlay disable com.android.theme.icon.leaf");
				execShell("cmd overlay enable com.android.theme.icon.leaf");
				break;
			case R.id.mallow:
				//点击切换mallow
				execShell("cmd overlay disable com.android.theme.icon.mallow");
				execShell("cmd overlay enable com.android.theme.icon.mallow");
				break;
			case R.id.pebble:
				//点击切换pebble
				execShell("cmd overlay disable com.android.theme.icon.pebble");
				execShell("cmd overlay enable com.android.theme.icon.pebble");
				break;
			case R.id.roundedhexagon:
				//点击切换圆角六边形
				execShell("cmd overlay disable com.android.theme.icon.roundedhexagon");
				execShell("cmd overlay enable com.android.theme.icon.roundedhexagon");
				break;
			case R.id.roundedrect:
				//点击切换圆角方形
				execShell("cmd overlay disable com.android.theme.icon.roundedrect");
				execShell("cmd overlay enable com.android.theme.icon.roundedrect");
				break;
			case R.id.square:
				//点击切换正方形
				execShell("cmd overlay disable com.android.theme.icon.square");
				execShell("cmd overlay enable com.android.theme.icon.square");
				break;
			case R.id.squircle:
				//点击切换方圆形
				execShell("cmd overlay disable com.android.theme.icon.squircle");
				execShell("cmd overlay enable com.android.theme.icon.squircle");
				break;
			case R.id.stretched:
				//点击切换stretched
				execShell("cmd overlay disable com.android.theme.icon.stretched");
				execShell("cmd overlay enable com.android.theme.icon.stretched");
				break;
			case R.id.taperedrect:
				//点击切换taperedrect
				execShell("cmd overlay disable com.android.theme.icon.taperedrect");
				execShell("cmd overlay enable com.android.theme.icon.taperedrect");
				break;
			case R.id.teardrop:
				//点击切换泪珠
				execShell("cmd overlay disable com.android.theme.icon.teardrop");
				execShell("cmd overlay enable com.android.theme.icon.teardrop");
				break;
			case R.id.restoreicon:
				//点击还原
				execShell("cmd overlay disable com.android.theme.icon.cloudy");
				execShell("cmd overlay disable com.android.theme.icon.cylinder");
				execShell("cmd overlay disable com.android.theme.icon.flower");
				execShell("cmd overlay disable com.android.theme.icon.heart");
				execShell("cmd overlay disable com.android.theme.icon.leaf");
				execShell("cmd overlay disable com.android.theme.icon.mallow");
				execShell("cmd overlay disable com.android.theme.icon.pebble");
				execShell("cmd overlay disable com.android.theme.icon.roundedhexagon");
				execShell("cmd overlay disable com.android.theme.icon.roundedrect");
				execShell("cmd overlay disable com.android.theme.icon.square");
				execShell("cmd overlay disable com.android.theme.icon.squircle");
				execShell("cmd overlay disable com.android.theme.icon.stretched");
				execShell("cmd overlay disable com.android.theme.icon.taperedrect");
				execShell("cmd overlay disable com.android.theme.icon.teardrop");
				execShell("cmd overlay disable com.android.theme.icon.hexagon");
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
