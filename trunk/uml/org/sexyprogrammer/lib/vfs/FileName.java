package org.sexyprogrammer.lib.vfs;

import java.net.URI;


/**
 *    ファイル名を表す抽象クラス。 
 *   不変オブジェクトとなる。 
 *    
 *   @author shunji 
 *  
 */
public abstract class FileName {
	/**
	 *    区切り文字 
	 *  
	 */
	public static final String SEPARATOR = "/";
	/**
	 *    親パス 
	 *  
	 */
	public static final String PARENT = "..";
	/**
	 *    カレントパス 
	 *  
	 */
	public static final String CURRENT = ".";
	/**
	 *    スキーマ 
	 *  
	 */
	protected String scheme;
	/**
	 *    パスを表すファイル名の配列 
	 *  
	 */
	protected String[] path;
	/**
	 *    パスの文字列 
	 *  
	 */
	protected String pathString;
	/**
	 *    ホスト 
	 *  
	 */
	protected String host;
	/**
	 *    ポート番号 
	 *  
	 */
	protected int port;
	/**
	 *    クエリ 
	 *  
	 */
	protected String query;
	/**
	 *    フラグメント 
	 *  
	 */
	protected String fragment;
	/**
	 *    拡張子 
	 *  
	 */
	private String extension;
	/**
	 *    拡張子の小文字表現 
	 *  
	 */
	private String lowerExtension;
	/**
	 *    ファイル名の小文字表記 
	 *  
	 */
	private String lowerName;
	/**
	 *    longの配列に直した小文字のファイル名。 
	 *   数値はまとまりで一つの数字として捉え、 
	 *   そこにlongの最小値を足した数字に直す。 
	 *  
	 */
	private long[] longArrayName;
	/**
	 *    ハッシュ値 
	 *  
	 */
	protected int hash = -1;
	protected  FileName baseFileName;
	protected  UserInfo userInfo;
	/**
	 *    コンストラクタ 
	 *   @param scheme	スキーマ 
	 *   @param baseFileName	基準となるファイル名（アーカイブファイルなど) 
	 *   @param fileNames		ファイル名の配列で表されたパス 
	 *   @param userInfo		ユーザー情報 
	 *   @param host			ホスト名 
	 *   @param port			ポート番号 
	 *   @param query			クエリ 
	 *   @param fragment		フラグメント 
	 *  
	 */
	public FileName(String scheme, FileName baseFileName, String[] fileNames, UserInfo userInfo, String host, int port, String query, String fragment) {
	}
	/**
	 *    絶対パスを求める。 
	 *   @return	パス 
	 *  
	 */
	public abstract String getAbsolutePath();
	/**
	 *    セキュリティ上安全なパス（ユーザー情報の無いパス）を返す。 
	 *   @return	ユーザー情報抜きのパス 
	 *  
	 */
	public abstract String getSecurePath();
	/**
	 *    URIを求める。 
	 *   @return	URI 
	 *  
	 */
	public abstract URI getURI();
	/**
	 *    ファイル名を生成する。 
	 *    
	 *   @param baseFileName 
	 *   @param fileNames 
	 *   @param userInfo 
	 *   @param host 
	 *   @param port 
	 *   @param query 
	 *   @param fragment 
	 *   @return	指定された条件に一致するファイル名 
	 *  
	 */
	public abstract FileName createFileName(String scheme, FileName baseFileName, String[] path, UserInfo userInfo, String host, int port, String query, String fragment);
	/**
	 *    このファイル名を元にユーザー情報を指定して、ファイル名を生成する。 
	 *    
	 *   @param userInfo	ユーザー情報 
	 *   @return	指定されたユーザー情報を持つ、このオブジェクトと同等のファイル名 
	 *  
	 */
	public FileName createFileName(UserInfo userInfo) {
		return null;
	}
	/**
	 *    子ファイル名を生成する。 
	 *    
	 *   @param fileName	子ファイルのファイル名文字列 
	 *   @return	子ファイル名 
	 *  
	 */
	public FileName createChild(String fileName) {
		return null;
	}
	/**
	 *    親ファイル名を取得する。 
	 *   @return	親ファイル名 
	 *   @throws VFSException 
	 *  
	 */
	public FileName getParent() {
		return null;
	}
	/**
	 *    ルートを求める。 
	 *   @return	同一ファイルシステムのルートファイル名 
	 *  
	 */
	public FileName getRoot() {
		return null;
	}
	/**
	 *    ファイル名文字列を求める。 
	 *   @return	ファイル名文字列 
	 *  
	 */
	public String getName() {
		return null;
	}
	/**
	 *    ファイル名文字列の小文字表現を求める。 
	 *   @return	ファイル名文字列の小文字表現 
	 *  
	 */
	public String getLowerName() {
		return null;
	}
	/**
	 *    long配列に直した小文字表記のファイル名を返す。 
	 *   ファイル名中の数字は解釈して、そこからlongの最小値を引いた数に直してある。 
	 *   主にソート時に使用する（ファイル名の数値もソートするケースで)。 
	 *    
	 *   @return 
	 *  
	 */
	public long[] getLowerLongArrayName() {
		return null;
	}
	/**
	 *    パスをファイル名の配列で返す。 
	 *    
	 *   @return	ファイル名の配列形式のパス 
	 *  
	 */
	public String[] getPath() {
		return null;
	}
	/**
	 *    パス文字列を返す。 
	 *    
	 *   @return	パスの文字列 
	 *  
	 */
	public String getPathString() {
		return null;
	}
	/**
	 *    このファイルがファイルシステムのルート要素か判定する。 
	 *    
	 *   @return	このファイルがファイルシステムのルート要素ならtrueを返す。 
	 *  
	 */
	public boolean isRoot() {
		return false;
	}
	/**
	 *    拡張子を返す。 
	 *   もしも拡張子が無い場合、空文字を返す。 
	 *    
	 *   @return	拡張子 
	 *  
	 */
	public String getExtension() {
		return null;
	}
	/**
	 *    小文字に直した拡張子を返す。 
	 *    
	 *   @return	小文字に直した拡張子 
	 *  
	 */
	public String getLowerExtension() {
		return null;
	}
	/**
	 *    ユーザー情報を取得する。 
	 *   @return	ユーザー情報 
	 *  
	 */
	public UserInfo getUserInfo() {
		return null;
	}
	/**
	 *    スキームを取得する。 
	 *    
	 *   @return	スキーム 
	 *  
	 */
	public String getScheme() {
		return null;
	}
	/**
	 *    ホストを取得する。 
	 *    
	 *   @return	ホスト 
	 *  
	 */
	public String getHost() {
		return null;
	}
	/**
	 *    クエリを取得する 
	 *   @return	クエリ 
	 *  
	 */
	public String getQuery() {
		return null;
	}
	/**
	 *    フラグメントを取得する。 
	 *    
	 *   @return	フラグメント 
	 *  
	 */
	public String getFragment() {
		return null;
	}
	/**
	 *    基準になるファイル名を返す。 
	 *    
	 *   @return	基準ファイル名 
	 *  
	 */
	public FileName getBaseFileName() {
		return null;
	}
	/**
	 *    ポート番号を取得する 
	 *    
	 *   @return	ポート番号 
	 *  
	 */
	public int getPort() {
		return 0;
	}
	/**
	 *    このファイル名を基準にした相対パスを取得する。 
	 *   もしもファイルシステムが異なる場合、絶対パスを返す。 
	 *    
	 *   @param otherFileName	相対ファイル 
	 *   @return	相対パス 
	 *  
	 */
	public String resolveRelation(FileName otherFileName) {
		return null;
	}
	/**
	 *   
	 *  	 * 基準ファイル名が同一か判定する。 
	 *  	 *  
	 *  	 * @param otherFileName	比較対象 
	 *  	 * @return	同一ならtrueを返す。 
	 *  	 
	 */
	private boolean isBaseFileSame(FileName otherFileName) {
		return false;
	}
	/**
	 *    nullの可能性のある二つのStringを比較し、同一ならtrueを返す。 
	 *    
	 *   @param s1	文字列1 
	 *   @param s2	文字列2 
	 *   @return		同一ならtrueを返す。 
	 *  
	 */
	private boolean compareStrings(String s1, String s2) {
		return false;
	}
	/**
	 *    パスの配列から、パスの文字列を生成して返す。 
	 *    
	 *   @param path	パスの配列 
	 *   @param fromRoot	ルートからの絶対パスであるならtrueを指定する。 
	 *   @return	パスの文字列 
	 *  
	 */
	private String paths2String(String[] path, boolean fromRoot) {
		return null;
	}
	/**
	 *    相対パスを解釈し、相対ファイルを返す。 
	 *   @param relation	相対パス 
	 *   @return	このクラスを基準にし、relationで表される相対ファイル 
	 *   @throws VFSException 
	 *  
	 */
	public FileName resolveFileName(String relation) {
		return null;
	}
	/**
	 *    Stringをlongの配列に変換するメソッド 
	 *   String内の数字はまとめて一つの数字-(longの最小値)にする。 
	 *    
	 *  
	 */
	protected long[] string2longArray(String str) {
		return null;
	}
	/**
	 *    ハッシュ値を取得する。 
	 *   @return ハッシュ値 
	 *  
	 */
	public int hashCode() {
		return 0;
	}
	/**
	 *    オブジェクトが等しいか判定する。 
	 *    
	 *   @return 等しければtrueを返す。 
	 *  
	 */
	public boolean equals(Object o) {
		return false;
	}
	/**
	 *   
	 *  	 * 二つの配列が等しいか判定する。 
	 *  	 
	 */
	private boolean compareArrays(Object[] a1, Object[] a2) {
		return false;
	}
	/**
	 *   
	 *  	 * 二つのオブジェクトが等しいか判定する。 
	 *  	 
	 */
	private boolean compare(Object o1, Object o2) {
		return false;
	}
}
