package qyh.androidprojecthelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 描述：
 * Created by qyh on 2016/12/10.
 */
public class LuncherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luncher);
        System.out.println("=======================");
        Intent intent=new Intent(LuncherActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
