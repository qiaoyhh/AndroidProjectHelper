package qyh.androidprojecthelper.model;

import java.util.List;
import qyh.androidprojecthelper.api.Api;
import qyh.androidprojecthelper.baserx.RxSchedulers;
import qyh.androidprojecthelper.bean.FirstBean;
import qyh.androidprojecthelper.bean.GirlData;
import qyh.androidprojecthelper.contract.FirstContract;
import rx.Observable;
import rx.functions.Func1;

/**
 * 描述：
 * Created by qyh on 2016/12/28.
 */
public class FirstModel implements FirstContract.Model {
    @Override
    public Observable<List<FirstBean>> getListData(int size, int page) {

        return Api.getInstance().service.getListData(size,page)
                .map(new Func1<GirlData, List<FirstBean>>() {
            @Override
            public List<FirstBean> call(GirlData girlData) {
                return girlData.getResults();
            }
      }).compose(RxSchedulers.<List<FirstBean>>io_main());
    }
}
