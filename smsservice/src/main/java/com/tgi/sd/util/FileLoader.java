package com.tgi.sd.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * FileLoader intelligently loads a file from either the caller's ClassLoader,
 * current context_ classloader, or the system classloader. It makes a decision
 * depending on the parent-child relationships of the classloaders' of the
 * calling class.
 * 
 * Note 1: if class FileLoader is loaded by a class loader other then system
 * class loader, it will search the system class path, then the class loader's
 * local path for the file.
 * 
 * Note 2: it does not work with URL as file name
 * 
 * @author Selva & Parsu
 */
public final class FileLoader {

	private static Logger logger = Logger.getLogger(FileLoader.class);
	
	/**
	 * 
	 */
	private FileLoader() {
	}

	private static final String SYSPROP_SEARCH_FILESYSTEM_FIRST = FileLoader.class
			.getName() + ".FilesystemFirstMode";

	private static final boolean SEARCH_FILESYSTEM_FIRST = Boolean
			.getBoolean(SYSPROP_SEARCH_FILESYSTEM_FIRST);

	/**
	 * This class will search a file in the following order.
	 * 
	 * 1) treat the name as a good file name, search as an absolute file, or a
	 * relative path to the application
	 * 
	 * 2) if not found, search the application class path
	 * 
	 * User '/' to specify file path when use relative path
	 * 
	 * @param name
	 *            name
	 * @return File
	 * @throws FileNotFoundException
	 *             FileNotFoundException
	 * 
	 */
	@SuppressWarnings("deprecation")
	public static File getResourceAsFile(final String name)
			throws FileNotFoundException {
		URL url = null;

		if (!SEARCH_FILESYSTEM_FIRST) {
			// search thread context_ classpath
			ClassLoader loader = ClassLoader.getSystemClassLoader();
			if (loader != null) {
				url = loader.getResource(name);
			} else {
				url = ClassLoader.getSystemResource(name);
			}

			// search filesystem next
			if (url == null) {
				File f = new File(name);
				if (f.exists()) {
					try {
						url = f.toURL();
					} catch (MalformedURLException ex) {
						// f.exists() check should prevent this exception
						if(logger.isEnabledFor(Level.ERROR)){
							logger.error(ex.getMessage());
						}
						if(logger.isDebugEnabled()){
							logger.debug(ex.getMessage());	
						}
					}
				}
			}
		} else {
			// search filesystem
			File f = new File(name);
			if (f.exists()) {
				try {
					url = f.toURL();
				} catch (MalformedURLException ex) {
					// f.exists() check should prevent this exception
					if(logger.isEnabledFor(Level.ERROR)){
						logger.error(ex.getMessage());
					}
					if(logger.isDebugEnabled()){
						logger.debug(ex.getMessage());	
					}
				}
			}

			// search classpath
			if (url == null) {
				ClassLoader loader = ClassLoader.getSystemClassLoader();
				if (loader != null) {
					url = loader.getResource(name);
				} else {
					url = ClassLoader.getSystemResource(name);
				}
			}
		}

		if (url == null) {
			throw new FileNotFoundException("File " + name + " not found");
		}
		return new File(url.getFile());
	}

	/**
	 * This class will search a file from current class path.
	 * 
	 * if the file cannot be found, null is returned
	 * 
	 * @param file
	 *            file
	 * @return File
	 */
	public static File findFileInClassPath(String file) {
		URL url;

		ClassLoader loader = ClassLoader.getSystemClassLoader();
		if (loader != null) {
			url = loader.getResource(file);
		} else {
			url = ClassLoader.getSystemResource(file);
		}

		if (url == null) {
			return null;
		}
		file = url.getFile();

		File f = new File(file);
		if (f.exists()) {
			return f;
		} else {
			return null;
		}
	}

	/**
	 * This class will search a file in the following order.
	 * 
	 * 1) treat the name as a good file name, search as an absolute file, or a
	 * relative path to the application
	 * 
	 * 2) if not found, search the application class path
	 * 
	 * User '/' to specify file path when use relative path
	 * 
	 * @param name
	 *            name
	 * @return URL
	 * 
	 */
	@SuppressWarnings("deprecation")
	public static URL getResourceAsUrl(final String name) {
		URL url = null;

		if (!SEARCH_FILESYSTEM_FIRST) {
			// search thread context_ classpath
			ClassLoader loader = ClassLoader.getSystemClassLoader();
			if (loader != null) {
				url = loader.getResource(name);
			} else {
				url = ClassLoader.getSystemResource(name);
			}

			// search filesystem next
			if (url == null) {
				File f = new File(name);
				if (f.exists()) {
					try {
						url = f.toURL();
					} catch (MalformedURLException ex) {
						// f.exists() check should prevent this exception
						if(logger.isEnabledFor(Level.ERROR)){
							logger.error(ex.getMessage());
						}
						if(logger.isDebugEnabled()){
							logger.debug(ex.getMessage());	
						}
					}
				}
			}
		} else {
			// search filesystem
			File f = new File(name);
			if (f.exists()) {
				try {
					url = f.toURL();
				} catch (MalformedURLException ex) {
					// f.exists() check should prevent this exception
					if(logger.isEnabledFor(Level.ERROR)){
						logger.error(ex.getMessage());
					}
					if(logger.isDebugEnabled()){
						logger.debug(ex.getMessage());	
					}
				}
			}

			// search classpath
			if (url == null) {
				ClassLoader loader = ClassLoader.getSystemClassLoader();
				if (loader != null) {
					url = loader.getResource(name);
				} else {
					url = ClassLoader.getSystemResource(name);
				}
			}
		}

		return url;
	}

	/**
	 * This class will search a file in the following order.
	 * 
	 * 1) treat the name as a good file name, search as an absolute file, or a
	 * relative path to the application
	 * 
	 * 2) if not found, search the application class path
	 * 
	 * User '/' to specify file path when use relative path
	 * 
	 * @param name
	 *            name
	 * @return InputStream
	 * 
	 */
	public static InputStream getResourceAsInputStream(final String name) {
		InputStream is = null;

		if (!SEARCH_FILESYSTEM_FIRST) {
			// search classpath
			ClassLoader loader = ClassLoader.getSystemClassLoader();
			if (loader != null) {
				is = loader.getResourceAsStream(name);
			} else {
				is = ClassLoader.getSystemResourceAsStream(name);
			}

			// search filesystem next
			if (is == null) {
				File f = new File(name);
				if (f.exists()) {
					try {
						is = new FileInputStream(f);
					} catch (FileNotFoundException ex) {
						// f.exists() check should prevent this exception
						if(logger.isEnabledFor(Level.ERROR)){
							logger.error(ex.getMessage());
						}
						if(logger.isDebugEnabled()){
							logger.debug(ex.getMessage());	
						}
					}
				}
			}
		} else {
			// search filesystem
			File f = new File(name);
			if (f.exists()) {
				try {
					is = new FileInputStream(f);
				} catch (FileNotFoundException ex) {
					// f.exists() check should prevent this exception
					if(logger.isEnabledFor(Level.ERROR)){
						logger.error(ex.getMessage());
					}
					if(logger.isDebugEnabled()){
						logger.debug(ex.getMessage());	
					}
				}
			}

			// search classpath
			if (is == null) {
				ClassLoader loader = ClassLoader.getSystemClassLoader();
				if (loader != null) {
					is = loader.getResourceAsStream(name);
				} else {
					is = ClassLoader.getSystemResourceAsStream(name);
				}
			}
		}

		return is;
	}

	/**
	 * get the byte array for the given input stream
	 * @param in
	 * @return byte[]
	 */
	public static byte[] getBytesFromInputstream(InputStream in) {
		byte[] bytest = null;
		try {
			BufferedInputStream bis = new BufferedInputStream(in);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buffer = new byte[512];
			int i = 0;
			while ((i = bis.read(buffer)) != -1) {
				bos.write(buffer, 0, i);
			}

			bytest = bos.toByteArray();
			bis.close();
			in.close();
			bos.close();
		} catch (Exception e) {
			//throw new BusinessException("SERVICE_NOT_AVAILABLE");
		}
		return bytest;
	}

}
