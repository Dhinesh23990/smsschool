/**
 * 
 */
package com.tgi.sd.dao;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.tgi.sd.domain.fileUpload.BlobDetailsVO;
import com.tgi.sd.domain.fileUpload.BlobVO;



/**
 * @author asai
 *
 */
public interface BlobDAO {
	
	public static final String DIRECTORY_IMAGE = "images";
	
	public static final String DIRECTORY_VIDEO = "videos";
	
	public static final String DIRECTORY = "doc_files";
	
	public static final String CERT_FILE="cert_files";
	
	public static final String RESOURCE_CENTER_FILES="resource_center_files";
	
	/**
	 * Retrieves specific blob content by directory and fileId
	 * 
	 * @param directory
	 * @param fileId
	 * @return BlobDetailsVO
	 * @throws IOException 
	 */
	BlobDetailsVO getBlob(String directory, String fileId) throws IOException;
	
	/**
	 * Retrieves specific blob content by directory and fileName
	 * 
	 * @param directory
	 * @param fileName
	 * @return BlobDetailsVO
	 * @throws IOException 
	 */
	BlobDetailsVO getBlobByName(String directory, String fileName) throws IOException;
	

	/**
	 * 
	 * Saves blob content
	 * 
	 * @param blobVO
	 * @return BlobVO
	 */
	BlobVO saveBlob(BlobVO blobVO);
	
	
	/**
	 * Deletes specific blob content by directoryName and Id
	 * 
	 * @param directory
	 * @param id
	 * @return boolean
	 */
	boolean deleteBlob(String directory, String id);

	/**
	 * Gives specific blob Updated Date by directoryName and FileId
	 * 
	 * @param directory
	 * @param fileId
	 * @return Date
	 * @throws IOException
	 */
	Date getBlobUploadedDate(String directory, String fileId)
			throws IOException;
	
	/**
	 * Gives specific blob FileName by directoryName and FileId
	 * 
	 * @param directory 
	 * @param blobId
	 * @return String
	 * @throws IOException
	 */
	
	String getFileName(String directory, String blobId) throws IOException;
	
	
	/**
	 * Gives specific File Metadata by directoryName and FileId
	 * 
	 * @param directory
	 * @param fileId
	 * @return BlobDetailsVO
	 * @throws IOException 
	 */	
	BlobDetailsVO getFileMetaData(String directory, String fileId) throws IOException;
	
	/**
	 * Retrieves specific blob content by directory and fileId
	 * 
	 * @param directory
	 * @param fileId
	 * @return BlobDetailsVO
	 * @throws IOException 
	 */
	BlobDetailsVO getBlobByFileId(String directory, String fileId) throws IOException;
	
	/**
	 * Retrieves Blob ids eligible for OCR processing by MIME type.
	 * 
	 * @param blobIds
	 * @param directory
	 * @return blobIds
	 */	
	List<String> getBlobIdsEligableForOCRProcessing(List<String> blobIds, String directory);
	
	/**
	 * Deletes all blob belongs to the specific DirctoryName
	 * 
	 * @param directory 
	 * @return boolean
	 */
	boolean deleteAllBlob(String directory);
	
	/**
	 * Deletes all blob belongs to the specific DirctoryName and FileName
	 * 
	 * @param directory 
	 * @param fileName
	 * @return boolean
	 * 
	 */
	boolean deleteBlobByName(String directory,String fileName);
	
/*	*//**
	 * Saves documents
	 * 
	 * @param docFilesVO
	 * @throws Exception 
	 *//*
	DocFilesVO saveMongoDocument(DocFilesVO docFilesVO) throws Exception;
	
	*//**
	 * Retrieves document by DirectoryName and FileId
	 * 
	 * @param directory
	 * @param fileId
	 * @throws Exception 
	 * 
	 *//*
	DocFilesVO getMongoDocument(String directory, String fileId) throws Exception;*/

	/**
	 * 
	 * Deletes Document by DirectoryName and FileId
	 * 
	 * @param directory
	 * @param fileId
	 * @throws Exception
	 * 
	 */
	void deleteMongoDocument(String directory, String fileId) throws Exception;
	
	/**
	 * Checks document details by directoryName,mongoKey,checkSum,mimeType and size
	 * 
	 * @param directory
	 * @param mongoKey
	 * @param checkSum
	 * @param mimeType
	 * @param size
	 * @return boolean
	 */
	boolean checkMongoDocComparisonStatus(String directory,String mongoKey,String checkSum,String mimeType,long size);
	
/*	*//**
	 * Saves Certification Document
	 * 
	 * @param docFilesVO
	 * @return DocFilesVO
	 * @throws Exception
	 *//*
	DocFilesVO saveCertificationDocument(DocFilesVO docFilesVO) throws Exception;
	
	*//**
	 * Retrieves Certification Document by DirectoryName and mongoKey
	 * 
	 * @param mongoKey
	 * @return DocFilesVO
	 * @throws Exception 
	 *//*
	DocFilesVO getCertificationDocument(String directoryName,String mongoKey) throws Exception;
	*/
/*	*//**
	 * Retrieves Document files by DirectoryName and mongoKeys
	 * 
	 * @param mongoKeys
	 * @return DocFilesVO
	 * @throws Exception 
	 *//*
	List<DocFilesVO> getDocFiles(String directoryName,Object[] mongoKeys) throws Exception;

	*//**
	 * Saves Thumbnail Document
	 * 
	 * @param docFilesVO
	 * @return DocFilesVO
	 * @throws Exception
	 *//*
	DocFilesVO saveThumbnailDocument(DocFilesVO docFilesVO) throws Exception;
	
	*//**
	 * Retrieves Thumbnail Document by DirectoryName and fileId
	 * 
	 * @param directory
	 * @param fileId
	 * @return DocFilesVO
	 * @throws Exception
	 *//*
	DocFilesVO getThumbnailMongoDocument(String directory, String fileId) throws Exception ;
	*/
	/**
	 * Deletes Document by DirectoryName and fileIds
	 * 
	 * @param directory
	 * @param fileId
	 * @throws Exception 
	 */
	void deleteMongoDocument(String directory, List<String> fileId) throws Exception;
}
