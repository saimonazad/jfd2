package org.sexyprogrammer.lib.vfs.manipulation;

import org.sexyprogrammer.lib.vfs.Manipulation;
import org.sexyprogrammer.lib.vfs.VFile;


/**
 *    �t�@�C���̈ړ�����N���X 
 *   @author Shunji Yamaura 
 *  
 */
public interface MoveManipulation extends Manipulation {
	/**
	 *    ���얼 
	 *  
	 */
	public static final String NAME = "move file";
	/**
	 *    �ύX��t�@�C�����̂��Z�b�g����B 
	 *   @param dest 
	 *  
	 */
	public abstract void setDest(VFile dest);
	/**
	 *    �㏑�����j���Z�b�g����B 
	 *    
	 *   @param policy 
	 *  
	 */
	public abstract void setOverwritePolicy(OverwritePolicy policy);
}
