package com.chronoer.game.rotatepuzzle.fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chronoer.game.rotatepuzzle.utils.LogUtils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * Created by chronoer on 2019/3/18.
 */

public class BaseFragment extends Fragment {
    private final static String TAG = BaseFragment.class.getSimpleName();

    protected FragmentManager mChildFragmentManager;

    public BaseFragment() {
        LogUtils.i(TAG, "");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtils.v(TAG, "onAttach:"+ this.getClass().getSimpleName());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.v(TAG, "onCreate:"+ this.getClass().getSimpleName());

        setHasOptionsMenu(false);
        mChildFragmentManager = getChildFragmentManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtils.v(TAG, "onCreateView:"+ this.getClass().getSimpleName());

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LogUtils.v(TAG, "onViewCreated:"+ this.getClass().getSimpleName());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.v(TAG, "onActivityCreated:"+ this.getClass().getSimpleName());
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.v(TAG, "onStart:"+ this.getClass().getSimpleName());
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.v(TAG, "onResume:"+ this.getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.v(TAG, "onPause:"+ this.getClass().getSimpleName());
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.v(TAG, "onStop:"+ this.getClass().getSimpleName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.v(TAG, "onDestroyView:"+ this.getClass().getSimpleName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.v(TAG, "onDestroy:"+ this.getClass().getSimpleName());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtils.v(TAG, "onDetach:"+ this.getClass().getSimpleName());
    }
}
