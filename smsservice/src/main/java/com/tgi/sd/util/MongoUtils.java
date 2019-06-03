/**
 *
 */
package com.tgi.sd.util;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.tgi.sd.domain.fileUpload.BaseEntityVO;

/**
 * @author SGSAuthor
 * 
 */
public class MongoUtils {


	private static final Logger LOGGER = LoggerFactory.getLogger(MongoUtils.class);
	
	/**
	 * This Method is used for Populate Audit Fileds
	 * @param visionVo
	 * @param isUpdateOnly
	 */
	public static void populateAuditFields(BaseEntityVO visionVo, boolean isUpdateOnly) {
		BeanWrapper beanWrapper = new BeanWrapperImpl(visionVo);
		String userId = StringUtils.EMPTY; ;
		try{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			userId = auth.getName();
		} catch (Throwable e) {
			LOGGER.error(e.getMessage());
		}

		if (StringUtils.isEmpty(userId)) {
			userId=PersistenceConstants.AUDIT_DEFAULT_USER;
		}
		if (!isUpdateOnly) {
			beanWrapper.setPropertyValue(PersistenceConstants.AUDIT_CREATEDBY, userId);
			beanWrapper.setPropertyValue(PersistenceConstants.AUDIT_CREATEDON, GregorianCalendar.getInstance().getTime());
			beanWrapper.setPropertyValue(PersistenceConstants.AUDIT_UPDATEDBY, userId);
			beanWrapper.setPropertyValue(PersistenceConstants.AUDIT_UPDATEDON, GregorianCalendar.getInstance().getTime());
		} 
		
		if(isUpdateOnly)
		{
			
		beanWrapper.setPropertyValue(PersistenceConstants.AUDIT_UPDATEDBY, userId);
		beanWrapper.setPropertyValue(PersistenceConstants.AUDIT_UPDATEDON, GregorianCalendar.getInstance().getTime());
		}
	}


	/**
	 * To generate a unique id.
	 * @return String
	 */
	public static String generateMongoID() {
		return UUID.randomUUID().toString();
	}
	
	/**
	 * To get the updated Field Valus
	 * @param baseEntityVO
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> getUpdatedFieldValuesAsMap(BaseEntityVO baseEntityVO){
		Map<String, Object> updateMap = new HashMap<String, Object>();

		BeanWrapper bean = new BeanWrapperImpl(baseEntityVO);
		for (String fieldName : baseEntityVO.getUpdatedFieldNames()) {
			updateMap.put(fieldName, bean.getPropertyValue(fieldName));
		}
		updateMap.put(PersistenceConstants._ID, baseEntityVO.getId());
		return updateMap;
	}
}
