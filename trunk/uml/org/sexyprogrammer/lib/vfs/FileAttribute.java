package org.sexyprogrammer.lib.vfs;

import java.util.Date;


/**
 *    ファイルの属性インターフェイス 
 *   @author Shunji Yamaura 
 *  
 */
public interface FileAttribute {
	public static final String LENGTH = "length";
	public static final String FILE_TYPE = "fileType";
	public static final String DATE = "date";
	public static final String EXISTS = "exists";
	public static final Object[] ATTRIBUTES = {LENGTH,FILE_TYPE,DATE,EXISTS,};
	/**
	 *    ファイル種類を取得する。 
	 *   @return 
	 *  
	 */
	public FileType getFileType();
	/**
	 *    ファイル長を取得する。 
	 *   @return	ファイル長 
	 *  
	 */
	public long getLength();
	/**
	 *    タイムスタンプを取得する。 
	 *   @return	タイムスタンプ 
	 *  
	 */
	public Date getDate();
	/**
	 *    ファイルが存在するか判定する。 
	 *   @return	ファイルが存在するならtrueを返す。	 
	 *  
	 */
	public boolean isExists();
	/**
	 *    属性名の配列を取得する。 
	 *   @return	属性名の配列 
	 *  
	 */
	public Object[] getAttributeKeys();
	/**
	 *    ファイルの属性を取得する。 
	 *   @param key	属性キー 
	 *   @return	属性値 
	 *  
	 */
	public Object getAttribute(Object key);
}
