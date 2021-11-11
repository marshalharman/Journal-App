package androidsamples.java.journalapp;

import androidx.annotation.NonNull;
import androidx.room.TypeConverter;

import java.util.Date;
import java.util.UUID;

public class JournalTypeConverters {
    @TypeConverter
    public UUID toUUID(@NonNull String uuid) {
        return UUID.fromString(uuid);
    }

    @TypeConverter
    public String fromUUID(@NonNull UUID uuid) {
        return uuid.toString();
    }

//    @TypeConverter
//    public static Date toDate(@NonNull Long dateLong){
//        return new Date(dateLong);
//    }
//
//    @TypeConverter
//    public static Long fromDate(@NonNull Date date){
//            return date.getTime();
//    }
}
