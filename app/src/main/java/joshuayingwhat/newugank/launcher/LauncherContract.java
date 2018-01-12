package joshuayingwhat.newugank.launcher;

import joshuayingwhat.newugank.base.BasePresenter;
import joshuayingwhat.newugank.base.BaseView;

/**
 * Created by JoshuaYingWhat on 2017/12/5.
 */
public class LauncherContract {
    interface View extends BaseView {
        //进入首页
        void goHomeActivity();

        /**
         * 加载Launcher图片
         *
         * @param url 图片url
         */
        void loadImage(String url);
    }

    interface Presenter extends BasePresenter {

    }
}
