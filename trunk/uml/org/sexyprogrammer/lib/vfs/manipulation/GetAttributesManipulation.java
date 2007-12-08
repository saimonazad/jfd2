package org.sexyprogrammer.lib.vfs.manipulation;

import org.sexyprogrammer.lib.vfs.Manipulation;
import org.sexyprogrammer.lib.vfs.FileAttribute;


/**
 *    ファイルの各種属性を初期化、取得する操作インターフェイス。 
 *   @author Shunji Yamaura 
 *  
 */
public interface GetAttributesManipulation extends Manipulation {
	/**
	 *    操作名 
	 *  
	 */
	public static final String NAME = "init attribute";
	/**
	 *    ファイル属性を取得する。 
	 *    
	 *   @return 
	 *  
	 */
	public abstract FileAttribute getAttribute();
}
