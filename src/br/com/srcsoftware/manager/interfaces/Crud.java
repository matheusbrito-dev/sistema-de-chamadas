package br.com.srcsoftware.manager.interfaces;

import java.util.List;

import br.com.srcsoftware.manager.abstracts.AbstractPO;
import br.com.srcsoftware.manager.exceptions.BackendException;

public interface Crud {

	void inserir( AbstractPO po ) throws BackendException;

	void alterar( AbstractPO po ) throws BackendException;

	void excluir( AbstractPO po ) throws BackendException;

	List filtrar( AbstractPO po ) throws BackendException;

	AbstractPO filtrarPorId( String id ) throws BackendException;

	
}
