package joshuayingwhat.newugank.home;


import io.reactivex.Observable;
import joshuayingwhat.newugank.api.RetrofitServer;
import joshuayingwhat.newugank.entity.CategoryResult;
import joshuayingwhat.newugank.network.RetrofitFactory;

/**
 * @author joshuayingwhat
 */
public class HomeModel implements HomeContract.Model{

    @Override
    public Observable<CategoryResult> getRandomBeauties(int number) {
        return RetrofitFactory.getInstance().create(RetrofitServer.class).getRandomBeauties(number);
    }
}
