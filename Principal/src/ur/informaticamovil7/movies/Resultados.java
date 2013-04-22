package ur.informaticamovil7.movies;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;

public class Resultados extends Activity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resultados);
		// Show the Up button in the action bar.
		
		Typeface sansation = Typeface.createFromAsset(getAssets(), "fonts/Sansation_Regular.ttf");
		
		TextView tv_enhorabuena = (TextView)findViewById(R.id.tv_enhorabuena);
		TextView tv_numaciertos = (TextView)findViewById(R.id.tv_resultados_numaciertos);
		TextView tv_nuevaracha = (TextView)findViewById(R.id.tv_resultados_nuevaracha);
		final TextView tv_racha = (TextView)findViewById(R.id.tv_resultados_racha);
		TextView tv_nuevapuntuacion = (TextView)findViewById(R.id.tv_resultados_nuevapuntuacion);
		final TextView tv_puntuacion = (TextView)findViewById(R.id.tv_resultados_puntuacion);
		
		tv_enhorabuena.setTypeface(sansation);
		tv_numaciertos.setTypeface(sansation);
		tv_numaciertos.setTypeface(sansation);
		tv_nuevaracha.setTypeface(sansation);
		tv_racha.setTypeface(sansation);
		tv_nuevapuntuacion.setTypeface(sansation);
		tv_puntuacion.setTypeface(sansation);
		
		final Bundle bundle = getIntent().getExtras();
		
		int nivel = bundle.getInt("nivel");
		if (nivel == 6) {
			tv_numaciertos.setText("Has acertado "+bundle.getInt("numcorrectas")+" preguntas");
		} else {
			tv_numaciertos.setText("Has acertado "+bundle.getInt("numcorrectas")+"/15 preguntas");
		}
		
		if(Build.VERSION.SDK_INT >= 11.0){
			ValueAnimator va = ValueAnimator.ofInt(0, bundle.getInt("racha"));
		    va.setDuration(700);
		    va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
		        public void onAnimationUpdate(ValueAnimator animation) {
		            Integer value = (Integer) animation.getAnimatedValue();
		            tv_racha.setText(value+"X");
		        }
		    });
		    
		    va.start();
		} else {
			tv_racha.setText(bundle.getInt("racha")+"X");
		}

		if(Build.VERSION.SDK_INT >= 11.0){
			ValueAnimator va = ValueAnimator.ofInt(0, bundle.getInt("puntuacion"));
		    va.setDuration(700);
		    va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
		        public void onAnimationUpdate(ValueAnimator animation) {
		            Integer value = (Integer) animation.getAnimatedValue();
		            tv_puntuacion.setText(value+"");
		        }
		    });
		    
		    va.start();
		} else {
			tv_puntuacion.setText(bundle.getInt("puntuacion")+"");
		}
		
		Button bt_repetir = (Button) findViewById(R.id.bt_resultados_repetir);
		Button bt_continuar = (Button) findViewById(R.id.bt_resultados_continuar);
		
		bt_repetir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	
            		int nivel = bundle.getInt("nivel");
        			if (nivel == 1) {
        				Intent myIntent = new Intent(view.getContext(), JuegoNivel1.class);
                        startActivityForResult(myIntent, 0);
        			} else if (nivel == 2) {
        				Intent myIntent = new Intent(view.getContext(), JuegoNivel2.class);
                        startActivityForResult(myIntent, 0);
        			} else if (nivel == 3) {
        				Intent myIntent = new Intent(view.getContext(), JuegoNivel3.class);
                        startActivityForResult(myIntent, 0);
        			} else if (nivel == 4) {
        				Intent myIntent = new Intent(view.getContext(), JuegoNivel4.class);
                        startActivityForResult(myIntent, 0);
        			} else if (nivel == 5) {
        				Intent myIntent = new Intent(view.getContext(), JuegoNivel5.class);
                        startActivityForResult(myIntent, 0);
        			} else if (nivel == 6) {
        				Intent myIntent = new Intent(view.getContext(), JuegoNivel6.class);
                        startActivityForResult(myIntent, 0);
        			}
            }
        });
		
		bt_continuar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MenuNiveles.class);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.resultados, menu);
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
