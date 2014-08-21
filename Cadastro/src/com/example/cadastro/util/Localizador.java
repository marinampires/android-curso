package com.example.cadastro.util;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

public class Localizador {
	private Geocoder geo;
	
	public Localizador(Context ct){
		geo = new Geocoder(ct);
	}
	
	public LatLng getCoordenada(String endereco){
		try{
			List<Address> enderecos = geo.getFromLocationName(endereco, 1);
			if(!enderecos.isEmpty()){
				Address address = enderecos.get(0);
				return new LatLng(address.getLatitude(), address.getLongitude());
			}
		}catch(IOException e){
			
		}
		return null;
	}
}
