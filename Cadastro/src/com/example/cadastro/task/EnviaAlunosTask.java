package com.example.cadastro.task;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.cadastro.converter.AlunoConverter;
import com.example.cadastro.model.Aluno;
import com.example.cadastro.support.WebClient;

public class EnviaAlunosTask extends AsyncTask<Object, Object, String>{
	private Activity activity;
	private ProgressDialog dialog;
	private final static String ENDERECO = "http://www.caelum.com.br/mobile";
	
	public EnviaAlunosTask(Activity activity){
		this.activity = activity;
	}
	
	@Override
	protected String doInBackground(Object... params) {
		List<Aluno> alunos = Aluno.getAlunos(activity);
		String json = AlunoConverter.toJSON(alunos);
		
		WebClient webClient = new WebClient();
		
		return webClient.post(json, ENDERECO);
	}

	@Override
	protected void onPostExecute(String result) {
		dialog.dismiss();
		Toast.makeText(activity, result, Toast.LENGTH_LONG).show();
	}

	@Override
	protected void onPreExecute() {
		dialog = ProgressDialog.show(this.activity,"Aguarde...", "Enviando dados para o servidor", true, true);
	}
	
	
}
