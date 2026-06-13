package com.example.appandroid;

import android.content.Context;

import java.util.ArrayList;

public class NotaController {
    private NotaDAO notaDAO;
    public NotaController(Context context){
        notaDAO=new NotaDAO(context);
    }

    public void inserirNovaNota(Nota n){
        notaDAO.insereNota(n);
    }
    public boolean deleteNota(int id){
        notaDAO.deleteNota(id);
        return false;
    }
    public Nota getNota(int id){
        notaDAO.getNota(id);
        return null;
    }
    public ArrayList<Nota> listarNotas(){
        return notaDAO.listarNotas();
    }
    public ArrayList<String> listaTitulosNotas(){
        ArrayList<Nota> notas = this.listarNotas();
        ArrayList<String> titulosNotas = new ArrayList<>();
        for(Nota n: notas){
            titulosNotas.add(n.titulo);
        }
        return titulosNotas;
    }
}
