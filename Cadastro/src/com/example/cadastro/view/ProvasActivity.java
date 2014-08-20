package com.example.cadastro.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.example.cadastro.R;
import com.example.cadastro.fragment.DetalhesProvaFragment;
import com.example.cadastro.fragment.ListaProvasFragment;
import com.example.cadastro.model.Prova;

public class ProvasActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.provas);
		
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		
		if(isTablet()){
			transaction.replace(R.id.provas_lista, new ListaProvasFragment()).
			replace(R.id.provas_view, new DetalhesProvaFragment());
		}else{
			transaction.replace(R.id.provas_view, new ListaProvasFragment());
		}
		transaction.commit();
	}

	private boolean isTablet(){
		return getResources().getBoolean(R.bool.isTablet);
	}

	public void setProva(Prova prova){
		Bundle args = new Bundle();
		args.putSerializable("prova", prova);
		
		DetalhesProvaFragment detalhesProvaFragment = new DetalhesProvaFragment();
		detalhesProvaFragment.setArguments(args);
		
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.provas_view, detalhesProvaFragment);
		
		if(!isTablet()){
			transaction.addToBackStack(null);
		}
		
		transaction.commit();
	}
}
