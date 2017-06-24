package com.example.linghao.myfirstwork;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SmsActivity extends AppCompatActivity {
    private EditText ed1;

    private Button bt1,bt2;
    String  number;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        bt1 = (Button) findViewById(R.id.button1);
        ed1=(EditText) findViewById(R.id.ed1);
        bt2 = (Button) findViewById(R.id.button2);


        Log.i("jinlai","11");
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(SmsActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(SmsActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
                }else{
                    call();
                }
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("sdf","sdf");
                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                number = ed1.getText().toString();
                Log.i("num",number);
                Uri tel = Uri.parse("tel:" +number);
                intent1.setData(tel);
                startActivity(intent1);
            }
        });
    }
    private  void call(){
//        try{
//        SmsManager smsManager = SmsManager.getDefault();
//        smsManager.sendTextMessage(number, null, message, null, null);
//        Log.i("hao","2");
//        Toast.makeText(SmsActivity.this, "短信发送完成", Toast.LENGTH_LONG).show();}
//        catch (SecurityException e){
//            e.printStackTrace();
//        }
        try{
        Intent intent=new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:10086"));
        startActivity(intent);}
        catch (SecurityException e)
        {e.printStackTrace();}

    }
    public void onRequestPermissionResult(int requestCode,String[] permissions,int[] grantResults){
        switch (requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                   call();

                }
                else{
                    Toast.makeText(this,"you denied the permission",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}
