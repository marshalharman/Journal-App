package androidsamples.java.journalapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.UUID;

@Entity(tableName = "journal_table")
public class JournalEntry {
    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    private UUID mUid;

    @ColumnInfo(name = "title")
    private String mTitle;

    @ColumnInfo(name = "date")
    private String mDate;

    @ColumnInfo(name = "startTime")
    private String mStartTime;

    @ColumnInfo(name = "endTime")
    private String mEndTime;

    public JournalEntry(@NonNull String title,@NonNull String date,@NonNull String startTime,@NonNull String endTime ) {
        mUid = UUID.randomUUID();
        mTitle = title;
        mDate = date;
        mStartTime = startTime;
        mEndTime = endTime;
    }

    @NonNull
    public UUID getUid() {
        return mUid;
    }
    public void setUid(@NonNull UUID id) {
        mUid = id;
    }

    @NonNull
    public String title() {
        return mTitle;
    }
    public void setTitle(String title) {
        mTitle = title;
    }

    @NonNull
    public String startTime() {
        return mStartTime;
    }
    public void setStartTime(String startTime) {
        mStartTime = startTime;
    }

    @NonNull
    public String endTime() {
        return mEndTime;
    }
    public void setEndTime(String EndTime) {
        mEndTime = EndTime;
    }

    @NonNull
    public String date() {
        return mDate;
    }
    public void setDate(String date) {
        mDate = date;
    }
}
