package joshuayingwhat.newugank.network;

import android.content.Context;

import java.io.Serializable;

import joshuayingwhat.newugank.AppContextConfig;

/**
 * 网络配置文件
 */
public class Configurator implements Serializable {

    /**
     * 网络请求读取时间
     */
    private static int readOutTime;

    /**
     * 网络连接时间
     */
    private static int connectOutTime;

    /**
     * 网络请求url
     */
    private static String base_url;

    /**
     * 网络请求成功码
     */
    private static String isSuccess;

    public static String getSuccess() {
        return isSuccess;
    }

    public static int getReadOutTime() {
        return readOutTime;
    }

    public static int getConnectOutTime() {
        return connectOutTime;
    }

    public static String getBase_url() {
        return base_url;
    }

    public Configurator(Builder builder) {
        readOutTime = builder.readOutTime;
        connectOutTime = builder.connectOutTime;
        base_url = builder.base_url;
    }

    public void initContext(Context context) {
        AppContextConfig.intit(context);
    }

    public static final class Builder {
        private int readOutTime;

        private int connectOutTime;

        private String base_url;

        private String isSuccess;

        public Builder setReadOutTime(int readOutTime) {
            this.readOutTime = readOutTime;
            return this;
        }

        public Builder setConnectOutTime(int connectOutTime) {
            this.connectOutTime = connectOutTime;
            return this;
        }

        public Builder setBaseUrl(String base_url) {
            this.base_url = base_url;
            return this;
        }

        public Configurator builder() {
            return new Configurator(this);
        }
    }

}
