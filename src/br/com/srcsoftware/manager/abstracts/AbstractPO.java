package br.com.srcsoftware.manager.abstracts;

import java.beans.Transient;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import br.com.srcsoftware.manager.utilidades.Utilidades;

@MappedSuperclass
public abstract class AbstractPO{

	@Column( name = "dataHoraCadastro", nullable = false )
	private LocalDateTime dataHoraCadastro;

	public AbstractPO(){
		setDataHoraCadrastro( LocalDateTime.now() );
	}

	public LocalDateTime getDataHoraCadastro() {
		return dataHoraCadastro;
	}

	public void setDataHoraCadrastro( LocalDateTime dataHoraCadastro ) {
		this.dataHoraCadastro = dataHoraCadastro;
	}

	@Transient
	public String getDataHoraCadastroToString() {
		if ( getDataHoraCadastro() != null ) {
			return Utilidades.parseLocalDateTime( getDataHoraCadastro() );
		}
		return null;
	}

	public void setDataHoraCadastroToString( String dataHoraCadastro ) {
		if ( dataHoraCadastro != null && !dataHoraCadastro.isEmpty() ) {
			setDataHoraCadrastro( Utilidades.parseLocalDateTime( dataHoraCadastro ) );
			return;
		}
		setDataHoraCadrastro( null );
	}

	@Override
	public abstract String toString();

	@Override
	public abstract int hashCode();

	@Override
	public abstract boolean equals( Object obj );

}