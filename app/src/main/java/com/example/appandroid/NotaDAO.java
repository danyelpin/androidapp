package com.example.appandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class NotaDAO {
    private SQLiteDatabase db;

    public NotaDAO(Context context){
        db=context.openOrCreateDatabase("banco.db", Context.MODE_PRIVATE,null);
        db.execSQL("INSERT INTO notas (id, titulo, conteudo) VALUES (1, 'nota 1' ," +
                "'Conteudo da nota 1')");
    }

    public Nota insereNota(Nota n){
        if(n!=null){
            ContentValues contentValues = new ContentValues();
            //contentValues.put("id",1);
            contentValues.put("titulo", n.titulo);
            contentValues.put("conteudo", n.conteudo);
            int id = (int) db.insert("notas",null,contentValues);
            return new Nota(id,n.titulo,n.conteudo);
        }
        return null;

    }

    public void deleteNota(int id){
        Cursor c = db.rawQuery("SELECT * FROM notas WHERE id=?", new String[]{Integer.toString(id)});
        if(c.moveToFirst()){
            db.execSQL("DELETE FROM notas WHERE id=?", new String[]{Integer.toString(id)});
        }
        c.close();
    }



    public Nota getNota(int id){
        Cursor c = db.rawQuery("SELECT * FROM notas WHERE id=?", new String[]{Integer.toString(id)});
        c.moveToFirst();
        if (c.getCount()>0){
            return new Nota(c.getInt(0),c.getString(1),c.getString(2));
        }
        return null;
    }
    public ArrayList<Nota> listarNotas(){
        ArrayList result = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM notas", null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            result.add(new Nota(c.getInt(0), c.getString(1), c.getString(2)));
            c.moveToNext();
        }
        //Rawquery
        return result;
    }

}
