package com.example.appandroid;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabase;
    ListView listView;
    EditText editTitulo, editConteudo;
    private NotaController notaController;
    //Vai ter que adicionar os buttons update, delete, research, insert
    Button btnInsert, btnDelete, btnUpdate;
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
        editTitulo = findViewById(R.id.editTitulo);
        editConteudo = findViewById(R.id.editConteudo);
        btnInsert = findViewById(R.id.btnInsert);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);


        atualizarLista();

        btnInsert.setOnClickListener(v -> {
                    String txtTitulo = editTitulo.getText().toString().trim();
                    String txtConteudo = editConteudo.getText().toString().trim();
                    if (!txtTitulo.isEmpty() && !txtConteudo.isEmpty()) {
                        Nota novaNota = new Nota(txtConteudo, txtTitulo);
                        notaController.inserirNovaNota(novaNota);
                        editTitulo.setText("");
                        editConteudo.setText("");
                        atualizarLista();
                        Toast.makeText(this, "Nota inserida com sucesso!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Digite um título para a nota", Toast.LENGTH_SHORT).show();
                    }
        });

        btnUpdate.setOnClickListener(v -> {
            if (notaSelecionada != null) {
                String novoTitulo = editTitulo.getText().toString().trim();
                String novoConteudo = editConteudo.getText().toString().trim();
                if (!novoTitulo.isEmpty()) {
                    notaSelecionada.titulo = novoTitulo;
                    notaSelecionada.conteudo = novoConteudo;
                    boolean sucesso = notaController.updateNota(notaSelecionada);
                    if (sucesso) {
                        editTitulo.setText("");
                        editConteudo.setText("");
                        notaSelecionada = null;
                        atualizarLista();
                        Toast.makeText(this, "Nota atualizada!", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(this, "Selecione uma nota na lista primeiro!", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(v -> {
                    if (notaSelecionada != null) {
                        notaController.deleteNota(notaSelecionada.id);
                        editTitulo.setText("");
                        editConteudo.setText("");
                        notaSelecionada = null;

                        atualizarLista();
                        Toast.makeText(this, "Nota removida!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Selecione uma nota na lista primeiro!", Toast.LENGTH_SHORT).show();
                    }
        });



        listView.setOnItemClickListener((parent, view, position, id) -> {
            notaSelecionada = lista.get(position);
            editTitulo.setText(notaSelecionada.titulo);
            editConteudo.setText(notaSelecionada.conteudo);
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

            if (titulo == null) {
                titulo = new ArrayList<>();
            }

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


        button.setOnClickListener(v->{
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