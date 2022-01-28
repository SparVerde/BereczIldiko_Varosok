package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {
    //1. iniciálunk: lesz 3 EditText + 1 button + adatbázis
    private EditText editTextNev, editTextOrszag, editTextLakossag;
    private Button buttonMentes, buttonVissza;
    private DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        //3. értékadás meghívása
        init();
        //4. Rögzítés meghívása
        buttonMentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adatRogzites();
                //Intent intentMentes = new Intent(InsertActivity.this, MainActivity.class);
                //startActivity(intentMentes);
                //finish();
            }
        });
        //4. a MainActvity-be való visszalépés setOnClikListener függvénnyel (new View....)
        buttonVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MainActivity aktivity-t hívunk meg az InsertActivity.this ből
                //An intent is to perform an action on the screen. It is mostly used to start activity
                Intent intentMentesbolVissza = new Intent(InsertActivity.this, MainActivity.class);
                startActivity(intentMentesbolVissza);
                finish();
            }
        });

    }
    //2. értékadás
    public void init(){
        editTextNev = findViewById(R.id.editTextNev);
        editTextOrszag= findViewById(R.id.editTextOrszag);
        editTextLakossag = findViewById(R.id.editTextLakossag);
        buttonMentes = findViewById(R.id.buttonMentes);
        buttonVissza = findViewById(R.id.buttonVissza);

        adatbazis = new DBHelper(InsertActivity.this);
    }
    //3. rögzítés metódus
    public void adatRogzites(){
        //az értékeket kitesszük stringekbe
        //get-tel kiolvassuk az edit mezőből + trim-et is érdemes hozzátenni
        String nev = editTextNev.getText().toString().trim();
        String orszag = editTextOrszag.getText().toString().trim();
        String lakossag = editTextLakossag.getText().toString().trim();
        //if, ha üres toast üzenet , context:this; vagy .setError-ral is lehet
        //minden if végén kell return, hogy visszalépjen az elemzésekből
        if (nev.isEmpty()){
            Toast.makeText(this, "Név megadása kötelező", Toast.LENGTH_SHORT).show();
            editTextNev.setError("Név megadása kötelező");
            return;
        }
        if (orszag.isEmpty()){
            Toast.makeText(this, "Ország megadása kötelező", Toast.LENGTH_SHORT).show();
            editTextOrszag.setError("Ország megadása kötelező");
            return;
        }
        if (lakossag.isEmpty()){
            Toast.makeText(this, "JLakosság megadása kötelező", Toast.LENGTH_SHORT).show();
            editTextLakossag.setError("Lakosság megadása kötelező!");
            return;
        }
        int lakosszam = Integer.parseInt(lakossag);
        // az adatbázist meghívjuk insert metodussal, ha ez true, kiíratjuk hogy sikeres
        if (adatbazis.insert(nev, orszag, lakossag)){
            Toast.makeText(this, "Sikeres adatrögzítés", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            Toast.makeText(this, "Sikertelen adatrögzítés", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}