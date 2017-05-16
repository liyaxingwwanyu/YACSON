package Z_MajorStructure.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sheenly on 16/3/17.
 */
public class ObservableDefault {

    private List<IObserver> mObservers;

    public ObservableDefault() {
        mObservers = new ArrayList<IObserver>();
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
}
