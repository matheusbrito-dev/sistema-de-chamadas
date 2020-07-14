package br.com.srcsoftware.sistemadechamada.modulochamada.aluno.model;

import java.text.Collator;
import java.util.Locale;

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
import br.com.srcsoftware.sistemadechamada.modulochamada.turma.model.TurmaPO;

@Entity
@Table(name="alunos", uniqueConstraints = @UniqueConstraint(columnNames= {"nome","idTurma"},name="UK_alunos"))
public class AlunoPO extends AbstractPO{
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column (length=30, nullable= false)
	private String nome;
	
	@Column (length=50, nullable= false)
	private String endereco;
	
	@Column (length=40, nullable= false)
	private String email;
	
	@Column (length=30, nullable= false)
	private String telefones;
	
	@Column (length=30, nullable= false)
	private String facebook;
	
	@ManyToOne( fetch = FetchType.EAGER, optional = false ) //Padrão quando ManyToOne
	@JoinColumn( name = "idTurma", foreignKey = @ForeignKey( name = "fk_turma_aluno" ) ) //ToOne Vem
	private TurmaPO turma;
	
	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	@Transient
	public String getIdToString() {
		if ( getId() != null ) {
			return getId().toString();
		}
		return null;
	}

	public void setIdToString( String id ) {
		if ( id != null && !id.isEmpty() ) {
			setId( Long.valueOf( id ) );
			return;
		}
		setId( null );
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefones() {
		return telefones;
	}
	public void setTelefones(String telefones) {
		this.telefones = telefones;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	public TurmaPO getTurma() {
		if(turma == null) {
			turma = new TurmaPO();
		}
		return turma;
	}
	public void setTurma(TurmaPO turma) {
		this.turma = turma;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((turma == null) ? 0 : turma.hashCode());
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
		AlunoPO other = (AlunoPO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (turma == null) {
			if (other.turma != null)
				return false;
		} else if (!turma.equals(other.turma))
			return false;
		return true;
	}

	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AlunoPO [id=");
		builder.append(id);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", endereco=");
		builder.append(endereco);
		builder.append(", email=");
		builder.append(email);
		builder.append(", telefones=");
		builder.append(telefones);
		builder.append(", facebook=");
		builder.append(facebook);
		builder.append(", turma=");
		builder.append(turma);
		builder.append("]");
		return builder.toString();
	}

	public int compareTo( AlunoPO comparar) {
		Collator ignoraAcentos = Collator.getInstance(new Locale("pt","BR"));
		return ignoraAcentos.compare(this.getNome(), comparar.getNome());
	}
	
}
