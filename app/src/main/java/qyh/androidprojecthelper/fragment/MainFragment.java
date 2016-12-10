package qyh.androidprojecthelper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.yokeyword.fragmentation.SupportFragment;
import qyh.androidprojecthelper.R;
import qyh.androidprojecthelper.view.bottombar.BottomBar;
import qyh.androidprojecthelper.view.bottombar.BottomBarTab;

/**
 * 描述：
 * Created by qyh on 2016/12/10.
 */
public class MainFragment extends SupportFragment  {
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOUR = 3;

    private BottomBar mBottomBar;
    private SupportFragment[] mFragments = new SupportFragment[4];

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        if (savedInstanceState == null) {
            mFragments[FIRST] = FirstTabFragment.newInstance();
            mFragments[SECOND] = SecondTabFragment.newInstance();
            mFragments[THIRD] = ThirdTabFragment.newInstance();
            mFragments[FOUR]=FourTabFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_tab_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用,也可以通过getChildFragmentManager.getFragments()自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments[FIRST] = findChildFragment(FirstTabFragment.class);
            mFragments[SECOND] = findChildFragment(SecondTabFragment.class);
            mFragments[THIRD] = findChildFragment(ThirdTabFragment.class);
            mFragments[FOUR] = findChildFragment(FourTabFragment.class);
        }
        initView(view);
        return view;
    }

    private void initView(View view) {
        mBottomBar = (BottomBar) view.findViewById(R.id.bottomBar);

        mBottomBar
                .addItem(new BottomBarTab(_mActivity, R.mipmap.ic_message_white, "消息"))
                .addItem(new BottomBarTab(_mActivity, R.mipmap.ic_account_circle_white, "联系人"))
                .addItem(new BottomBarTab(_mActivity, R.mipmap.ic_discover_white, "发现"))
                .addItem(new BottomBarTab(_mActivity, R.mipmap.ic_discover_white, "我"));
        // 模拟未读消息
        mBottomBar.getItem(FIRST).setUnreadCount(9);

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);

                BottomBarTab tab = mBottomBar.getItem(FIRST);
                if (position == FIRST) {
                    tab.setUnreadCount(0);
                } else {
                    tab.setUnreadCount(tab.getUnreadCount() + 1);
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                // 这里推荐使用EventBus来实现 -> 解耦
                // 在FirstPagerFragment,FirstHomeFragment中接收, 因为是嵌套的Fragment
                // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
              //  EventBus.getDefault().post(new TabSelectedEvent(position));
            }
        });
    }
}
