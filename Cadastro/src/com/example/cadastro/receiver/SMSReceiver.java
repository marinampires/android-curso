package com.example.cadastro.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.example.cadastro.R;
import com.example.cadastro.model.Aluno;

public class SMSReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		Object messages [] = (Object []) bundle.get("pdus");
		SmsMessage smsMessage[] = new SmsMessage[messages.length];
		
		for(int n = 0; n < messages.length; n ++){
			smsMessage[n] = SmsMessage.createFromPdu((byte[]) messages[n]);
		}
		
		if(Aluno.isAluno(context, smsMessage[0].getDisplayOriginatingAddress())){
			Toast.makeText(context, "SMS do Aluno: " + smsMessage[0].getMessageBody(), Toast.LENGTH_LONG).show();
			MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.msg);
			mediaPlayer.start();
		}
		
	}

}
