package joshuayingwhat.newugank.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * activity公共抽象类
 *
 * @author joshuayingwhat
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;
    private BasePresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        unbinder = ButterKnife.bind(this);
        settingActivity();
        setContentView(setLayoutView());
        mPresenter = createPresenter();
        initData(savedInstanceState);
    }

    /**
     * 设置布局
     *
     * @return 返回布局id
     */
    public abstract int setLayoutView();

    /**
     * 初始化参数
     *
     * @param savedInstanceState
     */
    public abstract void initData(Bundle savedInstanceState);

    /**
     * 创建presenter的对象
     * @return
     */
    public abstract BasePresenter createPresenter();

    /**
     * 配置activity
     */
    public abstract void settingActivity();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }

        if (mPresenter != null) {
            mPresenter = null;
        }
    }
}
