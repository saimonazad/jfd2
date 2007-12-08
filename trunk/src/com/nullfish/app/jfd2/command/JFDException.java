/*
 * Created on 2004/06/03
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2.command;

import java.text.MessageFormat;

import com.nullfish.lib.vfs.exception.VFSException;

/**
 * jFD�p��O
 * 
 * @author shunji
 */
public class JFDException extends VFSException {

	Object[] errorValues;
	
	/**
	 * �R���X�g���N�^
	 * @param message	�G���[���b�Z�[�W
	 */
	public JFDException(String message) {
		this(null, message, new Object[0]);
	}
	
	/**
	 * �R���X�g���N�^
	 * @param message	�G���[���b�Z�[�W
	 * @param errorValues	�G���[�l
	 */
	public JFDException(String message, Object[] errorValues) {
		this(null, message, errorValues);
	}
	
	/**
	 * �R���X�g���N�^
	 * 
	 * @param cause	������O
	 * @param message	�G���[���b�Z�[�W
	 * @param errorValues	�G���[�l
	 */
	public JFDException(Throwable cause, String message, Object[] errorValues) {
		super(message, cause);
		this.errorValues = errorValues;
	}
	
	/* (non-Javadoc)
	 * @see com.nullfish.lib.vfs.exception.VFSException#getName()
	 */
	public String getName() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nullfish.lib.vfs.exception.VFSException#getErrorValues()
	 */
	public Object[] getErrorValues() {
		return errorValues;
	}

	public String getErrorMessage() {
		return MessageFormat.format(getMessage(), getErrorValues());
	}
}
