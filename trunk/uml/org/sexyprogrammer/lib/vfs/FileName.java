package org.sexyprogrammer.lib.vfs;

import java.net.URI;


/**
 *    �t�@�C������\�����ۃN���X�B 
 *   �s�σI�u�W�F�N�g�ƂȂ�B 
 *    
 *   @author shunji 
 *  
 */
public abstract class FileName {
	/**
	 *    ��؂蕶�� 
	 *  
	 */
	public static final String SEPARATOR = "/";
	/**
	 *    �e�p�X 
	 *  
	 */
	public static final String PARENT = "..";
	/**
	 *    �J�����g�p�X 
	 *  
	 */
	public static final String CURRENT = ".";
	/**
	 *    �X�L�[�} 
	 *  
	 */
	protected String scheme;
	/**
	 *    �p�X��\���t�@�C�����̔z�� 
	 *  
	 */
	protected String[] path;
	/**
	 *    �p�X�̕����� 
	 *  
	 */
	protected String pathString;
	/**
	 *    �z�X�g 
	 *  
	 */
	protected String host;
	/**
	 *    �|�[�g�ԍ� 
	 *  
	 */
	protected int port;
	/**
	 *    �N�G�� 
	 *  
	 */
	protected String query;
	/**
	 *    �t���O�����g 
	 *  
	 */
	protected String fragment;
	/**
	 *    �g���q 
	 *  
	 */
	private String extension;
	/**
	 *    �g���q�̏������\�� 
	 *  
	 */
	private String lowerExtension;
	/**
	 *    �t�@�C�����̏������\�L 
	 *  
	 */
	private String lowerName;
	/**
	 *    long�̔z��ɒ������������̃t�@�C�����B 
	 *   ���l�͂܂Ƃ܂�ň�̐����Ƃ��đ����A 
	 *   ������long�̍ŏ��l�𑫂��������ɒ����B 
	 *  
	 */
	private long[] longArrayName;
	/**
	 *    �n�b�V���l 
	 *  
	 */
	protected int hash = -1;
	protected  FileName baseFileName;
	protected  UserInfo userInfo;
	/**
	 *    �R���X�g���N�^ 
	 *   @param scheme	�X�L�[�} 
	 *   @param baseFileName	��ƂȂ�t�@�C�����i�A�[�J�C�u�t�@�C���Ȃ�) 
	 *   @param fileNames		�t�@�C�����̔z��ŕ\���ꂽ�p�X 
	 *   @param userInfo		���[�U�[��� 
	 *   @param host			�z�X�g�� 
	 *   @param port			�|�[�g�ԍ� 
	 *   @param query			�N�G�� 
	 *   @param fragment		�t���O�����g 
	 *  
	 */
	public FileName(String scheme, FileName baseFileName, String[] fileNames, UserInfo userInfo, String host, int port, String query, String fragment) {
	}
	/**
	 *    ��΃p�X�����߂�B 
	 *   @return	�p�X 
	 *  
	 */
	public abstract String getAbsolutePath();
	/**
	 *    �Z�L�����e�B����S�ȃp�X�i���[�U�[���̖����p�X�j��Ԃ��B 
	 *   @return	���[�U�[��񔲂��̃p�X 
	 *  
	 */
	public abstract String getSecurePath();
	/**
	 *    URI�����߂�B 
	 *   @return	URI 
	 *  
	 */
	public abstract URI getURI();
	/**
	 *    �t�@�C�����𐶐�����B 
	 *    
	 *   @param baseFileName 
	 *   @param fileNames 
	 *   @param userInfo 
	 *   @param host 
	 *   @param port 
	 *   @param query 
	 *   @param fragment 
	 *   @return	�w�肳�ꂽ�����Ɉ�v����t�@�C���� 
	 *  
	 */
	public abstract FileName createFileName(String scheme, FileName baseFileName, String[] path, UserInfo userInfo, String host, int port, String query, String fragment);
	/**
	 *    ���̃t�@�C���������Ƀ��[�U�[�����w�肵�āA�t�@�C�����𐶐�����B 
	 *    
	 *   @param userInfo	���[�U�[��� 
	 *   @return	�w�肳�ꂽ���[�U�[�������A���̃I�u�W�F�N�g�Ɠ����̃t�@�C���� 
	 *  
	 */
	public FileName createFileName(UserInfo userInfo) {
		return null;
	}
	/**
	 *    �q�t�@�C�����𐶐�����B 
	 *    
	 *   @param fileName	�q�t�@�C���̃t�@�C���������� 
	 *   @return	�q�t�@�C���� 
	 *  
	 */
	public FileName createChild(String fileName) {
		return null;
	}
	/**
	 *    �e�t�@�C�������擾����B 
	 *   @return	�e�t�@�C���� 
	 *   @throws VFSException 
	 *  
	 */
	public FileName getParent() {
		return null;
	}
	/**
	 *    ���[�g�����߂�B 
	 *   @return	����t�@�C���V�X�e���̃��[�g�t�@�C���� 
	 *  
	 */
	public FileName getRoot() {
		return null;
	}
	/**
	 *    �t�@�C��������������߂�B 
	 *   @return	�t�@�C���������� 
	 *  
	 */
	public String getName() {
		return null;
	}
	/**
	 *    �t�@�C����������̏������\�������߂�B 
	 *   @return	�t�@�C����������̏������\�� 
	 *  
	 */
	public String getLowerName() {
		return null;
	}
	/**
	 *    long�z��ɒ������������\�L�̃t�@�C������Ԃ��B 
	 *   �t�@�C�������̐����͉��߂��āA��������long�̍ŏ��l�����������ɒ����Ă���B 
	 *   ��Ƀ\�[�g���Ɏg�p����i�t�@�C�����̐��l���\�[�g����P�[�X��)�B 
	 *    
	 *   @return 
	 *  
	 */
	public long[] getLowerLongArrayName() {
		return null;
	}
	/**
	 *    �p�X���t�@�C�����̔z��ŕԂ��B 
	 *    
	 *   @return	�t�@�C�����̔z��`���̃p�X 
	 *  
	 */
	public String[] getPath() {
		return null;
	}
	/**
	 *    �p�X�������Ԃ��B 
	 *    
	 *   @return	�p�X�̕����� 
	 *  
	 */
	public String getPathString() {
		return null;
	}
	/**
	 *    ���̃t�@�C�����t�@�C���V�X�e���̃��[�g�v�f�����肷��B 
	 *    
	 *   @return	���̃t�@�C�����t�@�C���V�X�e���̃��[�g�v�f�Ȃ�true��Ԃ��B 
	 *  
	 */
	public boolean isRoot() {
		return false;
	}
	/**
	 *    �g���q��Ԃ��B 
	 *   �������g���q�������ꍇ�A�󕶎���Ԃ��B 
	 *    
	 *   @return	�g���q 
	 *  
	 */
	public String getExtension() {
		return null;
	}
	/**
	 *    �������ɒ������g���q��Ԃ��B 
	 *    
	 *   @return	�������ɒ������g���q 
	 *  
	 */
	public String getLowerExtension() {
		return null;
	}
	/**
	 *    ���[�U�[�����擾����B 
	 *   @return	���[�U�[��� 
	 *  
	 */
	public UserInfo getUserInfo() {
		return null;
	}
	/**
	 *    �X�L�[�����擾����B 
	 *    
	 *   @return	�X�L�[�� 
	 *  
	 */
	public String getScheme() {
		return null;
	}
	/**
	 *    �z�X�g���擾����B 
	 *    
	 *   @return	�z�X�g 
	 *  
	 */
	public String getHost() {
		return null;
	}
	/**
	 *    �N�G�����擾���� 
	 *   @return	�N�G�� 
	 *  
	 */
	public String getQuery() {
		return null;
	}
	/**
	 *    �t���O�����g���擾����B 
	 *    
	 *   @return	�t���O�����g 
	 *  
	 */
	public String getFragment() {
		return null;
	}
	/**
	 *    ��ɂȂ�t�@�C������Ԃ��B 
	 *    
	 *   @return	��t�@�C���� 
	 *  
	 */
	public FileName getBaseFileName() {
		return null;
	}
	/**
	 *    �|�[�g�ԍ����擾���� 
	 *    
	 *   @return	�|�[�g�ԍ� 
	 *  
	 */
	public int getPort() {
		return 0;
	}
	/**
	 *    ���̃t�@�C��������ɂ������΃p�X���擾����B 
	 *   �������t�@�C���V�X�e�����قȂ�ꍇ�A��΃p�X��Ԃ��B 
	 *    
	 *   @param otherFileName	���΃t�@�C�� 
	 *   @return	���΃p�X 
	 *  
	 */
	public String resolveRelation(FileName otherFileName) {
		return null;
	}
	/**
	 *   
	 *  	 * ��t�@�C���������ꂩ���肷��B 
	 *  	 *  
	 *  	 * @param otherFileName	��r�Ώ� 
	 *  	 * @return	����Ȃ�true��Ԃ��B 
	 *  	 
	 */
	private boolean isBaseFileSame(FileName otherFileName) {
		return false;
	}
	/**
	 *    null�̉\���̂�����String���r���A����Ȃ�true��Ԃ��B 
	 *    
	 *   @param s1	������1 
	 *   @param s2	������2 
	 *   @return		����Ȃ�true��Ԃ��B 
	 *  
	 */
	private boolean compareStrings(String s1, String s2) {
		return false;
	}
	/**
	 *    �p�X�̔z�񂩂�A�p�X�̕�����𐶐����ĕԂ��B 
	 *    
	 *   @param path	�p�X�̔z�� 
	 *   @param fromRoot	���[�g����̐�΃p�X�ł���Ȃ�true���w�肷��B 
	 *   @return	�p�X�̕����� 
	 *  
	 */
	private String paths2String(String[] path, boolean fromRoot) {
		return null;
	}
	/**
	 *    ���΃p�X�����߂��A���΃t�@�C����Ԃ��B 
	 *   @param relation	���΃p�X 
	 *   @return	���̃N���X����ɂ��Arelation�ŕ\����鑊�΃t�@�C�� 
	 *   @throws VFSException 
	 *  
	 */
	public FileName resolveFileName(String relation) {
		return null;
	}
	/**
	 *    String��long�̔z��ɕϊ����郁�\�b�h 
	 *   String���̐����͂܂Ƃ߂Ĉ�̐���-(long�̍ŏ��l)�ɂ���B 
	 *    
	 *  
	 */
	protected long[] string2longArray(String str) {
		return null;
	}
	/**
	 *    �n�b�V���l���擾����B 
	 *   @return �n�b�V���l 
	 *  
	 */
	public int hashCode() {
		return 0;
	}
	/**
	 *    �I�u�W�F�N�g�������������肷��B 
	 *    
	 *   @return ���������true��Ԃ��B 
	 *  
	 */
	public boolean equals(Object o) {
		return false;
	}
	/**
	 *   
	 *  	 * ��̔z�񂪓����������肷��B 
	 *  	 
	 */
	private boolean compareArrays(Object[] a1, Object[] a2) {
		return false;
	}
	/**
	 *   
	 *  	 * ��̃I�u�W�F�N�g�������������肷��B 
	 *  	 
	 */
	private boolean compare(Object o1, Object o2) {
		return false;
	}
}
