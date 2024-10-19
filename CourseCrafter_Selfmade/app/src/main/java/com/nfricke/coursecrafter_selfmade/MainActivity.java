package com.nfricke.coursecrafter_selfmade;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.nfricke.coursecrafter_selfmade.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public ModulManager modulManager;
    public ModulManagerDAO modulManagerDAO;
    public TextView appBarText;
    public String[] wochentage = new String[]{"<auswahl>","Montag","Dienstag","Mittwoch","Donnerstag","Freitag","Samstag","Sonntag"};
    public String[] bloecke = new String[]{"<auswahl>","8:15-9:45","10:15-11:45","12:15-13:45","14:15-15:45","16:00-17:30","17:45-19:15"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        appBarText = findViewById(R.id.appBarText);
        modulManagerDAO = new ModulManagerDAO(this);


        // Temp: Kann am Ende Gelöscht werden.
        ModulManager initialmodulManager = new ModulManager();
        initialmodulManager.add(new Modul("Name1", "Prof1", new int[]{1,2,3}, new int[]{4,5,6}, new String[]{"W101","W102","W103"}, true, (float) 1.3));
        initialmodulManager.add(new Modul("Name2", "Prof2", new int[]{2,3,1}, new int[]{5,6,4}, new String[]{"W201","W202","W203"}, true, (float) 0));
        initialmodulManager.add(new Modul("Name3", "Prof3", new int[]{3,1,2}, new int[]{6,4,5}, new String[]{"W301","W302","W303"}, false, (float) 3.3));
        initialmodulManager.add(new Modul("Name4", "", new int[]{0,0,0}, new int[]{0,0,0}, new String[]{"","",""}, true, (float) 0));
        modulManagerDAO.saveModulManager(initialmodulManager);


        modulManagerDAO.readModulManager(modulManager = new ModulManager());
        modulManager.printTest();
        
        replaceFragment(new ModulplanFragment());
        appBarText.setText("StudyHelper -> " + getString(R.string.modulplan_fragment_title));
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.modulplan:
                    replaceFragment(new ModulplanFragment());
                    appBarText.setText("StudyHelper -> " + getString(R.string.modulplan_fragment_title));
                    break;
                case R.id.modullist:
                    replaceFragment(new ModullistFragment());
                    appBarText.setText("StudyHelper -> " + getString(R.string.modullist_fragment_title));
                    break;
                case R.id.nav:
                    replaceFragment(new NavFragment());
                    appBarText.setText("StudyHelper -> " + getString(R.string.nav_fragment_title));
                    break;
            }
            return true;
        });
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.frameLayout);

        if (currentFragment instanceof NotenFragment || currentFragment instanceof RechnerFragment ||
                currentFragment instanceof FAQFragment || currentFragment instanceof TicTacToeFragment) {
            replaceFragment(new NavFragment());
            appBarText.setText("StudyHelper -> " + getString(R.string.nav_fragment_title));
        } else if (currentFragment instanceof NavFragment || currentFragment instanceof ModullistFragment) {
            replaceFragment(new ModulplanFragment());
            appBarText.setText("StudyHelper -> " + getString(R.string.modulplan_fragment_title));
            binding.bottomNavigationView.setSelectedItemId(R.id.modulplan);
        } else if (currentFragment instanceof FAQDetailFragment) {
            replaceFragment(new FAQFragment());
        } else {
            super.onBackPressed();
        }
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }

}