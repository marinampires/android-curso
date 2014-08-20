package com.example.cadastro.listener;

import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.util.Log;
import android.widget.EditText;

public class BrazilPhoneWatcher extends PhoneNumberFormattingTextWatcher {
	private EditText campo;
	
	public BrazilPhoneWatcher(EditText campo){
		this.campo = campo;
	}
	
	@Override
	public synchronized void afterTextChanged(Editable s) {
		String tel = s.toString();
		if(tel.length() <= 14)
			super.afterTextChanged(s);
		else{
			int indexTraco = tel.indexOf("-");
			char traco = tel.charAt(indexTraco);
			char digito = tel.charAt(indexTraco+1);
			String telFormatado = "";
			char [] caracteres = tel.toCharArray();
			for(int c = 0; c < caracteres.length; c ++){
				if(c != indexTraco && c != indexTraco + 1)
					telFormatado += String.valueOf(caracteres[c]);
				else{
					if(c == indexTraco)
					    telFormatado += String.valueOf(digito) + String.valueOf(traco);
				}
			}
			Log.i("Telefone", telFormatado);
			campo.setText(telFormatado);
		}
			
	}

	
}
