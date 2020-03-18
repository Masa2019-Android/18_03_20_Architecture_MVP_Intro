package com.telran.a16_03_20.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.telran.a16_03_20.R;
import com.telran.a16_03_20.business.auth.AuthInteractor;
import com.telran.a16_03_20.business.auth.AuthInteractorImpl;
import com.telran.a16_03_20.data.auth.AuthRepository;
import com.telran.a16_03_20.data.auth.AuthRepositoryImpl;
import com.telran.a16_03_20.data.provider.store.SprefStoreProvider;
import com.telran.a16_03_20.data.provider.store.StoreProvider;
import com.telran.a16_03_20.data.provider.web.ApiRx;
import com.telran.a16_03_20.di.ApiProvider;
import com.telran.a16_03_20.presentation.auth.presenter.AuthPresenter;
import com.telran.a16_03_20.presentation.auth.view.AuthFragment;

public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StoreProvider storeProvider = new SprefStoreProvider(this);
        ApiRx api = ApiProvider.getInstance().getApiRx();

        AuthRepository repository = new AuthRepositoryImpl(api,storeProvider);
        AuthInteractor interactor = new AuthInteractorImpl(repository);
        AuthPresenter presenter = new AuthPresenter(interactor);
        AuthFragment fragment = new AuthFragment(presenter);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.root,fragment)
                .commit();
    }
}
