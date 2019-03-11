package com.example.haveibeenpwnedmvvmdemo.repo;

import java.util.Observable;
import java.util.Observer;

public class HaveIBeenPawnedRepository extends Observable implements Observer, DataSource {

    private final DataSource localDataSource;
    private final DataSource remoteDataSource;

    public HaveIBeenPawnedRepository(DataSource localDataSource, DataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void update(Observable observable, Object result) {
        setChanged();
        notifyObservers();

    }


    @Override
    public void setObserver(io.reactivex.Observer observer) {
        addObserver((Observer) observer);

    }

    @Override
    public void getResultForDomain(String domain) {
        remoteDataSource.setObserver((io.reactivex.Observer) this);
        remoteDataSource.getResultForDomain(domain);

    }
}
