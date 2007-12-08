package com.nullfish.app.jfd2.command;

import com.nullfish.app.jfd2.Command;
import com.nullfish.app.jfd2.JFD2;

public abstract class AbstractCommand implements Command {
	/**
	 *  経過ウインドウ表示までの時間
	 */
	private int progressShownInterval;
	public void setAttribute(String name, Object value) {
	}
	public Object getAttribute(String name) {
		return null;
	}
	public boolean isSynchronous() {
		return false;
	}
	public JFD2 getJFD() {
		return null;
	}
}
