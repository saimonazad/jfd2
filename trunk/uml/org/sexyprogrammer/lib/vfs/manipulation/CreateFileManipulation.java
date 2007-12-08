package org.sexyprogrammer.lib.vfs.manipulation;

import org.sexyprogrammer.lib.vfs.Manipulation;
import org.sexyprogrammer.lib.vfs.FileType;


/**
 *    ファイル生成操作クラス 
 *   @author Shunji Yamaura 
 *  
 */
public interface CreateFileManipulation extends Manipulation {
	/**
	 *    操作名 
	 *  
	 */
	public static final String NAME = "create file";
	public abstract void setType(FileType fileType);
}
