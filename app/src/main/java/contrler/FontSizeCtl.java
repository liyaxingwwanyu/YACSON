package contrler;


import android.view.View;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

import collection.UILayoutConfigForMiniSite;

/**
 * Created by Administrator on 2016/4/19.
 */
public class FontSizeCtl extends UICtlDefault {
    protected List<View> mClickViews;

    public FontSizeCtl(UIStructDefault aUIStructDefault) {
        super(aUIStructDefault);
        mClickViews = new ArrayList<View>();
    }

    @Override
    public void init() {
        initShow();
        initClick();
    }

    protected void initShow() {
        int lInitIndex = getUIStructDefault().getBSModel().getPosForReal();
        RadioButton lBtn = (RadioButton) getUIStructDefault().getDetailView(mDetailViewsPos[lInitIndex]);
        lBtn.setChecked(true);
    }

    protected void initClick() {
        NumPwdListener lPwdListener = new NumPwdListener();
        lPwdListener.addObserver(this);
        mClickViews.clear();
        for (int i = 0; i < mDetailViewsPos.length; i++) {
            View lView = getUIStructDefault().getDetailView(mDetailViewsPos[i]);
            lView.setOnClickListener(lPwdListener);
            mClickViews.add(lView);
        }
    }

    @Override
    public void update(Object aObject) {
        if (aObject instanceof View) {
            /*View view = (View) aObject;
            if (view.getId() == Integer.valueOf(0x7f0b012d)) {
                //System.out.println("klklklkll,oooooooooo");
            }*/
            View view = (View) aObject;
            /*System.out.println(view.getId() +" 345678aaaoooooooooo");
            int i = view.getId();*/
//            if ((view.getId() +"").equals(2131689960 +"") || (view.getId() +"").equals(2131690008 +"") ) {
//                RussiaUtil.burypoint(631,"文章详情页右上角点击数22", RussiaUtil.myContext);
//                SharedPreferences sp = RussiaUtil.myContext.getSharedPreferences("ican", Context.MODE_PRIVATE);
//                sp.edit().putString("save","文章详情页右上角点击数a").commit();
//            }else if ((view.getId() +"").equals(2131689969 +"")) {
//                RussiaUtil.burypoint(617,"取消点击数a",RussiaUtil.myContext);
//            }else if ((view.getId() +"").equals(2131689965 +"")) {
//                RussiaUtil.burypoint(615,"字体大小点击数a",RussiaUtil.myContext);
//            }else if ((view.getId() +"").equals(2131755576 +"")) {
//                RussiaUtil.burypoint(616,"分享点击数",RussiaUtil.myContext);
//            }else if ((view.getId() +"").equals(2131755606 +"")) {
//                RussiaUtil.burypoint(621,"系统分享点击数",RussiaUtil.myContext);
//            }

            int lIndex = mClickViews.indexOf(aObject);  //有事件的view的集合。
            clickView(lIndex);
        }
    }

    protected void clickView(int aIndex) {
        //System.out.println("klklklklluuuuuuu,oooooooooo");
        if (aIndex >= 0) {
            if (aIndex <= 2) {
                getUIStructDefault().getBSModel().setCurPos(aIndex);
            }
            getUIStructDefault().setCurState(UILayoutConfigForMiniSite.mState[0]);
            updateObserver(getUIStructDefault());
        }
    }

}
