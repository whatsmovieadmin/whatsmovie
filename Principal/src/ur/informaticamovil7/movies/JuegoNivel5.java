package ur.informaticamovil7.movies;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import Utils.GestorJuego;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;

public class JuegoNivel5 extends Activity {

	private String correcta;
	private int numeroPregunta=1;
	private int numeroRacha=0;
	private int numeroPuntuacion=0;
	private int mejorRacha = 0;
	private int numCorrectas = 0;
	private String carpeta="05Malos";
	
	private TextView countdown;
	private TextView tv_respuesta1;
	private TextView tv_respuesta2;
	private TextView tv_respuesta3;
	private TextView tv_respuesta4;
	private ImageView imageView;
	private Animation fadeOutAnimation;
	private Animation fadeInAnimation;
	private TextView tv_nivel5_racha;
	private TextView tv_nivel5_highscore;
	private TextView tv_nivel5_numpregunta;
	private GestorJuego gestorJuego;
	private MyCount counter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_juego_nivel5);
		
		Typeface sansation = Typeface.createFromAsset(getAssets(), "fonts/Sansation_Regular.ttf");
		
		tv_respuesta1 = (TextView) findViewById(R.id.bt_nivel5_respuesta1);
		tv_respuesta2 = (TextView) findViewById(R.id.bt_nivel5_respuesta2);
		tv_respuesta3 = (TextView) findViewById(R.id.bt_nivel5_respuesta3);
		tv_respuesta4 = (TextView) findViewById(R.id.bt_nivel5_respuesta4);
		
		imageView = (ImageView) findViewById(R.id.iv_nivel5_imagen);
		
		countdown = (TextView) findViewById(R.id.countdown);
		
		fadeOutAnimation = AnimationUtils.loadAnimation(JuegoNivel5.this, R.anim.fadeout);
    	fadeInAnimation = AnimationUtils.loadAnimation(JuegoNivel5.this, R.anim.fadein);
		
		tv_nivel5_racha = (TextView) findViewById(R.id.tv_nivel5_racha);
		tv_nivel5_highscore = (TextView) findViewById(R.id.tv_nivel5_highscore);
		tv_nivel5_numpregunta = (TextView) findViewById(R.id.tv_nivel5_numpregunta);
		
		tv_respuesta1.setTypeface(sansation);
		tv_respuesta2.setTypeface(sansation);
		tv_respuesta3.setTypeface(sansation);
		tv_respuesta4.setTypeface(sansation);
		
		counter = new MyCount(11000,1000);
		
		tv_nivel5_racha.setTypeface(sansation);
		tv_nivel5_highscore.setTypeface(sansation);
		tv_nivel5_numpregunta.setTypeface(sansation);
		
		gestorJuego=new GestorJuego();
		gestorJuego.cargarMemoria("nivel5",this.getApplicationContext());	
		
		String[] dei = gestorJuego.peliculaYTitulosCorrectoYAleatorios();
		imageView.setImageBitmap(getBitmapFromAsset(carpeta+"/"+dei[0]));
		Random r = new Random();
		
		tv_nivel5_numpregunta.setText(numeroPregunta+"/15");
		tv_nivel5_highscore.setText(numeroPuntuacion+"");
		tv_nivel5_racha.setText(numeroRacha+"X");
		correcta=dei[1];
		
		int posicion=((r.nextInt(4))+1);
		List<Integer> a=new Vector<Integer>();
		a.add(posicion);
		tv_respuesta1.setText(dei[posicion]);
		
		posicion=((r.nextInt(4))+1);
		
		while(a.contains(posicion))
		{
			posicion=((r.nextInt(4))+1);
		}
		
		tv_respuesta2.setText(dei[posicion]);
		
		a.add(posicion);
		
		posicion=((r.nextInt(4))+1);
		
		while(a.contains(posicion))
		{
			posicion=((r.nextInt(4))+1);
		}
		
		tv_respuesta3.setText(dei[posicion]);
		
		a.add(posicion);
		
		posicion=((r.nextInt(4))+1);
		
		while(a.contains(posicion))
		{
			posicion=((r.nextInt(4))+1);
		}
		
		tv_respuesta4.setText(dei[posicion]);
		
		counter.start();
		
		tv_respuesta1.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {
            	
            	counter.cancel();

            	tv_respuesta1.setClickable(false);
				tv_respuesta1.setEnabled(false);
				tv_respuesta2.setClickable(false);
				tv_respuesta2.setEnabled(false);
				tv_respuesta3.setClickable(false);
				tv_respuesta3.setEnabled(false);
				tv_respuesta4.setClickable(false);
				tv_respuesta4.setEnabled(false);
				
            	if(tv_respuesta1.getText().toString().compareTo(correcta)==0)
            	{
            		tv_respuesta1.setBackgroundResource(R.anim.main_button_respuesta_correcta);
            		numCorrectas++;
            		numeroRacha=numeroRacha+1;
            		
            		if(mejorRacha < numeroRacha) {
						mejorRacha = numeroRacha;
					}
            		
            		numeroPuntuacion=numeroPuntuacion+(numeroRacha*100);
            	}
            	else
            	{
            		tv_respuesta1.setBackgroundResource(R.anim.main_button_respuesta_fallida);
            		numeroRacha=0;
            		numeroPuntuacion=numeroPuntuacion+(numeroRacha*100);
            		
            	}
            	
            	numeroPregunta=numeroPregunta+1;
            	
            	view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    	
                    	if (numeroPregunta > 15) {
							Intent myIntent = new Intent(JuegoNivel5.this,
									Resultados.class);
							myIntent.putExtra("numcorrectas", numCorrectas);
							myIntent.putExtra("racha", mejorRacha);
							myIntent.putExtra("puntuacion", numeroPuntuacion);
							myIntent.putExtra("nivel", 5);
							startActivity(myIntent);
						} else {

							imageView.startAnimation(fadeOutAnimation);
							tv_respuesta1.startAnimation(fadeOutAnimation);
							tv_respuesta2.startAnimation(fadeOutAnimation);
							tv_respuesta3.startAnimation(fadeOutAnimation);
							tv_respuesta4.startAnimation(fadeOutAnimation);
						}
                    }
                }, 1500);
            	
            	view.postDelayed(new Runnable() {
                    @SuppressLint("NewApi")
					@Override
                    public void run() {
                    	
                    	String[] dei = gestorJuego.peliculaYTitulosCorrectoYAleatorios();
                		
                		imageView.setImageBitmap(getBitmapFromAsset(carpeta+"/"+dei[0]));
                	
                		Random r = new Random();
                		
                		correcta=dei[1];
                		
                		int posicion=((r.nextInt(4))+1);
                		
                		List<Integer> a=new Vector<Integer>();
                		
                		a.add(posicion);
                		
                		tv_respuesta1.setText(dei[posicion]);
                		
                		posicion=((r.nextInt(4))+1);
                		
                		while(a.contains(posicion))
                		{
                			posicion=((r.nextInt(4))+1);
                		}
                		
                		tv_respuesta2.setText(dei[posicion]);
                		
                		a.add(posicion);
                		
                		posicion=((r.nextInt(4))+1);
                		
                		while(a.contains(posicion))
                		{
                			posicion=((r.nextInt(4))+1);
                		}
                		
                		tv_respuesta3.setText(dei[posicion]);
                		
                		a.add(posicion);
                		
                		posicion=((r.nextInt(4))+1);
                		
                		while(a.contains(posicion))
                		{
                			posicion=((r.nextInt(4))+1);
                		}
                		
                		tv_respuesta4.setText(dei[posicion]);
                		
                		tv_nivel5_numpregunta.setText(numeroPregunta+"/15");
                		
                		tv_nivel5_racha.setText(numeroRacha+"X");
                    	
                    	tv_respuesta1.setBackgroundResource(R.anim.main_button_respuesta);

                        imageView.startAnimation(fadeInAnimation);
                        tv_respuesta1.startAnimation(fadeInAnimation);
                        tv_respuesta2.startAnimation(fadeInAnimation);
                        tv_respuesta3.startAnimation(fadeInAnimation);
                        tv_respuesta4.startAnimation(fadeInAnimation);

						int anterior = Integer.parseInt((String)tv_nivel5_highscore.getText());
						
						if(Build.VERSION.SDK_INT >= 11.0){
							ValueAnimator va = ValueAnimator.ofInt(anterior, numeroPuntuacion);
						    va.setDuration(700);
						    va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
						        public void onAnimationUpdate(ValueAnimator animation) {
						            Integer value = (Integer) animation.getAnimatedValue();
						            tv_nivel5_highscore.setText(value + "");
						        }
						    });
						    
						    va.start();
						} else {
							tv_nivel5_highscore.setText(numeroPuntuacion + "");
						}
                        
                        tv_respuesta1.setClickable(true);
						tv_respuesta1.setEnabled(true);
						tv_respuesta2.setClickable(true);
						tv_respuesta2.setEnabled(true);
						tv_respuesta3.setClickable(true);
						tv_respuesta3.setEnabled(true);
						tv_respuesta4.setClickable(true);
						tv_respuesta4.setEnabled(true);
						
						if (numeroPregunta <= 15) {
							counter.start();
						}
                    }
                }, 3000);
            }
        });
		
		tv_respuesta2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	
            	counter.cancel();
            	
            	tv_respuesta1.setClickable(false);
				tv_respuesta1.setEnabled(false);
				tv_respuesta2.setClickable(false);
				tv_respuesta2.setEnabled(false);
				tv_respuesta3.setClickable(false);
				tv_respuesta3.setEnabled(false);
				tv_respuesta4.setClickable(false);
				tv_respuesta4.setEnabled(false);
				
            	if(tv_respuesta2.getText().toString().compareTo(correcta)==0)
            	{
            		tv_respuesta2.setBackgroundResource(R.anim.main_button_respuesta_correcta);
            		numCorrectas++;
            		numeroRacha=numeroRacha+1;
            		
            		if(mejorRacha < numeroRacha) {
						mejorRacha = numeroRacha;
					}
            		
            		numeroPuntuacion=numeroPuntuacion+(numeroRacha*100);
            	}
            	else
            	{
            		tv_respuesta2.setBackgroundResource(R.anim.main_button_respuesta_fallida);
            		numeroRacha=0;
            		numeroPuntuacion=numeroPuntuacion+(numeroRacha*100);
            		
            	}
            	
            	numeroPregunta=numeroPregunta+1;
            	
            	view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    	
                    	if (numeroPregunta > 15) {
							Intent myIntent = new Intent(JuegoNivel5.this,
									Resultados.class);
							myIntent.putExtra("numcorrectas", numCorrectas);
							myIntent.putExtra("racha", mejorRacha);
							myIntent.putExtra("puntuacion", numeroPuntuacion);
							myIntent.putExtra("nivel", 5);
							startActivity(myIntent);
						} else {

							imageView.startAnimation(fadeOutAnimation);
							tv_respuesta1.startAnimation(fadeOutAnimation);
							tv_respuesta2.startAnimation(fadeOutAnimation);
							tv_respuesta3.startAnimation(fadeOutAnimation);
							tv_respuesta4.startAnimation(fadeOutAnimation);
						}
                    }
                }, 1500);
            	
            	view.postDelayed(new Runnable() {
                    @SuppressLint("NewApi")
					@Override
                    public void run() {
                    	
                    	String[] dei = gestorJuego.peliculaYTitulosCorrectoYAleatorios();
                		
                		imageView.setImageBitmap(getBitmapFromAsset(carpeta+"/"+dei[0]));
                	
                		Random r = new Random();
                		
                		correcta=dei[1];
                		
                		int posicion=((r.nextInt(4))+1);
                		
                		List<Integer> a=new Vector<Integer>();
                		
                		a.add(posicion);
                		
                		tv_respuesta1.setText(dei[posicion]);
                		
                		posicion=((r.nextInt(4))+1);
                		
                		while(a.contains(posicion))
                		{
                			posicion=((r.nextInt(4))+1);
                		}
                		
                		tv_respuesta2.setText(dei[posicion]);
                		
                		a.add(posicion);
                		
                		posicion=((r.nextInt(4))+1);
                		
                		while(a.contains(posicion))
                		{
                			posicion=((r.nextInt(4))+1);
                		}
                		
                		tv_respuesta3.setText(dei[posicion]);
                		
                		a.add(posicion);
                		
                		posicion=((r.nextInt(4))+1);
                		
                		while(a.contains(posicion))
                		{
                			posicion=((r.nextInt(4))+1);
                		}
                		
                		tv_respuesta4.setText(dei[posicion]);
                		
                		tv_nivel5_numpregunta.setText(numeroPregunta+"/15");
                		
                		tv_nivel5_racha.setText(numeroRacha+"X");
                		
                    	tv_respuesta2.setBackgroundResource(R.anim.main_button_respuesta);

                        imageView.startAnimation(fadeInAnimation);
                        tv_respuesta1.startAnimation(fadeInAnimation);
                        tv_respuesta2.startAnimation(fadeInAnimation);
                        tv_respuesta3.startAnimation(fadeInAnimation);
                        tv_respuesta4.startAnimation(fadeInAnimation);

						int anterior = Integer.parseInt((String)tv_nivel5_highscore.getText());
						
						if(Build.VERSION.SDK_INT >= 11.0){
							ValueAnimator va = ValueAnimator.ofInt(anterior, numeroPuntuacion);
						    va.setDuration(700);
						    va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
						        public void onAnimationUpdate(ValueAnimator animation) {
						            Integer value = (Integer) animation.getAnimatedValue();
						            tv_nivel5_highscore.setText(value + "");
						        }
						    });
						    
						    va.start();
						} else {
							tv_nivel5_highscore.setText(numeroPuntuacion + "");
						}
                        
                        tv_respuesta1.setClickable(true);
						tv_respuesta1.setEnabled(true);
						tv_respuesta2.setClickable(true);
						tv_respuesta2.setEnabled(true);
						tv_respuesta3.setClickable(true);
						tv_respuesta3.setEnabled(true);
						tv_respuesta4.setClickable(true);
						tv_respuesta4.setEnabled(true);
						
						if (numeroPregunta <= 15) {
							counter.start();
						}
                    }
                }, 3000);
            }
        });
		
		tv_respuesta3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	
            	counter.cancel();
                
            	tv_respuesta1.setClickable(false);
				tv_respuesta1.setEnabled(false);
				tv_respuesta2.setClickable(false);
				tv_respuesta2.setEnabled(false);
				tv_respuesta3.setClickable(false);
				tv_respuesta3.setEnabled(false);
				tv_respuesta4.setClickable(false);
				tv_respuesta4.setEnabled(false);
				
            	if(tv_respuesta3.getText().toString().compareTo(correcta)==0)
            	{
            		tv_respuesta3.setBackgroundResource(R.anim.main_button_respuesta_correcta);
            		numCorrectas++;
            		
            		numeroRacha=numeroRacha+1;
            		
            		if(mejorRacha < numeroRacha) {
						mejorRacha = numeroRacha;
					}
            		
            		numeroPuntuacion=numeroPuntuacion+(numeroRacha*100);
            	}
            	else
            	{
            		tv_respuesta3.setBackgroundResource(R.anim.main_button_respuesta_fallida);
            		numeroRacha=0;
            		numeroPuntuacion=numeroPuntuacion+(numeroRacha*100);
            		
            	}
            	
            	numeroPregunta=numeroPregunta+1;

            	view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    	
                    	if (numeroPregunta > 15) {
							Intent myIntent = new Intent(JuegoNivel5.this,
									Resultados.class);
							myIntent.putExtra("numcorrectas", numCorrectas);
							myIntent.putExtra("racha", mejorRacha);
							myIntent.putExtra("puntuacion", numeroPuntuacion);
							myIntent.putExtra("nivel", 5);
							startActivity(myIntent);
						} else {

							imageView.startAnimation(fadeOutAnimation);
							tv_respuesta1.startAnimation(fadeOutAnimation);
							tv_respuesta2.startAnimation(fadeOutAnimation);
							tv_respuesta3.startAnimation(fadeOutAnimation);
							tv_respuesta4.startAnimation(fadeOutAnimation);
						}
                    }
                }, 1500);
            	
            	view.postDelayed(new Runnable() {
                    @SuppressLint("NewApi")
					@Override
                    public void run() {
                    	
                    	String[] dei = gestorJuego.peliculaYTitulosCorrectoYAleatorios();
                		
                		imageView.setImageBitmap(getBitmapFromAsset(carpeta+"/"+dei[0]));
                	
                		Random r = new Random();
                		
                		correcta=dei[1];
                		
                		int posicion=((r.nextInt(4))+1);
                		
                		List<Integer> a=new Vector<Integer>();
                		
                		a.add(posicion);
                		
                		tv_respuesta1.setText(dei[posicion]);
                		
                		posicion=((r.nextInt(4))+1);
                		
                		while(a.contains(posicion))
                		{
                			posicion=((r.nextInt(4))+1);
                		}
                		
                		tv_respuesta2.setText(dei[posicion]);
                		
                		a.add(posicion);
                		
                		posicion=((r.nextInt(4))+1);
                		
                		while(a.contains(posicion))
                		{
                			posicion=((r.nextInt(4))+1);
                		}
                		
                		tv_respuesta3.setText(dei[posicion]);
                		
                		a.add(posicion);
                		
                		posicion=((r.nextInt(4))+1);
                		
                		while(a.contains(posicion))
                		{
                			posicion=((r.nextInt(4))+1);
                		}
                		
                		tv_respuesta4.setText(dei[posicion]);
                		
                		tv_nivel5_numpregunta.setText(numeroPregunta+"/15");
                		
                		tv_nivel5_racha.setText(numeroRacha+"X");
                    	
                    	tv_respuesta3.setBackgroundResource(R.anim.main_button_respuesta);

                        imageView.startAnimation(fadeInAnimation);
                        tv_respuesta1.startAnimation(fadeInAnimation);
                        tv_respuesta2.startAnimation(fadeInAnimation);
                        tv_respuesta3.startAnimation(fadeInAnimation);
                        tv_respuesta4.startAnimation(fadeInAnimation);

						int anterior = Integer.parseInt((String)tv_nivel5_highscore.getText());
						
						if(Build.VERSION.SDK_INT >= 11.0){
							ValueAnimator va = ValueAnimator.ofInt(anterior, numeroPuntuacion);
						    va.setDuration(700);
						    va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
						        public void onAnimationUpdate(ValueAnimator animation) {
						            Integer value = (Integer) animation.getAnimatedValue();
						            tv_nivel5_highscore.setText(value + "");
						        }
						    });
						    
						    va.start();
						} else {
							tv_nivel5_highscore.setText(numeroPuntuacion + "");
						}
                        
                        tv_respuesta1.setClickable(true);
						tv_respuesta1.setEnabled(true);
						tv_respuesta2.setClickable(true);
						tv_respuesta2.setEnabled(true);
						tv_respuesta3.setClickable(true);
						tv_respuesta3.setEnabled(true);
						tv_respuesta4.setClickable(true);
						tv_respuesta4.setEnabled(true);
						
						if (numeroPregunta <= 15) {
							counter.start();
						}
                    }
                }, 3000);
            }
        });
		
		tv_respuesta4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	
            	counter.cancel();
                
            	tv_respuesta1.setClickable(false);
				tv_respuesta1.setEnabled(false);
				tv_respuesta2.setClickable(false);
				tv_respuesta2.setEnabled(false);
				tv_respuesta3.setClickable(false);
				tv_respuesta3.setEnabled(false);
				tv_respuesta4.setClickable(false);
				tv_respuesta4.setEnabled(false);
				
            	if(tv_respuesta4.getText().toString().compareTo(correcta)==0)
            	{
            		tv_respuesta4.setBackgroundResource(R.anim.main_button_respuesta_correcta);
            		numCorrectas++;
            		numeroRacha=numeroRacha+1;
            		
            		if(mejorRacha < numeroRacha) {
						mejorRacha = numeroRacha;
					}
            		
            		numeroPuntuacion=numeroPuntuacion+(numeroRacha*100);
            	}
            	else
            	{
            		tv_respuesta4.setBackgroundResource(R.anim.main_button_respuesta_fallida);
            		numeroRacha=0;
            		numeroPuntuacion=numeroPuntuacion+(numeroRacha*100);
            		
            	}
            	
            	numeroPregunta=numeroPregunta+1;

            	view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    	if (numeroPregunta > 15) {
							Intent myIntent = new Intent(JuegoNivel5.this,
									Resultados.class);
							myIntent.putExtra("numcorrectas", numCorrectas);
							myIntent.putExtra("racha", mejorRacha);
							myIntent.putExtra("puntuacion", numeroPuntuacion);
							myIntent.putExtra("nivel", 5);
							startActivity(myIntent);
						} else {

							imageView.startAnimation(fadeOutAnimation);
							tv_respuesta1.startAnimation(fadeOutAnimation);
							tv_respuesta2.startAnimation(fadeOutAnimation);
							tv_respuesta3.startAnimation(fadeOutAnimation);
							tv_respuesta4.startAnimation(fadeOutAnimation);
						}
                    }
                }, 1500);
            	
            	view.postDelayed(new Runnable() {
                    @SuppressLint("NewApi")
					@Override
                    public void run() {
                    	
                    	String[] dei = gestorJuego.peliculaYTitulosCorrectoYAleatorios();
                		
                		imageView.setImageBitmap(getBitmapFromAsset(carpeta+"/"+dei[0]));
                	
                		Random r = new Random();
                		
                		correcta=dei[1];
                		
                		int posicion=((r.nextInt(4))+1);
                		
                		List<Integer> a=new Vector<Integer>();
                		
                		a.add(posicion);
                		
                		tv_respuesta1.setText(dei[posicion]);
                		
                		posicion=((r.nextInt(4))+1);
                		
                		while(a.contains(posicion))
                		{
                			posicion=((r.nextInt(4))+1);
                		}
                		
                		tv_respuesta2.setText(dei[posicion]);
                		
                		a.add(posicion);
                		
                		posicion=((r.nextInt(4))+1);
                		
                		while(a.contains(posicion))
                		{
                			posicion=((r.nextInt(4))+1);
                		}
                		
                		tv_respuesta3.setText(dei[posicion]);
                		
                		a.add(posicion);
                		
                		posicion=((r.nextInt(4))+1);
                		
                		while(a.contains(posicion))
                		{
                			posicion=((r.nextInt(4))+1);
                		}
                		
                		tv_respuesta4.setText(dei[posicion]);
                		
                		tv_nivel5_numpregunta.setText(numeroPregunta+"/15");
                		
                		tv_nivel5_racha.setText(numeroRacha+"X");
                    	
                    	tv_respuesta4.setBackgroundResource(R.anim.main_button_respuesta);

                        imageView.startAnimation(fadeInAnimation);
                        tv_respuesta1.startAnimation(fadeInAnimation);
                        tv_respuesta2.startAnimation(fadeInAnimation);
                        tv_respuesta3.startAnimation(fadeInAnimation);
                        tv_respuesta4.startAnimation(fadeInAnimation);

						int anterior = Integer.parseInt((String)tv_nivel5_highscore.getText());
						
						if(Build.VERSION.SDK_INT >= 11.0){
							ValueAnimator va = ValueAnimator.ofInt(anterior, numeroPuntuacion);
						    va.setDuration(700);
						    va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
						        public void onAnimationUpdate(ValueAnimator animation) {
						            Integer value = (Integer) animation.getAnimatedValue();
						            tv_nivel5_highscore.setText(value + "");
						        }
						    });
						    
						    va.start();
						} else {
							tv_nivel5_highscore.setText(numeroPuntuacion + "");
						}
                        
                        tv_respuesta1.setClickable(true);
						tv_respuesta1.setEnabled(true);
						tv_respuesta2.setClickable(true);
						tv_respuesta2.setEnabled(true);
						tv_respuesta3.setClickable(true);
						tv_respuesta3.setEnabled(true);
						tv_respuesta4.setClickable(true);
						tv_respuesta4.setEnabled(true);
						
						if (numeroPregunta <= 15) {
							counter.start();
						}
                    }
                }, 3000);
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
		getMenuInflater().inflate(R.menu.juego_nivel5, menu);
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
	
	private Bitmap getBitmapFromAsset(String strName)
    {
        AssetManager assetManager = getAssets();
        InputStream istr = null;
        try {
            istr = assetManager.open(strName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(istr);
        return bitmap;
    }
	
	public class MyCount extends CountDownTimer
	{

		public MyCount(long millisInFuture, long countDownInterval) 
		{
			super(millisInFuture, countDownInterval);
		}

		public void onFinish()
		{
			countdown.setText("00");
			counter.cancel();
			
			tv_respuesta1.setClickable(false);
			tv_respuesta1.setEnabled(false);
			tv_respuesta2.setClickable(false);
			tv_respuesta2.setEnabled(false);
			tv_respuesta3.setClickable(false);
			tv_respuesta3.setEnabled(false);
			tv_respuesta4.setClickable(false);
			tv_respuesta4.setEnabled(false);
			
			
			numeroRacha = 0;
			numeroPuntuacion = numeroPuntuacion + (numeroRacha * 100);

			numeroPregunta = numeroPregunta + 1;
			Handler handler = new Handler();
			
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {

					if (numeroPregunta > 15) {
						Intent myIntent = new Intent(JuegoNivel5.this,
								Resultados.class);
						myIntent.putExtra("numcorrectas", numCorrectas);
						myIntent.putExtra("racha", mejorRacha);
						myIntent.putExtra("puntuacion", numeroPuntuacion);
						myIntent.putExtra("nivel", 5);
						startActivity(myIntent);
					} else {

						imageView.startAnimation(fadeOutAnimation);
						tv_respuesta1.startAnimation(fadeOutAnimation);
						tv_respuesta2.startAnimation(fadeOutAnimation);
						tv_respuesta3.startAnimation(fadeOutAnimation);
						tv_respuesta4.startAnimation(fadeOutAnimation);
					}
				}
			}, 1500);

			handler.postDelayed(new Runnable() {
				@SuppressLint("NewApi")
				@Override
				public void run() {

					String[] dei = gestorJuego
							.peliculaYTitulosCorrectoYAleatorios();

					imageView.setImageBitmap(getBitmapFromAsset(carpeta
							+ "/" + dei[0]));

					Random r = new Random();

					correcta = dei[1];

					int posicion = ((r.nextInt(4)) + 1);

					List<Integer> a = new Vector<Integer>();

					a.add(posicion);

					tv_respuesta1.setText(dei[posicion]);

					posicion = ((r.nextInt(4)) + 1);

					while (a.contains(posicion)) {
						posicion = ((r.nextInt(4)) + 1);
					}

					tv_respuesta2.setText(dei[posicion]);

					a.add(posicion);

					posicion = ((r.nextInt(4)) + 1);

					while (a.contains(posicion)) {
						posicion = ((r.nextInt(4)) + 1);
					}

					tv_respuesta3.setText(dei[posicion]);

					a.add(posicion);

					posicion = ((r.nextInt(4)) + 1);

					while (a.contains(posicion)) {
						posicion = ((r.nextInt(4)) + 1);
					}

					tv_respuesta4.setText(dei[posicion]);

					tv_nivel5_numpregunta.setText(numeroPregunta
							+ "/15");

					tv_nivel5_racha.setText(numeroRacha + "X");

					imageView.startAnimation(fadeInAnimation);
					tv_respuesta1.startAnimation(fadeInAnimation);
					tv_respuesta2.startAnimation(fadeInAnimation);
					tv_respuesta3.startAnimation(fadeInAnimation);
					tv_respuesta4.startAnimation(fadeInAnimation);
					
					int anterior = Integer.parseInt((String)tv_nivel5_highscore.getText());
					
					if(Build.VERSION.SDK_INT >= 11.0){
						ValueAnimator va = ValueAnimator.ofInt(anterior, numeroPuntuacion);
					    va.setDuration(700);
					    va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					        public void onAnimationUpdate(ValueAnimator animation) {
					            Integer value = (Integer) animation.getAnimatedValue();
					            tv_nivel5_highscore.setText(value + "");
					        }
					    });
					    
					    va.start();
					} else {
						tv_nivel5_highscore.setText(numeroPuntuacion + "");
					}
					
					tv_respuesta1.setClickable(true);
					tv_respuesta1.setEnabled(true);
					tv_respuesta2.setClickable(true);
					tv_respuesta2.setEnabled(true);
					tv_respuesta3.setClickable(true);
					tv_respuesta3.setEnabled(true);
					tv_respuesta4.setClickable(true);
					tv_respuesta4.setEnabled(true);
					
					if (numeroPregunta <= 15) {
						counter.start();
					}
				
				}
			}, 3000);	
			
		}

		public void onTick(long millisUntilFinished) 
		{
			if(millisUntilFinished/1000 < 6) {
				countdown.setTextColor(Color.RED);
			} else {
				countdown.setTextColor(Color.parseColor("#373737"));
				
			}
			if (millisUntilFinished/1000 == 10) {
				countdown.setText(""+millisUntilFinished/1000);
			} else {
				countdown.setText("0"+millisUntilFinished/1000);
			}
			

		}
	}

}
