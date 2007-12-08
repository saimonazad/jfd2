package org.sexyprogrammer.lib.vfs;


/**
 *    パス文字列からファイルを生成するクラス。 
 *   ファイルクラス一つにつき一つ存在する。 
 *    
 *   @author shunji 
 *  
 */
public abstract class FileFactory {
	/**
	 *    ファイルのパスを接続するパイプ文字 
	 *  
	 */
	public static final char PIPE = '|';
	protected  VFS fileSystemManager;
	/**
	 *    パスがこのクラスで解釈可能か判定する。 
	 *    
	 *   @param path	パス	 
	 *   @return	解釈可能ならtrueを返す。 
	 *  
	 */
	public abstract boolean isInterpretable(String path);
	/**
	 *    ファイル名がこのクラスで解釈できるか判定する。 
	 *   @param fileName	ファイル名 
	 *   @return	解釈できるならtrueを返す。 
	 *  
	 */
	public abstract boolean isBelongingFileName(FileName fileName);
	/**
	 *    パスからファイル名を生成する。 
	 *   @param path	パス 
	 *   @return	ファイル名 
	 *  
	 */
	public FileName interpretFileName(String path) {
		return null;
	}
	/**
	 *    ファイル名クラスから絶対パスを取得する。 
	 *   @param fileName	ファイル名 
	 *   @return	絶対パス文字列 
	 *  
	 */
	public static String interpretPath(FileName fileName) {
		return null;
	}
	/**
	 *    ファイル名クラスからユーザー情報抜きの絶対パスを取得する。 
	 *   @param fileName 
	 *   @return	ユーザー情報抜きの絶対パス 
	 *  
	 */
	public static String interpretSecurePath(FileName fileName) {
		return null;
	}
	/**
	 *    パスからファイル名を生成する。 
	 *   @param path	パス 
	 *   @param baseFileName 基準ファイル名 
	 *   @return	ファイル名 
	 *  
	 */
	public abstract FileName interpretFileName(String path, FileName baseFileName);
	/**
	 *    ファイル名からファイルシステムを取得する。 
	 *   @param fileName	ファイル名 
	 *   @return	ファイルシステム 
	 *  
	 */
	public abstract FileSystem interpretFileSystem(FileName fileName);
	/**
	 *    パスからファイルを生成する。 
	 *    
	 *   @param path	パス 
	 *   @return	ファイル 
	 *  
	 */
	public VFile interpretFile(String path) {
		return null;
	}
	/**
	 *    ファイル名からファイルを生成する。 
	 *   @param fileName	ファイル名 
	 *   @return	ファイル 
	 *  
	 */
	public VFile interpretFile(FileName fileName) {
		return null;
	}
	/**
	 *    ファイルシステム管理を行うVFSクラスを取得する。 
	 *   @return	VFSクラス 
	 *  
	 */
	public VFS getFileSystemManager() {
		return null;
	}
	/**
	 *    ファイルシステム管理を行うVFSクラスをセットする。 
	 *   @param manager	VFSクラス 
	 *  
	 */
	public void setFileSystemManager(VFS manager) {
	}
}
