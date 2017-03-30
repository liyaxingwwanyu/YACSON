package collection;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.newcoolook_0329.R;

/**
 * Created by wty on 2017/3/30.
 */

public class CollectionActivity extends ActivityConfigForCollection {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mView = View.inflate(this, R.layout.activity_collection, null);
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
