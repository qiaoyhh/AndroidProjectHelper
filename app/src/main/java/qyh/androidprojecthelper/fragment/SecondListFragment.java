package qyh.androidprojecthelper.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.uuzuche.lib_zxing.view.ViewfinderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import qyh.androidprojecthelper.R;
import qyh.androidprojecthelper.base.BaseFragment;
import qyh.androidprojecthelper.base.BaseFragmentAdapter;
import qyh.androidprojecthelper.bean.SecondChannelTabBean;
import qyh.androidprojecthelper.db.SecondChannelManager;


/**
 * 描述：
 * Created by qyh on 2017/1/12.
 */
public class SecondListFragment extends BaseFragment {
    @BindView(R.id.tabs)
    public TabLayout tabs;
    @BindView(R.id.viewpager)
    public ViewPager viewPager;
    private BaseFragmentAdapter baseFragmentAdapter;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_second_list;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
            if(null!=getArguments()){
                String id = getArguments().getString("id");
                System.out.println("id=="+id);
            }
        ArrayList<String> tabName=new ArrayList<>();
        ArrayList<Fragment> fragments=new ArrayList<>();
        List<SecondChannelTabBean> tabDatas = SecondChannelManager.loadTab();
        for(int i=0;i<tabDatas.size();i++){
            tabName.add(tabDatas.get(i).getName());
            fragments.add(createListFragments(tabDatas.get(i)));
        }

        baseFragmentAdapter = new BaseFragmentAdapter(getChildFragmentManager(), fragments, tabName);
        viewPager.setAdapter(baseFragmentAdapter);
        tabs.setupWithViewPager(viewPager);
    }

    private TestFragment createListFragments(SecondChannelTabBean data) {
        TestFragment fragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", data.getId());
        fragment.setArguments(bundle);
        return fragment;
    }
}
