package project.first.lal.moudle.login;

import android.content.Intent;
import android.first.lal.R;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import project.first.lal.common.base.BaseActivity;
import project.first.lal.common.http.HttpInterface;
import project.first.lal.moudle.UserInfo;
import project.first.lal.moudle.register.RegisterActivity;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.sub_login)
    Button mSubLogin;
    @BindView(R.id.sub_register)
    Button mSubRegister;

    private String account;
    private String pwd;

    private final String TAG = "LoginActivity";

    @Override
    protected void onCreate() {

    }

    @Override
    protected Integer setLayout() {
        return R.layout.login_layout;
    }

    @Override
    protected void initClick() {

        mSubRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });

        mSubLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account = mEtPhone.getText().toString();
                pwd = mEtPwd.getText().toString();
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("account", "zhoukaikai");
                map.put("pwd", "3f476d9130d43d57f9fafa1314ae1334");
                LoginDateHandle.getInstance().login(new HttpInterface<UserInfo>() {

                    @Override
                    public void onNext(ArrayList<UserInfo> data) {
                        Log.i(TAG, data.toString());
                    }

                    @Override
                    public void onFail(String code, String msg) {
                        Log.i(TAG, "code= " + code + "\n" + "msg= " + msg);
                    }
                }, map);
            }
        });
    }
}
