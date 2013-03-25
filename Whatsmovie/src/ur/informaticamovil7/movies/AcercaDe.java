package ur.informaticamovil7.movies;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class AcercaDe extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acerca_de);

		Typeface pacifico = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");

		TextView et_creador1 = (TextView) findViewById(R.id.txt_acercade_creadores);
		TextView et_creador2 = (TextView) findViewById(R.id.txt_acercade_creadores1);
		TextView et_version = (TextView) findViewById(R.id.txt_acercade_version);
		TextView et_derechos = (TextView) findViewById(R.id.txt_acercade_derechos);
		TextView et_anio = (TextView) findViewById(R.id.txt_acercade_anoYEmpresa);

		et_creador1.setTypeface(pacifico);
		et_creador2.setTypeface(pacifico);
		et_version.setTypeface(pacifico);
		et_derechos.setTypeface(pacifico);
		et_anio.setTypeface(pacifico);

		// Show the Up button in the action bar.
		// getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_acerca_de, menu);
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
