/*
 * Created on 2004/09/11
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.ext_command;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.nullfish.app.jfd2.JFD;
import com.nullfish.app.jfd2.ext_command.window.WindowProcessReader;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * @author shunji
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CommandExecuter {
	/**
	 * �V���O���g���C���X�^���X
	 */
	private static CommandExecuter instance = new CommandExecuter();

    //  �O���A�v���P�[�V��������̏o�͂�ǂݏo���N���X
//    ProcessReader processReader = StandardOutputProcessReader.getInstance();
    ProcessReader processReader = WindowProcessReader.getInstance();

    private String shell;

    private String appShell;

	/**
	 * �V�F���p�����[�^��
	 */
	public static final String PARAM_SHELL = "shell";

	/**
	 * �A�v���P�[�V�����V�F���p�����[�^��
	 */
	public static final String PARAM_APP_SHELL = "app_shell";

	/**
	 * �R���X�g���N�^
	 *  
	 */
	private CommandExecuter() {
	}

	/**
	 * �V���O���g���C���X�^���X���擾����B
	 * 
	 * @return
	 */
	public static CommandExecuter getInstance() {
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nullfish.app.jfd2.Initable#init(com.nullfish.lib.vfs.VFile)
	 */
	public void init(JFD jfd) throws VFSException {
		shell = (String) jfd.getCommonConfigulation().getParam(PARAM_SHELL, null);
		appShell = (String) jfd.getCommonConfigulation().getParam(PARAM_APP_SHELL, null);
		processReader.setShowsAutomatic(((Boolean)jfd.getCommonConfigulation().getParam("shows_console_auto", Boolean.TRUE)).booleanValue());
	}

	public Process exec(String command, boolean useShell)
			throws IOException {
		return exec(command, useShell, null);
	}
	
	public Process exec(String command, boolean useShell, File dir)
			throws IOException {
//System.out.println(useShell);
//System.out.println(command);
//Thread.dumpStack();
		Process process = null;
		if (useShell && shell != null && shell.length() > 0) {
			String[] interpretedCommand = interpretShell(command);
//for(int i=0; i<interpretedCommand.length; i++) {
//			System.out.println(i + ":" + interpretedCommand[i]);
//}
			if(interpretedCommand.length == 1) {
//System.out.println(interpretedCommand[0]);
				process = Runtime.getRuntime().exec(interpretedCommand[0],
						null, dir);
			} else {
				process = Runtime.getRuntime().exec(interpretedCommand,
						null, dir);
			}
		} else {
//System.out.println(command);
			process = Runtime.getRuntime().exec(command,
					null, dir);
		}

		processReader.addProcess(process);
			
		return process;
	}

	/**
	 * �V�F����`�����߂���B
	 * $C -> �R�}���h
	 * $Q -> �_�u���N�H�[�g�ň͂܂ꂽ�R�}���h
	 * $$CQ -> �������R�}���h�ɃX�y�[�X���܂܂�Ă���_�u���N�H�[�g�ň͂��R�}���h
	 * 
	 * @param command
	 * @return
	 */
	public String[] interpretShell(String command) {
		command = command.replaceAll("\\$N", "\n");
		String formatStr = new String(shell);
		formatStr = formatStr.replaceAll("\\$CQ", "{2}");
		formatStr = formatStr.replaceAll("\\$C", "{0}");
		formatStr = formatStr.replaceAll("\\$Q", "{1}");
		
		String[] commands = new String[3];
		commands[0] = command;
		commands[1] = "\"" + command + "\"";
		Matcher matcher = Pattern.compile("\\s").matcher(command);
		if(matcher.find()) {
			commands[2] = commands[1];
		} else {
			commands[2] = commands[0];
		}
		
		String result = MessageFormat.format(formatStr, commands);
		return result.split("\n");
	}

	public Process exec(String app, String[] params, boolean useShell)
		throws IOException {
		return exec(app, params, useShell, null);
	}
	
	public Process exec(String app, String[] params, boolean useShell, File dir)
		throws IOException {
//System.out.println(app);
//System.out.println(params);
//Thread.dumpStack();
		Process process = null;
		
		if (useShell && appShell != null && appShell.length() > 0) {
			String[] interpretedCommand = interpretAppShell(app, params);
//for(int i=0; i<interpretedCommand.length; i++) {
//		System.out.println(i + ":" + interpretedCommand[i]);
//}
			if(interpretedCommand.length == 1) {
				process = Runtime.getRuntime().exec(interpretedCommand[0],
						null, dir);
			} else {
				process = Runtime.getRuntime().exec(interpretedCommand,
						null, dir);
			}
		} else {
//System.out.println(command);
			StringBuffer sb = new StringBuffer(app);
			sb.append(" ");
			for(int i=0; i<params.length; i++) {
				sb.append(params[i] + " ");
			}
			process = Runtime.getRuntime().exec(sb.toString(),
					null, dir);
		}
		
		processReader.addProcess(process);
			
		return process;
	}

	/**
	 * �V�F����`�����߂���B
	 * $A -> �A�v���P�[�V����
	 * $B -> �X�y�[�X��؂�̃p�����[�^
	 * $BQ -> �X�y�[�X��؂�̃_�u���N�H�[�g�ł��������p�����[�^
	 * $BN -> ���s��؂�̃p�����[�^
	 * $BNQ -> �X�y�[�X��؂�̃_�u���N�H�[�g�ł��������p�����[�^
	 * 
	 * @param command
	 * @return
	 */
	public String[] interpretAppShell(String app, String[] params) {
		app = app.replaceAll("\\$N", "\n");
		StringBuffer paramSpaceSplit = new StringBuffer();
		StringBuffer paramSpaceSplitQuate = new StringBuffer();
		StringBuffer paramLineSplit = new StringBuffer();
		StringBuffer paramLineSplitQuate = new StringBuffer();
		for(int i=0; i<params.length; i++) {
			paramSpaceSplit.append(params[i] + " ");
			paramSpaceSplitQuate.append("\"" +params[i] + "\" ");
			paramLineSplit.append(params[i] + "\n");
			paramLineSplitQuate.append("\"" + params[i] + "\"\n");
		}
		
		String formatStr = new String(appShell);
		formatStr = formatStr.replaceAll("\\$BNQ", "{4}");
		formatStr = formatStr.replaceAll("\\$BQ", "{2}");
		formatStr = formatStr.replaceAll("\\$BN", "{3}");
		formatStr = formatStr.replaceAll("\\$A", "{0}");
		formatStr = formatStr.replaceAll("\\$B", "{1}");
		
		String[] commands = new String[5];
		commands[0] = app;
		commands[1] = paramSpaceSplit.toString();
		commands[2] = paramSpaceSplitQuate.toString();
		commands[3] = paramLineSplit.toString();
		commands[4] = paramLineSplitQuate.toString();
		
		String result = MessageFormat.format(formatStr, commands);
		return result.split("\n");
	}
}