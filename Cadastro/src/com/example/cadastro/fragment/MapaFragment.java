package com.example.cadastro.fragment;

import java.util.List;

import android.content.Context;

import com.example.cadastro.model.Aluno;
import com.example.cadastro.util.Localizador;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaFragment extends SupportMapFragment{
	
	@Override
	public void onResume() {
		super.onResume();
		GoogleMap mapa = getMap();
		Context context = getActivity();
		Localizador localizador = new Localizador(context);
		LatLng local = localizador.getCoordenada("Rua ouvidor 50, rio de janeiro");
		if(local != null){
			mapa.addMarker(new MarkerOptions().title("Caelum").snippet("Ensino e inovação").position(local));
			centralizaNo(local);
		}
		
		List<Aluno> alunos = Aluno.getAlunos(context);
		for(Aluno aluno : alunos){
			local = localizador.getCoordenada(aluno.getEndereco());
			if(local != null){
				mapa.addMarker(new MarkerOptions().title(aluno.getNome()).position(local));
			}
		}
	}
	
	public void centralizaNo(LatLng local){
		if(local != null){
			GoogleMap mapa = getMap();
			mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(local, 17));
		}
	}

}
