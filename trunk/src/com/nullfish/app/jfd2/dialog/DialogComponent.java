/*
 * Created on 2004/08/03
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.dialog;

/**
 * �_�C�A���O�ɕ\�������R���|�[�l���g�̃C���^�[�t�F�C�X�B
 * ���\�b�h�̎����͕K���X���b�h�Z�[�t�Ƃ��Ď��������B
 * 
 * @author shunji
 */
public interface DialogComponent {
	/**
	 * ���莞�i�G���^�[�L�[�������j�Ƀ_�C�A���O����邩�ǂ����ݒ肷��B
	 * @param bool
	 */
	public void setClosesOnDecision(boolean bool);

	/**
	 * �ݒ����ݒ肷��B
	 * @param config
	 */
	public void setConfigulationInfo(ConfigulationInfo config);
	
	/**
	 * �����ݒ�ɔ��f������B
	 *
	 */
	public void applyConfigulation();
}
