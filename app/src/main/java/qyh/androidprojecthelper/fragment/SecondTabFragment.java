package qyh.androidprojecthelper.fragment;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import qyh.androidprojecthelper.R;
import qyh.androidprojecthelper.base.BaseFragment;

/**
 * 描述：
 * Created by qyh on 2016/12/10.
 */
public class SecondTabFragment extends BaseFragment {

    @BindView(R.id.tabs)
    public TabLayout tabs;
    @BindView(R.id.view_pager)
    public ViewPager viewPager;

    @Override
    protected int getLayoutResource() {
        return  R.layout.fragment_second;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {

    }
}
