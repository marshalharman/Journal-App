package androidsamples.java.journalapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DeleteDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Delete entry").setMessage("Are you sure you want to delete?")
                .setNegativeButton("NO",(((dialogInterface, i) -> dismiss()))).setPositiveButton("YES", (((dialogInterface, i) -> dismiss())));
        return builder.create();
    }

    interface OnYesSelected{

    }
}

