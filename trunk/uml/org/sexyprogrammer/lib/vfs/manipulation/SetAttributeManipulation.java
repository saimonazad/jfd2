package org.sexyprogrammer.lib.vfs.manipulation;

import org.sexyprogrammer.lib.vfs.Manipulation;
import org.sexyprogrammer.lib.vfs.FileAttribute;


/**
 *    @author shunji 
 *   
 *   ���̐������ꂽ�R�����g�̑}�������e���v���[�g��ύX���邽�� 
 *   �E�B���h�E > �ݒ� > Java > �R�[�h���� > �R�[�h�ƃR�����g 
 *  
 */
public interface SetAttributeManipulation extends Manipulation {
	/**
	 *    ���얼 
	 *  
	 */
	public static final String NAME = "set attribute";
	/**
	 *    �������Z�b�g����B 
	 *   @param attr 
	 *  
	 */
	public abstract void setAttribute(FileAttribute attr);
}
