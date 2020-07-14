package br.com.srcsoftware.manager.utilidades;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

public class MessageResource {

	private static Properties propertie;

	/**
	 * Método responsável por retornar o valor do atributo propertie.
	 * 
	 * @return Properties propertie - propertie a ser retornado(a).
	 */
	public static Properties getPropertie() {
		try {
			if ( propertie == null ) {
				propertie = new Properties();
				propertie.load( MessageResource.class.getResourceAsStream( "/application.properties" ) );
			}
		} catch ( IOException e ) {
			e.printStackTrace();
		}
		return propertie;
	}

	public static final String getString( String key ) {
		return getPropertie().getProperty( key );
	}

	public static final String getString( String key, String... parametros ) {
		return MessageFormat.format( getString( key ), parametros );
	}
	
}
