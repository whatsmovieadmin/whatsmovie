package ur.informaticamovil7.movies;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;

public class BuscarUsuario extends Activity {

	@SuppressLint("WorldReadableFiles")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buscar_usuario);
		
		
		Typeface sansation = Typeface.createFromAsset(getAssets(), "fonts/Sansation_Regular.ttf");
		Typeface walkway_semibold = Typeface.createFromAsset(getAssets(), "fonts/Walkway_SemiBold.ttf");
		
		final ListView lv_buscar_amigos = (ListView)findViewById(R.id.lv_buscar_amigos);
		//TextView tv_nombre_amigos = (TextView)findViewById(R.id.amigo_nombre);
		final EditText et_buscar = (EditText)findViewById(R.id.input_buscar_usuario);
		
		et_buscar.setTypeface(walkway_semibold);
		 
		final ArrayList<String> alista = new ArrayList<String>();
		final ArrayAdapter<String> aa  = new ArrayAdapter<String>(this, R.layout.list_item_buscar,R.id.buscar_amigo_nombre, alista);
		lv_buscar_amigos.setAdapter(aa);
		
		alista.add("Realice su busqueda");
		aa.notifyDataSetChanged();
		
		Button bt_buscar_usuario = (Button) findViewById(R.id.bt_buscar_usuario);
		bt_buscar_usuario.setTypeface(sansation);
		bt_buscar_usuario.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("deprecation")
			public void onClick(View view) {
            	
            	try {
            	SharedPreferences myPrefs;    
				myPrefs = getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
				
            	String email=myPrefs.getString("LOGIN_EMAIL", "");
				String pwd=myPrefs.getString("LOGIN_PSWD", "");
				String searchText=et_buscar.getText().toString();
            	
				BuscarUsuarioTask task = new BuscarUsuarioTask();
                task.execute(email,pwd,searchText);
				
                String a;
				
					a = task.get();
				
                
                if(!a.contains("error"))
                {
                	String resultSize=a.substring(a.indexOf("<correct>")+9, a.indexOf("</correct>"));
                	
                	String numIntera=resultSize.substring(resultSize.indexOf("%")+1, resultSize.indexOf("/%"));
                    
                	List<String> listaResultadosAmigos =new Vector<String>();
                	
                	int num=Integer.parseInt(numIntera);
                	
                	for(int i=0;i<num;i++)
                	{
                		String friend=resultSize.substring(resultSize.indexOf("<user>")+6, resultSize.indexOf("</user>"));
                		listaResultadosAmigos.add(friend);
                		resultSize=resultSize.substring(resultSize.indexOf("</user>")+7);
                	}
                	
                	if(listaResultadosAmigos.isEmpty())
                	{
                		alista.clear();
                		alista.add("No hay resultados");
                		aa.notifyDataSetChanged();
                	}
                	else
                	{
                		alista.clear();
                		
                		for(int i=0;listaResultadosAmigos.size()>i;i++)
                		{
                			alista.add(listaResultadosAmigos.get(i));
                			aa.notifyDataSetChanged();
                		}
                	}
                }
                else
                {                  
                    
                    String resultSize=a.substring(a.indexOf("<error>")+7, a.indexOf("</error>"));
                    
                    Toast t=Toast.makeText(getApplicationContext(), resultSize, Toast.LENGTH_SHORT);
                    t.show();
                }
           
        		aa.notifyDataSetChanged();	
            } catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
            }
        });
		
		lv_buscar_amigos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	        @SuppressWarnings("deprecation")
			@Override
	        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
	            Object o = lv_buscar_amigos.getItemAtPosition(position);
	            final String nombre = (String)o;
	            if ((nombre.equals("No hay resultados"))||(nombre.equals("Realice su busqueda"))) {
					
				} else {
					
		            AlertDialog alerta = new AlertDialog.Builder(BuscarUsuario.this).create();
	            	alerta.setTitle("Añadir nuevo amigo");
	            	alerta.setMessage("¿Desea agregar a " + nombre + " como amigo?");
	            	alerta.setButton("Sí", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
			            						
							try {
								SharedPreferences myPrefs;    
								myPrefs = getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
								
				            	String email=myPrefs.getString("LOGIN_EMAIL", "");
								String pwd=myPrefs.getString("LOGIN_PSWD", "");
				            	
								AnadirAmigoTask task = new AnadirAmigoTask();
				                task.execute(email,pwd,nombre);
								
				                String a;

								a = task.get();
										
				                if(!a.contains("error"))
				                {
				                	Intent myIntent = new Intent(BuscarUsuario.this, Amigos.class);
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
	            	alerta.setButton2("No", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							
						}
					});
	            	
	            	alerta.show();
				}
	            
	        }
	    });
		
		// Show the Up button in the action bar.
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
		getMenuInflater().inflate(R.menu.buscar_usuario, menu);
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
	
	class BuscarUsuarioTask extends AsyncTask<String, Void, String>{
		 
        @SuppressWarnings("deprecation")
		@Override
        protected String doInBackground(String... params) {
        	HttpURLConnection conn = null;
        	String message=null;

            try {           	
            	
            	URL url = new URL("http://movies-quiz.appspot.com/webservices/GetSearch?email="+params[0]+"&pwd="+params[1]+"&textSeach="+params[2]);
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
	
	class AnadirAmigoTask extends AsyncTask<String, Void, String>{
		 
        @SuppressWarnings("deprecation")
		@Override
        protected String doInBackground(String... params) {
        	HttpURLConnection conn = null;
        	String message=null;

            try {           	
            	
            	URL url = new URL("http://movies-quiz.appspot.com/webservices/AddFriend?email="+params[0]+"&pwd="+params[1]+"&newFriend="+params[2]);
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
