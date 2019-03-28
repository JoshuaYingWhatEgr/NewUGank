package joshuayingwhat.newugank.launcher;

import android.text.TextUtils;

import joshuayingwhat.newugank.ConfigManager;

/**
 * Created by JoshuaYingWhat on 2017/12/6.
 */
public class LauncherPresenter implements LauncherContract.Presenter {
    private LauncherContract.View launcherView;

    LauncherPresenter(LauncherContract.View view) {
        this.launcherView = view;
    }

    @Override
    public void subscribe() {
        // TODO: 2017/12/6 首先在设置中设置是否启动launcher页面,如果不启动就直接进入首页 这里先不进行设置

        //获取banner URL
        String bannerUrl = ConfigManager.INSTANCE.getBannerURL();
        if (!TextUtils.isEmpty(bannerUrl)) {
            //如果banner url 不为空显示图片
            launcherView.loadImage(bannerUrl);
        } else {
            //如果url 为空直接进入首页
            launcherView.goHomeActivity();
        }
    }

    @Override
    public void unsubscribe() {
        launcherView = null;
    }
}
