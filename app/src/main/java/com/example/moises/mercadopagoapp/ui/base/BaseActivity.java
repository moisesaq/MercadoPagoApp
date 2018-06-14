package com.example.moises.mercadopagoapp.ui.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by moises on 13/06/2018.
 */

public class BaseActivity extends AppCompatActivity {

    protected void replaceFragment(Fragment fragment, int layoutId, boolean addToStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (addToStack)
            transaction.addToBackStack(fragment.getClass().getName());
        transaction.replace(layoutId, fragment);
        transaction.commit();
    }

    protected boolean popBackStack() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            return true;
        }
        return false;
    }

}
