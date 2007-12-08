package com.nullfish.app.jfd2.viewer.jlayer;

import java.io.InputStream;

import com.nullfish.lib.vfs.exception.VFSException;

public interface StreamFactory {
	/**
	 * �C���v�b�g�X�g���[�����擾����B
	 * 
	 * @return
	 */
	public InputStream getInputStream() throws VFSException;
	
	/**
	 * �\�����閼�̂��擾����B
	 * @return
	 */
	public String getName();
	
}
