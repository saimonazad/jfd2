package org.sexyprogrammer.lib.vfs;

import java.util.Date;


/**
 *    �t�@�C���̑����C���^�[�t�F�C�X 
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
	 *    �t�@�C����ނ��擾����B 
	 *   @return 
	 *  
	 */
	public FileType getFileType();
	/**
	 *    �t�@�C�������擾����B 
	 *   @return	�t�@�C���� 
	 *  
	 */
	public long getLength();
	/**
	 *    �^�C���X�^���v���擾����B 
	 *   @return	�^�C���X�^���v 
	 *  
	 */
	public Date getDate();
	/**
	 *    �t�@�C�������݂��邩���肷��B 
	 *   @return	�t�@�C�������݂���Ȃ�true��Ԃ��B	 
	 *  
	 */
	public boolean isExists();
	/**
	 *    �������̔z����擾����B 
	 *   @return	�������̔z�� 
	 *  
	 */
	public Object[] getAttributeKeys();
	/**
	 *    �t�@�C���̑������擾����B 
	 *   @param key	�����L�[ 
	 *   @return	�����l 
	 *  
	 */
	public Object getAttribute(Object key);
}
