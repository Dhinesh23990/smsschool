package com.tgi.sd.common.dao;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * The GenericSystemException class is the base exception class for all runtime
 * (or hard failure) related exceptions. Few exceptions are runtime exceptions;
 * most are ordinary exceptions. It is a "RuntimeException" and therefore it
 * does not have to be listed in the "throws" clause of every method that can
 * generate it. They just propagate up to the very top where we can catch them
 * and do error-reporting in a centralized way (unless a catch(Exception) in
 * between interrupts it)
 *
 *
 * @version 1.0, 10/21/2009
 * @author Sujith Vellat
 * @since 1.0.0
 */

public class GenericSystemException extends RuntimeException {

	/*The table name identifier from where the messages need to be fetched for i18n */
	private static final String TABLE_PREFIX = "EXCEPTION_";

	/*The table name identifier for prefixing the exception codes while fetching i18n messages */
	private static final String MODULE_PREFIX = "SYSTEM_";
	
	/**
	 *
	 */
	private static final long serialVersionUID = -1398963035303558113L;

	/**
	 * The error number associated with this exception
	 *
	 */
	protected String errorCode;

	/**
	 * The original exception
	 *
	 */
	protected Throwable originalException = null;

	/**
	 * The associated information for this exception
	 */
	protected Object[] params = null;

	
	protected String message;
	
	protected String messageType;
	
	protected Map<String, Object> extendedData = new HashMap<String, Object>();
	
	/**
	 * Constructor for an exception.
	 *
	 * @param errorCode
	 *            The error number for this exception
	 * @param params
	 *            An array of replacable params for the exception number
	 * @param ex
	 *            The original exception caught
	 */
	public GenericSystemException(String errorCode, Throwable ex) {
		this(errorCode, null, ex);
	}
	
	/**
	 * Constructor for an exception.
	 *
	 * @param errorCode
	 *            The error number for this exception
	 * @param params
	 *            An array of replacable params for the exception number
	 * @param ex
	 *            The original exception caught
	 */
	public GenericSystemException(String errorCode, Object[] params, Throwable ex) {
		this.errorCode = errorCode;
		originalException = ex;
		this.params = params;
//		MessageImpl messageImpl = getDetailedMessage();
//		this.message = messageImpl.getMessage();
//		this.messageType = messageImpl.getMessageType().toString();
        extendedData.put("exceptionType", "SystemException");
        extendedData.put("messageType", this.messageType);
	}

	/**
	 * Retrieve the error number associated with this exception
	 *
	 * @return integer value indicating which error this represents
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * Return the information strings associcated with this exception
	 *
	 * @return String array for this exception
	 */
	public Object[] getParams() {
		return params;
	}

	/**
	 * Retrieve the cause of the exception
	 *
	 * @return A string representing the cause of this exception.
	 */
	public Object getExceptionCause() {
		if (params == null)
			return "";
		else
			return params[0];
	}

	/**
	 * Return a string representation of the exception info
	 *
	 * @return String value for this exception
	 */
	@Override
	public String getMessage() {
		return this.message;
	}

	/**
	 * Return a string representation of the exception info
	 *
	 * @return String value for this exception
	 */
	@Override
	public String getLocalizedMessage() {
		//return I18NUtil.getMessage(errorCode);
		return null;
	}

	/**
	 * Return a string representation of the exception info
	 *
	 * @return String value for this exception
	 */
	public String getSWMessage() {
		//return I18NUtil.getMessage(errorCode, Locale.getDefault());
		return null;
	}


	/**
	 * Return the original exception associcated with this exception
	 *
	 * @return the original exception
	 */
	public Throwable getOriginalException() {
		return originalException;
	}

	/**
	 * Return a string representation of the exception
	 *
	 * @return String value for this exception
	 */
	public String toString() {
		return this.getClass().getName() + ": " + getMessage();
	}
	
	/**
	 * Return a string representation of the exception info
	 *
	 * @return String value for this exception
	 */
	public String getDetailedMessage() {
//		return I18NUtil.getDetailedMessage(new StringBuffer(TABLE_PREFIX).append(getModulePrefix()).append(errorCode).toString(), Locale.getDefault(),
//				params);
		return null;
	}	
	
	public String getModulePrefix() {
		return MODULE_PREFIX;
	}

	public Map<String, Object> getExtendedData() {
		return extendedData;
	}
}
