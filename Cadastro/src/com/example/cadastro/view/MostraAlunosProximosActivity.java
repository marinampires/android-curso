package com.example.cadastro.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.example.cadastro.R;
import com.example.cadastro.fragment.MapaFragment;

public class MostraAlunosProximosActivity extends FragmentActivity {
	
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.mapa_layout);
		
		MapaFragment mapa = new MapaFragment();
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.mapa, mapa);
		transaction.commit();
	}
	
}
