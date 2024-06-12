package com.culturecode.main.app.connection;

import com.culturecode.main.app.localisations.LocalisationResponse;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Body;
import retrofit2.Call;
public interface LocalisationService {
    @GET
    Call<LocalisationResponse> getLocalisations();
}
