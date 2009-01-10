package com.nullfish.app.jfd2.util.thumbnail;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.nullfish.app.jfd2.util.NumberLimitedMap;
import com.nullfish.app.jfd2.viewer.graphicviewer.ImageComponent;
import com.nullfish.lib.ui.list_table.ListTableModel;
import com.nullfish.lib.vfs.VFile;

public class ThumbnailCache {
	private NumberLimitedMap fileThumbnailMap = new NumberLimitedMap();
	
	private List taskCueue = new ArrayList();
	
	private int thumbnailWidth = 100;
	private int thumbnailHeight = 100;
	
	private LoaderThread loader = new LoaderThread();
	
	public ThumbnailCache() {
		fileThumbnailMap.setMaxItemNumber(200);
		loader.setPriority(Thread.NORM_PRIORITY - 1);
	}
	
	private void cache(VFile file, Image image) {
		fileThumbnailMap.put(file, image);
	}
	
	public Image getThumbnail(VFile file, ListTableModel model) {
		if(file == null) {
			return null;
		}
		BufferedImage rtn = (BufferedImage)fileThumbnailMap.get(file);
		if(rtn != null) {
			return rtn;
		}
		
		try {
			rtn = ThumbnailDataBase.getInstance().getImage(file);
			if(rtn != null) {
				cache(file, rtn);
				return rtn;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		LoadingTask task = new LoadingTask(file, model);
		addTaskTop(task);
		return null;
	}

	public void loadLater(VFile file, ListTableModel model) {
		if(file == null) {
			return;
		}
		
		LoadingTask task = new LoadingTask(file, model);
		addTaskEnd(task);
	}

	/**
	 * サムネイルを作成する。
	 * @param file
	 * @return
	 */
	private BufferedImage createThumbnail(VFile file) {
		BufferedImage originalImage = null;
		InputStream is = null;
		Graphics2D g2 = null;
		try {
			is = file.getInputStream();
			originalImage = ImageIO.read(is);
			if(originalImage == null) {
				return null;
			}
			
			int width = originalImage.getWidth();
			int height = originalImage.getHeight();
			
			if(width <= 0 || height <= 0) {
				return null;
			}
			
			double widthRatio = (double)thumbnailWidth / (double)width;
			double heightRatio = (double)thumbnailHeight / (double)height;
			double ratio = widthRatio < heightRatio ? widthRatio : heightRatio;
			
			int imageType = originalImage.getType() != BufferedImage.TYPE_CUSTOM ? originalImage.getType() : BufferedImage.TYPE_INT_ARGB;
			BufferedImage rtn = new BufferedImage((int)(width * ratio), (int)(height * ratio), imageType);
			g2 = rtn.createGraphics();
			g2.setColor(Color.BLACK);
			g2.clearRect(0, 0, thumbnailWidth, thumbnailHeight);

			AffineTransformOp atOp = new AffineTransformOp(
                    AffineTransform.getScaleInstance(ratio, ratio), null);

			atOp.filter(originalImage, rtn);

			return rtn;
		} catch (Exception e) {
e.printStackTrace();
			return null;
		} catch (Throwable e) {
e.printStackTrace();
			return null;
		} finally {
			if(g2 != null) {
				g2.dispose();
			}
			try {is.close(); } catch (Exception e) {}
			if(originalImage != null) {
				originalImage.flush();
			}
		}
	}

	private JPanel imageObserver = new JPanel();
	/**
	 * サムネイルを作成する。
	 * @param file
	 * @return
	 */
	private BufferedImage createThumbnailFast(VFile file) {
		Image originalImage = null;
		InputStream is = null;
		Graphics2D g2 = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			is = file.getInputStream();
			byte[] buffer = new byte[4096];
			int l = 0;
			while((l = is.read(buffer)) != -1) {
				bos.write(buffer, 0, l);
			}
			
			originalImage = Toolkit.getDefaultToolkit().createImage(bos.toByteArray());
			MediaTracker tracker = new MediaTracker(imageObserver);
			tracker.addImage(originalImage, 0);
			try {
				tracker.waitForID(0);
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
			if(originalImage == null) {
				return null;
			}
			
			int width = originalImage.getWidth(imageObserver);
			int height = originalImage.getHeight(imageObserver);
			
			if(width <= 0 || height <= 0) {
				return null;
			}
			
			double widthRatio = (double)thumbnailWidth / (double)width;
			double heightRatio = (double)thumbnailHeight / (double)height;
			double ratio = widthRatio < heightRatio ? widthRatio : heightRatio;
			
			BufferedImage rtn = new BufferedImage((int)(width * ratio), (int)(height * ratio), BufferedImage.TYPE_INT_ARGB);
			g2 = rtn.createGraphics();
			g2.setColor(Color.BLACK);
			g2.clearRect(0, 0, thumbnailWidth, thumbnailHeight);

			g2.drawImage(originalImage, AffineTransform.getScaleInstance(ratio, ratio), null);

			return rtn;
		} catch (Exception e) {
e.printStackTrace();
			return null;
		} catch (Throwable e) {
e.printStackTrace();
			return null;
		} finally {
			if(g2 != null) {
				g2.dispose();
			}
			try {is.close(); } catch (Exception e) {}
			if(originalImage != null) {
				originalImage.flush();
			}
		}
	}

	public void setCacheSize(int size) {
		fileThumbnailMap.setMaxItemNumber(size);
	}

	public int getCacheSize() {
		return fileThumbnailMap.getMaxItemNumber();
	}
	
	private void addTaskTop(LoadingTask task) {
		synchronized (taskCueue) {
			taskCueue.remove(task);
			
			taskCueue.add(0, task);
			taskCueue.notify();
		}
	}
	
	private void addTaskEnd(LoadingTask task) {
		synchronized (taskCueue) {
			if(!taskCueue.contains(task)) {
				taskCueue.add(task);
				taskCueue.notify();
			}
		}
	}
	
	private LoadingTask getNextTask() {
		synchronized(taskCueue) {
			if(taskCueue.size() == 0) {
				return null;
			}
			
			LoadingTask rtn = (LoadingTask)taskCueue.get(0);
			taskCueue.remove(0);
			
			return rtn;
		}
	}
	
	public void start() {
		loader.start();
	}
	
	public void stop() {
		loader.stopThread();
	}
	
	public void clearTask() {
		synchronized (taskCueue) {
			taskCueue.clear();
			taskCueue.notify();
			loader.interrupt();
		}
	}
	
	public void clear() {
		fileThumbnailMap.clear();
	}
	
	private class LoaderThread extends Thread {
		private boolean running;
		
		public void run() {
			running = true;
			while(running) {
				try {
					final LoadingTask nextTask = getNextTask();
					if(nextTask == null) {
						synchronized(taskCueue) {
							taskCueue.wait();
						}
					} else {
						VFile file = nextTask.getFile();
						BufferedImage rtn = (BufferedImage)fileThumbnailMap.get(file);
						if(rtn != null) {
							continue;
						}
						
						try {
							if(ThumbnailDataBase.getInstance().contains(file)) {
								Image image = ThumbnailDataBase.getInstance().getImage(file);
								cache(file, image);
								continue;
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
						//BufferedImage thumbnail = createThumbnail(nextTask.getFile());
						String extension = nextTask.getFile().getFileName().getExtension().toLowerCase();
						BufferedImage thumbnail;
						if("jpg".equals(extension)
							|| "jpeg".equals(extension)
							|| "gif".equals(extension)
							|| "png".equals(extension)) {
							thumbnail = createThumbnailFast(nextTask.getFile());
						} else {
							thumbnail = createThumbnail(nextTask.getFile());
						}
						if(thumbnail != null) {
							cache(nextTask.getFile(), thumbnail);
							try {
								ThumbnailDataBase.getInstance().store(file, thumbnail);
							} catch (SQLException e) {
								e.printStackTrace();
							}
							Runnable runnable = new Runnable() {
								public void run() {
									nextTask.getModel().itemUpdated(nextTask.getFile());
								}
							};
							SwingUtilities.invokeLater(runnable);
						}
					}
				} catch (InterruptedException e) {}
			}
		}
		
		public void stopThread() {
			running = false;
			interrupt();
		}
	}
}
