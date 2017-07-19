package me.weyye.todaynews.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.weyye.todaynews.R;
import me.weyye.todaynews.base.BaseActivity;
import me.weyye.todaynews.ui.fragment.FragmentController;

public class MainActivity extends BaseActivity {


    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.llBottom)
    LinearLayout llBottom;
    @BindView(R.id.ivIconHome)
    ImageView ivIconHome;
    @BindView(R.id.tvTextHome)
    TextView tvTextHome;
    @BindView(R.id.tvBadgeHome)
    TextView tvBadgeHome;
    @BindView(R.id.ivIconVideo)
    ImageView ivIconVideo;
    @BindView(R.id.tvTextVideo)
    TextView tvTextVideo;
    @BindView(R.id.ivIconAttention)
    ImageView ivIconAttention;
    @BindView(R.id.tvTextAttention)
    TextView tvTextAttention;
    @BindView(R.id.ivIconMe)
    ImageView ivIconMe;
    @BindView(R.id.tvTextMe)
    TextView tvTextMe;
    private FragmentController mController;

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @Override
    protected void bindViews() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        mController = FragmentController.getInstance(this, R.id.fl_content, true);
        setEnableSwipe(false);
        mController.showFragment(0);
    }

    private View lastSelectedIcon;
    private View lastSelectedText;

    @Override
    protected void setListener() {
        for (int i = 0; i < llBottom.getChildCount(); i++) {
            if (i == 0) {
                //默认选中首页
                setSelectIcon(ivIconHome, tvTextHome);
            }
            final int position = i;
            llBottom.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (lastSelectedIcon != null) lastSelectedIcon.setSelected(false);
                    if (lastSelectedText != null) lastSelectedText.setSelected(false);

                    RelativeLayout rl = (RelativeLayout) v;
                    ImageView icon = (ImageView) rl.getChildAt(0);
                    TextView text = (TextView) rl.getChildAt(1);
                    mController.showFragment(position);
                    setSelectIcon(icon, text);
                }
            });
        }
    }

    private void setSelectIcon(ImageView iv, TextView tv) {
        iv.setSelected(true);
        tv.setSelected(true);
        lastSelectedIcon = iv;
        lastSelectedText = tv;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mController.onDestroy();
    }
}
