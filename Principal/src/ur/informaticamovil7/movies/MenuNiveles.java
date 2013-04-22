package ur.informaticamovil7.movies;

import com.viewpagerindicator.CirclePageIndicator;

import android.os.Bundle;
import android.os.Parcelable;
import android.app.Activity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;

public class MenuNiveles extends Activity {
	
	ViewPager vp;
	private vpAdapter miAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_niveles);
		
		vp = (ViewPager) findViewById(R.id.viewpager);
		miAdapter = new vpAdapter();
		vp.setAdapter(miAdapter);
		
		CirclePageIndicator titleIndicator = (CirclePageIndicator)findViewById(R.id.titles);
		titleIndicator.setViewPager(vp);
		
		// Show the Up button in the action bar.
		setupActionBar();
	}
	
	private class vpAdapter extends PagerAdapter{

		private Typeface pacifico = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
		private Typeface sansation = Typeface.createFromAsset(getAssets(), "fonts/Sansation_Regular.ttf");
		private Typeface sansation_bold = Typeface.createFromAsset(getAssets(), "fonts/Sansation_Bold.ttf");
		@Override
		public int getCount() {
			return 7;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == ((LinearLayout) object);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager)container).removeView((LinearLayout)object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			
			LayoutInflater inflater = (LayoutInflater)container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			
			View v = null;
			switch (position) {
			case 0:
				v = inflater.inflate(R.layout.niveles_nivel1, null);
				
				TextView tv_niveles_titulo_1 = (TextView) v.findViewById(R.id.niveles_titulo_1);
				TextView tv_niveles_subtitulo_1 = (TextView) v.findViewById(R.id.niveles_subtitulo_1);
				TextView tv_niveles_desc_1 = (TextView) v.findViewById(R.id.niveles_desc_1);
				TextView tv_niveles_highscore_1 = (TextView) v.findViewById(R.id.niveles_highscore_1);
				TextView tv_niveles_racha_1 = (TextView) v.findViewById(R.id.niveles_racha_1);
				Button bt_play_nivel_1 = (Button) v.findViewById(R.id.bt_play_nivel_1);
				
				tv_niveles_titulo_1.setTypeface(pacifico);
				tv_niveles_subtitulo_1.setTypeface(pacifico);
				tv_niveles_desc_1.setTypeface(sansation);
				tv_niveles_highscore_1.setTypeface(sansation_bold);
				tv_niveles_racha_1.setTypeface(sansation_bold);
				
				bt_play_nivel_1.setOnClickListener(new View.OnClickListener() {
		            public void onClick(View view) {
		                Intent myIntent = new Intent(view.getContext(), JuegoNivel1.class);
		                startActivityForResult(myIntent, 0);
		            }
		        });
				
				break;
			case 1:
				v = inflater.inflate(R.layout.niveles_nivel2, null);
				
				TextView tv_niveles_titulo_2 = (TextView) v.findViewById(R.id.niveles_titulo_2);
				TextView tv_niveles_subtitulo_2 = (TextView) v.findViewById(R.id.niveles_subtitulo_2);
				TextView tv_niveles_desc_2 = (TextView) v.findViewById(R.id.niveles_desc_2);
				TextView tv_niveles_highscore_2 = (TextView) v.findViewById(R.id.niveles_highscore_2);
				TextView tv_niveles_racha_2 = (TextView) v.findViewById(R.id.niveles_racha_2);
				Button bt_play_nivel_2 = (Button) v.findViewById(R.id.bt_play_nivel_2);
				
				tv_niveles_titulo_2.setTypeface(pacifico);
				tv_niveles_subtitulo_2.setTypeface(pacifico);
				tv_niveles_desc_2.setTypeface(sansation);
				tv_niveles_highscore_2.setTypeface(sansation_bold);
				tv_niveles_racha_2.setTypeface(sansation_bold);
				
				bt_play_nivel_2.setOnClickListener(new View.OnClickListener() {
		            public void onClick(View view) {
		                Intent myIntent = new Intent(view.getContext(), JuegoNivel2.class);
		                startActivityForResult(myIntent, 0);
		            }
		        });
				
				break;
			case 2:
				v = inflater.inflate(R.layout.niveles_nivel3, null);
				
				TextView tv_niveles_titulo_3 = (TextView) v.findViewById(R.id.niveles_titulo_3);
				TextView tv_niveles_subtitulo_3 = (TextView) v.findViewById(R.id.niveles_subtitulo_3);
				TextView tv_niveles_desc_3 = (TextView) v.findViewById(R.id.niveles_desc_3);
				TextView tv_niveles_highscore_3 = (TextView) v.findViewById(R.id.niveles_highscore_3);
				TextView tv_niveles_racha_3 = (TextView) v.findViewById(R.id.niveles_racha_3);
				Button bt_play_nivel_3 = (Button) v.findViewById(R.id.bt_play_nivel_3);
				
				tv_niveles_titulo_3.setTypeface(pacifico);
				tv_niveles_subtitulo_3.setTypeface(pacifico);
				tv_niveles_desc_3.setTypeface(sansation);
				tv_niveles_highscore_3.setTypeface(sansation_bold);
				tv_niveles_racha_3.setTypeface(sansation_bold);
				
				bt_play_nivel_3.setOnClickListener(new View.OnClickListener() {
		            public void onClick(View view) {
		                Intent myIntent = new Intent(view.getContext(), JuegoNivel3.class);
		                startActivityForResult(myIntent, 0);
		            }
		        });
				
				break;
			case 3:
				v = inflater.inflate(R.layout.niveles_nivel4, null);
				
				TextView tv_niveles_titulo_4 = (TextView) v.findViewById(R.id.niveles_titulo_4);
				TextView tv_niveles_subtitulo_4 = (TextView) v.findViewById(R.id.niveles_subtitulo_4);
				TextView tv_niveles_desc_4 = (TextView) v.findViewById(R.id.niveles_desc_4);
				TextView tv_niveles_highscore_4 = (TextView) v.findViewById(R.id.niveles_highscore_4);
				TextView tv_niveles_racha_4 = (TextView) v.findViewById(R.id.niveles_racha_4);
				Button bt_play_nivel_4 = (Button) v.findViewById(R.id.bt_play_nivel_4);
				
				tv_niveles_titulo_4.setTypeface(pacifico);
				tv_niveles_subtitulo_4.setTypeface(pacifico);
				tv_niveles_desc_4.setTypeface(sansation);
				tv_niveles_highscore_4.setTypeface(sansation_bold);
				tv_niveles_racha_4.setTypeface(sansation_bold);
				
				bt_play_nivel_4.setOnClickListener(new View.OnClickListener() {
		            public void onClick(View view) {
		                Intent myIntent = new Intent(view.getContext(), JuegoNivel4.class);
		                startActivityForResult(myIntent, 0);
		            }
		        });
				
				break;
			case 4:
				v = inflater.inflate(R.layout.niveles_nivel5, null);
				
				TextView tv_niveles_titulo_5 = (TextView) v.findViewById(R.id.niveles_titulo_5);
				TextView tv_niveles_subtitulo_5 = (TextView) v.findViewById(R.id.niveles_subtitulo_5);
				TextView tv_niveles_desc_5 = (TextView) v.findViewById(R.id.niveles_desc_5);
				TextView tv_niveles_highscore_5 = (TextView) v.findViewById(R.id.niveles_highscore_5);
				TextView tv_niveles_racha_5 = (TextView) v.findViewById(R.id.niveles_racha_5);
				Button bt_play_nivel_5 = (Button) v.findViewById(R.id.bt_play_nivel_5);
				
				tv_niveles_titulo_5.setTypeface(pacifico);
				tv_niveles_subtitulo_5.setTypeface(pacifico);
				tv_niveles_desc_5.setTypeface(sansation);
				tv_niveles_highscore_5.setTypeface(sansation_bold);
				tv_niveles_racha_5.setTypeface(sansation_bold);
				
				bt_play_nivel_5.setOnClickListener(new View.OnClickListener() {
		            public void onClick(View view) {
		                Intent myIntent = new Intent(view.getContext(), JuegoNivel5.class);
		                startActivityForResult(myIntent, 0);
		            }
		        });
				
				break;
				
			case 5:
				v = inflater.inflate(R.layout.niveles_nivel6, null);
				
				TextView tv_niveles_titulo_6 = (TextView) v.findViewById(R.id.niveles_titulo_6);
				TextView tv_niveles_subtitulo_6 = (TextView) v.findViewById(R.id.niveles_subtitulo_6);
				TextView tv_niveles_desc_6 = (TextView) v.findViewById(R.id.niveles_desc_6);
				TextView tv_niveles_highscore_6 = (TextView) v.findViewById(R.id.niveles_highscore_6);
				TextView tv_niveles_racha_6 = (TextView) v.findViewById(R.id.niveles_racha_6);
				Button bt_play_nivel_6 = (Button) v.findViewById(R.id.bt_play_nivel_6);
				
				tv_niveles_titulo_6.setTypeface(pacifico);
				tv_niveles_subtitulo_6.setTypeface(pacifico);
				tv_niveles_desc_6.setTypeface(sansation);
				tv_niveles_highscore_6.setTypeface(sansation_bold);
				tv_niveles_racha_6.setTypeface(sansation_bold);
				
				bt_play_nivel_6.setOnClickListener(new View.OnClickListener() {
		            public void onClick(View view) {
		                Intent myIntent = new Intent(view.getContext(), JuegoNivel6.class);
		                startActivityForResult(myIntent, 0);
		            }
		        });
				
				break;
				
			case 6:
				v = inflater.inflate(R.layout.nextlevels, null);
				
				TextView tv_niveles_proximamente = (TextView) v.findViewById(R.id.tv_niveles_proximamente);
				TextView tv_siguenos = (TextView) v.findViewById(R.id.tv_siguenos);
				Button bt_twitter = (Button) v.findViewById(R.id.bt_twitter);
				Button bt_facebook = (Button) v.findViewById(R.id.bt_facebook);
				
				
				tv_niveles_proximamente.setTypeface(pacifico);
				tv_siguenos.setTypeface(pacifico);
				
				bt_twitter.setOnClickListener(new View.OnClickListener() {
		            public void onClick(View view) {

		            }
		        });
				
				bt_facebook.setOnClickListener(new View.OnClickListener() {
		            public void onClick(View view) {

		            }
		        });
				
				break;
			}
			
			((ViewPager)container).addView(v,0);
			
			return v;
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(ViewGroup container) {
			// TODO Auto-generated method stub
			
		}
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
		getMenuInflater().inflate(R.menu.menu_niveles, menu);
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
