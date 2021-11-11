package androidsamples.java.journalapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class SharedViewModel {
    private static SharedViewModel sharedVM = null;

    private String mType,mDate, mStartTime, mEndTime;
    private SharedViewModel(){
        mType = mEndTime = mDate = mStartTime = "";
    }

    public static SharedViewModel getInstance()
    {
        if (sharedVM == null)
            sharedVM = new SharedViewModel();

        return sharedVM;
    }

    public String getDate() {
        return mDate;
    }
    public void setDate(String mDate) {
        this.mDate = mDate;
    }


    public String getType() {
        return mType;
    }
    public void setType(String mType) {
        this.mType = mType;
    }


    public String getStartTime() {
        return mStartTime;
    }
    public void setStartTime(String mStartTime) {
        this.mStartTime = mStartTime;
    }


    public String getEndTime() {
        return mEndTime;
    }
    public void setEndTime(String mEndTime) {
        this.mEndTime = mEndTime;
    }
}

