package org.sexyprogrammer.lib.vfs.manipulation;

import org.sexyprogrammer.lib.vfs.Manipulation;
import org.sexyprogrammer.lib.vfs.VFile;


/**
 *    リンク生成操作クラス 
 *   @author Shunji Yamaura 
 *  
 */
public interface CreateLinkManipulation extends Manipulation {
	/**
	 *    操作名 
	 *  
	 */
	public static final String NAME = "create link";
	/**
	 *    リンク先を指定する。 
	 *   @param file 
	 *  
	 */
	public abstract void setDest(VFile file);
}
