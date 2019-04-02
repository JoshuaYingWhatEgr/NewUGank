package joshuayingwhat.newugank.network.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import joshuayingwhat.newugank.network.Configurator;

public class BaseResponseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public String isSuccess;

    public String msg;

    @SerializedName("error")
    public boolean error;

    public boolean isSuccess() {
        return Configurator.getSuccess().equals(isSuccess);
    }

}
