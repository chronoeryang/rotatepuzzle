package com.chronoer.game.rotatepuzzle.fragment.base;

import android.view.View;

import com.chronoer.game.rotatepuzzle.utils.AppUtils;

/**
 * Created by chronoer on 2019/3/18.
 */

public class BaseCommonFragment extends BaseFragment {
    protected void showKeyboard(View view){
        AppUtils.showKeyboard(getActivity(), view);
    }

    protected void hideKeyboard(){
        AppUtils.hideKeyboard(getActivity());
    }
}
