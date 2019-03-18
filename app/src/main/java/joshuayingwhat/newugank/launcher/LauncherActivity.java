package joshuayingwhat.newugank.launcher;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.Window;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import joshuayingwhat.newugank.R;
import joshuayingwhat.newugank.base.BaseActivity;
import joshuayingwhat.newugank.base.BasePresenter;
import joshuayingwhat.newugank.home.HomeActivity;

/**
 * 启动页
 *
 * @author JoshuaYingWhat
 * @date 2017/12/4
 */
public class LauncherActivity extends BaseActivity implements LauncherContract.View {

    public boolean isResume;

    public LauncherContract.Presenter mLauncherPresenter;

    @BindView(R.id.img_launcher_welcome)
    AppCompatImageView mImageView;

    @Override
    public int setLayoutView() {
        return R.layout.activity_launcher;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        //初始化LauncherPresenter
        mLauncherPresenter.subscribe();
    }

    @Override
    public BasePresenter createPresenter() {
        if (mLauncherPresenter == null) {
            mLauncherPresenter = new LauncherPresenter(this);
        }
        return mLauncherPresenter;
    }

    @Override
    public void settingActivity() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isResume = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isResume = false;
    }

    /**
     * 进入首页
     */
    @Override
    public void goHomeActivity() {
        Intent intent = new Intent(LauncherActivity.this, HomeActivity.class);
        startActivity(intent);
        //Activity 切换淡入淡出动画
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        //结束当前界面
        finish();
    }

    /**
     * 加载图片
     *
     * @param url 图片url
     */
    @Override
    public void loadImage(String url) {
        /**
         * 通过picasso第三方图片加载器加载图片
         */
        Picasso.with(this).load(url).into(mImageView, new Callback() {
            @Override
            public void onSuccess() {
                //图片加载成功
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //前台显示launcher图片
                        if (!isResume) {
                            finish();
                            return;
                        }
                        goHomeActivity();
                    }
                }, 1200);
            }

            @Override
            public void onError() {
                //图片加载失败直接进入主界面
                goHomeActivity();
            }
        });
    }

    @Override
    protected void onDestroy() {
        //接触注册
        mLauncherPresenter.unsubscribe();
        super.onDestroy();
    }
}
