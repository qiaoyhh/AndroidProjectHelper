package qyh.androidprojecthelper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import qyh.androidprojecthelper.base.BaseMainFragment;

/**
 * 描述：
 * Created by qyh on 2016/12/10.
 */
public class FourTabFragment extends BaseMainFragment {
    public static FourTabFragment newInstance() {

        Bundle args = new Bundle();

        FourTabFragment fragment = new FourTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }
}
