package joshuayingwhat.newugank.home;

import android.support.v7.graphics.Palette;
import android.util.Log;

import joshuayingwhat.newugank.App;
import joshuayingwhat.newugank.ConfigManager;
import joshuayingwhat.newugank.R;
import joshuayingwhat.newugank.ThemeManager;
import joshuayingwhat.newugank.entity.CategoryResult;
import joshuayingwhat.newugank.network.NetWork;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 *
 * @author JoshuaYingWhat
 * @date 2017/12/6
 */
public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View mHomeView;

    private CompositeSubscription mSubscription;

    public HomePresenter(HomeContract.View view) {
        this.mHomeView = view;
        mSubscription = new CompositeSubscription();
    }

    @Override
    public void saveCacheImgUrl(String url) {
        ConfigManager.INSTANCE.setBannerURL(url);
    }

    //获取随机图片
    @Override
    public void getRandomBanner() {
        getBanner(true);
    }

    /**
     * 设置状态栏颜色
     *
     * @param palette
     */
    @Override
    public void setThemeColor(Palette palette) {
        if (palette != null) {
            int color = App.getInstance().getResources().getColor(R.color.colorPrimary);
            ThemeManager.INSTANCE.setColorParimay(palette.getDarkVibrantColor(color));
            mHomeView.setAppBarBackColor(ThemeManager.INSTANCE.getColorParimay());
            //设置fbbutton的背景色
            mHomeView.setFabButtonColor(ThemeManager.INSTANCE.getColorParimay());
            mHomeView.setEnableClick();
            mHomeView.stopBannerLoadingAnim();
        }
    }

    /**
     * 获取图片是否随机  true 不随机  false 随机
     *
     * @param isRandom
     */
    private void getBanner(boolean isRandom) {
        //开始设置FloatingActionButton动画
        mHomeView.startBannerLoadingAnim();
        //设置不可点击
        mHomeView.setUnEnableClick();
        Observable<CategoryResult> observable = null;
        if (isRandom) {
            //获取随机图片
            observable = NetWork.getGankApi().getRandomBeauties(1);
        } else {

        }
        Subscription subscribe = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CategoryResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mHomeView.showBannerFail();
                mHomeView.setEnableClick();//恢复点击状态
                mHomeView.stopBannerLoadingAnim();
            }

            @Override
            public void onNext(CategoryResult categoryResult) {
                if (categoryResult != null && categoryResult.results != null && categoryResult.results.size() > 0
                        && categoryResult.results.get(0).url != null) {
                    Log.e("Tag", "url=" + categoryResult.results.get(0).url);
                    //设置banner图片
                    mHomeView.setBannerImage(categoryResult.results.get(0).url);
                } else {
                    mHomeView.showBannerFail();
                }
                //获取到图片后停止动画
                mHomeView.stopBannerLoadingAnim();
                //恢复点击状态
                mHomeView.setEnableClick();
            }
        });
    }


    @Override
    public void subscribe() {
        cacheRandomImg();
    }

    @Override
    public void unsubscribe() {
        mSubscription.clear();
    }

    public void cacheRandomImg() {
        //网络获取随机妹子
        Observable<CategoryResult> observable;
        observable = NetWork.getGankApi().getRandomBeauties(1);
        Subscription subscription = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CategoryResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CategoryResult categoryResult) {
                        if (categoryResult != null && categoryResult.results != null
                                && categoryResult.results.size() > 0 && categoryResult.results.get(0).url != null) {
                            mHomeView.cacheImg(categoryResult.results.get(0).url);
                        }
                    }
                });
        mSubscription.add(subscription);
    }

}
