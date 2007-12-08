import com.nullfish.lib.vfs.VFS
import com.nullfish.app.jfd2.dialog.DialogUtilities

currentDir = jfd.getModel().getCurrentDirectory()
result = new StringBuffer()

listDir(currentDir)
DialogUtilities.showTextAreaMessageDialog(jfd, result.toString(), false, "íœŠ®—¹")

def listDir(dir) {
	result.append(currentDir.getRelation(dir))
	result.append("\n")

	children = dir.getChildren(command)

	for(child in children) {
		if(child.isDirectory()) {
			listDir(child)
		}
	}
}