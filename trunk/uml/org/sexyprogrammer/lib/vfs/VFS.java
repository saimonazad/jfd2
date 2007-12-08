package org.sexyprogrammer.lib.vfs;

import java.util.Map;


/**
 *    ファイルシステムを管理するシングルトンクラス。 
 *   このライブラリの処理の起点となる。 
 *  
 */
public class VFS {
	/**
	 *    標準インスタンス 
	 *  
	 */
	public static final String DEFAULT_INSTANCE = "default_file_system_manager";
	/**
	 *    ファイルシステム定義ファイルのURL 
	 *  
	 */
	private static final String FILE_SYSTEMS = "/file_systems";
	private  FileFactory[] interpreters;
	private  Map fileSystemsMap = new HashMap();
	private static  Map instanceMap = new HashMap();
	/**
	 *    コンストラクタ。 
	 *   プライベート。 
	 *   
	 *  
	 */
	private VFS() {
	}
	/**
	 *    パスからファイルを取得する。 
	 *   @param path 
	 *   @return 
	 *   @throws VFSException 
	 *  
	 */
	public VFile getFile(String path) {
		return null;
	}
	/**
	 *    ファイル名からファイルを取得する。 
	 *   @param fileName 
	 *   @return 
	 *   @throws VFSException 
	 *  
	 */
	public VFile getFile(FileName fileName) {
		return null;
	}
	/**
	 *    このクラスのインスタンスを取得する。 
	 *   @return 
	 *  
	 */
	public static VFS getInstance() {
		return null;
	}
	/**
	 *    このクラスのインスタンスを取得する。 
	 *   @param key 
	 *   @return 
	 *  
	 */
	public static VFS getInstance(Object key) {
		return null;
	}
	/**
	 *    アクティブなファイルシステムを追加する。 
	 *   @param fileSystem 
	 *  
	 */
	public void addFileSystem(FileName root, FileSystem fileSystem) {
	}
	/**
	 *    アクティブなファイルシステムを取得する。 
	 *   @param root 
	 *   @return 
	 *  
	 */
	public FileSystem getFileSystem(FileName root) {
		return null;
	}
	/**
	 *    アクティブなファイルシステムを削除する。 
	 *   @param root 
	 *  
	 */
	public void removeFileSystem(FileName root) {
	}
}
