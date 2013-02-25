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
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class Registro extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registro);
		// Show the Up button in the action bar.
		setupActionBar();
		
		Button next = (Button) findViewById(R.id.bt_reg_registrar);
        next.setOnClickListener(new View.OnClickListener() {
            private EditText userName;
			private EditText email;
			private EditText pwd;
			private EditText rpwd;

			public void onClick(View view) {
            	           	
				try {	
				userName = (EditText)findViewById(R.id.input_reg_user);
                email = (EditText)findViewById(R.id.input_reg_email);
                pwd = (EditText)findViewById(R.id.input_reg_pass);
                rpwd = (EditText)findViewById(R.id.input_reg_rpass);
                	
            	RegistroTask task = new RegistroTask();
                task.execute(userName,email,pwd,rpwd);
                
				String a=task.get();
                
				// Output response to standard out
                if(!a.contains("error"))
                {
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
		getMenuInflater().inflate(R.menu.registro, menu);
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
	
	class RegistroTask extends AsyncTask<EditText, Void, String>{
		 
        @SuppressWarnings("deprecation")
		@Override
        protected String doInBackground(EditText... params) {
        	HttpURLConnection conn = null;
        	String message=null;

            try {           	
            	
            	URL url = new URL("http://movies-quiz.appspot.com/webservices/SignIn?name="+params[0].getText().toString()+"&pwd="+params[2].getText().toString()+"&email="+params[1].getText().toString()+"&rpwd="+params[3].getText().toString());
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
