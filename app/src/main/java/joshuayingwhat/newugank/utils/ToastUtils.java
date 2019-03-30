package joshuayingwhat.newugank.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

/**
 * toast工具类
 */
public class ToastUtils {

    private static Toast mToast;

    /**
     * toast 工具类
     * @param context 上下文对象
     * @param msg     显示内容
     * @param duration 显示时长
     */
    @SuppressLint("ShowToast")
    public static void showToastMsg(Context context, String msg, int duration) {

        if (mToast == null) {
            mToast = Toast.makeText(context, msg, duration);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

}
