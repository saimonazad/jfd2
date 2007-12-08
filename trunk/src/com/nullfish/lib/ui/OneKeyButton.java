/*
 * Created on 2004/06/07
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.lib.ui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import com.nullfish.lib.keymap.KeyStrokeMap;

/**
 * Altとか無しで１キーで押せるボタン
 * 
 * @author shunji
 */
public class OneKeyButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4870489433997724398L;
	public static final String CLICK_ACTION = "click_action";

	public OneKeyButton(KeyStroke key) {
		super();
		registerKey(key);
	}

	public OneKeyButton(Action a, KeyStroke key) {
		super(a);
		registerKey(key);
	}

	public OneKeyButton(Icon icon, KeyStroke key) {
		super(icon);
		registerKey(key);

	}

	public OneKeyButton(String text, KeyStroke key) {
		super(text + "(" + KeyEvent.getKeyText(key.getKeyCode()) + ")");
		registerKey(key);
	}

	public OneKeyButton(String text, Icon icon, KeyStroke key) {
		super(text + "(" + KeyEvent.getKeyText(key.getKeyCode()) + ")");
		registerKey(key);
	}

	public void registerKey(KeyStroke key) {
		this.setMnemonic(key.getKeyChar());
		ActionMap actionMap = getActionMap();

		actionMap.put(CLICK_ACTION, new ButtonClickAction(this));

		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key, CLICK_ACTION);
		getInputMap(JComponent.WHEN_FOCUSED).put(KeyStrokeMap.getKeyStroke(KeyEvent.VK_ENTER, 0), CLICK_ACTION);
	}

	public class ButtonClickAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 7696541629407658217L;
		AbstractButton button;

		public ButtonClickAction(AbstractButton button) {
			this.button = button;
		}

		public void actionPerformed(ActionEvent e) {
			button.doClick();
		}
	}
}