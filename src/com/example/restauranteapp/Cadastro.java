package com.example.restauranteapp;

import java.util.ArrayList;
import java.util.List;

import Model.Restaurante;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import Dao.*;

public class Cadastro extends Activity {

	Restaurante restaurante;	
	
	Spinner uf;
	Spinner categoria;
	Spinner cidade;
	EditText nome;
	EditText telefone;
	EditText endereco;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro);		
		
		//categorias
		String[] cat = {"Churrascaria", "Chopperia", "Mexicana", "Tailandesa", "Baiana", "Mineira"};        
        ArrayAdapter<String> adpter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, cat);        
        categoria = (Spinner) findViewById(R.id.categoria);
        categoria.setAdapter(adpter);
        
        //cidades
        String[] cid = {"São João da Boa Vista", "Vargem Grande do Sul", "Santa Cruz das Palmeiras"};
        adpter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, cid);        
        cidade = (Spinner) findViewById(R.id.cidade);
        cidade.setAdapter(adpter);
        
        //uf
        String[] ufs = {"SP", "RJ"};        
        adpter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, ufs);        
        uf = (Spinner) findViewById(R.id.uf);
        uf.setAdapter(adpter);
        
        nome = (EditText) findViewById(R.id.nome);
        telefone = (EditText) findViewById(R.id.telefone);
        endereco = (EditText) findViewById(R.id.endereco); 
        Button cadastrar = (Button) findViewById(R.id.cadastrar); 
        
        cadastrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	restaurante = new Restaurante();
                restaurante.setNome(nome.getText().toString());
                restaurante.setEndereco(endereco.getText().toString());
                restaurante.setTelefone(telefone.getText().toString());
            	restaurante.setCategoria(categoria.getSelectedItem().toString());
                restaurante.setCidade(cidade.getSelectedItem().toString());
                restaurante.setUf(uf.getSelectedItem().toString());
                RestauranteDao dao = new RestauranteDao(Cadastro.this);
                dao.inserir(restaurante);
                dao.close();
                finish();
            }
        });        
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastro, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
