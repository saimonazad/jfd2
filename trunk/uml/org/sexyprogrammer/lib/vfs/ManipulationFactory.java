package org.sexyprogrammer.lib.vfs;

import org.sexyprogrammer.lib.vfs.manipulation.GetAttributesManipulation;
import org.sexyprogrammer.lib.vfs.manipulation.SetAttributeManipulation;
import org.sexyprogrammer.lib.vfs.manipulation.GetPermissionManipulation;
import org.sexyprogrammer.lib.vfs.manipulation.SetPermissionManipulation;
import org.sexyprogrammer.lib.vfs.manipulation.GetInputStreamManipulation;
import org.sexyprogrammer.lib.vfs.manipulation.GetOutputStreamManipulation;
import org.sexyprogrammer.lib.vfs.manipulation.GetChildrenManipulation;
import org.sexyprogrammer.lib.vfs.manipulation.CreateFileManipulation;
import org.sexyprogrammer.lib.vfs.manipulation.CreateLinkManipulation;
import org.sexyprogrammer.lib.vfs.manipulation.DeleteManipulation;
import org.sexyprogrammer.lib.vfs.manipulation.MoveManipulation;
import org.sexyprogrammer.lib.vfs.manipulation.CopyFileManipulation;
import org.sexyprogrammer.lib.vfs.manipulation.SetTimestampManipulation;


/**
 *    ファイル操作クラスのファクトリークラス。 
 *    
 *   @author shunji 
 *   
 *   To change the template for this generated type comment go to 
 *   Window - Preferences - Java - Code Generation - Code and Comments 
 *  
 */
public interface ManipulationFactory {
	/**
	 *    ファイル属性初期化取得クラスを取得する。 
	 *   @param file 
	 *   @return 
	 *   @throws ManipulationNotAvailableException 
	 *  
	 */
	public GetAttributesManipulation getGetAttributeManipulation(VFile file);
	/**
	 *    ファイル属性セットクラスを取得する。 
	 *   @param file 
	 *   @return 
	 *   @throws ManipulationNotAvailableException 
	 *  
	 */
	public SetAttributeManipulation getSetAttributeManipulation(VFile file);
	/**
	 *    ファイルパーミッションクラス取得クラスを返す。 
	 *   @return 
	 *  
	 */
	public GetPermissionManipulation getGetPermissionManipulation(VFile file);
	/**
	 *    ファイルパーミッションセット操作クラスを返す。 
	 *   @param file 
	 *   @return 
	 *   @throws ManipulationNotAvailableException 
	 *  
	 */
	public SetPermissionManipulation getSetPermissionManipulation(VFile file);
	/**
	 *    入力ストリーム取得クラスを返す。 
	 *   @return 
	 *  
	 */
	public GetInputStreamManipulation getGetInputStreamManipulation(VFile file);
	/**
	 *    出力ストリーム取得クラスを返す。 
	 *   @return 
	 *  
	 */
	public GetOutputStreamManipulation getGetOutputStreamManipulation(VFile file);
	/**
	 *    子ファイル取得クラスを返す。 
	 *   @return 
	 *  
	 */
	public GetChildrenManipulation getGetChildrenManipulation(VFile file);
	/**
	 *    ファイル生成クラスを返す。 
	 *   @return 
	 *  
	 */
	public CreateFileManipulation getCreateFileManipulation(VFile file);
	/**
	 *    リンク生成クラスを返す。 
	 *   @return 
	 *  
	 */
	public CreateLinkManipulation getCreateLinkManipulation(VFile file);
	/**
	 *    ファイル削除クラスを返す。 
	 *   @return 
	 *  
	 */
	public DeleteManipulation getDeleteManipulation(VFile file);
	/**
	 *    ファイル名称変更クラスを返す。 
	 *   @return 
	 *  
	 */
	public MoveManipulation getMoveManipulation(VFile file);
	/**
	 *    ファイルコピー操作クラスを返す。 
	 *   @return 
	 *  
	 */
	public CopyFileManipulation getCopyFileManipulation(VFile file);
	/**
	 *    最終更新日設定操作クラスを返す。 
	 *   @return 
	 *  
	 */
	public SetTimestampManipulation getSetTimestampManipulation(VFile file);
}
