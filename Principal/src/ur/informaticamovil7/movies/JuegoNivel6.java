package ur.informaticamovil7.movies;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
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
import android.widget.RelativeLayout;
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

public class JuegoNivel6 extends Activity {

	private String correcta;
	private int numeroPregunta = 1;
	private boolean fallada;
	private int numeroRacha=0;
	private int numeroPuntuacion=0;
	private int mejorRacha = 0;
	private int numCorrectas = 0;
	private int nivelactual;
	
	private TextView countdown;
	private TextView tv_respuesta1;
	private TextView tv_respuesta2;
	private TextView tv_respuesta3;
	private TextView tv_respuesta4;
	private RelativeLayout rl6;
	private ImageView imageView;
	private ImageView pin;
	private TextView textopregunta;
	private Animation fadeOutAnimation;
	private Animation fadeInAnimation;
	private TextView tv_nivel6_racha;
	private TextView tv_nivel6_highscore;
	private TextView tv_nivel6_numpregunta;
	private GestorJuego gestorJuego;
	private HashMap<Integer, String> niveles;
	private HashMap<Integer, String> carpetas;
	private MyCount counter;
	
	@SuppressLint("UseSparseArrays")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_juego_nivel6);

		Typeface sansation = Typeface.createFromAsset(getAssets(), "fonts/Sansation_Regular.ttf");
		
		tv_respuesta1 = (TextView) findViewById(R.id.bt_nivel6_respuesta1);
		tv_respuesta2 = (TextView) findViewById(R.id.bt_nivel6_respuesta2);
		tv_respuesta3 = (TextView) findViewById(R.id.bt_nivel6_respuesta3);
		tv_respuesta4 = (TextView) findViewById(R.id.bt_nivel6_respuesta4);
		
		imageView = (ImageView) findViewById(R.id.iv_nivel6_imagen);
		
		countdown = (TextView) findViewById(R.id.countdown);
		
		rl6 = (RelativeLayout) findViewById(R.id.relativeLayout6);
		pin = (ImageView) findViewById(R.id.iv_pin);
		textopregunta = (TextView) findViewById(R.id.textopregunta6);
		
		fadeOutAnimation = AnimationUtils.loadAnimation(JuegoNivel6.this, R.anim.fadeout);
    	fadeInAnimation = AnimationUtils.loadAnimation(JuegoNivel6.this, R.anim.fadein);
		
		tv_nivel6_racha = (TextView) findViewById(R.id.tv_nivel6_racha);
		tv_nivel6_highscore = (TextView) findViewById(R.id.tv_nivel6_highscore);
		tv_nivel6_numpregunta = (TextView) findViewById(R.id.tv_nivel6_numpregunta);
		
		tv_respuesta1.setTypeface(sansation);
		tv_respuesta2.setTypeface(sansation);
		tv_respuesta3.setTypeface(sansation);
		tv_respuesta4.setTypeface(sansation);
		
		counter = new MyCount(11000,1000);
		
		tv_nivel6_racha.setTypeface(sansation);
		tv_nivel6_highscore.setTypeface(sansation);
		tv_nivel6_numpregunta.setTypeface(sansation);
		
		gestorJuego=new GestorJuego();
		
		niveles = new HashMap<Integer, String>();
		niveles.put(1, "nivel1");
		niveles.put(2, "nivel2");
		niveles.put(3, "nivel3");
		niveles.put(4, "nivel4");
		niveles.put(5, "nivel5");
		
		carpetas = new HashMap<Integer, String>();
		carpetas.put(1, "01Fotogramas");
		carpetas.put(2, "02Posters");
		carpetas.put(3, "03Actores");
		carpetas.put(4, "04Frases");
		carpetas.put(5, "05Malos");
		
		Random r = new Random();
		int pos = (r.nextInt(5))+1;
		
		gestorJuego.cargarMemoria(niveles.get(pos),this.getApplicationContext());	
		String[] dei = gestorJuego.peliculaYTitulosCorrectoYAleatorios();
	
		switch (pos) {
		case 1:
			tv_nivel6_numpregunta.setText("N1");
			nivelactual = 1;
			break;
		case 2:
			tv_nivel6_numpregunta.setText("N2");
			nivelactual = 2;
			break;
		case 3:
			tv_nivel6_numpregunta.setText("N3");
			nivelactual = 3;
			break;
		case 4:
			tv_nivel6_numpregunta.setText("N4");
			nivelactual = 4;
			break;
		case 5:
			tv_nivel6_numpregunta.setText("N5");
			nivelactual = 5;
			break;
		}
		
		if (nivelactual == 4) {
			rl6.setBackgroundResource(R.drawable.rounded_corners_postit);
			imageView.setVisibility(View.INVISIBLE);
			pin.setVisibility(View.VISIBLE);
			textopregunta.setText(dei[0]);
		} else {
			rl6.setBackgroundResource(R.drawable.gold);
			imageView.setVisibility(View.VISIBLE);
			imageView.setImageBitmap(getBitmapFromAsset(carpetas.get(pos)+"/"+dei[0]));
			pin.setVisibility(View.INVISIBLE);
			textopregunta.setText("");
		}

			
		tv_nivel6_highscore.setText(numeroPuntuacion+"");
		tv_nivel6_racha.setText(numeroRacha+"X");
		
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
            		fallada = true;
            		tv_respuesta1.setBackgroundResource(R.anim.main_button_respuesta_fallida);
            		numeroRacha=0;
            		numeroPuntuacion=numeroPuntuacion+(numeroRacha*100);
            		
            	}
            	
            	numeroPregunta=numeroPregunta+1;
            	
            	view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    	
                    	if (fallada) {
							Intent myIntent = new Intent(JuegoNivel6.this,
									Resultados.class);
							myIntent.putExtra("numcorrectas", numCorrectas);
							myIntent.putExtra("racha", mejorRacha);
							myIntent.putExtra("puntuacion", numeroPuntuacion);
							myIntent.putExtra("nivel", 6);
							startActivity(myIntent);
						} else {

							rl6.startAnimation(fadeOutAnimation);
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
                    	
                    	Random r = new Random();
                		int pos = (r.nextInt(5))+1;
                		
                		gestorJuego.cargarMemoria(niveles.get(pos),JuegoNivel6.this);	
                		String[] dei = gestorJuego.peliculaYTitulosCorrectoYAleatorios();
                		
                		switch (pos) {
                		case 1:
                			tv_nivel6_numpregunta.setText("N1");
                			nivelactual = 1;
                			break;
                		case 2:
                			tv_nivel6_numpregunta.setText("N2");
                			nivelactual = 2;
                			break;
                		case 3:
                			tv_nivel6_numpregunta.setText("N3");
                			nivelactual = 3;
                			break;
                		case 4:
                			tv_nivel6_numpregunta.setText("N4");
                			nivelactual = 4;
                			break;
                		case 5:
                			tv_nivel6_numpregunta.setText("N5");
                			nivelactual = 5;
                			break;
                		}
                		
                		if (nivelactual == 4) {
                			rl6.setBackgroundResource(R.drawable.rounded_corners_postit);
                			imageView.setVisibility(View.INVISIBLE);
                			pin.setVisibility(View.VISIBLE);
                			textopregunta.setText(dei[0]);
                		} else {
                			rl6.setBackgroundResource(R.drawable.gold);
                			imageView.setVisibility(View.VISIBLE);
                			imageView.setImageBitmap(getBitmapFromAsset(carpetas.get(pos)+"/"+dei[0]));
                			pin.setVisibility(View.INVISIBLE);
                			textopregunta.setText("");
                		}
                		
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
                		
                		tv_nivel6_racha.setText(numeroRacha+"X");
                    	
                    	tv_respuesta1.setBackgroundResource(R.anim.main_button_respuesta);

                    	rl6.startAnimation(fadeInAnimation);
                        tv_respuesta1.startAnimation(fadeInAnimation);
                        tv_respuesta2.startAnimation(fadeInAnimation);
                        tv_respuesta3.startAnimation(fadeInAnimation);
                        tv_respuesta4.startAnimation(fadeInAnimation);

						int anterior = Integer.parseInt((String)tv_nivel6_highscore.getText());
						
						if(Build.VERSION.SDK_INT >= 11.0){
							ValueAnimator va = ValueAnimator.ofInt(anterior, numeroPuntuacion);
						    va.setDuration(700);
						    va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
						        public void onAnimationUpdate(ValueAnimator animation) {
						            Integer value = (Integer) animation.getAnimatedValue();
						            tv_nivel6_highscore.setText(value + "");
						        }
						    });
						    
						    va.start();
						} else {
							tv_nivel6_highscore.setText(numeroPuntuacion + "");
						}
                        
                        tv_respuesta1.setClickable(true);
						tv_respuesta1.setEnabled(true);
						tv_respuesta2.setClickable(true);
						tv_respuesta2.setEnabled(true);
						tv_respuesta3.setClickable(true);
						tv_respuesta3.setEnabled(true);
						tv_respuesta4.setClickable(true);
						tv_respuesta4.setEnabled(true);
						
						if (!fallada) {
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
            		fallada = true;
            		tv_respuesta2.setBackgroundResource(R.anim.main_button_respuesta_fallida);
            		numeroRacha=0;
            		numeroPuntuacion=numeroPuntuacion+(numeroRacha*100);
            		
            	}
            	
            	numeroPregunta=numeroPregunta+1;
            	
            	view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    	
                    	if (fallada) {
							Intent myIntent = new Intent(JuegoNivel6.this,
									Resultados.class);
							myIntent.putExtra("numcorrectas", numCorrectas);
							myIntent.putExtra("racha", mejorRacha);
							myIntent.putExtra("puntuacion", numeroPuntuacion);
							myIntent.putExtra("nivel", 6);
							startActivity(myIntent);
						} else {

							rl6.startAnimation(fadeOutAnimation);
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
                    	
                    	Random r = new Random();
                		int pos = (r.nextInt(5))+1;
                		
                		gestorJuego.cargarMemoria(niveles.get(pos),JuegoNivel6.this);	
                		String[] dei = gestorJuego.peliculaYTitulosCorrectoYAleatorios();
                		
                		switch (pos) {
                		case 1:
                			tv_nivel6_numpregunta.setText("N1");
                			nivelactual = 1;
                			break;
                		case 2:
                			tv_nivel6_numpregunta.setText("N2");
                			nivelactual = 2;
                			break;
                		case 3:
                			tv_nivel6_numpregunta.setText("N3");
                			nivelactual = 3;
                			break;
                		case 4:
                			tv_nivel6_numpregunta.setText("N4");
                			nivelactual = 4;
                			break;
                		case 5:
                			tv_nivel6_numpregunta.setText("N5");
                			nivelactual = 5;
                			break;
                		}
                		
                		if (nivelactual == 4) {
                			rl6.setBackgroundResource(R.drawable.rounded_corners_postit);
                			imageView.setVisibility(View.INVISIBLE);
                			pin.setVisibility(View.VISIBLE);
                			textopregunta.setText(dei[0]);
                		} else {
                			rl6.setBackgroundResource(R.drawable.gold);
                			imageView.setVisibility(View.VISIBLE);
                			imageView.setImageBitmap(getBitmapFromAsset(carpetas.get(pos)+"/"+dei[0]));
                			pin.setVisibility(View.INVISIBLE);
                			textopregunta.setText("");
                		}
                		
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
                		
                		tv_nivel6_racha.setText(numeroRacha+"X");
                		
                    	tv_respuesta2.setBackgroundResource(R.anim.main_button_respuesta);

                    	rl6.startAnimation(fadeInAnimation);
                        tv_respuesta1.startAnimation(fadeInAnimation);
                        tv_respuesta2.startAnimation(fadeInAnimation);
                        tv_respuesta3.startAnimation(fadeInAnimation);
                        tv_respuesta4.startAnimation(fadeInAnimation);

						int anterior = Integer.parseInt((String)tv_nivel6_highscore.getText());
						
						if(Build.VERSION.SDK_INT >= 11.0){
							ValueAnimator va = ValueAnimator.ofInt(anterior, numeroPuntuacion);
						    va.setDuration(700);
						    va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
						        public void onAnimationUpdate(ValueAnimator animation) {
						            Integer value = (Integer) animation.getAnimatedValue();
						            tv_nivel6_highscore.setText(value + "");
						        }
						    });
						    
						    va.start();
						} else {
							tv_nivel6_highscore.setText(numeroPuntuacion + "");
						}
                        
                        tv_respuesta1.setClickable(true);
						tv_respuesta1.setEnabled(true);
						tv_respuesta2.setClickable(true);
						tv_respuesta2.setEnabled(true);
						tv_respuesta3.setClickable(true);
						tv_respuesta3.setEnabled(true);
						tv_respuesta4.setClickable(true);
						tv_respuesta4.setEnabled(true);
						
						if (!fallada) {
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
            		fallada = true;
            		tv_respuesta3.setBackgroundResource(R.anim.main_button_respuesta_fallida);
            		numeroRacha=0;
            		numeroPuntuacion=numeroPuntuacion+(numeroRacha*100);
            		
            	}
            	
            	numeroPregunta=numeroPregunta+1;

            	view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    	
                    	if (fallada) {
							Intent myIntent = new Intent(JuegoNivel6.this,
									Resultados.class);
							myIntent.putExtra("numcorrectas", numCorrectas);
							myIntent.putExtra("racha", mejorRacha);
							myIntent.putExtra("puntuacion", numeroPuntuacion);
							myIntent.putExtra("nivel", 6);
							startActivity(myIntent);
						} else {

							rl6.startAnimation(fadeOutAnimation);
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
                    	
                    	Random r = new Random();
                		int pos = (r.nextInt(5))+1;
                		
                		gestorJuego.cargarMemoria(niveles.get(pos),JuegoNivel6.this);	
                		String[] dei = gestorJuego.peliculaYTitulosCorrectoYAleatorios();
                		
                		switch (pos) {
                		case 1:
                			tv_nivel6_numpregunta.setText("N1");
                			nivelactual = 1;
                			break;
                		case 2:
                			tv_nivel6_numpregunta.setText("N2");
                			nivelactual = 2;
                			break;
                		case 3:
                			tv_nivel6_numpregunta.setText("N3");
                			nivelactual = 3;
                			break;
                		case 4:
                			tv_nivel6_numpregunta.setText("N4");
                			nivelactual = 4;
                			break;
                		case 5:
                			tv_nivel6_numpregunta.setText("N5");
                			nivelactual = 5;
                			break;
                		}
                		
                		if (nivelactual == 4) {
                			rl6.setBackgroundResource(R.drawable.rounded_corners_postit);
                			imageView.setVisibility(View.INVISIBLE);
                			pin.setVisibility(View.VISIBLE);
                			textopregunta.setText(dei[0]);
                		} else {
                			rl6.setBackgroundResource(R.drawable.gold);
                			imageView.setVisibility(View.VISIBLE);
                			imageView.setImageBitmap(getBitmapFromAsset(carpetas.get(pos)+"/"+dei[0]));
                			pin.setVisibility(View.INVISIBLE);
                			textopregunta.setText("");
                		}
                		
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
                		
                		tv_nivel6_racha.setText(numeroRacha+"X");
                    	
                    	tv_respuesta3.setBackgroundResource(R.anim.main_button_respuesta);

                    	rl6.startAnimation(fadeInAnimation);
                        tv_respuesta1.startAnimation(fadeInAnimation);
                        tv_respuesta2.startAnimation(fadeInAnimation);
                        tv_respuesta3.startAnimation(fadeInAnimation);
                        tv_respuesta4.startAnimation(fadeInAnimation);

						int anterior = Integer.parseInt((String)tv_nivel6_highscore.getText());
						
						if(Build.VERSION.SDK_INT >= 11.0){
							ValueAnimator va = ValueAnimator.ofInt(anterior, numeroPuntuacion);
						    va.setDuration(700);
						    va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
						        public void onAnimationUpdate(ValueAnimator animation) {
						            Integer value = (Integer) animation.getAnimatedValue();
						            tv_nivel6_highscore.setText(value + "");
						        }
						    });
						    
						    va.start();
						} else {
							tv_nivel6_highscore.setText(numeroPuntuacion + "");
						}
                        
                        tv_respuesta1.setClickable(true);
						tv_respuesta1.setEnabled(true);
						tv_respuesta2.setClickable(true);
						tv_respuesta2.setEnabled(true);
						tv_respuesta3.setClickable(true);
						tv_respuesta3.setEnabled(true);
						tv_respuesta4.setClickable(true);
						tv_respuesta4.setEnabled(true);
						
						if (!fallada) {
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
            		fallada = true;
            		tv_respuesta4.setBackgroundResource(R.anim.main_button_respuesta_fallida);
            		numeroRacha=0;
            		numeroPuntuacion=numeroPuntuacion+(numeroRacha*100);
            		
            	}
            	
            	numeroPregunta=numeroPregunta+1;

            	view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    	if (fallada) {
							Intent myIntent = new Intent(JuegoNivel6.this,
									Resultados.class);
							myIntent.putExtra("numcorrectas", numCorrectas);
							myIntent.putExtra("racha", mejorRacha);
							myIntent.putExtra("puntuacion", numeroPuntuacion);
							myIntent.putExtra("nivel", 6);
							startActivity(myIntent);
						} else {

							rl6.startAnimation(fadeOutAnimation);
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
                    	
                    	Random r = new Random();
                		int pos = (r.nextInt(5))+1;
                		
                		gestorJuego.cargarMemoria(niveles.get(pos),JuegoNivel6.this);	
                		String[] dei = gestorJuego.peliculaYTitulosCorrectoYAleatorios();
                		
                		switch (pos) {
                		case 1:
                			tv_nivel6_numpregunta.setText("N1");
                			nivelactual = 1;
                			break;
                		case 2:
                			tv_nivel6_numpregunta.setText("N2");
                			nivelactual = 2;
                			break;
                		case 3:
                			tv_nivel6_numpregunta.setText("N3");
                			nivelactual = 3;
                			break;
                		case 4:
                			tv_nivel6_numpregunta.setText("N4");
                			nivelactual = 4;
                			break;
                		case 5:
                			tv_nivel6_numpregunta.setText("N5");
                			nivelactual = 5;
                			break;
                		}
                		
                		if (nivelactual == 4) {
                			rl6.setBackgroundResource(R.drawable.rounded_corners_postit);
                			imageView.setVisibility(View.INVISIBLE);
                			pin.setVisibility(View.VISIBLE);
                			textopregunta.setText(dei[0]);
                		} else {
                			rl6.setBackgroundResource(R.drawable.gold);
                			imageView.setVisibility(View.VISIBLE);
                			imageView.setImageBitmap(getBitmapFromAsset(carpetas.get(pos)+"/"+dei[0]));
                			pin.setVisibility(View.INVISIBLE);
                			textopregunta.setText("");
                		}
                		
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
                		
                		tv_nivel6_racha.setText(numeroRacha+"X");
                    	
                    	tv_respuesta4.setBackgroundResource(R.anim.main_button_respuesta);

                    	rl6.startAnimation(fadeInAnimation);
                        tv_respuesta1.startAnimation(fadeInAnimation);
                        tv_respuesta2.startAnimation(fadeInAnimation);
                        tv_respuesta3.startAnimation(fadeInAnimation);
                        tv_respuesta4.startAnimation(fadeInAnimation);

						int anterior = Integer.parseInt((String)tv_nivel6_highscore.getText());
						
						if(Build.VERSION.SDK_INT >= 11.0){
							ValueAnimator va = ValueAnimator.ofInt(anterior, numeroPuntuacion);
						    va.setDuration(700);
						    va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
						        public void onAnimationUpdate(ValueAnimator animation) {
						            Integer value = (Integer) animation.getAnimatedValue();
						            tv_nivel6_highscore.setText(value + "");
						        }
						    });
						    
						    va.start();
						} else {
							tv_nivel6_highscore.setText(numeroPuntuacion + "");
						}
                        
                        tv_respuesta1.setClickable(true);
						tv_respuesta1.setEnabled(true);
						tv_respuesta2.setClickable(true);
						tv_respuesta2.setEnabled(true);
						tv_respuesta3.setClickable(true);
						tv_respuesta3.setEnabled(true);
						tv_respuesta4.setClickable(true);
						tv_respuesta4.setEnabled(true);
						
						if (!fallada) {
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
		getMenuInflater().inflate(R.menu.juego_nivel6, menu);
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
			
        	numeroRacha=0;
        	numeroPuntuacion=numeroPuntuacion+(numeroRacha*100);

        	numeroPregunta=numeroPregunta+1;
        	Handler handler = new Handler();

        	handler.postDelayed(new Runnable() {
                @Override
                public void run() {
						Intent myIntent = new Intent(JuegoNivel6.this,
								Resultados.class);
						myIntent.putExtra("numcorrectas", numCorrectas);
						myIntent.putExtra("racha", mejorRacha);
						myIntent.putExtra("puntuacion", numeroPuntuacion);
						myIntent.putExtra("nivel", 6);
						startActivity(myIntent);
                }
            }, 1500);		
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
