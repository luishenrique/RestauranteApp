package com.example.restauranteapp;

import java.util.ArrayList;
import java.util.List;

import Model.Restaurante;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import Dao.*;


public class MainActivity extends Activity {

	List<Restaurante> listaRestaurante;
	private ListView lista;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lista = (ListView) findViewById(R.id.lista_de_restaurantes);
		listar(null);
		
		lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	        
			@Override
	        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
	        	Restaurante restaurante = (Restaurante) lista.getItemAtPosition(position);	                        
	            Toast.makeText(getApplicationContext(), "Telefone: " + restaurante.getTelefone(), Toast.LENGTH_SHORT).show();
	        }
	    });		
	}	
	
	public void listar(View v){		
		RestauranteDao dao = new RestauranteDao(this);
		listaRestaurante = dao.getLista();			
		dao.close();				
		ArrayAdapter<Restaurante> adapter = new ArrayAdapter<Restaurante>(MainActivity.this, android.R.layout.simple_list_item_1, listaRestaurante);
		lista.setAdapter(adapter);
		lista.setClickable(true);	
	}
		
	public void onResume(){
		super.onResume();
		listar(null);
	}
	
	public void AbrirCadastro(View v){
    	Intent intent = new Intent(MainActivity.this, Cadastro.class);
    	startActivity(intent);
    }  

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
