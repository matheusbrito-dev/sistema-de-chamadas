package br.com.srcsoftware.sistemadechamada.modulochamada.turma.view;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import br.com.srcsoftware.sistemadechamada.modulochamada.turma.model.TurmaPO;


public class TurmaForm extends ActionForm{
	
	private String idSelecionar;
	private TurmaPO turma;
	private ArrayList< TurmaPO > turmas;

	/** SINGLETON */
	public TurmaPO getTurma() {
		if ( turma == null ) {
			turma = new TurmaPO();

		}
		return turma;
	}

	public void setTurma( TurmaPO turma ) {
		this.turma = turma;
	}

	public ArrayList< TurmaPO > getTurmas() {
		if ( turmas == null ) {
			turmas = new ArrayList< TurmaPO >();

		}
		return turmas;
	}

	public void setTurmas( ArrayList< TurmaPO > turmas ) {
		this.turmas = turmas;
	}

	public String getIdSelecionar() {
		return idSelecionar;
	}

	public void setIdSelecionar( String idSelecionar ) {
		this.idSelecionar = idSelecionar;
	}

	public void limparTela() {
		setIdSelecionar( null );
		setTurma( null );
		getTurmas().clear();
	}


}

