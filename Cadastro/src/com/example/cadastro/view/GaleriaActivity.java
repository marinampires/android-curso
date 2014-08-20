package com.example.cadastro.view;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.example.cadastro.R;
import com.example.cadastro.adapter.GaleriaAlunoAdapter;
import com.example.cadastro.model.Aluno;

public class GaleriaActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.galeria);

		ViewPager gallery = (ViewPager)findViewById(R.id.gallery);
		List<Aluno> alunos = Aluno.getAlunos(this);
		GaleriaAlunoAdapter adapter = new GaleriaAlunoAdapter(alunos, this);
		
		gallery.setAdapter(adapter);
	}
}
