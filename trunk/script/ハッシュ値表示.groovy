import com.nullfish.app.jfd2.dialog.DialogUtilities
import com.nullfish.lib.vfs.VFile
import com.nullfish.lib.vfs.manipulation.common.MD5HashManipulation

file = jfd.getModel().getSelectedFile();

if(file.isDirectory()) {
	DialogUtilities.showMessageDialog(jfd, "�t�@�C���ł͂���܂���", "jFD2")
	return
}

DialogUtilities.showTextAreaMessageDialog(jfd, jfd.getModel().getSelectedFile().getContentHashStr(command), false, "jFD2 �n�b�V���l") 
