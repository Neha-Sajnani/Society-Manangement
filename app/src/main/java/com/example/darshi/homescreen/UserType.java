package com.example.darshi.homescreen;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


//Registering
//Opens only one time
public class UserType extends AppCompatActivity {

    EditText house_blockno, house_flatno, house_members, house_residentname;
    Button residentbtn, secretarybtn;
    DatabaseReference HouseRef;
    HouseMaster houseMaster;
    Globals g = Globals.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Shared Preferences Here



            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_user_type);
//
//        final SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
//        boolean firstStart = pref.getBoolean("first_start", true);
//        if (firstStart)
//        {
            residentbtn = findViewById(R.id.residentbtn);
            secretarybtn = findViewById(R.id.secretarybtn);

            Toast.makeText(getApplicationContext(), g.getSname(), Toast.LENGTH_LONG).show();


            residentbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    house_blockno = findViewById(R.id.Global_BlockNo);
                    house_flatno = findViewById(R.id.Global_FlatNo);
                    house_members = findViewById(R.id.Global_No_Of_Members);
                    house_residentname = findViewById(R.id.Global_ResidentName);

                    HouseRef = FirebaseDatabase.getInstance().getReference();
                    FirebaseDatabase.getInstance().getReference()
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    HouseMaster houseMaster = new HouseMaster();
                                    houseMaster.setBlock(house_blockno.getText().toString());
                                    houseMaster.setFlatNo(house_flatno.getText().toString());
                                    houseMaster.setResidentName(house_residentname.getText().toString());
                                    houseMaster.setNoOfMembers(house_members.getText().toString());
                                    houseMaster.setUserType(residentbtn.getText().toString());
                                    HouseRef.child(g.getSname()).child("HouseMaster").child(house_blockno.getText().toString().concat(house_flatno.getText().toString())).setValue(houseMaster);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                    g.setHm_blockno(house_blockno.getText().toString());
                    g.setHm_flatno(house_flatno.getText().toString());
                    g.setHm_members(house_members.getText().toString());
                    g.setHm_resname(house_residentname.getText().toString());
                    g.setHm_usertype("Resident");

                    FileOutputStream fos = null;
                    try {
                        fos = openFileOutput("memeberdetail.txt", MODE_PRIVATE);
                        fos.write(g.getHm_blockno().concat(" ").concat(g.getHm_flatno()).concat(" ").concat(g.getHm_members())
                                .concat(" ").concat(g.getHm_usertype()).getBytes());

                        fos = openFileOutput("memebername.txt", MODE_PRIVATE);
                        fos.write(g.getHm_resname().getBytes());

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (fos != null) {
                            try {
                                fos.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

//
//                    SharedPreferences.Editor editor = pref.edit();
//                    editor.putBoolean("first_start", false);
//                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                    //showing pop up
//                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//                    builder.setTitle("Log in as a Resident ?")
//                            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//
//                                }
//                            })
//                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//
//                                    house_blockno = findViewById(R.id.Global_BlockNo);
//                                    house_flatno = findViewById(R.id.Global_FlatNo);
//                                    house_members = findViewById(R.id.Global_No_Of_Members);
//                                    house_residentname = findViewById(R.id.Global_ResidentName);
//
//                                    HouseRef = FirebaseDatabase.getInstance().getReference();
//                                    FirebaseDatabase.getInstance().getReference()
//                                            .addListenerForSingleValueEvent(new ValueEventListener() {
//                                                @Override
//                                                public void onDataChange(DataSnapshot dataSnapshot) {
//
//                                                    HouseMaster houseMaster = new HouseMaster();
//                                                    houseMaster.setBlock(house_blockno.getText().toString());
//                                                    houseMaster.setFlatNo(house_flatno.getText().toString());
//                                                    houseMaster.setResidentName(house_residentname.getText().toString());
//                                                    houseMaster.setNoOfMembers(house_members.getText().toString());
//                                                    houseMaster.setUserType(residentbtn.getText().toString());
//                                                    HouseRef.child(g.getSname()).child("HouseMaster").child(house_blockno.getText().toString().concat(house_flatno.getText().toString())).setValue(houseMaster);
//                                                }
//
//                                                @Override
//                                                public void onCancelled(DatabaseError databaseError) {
//
//                                                }
//                                            });
//
//                    SharedPreferences.Editor editor = pref.edit();
//                    editor.putBoolean("first_start",false);
//                    editor.apply();
//
//
//                                    g.setHm_blockno(house_blockno.getText().toString());
//                                    g.setHm_flatno(house_flatno.getText().toString());
//                                    g.setHm_members(house_members.getText().toString());
//                                    g.setHm_resname(house_residentname.getText().toString());
//                                    g.setHm_usertype("Resident");
//
//
//                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                                    startActivity(intent);
//                                }
//                            });


                }
            });

            secretarybtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    house_blockno = findViewById(R.id.Global_BlockNo);
                    house_flatno = findViewById(R.id.Global_FlatNo);
                    house_members = findViewById(R.id.Global_No_Of_Members);
                    house_residentname = findViewById(R.id.Global_ResidentName);


                    HouseRef = FirebaseDatabase.getInstance().getReference();

                    HouseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            houseMaster = new HouseMaster();
                            houseMaster.setBlock(house_blockno.getText().toString());
                            houseMaster.setFlatNo(house_flatno.getText().toString());
                            houseMaster.setResidentName(house_residentname.getText().toString());
                            houseMaster.setNoOfMembers(house_members.getText().toString());
                            houseMaster.setUserType(secretarybtn.getText().toString());
                            HouseRef.child(g.getSname()).child("HouseMaster").child(house_blockno.getText().toString().concat(house_flatno.getText().toString())).setValue(houseMaster);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    g.setHm_blockno(house_blockno.getText().toString());
                    g.setHm_flatno(house_flatno.getText().toString());
                    g.setHm_members(house_members.getText().toString());
                    g.setHm_resname(house_residentname.getText().toString());
                    g.setHm_usertype("Secretary");

                    FileOutputStream fos = null;
                    try {
                        fos = openFileOutput("memeberdetail.txt", MODE_PRIVATE);
                        fos.write(g.getHm_blockno().concat(" ").concat(g.getHm_flatno()).concat(" ").concat(g.getHm_members())
                                .concat(" ").concat(g.getHm_usertype()).getBytes());

                        fos = openFileOutput("memebername.txt", MODE_PRIVATE);
                        fos.write(g.getHm_resname().getBytes());

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (fos != null) {
                            try {
                                fos.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
//
//                    SharedPreferences.Editor editor = pref.edit();
//                    editor.putBoolean("first_start", false);
//                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

//                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//                    builder.setTitle("Log in as Secreatry ?")
//                            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//
//                                }
//                            })
//                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    house_blockno = findViewById(R.id.Global_BlockNo);
//                                    house_flatno = findViewById(R.id.Global_FlatNo);
//                                    house_members = findViewById(R.id.Global_No_Of_Members);
//                                    house_residentname = findViewById(R.id.Global_ResidentName);
//
//
//                                    HouseRef = FirebaseDatabase.getInstance().getReference();
//
//                                    HouseRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                                        @Override
//                                        public void onDataChange(DataSnapshot dataSnapshot) {
//
//                                            houseMaster = new HouseMaster();
//                                            houseMaster.setBlock(house_blockno.getText().toString());
//                                            houseMaster.setFlatNo(house_flatno.getText().toString());
//                                            houseMaster.setResidentName(house_residentname.getText().toString());
//                                            houseMaster.setNoOfMembers(house_members.getText().toString());
//                                            houseMaster.setUserType(secretarybtn.getText().toString());
//                                            HouseRef.child(g.getSname()).child("HouseMaster").child(house_blockno.getText().toString().concat(house_flatno.getText().toString())).setValue(houseMaster);
//                                        }
//
//                                        @Override
//                                        public void onCancelled(DatabaseError databaseError) {
//
//                                        }
//                                    });
////                    SharedPreferences.Editor editor = pref.edit();
////                    editor.putBoolean("first_start",false);
////                    editor.apply();
//
//                                    g.setHm_blockno(house_blockno.getText().toString());
//                                    g.setHm_flatno(house_flatno.getText().toString());
//                                    g.setHm_members(house_members.getText().toString());
//                                    g.setHm_resname(house_residentname.getText().toString());
//                                    g.setHm_usertype("Secretary");
//
//                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                                    startActivity(intent);
//
//                                }
//                            });


                }
            });


//        }
//        else {
//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            startActivity(intent);
//        }


    }
}
