package ru.digios.sms2net.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SmsMonitor extends BroadcastReceiver {
    private static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("INFO.SmsMonitor", intent.getAction());
        if (intent != null && intent.getAction() != null && ACTION.compareToIgnoreCase(intent.getAction()) == 0) {
        //---получить входящее SMS сообщение---
        Bundle bundle= intent.getExtras();
        SmsMessage[] msgs=null;
        String str="";
        if(bundle!=null)
        {
//---извлечь полученное SMS ---
            Object[] pdus=(Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            for(int i=0; i<msgs.length; i++){
                msgs[i]= SmsMessage.createFromPdu((byte[])pdus[i]);
                str+="SMS from "+ msgs[i].getOriginatingAddress();
                str+=" :";
                str+= msgs[i].getMessageBody().toString();
                str+="\n";
            }
//---Показать новое SMS сообщение---
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        }
    }


//        if (intent != null && intent.getAction() != null &&
//                ACTION.compareToIgnoreCase(intent.getAction()) == 0) {
//            Object[] pduArray = (Object[]) intent.getExtras().get("pdus");
//            SmsMessage[] messages = new SmsMessage[pduArray.length];
//            for (int i = 0; i < pduArray.length; i++) {
//                messages[i] = SmsMessage.createFromPdu((byte[]) pduArray[i]);
//            }
//            String sms_from = messages[0].getDisplayOriginatingAddress();
//            if (sms_from.equalsIgnoreCase("RM FIGHT")) {
//                StringBuilder bodyText = new StringBuilder();
//                for (int i = 0; i < messages.length; i++) {
//                    bodyText.append(messages[i].getMessageBody());
//                }
//                String body = bodyText.toString();
//                Log.i("INFO.SmsMonitor", body);
//
//                /*Intent mIntent = new Intent(context, SmsService.class);
//                mIntent.putExtra("sms_body", body);
//                context.startService(mIntent);
//
//                abortBroadcast();*/
//            }
//        }
    }
}
