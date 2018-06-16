package com.example.moises.mercadopagoapp.ui.base;

public interface BasePresenter <T>{

    void setView(T view);

    void doDispose();
}
