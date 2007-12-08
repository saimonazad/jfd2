package org.sexyprogrammer.lib.vfs.manipulation;

import org.sexyprogrammer.lib.vfs.Manipulation;
import org.sexyprogrammer.lib.vfs.VFile;


/**
 *    ファイルのコピー操作 
 *   @author Shunji Yamaura 
 *  
 */
public interface CopyFileManipulation extends Manipulation {
	/**
	 *    操作名 
	 *  
	 */
	public static final String NAME = "copy file";
	/**
	 *    コピー先をセットする。 
	 *    
	 *   @param dest 
	 *  
	 */
	public abstract void setDest(VFile dest);
	/**
	 *    上書き方針をセットする。 
	 *   prepare前に実行すること。 
	 *    
	 *   @param policy 
	 *  
	 */
	public abstract void setOverwritePolicy(OverwritePolicy policy);
}
