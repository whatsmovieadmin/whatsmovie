package ur.informaticamovil7.movies;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;

@SuppressLint("WorldReadableFiles")
public class Editar extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editar);
		// Show the Up button in the action bar.
		setupActionBar();
		
		Button bt_guardar_cambios = (Button) findViewById(R.id.bt_edi_guardar);
		bt_guardar_cambios.setOnClickListener(new View.OnClickListener() {
			private EditText edNpwd;
			private EditText edRnpwd;

			@SuppressWarnings("deprecation")
			public void onClick(View view) {
            	           	
				try {	

                edNpwd = (EditText)findViewById(R.id.input_edi_pass);
                edRnpwd = (EditText)findViewById(R.id.input_edi_rpass);
                
                SharedPreferences myPrefs;    
				myPrefs = getSharedPreferences("myPrefs", MODE_WORLD_READABLE);  
				String email=myPrefs.getString("LOGIN_EMAIL", "");
				String pwd=myPrefs.getString("LOGIN_PSWD", "");
				String npwd=edNpwd.getText().toString();
				String rnpwd=edRnpwd.getText().toString();
                	
            	EditarTask task = new EditarTask();
                task.execute(email,pwd,npwd,rnpwd);
                
				String a=task.get();
                
				// Output response to standard out
                if(!a.contains("error"))
                {
                	SharedPreferences.Editor prefsEditor = myPrefs.edit();
                    prefsEditor.putString("LOGIN_PSWD", npwd);
                    prefsEditor.commit();
                	
                	Intent myIntent = new Intent(view.getContext(), MenuMovies.class);
	                startActivityForResult(myIntent, 0);
                }
                else
                {                  
                    
                    String resultSize=a.substring(a.indexOf("<error>")+7, a.indexOf("</error>"));
                    
                    Toast t=Toast.makeText(getApplicationContext(), resultSize, Toast.LENGTH_SHORT);
                    t.show();
                }
	                
                    
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
            }

        });
		
		Button bt_salir_sin_guardar = (Button) findViewById(R.id.bt_edi_salir);
		bt_salir_sin_guardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Opciones.class);
                startActivityForResult(myIntent, 0);
            }
        });
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
		getMenuInflater().inflate(R.menu.editar, menu);
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
	
	class EditarTask extends AsyncTask<String, Void, String>{
		 
        @SuppressWarnings("deprecation")
		@Override
        protected String doInBackground(String... params) {
        	HttpURLConnection conn = null;
        	String message=null;

            try {           	
            	
            	URL url = new URL("http://movies-quiz.appspot.com/webservices/Edit?email="+params[0]+"&pwd="+params[1]+"&npwd="+params[2]+"&rnpwd="+params[3]);
                conn = (HttpURLConnection) url.openConnection(); 
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "text/xml");
   
                // Send data to server
    			PrintStream out = new PrintStream(conn.getOutputStream());
    			out.print("\n");
    			out.close();
    			
    			
    			// Get input stream from response and convert to String
    			DataInputStream in=new DataInputStream(conn.getInputStream());
    			
    			String linea=in.readLine();
    			
    			String responseContent = null;
    			
    			while(linea!=null)
    			{
    			 responseContent=linea;
    			 linea=in.readLine();
    			}
    			
    			in.close();
    			
    			message=responseContent;
                
                
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (conn != null)
                	conn.disconnect();                    
            }
            return message;
        }
    }

}
