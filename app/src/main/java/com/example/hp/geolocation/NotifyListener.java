package com.example.hp.geolocation;

import android.os.Vibrator;
import android.widget.TextView;
import com.baidu.location.BDLocation;
import com.baidu.location.BDNotifyListener;

import java.util.Date;
import java.util.List;


/**
 * Created by HP on 2017/1/20.
 */

public class NotifyListener extends BDNotifyListener{
    private int number;
    private Vibrator mVibrator;
    private List<LocationItem> mList;
    private ItemLab mItemLab;
    private MainActivity.LocationItemAdapter mItemAdapter;
    public NotifyListener(Vibrator vibrator, ItemLab itemLab, MainActivity.LocationItemAdapter adapter){
        mVibrator = vibrator;
        number=0;
        mList = itemLab.getList();
        mItemAdapter = adapter;
    }
    @Override
    public void onNotify(BDLocation bdLocation, float v) {
        mVibrator.vibrate(1000);
        number++;
        LocationItem item = new LocationItem();
        item.setTime("第"+number+"签到！");
        item.setDate(new Date());
        mList.add(item);
        mItemAdapter.notifyDataSetChanged();
    }
}
