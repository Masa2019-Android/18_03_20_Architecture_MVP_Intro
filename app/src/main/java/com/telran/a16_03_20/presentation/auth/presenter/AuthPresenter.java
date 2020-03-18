package com.telran.a16_03_20.presentation.auth.presenter;

import android.util.Log;

import com.telran.a16_03_20.business.auth.AuthInteractor;
import com.telran.a16_03_20.presentation.auth.view.AuthView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AuthPresenter {
    private static final String TAG = "AuthPresenter";
    AuthView view;
    AuthInteractor interactor;
    Disposable disposable;

    public AuthPresenter(AuthInteractor interactor) {
        this.interactor = interactor;
    }

    public void onAttach(AuthView view){
        this.view = view;
    }

    public void onLogin(String email, String password){
        if(view != null) {
            view.showProgress();
        }
        disposable = interactor.onLogin(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::authSuccess,throwable -> {
                    Log.e(TAG, "onLogin: ", throwable );
                    onAuthError(throwable.getMessage());
                });
    }

    public void onRegistration(String email, String password){
        if(view != null){
            view.showProgress();
        }

        disposable = interactor.onRegistration(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::authSuccess,throwable -> {
                    Log.e(TAG, "onRegistration: ", throwable );
                    onAuthError(throwable.getMessage());
                });
    }

    private void authSuccess(){
        if(view != null){
            view.hideProgress();
            view.showNextView();
        }
    }

    private void onAuthError(String error){
        if(view != null){
            view.hideProgress();
            view.showError(error);
        }
    }

    public void onDetachView(){
        view = null;
    }
}
