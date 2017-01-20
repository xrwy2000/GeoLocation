package com.example.hp.geolocation;

import android.os.Vibrator;
import android.widget.TextView;
import com.baidu.location.BDLocation;
import com.baidu.location.BDNotifyListener;



/**
 * Created by HP on 2017/1/20.
 */

public class NotifyListener extends BDNotifyListener{
    private Vibrator mVibrator;
    public NotifyListener(Vibrator vibrator){
        mVibrator = vibrator;
    }
    @Override
    public void onNotify(BDLocation bdLocation, float v) {
        mVibrator.vibrate(1000);
    }
}
