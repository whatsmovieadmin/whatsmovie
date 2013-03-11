package ur.informaticamovil7.movies;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;

@SuppressLint("WorldReadableFiles")
public class Opciones extends Activity {

	SoundManager snd;
	int button1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opciones);
		
		snd = new SoundManager(getApplicationContext());
		button1 = snd.load(R.raw.button3);
		
		// Show the Up button in the action bar.
		setupActionBar();
		
		Button bt_editar = (Button) findViewById(R.id.bt_opciones_editar);
		bt_editar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	snd.play(button1);
            	
            	Intent myIntent = new Intent(view.getContext(), Editar.class);
                startActivityForResult(myIntent, 0);
            }
        });
		
		Button bt_ranking = (Button) findViewById(R.id.bt_opciones_logros);
		bt_ranking.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	snd.play(button1);
            }
        });
		
		Button bt_estadisticas = (Button) findViewById(R.id.bt_opciones_stats);
		bt_estadisticas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	snd.play(button1);
            }
        });
		
		Button bt_info = (Button) findViewById(R.id.bt_opciones_instrucciones);
		bt_info.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	snd.play(button1);
            	
            	Intent myIntent = new Intent(view.getContext(), Instrucciones.class);
                startActivityForResult(myIntent, 0);
            }
        });
        
		Button bt_salir = (Button) findViewById(R.id.bt_opciones_salir);
		bt_salir.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("deprecation")
			public void onClick(View view) {
            	snd.play(button1);
            	
            	AlertDialog alerta = new AlertDialog.Builder(Opciones.this).create();
            	alerta.setTitle("Salir de la aplicación");
            	alerta.setMessage("¿Está seguro que desea salir?");
            	alerta.setButton("Sí", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
		            	
						SharedPreferences myPrefs = Opciones.this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
                        SharedPreferences.Editor prefsEditor = myPrefs.edit();
                        prefsEditor.putString("LOGIN_EMAIL", "");
                        prefsEditor.putString("LOGIN_PSWD", "");
                        prefsEditor.putBoolean("REMEMBER", false);
                        prefsEditor.commit();
						
		                Intent myIntent = new Intent(Opciones.this, Principal.class);
		                startActivityForResult(myIntent, 0);
					}
				});
            	alerta.setButton2("No", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
				});
            	
            	alerta.show();
            }
        });
        
		Button bt_acercade = (Button) findViewById(R.id.bt_opciones_acercade);
        bt_acercade.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	snd.play(button1);
            	
            	Intent myIntent = new Intent(view.getContext(), AcercaDe.class);
                startActivityForResult(myIntent, 0);
            }
        });
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.opciones, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
