/*
 * Created on 2004/12/21
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.viewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.nullfish.app.jfd2.Initable;
import com.nullfish.app.jfd2.JFD;
import com.nullfish.lib.vfs.VFS;
import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * 
 * @author shunji
 */
public class FileViewerManager implements Initable {
	/**
	 * �V���O���g���C���X�^���X
	 */
	private static FileViewerManager instance = new FileViewerManager();

	/**
	 * �ݒ�f�B���N�g��
	 */
	private VFile baseDir;
	
	/**
	 * �r���[�A�̃t�@�N�g���̃��X�g
	 */
	private List viewerFactories = new ArrayList();

	/**
	 * �g���q�ƃr���[�A�t�@�N�g���[�̃}�b�v
	 */
	private Map extensionViewerFactoryMap = new HashMap();

	/**
	 * �L�[�ƃr���[�A�̃Z�b�g�̃}�b�v
	 */
	private Map viewerSetMap = new HashMap();
	
	/**
	 * �r���[�A�̃L���b�V���̃}�b�v
	 */
	private Map viewerCacheMap = new HashMap();
	
	/**
	 * �f�t�H���g�̃r���[�A
	 */
	private FileViewerFactory defaultViewerFactory;
	
	public static FileViewerManager getInstance() {
		return instance;
	}

	private FileViewerManager() {
	}

	/**
	 * ����������B
	 */
	public void init(VFile baseDir) throws VFSException {
		this.baseDir = baseDir;
		viewerFactories.clear();
		extensionViewerFactoryMap.clear();
		viewerSetMap.clear();
		viewerCacheMap.clear();

		String[] files = {
				"classpath:///text_viewer.xml",
				"classpath:///jl_player.xml",
				"classpath:///graphic_viewer.xml"
		};
		
		FileViewerFactory[] factories = new FileViewerFactory[files.length];
		for(int i=0; i<files.length; i++) {
			try {
				factories[i] = initFactory(VFS.getInstance().getFile(files[i]), null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		defaultViewerFactory = factories[0];
	}

	public FileViewerFactory addFileViewer(VFile definition) throws JDOMException, IOException, VFSException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		return initFactory(definition, getClass().getClassLoader());
	}
	
	public FileViewerFactory addFileViewer(VFile definition, ClassLoader loader) throws JDOMException, IOException, VFSException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		return initFactory(definition, loader);
	}
	
	
	/**
	 * �t�@�C���r���[�A�̃t�@�N�g���[�𐶐��A���������ĕԂ��B
	 * 
	 * @param definition
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 * @throws VFSException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	private FileViewerFactory initFactory(VFile definition, ClassLoader loader) throws JDOMException, IOException, VFSException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Element root = new SAXBuilder().build(definition.getInputStream()).getRootElement();
		FileViewerFactory factory = (FileViewerFactory) Class.forName(root.getAttributeValue("factory")).newInstance();
		if(loader != null) {
			factory.setLoader(loader);
		}

		factory.init(baseDir, root);
		String[] extensions = factory.getSupportedExtensions();
		for(int i=0; extensions != null && i<extensions.length; i++) {
			registerFactory(extensions[i], factory);
		}

		return factory;
	}
	
	public void registerFactory(String extension, FileViewerFactory factory) {
		extensionViewerFactoryMap.put(extension, factory);
	}
	
	/**
	 * �p��
	 * 
	 */
	public void dispose(Object owner) {
		FileViewerCache cache = getFileViewerCache(owner);
		cache.disposeAll();
		viewerCacheMap.remove(owner);
		viewerSetMap.remove(owner);
	}

	/**
	 * �t�@�C����\������B
	 * 
	 * @param jfd
	 * @param file
	 * @return �t�@�C���`���ɑΉ�����t�@�C���r���[�A�����݂��A�r���[�A���J���ꂽ�ꍇtrue��Ԃ��B
	 */
	public boolean openFileWithDefaultViewer(JFD jfd, VFile file) {
		return openFile(jfd, file, defaultViewerFactory);
	}
	
	private boolean openFile(JFD jfd, VFile file, FileViewerFactory factory) {
		ViewerController controller = factory.getController();
		FileViewer viewer = controller.getViewer(jfd, file, factory);
		if(viewer == null) {
			return false;
		}
		
		ViewerSet viewerSet = controller.getViewerSet(jfd);
		FileViewer currentViewer = viewerSet.getViewer(viewer.getPosition());
		if(currentViewer != null && currentViewer != viewer) {
			currentViewer.close();
		}
		
		viewer.open(file, jfd);
		viewerSet.setViewer(viewer.getPosition(), viewer);
		
		return true;
	}

	/**
	 * �t�@�C����\������B
	 * 
	 * @param jfd
	 * @param file
	 * @return �t�@�C���`���ɑΉ�����t�@�C���r���[�A�����݂��A�r���[�A���J���ꂽ�ꍇtrue��Ԃ��B
	 */
	public boolean openFile(JFD jfd, VFile file) {
		FileViewerFactory factory = (FileViewerFactory) extensionViewerFactoryMap
				.get(file.getFileName().getExtension().toLowerCase());
		if (factory == null) {
			factory = defaultViewerFactory;
		}

		return openFile(jfd, file, factory);
	}

	public FileViewerCache getFileViewerCache(Object key) {
		FileViewerCache rtn = (FileViewerCache)viewerCacheMap.get(key);
		if(rtn== null) {
			rtn = new FileViewerCache();
			viewerCacheMap.put(key, rtn);
		}
		
		return rtn;
	}
	
	/**
	 * ��ԏ�̃t�@�C���r���[�A�i�G�X�P�[�v�L�[�ŃN���[�Y�����r���[�A�j���擾����B
	 * 
	 * @param jfd
	 * @return
	 */
	public FileViewer getTopViewer(JFD jfd) {

		FileViewer jfdViewer = getViewerSet(jfd).getTopViewer();
		if(jfdViewer != null) {
			return jfdViewer;
		}
	
		FileViewer ownerViewer = getViewerSet(jfd.getJFDOwner()).getTopViewer();
		if(ownerViewer != null) {
			return ownerViewer;
		}
		
		return getViewerSet(Runtime.getRuntime()).getTopViewer();
	}
	
	/**
	 * �t�@�C���r���[�A�������Ƃ��ɌĂяo�����B
	 * @param viewer
	 */
	public void fileViewerClosed(FileViewer viewer) {
		ViewerSet[] sets = (ViewerSet[])viewerSetMap.values().toArray(new ViewerSet[0]);
		for(int i=0; i<sets.length; i++) {
			sets[i].removeViewer(viewer);
		}
	}
	
	/**
	 * �r���[�A�̃Z�b�g���擾����B
	 * @param key
	 * @return
	 */
	public ViewerSet getViewerSet(Object key) {
		ViewerSet rtn = (ViewerSet)viewerSetMap.get(key);
		if(rtn == null) {
			rtn = new ViewerSet();
			viewerSetMap.put(key, rtn);
		}
		
		return rtn;
	}
}
