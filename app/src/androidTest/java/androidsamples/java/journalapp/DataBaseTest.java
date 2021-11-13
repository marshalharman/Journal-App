package androidsamples.java.journalapp;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Tests for room database created
 */
@RunWith(AndroidJUnit4.class)
public class DataBaseTest {
    private JournalEntryDao mDao;
    private JournalRoomDatabase mDB;

    @Before
    public void createDB(){
        Context context = ApplicationProvider.getApplicationContext();
        mDB = Room.inMemoryDatabaseBuilder(context,JournalRoomDatabase.class).build();
        mDao = mDB.journalEntryDao();
    }

    @After
    public void closeDb() throws IOException {
        mDB.close();
    }


    @Test
    public void insertTest() throws Exception{
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2021);
        c.set(Calendar.MONTH, 10);
        c.set(Calendar.DAY_OF_MONTH, 11);
        SimpleDateFormat DateFor = new SimpleDateFormat("MM/dd/yyyy");
        DateFor = new SimpleDateFormat("E, dd MMM, yyyy");
        String strDate =  DateFor.format(c.getTime());

        int hourOfDay = 10;
        int minute = 45;
        String strStartTime1 = String.format("%02d:%02d", hourOfDay,minute);
        String strStartTime2 = String.format("%02d:%02d", hourOfDay+1,minute);
        String strEndTime2 = String.format("%02d:%02d", hourOfDay+5,minute+10);


        mDao.insert(new JournalEntry("Entry1",strDate, strStartTime1,strStartTime2));
        mDao.insert(new JournalEntry("Entry2",strDate, strStartTime2,strEndTime2));

        List<JournalEntry> entries = mDao.getEntriesList();
        assertThat(entries.size(),is(2));
    }

    @Test
    public void deleteTest() throws Exception{
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2021);
        c.set(Calendar.MONTH, 10);
        c.set(Calendar.DAY_OF_MONTH, 11);
        SimpleDateFormat DateFor = new SimpleDateFormat("MM/dd/yyyy");
        DateFor = new SimpleDateFormat("E, dd MMM, yyyy");
        String strDate =  DateFor.format(c.getTime());

        int hourOfDay = 10;
        int minute = 45;
        String strStartTime1 = String.format("%02d:%02d", hourOfDay,minute);
        String strStartTime2 = String.format("%02d:%02d", hourOfDay+1,minute);
        String strEndTime2 = String.format("%02d:%02d", hourOfDay+5,minute+10);


        mDao.insert(new JournalEntry("Entry1",strDate, strStartTime1,strStartTime2));
        mDao.insert(new JournalEntry("Entry2",strDate, strStartTime2,strEndTime2));


        List<JournalEntry> entries = mDao.getEntriesList();
        int n = entries.size();
        mDao.deleteEntry(entries.get(0).getUid());
        entries = mDao.getEntriesList();
        assertThat(entries.size(),is(n-1));
    }

    @Test
    public void updateTest() throws Exception{
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2021);
        c.set(Calendar.MONTH, 10);
        c.set(Calendar.DAY_OF_MONTH, 11);
        SimpleDateFormat DateFor = new SimpleDateFormat("MM/dd/yyyy");
        DateFor = new SimpleDateFormat("E, dd MMM, yyyy");
        String strDate =  DateFor.format(c.getTime());

        int hourOfDay = 10;
        int minute = 45;
        String strStartTime1 = String.format("%02d:%02d", hourOfDay,minute);
        String strStartTime2 = String.format("%02d:%02d", hourOfDay+1,minute);
        String strEndTime2 = String.format("%02d:%02d", hourOfDay+5,minute+10);


        mDao.insert(new JournalEntry("Entry1",strDate, strStartTime1,strStartTime2));
        mDao.insert(new JournalEntry("Entry2",strDate, strStartTime2,strEndTime2));


        List<JournalEntry> entries = mDao.getEntriesList();
        int n = entries.size();

        entries.get(0).setTitle("EntryUpdated");
        mDao.update(entries.get(0));
        entries = mDao.getEntriesList();
        assertThat(entries.get(0).title(),is("EntryUpdated"));
    }
}