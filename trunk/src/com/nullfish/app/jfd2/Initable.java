/*
 * Created on 2004/06/20
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2;

import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * ����̃f�B���N�g���̃t�@�C�������ɏ������\�ȃN���X�̃C���^�[�t�F�C�X
 * 
 * @author shunji
 */
public interface Initable {
	public void init(VFile baseDir) throws VFSException;
}
