package org.sexyprogrammer.lib.vfs;


/**
 *    ファイル種類を表すクラス。 
 *   インスタンス生成は出来ず、FILE、DIRECTORY、LINKのみ存在する。 
 *    
 *   @author shunji 
 *  
 */
public class FileType {
	/**
	 *    名称 
	 *  
	 */
	private String name;
	/**
	 *    子ファイルを持つかのフラグ 
	 *  
	 */
	boolean hasChildren;
	/**
	 *    内容をもつかのフラグ 
	 *  
	 */
	boolean hasContent;
	/**
	 *    属性を持つかのフラグ 
	 *  
	 */
	boolean hasAttributes;
	/**
	 *    ハッシュ値 
	 *  
	 */
	int hashCode = -1;
	public static final  FileType FILE = new FileType("file",false,true,true);
	public static  FileType DIRECTORY = new FileType("direcrory",true,false,true);
	public static  FileType LINK = new FileType("LINK",false,true,true);
	private FileType(String name, boolean hasChildren, boolean hasContent, boolean hasAttributes) {
	}
	/**
	 *    @return 
	 *  
	 */
	public boolean isHasAttributes() {
		return false;
	}
	/**
	 *    @return 
	 *  
	 */
	public boolean isHasChildren() {
		return false;
	}
	/**
	 *    @return 
	 *  
	 */
	public boolean isHasContent() {
		return false;
	}
	/**
	 *    @return 
	 *  
	 */
	public String getName() {
		return null;
	}
	/**
	 *    toString()の実装 
	 *  
	 */
	public String toString() {
		return null;
	}
	/**
	 *    ハッシュ値を求める。 
	 *  
	 */
	public int hashCode() {
		return 0;
	}
}
