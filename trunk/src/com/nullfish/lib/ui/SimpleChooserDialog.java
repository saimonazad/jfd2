/*
 * Created on 2004/06/08
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.lib.ui;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.DefaultListModel;
import javax.swing.InputMap;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import com.nullfish.lib.keymap.KeyStrokeMap;

/**
 * �V���v���ȑI��p�_�C�A���O�B
 * Jlist�����邾���B
 * 
 * @author shunji
 */
public class SimpleChooserDialog extends JDialog implements MouseListener {
	JList list = new JList();
	JScrollPane scroll = new JScrollPane();

	//	���X�g�̃f�[�^���f��
	DefaultListModel model = new DefaultListModel();

	//	�I�����ꂽ������
	Object choosen;

	public static final String CHOICE_ACTION = "choice";

	public static final String CANCEL_ACTION = "cancel";

	/**
	 * �R���X�g���N�^
	 * 
	 * @param owner �e�t���[��
	 */
	public SimpleChooserDialog(Frame owner) {
		super(owner, true);

		init();
		initKey();
	}

	/**
	 * �R���X�g���N�^
	 * 
	 * @param owner �e�_�C�A���O
	 */
	public SimpleChooserDialog(Dialog owner) {
		super(owner, true);
		setUndecorated(true);

		init();
		initKey();
	}

	private void init() {
		list.setModel(model);
		list.setVisibleRowCount(7);
		scroll.setViewportView(list);
		getContentPane().add(scroll);

		list.addMouseListener(this);
	}

	private void initKey() {
		ActionMap actionMap = list.getActionMap();
		actionMap.put(CHOICE_ACTION, new ChoiceAction());
		actionMap.put(CANCEL_ACTION, new CancelAction());

		InputMap imap = list.getInputMap();
		imap.put(KeyStrokeMap.getKeyStroke(KeyEvent.VK_ENTER, 0), CHOICE_ACTION);
		imap.put(KeyStrokeMap.getKeyStroke(KeyEvent.VK_ESCAPE, 0), CANCEL_ACTION);
	}

	/**
	 * �p�������B
	 * ���X�i���O��
	 */
	public void dispose() {
		list.removeMouseListener(this);
	}

	/**
	 * �I�����ꂽ�v�f��Ԃ��B
	 * �������L�����Z�����ꂽ�Ȃ�null��Ԃ��B
	 * 
	 * @return Object
	 */
	public Object getChoosen() {
		return choosen;
	}

	public void setListData(Object[] data) {
		model.removeAllElements();
		for (int i = 0; i < data.length; i++) {
			model.addElement(data[i]);
		}
		list.setSelectedIndex(0);
	}

	public void setListData(java.util.List data) {
		model.removeAllElements();
		for (int i = 0; i < data.size(); i++) {
			model.add(0, data.get(i));
		}

		if (model.size() > 0) {
			list.setSelectedIndex(0);
		}
	}
	
	public void addData(Object data) {
		model.add(0, data);
	}
	
	public void setSelectedData(Object value) {
		list.setSelectedValue(value, true);
	}

	public void setVisible(boolean bool) {
		if (bool) {
			choosen = null;
		}

		super.setVisible(bool);
	}

    /**
     * �����_���R���|�[�l���g���Z�b�g����
     */
    public void setCellRenderer(ListCellRenderer renderer) {
    	list.setCellRenderer(renderer);
    }

	/*------------------------ MouseListener ----------------------*/
	/**
	 * �_�u���N���b�N�̏���
	 */
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			choosen = (String) list.getSelectedValue();
			setVisible(false);
		}
	}

	/**
	 * Invoked when a mouse button has been pressed on a component.
	 */
	public void mousePressed(MouseEvent e) {
	}

	/**
	 * Invoked when a mouse button has been released on a component.
	 */
	public void mouseReleased(MouseEvent e) {
	}

	/**
	 * Invoked when the mouse enters a component.
	 */
	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * Invoked when the mouse exits a component.
	 */
	public void mouseExited(MouseEvent e) {
	}

	/*------------------------ FocusListener ----------------------*/
	/**
	 * �I�����A�_�C�A���O�����Action�N���X
	 */
	class ChoiceAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			choosen = list.getSelectedValue();
			setVisible(false);
		}
	}

	/**
	 * �L�����Z�����A�_�C�A���O�����Action�N���X
	 */
	class CancelAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			choosen = null;
			setVisible(false);
		}
	}
}
