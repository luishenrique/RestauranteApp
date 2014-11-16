package Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import Model.*;

public class RestauranteDao extends SQLiteOpenHelper {

    private static final int VERSAO = 2;
    private static final String TABELA = "Restaurante";
    private static final String[] COLS = {"nome", "categoria", "telefone", "endereco", "cidade", "uf"};

    public RestauranteDao(Context context) {
        super(context, TABELA, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABELA + " ");        
        sb.append("(nome TEXT UNIQUE NOT NULL, ");
        sb.append(" categoria TEXT, ");
        sb.append(" telefone TEXT, ");
        sb.append(" endereco TEXT, ");
        sb.append(" cidade TEXT, ");
        sb.append(" uf TEXT);");
        db.execSQL(sb.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE IF EXISTS " + TABELA);
        db.execSQL(sb.toString());
        onCreate(db);

    }

    public void inserir(Restaurante restaurante) {
        ContentValues values = new ContentValues();
        values.put("nome", restaurante.getNome());
        values.put("categoria", restaurante.getCategoria());
        values.put("telefone", restaurante.getTelefone());
        values.put("endereco", restaurante.getEndereco());
        values.put("cidade", restaurante.getCidade());
        values.put("uf", restaurante.getUf());

        getWritableDatabase().insert(TABELA, null, values);
    }

    public List<Restaurante> getLista(){
        List<Restaurante> restaurantes = new ArrayList<Restaurante>();

        Cursor c = getWritableDatabase().query(TABELA,COLS, null, null, null, null, null);

        while(c.moveToNext()){
        	Restaurante restaurante = new Restaurante();        	
        	restaurante.setNome(c.getString(0));
        	restaurante.setCategoria(c.getString(1));
        	restaurante.setTelefone(c.getString(2));
        	restaurante.setEndereco(c.getString(3));
        	restaurante.setCidade(c.getString(4));
        	restaurante.setUf(c.getString(5));
        	restaurantes.add(restaurante);            
        }
        c.close();

        return restaurantes;
    }
}