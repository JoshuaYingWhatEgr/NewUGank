package joshuayingwhat.newugank.home;

import joshuayingwhat.newugank.base.BasePresenter;
import joshuayingwhat.newugank.base.BaseView;

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
    }

    interface Presenter extends BasePresenter {
        void saveCacheImgUrl(String url);

        //获取随机图片
        void getRandomBanner();
    }
}
