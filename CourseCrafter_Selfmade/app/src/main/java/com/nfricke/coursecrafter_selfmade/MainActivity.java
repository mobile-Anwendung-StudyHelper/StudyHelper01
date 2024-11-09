package com.nfricke.coursecrafter_selfmade;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.nfricke.coursecrafter_selfmade.DAO.ModulManager;
import com.nfricke.coursecrafter_selfmade.DAO.ModulManagerDAO;
import com.nfricke.coursecrafter_selfmade.DAO.TodoManager;
import com.nfricke.coursecrafter_selfmade.DAO.TodoManagerDAO;
import com.nfricke.coursecrafter_selfmade.Fragment.FAQDetailFragment;
import com.nfricke.coursecrafter_selfmade.Fragment.FAQFragment;
import com.nfricke.coursecrafter_selfmade.Fragment.HomeFragment;
import com.nfricke.coursecrafter_selfmade.Fragment.ModullistFragment;
import com.nfricke.coursecrafter_selfmade.Fragment.ModulplanFragment;
import com.nfricke.coursecrafter_selfmade.Fragment.NavFragment;
import com.nfricke.coursecrafter_selfmade.Fragment.NotenFragment;
import com.nfricke.coursecrafter_selfmade.Fragment.RechnerFragment;
import com.nfricke.coursecrafter_selfmade.Fragment.TicTacToeFragment;
import com.nfricke.coursecrafter_selfmade.Fragment.TodoFragment;
import com.nfricke.coursecrafter_selfmade.databinding.ActivityMainBinding;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //Poblic vars need in other classes
    public ActivityMainBinding binding;
    public ModulManager modulManager;
    public ModulManagerDAO modulManagerDAO;
    public TodoManager todoManager;
    public TodoManagerDAO todoManagerDAO;
    public TextView appBarText;
    public String[] bloecke = new String[]{"","08:15 - 09:45","10:15 - 11:45","12:15 - 13:45","14:15 - 15:45","16:00 - 17:30","17:45 - 19:15"};
    public String[] wochentage = getLocalizedWeekdays();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wochentage[0] = "< " + getString(R.string.auswaehlen) + " >";
        bloecke[0] = "< " + getString(R.string.auswaehlen) + " >";
        //Initialize App
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        appBarText = findViewById(R.id.appBarText);
        //Initialize DataObjekt and DataAccessObjekt
        modulManagerDAO = new ModulManagerDAO(this);
        todoManagerDAO = new TodoManagerDAO(this);

        // Temp: Can be deleted
        /*ModulManager initialmodulManager = new ModulManager();
        initialmodulManager.add(new Modul("Name1", "Prof1", new int[]{1,2,3}, new int[]{4,5,6}, new String[]{"W101","W102","W103"}, true, (float) 1.3));
        initialmodulManager.add(new Modul("Name2", "Prof2", new int[]{3,1,2}, new int[]{4,5,6}, new String[]{"W201","W202","W203"}, true, (float) 0));
        initialmodulManager.add(new Modul("Name3", "Prof3", new int[]{2,3,1}, new int[]{4,5,6}, new String[]{"W301","W302","W303"}, true, (float) 3.3));
        initialmodulManager.add(new Modul("Name4", "", new int[]{0,0,0}, new int[]{0,0,0}, new String[]{"","",""}, true, (float) 0));
        modulManagerDAO.saveModulManager(initialmodulManager);
         */
        // Temp: Can be deleted
        /*TodoManager initialtodoManager = new TodoManager();
        initialtodoManager.add(new Todo("Aufgabe 1", false));
        initialtodoManager.add(new Todo("Aufgabe 2", false));
        initialtodoManager.add(new Todo("Aufgabe 3", true));
        initialtodoManager.add(new Todo("Aufgabe 4", false));
        todoManagerDAO.saveTodoManager(initialtodoManager);
        */

        todoManagerDAO.readTodoManager(todoManager = new TodoManager());
        modulManagerDAO.readModulManager(modulManager = new ModulManager());

        //Bottom Navigation
        replaceFragment(new HomeFragment());
        appBarText.setText(getString(R.string.home_fragment_title));
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.modulplan:
                    replaceFragment(new ModulplanFragment());
                    appBarText.setText(getString(R.string.modulplan_fragment_title));
                    break;
                case R.id.nav:
                    replaceFragment(new NavFragment());
                    appBarText.setText(getString(R.string.nav_fragment_title));
                    break;
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    appBarText.setText(getString(R.string.home_fragment_title));
                    break;
            }
            return true;
        });
    }

    //Implementation of Andriod's own back functionality
    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.frameLayout);

        if (currentFragment instanceof NotenFragment || currentFragment instanceof RechnerFragment || currentFragment instanceof TodoFragment ||
                currentFragment instanceof FAQFragment || currentFragment instanceof TicTacToeFragment || currentFragment instanceof ModullistFragment) {
            replaceFragment(new NavFragment());
            appBarText.setText(getString(R.string.nav_fragment_title));
        } else if (currentFragment instanceof NavFragment || currentFragment instanceof ModulplanFragment) {
            replaceFragment(new HomeFragment());
            appBarText.setText(getString(R.string.home_fragment_title));
            binding.bottomNavigationView.setSelectedItemId(R.id.home);
        } else if (currentFragment instanceof FAQDetailFragment) {
            replaceFragment(new FAQFragment());
        } else {
            super.onBackPressed();
        }
    }


    public String[] getLocalizedWeekdays() {
        String[] weekdays = new DateFormatSymbols(Locale.getDefault()).getWeekdays();
        return new String[]{"",weekdays[Calendar.MONDAY], weekdays[Calendar.TUESDAY],
                weekdays[Calendar.WEDNESDAY], weekdays[Calendar.THURSDAY],
                weekdays[Calendar.FRIDAY], weekdays[Calendar.SATURDAY],
                weekdays[Calendar.SUNDAY]};
    }

    //Set new Fragment for both navigation's
    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }

}