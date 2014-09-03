package com.example.cadastro.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cadastro.R;
import com.example.cadastro.adapter.PickPhotoAdapter;
import com.example.cadastro.helper.FormularioHelper;
import com.example.cadastro.model.Aluno;

public class FormularioActivity extends ActionBarActivity {
	private FormularioHelper helper;
	private String caminhoFoto;
	private static final int PICK_FROM_CAMERA = 101;
	private static final int IMAGE_PICKER_SELECT = 102;
	private static final int CROP_IMAGE = 103;
	private Dialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);
		EditText telefoneEdit = (EditText) findViewById(R.id.telefone);
		telefoneEdit.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

		this.helper = new FormularioHelper(this);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		Aluno aluno = (Aluno) getIntent().getSerializableExtra("alunoSelecionado");

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
				Toast.makeText(FormularioActivity.this,aluno.getNome() + " salvo com sucesso",Toast.LENGTH_SHORT).show();
				finish();
			}
		});

		ImageView foto = helper.getFoto();
		foto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog = new Dialog(FormularioActivity.this);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				WindowManager.LayoutParams WMLP = dialog.getWindow().getAttributes();
				WMLP.gravity = Gravity.CENTER;
				dialog.getWindow().setAttributes(WMLP);
				dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
				dialog.setCanceledOnTouchOutside(true);
				dialog.setContentView(R.layout.about_dialog);
				dialog.setCancelable(true);
				ListView lv = (ListView) dialog.findViewById(R.id.listView1);

				PickPhotoAdapter adapter = new PickPhotoAdapter(FormularioActivity.this);
				lv.setAdapter(adapter);
				lv.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id) {
						FormularioActivity.this.caminhoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
						if(posicao == 0){
							Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
							Uri uriFoto = Uri.fromFile(new File(FormularioActivity.this.caminhoFoto));
							camera.putExtra(MediaStore.EXTRA_OUTPUT, uriFoto);
							startActivityForResult(camera, PICK_FROM_CAMERA);
						}else{
							Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI); 
							startActivityForResult(i, IMAGE_PICKER_SELECT);	
						}
					}
				});
				dialog.show();
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

		File file = null;
		
		switch (requestCode) {
			case IMAGE_PICKER_SELECT:
				try {
					File fotoGaleria = new File(getBitmapFromCameraData(data, this));
					Log.i("Galeria", fotoGaleria.getAbsolutePath());
					Log.i("caminho", this.caminhoFoto);
					file = new File(this.caminhoFoto);
					InputStream in = new FileInputStream(fotoGaleria);
	                OutputStream out = new FileOutputStream(file);

	                byte[] buf = new byte[1024];
	                int len;
	                while ((len = in.read(buf)) > 0) {
	                    out.write(buf, 0, len);
	                }
	                in.close();
	                out.close();
					
					cropCapturedImage(Uri.fromFile(file));
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
				
			case PICK_FROM_CAMERA:
				file = new File(this.caminhoFoto);
				try {
					cropCapturedImage(Uri.fromFile(file));
				} catch (ActivityNotFoundException aNFE) {
					String errorMessage = "Sorry - your device doesn't support the crop action!";
					Toast toast = Toast.makeText(this, errorMessage,Toast.LENGTH_SHORT);
					toast.show();
				}
				break;
			case CROP_IMAGE:
				Bundle extras = data.getExtras();
				Bitmap photo = extras.getParcelable("data");
				
				file = new File(caminhoFoto);
				try {
					file.createNewFile();
	                FileOutputStream fos = new FileOutputStream(file);
	                photo.compress(Bitmap.CompressFormat.PNG, 95, fos);
	                this.helper.loadPhoto(caminhoFoto, false); 
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
		}
	}
	
	public static String getBitmapFromCameraData(Intent data, Context context){ 
		Uri selectedImage = data.getData(); 
		String[] filePathColumn = { MediaStore.Images.Media.DATA }; 
		Cursor cursor = context.getContentResolver().query(selectedImage,filePathColumn, null, null, null); 
		cursor.moveToFirst(); 
		int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
		String picturePath = cursor.getString(columnIndex); 
		cursor.close(); 
		return picturePath; 
	}
	
	public void cropCapturedImage(Uri picUri) {
		this.dialog.dismiss();
		
		Intent cropIntent = new Intent("com.android.camera.action.CROP");
		cropIntent.setDataAndType(picUri, "image/*");
		cropIntent.putExtra("crop", "true");
		cropIntent.putExtra("aspectX", 1);
		cropIntent.putExtra("aspectY", 1);
		cropIntent.putExtra("outputX", 256);
		cropIntent.putExtra("outputY", 256);
		cropIntent.putExtra("return-data", true);
		startActivityForResult(cropIntent, CROP_IMAGE);
	}	
}