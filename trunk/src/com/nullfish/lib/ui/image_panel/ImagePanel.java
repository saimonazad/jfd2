package com.nullfish.lib.ui.image_panel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.RepaintManager;

import com.nullfish.lib.keymap.KeyStrokeMap;
import com.nullfish.lib.ui.ThreadSafeUtilities;

/**
 * @author shunji
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ImagePanel extends JComponent {
	//	�\���C���[�W
	Image image = null;
	
	//	�\���T�C�Y�{��
	int magnification = 100;
	
	//	�p�x
	int rotation = 0;
	
	//	���E���]
	boolean hFlip = false;
	
	//	�㉺���]
	boolean vFlip = false;
	
	//	�\���T�C�Y���Œ肷��t���O
	boolean fixSize = false;
	
	//	�\������
	int viewWidth;
	
	//	�\���c��
	int viewHeight;
	
	/** �E�X�O�x��] */
	public static final String ROTATE_RIGHT = "rotate_right";
	
	/** ���X�O�x��] */
	public static final String ROTATE_LEFT = "rotate_left";
	
	/** �g�� */
	public static final String ZOOM_IN = "zoom_in";
	
	/** �k�� */
	public static final String ZOOM_OUT = "zoom_out";
	
	/** �\���̂��߂̃A�t�B���ϊ� */ 
	AffineTransform transform = AffineTransform.getRotateInstance(0);
	
	java.util.List listeners = new ArrayList();
	
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	
	public static final int BUFFER_SIZE = 4096; //	�o�b�t�@�̃T�C�Y

	/**
	 * �f�t�H���g�R���X�g���N�^
	 */
	public ImagePanel() {
		initAction();
		initInput();
	}

	public void dispose() {
		if(image != null) {
			image.flush();
		}
	}
	
	/**
	 * Action������������B
	 */	
	protected void initAction() {
		ActionMap map = getActionMap();
		
		Action rightRotateAction = new RotateAction(90);
		rightRotateAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_R));
		map.put(ROTATE_RIGHT, rightRotateAction);

		Action leftRotateAction = new RotateAction(-90);
		leftRotateAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_L));
		map.put(ROTATE_LEFT, leftRotateAction);

		Action zoomInAction = new ZoomAction(-25);
		zoomInAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_B));
		map.put(ZOOM_IN, zoomInAction);

		Action zoomOutAction = new ZoomAction(25);
		zoomOutAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_L));
		map.put(ZOOM_OUT, zoomOutAction);
	}
	
	/**
	 * �L�[���͂�����������B
	 */
	protected void initInput() {
		InputMap map = getInputMap();
		
		map.put(KeyStrokeMap.getKeyStroke(KeyEvent.VK_L, 0), ROTATE_LEFT);
		map.put(KeyStrokeMap.getKeyStroke(KeyEvent.VK_SEMICOLON, 0), ROTATE_LEFT);
		map.put(KeyStrokeMap.getKeyStroke(KeyEvent.VK_EQUALS, 0), ROTATE_LEFT);
		
		map.put(KeyStrokeMap.getKeyStroke(KeyEvent.VK_R, 0), ROTATE_RIGHT);
		map.put(KeyStrokeMap.getKeyStroke(KeyEvent.VK_COLON, 0), ROTATE_RIGHT);
		
		map.put(KeyStrokeMap.getKeyStroke(KeyEvent.VK_CLOSE_BRACKET, 0), ZOOM_OUT);
		map.put(KeyStrokeMap.getKeyStroke(KeyEvent.VK_MINUS, 0), ZOOM_OUT);

		map.put(KeyStrokeMap.getKeyStroke(KeyEvent.VK_OPEN_BRACKET, 0), ZOOM_IN);
		map.put(KeyStrokeMap.getKeyStroke(KeyEvent.VK_PLUS, 0), ZOOM_IN);

	}
	
	/**
	 * �\�����[�h������������
	 */
	public void initShowingMode() {
		magnification = 100;
		rotation = 0;
		hFlip = false;
		vFlip = false;
		//fixSize = false;
	}
	
	/**
	 * �\�����X�V���ꂽ�ہA�ʒm�������郊�X�i��ǉ�����B
	 * @param listener
	 */
	public void addImageComponentListener(ImagePanelListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * �e���X�i�ɕω���ʒm����B
	 */
	private void fireUpdated() {
		for(int i=0; i<listeners.size(); i++) {
			((ImagePanelListener)listeners.get(i)).imageComponentUpdated(this);
		}
	}

	/**
	 * �\���C���[�W���Z�b�g����B
	 * �X���b�h�Z�[�t�B
	 * @param image
	 */
	public synchronized void setImage(final Image image) {
		Runnable runnable = new Runnable() {
			public void run() {
				if(ImagePanel.this.image != null) {
					ImagePanel.this.image.flush();
				}
				
				ImagePanel.this.image = image;
				applyChange();
			}
		};

		ThreadSafeUtilities.executeRunnable(runnable);
	}
	
	protected void applyChange() {
		if(image == null) {
			setPreferredSize( new Dimension(0, 0) );
			fireUpdated();
			RepaintManager.currentManager(this).markCompletelyDirty(this);
			return;
		}
		
		AffineTransform t = null;

		if(fixSize) {
			this.transform = getSizeFixedTransform();
		} else {
			this.transform = getTransform();
		}
		
		fireUpdated();
		
		RepaintManager.currentManager(this).markCompletelyDirty(this);
	}

	/**
	 * �\�����[�h�ɍ��킹�ăA�t�B���ϊ����쐬���ĕԂ��B
	 * @return AffineTransform
	 */
	protected AffineTransform getTransform() {
		AffineTransform newTransform = AffineTransform.getScaleInstance(vFlip ? -1 : 1, hFlip ? -1 : 1 );
		newTransform.concatenate( AffineTransform.getRotateInstance(Math.toRadians(rotation)) );
		double scale = (double)magnification / 100;
		
		newTransform.concatenate(AffineTransform.getScaleInstance(scale, scale));
		
		Point2D[] apexs = {
			new Point(0, 0),
			new Point(0, image.getHeight(this)),
			new Point(image.getWidth(this), 0),
			new Point(image.getWidth(this), image.getHeight(this))
		};
		
		double minX = apexs[0].getX();
		double minY = apexs[0].getY();
		double maxX = apexs[0].getX();
		double maxY = apexs[0].getY();

		for(int i=0; i<apexs.length; i++) {
			newTransform.transform(apexs[i], apexs[i]);
			
			minX = minX < apexs[i].getX() ? minX : apexs[i].getX(); 
			minY = minY < apexs[i].getY() ? minY : apexs[i].getY();
			maxX = maxX > apexs[i].getX() ? maxX : apexs[i].getX();
			maxY = maxY > apexs[i].getY() ? maxY : apexs[i].getY();
		}

		setPreferredSize( new Dimension((int)(maxX - minX), (int)(maxY - minY)) );

		AffineTransform deltaTransform = AffineTransform.getTranslateInstance(minX *(-1), minY *(-1)); 
		deltaTransform.concatenate(newTransform);
		
		return deltaTransform;
	}
	
	/**
	 * �\���T�C�Y�Ɏ��܂�A�t�B���ϊ����쐬���ĕԂ��B
	 * @return AffineTransform
	 */
	protected AffineTransform getSizeFixedTransform() {
		AffineTransform newTransform = AffineTransform.getScaleInstance(vFlip ? -1 : 1, hFlip ? -1 : 1 );
		newTransform.concatenate( AffineTransform.getRotateInstance(Math.toRadians(rotation)) );
		
		int imageWidth = image.getWidth(this);
		int imageHeight = image.getHeight(this);
		
		Point2D[] apexs = {
			new Point(0, 0),
			new Point(0, imageHeight),
			new Point(imageWidth, 0),
			new Point(imageWidth, imageHeight)
		};
		
		//	�\���T�C�Y
		double minX = apexs[0].getX();
		double minY = apexs[0].getY();
		double maxX = apexs[0].getX();
		double maxY = apexs[0].getY();

		for(int i=0; i<apexs.length; i++) {
			newTransform.transform(apexs[i], apexs[i]);
			
			minX = minX < apexs[i].getX() ? minX : apexs[i].getX(); 
			minY = minY < apexs[i].getY() ? minY : apexs[i].getY();
			maxX = maxX > apexs[i].getX() ? maxX : apexs[i].getX();
			maxY = maxY > apexs[i].getY() ? maxY : apexs[i].getY();
		}
		
		int width = getWidth();
		int height = getHeight();
		
		double vScale = width < maxX - minX ? width/(maxX - minX) : 1;  
		double hScale = height < maxY - minY ? height/(maxY - minY) : 1;  
		double scale = vScale < hScale ? vScale : hScale;
		
		newTransform.concatenate(AffineTransform.getScaleInstance(scale, scale));
		
		//	���S���킹
		Point2D imageCenter = new Point(imageWidth / 2, imageHeight / 2);
		newTransform.transform(imageCenter, imageCenter);
		
		AffineTransform deltaTransform = AffineTransform.getTranslateInstance((width/2) - imageCenter.getX(), (height/2) - imageCenter.getY()); 
		deltaTransform.concatenate(newTransform);

		return deltaTransform;
	}
	
	/**
	 * �`�惁�\�b�h
	 * @see javax.swing.JComponent#paintComponent(Graphics)
	 */
	public void paintComponent(Graphics g) {
		if (image != null) {
			Graphics2D g2 = (Graphics2D)g;
			AffineTransform oldTransform = g2.getTransform();

			g2.transform(transform);
			g2.drawImage(image, 0, 0, this);

			g2.setTransform(oldTransform);
		}
	}

	/**
	 * �萔�ɑΉ�����Action��Ԃ��B
	 * 
	 * @param key
	 * @return Action
	 */
	public Action getAction(Object key) {
		return getActionMap().get(key);
	}
	
	/**
	 * Returns the fixSize.
	 * @return boolean
	 */
	public boolean isFixSize() {
		return fixSize;
	}

	/**
	 * Returns the hFlip.
	 * @return boolean
	 */
	public boolean isHFlip() {
		return hFlip;
	}

	/**
	 * Returns the image.
	 * @return Image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Returns the magnification.
	 * @return int
	 */
	public int getMagnification() {
		return magnification;
	}

	/**
	 * Returns the rotation.
	 * @return int
	 */
	public int getRotation() {
		return rotation;
	}

	/**
	 * Returns the vFlip.
	 * @return boolean
	 */
	public boolean isVFlip() {
		return vFlip;
	}

	/**
	 * ��]�p��0����360�̊Ԃɂ���B
	 */
	private void verifyRotation() {
		while(rotation >= 360) {
			rotation -= 360;
		}
		
		while(rotation < 0) {
			rotation += 360;
		}
	}
	
	/**
	 * �摜�̉�]���s��Action
	 */
	class RotateAction extends AbstractAction {
		int delta;
		
		public RotateAction (int delta) {
			this.delta = delta;
		}
		
		public void actionPerformed(ActionEvent e) {
			rotation +=delta;
			verifyRotation();
			applyChange(); 
		}
	}
	
	/**
	 * �g��k�����s��Action
	 * 
 	 */
	class ZoomAction extends AbstractAction {
		int delta;
		
		public ZoomAction(int delta) {
			this.delta = delta;
		}
		
		public void actionPerformed(ActionEvent e) {
			if(magnification + delta > 0) {
				magnification+=delta;
				applyChange();
			}
		}
	}
	/**
	 * Sets the fixSize.
	 * @param fixSize The fixSize to set
	 */
	public void setFixSize(boolean fixSize) {
		this.fixSize = fixSize;
		applyChange();
	}

}
