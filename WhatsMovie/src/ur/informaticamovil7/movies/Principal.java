package ur.informaticamovil7.movies;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Principal extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        
        Button next = (Button) findViewById(R.id.bt_main_registrar);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Registro.class);
                startActivityForResult(myIntent, 0);
            }
        });
        
        Button next2 = (Button) findViewById(R.id.bt_main_entrar);
        next2.setOnClickListener(new View.OnClickListener() {
            
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
