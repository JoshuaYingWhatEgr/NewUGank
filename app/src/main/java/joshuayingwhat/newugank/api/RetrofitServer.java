package joshuayingwhat.newugank.api;

import io.reactivex.Observable;
import joshuayingwhat.newugank.entity.CategoryResult;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * api retrofit
 * @author joshuayingwhat
 */
public interface RetrofitServer {
    @GET("random/data/福利/{number}")
    Observable<CategoryResult> getRandomBeauties(@Path("number") int number);
}
