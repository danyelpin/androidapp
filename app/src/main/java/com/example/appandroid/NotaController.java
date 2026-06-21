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
        return true;
    }
    public Nota getNota(int id){
        return notaDAO.getNota(id);
    }
    public boolean updateNota(Nota n){
        Nota notaAtualizada = notaDAO.updateNota(n);
        return notaAtualizada != null;
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
