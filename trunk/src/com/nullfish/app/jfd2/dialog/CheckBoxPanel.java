/*
 * Created on 2004/06/06
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2.dialog;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JCheckBox;

import com.nullfish.app.jfd2.dialog.components.ConfigCheckBox;
import com.nullfish.app.jfd2.dialog.focus.FocusOrderManagingPanel;
import com.nullfish.lib.ui.ReturnableRunnable;
import com.nullfish.lib.ui.ThreadSafeUtilities;

/**
 * �`�F�b�N�{�b�N�X��ێ�����p�l���B
 * n�s2��ŏ��ԂɃ`�F�b�N�{�b�N�X��z�u����B
 * 
 * @author shunji
 */
public class CheckBoxPanel extends FocusOrderManagingPanel {
	/**
	 * ���̂ƃ`�F�b�N�{�b�N�X�̃}�b�v
	 */
	Map nameCheckBoxMap = new HashMap();

	/**
	 * �`�F�b�N�{�b�N�X�̃��X�g
	 */
	List checkBoxList = new ArrayList();
	
	/**
	 * �R���X�g���N�^
	 *
	 */
	public CheckBoxPanel() {
		setLayout(new GridBagLayout());
	}

	/**
	 * �`�F�b�N�{�b�N�X��ǉ�����B
	 * 
	 * @param name
	 * @param label
	 * @param mnemonic
	 * @param defaultValue
	 */
	public void addCheckBox(String name, ConfigCheckBox checkBox) {
		int index = nameCheckBoxMap.size();
		this.add(checkBox, new GridBagConstraints(index % 2, index / 2, 1, 1,
				1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		super.addMember(checkBox);
		nameCheckBoxMap.put(name, checkBox);
		checkBoxList.add(checkBox);
	}

	/**
	 * �w�薼�̂̃`�F�b�N�{�b�N�X���`�F�b�N����Ă���Ȃ�true��Ԃ��B
	 * �X���b�h�Z�[�t�B
	 * @param name
	 * @return
	 */
	public boolean isChecked(String name) {
		final JCheckBox checkBox = (JCheckBox)nameCheckBoxMap.get(name);
		
		ReturnableRunnable runnable = new ReturnableRunnable() {
			private boolean checked;
			
			public void run() {
				checked = checkBox.isSelected();
			}
			
			public Object getReturnValue() {
				return Boolean.valueOf(checked);
			}
		};
		
		ThreadSafeUtilities.executeReturnableRunnable(runnable);

		return ((Boolean)runnable.getReturnValue()).booleanValue();
	}
	
	/**
	 * �ύX��ݒ�ɔ��f����B
	 *
	 */
	public void applyConfig() {
		for(int i=0; i<checkBoxList.size(); i++) {
			((DialogComponent)checkBoxList.get(i)).applyConfigulation();
		}
	}
	
	public boolean focusFirstComponent() {
		if(checkBoxList.size() > 0) {
			((ConfigCheckBox)checkBoxList.get(0)).requestFocusInWindow();
			return true;
		}
		
		return false;
	}
}