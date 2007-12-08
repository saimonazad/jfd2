/*
 * Created on 2004/05/24
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2.ui.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import com.nullfish.app.jfd2.JFDModel;
import com.nullfish.app.jfd2.JFDModelListener;
import com.nullfish.lib.ui.ThreadSafeUtilities;
import com.nullfish.lib.ui.list_table.ListCursorModel;
import com.nullfish.lib.ui.list_table.ListCursorModelListener;

/**
 * @author shunji
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class JFDListCursorModel implements ListCursorModel {
	/**
	 * ���X�i
	 */
	private List listeners = new ArrayList();
	
	private JFDModel model;
	
	public JFDListCursorModel(JFDModel model) {
		this.model = model;
		model.addJFDModelListener(new JFDModelListenerImpl());
	}
	
	/* (non-Javadoc)
	 * @see com.nullfish.lib.ui.list_table.ListCursorModel#getSelectedIndex()
	 */
	public int getSelectedIndex() {
		return model.getSelectedIndex();
	}

	/* (non-Javadoc)
	 * @see com.nullfish.lib.ui.list_table.ListCursorModel#setSelectedIndex(int)
	 */
	public void setSelectedIndex(int index) {
		model.setSelectedIndex(index);
	}

	/* (non-Javadoc)
	 * @see com.nullfish.lib.ui.list_table.ListCursorModel#addListCursorModelListener(com.nullfish.lib.ui.list_table.ListCursorModelListener)
	 */
	public void addListCursorModelListener(ListCursorModelListener listener) {
		listeners.add(listener);
	}

	/**
	 * �J�[�\���̈ړ���ʒm����B
	 * @param oldIndex
	 * @param newIndex
	 */
	private void fireCursorChanged() {
		for(int i=0; i<listeners.size(); i++) {
			ListCursorModelListener listener = (ListCursorModelListener)listeners.get(i);
			listener.cursorChanged(this);
		}
	}
	
	private class JFDModelListenerImpl implements JFDModelListener {
		/* (non-Javadoc)
		 * @see com.nullfish.app.jfd2.JFDModelListener#dataChanged(com.nullfish.app.jfd2.JFDModel)
		 */
		public void dataChanged(JFDModel model) {
		}
	
		/* (non-Javadoc)
		 * @see com.nullfish.app.jfd2.JFDModelListener#directoryChanged(com.nullfish.app.jfd2.JFDModel)
		 */
		public void directoryChanged(JFDModel model) {
			Runnable caller = new Runnable() {
				public void run() {
					fireCursorChanged();
				}
			};
			
			ThreadSafeUtilities.executeRunnable(caller);
		}
	
		/**
		 * JFDModel�ŃJ�[�\�����ړ������ۂɌĂяo�����B
		 * @see com.nullfish.app.jfd2.JFDModelListener#cursorMoved(com.nullfish.app.jfd2.JFDModel, int, int)
		 */
		public void cursorMoved(JFDModel model) {
			// TODO �K�v�����ɂ߂�B�p�t�H�[�}���X��v��Ȃ�������B
			if(SwingUtilities.isEventDispatchThread()) {
				fireCursorChanged();
				return;
			}
			
			Runnable caller = new Runnable() {
				public void run() {
					fireCursorChanged();
				}
			};
			
			ThreadSafeUtilities.executeRunnable(caller);
		}

		/* (non-Javadoc)
		 * @see com.nullfish.app.jfd2.JFDModelListener#markChanged(com.nullfish.app.jfd2.JFDModel)
		 */
		public void markChanged(JFDModel model) {
			// TODO Auto-generated method stub
			
		}
	}
}
