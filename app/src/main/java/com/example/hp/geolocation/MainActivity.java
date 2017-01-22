package com.example.hp.geolocation;

import android.content.Context;

import android.location.LocationManager;
import android.os.Vibrator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.baidu.location.BDLocationListener;
import com.baidu.location.BDNotifyListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import org.w3c.dom.Text;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    Button mButton;

    private int number = 0;
    private double latitude;//纬度
    private double longitude;//经度


    public LocationClient mLocationClient = null;
    public BDLocationListener myListener;
    public BDNotifyListener notify;
    public Vibrator mVibrator;


    //视图
    private RecyclerView mRecyclerView;

    private LocationItemAdapter mItemAdapter;

    ItemLab itemLab;
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);


        mButton = (Button) findViewById(R.id.cc);
        mVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        mRecyclerView = (RecyclerView)findViewById(R.id.check_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        updateUI();
         initLocation();


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 getLocationByBDLBS();
            }
        });
    }

    private void updateUI(){
        itemLab = ItemLab.get(this);
        List<LocationItem> itemList = itemLab.getList();

        mItemAdapter = new LocationItemAdapter(itemList);
        mRecyclerView.setAdapter(mItemAdapter);
    }
    private void initLocation() {
        mLocationClient = new LocationClient(getApplicationContext());
        myListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        int span = 3000;
        option.setScanSpan(span);
        option.setIsNeedAddress(false);
        mLocationClient.setLocOption(option);
        notify = new NotifyListener(mVibrator,itemLab,mItemAdapter);
        notify.SetNotifyLocation(29.63799, 111.751078, 3000, "gps");
        mLocationClient.registerNotify(notify);

    }

    private void getLocationByBDLBS() {
        mLocationClient.start();
        mVibrator.vibrate(1000);

    }


    private class LocationItemHolder extends RecyclerView.ViewHolder{

        private LocationItem mItem;
        private TextView time;
        private TextView checkTime;
        public LocationItemHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.time);
            checkTime = (TextView) itemView.findViewById(R.id.check_time);
        }
        public void bindItem(LocationItem item){
            mItem = item;
            time.setText(mItem.getTime());
            checkTime.setText(mItem.getDate().toString());
        }
    }

    public class LocationItemAdapter extends RecyclerView.Adapter<LocationItemHolder>{

        private List<LocationItem> mList;
        public LocationItemAdapter(List<LocationItem> list){
            mList = list;
        }
        @Override
        public LocationItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
            View view = layoutInflater.inflate(R.layout.item,parent,false);

            return new LocationItemHolder(view);
        }

        @Override
        public void onBindViewHolder(LocationItemHolder holder, int position) {

            LocationItem item = mList.get(position);
            holder.bindItem(item);
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }


}


