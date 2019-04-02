package joshuayingwhat.newugank.network.api;

import io.reactivex.Observable;
import joshuayingwhat.newugank.entity.CategoryResult;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by JoshuaYingWhat on 2017/12/6.
 */
public interface GankApi {

    @GET("data/{category}/{number}/{page}")
    Observable<CategoryResult> getCategoryDate(@Path("category") String category,
                                               @Path("number") int number, @Path("page") int page);

    @GET("random/data/福利/{number}")
    Observable<CategoryResult> getRandomBeauties(@Path("number") int number);

}
