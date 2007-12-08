package com.nullfish.lib.ui.filetree;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import com.nullfish.lib.ui.ThreadSafeUtilities;
import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;

public class VFileTreeNode implements TreeNode {
	private VFile file;
	
	private boolean childInit = false;
	
	private List childNodes = new ArrayList();
	
	private VFileTreeNode parent;
	
	private boolean showsFile = true;
	
	private DefaultTreeModel model;
	
	private boolean loading = false;
	
	public VFileTreeNode(DefaultTreeModel model, VFileTreeNode parent, VFile file) {
		this.model = model;
		this.parent = parent;
		this.file = file;
	}
	
	private void initChild() {
		if(childInit) {
			return;
		}
		
		childInit = true;
		if(file.getFileSystem().isLocal()) {
			try {
				VFile[] children = file.getChildren();
				for(int i=0; i<children.length; i++) {
					if(showsFile || children[i].isDirectory()) {
						childNodes.add(new VFileTreeNode(model, this, children[i]));
					}
				}
			} catch (VFSException e) {
				e.printStackTrace();
			}
		} else {
			Thread thread = new Thread() {
				public void run() {
					setLoading(true);
					Runnable runnable = new Runnable() {
						public void run() {
							model.reload(VFileTreeNode.this);
						}
					};
					ThreadSafeUtilities.executeRunnable(runnable);

					try {
						VFile[] children = file.getChildren();
						for(int i=0; i<children.length; i++) {
							if(showsFile || children[i].isDirectory()) {
								childNodes.add(new VFileTreeNode(model, VFileTreeNode.this, children[i]));
							}
						}
						
						setLoading(false);
						ThreadSafeUtilities.executeRunnable(runnable);
					} catch (VFSException e) {
						e.printStackTrace();
					}
					
				}
			};
			thread.start();
		}
	}

	public Enumeration children() {
		initChild();
		return new Enumeration() {
			int index = -1;
			
			public boolean hasMoreElements() {
				return index + 1 < childNodes.size();
			}

			public Object nextElement() {
				index++;
				return childNodes.get(index);
			}
		};
	}

	public boolean getAllowsChildren() {
		try {
			return file.isDirectory();
		} catch (VFSException e) {
			return false;
		}
	}

	public TreeNode getChildAt(int childIndex) {
		initChild();
		return (TreeNode)childNodes.get(childIndex);
	}

	public int getChildCount() {
		initChild();
		return childNodes.size();
	}

	public int getIndex(TreeNode node) {
		initChild();
		return childNodes.indexOf(node);
	}

	public TreeNode getParent() {
		return parent;
	}

	public boolean isLeaf() {
		try {
			return file.isFile();
		} catch (VFSException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String toString() {
		return (file.isRoot() ? file.getAbsolutePath() : file.getName()) + (isLoading() ? "(loading)" : "");
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof VFileTreeNode)) {
			return false;
		}
		
		return file.equals(((VFileTreeNode)o).file);
	}
	
	public int hashCode() {
		return file.hashCode();
	}
	
	public VFile getFile() {
		return file;
	}

	public void setModel(DefaultTreeModel model) {
		this.model = model;
	}

	private synchronized boolean isLoading() {
		return loading;
	}

	private synchronized void setLoading(boolean loading) {
		this.loading = loading;
	}
}
