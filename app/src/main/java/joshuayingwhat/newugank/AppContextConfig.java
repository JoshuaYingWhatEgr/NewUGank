package joshuayingwhat.newugank;

import android.content.Context;

public class AppContextConfig {
    private static Context context;

    public AppContextConfig() {
        throw new UnsupportedOperationException("can not create this object");
    }

    public static void intit(Context context) {
        AppContextConfig.context = context.getApplicationContext();
    }

    public static Context getContext() {
        if (context != null) {
            return context;
        } else {
            throw  new NullPointerException("请先初始化此类");
        }
    }
}
