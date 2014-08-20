package com.example.cadastro.util;

import java.io.File;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;

public class BitmapHelper {
	public static Bitmap fixOrientation(Bitmap mBitmap) {
		if (mBitmap.getWidth() > mBitmap.getHeight()) {
			Matrix matrix = new Matrix();
			matrix.postRotate(90);
			mBitmap = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(),
					mBitmap.getHeight(), matrix, true);
		}

		return mBitmap;
	}

	public static Bitmap fixOrientation(Bitmap bitmap, String pathImage) {
		Matrix matrix = new Matrix();
		matrix.postRotate(getImageOrientation(pathImage));
		Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
				bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		
		return rotatedBitmap;
	}

	private static int getImageOrientation(String imagePath) {
		int rotate = 0;
		try {
			File imageFile = new File(imagePath);
			ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
			int orientation = exif.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);

			switch (orientation) {
			case ExifInterface.ORIENTATION_ROTATE_270:
				rotate = 270;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				rotate = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_90:
				rotate = 90;
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return rotate;
	}
}
