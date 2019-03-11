package com.example.haveibeenpwnedmvvmdemo.home;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.LiveData;

import com.example.haveibeenpwnedmvvmdemo.data.DomainResponse;
import com.example.haveibeenpwnedmvvmdemo.repo.DataSource;
import com.example.haveibeenpwnedmvvmdemo.repo.HaveIBeenPawnedRepository;
import com.example.haveibeenpwnedmvvmdemo.repo.LocalDataSource;
import com.example.haveibeenpwnedmvvmdemo.repo.RemoteDataSource;

import java.util.List;
import java.util.Observable;
import java.util.Observer;



public class HomeViewModel implements Observer {

    private  final DataSource haveIBeenPawnedRepository;
    private  final MutableLiveData<List<DomainResponse>> resultsLiveData = new MutableLiveData<>();


    public HomeViewModel() {
        haveIBeenPawnedRepository = new HaveIBeenPawnedRepository(new LocalDataSource(), new RemoteDataSource() );
    }


    @Override
    public void update(Observable observable, Object result) {
        List<DomainResponse> domainResponses = (List<DomainResponse>) result;
        resultsLiveData.setValue(domainResponses);

    }

    public LiveData<List<DomainResponse>> getResponseLiveData(){
        return resultsLiveData;
    }

    public void getDomain (String domain){
        haveIBeenPawnedRepository.setObserver((io.reactivex.Observer) this);
        haveIBeenPawnedRepository.getResultForDomain(domain);
    }
}
