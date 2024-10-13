package com.nfricke.coursecrafter_selfmade;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.fragment.app.Fragment;

public class ModullistFragment extends Fragment {

    private MainActivity parent;

    public ModullistFragment(MainActivity p) {
        this.parent = p;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialization not involving view setup can be done here
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_modullist, container, false);

        // Use 'view' to find views inside the fragment
        ListView modulListView = view.findViewById(R.id.listView);

        // Create the adapter using the fragment's context or activity context
        ModulAdapter modulAdapter = new ModulAdapter(getActivity(), parent.modulManager);

        // Set the adapter
        modulListView.setAdapter(modulAdapter);

        return view;
    }

}


    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_modullist);

        ModulManagerAdapter modulManagerAdapter = new ModulManagerAdapter(getApplicationContext(), parent.modulManager);

        // create the instance of the ListView to set the numbersViewAdapter
        ListView numbersListView = findViewById(R.id.listView);

        // set the numbersViewAdapter for ListView
        numbersListView.setAdapter(modulManagerAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_modullist, container, false);
    }
}*/