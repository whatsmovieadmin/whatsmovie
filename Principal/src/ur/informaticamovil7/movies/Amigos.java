package ur.informaticamovil7.movies;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import model.Amigo;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;

@SuppressLint("WorldReadableFiles")
public class Amigos extends Activity {

	@SuppressLint("WorldReadableFiles")
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_amigos);
		
		ListView lv_amigos = (ListView)findViewById(R.id.lv_amigos);
	        
		final ArrayList<Amigo> alista = new ArrayList<Amigo>();
		final AmigosAdapter aa  = new AmigosAdapter(this, R.layout.list_item_amigos, alista);
		lv_amigos.setAdapter(aa);

		try {
        	SharedPreferences myPrefs;    
			myPrefs = getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
			
        	String email=myPrefs.getString("LOGIN_EMAIL", "");
			String pwd=myPrefs.getString("LOGIN_PSWD", "");
        	
			ListarAmigosTask task = new ListarAmigosTask();
            task.execute(email,pwd);
			
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
            	}
            	else
            	{
            		alista.clear();
            		
            		for(int e=0;listaResultadosAmigos.size()>e;e++)
            		{
            			alista.add(new Amigo(listaResultadosAmigos.get(e)));
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
		
		Button bt_amigos_aniadir = (Button) findViewById(R.id.bt_amigos_aniadir);
		bt_amigos_aniadir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	
                Intent myIntent = new Intent(view.getContext(), BuscarUsuario.class);
                startActivityForResult(myIntent, 0);
            }
        });
		
		setupActionBar();
	}
	
	private class AmigosAdapter extends ArrayAdapter<Amigo> {

        private ArrayList<Amigo> items;

        public AmigosAdapter(Context context, int textViewResourceId, ArrayList<Amigo> items) {
                super(context, textViewResourceId, items);
                this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
                View v = convertView;
        		Typeface sansation = Typeface.createFromAsset(getAssets(), "fonts/Sansation_Regular.ttf");

                if (v == null) {
                    LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = vi.inflate(R.layout.list_item_amigos, null);
                }
                final Amigo amigo = items.get(position);
                if (amigo != null) {
                        TextView tt = (TextView) v.findViewById(R.id.amigo_nombre);
                        tt.setTypeface(sansation);

                        
                        if (tt != null) {
                              tt.setText(amigo.getNombre());                            
                        }
                }
                
                Button iv_estadisticas_amigo = (Button) v.findViewById(R.id.amigos_stats);
                iv_estadisticas_amigo.setOnClickListener(new View.OnClickListener() {
					public void onClick(View view) {
                    	Intent myIntent = new Intent(Amigos.this, EstadisticasAmigo.class);
		                startActivityForResult(myIntent, 0);
                    }
                });
                
                Button iv_delete_amigo = (Button) v.findViewById(R.id.amigos_delete);
                iv_delete_amigo.setOnClickListener(new View.OnClickListener() {
                    @SuppressWarnings("deprecation")
					public void onClick(View view) {
                    	AlertDialog alerta = new AlertDialog.Builder(Amigos.this).create();
                    	alerta.setTitle("Eliminar amigo");
                    	final String nombre=amigo.getNombre();
                    	alerta.setMessage("¿Desea eliminar a " + nombre + " de tus amigos?");
                    	alerta.setButton("Sí", new DialogInterface.OnClickListener() {
        					
        					@Override
        					public void onClick(DialogInterface dialog, int which) {
        		            						
        						try {
        				        	SharedPreferences myPrefs;    
        							myPrefs = getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
        							
        				        	String email=myPrefs.getString("LOGIN_EMAIL", "");
        							String pwd=myPrefs.getString("LOGIN_PSWD", "");
        				        	
        							EliminarAmigoTask task = new EliminarAmigoTask();
        				            task.execute(email,pwd,nombre);
        							
        				            String a;
        							
        							a = task.get();
        							
        				            if(!a.contains("error"))
        				            {
                		                Intent myIntent = new Intent(Amigos.this, Amigos.class);
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
		getMenuInflater().inflate(R.menu.amigos, menu);
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
	
	class ListarAmigosTask extends AsyncTask<String, Void, String>{
		 
        @SuppressWarnings("deprecation")
		@Override
        protected String doInBackground(String... params) {
        	HttpURLConnection conn = null;
        	String message=null;

            try {           	
            	
            	URL url = new URL("http://movies-quiz.appspot.com/webservices/GetFriends?email="+params[0]+"&pwd="+params[1]);
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
	
	class EliminarAmigoTask extends AsyncTask<String, Void, String>{
		 
        @SuppressWarnings("deprecation")
		@Override
        protected String doInBackground(String... params) {
        	HttpURLConnection conn = null;
        	String message=null;

            try {           	
            	
            	URL url = new URL("http://movies-quiz.appspot.com/webservices/DeleteFriend?email="+params[0]+"&pwd="+params[1]+"&friendName="+params[2]);
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
