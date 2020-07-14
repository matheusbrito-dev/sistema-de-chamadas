package br.com.srcsoftware.sistemadechamada.modulochamada.turma.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.srcsoftware.manager.connection.HibernateConnection;
import br.com.srcsoftware.manager.exceptions.BackendException;
import br.com.srcsoftware.sistemadechamada.modulochamada.turma.model.TurmaPO;

public final class TurmaDAO{

	public void inserir( HibernateConnection hibernate, TurmaPO po ) throws BackendException {
		/**
		 * Invocando o método inserir do nosso
		 * componente de Conexão HibernateConnection
		 */

		TurmaPO poInserido = (TurmaPO) hibernate.inserir( po );

		/** Pegando o ID do Turma Recem inserida */
		po.setId( poInserido.getId() );
	}

	public void alterar( HibernateConnection hibernate, TurmaPO po ) throws BackendException {

		hibernate.alterar( po );
	}

	public void excluir( HibernateConnection hibernate, TurmaPO po ) throws BackendException {

		hibernate.excluir( po );
	}

	public TurmaPO filtrarPorId( Long id ) throws BackendException {
		return (TurmaPO) new HibernateConnection().filtrarPorId( id, TurmaPO.class );
	}

	public List< TurmaPO > filtrar( TurmaPO poFiltrar ) throws BackendException {

		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			//Fazer o que tem que fazer
			/** utilizando CriteriaBuilder para a confecção de nossas Queries */
			//Criando a Criteria com base na CriteriaBuilder
			CriteriaBuilder builder = hibernate.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery criteria = builder.createQuery( TurmaPO.class );

			//Definindo o FROM
			Root root = criteria.from( TurmaPO.class );

			//Deixando a Criteria preparada para a consulta
			criteria.select( root );

			//Definindo os Parametros(Predicados) para a consulta(WHERE)
			ArrayList< Predicate > predicados = new ArrayList< Predicate >();

			if ( poFiltrar != null ) {
				if ( poFiltrar.getNome() != null && poFiltrar.getNome().isEmpty() ) {
					Predicate nomeParam = builder.like( root.get( "nome" ), poFiltrar.getNome().concat( "%" ) );
					predicados.add( nomeParam );
				}
				if ( poFiltrar.getNomeInstrutor() != null && poFiltrar.getNomeInstrutor().isEmpty() ) {
					Predicate nomeInstrutorParam = builder.like( root.get( "nomeInstrutor" ), poFiltrar.getNomeInstrutor().concat( "%" ) );
					predicados.add( nomeInstrutorParam );
				}
				if ( poFiltrar.getTipo() != null && poFiltrar.getTipo().isEmpty() ) {
					Predicate tipoParam = builder.like( root.get( "tipo" ), poFiltrar.getTipo().concat( "%" ) );
					predicados.add( tipoParam );
				}
				if ( poFiltrar.getCargaHoraria() != null) {
					Predicate cargaHorariaParam = builder.equal(root.get("cargaHoraria"), poFiltrar.getCargaHoraria());
					predicados.add( cargaHorariaParam );
				}
				if ( poFiltrar.getDataInicio() != null) {
					Predicate dataInicioParam = builder.equal(root.get("dataInicio"), poFiltrar.getDataInicio());
					predicados.add( dataInicioParam );
				}
				if ( poFiltrar.getDataTermino() != null) {
					Predicate dataTerminoParam = builder.equal(root.get("dataTermino"), poFiltrar.getDataTermino());
					predicados.add( dataTerminoParam );
				}
			}

			//Adicionando o Predicado no WHERE
			criteria.where( predicados.toArray( new Predicate[ predicados.size() ] ) );

			List< TurmaPO > encontrados = hibernate.getCurrentSession().createQuery( criteria ).getResultList();

			hibernate.commitTransacao();

			return encontrados;

		} catch ( BackendException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Throwable e ) {
			hibernate.rollbackTransacao();
			throw new BackendException( "Erro ao filtrar", e );
		}

	}
}