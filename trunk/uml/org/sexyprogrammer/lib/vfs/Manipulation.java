package org.sexyprogrammer.lib.vfs;


/**
 *    �t�@�C���ɑ΂��鑀��̃C���^�[�t�F�C�X�B 
 *    
 *   @author shunji 
 *  
 */
public interface Manipulation {
	/**
	 *    �s�m��v���O���X�l�̒萔 
	 *  
	 */
	public static final int PROGRESS_INDETERMINED = Integer.MIN_VALUE;
	/**
	 *    ����𓯊����s����B 
	 *    
	 *   @throws VFSException 
	 *  
	 */
	public abstract void start();
	/**
	 *    �����񓯊����s����B 
	 *    
	 *   @throws VFSException 
	 *  
	 */
	public abstract void startAsync();
	/**
	 *    ����̑O�����i�ő�v���O���X�v�Z�Ȃǁj�����s����B 
	 *    
	 *   @throws VFSException 
	 *  
	 */
	public abstract void prepare();
	/**
	 *    ��������s����B 
	 *    
	 *   @throws VFSException 
	 *  
	 */
	public abstract void execute();
	/**
	 *    ��Ə󋵂��擾����B 
	 *  
	 */
	public abstract int getProgress();
	/**
	 *    ��Ə󋵂̍ő���擾����B 
	 *   @return 
	 *  
	 */
	public abstract int getProgressMin();
	/**
	 *    ��Ə󋵂̍ŏ����擾����B 
	 *   @return 
	 *  
	 */
	public abstract int getProgressMax();
	/**
	 *    �����ۂɎ��s����Ă����Ƃ��擾����B 
	 *   @return 
	 *  
	 */
	public abstract Manipulation getCurrentManipulation();
	/**
	 *    ��Ƃ𒆎~����B 
	 *   
	 *  
	 */
	public void stop();
	/**
	 *    ��Ƃ����~����Ă���Ȃ�true��Ԃ��B 
	 *   @return 
	 *  
	 */
	public boolean isStopped();
	/**
	 *    �I�� 
	 *   
	 *  
	 */
	public void setFinished(boolean finished);
	/**
	 *    ��Ƃ��I�����Ă���Ȃ�true��Ԃ��B 
	 *   @return 
	 *  
	 */
	public boolean isFinished();
	/**
	 *    �e�ƂȂ鑀����Z�b�g����B 
	 *   @param parent 
	 *  
	 */
	public void setParentManipulation(Manipulation parent);
	/**
	 *    �e�ƂȂ鑀����擾����B 
	 *   @return 
	 *  
	 */
	public Manipulation getParentManipulation();
	/**
	 *    ���[�g�ƂȂ鑀����擾����B 
	 *   @return 
	 *  
	 */
	public Manipulation getRootManipulation();
	/**
	 *    ����Ƀ��X�i��ǉ�����B 
	 *  
	 */
	public void addManipulationListener(ManipulationListener listener);
	/**
	 *    ���삩�烊�X�i���폜����B 
	 *  
	 */
	public void removeManipulationListener(ManipulationListener listener);
}
