package androidsamples.java.journalapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

/**
 * A fragment representing a list of Items.
 */
public class EntryListFragment extends Fragment {

  private static final String TAG = "EntryListFragment";
  private EntryListViewModel mEntryListViewModel;

  @NonNull
  public static EntryListFragment newInstance() {
    EntryListFragment fragment = new EntryListFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.fragment_entry_list,menu);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if(item.getItemId() == R.id.menu_info){
      final NavController navController = Navigation.findNavController(this.getView());
      navController.navigate(R.id.getInfo);
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mEntryListViewModel = new ViewModelProvider(this).get(EntryListViewModel.class);
    setHasOptionsMenu(true);
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_entry_list, container, false);
    FloatingActionButton fab = view.findViewById(R.id.btn_add_entry);

    fab.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        JournalEntry entry = addNewEntry(view);

        EntryListFragmentDirections.AddEntryAction action = EntryListFragmentDirections.addEntryAction();

        action.setEntryId(entry.getUid().toString());
        Navigation.findNavController(view).navigate(action);
      }
    });

    RecyclerView entriesList = view.findViewById(R.id.recyclerView);
    entriesList.setLayoutManager(new LinearLayoutManager(getActivity()));
    EntryListAdapter adapter = new EntryListAdapter(getActivity());
    entriesList.setAdapter(adapter);

    mEntryListViewModel.getAllEntries().observe(getActivity(), adapter::setEntries);
    return view;
  }

  public JournalEntry addNewEntry(View view) {
    JournalEntry entry = new JournalEntry("", "DATE", "START TIME", "END TIME");
    mEntryListViewModel.insert(entry);
    return entry;
  }

  private class EntryViewHolder extends RecyclerView.ViewHolder {
    private final TextView mTxtTitle;
    private final TextView mTxtDate;
    private final TextView mTxtStartTime;
    private final TextView mTxtEndTime;
    private JournalEntry mEntry;


    public EntryViewHolder(@NonNull View itemView) {
      super(itemView);

      mTxtTitle = itemView.findViewById(R.id.txt_item_title);
      mTxtDate =  itemView.findViewById(R.id.txt_item_date);
      mTxtStartTime =  itemView.findViewById(R.id.txt_item_start_time);
      mTxtEndTime =  itemView.findViewById(R.id.txt_item_end_time);

      itemView.setOnClickListener(this::launchJournalEntryFragment);
    }

    private void launchJournalEntryFragment(View v) {
      Log.d(TAG, "launchJournalEntryFragment with Entry: " + mEntry.title());
      EntryListFragmentDirections.ModifyEntryAction action = EntryListFragmentDirections.modifyEntryAction();
      action.setEntryId(mEntry.getUid().toString());
      Navigation.findNavController(v).navigate(action);
    }

    void bind(JournalEntry entry) {
      mEntry = entry;
      this.mTxtTitle.setText(mEntry.title());
      this.mTxtDate.setText(mEntry.date());
      this.mTxtStartTime.setText(mEntry.startTime());
      this.mTxtEndTime.setText(mEntry.endTime());
    }
  }
  private class EntryListAdapter extends RecyclerView.Adapter<EntryViewHolder> {
    private final LayoutInflater mInflater;
    private List<JournalEntry> mEntries;

    public EntryListAdapter(Context context) {
      mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public EntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View itemView = mInflater.inflate(R.layout.fragment_entry, parent, false);
      return new EntryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EntryViewHolder holder, int position) {
      if (mEntries != null) {
        JournalEntry current = mEntries.get(position);
        holder.bind(current);
      }
    }

    @Override
    public int getItemCount() {
      return (mEntries == null) ? 0 : mEntries.size();
    }

    public void setEntries(List<JournalEntry> entries) {
      mEntries = entries;
      notifyDataSetChanged();
    }
  }
}