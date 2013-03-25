package ur.informaticamovil7.movies;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("WorldReadableFiles")
public class Principal extends Activity {
    @SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        
        Typeface pacifico = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
        Typeface walkway_semibold = Typeface.createFromAsset(getAssets(), "fonts/Walkway_SemiBold.ttf");
        
        EditText et_email = (EditText)findViewById(R.id.input_main_email);
        EditText et_pwd = (EditText)findViewById(R.id.input_main_pass);
        
        et_email.setTypeface(walkway_semibold);
        et_pwd.setTypeface(walkway_semibold);
        
        SharedPreferences myPrefs = this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
        boolean isRemember = myPrefs.getBoolean("REMEMBER", false);
        if(!isRemember) {
        	
        } else{
       	
        	Intent myIntent = new Intent(Principal.this, MenuMovies.class);
            startActivityForResult(myIntent, 0);
        	
        }
        
        Button bt_registrar = (Button) findViewById(R.id.bt_main_registrar);
        
        bt_registrar.setTypeface(pacifico);
        bt_registrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Registro.class);
                startActivityForResult(myIntent, 0);
            }
        });
        
        Button bt_entrar = (Button) findViewById(R.id.bt_main_entrar);
        
        bt_entrar.setTypeface(pacifico);
        bt_entrar.setOnClickListener(new View.OnClickListener() {
            
        	private EditText email;
			private EditText pwd;
        	
			public void onClick(View view) {
                
            	    try{       	
                	email = (EditText)findViewById(R.id.input_main_email);
                	pwd = (EditText)findViewById(R.id.input_main_pass);
                	
                	PrincipalTask task = new PrincipalTask();
                    task.execute(email,pwd);
                    
    				String a=task.get();
                    
                    if(!a.contains("error"))
                    {
                    	
                    	SharedPreferences myPrefs = Principal.this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
                        SharedPreferences.Editor prefsEditor = myPrefs.edit();
                        prefsEditor.putString("LOGIN_EMAIL", email.getText().toString());
                        prefsEditor.putString("LOGIN_PSWD", pwd.getText().toString());
                        prefsEditor.putBoolean("REMEMBER", true);
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
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }
    
    class PrincipalTask extends AsyncTask<EditText, Void, String>{
		 
        @SuppressWarnings("deprecation")
		@Override
        protected String doInBackground(EditText... params) {
        	HttpURLConnection conn = null;
        	
        	String message=null;

            try {           	
            	
            	URL url = new URL("http://movies-quiz.appspot.com/webservices/Login?&pwd="+params[1].getText().toString()+"&email="+params[0].getText().toString());
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
