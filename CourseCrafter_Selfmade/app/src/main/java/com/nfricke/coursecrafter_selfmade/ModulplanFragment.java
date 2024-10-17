package com.nfricke.coursecrafter_selfmade;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.res.Configuration;
import androidx.fragment.app.Fragment;

public class ModulplanFragment extends Fragment {

    public ModulplanFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_modulplan, container, false);





        /*if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return inflater.inflate(R.layout.fragment_modulplan_landscape,
                    container, false);
        } else {
            return inflater.inflate(R.layout.fragment_modulplan, container, false);
        }*/
    }
}

