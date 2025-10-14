/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import com.mhdsys.schema.exception.NoSuchAwardYouthOrgException;
import com.mhdsys.schema.model.AwardYouthOrg;
import com.mhdsys.schema.model.AwardYouthOrgTable;
import com.mhdsys.schema.model.impl.AwardYouthOrgImpl;
import com.mhdsys.schema.model.impl.AwardYouthOrgModelImpl;
import com.mhdsys.schema.service.persistence.AwardYouthOrgPersistence;
import com.mhdsys.schema.service.persistence.AwardYouthOrgUtil;
import com.mhdsys.schema.service.persistence.impl.constants.mhdsysPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the award youth org service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = AwardYouthOrgPersistence.class)
public class AwardYouthOrgPersistenceImpl
	extends BasePersistenceImpl<AwardYouthOrg>
	implements AwardYouthOrgPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>AwardYouthOrgUtil</code> to access the award youth org persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		AwardYouthOrgImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByUserId;
	private FinderPath _finderPathCountByUserId;

	/**
	 * Returns the award youth org where userId = &#63; or throws a <code>NoSuchAwardYouthOrgException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching award youth org
	 * @throws NoSuchAwardYouthOrgException if a matching award youth org could not be found
	 */
	@Override
	public AwardYouthOrg findByUserId(long userId)
		throws NoSuchAwardYouthOrgException {

		AwardYouthOrg awardYouthOrg = fetchByUserId(userId);

		if (awardYouthOrg == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("userId=");
			sb.append(userId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchAwardYouthOrgException(sb.toString());
		}

		return awardYouthOrg;
	}

	/**
	 * Returns the award youth org where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching award youth org, or <code>null</code> if a matching award youth org could not be found
	 */
	@Override
	public AwardYouthOrg fetchByUserId(long userId) {
		return fetchByUserId(userId, true);
	}

	/**
	 * Returns the award youth org where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching award youth org, or <code>null</code> if a matching award youth org could not be found
	 */
	@Override
	public AwardYouthOrg fetchByUserId(long userId, boolean useFinderCache) {
		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {userId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUserId, finderArgs, this);
		}

		if (result instanceof AwardYouthOrg) {
			AwardYouthOrg awardYouthOrg = (AwardYouthOrg)result;

			if (userId != awardYouthOrg.getUserId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_AWARDYOUTHORG_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				List<AwardYouthOrg> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUserId, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {userId};
							}

							_log.warn(
								"AwardYouthOrgPersistenceImpl.fetchByUserId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					AwardYouthOrg awardYouthOrg = list.get(0);

					result = awardYouthOrg;

					cacheResult(awardYouthOrg);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (AwardYouthOrg)result;
		}
	}

	/**
	 * Removes the award youth org where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the award youth org that was removed
	 */
	@Override
	public AwardYouthOrg removeByUserId(long userId)
		throws NoSuchAwardYouthOrgException {

		AwardYouthOrg awardYouthOrg = findByUserId(userId);

		return remove(awardYouthOrg);
	}

	/**
	 * Returns the number of award youth orgs where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching award youth orgs
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_AWARDYOUTHORG_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_USERID_USERID_2 =
		"awardYouthOrg.userId = ?";

	public AwardYouthOrgPersistenceImpl() {
		setModelClass(AwardYouthOrg.class);

		setModelImplClass(AwardYouthOrgImpl.class);
		setModelPKClass(long.class);

		setTable(AwardYouthOrgTable.INSTANCE);
	}

	/**
	 * Caches the award youth org in the entity cache if it is enabled.
	 *
	 * @param awardYouthOrg the award youth org
	 */
	@Override
	public void cacheResult(AwardYouthOrg awardYouthOrg) {
		entityCache.putResult(
			AwardYouthOrgImpl.class, awardYouthOrg.getPrimaryKey(),
			awardYouthOrg);

		finderCache.putResult(
			_finderPathFetchByUserId, new Object[] {awardYouthOrg.getUserId()},
			awardYouthOrg);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the award youth orgs in the entity cache if it is enabled.
	 *
	 * @param awardYouthOrgs the award youth orgs
	 */
	@Override
	public void cacheResult(List<AwardYouthOrg> awardYouthOrgs) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (awardYouthOrgs.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (AwardYouthOrg awardYouthOrg : awardYouthOrgs) {
			if (entityCache.getResult(
					AwardYouthOrgImpl.class, awardYouthOrg.getPrimaryKey()) ==
						null) {

				cacheResult(awardYouthOrg);
			}
		}
	}

	/**
	 * Clears the cache for all award youth orgs.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AwardYouthOrgImpl.class);

		finderCache.clearCache(AwardYouthOrgImpl.class);
	}

	/**
	 * Clears the cache for the award youth org.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AwardYouthOrg awardYouthOrg) {
		entityCache.removeResult(AwardYouthOrgImpl.class, awardYouthOrg);
	}

	@Override
	public void clearCache(List<AwardYouthOrg> awardYouthOrgs) {
		for (AwardYouthOrg awardYouthOrg : awardYouthOrgs) {
			entityCache.removeResult(AwardYouthOrgImpl.class, awardYouthOrg);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(AwardYouthOrgImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(AwardYouthOrgImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		AwardYouthOrgModelImpl awardYouthOrgModelImpl) {

		Object[] args = new Object[] {awardYouthOrgModelImpl.getUserId()};

		finderCache.putResult(_finderPathCountByUserId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByUserId, args, awardYouthOrgModelImpl);
	}

	/**
	 * Creates a new award youth org with the primary key. Does not add the award youth org to the database.
	 *
	 * @param awardYouthOrgId the primary key for the new award youth org
	 * @return the new award youth org
	 */
	@Override
	public AwardYouthOrg create(long awardYouthOrgId) {
		AwardYouthOrg awardYouthOrg = new AwardYouthOrgImpl();

		awardYouthOrg.setNew(true);
		awardYouthOrg.setPrimaryKey(awardYouthOrgId);

		return awardYouthOrg;
	}

	/**
	 * Removes the award youth org with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param awardYouthOrgId the primary key of the award youth org
	 * @return the award youth org that was removed
	 * @throws NoSuchAwardYouthOrgException if a award youth org with the primary key could not be found
	 */
	@Override
	public AwardYouthOrg remove(long awardYouthOrgId)
		throws NoSuchAwardYouthOrgException {

		return remove((Serializable)awardYouthOrgId);
	}

	/**
	 * Removes the award youth org with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the award youth org
	 * @return the award youth org that was removed
	 * @throws NoSuchAwardYouthOrgException if a award youth org with the primary key could not be found
	 */
	@Override
	public AwardYouthOrg remove(Serializable primaryKey)
		throws NoSuchAwardYouthOrgException {

		Session session = null;

		try {
			session = openSession();

			AwardYouthOrg awardYouthOrg = (AwardYouthOrg)session.get(
				AwardYouthOrgImpl.class, primaryKey);

			if (awardYouthOrg == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAwardYouthOrgException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(awardYouthOrg);
		}
		catch (NoSuchAwardYouthOrgException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected AwardYouthOrg removeImpl(AwardYouthOrg awardYouthOrg) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(awardYouthOrg)) {
				awardYouthOrg = (AwardYouthOrg)session.get(
					AwardYouthOrgImpl.class, awardYouthOrg.getPrimaryKeyObj());
			}

			if (awardYouthOrg != null) {
				session.delete(awardYouthOrg);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (awardYouthOrg != null) {
			clearCache(awardYouthOrg);
		}

		return awardYouthOrg;
	}

	@Override
	public AwardYouthOrg updateImpl(AwardYouthOrg awardYouthOrg) {
		boolean isNew = awardYouthOrg.isNew();

		if (!(awardYouthOrg instanceof AwardYouthOrgModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(awardYouthOrg.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					awardYouthOrg);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in awardYouthOrg proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom AwardYouthOrg implementation " +
					awardYouthOrg.getClass());
		}

		AwardYouthOrgModelImpl awardYouthOrgModelImpl =
			(AwardYouthOrgModelImpl)awardYouthOrg;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (awardYouthOrg.getCreateDate() == null)) {
			if (serviceContext == null) {
				awardYouthOrg.setCreateDate(date);
			}
			else {
				awardYouthOrg.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!awardYouthOrgModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				awardYouthOrg.setModifiedDate(date);
			}
			else {
				awardYouthOrg.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(awardYouthOrg);
			}
			else {
				awardYouthOrg = (AwardYouthOrg)session.merge(awardYouthOrg);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			AwardYouthOrgImpl.class, awardYouthOrgModelImpl, false, true);

		cacheUniqueFindersCache(awardYouthOrgModelImpl);

		if (isNew) {
			awardYouthOrg.setNew(false);
		}

		awardYouthOrg.resetOriginalValues();

		return awardYouthOrg;
	}

	/**
	 * Returns the award youth org with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the award youth org
	 * @return the award youth org
	 * @throws NoSuchAwardYouthOrgException if a award youth org with the primary key could not be found
	 */
	@Override
	public AwardYouthOrg findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAwardYouthOrgException {

		AwardYouthOrg awardYouthOrg = fetchByPrimaryKey(primaryKey);

		if (awardYouthOrg == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAwardYouthOrgException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return awardYouthOrg;
	}

	/**
	 * Returns the award youth org with the primary key or throws a <code>NoSuchAwardYouthOrgException</code> if it could not be found.
	 *
	 * @param awardYouthOrgId the primary key of the award youth org
	 * @return the award youth org
	 * @throws NoSuchAwardYouthOrgException if a award youth org with the primary key could not be found
	 */
	@Override
	public AwardYouthOrg findByPrimaryKey(long awardYouthOrgId)
		throws NoSuchAwardYouthOrgException {

		return findByPrimaryKey((Serializable)awardYouthOrgId);
	}

	/**
	 * Returns the award youth org with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param awardYouthOrgId the primary key of the award youth org
	 * @return the award youth org, or <code>null</code> if a award youth org with the primary key could not be found
	 */
	@Override
	public AwardYouthOrg fetchByPrimaryKey(long awardYouthOrgId) {
		return fetchByPrimaryKey((Serializable)awardYouthOrgId);
	}

	/**
	 * Returns all the award youth orgs.
	 *
	 * @return the award youth orgs
	 */
	@Override
	public List<AwardYouthOrg> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the award youth orgs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardYouthOrgModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of award youth orgs
	 * @param end the upper bound of the range of award youth orgs (not inclusive)
	 * @return the range of award youth orgs
	 */
	@Override
	public List<AwardYouthOrg> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the award youth orgs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardYouthOrgModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of award youth orgs
	 * @param end the upper bound of the range of award youth orgs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of award youth orgs
	 */
	@Override
	public List<AwardYouthOrg> findAll(
		int start, int end,
		OrderByComparator<AwardYouthOrg> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the award youth orgs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardYouthOrgModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of award youth orgs
	 * @param end the upper bound of the range of award youth orgs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of award youth orgs
	 */
	@Override
	public List<AwardYouthOrg> findAll(
		int start, int end, OrderByComparator<AwardYouthOrg> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<AwardYouthOrg> list = null;

		if (useFinderCache) {
			list = (List<AwardYouthOrg>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_AWARDYOUTHORG);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_AWARDYOUTHORG;

				sql = sql.concat(AwardYouthOrgModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<AwardYouthOrg>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the award youth orgs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AwardYouthOrg awardYouthOrg : findAll()) {
			remove(awardYouthOrg);
		}
	}

	/**
	 * Returns the number of award youth orgs.
	 *
	 * @return the number of award youth orgs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_AWARDYOUTHORG);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "awardYouthOrgId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_AWARDYOUTHORG;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return AwardYouthOrgModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the award youth org persistence.
	 */
	@Activate
	public void activate() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathFetchByUserId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByUserId",
			new String[] {Long.class.getName()}, new String[] {"userId"}, true);

		_finderPathCountByUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] {Long.class.getName()}, new String[] {"userId"},
			false);

		AwardYouthOrgUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		AwardYouthOrgUtil.setPersistence(null);

		entityCache.removeCache(AwardYouthOrgImpl.class.getName());
	}

	@Override
	@Reference(
		target = mhdsysPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = mhdsysPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = mhdsysPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_AWARDYOUTHORG =
		"SELECT awardYouthOrg FROM AwardYouthOrg awardYouthOrg";

	private static final String _SQL_SELECT_AWARDYOUTHORG_WHERE =
		"SELECT awardYouthOrg FROM AwardYouthOrg awardYouthOrg WHERE ";

	private static final String _SQL_COUNT_AWARDYOUTHORG =
		"SELECT COUNT(awardYouthOrg) FROM AwardYouthOrg awardYouthOrg";

	private static final String _SQL_COUNT_AWARDYOUTHORG_WHERE =
		"SELECT COUNT(awardYouthOrg) FROM AwardYouthOrg awardYouthOrg WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "awardYouthOrg.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No AwardYouthOrg exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No AwardYouthOrg exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		AwardYouthOrgPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}