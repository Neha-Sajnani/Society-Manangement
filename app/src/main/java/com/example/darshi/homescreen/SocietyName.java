package com.example.darshi.homescreen;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SocietyName extends AppCompatActivity  {

    EditText society_name;
    Button set_society_name_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_society_name);
//
//        final SharedPreferences apppref = getSharedPreferences("first_pref", MODE_PRIVATE);
//        boolean firstStartapp = apppref.getBoolean("first_start_app", true);
//
//        if (firstStartapp) {

            society_name = findViewById(R.id.society_name);
            set_society_name_btn = findViewById(R.id.set_society_name);

            set_society_name_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Globals g = Globals.getInstance();
                    g.setSname(society_name.getText().toString());

                    Toast.makeText(getApplicationContext(),"Society Created",Toast.LENGTH_SHORT).show();

                    FileOutputStream fos = null;
                    try {
                        fos = openFileOutput("socname.txt",MODE_PRIVATE);
                        fos.write(g.getSname().getBytes());

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        if (fos != null) {
                            try {
                                fos.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    Intent intent =new Intent(getApplicationContext(),UserType.class);
                    startActivity(intent);

//                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//                    builder.setTitle("Set Society name as "+society_name.getText().toString()+" ?")
//                            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//
//                                }
//                            })
//                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    Globals g = Globals.getInstance();
//                                    g.setSname(society_name.getText().toString());
//
//                                    Toast.makeText(getApplicationContext(),"Society Created",Toast.LENGTH_SHORT).show();
//
//                                    Intent intent =new Intent(getApplicationContext(),UserType.class);
//                                    startActivity(intent);
//
//                                }
//                            }).create().show();



//                    SharedPreferences.Editor editor = apppref.edit();
//                    editor.putBoolean("first_start_app", false);
//                    editor.apply();
                }
            });

//        }
//        else
//        {
//            Intent intent =new Intent(getApplicationContext(),UserType.class);
//            startActivity(intent);
//        }
//
   }

}
