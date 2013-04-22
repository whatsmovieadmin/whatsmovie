package ur.informaticamovil7.movies;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class Instrucciones extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instrucciones);
		
        Typeface sansation = Typeface.createFromAsset(getAssets(), "fonts/Sansation_Regular.ttf");
        Typeface sansation_bold = Typeface.createFromAsset(getAssets(), "fonts/Sansation_Bold.ttf");
               
        TextView tv_instrucciones_titulo1 = (TextView)findViewById(R.id.instrucciones_titulo1);
        TextView tv_instrucciones_titulo2 = (TextView)findViewById(R.id.instrucciones_titulo2);
        TextView tv_instrucciones_titulo3 = (TextView)findViewById(R.id.instrucciones_titulo3);
        
        TextView tv_instrucciones_contenido1 = (TextView)findViewById(R.id.instrucciones_contenido1);
        TextView tv_instrucciones_contenido2 = (TextView)findViewById(R.id.instrucciones_contenido2);
        TextView tv_instrucciones_contenido3 = (TextView)findViewById(R.id.instrucciones_contenido3);
        
        tv_instrucciones_titulo1.setTypeface(sansation_bold);
        tv_instrucciones_titulo2.setTypeface(sansation_bold);
        tv_instrucciones_titulo3.setTypeface(sansation_bold);
        
        tv_instrucciones_contenido1.setTypeface(sansation);
        tv_instrucciones_contenido2.setTypeface(sansation);
        tv_instrucciones_contenido3.setTypeface(sansation);
        
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
		getMenuInflater().inflate(R.menu.activity_instrucciones, menu);
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
