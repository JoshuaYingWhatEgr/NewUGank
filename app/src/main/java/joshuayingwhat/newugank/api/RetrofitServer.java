package joshuayingwhat.newugank.api;

import joshuayingwhat.newugank.entity.CategoryResult;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * api retrofit
 */
public interface RetrofitServer {
    @GET("random/data/福利/{number}")
    Observable<CategoryResult> getRandomBeauties(@Path("number") int number);
}
