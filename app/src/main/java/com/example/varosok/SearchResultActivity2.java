package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchResultActivity2 extends AppCompatActivity {
    //1. inicializálás
    private Button buttonVissza;
    private TextView textViewAdatok;
    private DBHelper adatbazis;
    private MainActivity OrszagKeres;
    //private EditText OrszagKeres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result2);
        //teszt get Intent
        Intent intent = getIntent();
        //final String OrszagKeres = intent.getStringExtra("OrszagKeres");
        String OrszagKeres = intent.getStringExtra("OrszagKeres");
        //textViewAdatokTeszt.setText(OrszagKeres);

        //String orszagKeres = getIntent().getExtras().getString("OrszagKeres","");
        //teszt vége getIntent

        //3. init meghívása
        init();
        adatlekerdezes();

        //5. a MainActvity-be való visszalépés setOnClikListener függvénnyel (new View....)
        buttonVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MainActivity aktivity-t hívunk meg az InsertActivity.this ből
                //An intent is to perform an action on the screen. It is mostly used to start activity
                Intent intentVissza = new Intent(SearchResultActivity2.this, MainActivity.class);
                startActivity(intentVissza);
                finish();
            }
        });
    }

    //4. adatlekérdezés metódus:
    public void adatlekerdezes(){

        //az adatok amit visszakapunk egy cursor lesz amit az adatbazsi-bol fogunk visszakapni
        Cursor adatok = adatbazis.SearchResult();
        //if végén return!!!
        if (adatok==null){
            Toast.makeText(this, "Hiba történt a lekérdezés során", Toast.LENGTH_SHORT).show();
            return;
        }
        if (adatok.getCount()==0){
            Toast.makeText(this, "Még nincs felvéve adat!", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            //a StringBuilder, a Cursoros értékeket fűzi össze, a textView-ban meghívható
            StringBuilder builder = new StringBuilder();
            builder.append("Választott ország:").append(OrszagKeres).append("\n");
            while (adatok.moveToNext()){
                ////while (adatok.moveToNext() && adatok.getString(2) == OrszagKeres){

                    builder.append("Város név:").append(adatok.getString(1)).append("\n");
                    //builder.append("Ország:").append(adatok.getString(2)).append("\n");
                //else{builder.append("Választott ország:").append("Nincs ilyen ország az adatbázisban");}
            }
            //setText-el beletesszük a builder összefűzött adatokat majd toast-tal kiiratjuk
            textViewAdatok.setText(builder);
            Toast.makeText(this, "Sikeres adatlekérdezés", Toast.LENGTH_SHORT).show();
        }
    }
    public void init(){
        //2. értékadás
        buttonVissza = findViewById(R.id.buttonVissza2);
        textViewAdatok = findViewById(R.id.textviewAdatok);
        adatbazis = new DBHelper(this);
        //Teszt:
        //String OrszagKeres = MainActivity.class.editTextOrszag.getText().toString().trim();
        //Teszt vége



        //hogy TextView scrollozható legyen ehhez kell egy metódust meghívni
        textViewAdatok.setMovementMethod(new ScrollingMovementMethod());
    }

}
//editTextOrszag = findViewById(R.id.editTextOrszag);
//String OrszagKeres = editTextOrszag.getText().toString().trim();