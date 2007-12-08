/*
 * Created on 2005/01/24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.viewer;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;

import com.nullfish.app.jfd2.JFD;
import com.nullfish.app.jfd2.viewer.controller.EachJFDOwnerViewrController;
import com.nullfish.app.jfd2.viewer.controller.EachJFDViewerController;
import com.nullfish.app.jfd2.viewer.controller.SingletonViewerController;
import com.nullfish.lib.meta_data.MetaDataManager;
import com.nullfish.lib.vfs.VFile;

/**
 * @author shunji TODO To change the template for this generated type comment go
 *         to Window - Preferences - Java - Code Style - Code Templates
 */
public class DefaultFileViewerFactory implements FileViewerFactory {
	/**
	 * �p�����[�^�̃��X�g
	 */
	private List parameters = new ArrayList();

	/**
	 * �ݒ�f�B���N�g��
	 */
	private VFile baseDir;
	
	/**
	 * �I�[�v���A�N���[�Y���̐U�镑��
	 */
	private ViewerController controller;

	/**
	 * ��I�u�W�F�N�g�ɑ΂���t�@�C���r���[�A�̈ʒu
	 */
	private FileViewerPosition position;

	/**
	 * �N���X��
	 */
	protected String className;

	/**
	 * �T�|�[�g����Ă�g���q
	 */
	private String[] supportedExtensions;
	
	/**
	 * �N���X���[�_
	 */
	private ClassLoader loader = getClass().getClassLoader();
	
	/**
	 * jFD�{�̂̃J�[�\���ړ���ǂ����ǂ����̃t���O
	 */
	protected boolean followsOwner;

	public static final String ATTR_ClASS_NAME = "class";

	public static final String ATTR_CONSTRAINTS = "constraints";

	public static final String ATTR_UNIT = "unit";

	public static final String ATTR_POSITION = "position";

	public static final String ATTR_FOLLOW_CURSOR = "follows";
	
	public static final String TAG_EXTENSION = "extension";
	
	/**
	 * �r���[�A���擾����B
	 */
	public FileViewer getFileViewer(JFD jfd) {
		FileViewer rtn = createFileViewer(jfd);

		for (int i = 0; i < parameters.size(); i++) {
			Parameter param = (Parameter) parameters.get(i);
			rtn.setParam(param.getName(), param.getValue());
		}

		rtn.setPosition(position);
		rtn.setFollowsCursor(followsOwner);
		rtn.setController(getController());
		rtn.init(baseDir);
		
		return rtn;
	}

	/**
	 * �I�[�v���A�N���[�Y���̐U�镑���N���X��ݒ肷��B
	 * 
	 * @return
	 */
	public void setController(ViewerController controller) {
		this.controller = controller;
	}

	/**
	 * �I�[�v���A�N���[�Y���̐U�镑���N���X��Ԃ��B
	 * 
	 * @return
	 */
	public ViewerController getController() {
		return controller;
	}

	/**
	 * �p�����[�^��ݒ肷��B
	 */
	public void setParam(String name, Object value) {
		parameters.add(new Parameter(name, value));
	}

	/**
	 * �p�����[�^�N���X
	 */
	private class Parameter {
		String name;

		Object value;

		public Parameter(String name, Object value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public Object getValue() {
			return value;
		}
	}

	public FileViewerPosition getPosition() {
		return position;
	}

	public void setPosition(FileViewerPosition position) {
		this.position = position;
	}

	public FileViewer createFileViewer(JFD jfd) {
		try {
			return (FileViewer) loader.loadClass(className).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void init(VFile baseDir, Element node) {
		this.baseDir = baseDir;
		className = node.getAttributeValue(ATTR_ClASS_NAME);

		setParam(FileViewerContainerPanel.PARAM_CONSTRAINTS, node
				.getAttributeValue(ATTR_CONSTRAINTS));

		String controllerStr = node.getAttributeValue(ATTR_UNIT);
		if ("each_jfd".equals(controllerStr)) {
			setController(new EachJFDViewerController());
		} else if ("each_owner".equals(controllerStr)) {
			setController(new EachJFDOwnerViewrController());
		} else {
			setController(new SingletonViewerController());
		}

		position = FileViewerPosition.getInstance(node
				.getAttributeValue(ATTR_POSITION));

		List paramsList = node.getChildren(MetaDataManager.TAG_NAME);
		for (int i = 0; i < paramsList.size(); i++) {
			Element paramNode = (Element) paramsList.get(i);
			setParam(paramNode.getAttributeValue(MetaDataManager.ATTR_NAME),
					MetaDataManager.getInstance().paramNode2Object(paramNode));
		}
		
		followsOwner = Boolean.valueOf(node.getAttributeValue(ATTR_FOLLOW_CURSOR)).booleanValue();
		
		Element exts = node.getChild("extension");
		if(exts == null) {
			return;
		}
		
		Element lists = exts.getChild("list");
		if(lists == null) {
			return;
		}
		
		if(lists != null) {
			List extensionList = (List)MetaDataManager.getInstance().node2Object(lists);
			supportedExtensions = new String[extensionList.size()];
			supportedExtensions = (String[])extensionList.toArray(supportedExtensions);
		}
	}

	public String[] getSupportedExtensions() {
		return supportedExtensions;
	}

	public void setLoader(ClassLoader loader) {
		this.loader = loader;
	}
}
