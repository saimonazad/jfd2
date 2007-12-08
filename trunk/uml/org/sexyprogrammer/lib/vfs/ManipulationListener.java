package org.sexyprogrammer.lib.vfs;


/**
 *    ファイル操作のリスナーインターフェイス。 
 *   このインターフェイスのメソッドは、必ずしもイベントディスパッチスレッドから 
 *   呼ばれないことに注意すること。 
 *    
 *   @author shunji 
 *  
 */
public interface ManipulationListener {
	/**
	 *    全処理が開始した際に呼び出される。 
	 *   @param e 
	 *  
	 */
	public void started(ManipulationEvent e);
	/**
	 *    前処理が開始した際に呼び出される。 
	 *   @param e 
	 *  
	 */
	public void preparationStarted(ManipulationEvent e);
	/**
	 *    前処理が終了した際に呼び出される。 
	 *   @param e 
	 *  
	 */
	public void preparationFinished(ManipulationEvent e);
	/**
	 *    操作が開始した際に呼び出される。 
	 *   @param e 
	 *  
	 */
	public void manipulationStarted(ManipulationEvent e);
	/**
	 *    操作が終了した際に呼び出される。 
	 *   @param e 
	 *  
	 */
	public void manipulationFinished(ManipulationEvent e);
	/**
	 *    全処理が終了した際に呼び出される。 
	 *   @param e 
	 *  
	 */
	public void finished(ManipulationEvent e);
	/**
	 *    操作が中止された際に呼び出される。 
	 *   @param e 
	 *  
	 */
	public void manipulationStopped(ManipulationEvent e);
}
