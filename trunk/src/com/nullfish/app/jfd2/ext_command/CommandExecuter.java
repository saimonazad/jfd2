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
     * �V�F���s�g�p
     */
    public static final int SHELL_NONE = 0;
    
    /**
     * �V�F���g�p
     */
    public static final int USE_FILE_SHELL = 1;
    
    /**
     * �A�v���P�[�V�����p�V�F���g�p
     */
    public static final int USE_APP_SHELL = 2;
    
    
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

	public Process exec(String command, int shellType) throws IOException {
		return exec(command, shellType, null);
	}

	public Process exec(String command, int shellType, File dir)
			throws IOException {
//System.out.println(shellType);
//System.out.println(command);
//Thread.dumpStack();
		Process process = null;
		if (shellType == USE_FILE_SHELL && shell != null && shell.length() > 0) {
			String[] interpretedCommand = interpretShell(command, shell);
//for(int i=0; i<interpretedCommand.length; i++) {
//			System.out.println(i + ":" + interpretedCommand[i]);
//}
			if(interpretedCommand.length == 1) {
System.out.println(interpretedCommand[0]);
				process = Runtime.getRuntime().exec(interpretedCommand[0],
						null, dir);
//				process = Runtime.getRuntime().exec("cmd.exe /C \"Explorer /e,/root,C:\\Program^ Files\\DIFX\"",
//						null, dir);
			} else {
				process = Runtime.getRuntime().exec(interpretedCommand,
						null, dir);
			}
		} else if (shellType == USE_APP_SHELL && appShell != null && appShell.length() > 0) {
			String[] interpretedCommand = interpretShell(command, appShell);
//for(int i=0; i<interpretedCommand.length; i++) {
//				System.out.println(i + ":" + interpretedCommand[i]);
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
			process = Runtime.getRuntime().exec(command,
					null, dir);
		}

		processReader.addProcess(process);
			
		return process;
	}

	private String[] command2Array(String shell, String command) {
		String[] shellArray = string2CommandArray(shell);
		String[] rtn = new String[shellArray.length + 1];
		System.arraycopy(shellArray, 0, rtn, 0, shellArray.length);
		rtn[rtn.length - 1] = command;

		return rtn;
	}

	private String[] string2CommandArray(String command) {
		boolean inQuote = false;
		List list = new ArrayList();

		StringTokenizer tokenizer = new StringTokenizer(command, "\" ", true);
		while (tokenizer.hasMoreTokens()) {
			String token;
			if (inQuote) {
				token = tokenizer.nextToken("\"");
			} else {
				token = tokenizer.nextToken("\" ");
			}

			if (token.equals("\"")) {
				inQuote = !inQuote;
			} else if (token.equals(" ")) {
			} else {
				list.add(token);
			}
		}
		String[] rtn = new String[list.size()];

		return (String[]) (list.toArray(rtn));
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
	public String[] interpretShell(String command, String shell) {
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
}