package com.example.moises.mercadopagoapp.ui.base;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.transition.Explode;
import android.transition.Slide;

import com.example.moises.mercadopagoapp.model.Payment;

public abstract class BaseFragment extends Fragment{

    protected abstract void setUp();

    protected void addTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementEnterTransition(new Explode());
            setEnterTransition(new Slide());
            setExitTransition(new Slide());
            //setExitTransition(new Slide());
        }
    }
}
