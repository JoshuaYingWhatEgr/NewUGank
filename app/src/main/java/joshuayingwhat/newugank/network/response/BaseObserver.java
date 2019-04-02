package joshuayingwhat.newugank.network.response;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import joshuayingwhat.newugank.AppContextConfig;
import joshuayingwhat.newugank.network.exception.ExceptionInfo;
import joshuayingwhat.newugank.utils.ToastUtils;
import retrofit2.HttpException;

public abstract class BaseObserver<T extends BaseResponseEntity> implements Observer<T> {

    private static final String RESPONSE_RETURN_ERROR = "服务器返回数据失败";

    private static final String HTTP_ERROR = "服务器异常";

    private static final String CONNECT_ERROR = "网络连接失败,请检查网络";

    private static final String CONNECT_TIME_ERROR = "网络连接超时,请稍后重试";

    private static final String JSON_ERROR = "数据解析失败,请检查返回参数";

    private static final String UNKONW_ERROR = "未知错误";

    /**
     * 网络请求之前的初始化工作
     *
     * @param d
     */
    @Override
    public void onSubscribe(Disposable d) {
        onStart();
    }

    /**
     * 数据处理
     *
     * @param response 返回值
     */
    @Override
    public void onNext(T response) {
        onFinish();

        if (response.error == false) {
            onSuccess(response);
        } else if(response.error == true){
            onFailed(response);
        }

    }

    /**
     * 请求完成
     */
    @Override
    public void onComplete() {
        onFinish();
    }

    /**
     * 网络请求失败
     * 错误请求
     *
     * @param e 错误信息
     */
    @Override
    public void onError(Throwable e) {
        onFinish();
        Log.e("错误异常", "-------------------------------------------------------------");
        Log.i("网络错误", "onError: " + e.toString());
        Log.e("错误异常", "-------------------------------------------------------------");
        if (e instanceof HttpException) {
            //http错误
            dealError(ExceptionInfo.HTTP_ERROR);
        } else if (e instanceof ConnectException) {
            //网络连接错误
            dealError(ExceptionInfo.CONNECTION_ERROR);
        } else if (e instanceof InterruptedIOException) {
            //网络连接超时错误
            dealError(ExceptionInfo.CONNECTION_TIME_ERROR);
        } else if (e instanceof JSONException || e instanceof JsonParseException || e instanceof ParseException) {
            //json解析错误
            dealError(ExceptionInfo.JSON_ERROR);
        } else {
            //未知错误
            dealError(ExceptionInfo.OTHER_ERROR);
        }
    }

    /**
     * 网络请求失败的错误处理
     *
     * @param exceptionInfo 错误异常
     */
    private void dealError(ExceptionInfo exceptionInfo) {
        switch (exceptionInfo) {
            case HTTP_ERROR:
                ToastUtils.showToastMsg(AppContextConfig.getContext(), HTTP_ERROR, Toast.LENGTH_SHORT);
                break;
            case CONNECTION_ERROR:
                ToastUtils.showToastMsg(AppContextConfig.getContext(), CONNECT_ERROR, Toast.LENGTH_SHORT);
                break;
            case CONNECTION_TIME_ERROR:
                ToastUtils.showToastMsg(AppContextConfig.getContext(), CONNECT_TIME_ERROR, Toast.LENGTH_SHORT);
                break;
            case JSON_ERROR:
                ToastUtils.showToastMsg(AppContextConfig.getContext(), JSON_ERROR, Toast.LENGTH_SHORT);
                break;
            case OTHER_ERROR:
                ToastUtils.showToastMsg(AppContextConfig.getContext(), UNKONW_ERROR, Toast.LENGTH_SHORT);
                break;
            default:
        }
    }

    private void onFailed(T response) {
        if (!TextUtils.isEmpty(response.msg)) {
            ToastUtils.showToastMsg(AppContextConfig.getContext(), response.msg, Toast.LENGTH_SHORT);
        } else {
            ToastUtils.showToastMsg(AppContextConfig.getContext(), RESPONSE_RETURN_ERROR, Toast.LENGTH_SHORT);
        }
    }

    protected abstract void onStart();

    protected abstract void onFinish();

    protected abstract void onSuccess(T response);

}
