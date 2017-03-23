package qyh.androidprojecthelper.fragment;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;

import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import qyh.androidprojecthelper.R;
import qyh.androidprojecthelper.adapter.FirstTabAdapter;
import qyh.androidprojecthelper.base.BaseFragment;
import qyh.androidprojecthelper.base.baseadapter.BaseQuickAdapter;
import qyh.androidprojecthelper.base.baseadapter.OnItemClickListener;
import qyh.androidprojecthelper.bean.FirstBean;
import qyh.androidprojecthelper.contract.FirstContract;
import qyh.androidprojecthelper.model.FirstModel;
import qyh.androidprojecthelper.presenter.FirstPresenter;
import qyh.androidprojecthelper.view.LoadingDialog;
import qyh.androidprojecthelper.view.refresh.NormalRefreshViewHolder;
import qyh.androidprojecthelper.view.refresh.RefreshLayout;


/**
 * 描述：
 * Created by qyh on 2016/12/10.
 */
public class FirstTabFragment extends BaseFragment<FirstPresenter,FirstModel> implements
        FirstContract.View, BaseQuickAdapter.RequestLoadMoreListener, RefreshLayout.RefreshLayoutDelegate {

    private  int SIZE=20;
    private static final int STARTPAGE=1;
    @BindView(R.id.rv_content)
    public RecyclerView rv_content;
    @BindView(R.id.refresh)
    public RefreshLayout refreshLayout;
    @BindView(R.id.toolbar_title)
    public TextView toolbar_title;

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
        toolbar_title.setText("妹纸");
        mFirstTabAdapter = new FirstTabAdapter(R.layout.item_first,null);
        rv_content.setLayoutManager(new GridLayoutManager(mContext,2));
        rv_content.setHasFixedSize(true);
        //设置适配器加载动画
        mFirstTabAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        rv_content.setAdapter(mFirstTabAdapter);
        //设置适配器可以上拉加载
        mFirstTabAdapter.setOnLoadMoreListener(this);
        //设置下拉、上拉
        refreshLayout.setDelegate(this);
        refreshLayout.setRefreshViewHolder(new NormalRefreshViewHolder(mContext,true));

        mPresenter.getFirstListDataRequest(SIZE,STARTPAGE);

        //条目的点击事件
        rv_content.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
                System.out.println("position===="+position);
            }
        });

    }

    @Override
    public void showListData(List<FirstBean> listData) {
        mFirstTabAdapter.setNewData(listData);
        refreshLayout.endRefreshing();
        refreshLayout.endLoadingMore();
    }
    //如果使用内嵌的加载提示，需要在这里处理，，
    // 我使用的LoadingDialog的方法，加载成功自动消失，所以不用处理进度消失等事件
    @Override
    public void showLoading(String title) {
        LoadingDialog.showDialogForLoading(getActivity());
    }

    @Override
    public void stopLoading() {
        LoadingDialog.cancelDialogForLoading();
    }
    //加载失败提示，根据需要自己处理
    @Override
    public void showErrorTip(String msg) {
    }

    @Override
    public void onLoadMoreRequested() {
        //   BaseQuickAdapter的上拉加载更多方法，和onRefreshLayoutBeginLoadingMore使用其中一个就可以
    }

    @Override
    public void onRefreshLayoutBeginRefreshing(RefreshLayout refreshLayout) {
        System.out.println("onRefreshLayoutBeginRefreshing===");
        mPresenter.getFirstListDataRequest(SIZE,STARTPAGE);
    }

    @Override
    public boolean onRefreshLayoutBeginLoadingMore(RefreshLayout refreshLayout) {
        SIZE+=20;
        mPresenter.getFirstListDataRequest(SIZE,STARTPAGE);
        return true;
    }
}
