package com.example.demoapplication.utility;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

import com.example.demoapplication.R;


public class NetworkChangeListener extends BroadcastReceiver {
    MediaPlayer mediaPlayer ;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (!Common.isConnectedToInternet(context)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View layout_dialog = LayoutInflater.from(context).inflate(R.layout.check_internet_demo, null);
            builder.setView(layout_dialog);
            AppCompatButton retry = layout_dialog.findViewById(R.id.btnretry);


            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
            alertDialog.getWindow().setGravity(Gravity.CENTER);

            retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    
                    alertDialog.dismiss();
                    onReceive(context, intent);
                }
            });

        }
    }
}
