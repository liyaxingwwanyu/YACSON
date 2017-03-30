package contrler;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

import model.BSModelDefault;
import observer.IObserver;
import util.AnimationTool;
import util.CtlUtil;
import util.ReflectUtil;


/**
 * Created by sheenly on 16/3/8.
 */
public class UILayoutMrg {
    private UILayoutConfigDefault mUILayoutConfigDefault;
    private Context mContext;
    private View mView;
    private IObserver mObserver;

    public Map<String, UIStructDefault> mUIStructDefaults;
    private Map<String, UICtlDefault> mUICtlDefaults;
  //  private com.reach.IAdService adService;

    public UILayoutMrg(UILayoutConfigDefault aUILayoutConfigDefault, Context aContext, View aView, IObserver aObserver) {
        mUILayoutConfigDefault = aUILayoutConfigDefault;
        mContext = aContext;
        mView = aView;
        mObserver = aObserver;
        mUIStructDefaults = new HashMap<String, UIStructDefault>();
        mUICtlDefaults = new HashMap<String, UICtlDefault>();
    }

    public Map<String, UIStructDefault> getUIStructDefault() {
        return mUIStructDefaults;
    }

    public void create(String aCurIDs[]) {
        create(aCurIDs, null);
    }

    public void create(String aCurIDs[], Map<String, BSModelDefault> aBSModels) { //id
        if (aCurIDs == null) {
            return;
        }
        for (int i = 0; i < aCurIDs.length; i++) {
            String lCurID = aCurIDs[i];
            if (mUICtlDefaults.get(lCurID) != null) {
                continue;
            }
            UIStructDefault lUIStructDefault = new UIStructDefault(mUILayoutConfigDefault);
            lUIStructDefault.setView(mContext, mView);
            if (aBSModels != null) {
                lUIStructDefault.setID(lCurID, aBSModels.get(lCurID));
            } else {
                lUIStructDefault.setID(lCurID);
            }
            mUIStructDefaults.put(lCurID, lUIStructDefault); //lUIStructDefault小框架
            ViewGroup lToContain = lUIStructDefault.getContain(0);
            ViewGroup lMasterContain = lUIStructDefault.getContain(2);
            if (lToContain == null || lMasterContain == null) {
                continue;
            }
            lToContain.addView(lMasterContain);
            Integer lCurPos = mUILayoutConfigDefault.getIDPos(lCurID);
            Object lObject = ReflectUtil.getInstance(mUILayoutConfigDefault.getCtlIDs()[lCurPos], lUIStructDefault);
            if (lObject instanceof UICtlDefault) {
                UICtlDefault lUICtlDefault = (UICtlDefault) lObject;
                lUICtlDefault.addObserver(mObserver);
                lUICtlDefault.init();
                mUICtlDefaults.put(lCurID, lUICtlDefault);
            }
        }
    }

    public void change(String aCurIDs[]) {
        change(aCurIDs, 0);
    }

    public void restore(String aCurIDs[]) {
        change(aCurIDs, 1);
    }

    private void change(String aCurIDs[], int aType) {
        for (int i = 0; i < aCurIDs.length; i++) {
            UIStructDefault lUIStructDefault = mUIStructDefaults.get(aCurIDs[i]);
            if (lUIStructDefault != null) {
                ViewGroup lTo = lUIStructDefault.getContain(0);
                ViewGroup lChange = lUIStructDefault.getContain(1);
                ViewGroup lMaster = lUIStructDefault.getContain(2);
                if (aType == 1) {
                    ViewGroup lViewGroup = lTo;
                    lTo = lChange;
                    lChange = lViewGroup;
                }
                CtlUtil.moveView(lTo, lMaster, lChange);
            }
        }
    }


    public void visibility(String aCurIDs[], int aVisibility) {
        visibility(2, aCurIDs, aVisibility);
    }

    public void visibilityForToContain(String aCurIDs[], int aVisibility) {
        visibility(0, aCurIDs, aVisibility);
    }

    public void visibilityAnimationForToContain(String aCurIDs[], int aVisibility, int aAnimationIndex) {
        visibility(0, aCurIDs, aVisibility, aAnimationIndex);
    }

    public void visibilityAnimationForToContain(String aCurIDs[], int aVisibility, int aAnimationIndex, boolean aRepeat) {
        visibility(0, aCurIDs, aVisibility, aAnimationIndex, aRepeat);
    }

    public void visibilityAnimationForMasterContain(String aCurIDs[], int aVisibility, int aAnimationIndex) {
        visibility(2, aCurIDs, aVisibility, aAnimationIndex);
    }

    //aContainType: 0: ToContain , 1: ChangeToContain , 2: MasterContain
    private void visibility(int aContainType, String aCurIDs[], int aVisibility) {
        visibility(aContainType, aCurIDs, aVisibility, -1);
    }

    private void visibility(int aContainType, String aCurIDs[], int aVisibility, int aAnimationIndex) {
        visibility(aContainType, aCurIDs, aVisibility, aAnimationIndex, true);
    }

    private void visibility(int aContainType, String aCurIDs[], int aVisibility, int aAnimationIndex, boolean aRepeat) {
        if (aVisibility == View.GONE || aVisibility == View.INVISIBLE || aVisibility == View.VISIBLE) {
            for (int i = 0; i < aCurIDs.length; i++) {
                UIStructDefault lUIStructDefault = mUIStructDefaults.get(aCurIDs[i]);
                if (lUIStructDefault != null) {
                    ViewGroup lContain = lUIStructDefault.getContain(aContainType);
                    if (lContain != null) {
                        if (!aRepeat && lContain.getVisibility() == aVisibility) {
                            continue;
                        }
                        lContain.clearAnimation();
                        lContain.setVisibility(aVisibility);
                        AnimationTool.execute(aAnimationIndex, lContain);
                    }
                }
            }
        }
    }

    public void visibilityAnimationForDetailView(String aCurID, int aVisibility, int aAnimationIndex, Integer aDetailPos[]) {
        if (aVisibility == View.GONE || aVisibility == View.INVISIBLE || aVisibility == View.VISIBLE) {
            final UIStructDefault lUIStructDefault = mUIStructDefaults.get(aCurID);

            if (lUIStructDefault != null) {
                for (int i = 0; i < aDetailPos.length; i++) {
                    View lDetailView = lUIStructDefault.getDetailView(aDetailPos[i]);
                    if (lDetailView != null) {
                        lDetailView.clearAnimation();
                        lDetailView.setVisibility(aVisibility);
                        //System.out.println( lDetailView.getId());
                        AnimationTool.execute(aAnimationIndex, lDetailView);

                    }
                }
            }
        }
    }

    public void setViewAlphaForDetailView(String aCurID, Integer aDetailPos[], float aAlpha) {
        final UIStructDefault lUIStructDefault = mUIStructDefaults.get(aCurID);
        if (lUIStructDefault != null) {
            View lDetailView = lUIStructDefault.getDetailView(aDetailPos[0]);
            if (lDetailView != null) {
                lDetailView.setAlpha(aAlpha);
            }
        }

    }

    public void setViewAlphaForDetailView(String aCurID, Integer aDetailPos[], int aAlpha) {
        final UIStructDefault lUIStructDefault = mUIStructDefaults.get(aCurID);
        if (lUIStructDefault != null) {
            for (int i = 0; i < aDetailPos.length; i++) {
                View lDetailView = lUIStructDefault.getDetailView(aDetailPos[i]);
                if (lDetailView != null) {
                    lDetailView.setAlpha(aAlpha);
                }
            }
        }
    }


    public void switchVisibilityAnimationForDetailView(String aCurID, int aAnimationIndex, Integer aDetailPos[]) {
        UIStructDefault lUIStructDefault = mUIStructDefaults.get(aCurID);
        if (lUIStructDefault != null) {
            for (int i = 0; i < aDetailPos.length; i++) {
                View lDetailView = lUIStructDefault.getDetailView(aDetailPos[i]);
                if (lDetailView != null) {
                    lDetailView.clearAnimation();
                    if (lDetailView.getVisibility() == View.VISIBLE) {
                        lDetailView.setVisibility(View.GONE);
                    } else {
                        lDetailView.setVisibility(View.VISIBLE);
                        // System.out.println( lDetailView.getId());
                    }

                    AnimationTool.execute(aAnimationIndex, lDetailView);
                }
            }
        }
    }

    public void clear() {
        if (mUICtlDefaults != null) {
            for (Map.Entry<String, UICtlDefault> entry : mUICtlDefaults.entrySet()) {
                UICtlDefault lUICtlDefault = entry.getValue();
                if (lUICtlDefault != null) {
                    lUICtlDefault.clear();
                }
            }
            mUICtlDefaults.clear();
            mUICtlDefaults = null;
        }
        if (mUIStructDefaults != null) {
            for (Map.Entry<String, UIStructDefault> entry : mUIStructDefaults.entrySet()) {
                UIStructDefault lUIStructDefault = entry.getValue();
                if (lUIStructDefault != null) {
                    lUIStructDefault.clear();
                }
            }
            mUIStructDefaults.clear();
            mUIStructDefaults = null;
        }
        mUILayoutConfigDefault = null;
        mContext = null;
        mView = null;
    }
}
