import java.io.BufferedInputStream
import org.apache.tools.zip.ZipEntry
import org.apache.tools.zip.ZipOutputStream
import com.nullfish.app.jfd2.dialog.DialogUtilities
import com.nullfish.lib.vfs.VFS

current = jfd.getModel().getCurrentDirectory()
model = jfd.getModel()
dialog = null
outDir = null
try {
	dialog = DialogUtilities.createOkCancelDialog(jfd, "å¬ï à≥èk - jFD2")
	dialog.addFileComboBoxWithHistory("dir", true, true, true, jfd, null);

	dialog.pack()
	dialog.setVisible(true)

	answer = dialog.getButtonAnswer()
	if (answer == null || "cancel".equals(answer)) {
		return;
	}
	
	outDir = dialog.getTextFieldAnswer("dir")
	if (outDir == null || "".equals(outDir)) {
		return;
	}
} finally {
	try {
		dialog.dispose()
	} catch (Exception e) {}
}

files = jfd.getModel().getMarkedFiles()
if(files == null || files.length == 0) {
	files = [jfd.getModel().getSelectedFile()]
}

for(file in files) {
	createZip(file, VFS.getInstance(jfd).getFile(outDir))
}

def createZip(file, baseDir) {
	if(file.isFile(command)) {
		return
	}

	if(file.getChildren(command).length == 0) {
		return
	}

	outFile = baseDir.getChild(file.getName() + ".zip")
	os = null
	try {
		os = new ZipOutputStream(outFile.getOutputStream())
		children = file.getChildren(command)
		
		for(child in children) {
			pack(os, child, file)
		}
	} finally {
		try {
			os.flush();
		} catch (Exception e) {
		}
		try {
			os.close();
		} catch (Exception e) {
		}
	}
}

def pack(zos, file, baseDir) {
	if(file.isDirectory(command)) {
		entry = new ZipEntry(baseDir.getRelation(file) + "/")
		zos.putNextEntry(entry)
		zos.closeEntry()

		children = file.getChildren(command)
		for(child in children) {
			pack(zos, child, baseDir)
		}
	} else {
		entry = new ZipEntry(baseDir.getRelation(file))
		zos.putNextEntry(entry)
		outputFile(zos, file)
		zos.closeEntry()
	}
}

def outputFile(os, file) {
	bis = null
	try {
		bis = new BufferedInputStream(file.getInputStream(command))
		buffer = new byte[4096]
		l = 0
		
		while(true) {
			l = bis.read(buffer)
			if (l <= 0) {
				return
			}

			os.write(buffer, 0, l)
		}
	} finally {
		try {
			bis.close()
		} catch (Exception e) {}
	}
}