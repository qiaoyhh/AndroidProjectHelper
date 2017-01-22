package qyh.androidprojecthelper;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


/**
 * 描述：APP启动页
 * Created by qyh on 2016/12/10.
 */
public class LuncherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luncher);

        Button bu_intoapp = (Button) findViewById(R.id.bu_intoapp);
        bu_intoapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               toMainActivity();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toMainActivity();
            }
        }, 4000);
    }
    private void toMainActivity(){
        Intent intent=new Intent(LuncherActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
