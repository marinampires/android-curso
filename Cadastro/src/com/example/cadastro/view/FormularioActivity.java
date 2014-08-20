package com.example.cadastro.view;

import java.io.File;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cadastro.R;
import com.example.cadastro.helper.FormularioHelper;
import com.example.cadastro.model.Aluno;

public class FormularioActivity extends ActionBarActivity {
	private FormularioHelper helper;
	private String caminhoFoto;
	private static final int PICK_FROM_CAMERA = 101;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);
		EditText telefoneEdit = (EditText) findViewById(R.id.telefone);
		telefoneEdit
				.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

		this.helper = new FormularioHelper(this);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		Aluno aluno = (Aluno) getIntent().getSerializableExtra(
				"alunoSelecionado");

		Button botao = (Button) findViewById(R.id.botao);

		if (aluno != null) {
			botao.setText("Alterar");
			this.helper.setAluno(aluno);
		}

		botao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Aluno aluno = FormularioActivity.this.helper.getAluno();
				aluno.gravar(FormularioActivity.this);
				Toast.makeText(FormularioActivity.this,
						aluno.getNome() + " salvo com sucesso",
						Toast.LENGTH_SHORT).show();
				finish();
			}
		});

		ImageView foto = helper.getFoto();
		foto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				FormularioActivity.this.caminhoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
				Uri uriFoto = Uri.fromFile(new File(FormularioActivity.this.caminhoFoto));
				camera.putExtra(MediaStore.EXTRA_OUTPUT, uriFoto);
				startActivityForResult(camera, PICK_FROM_CAMERA);
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			super.onBackPressed();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != RESULT_OK) {
			return;
		}

		switch (requestCode) {
			case PICK_FROM_CAMERA: {
				this.helper.loadPhoto(this.caminhoFoto);
				break;
	
			}
		}
	}
}