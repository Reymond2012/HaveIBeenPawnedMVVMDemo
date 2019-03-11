package com.example.haveibeenpwnedmvvmdemo.repo;

import com.example.haveibeenpwnedmvvmdemo.Constants;
import com.example.haveibeenpwnedmvvmdemo.data.DomainResponse;
import com.example.haveibeenpwnedmvvmdemo.network.DomainService;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource extends Observable implements DataSource {

    private final DomainService domainService;


    public RemoteDataSource (){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(25, TimeUnit.SECONDS )
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        domainService = retrofit.create(DomainService.class);
    }

    @Override
    public void setObserver(Observer observer) {
        addObserver((java.util.Observer) observer);

    }

    @Override
    public void getResultForDomain(String domain) {

        final List<DomainResponse> domainResponses = new ArrayList<>();

        domainService.getDataForDomain( domain).enqueue(new Callback<List<DomainResponse>>() {
            @Override
            public void onResponse(Call<List<DomainResponse>> call, Response<List<DomainResponse>> response) {
                if(response.isSuccessful() && response.body() !=null){
                    domainResponses.clear();
                    domainResponses.addAll(response.body());
                    setChanged();
                    notifyObservers(domainResponses);
                }

            }

            @Override
            public void onFailure(Call<List<DomainResponse>> call, Throwable throwable) {
                throwable.printStackTrace();

            }
        });

    }
}
