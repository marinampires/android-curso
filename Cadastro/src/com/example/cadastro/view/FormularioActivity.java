package com.example.cadastro.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
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
	private static final int IMAGE_PICKER_SELECT = 102;
	private static final int CROP_IMAGE = 103;
	
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
//				Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//				FormularioActivity.this.caminhoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
//				Uri uriFoto = Uri.fromFile(new File(FormularioActivity.this.caminhoFoto));
//				camera.putExtra(MediaStore.EXTRA_OUTPUT, uriFoto);
//				startActivityForResult(camera, PICK_FROM_CAMERA);
				
				Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI); 
				startActivityForResult(i, IMAGE_PICKER_SELECT);
//				
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
//			case PICK_FROM_CAMERA: 
//				this.helper.loadPhoto(this.caminhoFoto, true);
//				break;
//				
			case IMAGE_PICKER_SELECT:
				String pathFoto = getBitmapFromCameraData(data, this); 
				//this.helper.loadPhoto(pathFoto, false); 
				File file3 = new File(pathFoto);
				cropCapturedImage(Uri.fromFile(file3));
				break;
				
			case PICK_FROM_CAMERA:
				// create instance of File with same name we created before to get
				// image from storage
				File file = new File(this.caminhoFoto);
				// Crop the captured image using an other intent
				try {
					/* the user's device may not support cropping */
					cropCapturedImage(Uri.fromFile(file));
				} catch (ActivityNotFoundException aNFE) {
					// display an error message if user device doesn't support
					String errorMessage = "Sorry - your device doesn't support the crop action!";
					Toast toast = Toast.makeText(this, errorMessage,Toast.LENGTH_SHORT);
					toast.show();
				}
				break;
			case CROP_IMAGE:
				// Create an instance of bundle and get the returned data
				Bundle extras = data.getExtras();
				// get the cropped bitmap from extras
				Bitmap photo = extras.getParcelable("data");
				// set image bitmap to image view
				//this.helper.getFoto().setImageBitmap(thePic);
				
				File file2 = new File(caminhoFoto);
				try {
					file2.createNewFile();
	                FileOutputStream fos = new FileOutputStream(file2);
	                photo.compress(Bitmap.CompressFormat.PNG, 95, fos);
	                this.helper.loadPhoto(caminhoFoto, false); 
				} catch (IOException e) {
					// TODO Auto-generated catch block
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
	
	// create helping method cropCapturedImage(Uri picUri)
	public void cropCapturedImage(Uri picUri) {
		// call the standard crop action intent
		Intent cropIntent = new Intent("com.android.camera.action.CROP");
		// indicate image type and Uri of image
		cropIntent.setDataAndType(picUri, "image/*");
		// set crop properties
		cropIntent.putExtra("crop", "true");
		// indicate aspect of desired crop
		cropIntent.putExtra("aspectX", 1);
		cropIntent.putExtra("aspectY", 1);
		// indicate output X and Y
		cropIntent.putExtra("outputX", 256);
		cropIntent.putExtra("outputY", 256);
		// retrieve data on return
		cropIntent.putExtra("return-data", true);
		// start the activity - we handle returning in onActivityResult
		startActivityForResult(cropIntent, CROP_IMAGE);
	}	
}