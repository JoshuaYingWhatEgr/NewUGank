package joshuayingwhat.newugank.home;

import android.support.v7.graphics.Palette;

import io.reactivex.Observable;
import joshuayingwhat.newugank.base.BaseModel;
import joshuayingwhat.newugank.base.BasePresenter;
import joshuayingwhat.newugank.base.BaseView;
import joshuayingwhat.newugank.entity.CategoryResult;

/**
 * homeContract契约类
 * Created by JoshuaYingWhat on 2017/12/6.
 */
public interface HomeContract {
    interface View extends BaseView {
        void cacheImg(String imgUrl);

        //开始动画
        void startBannerLoadingAnim();

        //结束动画
        void stopBannerLoadingAnim();

        //设置floating可以点击
        void setEnableClick();

        //设置floating不可以点击
        void setUnEnableClick();

        void setBannerImage(String url);

        void showBannerFail();

        void setAppBarBackColor(int colorParimay);

        void setFabButtonColor(int colorParimay);
    }

    interface Presenter extends BasePresenter {
        void saveCacheImgUrl(String url);

        //获取随机图片
        void getRandomBanner();

        void setThemeColor(Palette palette);
    }

    interface Model extends BaseModel {
        Observable<CategoryResult> getRandomBeauties(int number);
    }
}
