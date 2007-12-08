package org.sexyprogrammer.lib.vfs.manipulation;

import org.sexyprogrammer.lib.vfs.VFile;


/**
 *    �t�@�C���̏㏑�����ɁA���s���邩�A���s���Ȃ����A�ʃt�@�C�����Ŏ��s���邩�A 
 *   �S�Ē��~���邩���f����N���X�̃C���^�[�t�F�C�X�B 
 *    
 *   @author shunji 
 *  
 */
public interface OverwritePolicy {
	/**
	 *    �㏑������B 
	 *  
	 */
	public static final int OVERWRITE = 1;
	/**
	 *    ���s���Ȃ��B 
	 *  
	 */
	public static final int NO_OVERWRITE = 2;
	/**
	 *    �ʂ̃t�@�C���ɃR�s�[����B 
	 *  
	 */
	public static final int NEW_DEST = 3;
	/**
	 *    �S�Ă̑���𒆎~����B 
	 *  
	 */
	public static final int STOP_ALL = 99;
	/**
	 *    �㏑���m�F���s���B 
	 *   �㏑������Ȃ�OVERWRITE�A���Ȃ��Ȃ�NO_OVERWRITE�A 
	 *   �����S�Ē��~����Ȃ�STOP_ALL�A 
	 *   �ʃt�@�C���ɏ㏑������Ȃ�NEW_DEST��Ԃ��B 
	 *    
	 *   @param srcFile	�㏑�����t�@�C�� 
	 *   @param dest		�㏑����t�@�C�� 
	 *   @return 
	 *  
	 */
	public int isDoOverwrite(VFile srcFile, VFile dest);
	/**
	 *    �V�����㏑����t�@�C����Ԃ��B 
	 *   @return 
	 *  
	 */
	public VFile getNewDestination();
}
