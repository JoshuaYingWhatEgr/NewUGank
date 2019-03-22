package joshuayingwhat.newugank.network.api;

import joshuayingwhat.newugank.entity.CategoryResult;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by JoshuaYingWhat on 2017/12/6.
 */
public interface GankApi {

    @GET("random/data/福利/{number}")
    Observable<CategoryResult> getRandomBeauties(@Path("number") int number);
}
