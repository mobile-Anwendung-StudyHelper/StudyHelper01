package com.nfricke.coursecrafter_java_selfmade;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.nfricke.coursecrafter_java_selfmade.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(R.layout.activity_main);
        setContentView(binding.getRoot());

        ModulManagerDAO modulManagerDAO = new ModulManagerDAO(this);

        //ModulManager initialModulManager = new ModulManager();
        //initialModulManager.add(new Modul("Name1", "Prof1", new int[]{1,2,3}, new int[]{4,5,6}, new String[]{"a","b","c"}));
        //initialModulManager.add(new Modul("Name2", "Prof2", new int[]{2,3,1}, new int[]{5,6,4}, new String[]{"b","c","a"}));
        //initialModulManager.add(new Modul("Name3", "Prof3", new int[]{3,1,2}, new int[]{6,4,5}, new String[]{"c","a","b"}));
        //modulManagerDAO.saveModulManager(initialModulManager);

        ModulManager readModulManager = modulManagerDAO.readModulManager();
        readModulManager.printTest();



        replaceFragment(new ModullistFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.modullist:
                    replaceFragment(new ModullistFragment());
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