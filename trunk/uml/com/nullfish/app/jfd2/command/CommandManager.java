package com.nullfish.app.jfd2.command;

import java.util.Map;
import javax.swing.KeyStroke;
import java.io.InputStream;

public class CommandManager {
	private Map nameCommandMap;
	private Map keyCommandNameMap;
	public AbstractCommand getCommand(String commandName) {
		return null;
	}
	public AbstractCommand getCommand(KeyStroke key) {
		return null;
	}
	public void init(InputStream definition) {
	}
}
