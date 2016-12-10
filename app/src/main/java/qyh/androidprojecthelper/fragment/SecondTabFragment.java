package qyh.androidprojecthelper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import qyh.androidprojecthelper.base.BaseMainFragment;

/**
 * 描述：
 * Created by qyh on 2016/12/10.
 */
public class SecondTabFragment extends BaseMainFragment {
    public static SecondTabFragment newInstance() {

        Bundle args = new Bundle();

        SecondTabFragment fragment = new SecondTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }
}
