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

import com.mhdsys.schema.exception.NoSuchRoasterStatusException;
import com.mhdsys.schema.model.RoasterStatus;
import com.mhdsys.schema.model.RoasterStatusTable;
import com.mhdsys.schema.model.impl.RoasterStatusImpl;
import com.mhdsys.schema.model.impl.RoasterStatusModelImpl;
import com.mhdsys.schema.service.persistence.RoasterStatusPersistence;
import com.mhdsys.schema.service.persistence.RoasterStatusUtil;
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
 * The persistence implementation for the roaster status service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = RoasterStatusPersistence.class)
public class RoasterStatusPersistenceImpl
	extends BasePersistenceImpl<RoasterStatus>
	implements RoasterStatusPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>RoasterStatusUtil</code> to access the roaster status persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		RoasterStatusImpl.class.getName();

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
	 * Returns the roaster status where userId = &#63; or throws a <code>NoSuchRoasterStatusException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching roaster status
	 * @throws NoSuchRoasterStatusException if a matching roaster status could not be found
	 */
	@Override
	public RoasterStatus findByUserId(long userId)
		throws NoSuchRoasterStatusException {

		RoasterStatus roasterStatus = fetchByUserId(userId);

		if (roasterStatus == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("userId=");
			sb.append(userId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchRoasterStatusException(sb.toString());
		}

		return roasterStatus;
	}

	/**
	 * Returns the roaster status where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching roaster status, or <code>null</code> if a matching roaster status could not be found
	 */
	@Override
	public RoasterStatus fetchByUserId(long userId) {
		return fetchByUserId(userId, true);
	}

	/**
	 * Returns the roaster status where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching roaster status, or <code>null</code> if a matching roaster status could not be found
	 */
	@Override
	public RoasterStatus fetchByUserId(long userId, boolean useFinderCache) {
		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {userId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUserId, finderArgs, this);
		}

		if (result instanceof RoasterStatus) {
			RoasterStatus roasterStatus = (RoasterStatus)result;

			if (userId != roasterStatus.getUserId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_ROASTERSTATUS_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				List<RoasterStatus> list = query.list();

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
								"RoasterStatusPersistenceImpl.fetchByUserId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					RoasterStatus roasterStatus = list.get(0);

					result = roasterStatus;

					cacheResult(roasterStatus);
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
			return (RoasterStatus)result;
		}
	}

	/**
	 * Removes the roaster status where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the roaster status that was removed
	 */
	@Override
	public RoasterStatus removeByUserId(long userId)
		throws NoSuchRoasterStatusException {

		RoasterStatus roasterStatus = findByUserId(userId);

		return remove(roasterStatus);
	}

	/**
	 * Returns the number of roaster statuses where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching roaster statuses
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ROASTERSTATUS_WHERE);

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
		"roasterStatus.userId = ?";

	private FinderPath _finderPathFetchByEstablishmentDetailId;
	private FinderPath _finderPathCountByEstablishmentDetailId;

	/**
	 * Returns the roaster status where establishmentDetailId = &#63; or throws a <code>NoSuchRoasterStatusException</code> if it could not be found.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @return the matching roaster status
	 * @throws NoSuchRoasterStatusException if a matching roaster status could not be found
	 */
	@Override
	public RoasterStatus findByEstablishmentDetailId(long establishmentDetailId)
		throws NoSuchRoasterStatusException {

		RoasterStatus roasterStatus = fetchByEstablishmentDetailId(
			establishmentDetailId);

		if (roasterStatus == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("establishmentDetailId=");
			sb.append(establishmentDetailId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchRoasterStatusException(sb.toString());
		}

		return roasterStatus;
	}

	/**
	 * Returns the roaster status where establishmentDetailId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @return the matching roaster status, or <code>null</code> if a matching roaster status could not be found
	 */
	@Override
	public RoasterStatus fetchByEstablishmentDetailId(
		long establishmentDetailId) {

		return fetchByEstablishmentDetailId(establishmentDetailId, true);
	}

	/**
	 * Returns the roaster status where establishmentDetailId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching roaster status, or <code>null</code> if a matching roaster status could not be found
	 */
	@Override
	public RoasterStatus fetchByEstablishmentDetailId(
		long establishmentDetailId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {establishmentDetailId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByEstablishmentDetailId, finderArgs, this);
		}

		if (result instanceof RoasterStatus) {
			RoasterStatus roasterStatus = (RoasterStatus)result;

			if (establishmentDetailId !=
					roasterStatus.getEstablishmentDetailId()) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_ROASTERSTATUS_WHERE);

			sb.append(
				_FINDER_COLUMN_ESTABLISHMENTDETAILID_ESTABLISHMENTDETAILID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(establishmentDetailId);

				List<RoasterStatus> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByEstablishmentDetailId, finderArgs,
							list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {
									establishmentDetailId
								};
							}

							_log.warn(
								"RoasterStatusPersistenceImpl.fetchByEstablishmentDetailId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					RoasterStatus roasterStatus = list.get(0);

					result = roasterStatus;

					cacheResult(roasterStatus);
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
			return (RoasterStatus)result;
		}
	}

	/**
	 * Removes the roaster status where establishmentDetailId = &#63; from the database.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @return the roaster status that was removed
	 */
	@Override
	public RoasterStatus removeByEstablishmentDetailId(
			long establishmentDetailId)
		throws NoSuchRoasterStatusException {

		RoasterStatus roasterStatus = findByEstablishmentDetailId(
			establishmentDetailId);

		return remove(roasterStatus);
	}

	/**
	 * Returns the number of roaster statuses where establishmentDetailId = &#63;.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @return the number of matching roaster statuses
	 */
	@Override
	public int countByEstablishmentDetailId(long establishmentDetailId) {
		FinderPath finderPath = _finderPathCountByEstablishmentDetailId;

		Object[] finderArgs = new Object[] {establishmentDetailId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ROASTERSTATUS_WHERE);

			sb.append(
				_FINDER_COLUMN_ESTABLISHMENTDETAILID_ESTABLISHMENTDETAILID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(establishmentDetailId);

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

	private static final String
		_FINDER_COLUMN_ESTABLISHMENTDETAILID_ESTABLISHMENTDETAILID_2 =
			"roasterStatus.establishmentDetailId = ?";

	private FinderPath _finderPathFetchByroasterStatusId;
	private FinderPath _finderPathCountByroasterStatusId;

	/**
	 * Returns the roaster status where roasterStatusId = &#63; or throws a <code>NoSuchRoasterStatusException</code> if it could not be found.
	 *
	 * @param roasterStatusId the roaster status ID
	 * @return the matching roaster status
	 * @throws NoSuchRoasterStatusException if a matching roaster status could not be found
	 */
	@Override
	public RoasterStatus findByroasterStatusId(long roasterStatusId)
		throws NoSuchRoasterStatusException {

		RoasterStatus roasterStatus = fetchByroasterStatusId(roasterStatusId);

		if (roasterStatus == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("roasterStatusId=");
			sb.append(roasterStatusId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchRoasterStatusException(sb.toString());
		}

		return roasterStatus;
	}

	/**
	 * Returns the roaster status where roasterStatusId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param roasterStatusId the roaster status ID
	 * @return the matching roaster status, or <code>null</code> if a matching roaster status could not be found
	 */
	@Override
	public RoasterStatus fetchByroasterStatusId(long roasterStatusId) {
		return fetchByroasterStatusId(roasterStatusId, true);
	}

	/**
	 * Returns the roaster status where roasterStatusId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param roasterStatusId the roaster status ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching roaster status, or <code>null</code> if a matching roaster status could not be found
	 */
	@Override
	public RoasterStatus fetchByroasterStatusId(
		long roasterStatusId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {roasterStatusId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByroasterStatusId, finderArgs, this);
		}

		if (result instanceof RoasterStatus) {
			RoasterStatus roasterStatus = (RoasterStatus)result;

			if (roasterStatusId != roasterStatus.getRoasterStatusId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_ROASTERSTATUS_WHERE);

			sb.append(_FINDER_COLUMN_ROASTERSTATUSID_ROASTERSTATUSID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(roasterStatusId);

				List<RoasterStatus> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByroasterStatusId, finderArgs,
							list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {roasterStatusId};
							}

							_log.warn(
								"RoasterStatusPersistenceImpl.fetchByroasterStatusId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					RoasterStatus roasterStatus = list.get(0);

					result = roasterStatus;

					cacheResult(roasterStatus);
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
			return (RoasterStatus)result;
		}
	}

	/**
	 * Removes the roaster status where roasterStatusId = &#63; from the database.
	 *
	 * @param roasterStatusId the roaster status ID
	 * @return the roaster status that was removed
	 */
	@Override
	public RoasterStatus removeByroasterStatusId(long roasterStatusId)
		throws NoSuchRoasterStatusException {

		RoasterStatus roasterStatus = findByroasterStatusId(roasterStatusId);

		return remove(roasterStatus);
	}

	/**
	 * Returns the number of roaster statuses where roasterStatusId = &#63;.
	 *
	 * @param roasterStatusId the roaster status ID
	 * @return the number of matching roaster statuses
	 */
	@Override
	public int countByroasterStatusId(long roasterStatusId) {
		FinderPath finderPath = _finderPathCountByroasterStatusId;

		Object[] finderArgs = new Object[] {roasterStatusId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ROASTERSTATUS_WHERE);

			sb.append(_FINDER_COLUMN_ROASTERSTATUSID_ROASTERSTATUSID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(roasterStatusId);

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

	private static final String
		_FINDER_COLUMN_ROASTERSTATUSID_ROASTERSTATUSID_2 =
			"roasterStatus.roasterStatusId = ?";

	public RoasterStatusPersistenceImpl() {
		setModelClass(RoasterStatus.class);

		setModelImplClass(RoasterStatusImpl.class);
		setModelPKClass(long.class);

		setTable(RoasterStatusTable.INSTANCE);
	}

	/**
	 * Caches the roaster status in the entity cache if it is enabled.
	 *
	 * @param roasterStatus the roaster status
	 */
	@Override
	public void cacheResult(RoasterStatus roasterStatus) {
		entityCache.putResult(
			RoasterStatusImpl.class, roasterStatus.getPrimaryKey(),
			roasterStatus);

		finderCache.putResult(
			_finderPathFetchByUserId, new Object[] {roasterStatus.getUserId()},
			roasterStatus);

		finderCache.putResult(
			_finderPathFetchByEstablishmentDetailId,
			new Object[] {roasterStatus.getEstablishmentDetailId()},
			roasterStatus);

		finderCache.putResult(
			_finderPathFetchByroasterStatusId,
			new Object[] {roasterStatus.getRoasterStatusId()}, roasterStatus);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the roaster statuses in the entity cache if it is enabled.
	 *
	 * @param roasterStatuses the roaster statuses
	 */
	@Override
	public void cacheResult(List<RoasterStatus> roasterStatuses) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (roasterStatuses.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (RoasterStatus roasterStatus : roasterStatuses) {
			if (entityCache.getResult(
					RoasterStatusImpl.class, roasterStatus.getPrimaryKey()) ==
						null) {

				cacheResult(roasterStatus);
			}
		}
	}

	/**
	 * Clears the cache for all roaster statuses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(RoasterStatusImpl.class);

		finderCache.clearCache(RoasterStatusImpl.class);
	}

	/**
	 * Clears the cache for the roaster status.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(RoasterStatus roasterStatus) {
		entityCache.removeResult(RoasterStatusImpl.class, roasterStatus);
	}

	@Override
	public void clearCache(List<RoasterStatus> roasterStatuses) {
		for (RoasterStatus roasterStatus : roasterStatuses) {
			entityCache.removeResult(RoasterStatusImpl.class, roasterStatus);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(RoasterStatusImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(RoasterStatusImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		RoasterStatusModelImpl roasterStatusModelImpl) {

		Object[] args = new Object[] {roasterStatusModelImpl.getUserId()};

		finderCache.putResult(_finderPathCountByUserId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByUserId, args, roasterStatusModelImpl);

		args = new Object[] {roasterStatusModelImpl.getEstablishmentDetailId()};

		finderCache.putResult(
			_finderPathCountByEstablishmentDetailId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByEstablishmentDetailId, args,
			roasterStatusModelImpl);

		args = new Object[] {roasterStatusModelImpl.getRoasterStatusId()};

		finderCache.putResult(
			_finderPathCountByroasterStatusId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByroasterStatusId, args, roasterStatusModelImpl);
	}

	/**
	 * Creates a new roaster status with the primary key. Does not add the roaster status to the database.
	 *
	 * @param roasterStatusId the primary key for the new roaster status
	 * @return the new roaster status
	 */
	@Override
	public RoasterStatus create(long roasterStatusId) {
		RoasterStatus roasterStatus = new RoasterStatusImpl();

		roasterStatus.setNew(true);
		roasterStatus.setPrimaryKey(roasterStatusId);

		return roasterStatus;
	}

	/**
	 * Removes the roaster status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param roasterStatusId the primary key of the roaster status
	 * @return the roaster status that was removed
	 * @throws NoSuchRoasterStatusException if a roaster status with the primary key could not be found
	 */
	@Override
	public RoasterStatus remove(long roasterStatusId)
		throws NoSuchRoasterStatusException {

		return remove((Serializable)roasterStatusId);
	}

	/**
	 * Removes the roaster status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the roaster status
	 * @return the roaster status that was removed
	 * @throws NoSuchRoasterStatusException if a roaster status with the primary key could not be found
	 */
	@Override
	public RoasterStatus remove(Serializable primaryKey)
		throws NoSuchRoasterStatusException {

		Session session = null;

		try {
			session = openSession();

			RoasterStatus roasterStatus = (RoasterStatus)session.get(
				RoasterStatusImpl.class, primaryKey);

			if (roasterStatus == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRoasterStatusException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(roasterStatus);
		}
		catch (NoSuchRoasterStatusException noSuchEntityException) {
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
	protected RoasterStatus removeImpl(RoasterStatus roasterStatus) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(roasterStatus)) {
				roasterStatus = (RoasterStatus)session.get(
					RoasterStatusImpl.class, roasterStatus.getPrimaryKeyObj());
			}

			if (roasterStatus != null) {
				session.delete(roasterStatus);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (roasterStatus != null) {
			clearCache(roasterStatus);
		}

		return roasterStatus;
	}

	@Override
	public RoasterStatus updateImpl(RoasterStatus roasterStatus) {
		boolean isNew = roasterStatus.isNew();

		if (!(roasterStatus instanceof RoasterStatusModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(roasterStatus.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					roasterStatus);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in roasterStatus proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom RoasterStatus implementation " +
					roasterStatus.getClass());
		}

		RoasterStatusModelImpl roasterStatusModelImpl =
			(RoasterStatusModelImpl)roasterStatus;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (roasterStatus.getCreateDate() == null)) {
			if (serviceContext == null) {
				roasterStatus.setCreateDate(date);
			}
			else {
				roasterStatus.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!roasterStatusModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				roasterStatus.setModifiedDate(date);
			}
			else {
				roasterStatus.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(roasterStatus);
			}
			else {
				roasterStatus = (RoasterStatus)session.merge(roasterStatus);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			RoasterStatusImpl.class, roasterStatusModelImpl, false, true);

		cacheUniqueFindersCache(roasterStatusModelImpl);

		if (isNew) {
			roasterStatus.setNew(false);
		}

		roasterStatus.resetOriginalValues();

		return roasterStatus;
	}

	/**
	 * Returns the roaster status with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the roaster status
	 * @return the roaster status
	 * @throws NoSuchRoasterStatusException if a roaster status with the primary key could not be found
	 */
	@Override
	public RoasterStatus findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRoasterStatusException {

		RoasterStatus roasterStatus = fetchByPrimaryKey(primaryKey);

		if (roasterStatus == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRoasterStatusException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return roasterStatus;
	}

	/**
	 * Returns the roaster status with the primary key or throws a <code>NoSuchRoasterStatusException</code> if it could not be found.
	 *
	 * @param roasterStatusId the primary key of the roaster status
	 * @return the roaster status
	 * @throws NoSuchRoasterStatusException if a roaster status with the primary key could not be found
	 */
	@Override
	public RoasterStatus findByPrimaryKey(long roasterStatusId)
		throws NoSuchRoasterStatusException {

		return findByPrimaryKey((Serializable)roasterStatusId);
	}

	/**
	 * Returns the roaster status with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param roasterStatusId the primary key of the roaster status
	 * @return the roaster status, or <code>null</code> if a roaster status with the primary key could not be found
	 */
	@Override
	public RoasterStatus fetchByPrimaryKey(long roasterStatusId) {
		return fetchByPrimaryKey((Serializable)roasterStatusId);
	}

	/**
	 * Returns all the roaster statuses.
	 *
	 * @return the roaster statuses
	 */
	@Override
	public List<RoasterStatus> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the roaster statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoasterStatusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of roaster statuses
	 * @param end the upper bound of the range of roaster statuses (not inclusive)
	 * @return the range of roaster statuses
	 */
	@Override
	public List<RoasterStatus> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the roaster statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoasterStatusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of roaster statuses
	 * @param end the upper bound of the range of roaster statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of roaster statuses
	 */
	@Override
	public List<RoasterStatus> findAll(
		int start, int end,
		OrderByComparator<RoasterStatus> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the roaster statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RoasterStatusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of roaster statuses
	 * @param end the upper bound of the range of roaster statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of roaster statuses
	 */
	@Override
	public List<RoasterStatus> findAll(
		int start, int end, OrderByComparator<RoasterStatus> orderByComparator,
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

		List<RoasterStatus> list = null;

		if (useFinderCache) {
			list = (List<RoasterStatus>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_ROASTERSTATUS);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_ROASTERSTATUS;

				sql = sql.concat(RoasterStatusModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<RoasterStatus>)QueryUtil.list(
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
	 * Removes all the roaster statuses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (RoasterStatus roasterStatus : findAll()) {
			remove(roasterStatus);
		}
	}

	/**
	 * Returns the number of roaster statuses.
	 *
	 * @return the number of roaster statuses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_ROASTERSTATUS);

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
		return "roasterStatusId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_ROASTERSTATUS;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return RoasterStatusModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the roaster status persistence.
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

		_finderPathFetchByEstablishmentDetailId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByEstablishmentDetailId",
			new String[] {Long.class.getName()},
			new String[] {"establishmentDetailId"}, true);

		_finderPathCountByEstablishmentDetailId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByEstablishmentDetailId", new String[] {Long.class.getName()},
			new String[] {"establishmentDetailId"}, false);

		_finderPathFetchByroasterStatusId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByroasterStatusId",
			new String[] {Long.class.getName()},
			new String[] {"roasterStatusId"}, true);

		_finderPathCountByroasterStatusId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByroasterStatusId",
			new String[] {Long.class.getName()},
			new String[] {"roasterStatusId"}, false);

		RoasterStatusUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		RoasterStatusUtil.setPersistence(null);

		entityCache.removeCache(RoasterStatusImpl.class.getName());
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

	private static final String _SQL_SELECT_ROASTERSTATUS =
		"SELECT roasterStatus FROM RoasterStatus roasterStatus";

	private static final String _SQL_SELECT_ROASTERSTATUS_WHERE =
		"SELECT roasterStatus FROM RoasterStatus roasterStatus WHERE ";

	private static final String _SQL_COUNT_ROASTERSTATUS =
		"SELECT COUNT(roasterStatus) FROM RoasterStatus roasterStatus";

	private static final String _SQL_COUNT_ROASTERSTATUS_WHERE =
		"SELECT COUNT(roasterStatus) FROM RoasterStatus roasterStatus WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "roasterStatus.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No RoasterStatus exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No RoasterStatus exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		RoasterStatusPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}