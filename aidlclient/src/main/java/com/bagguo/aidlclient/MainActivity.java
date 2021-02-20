package com.bagguo.aidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bagguo.aidldemo.IMyAidlInterface;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private IMyAidlInterface iMyAidlInterface;
    //绑定服务回调
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //服务绑定成功后调用，获取服务端的接口，这里的service就是服务端onBind返回的iBinder，即已实现的接口
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //解除绑定时调用，清空接口，防止内存溢出
            iMyAidlInterface = null;

        }
    };
    private EditText et_num1;
    private EditText et_num2;
    private TextView tv_total;
    private Button btn_add;
    private int nNum1;
    private int nNum2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        bindService();
        bindService2();
        initView();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_add) {
            handleBtnClickEvent();
        }
    }


    private void initView() {
        et_num1 = findViewById(R.id.et_num1);
        et_num2 = findViewById(R.id.et_num2);
        tv_total = findViewById(R.id.tv_total);
        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
    }


    private void handleBtnClickEvent() {
        nNum1 = Integer.parseInt(et_num1.getText().toString());
        nNum2 = Integer.parseInt(et_num2.getText().toString());

        int total = 0;
        try {
            total = iMyAidlInterface.add(nNum1, nNum2);
            Log.d(TAG, "onCreate: ======total:" + total);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        tv_total.setText(total + "");
    }

    private void bindService() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(
                "com.bagguo.aidldemo",
                "com.bagguo.aidldemo.IRemoteService"));
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    //------------- demo 2-------------
    private void bindService2() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(
                "com.bagguo.aidlclient",
                "com.bagguo.aidlclient.MyService"));
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }
}
