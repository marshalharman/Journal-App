package androidsamples.java.journalapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

public class DatePickerFragment extends DialogFragment  {

  @NonNull
  public static DatePickerFragment newInstance(Date date) {
    // TODO implement the method
    DatePickerFragment dpf = new DatePickerFragment();
    return null;
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    // TODO implement the method
    Log.d("DatePickerFragment:", "onCreateDialog");

    Calendar c = Calendar.getInstance();
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);
    int day = c.get(Calendar.DATE);

//    return new DatePickerDialog(requireContext(), (dp, y, m, d) -> {
//    }, year, month, day);
    return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
  }

//  @Override
//  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//    super.onViewCreated(view, savedInstanceState);
//
//  }


//
////    Button mBtnDate = findViewById(R.id.btn_entry_date);
////    mBtnDate.setText(strDate);
////    SharedViewModel.mDate = strDate;
//  }
}
