/*
 * Created on 2004/07/06
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.command.groovy;


import groovy.lang.GroovyShell;

import org.codehaus.groovy.control.CompilationFailedException;

import com.nullfish.app.jfd2.command.JFDException;
import com.nullfish.app.jfd2.resource.JFDResource;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * @author shunji
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GroovyFileCommand extends AbstractGroovyCommand {
	private String scriptFile;
	
	public GroovyFileCommand(String scriptFile) {
		this.scriptFile = scriptFile;
	}
	
	/* (non-Javadoc)
	 * @see com.nullfish.lib.vfs.manipulation.abst.AbstractManipulation#doExecute()
	 */
	public void doExecute() throws VFSException {
		try {
			GroovyShell shell = new GroovyShell(getBinding());

			shell.evaluate(getScriptDirectory().getChild(scriptFile).getInputStream());
		} catch (CompilationFailedException e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
			throw new JFDException(JFDResource.MESSAGES.getString("can_not_execute_script"), null);
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new JFDException(e, e.getMessage(), new Object[0]);
		} catch (Exception e) {
			e.printStackTrace();
			throw new JFDException(e, e.getMessage(), new Object[0]);
		}
	}
}
