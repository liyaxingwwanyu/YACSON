package login;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.ya.cson.R;


/**
 * Created by Administrator on 2016/5/28.
 */
public class PersonalCenterActivity extends ActivityConfigForPersonalCenter {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mView = View.inflate(this, R.layout.activity_personal_center, null);
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
