/*
 * Created on 2004/08/30
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.ui.container2;

import com.nullfish.app.jfd2.JFDComponent;

/**
 * @author shunji
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface JFDContainer {
	/**
	 * �R���|�[�l���g��ݒ肷��
	 * @param component
	 */
	public void setComponent(JFDComponent component);
	
	/**
	 * �R���|�[�l���g���擾����
	 */
	public JFDComponent getComponent();
	
	/**
	 * �R���|�[�l���g���폜����
	 */
	public void clearComponent();
	
	/**
	 * �R���e�i�̉�����؂�ւ���
	 * @param visible
	 */
	public void setContainerVisible(boolean visible);
	
	/**
	 * �^�C�g����ݒ肷��
	 * @param title
	 */
	public void setTitle(String title);
	
	/**
	 * �^�C�g�����擾����
	 * @return �^�C�g��
	 */
	public String getTitle();
	
	/**
	 * �p������
	 *
	 */
	public void dispose();
	
	/**
	 * ���̃R���e�i���ێ�����R���|�[�l���g�Ƀt�H�[�J�X�����N�G�X�g����B
	 *
	 */
	public void requestContainerFocus();
}
