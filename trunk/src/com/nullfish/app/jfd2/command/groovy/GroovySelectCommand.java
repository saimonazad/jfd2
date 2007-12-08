/*
 * Created on 2004/07/06
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.command.groovy;

import groovy.lang.GroovyShell;

import java.io.InputStream;
import java.util.List;

import org.codehaus.groovy.control.CompilationFailedException;

import com.nullfish.app.jfd2.JFD;
import com.nullfish.app.jfd2.dialog.DialogUtilities;
import com.nullfish.app.jfd2.dialog.JFDDialog;
import com.nullfish.app.jfd2.resource.JFDResource;
import com.nullfish.lib.vfs.VFS;
import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.ManipulationStoppedException;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * @author shunji
 *
 */
public class GroovySelectCommand extends AbstractGroovyCommand {
	public static final String CONFIG_ENCODING = "script_encoding";
	
	/* (non-Javadoc)
	 * @see com.nullfish.lib.vfs.manipulation.abst.AbstractManipulation#doExecute()
	 */
	public void doExecute() throws VFSException {
		InputStream is = null;
		JFDDialog dialog = null;
		try {
			VFS vfs = VFS.getInstance(getJFD());
			JFD jfd = getJFD();
			String scriptDirPath = (String)jfd.getCommonConfigulation().getParam(SCRIPT_DIR_PATH, DEFAULT_SCRIPT_DIR);
			VFile scriptDir = vfs.getFile(scriptDirPath);
			if(!scriptDir.exists(this)) {
				scriptDir.createDirectory(this);
			}
			VFile[] files = scriptDir.getChildren(this);
			String[] scripts = new String[files.length];
			for(int i=0; i<files.length; i++) {
				scripts[i] = files[i].getName();
			}
			
			dialog = DialogUtilities.createOkCancelDialog(jfd);
			dialog.addButton(OPEN_SCRIPT_DIR, JFDResource.LABELS.getString("open_script_dir"), 'p', false);
			
			dialog.addMessage(JFDResource.MESSAGES.getString("message_select_script"));
			
			String lastScript = (String)jfd.getLocalConfigulation().getParam("last_script", null);
			dialog.addComboBox(SCRIPT, scripts, lastScript, false, true, null);

			List encodeList = (List)jfd.getCommonConfigulation().getParam("grep_encode_all", null);
			dialog.addComboBox(CONFIG_ENCODING, encodeList, (String)jfd.getCommonConfigulation().getParam(CONFIG_ENCODING, "UTF-8"), false, false, null);
			
			dialog.pack();
			dialog.setVisible(true);
			
			String answer = dialog.getButtonAnswer();
			String script = dialog.getTextFieldAnswer(SCRIPT);
			if(OPEN_SCRIPT_DIR.equals(answer)) {
				if(!scriptDir.exists(this)) {
					scriptDir.createDirectory(this);
				}
				
				jfd.getModel().setDirectory(scriptDir, 0);
				return;
			}
			
			if (answer == null || JFDDialog.CANCEL.equals(answer) || script == null
					|| script.length() == 0) {
				return;
			}
			
			jfd.getLocalConfigulation().setParam("last_script", script);
			VFile scriptFile = scriptDir.getChild(script);
			//is = scriptFile.getInputStream(this);

			GroovyShell shell = new GroovyShell(getBinding());

			//shell.evaluate(is);
			String encoding = dialog.getTextFieldAnswer(CONFIG_ENCODING);
			jfd.getCommonConfigulation().setParam(CONFIG_ENCODING, encoding);
			String scriptText = new String(scriptFile.getContent(this), encoding);
			shell.evaluate(scriptText);
		} catch (ManipulationStoppedException e) {
		} catch (CompilationFailedException e) {
			showErrorMessage(e, e.getLocalizedMessage());
			//throw new JFDException(JFDResource.MESSAGES.getString("can_not_execute_script"), null);
		} catch (RuntimeException e) {
			showErrorMessage(e, null);
			//throw new JFDException(e, e.getMessage(), new Object[0]);
		} catch (Exception e) {
			showErrorMessage(e, null);
			//throw new JFDException(e, e.getMessage(), new Object[0]);
		} finally {
			if(is != null) {
				try {
					is.close();
				} catch (Exception e) {
				}
			}
			
			if(dialog != null) {
				dialog.dispose();
			}
		}
	}
}
