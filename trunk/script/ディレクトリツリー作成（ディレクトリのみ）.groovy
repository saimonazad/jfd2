import com.nullfish.app.jfd2.dialog.DialogUtilities

model = jfd.getModel()
currentDir = model.getCurrentDirectory()

tree = new TreeNode(currentDir, null)

result = new StringBuffer()
tree.output(result)

DialogUtilities.showTextAreaMessageDialog(jfd, result.toString(), false, "åãâ ")

class FileComparator implements Comparator {
	def int compare(Object o1, Object o2) {
		if(o1.isDirectory() && o2.isFile()) {
			return -1
		}

		if(o2.isDirectory() && o1.isFile()) {
			return 1
		}

		return o1.getName().compareTo(o2.getName())
	}
}

class TreeNode {
	def file
	public def parent
	def children = []
	def index = 0

	def TreeNode(file, parent) {
		this.file = file
		this.parent = parent

		if(file.isDirectory()) {
			def childFiles = file.getChildren()
			Arrays.sort(childFiles, new FileComparator())
			for(child in childFiles) {
				if(child.isDirectory()) {
					children.add(new TreeNode(child, this))
				}
			}
		}
	}

	def output(result) {
		if(parent != null) {
			parent.outputAncestor(result, this)
		}

		if(file.isDirectory()) {
			result.append("Åõ")
		}
		result.append(file.getName())
		result.append("\n")

		for(child in children) {
			child.output(result)
			index ++
		}
	}

	def outputAncestor(result, current) {
		if(parent != null) {
			parent.outputAncestor(result, current)
		}

		if(current.parent == this) {
			if(index < children.size() - 1) {
				result.append("Ñ•")
			} else if(index == children.size() - 1) {
				result.append("Ñ§")
			} else {
				result.append("Å@")
			}
		} else {
			if(index < children.size() - 1) {
				result.append("Ñ†")
			} else {
				result.append("Å@")
			}
		}
	}
}