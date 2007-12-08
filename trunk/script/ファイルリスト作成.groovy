import com.nullfish.app.jfd2.dialog.DialogUtilities

current = jfd.getModel().getCurrentDirectory()
files = current.getChildren(command)

result = new StringBuffer()

for(f in files) {
	listup(f)
}

DialogUtilities.showTextAreaMessageDialog(jfd, result.toString(), false, "Œ‹‰Ê")

def listup(file) {
	if(file.isDirectory()) {
		children = file.getChildren(command)
		for(child in children) {
			listup(child)
		}
	} else {
		result.append(file.getName())
		result.append("\n")
	}
}