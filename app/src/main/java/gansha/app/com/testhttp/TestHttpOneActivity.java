package gansha.app.com.testhttp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestHttpOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_http_one);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        testHttpURLConnectionGet();
                        testHttpURLConnetionPost();
                    }
                }).start();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    public  String  testHttpURLConnectionGet(){
        String result=null;
        HttpURLConnection connection=null;
        InputStreamReader inputStreamReader=null;
        try {
            URL url=new URL("http://api.gh2.cn/api/common.ashx?action=getProductCategory");
           connection=(HttpURLConnection) url.openConnection();
            inputStreamReader=new InputStreamReader(connection.getInputStream());
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer=new StringBuffer();
            String line=null;
            while ((line=bufferedReader.readLine())!=null){
                stringBuffer.append(line);
            }
            result=stringBuffer.toString();
            Log.i("result===get===",result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(connection!=null){
                connection.disconnect();
            }
            if(inputStreamReader!=null){
                try {
                    inputStreamReader.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    private String testHttpURLConnetionPost(){
        String restult=null;
        URL url=null;
        HttpURLConnection connection=null;
        InputStreamReader inputStreamReader=null;

        try {

            url=new URL("http://api.gh2.cn/api/common.ashx?action=getProductSpecialty");
            connection=(HttpURLConnection)url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Charset", "utf-8");

            DataOutputStream dataOutputStream=new DataOutputStream(connection.getOutputStream());
            dataOutputStream.writeBytes("productCategoryID=BE4EC28689A04C769BA86690F7047CA9");
            dataOutputStream.writeBytes("uid=93F10C956DB943DF9BD23AB07B6F9889");
            dataOutputStream.flush();
            dataOutputStream.close();

            inputStreamReader=new InputStreamReader(connection.getInputStream());
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer=new StringBuffer();
            String line=null;
            while ((line=bufferedReader.readLine())!=null){
                stringBuffer.append(line);
            }
            restult=stringBuffer.toString();
            Log.i("restult==post==",restult);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(connection!=null){
                connection.disconnect();
            }
            try
            {
                if(inputStreamReader!=null){
                    inputStreamReader.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        return restult;
    }


    private String testHttpClientGet(){
        String result=null;
        BufferedReader reader=null;
        try {




        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(reader!=null){
                try{
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        return  result;
    }

    private String testHttpClientPost(){
        String result=null;
        return  result;
    }

}

