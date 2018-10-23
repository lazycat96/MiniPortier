/*
 * Copyright (C) Nikolay Nesterov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.nnesterov.smiley;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.*;

import static android.app.Activity.RESULT_OK;

/**
 * Utils for saving files
 */
public final class FileUtils {
    public static String directory = "hi";
    public Uri selectedImage;
    public static Uri imageUri;
    private FileUtils() {
    }

    /**
     * Save binary data to public image dirr
     *
     * @param content data to save
     * @return newly created file
     * @throws IOException
     */
    public static File saveImage(byte[] content, String extension) throws IOException {
        // Create an image file name

        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES) + File.separator + directory);
               storageDir.mkdir();
        File image = new File(storageDir, timeStamp + "." + extension);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(image));
        imageUri = Uri.fromFile(image);


        FileOutputStream out = null;
        try {
            out = new FileOutputStream(image);
            out.write(content);
            out.close();
        } finally {
            if (out != null) {
                out.close();
            }
        }
        return image;

        }


}
