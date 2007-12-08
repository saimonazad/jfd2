package org.sexyprogrammer.lib.vfs;


/**
 *    ファイルシステムの抽象クラス。 
 *    
 *   @author shunji 
 *  
 */
public abstract class FileSystem {
	/**
	 *    ファイルシステムを開く 
	 *   @throws VFSException 
	 *  
	 */
	public abstract void open();
	/**
	 *    ファイルシステムを閉じる。 
	 *   @throws VFSException 
	 *  
	 */
	public abstract void close();
	/**
	 *    ファイルシステムが開かれているか判定する。 
	 *   @return	ファイルシステムが開かれているならtrueを返す。 
	 *  
	 */
	public abstract boolean isOpened();
	/**
	 *    ファイルシステムを生成する。 
	 *   @throws VFSException 
	 *  
	 */
	public abstract void createFileSystem();
	/**
	 *    指定されたファイル名のファイルインスタンスを取得する。 
	 *   @param fileName	ファイル 
	 *   @return 
	 *  
	 */
	public abstract VFile getFile(FileName fileName);
	/**
	 *    ファイルシステムの基準ファイルを取得する。 
	 *   @return	基準ファイル 
	 *  
	 */
	public abstract VFile getMountPoint();
	/**
	 *    ファイルシステムのルートを取得する。 
	 *   @return	ルート 
	 *  
	 */
	public abstract FileName getRootName();
	/**
	 *    このファイルシステムを生成したVFSを取得する。 
	 *   @return	生成したVFS 
	 *  
	 */
	public abstract VFS getVFS();
	/**
	 *    このファイルシステムがローカルファイルシステムならtrueを返す。 
	 *   @return 
	 *  
	 */
	public abstract boolean isLocal();
}
