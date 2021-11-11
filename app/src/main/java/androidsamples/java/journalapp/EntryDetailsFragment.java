package androidsamples.java.journalapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EntryDetailsFragment # newInstance} factory method to
 * create an instance of this fragment.
 */
public class EntryDetailsFragment extends Fragment {

  private static final String TAG = "EntryDetailsFragment";
  private JournalEntry mEntry;
  private EntryDetailsViewModel mEntryDetailsViewModel;
  private EditText mEditTitle;
  private Button mBtnDate, mBtnStartTime, mBtnEndTime, mBtnSave;
  private SharedViewModel sharedVM;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);

    mEntryDetailsViewModel = new ViewModelProvider(getActivity()).get(EntryDetailsViewModel.class);

    UUID entryId = UUID.fromString(getArguments().get("entryId").toString());
    Log.d(TAG, "Loading entry: " + entryId);


    mEntryDetailsViewModel.loadEntry(entryId);
  }

  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.fragment_entry_details,menu);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if(item.getItemId() == R.id.menu_delete){
      deleteEntry(this.getView());
      return true;
    }
    if(item.getItemId() == R.id.menu_share){
      Intent intent = new Intent(Intent.ACTION_SEND);
      intent.setType("text/plain");
      intent.putExtra(Intent.EXTRA_TEXT, "Look what I have been up to: "+ mEditTitle.getText().toString() +" on "+ sharedVM.getDate() +", "+ sharedVM.getStartTime() +" to "+ sharedVM.getEndTime());
      startActivity(Intent.createChooser(intent, "Share"));
    }
    return super.onOptionsItemSelected(item);
  }


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_entry_details, container, false);

    mEditTitle = view.findViewById(R.id.edit_title);
    mBtnDate = view.findViewById(R.id.btn_entry_date);
    mBtnStartTime = view.findViewById(R.id.btn_start_time);
    mBtnEndTime = view.findViewById(R.id.btn_end_time);
    mBtnSave = view.findViewById(R.id.btn_save);

    mBtnSave.setOnClickListener(this::saveEntry);

    return view;
  }


  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putString("title", mEditTitle.getText().toString());
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    Log.d(TAG, "OnViewCreatead");
    super.onViewCreated(view, savedInstanceState);
    sharedVM = SharedViewModel.getInstance();

    mEntryDetailsViewModel.getEntryLiveData().observe(getActivity(),
            entry -> {
              this.mEntry = entry;
              if(savedInstanceState == null){
                if(mEntry == null){
                  sharedVM.setDate("");
                  sharedVM.setStartTime("");
                  sharedVM.setEndTime("");
                  mEditTitle.setText("");
                }
                else{
                  sharedVM.setDate(this.mEntry.date());
                  sharedVM.setStartTime(this.mEntry.startTime());
                  sharedVM.setEndTime(this.mEntry.endTime());
                  mEditTitle.setText(this.mEntry.title());
                }
              }
              else{
                mEditTitle.setText(savedInstanceState.get("title").toString());
              }
              updateUI();

            });

    final NavController navController = Navigation.findNavController(view);


    mBtnDate.setOnClickListener(v -> navController.navigate(R.id.datePickerAction));
    mBtnStartTime.setOnClickListener(v -> {
      sharedVM.setType("start");
      navController.navigate(R.id.timePickerAction);
    });

    mBtnEndTime.setOnClickListener(v -> {
      sharedVM.setType("end");
      navController.navigate(R.id.timePickerAction);
    });
  }

  private void saveEntry(View v) {
    Log.d(TAG, "Save button clicked");
    mEntry.setTitle(mEditTitle.getText().toString());
    mEntry.setDate(sharedVM.getDate());
    mEntry.setStartTime(sharedVM.getStartTime());
    mEntry.setEndTime(sharedVM.getEndTime());
    mEntryDetailsViewModel.saveEntry(mEntry);
    getActivity().onBackPressed();
  }

  private void deleteEntry(View v){
    getActivity().onBackPressed();
    Log.d(TAG, "deletion");
    mEntryDetailsViewModel.deleteEntry(mEntry.getUid());

  }

  private void updateUI() {
    mBtnDate.setText(sharedVM.getDate());
    mBtnStartTime.setText(sharedVM.getStartTime());
    mBtnEndTime.setText(sharedVM.getEndTime());
  }
}