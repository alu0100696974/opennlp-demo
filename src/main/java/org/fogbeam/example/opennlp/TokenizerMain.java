/**
*TokenizerMain.java
*
*Created on May 25, 2003, 5:33 PM
*/

/**
*
*@author Ayoze Elvira
*/


package org.fogbeam.example.opennlp;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;


public class TokenizerMain
{
    /**@param args Array*/
     /**@throws  Exception Exception*/
	public static void main( String[] args ) throws Exception
	{
		
		// the provided model
		// InputStream modelIn = new FileInputStream( "models/en-token.bin" );
        try {
		archivo = new File("C:\\Users\\Ayoze//archivo.txt");//"archivo.txt" es el archivo que va a leer
		String linea;
		//List<String> palabras= new ArrayList(); //Creacion del array
		Integer l= new Integer(1);
            Map<String,Integer> listaPalabras = new HashMap();
            try (FileReader fr = new FileReader (archivo)) {
                BufferedReader br = new BufferedReader(fr);
                

                int a=2;
                while((linea=br.readLine())!=null) {
                    StringTokenizer st = new StringTokenizer(linea);
                    while(st.hasMoreTokens()){
                        String pal= st.nextToken();
                   //palabras.add(st.nextToken());    // Introducir las palabras en un array            
                   if(listaPalabras.containsKey(pal)){
                       listaPalabras.put(pal, a);
                   }else{
                       listaPalabras.put(pal, l); 
                   }
                    }
                }
                      int b=0;
		Iterator<String> it = listaPalabras.keySet().iterator();
		while(it.hasNext()){
		String key = it.next();
		if(listaPalabras.get(key)<2){
		b++;
		System.out.println("Clave: " + key + " -> Valor: " + listaPalabras.get(key));
			} 
		} System.out.println("Son "+ b + " palabras sin repetir");

                //System.out.println("La parabra: " + palabras); //Muestra las palabras del array
               // System.out.println("La parabra: " + listaPalabras);

            }

	}
	catch(IOException a){
	System.out.println(a);
	}
		
		
		// the model we trained
		InputStream modelIn = new FileInputStream( "models/en-token.model" );
		
		try
		{
			TokenizerModel model = new TokenizerModel( modelIn );
		
			Tokenizer tokenizer = new TokenizerME(model);
			
				/* note what happens with the "three depending on which model you use */
			String[] tokens = tokenizer.tokenize
					(  "A ranger journeying with Oglethorpe, founder of the Georgia Colony, " 
							+ " mentions \"three Mounts raised by the Indians over three of their Great Kings" 
							+ " who were killed in the Wars.\"" );
			
			for( String token : tokens )
			{
				System.out.println( token );
			}
			
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
		finally
		{
			if( modelIn != null )
			{
				try
				{
					modelIn.close();
				}
				catch( IOException e )
				{
				}
			}
		}
		System.out.println( "\n-----\ndone" );
	}
}
