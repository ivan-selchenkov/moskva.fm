package ru.moskva.fm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Environment;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static java.io.File.createTempFile;

public class Utils {

//    public static String getNextTrackUrl(String paramString)
//      {
//        String str1 = paramString;
//        String[] arrayOfString = paramString.split("/");
//        String str2 = arrayOfString[4];
//        String str3 = arrayOfString[6];
//        String str4 = arrayOfString[7];
//        String str5 = arrayOfString[8];
//        String str6 = arrayOfString[9].split("\\.")[0];
//        String str7 = str6.substring(0, 2);
//        String str8 = str6.substring(2, 4);
//        try
//        {
//          DateFormat localDateFormat1 = DF;
//          String str9 = str3 + "/" + str4 + "/" + str5 + "/" + str7 + str8;
//          Date localDate1 = localDateFormat1.parse(str9);
//          Calendar localCalendar = Calendar.getInstance();
//          localCalendar.setTime(localDate1);
//          localCalendar.add(12, 1);
//          DateFormat localDateFormat2 = DF;
//          Date localDate2 = localCalendar.getTime();
//          String str10 = localDateFormat2.format(localDate2);
//          String str11 = "http://fresh.moskva.fm/files/" + str2 + "/mp4/" + str10 + ".mp4";
//          str12 = str11;
//          return str12;
//        }
//        catch (ParseException localParseException)
//        {
//          while (true)
//          {
//            localParseException.printStackTrace();
//            String str13 = str1;
//            String str12 = str1;
//          }
//        }
//      }

    public static Track getFirstTranslationTrack(Channel ch) {
        String id = ch.getChannelID();

        long l = System.currentTimeMillis() - 300000L;

        l = l - l % 60000L;

        Date localDate = new Date(l);

        Track track = new Track();

        track.url = "http://fresh.moskva.fm/files/" + id + "/mp4/" + getFormattedDate(localDate) + ".mp4";

        track.timestamp = localDate;

        return track;
    }

    private static String getFormattedDate(Date in) {

        TimeZone localTimeZone = TimeZone.getTimeZone("Europe/Moscow");

        GregorianCalendar localGregorianCalendar = new GregorianCalendar(localTimeZone);

        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/HHmm");

        localSimpleDateFormat.setCalendar(localGregorianCalendar);

        return localSimpleDateFormat.format(in);

    }

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
