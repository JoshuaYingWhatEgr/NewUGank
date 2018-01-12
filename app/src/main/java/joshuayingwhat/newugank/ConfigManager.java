package joshuayingwhat.newugank;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by JoshuaYingWhat on 2017/12/6.
 */
public enum ConfigManager {
    INSTANCE;

    private final String spName = "app_config";
    private final String key_banner_url = "keyBannrUrl";
    private String bannerURL;

    public void initConfig(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        //BannerUrl 用于加载页显示 获取保存的banner图片的url
        bannerURL = sharedPreferences.getString(key_banner_url, "");
    }

    //获取banner图片
    public String getBannerURL() {
        return bannerURL;
    }

    //设置banner图片,预加载图片等 第一次启动时没有显示页图片的,第二次启动设置了之后才有
    public void setBannerURL(String bannerURL) {
        //将url保存到内部存储中,一便下次显示启动页图片
        SharedPreferences sharedPreferences = App.getInstance().getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key_banner_url, bannerURL);
        if (editor.commit()) {
            this.bannerURL = bannerURL;
        }
    }
}
