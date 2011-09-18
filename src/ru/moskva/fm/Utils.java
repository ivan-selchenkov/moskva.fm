package ru.moskva.fm;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

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

    public static String getNowUrl(Channel ch) {
        String id = ch.getChannelID();

        TimeZone localTimeZone = TimeZone.getTimeZone("Europe/Moscow");

        GregorianCalendar localGregorianCalendar = new GregorianCalendar(localTimeZone);

        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/HHmm");

        localSimpleDateFormat.setCalendar(localGregorianCalendar);

        long l = System.currentTimeMillis() - 300000L;

        Date localDate = new Date(l);

        String str2 = localSimpleDateFormat.format(localDate);

        return "http://fresh.moskva.fm/files/" + id + "/mp4/" + str2 + ".mp4";
    }


}
