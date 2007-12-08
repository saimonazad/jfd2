/*
 * Created on 2004/08/16
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.dialog.focus;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * @author shunji
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class FocusOrderManagingPanel extends JPanel {
	/**
	 * �����̃R���|�[�l���g�̃��X�g
	 */
	private List members = new ArrayList();

	/**
	 * ��O�̃p�l��
	 */
	private FocusOrderManagingPanel prevPanel;

	/**
	 * ���̃p�l��
	 */
	private FocusOrderManagingPanel nextPanel;

	public FocusOrderManagingPanel() {
	}

	/**
	 * �v�f��ǉ�����B
	 * @param comp
	 */
	public void addMember(Component comp) {
		members.add(comp);
	}
	
	/**
	 * @param nextPanel
	 *            The nextPanel to set.
	 */
	public void setNextPanel(FocusOrderManagingPanel nextPanel) {
		this.nextPanel = nextPanel;
	}

	/**
	 * @param prevPanel
	 *            The prevPanel to set.
	 */
	public void setPrevPanel(FocusOrderManagingPanel prevPanel) {
		this.prevPanel = prevPanel;
	}

	/**
	 * �w�肳�ꂽ�R���|�[�l���g�̃C���f�b�N�X�����߂�B
	 * ���������̃p�l���ɑ��݂��Ȃ��ꍇ��-1��Ԃ��B
	 * @param comp
	 * @return
	 */
	public int indexOf(Component comp) {
		for (int i = 0; i < members.size(); i++) {
			if(members.get(i) == comp) {
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * �w��C���f�b�N�X�̃R���|�[�l���g���擾����B
	 */
	public Component getComponentToFocus(int index) {
		if(index < 0) {
			return prevPanel.getComponentToFocus(index + prevPanel.getCount());
		}
		
		if(index < members.size()) {
			return (Component)members.get(index);
		}
		
		return nextPanel.getComponentToFocus(index - members.size());
	}
	
	/**
	 * �R���|�[�l���g�̐������߂�B
	 * @return
	 */
	public int getCount() {
		return members.size();
	}
}