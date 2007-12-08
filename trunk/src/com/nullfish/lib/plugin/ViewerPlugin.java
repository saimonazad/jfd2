package com.nullfish.lib.plugin;

import com.nullfish.app.jfd2.viewer.FileViewerFactory;
import com.nullfish.app.jfd2.viewer.FileViewerManager;
import com.nullfish.lib.vfs.exception.VFSException;

public class ViewerPlugin extends AbstractPlugin {
	/**
	 * �t�@�C���r���[�A�N���X
	 */
	public static final String PARAM_VIEWER_CLASS = "viewer_class";
	
	/**
	 * �V�X�e���J�n���ɌĂяo�����B
	 * 
	 * @throws VFSException
	 */
	public void systemStarted() {
		String factoryClassName = (String)getParam(PARAM_VIEWER_CLASS);
		try {
			FileViewerFactory factory = (FileViewerFactory)Class.forName(factoryClassName).newInstance();
			String[] extension = (String[])getParam("extension");
			
			for(int i=0; i<extension.length; i++) {
				FileViewerManager.getInstance().registerFactory(extension[i], factory);
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
