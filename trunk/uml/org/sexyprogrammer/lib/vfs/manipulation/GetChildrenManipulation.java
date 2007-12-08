package org.sexyprogrammer.lib.vfs.manipulation;

import org.sexyprogrammer.lib.vfs.Manipulation;
import org.sexyprogrammer.lib.vfs.VFile;


/**
 *    ファイルの子ファイルを取得するクラス。 
 *    
 *   @author Shunji Yamaura 
 *  
 */
public interface GetChildrenManipulation extends Manipulation {
	/**
	 *    操作名 
	 *  
	 */
	public static final String NAME = "get children";
	/**
	 *    子ファイルを取得する。 
	 *   @return 
	 *  
	 */
	public abstract VFile[] getChildren();
}
