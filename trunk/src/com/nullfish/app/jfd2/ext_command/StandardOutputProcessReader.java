/*
 * Created on 2004/09/12
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.ext_command;

/**
 * <p>�^�C�g��: StandardOutputProcessReader</p>
 * <p>����: jFD�̊O���R�}���h����̏o�͂�W���o�͂ɏo�͂��郊�[�_�[�N���X</p>
 * <p>���쌠: Copyright (c) Shunji Yamaura</p>
 * <p>��Ж�: </p>
 * @author Shunji Yamaura
 * @version 1.0
 */


public class StandardOutputProcessReader implements ProcessReader {
    public static final int READER_ID = 2;
    
    /**
     * ���̃N���X�̃V���O���g���C���X�^���X
     */
    private static StandardOutputProcessReader instance = new StandardOutputProcessReader();

    /**
     * �f�t�H���g�R���X�g���N�^
     */
    private StandardOutputProcessReader() {}

    /**
     * ���̃N���X�̃V���O���g���C���X�^���X���擾���郁�\�b�h
     */
    public static ProcessReader getInstance() {
        return instance;
    }

    public void addProcess(Process process) {
        StreamReaderThread outReaderThread = new StreamReaderThread(process.getInputStream(), System.out);
        outReaderThread.start();
        StreamReaderThread errReaderThread = new StreamReaderThread(process.getErrorStream(), System.err);
        errReaderThread.start();
    }

    /**
     * ���̃N���X��ID���擾����
     */
    public int getReaderID() {
        return READER_ID;
    }

    /**
     * �\������
     */
    public void showConsole(boolean visible) {
        //  �������Ȃ�
    }

	public void setShowsAutomatic(boolean shows) {
	}
}
