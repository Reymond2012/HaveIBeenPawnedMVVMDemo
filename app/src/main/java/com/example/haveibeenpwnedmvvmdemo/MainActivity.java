package com.example.haveibeenpwnedmvvmdemo;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.haveibeenpwnedmvvmdemo.data.DomainResponse;
import com.example.haveibeenpwnedmvvmdemo.home.HaveIBeenPawnedAdapter;
import com.example.haveibeenpwnedmvvmdemo.home.HomeViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btGetData = findViewById(R.id.btGetData);
        RecyclerView rvData = findViewById(R.id.rvData);

        final EditText etDomain = findViewById(R.id.etDomain);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvData.setLayoutManager(linearLayoutManager);
        rvData.addItemDecoration(new DividerItemDecoration(this,
                linearLayoutManager.getOrientation()));
        final HaveIBeenPawnedAdapter haveIBeenPawnedAdapter = new HaveIBeenPawnedAdapter();
        rvData.setAdapter(haveIBeenPawnedAdapter);

        final HomeViewModel homeViewModel = new HomeViewModel();
        homeViewModel.getResponseLiveData().observe(this, new Observer<List<DomainResponse>>() {
            @Override
            public void onChanged(@Nullable List<DomainResponse> domainResponses) {
                haveIBeenPawnedAdapter.setData(domainResponses);
            }
        });

        btGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeViewModel.getDomain(etDomain.getText().toString());
            }
        });
    }
}
