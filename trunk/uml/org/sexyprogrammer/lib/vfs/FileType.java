package org.sexyprogrammer.lib.vfs;


/**
 *    �t�@�C����ނ�\���N���X�B 
 *   �C���X�^���X�����͏o�����AFILE�ADIRECTORY�ALINK�̂ݑ��݂���B 
 *    
 *   @author shunji 
 *  
 */
public class FileType {
	/**
	 *    ���� 
	 *  
	 */
	private String name;
	/**
	 *    �q�t�@�C���������̃t���O 
	 *  
	 */
	boolean hasChildren;
	/**
	 *    ���e�������̃t���O 
	 *  
	 */
	boolean hasContent;
	/**
	 *    �����������̃t���O 
	 *  
	 */
	boolean hasAttributes;
	/**
	 *    �n�b�V���l 
	 *  
	 */
	int hashCode = -1;
	public static final  FileType FILE = new FileType("file",false,true,true);
	public static  FileType DIRECTORY = new FileType("direcrory",true,false,true);
	public static  FileType LINK = new FileType("LINK",false,true,true);
	private FileType(String name, boolean hasChildren, boolean hasContent, boolean hasAttributes) {
	}
	/**
	 *    @return 
	 *  
	 */
	public boolean isHasAttributes() {
		return false;
	}
	/**
	 *    @return 
	 *  
	 */
	public boolean isHasChildren() {
		return false;
	}
	/**
	 *    @return 
	 *  
	 */
	public boolean isHasContent() {
		return false;
	}
	/**
	 *    @return 
	 *  
	 */
	public String getName() {
		return null;
	}
	/**
	 *    toString()�̎��� 
	 *  
	 */
	public String toString() {
		return null;
	}
	/**
	 *    �n�b�V���l�����߂�B 
	 *  
	 */
	public int hashCode() {
		return 0;
	}
}
