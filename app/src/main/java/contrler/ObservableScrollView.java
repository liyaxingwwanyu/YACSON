package contrler;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

import observer.IObserver;


/**
 * Created by Administrator on 2016/6/22.
 */
public class ObservableScrollView extends ScrollView {

    private List<IObserver> mObservers;
    private UIDataDefault mUIDataDefault;

    public ObservableScrollView(Context context) {
        super(context);
        init();
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void addObserver(IObserver aObserver) {
        if (aObserver != null) {
            mObservers.add(aObserver);
        }
    }

    public void removeObserver(IObserver aObserver) {
        if (aObserver != null && mObservers.size() > 0) {
            mObservers.remove(aObserver);
        }
    }

    public void updateObserver(Object aObject) {
        for (IObserver lObserver : mObservers) {
            if (lObserver != null) {
                lObserver.update(aObject);
            }
        }
    }

    private void  init(){
        mObservers = new ArrayList<IObserver>();
        mUIDataDefault = new UIDataDefault();
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        mUIDataDefault.setView(this);
        mUIDataDefault.setCurID(UIDataDefault.mIDs[2]);
        mUIDataDefault.setCurState(UIDataDefault.mStates[13]);
        mUIDataDefault.setXY(x, y);
        updateObserver(mUIDataDefault);
    }
}
