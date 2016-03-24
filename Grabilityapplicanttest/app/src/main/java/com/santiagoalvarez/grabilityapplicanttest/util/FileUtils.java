package com.santiagoalvarez.grabilityapplicanttest.util;

import android.content.Context;
import android.net.Uri;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileUtils {

    private static final String TAG = FileUtils.class.getSimpleName();

    private FileUtils() {
        throw new UnsupportedOperationException("utility class. Please don't instantiate this class");
    }

    public static void saveObjectToDisk(Context ctx, String fileName, Object object) throws IOException {
        FileOutputStream fileOutputStream = ctx.openFileOutput(fileName, Context.MODE_PRIVATE);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public static Object getObjectFromDisk(Context ctx, String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = null;
        Object object = null;
        fis = ctx.openFileInput(fileName);
        ObjectInputStream is = new ObjectInputStream(fis);
        object = is.readObject();
        is.close();
        return object;
    }

    public static void deleteObjectToDisk(Context ctx, String fileName) throws IOException {
        FileOutputStream fileOutputStream = ctx.openFileOutput(fileName, Context.MODE_PRIVATE);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.reset();
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public static Uri saveImageTemp(Context context, String filename, byte[] bytes) throws Exception {
        File file = File.createTempFile(filename, ".jpg", context.getCacheDir());
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
        bos.write(bytes);
        bos.flush();
        bos.close();
        return getUriForFile(file);
    }

    private static Uri getUriForFile(File file) {
        return Uri.parse("content://" + "com.santiagoalvarez.grabilityapplicanttest.media/cache_images/" + file.getName());
    }


    public static void trimCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // The directory is now empty so delete it
        return dir.delete();
    }
}
