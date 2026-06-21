package com.example.appandroid;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button buttonMudarCor;
    Button btnRetangulo;
    Button btnLinha;
    SimplePaint simplePaint;
    ImageView btnCirculo;
    //        btnRetangulo;
    //        btnLinha
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);



    }
    public void mudaCor(ColorEnvelope envelope){
        simplePaint.mudarCor(envelope.getColor());
    }

    //criar um enum talvez no simple paint
    //pra criar uma linha, ou circulo, retangulo
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonMudarCor = findViewById(R.id.buttonMudaCor);
        simplePaint = findViewById(R.id.simplePaint);
        btnCirculo = findViewById(R.id.tapButton);
        btnLinha = findViewById(R.id.btnlinha);
        btnRetangulo = findViewById(R.id.btnretangulo);

        //tenho que criar um botao para a linha...
        //btnRetangulo = findViewById(R.id._____).....

        btnCirculo.setOnClickListener(v->{
            simplePaint.setFerramenta(SimplePaint.Ferramenta.CIRCULO);
        });
        btnLinha.setOnClickListener(v->{
            simplePaint.setFerramenta(SimplePaint.Ferramenta.LINHA);
        });
        btnRetangulo.setOnClickListener(v->{
            simplePaint.setFerramenta(SimplePaint.Ferramenta.RETANGULO);
        });

        //btnRetangulo.setOnClickListener(v->{
        //simplePaint.setFerramente(SimplePaint.Ferramente.RETANGULO);
        //});

        buttonMudarCor.setOnClickListener(v->{
            new ColorPickerDialog.Builder(this)
                    .setTitle("Mude a cor do desenho")
                    .setPreferenceName("MyColorPickerDialog")
                    .setPositiveButton("Confirmar",
                            new ColorEnvelopeListener() {
                                @Override
                                public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
                                    mudaCor(envelope);
                                }
                            })
                    .setNegativeButton("Cancelar",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                    .attachAlphaSlideBar(true) // the default value is true.
                    .attachBrightnessSlideBar(true)  // the default value is true.
                    .setBottomSpace(12) // set a bottom space between the last slidebar and buttons.
                    .show();
        });
    }
}