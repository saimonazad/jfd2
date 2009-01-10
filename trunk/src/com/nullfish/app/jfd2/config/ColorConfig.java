package com.nullfish.app.jfd2.config;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nullfish.app.jfd2.resource.JFDResource;
import com.nullfish.lib.ui.ColorUtility;

public class ColorConfig extends JPanel {
	/**
	 * �ݒ薼
	 */
	private String configName;

	/**
	 * �F
	 */
	private Color color = Color.WHITE;

	/**
	 * �f�t�H���g�F
	 */
	private Color defaultColor;

	private JLabel label = new JLabel(" ");

	private JButton changeButton = new JButton();

	public ColorConfig(String configName, String labelName, Color defaultColor) {
		this.configName = configName;
		this.defaultColor = defaultColor;

		initGui();
		label.setText(" " + JFDResource.LABELS.getString(labelName));
		colorChanged();
	}

	private void initGui() {
		label.setOpaque(true);
		Dimension labelSize = label.getPreferredSize();
		labelSize.width = 150;
		label.setPreferredSize(labelSize);
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		changeButton.setAction(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				showColorChooser(color);
			}
		});
		changeButton.setText(JFDResource.LABELS.getString("modify"));

		setLayout(new GridBagLayout());
		add(label, new GridBagConstraints(0, 0, 1, 1, 0, 0,
				GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1,
						1, 1, 1), 0, 0));
		add(changeButton, new GridBagConstraints(1, 0, 1, 1, 0, 0,
				GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1,
						1, 1, 1), 0, 0));
	}

	/**
	 * �ݒ��\���ɔ��f����B
	 * 
	 * @param configulation
	 */
	public void setConfigulation(Configulation configulation) {
		color = (Color) configulation.getParam(configName, defaultColor);
		colorChanged();
	}

	/**
	 * �ݒ�ɔ��f����B
	 * 
	 * @param configulation
	 */
	public void apply(Configulation configulation) {
		configulation.setParam(configName, color);
	}

	/**
	 * �F���ύX���ꂽ�ۂɌĂяo�����B
	 * 
	 * @param configulation
	 */
	private void colorChanged() {
		label.setBackground(color);
		//label.setForeground(ColorUtility.getComplementaryColor(color));
	}

	/**
	 * �F�I���_�C�A���O��\������B
	 * 
	 * @param originalColor
	 */
	private void showColorChooser(Color originalColor) {
		Color choosen = JColorChooser.showDialog(this, label.getText(), color);

		if (choosen != null) {
			color = choosen;
			colorChanged();
		}
	}
}
