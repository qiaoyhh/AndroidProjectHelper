package qyh.androidprojecthelper.api;

import qyh.androidprojecthelper.bean.FirstBean;
import qyh.androidprojecthelper.bean.GirlData;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 描述：
 * Created by qyh on 2016/12/28.
 */
public interface ApiService {

    @GET("data/福利/{size}/{page}")
    Observable<GirlData> getListData(
            @Header("Cache-Control") String cacheControl,
            @Path("size") int size,
            @Path("page") int page);
}
