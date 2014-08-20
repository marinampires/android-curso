package com.example.cadastro.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cadastro.R;
import com.example.cadastro.model.Aluno;
import com.example.cadastro.util.BitmapHelper;
import com.example.cadastro.view.ListaAlunosActivity;

@SuppressLint({ "ViewHolder", "InflateParams" })
public class ListaAlunoAdapter extends BaseAdapter{
	private List<Aluno> alunos;
	private ListaAlunosActivity activity;

	
	public ListaAlunoAdapter(List<Aluno> alunos, ListaAlunosActivity activity) {
		this.alunos = alunos;
		this.activity = activity;
	}

	@Override
	public int getCount() {
		return alunos.size();
	}

	@Override
	public Object getItem(int posicao) {
		return alunos.get(posicao);
	}

	@Override
	public long getItemId(int posicao) {
		return ((Aluno)getItem(posicao)).getId();
	}

	@Override
	public View getView(int posicao, View convertView, ViewGroup parent) {
		View view = activity.getLayoutInflater().inflate(R.layout.item, null);
		
		if(posicao % 2 == 0){
			view.setBackgroundColor(activity.getResources().getColor(R.color.linha_par));
		}
		
		Aluno aluno = (Aluno) getItem(posicao);
		ImageView foto = (ImageView)view.findViewById(R.id.foto);
		Bitmap bm;
		String pathImage = null;
		if(aluno.getCaminhoFoto() != null){
			bm = BitmapFactory.decodeFile(aluno.getCaminhoFoto());
			pathImage = aluno.getCaminhoFoto();
		}else{
			bm = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_no_image);
		}
		
		bm = Bitmap.createScaledBitmap(bm, 101, 100, true);
		
		if(pathImage != null)
			bm = BitmapHelper.fixOrientation(bm, pathImage);
		
		foto.setImageBitmap(bm);
		
		TextView nome = (TextView) view.findViewById(R.id.nome);
		nome.setText(aluno.getNome());

		TextView telefone = (TextView) view.findViewById(R.id.telefone);
		if(telefone != null)
			telefone.setText(aluno.getTelefone());
		
		TextView site = (TextView) view.findViewById(R.id.site);
		if(site != null)
			site.setText(aluno.getSite());
		
		return view;
	}

}
