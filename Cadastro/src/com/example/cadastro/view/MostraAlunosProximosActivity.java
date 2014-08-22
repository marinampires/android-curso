package com.example.cadastro.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.example.cadastro.R;
import com.example.cadastro.fragment.MapaFragment;
import com.example.cadastro.listener.AtualizadorLocalizacaoListener;

public class MostraAlunosProximosActivity extends FragmentActivity {
	
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.mapa_layout);
		
		MapaFragment mapa = new MapaFragment();
		//criando o listner
		new AtualizadorLocalizacaoListener(this, mapa);
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.mapa, mapa);
		transaction.commit();
	}
	
}
