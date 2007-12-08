/*
 * Created on 2004/06/07
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.lib.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.nullfish.lib.keymap.KeyStrokeMap;

/**
 * �������̑I����������I�Ԃ̂Ɏg���R���|�[�l���g�B
 * �t�H�[�J�X�̏�Ԃɂ���Ęg�����ς��B
 * �g�p��Adispose()���ĂԕK�v������B
 * 
 * ���쌠:   Copyright (c) Shunji Yamaura
 * ��Ж�:
 * @author Shunji Yamaura
 * @version 1.0
 */
public class ChooserPanel extends JPanel implements FocusListener {
	//	�I�����W�I�{�^���̔z��
	JRadioButton[] buttons;
	
	Choice[] choice;
	
	ButtonGroup group = new ButtonGroup();
	
	TitledBorder border;
	
	static Border focusedBorder = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
//	static Border focusedBorder = BorderFactory.createRaisedBevelBorder();
	
//	static Border noFocusBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	static Border noFocusBorder = BorderFactory.createEmptyBorder();
//	static Border noFocusBorder = BorderFactory.createLineBorder(Color.WHITE);
	
	GridLayout layout;
	
	private List listeners = new ArrayList();
	
	public static final String SELECT_NEXT = "next";
	
	public static final String SELECT_PREV = "prev";
	
	/**
	 * �R���X�g���N�^
	 * 
	 * @param	title	�g�ɕ\�������^�C�g���̕�����
	 * @param	choice	�I�����ƂȂ镶����
	 * @param	keys	�I�����W�I�{�^���̃V���[�g�J�b�g�L�[
	 * @param	rows	���W�I�{�^���́A���\����
	 * @param	cols	���W�I�{�^���́A�c�\����
	 * @param	defaultChoice	�ŏ��ɑI������Ă���I���̔ԍ�
	 */
	public ChooserPanel(
		String title,
		Choice[] choice,
		int cols,
		String defaultChoice) {
		for(int i=0; i<choice.length; i++) {
			if(choice[i].getName().equals(defaultChoice)) {
				init(title, choice, cols, i);
				return;
			}
		}
		init(title, choice, cols, 0);		
	}
	
	/**
	 * �R���X�g���N�^
	 * 
	 * @param	title	�g�ɕ\�������^�C�g���̕�����
	 * @param	choice	�I�����ƂȂ镶����
	 * @param	keys	�I�����W�I�{�^���̃V���[�g�J�b�g�L�[
	 * @param	rows	���W�I�{�^���́A���\����
	 * @param	cols	���W�I�{�^���́A�c�\����
	 * @param	defaultChoice	�ŏ��ɑI������Ă���I���̔ԍ�
	 */
	public ChooserPanel(
		String title,
		Choice[] choice,
		int cols,
		int defaultChoice) {
		init(title, choice, cols, defaultChoice);
	}
	
	private void init(
			String title,
			Choice[] choice,
			int cols,
			int defaultChoice) {
		this.choice = choice;
		
		int rows = 1+ ((choice.length - 1) / cols);
		
		//	�{�[�_�[�ݒ�
        border = new TitledBorder(noFocusBorder, title);
        this.setBorder(border);
        
        this.setFocusable(true);
        
   		layout = new GridLayout(rows, cols);
		this.setLayout(layout);
		
		buttons = new JRadioButton[choice.length];

		ActionMap actionMap = getActionMap();
				
		InputMap inWindowMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

		for(int i=0; i<buttons.length; i++) {
			if(choice[i].getMnemonic() == (char)-1) {
				buttons[i] = new JRadioButton(choice[i].getLabel());
			} else {
				buttons[i] = new JRadioButton(choice[i].getLabel() + '(' + Character.toUpperCase(choice[i].getMnemonic()) + ')');
				inWindowMap.put(KeyStroke.getKeyStroke(choice[i].getMnemonic()), buttons[i]);
			}
			buttons[i].setFocusable(false);
			buttons[i].setEnabled(choice[i].isActive());
			buttons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					requestFocusInWindow();
				}
			});
			
			actionMap.put(buttons[i], new SelectAction(buttons[i]));
			
			group.add(buttons[i]);
			this.add(buttons[i]);
			
			if(i == defaultChoice) {
				buttons[i].setSelected(true);
			}
		}
		
		actionMap.put(SELECT_NEXT, new SelectNextAction());
		actionMap.put(SELECT_PREV, new SelectPrevAction());
	
		InputMap focusedMap = getInputMap(JComponent.WHEN_FOCUSED);
		focusedMap.put(KeyStrokeMap.getKeyStroke(KeyEvent.VK_RIGHT, 0), SELECT_NEXT);
		focusedMap.put(KeyStrokeMap.getKeyStroke(KeyEvent.VK_DOWN, 0), SELECT_NEXT);

		focusedMap.put(KeyStrokeMap.getKeyStroke(KeyEvent.VK_LEFT, 0), SELECT_PREV);
		focusedMap.put(KeyStrokeMap.getKeyStroke(KeyEvent.VK_UP, 0), SELECT_PREV);
		
		addFocusListener(this);
	}

	/**
	 * �p��
	 */
	public void dispose() {
		getActionMap().clear();
		removeFocusListener(this);
	}
		
	/**
	 * �I�����ꂽ���W�I�{�^���̃C���f�b�N�X��Ԃ�
	 */
	public int getSelectedIndex() {
		for(int i=0; i<buttons.length; i++) {
			if(buttons[i].isSelected()) {
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * �I�����ꂽ������Ԃ��B
	 * 
	 * @return
	 */
	public String getSelectedAnswer() {
		int index = getSelectedIndex();
		if(index < 0) {
			return null;
		}
		
		return choice[index].getName();
	}

	public void setSelectedAnser(String answer) {
		for(int i=0; i<choice.length; i++) {
			if(answer.equals(choice[i].getName())) {
				buttons[i].setSelected(true);
				return;
			}
		}
	}
	
	/**
	 * �C���f�b�N�X�̃��W�I�{�^����I������
	 */
	public void setSelectedIndex(int index) {
		buttons[index].setSelected(true);
	}
	
	/**
	 * ���݂̑I���̎��̃��W�I�{�^����I��Action
	 */
	class SelectNextAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			int index = getSelectedIndex();
			while(true) {
				index++;
				index -= (index >= buttons.length ? buttons.length : 0);
				if(buttons[index].isEnabled()) {
					break;
				}
			}
			
			buttons[index].setSelected(true);
		}
	}
	
	/**
	 * ���݂̑I���̑O�̃��W�I�{�^����I��Action
	 */
	class SelectPrevAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			int index = getSelectedIndex();
			while(true) {
				index--;
				if(index < 0) {
					index += buttons.length;
				}
				if(buttons[index].isEnabled()) {
					break;
				}
			}
			
			buttons[index].setSelected(true);
		}
	}
	
	/**
	 * ����̃��W�I�{�^����I�сA�t�H�[�J�X���Ă�Action
	 */
	class SelectAction extends AbstractAction {
		JRadioButton button;
		
		public SelectAction(JRadioButton button) {
			this.button = button;
		}
			
		public void actionPerformed(ActionEvent e) {
			button.setSelected(true);
			requestFocusInWindow();
		}
	}
	
	public void focusGained(FocusEvent e) {
		border.setBorder(focusedBorder);
		repaint();
	}

	public void focusLost(FocusEvent e) {
		border.setBorder(noFocusBorder);
		repaint();
	}
	
	/**
	 * ���X�i��ǉ�����B
	 * ���X�i�͑I�����ω������ۂɌĂяo�����B
	 * @param listener
	 */
	public void addActionListener(ActionListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * ���X�i���폜����B
	 * @param listener
	 */
	public void removeActionListener(ActionListener listener) {
		listeners.remove(listener);
	}
	
	/**
	 * �I�����ω��������Ƃ�
	 *
	 */
	private void noticeActionPerformed() {
		ActionEvent e = new ActionEvent(this, 0, "");
		
		for(int i=0; i<listeners.size(); i++) {
			((ActionListener)listeners.get(i)).actionPerformed(e);
		}
	}
}
