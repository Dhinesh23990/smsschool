package com.tgi.sd.util;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Utilities for common use.
 *
 * @author Selva & Parsu
 */
@SuppressWarnings({"unused","rawtypes","unchecked"})
public class CommonUtils {

    private static Log log = LogFactory.getLog(CommonUtils.class);

    public static final String VALID_EMAIL_REGEXP = "[-a-zA-Z0-9!#$%&'*+/=?^_'{|}~]+(?:\\.[-a-zA-Z0-9!#$%&'*+/=?^_'{|}~]+)*@([a-zA-Z0-9](?:[-a-zA-Z0-9]*[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[-a-zA-Z0-9]*[a-zA-Z0-9])?)*(?:\\.[a-zA-Z0-9]{2,}+)|\\[(?:\\d{1,3}(?:\\.\\d{1,3}){3}|IPv6:[0-9A-Fa-f:]{4,39})\\])";

    public static final String LIVEDEMO_LABEL = "livedemo";

	public static final String ENV_PRODUCTION_STR = "config/env";

	public static final String ENV_LIVEDEMO_STR = "config/env-livedemo";

	public static final String ENV_INCLUDE_STR = "include.env";
	
	public static final String ENV_INFRA_PRODUCTION_STR = "config/env-infra";
	
	public static final String ENV_INFRA_LIVEDEMO_STR = "config/env-infra-livedemo";

	public static final String ENV_INFRA_INCLUDE_STR = "include.infra";
	
    /**
     * Create a new common utils.
     */
    private CommonUtils() {
    }

    // ---------------------------------------------
    // Check a string
    // ---------------------------------------------

    /**
     * Check to see if the string is empty.
     * @param s string to be checked
     * @return true if empty, otherwise false
     */
    public static boolean isEmpty(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Check to see if the string is not empty.
     * @param s string to be checked
     * @return true if not empty, otherwise false
     */
    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

    /**
     * Check to see if the trimed string is empty.
     * @param s string to be checked
     * @return true if empty, otherwise false
     */
    public static boolean isEmptyTrim(String s) {
        if (s == null || s.trim().length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Check to see if the trimed string is not empty.
     * @param s string to be checked
     * @return true if not empty, otherwise false
     */
    public static boolean isNotEmptyTrim(String s) {
        return !isEmptyTrim(s);
    }

    /**
     * Check to see if the value is numeric
     *
     * @param value value
     * @return true:numeric, false:not numeric
     */
    public static boolean isNumeric(String value) {
        boolean result = false;

        if (value != null && value.trim().length() > 0) {
            try {
                Double.parseDouble(value);
                result = true;
            } catch (NumberFormatException e) { }
        }
        return result;
    }

   
	private static String replaceString(String str, String oldStr, String newStr) {
        String ret = str;
        if (str == null || oldStr == null || newStr == null) {
            return ret;
        } //end if str

        try {
            StringBuffer buf = new StringBuffer(str);
            int index  = 0;
            int oldLen = oldStr.length();
            int newLen = newStr.length();

            while ((index = str.indexOf(oldStr, index)) >= 0) {
                buf.replace(index, index + oldLen, newStr);
                index += newLen;
                str = buf.toString();
            } //end while index
            ret = buf.toString();
        } catch (Exception e) { }

        return ret;
    } //end replaceString

    // ---------------------------------------------
    // Resource file
    // ---------------------------------------------

    /**
     * Return a value of the key from the properties file.
     * @param path path of the properties file
     * @param key key name
     * @return a value of the key
     */
    public static String getResourceValue(String path, String key) {
        String value = null;
        if (CommonUtils.isEmptyTrim(path)) {
            return value;
        }

        ResourceBundle bundle = null;
        try {
            bundle = ResourceBundle.getBundle(path);
        } catch (Exception e) { }

        try {
            if (bundle != null) {
                value = bundle.getString(key);
            }
        } catch (Exception e) {
            //log.error("Failed to read properties file ("+path+"). key=("+key+")", e);
        }

        return value;
    }

    /**
     * Return a set of key/values from the properties file.
     * @param path path of the properties file
     * @return a set of key/values
     */
    public static Map createResourceData(String path) {
        return createResourceData(path, null);
    }

    /**
     * Return a set of key/values from the properties file.
     * But only keys that starts with the prefix are accepted.
     * @param path path of the properties file
     * @param prefix prefix of key
     * @return a set of key/values
     */
    public static Map createResourceData(String path, String prefix) {
        Map data = new HashMap();
        if (CommonUtils.isEmptyTrim(path)) {
            return data;
        }

        ResourceBundle bundle = null;
        try {
            bundle = ResourceBundle.getBundle(path);
        } catch (Exception e) { }

        try {
            if (bundle != null) {
                for (Enumeration e = bundle.getKeys(); e.hasMoreElements();) {
                    String key = (String) e.nextElement();
                    String value = bundle.getString(key);

                    if (key != null && value != null) {
                        if (CommonUtils.isEmpty(prefix) || key.startsWith(prefix)) {
                            if (CommonUtils.isNotEmpty(prefix)) {
                                // If prifix is specified, remove it from key
                                key = key.substring(prefix.length());
                            }
                            data.put(key, value);
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("Failed to read properties file (" + path + "). " + "prefix=(" + prefix + ")", e);
        }
        return data;
    }

    // ---------------------------------------------
    // File Operations
    // ---------------------------------------------

    /**
     * Returns the time that the file denoted by this abstract pathname
     * was last modified.
     * @param path path of the directory or file
     * @return the time that it was last modified
     */
    public static long getFileLastModified(String path) {
        if (isEmpty(path)) {
            return -1;
        }
        return getFileLastModified(new File(path));
    }

    /**
     * Returns the time that the file denoted by this abstract pathname
     * was last modified.
     * @param file file
     * @return the time that it was last modified
     */
    public static long getFileLastModified(File file) {
        if (file == null) {
            return -1;
        }

        long lastModifiedTime = -1;

        if (file.exists()) {
            lastModifiedTime = file.lastModified();
        }

        return lastModifiedTime;
    }

    /**
     * Checks to see if absolute path.
     * @param path    file path
     * @return true if absplute path, otherwise false
     */
    public static boolean isAbsolutePath(String path) {
        if (isEmpty(path)) {
            return false;
        }

        boolean result = false;
        if (path.startsWith("/") || path.startsWith("\\") ||
            (path.length() > 2 && path.charAt(1) == ':')) {
            result = true;
        }
        return result;
    }

    /**
     * Must be valid according to RFC 2821 and 2822.
     * @param em - candidate email address
     * @return true for well formed or false for format error
     */
    public static boolean isValidEmailFormat(String em) {
        em = org.apache.commons.lang.StringUtils.trimToEmpty(em);
        if (org.apache.commons.lang.StringUtils.isBlank(em)) {
            return false;
        }
        return em.matches(VALID_EMAIL_REGEXP);
    }

    /**
     * 
     * @param exportType
     * @return String
     */
    public static String getMimeType(String exportType) {

    	if (log.isDebugEnabled()) {
    		log.debug("get the mimeType for this exportType : " + exportType);
    	}

    	String mimeType = "";
        if ("pdf".equalsIgnoreCase(exportType)) {
            mimeType = "text/pdf";
        } else if ("xls".equalsIgnoreCase(exportType)) {
            mimeType ="text/xls";
        } else if ("csv".equalsIgnoreCase(exportType)) {
            mimeType ="text/csv";
        } else if ("html".equalsIgnoreCase(exportType)) {
            mimeType ="text/html";
        } else if ("rtf".equalsIgnoreCase(exportType)) {
            mimeType ="text/rtf";
        } else if ("xlsx".equalsIgnoreCase(exportType)) {
            mimeType ="text/xlsx";
        } else if ("doc".equalsIgnoreCase(exportType)) {
            mimeType ="text/doc";
        } else if ("docx".equalsIgnoreCase(exportType)) {
            mimeType ="text/docx";
        } else if ("txt".equalsIgnoreCase(exportType)) {
            mimeType ="application/text";
        } else if ("jpg".equalsIgnoreCase(exportType)) {
            mimeType ="image/jpg";
        } else if ("gif".equalsIgnoreCase(exportType)) {
            mimeType ="image/gif";
        } else if ("jpeg".equalsIgnoreCase(exportType)) {
            mimeType ="image/jpeg";
        } else if ("jrxml".equalsIgnoreCase(exportType)) {
            mimeType ="text/jrxml";
        } else if ("png".equalsIgnoreCase(exportType)) {
            mimeType ="image/png";
        } else if ("tif".equalsIgnoreCase(exportType)) {
            mimeType ="image/tif";
        } else if ("bmp".equalsIgnoreCase(exportType)) {
            mimeType ="image/bmp";
        }else if ("json".equalsIgnoreCase(exportType)) {
            mimeType ="text/json";
        } else {
            //#US486
            mimeType ="text/xml";
        }
        if (log.isDebugEnabled()) {
        	log.debug("mimeType : " + mimeType);
        }
        return mimeType;
    }

    /**
     * 
     * @param mimeType
     * @return String
     */
	/*public static String getFileExtension(String mimeType) {

		if (log.isDebugEnabled()) {
			log.debug("get the fileExtension for this mimeType : " + mimeType);
		}
		String fileExt = null;
		if (mimeType != null
				&& mimeType
						.equalsIgnoreCase(VisionDTO.CONTENT_TYPE_PDF)) {
			fileExt = Constants.REPORT_TYPE_PDF;
		} else if (mimeType != null
				&& mimeType
						.equalsIgnoreCase(VisionDTO.CONTENT_TYPE_XLS)) {
			fileExt = Constants.REPORT_TYPE_XLS;
		} else if (mimeType != null
				&& mimeType
				.equalsIgnoreCase(VisionDTO.CONTENT_TYPE_XLSX)) {
			fileExt = Constants.REPORT_TYPE_XLSX;
		} else if (mimeType != null
				&& mimeType
				.equalsIgnoreCase(VisionDTO.CONTENT_TYPE_DOC)) {
			fileExt = Constants.REPORT_TYPE_DOC;
		} else if (mimeType != null
				&& mimeType
				.equalsIgnoreCase(VisionDTO.CONTENT_TYPE_DOCX)) {
			fileExt = Constants.REPORT_TYPE_DOCX;
		} else if (mimeType != null
				&& mimeType
						.equalsIgnoreCase(VisionDTO.CONTENT_TYPE_IMG_JPG)) {
			fileExt = Constants.REPORT_TYPE_IMG_JPG;
		} else if (mimeType != null
				&& mimeType
						.equalsIgnoreCase(VisionDTO.CONTENT_TYPE_IMG_GIF)) {
			fileExt = Constants.REPORT_TYPE_IMG_GIF;
		} else if (mimeType != null
				&& mimeType
						.equalsIgnoreCase(VisionDTO.CONTENT_TYPE_TEXT)) {
			fileExt = Constants.REPORT_TYPE_TEXT;
		} else if (mimeType != null 
				&& mimeType.equalsIgnoreCase(VisionDTO.CONTENT_TYPE_IMG_PNG)) {
			fileExt = Constants.REPORT_TYPE_IMG_PNG;
		}
		if (log.isDebugEnabled()) {
			log.debug("fileExt : " + fileExt);
		}
		return fileExt;
	}
*/
//    // ---------------------------------------------
//    // Base64
//    // ---------------------------------------------
//
//    private static final BASE64Encoder BASE64ENC = new BASE64Encoder();
//    private static final BASE64Decoder BASE64DEC = new BASE64Decoder();

    /**
     * Encodes with base64.
     * @param source    string to be encoded
     * @return encoded string
     */
//    public static String encodeBase64(String source) throws Exception {
//        String s = BASE64ENC.encode(source.getBytes());
//
//        // Delete newlines
//        s = replaceString(s, "\r", "");
//        return replaceString(s, "\n", "");
//    }

    /**
     * Decodes with base64.
     * @param source    string to be decoded
     * @return decoded string
     */
//    public static String decodeBase64(String source) throws Exception {
//        return new String(BASE64DEC.decodeBuffer(source));
//    }

 

    /**
     * Create a class from the class type string.
     * @param type class path
     * @return the class
     */
    public static Class createClass(String type) throws Exception {
        Class classType = null;
        if (type != null) {
            if ("boolean".equals(type)) {
                classType = Boolean.TYPE;
            } else if ("byte".equals(type)) {
                classType = Byte.TYPE;
            } else if ("char".equals(type)) {
                classType = Character.TYPE;
            } else if ("short".equals(type)) {
                classType = Short.TYPE;
            } else if ("int".equals(type)) {
                classType = Integer.TYPE;
            } else if ("long".equals(type)) {
                classType = Long.TYPE;
            } else if ("float".equals(type)) {
                classType = Float.TYPE;
            } else if ("double".equals(type)) {
                classType = Double.TYPE;
            } else {
               // classType = ObjectFactory.getInstance().createClass(type);
            }
        }
        return classType;
    }
    
//    /**
//     * This method is used to find the request content type is XML or JSON.
//	 * @param content
//	 * @return
//     * @throws VMBusinessException 
//	 */
//    public static String getRequestContentType(String content) throws VMApplicationException{
//    	String requestContentType = "";
//    	if(content != null && !content.isEmpty()){
//    		try {
//    			 DOMParser parser = new DOMParser();
//    			 parser.parse(new InputSource(new StringReader(content)));
//    			 requestContentType =  Constants.XML;
//    		} catch (Exception e) {
//    			try {
//    				JSONObject object = new JSONObject(content);
//    				requestContentType = Constants.JSON;
//    			} catch (Exception e2) {
//    				throw new VMApplicationException(CommonErrorConstants.INVALID_VISION_REQUEST);
//    			}
//    		}
//    	}
//    	return requestContentType;
//    }
//    
    
    
    
    
//    public static String getContentType(String content) throws VMApplicationException{
//    	String requestContentType = "";
//    	if(content != null && !content.isEmpty()){
//    		try {
//    			 DOMParser parser = new DOMParser();
//    			 parser.parse(new InputSource(new StringReader(content)));
//    			 requestContentType =  Constants.XML;
//    		} catch (Exception e) {
//    			try {
//    				JSONObject object = new JSONObject(content);
//    				requestContentType = Constants.JSON;
//    			} catch (Exception e2) {
//     				throw new VMApplicationException(CommonErrorConstants.INVALID_VISION_REQUEST);
//    			}
//    		}
//    	}
//    	return requestContentType;
//    }
    
    
    /*public static String getContentType(String content) throws VMApplicationException{
    	
    	return validateAndGetContentType(content);
    	
    }*/
   
}
