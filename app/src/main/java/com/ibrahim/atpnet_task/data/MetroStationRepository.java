package com.ibrahim.atpnet_task.data;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import com.ibrahim.atpnet_task.data.model.MetroStation;
import com.ibrahim.atpnet_task.data.network.ApiManager;
import com.ibrahim.atpnet_task.data.network.responses.MetroStationsResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MetroStationRepository {
    private static final String TAG = "MetroStationRepository";

    public MutableLiveData<List<MetroStation>> getMetroStations() {

        final MutableLiveData<List<MetroStation>> metroSttionsLiveData = new MutableLiveData<>();

        ApiManager.getAPIs().getMetroStations().enqueue(new Callback<MetroStationsResponse>() {
            @Override
            public void onResponse(Call<MetroStationsResponse> call, Response<MetroStationsResponse> response) {
                Log.d(TAG, "onResponse: " + response.body().getRows());
                metroSttionsLiveData.setValue( response.body().getRows());
            }

            @Override
            public void onFailure(Call<MetroStationsResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());

            }
        });

        return metroSttionsLiveData;
    }

}
