package joshuayingwhat.newugank;

import android.app.Application;

/**
 * Created by JoshuaYingWhat on 2017/12/6.
 */
public class App extends Application {

    private static App INSTANCE;

    public static App getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化配置管理enum
        ConfigManager.INSTANCE.initConfig(this);
        INSTANCE = this;
    }

}
