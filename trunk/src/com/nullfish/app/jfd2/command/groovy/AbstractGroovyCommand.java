/*
 * Created on 2005/01/04
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.command.groovy;

import groovy.lang.Binding;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import com.nullfish.app.jfd2.JFD;
import com.nullfish.app.jfd2.command.Command;
import com.nullfish.app.jfd2.dialog.DialogUtilities;
import com.nullfish.lib.vfs.Manipulation;
import com.nullfish.lib.vfs.VFS;
import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * @author shunji
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class AbstractGroovyCommand extends Command {
	public static final String DEFAULT_SCRIPT_DIR = new File(new File(new File(System.getProperty("user.home")), ".jfd_user"), "script").getAbsolutePath();

	/**
	 * スクリプトファイルのディレクトリ
	 */
	public static final String SCRIPT_DIR_PATH = "script_dir";
	/**
	 * スクリプトファイル
	 */
	public static final String SCRIPT = "script";
	/**
	 * スクリプトディレクトリオープンコマンド
	 */
	public static final String OPEN_SCRIPT_DIR = "openScriptDir";
	/**
	 * 実行後、不要ファイルシステムをクローズするかのフラグ
	 */
	private boolean closesUnusingFileSystem = false;

	/**
	 * 子操作を設定する。
	 * @param children
	 */
	public void setChildManipulations(Manipulation[] children) {
		super.setChildManipulations(children);
	}

	public void setClosesUnusingFileSystem(boolean closesUnusingFileSystem) {
		this.closesUnusingFileSystem = closesUnusingFileSystem;
	}

	public boolean closesUnusingFileSystem() {
		return closesUnusingFileSystem;
	}

	protected VFile getScriptDirectory() throws VFSException {
		VFS vfs = VFS.getInstance(getJFD());
		JFD jfd = getJFD();
		String scriptDirPath = (String)jfd.getCommonConfigulation().getParam(SCRIPT_DIR_PATH, DEFAULT_SCRIPT_DIR);
		return vfs.getFile(scriptDirPath);
	}
	
	protected Binding getBinding() {
		Binding binding = new Binding();
		binding.setVariable("jfd", getJFD());
		binding.setVariable("command", this);
		binding.setVariable("vfs", VFS.getInstance(getJFD()));
		
		return binding;
	}

	protected void showErrorMessage(Exception e, String message) {
		PrintStream writer = null;
		
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			writer = new PrintStream(baos);
			if(message != null) {
				writer.println(message);
			}
			e.printStackTrace(writer);
			
			baos.flush();
			
			DialogUtilities.showTextAreaMessageDialog(getJFD(), baos.toString(), false, "jFD2");
		} catch (Exception ex) {
		} finally {
			try {writer.close(); } catch (Exception ex) {}
		}
	}
}
