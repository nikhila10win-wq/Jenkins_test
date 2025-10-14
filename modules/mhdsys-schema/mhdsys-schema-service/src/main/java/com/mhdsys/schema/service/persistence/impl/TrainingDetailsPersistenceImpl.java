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

import com.mhdsys.schema.exception.NoSuchTrainingDetailsException;
import com.mhdsys.schema.model.TrainingDetails;
import com.mhdsys.schema.model.TrainingDetailsTable;
import com.mhdsys.schema.model.impl.TrainingDetailsImpl;
import com.mhdsys.schema.model.impl.TrainingDetailsModelImpl;
import com.mhdsys.schema.service.persistence.TrainingDetailsPersistence;
import com.mhdsys.schema.service.persistence.TrainingDetailsUtil;
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
 * The persistence implementation for the training details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = TrainingDetailsPersistence.class)
public class TrainingDetailsPersistenceImpl
	extends BasePersistenceImpl<TrainingDetails>
	implements TrainingDetailsPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>TrainingDetailsUtil</code> to access the training details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		TrainingDetailsImpl.class.getName();

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
	 * Returns the training details where establishmentDetailId = &#63; or throws a <code>NoSuchTrainingDetailsException</code> if it could not be found.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @return the matching training details
	 * @throws NoSuchTrainingDetailsException if a matching training details could not be found
	 */
	@Override
	public TrainingDetails findByEstablishmentDetailId(
			long establishmentDetailId)
		throws NoSuchTrainingDetailsException {

		TrainingDetails trainingDetails = fetchByEstablishmentDetailId(
			establishmentDetailId);

		if (trainingDetails == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("establishmentDetailId=");
			sb.append(establishmentDetailId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchTrainingDetailsException(sb.toString());
		}

		return trainingDetails;
	}

	/**
	 * Returns the training details where establishmentDetailId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @return the matching training details, or <code>null</code> if a matching training details could not be found
	 */
	@Override
	public TrainingDetails fetchByEstablishmentDetailId(
		long establishmentDetailId) {

		return fetchByEstablishmentDetailId(establishmentDetailId, true);
	}

	/**
	 * Returns the training details where establishmentDetailId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching training details, or <code>null</code> if a matching training details could not be found
	 */
	@Override
	public TrainingDetails fetchByEstablishmentDetailId(
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

		if (result instanceof TrainingDetails) {
			TrainingDetails trainingDetails = (TrainingDetails)result;

			if (establishmentDetailId !=
					trainingDetails.getEstablishmentDetailId()) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_TRAININGDETAILS_WHERE);

			sb.append(
				_FINDER_COLUMN_ESTABLISHMENTDETAILID_ESTABLISHMENTDETAILID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(establishmentDetailId);

				List<TrainingDetails> list = query.list();

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
								"TrainingDetailsPersistenceImpl.fetchByEstablishmentDetailId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					TrainingDetails trainingDetails = list.get(0);

					result = trainingDetails;

					cacheResult(trainingDetails);
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
			return (TrainingDetails)result;
		}
	}

	/**
	 * Removes the training details where establishmentDetailId = &#63; from the database.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @return the training details that was removed
	 */
	@Override
	public TrainingDetails removeByEstablishmentDetailId(
			long establishmentDetailId)
		throws NoSuchTrainingDetailsException {

		TrainingDetails trainingDetails = findByEstablishmentDetailId(
			establishmentDetailId);

		return remove(trainingDetails);
	}

	/**
	 * Returns the number of training detailses where establishmentDetailId = &#63;.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @return the number of matching training detailses
	 */
	@Override
	public int countByEstablishmentDetailId(long establishmentDetailId) {
		FinderPath finderPath = _finderPathCountByEstablishmentDetailId;

		Object[] finderArgs = new Object[] {establishmentDetailId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_TRAININGDETAILS_WHERE);

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
			"trainingDetails.establishmentDetailId = ?";

	private FinderPath _finderPathFetchByUserId;
	private FinderPath _finderPathCountByUserId;

	/**
	 * Returns the training details where userId = &#63; or throws a <code>NoSuchTrainingDetailsException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching training details
	 * @throws NoSuchTrainingDetailsException if a matching training details could not be found
	 */
	@Override
	public TrainingDetails findByUserId(long userId)
		throws NoSuchTrainingDetailsException {

		TrainingDetails trainingDetails = fetchByUserId(userId);

		if (trainingDetails == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("userId=");
			sb.append(userId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchTrainingDetailsException(sb.toString());
		}

		return trainingDetails;
	}

	/**
	 * Returns the training details where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching training details, or <code>null</code> if a matching training details could not be found
	 */
	@Override
	public TrainingDetails fetchByUserId(long userId) {
		return fetchByUserId(userId, true);
	}

	/**
	 * Returns the training details where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching training details, or <code>null</code> if a matching training details could not be found
	 */
	@Override
	public TrainingDetails fetchByUserId(long userId, boolean useFinderCache) {
		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {userId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUserId, finderArgs, this);
		}

		if (result instanceof TrainingDetails) {
			TrainingDetails trainingDetails = (TrainingDetails)result;

			if (userId != trainingDetails.getUserId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_TRAININGDETAILS_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				List<TrainingDetails> list = query.list();

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
								"TrainingDetailsPersistenceImpl.fetchByUserId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					TrainingDetails trainingDetails = list.get(0);

					result = trainingDetails;

					cacheResult(trainingDetails);
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
			return (TrainingDetails)result;
		}
	}

	/**
	 * Removes the training details where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the training details that was removed
	 */
	@Override
	public TrainingDetails removeByUserId(long userId)
		throws NoSuchTrainingDetailsException {

		TrainingDetails trainingDetails = findByUserId(userId);

		return remove(trainingDetails);
	}

	/**
	 * Returns the number of training detailses where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching training detailses
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_TRAININGDETAILS_WHERE);

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
		"trainingDetails.userId = ?";

	public TrainingDetailsPersistenceImpl() {
		setModelClass(TrainingDetails.class);

		setModelImplClass(TrainingDetailsImpl.class);
		setModelPKClass(long.class);

		setTable(TrainingDetailsTable.INSTANCE);
	}

	/**
	 * Caches the training details in the entity cache if it is enabled.
	 *
	 * @param trainingDetails the training details
	 */
	@Override
	public void cacheResult(TrainingDetails trainingDetails) {
		entityCache.putResult(
			TrainingDetailsImpl.class, trainingDetails.getPrimaryKey(),
			trainingDetails);

		finderCache.putResult(
			_finderPathFetchByEstablishmentDetailId,
			new Object[] {trainingDetails.getEstablishmentDetailId()},
			trainingDetails);

		finderCache.putResult(
			_finderPathFetchByUserId,
			new Object[] {trainingDetails.getUserId()}, trainingDetails);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the training detailses in the entity cache if it is enabled.
	 *
	 * @param trainingDetailses the training detailses
	 */
	@Override
	public void cacheResult(List<TrainingDetails> trainingDetailses) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (trainingDetailses.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (TrainingDetails trainingDetails : trainingDetailses) {
			if (entityCache.getResult(
					TrainingDetailsImpl.class,
					trainingDetails.getPrimaryKey()) == null) {

				cacheResult(trainingDetails);
			}
		}
	}

	/**
	 * Clears the cache for all training detailses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TrainingDetailsImpl.class);

		finderCache.clearCache(TrainingDetailsImpl.class);
	}

	/**
	 * Clears the cache for the training details.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TrainingDetails trainingDetails) {
		entityCache.removeResult(TrainingDetailsImpl.class, trainingDetails);
	}

	@Override
	public void clearCache(List<TrainingDetails> trainingDetailses) {
		for (TrainingDetails trainingDetails : trainingDetailses) {
			entityCache.removeResult(
				TrainingDetailsImpl.class, trainingDetails);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(TrainingDetailsImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(TrainingDetailsImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		TrainingDetailsModelImpl trainingDetailsModelImpl) {

		Object[] args = new Object[] {
			trainingDetailsModelImpl.getEstablishmentDetailId()
		};

		finderCache.putResult(
			_finderPathCountByEstablishmentDetailId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByEstablishmentDetailId, args,
			trainingDetailsModelImpl);

		args = new Object[] {trainingDetailsModelImpl.getUserId()};

		finderCache.putResult(_finderPathCountByUserId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByUserId, args, trainingDetailsModelImpl);
	}

	/**
	 * Creates a new training details with the primary key. Does not add the training details to the database.
	 *
	 * @param trainingDetailsId the primary key for the new training details
	 * @return the new training details
	 */
	@Override
	public TrainingDetails create(long trainingDetailsId) {
		TrainingDetails trainingDetails = new TrainingDetailsImpl();

		trainingDetails.setNew(true);
		trainingDetails.setPrimaryKey(trainingDetailsId);

		return trainingDetails;
	}

	/**
	 * Removes the training details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param trainingDetailsId the primary key of the training details
	 * @return the training details that was removed
	 * @throws NoSuchTrainingDetailsException if a training details with the primary key could not be found
	 */
	@Override
	public TrainingDetails remove(long trainingDetailsId)
		throws NoSuchTrainingDetailsException {

		return remove((Serializable)trainingDetailsId);
	}

	/**
	 * Removes the training details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the training details
	 * @return the training details that was removed
	 * @throws NoSuchTrainingDetailsException if a training details with the primary key could not be found
	 */
	@Override
	public TrainingDetails remove(Serializable primaryKey)
		throws NoSuchTrainingDetailsException {

		Session session = null;

		try {
			session = openSession();

			TrainingDetails trainingDetails = (TrainingDetails)session.get(
				TrainingDetailsImpl.class, primaryKey);

			if (trainingDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTrainingDetailsException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(trainingDetails);
		}
		catch (NoSuchTrainingDetailsException noSuchEntityException) {
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
	protected TrainingDetails removeImpl(TrainingDetails trainingDetails) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(trainingDetails)) {
				trainingDetails = (TrainingDetails)session.get(
					TrainingDetailsImpl.class,
					trainingDetails.getPrimaryKeyObj());
			}

			if (trainingDetails != null) {
				session.delete(trainingDetails);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (trainingDetails != null) {
			clearCache(trainingDetails);
		}

		return trainingDetails;
	}

	@Override
	public TrainingDetails updateImpl(TrainingDetails trainingDetails) {
		boolean isNew = trainingDetails.isNew();

		if (!(trainingDetails instanceof TrainingDetailsModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(trainingDetails.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					trainingDetails);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in trainingDetails proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom TrainingDetails implementation " +
					trainingDetails.getClass());
		}

		TrainingDetailsModelImpl trainingDetailsModelImpl =
			(TrainingDetailsModelImpl)trainingDetails;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (trainingDetails.getCreateDate() == null)) {
			if (serviceContext == null) {
				trainingDetails.setCreateDate(date);
			}
			else {
				trainingDetails.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!trainingDetailsModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				trainingDetails.setModifiedDate(date);
			}
			else {
				trainingDetails.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(trainingDetails);
			}
			else {
				trainingDetails = (TrainingDetails)session.merge(
					trainingDetails);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			TrainingDetailsImpl.class, trainingDetailsModelImpl, false, true);

		cacheUniqueFindersCache(trainingDetailsModelImpl);

		if (isNew) {
			trainingDetails.setNew(false);
		}

		trainingDetails.resetOriginalValues();

		return trainingDetails;
	}

	/**
	 * Returns the training details with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the training details
	 * @return the training details
	 * @throws NoSuchTrainingDetailsException if a training details with the primary key could not be found
	 */
	@Override
	public TrainingDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTrainingDetailsException {

		TrainingDetails trainingDetails = fetchByPrimaryKey(primaryKey);

		if (trainingDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTrainingDetailsException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return trainingDetails;
	}

	/**
	 * Returns the training details with the primary key or throws a <code>NoSuchTrainingDetailsException</code> if it could not be found.
	 *
	 * @param trainingDetailsId the primary key of the training details
	 * @return the training details
	 * @throws NoSuchTrainingDetailsException if a training details with the primary key could not be found
	 */
	@Override
	public TrainingDetails findByPrimaryKey(long trainingDetailsId)
		throws NoSuchTrainingDetailsException {

		return findByPrimaryKey((Serializable)trainingDetailsId);
	}

	/**
	 * Returns the training details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param trainingDetailsId the primary key of the training details
	 * @return the training details, or <code>null</code> if a training details with the primary key could not be found
	 */
	@Override
	public TrainingDetails fetchByPrimaryKey(long trainingDetailsId) {
		return fetchByPrimaryKey((Serializable)trainingDetailsId);
	}

	/**
	 * Returns all the training detailses.
	 *
	 * @return the training detailses
	 */
	@Override
	public List<TrainingDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training detailses
	 * @param end the upper bound of the range of training detailses (not inclusive)
	 * @return the range of training detailses
	 */
	@Override
	public List<TrainingDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the training detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training detailses
	 * @param end the upper bound of the range of training detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of training detailses
	 */
	@Override
	public List<TrainingDetails> findAll(
		int start, int end,
		OrderByComparator<TrainingDetails> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the training detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training detailses
	 * @param end the upper bound of the range of training detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of training detailses
	 */
	@Override
	public List<TrainingDetails> findAll(
		int start, int end,
		OrderByComparator<TrainingDetails> orderByComparator,
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

		List<TrainingDetails> list = null;

		if (useFinderCache) {
			list = (List<TrainingDetails>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_TRAININGDETAILS);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_TRAININGDETAILS;

				sql = sql.concat(TrainingDetailsModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<TrainingDetails>)QueryUtil.list(
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
	 * Removes all the training detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TrainingDetails trainingDetails : findAll()) {
			remove(trainingDetails);
		}
	}

	/**
	 * Returns the number of training detailses.
	 *
	 * @return the number of training detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_TRAININGDETAILS);

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
		return "trainingDetailsId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_TRAININGDETAILS;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return TrainingDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the training details persistence.
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

		TrainingDetailsUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		TrainingDetailsUtil.setPersistence(null);

		entityCache.removeCache(TrainingDetailsImpl.class.getName());
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

	private static final String _SQL_SELECT_TRAININGDETAILS =
		"SELECT trainingDetails FROM TrainingDetails trainingDetails";

	private static final String _SQL_SELECT_TRAININGDETAILS_WHERE =
		"SELECT trainingDetails FROM TrainingDetails trainingDetails WHERE ";

	private static final String _SQL_COUNT_TRAININGDETAILS =
		"SELECT COUNT(trainingDetails) FROM TrainingDetails trainingDetails";

	private static final String _SQL_COUNT_TRAININGDETAILS_WHERE =
		"SELECT COUNT(trainingDetails) FROM TrainingDetails trainingDetails WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "trainingDetails.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No TrainingDetails exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No TrainingDetails exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		TrainingDetailsPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}