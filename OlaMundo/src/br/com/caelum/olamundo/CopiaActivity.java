package br.com.caelum.olamundo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CopiaActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_copia);
	}
	
	public void copiar(View view){
		final EditText textoEditado = (EditText) findViewById(R.id.texto_editado);
		final TextView texto = (TextView) findViewById(R.id.texto_copiado);
		texto.setText("Você digitou: " + textoEditado.getText().toString());
	}
}
