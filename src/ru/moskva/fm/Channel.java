package ru.moskva.fm;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Channel {

    private String id;

    public Channel(String channelID) {
        this.id = channelID;
    }

    public String getChannelID() {
        return id;
    }

    public Track getFirstTranslationTrack() {

        long l = System.currentTimeMillis() - 300000L;

        l = l - l % 60000L;

        Date localDate = new Date(l);

        Track track = new Track();

        track.url = "http://fresh.moskva.fm/files/" + this.id + "/mp4/" + getFormattedDate(localDate) + ".mp4";

        track.date = localDate;

        return track;
    }

    public Track getNextTrack(Track current) {

        Track result = new Track();

        Calendar c = Calendar.getInstance();

        c.setTime(current.date);

        c.add(Calendar.MINUTE, 1);

        result.date = c.getTime();

        result.url = "http://fresh.moskva.fm/files/" + this.id + "/mp4/" + getFormattedDate(result.date) + ".mp4";

        return result;
    }

    private static String getFormattedDate(Date in) {

        TimeZone localTimeZone = TimeZone.getTimeZone("Europe/Moscow");

        GregorianCalendar localGregorianCalendar = new GregorianCalendar(localTimeZone);

        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/HHmm");

        localSimpleDateFormat.setCalendar(localGregorianCalendar);

        return localSimpleDateFormat.format(in);

    }


}
