package qyh.androidprojecthelper;

import android.os.Bundle;

import me.yokeyword.fragmentation.SupportActivity;
import qyh.androidprojecthelper.fragment.MainFragment;

/**
 * 描述：
 * Created by qyh on 2016/12/6.
 */
public class MainActivity extends SupportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_container, MainFragment.newInstance());
        }
    }
}
