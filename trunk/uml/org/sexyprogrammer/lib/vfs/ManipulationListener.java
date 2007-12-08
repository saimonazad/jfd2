package org.sexyprogrammer.lib.vfs;


/**
 *    �t�@�C������̃��X�i�[�C���^�[�t�F�C�X�B 
 *   ���̃C���^�[�t�F�C�X�̃��\�b�h�́A�K�������C�x���g�f�B�X�p�b�`�X���b�h���� 
 *   �Ă΂�Ȃ����Ƃɒ��ӂ��邱�ƁB 
 *    
 *   @author shunji 
 *  
 */
public interface ManipulationListener {
	/**
	 *    �S�������J�n�����ۂɌĂяo�����B 
	 *   @param e 
	 *  
	 */
	public void started(ManipulationEvent e);
	/**
	 *    �O�������J�n�����ۂɌĂяo�����B 
	 *   @param e 
	 *  
	 */
	public void preparationStarted(ManipulationEvent e);
	/**
	 *    �O�������I�������ۂɌĂяo�����B 
	 *   @param e 
	 *  
	 */
	public void preparationFinished(ManipulationEvent e);
	/**
	 *    ���삪�J�n�����ۂɌĂяo�����B 
	 *   @param e 
	 *  
	 */
	public void manipulationStarted(ManipulationEvent e);
	/**
	 *    ���삪�I�������ۂɌĂяo�����B 
	 *   @param e 
	 *  
	 */
	public void manipulationFinished(ManipulationEvent e);
	/**
	 *    �S�������I�������ۂɌĂяo�����B 
	 *   @param e 
	 *  
	 */
	public void finished(ManipulationEvent e);
	/**
	 *    ���삪���~���ꂽ�ۂɌĂяo�����B 
	 *   @param e 
	 *  
	 */
	public void manipulationStopped(ManipulationEvent e);
}
