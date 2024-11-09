package com.nfricke.coursecrafter_selfmade.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nfricke.coursecrafter_selfmade.MainActivity;
import com.nfricke.coursecrafter_selfmade.Adapter.NavListAdapter;
import com.nfricke.coursecrafter_selfmade.R;


public class NavFragment extends Fragment {

    private View view;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_nav, container, false);

        listView = view.findViewById(R.id.navListView);

        //Setup the menu items
        final String[] options = {
                getString(R.string.todo_fragment_title),
                getString(R.string.modullist_fragment_title),
                getString(R.string.noten_fragment_title),
                getString(R.string.rechner_fragment_title),
                getString(R.string.faq_fragment_title),
                getString(R.string.tictacto_fragment_title)
        };
        final int[] icons = {
                R.drawable.baseline_checklist_24,
                R.drawable.baseline_format_list_bulleted_24,
                R.drawable.baseline_exposure_plus_1_24,
                R.drawable.baseline_calculate_24,
                R.drawable.baseline_question_mark_24,
                R.drawable.game_adaptive_fore
        };

        //Set adapter for view
        NavListAdapter adapter = new NavListAdapter(requireContext(), options, icons);
        listView.setAdapter(adapter);

        //Change clicked fragment
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        ((MainActivity) requireActivity()).replaceFragment(new TodoFragment());
                        ((MainActivity) requireActivity()).appBarText.setText(getString(R.string.todo_fragment_title));
                        break;
                    case 1:
                        ((MainActivity) requireActivity()).replaceFragment(new ModullistFragment());
                        ((MainActivity) requireActivity()).appBarText.setText(getString(R.string.modullist_fragment_title));
                        break;
                    case 2:
                        ((MainActivity) requireActivity()).replaceFragment(new NotenFragment());
                        ((MainActivity) requireActivity()).appBarText.setText(getString(R.string.noten_fragment_title));
                        break;
                    case 3:
                        ((MainActivity) requireActivity()).replaceFragment(new RechnerFragment());
                        ((MainActivity) requireActivity()).appBarText.setText(getString(R.string.rechner_fragment_title));
                        break;
                    case 4:
                        ((MainActivity) requireActivity()).replaceFragment(new FAQFragment());
                        ((MainActivity) requireActivity()).appBarText.setText(getString(R.string.faq_fragment_title));
                        break;
                    case 5:
                        ((MainActivity) requireActivity()).replaceFragment(new TicTacToeFragment());
                        ((MainActivity) requireActivity()).appBarText.setText(getString(R.string.tictacto_fragment_title));
                        break;
                }
            }
        });

        return view;
    }
}