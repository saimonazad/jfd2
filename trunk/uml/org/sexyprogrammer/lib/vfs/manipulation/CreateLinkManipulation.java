package org.sexyprogrammer.lib.vfs.manipulation;

import org.sexyprogrammer.lib.vfs.Manipulation;
import org.sexyprogrammer.lib.vfs.VFile;


/**
 *    �����N��������N���X 
 *   @author Shunji Yamaura 
 *  
 */
public interface CreateLinkManipulation extends Manipulation {
	/**
	 *    ���얼 
	 *  
	 */
	public static final String NAME = "create link";
	/**
	 *    �����N����w�肷��B 
	 *   @param file 
	 *  
	 */
	public abstract void setDest(VFile file);
}
