package com.example.cadastro.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cadastro.model.Aluno;

public class AlunoDAO extends SQLiteOpenHelper {
	private static final String DATABASE = "CadastroCaelum";
	private static final String TABELA = "Alunos";
	private static final CursorFactory FACTORY = null;
	private static final int VERSION = 1;
	
	public AlunoDAO(Context context) {
		super(context, DATABASE, FACTORY, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		String sqlCreate = "CREATE TABLE " + TABELA + " (id INTEGER PRIMARY KEY, nome TEXT UNIQUE NOT NULL," +
				"telefone TEXT, endereco TEXT, site TEXT, nota REAL, caminhoFoto TEXT);";
		database.execSQL(sqlCreate);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int versaoAnterior, int versaoAtual) {
		String sqlCreate = "DROP TABLE IF EXISTS " + TABELA + ";";
		database.execSQL(sqlCreate);
		onCreate(database);
	}

	
	public void inserir(Aluno aluno){
		ContentValues values = preencheCampos(aluno);
		getWritableDatabase().insert(TABELA, null, values);
	}
	
	public void alterar(Aluno aluno){
		Long id = aluno.getId();
		ContentValues values = preencheCampos(aluno);
		getWritableDatabase().update(TABELA, values, "id=?", new String[] {id.toString()});
	}
	
	
	private ContentValues preencheCampos(Aluno aluno){
		ContentValues values = new ContentValues();
		
		values.put("nome", aluno.getNome());
		values.put("endereco", aluno.getEndereco());
		values.put("site", aluno.getSite());
		values.put("telefone", aluno.getTelefone());
		values.put("nota", aluno.getNota());
		values.put("caminhoFoto", aluno.getCaminhoFoto());

		return values;
	}
	
	public List<Aluno> list(){
		String sqlList = "SELECT * FROM Alunos; ";
		Cursor cursor = getReadableDatabase().rawQuery(sqlList, null);
		List<Aluno> alunos = new ArrayList<Aluno>();
		
		try{
			while(cursor.moveToNext()){
				Aluno aluno = new Aluno();
				
				aluno.setId(cursor.getLong(cursor.getColumnIndex("id")));
				aluno.setCaminhoFoto(cursor.getString(cursor.getColumnIndex("caminhoFoto")));
				aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
				aluno.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
				aluno.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
				aluno.setSite(cursor.getString(cursor.getColumnIndex("site")));
				aluno.setNota(cursor.getDouble(cursor.getColumnIndex("nota")));
				
				alunos.add(aluno);
			}
		}finally{
			cursor.close();
		}
		
		return alunos;
	}
	
	public Aluno findBy(Long id){
		String [] args = {id.toString()};
		String sqlFind = "SELECT * FROM Alunos WHERE id = ?; ";
		Cursor cursor = getReadableDatabase().rawQuery(sqlFind, args);
		
		Aluno aluno = new Aluno();
		
		try{
			if(cursor.moveToFirst()){
				aluno.setId(cursor.getLong(cursor.getColumnIndex("id")));
				aluno.setCaminhoFoto(cursor.getString(cursor.getColumnIndex("caminhoFoto")));
				aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
				aluno.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
				aluno.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
				aluno.setSite(cursor.getString(cursor.getColumnIndex("site")));
				aluno.setNota(cursor.getDouble(cursor.getColumnIndex("nota")));
			}			
			
		}finally{
			cursor.close();
		}
		
		return aluno;
	}
	
	public void delete(Long id){
		String [] args = {id.toString()};
		getWritableDatabase().delete(TABELA, "id=?", args);
	}

	public boolean isAluno(String telefone) {
		String [] args = {telefone};
		String sqlFind = "SELECT 1 FROM Alunos WHERE telefone = ?; ";
		Cursor cursor = getReadableDatabase().rawQuery(sqlFind, args);
		
		boolean isAluno = false;
		
		try{
			isAluno = cursor.moveToFirst();
		}finally{
			cursor.close();
		}
		
		return isAluno;
	}
}
