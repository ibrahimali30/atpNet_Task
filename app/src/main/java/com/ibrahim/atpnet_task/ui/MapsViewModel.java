package com.ibrahim.atpnet_task.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.ibrahim.atpnet_task.data.MetroStationRepository;
import com.ibrahim.atpnet_task.data.model.MetroStation;

import java.util.List;

public class MapsViewModel extends AndroidViewModel {
    private static final String TAG = "MapsViewModel";
    private MetroStationRepository mRepository;
    private MutableLiveData<List<MetroStation>> mMetroStationLiveData;

    public MapsViewModel(@NonNull Application application) {
        super(application);
        mRepository = new MetroStationRepository();
        mMetroStationLiveData = mRepository.getMetroStations();
    }

    public MutableLiveData<List<MetroStation>> getMetroStationLiveData() {
        return mMetroStationLiveData;
    }
}
