package org.sexyprogrammer.lib.vfs.manipulation;

import org.sexyprogrammer.lib.vfs.Manipulation;
import org.sexyprogrammer.lib.vfs.VFile;


/**
 *    ファイルの移動操作クラス 
 *   @author Shunji Yamaura 
 *  
 */
public interface MoveManipulation extends Manipulation {
	/**
	 *    操作名 
	 *  
	 */
	public static final String NAME = "move file";
	/**
	 *    変更先ファイル名称をセットする。 
	 *   @param dest 
	 *  
	 */
	public abstract void setDest(VFile dest);
	/**
	 *    上書き方針をセットする。 
	 *    
	 *   @param policy 
	 *  
	 */
	public abstract void setOverwritePolicy(OverwritePolicy policy);
}
