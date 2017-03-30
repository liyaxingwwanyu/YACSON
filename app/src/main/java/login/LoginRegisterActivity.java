package login;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.newcoolook_0329.R;


/**
 * Created by Administrator on 2016/5/25.
 */
public class LoginRegisterActivity extends ActivityConfigForLoginRegister {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mView = View.inflate(this, R.layout.activity_login_register, null);
        setContentView(mView);
        init();
    }

    @Override
    public void init() {
        initUILayoutConfig();
        initUILayoutMrg();
        initCreate();
    }

}
