package qyh.androidprojecthelper.presenter;
import com.flyco.tablayout.widget.MsgView;

import java.util.List;

import qyh.androidprojecthelper.R;
import qyh.androidprojecthelper.baserx.RxSubscriber;
import qyh.androidprojecthelper.bean.FirstBean;
import qyh.androidprojecthelper.contract.FirstContract;

/**
 * 描述：
 * Created by qyh on 2016/12/28.
 */
public class FirstPresenter extends FirstContract.Presenter{

    @Override
    public void getFirstListDataRequest(int size, int page) {
        mRxManage.add(mModel.getListData(size,page).subscribe(new RxSubscriber<List<FirstBean>>(mContext,true) {

            @Override
            public void onStart() {
                super.onStart();
                mView.showLoading(mContext.getString(R.string.loading));
            }

            @Override
            protected void _onNext(List<FirstBean> firstBeen) {
                mView.showListData(firstBeen);
                mView.stopLoading();
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
}
