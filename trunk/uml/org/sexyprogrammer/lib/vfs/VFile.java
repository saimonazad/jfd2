package org.sexyprogrammer.lib.vfs;

import java.net.URI;
import org.sexyprogrammer.lib.vfs.permission.FileAccess;
import org.sexyprogrammer.lib.vfs.permission.PermissionType;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import org.sexyprogrammer.lib.vfs.manipulation.OverwritePolicy;


/**
 *   �t�@�C����\�����ۃN���X
 *  
 */
public abstract class VFile {
	/**
	 *   �n�b�V���l
	 *  
	 */
	protected int hashCode = -1;
	protected  FileName fileName;
	protected  FileSystem fileSystem;
	protected  FileAttribute attributes;
	protected  Permission permission;
	/**
	 *   �R���X�g���N�^
	 *   @param fileSystem	�t�@�C���V�X�e��
	 *   @param fileName		�t�@�C����
	 *  
	 */
	public VFile(FileSystem fileSystem, FileName fileName) {
	}
	/**
	 *   �R���X�g���N�^
	 *   @param fileSystem	�t�@�C���V�X�e��
	 *   @param fileName		�t�@�C����
	 *   @param attributes	�t�@�C������
	 *  
	 */
	public VFile(FileSystem fileSystem, FileName fileName, FileAttribute attributes) {
	}
	/**
	 *   �t�@�C���̑���N���X��񋟃N���X���擾����B
	 *   
	 *   @return	�t�@�C������N���X
	 *  
	 */
	public abstract ManipulationFactory getManipulationFactory();
	/**
	 *   �t�@�C�����̕�������擾����B
	 *   @return	�t�@�C����������
	 *  
	 */
	public String getName() {
		return null;
	}
	/**
	 *   �t�@�C�������擾����B
	 *   
	 *   @return	�t�@�C����
	 *  
	 */
	public FileName getFileName() {
		return null;
	}
	/**
	 *   �t�@�C���V�X�e�����擾����B
	 *   
	 *   @return	�t�@�C���V�X�e��
	 *  
	 */
	public FileSystem getFileSystem() {
		return null;
	}
	/**
	 *   �t�@�C�����X�i��ǉ�����B
	 *   
	 *   @param listener	�ǉ����郊�X�i
	 *  
	 */
	public void addFileListener(FileListener listener) {
	}
	/**
	 *   �t�@�C�����X�i���폜����
	 *   
	 *   @param listener	�폜����t�@�C�����X�i
	 *  
	 */
	public void removeFileListener(FileListener listener) {
	}
	/**
	 *   �q�t�@�C���̃t�@�C�����X�i��ǉ�����B
	 *   
	 *   @param listener	�ǉ����郊�X�i
	 *  
	 */
	public void addChildFileListener(FileListener listener) {
	}
	/**
	 *   �T�u�t�@�C���̃t�@�C�����X�i���O���B
	 *   
	 *   @param listener	�폜����t�@�C�����X�i
	 *  
	 */
	public void removeChildFileListener(FileListener listener) {
	}
	/**
	 *   ��΃p�X�̕�������擾����B
	 *   
	 *   @return	��΃p�X
	 *  
	 */
	public String getAbsolutePath() {
		return null;
	}
	/**
	 *   ���[�U�[��񔲂��̐�΃p�X���擾����B
	 *   
	 *   @return	���[�U�[����`������΃p�X
	 *  
	 */
	public String getSecurePath() {
		return null;
	}
	/**
	 *   URI���擾����B
	 *   
	 *   @return	���̃t�@�C����URI
	 *   @throws URISyntaxException ���̃t�@�C���̃p�X��URI�Ɉ�v���Ȃ��ꍇ��������B
	 *  
	 */
	public URI getURI() {
		return null;
	}
	/**
	 *   �L���b�V�����ꂽ�t�@�C���������N���A����B
	 *  
	 *  
	 */
	public void clearFileAttribute() {
	}
	/**
	 *   �p�[�~�b�V�������擾����B
	 *   
	 *   @return	�p�[�~�b�V����
	 *   @throws VFSException
	 *  
	 */
	public Permission getPermission() {
		return null;
	}
	/**
	 *   �p�[�~�b�V�������擾����B
	 *   
	 *   @param parentManipulation �e����
	 *   @return	�p�[�~�b�V����
	 *   @throws VFSException
	 *  
	 */
	public Permission getPermission(Manipulation parentManipulation) {
		return null;
	}
	/**
	 *   �p�[�~�b�V������ݒ肷��
	 *   @param access
	 *   @param type
	 *   @param value
	 *   @throws VFSException
	 *  
	 */
	public void setPermission(FileAccess access, PermissionType type, boolean value) {
	}
	/**
	 *   �p�[�~�b�V������ݒ肷��
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
	 *   �p�[�~�b�V������ݒ肷��B
	 *   @param permission
	 *   @throws VFSException
	 *  
	 */
	public void setPermission(Permission permission) {
	}
	/**
	 *   �p�[�~�b�V������ݒ肷��B
	 *   @param permission
	 *   @param parentManipulation
	 *   @throws VFSException
	 *  
	 */
	public void setPermission(Permission permission, Manipulation parentManipulation) {
	}
	/**
	 *   �L���b�V�����ꂽ�p�[�~�b�V�������N���A����B
	 *  
	 *  
	 */
	public void clearPermission() {
	}
	/**
	 *   �p�[�~�b�V�����̃L���b�V�����擾����B
	 *   @return	�p�[�~�b�V�����̃L���b�V��
	 *  
	 */
	public Permission getPermissionCache() {
		return null;
	}
	/**
	 *   �p�[�~�b�V�����̃L���b�V�����Z�b�g����B
	 *   @param permission	�p�[�~�b�V����
	 *  
	 */
	public void setPermissionCache(Permission permission) {
	}
	/**
	 *   ���̓X�g���[�����擾����B
	 *   
	 *   @return	�t�@�C���̓��̓X�g���[��
	 *   @throws VFSException
	 *  
	 */
	public InputStream getInputStream() {
		return null;
	}
	/**
	 *   ���̓X�g���[�����擾����B
	 *   
	 *   @param parentManipulation �e����
	 *   @return	�t�@�C���̓��̓X�g���[��
	 *   @throws VFSException
	 *  
	 */
	public InputStream getInputStream(Manipulation parentManipulation) {
		return null;
	}
	/**
	 *   �o�̓X�g���[�����擾����B
	 *   
	 *   @return	�t�@�C���̏o�̓X�g���[��
	 *   @throws VFSException
	 *  
	 */
	public OutputStream getOutputStream() {
		return null;
	}
	/**
	 *   �o�̓X�g���[�����擾����B
	 *   
	 *   @param parentManipulation �e����
	 *   @return �t�@�C���̏o�̓X�g���[��
	 *   @throws VFSException
	 *  
	 */
	public OutputStream getOutputStream(Manipulation parentManipulation) {
		return null;
	}
	/**
	 *   �t�@�C���������擾����B
	 *   
	 *   @return	�t�@�C���̑���
	 *   @throws VFSException
	 *  
	 */
	public FileAttribute getAttribute() {
		return null;
	}
	/**
	 *   �t�@�C���������擾����B
	 *   @param parentManipulation �e����
	 *   @return	�t�@�C������
	 *   @throws VFSException
	 *  
	 */
	public FileAttribute getAttribute(Manipulation parentManipulation) {
		return null;
	}
	/**
	 *   �t�@�C���������L���b�V������B
	 *   @param attr	�t�@�C������
	 *  
	 */
	public void setAttributeCache(FileAttribute attr) {
	}
	/**
	 *   �t�@�C�������̃L���b�V�����擾����B
	 *   @return	�t�@�C�������L���b�V��
	 *  
	 */
	public FileAttribute getAttributeCache() {
		return null;
	}
	/**
	 *   �t�@�C�������擾����B
	 *   @return
	 *  
	 */
	public long getLength() {
		return 0;
	}
	/**
	 *   �t�@�C�������擾����B
	 *   @return
	 *  
	 */
	public long getLength(Manipulation parentManipulation) {
		return 0;
	}
	/**
	 *   �^�C���X�^���v���擾����B
	 *   
	 *   @return
	 *   @throws VFSException
	 *  
	 */
	public Date getTimestamp() {
		return null;
	}
	/**
	 *   �^�C���X�^���v���擾����B
	 *   
	 *   @param parentManipulation �e����
	 *   @return	�^�C���X�^���v
	 *   @throws VFSException
	 *  
	 */
	public Date getTimestamp(Manipulation parentManipulation) {
		return null;
	}
	/**
	 *   �^�C���X�^���v��ݒ肷��B
	 *   @param date
	 *   @throws VFSException
	 *  
	 */
	public void setTimestamp(Date date) {
	}
	/**
	 *   �^�C���X�^���v��ݒ肷��B
	 *   
	 *   @param date
	 *   @param parentManipulation
	 *   @throws VFSException
	 *  
	 */
	public void setTimestamp(Date date, Manipulation parentManipulation) {
	}
	/**
	 *   �t�@�C�������݂���Ȃ�true��Ԃ��B
	 *   @return
	 *   @throws VFSException
	 *  
	 */
	public boolean isExists() {
		return false;
	}
	/**
	 *   �t�@�C�������݂���Ȃ�true��Ԃ��B
	 *   @param parentManipulation �e����
	 *   @return
	 *   @throws VFSException
	 *  
	 */
	public boolean isExists(Manipulation parentManipulation) {
		return false;
	}
	/**
	 *   �t�@�C���̎�ނ�Ԃ��B
	 *   @return
	 *   @throws VFSException
	 *  
	 */
	public FileType getType() {
		return null;
	}
	/**
	 *   �t�@�C���̎�ނ�Ԃ��B
	 *   @param parentManipulation �e����
	 *   @return
	 *   @throws VFSException
	 *  
	 */
	public FileType getType(Manipulation parentManipulation) {
		return null;
	}
	/**
	 *   �t�@�C�����t�@�C���Ȃ�true��Ԃ��B
	 *   @return
	 *   @throws VFSException
	 *  
	 */
	public boolean isFile() {
		return false;
	}
	/**
	 *   �t�@�C�����t�@�C���Ȃ�true��Ԃ��B
	 *   @param parentManipulation �e����
	 *   @return
	 *   @throws VFSException
	 *  
	 */
	public boolean isFile(Manipulation parentManipulation) {
		return false;
	}
	/**
	 *   �t�@�C�����f�B���N�g���Ȃ�true��Ԃ��B
	 *   @return
	 *   @throws VFSException
	 *  
	 */
	public boolean isDirectory() {
		return false;
	}
	/**
	 *   �t�@�C�����f�B���N�g���Ȃ�true��Ԃ��B
	 *   @return
	 *   @throws VFSException
	 *  
	 */
	public boolean isDirectory(Manipulation parentManipulation) {
		return false;
	}
	/**
	 *   �t�@�C���������N�Ȃ�true��Ԃ��B
	 *   @return
	 *   @throws VFSException
	 *  
	 */
	public boolean isLink() {
		return false;
	}
	/**
	 *   �t�@�C���������N�Ȃ�true��Ԃ��B
	 *   @return
	 *   @throws VFSException
	 *  
	 */
	public boolean isLink(Manipulation parentManipulation) {
		return false;
	}
	/**
	 *   �e�t�@�C�����擾����B
	 *   @return	�e�t�@�C��
	 *   @throws FileNameException ���̃t�@�C�������[�g�Ȃ瓊������B
	 *   @throws VFSException
	 *  
	 */
	public VFile getParent() {
		return null;
	}
	/**
	 *   ���̃t�@�C�����t�@�C���V�X�e���̃��[�g�Ȃ�Atrue��Ԃ��B
	 *   @return
	 *  
	 */
	public boolean isRoot() {
		return false;
	}
	/**
	 *   �q�t�@�C�����擾����B
	 *   @return	�q�t�@�C��
	 *   @throws VFSException
	 *  
	 */
	public VFile[] getChildren() {
		return null;
	}
	/**
	 *   �q�t�@�C�����擾����B
	 *   @param parentManipulation �e����
	 *   @return	�q�t�@�C��
	 *   @throws VFSException
	 *  
	 */
	public VFile[] getChildren(Manipulation parentManipulation) {
		return null;
	}
	/**
	 *   �t�@�C���𐶐�����B
	 *   @throws VFSException
	 *  
	 */
	public void createFile() {
	}
	/**
	 *   �t�@�C���𐶐�����B
	 *   @param parentManipulation �e����
	 *   @throws VFSException
	 *  
	 */
	public void createFile(Manipulation parentManipulation) {
	}
	/**
	 *   �f�B���N�g���𐶐�����B
	 *   @throws VFSException
	 *  
	 */
	public void createDirectory() {
	}
	/**
	 *   �f�B���N�g���𐶐�����B
	 *   @param parentManipulation �e����
	 *   @throws VFSException
	 *  
	 */
	public void createDirectory(Manipulation parentManipulation) {
	}
	/**
	 *   �����N�𐶐�����B
	 *   @param dest	�����N��
	 *   @throws VFSException
	 *  
	 */
	public void createLink(VFile dest) {
	}
	/**
	 *   �����N�𐶐�����B
	 *   @param dest	�����N��
	 *   @param parentManipulation �e����
	 *   @throws VFSException
	 *  
	 */
	public void createLink(VFile dest, Manipulation parentManipulation) {
	}
	/**
	 *   �t�@�C�����폜����B
	 *   @throws VFSException
	 *  
	 */
	public void delete() {
	}
	/**
	 *   �t�@�C�����폜����B
	 *   @param parentManipulation �e����
	 *   @throws VFSException
	 *  
	 */
	public void delete(Manipulation parentManipulation) {
	}
	/**
	 *   �t�@�C�����ړ�����B
	 *   @param dest	�ړ���t�@�C��
	 *   @throws VFSException
	 *  
	 */
	public void moveTo(VFile dest) {
	}
	/**
	 *   �t�@�C�����ړ�����B
	 *   @param dest	�ړ���t�@�C��
	 *   @param parentManipulation �e����
	 *   @throws VFSException
	 *  
	 */
	public void moveTo(VFile dest, Manipulation parentManipulation) {
	}
	/**
	 *   �t�@�C�����ړ�����B
	 *   @param dest	�ړ���
	 *   @param policy	�㏑���|���V�[
	 *   @param parentManipulation �e����
	 *   @throws VFSException
	 *  
	 */
	public void moveTo(VFile dest, OverwritePolicy policy, Manipulation parentManipulation) {
	}
	/**
	 *   �t�@�C�����R�s�[����B
	 *   @param dest	�R�s�[��
	 *   @throws VFSException
	 *  
	 */
	public void copyTo(VFile dest) {
	}
	/**
	 *   �t�@�C�����R�s�[����B
	 *   @param dest	�R�s�[��
	 *   @param parentManipulation �e����
	 *   @throws VFSException
	 *  
	 */
	public void copyTo(VFile dest, Manipulation parentManipulation) {
	}
	/**
	 *   �t�@�C�����R�s�[����B
	 *   @param dest	�R�s�[��
	 *   @param	policy �㏑���|���V�[ 
	 *   @param parentManipulation �e����
	 *   @throws VFSException
	 *  
	 */
	public void copyTo(VFile dest, OverwritePolicy policy, Manipulation parentManipulation) {
	}
	/**
	 *   �w�肳�ꂽ���̂̎q�t�@�C���I�u�W�F�N�g�𐶐�����B
	 *   @param name	�q�t�@�C���̃t�@�C������
	 *   @return	�����Ŏw�肳�ꂽ�q�t�@�C��
	 *   @throws VFSException
	 *  
	 */
	public VFile getChild(String name) {
		return null;
	}
	/**
	 *   ���΃p�X�Ŏw�肳���t�@�C�����擾����B
	 *   @param relation	���΃p�X
	 *   @return	���΃t�@�C��
	 *   @throws VFSException
	 *  
	 */
	public VFile getRelativeFile(String relation) {
		return null;
	}
	/**
	 *   ������ɕϊ�����B
	 *  
	 */
	public String toString() {
		return null;
	}
	/**
	 *   �n�b�V���l���擾����B
	 *  
	 */
	public int hashCode() {
		return 0;
	}
	/**
	 *   �t�@�C���̒��g��MD5�n�b�V���l�̕������Ԃ��B
	 *   @param parentManipulation	�e����
	 *   @return	MD5�n�b�V��������
	 *   @throws VFSException
	 *  
	 */
	public String getContentHashStr() {
		return null;
	}
	/**
	 *   �t�@�C���̒��g��MD5�n�b�V���l�̕������Ԃ��B
	 *   @param parentManipulation	�e����
	 *   @return	MD5�n�b�V��������
	 *   @throws VFSException
	 *  
	 */
	public String getContentHashStr(Manipulation parentManipulation) {
		return null;
	}
	/**
	 *   �t�@�C���̒��g��MD5�n�b�V���l��Ԃ��B
	 *   @return	MD5�n�b�V��
	 *   @throws VFSException
	 *  
	 */
	public byte[] getContentHash() {
		return null;
	}
	/**
	 *   �t�@�C���̒��g��MD5�n�b�V���l��Ԃ��B
	 *   @param parentManipulation	�e����
	 *   @return	MD5�n�b�V��
	 *   @throws VFSException
	 *  
	 */
	public byte[] getContentHash(Manipulation parentManipulation) {
		return null;
	}
	/**
	 *   �t�@�C���������������肷��B
	 *   �N���X�������Ńp�X�����������Ŕ��肵�Ă���B
	 *  
	 */
	public boolean equals(Object o) {
		return false;
	}
}
