package com.tgi.sd.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tgi.sd.dao.BlobDAO;
import com.tgi.sd.domain.fileUpload.BlobDetailsVO;
import com.tgi.sd.domain.fileUpload.BlobDocumentMappingVO;
import com.tgi.sd.domain.fileUpload.BlobVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.CommonUtils;
import com.tgi.sd.util.SuccessResponse;

/**
 * Handles requests for the application file upload requests
 */
@Controller
public class FileUploadController {

	private static final Logger logger = LoggerFactory
			.getLogger(FileUploadController.class);

	
	private BlobDAO blobDAO;
	

	
	public BlobDAO getBlobDAO() {
		return blobDAO;
	}

	public void setBlobDAO(BlobDAO blobDAO) {
		this.blobDAO = blobDAO;
	}
	
	
	/**
	 * Upload single file using Spring Controller
	 * @throws SMSBusinessException 
	 */
	@RequestMapping(value = "/rest/fileUpload/uploadFile", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> uploadFileHandler(@RequestParam("file") MultipartFile[] file) throws SMSBusinessException {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Map<String, Object> errorMap = new HashMap<String, Object>();
		String imageType = null;//(String) request.getParameter("type");

		BlobVO blobVO=new BlobVO();
		//blobVO.setDirectory(BlobDAO.DIRECTORY_IMAGE);
		List<String> blogids=new ArrayList<String>();
		BlobDetailsVO blobDetailVO = new BlobDetailsVO();
		List<BlobDocumentMappingVO> blobDocumentList = new ArrayList<BlobDocumentMappingVO>();
		if (file.length>0) {
			
				if(StringUtils.isNotBlank(imageType)){
					//modelMap = saveImage(blobVO, imageType);
				}else {
					try {
						for(int i=0;i<file.length;i++)
						{
						blobVO.setFileName(file[i].getOriginalFilename());
						blobVO.setData(file[i].getBytes());
						String[] mimetype = StringUtils.split(blobVO.getFileName(), ".");
						if(mimetype != null && mimetype.length > 0 && mimetype[mimetype.length-1] != null){
							
							if(mimetype[1].toLowerCase().equals("png")||mimetype[1].toLowerCase().equals("jpg")||mimetype[1].toLowerCase().equals("gif")
									||mimetype[1].toLowerCase().equals("mp4")||mimetype[1].toLowerCase().equals("ogg")||mimetype[1].toLowerCase().equals("webm")	){
							blobVO.setMimeType(CommonUtils.getMimeType(mimetype[mimetype.length-1].toUpperCase()));
							
							if(blobVO.getMimeType().toLowerCase().equals("image/png")||blobVO.getMimeType().toLowerCase().equals("image/jpg")||
									blobVO.getMimeType().toLowerCase().equals("image/gif"))
								blobVO.setDirectory(BlobDAO.DIRECTORY_IMAGE);
								
							else if(blobVO.getMimeType().equals("text/xml"))
									blobVO.setDirectory(BlobDAO.DIRECTORY_VIDEO);
							
							else
								throw new SMSBusinessException(imageType);
							
 							 blobVO = saveBlob(blobVO);							
							//blogids.add(blobVO.getId());
							}
							else{
								throw new SMSBusinessException(
										"FORMAT NOT SUPPORT");
							}
						}
						blobDetailVO.populateBlobDetails(blobVO.getId(),blobVO);
						// adding blob details blobDocumentMappingVO
								
					/*	BlobDocumentMappingVO blobDocumentMappingVO	= blobDocumentMappingDAO.addBlobDetails(blobVO.getId(),
								blobVO.getDirectory(),blobVO.getMimeType());								
								
						blobDocumentList.add(blobDocumentMappingVO);*/
						
						}

						modelMap.put("blobDetailsVO", blobDocumentList);
					/*	modelMap.put("blobids", blobDetailVO.getBlogids());
						modelMap.put("name", blobDetailVO.getFileName());
						modelMap.put("directory", blobDetailVO.getDirectory());*/
					}
						catch (Exception e) {
							logger.error("You failed to upload " + blobVO.getFileName() + " => " + e.getMessage());
							
							FileUploadError errorObj=new FileUploadError();
							errorObj.setFilename(blobVO.getFileName());
							errorObj.setErrorType(e.getMessage());
							
							errorMap.put("UploadError", errorObj);
							//errorMap.put("blobDetailsVO", blobDetailVO);
							
							return errorMap;
						}
					
					
				}
				return modelMap;
			} 
		 else {
			logger.error( "You failed to upload file because the file was empty.");
		}
		return modelMap;
	}

	/**
	 * 
	 * @param blobVO
	 * @return
	 * @throws SMSBusinessException
	 */
	private BlobVO saveBlob(BlobVO blobVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("saveBlob() : start");
		}
		
		blobVO = blobDAO.saveBlob(blobVO);
		// Populate blob details from blobVO
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveBlob(): End");
		}

		return blobVO;
	}
	
	/**
	 * {@inheritDoc}.
	 */
    @RequestMapping(value = "/rest/fileUpload/downloadFile", method = RequestMethod.GET)
    public final void downloadFile(final HttpServletRequest request,
			final HttpServletResponse response) {
		BlobDetailsVO blobDetailsVO = null;
		int targetWidth = 0;
		int targetHeight = 0;
		String blobId = request.getParameter("id");
		String directory = request.getParameter("directory");
		String stringHeight = request.getParameter("targetHeight");
		String stringWidth = request.getParameter("targetWidth");
		
		try {
			//validate directory, If not exists will throw the exception for No Document Found
			//validateBlobDirectory(directory);
			//Numeric conversion for stringWidth
			if(StringUtils.isNotEmpty(stringWidth) && StringUtils.isNumeric(stringWidth)){
				targetWidth = (Integer.parseInt(stringWidth));
			}
			//Numeric conversion for stringHeight
			if(StringUtils.isNotEmpty(stringHeight) && StringUtils.isNumeric(stringHeight)){
				targetHeight = (Integer.parseInt(stringHeight));
			}
			
			if (targetHeight == 0 && targetWidth == 0) {
				blobDetailsVO = getBlob(blobDetailsVO, blobId, directory);
			} else {
				blobDetailsVO = getBlobWithDetails(blobId, directory,
						targetWidth, targetHeight);
			}
			getFileFromObject(response, blobDetailsVO);
		} catch (Throwable e) {
			logger.error("Error in downloadFile() ---> directory:::"
					+ directory + ":::id::: " + blobId,e);
		} finally {
			blobDetailsVO = null;
		}
	}

    /**
     * 
     * @param blobDetailsVO
     * @param blobId
     * @param directory
     * @return
     * @throws IOException
     * @throws SMSBusinessException
     */
	private BlobDetailsVO getBlob(BlobDetailsVO blobDetailsVO, String blobId,
			String directory) throws IOException, SMSBusinessException {
		if (StringUtils.isNotBlank(blobId)
				&& StringUtils.isNotBlank(directory)) {
			if (logger.isDebugEnabled()) {
				logger.debug("getBlob() ---> directory:::"
						+ directory + "id:::" + blobId);
			}
			blobDetailsVO = blobDAO.getBlob(directory, blobId);
			if (blobDetailsVO == null) {
				logger.error("Error in getBlob() ---> directory:::"
						+ directory + ":::id::: " + blobId);
				throw new SMSBusinessException(
						"NO_DOCUMENTS_FOUND");
			}
		}
		return blobDetailsVO;
	}
	
	/**
	 * 
	 * @param blobId
	 * @param directory
	 * @param targetWidth
	 * @param targetHeight
	 * @return
	 * @throws SMSBusinessException
	 */
	public BlobDetailsVO getBlobWithDetails(String blobId, String directory,
			int targetWidth, int targetHeight) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getBlobWithDetails() ---> directory:::" + directory
					+ "id:::" + blobId);
		}
		BlobDetailsVO blobDetailsVO = null;
		try {
			blobDetailsVO = blobDAO.getBlob(directory, blobId);
			if(blobDetailsVO != null && blobDetailsVO.getData() != null){
				blobDetailsVO = null;//resizeImage(blobDetailsVO, targetWidth, targetHeight,null);
			}
		} catch (Exception e) {
			logger.error("Error in getBlobWithDetails() --->targetWidth::"+targetWidth+" targetHeight::"+targetHeight+"::directory:::" + directory
					+ ":::id::: " + blobId + " ::: " +e);
			throw new SMSBusinessException("NO_DOCUMENTS_FOUND");
		}
		if (blobDetailsVO == null) {
			logger.error("Error in getBlobWithDetails() ---> directory:::" + directory
					+ ":::id::: " + blobId );
			throw new SMSBusinessException("NO_DOCUMENTS_FOUND");
		}
		return blobDetailsVO;
	}
	
	/**
	 * 
	 * @param blobDetailsVO
	 * @param targetWidth
	 * @param targetHeight
	 * @return BlobDetailsVO
	 * @throws VMSMSBusinessException
	 * @throws IOException
	 */
	/*private BlobDetailsVO resizeImage(BlobDetailsVO blobDetailsVO,
			int targetWidth, int targetHeight,String type) throws VMSMSBusinessException, IOException {
		BlobDetailsVO returnVO = new BlobDetailsVO();
		returnVO.setFileName(blobDetailsVO.getFileName());
		returnVO.setId(blobDetailsVO.getId());
		returnVO.setDirectory(blobDetailsVO.getDirectory());
		returnVO.setMimeType(blobDetailsVO.getMimeType());
		returnVO.setData(blobDetailsVO.getData());
		byte[] file = blobDetailsVO.getData();
		int orgWidth = 0;
		int orgHeight = 0;
		if (file != null) {
			int[] imageWidthHeight = null;
			try{
				imageWidthHeight = ImageHelper.getSize(file);
			}catch(Throwable e){
				logger.error("Error while get image size", e);
				throw new VMSMSBusinessException(CommonErrorConstants.UNSUPPORTED_IMAGE_CONTENT);
			}
			
			orgWidth = imageWidthHeight[0];
			orgHeight = imageWidthHeight[1];
		}else{
			logger.error("Error while get image size");
		}
		ByteArrayInputStream in = new ByteArrayInputStream(file);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		BufferedImage bi = ImageIO.read(in);
		BufferedImage resizeImage = null;
		String[] mimetype = StringUtils.split(returnVO.getFileName(), ".");
		String fileMimetype = "";
		if (mimetype != null && mimetype.length > 0
				&& mimetype[mimetype.length - 1] != null) {
			fileMimetype = mimetype[mimetype.length - 1].toUpperCase();
		}
		
		// check for transparency image
		if (StringUtils.equalsIgnoreCase(type, Constants.IMAGE_EQUAL_HEIGHT_WIDTH)) {
			if (orgHeight <= targetHeight && orgWidth <= targetWidth) {
				return returnVO;
			} else if (orgHeight <= targetHeight && orgWidth >= targetWidth) {
				resizeImage = Scalr.resize(bi, targetHeight);
			} else if (orgHeight >= targetHeight && orgWidth <= targetWidth) {
				resizeImage = Scalr.resize(bi, targetHeight);
			} else {
				resizeImage = Scalr.resize(bi, targetHeight);
			}
		} else if (targetHeight > 0 && targetWidth > 0
				&& (orgHeight >= targetHeight || orgWidth >= targetWidth)
				&& bi.getColorModel().hasAlpha()) {
			resizeImage = Scalr.resize(bi, targetWidth);
		} else if (targetHeight > 0 && targetWidth > 0
				&& (orgHeight >= targetHeight || orgWidth >= targetWidth)) {
			resizeImage = Scalr.resize(bi, Scalr.Method.QUALITY, Scalr.Mode.AUTOMATIC,
					targetWidth, targetHeight, Scalr.OP_ANTIALIAS);
		} else if (targetWidth > 0 && orgWidth >= targetWidth) {
			resizeImage = Scalr.resize(bi, targetWidth);
		} else {
			return returnVO;
		}

		// write buffered image out as a byte array with a jpeg mime type
		ImageIO.write(resizeImage, fileMimetype, out);
		// write buffered image out as a byte array with a jpeg mime type
		// ImageIO.write(bi, "jpeg", out);
		byte[] thumbnailImageContent = out.toByteArray();
		returnVO.setData(thumbnailImageContent);

		return returnVO;
	}*/

	/**
	 * Upload multiple file using Spring Controller
	 */
	@RequestMapping(value = "/services/rest/unsecure/uploadMultipleFile", method = RequestMethod.POST)
	public @ResponseBody
	String uploadMultipleFileHandler(@RequestParam("name") String[] names,
			@RequestParam("file") MultipartFile[] files) {

		if (files.length != names.length)
			return "Mandatory information missing";

		String message = "";
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			String name = names[i];
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location="
						+ serverFile.getAbsolutePath());

				message = message + "You successfully uploaded file=" + name;
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}
	
	 /**
		 * Getting File Content from DocFilesVo 
		 * @param response
		 * @param docFilesVO
		 * @throws IOException
		 */
		private void getFileFromObject(final HttpServletResponse response,BlobDetailsVO blobDetailsVO) throws IOException {
			response.reset();
			response.setContentType(blobDetailsVO.getMimeType());
			response.setHeader("Content-Disposition","inline; filename=\"" + blobDetailsVO.getFileName() + "\"");
			response.setContentLength(blobDetailsVO.getData().length);
			response.getOutputStream().write(blobDetailsVO.getData());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
		
		//remove single image by sikk
		@RequestMapping(value = "/services/rest/unsecure/removesingle", method = RequestMethod.POST)
		public @ResponseBody SuccessResponse removeSingleImage(@RequestParam("blobId") String blobId) throws SMSBusinessException {
			
			if (logger.isDebugEnabled()) {
				logger.debug("Start Removing Single Image" + blobId);
			}
			//BlobDocumentMappingVO blobDocumentMappingVO	= blobDocumentMappingDAO.getBlobDetailsById(blobId);
			
//			if(blobDocumentMappingVO==null){
//				throw new SMSBusinessException("BLOB INFORMATION NOT FOUND");
//			}
			//image delete
			//blobDAO.deleteBlob(blobDocumentMappingVO.getDirectory(),blobDocumentMappingVO.getBlobId());
			
			//List<String> oldBlobIds=new ArrayList<String>();
			
			//blob delete
			//blobDocumentMappingDAO.removeBlobDetails(blobDocumentMappingVO.getBlobId(),blobDocumentMappingVO.getDirectory());
			
			SuccessResponse successMessge=new SuccessResponse();
			
			successMessge.setStatus("DELETED");
			
			if (logger.isDebugEnabled()) {
				logger.debug("End Removing Single Image" + blobId);
			}
			return successMessge;
			
		}
}