package com.tgi.sd.dao.mongo;

import com.tgi.sd.domain.BaseVO;


@SuppressWarnings("rawtypes")
public interface EntityDAO {
	

	/**
	 * Generic interface to get VisionVO using its Class and Oid.
	 * 
	 * @param entity
	 *            entity
	 * @param oid
	 *            oid
	 * @return VisionVO
	 */
	BaseVO get(Class entity, String oid);

	/**
	 * Generic interface to save VisionVO.
	 * 
	 * @param entity
	 *            entity
	 * @param entityObject
	 *            entityObject
	 * @return VisionVO
	 */
	BaseVO save(Class entity, Object entityObject);
	
	/**
	 * Generic interface to save VisionVO.
	 * 
	 * @param entity
	 *            entity
	 * @param entityObject
	 *            entityObject
	 * @return VisionVO
	 */
	BaseVO update(Class entity, Object entityObject);
	
	/**
	 * Generic interface to save VisionVO.
	 * 
	 * @param entity
	 *            entity
	 * @param entityObject
	 *            entityObject
	 * @return VisionVO
	 */
	BaseVO remove(Object entityObject);



	}