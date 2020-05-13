package com.example.notificationtryandpractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity {
    TextView text;
    public static final String SEND_BROADCAST = "broadcast";
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.textToken);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                text.setText(SharedPrefManagar.getInstance(MainActivity.this).getToken());
            }
        };
        registerReceiver(broadcastReceiver,new IntentFilter(SEND_BROADCAST));
        getDeviceToken();
    }

    private void getDeviceToken() {
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (!task.isSuccessful()) {
                    Log.w("faild", "getInstanceId failed", task.getException());
                    return;
                }
                // Get new Instance ID token
                String token = task.getResult().getToken();
                Log.d("token", token);
                Toast.makeText(MainActivity.this, token, Toast.LENGTH_LONG).show();
                getApplicationContext().sendBroadcast(new Intent(SEND_BROADCAST));
                storeToken(token);//my own method for save token
            }
        });
    }

    private void storeToken(String token) {
        SharedPrefManagar.getInstance(getApplicationContext()).storeToken(token);
    }

}
