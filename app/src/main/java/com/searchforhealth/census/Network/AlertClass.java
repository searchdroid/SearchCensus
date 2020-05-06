package com.searchforhealth.census.Network;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.searchforhealth.census.R;


/**
 * Created by Dvimay on 6/29/2016.
 */
public class AlertClass {

    public void customDialogforAlertMessage(Activity activity, String title, String message) {
        // TODO Auto-generated method stub

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_popup_alert);
        // set the custom dialog components - text, image and button
        TextView btnOk = (TextView) dialog.findViewById(R.id.btn_ok);
        TextView txtTitle = (TextView) dialog.findViewById(R.id.txtAlertTile);
        TextView txtMsg= (TextView) dialog.findViewById(R.id.txtAlertMesssage);
        txtTitle.setText(title);
        txtMsg.setText(message);
        if(title.equalsIgnoreCase(""))
            txtTitle.setVisibility(View.GONE);
        // if button is clicked, close the custom dialog
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        dialog.show();
    }
}
