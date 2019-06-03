package com.tgi.sd.domain;

import java.util.ArrayList;
import java.util.List;
import com.tgi.sd.common.domain.GenericDomainObject;
import com.tgi.sd.domain.fileUpload.BlobDocumentMappingVO;


public class CategoryVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	
	private String categoryName;
	private String status;
	private String schoolId;
	private List<BlobDocumentMappingVO> categorysImage =  new ArrayList<BlobDocumentMappingVO>();
	
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public List<BlobDocumentMappingVO> getCategorysImage() {
		return categorysImage;
	}
	public void setCategorysImage(List<BlobDocumentMappingVO> categorysImage) {
		this.categorysImage = categorysImage;
	}
	

}