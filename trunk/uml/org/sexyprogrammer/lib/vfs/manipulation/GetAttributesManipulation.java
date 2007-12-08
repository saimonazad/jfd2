package org.sexyprogrammer.lib.vfs.manipulation;

import org.sexyprogrammer.lib.vfs.Manipulation;
import org.sexyprogrammer.lib.vfs.FileAttribute;


/**
 *    �t�@�C���̊e�푮�����������A�擾���鑀��C���^�[�t�F�C�X�B 
 *   @author Shunji Yamaura 
 *  
 */
public interface GetAttributesManipulation extends Manipulation {
	/**
	 *    ���얼 
	 *  
	 */
	public static final String NAME = "init attribute";
	/**
	 *    �t�@�C���������擾����B 
	 *    
	 *   @return 
	 *  
	 */
	public abstract FileAttribute getAttribute();
}
