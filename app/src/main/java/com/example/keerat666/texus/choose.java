package com.example.keerat666.texus;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import junit.framework.Assert;


import org.junit.Test;

import java.net.URISyntaxException;

public class choose extends AppCompatActivity {
    private static final int FILE_SELECT_CODE = 0;
    String fpath="";
    public void uploadmethod(View view)
    {

        showFileChooser();

    }
@Test
    public void splitFile() throws IOException, NoSuchAlgorithmException {
        String filename1 = fpath;

        File file = new File(filename1);
   // Toast.makeText(this, "I came here "+file, Toast.LENGTH_SHORT).show();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        long filesize = file.length();
        long filesizeActual = 0L;
        int splitval = 7;
        int splitsize = (int)(filesize / splitval) + (int)(filesize % splitval);
        byte[] b = new byte[splitsize];
        //System.out.println(filename1 + "            " + filesize + " bytes");
       // Toast.makeText(this, "I came here "+filename1+" size: "+filesize, Toast.LENGTH_SHORT).show();

        try {
            Toast.makeText(this, "I came here before ", Toast.LENGTH_SHORT).show();
            fis = new FileInputStream(file);
            Toast.makeText(this, "I came here after ", Toast.LENGTH_SHORT).show();
            String name1 = filename1.replaceAll(".mp3", "");

            for (int j = 1; j <= splitval; j++) {
                String filecalled = name1 + "_split_" + j + ".mp3";
                fos = new FileOutputStream(filecalled);
                int i = fis.read(b);
                fos.write(b, 0, i);
                fos.close();
                fos = null;
                System.out.println(filecalled + "    " + i + " bytes");
                filesizeActual += i;
            }
            Assert.assertEquals(filesize, filesizeActual);
            //mergeFileParts(filename1, splitval);
            //check(filename1, mergeFile);
        }
        catch (Exception e)
        {
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
            Log.i("Error",e+"");
        }finally {
            if(fis != null) {
                fis.close();
            }
            if(fos != null) {
                fos.close();
            }
        }
    Toast.makeText(this, "I came here to display fis"+fis, Toast.LENGTH_SHORT).show();

}
    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    //Log.d(TAG, "File Uri: " + uri.toString());
                   // Toast.makeText(this, "File Uri:"+ uri.toString(), Toast.LENGTH_SHORT).show();
                    // Get the path
                    String path = null;
                    try {
                        path = getPath(this, uri);
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    //Log.d(TAG, "File Path: " + path);
                    Toast.makeText(this, "File Path:"+ path, Toast.LENGTH_SHORT).show();
                    fpath=path;
                    Toast.makeText(this, ""+fpath, Toast.LENGTH_SHORT).show();
                    // Get the file instance
                    // File file = new File(path);
                    // Initiate the upload
                    try {
                        splitFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public static String getPath(Context context, Uri uri) throws URISyntaxException {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = { "_data" };
            Cursor cursor = null;

            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                // Eat it
            }
        }
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

    }
}
