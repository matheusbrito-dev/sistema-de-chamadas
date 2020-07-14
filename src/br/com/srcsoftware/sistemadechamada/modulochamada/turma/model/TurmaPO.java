package br.com.srcsoftware.sistemadechamada.modulochamada.turma.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.srcsoftware.manager.abstracts.AbstractPO;
import br.com.srcsoftware.manager.utilidades.Utilidades;


@Entity
@Table(name= "turmas")
public class TurmaPO extends AbstractPO{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 50, nullable = false)
	private String nomeInstrutor;
	
	@Column(length = 50, nullable = false)
	private String nome;
	
	@Column(length = 20, nullable = false)
	private String tipo;
	
	@Column(length = 5, nullable = false)
	private Short cargaHoraria;
	
	@Column(length = 20, nullable = false)
	private LocalDate dataInicio;
	
	@Column(length = 20, nullable = false)
	private LocalDate dataTermino;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Transient
	public String getIdToString() {
		if(getId() != null) {
			return getId().toString();
		}
		return null;
	}
	public void setIdToString(String id) {
		if(id != null && !id.isEmpty()) {
			setId(Long.valueOf(id));
			return;
		}
		setId (null);
	}
	@Transient
	public String getDataInicioToString() {
		if ( getDataInicio() != null ) {
			return Utilidades.parseLocalDate(getDataInicio());
		}
		return null;
	}

	public void setDataInicioToString( String dataInicio ) {
		if ( dataInicio != null && !dataInicio.isEmpty() ) {
			setDataInicio( Utilidades.parseLocalDate(dataInicio));
			return;
		}
		setDataInicio( null );
	}
	@Transient
	public String getDataTerminoToString() {
		if ( getDataTermino() != null ) {
			return Utilidades.parseLocalDate(getDataTermino());
		}
		return null;
	}

	public void setDataTerminoToString( String dataTermino ) {
		if ( dataTermino != null && !dataTermino.isEmpty() ) {
			setDataTermino( Utilidades.parseLocalDate(dataTermino));
			return;
		}
		setDataTermino( null );
	}
	
	@Transient
	public String getCargaHorariaToString() {
		if(getCargaHoraria()!=null) {
			return getCargaHoraria().toString();
		}
		return null;
	}
	public void setCargaHorariaToString(String cargaHoraria) {
		if(cargaHoraria != null && !cargaHoraria.isEmpty()) {
			setCargaHoraria(Short.valueOf(cargaHoraria));
			return;
		}
		setCargaHoraria (null);
		
	}
	
	
	
	public String getNomeInstrutor() {
		return nomeInstrutor;
	}
	public void setNomeInstrutor(String nomeInstrutor) {
		this.nomeInstrutor = nomeInstrutor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Short getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(Short cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	public LocalDate getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}
	public LocalDate getDataTermino() {
		return dataTermino;
	}
	public void setDataTermino(LocalDate dataTermino) {
		this.dataTermino = dataTermino;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TurmaPO other = (TurmaPO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TurmaPO[\n\t id=");
		builder.append(id);
		builder.append("\n\t nomeInstrutor=");
		builder.append(nomeInstrutor);
		builder.append("\n\t nome=");
		builder.append(nome);
		builder.append("\n\t tipo=");
		builder.append(tipo);
		builder.append("\n\t cargaHoraria=");
		builder.append(cargaHoraria);
		builder.append("\n\t dataInicio=");
		builder.append(dataInicio);
		builder.append("\n\t dataTermino=");
		builder.append(dataTermino);
		builder.append("]\n");
		return builder.toString();
	}
	
	
}
