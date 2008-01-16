package com.nullfish.lib.ui.filetree;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.nullfish.lib.vfs.VFS;
import com.nullfish.lib.vfs.VFile;

public class VFileTree extends JPanel {
	private JTree tree = new JTree();
	
	private VFile root;
	
	private JScrollPane scroll = new JScrollPane(tree);
	
	private DefaultTreeModel model;
	
	public VFileTree() {
		add(scroll);
	}
	
	public void setRoot(VFile root) {
		VFileTreeNode rootNode = new VFileTreeNode(this, null, root);
		model = new DefaultTreeModel(rootNode);
		tree.setModel(model);
		this.root = root;
	}
	
	public void setSelectedFile(VFile file) {
		List fileList = new ArrayList();
		while(file != null) {
			fileList.add(0, file);
			file = file.getParent();
		}
		
		List nodeList = new ArrayList();
		VFileTreeNode node = (VFileTreeNode)tree.getModel().getRoot();
		for(int i=0; i<fileList.size(); i++) {
			if(i==0) {
				nodeList.add(node);
			} else {
				VFileTreeNode child = findChildNode((VFileTreeNode)nodeList.get(nodeList.size() - 1), (VFile)fileList.get(i));
				if(child == null) {
					break;
				}
				nodeList.add(child);
			}
		}
		
		TreePath path = new TreePath(nodeList.toArray());
		tree.setSelectionPath(path);
		tree.scrollPathToVisible(path);
	}
	
	private VFileTreeNode findChildNode(VFileTreeNode node, VFile childFile) {
		Enumeration children = node.children();
		while(children.hasMoreElements()) {
			VFileTreeNode child = (VFileTreeNode)children.nextElement();
			if(child.getFile().equals(childFile)) {
				return child;
			}
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		try {
			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			VFileTree tree = new VFileTree();
			tree.setRoot(VFS.getInstance().getFile("root:///"));
//			tree.setRoot(VFS.getInstance().getFile("ftp://hogehoge:tkcstiis@hogehoge.sakura.ne.jp/"));
			frame.setContentPane(tree);
			frame.pack();
			frame.setVisible(true);
			tree.setSelectedFile(VFS.getInstance().getFile("C:\\Program Files2\\ChangeKey"));
//			tree.setSelectedFile(VFS.getInstance().getFile("ftp://hogehoge:tkcstiis@hogehoge.sakura.ne.jp/home/hogehoge/www/ikemen"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DefaultTreeModel getModel() {
		return model;
	}
}
