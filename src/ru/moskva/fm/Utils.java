package ru.moskva.fm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.nfc.Tag;
import android.os.Environment;
import android.util.Log;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static java.io.File.createTempFile;

public class Utils {

    final static String TAG = "Moskva.fm";

    public static boolean isSDCardAvailable() {
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return false;
        }
        return true;
    }

    public static File downloadFile(String url) throws IOException {

        final int BUFFER_SIZE = 32 * 1024;

        File tempFile = null;

        if (!isSDCardAvailable()) {
            throw new IOException("SD card not found");
        }

        BufferedInputStream in = null;
        RandomAccessFile raf = null;

        try {

            URL mURL = new URL(url);

            // open Http connection to URL
            HttpURLConnection conn = (HttpURLConnection) mURL.openConnection();
            // connect to server
            conn.connect();

            // Make sure the response code is in the 200 range.
            if (conn.getResponseCode() / 100 != 2) {
                throw new IOException("Bad http response code: " + conn.getResponseCode());
            }

            // get the input stream
            in = new BufferedInputStream(conn.getInputStream());

            // open the output file and seek to the start location
            tempFile = createTempFile("moskvafm", null);

            raf = new RandomAccessFile(tempFile, "rw");

            byte data[] = new byte[BUFFER_SIZE];

            int numRead;

            while (((numRead = in.read(data, 0, BUFFER_SIZE)) != -1)) {
                raf.write(data, 0, numRead);
            }

            Log.d(TAG, "Downloaded: " + url + ", saved: " + tempFile.getAbsolutePath());

        } catch (MalformedURLException e) {
            throw new IOException(e.getMessage());
        } finally {
            if (raf != null) {
                raf.close();
            }
            if (in != null) {
                in.close();
            }
        }

        return tempFile;
    }


}
