package org.sexyprogrammer.lib.vfs;

import java.util.Map;


/**
 *    ファイル更新管理クラス。 
 *   シングルトンになっている。 
 *    
 *   @author shunji 
 *  
 */
public class UpdateManager {
	private static final int FILE_CHANGED = 0;
	private static final int FILE_CREATED = 1;
	private static final int FILE_DELETED = 2;
	 Map fileListenerMap = new HashMap();
	 Map childListenerMap = new HashMap();
	private static  UpdateManager instance = new UpdateManager();
	/**
	 *    コンストラクタ 
	 *   
	 *  
	 */
	private UpdateManager() {
	}
	/**
	 *    シングルトンインスタンスを取得する。 
	 *   @return 
	 *  
	 */
	public static UpdateManager getInstance() {
		return null;
	}
	/**
	 *    ファイルリスナを追加する。 
	 *    
	 *   @param file 対象ファイル 
	 *   @param listener	追加するリスナ 
	 *  
	 */
	public void addFileListener(VFile file, FileListener listener) {
	}
	/**
	 *    ファイルリスナを削除する 
	 *    
	 *   @param file 対象ファイル 
	 *   @param listener	削除するファイルリスナ 
	 *  
	 */
	public void removeFileListener(VFile file, FileListener listener) {
	}
	/**
	 *    子ファイルのファイルリスナを追加する。 
	 *    
	 *   @param file 対象ファイル 
	 *   @param listener	追加するリスナ 
	 *  
	 */
	public void addChildFileListener(VFile file, FileListener listener) {
	}
	/**
	 *    子ファイルのファイルリスナを外す。 
	 *    
	 *   @param file 対象ファイル 
	 *   @param listener	削除するファイルリスナ 
	 *  
	 */
	public void removeChildFileListener(VFile file, FileListener listener) {
	}
	/**
	 *    ファイルが更新された際に呼び出される 
	 *   @param file 対象ファイル 
	 *  
	 */
	public void fileChanged(VFile file) {
	}
	/**
	 *    ファイルが削除された際に呼び出される。 
	 *   @param file 対象ファイル 
	 *  
	 */
	public void fileDeleted(VFile file) {
	}
	/**
	 *    ファイルが生成された際に呼び出される。 
	 *   @param file 対象ファイル 
	 *  
	 */
	public void fileCreated(VFile file) {
	}
	/**
	 *    リスナを呼ぶ 
	 *   @param file 
	 *   @param updateType 
	 *  
	 */
	private void callListeners(VFile file, int updateType) {
	}
}
