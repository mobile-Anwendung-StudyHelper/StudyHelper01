package com.nfricke.coursecrafter_selfmade;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

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
        faqList.add(new FAQ("#Allgemein", "Wie melde ich mich an?", "Klicke auf den 'Registrieren' Button...", "sample_picture.jpg","Inhalt", ""));
        faqList.add(new FAQ("#Bezahlung", "Welche Zahlungsarten gibt es?", "Wir akzeptieren Kreditkarte, PayPal...","artikel_one.html","", ""));

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

                // Fragment-Transaktion starten (hier musst du ggf. deine Fragment-Manager-Logik anpassen)
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, detailFragment) // Ersetze 'R.id.fragment_container_view' mit der ID deines Containers
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }
}