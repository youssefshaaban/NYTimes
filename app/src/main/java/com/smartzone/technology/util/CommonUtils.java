package com.smartzone.technology.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import com.smartzone.technology.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Created by joe on 07/09/2018.
 */

public class CommonUtils {

    private static final String TAG = "CommonUtils";
    //    public static final String BASE_IMAGES_URL="https://api.glamour-plan.com/api/Upload/Images?";
    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    public static String parseDateToEEEMMMd(String dateStr, String inputFormatStr, String outputFormatStr, Locale locale) {
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputFormatStr, locale);
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputFormatStr);
        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(dateStr);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }
    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }


}

