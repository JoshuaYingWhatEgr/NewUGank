package joshuayingwhat.newugank.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * fragment公共抽象类
 *
 * @author joshuayingwhat
 */
public abstract class BaseFragment extends Fragment {

    private Activity activity;
    private Unbinder unbinder;
    private BasePresenter mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = setLayoutView(inflater, container, savedInstanceState);
        if (view != null) {
            unbinder = ButterKnife.bind(this, view);
        }

        mPresenter = createPresenter();
        return view;
    }

    public abstract View setLayoutView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public abstract BasePresenter createPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (mPresenter != null) {
            mPresenter = null;
        }
    }
}
