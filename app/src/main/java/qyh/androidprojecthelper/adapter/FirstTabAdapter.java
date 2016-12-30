package qyh.androidprojecthelper.adapter;



import android.widget.ImageView;

import java.util.List;

import qyh.androidprojecthelper.R;
import qyh.androidprojecthelper.base.baseadapter.BaseQuickAdapter;
import qyh.androidprojecthelper.base.baseadapter.BaseViewHolder;
import qyh.androidprojecthelper.bean.FirstBean;
import qyh.androidprojecthelper.utils.ImageLoaderUtils;

/**
 * 描述：
 * Created by qyh on 2016/12/30.
 */
public class FirstTabAdapter extends BaseQuickAdapter {
    
    public FirstTabAdapter(int layoutResId, List<FirstBean> listData) {
        super(layoutResId, listData);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item, int position) {
        FirstBean data=(FirstBean)item;
        ImageLoaderUtils.display(mContext,(ImageView) helper.getView(R.id.iv_item_picture)
                ,data.getUrl());
    }
}
