package br.com.srcsoftware.sistemadechamada.modulochamada.presenca.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.manager.abstracts.AbstractPO;
import br.com.srcsoftware.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistemadechamada.modulochamada.aluno.model.AlunoPO;

@Entity
@Table(name = "presencas", uniqueConstraints = @UniqueConstraint(columnNames = { "presente","idAluno" }, name = "UK_presencas"))
public class PresencaPO extends AbstractPO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 10, nullable = false)
	private LocalDate data;

	@Column(length = 2, nullable = false)
	private Boolean presente;

	@Column(length = 50, nullable = false)
	private String observacao;

	@ManyToOne(fetch = FetchType.EAGER, optional = false) // Padrão quando ManyToOne
	@JoinColumn(name = "idAluno", foreignKey = @ForeignKey(name = "fk_aluno_presenca")) // ToOne Vem
	private AlunoPO aluno;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Transient
	public String getIdToString() {
		if (getId() != null) {
			return getId().toString();
		}
		return null;
	}

	public void setIdToString(String id) {
		if (id != null && !id.isEmpty()) {
			setId(Long.valueOf(id));
			return;
		}
		setId(null);
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	@Transient
	public String getDataToString() {
		if (getData() != null) {
			return Utilidades.parseLocalDate(data);
		}
		return null;
	}

	public void setDataToString(String data) {
		if (data != null && !data.isEmpty()) {
			setData(Utilidades.parseLocalDate(data));
			return;
		}
		setData(null);
	}

	public Boolean getPresente() {
		return presente;
	}

	public void setPresente(Boolean presente) {
		this.presente = presente;
	}

	@Transient
	public String getPresenteToString() {
		if (getPresente() != null && getPresente() == true) {
			return "Presente";
		} else if (getPresente() != null && getPresente() == false) {
			return "Faltou";
		}
		return null;
	}

	public void setPresenteToString(String presente) {
		if (presente != null && !presente.isEmpty()) {
			setPresente(Boolean.valueOf(presente));
			return;
		}
		setId(null);
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public AlunoPO getAluno() {
		if (aluno == null) {
			aluno = new AlunoPO();
		}
		return aluno;
	}

	public void setAluno(AlunoPO aluno) {
		this.aluno = aluno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
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
		PresencaPO other = (PresencaPO) obj;
		if (aluno == null) {
			if (other.aluno != null)
				return false;
		} else if (!aluno.equals(other.aluno))
			return false;
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
		builder.append("PresencaPO [\n\t id=");
		builder.append(id);
		builder.append(",\n\t data=");
		builder.append(data);
		builder.append(",\n\t presente=");
		builder.append(presente);
		builder.append(",\n\t observacao=");
		builder.append(observacao);
		builder.append(",\n\t aluno=");
		builder.append(aluno);
		builder.append("]");
		return builder.toString();
	}

}
