/*
 * package com.tgi.sd.dao.mongo;
 * 
 * import java.lang.reflect.Field; import java.util.ArrayList; import
 * java.util.Date; import java.util.GregorianCalendar; import java.util.HashMap;
 * import java.util.List; import java.util.Map; import java.util.Set; import
 * java.util.UUID; import org.apache.commons.lang.ClassUtils; import
 * org.apache.log4j.Level; import org.apache.log4j.Logger; import
 * org.springframework.beans.BeanWrapper; import
 * org.springframework.beans.BeanWrapperImpl; import
 * org.springframework.data.annotation.Transient; import
 * com.tgi.sd.domain.AuditFields; import com.tgi.sd.exception.ErrorConstants;
 * import com.tgi.sd.exception.SMSBusinessException;
 * 
 * 
 * public class SMSMongoDAOSupport extends MongoDAOSupport implements
 * MongoEntityDAO{
 * 
 * private static Logger logger = Logger.getLogger(SMSMongoDAOSupport.class);
 * 
 * @Override public AuditFields get(Class entity, String oid) { //return
 * findById(entity,oid); AuditFields vo = null; try { vo = findById(entity,oid);
 * } catch (SMSBusinessException e) { } return vo;
 * 
 * }
 * 
 * @Override public AuditFields save(Class entity, Object entityObject) { try {
 * insert((AuditFields) entityObject); } catch (SMSBusinessException e) { }
 * return (AuditFields) entityObject; }
 * 
 * 
 * @Override public AuditFields update(Class entity, Object entityObject) { try
 * { update((AuditFields) entityObject); } catch (SMSBusinessException e) { }
 * return get(entity,((AuditFields)entityObject).getId()); }
 * 
 * @Override public AuditFields remove(Object entityObject) {
 * 
 * getMongoTemplate().remove(entityObject);
 * 
 * return (AuditFields)entityObject; }
 * 
 * @Override public void removeByQuery(Query query, Class<?> entityClass) throws
 * SMSBusinessException {
 * 
 * //Remove all the original info for the given entity try {
 * getMongoTemplate().remove(query, entityClass); } catch (Exception e) { throw
 * new SMSBusinessException(e.getMessage()); }
 * 
 * }
 * 
 * @Override public AuditFields findById(Class<?> entityClass, String oid)
 * throws SMSBusinessException { Object findOne = null; try {
 * 
 * findOne = getMongoTemplate().findById(oid, entityClass);
 * 
 * } catch (Exception e) { throw new
 * SMSBusinessException(ErrorConstants.DB_CONNECTION_ERROR); } return
 * (AuditFields) findOne;
 * 
 * }
 * 
 * @Override public List<? extends AuditFields> findAllByPagination(Class<?>
 * entityClass, int pageNo, int sizeLimit) throws SMSBusinessException { try {
 * Query query = new Query(); query.skip(pageNo * sizeLimit);
 * query.limit(sizeLimit);
 * 
 * 
 * return (List<? extends AuditFields>)
 * getMongoTemplate().find(query,entityClass); } catch (Exception e) { throw new
 * SMSBusinessException(e.getMessage()); } }
 * 
 * public List<? extends AuditFields> findAllByQueryPagination(Query
 * query,Class<?> entityClass, int pageNo, int sizeLimit) throws
 * SMSBusinessException { try { query.skip(pageNo * sizeLimit);
 * query.limit(sizeLimit);
 * 
 * return (List<? extends AuditFields>)
 * getMongoTemplate().find(query,entityClass); } catch (Exception e) { throw new
 * SMSBusinessException(ErrorConstants.DB_CONNECTION_ERROR); } }
 * 
 * 
 * @Override public List<? extends AuditFields> findAll(Class<?> entityClass)
 * throws SMSBusinessException { try { return (List<? extends AuditFields>)
 * getMongoTemplate().findAll(entityClass); } catch (Exception e) { throw new
 * SMSBusinessException(e.getMessage()); } }
 * 
 * @Override public List<? extends AuditFields> findByQuery(Query query,
 * Class<?> entityClass) throws SMSBusinessException { try { return (List<?
 * extends AuditFields>) getMongoTemplate().find(query,entityClass); } catch
 * (Exception e) { throw new
 * SMSBusinessException(ErrorConstants.DB_CONNECTION_ERROR); } }
 * 
 * @Override public DBCursor findByDBObject(BasicDBObject basicDBObject,
 * Class<?> entityClass,int limitsize) throws SMSBusinessException { try {
 * return (DBCursor)getMongoTemplate().getCollection(getMongoTemplate().
 * getCollectionName(entityClass)).find(basicDBObject).limit(limitsize); } catch
 * (Exception e) { throw new SMSBusinessException(e.getMessage()); } }
 * 
 * @Override public DBCursor findByDBObject(BasicDBObject basicDBObject,
 * Class<?> entityClass) throws SMSBusinessException { try { return
 * (DBCursor)getMongoTemplate().getCollection(getMongoTemplate().
 * getCollectionName(entityClass)).find(basicDBObject); } catch (Exception e) {
 * throw new SMSBusinessException(e.getMessage()); } }
 * 
 * @Override public AuditFields findOneByQuery(Query query, Class<?>
 * entityClass) throws SMSBusinessException { try { return (AuditFields)
 * getMongoTemplate().findOne(query, entityClass); } catch (Exception e) { throw
 * new SMSBusinessException(ErrorConstants.DB_CONNECTION_ERROR); } }
 * 
 * @SuppressWarnings("rawtypes")
 * 
 * @Override public Map findOneByQuery(Query query,String collectionName) throws
 * SMSBusinessException { try { return getMongoTemplate().findOne(query,
 * HashMap.class, collectionName); } catch (Exception e) { throw new
 * SMSBusinessException(e.getMessage()); } }
 * 
 * 
 * @SuppressWarnings("rawtypes")
 * 
 * @Override public List<HashMap> findByQuery(Query query, String
 * collectionName) throws SMSBusinessException { try { return
 * getMongoTemplate().find(query,HashMap.class,collectionName); } catch
 * (Exception e) { throw new SMSBusinessException(e.getMessage()); } }
 * 
 * @Override public void insert(AuditFields baseVo) throws SMSBusinessException
 * {
 * 
 * try { // TODO we need to populate the created & updated fields.
 * populateAuditFields(baseVo, false); getMongoTemplate().insert(baseVo); }
 * catch(DuplicateKeyException e){ logger.error("Error in Insert: ", e); throw
 * new SMSBusinessException(ErrorConstants.DUPLICATE_KEY_ERROR); } catch
 * (Exception e) { logger.error("Error in Insert: ", e); throw new
 * SMSBusinessException(ErrorConstants.DB_CONNECTION_ERROR); }
 * 
 * }
 * 
 * @Override public void insert(AuditFields baseVo, boolean populateAuditFields)
 * throws SMSBusinessException { try { if (populateAuditFields) { // TODO we
 * need to populate the created & updated fields. populateAuditFields(baseVo,
 * false); } getMongoTemplate().insert(baseVo); } catch (Exception e) {
 * logger.error("Error in Insert: ", e); throw new
 * SMSBusinessException(e.getMessage()); } }
 * 
 * 
 * @SuppressWarnings("rawtypes")
 * 
 * @Override public void insert(Map reportData, String reportCollection) throws
 * SMSBusinessException { try { reportData.put("_id",
 * UUID.randomUUID().toString()); getMongoTemplate().insert(reportData,
 * reportCollection); } catch (Exception e) {
 * logger.error("Error in Insert: ",e); throw new
 * SMSBusinessException(e.getMessage()); } }
 * 
 * @Override public void update(AuditFields baseVo) throws SMSBusinessException
 * { try { // TODO we need to populate the updated fields.
 * populateAuditFields(baseVo, true); getMongoTemplate().save(baseVo); }
 * catch(DuplicateKeyException e){ logger.error("Error in Insert: ", e); throw
 * new SMSBusinessException(ErrorConstants.DUPLICATE_KEY_ERROR); } catch
 * (Exception e) { logger.error("Error in Insert: ", e); throw new
 * SMSBusinessException(ErrorConstants.DB_CONNECTION_ERROR); } }
 * 
 * 
 * @Override public void updateDocument(AuditFields visionVO) throws
 * SMSBusinessException { updateDocument(visionVO,true); }
 * 
 * @Override public void updateDocument(AuditFields visionVO, boolean
 * populateAuditFields) throws SMSBusinessException { try {
 * 
 * BeanWrapper beanWrapper = new BeanWrapperImpl(visionVO);
 * 
 * String objectId = (String) beanWrapper.getPropertyValue("id"); Query query =
 * new Query(); query.addCriteria(Criteria.where("id").is(objectId));
 * 
 * AuditFields oldEntity = findById(visionVO.getClass(), objectId); // if
 * (visionVO.getOldEntity() == null) { // visionVO.setOldEntity(oldEntity); // }
 * 
 * Update update = populateUpdatedFields(visionVO, beanWrapper);
 * 
 * // TODO we need to populate the updated fields. if
 * (update.getUpdateObject().toMap() != null &&
 * update.getUpdateObject().toMap().size() > 0) { if (populateAuditFields) {
 * populateAuditFields(update); } if (logger.isDebugEnabled()) {
 * logger.debug("Doc Id : " + objectId + " : Data to update :" +
 * update.getUpdateObject().toMap()); } WriteResult wr =
 * getMongoTemplate().updateFirst(query, update, visionVO.getClass());
 * parseError(wr); } } catch (Throwable e) {
 * logger.error("Error in updateDocument : ", e); throw new
 * SMSBusinessException(e.getMessage()); } }
 * 
 * @Override public long getCountOfOccurences(Query query, Class<?> entityClass)
 * throws SMSBusinessException { try { return getMongoTemplate().count(query,
 * entityClass); } catch (Throwable e) { throw new
 * SMSBusinessException(ErrorConstants.DB_CONNECTION_ERROR); }
 * 
 * }
 * 
 * @Override public long getCountOfOccurences(Query query, String
 * collectionName) throws SMSBusinessException { try { return
 * getMongoTemplate().count(query, collectionName); } catch (Throwable e) {
 * throw new SMSBusinessException(e.getMessage()); }
 * 
 * }
 * 
 * public GridFS getGridFS(String directory) {
 * //getMongoTemplate().getDb().requestStart();
 * //getMongoTemplate().getDb().requestEnsureConnection(); return new
 * GridFS(getMongoTemplate().getDb(), directory); }
 * 
 * public void closeGridFS(GridFS gridFS) { // if (null != gridFS) //
 * gridFS.getDB().requestDone(); }
 * 
 * @SuppressWarnings("rawtypes")
 * 
 * @Override public List getUniqueField(Class<?> className,String fieldName,
 * Query query) { String collectionName =
 * getMongoTemplate().getCollectionName(className); DBCollection collection =
 * getMongoTemplate().getCollection(collectionName); List uniqueFieldList =
 * collection.distinct(fieldName, query.getQueryObject()); return
 * uniqueFieldList; }
 * 
 * 
 * @Override public void updateDocument(Map<String, ?> updatedFields, Query
 * query, Class<?> entityClass) throws SMSBusinessException { Update update =
 * populateUpdatedFields(updatedFields); getMongoTemplate().updateFirst(query,
 * update, entityClass);
 * 
 * }
 * 
 * // @Override public void updateDocument(Map<String, ?> updatedFields, Query
 * query, Class<?> entityClass, boolean populateAuditFields) throws
 * SMSBusinessException { Update update = populateUpdatedFields(updatedFields);
 * 
 * if (populateAuditFields) { populateAuditFields(update); }
 * getMongoTemplate().updateFirst(query, update, entityClass);
 * 
 * }
 * 
 * @Override public void updateMultiDocument(Map<String, ?> updatedFields, Query
 * query, Class<?> entityClass, boolean populateAuditFields) throws
 * SMSBusinessException { Update update = populateUpdatedFields(updatedFields);
 * 
 * if (populateAuditFields) { populateAuditFields(update); }
 * getMongoTemplate().updateMulti(query, update, entityClass); }
 * 
 * @Override public void saveAllDocument(List<? extends AuditFields> documents,
 * boolean populateAuditFields) throws SMSBusinessException { if
 * (populateAuditFields) { for (AuditFields baseVo : documents) {
 * populateAuditFields(baseVo, false); } }
 * getMongoTemplate().insertAll(documents); }
 * 
 * @Override public void updateDBDoc(Map<String, ?> updatedFields, Query query,
 * Class<?> className) throws Exception { Update update =
 * populateUpdatedFields(updatedFields); getMongoTemplate().updateFirst(query,
 * update, className); }
 * 
 * @Override public void updateDBDoc(Map<String, ?> updatedFields, Query query,
 * String reportCollection) throws Exception { Update update =
 * populateUpdatedFields(updatedFields); getMongoTemplate().updateFirst(query,
 * update, reportCollection); }
 * 
 * @Override public List<String> getAllUniqueFieldsInCollection(Class<?>
 * entityClass, String fieldname) throws SMSBusinessException { List<Object>
 * dbCollection =
 * getMongoTemplate().getCollection(getMongoTemplate().getCollectionName(
 * entityClass)).distinct(fieldname); List<String> collectionResult = new
 * ArrayList<String>(); for (Object object : dbCollection) { if (object
 * instanceof BasicDBObject) { BasicDBObject dbObject = (BasicDBObject) object;
 * String uniqueValue = dbObject.getString(fieldname);
 * collectionResult.add(uniqueValue); } else if (object instanceof String) {
 * String uniqueValue = (String) object; collectionResult.add(uniqueValue); } }
 * return collectionResult; }
 * 
 * @Override public AggregationResults aggregate1(Aggregation aggregation,String
 * inputCollectionName,Class<?> entityClass){ return (AggregationResults)
 * getMongoTemplate().aggregate(aggregation,inputCollectionName,entityClass); }
 * 
 * @Override public AggregationResults aggregate(Aggregation aggregation,
 * Class<?> entityClass1, Class<?> entityClass2) { // TODO Auto-generated method
 * stub return (AggregationResults) getMongoTemplate().aggregate(aggregation,
 * entityClass1, entityClass2); }
 * 
 * @Override public AggregationResults aggregate1(TypedAggregation
 * aggregation,Class<?> entityClass,Class<?> entClass){ return
 * (AggregationResults) getMongoTemplate().aggregate(aggregation, entityClass,
 * entClass); }
 * 
 * private void populateAuditFields(AuditFields baseVo, boolean isUpdateOnly) {
 * // BeanWrapper beanWrapper = new BeanWrapperImpl(baseVo); // code to check
 * whether audit fields exists in BaseVO // try { //
 * beanWrapper.getPropertyDescriptor("created"); //
 * beanWrapper.getPropertyDescriptor("updated"); // }catch(Throwable e) { //
 * return; // } // String userId = null; // try { // UserProfile profile =
 * (UserProfile) SecurityContextHolder //
 * .getContext().getAuthentication().getPrincipal(); // userId =
 * profile.getUserId(); // } catch (Throwable e) { // } // // if
 * (StringUtils.isEmpty(userId)) { // return; // } Date date = GregorianCalendar
 * .getInstance().getTime(); if (isUpdateOnly) { //
 * beanWrapper.setPropertyValue("updated.by", userId); //
 * beanWrapper.setPropertyValue("updated.on", GregorianCalendar //
 * .getInstance().getTime()); baseVo.setUpdatedDate(date); } else {
 * 
 * // beanWrapper.setPropertyValue("created", new AuditFields(userId, //
 * GregorianCalendar.getInstance().getTime())); //
 * beanWrapper.setPropertyValue("updated", new AuditFields(userId, //
 * GregorianCalendar.getInstance().getTime()));
 * 
 * baseVo.setCreatedDate(date); baseVo.setUpdatedDate(date); }
 * 
 * }
 * 
 * private void populateAuditFields(Update update) {
 * 
 * // String userId = null; // try { // UserProfile profile = (UserProfile)
 * SecurityContextHolder // .getContext().getAuthentication().getPrincipal(); //
 * userId = profile.getUserId(); // } catch (Throwable e) { // } // // if
 * (StringUtils.isEmpty(userId)) { // return; // } // //
 * update.set("updated.by", userId); // update.set("updated.on",
 * GregorianCalendar.getInstance().getTime());
 * 
 * }
 * 
 *//**
	 * @param visionVO
	 * @param beanWrapper
	 * @return
	 * @throws SecurityException
	 *//*
		 * private Update populateUpdatedFields(AuditFields visionVO, BeanWrapper
		 * beanWrapper) throws SecurityException { //
		 * BeanUtilities.comparePopulateUpdatableFields(visionVO.getOldEntity(), //
		 * visionVO, visionVO.getClass()); // String[] updatedFieldNames =
		 * visionVO.getUpdatedFieldsNames(); // Update update = new Update(); //
		 * setUpdateObject(beanWrapper, updatedFieldNames, update, null); // // for
		 * (Field f : visionVO.getClass().getDeclaredFields()) { // try { // // Object
		 * inner_new_Object = f.getType().newInstance(); // // if
		 * (isMongoTransientField(f.getName(), visionVO.getClass())) { // continue; // }
		 * // if (inner_new_Object instanceof BaseVO) { // // BaseVO innerBaseVO =
		 * (BaseVO) beanWrapper // .getPropertyValue(f.getName()); // // if (innerBaseVO
		 * == null) { // // BeanWrapper beanWrapperOld = new BeanWrapperImpl( //
		 * visionVO.getOldEntity()); // BaseVO oldinnerBaseVO = (BaseVO) beanWrapperOld
		 * // .getPropertyValue(f.getName()); // if (oldinnerBaseVO != null) { //
		 * update.set(f.getName(), null); // } // // } else { // if
		 * (innerBaseVO.getOldEntity() == null) { // update.set(f.getName(),
		 * innerBaseVO); // } else { // BeanWrapper beanWrapper_inner = new
		 * BeanWrapperImpl( // innerBaseVO); // BeanWrapper innerBeanWrapperOld = new
		 * BeanWrapperImpl( // innerBaseVO.getOldEntity()); // String[]
		 * updatedFieldNames_inner = innerBaseVO // .getUpdatedFieldsNames(); // //
		 * setUpdateObject(beanWrapper, updatedFieldNames, // // update,f.getName()); //
		 * try { // beanWrapper_inner // .getPropertyTypeDescriptor("id"); //
		 * if(!StringUtils.equals((String) beanWrapper_inner //
		 * .getPropertyValue("id"),(String) innerBeanWrapperOld //
		 * .getPropertyValue("id"))){ // update.set(f.getName() + "._id", // (String)
		 * beanWrapper_inner // .getPropertyValue("id")); // } // } catch (Throwable e)
		 * { // // } // // setUpdateObject(beanWrapper_inner, //
		 * updatedFieldNames_inner, update, // f.getName()); // } // } // // } // }
		 * catch (Throwable ex) { // continue; // } // } // //
		 * updateCollectionFields(beanWrapper, visionVO, update); return update; }
		 * 
		 * // This will be used for List type of childs only.and the child objects //
		 * should contain the id field to compare.otherwise whole object will be //
		 * stored in db. // This will override the db list with the new list of objects
		 * if any // changes is done in any child objects
		 * 
		 * @SuppressWarnings("rawtypes") private void updateCollectionFields(BeanWrapper
		 * beanWrapper, AuditFields visionVO, Update update) {
		 * 
		 * // BeanWrapper beanWrapperOld = null; // if (visionVO.getOldEntity() != null)
		 * { // beanWrapperOld = new BeanWrapperImpl(visionVO.getOldEntity()); // } //
		 * // for (Field f : visionVO.getClass().getDeclaredFields()) { // try { // if
		 * (isMongoTransientField(f.getName(), visionVO.getClass())) { // continue; // }
		 * // if (ClassUtils.isAssignable(f.getType(), List.class)) { // //
		 * update.unset(f.getName()); // List newInnerList = (List)
		 * beanWrapper.getPropertyValue(f // .getName()); // List oldInnerList = null;
		 * // if (beanWrapperOld != null) { // oldInnerList = (List)
		 * beanWrapperOld.getPropertyValue(f // .getName()); // } // // if (newInnerList
		 * == null) { // if (oldInnerList != null) { // update.set(f.getName(), null);
		 * // } // } else { // if (oldInnerList == null // || oldInnerList.size() !=
		 * newInnerList.size()) { // update.set(f.getName(), newInnerList); // continue;
		 * // } // // check whether anything changed, if changed override // // new list
		 * there otherwise skip list update // // for (int i = 0; i <
		 * newInnerList.size(); i++) { // Object newInnerObject = newInnerList.get(i);
		 * // if (newInnerObject instanceof BaseVO) { // BaseVO newInnerBaseVO =
		 * (BaseVO) newInnerObject; // BeanWrapper beanWrapper_inner = new
		 * BeanWrapperImpl( // newInnerBaseVO); // String newObjId = (String)
		 * beanWrapper_inner // .getPropertyValue("id"); // if (newObjId == null) { //
		 * update.set(f.getName(), newInnerList); // break; // } else { // BaseVO
		 * innerOldBaseVO = getOldInnerData( // oldInnerList, newObjId); // if
		 * (innerOldBaseVO == null) { // update.set(f.getName(), newInnerList); //
		 * break; // } else { // // innerBaseVO.setOldEntity(innerOldBaseVO); //
		 * BeanUtilities // .comparePopulateUpdatableFields( // innerOldBaseVO, //
		 * newInnerBaseVO, // innerOldBaseVO // .getClass()); // if (newInnerBaseVO //
		 * .getUpdatedFieldsNames() != null // && newInnerBaseVO //
		 * .getUpdatedFieldsNames().length > 0) { // update.set(f.getName(), //
		 * newInnerList); // break; // } else { // // break; // } // } // // } // } else
		 * { // // TODO for other list variables............ // if (newInnerObject
		 * instanceof String) { // if (!oldInnerList.contains(newInnerObject)){ //
		 * update.set(f.getName(), newInnerList); // break; // } // } // } // } // // }
		 * // } // } catch (Throwable ex) { // continue; // } // }
		 * 
		 * }
		 * 
		 * @SuppressWarnings("rawtypes") private AuditFields getOldInnerData(List
		 * visionVOs, String newObjId) { // for (int i = 0; i < visionVOs.size(); i++) {
		 * // BaseVO oldInnerObject = (BaseVO) visionVOs.get(i); // BeanWrapper
		 * beanWrapperOld = new BeanWrapperImpl(oldInnerObject); // String oldObjId =
		 * (String) beanWrapperOld.getPropertyValue("id"); // if
		 * (newObjId.equals(oldObjId)) { // return oldInnerObject; // } // }
		 * 
		 * return null; }
		 * 
		 * private void parseError(WriteResult wr) { // CommandResult lastError =
		 * wr.getLastError(); // if (lastError != null) { // if
		 * (lastError.getException() != null) { // lastError.throwOnError(); // } // }
		 * System.out.println("write result : " + wr.toString());
		 * 
		 * }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * (non-Javadoc) Author : Suraj & Shiva
		 * 
		 * @see
		 * com.jm.common.system.dao.entity.MongoEntityDAO#addChild(java.lang.String,
		 * java.lang.String, java.lang.String, java.lang.Object) subDocumentName is the
		 * child collection name subDocument is the child object to be added
		 * 
		 * // @Override // public void addNewChild(String collectionName, String
		 * parentCollectionId, // String subDocumentCollectionName, Object subDocument)
		 * // throws VMRuntimeException { // try { // Update update = new Update(); //
		 * update.push(subDocumentCollectionName, subDocument); // // Query query = new
		 * Query(); // query.addCriteria(Criteria.where("id").is(parentCollectionId));
		 * // getMongoTemplate().updateFirst(query, update, collectionName); // } catch
		 * (Exception e) { // e.printStackTrace(); // throw new
		 * VMRuntimeException(e.getMessage()); // } // // }
		 * 
		 * 
		 * @Override public void updateChildDocumentOnly(BaseVO baseVo, String
		 * childName) throws VMRuntimeException {
		 * 
		 * try {
		 * 
		 * BeanWrapper beanWrapper = new BeanWrapperImpl(baseVo); String objectId =
		 * (String) beanWrapper.getPropertyValue("id"); BaseVO child = (BaseVO)
		 * beanWrapper.getPropertyValue(childName);
		 * 
		 * BeanUtilities.comparePopulateUpdatableFields(child.getOldEntity(), child,
		 * child.getClass());
		 * 
		 * String[] fieldNames = child.getUpdatedFieldsNames(); BeanWrapper
		 * beanWrapper_child = new BeanWrapperImpl(child); String child_id = (String)
		 * beanWrapper_child.getPropertyValue("id"); Update update = new Update(); for
		 * (String fieldName : fieldNames) { Object fieldValue = beanWrapper_child
		 * .getPropertyValue(fieldName);
		 * 
		 * update.set(childName.concat(".$.").concat(fieldName), fieldValue); }
		 * 
		 * Query query = new Query();
		 * query.addCriteria(Criteria.where(childName.concat("._id")).is( child_id));
		 * getMongoTemplate().updateFirst(query, update, baseVo.getClass());
		 * 
		 * } catch (Exception e) { throw new VMRuntimeException(e.getMessage()); } }
		 * 
		 * private void setUpdateObject(BeanWrapper beanWrapper, String[]
		 * updatedFieldNames, Update update, String parentName) { for (String fieldName
		 * : updatedFieldNames) { if
		 * (ClassUtils.isAssignable(beanWrapper.getPropertyType(fieldName), List.class))
		 * { continue; } if
		 * (ClassUtils.isAssignable(beanWrapper.getPropertyType(fieldName),
		 * AuditFields.class)) { continue; } if (isMongoTransientField(fieldName,
		 * beanWrapper.getWrappedClass())) { continue; }
		 * 
		 * Object fieldValue = beanWrapper.getPropertyValue(fieldName); if (parentName
		 * != null) { fieldName = parentName + "." + fieldName; } update.set(fieldName,
		 * fieldValue); } }
		 * 
		 * @SuppressWarnings("rawtypes") private static boolean
		 * isMongoTransientField(String fieldName, Class entityClass) { Field field =
		 * null; try { field = entityClass.getDeclaredField(fieldName); } catch
		 * (NoSuchFieldException nsfex) { try { field = entityClass.getField(fieldName);
		 * } catch (SecurityException e) { if(logger.isEnabledFor(Level.ERROR)){
		 * logger.error(e.getMessage()); } if(logger.isDebugEnabled()){
		 * logger.debug(e.getMessage()); } } catch (NoSuchFieldException e) {
		 * if(logger.isEnabledFor(Level.ERROR)){ logger.error(e.getMessage()); }
		 * if(logger.isDebugEnabled()){ logger.debug(e.getMessage()); } } }
		 * 
		 * // get all field annotations and check it is // @AlwaysUpdate or not. // if
		 * it is @AlwaysUpdate then put the field value in // updateField map. Transient
		 * annotations = field.getAnnotation(Transient.class); if (annotations == null)
		 * { return false; } else { return true; } }
		 * 
		 * 
		 * 
		 * private Update populateUpdatedFields(Map<String, ?> updatedFields) {
		 * Set<String> keys = updatedFields.keySet(); Update update = new Update(); for
		 * (String fieldName : keys) { Object fieldvalue = updatedFields.get(fieldName);
		 * if (fieldvalue instanceof Map) { String parentName = fieldName; Map<String,
		 * ?> subdoc = (Map<String, ?>) fieldvalue;
		 * 
		 * Set<String> subDocKeys = subdoc.keySet();
		 * 
		 * for (String subDocKey : subDocKeys) { Object subDocFieldvalue =
		 * subdoc.get(subDocKey); String subDocFieldName = parentName + "." + subDocKey;
		 * update.set(subDocFieldName, subDocFieldvalue); }
		 * 
		 * } else { update.set(fieldName, fieldvalue); } } return update; }
		 * 
		 * private void saveDeletedEntity(AuditFields entityObject) { if(null !=
		 * entityObject){ // String userId = null; // Authentication auth =
		 * SecurityContextHolder.getContext().getAuthentication(); // if(auth != null){
		 * // userId = ((UserProfile)auth.getPrincipal()).getUserId(); // }else{ //
		 * userId = "System"; // } // // DeleteAuditVO deleteAuditVO = new
		 * DeleteAuditVO(); // deleteAuditVO.setDeletedRowId(entityObject.getId()); //
		 * deleteAuditVO.setDeletedRowValue(entityObject); //
		 * deleteAuditVO.setDeletedBy(userId); //
		 * deleteAuditVO.setDeletedOn(GregorianCalendar.getInstance().getTime()); //
		 * deleteAuditVO.setDeletedCollectionName(getMongoTemplate().getCollectionName(
		 * entityObject.getClass())); // // insert(deleteAuditVO); } }
		 * 
		 * @Override public void removeDocumentByQuery(Query query, Class<?>
		 * entityClass) throws SMSBusinessException { try {
		 * getMongoTemplate().remove(query, entityClass); } catch (Exception e) { throw
		 * new SMSBusinessException(e.getMessage()); } } }
		 */