package com.ibrahim.atpnet_task.data;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

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
    private Context mContext;

    MutableLiveData<List<MetroStation>> metroSttionsLiveData = new MutableLiveData<>();

    public MetroStationRepository(Application application) {
        mContext = application;
    }

    public MutableLiveData<List<MetroStation>> getMetroStations() {


        ApiManager.getAPIs().getMetroStations().enqueue(new Callback<MetroStationsResponse>() {
            @Override
            public void onResponse(Call<MetroStationsResponse> call, Response<MetroStationsResponse> response) {
                Log.d(TAG, "onResponse: " + response.body().getRows());
                metroSttionsLiveData.setValue( response.body().getRows());
            }

            @Override
            public void onFailure(Call<MetroStationsResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " +" "+t.toString());
                Toast.makeText(mContext, "some thing went wrong \n"+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return metroSttionsLiveData;
    }

}
