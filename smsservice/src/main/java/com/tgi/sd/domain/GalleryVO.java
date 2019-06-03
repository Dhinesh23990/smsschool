package com.tgi.sd.domain;

import java.util.ArrayList;
import java.util.List;
import com.tgi.sd.common.domain.GenericDomainObject;
import com.tgi.sd.domain.fileUpload.BlobDocumentMappingVO;


public class GalleryVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	
	private String type;
	private String schoolId;
	private List<BlobDocumentMappingVO> schoolImage =  new ArrayList<BlobDocumentMappingVO>();
	
	
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<BlobDocumentMappingVO> getSchoolImage() {
		return schoolImage;
	}
	public void setSchoolImage(List<BlobDocumentMappingVO> schoolImage) {
		this.schoolImage = schoolImage;
	}

	

}