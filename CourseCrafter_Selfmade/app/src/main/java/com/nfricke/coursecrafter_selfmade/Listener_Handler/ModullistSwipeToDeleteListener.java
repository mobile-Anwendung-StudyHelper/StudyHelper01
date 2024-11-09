package com.nfricke.coursecrafter_selfmade.Listener_Handler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.nfricke.coursecrafter_selfmade.Adapter.ModulListAdapter;
import com.nfricke.coursecrafter_selfmade.MainActivity;
import com.nfricke.coursecrafter_selfmade.R;

public class ModullistSwipeToDeleteListener extends GestureDetector.SimpleOnGestureListener {
    private final ListView listView;
    private FragmentActivity activity;
    private ModulListAdapter modulAdapter;

    // Swipe gesture thresholds
    private static final int SWIPE_MIN_DISTANCE = 100; // Minimum distance in pixels for a swipe
    private static final int SWIPE_THRESHOLD_VELOCITY = 100; // Minimum velocity in pixels/second
    private static final int SWIPE_MAX_OFF_PATH = 250; // Maximum perpendicular deviation in pixels

    public ModullistSwipeToDeleteListener(ListView listView,FragmentActivity activity, ModulListAdapter modulAdapter) {
        this.listView = listView;
        this.modulAdapter = modulAdapter;
        this.activity = activity;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true; // Necessary to return true to process further gestures
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) {
            return false;
        }

        // Right to Left swipe
        if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
            final int position = listView.pointToPosition((int) e1.getX(), (int) e1.getY());
            if (position >= 0 && position < ((MainActivity)activity).modulManager.size()) {
                // Show confirmation dialog
                new AlertDialog.Builder(activity)
                        .setTitle(((MainActivity)activity).modulManager.get(position).getModulName() + " " +activity.getString(R.string.del_question)+"?")
                        .setPositiveButton(activity.getString(R.string.del_button), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Remove the item from the data source
                                ((MainActivity) activity).modulManager.remove(position);
                                // Persist changes if necessary
                                ((MainActivity)activity).modulManagerDAO.saveModulManager(((MainActivity)activity).modulManager);
                                // Notify the adapter of deletion
                                modulAdapter.notifyDataSetChanged();
                                Toast.makeText(activity, activity.getString(R.string.del_true), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton(activity.getString(R.string.abbrechen_Button), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss(); // Cancel the dialog
                                Toast.makeText(activity, activity.getString(R.string.abgebrochen), Toast.LENGTH_SHORT).show();
                                modulAdapter.notifyDataSetChanged(); // Reset the view without deletion
                            }
                        })
                        .show();
            }
            return true;
        }
        return false;
    }
}