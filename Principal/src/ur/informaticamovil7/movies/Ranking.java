package ur.informaticamovil7.movies;


import java.util.ArrayList;
import model.RankingItem;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;

public class Ranking extends Activity {
	
	ViewPager vp;
	private vpAdapter miAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ranking);

		vp = (ViewPager) findViewById(R.id.viewpager_ranking);
		miAdapter = new vpAdapter();
		vp.setAdapter(miAdapter);
		
		CirclePageIndicator titleIndicator = (CirclePageIndicator)findViewById(R.id.ranking);
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
				v = inflater.inflate(R.layout.ranking_global, null);
				
				ListView lv_ranking_global = (ListView) v.findViewById(R.id.lv_ranking_global);
				
				final ArrayList<RankingItem> alista = new ArrayList<RankingItem>();
				final RankingAdapter aa  = new RankingAdapter(Ranking.this, R.layout.list_item_ranking, alista);
				lv_ranking_global.setAdapter(aa);

				alista.add(new RankingItem("1","Ana", 10280));
				alista.add(new RankingItem("2","David", 3300));
				alista.add(new RankingItem("3","Elena", 2280));
				alista.add(new RankingItem("4","Maria", 1280));
				alista.add(new RankingItem("5","Nacho", 280));
				alista.add(new RankingItem("6","Tomás", 120));
				alista.add(new RankingItem("7","Sara", 80));
				
				aa.notifyDataSetChanged();

				break;
			case 1:
				v = inflater.inflate(R.layout.ranking_amigos, null);
				
				ListView lv_ranking_amigos = (ListView) v.findViewById(R.id.lv_ranking_amigos);
				
				final ArrayList<RankingItem> alista2 = new ArrayList<RankingItem>();
				final RankingAdapter aa2  = new RankingAdapter(Ranking.this, R.layout.list_item_ranking, alista2);
				lv_ranking_amigos.setAdapter(aa2);

				alista2.add(new RankingItem("1","David", 11180));
				alista2.add(new RankingItem("2","Elena", 1280));
				alista2.add(new RankingItem("3","Maria", 180));
				alista2.add(new RankingItem("4","Nacho", 120));
				
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
	
	private class RankingAdapter extends ArrayAdapter<RankingItem> {

        private ArrayList<RankingItem> items;

        public RankingAdapter(Context context, int textViewResourceId, ArrayList<RankingItem> items) {
                super(context, textViewResourceId, items);
                this.items = items;
        }

		@Override
        public View getView(int position, View convertView, ViewGroup parent) {
                View v = convertView;
        		Typeface sansation = Typeface.createFromAsset(getAssets(), "fonts/Sansation_Regular.ttf");

                if (v == null) {
                    LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = vi.inflate(R.layout.list_item_ranking, null);
                }
                final RankingItem ranking = items.get(position);
                if (ranking != null) {
                        TextView ranking_nombre = (TextView) v.findViewById(R.id.ranking_nombre);
                        ImageView imageView = (ImageView) v.findViewById(R.id.iv_medal);
                        TextView tv_puntuacion = (TextView) v.findViewById(R.id.tv_ranking_puntuacion);
                        
                        tv_puntuacion.setTypeface(sansation);
                        ranking_nombre.setTypeface(sansation);

                        if (ranking.getPosicion().equals("1")) {
                        	imageView.setImageResource(R.drawable.gold);
						}
                        if (ranking.getPosicion().equals("2")) {
                        	imageView.setImageResource(R.drawable.silver);
						}
                        if (ranking.getPosicion().equals("3")) {
                        	imageView.setImageResource(R.drawable.bronze);
						}
                        
                        if (ranking_nombre != null) {
                        	ranking_nombre.setText(ranking.getPosicion() + " " + ranking.getNombre()); 
                        }
                        
                        if (tv_puntuacion != null) {
                        	tv_puntuacion.setText(ranking.getPuntuacion() + "");       
                        }
                }
                
                Button iv_estadisticas_amigo = (Button) v.findViewById(R.id.iv_stats);
                iv_estadisticas_amigo.setOnClickListener(new View.OnClickListener() {
					public void onClick(View view) {
                    	Intent myIntent = new Intent(Ranking.this, EstadisticasAmigo.class);
		                startActivityForResult(myIntent, 0);
                    }
                });
                
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
		getMenuInflater().inflate(R.menu.ranking, menu);
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
