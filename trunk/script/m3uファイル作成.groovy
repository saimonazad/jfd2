import java.io.BufferedWriter
import java.io.OutputStreamWriter
import com.nullfish.lib.vfs.FileUtil
import com.nullfish.lib.vfs.Manipulation
import com.nullfish.app.jfd2.dialog.DialogUtilities

writer = null
try{
	markedFiles = jfd.getModel().getMarkedFiles()
	if(markedFiles == null || markedFiles.size() == 0) {
		DialogUtilities.showMessageDialog(jfd, "ファイルを選択してください。", "jFD2");
		return
	}

	dialog = DialogUtilities.createOkCancelDialog(jfd, "jFD2")
	dialog.addMessage('m3uファイル名を入力してください')
	dialog.addTextField("file", "", true)
	dialog.pack()
	dialog.setVisible(true)

	button = dialog.getButtonAnswer()
	fileName = dialog.getTextFieldAnswer("file")
	if(button == "cancel" || button == null || fileName.length() == 0) {
		return
	}

	if(!fileName.endsWith(".m3u")) {
		fileName = fileName + ".m3u"
	}

	current = jfd.getModel().getCurrentDirectory()
	m3uFile = current.getChild(fileName)

	writer = new BufferedWriter(new OutputStreamWriter(m3uFile.getOutputStream()))
	writer.write("#EXTM3U\n")

	for(f in markedFiles) {
		writer.write(f.getAbsolutePath())
		writer.write("\n")
	}
} catch (Exception e) {
	e.printStackTrace()
} finally {
	try {
		writer.flush()
		writer.close();
	} catch (Exception e) {}
	dialog.dispose()
}
