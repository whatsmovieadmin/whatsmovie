package Utils;

import android.annotation.SuppressLint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Vector;


@SuppressLint("UseSparseArrays")
public  class GestorJuego {
	
	private HashMap<Integer, String[]> bd;
	private List<Integer> usados;
	
	public void cargarMemoria(String nivelJuego) {
		try {
			bd= new HashMap<Integer, String[]>();
			
			String nivel=nivelJuego;
			
			String fase = null;
			
			if(nivel.compareTo("nivel1")==0)
			{
				fase="fotogramas";
			}
			if(nivel.compareTo("nivel2")==0)
			{
				fase="carteles";
			}
			if(nivel.compareTo("nivel3")==0)
			{
				fase="actores";
			}
			if(nivel.compareTo("nivel4")==0)
			{
				fase="frases";
			}
			if(nivel.compareTo("nivel5")==0)
			{
				fase="malos";
			}
			
			BufferedReader bf = new BufferedReader(new FileReader("/glosario/"+fase+".txt"));
			
			String sCadena;
			int i=1;
			
			while ((sCadena = bf.readLine())!=null) {
				String[] linkTitulo = new String[]{sCadena.substring(0, sCadena.indexOf("/!&?/")), sCadena.substring(sCadena.indexOf("/!&?/")+5)};
				bd.put(i, linkTitulo);
				i=i+1;
			}
			
			bf.close();
			
			usados=new Vector<Integer>();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public String[] peliculaYTitulosCorrectoYAleatorios() {
		
		Random r = new Random();
		
		int sizeBD=bd.size();
		
		int keyPelicula=((r.nextInt(sizeBD))+1);
		
		if(usados!=null)
		{
			while(usados.contains(keyPelicula))
			{
				keyPelicula=(r.nextInt(bd.size()))+1;
			}
		}
		
		String[] enlaceYTitulo =bd.get(keyPelicula);
		
		String enlace=enlaceYTitulo[0];
		
		String titulo=enlaceYTitulo[1];
	    
	    int keyTituloMalo1=(r.nextInt(bd.size()))+1;
	    
	    while(keyPelicula==keyTituloMalo1)
		{
	    	keyTituloMalo1=(r.nextInt(bd.size()))+1;
		}
	    
	    String tituloMalo1=bd.get(keyTituloMalo1)[1];
	    
	    int keyTituloMalo2=(r.nextInt(bd.size()))+1;
	    
	    while(keyPelicula==keyTituloMalo2)
		{
	    	keyTituloMalo2=(r.nextInt(bd.size()))+1;
		}
	    
	    String tituloMalo2=bd.get(keyTituloMalo2)[1];
	    
	    int keyTituloMalo3=(r.nextInt(bd.size()))+1;
	    
	    while(keyPelicula==keyTituloMalo3)
		{
	    	keyTituloMalo3=(r.nextInt(bd.size()))+1;
		}
	    
	    String tituloMalo3=bd.get(keyTituloMalo3)[1];
	    
	    String[] dei = new String[]{enlace, titulo, tituloMalo1, tituloMalo2, tituloMalo3};
	    
		usados.add(keyPelicula);
		
		return dei;	
	}
	
	
}
