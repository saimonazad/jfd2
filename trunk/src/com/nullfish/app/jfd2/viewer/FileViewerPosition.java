package com.nullfish.app.jfd2.viewer;

import java.util.HashMap;
import java.util.Map;

/**
 * ��ƂȂ�I�u�W�F�N�g�i�t�@�C���[�A�I�[�i�[�A�V�X�e�����j�ɑ΂��Ă�
 * �t�@�C���r���[�A�̃|�W�V�����i�O�A��듙�j������킷�N���X�B
 * 
 * @author shunji
 *
 */
public class FileViewerPosition {
	private static final Map instanceMap = new HashMap();
	
	private String position;
	
	/**
	 * �O
	 */
	public static final FileViewerPosition FORWARD = new FileViewerPosition("forward");
	
	/**
	 * ��
	 */
	public static final FileViewerPosition BACKGROUND = new FileViewerPosition("bg");
	
	private static final FileViewerPosition[] priority = {
		FORWARD,
		BACKGROUND
	};
		
	private FileViewerPosition(String position) {
		this.position = position;
		instanceMap.put(position, this);
	}
	

	/**
	 * �D�揇�ʂ̔z����擾����B
	 * @return
	 */
	public static FileViewerPosition[] getPriority() {
		return (FileViewerPosition[])priority.clone();
	}
	
	public static FileViewerPosition getInstance(String name) {
		return (FileViewerPosition)instanceMap.get(name);
	}
}
