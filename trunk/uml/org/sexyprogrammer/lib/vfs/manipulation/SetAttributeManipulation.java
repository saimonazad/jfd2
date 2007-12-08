package org.sexyprogrammer.lib.vfs.manipulation;

import org.sexyprogrammer.lib.vfs.Manipulation;
import org.sexyprogrammer.lib.vfs.FileAttribute;


/**
 *    @author shunji 
 *   
 *   この生成されたコメントの挿入されるテンプレートを変更するため 
 *   ウィンドウ > 設定 > Java > コード生成 > コードとコメント 
 *  
 */
public interface SetAttributeManipulation extends Manipulation {
	/**
	 *    操作名 
	 *  
	 */
	public static final String NAME = "set attribute";
	/**
	 *    属性をセットする。 
	 *   @param attr 
	 *  
	 */
	public abstract void setAttribute(FileAttribute attr);
}
