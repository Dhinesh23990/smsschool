package com.tgi.sd.dao;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;
import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.AdminVO;
import com.tgi.sd.domain.RoleVO;
import com.tgi.sd.domain.TokenVO;
import com.tgi.sd.domain.UserVO;
import com.tgi.sd.exception.SMSBusinessException;

@Transactional
public class UserDAOImpl extends GenericHibernateDAOImpl<AdminVO, String> implements UserDAO {

	private static Logger logger = Logger.getLogger(UserDAOImpl.class);

	@Override
	public void saveUser(UserVO userVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveUser() starts");
		}
		try {
			
			userVO.setLastLogin(new Date());
			super.getSession().save(userVO);
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("saveUser() ends");
		}
	}
	
	@Override
	public void saveToken(TokenVO tokenVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveToken() starts");
		}
		try {
			super.getSession().save(tokenVO);
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("saveToken() ends");
		}
	}

	@Override
	public UserVO getUserById(String id) {
		if (logger.isDebugEnabled()) {
			logger.debug("getByUserID() starts");
		}
		UserVO userVO = null;
		try {
			
			userVO =  (UserVO) super.getSession().get(UserVO.class, id);

			if (logger.isDebugEnabled()) {
				logger.debug("getUserById() ends");
			}
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
		}

		return userVO;
	}

	@Override
	public List<UserVO> getUserByCriteria(String socialId, String socialType) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {

			logger.debug("get By User Criteria starts");
		}
		List<UserVO> userLst = null;
		try {			
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			//if (null != socialType && socialType.equals("Facebook"))
			//else if (null != socialType && socialType.equals("Twitter"))
			
			queryBuilder.append("from UserVO WHERE facebookId = :fbId");

			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("fbId", socialId);

			userLst = query.list();
			
			if (logger.isDebugEnabled()) {
				logger.debug("get By User Criteria ends");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		return userLst;
	}
	
	
	@Override
	public List<UserVO> getUserByIdPwd(String userName, String password) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {

			logger.debug("get By User Criteria starts");
		}
		List<UserVO> userLst = null;
		try {			
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from UserVO WHERE userName = :usrName and password = :pwd");

			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("usrName", userName);
			query.setParameter("pwd", password);

			userLst = query.list();
			
			if (logger.isDebugEnabled()) {
				logger.debug("get By User Criteria ends");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		return userLst;
	}	


	@Override
	public List<UserVO> getAllUserList(String entityId, int pageNo, int pageSize) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {

			logger.debug("User list starts");
		}
		List<UserVO> userLst = null;
		try {		
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from UserVO WHERE entityId = :entityId");

			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("entityId", entityId);

			query.setFirstResult((pageNo - 1) * pageSize);
			query.setMaxResults(pageSize);

			userLst = query.list();

			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("User list ends");
		}
		return userLst;

	}

	@Override
	public long getAllListCountEntity(String entityId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("user count starts");
		}
		int userOrderCount = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from UserVO WHERE entityId = :entityId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("entityId", entityId);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("user count ends");
		}
		
		return userOrderCount;
		
	}

	@Override
	public void updateUser(UserVO userVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {

			logger.debug("user update starts");
		}
		try {
			
			super.getSession().saveOrUpdate(userVO);
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("user update ends");
		}
	}

	@Override
	public void deleteUser(UserVO userVO) {

		if (logger.isDebugEnabled()) {

			logger.debug("user delete starts");
		}
		try {
			Session session = getSession();
			UserVO userVO2 = new UserVO();
			userVO2.setId(userVO.getId());
			session.delete(userVO2);			
		
		} catch (Exception re) {
			logger.error("" + re.getMessage());
		}
		if (logger.isDebugEnabled()) {

			logger.debug("user delete ends");
		}

	}

	@Override
	public UserVO getUserBySocialId(String socialId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("getByUserSocialId() starts");
		}
		UserVO userVO = null;
		try {
			
			//userVO = (UserVO) findById(UserVO.class, socialId);
			
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("from UserVO WHERE socialId = :socialId");

			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("socialId", socialId);

			List<UserVO> userLst = query.list();
			if (userLst != null && userLst.size() > 0) 
				userVO = userLst.get(0);
			
			if (logger.isDebugEnabled()) {
				logger.debug("getUserSocialId() ends");
			}
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}

		return userVO;
	}

	@Override
	public UserVO getUserByUserName(String userName, String roleId, String emailAddress) 
			throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getUserByUserName starts");
		}
		UserVO userVO = null;
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();		
			queryBuilder.append("from UserVO WHERE userName =:usrName");
			
			if(roleId != null){
				queryBuilder.append(" and roleId =:roleId");
			}

			if(emailAddress != null){
				queryBuilder.append(" and emailAddress =:emailAddress");
			}
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("usrName", userName);
			if(roleId != null)
				query.setParameter("roleId", roleId);
			if(emailAddress != null)
				query.setParameter("emailAddress", emailAddress);

			List<UserVO> userLst = query.list();
			
			if (userLst != null && userLst.size() > 0) {
				userVO = userLst.get(0);
				if (userVO.getRoleId() != null) {
					RoleVO roleVO = (RoleVO) super.getSession().get(RoleVO.class, userVO.getRoleId());
					userVO.setRoleVO(roleVO);
					TokenVO tokenVO = (TokenVO) super.getSession().get(TokenVO.class, userVO.getId());
					userVO.setTokenVO(tokenVO);					
				}
			}
		}
		catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		return userVO;
	}

	@Override
	public UserVO getUserByToken(String token) {
		if (logger.isDebugEnabled()) {
			logger.debug("getUserByToken() starts");
		}
		UserVO userVO = null;
		/*
		try {
			
			Query query = new Query();
			query.addCriteria(Criteria.where(SMSConstants.TOKEN).is(token));
			TokenVO tokenVO = (TokenVO) findOneByQuery(query, TokenVO.class);
			if(tokenVO != null) {
				userVO = (UserVO) findById(UserVO.class, tokenVO.getId());
				userVO.setTokenVO(tokenVO);
				if(StringUtils.isNotBlank(userVO.getRoleId())) {
					RoleVO roleVO = (RoleVO) findById(RoleVO.class, userVO.getRoleId());
					userVO.setRoleVO(roleVO);
				}
			}
			
		} catch (SMSBusinessException re) {
			logger.error("" + re.getMessage());
		}
		*/
		
		if (logger.isDebugEnabled()) {
			logger.debug("getUserByToken() ends");
		}
		return userVO;
	}

	@Override
	public void refreshToken(String id) {
		if (logger.isDebugEnabled()) {
			logger.debug("refreshToken() starts");
		}

		/*
		String token = SMSUtility.MD5Converter(UUID.randomUUID().toString());
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_id").is(id));
			Map<String,Object> updatedFields = new HashMap<String,Object>();
			updatedFields.put("token", token);
			updatedFields.put("tokenExpire",SMSUtility.refreshTokenExpDate() );
			updateDocument(updatedFields, query, TokenVO.class, true);
		} catch (SMSBusinessException e) {
			logger.error(e.getMessage());
		}
		*/
		if (logger.isDebugEnabled()) {
			logger.debug("refreshToken() ends");
		}
		
	}

	@Override
	public List<RoleVO> getAllRole() {
		if (logger.isDebugEnabled()) {

			logger.debug("User list starts");
		}
		List<RoleVO> roleLst = null;
		/*
		try {
			roleLst = (List<RoleVO>) findAll(RoleVO.class);
		} catch (SMSBusinessException re) {
			logger.error("" + re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("User list ends");
		}
		*/
		return roleLst;
	}
	

	@Override
	public List<UserVO> getAllUsersBySchoolId() {
		if (logger.isDebugEnabled()) {

			logger.debug("User list starts");
		}
		List<UserVO> userList = null;
		/*
		try {
			userList = (List<UserVO>) findAll(UserVO.class);
		} catch (SMSBusinessException re) {
			logger.error("" + re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("User list ends");
		}
		*/
		return userList;
	}

}
