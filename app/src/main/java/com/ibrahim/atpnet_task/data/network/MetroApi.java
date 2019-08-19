package com.ibrahim.atpnet_task.data.network;


import com.ibrahim.atpnet_task.data.network.responses.MetroStationsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MetroApi {

   @GET("metro.json")
   Call<MetroStationsResponse> getMetroStations( );
}
