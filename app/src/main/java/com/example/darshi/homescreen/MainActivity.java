package com.example.darshi.homescreen;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Globals g = Globals.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //String userType = getIntent().getExtras().getString("usertype");

        FileInputStream fis = null;
        try {

            //setreportname
            fis = openFileInput("socname.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String LastRepName;

            while ((LastRepName = br.readLine()) != null) {
                sb.append(LastRepName);
                g.setSname(sb.toString());
            }

            fis = openFileInput("memberdetail.txt");
            InputStreamReader isr1 = new InputStreamReader(fis);
            BufferedReader br1 = new BufferedReader(isr1);
            StringBuilder sb1 = new StringBuilder();
            String MemberDeatail;

            //setting member details
            while ((MemberDeatail = br1.readLine()) != null) {
                sb1.append(MemberDeatail);
            }

            String[] details = sb1.toString().split(" ");
            g.setHm_blockno(details[0]);
            g.setHm_flatno(details[1]);
            g.setHm_members(details[2]);
            g.setHm_usertype(details[3]);


            //set member name
            fis = openFileInput("membername.txt");
            InputStreamReader isr2 = new InputStreamReader(fis);
            BufferedReader br2 = new BufferedReader(isr2);
            StringBuilder sb2 = new StringBuilder();
            String MemberName;

            while ((MemberName = br2.readLine()) != null) {
                sb2.append(MemberName);
                g.setHm_resname(sb2.toString());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //g.setHm_usertype("Secretary");
        String userType = g.getHm_usertype();

        Toast.makeText(getApplicationContext(),userType,Toast.LENGTH_LONG).show();
        if(userType.equals("Secretary"))
        {
            BottomNavigationView bottomnav = findViewById(R.id.bottomnav);
            bottomnav.setOnNavigationItemSelectedListener(navListener);

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        }
        else if(userType.equals("Resident"))
        {
            BottomNavigationView bottomnav = findViewById(R.id.bottomnav);
            bottomnav.setOnNavigationItemSelectedListener(navListenerResident);

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        }

    }



    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.home:
                            selectedFragment = new HomeFragment();
                            break;

                        case R.id.receipts:
                            selectedFragment = new ReceiptsFragment();
                            break;

                        case R.id.complaintBox:
                            selectedFragment = new ComplaintsFragment();
                            break;

                        case R.id.voteOnIssue:
                            selectedFragment = new VoteOnIssueFragment();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };



    private BottomNavigationView.OnNavigationItemSelectedListener navListenerResident =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.home:
                            selectedFragment = new HomeFragment();
                            break;

                        case R.id.receipts:
                            selectedFragment = new ResidentReceiptsFragment();
                            break;

                        case R.id.complaintBox:
                            selectedFragment = new ComplaintsFragment();
                            break;

                        case R.id.voteOnIssue:
                            selectedFragment = new ResidentVoteOnIssueFragment1();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };
}
