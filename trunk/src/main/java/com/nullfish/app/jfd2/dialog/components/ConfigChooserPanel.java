/*
 * Created on 2004/08/08
 *
 */
package com.nullfish.app.jfd2.dialog.components;

import java.awt.event.KeyEvent;

import javax.swing.JComponent;

import com.nullfish.app.jfd2.dialog.ConfigulationInfo;
import com.nullfish.app.jfd2.dialog.DialogComponent;
import com.nullfish.lib.keymap.KeyStrokeMap;
import com.nullfish.lib.ui.Choice;
import com.nullfish.lib.ui.ChooserPanel;


public class ConfigChooserPanel extends ChooserPanel implements DialogComponent {
	private ConfigulationInfo config;
	
	private boolean closeOnDecision = false;
	
	/**
	 * @param title
	 * @param choice
	 * @param cols
	 * @param defaultChoice
	 */
	public ConfigChooserPanel(String title, Choice[] choice, int cols, int defaultChoice) {
		super(title, choice, cols, defaultChoice);

		getInputMap(JComponent.WHEN_FOCUSED).put(KeyStrokeMap.getKeyStroke(KeyEvent.VK_ENTER, 0, true), FocusNextAction.NAME);
		getActionMap().put(FocusNextAction.NAME, new FocusNextAction(this));
	}

	/**
	 * コンストラクタ
	 * @param title
	 * @param choice
	 * @param cols
	 * @param defaultChoice
	 */
	public ConfigChooserPanel(String title, Choice[] choice, int cols, String defaultChoice) {
		super(title, choice, cols, defaultChoice);
		
		getInputMap(JComponent.WHEN_FOCUSED).put(KeyStrokeMap.getKeyStroke(KeyEvent.VK_ENTER, 0, true), FocusNextAction.NAME);
		getActionMap().put(FocusNextAction.NAME, new FocusNextAction(this));
	}
	
	/* (non-Javadoc)
	 * @see com.nullfish.app.jfd2.ui.dialog.ConfigulationEditor#setConfigulationInfo(com.nullfish.app.jfd2.ui.dialog.ConfigulationInfo)
	 */
	public void setConfigulationInfo(ConfigulationInfo config) {
		this.config = config;
		if(config == null) {
			return;
		}
		
		String value = (String)config.getParam();
		if(value != null) {
			setSelectedAnser(value);
		}
	}

	/* (non-Javadoc)
	 * @see com.nullfish.app.jfd2.ui.dialog.ConfigulationEditor#applyConfigulation()
	 */
	public void applyConfigulation() {
		if(config != null) {
			config.setParam(getSelectedAnswer());
		}
	}

	/* (non-Javadoc)
	 * @see com.nullfish.app.jfd2.dialog.DialogComponent#setClosesOnDecision(boolean)
	 */
	public void setClosesOnDecision(boolean bool) {
		closeOnDecision = bool;
	}
}
