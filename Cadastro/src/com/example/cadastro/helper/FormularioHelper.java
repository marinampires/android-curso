package com.example.cadastro.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.example.cadastro.R;
import com.example.cadastro.model.Aluno;
import com.example.cadastro.util.BitmapHelper;
import com.example.cadastro.view.FormularioActivity;

public class FormularioHelper {
	private Long id;
	private String caminhoFoto;
	private EditText nome;
	private EditText endereco;
	private EditText site;
	private EditText telefone;
	private ImageView foto;
	private RatingBar nota;
	
	public FormularioHelper(FormularioActivity activity) {
		nome = (EditText) activity.findViewById(R.id.nome);
		endereco = (EditText) activity.findViewById(R.id.endereco);
		site = (EditText) activity.findViewById(R.id.site);
		telefone = (EditText) activity.findViewById(R.id.telefone);
		foto = (ImageView) activity.findViewById(R.id.foto);
		nota = (RatingBar) activity.findViewById(R.id.nota);
		id = null;
	}
	
	
	public Aluno getAluno(){
		Aluno aluno = new Aluno();
		aluno.setNome(getStringValue(nome));
		aluno.setEndereco(getStringValue(endereco));
		aluno.setSite(getStringValue(site));
		aluno.setTelefone(getStringValue(telefone));
		aluno.setCaminhoFoto(this.caminhoFoto);
		aluno.setNota(Double.valueOf( nota.getProgress()));
		aluno.setId(id);
		
		return aluno;
	}
	
	public void setAluno(Aluno aluno){
		this.id = aluno.getId();
		setStringValue(nome, aluno.getNome());
		setStringValue(endereco, aluno.getEndereco());
		setStringValue(site, aluno.getSite());
		setStringValue(telefone, aluno.getTelefone());
		if(aluno.getCaminhoFoto() != null){
			loadPhoto(aluno.getCaminhoFoto());
		}
		
		nota.setProgress(aluno.getNota().intValue());
	}
	
	private String getStringValue(EditText editText){
		return editText.getText().toString();
	}
	
	private void setStringValue(EditText editText, String value){
		editText.setText(value);
	}
	
	public void loadPhoto(String caminhoFoto){
		this.caminhoFoto = caminhoFoto;
		Bitmap original = BitmapFactory.decodeFile(caminhoFoto);
		int largura = 250;
		int altura = (original.getHeight() * largura)/original.getWidth();
		Bitmap reduzido = Bitmap.createScaledBitmap(original, largura, altura, true);
		reduzido = BitmapHelper.fixOrientation(reduzido);
		this.foto.setImageBitmap(reduzido);
	}
	
	public ImageView getFoto(){
		return this.foto;
	}
}
