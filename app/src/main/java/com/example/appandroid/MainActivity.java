package com.example.appandroid;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabase;
    ListView listView;
    EditText editText;
    private NotaController notaController;
    //Vai ter que adicionar os buttons update, delete, research, insert
    Button button;
    ArrayList<Nota> lista;
    ArrayList<String> titulo;
    ArrayAdapter<String> adapter;
    Nota notaSelecionada = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notaController = new NotaController(getApplicationContext());

        listView = findViewById(R.id.listview);
        editText = findViewById(R.id.editTextText);
        button = findViewById(R.id.button);


        button.setOnClickListener(v -> {
            notaController.listarNotas();
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            notaSelecionada = lista.get(position);
            editText.setText(notaSelecionada.titulo);
            Toast.makeText(this, "Selecionado: " + notaSelecionada.titulo, Toast.LENGTH_SHORT).show();
        });

        //outro button com insert
        //String nota = etName.getText().toString();
        //notaController.inserirNovaNota();
        //vai ter que ser criado os botoes do crud


    }

        public void atualizarLista () {
            //Recuperar dados
            lista = notaController.listarNotas();
            titulo = notaController.listaTitulosNotas();

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titulo);
            listView.setAdapter(adapter);
        }
    }

/*
ArrayList<String> Lista = new ArrayList<>();
        while(!cursor.isAfterLast()){

            //cursor.getColumnIndex("id");
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String titulo = cursor.getString(cursor.getColumnIndex("titulo"));
            String conteudo = cursor.getString(cursor.getColumnIndex("conteudo"));
            lista.add("id:" +Integer.toString(id)+"titulo" +titulo);
            cursor.moveToNext();
            Log.d("SELECT NOTAS", "id:"+id+" titulo:"+titulo+" conteudo: "+conteudo);
        }
 */

        /*button.setOnClickListener(v->{
            String string = editText.getText().toString();
            ContentValues contentValues = new ContentValues();
            //contentValues.put("id",1);
            contentValues.put("titulo", "Nota 1");
            contentValues.put("conteudo", "Conteudo da nota 1");
            sqLiteDatabase.insert("notas", null, contentValues);

            listarDB();
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main),
                (v, insets) -> {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left,  systemBars.top,
                            systemBars.right, systemBars.bottom);
                    return insets;
                });

    sqLiteDatabase = openOrCreateDatabase("banco.db", MODE_PRIVATE, null);

    sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS notas (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "titulo TEXT, conteudo TEXT)");
    sqLiteDatabase.execSQL("INSERT INTO notas (id, titulo, conteudo) VALUES (1, 'nota 1' ," +
            "'Conteudo da nota 1')");






    }*/