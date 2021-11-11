package androidsamples.java.journalapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.UUID;

public class DeleteDialogFragment extends DialogFragment {
    String entryID = "";

    public static DeleteDialogFragment newInstance(String id) {
        DeleteDialogFragment frag = new DeleteDialogFragment();
        Bundle args = new Bundle();
        args.putString("id", id);
        frag.setArguments(args);
        return frag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        entryID = getArguments().getString("id");

        return new AlertDialog.Builder(getActivity()).setTitle("Delete entry").setMessage("Are you sure you want to delete?")
                .setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                doPositiveClick();
                            }
                        }
                ).setNegativeButton("NO",(((dialogInterface, i) -> doNegativeClick()))).create();
    }

    public void doPositiveClick() {
        // Do stuff here.
        Log.i("FragmentAlertDialog", "Positive click!");
        JournalRepository.getInstance().delete(UUID.fromString(entryID));
        getActivity().onBackPressed();
        dismiss();
    }

    public void doNegativeClick() {
        // Do stuff here.
        Log.i("FragmentAlertDialog", "Negative click!");
        dismiss();
    }

}

