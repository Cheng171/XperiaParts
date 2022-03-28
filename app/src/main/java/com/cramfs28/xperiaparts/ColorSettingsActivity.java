package  com.cramfs28.xperiaparts;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;


public class ColorSettingsActivity extends Activity implements OnClickListener
{


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        TextView m_TextView = (TextView) findViewById(R.id.TextView01);
        EditText m_EditText = (EditText) findViewById(R.id.EditText01);
        Button m_Button = (Button) findViewById(R.id.button);
        Button m_Button2 = (Button) findViewById(R.id.button2);


        m_TextView.setTextSize(20);
        //m_EditText.setHint("请输入");
        
        /* 设置监听 */
        m_Button.setOnClickListener(this);
        m_Button2.setOnClickListener(this);
        m_EditText.setOnKeyListener((arg0, arg1, arg2) -> {
            // TODO Auto-generated method stub
            // 得到文字，将其显示到TextView中_BY CHENG
            // m_TextView.setText("您要调整的屏幕鲜艳度为：" + m_EditText.getText().toString());
            return false;
        });
    }
    
	@SuppressLint("NonConstantResourceId")
    @Override
	public void onClick(View view){
		switch(view.getId()){
			case R.id.button:
		String str1;
        EditText EditText01 =(EditText)findViewById(R.id.EditText01);
        str1=EditText01.getText().toString();
        //合并为shell
        String str2="su -c service call SurfaceFlinger 1022 f ";
        str2=str2+str1;
     //执行自设值
execShell(str2);
			break;
			case R.id.button2:
			//还原
execShell("su -c service call SurfaceFlinger 1022 f 1.048");
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
	//使用warFor()可以等待命令执行完成以后才返回
		}  
		catch(Throwable t)  
        {  
			t.printStackTrace();  
		} 
    }
}
