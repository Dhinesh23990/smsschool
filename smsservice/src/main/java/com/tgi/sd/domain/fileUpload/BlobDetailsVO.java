package com.tgi.sd.domain.fileUpload;

import java.util.Date;

import com.tgi.sd.domain.fileUpload.BaseEntityVO;
import org.springframework.data.annotation.Transient;

/**
 * BlobDetailsVO is the value object class to hold the Blob details.
 * @author SGS
 */
public class BlobDetailsVO extends BaseEntityVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//@MongoFieldAnnotations(name = "directory")
    private String directory;
	
	//@MongoFieldAnnotations(name = "_id")
    private String id;
    
    private String blogid;
	
    @Transient
    private String fileName;
    @Transient
    private byte[] data;
    @Transient
	private String mimeType;
    @Transient
	private Date uploadedDate;
    @Transient
    private String ocrBlobId;
    @Transient
    private boolean ocrProcessed=false;
    
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
	 * @return the uploadedDate
	 */
	public Date getUploadedDate() {
		return uploadedDate;
	}
	
	
	public String getBlogid() {
		return blogid;
	}

	public void setBlogid(String blogid) {
		this.blogid = blogid;
	}

	/**
	 * @param uploadedDate the uploadedDate to set
	 */
	public void setUploadedDate(Date uploadedDate) {
		this.uploadedDate = uploadedDate;
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
	
	/**
	 * @return the ocrBlobId
	 */
	public String getOcrBlobId() {
		return ocrBlobId;
	}

	/**
	 * @return the ocrProcessed
	 */
	public boolean isOcrProcessed() {
		return ocrProcessed;
	}

	/**
	 * @param ocrProcessed the ocrProcessed to set
	 */
	public void setOcrProcessed(boolean ocrProcessed) {
		this.ocrProcessed = ocrProcessed;
	}

	/**
	 * @param ocrBlobId the ocrBlobId to set
	 */
	public void setOcrBlobId(String ocrBlobId) {
		this.ocrBlobId = ocrBlobId;
	}

	/**
	 * Populates all the Blob details by blob vo content
	 * @param blobVo
	 */
	public void populateBlobDetails(String blobid, BlobVO blobVo) {
		setId(blobVo.getId());
		setBlogid(blobid);
		setDirectory(blobVo.getDirectory());
		setData(blobVo.getData());
		setFileName(blobVo.getFileName());
		setMimeType(blobVo.getMimeType());
		setUploadedDate(blobVo.getUploadedDate());
		
	}
}
