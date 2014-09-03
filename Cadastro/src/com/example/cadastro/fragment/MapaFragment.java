package com.example.cadastro.fragment;

import java.util.List;

import com.example.cadastro.R;
import com.example.cadastro.model.Aluno;
import com.example.cadastro.util.Localizador;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaFragment extends SupportMapFragment{
	
	@Override
	public void onResume() {
		super.onResume();
		Localizador localizador = new Localizador(getActivity());
		LatLng local = localizador.getCoordenada("Rio de Janeiro, RJ, Brasil");
		reposicionaMarkers(local);
	}
	
	public void centralizaNo(LatLng local){
		if(local != null){
			reposicionaMarkers(local);
			
			GoogleMap mapa = getMap();
			mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(local, 17));
		}
	}

	private void reposicionaMarkers(LatLng local){
		Localizador localizador = new Localizador(getActivity());
		GoogleMap mapa = getMap();
		mapa.clear();
		mapa.addMarker( new MarkerOptions().title("Eu").position(local).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_no_image)));
		
		List<Aluno> alunos = Aluno.getAlunos(getActivity());
		for(Aluno aluno : alunos){
			local = localizador.getCoordenada(aluno.getEndereco());
			if(local != null){
				BitmapDescriptor foto = null;
				if(aluno.getCaminhoFoto() != null){
					foto = BitmapDescriptorFactory.fromPath(aluno.getCaminhoFoto());
				}else{
					foto = BitmapDescriptorFactory.fromResource(R.drawable.ic_no_image);
				}
				mapa.addMarker(new MarkerOptions().title(aluno.getNome()).position(local).icon(foto));
			}
		}
	}
}
