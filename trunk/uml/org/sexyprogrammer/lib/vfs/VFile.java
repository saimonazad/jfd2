package org.sexyprogrammer.lib.vfs;

import java.net.URI;
import org.sexyprogrammer.lib.vfs.permission.FileAccess;
import org.sexyprogrammer.lib.vfs.permission.PermissionType;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import org.sexyprogrammer.lib.vfs.manipulation.OverwritePolicy;


/**
 *   ファイルを表す抽象クラス
 *  
 */
public abstract class VFile {
	/**
	 *   ハッシュ値
	 *  
	 */
	protected int hashCode = -1;
	protected  FileName fileName;
	protected  FileSystem fileSystem;
	protected  FileAttribute attributes;
	protected  Permission permission;
	/**
	 *   コンストラクタ
	 *   @param fileSystem	ファイルシステム
	 *   @param fileName		ファイル名
	 *  
	 */
	public VFile(FileSystem fileSystem, FileName fileName) {
	}
	/**
	 *   コンストラクタ
	 *   @param fileSystem	ファイルシステム
	 *   @param fileName		ファイル名
	 *   @param attributes	ファイル属性
	 *  
	 */
	public VFile(FileSystem fileSystem, FileName fileName, FileAttribute attributes) {
	}
	/**
	 *   ファイルの操作クラスを提供クラスを取得する。
	 *   
	 *   @return	ファイル操作クラス
	 *  
	 */
	public abstract ManipulationFactory getManipulationFactory();
	/**
	 *   ファイル名の文字列を取得する。
	 *   @return	ファイル名文字列
	 *  
	 */
	public String getName() {
		return null;
	}
	/**
	 *   ファイル名を取得する。
	 *   
	 *   @return	ファイル名
	 *  
	 */
	public FileName getFileName() {
		return null;
	}
	/**
	 *   ファイルシステムを取得する。
	 *   
	 *   @return	ファイルシステム
	 *  
	 */
	public FileSystem getFileSystem() {
		return null;
	}
	/**
	 *   ファイルリスナを追加する。
	 *   
	 *   @param listener	追加するリスナ
	 *  
	 */
	public void addFileListener(FileListener listener) {
	}
	/**
	 *   ファイルリスナを削除する
	 *   
	 *   @param listener	削除するファイルリスナ
	 *  
	 */
	public void removeFileListener(FileListener listener) {
	}
	/**
	 *   子ファイルのファイルリスナを追加する。
	 *   
	 *   @param listener	追加するリスナ
	 *  
	 */
	public void addChildFileListener(FileListener listener) {
	}
	/**
	 *   サブファイルのファイルリスナを外す。
	 *   
	 *   @param listener	削除するファイルリスナ
	 *  
	 */
	public void removeChildFileListener(FileListener listener) {
	}
	/**
	 *   絶対パスの文字列を取得する。
	 *   
	 *   @return	絶対パス
	 *  
	 */
	public String getAbsolutePath() {
		return null;
	}
	/**
	 *   ユーザー情報抜きの絶対パスを取得する。
	 *   
	 *   @return	ユーザー情報を覗いた絶対パス
	 *  
	 */
	public String getSecurePath() {
		return null;
	}
	/**
	 *   URIを取得する。
	 *   
	 *   @return	このファイルのURI
	 *   @throws URISyntaxException このファイルのパスがURIに一致しない場合投げられる。
	 *  
	 */
	public URI getURI() {
		return null;
	}
	/**
	 *   キャッシュされたファイル属性をクリアする。
	 *  
	 *  
	 */
	public void clearFileAttribute() {
	}
	/**
	 *   パーミッションを取得する。
	 *   
	 *   @return	パーミッション
	 *   @throws VFSException
	 *  
	 */
	public Permission getPermission() {
		return null;
	}
	/**
	 *   パーミッションを取得する。
	 *   
	 *   @param parentManipulation 親操作
	 *   @return	パーミッション
	 *   @throws VFSException
	 *  
	 */
	public Permission getPermission(Manipulation parentManipulation) {
		return null;
	}
	/**
	 *   パーミッションを設定する
	 *   @param access
	 *   @param type
	 *   @param value
	 *   @throws VFSException
	 *  
	 */
	public void setPermission(FileAccess access, PermissionType type, boolean value) {
	}
	/**
	 *   パーミッションを設定する
	 *   @param access
	 *   @param type
	 *   @param value
	 *   @param parentManipulation
	 *   @throws VFSException
	 *  
	 */
	public void setPermission(FileAccess access, PermissionType type, boolean value, Manipulation parentManipulation) {
	}
	/**
	 *   パーミッションを設定する。
	 *   @param permission
	 *   @throws VFSException
	 *  
	 */
	public void setPermission(Permission permission) {
	}
	/**
	 *   パーミッションを設定する。
	 *   @param permission
	 *   @param parentManipulation
	 *   @throws VFSException
	 *  
	 */
	public void setPermission(Permission permission, Manipulation parentManipulation) {
	}
	/**
	 *   キャッシュされたパーミッションをクリアする。
	 *  
	 *  
	 */
	public void clearPermission() {
	}
	/**
	 *   パーミッションのキャッシュを取得する。
	 *   @return	パーミッションのキャッシュ
	 *  
	 */
	public Permission getPermissionCache() {
		return null;
	}
	/**
	 *   パーミッションのキャッシュをセットする。
	 *   @param permission	パーミッション
	 *  
	 */
	public void setPermissionCache(Permission permission) {
	}
	/**
	 *   入力ストリームを取得する。
	 *   
	 *   @return	ファイルの入力ストリーム
	 *   @throws VFSException
	 *  
	 */
	public InputStream getInputStream() {
		return null;
	}
	/**
	 *   入力ストリームを取得する。
	 *   
	 *   @param parentManipulation 親操作
	 *   @return	ファイルの入力ストリーム
	 *   @throws VFSException
	 *  
	 */
	public InputStream getInputStream(Manipulation parentManipulation) {
		return null;
	}
	/**
	 *   出力ストリームを取得する。
	 *   
	 *   @return	ファイルの出力ストリーム
	 *   @throws VFSException
	 *  
	 */
	public OutputStream getOutputStream() {
		return null;
	}
	/**
	 *   出力ストリームを取得する。
	 *   
	 *   @param parentManipulation 親操作
	 *   @return ファイルの出力ストリーム
	 *   @throws VFSException
	 *  
	 */
	public OutputStream getOutputStream(Manipulation parentManipulation) {
		return null;
	}
	/**
	 *   ファイル属性を取得する。
	 *   
	 *   @return	ファイルの属性
	 *   @throws VFSException
	 *  
	 */
	public FileAttribute getAttribute() {
		return null;
	}
	/**
	 *   ファイル属性を取得する。
	 *   @param parentManipulation 親操作
	 *   @return	ファイル属性
	 *   @throws VFSException
	 *  
	 */
	public FileAttribute getAttribute(Manipulation parentManipulation) {
		return null;
	}
	/**
	 *   ファイル属性をキャッシュする。
	 *   @param attr	ファイル属性
	 *  
	 */
	public void setAttributeCache(FileAttribute attr) {
	}
	/**
	 *   ファイル属性のキャッシュを取得する。
	 *   @return	ファイル属性キャッシュ
	 *  
	 */
	public FileAttribute getAttributeCache() {
		return null;
	}
	/**
	 *   ファイル長を取得する。
	 *   @return
	 *  
	 */
	public long getLength() {
		return 0;
	}
	/**
	 *   ファイル長を取得する。
	 *   @return
	 *  
	 */
	public long getLength(Manipulation parentManipulation) {
		return 0;
	}
	/**
	 *   タイムスタンプを取得する。
	 *   
	 *   @return
	 *   @throws VFSException
	 *  
	 */
	public Date getTimestamp() {
		return null;
	}
	/**
	 *   タイムスタンプを取得する。
	 *   
	 *   @param parentManipulation 親操作
	 *   @return	タイムスタンプ
	 *   @throws VFSException
	 *  
	 */
	public Date getTimestamp(Manipulation parentManipulation) {
		return null;
	}
	/**
	 *   タイムスタンプを設定する。
	 *   @param date
	 *   @throws VFSException
	 *  
	 */
	public void setTimestamp(Date date) {
	}
	/**
	 *   タイムスタンプを設定する。
	 *   
	 *   @param date
	 *   @param parentManipulation
	 *   @throws VFSException
	 *  
	 */
	public void setTimestamp(Date date, Manipulation parentManipulation) {
	}
	/**
	 *   ファイルが存在するならtrueを返す。
	 *   @return
	 *   @throws VFSException
	 *  
	 */
	public boolean isExists() {
		return false;
	}
	/**
	 *   ファイルが存在するならtrueを返す。
	 *   @param parentManipulation 親操作
	 *   @return
	 *   @throws VFSException
	 *  
	 */
	public boolean isExists(Manipulation parentManipulation) {
		return false;
	}
	/**
	 *   ファイルの種類を返す。
	 *   @return
	 *   @throws VFSException
	 *  
	 */
	public FileType getType() {
		return null;
	}
	/**
	 *   ファイルの種類を返す。
	 *   @param parentManipulation 親操作
	 *   @return
	 *   @throws VFSException
	 *  
	 */
	public FileType getType(Manipulation parentManipulation) {
		return null;
	}
	/**
	 *   ファイルがファイルならtrueを返す。
	 *   @return
	 *   @throws VFSException
	 *  
	 */
	public boolean isFile() {
		return false;
	}
	/**
	 *   ファイルがファイルならtrueを返す。
	 *   @param parentManipulation 親操作
	 *   @return
	 *   @throws VFSException
	 *  
	 */
	public boolean isFile(Manipulation parentManipulation) {
		return false;
	}
	/**
	 *   ファイルがディレクトリならtrueを返す。
	 *   @return
	 *   @throws VFSException
	 *  
	 */
	public boolean isDirectory() {
		return false;
	}
	/**
	 *   ファイルがディレクトリならtrueを返す。
	 *   @return
	 *   @throws VFSException
	 *  
	 */
	public boolean isDirectory(Manipulation parentManipulation) {
		return false;
	}
	/**
	 *   ファイルがリンクならtrueを返す。
	 *   @return
	 *   @throws VFSException
	 *  
	 */
	public boolean isLink() {
		return false;
	}
	/**
	 *   ファイルがリンクならtrueを返す。
	 *   @return
	 *   @throws VFSException
	 *  
	 */
	public boolean isLink(Manipulation parentManipulation) {
		return false;
	}
	/**
	 *   親ファイルを取得する。
	 *   @return	親ファイル
	 *   @throws FileNameException このファイルがルートなら投げられる。
	 *   @throws VFSException
	 *  
	 */
	public VFile getParent() {
		return null;
	}
	/**
	 *   このファイルがファイルシステムのルートなら、trueを返す。
	 *   @return
	 *  
	 */
	public boolean isRoot() {
		return false;
	}
	/**
	 *   子ファイルを取得する。
	 *   @return	子ファイル
	 *   @throws VFSException
	 *  
	 */
	public VFile[] getChildren() {
		return null;
	}
	/**
	 *   子ファイルを取得する。
	 *   @param parentManipulation 親操作
	 *   @return	子ファイル
	 *   @throws VFSException
	 *  
	 */
	public VFile[] getChildren(Manipulation parentManipulation) {
		return null;
	}
	/**
	 *   ファイルを生成する。
	 *   @throws VFSException
	 *  
	 */
	public void createFile() {
	}
	/**
	 *   ファイルを生成する。
	 *   @param parentManipulation 親操作
	 *   @throws VFSException
	 *  
	 */
	public void createFile(Manipulation parentManipulation) {
	}
	/**
	 *   ディレクトリを生成する。
	 *   @throws VFSException
	 *  
	 */
	public void createDirectory() {
	}
	/**
	 *   ディレクトリを生成する。
	 *   @param parentManipulation 親操作
	 *   @throws VFSException
	 *  
	 */
	public void createDirectory(Manipulation parentManipulation) {
	}
	/**
	 *   リンクを生成する。
	 *   @param dest	リンク先
	 *   @throws VFSException
	 *  
	 */
	public void createLink(VFile dest) {
	}
	/**
	 *   リンクを生成する。
	 *   @param dest	リンク先
	 *   @param parentManipulation 親操作
	 *   @throws VFSException
	 *  
	 */
	public void createLink(VFile dest, Manipulation parentManipulation) {
	}
	/**
	 *   ファイルを削除する。
	 *   @throws VFSException
	 *  
	 */
	public void delete() {
	}
	/**
	 *   ファイルを削除する。
	 *   @param parentManipulation 親操作
	 *   @throws VFSException
	 *  
	 */
	public void delete(Manipulation parentManipulation) {
	}
	/**
	 *   ファイルを移動する。
	 *   @param dest	移動先ファイル
	 *   @throws VFSException
	 *  
	 */
	public void moveTo(VFile dest) {
	}
	/**
	 *   ファイルを移動する。
	 *   @param dest	移動先ファイル
	 *   @param parentManipulation 親操作
	 *   @throws VFSException
	 *  
	 */
	public void moveTo(VFile dest, Manipulation parentManipulation) {
	}
	/**
	 *   ファイルを移動する。
	 *   @param dest	移動先
	 *   @param policy	上書きポリシー
	 *   @param parentManipulation 親操作
	 *   @throws VFSException
	 *  
	 */
	public void moveTo(VFile dest, OverwritePolicy policy, Manipulation parentManipulation) {
	}
	/**
	 *   ファイルをコピーする。
	 *   @param dest	コピー先
	 *   @throws VFSException
	 *  
	 */
	public void copyTo(VFile dest) {
	}
	/**
	 *   ファイルをコピーする。
	 *   @param dest	コピー先
	 *   @param parentManipulation 親操作
	 *   @throws VFSException
	 *  
	 */
	public void copyTo(VFile dest, Manipulation parentManipulation) {
	}
	/**
	 *   ファイルをコピーする。
	 *   @param dest	コピー先
	 *   @param	policy 上書きポリシー 
	 *   @param parentManipulation 親操作
	 *   @throws VFSException
	 *  
	 */
	public void copyTo(VFile dest, OverwritePolicy policy, Manipulation parentManipulation) {
	}
	/**
	 *   指定された名称の子ファイルオブジェクトを生成する。
	 *   @param name	子ファイルのファイル名称
	 *   @return	引数で指定された子ファイル
	 *   @throws VFSException
	 *  
	 */
	public VFile getChild(String name) {
		return null;
	}
	/**
	 *   相対パスで指定されるファイルを取得する。
	 *   @param relation	相対パス
	 *   @return	相対ファイル
	 *   @throws VFSException
	 *  
	 */
	public VFile getRelativeFile(String relation) {
		return null;
	}
	/**
	 *   文字列に変換する。
	 *  
	 */
	public String toString() {
		return null;
	}
	/**
	 *   ハッシュ値を取得する。
	 *  
	 */
	public int hashCode() {
		return 0;
	}
	/**
	 *   ファイルの中身のMD5ハッシュ値の文字列を返す。
	 *   @param parentManipulation	親操作
	 *   @return	MD5ハッシュ文字列
	 *   @throws VFSException
	 *  
	 */
	public String getContentHashStr() {
		return null;
	}
	/**
	 *   ファイルの中身のMD5ハッシュ値の文字列を返す。
	 *   @param parentManipulation	親操作
	 *   @return	MD5ハッシュ文字列
	 *   @throws VFSException
	 *  
	 */
	public String getContentHashStr(Manipulation parentManipulation) {
		return null;
	}
	/**
	 *   ファイルの中身のMD5ハッシュ値を返す。
	 *   @return	MD5ハッシュ
	 *   @throws VFSException
	 *  
	 */
	public byte[] getContentHash() {
		return null;
	}
	/**
	 *   ファイルの中身のMD5ハッシュ値を返す。
	 *   @param parentManipulation	親操作
	 *   @return	MD5ハッシュ
	 *   @throws VFSException
	 *  
	 */
	public byte[] getContentHash(Manipulation parentManipulation) {
		return null;
	}
	/**
	 *   ファイルが等しいか判定する。
	 *   クラスが同じでパスが等しいかで判定している。
	 *  
	 */
	public boolean equals(Object o) {
		return false;
	}
}
