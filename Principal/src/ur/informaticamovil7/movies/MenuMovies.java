package ur.informaticamovil7.movies;

import Utils.SoundManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;

public class MenuMovies extends Activity {

	SoundManager snd;
	MediaPlayer mp;
	int claqueta, button1;
	
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		snd = new SoundManager(getApplicationContext());
		claqueta = snd.load(R.raw.claqueta);
		button1 = snd.load(R.raw.button3);
		//mp = MediaPlayer.create(this, R.raw.kalimba);
		//mp.setLooping(true);
		//mp.setVolume(0.5f, 0.5f);
		
//		if (!mp.isPlaying()) {
//			mp.start();
//		}
		
		if (!networkAvailable()) {
			AlertDialog alerta = new AlertDialog.Builder(MenuMovies.this).create();
        	alerta.setTitle("Conexión a internet deshabilitada");
        	alerta.setMessage("What's Movie necesita conexión a internet para funcionar");
        	alerta.setButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
				}
			});
        	
        	alerta.show();
		}
		
		Button bt_play = (Button) findViewById(R.id.bt_menu_play);
        bt_play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                snd.play(claqueta);
                Intent myIntent = new Intent(view.getContext(), MenuNiveles.class);
                startActivityForResult(myIntent, 0);
            }
        });
        
		Button bt_opciones = (Button) findViewById(R.id.bt_menu_opciones);
		bt_opciones.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	snd.play(button1);
            	
                Intent myIntent = new Intent(view.getContext(), Opciones.class);
                startActivityForResult(myIntent, 0);
            }
        });
        
		Button bt_amigos = (Button) findViewById(R.id.bt_menu_director);
		bt_amigos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	snd.play(button1);
            	
            	Intent myIntent = new Intent(view.getContext(), Amigos.class);
                startActivityForResult(myIntent, 0);
            }
        });
		
		setupActionBar();
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


	public boolean onCreateOptionsMenu(MenuMovies menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, (android.view.Menu) menu);
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
	
	public boolean networkAvailable() {
		Context context = getApplicationContext();
		ConnectivityManager connectMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectMgr != null) {
			NetworkInfo[] netInfo = connectMgr.getAllNetworkInfo();
			if (netInfo != null) {
				for (NetworkInfo net : netInfo) {
					if (net.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		
		return false;
	}

}
