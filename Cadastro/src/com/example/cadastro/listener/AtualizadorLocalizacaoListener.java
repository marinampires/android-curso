package com.example.cadastro.listener;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;

import com.example.cadastro.fragment.MapaFragment;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.model.LatLng;

public class AtualizadorLocalizacaoListener implements LocationListener, GooglePlayServicesClient.ConnectionCallbacks{

	private LocationClient client;
	private MapaFragment mapa;
	
	public AtualizadorLocalizacaoListener(Context context, MapaFragment mapa) {
		this.client = new LocationClient(context, this, null);
		this.client.connect();
		this.mapa = mapa;
	}

	@Override
	public void onLocationChanged(Location location) {
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
		
		LatLng local = new LatLng(latitude, longitude);
		this.mapa.centralizaNo(local);
	}

	@Override
	public void onConnected(Bundle connectionHint) {
		LocationRequest request = LocationRequest.create();
		request.setInterval(2000);
		request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
		request.setSmallestDisplacement(50);
		
		this.client.requestLocationUpdates(request, this);
	}

	@Override
	public void onDisconnected() {	}

	public void cancela() {
		this.client.removeLocationUpdates(this);
		this.client.disconnect();
	}


}
