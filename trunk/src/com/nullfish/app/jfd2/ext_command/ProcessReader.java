/*
 * Created on 2004/09/12
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.ext_command;

/**
 * <p>�^�C�g��: ProcessReader</p>
 * <p>����: �O���R�}���h����̏o�͂�ǂ݂Ƃ�N���X�̃C���^�[�t�F�C�X</p>
 * <p>���쌠: Copyright (c) Shunji Yamaura</p>
 * <p>��Ж�: </p>
 * @author Shunji Yamaura
 * @version 1.0
 */


public interface ProcessReader {
    /**
     * ���̃��[�_�[�N���X�ɏo�͂���v���Z�X��ǉ�����
     */
    public void addProcess(Process process);

    /**
     * ���̃N���X��ID���擾����
     */
    public int getReaderID();

    /**
     * �\������
     */
    public void showConsole(boolean visible);
    
    /**
     * �o�͎��Ɏ����ŕ\�����邩�ǂ������Z�b�g����B
     * @param shows
     */
    public void setShowsAutomatic(boolean shows);
}
