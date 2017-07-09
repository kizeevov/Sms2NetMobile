package ru.digios.sms2net.core;


import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.text.SimpleDateFormat;

public class SmsStorage {
    private Context context;

    public SmsStorage(Context context) {
        this.context = context;
    }

    public void getSms() {
        if (context == null)
            return;
        /*Uri uriSms = Uri.parse("content://sms/");
        Cursor cur = context.getContentResolver().query(uriSms, null,null,null,null);
        startManagingCursor(cur);
        SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");

        StringBuilder bodyText = new StringBuilder();
        if (cur.getCount() > 0){
            while (cur.moveToNext()){
                bodyText.append("\n" + format1.format(cur.getLong(4)) + " " + cur.getString(2) + ": " + cur.getString(12) );
                bodyText.append("\n");
            }
        }*/

        Cursor cursor = context.getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);

        if (cursor.moveToFirst()) { // must check the result to prevent exception
            SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
            do {
                StringBuilder msgData = new StringBuilder();
                /*for(int idx=0;idx<cursor.getColumnCount();idx++)
                {
                    msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx);
                }*/
                msgData.append("\n" + format1.format(cursor.getLong(4)) + " " + cursor.getString(2) + ": " + cursor.getString(12) );

                Log.i("INFO.getSms", msgData.toString());
            } while (cursor.moveToNext());
        } else {
            // empty box, no SMS
        }
    }
}
