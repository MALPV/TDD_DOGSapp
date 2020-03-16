package com.apps.malpv.dogs.api;

import com.apps.malpv.dogs.model.BreedsListResponse;
import com.apps.malpv.dogs.model.PicsListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IApiDog {

    @GET("api/breeds/list")
    Call<BreedsListResponse> getBreedList();

    @GET("api/breed/{breed}/images/")
    Call<PicsListResponse>getBreedImageList(@Path("breed") String breed);

}
