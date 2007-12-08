package org.sexyprogrammer.lib.vfs.manipulation;

import org.sexyprogrammer.lib.vfs.Manipulation;
import org.sexyprogrammer.lib.vfs.VFile;


/**
 *    �t�@�C���̎q�t�@�C�����擾����N���X�B 
 *    
 *   @author Shunji Yamaura 
 *  
 */
public interface GetChildrenManipulation extends Manipulation {
	/**
	 *    ���얼 
	 *  
	 */
	public static final String NAME = "get children";
	/**
	 *    �q�t�@�C�����擾����B 
	 *   @return 
	 *  
	 */
	public abstract VFile[] getChildren();
}
