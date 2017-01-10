package project.first.lal.common.base;

import android.first.lal.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import project.first.lal.common.Constants;
import project.first.lal.common.utils.statusBar.StatusBarUtil;

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract void onCreate();

    protected abstract Integer setLayout();

    protected abstract void initClick();

    protected Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Integer layout = setLayout();
        if (null == layout)
            return;
        setContentView(layout);
        setStatusBar();
        mUnbinder = ButterKnife.bind(this);  //保存引用
        onCreate();
        initClick();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    protected void setStatusBar() {
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.title_bg), Constants.TRANTSPARENT);
    }
}
