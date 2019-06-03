package com.tgi.sd.domain.fileUpload;

import java.util.Date;

import javax.persistence.Column;

import com.tgi.sd.domain.fileUpload.BaseEntityVO;


/**
 * BlobVO is the value object class to hold the basic blob details.
 * @author sgs
 *
 */
public class BlobVO extends BaseEntityVO {
	
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    @Column(name="fileName")
  
	private String fileName;
    
   
    private byte[] data;
   
	private String mimeType;
	
    private String directory;	
	
	private Date uploadedDate;

	public Date getUploadedDate() {
		return uploadedDate;
	}

	public void setUploadedDate(Date uploadedDate) {

		this.uploadedDate = uploadedDate;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}


	/**
	 * @return the mimeType
	 */
	public String getMimeType() {
		return mimeType;
	}

	/**
	 * @param mimeType the mimeType to set
	 */
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	

	    	
}
