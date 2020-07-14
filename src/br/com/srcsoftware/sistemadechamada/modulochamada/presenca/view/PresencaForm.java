package br.com.srcsoftware.sistemadechamada.modulochamada.presenca.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.util.LabelValueBean;

import br.com.srcsoftware.manager.exceptions.BackendException;
import br.com.srcsoftware.sistemadechamada.modulochamada.presenca.model.PresencaPO;
import br.com.srcsoftware.sistemadechamada.modulochamada.aluno.controller.AlunoFACADE;
import br.com.srcsoftware.sistemadechamada.modulochamada.aluno.model.AlunoPO;

public class PresencaForm extends ActionForm{
	
	private String idSelecionar;
	private PresencaPO presenca;
	private ArrayList< PresencaPO > presencas;

	/** SINGLETON */
	public PresencaPO getPresenca() {
		if ( presenca == null ) {
			presenca = new PresencaPO();

		}
		return presenca;
	}

	public void setPresenca( PresencaPO presenca ) {
		this.presenca = presenca;
	}

	public ArrayList< PresencaPO > getPresencas() {
		if ( presencas == null ) {
			presencas = new ArrayList< PresencaPO >();

		}
		return presencas;
	}

	public void setPresencas( ArrayList< PresencaPO > presencas ) {
		this.presencas = presencas;
	}

	public String getIdSelecionar() {
		return idSelecionar;
	}

	public void setIdSelecionar( String idSelecionar ) {
		this.idSelecionar = idSelecionar;
	}

	public ArrayList< LabelValueBean > getComboAlunos() {
		ArrayList< LabelValueBean > options = new ArrayList<>();

		try {
			AlunoFACADE facade = new AlunoFACADE();
			List< AlunoPO > alunos = facade.filtrar( null );

			/** Montando a lista da LabelValueBean para enviar ao JSP */
			for ( AlunoPO alunoCorrente : alunos ) {
				LabelValueBean labelValue = new LabelValueBean();

				/** Preenchendo o Label com o valor que desejo exibir na Combo */
				labelValue.setLabel( alunoCorrente.getNome() );
				/** Preenchendo o Label com o valor que desejo atribuir ao property */
				labelValue.setValue( alunoCorrente.getIdToString() );

				options.add( labelValue );
			}

		} catch ( BackendException e ) {
			e.printStackTrace();
		}
		return options;
	}


	public void limparTela() {
		setIdSelecionar( null );
		setPresenca( null );
		getPresencas().clear();
	}	
	
}
