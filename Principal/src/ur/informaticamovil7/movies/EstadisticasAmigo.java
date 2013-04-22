package ur.informaticamovil7.movies;


import java.util.ArrayList;

import model.Estadistica;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.viewpagerindicator.CirclePageIndicator;

import android.os.Bundle;
import android.os.Parcelable;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;

public class EstadisticasAmigo extends Activity {

	ViewPager vp;
	private vpAdapter miAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_estadisticas_amigo);
		
		vp = (ViewPager) findViewById(R.id.viewpager);
		miAdapter = new vpAdapter();
		vp.setAdapter(miAdapter);
		
		CirclePageIndicator titleIndicator = (CirclePageIndicator)findViewById(R.id.titles);
		titleIndicator.setViewPager(vp);
		
		setupActionBar();
	}
	
	private class vpAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return 2;
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
				v = inflater.inflate(R.layout.stats_amigo, null);

				ListView lv_stats_amigo = (ListView) v.findViewById(R.id.lv_stats_amigo);
				
				final ArrayList<Estadistica> alista = new ArrayList<Estadistica>();
				final EstadisticaAdapter aa  = new EstadisticaAdapter(EstadisticasAmigo.this, R.layout.list_item_stats, alista);
				lv_stats_amigo.setAdapter(aa);
				
				alista.add(new Estadistica("1", "Nivel 1. Fotogramas", 100, "3X"));
				alista.add(new Estadistica("2", "Nivel 2. Carteles", 200, "5X"));
				alista.add(new Estadistica("3", "Nivel 3. Actores", 300, "8X"));
				alista.add(new Estadistica("4", "Nivel 4. Frases", 400, "3X"));
				alista.add(new Estadistica("5", "Nivel 5. Malos", 500, "2X"));
				alista.add(new Estadistica("6", "Nivel 6. El reto final", 600, "2X"));
				alista.add(new Estadistica("7", "Estadísticas globales", 2100, "8X"));
				
				aa.notifyDataSetChanged();
				
				break;
			case 1:
				v = inflater.inflate(R.layout.stats_user, null);
				
				ListView lv_stats_user = (ListView) v.findViewById(R.id.lv_stats_user);
				
				final ArrayList<Estadistica> alista2 = new ArrayList<Estadistica>();
				final EstadisticaAdapter aa2  = new EstadisticaAdapter(EstadisticasAmigo.this, R.layout.list_item_stats, alista2);
				lv_stats_user.setAdapter(aa2);
				
				alista2.add(new Estadistica("1", "Nivel 1. Fotogramas", 1000, "3X"));
				alista2.add(new Estadistica("2", "Nivel 2. Carteles", 2000, "5X"));
				alista2.add(new Estadistica("3", "Nivel 3. Actores", 3000, "8X"));
				alista2.add(new Estadistica("4", "Nivel 4. Frases", 4000, "3X"));
				alista2.add(new Estadistica("5", "Nivel 5. Malos", 5000, "2X"));
				alista2.add(new Estadistica("6", "Nivel 6. El reto final", 6000, "2X"));
				alista2.add(new Estadistica("7", "Estadísticas globales", 21000, "8X"));
				
				aa2.notifyDataSetChanged();
				
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
	
	private class EstadisticaAdapter extends ArrayAdapter<Estadistica> {

        private ArrayList<Estadistica> items;

        public EstadisticaAdapter(Context context, int textViewResourceId, ArrayList<Estadistica> items) {
                super(context, textViewResourceId, items);
                this.items = items;
        }

		@Override
        public View getView(int position, View convertView, ViewGroup parent) {
                View v = convertView;
        		Typeface sansation = Typeface.createFromAsset(getAssets(), "fonts/Sansation_Regular.ttf");

                if (v == null) {
                    LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = vi.inflate(R.layout.list_item_stats, null);
                }
                final Estadistica stat = items.get(position);
                if (stat != null) {
                        TextView stats_grupo = (TextView) v.findViewById(R.id.stats_grupo);
                        TextView tv_stats_puntuacion = (TextView) v.findViewById(R.id.tv_stats_puntuacion);
                        TextView tv_stats_racha = (TextView) v.findViewById(R.id.tv_stats_racha);
                        RelativeLayout container = (RelativeLayout) v.findViewById(R.id.stats_container);
                        
                        if (stat.getPosicion().equals("1")) {
                        	container.setBackgroundResource(R.drawable.rounded_corners_red);
                        }
                        if (stat.getPosicion().equals("2")) {
                        	container.setBackgroundResource(R.drawable.rounded_corners_orange);
                        }
                        if (stat.getPosicion().equals("3")) {
                        	container.setBackgroundResource(R.drawable.rounded_corners_yellow);
                        }
                        if (stat.getPosicion().equals("4")) {
                        	container.setBackgroundResource(R.drawable.rounded_corners_green);
                        }
                        if (stat.getPosicion().equals("5")) {
                        	container.setBackgroundResource(R.drawable.rounded_corners_blue);
                        }
                        if (stat.getPosicion().equals("6")) {
                        	container.setBackgroundResource(R.drawable.rounded_corners_purple);
                        }
                        if (stat.getPosicion().equals("7")) {
                        	container.setBackgroundResource(R.drawable.rounded_corners_gray);
                        }
                        
                        stats_grupo.setTypeface(sansation);
                        tv_stats_puntuacion.setTypeface(sansation);
                        tv_stats_racha.setTypeface(sansation);

                        if (stats_grupo != null) {
                        	stats_grupo.setText(stat.getGrupo()); 
                        }
                        if (tv_stats_puntuacion != null) {
                        	tv_stats_puntuacion.setText(stat.getPuntuacion() + ""); 
                        }
                        if (tv_stats_racha != null) {
                        	tv_stats_racha.setText(stat.getRacha()); 
                        }
                        
                }
                
                return v;
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
		getMenuInflater().inflate(R.menu.estadisticas_amigo, menu);
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
