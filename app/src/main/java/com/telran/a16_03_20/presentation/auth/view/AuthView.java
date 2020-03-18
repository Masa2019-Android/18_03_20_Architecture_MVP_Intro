package com.telran.a16_03_20.presentation.auth.view;

public interface AuthView {
    void showProgress();
    void hideProgress();
    void showError(String error);
    void showNextView();
}
