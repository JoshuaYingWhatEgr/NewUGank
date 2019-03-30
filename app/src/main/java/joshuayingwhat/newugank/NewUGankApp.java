package joshuayingwhat.newugank;

import android.app.Application;

import joshuayingwhat.newugank.api.ApiList;
import joshuayingwhat.newugank.network.Configurator;

/**
 * @author JoshuaYingWhat
 * @date 2017/12/6
 */
public class NewUGankApp extends Application {

    private static NewUGankApp INSTANCE;

    private static final int CONNECT_OUT_TIME = 2000;

    private static final int READ_OUT_TIME = 2000;

    private static final String IS_SUCCESS = "y";

    public static NewUGankApp getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化配置管理enum
        ConfigManager.INSTANCE.initConfig(this);
        INSTANCE = this;

        //初始化网络配置
        Configurator builder = new Configurator.Builder()
                .setSuccess(IS_SUCCESS)
                .setBaseUrl(ApiList.BASE_URL).setConnectOutTime(CONNECT_OUT_TIME).setReadOutTime(READ_OUT_TIME)
                .builder();
        builder.initContext(this);
    }

}
