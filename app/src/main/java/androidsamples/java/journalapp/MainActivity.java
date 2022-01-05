package androidsamples.java.journalapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements  DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener  {
  private String TAG = "Main Activity";
  static final String KEY_ENTRY_ID = "KEY_ENTRY_ID";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
    if (currentFragment == null) {
      Fragment fragment = new EntryListFragment();
      getSupportFragmentManager()
              .beginTransaction()
              .add(R.id.nav_host_fragment, fragment)
              .commit();
    }
  }

  @Override
  public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
    Log.d("MainActivity", "onDateSet");
    Calendar c = Calendar.getInstance();
    c.set(Calendar.YEAR, year);
    c.set(Calendar.MONTH, month);
    c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
    SimpleDateFormat DateFor = new SimpleDateFormat("MM/dd/yyyy");
    DateFor = new SimpleDateFormat("E, dd MMM, yyyy");
    String strDate =  DateFor.format(c.getTime());

    SharedViewModel sharedVM = SharedViewModel.getInstance();
    Button btnDate = findViewById(R.id.btn_entry_date);
    sharedVM.setDate(strDate);
    btnDate.setText(strDate);
  }

  @Override
  public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
    Log.d("TimePickerFragment", "onDateSet");

    String strTime = String.format("%02d:%02d", hourOfDay,minute);

    SharedViewModel sharedVM = SharedViewModel.getInstance();

    if(sharedVM.getType() == "start"){
      Button btnStartTime = findViewById(R.id.btn_start_time);
      btnStartTime.setText(strTime);
      sharedVM.setStartTime(strTime);
    }
    else{
      Button btnEndTime = findViewById(R.id.btn_end_time);
      btnEndTime.setText(strTime);
      sharedVM.setEndTime(strTime);
    }
  }

  @Override
  public void onBackPressed() {
      super.onBackPressed();
  }
}