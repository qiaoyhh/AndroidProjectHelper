package qyh.androidprojecthelper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import qyh.androidprojecthelper.R;
import qyh.androidprojecthelper.base.BaseApplication;
import qyh.androidprojecthelper.base.BaseFragment;
import qyh.androidprojecthelper.base.BaseMainFragment;
import qyh.androidprojecthelper.bean.FirstBean;
import qyh.androidprojecthelper.contract.FirstContract;
import qyh.androidprojecthelper.model.FirstModel;
import qyh.androidprojecthelper.presenter.FirstPresenter;
import qyh.androidprojecthelper.utils.ToastUitl;

/**
 * 描述：
 * Created by qyh on 2016/12/10.
 */
public class FirstTabFragment extends BaseFragment<FirstPresenter,FirstModel> implements FirstContract.View {
    private static final int SIZE=20;
    private static final int STARTPAGE=1;

    public static FirstTabFragment newInstance() {
        Bundle args = new Bundle();
        FirstTabFragment fragment = new FirstTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_firtst;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        mPresenter.getFirstListDataRequest(SIZE,STARTPAGE);

    }

    @Override
    public void showListData(List<FirstBean> listData) {
        ToastUitl.show(listData.toString(),1500);
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {

    }
}
