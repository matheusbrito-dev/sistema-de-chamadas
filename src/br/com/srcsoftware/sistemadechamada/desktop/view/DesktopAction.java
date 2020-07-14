package br.com.srcsoftware.sistemadechamada.desktop.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DesktopAction extends DispatchAction{
	
	public ActionForward abrir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		return mapping.findForward( "desktopView" );
	}

	
}
