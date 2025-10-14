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

import com.mhdsys.schema.exception.NoSuchGPFDetailsException;
import com.mhdsys.schema.model.GPFDetails;
import com.mhdsys.schema.model.GPFDetailsTable;
import com.mhdsys.schema.model.impl.GPFDetailsImpl;
import com.mhdsys.schema.model.impl.GPFDetailsModelImpl;
import com.mhdsys.schema.service.persistence.GPFDetailsPersistence;
import com.mhdsys.schema.service.persistence.GPFDetailsUtil;
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
 * The persistence implementation for the gpf details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = GPFDetailsPersistence.class)
public class GPFDetailsPersistenceImpl
	extends BasePersistenceImpl<GPFDetails> implements GPFDetailsPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>GPFDetailsUtil</code> to access the gpf details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		GPFDetailsImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByEstablishmentDetailId;
	private FinderPath _finderPathCountByEstablishmentDetailId;

	/**
	 * Returns the gpf details where establishmentDetailId = &#63; or throws a <code>NoSuchGPFDetailsException</code> if it could not be found.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @return the matching gpf details
	 * @throws NoSuchGPFDetailsException if a matching gpf details could not be found
	 */
	@Override
	public GPFDetails findByEstablishmentDetailId(long establishmentDetailId)
		throws NoSuchGPFDetailsException {

		GPFDetails gpfDetails = fetchByEstablishmentDetailId(
			establishmentDetailId);

		if (gpfDetails == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("establishmentDetailId=");
			sb.append(establishmentDetailId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchGPFDetailsException(sb.toString());
		}

		return gpfDetails;
	}

	/**
	 * Returns the gpf details where establishmentDetailId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @return the matching gpf details, or <code>null</code> if a matching gpf details could not be found
	 */
	@Override
	public GPFDetails fetchByEstablishmentDetailId(long establishmentDetailId) {
		return fetchByEstablishmentDetailId(establishmentDetailId, true);
	}

	/**
	 * Returns the gpf details where establishmentDetailId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching gpf details, or <code>null</code> if a matching gpf details could not be found
	 */
	@Override
	public GPFDetails fetchByEstablishmentDetailId(
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

		if (result instanceof GPFDetails) {
			GPFDetails gpfDetails = (GPFDetails)result;

			if (establishmentDetailId !=
					gpfDetails.getEstablishmentDetailId()) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_GPFDETAILS_WHERE);

			sb.append(
				_FINDER_COLUMN_ESTABLISHMENTDETAILID_ESTABLISHMENTDETAILID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(establishmentDetailId);

				List<GPFDetails> list = query.list();

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
								"GPFDetailsPersistenceImpl.fetchByEstablishmentDetailId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					GPFDetails gpfDetails = list.get(0);

					result = gpfDetails;

					cacheResult(gpfDetails);
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
			return (GPFDetails)result;
		}
	}

	/**
	 * Removes the gpf details where establishmentDetailId = &#63; from the database.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @return the gpf details that was removed
	 */
	@Override
	public GPFDetails removeByEstablishmentDetailId(long establishmentDetailId)
		throws NoSuchGPFDetailsException {

		GPFDetails gpfDetails = findByEstablishmentDetailId(
			establishmentDetailId);

		return remove(gpfDetails);
	}

	/**
	 * Returns the number of gpf detailses where establishmentDetailId = &#63;.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @return the number of matching gpf detailses
	 */
	@Override
	public int countByEstablishmentDetailId(long establishmentDetailId) {
		FinderPath finderPath = _finderPathCountByEstablishmentDetailId;

		Object[] finderArgs = new Object[] {establishmentDetailId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_GPFDETAILS_WHERE);

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
			"gpfDetails.establishmentDetailId = ?";

	private FinderPath _finderPathFetchByUserId;
	private FinderPath _finderPathCountByUserId;

	/**
	 * Returns the gpf details where userId = &#63; or throws a <code>NoSuchGPFDetailsException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching gpf details
	 * @throws NoSuchGPFDetailsException if a matching gpf details could not be found
	 */
	@Override
	public GPFDetails findByUserId(long userId)
		throws NoSuchGPFDetailsException {

		GPFDetails gpfDetails = fetchByUserId(userId);

		if (gpfDetails == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("userId=");
			sb.append(userId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchGPFDetailsException(sb.toString());
		}

		return gpfDetails;
	}

	/**
	 * Returns the gpf details where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching gpf details, or <code>null</code> if a matching gpf details could not be found
	 */
	@Override
	public GPFDetails fetchByUserId(long userId) {
		return fetchByUserId(userId, true);
	}

	/**
	 * Returns the gpf details where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching gpf details, or <code>null</code> if a matching gpf details could not be found
	 */
	@Override
	public GPFDetails fetchByUserId(long userId, boolean useFinderCache) {
		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {userId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUserId, finderArgs, this);
		}

		if (result instanceof GPFDetails) {
			GPFDetails gpfDetails = (GPFDetails)result;

			if (userId != gpfDetails.getUserId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_GPFDETAILS_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				List<GPFDetails> list = query.list();

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
								"GPFDetailsPersistenceImpl.fetchByUserId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					GPFDetails gpfDetails = list.get(0);

					result = gpfDetails;

					cacheResult(gpfDetails);
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
			return (GPFDetails)result;
		}
	}

	/**
	 * Removes the gpf details where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the gpf details that was removed
	 */
	@Override
	public GPFDetails removeByUserId(long userId)
		throws NoSuchGPFDetailsException {

		GPFDetails gpfDetails = findByUserId(userId);

		return remove(gpfDetails);
	}

	/**
	 * Returns the number of gpf detailses where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching gpf detailses
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_GPFDETAILS_WHERE);

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
		"gpfDetails.userId = ?";

	public GPFDetailsPersistenceImpl() {
		setModelClass(GPFDetails.class);

		setModelImplClass(GPFDetailsImpl.class);
		setModelPKClass(long.class);

		setTable(GPFDetailsTable.INSTANCE);
	}

	/**
	 * Caches the gpf details in the entity cache if it is enabled.
	 *
	 * @param gpfDetails the gpf details
	 */
	@Override
	public void cacheResult(GPFDetails gpfDetails) {
		entityCache.putResult(
			GPFDetailsImpl.class, gpfDetails.getPrimaryKey(), gpfDetails);

		finderCache.putResult(
			_finderPathFetchByEstablishmentDetailId,
			new Object[] {gpfDetails.getEstablishmentDetailId()}, gpfDetails);

		finderCache.putResult(
			_finderPathFetchByUserId, new Object[] {gpfDetails.getUserId()},
			gpfDetails);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the gpf detailses in the entity cache if it is enabled.
	 *
	 * @param gpfDetailses the gpf detailses
	 */
	@Override
	public void cacheResult(List<GPFDetails> gpfDetailses) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (gpfDetailses.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (GPFDetails gpfDetails : gpfDetailses) {
			if (entityCache.getResult(
					GPFDetailsImpl.class, gpfDetails.getPrimaryKey()) == null) {

				cacheResult(gpfDetails);
			}
		}
	}

	/**
	 * Clears the cache for all gpf detailses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(GPFDetailsImpl.class);

		finderCache.clearCache(GPFDetailsImpl.class);
	}

	/**
	 * Clears the cache for the gpf details.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(GPFDetails gpfDetails) {
		entityCache.removeResult(GPFDetailsImpl.class, gpfDetails);
	}

	@Override
	public void clearCache(List<GPFDetails> gpfDetailses) {
		for (GPFDetails gpfDetails : gpfDetailses) {
			entityCache.removeResult(GPFDetailsImpl.class, gpfDetails);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(GPFDetailsImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(GPFDetailsImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		GPFDetailsModelImpl gpfDetailsModelImpl) {

		Object[] args = new Object[] {
			gpfDetailsModelImpl.getEstablishmentDetailId()
		};

		finderCache.putResult(
			_finderPathCountByEstablishmentDetailId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByEstablishmentDetailId, args, gpfDetailsModelImpl);

		args = new Object[] {gpfDetailsModelImpl.getUserId()};

		finderCache.putResult(_finderPathCountByUserId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByUserId, args, gpfDetailsModelImpl);
	}

	/**
	 * Creates a new gpf details with the primary key. Does not add the gpf details to the database.
	 *
	 * @param gPFDetailsId the primary key for the new gpf details
	 * @return the new gpf details
	 */
	@Override
	public GPFDetails create(long gPFDetailsId) {
		GPFDetails gpfDetails = new GPFDetailsImpl();

		gpfDetails.setNew(true);
		gpfDetails.setPrimaryKey(gPFDetailsId);

		return gpfDetails;
	}

	/**
	 * Removes the gpf details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param gPFDetailsId the primary key of the gpf details
	 * @return the gpf details that was removed
	 * @throws NoSuchGPFDetailsException if a gpf details with the primary key could not be found
	 */
	@Override
	public GPFDetails remove(long gPFDetailsId)
		throws NoSuchGPFDetailsException {

		return remove((Serializable)gPFDetailsId);
	}

	/**
	 * Removes the gpf details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the gpf details
	 * @return the gpf details that was removed
	 * @throws NoSuchGPFDetailsException if a gpf details with the primary key could not be found
	 */
	@Override
	public GPFDetails remove(Serializable primaryKey)
		throws NoSuchGPFDetailsException {

		Session session = null;

		try {
			session = openSession();

			GPFDetails gpfDetails = (GPFDetails)session.get(
				GPFDetailsImpl.class, primaryKey);

			if (gpfDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchGPFDetailsException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(gpfDetails);
		}
		catch (NoSuchGPFDetailsException noSuchEntityException) {
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
	protected GPFDetails removeImpl(GPFDetails gpfDetails) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(gpfDetails)) {
				gpfDetails = (GPFDetails)session.get(
					GPFDetailsImpl.class, gpfDetails.getPrimaryKeyObj());
			}

			if (gpfDetails != null) {
				session.delete(gpfDetails);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (gpfDetails != null) {
			clearCache(gpfDetails);
		}

		return gpfDetails;
	}

	@Override
	public GPFDetails updateImpl(GPFDetails gpfDetails) {
		boolean isNew = gpfDetails.isNew();

		if (!(gpfDetails instanceof GPFDetailsModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(gpfDetails.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(gpfDetails);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in gpfDetails proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom GPFDetails implementation " +
					gpfDetails.getClass());
		}

		GPFDetailsModelImpl gpfDetailsModelImpl =
			(GPFDetailsModelImpl)gpfDetails;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (gpfDetails.getCreateDate() == null)) {
			if (serviceContext == null) {
				gpfDetails.setCreateDate(date);
			}
			else {
				gpfDetails.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!gpfDetailsModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				gpfDetails.setModifiedDate(date);
			}
			else {
				gpfDetails.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(gpfDetails);
			}
			else {
				gpfDetails = (GPFDetails)session.merge(gpfDetails);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			GPFDetailsImpl.class, gpfDetailsModelImpl, false, true);

		cacheUniqueFindersCache(gpfDetailsModelImpl);

		if (isNew) {
			gpfDetails.setNew(false);
		}

		gpfDetails.resetOriginalValues();

		return gpfDetails;
	}

	/**
	 * Returns the gpf details with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the gpf details
	 * @return the gpf details
	 * @throws NoSuchGPFDetailsException if a gpf details with the primary key could not be found
	 */
	@Override
	public GPFDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchGPFDetailsException {

		GPFDetails gpfDetails = fetchByPrimaryKey(primaryKey);

		if (gpfDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchGPFDetailsException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return gpfDetails;
	}

	/**
	 * Returns the gpf details with the primary key or throws a <code>NoSuchGPFDetailsException</code> if it could not be found.
	 *
	 * @param gPFDetailsId the primary key of the gpf details
	 * @return the gpf details
	 * @throws NoSuchGPFDetailsException if a gpf details with the primary key could not be found
	 */
	@Override
	public GPFDetails findByPrimaryKey(long gPFDetailsId)
		throws NoSuchGPFDetailsException {

		return findByPrimaryKey((Serializable)gPFDetailsId);
	}

	/**
	 * Returns the gpf details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param gPFDetailsId the primary key of the gpf details
	 * @return the gpf details, or <code>null</code> if a gpf details with the primary key could not be found
	 */
	@Override
	public GPFDetails fetchByPrimaryKey(long gPFDetailsId) {
		return fetchByPrimaryKey((Serializable)gPFDetailsId);
	}

	/**
	 * Returns all the gpf detailses.
	 *
	 * @return the gpf detailses
	 */
	@Override
	public List<GPFDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the gpf detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GPFDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of gpf detailses
	 * @param end the upper bound of the range of gpf detailses (not inclusive)
	 * @return the range of gpf detailses
	 */
	@Override
	public List<GPFDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the gpf detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GPFDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of gpf detailses
	 * @param end the upper bound of the range of gpf detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of gpf detailses
	 */
	@Override
	public List<GPFDetails> findAll(
		int start, int end, OrderByComparator<GPFDetails> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the gpf detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GPFDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of gpf detailses
	 * @param end the upper bound of the range of gpf detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of gpf detailses
	 */
	@Override
	public List<GPFDetails> findAll(
		int start, int end, OrderByComparator<GPFDetails> orderByComparator,
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

		List<GPFDetails> list = null;

		if (useFinderCache) {
			list = (List<GPFDetails>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_GPFDETAILS);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_GPFDETAILS;

				sql = sql.concat(GPFDetailsModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<GPFDetails>)QueryUtil.list(
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
	 * Removes all the gpf detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (GPFDetails gpfDetails : findAll()) {
			remove(gpfDetails);
		}
	}

	/**
	 * Returns the number of gpf detailses.
	 *
	 * @return the number of gpf detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_GPFDETAILS);

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
		return "gPFDetailsId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_GPFDETAILS;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return GPFDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the gpf details persistence.
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

		_finderPathFetchByEstablishmentDetailId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByEstablishmentDetailId",
			new String[] {Long.class.getName()},
			new String[] {"establishmentDetailId"}, true);

		_finderPathCountByEstablishmentDetailId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByEstablishmentDetailId", new String[] {Long.class.getName()},
			new String[] {"establishmentDetailId"}, false);

		_finderPathFetchByUserId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByUserId",
			new String[] {Long.class.getName()}, new String[] {"userId"}, true);

		_finderPathCountByUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] {Long.class.getName()}, new String[] {"userId"},
			false);

		GPFDetailsUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		GPFDetailsUtil.setPersistence(null);

		entityCache.removeCache(GPFDetailsImpl.class.getName());
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

	private static final String _SQL_SELECT_GPFDETAILS =
		"SELECT gpfDetails FROM GPFDetails gpfDetails";

	private static final String _SQL_SELECT_GPFDETAILS_WHERE =
		"SELECT gpfDetails FROM GPFDetails gpfDetails WHERE ";

	private static final String _SQL_COUNT_GPFDETAILS =
		"SELECT COUNT(gpfDetails) FROM GPFDetails gpfDetails";

	private static final String _SQL_COUNT_GPFDETAILS_WHERE =
		"SELECT COUNT(gpfDetails) FROM GPFDetails gpfDetails WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "gpfDetails.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No GPFDetails exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No GPFDetails exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		GPFDetailsPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}