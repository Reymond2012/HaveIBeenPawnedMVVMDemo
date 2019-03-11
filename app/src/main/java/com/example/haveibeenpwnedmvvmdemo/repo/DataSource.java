package com.example.haveibeenpwnedmvvmdemo.repo;

import com.example.haveibeenpwnedmvvmdemo.home.HomeViewModel;

import io.reactivex.Observer;

public interface DataSource {
    void setObserver(Observer observer);

    void getResultForDomain(String domain);
}
