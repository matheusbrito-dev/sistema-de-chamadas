package br.com.srcsoftware.sistemadechamada.modulochamada.aluno.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.util.LabelValueBean;

import br.com.srcsoftware.manager.exceptions.BackendException;
import br.com.srcsoftware.sistemadechamada.modulochamada.aluno.model.AlunoPO;
import br.com.srcsoftware.sistemadechamada.modulochamada.turma.controller.TurmaFACADE;
import br.com.srcsoftware.sistemadechamada.modulochamada.turma.model.TurmaPO;

public class AlunoForm extends ActionForm{
	
	private String idSelecionar;
	private AlunoPO aluno;
	private ArrayList< AlunoPO > alunos;

	/** SINGLETON */
	public AlunoPO getAluno() {
		if ( aluno == null ) {
			aluno = new AlunoPO();

		}
		return aluno;
	}

	public void setAluno( AlunoPO aluno ) {
		this.aluno = aluno;
	}

	public ArrayList< AlunoPO > getAlunos() {
		if ( alunos == null ) {
			alunos = new ArrayList< AlunoPO >();

		}
		return alunos;
	}

	public void setAlunos( ArrayList< AlunoPO > alunos ) {
		this.alunos = alunos;
	}

	public String getIdSelecionar() {
		return idSelecionar;
	}

	public void setIdSelecionar( String idSelecionar ) {
		this.idSelecionar = idSelecionar;
	}

	public ArrayList< LabelValueBean > getComboTurmas() {
		ArrayList< LabelValueBean > options = new ArrayList<>();

		try {
			TurmaFACADE facade = new TurmaFACADE();
			List< TurmaPO > turmas = facade.filtrar( null );

			/** Montando a lista da LabelValueBean para enviar ao JSP */
			for ( TurmaPO turmaCorrente : turmas ) {
				LabelValueBean labelValue = new LabelValueBean();

				/** Preenchendo o Label com o valor que desejo exibir na Combo */
				labelValue.setLabel( turmaCorrente.getNome() );
				/** Preenchendo o Label com o valor que desejo atribuir ao property */
				labelValue.setValue( turmaCorrente.getIdToString() );

				options.add( labelValue );
			}

		} catch ( BackendException e ) {
			e.printStackTrace();
		}
		return options;
	}


	public void limparTela() {
		setIdSelecionar( null );
		setAluno( null );
		getAlunos().clear();
	}
}
