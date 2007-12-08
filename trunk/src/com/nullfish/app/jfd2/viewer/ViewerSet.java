package com.nullfish.app.jfd2.viewer;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author shunji
 *
 */
public class ViewerSet {
	/**
	 * �ʒu�ƃr���[�A�̃}�b�v
	 */
	private Map positionViewerMap = new HashMap();
	
	/**
	 * �r���[�A���擾����B
	 * 
	 * @param position	�ʒu
	 * @return
	 */
	public FileViewer getViewer(FileViewerPosition position) {
		return (FileViewer)positionViewerMap.get(position);
	}
	
	/**
	 * �r���[�A���Z�b�g����B
	 * 
	 * @param position
	 * @param viewer
	 */
	public void setViewer(FileViewerPosition position, FileViewer viewer) {
		positionViewerMap.put(position, viewer);
	}
	
	public FileViewer getTopViewer() {
		FileViewerPosition[] priority = FileViewerPosition.getPriority();
		for(int i=0; i<priority.length; i++) {  
			FileViewer viewer = getViewer(priority[i]);
			if(viewer != null) {
				return viewer;
			}
		}

		return null;
	}
	
	public boolean contains(FileViewer viewer) {
		return positionViewerMap.containsValue(viewer);
	}
	
	public void removeViewer(FileViewer viewer) {
		FileViewerPosition[] priority = FileViewerPosition.getPriority();
		for(int i=0; i<priority.length; i++) {  
			FileViewer v = getViewer(priority[i]);
			if(v == viewer) {
				positionViewerMap.remove(priority[i]);
			}
		}

	}
}
