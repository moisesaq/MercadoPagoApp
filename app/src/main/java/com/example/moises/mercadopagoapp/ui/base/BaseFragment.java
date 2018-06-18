package com.example.moises.mercadopagoapp.ui.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Slide;
import android.view.Gravity;
import android.view.MenuItem;

public abstract class BaseFragment extends Fragment{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        showOrHideHomeBackButton(true);
    }

    protected void showOrHideHomeBackButton(boolean enabled){
        if(getActivity() == null)
            return;
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(enabled);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home && getActivity() != null){
            getActivity().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected abstract void setUp();

    protected void addTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setEnterTransition(new Slide(Gravity.RIGHT));
            setExitTransition(new Slide(Gravity.LEFT));
            //setExitTransition(new Slide());
        }
    }
}
