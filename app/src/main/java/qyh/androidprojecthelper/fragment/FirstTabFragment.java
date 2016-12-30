package qyh.androidprojecthelper.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.List;

import butterknife.BindView;
import qyh.androidprojecthelper.R;
import qyh.androidprojecthelper.adapter.FirstTabAdapter;
import qyh.androidprojecthelper.base.BaseFragment;
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
    @BindView(R.id.rv_content)
    public RecyclerView rv_content;
    private Context mContext;
    private FirstTabAdapter mFirstTabAdapter;

    @Override
    protected int getLayoutResource() {
        mContext = getActivity();
        return R.layout.fragment_firtst;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    protected void initView() {
        System.out.println("initView====");
        mFirstTabAdapter = new FirstTabAdapter(R.layout.item_first,null);
        rv_content.setLayoutManager(new GridLayoutManager(mContext,2));
        rv_content.setHasFixedSize(true);
        rv_content.setAdapter(mFirstTabAdapter);

        mPresenter.getFirstListDataRequest(SIZE,STARTPAGE);
    }

    @Override
    public void showListData(List<FirstBean> listData) {
      // ToastUitl.show(listData.toString(),1500);
        System.out.println("showListData===");
        mFirstTabAdapter.setNewData(listData);
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
