package com.nfricke.coursecrafter_selfmade.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.nfricke.coursecrafter_selfmade.DAO.FAQ;
import com.nfricke.coursecrafter_selfmade.Adapter.FAQListAdapter;
import com.nfricke.coursecrafter_selfmade.MainActivity;
import com.nfricke.coursecrafter_selfmade.R;

import java.util.ArrayList;
import java.util.List;

public class FAQFragment extends Fragment {

    private ListView listView;
    private List<FAQ> faqList; // Liste deiner FAQ-Objekte

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_f_a_q, container, false);

        listView = view.findViewById(R.id.faq_listView); // ID der ListView in fragment_f_a_q_xml
        // Datenquelle initialisieren (Beispiel)
        faqList = new ArrayList<>();
        faqList.add(new FAQ("#Lernen", "Die Pomodoro-Technik", "Fokussiert arbeiten und mehr erreichen", "test.md"));
        faqList.add(new FAQ("#Lernen", "Effektives Lernen", "Strategien und Techniken", "test2.md"));
        faqList.add(new FAQ("#Zeitmanagement", "Zeitmanagement im Studium", "Tipps für mehr Effektivität", "test3.md"));
        faqList.add(new FAQ("#Studizeit", "Tipps für ein erfolgreiches Studium", "Wie du dein Studium erfolgreich abschließen kannst", "test4.md"));

        // Adapter erstellen und setzen
        FAQListAdapter adapter = new FAQListAdapter(getContext(), faqList);
        listView.setAdapter(adapter);

        // OnItemClickListener hinzufügen
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Hier den Code zum Öffnen des Blog-Artikels einfügen
                FAQ clickedFAQ = faqList.get(position);

                // Beispiel: Öffne ein neues Fragment mit den Details des FAQ
                Bundle bundle = new Bundle();
                bundle.putSerializable("faq", clickedFAQ); // FAQ-Objekt übergeben

                FAQDetailFragment detailFragment = new FAQDetailFragment();
                detailFragment.setArguments(bundle);

                ((MainActivity) getActivity()).replaceFragment(detailFragment);
            }
        });

        return view;
    }
}