package com.nullfish.app.jfd2.viewer;

import java.util.HashMap;
import java.util.Map;

import com.nullfish.app.jfd2.JFD;

/**
 * �t�@�C���r���[�A�̃L���b�V���N���X
 * 
 * @author shunji
 *
 */
public class FileViewerCache {
	/**
	 * �r���[�A�̃}�b�v
	 */
	private Map viewerMap = new HashMap();
	
	/**
	 * �t�@�N�g���Ɋ֘A�t����ꂽ�r���[�A�̃L���b�V�����擾����B
	 * �L���b�V������ĂȂ��ꍇ�͐V�K�ɍ쐬���A�L���b�V�����Ă���Ԃ��B
	 * 
	 * @param factory
	 * @param jfd
	 * @return
	 */
	public FileViewer getCachedViewer(FileViewerFactory factory, JFD jfd) {
		FileViewer rtn = (FileViewer)viewerMap.get(factory);
		if(rtn == null) {
			rtn = factory.getFileViewer(jfd);
			viewerMap.put(factory, rtn);
		}
		
		return rtn;
	}
	
	public void disposeAll() {
		Object[] keys = viewerMap.keySet().toArray();
		for(int i=0; i<keys.length; i++) {
			FileViewer viewer = (FileViewer)viewerMap.get(keys[i]);
			viewer.dispose();
		}
		
		viewerMap.clear();
	}
}
