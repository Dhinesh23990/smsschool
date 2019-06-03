package com.tgi.sd.domain.fileUpload;

import java.util.Date;
import com.tgi.sd.common.domain.GenericDomainObject;

public class BlobDocumentMappingVO extends GenericDomainObject{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 6488439674343258021L;

	 
	 private String directory;
	 private String parentId;
	 private String blobId;
	 public Date created;
	 public Date updated;
	 
	//Added for OCR
	private boolean ocrProcessed;
	private String ocrBlobId;


	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	

	public String getBlobId() {
		return blobId;
	}

	public void setBlobId(String blobId) {
		this.blobId = blobId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
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
	 * @return the ocrBlobId
	 */
	public String getOcrBlobId() {
		return ocrBlobId;
	}

	/**
	 * @param ocrBlobId the ocrBlobId to set
	 */
	public void setOcrBlobId(String ocrBlobId) {
		this.ocrBlobId = ocrBlobId;
	}
}
