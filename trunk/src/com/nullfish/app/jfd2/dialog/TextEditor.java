/*
 * Created on 2004/07/18
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.dialog;

import javax.swing.JComponent;

/**
 * @author shunji
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface TextEditor {
	/**
	 * ���͌��ʂ��擾����B
	 * @return
	 */
	public String getAnswer();
	
	/**
	 * ���͌��ʂ�ݒ肷��B
	 */
	public void setAnswer(String text);

	/**
	 * �R���|�[�l���g���擾����B
	 * @return
	 */
	public JComponent getComponent();
}
