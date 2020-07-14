package br.com.srcsoftware.manager.utilidades;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utilidades {

	public static final String SCHEMA = "sistemadechamada";

	public static final String REGEX_SOMENTE_LETRAS_E_ESPACO = "[À-ú A-z]+";

	public static final LocalDate parseLocalDate( String data ) {

		if ( data == null || data.isEmpty() || !data.contains( "/" ) ) {
			return null;
		}

		LocalDate novaData = LocalDate.parse( data, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) );

		return novaData;
	}

	public static final String parseLocalDate( LocalDate data ) {

		if ( data == null ) {
			return null;
		}

		String novaData = data.format( DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) );

		return novaData;
	}

	public static final BigDecimal parseBigDecimal( String valor ) {
		if ( valor == null || valor.isEmpty() ) {
			return null;
		}
		BigDecimal novoValor = new BigDecimal( valor.replace( ".", "" ).replaceAll( ",", "." ) );
		return novoValor;
	}

	public static final String parseBigDecimal( BigDecimal valor ) {
		if ( valor == null ) {
			return null;
		}
		return String.format( "%,.2f", valor );
	}

	public static final String parseBigDecimal( BigDecimal valor, String moeda ) {
		if ( valor == null ) {
			return null;
		}
		//O metodo TRIM tira o espaço depois da string
		return moeda + " " + String.format( "%,.2f", valor ).trim();
	}

	public static final LocalDateTime parseLocalDateTime( String data ) {

		if ( data == null || data.isEmpty() || !data.contains( "/" ) ) {
			return null;
		}

		LocalDateTime novaData = LocalDateTime.parse( data, DateTimeFormatter.ofPattern( "dd/MM/yyyy HH:mm:ss" ) );

		return novaData;
	}

	public static final String parseLocalDateTime( LocalDateTime data ) {

		if ( data == null ) {
			return null;
		}

		String novaData = data.format( DateTimeFormatter.ofPattern( "dd/MM/yyyy HH:mm:ss" ) );

		return novaData;
	}
}
