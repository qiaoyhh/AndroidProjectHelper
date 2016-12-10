package qyh.androidprojecthelper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import qyh.androidprojecthelper.base.BaseMainFragment;

/**
 * 描述：
 * Created by qyh on 2016/12/10.
 */
public class FirstTabFragment extends BaseMainFragment {
    public static FirstTabFragment newInstance() {

        Bundle args = new Bundle();

        FirstTabFragment fragment = new FirstTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }
}
