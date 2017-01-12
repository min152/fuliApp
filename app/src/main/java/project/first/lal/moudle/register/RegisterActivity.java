package project.first.lal.moudle.register;

import android.content.Intent;
import android.first.lal.R;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import project.first.lal.common.Constants;
import project.first.lal.common.base.BaseActivity;
import project.first.lal.common.http.HttpInterface;
import project.first.lal.common.utils.MD5Util;
import project.first.lal.common.utils.SPUtils;
import project.first.lal.moudle.UserInfo;
import project.first.lal.moudle.home.HomeActivity;

/**
 * user:zhuwt
 *
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 * @ClassName: ${type_name}.java
 * 2017/1/5
 * @说明 代码版权归 作者 所有
 */
public class RegisterActivity extends BaseActivity {

    @BindView(R.id.et_register_phone)
    EditText mEtRegisterPhone;
    @BindView(R.id.et_register_pwd)
    EditText mEtRegisterPwd;
    @BindView(R.id.sub_register)
    Button mSubRegister;
    private String phone;

    private String pwd;

    private final String TAG = "RegisterActivity";

    @Override
    protected void onCreate() {

    }

    @Override
    protected Integer setLayout() {
        return R.layout.register_layout;
    }

    @Override
    protected void initClick() {
        mSubRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = mEtRegisterPhone.getText().toString();
                pwd = mEtRegisterPwd.getText().toString();
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("phone", "18767196567");
                map.put("pwd", MD5Util.Md5("123456"));
                RegisterDateHandle.getInstance().register(new HttpInterface<UserInfo>() {
                    @Override
                    public void onNext(ArrayList<UserInfo> data) {
                        if (null != data && data.size() > 0) {
                            UserInfo userInfo = data.get(0);
                            SPUtils.put(Constants.PHONE,"18767196567");
                            SPUtils.put(Constants.PWD,MD5Util.Md5("123456"));
                            SPUtils.put(Constants.ISLOGIN,true);
                            startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                        }
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
