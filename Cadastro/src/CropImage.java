

import java.io.File;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cadastro.R;

public class CropImage  extends Activity {
	 private ImageView imVCature_pic;
	 private Button btnCapture;
	 private String caminhoFoto;
	 
	private static final int PICK_FROM_CAMERA = 101;
	private static final int CROP_IMAGE = 102;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.crop);
		caminhoFoto  = (String) getIntent().getSerializableExtra("caminhoFoto");
		initializeControls();
	}

	private void initializeControls() {
		imVCature_pic = (ImageView) findViewById(R.id.capturaImagem);
		btnCapture = (Button) findViewById(R.id.capturaBotao);
		btnCapture.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				/*
				 * create an instance of intent pass action
				 * android.media.action.IMAGE_CAPTURE as argument to launch
				 * camera
				 */
				Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
				/* put uri as extra in intent object */
				Uri uriFoto = Uri.fromFile(new File(caminhoFoto));
				intent.putExtra(MediaStore.EXTRA_OUTPUT, uriFoto);
				/*
				 * start activity for result pass intent as argument and request
				 * code
				 */
				startActivityForResult(intent, PICK_FROM_CAMERA);
			}
		});

	}

	 @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// if request code is same we pass as argument in startActivityForResult
		if (resultCode != RESULT_OK) {
			return;
		}

		switch (requestCode) {
			case PICK_FROM_CAMERA:
				// create instance of File with same name we created before to get
				// image from storage
				File file = new File(Environment.getExternalStorageDirectory() + File.separator + "img.jpg");
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
				Bitmap thePic = extras.getParcelable("data");
				// set image bitmap to image view
				imVCature_pic.setImageBitmap(thePic);
				break;
		}
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

