package com.nullfish.app.jfd2.viewer;

import com.nullfish.app.jfd2.JFD;
import com.nullfish.lib.vfs.VFile;

/**
 * �r���[�A�̃I�[�v���A�N���[�Y���̓���̃C���^�[�t�F�C�X
 * @author shunji
 *
 */
public interface ViewerController {
	public FileViewer getViewer(JFD jfd, VFile file, FileViewerFactory factory);

	public ViewerSet getViewerSet(JFD jfd);
	
	public void close(FileViewer viewer);
}
