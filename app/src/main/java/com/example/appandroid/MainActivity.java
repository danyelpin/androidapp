package com.example.appandroid;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    FragmentoA fragmentoA;
    FragmentoB fragmentoB;
    FragmentoC fragmentoC;
    Button buttonA, buttonB, buttonC;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonA = findViewById((R.id.buttonA));
        buttonC = findViewById(R.id.buttonC);
        buttonB = findViewById(R.id.buttonB);


        //Inicializacao gerenciado de fragmentos
        FragmentManager fragmentManager = getSupportFragmentManager();
        //inicia uma transacao com fragmentManager
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //Adicionamos o conteiner e o objeto do nosso fragmento a para exibir
        fragmentTransaction.add(R.id.frameLayout, new FragmentoA());
        //finalizamos a transacao do fragment com commit
        fragmentTransaction.commit();

        buttonA.setOnClickListener(v -> {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.frameLayout, new FragmentoA());
            ft.commit();
        });

        buttonB.setOnClickListener(v -> {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.frameLayout, new FragmentoB());
            ft.commit();
        });

    }
}