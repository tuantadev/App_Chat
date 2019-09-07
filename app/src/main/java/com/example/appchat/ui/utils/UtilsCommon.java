package com.example.appchat.ui.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;

public class UtilsCommon {
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource( String path,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    public static String getFileImageToUpload(String path){
        Bitmap bm = decodeSampledBitmapFromResource(path, 50, 50);
        String pathNew = Environment.getExternalStorageDirectory().getPath() + "/AppChat";
        new File(pathNew).mkdir();
        pathNew = pathNew +"/"+new File(path).getName();
        try {
            bm.compress((path.contains(".png") ||path.contains(".PNG")) ? Bitmap.CompressFormat.PNG :Bitmap.CompressFormat.JPEG , 100, new FileOutputStream(new File(pathNew)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bm.recycle();
        return pathNew;
    }

}
