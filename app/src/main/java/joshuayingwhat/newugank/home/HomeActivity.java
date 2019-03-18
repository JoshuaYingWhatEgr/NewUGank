package joshuayingwhat.newugank.home;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.florent37.picassopalette.PicassoPalette;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import joshuayingwhat.newugank.R;
import joshuayingwhat.newugank.base.BaseActivity;
import joshuayingwhat.newugank.base.BasePresenter;
import joshuayingwhat.newugank.utils.MDTintUtil;

/**
 * Created by JoshuaYingWhat on 2017/12/5.
 */
public class HomeActivity extends BaseActivity implements HomeContract.View {


    @BindView(R.id.iv_home_setting)
    AppCompatImageView ivHomeSetting;
    //    @BindView(R.id.tab_home_category)
//    DachshundTabLayout tabHomeCategory;
    @BindView(R.id.tl_home_toolbar)
    Toolbar tlHomeToolbar;
    @BindView(R.id.ll_home_search)
    LinearLayout llHomeSearch;
    @BindView(R.id.iv_home_collection)
    AppCompatImageView ivHomeCollection;
    @BindView(R.id.iv_home_banner)
    ImageView ivHomeBanner;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.vp_home_category)
    ViewPager vpHomeCategory;
    @BindView(R.id.fab_home_random)
    FloatingActionButton fabHomeRandom;

    public HomeContract.Presenter mHomePresenter;
    private Unbinder bind;
    private ObjectAnimator mAnimator;

    @Override
    public int setLayoutView() {
        return R.layout.activity_home;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mHomePresenter.subscribe();
        mHomePresenter.getRandomBanner();
    }

    @Override
    public BasePresenter createPresenter() {
        if (mHomePresenter == null) {
            mHomePresenter = new HomePresenter(this);
        }
        return mHomePresenter;
    }

    @Override
    public void settingActivity() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    //点击FloatingActionButton从网络获取图片
    @OnClick(R.id.fab_home_random)
    public void random(View view) {
        //从presenter层中获取随机图片
        mHomePresenter.getRandomBanner();
    }

    //缓存图片
    @Override
    public void cacheImg(final String imgUrl) {
        //预加载 提前缓存好的欢迎图片 异步方式加载图片
        Picasso.with(this).load(imgUrl).fetch(new Callback() {
            @Override
            public void onSuccess() {
                //加载成功
                mHomePresenter.saveCacheImgUrl(imgUrl);
            }

            @Override
            public void onError() {

            }
        });
    }

    //开始floating动画
    @Override
    public void startBannerLoadingAnim() {
        fabHomeRandom.setImageResource(R.drawable.ic_loading);
        mAnimator = ObjectAnimator.ofFloat(fabHomeRandom, "rotation", 0, 360);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);//无线循环
        mAnimator.setDuration(800);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.start();
    }

    //结束floating动画
    @Override
    public void stopBannerLoadingAnim() {
        fabHomeRandom.setImageResource(R.drawable.ic_beauty);
        mAnimator.cancel();
        fabHomeRandom.setRotation(0);
    }

    //设置floating可以点击
    @Override
    public void setEnableClick() {
        fabHomeRandom.setEnabled(true);
    }

    //设置floating不可以点击
    @Override
    public void setUnEnableClick() {
        fabHomeRandom.setEnabled(false);
    }

    //设置banner图片
    @Override
    public void setBannerImage(String url) {
        Picasso.with(this).load(url).into(ivHomeBanner, PicassoPalette.with(url, ivHomeBanner)
                .intoCallBack(new PicassoPalette.CallBack() {
                    @Override
                    public void onPaletteLoaded(Palette palette) {
                        mHomePresenter.setThemeColor(palette);
                    }
                }));
    }

    @Override
    public void showBannerFail() {
        Toast.makeText(this, "图片加载失败", Toast.LENGTH_SHORT).show();
    }

    /**
     * 设置appbar的背景色
     *
     * @param colorParimay 颜色值
     */
    @Override
    public void setAppBarBackColor(int colorParimay) {
        appbar.setBackgroundColor(colorParimay);
    }

    /**
     * 设置fb的背景色
     *
     * @param colorParimay
     */
    @Override
    public void setFabButtonColor(int colorParimay) {
        MDTintUtil.setTint(fabHomeRandom, colorParimay);
    }


    @Override
    protected void onDestroy() {
        mHomePresenter.unsubscribe();
        super.onDestroy();
    }
}
