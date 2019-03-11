package com.example.haveibeenpwnedmvvmdemo.network;

import com.example.haveibeenpwnedmvvmdemo.Constants;
import com.example.haveibeenpwnedmvvmdemo.data.DomainResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DomainService {

    @GET(Constants.DOMAIN_ENDPOINT)
    Call<List<DomainResponse>> getDataForDomain(@Query("domain")String domain);
}
