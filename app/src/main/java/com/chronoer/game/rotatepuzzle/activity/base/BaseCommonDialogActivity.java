package com.chronoer.game.rotatepuzzle.activity.base;

import com.chronoer.game.rotatepuzzle.R;
import com.chronoer.game.rotatepuzzle.fragment.base.BaseFragment;

/**
 * Created by chronoer on 2019/3/18.
 */

public abstract class BaseCommonDialogActivity extends BaseActivity {

    abstract protected void setView();

    protected void replaceFragment(BaseFragment fragment){
        mFragmentManager.beginTransaction().replace(R.id.fragment_layout, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
