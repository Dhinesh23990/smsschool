package com.tgi.sd.dao.mongo;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DaoSupport;
import org.springframework.dao.support.PersistenceExceptionTranslator;

@SuppressWarnings("unchecked")
public  abstract class MongoDAOSupport extends DaoSupport{
	
	private static Logger logger = Logger.getLogger(MongoDAOSupport.class);

	/*
	 * private MongoTemplate mongoTemplate;
	 * 
	 * public MongoTemplate getMongoTemplate() { return mongoTemplate; }
	 * 
	 * public void setMongoTemplate(MongoTemplate mongoTemplate) {
	 * this.mongoTemplate = mongoTemplate; }
	 * 
	 * 
	 * @Override protected void checkDaoConfig() throws IllegalArgumentException {
	 * if (this.mongoTemplate == null) { throw new
	 * IllegalArgumentException("'mongoTemplate' is required"); } }
	 * 
	 * public final void setMongoDbFactory(MongoDbFactory mongoDbFactory) { if
	 * (this.mongoTemplate == null || (mongoDbFactory!=null &&
	 * mongoDbFactory.getDb() != this.mongoTemplate.getDb())) { this.mongoTemplate =
	 * createMongoTemplate(mongoDbFactory); } }
	 * 
	 * protected MongoTemplate createMongoTemplate(MongoDbFactory mongoDbFactory) {
	 * return new MongoTemplate(mongoDbFactory); }
	 * 
	 * protected final MongoDbFactory getMongoDbFactory() { return
	 * (this.mongoTemplate != null ? new MongoDbFactory() {
	 * 
	 * @Override public DB getDb(String arg0) throws DataAccessException { return
	 * this.getDb(arg0); }
	 * 
	 * @Override public DB getDb() throws DataAccessException { return this.getDb();
	 * }
	 * 
	 * @Override public PersistenceExceptionTranslator getExceptionTranslator() {
	 * return this.getExceptionTranslator(); }
	 * 
	 * } : null); }
	 */
	
}
