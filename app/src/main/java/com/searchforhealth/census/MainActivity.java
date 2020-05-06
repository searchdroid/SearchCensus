package com.searchforhealth.census;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.searchforhealth.census.Fragments.BaseFragment;
import com.searchforhealth.census.Fragments.DashboardFragment;
import com.searchforhealth.census.Fragments.BirthSlipFragment;
import com.searchforhealth.census.Fragments.DeathSlipFragment;
import com.searchforhealth.census.Fragments.FamilyHeadFragments;
import com.searchforhealth.census.Fragments.FamilyMembersFragment;
import com.searchforhealth.census.Fragments.HealthEducationFragment;
import com.searchforhealth.census.Fragments.MedicineFragment;
import com.searchforhealth.census.Fragments.MonthlyReportFragment;
import com.searchforhealth.census.Fragments.PneumoniaFragment;
import com.searchforhealth.census.Fragments.ReminderFragment;
import com.searchforhealth.census.Fragments.SupervisorTaskFragment;
import com.searchforhealth.census.Fragments.SyncFragment;
import com.searchforhealth.census.Fragments.VillageInfoFragment;
import com.searchforhealth.census.Preference.SharedCS;

import java.util.HashMap;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    Bundle bundleFrag = null;
    public static HashMap<String, Stack<Fragment>> mStacks;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        init();
    }

    private void init() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        SharedCS scs = new SharedCS(MainActivity.this);
        System.out.println("Logged In User :"+ SharedCS.getPreferences().getString(SharedCS.EMAIL,"") );
        mStacks = new HashMap<String, Stack<Fragment>>();
        mStacks.put("Search", new Stack<Fragment>());
        displayView(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_census, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuSync:
                displayView(10);
                break;
            case R.id.menuLogout:
                SharedCS shared_pref = new SharedCS(MainActivity.this);
                SharedCS.writeString(SharedCS.LOGIN_STATUS, "N");

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

                break;

        }
        return true;
    }

    private void displayView(int position) {
        Fragment fragment = null;
        Fragment baseFrg = new DashboardFragment();

        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new DashboardFragment();
                title = "Dashboard";
                break;
            case 1:
                fragment = new BirthSlipFragment();
                title = "Birth Slip";
                break;
            case 2:
                fragment = new DeathSlipFragment();
                title = "Death Slip";
                break;
            case 3:
                fragment = new PneumoniaFragment();
                title = "Pneumonia Information";
                break;
            case 4:
                fragment = new MedicineFragment();
                title = "Medicine Information";
                break;
            case 5:
                fragment = new HealthEducationFragment();
                title = "Health Education";
                break;
            case 6:
                fragment = new SupervisorTaskFragment();
                title = "Supervisor Task";
                break;
            case 7:
                fragment = new MonthlyReportFragment();
                title = "Monthly Report";
                break;
            case 8:
                fragment = new ReminderFragment();
                title = "Reminder";
                break;
            case 9:
                fragment = new VillageInfoFragment();
                title = "Village Information";
                break;
            case 10:
                fragment = new SyncFragment();
                title = "Sync";
                break;
            case 11:
                fragment = new FamilyHeadFragments();
                title = "Family Heads";
                break;
            case 12:
                fragment = new FamilyMembersFragment();
                title = "Family Members";
                break;
            default:
                break;
        }

        if (fragment != null) {

            if(mStacks.get("Search").size() > 0){
                mStacks.get("Search").clear();
                if(!fragment.toString().contains("DashboardFragment"))
                    mStacks.get("Search").push(new DashboardFragment());
                pushFragments(fragment, false, true, bundleFrag,title);
            }else{
                pushFragments(fragment, false, true, bundleFrag, title);
            }
        }

    }



    public void pushFragment(Fragment fragment, String title){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frmLayMain, fragment);
        fragmentTransaction.commit();

        if(!title.equalsIgnoreCase(""))
            getSupportActionBar().setTitle(title);
    }


    public void pushFragments(Fragment fragment,boolean shouldAnimate, boolean shouldAdd, Bundle bundle,String title) {

        bundleFrag = bundle;
        if (shouldAdd)
            mStacks.get("Search").push(fragment);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        fragment.setArguments(bundleFrag);
//            if (shouldAnimate)
//                ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        ft.replace(R.id.frmLayMain, fragment);

        ft.commit();

        if(!title.equalsIgnoreCase(""))
            getSupportActionBar().setTitle(title);

    }

    /*
     *    Select the second last fragment in current tab's stack..
     *    which will be shown after the fragment transaction given below
     */
    public void popFragments(){

        Fragment fragment  =   mStacks.get("Search").elementAt(mStacks.get("Search").size() - 2);

        /*pop current fragment from stack.. */
        mStacks.get("Search").pop();

        /* We have the target fragment in hand.. Just show it.. Show a standard navigation animation*/
        FragmentManager manager        =   getSupportFragmentManager();
        FragmentTransaction ft          =   manager.beginTransaction();
//        ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        ft.replace(R.id.frmLayMain, fragment);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        try {
            System.out.println("OnBackPressed :" + ((BaseFragment)mStacks.get("Search").lastElement()).onBackPressed());
            if(((BaseFragment)mStacks.get("Search").lastElement()).onBackPressed() == false){
                /*
                 * top fragment in current tab doesn't handles back press, we can do our thing, which is
                 *
                 * if current tab has only one fragment in stack, ie first fragment is showing for this tab.
                 *        finish the activity
                 * else
                 *        pop to previous fragment in stack for the same tab
                 *
                 */
                if(mStacks.get("Search").size() == 1){

                    final Dialog dialog = new Dialog(MainActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.popup_exit);


                    // set the custom dialog components - text, image and button

                    Button btn_Yes = (Button) dialog.findViewById(R.id.btnYes);
                    Button btn_Cancel = (Button) dialog.findViewById(R.id.btnCancel);


                    btn_Yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            finish();
                        }
                    });

                    btn_Cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();

                }else{
                    System.out.println("Last frag----" + mStacks.get("Search").lastElement().toString());
                    popFragments();
                }
            }else{
                //do nothing.. fragment already handled back button press.

                popFragments();
            }



        } catch (Exception e) {
//			// TODO: handle exception
            System.out.println("Exception on Backpress on teaser Screen"+e);
        }
    }
}
