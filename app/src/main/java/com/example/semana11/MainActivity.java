package com.example.semana11;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.semana11.Adapters.ContactoAdaptador;
import com.example.semana11.Helpers.QueueUtils;
import com.example.semana11.Models.Contacto;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ListView contactosList;
    ContactoAdaptador contactoAdaptador;
    QueueUtils.QueueObject queue = null;
    ArrayList<Contacto> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactosList = findViewById(R.id.contactosList);
        queue = QueueUtils.getInstance(this.getApplicationContext());

        items = new ArrayList<>();
        Contacto.injectContactsFromCloud(queue,items, this);
        Contacto.sendRequestPOST(queue,this);

        contactoAdaptador = new ContactoAdaptador(this, items);
        contactosList.setAdapter(contactoAdaptador);
    }

    public void refreshList(){
        if ( contactoAdaptador!= null ) {
            contactoAdaptador.notifyDataSetChanged();
        }
    }
}