package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //1. inicializálunk, button, editText, DBHelper
    private Button buttonFelvetel, buttonKereses;
    public EditText editTextOrszag;
    private DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //3. értékadás meghívása
        init();
        //4.1. Rögzítés elkészítése setOnClikListener függvénnyel (new View....) és egy másik activity-ben lesz, amit elkészítünk mint new empty activity
        buttonFelvetel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //másikRozgitesActivity aktivity-t hívunk meg MainActivity.this név: An intent is to perform an action on the screen. It is mostly used to start activity
                Intent intentFelvesz = new Intent(MainActivity.this, InsertActivity.class);
                startActivity(intentFelvesz);
                finish();
            }

        });

        //4.2. Lekérdezés elkészítése setOnClikListener függvénnyel (new View....) és egy másik activity-ben lesz, amit elkészítünk mint new empty activity
        buttonKereses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //másikRozgitesActivity aktivity-t hívunk meg MainActivity.this név: An intent is to perform an action on the screen. It is mostly used to start activity
                Intent intentKeres = new Intent(MainActivity.this, SearchResultActivity2.class);
                //ország mező kitöltésre a kerséshez és érték megőrzése putExtra getText-el
                if (editTextOrszag.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "az Ország mező üres", Toast.LENGTH_SHORT).show();

                } else {
                    String orszagKeres = editTextOrszag.getText().toString();
                    Intent intent = new Intent(MainActivity.this, SearchResultActivity2.class);
                    intent.putExtra("OrszagKeres", editTextOrszag.getText().toString().trim());
                    startActivity(intentKeres);
                    finish();
                }


            }
        });
    }
//2. értékadás
    public void init(){
        buttonFelvetel = findViewById(R.id.buttonFelvetel);
        buttonKereses = findViewById(R.id.buttonKereses);
        editTextOrszag = findViewById(R.id.editTextOrszag);
        adatbazis = new DBHelper(this);
    }


}