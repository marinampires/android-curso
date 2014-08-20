package com.example.cadastro.util;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.Toast;

import com.example.cadastro.model.Aluno;
import com.example.cadastro.view.ListaAlunosActivity;

public class ContextActionBar implements ActionMode.Callback {
	private Aluno alunoSelecionado;
	private ListaAlunosActivity activity;

	public ContextActionBar(Aluno alunoSelecionado, ListaAlunosActivity activity) {
		this.activity = activity;
		this.alunoSelecionado = alunoSelecionado;
	}

	@Override
	public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
		mode.finish();
		return false;
	}

	@Override
	public boolean onCreateActionMode(ActionMode mode, Menu menu) {
		
		MenuItem ligar = menu.add("Ligar");
		Intent intentLigar = new Intent(Intent.ACTION_CALL);
		intentLigar.setData(Uri.parse("tel:" + alunoSelecionado.getTelefone()));
		ligar.setIntent(intentLigar);

		MenuItem sms = menu.add("Enviar SMS");
		Intent intentSMS = new Intent(Intent.ACTION_VIEW);
		intentSMS.setData(Uri.parse("sms: " + alunoSelecionado.getTelefone()));
		intentSMS.putExtra("sms_body", "Mensagem do sms");
		sms.setIntent(intentSMS);

		MenuItem mapa = menu.add("Achar no Mapa");
		Intent intentMapa = new Intent(Intent.ACTION_VIEW);
		intentMapa.setData(Uri.parse("geo:0,0?q=" + alunoSelecionado.getEndereco()));
		mapa.setIntent(intentMapa);

		MenuItem site = menu.add("Navegar no site");
		Intent intentSite = new Intent(Intent.ACTION_VIEW);
		intentSite.setData(Uri.parse("http://" +alunoSelecionado.getSite()));
		site.setIntent(intentSite);

		MenuItem deletar = menu.add("Deletar");
		deletar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				new AlertDialog.Builder(activity)
						.setIcon(android.R.drawable.ic_dialog_alert)
						.setTitle("Deletar")
						.setMessage("Deseja mesmo deletar?")
						.setPositiveButton("Quero",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										alunoSelecionado.delete(activity);
										Toast.makeText(activity,
												"Aluno deletado",
												Toast.LENGTH_SHORT).show();
										activity.carregaLista();
									}
								}).setNegativeButton("Não", null).show();
				return false;
			}
		});

		return true;
	}

	@Override
	public void onDestroyActionMode(ActionMode mode) {
	}

	@Override
	public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
		return false;
	}

}
