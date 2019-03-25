package joshuayingwhat.newugank.home;


import joshuayingwhat.newugank.api.RetrofitServer;
import joshuayingwhat.newugank.entity.CategoryResult;
import joshuayingwhat.newugank.network.RetrofitFactory;
import rx.Observable;

public class HomeModel implements HomeContract.Model{

    @Override
    public Observable<CategoryResult> getRandomBeauties(int number) {
        return RetrofitFactory.getInstance().create(RetrofitServer.class).getRandomBeauties(number);
    }
}
