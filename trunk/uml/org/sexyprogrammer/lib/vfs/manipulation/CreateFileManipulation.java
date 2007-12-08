package org.sexyprogrammer.lib.vfs.manipulation;

import org.sexyprogrammer.lib.vfs.Manipulation;
import org.sexyprogrammer.lib.vfs.FileType;


/**
 *    �t�@�C����������N���X 
 *   @author Shunji Yamaura 
 *  
 */
public interface CreateFileManipulation extends Manipulation {
	/**
	 *    ���얼 
	 *  
	 */
	public static final String NAME = "create file";
	public abstract void setType(FileType fileType);
}
