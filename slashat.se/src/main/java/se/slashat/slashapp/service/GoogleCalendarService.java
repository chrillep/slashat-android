package se.slashat.slashapp.service;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import se.slashat.slashapp.Callback;
import se.slashat.slashapp.async.GoogleCalendarLoaderAsyncTask;
import se.slashat.slashapp.model.LiveEvent;

import static se.slashat.slashapp.Constants.GOOGLE_CALENDAR_API_KEY;

/**
 * Created by nicklas on 7/2/13.
 */
public class GoogleCalendarService {

    private final static DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("YYYY-MM-dd'T00:00:00Z'");


    private final static String CALENDAR_URL = "https://www.googleapis.com/calendar/v3/calendars/%s@group.calendar.google.com/events?key=" + GOOGLE_CALENDAR_API_KEY
            +"&singleEvents=true&orderBy=startTime&timeMin=%s&timeMax=%s";

    private URL buildCalendarUrl(String calendarId) throws MalformedURLException {

        DateTime dateTime = new DateTime();
        return new URL(String.format(CALENDAR_URL, calendarId, dateTimeFormatter.print(dateTime), dateTimeFormatter.print(dateTime.plusWeeks(3))));
    }

    public void getCalendarEntries(String calendarId, Callback<List<LiveEvent>> callback){


        try {
            URL url = buildCalendarUrl(calendarId);
            GoogleCalendarLoaderAsyncTask googleCalendarLoaderAsyncTask = new GoogleCalendarLoaderAsyncTask(callback);
            googleCalendarLoaderAsyncTask.execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}