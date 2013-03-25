package ur.informaticamovil7.movies;

import java.util.List;
import java.util.Random;
import java.util.Vector;

import Utils.GestorJuego;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;

public class JuegoNivel1 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_juego_nivel1);
		
		Typeface pacifico = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
        Typeface walkway_semibold = Typeface.createFromAsset(getAssets(), "fonts/Walkway_SemiBold.ttf");
        
        TextView tv_respuesta1 = (TextView) findViewById(R.id.bt_nivel1_respuesta1);
		TextView tv_respuesta2 = (TextView) findViewById(R.id.bt_nivel1_respuesta2);
		TextView tv_respuesta3 = (TextView) findViewById(R.id.bt_nivel1_respuesta3);
		TextView tv_respuesta4 = (TextView) findViewById(R.id.bt_nivel1_respuesta4);
		
		TextView tv_nivel1_racha = (TextView) findViewById(R.id.tv_nivel1_racha);
		TextView tv_nivel1_highscore = (TextView) findViewById(R.id.tv_nivel1_highscore);
		TextView tv_nivel1_numpregunta = (TextView) findViewById(R.id.tv_nivel1_numpregunta);
		
		ImageView movie_image= (ImageView) findViewById(R.id.iv_nivel1_imagen);
		
		tv_respuesta1.setTypeface(walkway_semibold);
		tv_respuesta2.setTypeface(walkway_semibold);
		tv_respuesta3.setTypeface(walkway_semibold);
		tv_respuesta4.setTypeface(walkway_semibold);
		
		tv_nivel1_racha.setTypeface(pacifico);
		tv_nivel1_highscore.setTypeface(pacifico);
		tv_nivel1_numpregunta.setTypeface(pacifico);
		
		GestorJuego gestorJuego=new GestorJuego();
		
		gestorJuego.cargarMemoria("nivel1");
		
		/*String[] dei = gestorJuego.peliculaYTitulosCorrectoYAleatorios();
		
		movie_image.setImageDrawable(Drawable.createFromPath("WMImages/01Fotogramas/"+dei[0]));
	
		Random r = new Random();
		
		int posicion=(r.nextInt(4))+1;
		
		List<Integer> a=new Vector<Integer>();
		
		a.add(posicion);
		
		tv_respuesta1.setText(dei[posicion]);
		
		posicion=(r.nextInt(4))+1;
		
		while(a.contains(posicion))
		{
			posicion=(r.nextInt(4))+1;
		}
		
		tv_respuesta2.setText(dei[posicion]);
		
		posicion=(r.nextInt(4))+1;
		
		while(a.contains(posicion))
		{
			posicion=(r.nextInt(4))+1;
		}
		
		tv_respuesta3.setText(dei[posicion]);
		
		posicion=(r.nextInt(4))+1;
		
		while(a.contains(posicion))
		{
			posicion=(r.nextInt(4))+1;
		}
		
		tv_respuesta4.setText(dei[posicion]);*/
		
		// Show the Up button in the action bar.
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.juego_nivel1, menu);
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
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
