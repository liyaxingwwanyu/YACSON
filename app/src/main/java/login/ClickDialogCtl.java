package login;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import Z_MajorStructure.contrler.ClickCtl;
import Z_MajorStructure.contrler.NumPwdListener;
import Z_MajorStructure.contrler.UIStructDefault;
import util.MyDialog;



/**
 * Created by Administrator on 2016/5/30.
 */
public class ClickDialogCtl extends ClickCtl {

    private int mIndex = -1;
    private util.MyDialog mDialog;

    public ClickDialogCtl(UIStructDefault aUIStructDefault) {
        super(aUIStructDefault);
    }

    @Override
    protected void clickView(int aIndex) {
        if (aIndex >= 0) {
           // System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkk");
            mIndex = aIndex;
            showDialog(getUIStructDefault().getString(0), getUIStructDefault().getString(1));
        }
    }

    @Override
    public void update(Object aObject) {
        if (aObject instanceof View) {
            if (mDialog != null && mDialog.isShowing()) {
                mDialog.dismiss();
                if (((View) aObject).getId() == getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(3)) {
                    if (mIndex >= 0) {
                        super.clickView(mIndex);
                    }
                }
                mDialog = null;
                mIndex = -1;
            } else {
                super.update(aObject);
            }
        }
    }

    private void showDialog(String aTitle, String aInfo) {
        mDialog = new MyDialog(getUIStructDefault().getContext(), getUIStructDefault().getItemDetailViewsForDetailImageID(0).get(0));
        View lView = View.inflate(getUIStructDefault().getContext(), getUIStructDefault().getItemForDetailViewsID(0), null);
        mDialog.setContentView(lView);

        Window window = mDialog.getWindow();

        WindowManager.LayoutParams lp = window.getAttributes();
        DisplayMetrics d = getUIStructDefault().getContext().getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 宽度设置为屏幕的0.8
        window.setAttributes(lp);

        ((TextView) mDialog.findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(0))).setText(aTitle);
        ((TextView) mDialog.findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(1))).setText(aInfo);
        NumPwdListener lPwdListener = new NumPwdListener();
        lPwdListener.addObserver(this);
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(2)).setOnClickListener(lPwdListener);
        mDialog.findViewById(getUIStructDefault().getItemDetailViewsForDetailViewsID(0).get(3)).setOnClickListener(lPwdListener);
        mDialog.show();
    }

    @Override
    public void clear() {
        if (mDialog != null) {
            if (mDialog.isShowing()) {
                mDialog.dismiss();
            }
            mDialog = null;
        }
        super.clear();
    }
}
