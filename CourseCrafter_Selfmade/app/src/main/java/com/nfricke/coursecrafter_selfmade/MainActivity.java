package com.nfricke.coursecrafter_selfmade;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.nfricke.coursecrafter_selfmade.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    public ModulManager modulManager;
    public ModulManagerDAO modulManagerDAO;
    public String[] wochentage = new String[]{"","Montag","Dienstag","Mittwoch","Donnerstag","Freitag","Samstag","Sonntag"};
    public String[] bloecke = new String[]{"","8:15-9:45","10:15-11:45","12:15-13:45","14:15-15:45","16:00-17:30","17:45-19:15"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(R.layout.activity_main);
        setContentView(binding.getRoot());

        modulManagerDAO = new ModulManagerDAO(this);

        ModulManager initialmodulManager = new ModulManager();
        initialmodulManager.add(new Modul(wochentage, bloecke, "Name1", "Prof1", new int[]{1,2,3}, new int[]{4,5,6}, new String[]{"W101","W102","W103"}));
        initialmodulManager.add(new Modul(wochentage, bloecke, "Name2", "Prof2", new int[]{2,3,1}, new int[]{5,6,4}, new String[]{"W201","W202","W203"}));
        initialmodulManager.add(new Modul(wochentage, bloecke, "Name3", "Prof3", new int[]{3,1,2}, new int[]{6,4,5}, new String[]{"W301","W302","W303"}));
        modulManagerDAO.saveModulManager(initialmodulManager);

        modulManagerDAO.readModulManager(modulManager = new ModulManager());



        replaceFragment(new ModullistFragment(this));
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.modullist:
                    replaceFragment(new ModullistFragment(this));
                    break;
                case R.id.modulplan:
                    replaceFragment(new ModulplanFragment());
                    break;
                case R.id.faq:
                    replaceFragment(new FAQFragment());
                    break;
                case R.id.rechner:
                    replaceFragment(new RechnerFragment());
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }

}