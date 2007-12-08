package org.sexyprogrammer.lib.vfs.manipulation;

import org.sexyprogrammer.lib.vfs.Manipulation;
import org.sexyprogrammer.lib.vfs.VFile;


/**
 *    �t�@�C���̃R�s�[���� 
 *   @author Shunji Yamaura 
 *  
 */
public interface CopyFileManipulation extends Manipulation {
	/**
	 *    ���얼 
	 *  
	 */
	public static final String NAME = "copy file";
	/**
	 *    �R�s�[����Z�b�g����B 
	 *    
	 *   @param dest 
	 *  
	 */
	public abstract void setDest(VFile dest);
	/**
	 *    �㏑�����j���Z�b�g����B 
	 *   prepare�O�Ɏ��s���邱�ƁB 
	 *    
	 *   @param policy 
	 *  
	 */
	public abstract void setOverwritePolicy(OverwritePolicy policy);
}
