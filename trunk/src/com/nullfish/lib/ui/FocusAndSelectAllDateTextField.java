/*
 * Created on 2004/07/09
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.lib.ui;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;

import com.nullfish.app.jfd2.dialog.TextEditor;
import com.nullfish.app.jfd2.dialog.components.FocusNextAction;
import com.nullfish.lib.keymap.KeyStrokeMap;


/**
 * �_�C�A���O�p�^�C���X�^���v�e�L�X�g�t�B�[���h
 * �t�H�[�J�X���󂯎������S�I������B
 * 
 * @author shunji
 */
public class FocusAndSelectAllDateTextField extends JFormattedTextField implements TextEditor {
	public FocusAndSelectAllDateTextField() {
		super(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));
		
		addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				setSelectionStart(0);
				setSelectionEnd(getText().length());
			}

			public void focusLost(FocusEvent e) {
			}
		});
		
		getInputMap(JComponent.WHEN_FOCUSED).put(KeyStrokeMap.getKeyStroke(KeyEvent.VK_ENTER, 0), FocusNextAction.NAME);
		getActionMap().put(FocusNextAction.NAME, new FocusNextAction(this));
	}

	/* (non-Javadoc)
	 * @see com.nullfish.app.jfd2.ui.dialog.TextEditor#getAnswer()
	 */
	public String getAnswer() {
		return getText();
	}

	/* (non-Javadoc)
	 * @see com.nullfish.app.jfd2.ui.dialog.TextEditor#getComponent()
	 */
	public JComponent getComponent() {
		return this;
	}

	public void setAnswer(String text) {
		setText(text);
	}
}
