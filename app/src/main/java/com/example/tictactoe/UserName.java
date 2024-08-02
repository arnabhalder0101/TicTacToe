package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UserName extends AppCompatActivity {
    EditText playerx, playery;
    ImageView imgView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_name);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        playerx = findViewById(R.id.playerx);
        playery = findViewById(R.id.playery);
        imgView = findViewById(R.id.nxtpage);


        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // very important to do .getText().toString() inside of setOnClickListener() --
                String name_x = playerx.getText().toString();
                String name_y = playery.getText().toString();
                Intent i = new Intent(UserName.this, MainActivity.class);

                if (name_x != "" && name_y != "") {
                    i.putExtra("player_name_x", name_x);
                    i.putExtra("player_name_y", name_y);
                } else if (name_x == "" && name_y == "") {
                    i.putExtra("player_name_x", "Player X");
                    i.putExtra("player_name_y", "Player O");
                } else {
                    if (name_x != "" || name_y == "") {
                        i.putExtra("player_name_x", name_x);
                        i.putExtra("player_name_y", "Player O");
                    }
                    if (name_x == "" || name_y != "") {
                        i.putExtra("player_name_x", "Player X");
                        i.putExtra("player_name_y", name_y);
                    }
                }

                startActivity(i);
                finish();
            }
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                new AlertDialog.Builder(UserName.this).setIcon(R.drawable.baseline_warning_24).setTitle("Exit")
                        .setMessage("Are you Sure?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finishAffinity();
                                System.exit(0);
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(UserName.this, "Thanks For Confirming! ", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }).setNeutralButton("Help!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(UserName.this, "Press 'Yes' to Exit the Application!", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });

    }
}