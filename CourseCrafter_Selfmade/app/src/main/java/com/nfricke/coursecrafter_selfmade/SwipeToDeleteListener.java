package com.nfricke.coursecrafter_selfmade;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

public class SwipeToDeleteListener extends GestureDetector.SimpleOnGestureListener {
    private final ListView listView;
    private ModulManager modulManager;
    private FragmentActivity activity;
    private ModulAdapter modulAdapter;
    private ModulManagerDAO modulManagerDAO;

    // Swipe gesture thresholds
    private static final int SWIPE_MIN_DISTANCE = 100; // Minimum distance in pixels for a swipe
    private static final int SWIPE_THRESHOLD_VELOCITY = 100; // Minimum velocity in pixels/second
    private static final int SWIPE_MAX_OFF_PATH = 250; // Maximum perpendicular deviation in pixels

    public SwipeToDeleteListener(ListView listView, ModulManager modulManager, FragmentActivity activity, ModulAdapter modulAdapter, ModulManagerDAO modulManagerDAO) {
        this.listView = listView;
        this.modulAdapter = modulAdapter;
        this.activity = activity;
        this.modulManager = modulManager;
        this.modulManagerDAO = modulManagerDAO;
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
            if (position >= 0 && position < modulManager.size()) {
                // Show confirmation dialog
                new AlertDialog.Builder(activity)
                        .setTitle(modulManager.get(position).getModulName() + " wircklich löschen?")
                        .setPositiveButton("Löschen", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Remove the item from the data source
                                modulManager.remove(position);
                                // Persist changes if necessary
                                modulManagerDAO.saveModulManager(modulManager);
                                // Notify the adapter of deletion
                                modulAdapter.notifyDataSetChanged();
                                Toast.makeText(activity, "Modul gelöscht", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss(); // Cancel the dialog
                                Toast.makeText(activity, "Abgebrochen", Toast.LENGTH_SHORT).show();
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