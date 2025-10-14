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

import com.mhdsys.schema.exception.NoSuchNPSDetailsException;
import com.mhdsys.schema.model.NPSDetails;
import com.mhdsys.schema.model.NPSDetailsTable;
import com.mhdsys.schema.model.impl.NPSDetailsImpl;
import com.mhdsys.schema.model.impl.NPSDetailsModelImpl;
import com.mhdsys.schema.service.persistence.NPSDetailsPersistence;
import com.mhdsys.schema.service.persistence.NPSDetailsUtil;
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
 * The persistence implementation for the nps details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = NPSDetailsPersistence.class)
public class NPSDetailsPersistenceImpl
	extends BasePersistenceImpl<NPSDetails> implements NPSDetailsPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>NPSDetailsUtil</code> to access the nps details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		NPSDetailsImpl.class.getName();

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
	 * Returns the nps details where establishmentDetailId = &#63; or throws a <code>NoSuchNPSDetailsException</code> if it could not be found.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @return the matching nps details
	 * @throws NoSuchNPSDetailsException if a matching nps details could not be found
	 */
	@Override
	public NPSDetails findByEstablishmentDetailId(long establishmentDetailId)
		throws NoSuchNPSDetailsException {

		NPSDetails npsDetails = fetchByEstablishmentDetailId(
			establishmentDetailId);

		if (npsDetails == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("establishmentDetailId=");
			sb.append(establishmentDetailId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchNPSDetailsException(sb.toString());
		}

		return npsDetails;
	}

	/**
	 * Returns the nps details where establishmentDetailId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @return the matching nps details, or <code>null</code> if a matching nps details could not be found
	 */
	@Override
	public NPSDetails fetchByEstablishmentDetailId(long establishmentDetailId) {
		return fetchByEstablishmentDetailId(establishmentDetailId, true);
	}

	/**
	 * Returns the nps details where establishmentDetailId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching nps details, or <code>null</code> if a matching nps details could not be found
	 */
	@Override
	public NPSDetails fetchByEstablishmentDetailId(
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

		if (result instanceof NPSDetails) {
			NPSDetails npsDetails = (NPSDetails)result;

			if (establishmentDetailId !=
					npsDetails.getEstablishmentDetailId()) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_NPSDETAILS_WHERE);

			sb.append(
				_FINDER_COLUMN_ESTABLISHMENTDETAILID_ESTABLISHMENTDETAILID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(establishmentDetailId);

				List<NPSDetails> list = query.list();

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
								"NPSDetailsPersistenceImpl.fetchByEstablishmentDetailId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					NPSDetails npsDetails = list.get(0);

					result = npsDetails;

					cacheResult(npsDetails);
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
			return (NPSDetails)result;
		}
	}

	/**
	 * Removes the nps details where establishmentDetailId = &#63; from the database.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @return the nps details that was removed
	 */
	@Override
	public NPSDetails removeByEstablishmentDetailId(long establishmentDetailId)
		throws NoSuchNPSDetailsException {

		NPSDetails npsDetails = findByEstablishmentDetailId(
			establishmentDetailId);

		return remove(npsDetails);
	}

	/**
	 * Returns the number of nps detailses where establishmentDetailId = &#63;.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @return the number of matching nps detailses
	 */
	@Override
	public int countByEstablishmentDetailId(long establishmentDetailId) {
		FinderPath finderPath = _finderPathCountByEstablishmentDetailId;

		Object[] finderArgs = new Object[] {establishmentDetailId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_NPSDETAILS_WHERE);

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
			"npsDetails.establishmentDetailId = ?";

	private FinderPath _finderPathFetchByUserId;
	private FinderPath _finderPathCountByUserId;

	/**
	 * Returns the nps details where userId = &#63; or throws a <code>NoSuchNPSDetailsException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching nps details
	 * @throws NoSuchNPSDetailsException if a matching nps details could not be found
	 */
	@Override
	public NPSDetails findByUserId(long userId)
		throws NoSuchNPSDetailsException {

		NPSDetails npsDetails = fetchByUserId(userId);

		if (npsDetails == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("userId=");
			sb.append(userId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchNPSDetailsException(sb.toString());
		}

		return npsDetails;
	}

	/**
	 * Returns the nps details where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching nps details, or <code>null</code> if a matching nps details could not be found
	 */
	@Override
	public NPSDetails fetchByUserId(long userId) {
		return fetchByUserId(userId, true);
	}

	/**
	 * Returns the nps details where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching nps details, or <code>null</code> if a matching nps details could not be found
	 */
	@Override
	public NPSDetails fetchByUserId(long userId, boolean useFinderCache) {
		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {userId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUserId, finderArgs, this);
		}

		if (result instanceof NPSDetails) {
			NPSDetails npsDetails = (NPSDetails)result;

			if (userId != npsDetails.getUserId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_NPSDETAILS_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				List<NPSDetails> list = query.list();

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
								"NPSDetailsPersistenceImpl.fetchByUserId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					NPSDetails npsDetails = list.get(0);

					result = npsDetails;

					cacheResult(npsDetails);
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
			return (NPSDetails)result;
		}
	}

	/**
	 * Removes the nps details where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the nps details that was removed
	 */
	@Override
	public NPSDetails removeByUserId(long userId)
		throws NoSuchNPSDetailsException {

		NPSDetails npsDetails = findByUserId(userId);

		return remove(npsDetails);
	}

	/**
	 * Returns the number of nps detailses where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching nps detailses
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_NPSDETAILS_WHERE);

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
		"npsDetails.userId = ?";

	public NPSDetailsPersistenceImpl() {
		setModelClass(NPSDetails.class);

		setModelImplClass(NPSDetailsImpl.class);
		setModelPKClass(long.class);

		setTable(NPSDetailsTable.INSTANCE);
	}

	/**
	 * Caches the nps details in the entity cache if it is enabled.
	 *
	 * @param npsDetails the nps details
	 */
	@Override
	public void cacheResult(NPSDetails npsDetails) {
		entityCache.putResult(
			NPSDetailsImpl.class, npsDetails.getPrimaryKey(), npsDetails);

		finderCache.putResult(
			_finderPathFetchByEstablishmentDetailId,
			new Object[] {npsDetails.getEstablishmentDetailId()}, npsDetails);

		finderCache.putResult(
			_finderPathFetchByUserId, new Object[] {npsDetails.getUserId()},
			npsDetails);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the nps detailses in the entity cache if it is enabled.
	 *
	 * @param npsDetailses the nps detailses
	 */
	@Override
	public void cacheResult(List<NPSDetails> npsDetailses) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (npsDetailses.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (NPSDetails npsDetails : npsDetailses) {
			if (entityCache.getResult(
					NPSDetailsImpl.class, npsDetails.getPrimaryKey()) == null) {

				cacheResult(npsDetails);
			}
		}
	}

	/**
	 * Clears the cache for all nps detailses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(NPSDetailsImpl.class);

		finderCache.clearCache(NPSDetailsImpl.class);
	}

	/**
	 * Clears the cache for the nps details.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(NPSDetails npsDetails) {
		entityCache.removeResult(NPSDetailsImpl.class, npsDetails);
	}

	@Override
	public void clearCache(List<NPSDetails> npsDetailses) {
		for (NPSDetails npsDetails : npsDetailses) {
			entityCache.removeResult(NPSDetailsImpl.class, npsDetails);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(NPSDetailsImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(NPSDetailsImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		NPSDetailsModelImpl npsDetailsModelImpl) {

		Object[] args = new Object[] {
			npsDetailsModelImpl.getEstablishmentDetailId()
		};

		finderCache.putResult(
			_finderPathCountByEstablishmentDetailId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByEstablishmentDetailId, args, npsDetailsModelImpl);

		args = new Object[] {npsDetailsModelImpl.getUserId()};

		finderCache.putResult(_finderPathCountByUserId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByUserId, args, npsDetailsModelImpl);
	}

	/**
	 * Creates a new nps details with the primary key. Does not add the nps details to the database.
	 *
	 * @param nPSDetailsId the primary key for the new nps details
	 * @return the new nps details
	 */
	@Override
	public NPSDetails create(long nPSDetailsId) {
		NPSDetails npsDetails = new NPSDetailsImpl();

		npsDetails.setNew(true);
		npsDetails.setPrimaryKey(nPSDetailsId);

		return npsDetails;
	}

	/**
	 * Removes the nps details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param nPSDetailsId the primary key of the nps details
	 * @return the nps details that was removed
	 * @throws NoSuchNPSDetailsException if a nps details with the primary key could not be found
	 */
	@Override
	public NPSDetails remove(long nPSDetailsId)
		throws NoSuchNPSDetailsException {

		return remove((Serializable)nPSDetailsId);
	}

	/**
	 * Removes the nps details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the nps details
	 * @return the nps details that was removed
	 * @throws NoSuchNPSDetailsException if a nps details with the primary key could not be found
	 */
	@Override
	public NPSDetails remove(Serializable primaryKey)
		throws NoSuchNPSDetailsException {

		Session session = null;

		try {
			session = openSession();

			NPSDetails npsDetails = (NPSDetails)session.get(
				NPSDetailsImpl.class, primaryKey);

			if (npsDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNPSDetailsException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(npsDetails);
		}
		catch (NoSuchNPSDetailsException noSuchEntityException) {
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
	protected NPSDetails removeImpl(NPSDetails npsDetails) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(npsDetails)) {
				npsDetails = (NPSDetails)session.get(
					NPSDetailsImpl.class, npsDetails.getPrimaryKeyObj());
			}

			if (npsDetails != null) {
				session.delete(npsDetails);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (npsDetails != null) {
			clearCache(npsDetails);
		}

		return npsDetails;
	}

	@Override
	public NPSDetails updateImpl(NPSDetails npsDetails) {
		boolean isNew = npsDetails.isNew();

		if (!(npsDetails instanceof NPSDetailsModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(npsDetails.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(npsDetails);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in npsDetails proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom NPSDetails implementation " +
					npsDetails.getClass());
		}

		NPSDetailsModelImpl npsDetailsModelImpl =
			(NPSDetailsModelImpl)npsDetails;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (npsDetails.getCreateDate() == null)) {
			if (serviceContext == null) {
				npsDetails.setCreateDate(date);
			}
			else {
				npsDetails.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!npsDetailsModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				npsDetails.setModifiedDate(date);
			}
			else {
				npsDetails.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(npsDetails);
			}
			else {
				npsDetails = (NPSDetails)session.merge(npsDetails);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			NPSDetailsImpl.class, npsDetailsModelImpl, false, true);

		cacheUniqueFindersCache(npsDetailsModelImpl);

		if (isNew) {
			npsDetails.setNew(false);
		}

		npsDetails.resetOriginalValues();

		return npsDetails;
	}

	/**
	 * Returns the nps details with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the nps details
	 * @return the nps details
	 * @throws NoSuchNPSDetailsException if a nps details with the primary key could not be found
	 */
	@Override
	public NPSDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchNPSDetailsException {

		NPSDetails npsDetails = fetchByPrimaryKey(primaryKey);

		if (npsDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNPSDetailsException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return npsDetails;
	}

	/**
	 * Returns the nps details with the primary key or throws a <code>NoSuchNPSDetailsException</code> if it could not be found.
	 *
	 * @param nPSDetailsId the primary key of the nps details
	 * @return the nps details
	 * @throws NoSuchNPSDetailsException if a nps details with the primary key could not be found
	 */
	@Override
	public NPSDetails findByPrimaryKey(long nPSDetailsId)
		throws NoSuchNPSDetailsException {

		return findByPrimaryKey((Serializable)nPSDetailsId);
	}

	/**
	 * Returns the nps details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param nPSDetailsId the primary key of the nps details
	 * @return the nps details, or <code>null</code> if a nps details with the primary key could not be found
	 */
	@Override
	public NPSDetails fetchByPrimaryKey(long nPSDetailsId) {
		return fetchByPrimaryKey((Serializable)nPSDetailsId);
	}

	/**
	 * Returns all the nps detailses.
	 *
	 * @return the nps detailses
	 */
	@Override
	public List<NPSDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the nps detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NPSDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of nps detailses
	 * @param end the upper bound of the range of nps detailses (not inclusive)
	 * @return the range of nps detailses
	 */
	@Override
	public List<NPSDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the nps detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NPSDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of nps detailses
	 * @param end the upper bound of the range of nps detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of nps detailses
	 */
	@Override
	public List<NPSDetails> findAll(
		int start, int end, OrderByComparator<NPSDetails> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the nps detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NPSDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of nps detailses
	 * @param end the upper bound of the range of nps detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of nps detailses
	 */
	@Override
	public List<NPSDetails> findAll(
		int start, int end, OrderByComparator<NPSDetails> orderByComparator,
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

		List<NPSDetails> list = null;

		if (useFinderCache) {
			list = (List<NPSDetails>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_NPSDETAILS);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_NPSDETAILS;

				sql = sql.concat(NPSDetailsModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<NPSDetails>)QueryUtil.list(
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
	 * Removes all the nps detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (NPSDetails npsDetails : findAll()) {
			remove(npsDetails);
		}
	}

	/**
	 * Returns the number of nps detailses.
	 *
	 * @return the number of nps detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_NPSDETAILS);

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
		return "nPSDetailsId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_NPSDETAILS;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return NPSDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the nps details persistence.
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

		NPSDetailsUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		NPSDetailsUtil.setPersistence(null);

		entityCache.removeCache(NPSDetailsImpl.class.getName());
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

	private static final String _SQL_SELECT_NPSDETAILS =
		"SELECT npsDetails FROM NPSDetails npsDetails";

	private static final String _SQL_SELECT_NPSDETAILS_WHERE =
		"SELECT npsDetails FROM NPSDetails npsDetails WHERE ";

	private static final String _SQL_COUNT_NPSDETAILS =
		"SELECT COUNT(npsDetails) FROM NPSDetails npsDetails";

	private static final String _SQL_COUNT_NPSDETAILS_WHERE =
		"SELECT COUNT(npsDetails) FROM NPSDetails npsDetails WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "npsDetails.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No NPSDetails exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No NPSDetails exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		NPSDetailsPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}