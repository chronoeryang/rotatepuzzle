package com.chronoer.game.rotatepuzzle.helper;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.widget.Toast;

import com.chronoer.game.rotatepuzzle.R;

import androidx.appcompat.app.AlertDialog;

/**
 * Created by chronoer on 2019/3/26.
 */

public class DialogHelper {

    public static ProgressDialog waitingDialog(Context context, String message){
        ProgressDialog dialog = new ProgressDialog(context, android.app.AlertDialog.THEME_HOLO_LIGHT);
        if(TextUtils.isEmpty(message)){
            message = context.getString(R.string.dialog_title_waiting);
        }else{
            dialog.setTitle(R.string.dialog_title_waiting);
        }
        dialog.setMessage(message);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);

        return dialog;
    }

    public static Dialog warningDialog(Context context, String message, DialogInterface.OnClickListener listener){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.dialog_button_text_ok, listener);
        builder.setCancelable(false);

        Dialog dialog = builder.create();
        return dialog;
    }

    public static Dialog singleChoiceListDialog(Context context, String title, int index, String[] array, DialogInterface.OnClickListener listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if(TextUtils.isEmpty(title) == false){
            builder.setTitle(title);
        }
        builder.setSingleChoiceItems(array, index, listener);
        builder.setNegativeButton(context.getString(R.string.dialog_button_text_cancel), null);

        Dialog dialog = builder.create();
        return dialog;
    }

    public static Dialog confirmDialog(Context context, String title, String message, DialogInterface.OnClickListener confirmListener, DialogInterface.OnClickListener cancelListener){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        if(TextUtils.isEmpty(title) == false){
            builder.setTitle(title);
        }
        if(TextUtils.isEmpty(message) == false){
            builder.setMessage(message);
        }

        builder.setPositiveButton(R.string.dialog_button_text_ok, confirmListener);
        builder.setNegativeButton(R.string.dialog_button_text_cancel, cancelListener);
        builder.setCancelable(false);

        Dialog dialog = builder.create();
        return dialog;
    }

    public static void showToast(Context context, String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
