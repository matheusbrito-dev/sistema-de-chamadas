package br.com.srcsoftware.sistemadechamada.modulochamada.aluno.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.srcsoftware.manager.connection.HibernateConnection;
import br.com.srcsoftware.manager.exceptions.BackendException;
import br.com.srcsoftware.sistemadechamada.modulochamada.aluno.model.AlunoPO;
import br.com.srcsoftware.sistemadechamada.modulochamada.turma.model.TurmaPO;

public final class AlunoDAO {

	public void inserir( HibernateConnection hibernate, AlunoPO po ) throws BackendException {
		/**
		 * Invocando o método inserir do nosso
		 * componente de Conexão HibernateConnection
		 */

		AlunoPO poInserido = (AlunoPO) hibernate.inserir( po );

		/** Pegando o ID do Aluno Recem inserida */
		po.setId( poInserido.getId() );
	}

	public void alterar( HibernateConnection hibernate, AlunoPO po ) throws BackendException {

		hibernate.alterar( po );
	}

	public void excluir( HibernateConnection hibernate, AlunoPO po ) throws BackendException {

		hibernate.excluir( po );
	}

	public AlunoPO filtrarPorId( Long id ) throws BackendException {
		return (AlunoPO) new HibernateConnection().filtrarPorId( id, AlunoPO.class );
	}

	public List< AlunoPO > filtrar( AlunoPO poFiltrar ) throws BackendException {

		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			//Fazer o que tem que fazer
			/** utilizando CriteriaBuilder para a confecção de nossas Queries */
			//Criando a Criteria com base na CriteriaBuilder
			CriteriaBuilder builder = hibernate.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery criteria = builder.createQuery( AlunoPO.class );

			//Definindo o FROM
			Root root = criteria.from( AlunoPO.class );

			//Deixando a Criteria preparada para a consulta
			criteria.select( root );

			//Definindo os Parametros(Predicados) para a consulta(WHERE)
			ArrayList< Predicate > predicados = new ArrayList< Predicate >();

			if ( poFiltrar != null ) {
				if ( poFiltrar.getNome() != null && poFiltrar.getNome().isEmpty() ) {
					Predicate nomeParam = builder.like( root.get( "nome" ), poFiltrar.getNome().concat( "%" ) );
					predicados.add( nomeParam );
				}
				if ( poFiltrar.getEndereco() != null && poFiltrar.getEndereco().isEmpty() ) {
					Predicate enderecoParam = builder.like( root.get( "endereco" ), poFiltrar.getEndereco().concat( "%" ) );
					predicados.add( enderecoParam );
				}
				if ( poFiltrar.getEmail() != null && poFiltrar.getEmail().isEmpty() ) {
					Predicate emailParam = builder.like( root.get( "email" ), poFiltrar.getEmail().concat( "%" ) );
					predicados.add( emailParam );
				}
				if ( poFiltrar.getTelefones() != null && poFiltrar.getTelefones().isEmpty() ) {
					Predicate telefonesParam = builder.like( root.get( "telefones" ), poFiltrar.getTelefones().concat( "%" ) );
					predicados.add( telefonesParam );
				}
				if ( poFiltrar.getFacebook() != null && poFiltrar.getFacebook().isEmpty() ) {
					Predicate facebookParam = builder.like( root.get( "facebook" ), poFiltrar.getFacebook().concat( "%" ) );
					predicados.add( facebookParam );
				}

				/** Criando um JOIN com TurmaPO */
				Join< AlunoPO, TurmaPO > joinTurma = root.join( "turma", JoinType.INNER );
				/*Sub join do join
				 * Join< TurmaPO, SubTurmaPO > joinSupTurma = joinTurma.join( "supTurma", JoinType.INNER );*/
				if ( poFiltrar.getTurma().getNome() != null && !poFiltrar.getTurma().getNome().isEmpty() ) {
					Predicate nomeTurmaParam = builder.like( joinTurma.get( "nome" ), poFiltrar.getTurma().getNome().concat( "%" ) );
					predicados.add( nomeTurmaParam );
				}

			}

			//Adicionando o Predicado no WHERE
			criteria.where( predicados.toArray( new Predicate[ predicados.size() ] ) );

			List< AlunoPO > encontrados = hibernate.getCurrentSession().createQuery( criteria ).getResultList();

			hibernate.commitTransacao();

			return encontrados;

		} catch (

		BackendException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Throwable e ) {
			hibernate.rollbackTransacao();
			throw new BackendException( "Erro ao filtrar", e );
		}

	}
	
	
}
