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

import com.mhdsys.schema.exception.NoSuchServiceDetailsException;
import com.mhdsys.schema.model.ServiceDetails;
import com.mhdsys.schema.model.ServiceDetailsTable;
import com.mhdsys.schema.model.impl.ServiceDetailsImpl;
import com.mhdsys.schema.model.impl.ServiceDetailsModelImpl;
import com.mhdsys.schema.service.persistence.ServiceDetailsPersistence;
import com.mhdsys.schema.service.persistence.ServiceDetailsUtil;
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
 * The persistence implementation for the service details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = ServiceDetailsPersistence.class)
public class ServiceDetailsPersistenceImpl
	extends BasePersistenceImpl<ServiceDetails>
	implements ServiceDetailsPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ServiceDetailsUtil</code> to access the service details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ServiceDetailsImpl.class.getName();

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
	 * Returns the service details where userId = &#63; or throws a <code>NoSuchServiceDetailsException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching service details
	 * @throws NoSuchServiceDetailsException if a matching service details could not be found
	 */
	@Override
	public ServiceDetails findByUserId(long userId)
		throws NoSuchServiceDetailsException {

		ServiceDetails serviceDetails = fetchByUserId(userId);

		if (serviceDetails == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("userId=");
			sb.append(userId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchServiceDetailsException(sb.toString());
		}

		return serviceDetails;
	}

	/**
	 * Returns the service details where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching service details, or <code>null</code> if a matching service details could not be found
	 */
	@Override
	public ServiceDetails fetchByUserId(long userId) {
		return fetchByUserId(userId, true);
	}

	/**
	 * Returns the service details where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching service details, or <code>null</code> if a matching service details could not be found
	 */
	@Override
	public ServiceDetails fetchByUserId(long userId, boolean useFinderCache) {
		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {userId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUserId, finderArgs, this);
		}

		if (result instanceof ServiceDetails) {
			ServiceDetails serviceDetails = (ServiceDetails)result;

			if (userId != serviceDetails.getUserId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_SERVICEDETAILS_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				List<ServiceDetails> list = query.list();

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
								"ServiceDetailsPersistenceImpl.fetchByUserId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ServiceDetails serviceDetails = list.get(0);

					result = serviceDetails;

					cacheResult(serviceDetails);
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
			return (ServiceDetails)result;
		}
	}

	/**
	 * Removes the service details where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the service details that was removed
	 */
	@Override
	public ServiceDetails removeByUserId(long userId)
		throws NoSuchServiceDetailsException {

		ServiceDetails serviceDetails = findByUserId(userId);

		return remove(serviceDetails);
	}

	/**
	 * Returns the number of service detailses where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching service detailses
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SERVICEDETAILS_WHERE);

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
		"serviceDetails.userId = ?";

	private FinderPath _finderPathFetchByEstablishmentDetailId;
	private FinderPath _finderPathCountByEstablishmentDetailId;

	/**
	 * Returns the service details where establishmentDetailId = &#63; or throws a <code>NoSuchServiceDetailsException</code> if it could not be found.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @return the matching service details
	 * @throws NoSuchServiceDetailsException if a matching service details could not be found
	 */
	@Override
	public ServiceDetails findByEstablishmentDetailId(
			long establishmentDetailId)
		throws NoSuchServiceDetailsException {

		ServiceDetails serviceDetails = fetchByEstablishmentDetailId(
			establishmentDetailId);

		if (serviceDetails == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("establishmentDetailId=");
			sb.append(establishmentDetailId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchServiceDetailsException(sb.toString());
		}

		return serviceDetails;
	}

	/**
	 * Returns the service details where establishmentDetailId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @return the matching service details, or <code>null</code> if a matching service details could not be found
	 */
	@Override
	public ServiceDetails fetchByEstablishmentDetailId(
		long establishmentDetailId) {

		return fetchByEstablishmentDetailId(establishmentDetailId, true);
	}

	/**
	 * Returns the service details where establishmentDetailId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching service details, or <code>null</code> if a matching service details could not be found
	 */
	@Override
	public ServiceDetails fetchByEstablishmentDetailId(
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

		if (result instanceof ServiceDetails) {
			ServiceDetails serviceDetails = (ServiceDetails)result;

			if (establishmentDetailId !=
					serviceDetails.getEstablishmentDetailId()) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_SERVICEDETAILS_WHERE);

			sb.append(
				_FINDER_COLUMN_ESTABLISHMENTDETAILID_ESTABLISHMENTDETAILID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(establishmentDetailId);

				List<ServiceDetails> list = query.list();

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
								"ServiceDetailsPersistenceImpl.fetchByEstablishmentDetailId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ServiceDetails serviceDetails = list.get(0);

					result = serviceDetails;

					cacheResult(serviceDetails);
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
			return (ServiceDetails)result;
		}
	}

	/**
	 * Removes the service details where establishmentDetailId = &#63; from the database.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @return the service details that was removed
	 */
	@Override
	public ServiceDetails removeByEstablishmentDetailId(
			long establishmentDetailId)
		throws NoSuchServiceDetailsException {

		ServiceDetails serviceDetails = findByEstablishmentDetailId(
			establishmentDetailId);

		return remove(serviceDetails);
	}

	/**
	 * Returns the number of service detailses where establishmentDetailId = &#63;.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @return the number of matching service detailses
	 */
	@Override
	public int countByEstablishmentDetailId(long establishmentDetailId) {
		FinderPath finderPath = _finderPathCountByEstablishmentDetailId;

		Object[] finderArgs = new Object[] {establishmentDetailId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SERVICEDETAILS_WHERE);

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
			"serviceDetails.establishmentDetailId = ?";

	private FinderPath _finderPathFetchByserviceDetailsId;
	private FinderPath _finderPathCountByserviceDetailsId;

	/**
	 * Returns the service details where serviceDetailsId = &#63; or throws a <code>NoSuchServiceDetailsException</code> if it could not be found.
	 *
	 * @param serviceDetailsId the service details ID
	 * @return the matching service details
	 * @throws NoSuchServiceDetailsException if a matching service details could not be found
	 */
	@Override
	public ServiceDetails findByserviceDetailsId(long serviceDetailsId)
		throws NoSuchServiceDetailsException {

		ServiceDetails serviceDetails = fetchByserviceDetailsId(
			serviceDetailsId);

		if (serviceDetails == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("serviceDetailsId=");
			sb.append(serviceDetailsId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchServiceDetailsException(sb.toString());
		}

		return serviceDetails;
	}

	/**
	 * Returns the service details where serviceDetailsId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param serviceDetailsId the service details ID
	 * @return the matching service details, or <code>null</code> if a matching service details could not be found
	 */
	@Override
	public ServiceDetails fetchByserviceDetailsId(long serviceDetailsId) {
		return fetchByserviceDetailsId(serviceDetailsId, true);
	}

	/**
	 * Returns the service details where serviceDetailsId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param serviceDetailsId the service details ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching service details, or <code>null</code> if a matching service details could not be found
	 */
	@Override
	public ServiceDetails fetchByserviceDetailsId(
		long serviceDetailsId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {serviceDetailsId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByserviceDetailsId, finderArgs, this);
		}

		if (result instanceof ServiceDetails) {
			ServiceDetails serviceDetails = (ServiceDetails)result;

			if (serviceDetailsId != serviceDetails.getServiceDetailsId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_SERVICEDETAILS_WHERE);

			sb.append(_FINDER_COLUMN_SERVICEDETAILSID_SERVICEDETAILSID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(serviceDetailsId);

				List<ServiceDetails> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByserviceDetailsId, finderArgs,
							list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {serviceDetailsId};
							}

							_log.warn(
								"ServiceDetailsPersistenceImpl.fetchByserviceDetailsId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ServiceDetails serviceDetails = list.get(0);

					result = serviceDetails;

					cacheResult(serviceDetails);
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
			return (ServiceDetails)result;
		}
	}

	/**
	 * Removes the service details where serviceDetailsId = &#63; from the database.
	 *
	 * @param serviceDetailsId the service details ID
	 * @return the service details that was removed
	 */
	@Override
	public ServiceDetails removeByserviceDetailsId(long serviceDetailsId)
		throws NoSuchServiceDetailsException {

		ServiceDetails serviceDetails = findByserviceDetailsId(
			serviceDetailsId);

		return remove(serviceDetails);
	}

	/**
	 * Returns the number of service detailses where serviceDetailsId = &#63;.
	 *
	 * @param serviceDetailsId the service details ID
	 * @return the number of matching service detailses
	 */
	@Override
	public int countByserviceDetailsId(long serviceDetailsId) {
		FinderPath finderPath = _finderPathCountByserviceDetailsId;

		Object[] finderArgs = new Object[] {serviceDetailsId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SERVICEDETAILS_WHERE);

			sb.append(_FINDER_COLUMN_SERVICEDETAILSID_SERVICEDETAILSID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(serviceDetailsId);

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
		_FINDER_COLUMN_SERVICEDETAILSID_SERVICEDETAILSID_2 =
			"serviceDetails.serviceDetailsId = ?";

	public ServiceDetailsPersistenceImpl() {
		setModelClass(ServiceDetails.class);

		setModelImplClass(ServiceDetailsImpl.class);
		setModelPKClass(long.class);

		setTable(ServiceDetailsTable.INSTANCE);
	}

	/**
	 * Caches the service details in the entity cache if it is enabled.
	 *
	 * @param serviceDetails the service details
	 */
	@Override
	public void cacheResult(ServiceDetails serviceDetails) {
		entityCache.putResult(
			ServiceDetailsImpl.class, serviceDetails.getPrimaryKey(),
			serviceDetails);

		finderCache.putResult(
			_finderPathFetchByUserId, new Object[] {serviceDetails.getUserId()},
			serviceDetails);

		finderCache.putResult(
			_finderPathFetchByEstablishmentDetailId,
			new Object[] {serviceDetails.getEstablishmentDetailId()},
			serviceDetails);

		finderCache.putResult(
			_finderPathFetchByserviceDetailsId,
			new Object[] {serviceDetails.getServiceDetailsId()},
			serviceDetails);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the service detailses in the entity cache if it is enabled.
	 *
	 * @param serviceDetailses the service detailses
	 */
	@Override
	public void cacheResult(List<ServiceDetails> serviceDetailses) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (serviceDetailses.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ServiceDetails serviceDetails : serviceDetailses) {
			if (entityCache.getResult(
					ServiceDetailsImpl.class, serviceDetails.getPrimaryKey()) ==
						null) {

				cacheResult(serviceDetails);
			}
		}
	}

	/**
	 * Clears the cache for all service detailses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ServiceDetailsImpl.class);

		finderCache.clearCache(ServiceDetailsImpl.class);
	}

	/**
	 * Clears the cache for the service details.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ServiceDetails serviceDetails) {
		entityCache.removeResult(ServiceDetailsImpl.class, serviceDetails);
	}

	@Override
	public void clearCache(List<ServiceDetails> serviceDetailses) {
		for (ServiceDetails serviceDetails : serviceDetailses) {
			entityCache.removeResult(ServiceDetailsImpl.class, serviceDetails);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ServiceDetailsImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ServiceDetailsImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		ServiceDetailsModelImpl serviceDetailsModelImpl) {

		Object[] args = new Object[] {serviceDetailsModelImpl.getUserId()};

		finderCache.putResult(_finderPathCountByUserId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByUserId, args, serviceDetailsModelImpl);

		args = new Object[] {
			serviceDetailsModelImpl.getEstablishmentDetailId()
		};

		finderCache.putResult(
			_finderPathCountByEstablishmentDetailId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByEstablishmentDetailId, args,
			serviceDetailsModelImpl);

		args = new Object[] {serviceDetailsModelImpl.getServiceDetailsId()};

		finderCache.putResult(
			_finderPathCountByserviceDetailsId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByserviceDetailsId, args, serviceDetailsModelImpl);
	}

	/**
	 * Creates a new service details with the primary key. Does not add the service details to the database.
	 *
	 * @param serviceDetailsId the primary key for the new service details
	 * @return the new service details
	 */
	@Override
	public ServiceDetails create(long serviceDetailsId) {
		ServiceDetails serviceDetails = new ServiceDetailsImpl();

		serviceDetails.setNew(true);
		serviceDetails.setPrimaryKey(serviceDetailsId);

		return serviceDetails;
	}

	/**
	 * Removes the service details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param serviceDetailsId the primary key of the service details
	 * @return the service details that was removed
	 * @throws NoSuchServiceDetailsException if a service details with the primary key could not be found
	 */
	@Override
	public ServiceDetails remove(long serviceDetailsId)
		throws NoSuchServiceDetailsException {

		return remove((Serializable)serviceDetailsId);
	}

	/**
	 * Removes the service details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the service details
	 * @return the service details that was removed
	 * @throws NoSuchServiceDetailsException if a service details with the primary key could not be found
	 */
	@Override
	public ServiceDetails remove(Serializable primaryKey)
		throws NoSuchServiceDetailsException {

		Session session = null;

		try {
			session = openSession();

			ServiceDetails serviceDetails = (ServiceDetails)session.get(
				ServiceDetailsImpl.class, primaryKey);

			if (serviceDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchServiceDetailsException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(serviceDetails);
		}
		catch (NoSuchServiceDetailsException noSuchEntityException) {
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
	protected ServiceDetails removeImpl(ServiceDetails serviceDetails) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(serviceDetails)) {
				serviceDetails = (ServiceDetails)session.get(
					ServiceDetailsImpl.class,
					serviceDetails.getPrimaryKeyObj());
			}

			if (serviceDetails != null) {
				session.delete(serviceDetails);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (serviceDetails != null) {
			clearCache(serviceDetails);
		}

		return serviceDetails;
	}

	@Override
	public ServiceDetails updateImpl(ServiceDetails serviceDetails) {
		boolean isNew = serviceDetails.isNew();

		if (!(serviceDetails instanceof ServiceDetailsModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(serviceDetails.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					serviceDetails);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in serviceDetails proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ServiceDetails implementation " +
					serviceDetails.getClass());
		}

		ServiceDetailsModelImpl serviceDetailsModelImpl =
			(ServiceDetailsModelImpl)serviceDetails;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (serviceDetails.getCreateDate() == null)) {
			if (serviceContext == null) {
				serviceDetails.setCreateDate(date);
			}
			else {
				serviceDetails.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!serviceDetailsModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				serviceDetails.setModifiedDate(date);
			}
			else {
				serviceDetails.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(serviceDetails);
			}
			else {
				serviceDetails = (ServiceDetails)session.merge(serviceDetails);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			ServiceDetailsImpl.class, serviceDetailsModelImpl, false, true);

		cacheUniqueFindersCache(serviceDetailsModelImpl);

		if (isNew) {
			serviceDetails.setNew(false);
		}

		serviceDetails.resetOriginalValues();

		return serviceDetails;
	}

	/**
	 * Returns the service details with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the service details
	 * @return the service details
	 * @throws NoSuchServiceDetailsException if a service details with the primary key could not be found
	 */
	@Override
	public ServiceDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchServiceDetailsException {

		ServiceDetails serviceDetails = fetchByPrimaryKey(primaryKey);

		if (serviceDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchServiceDetailsException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return serviceDetails;
	}

	/**
	 * Returns the service details with the primary key or throws a <code>NoSuchServiceDetailsException</code> if it could not be found.
	 *
	 * @param serviceDetailsId the primary key of the service details
	 * @return the service details
	 * @throws NoSuchServiceDetailsException if a service details with the primary key could not be found
	 */
	@Override
	public ServiceDetails findByPrimaryKey(long serviceDetailsId)
		throws NoSuchServiceDetailsException {

		return findByPrimaryKey((Serializable)serviceDetailsId);
	}

	/**
	 * Returns the service details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param serviceDetailsId the primary key of the service details
	 * @return the service details, or <code>null</code> if a service details with the primary key could not be found
	 */
	@Override
	public ServiceDetails fetchByPrimaryKey(long serviceDetailsId) {
		return fetchByPrimaryKey((Serializable)serviceDetailsId);
	}

	/**
	 * Returns all the service detailses.
	 *
	 * @return the service detailses
	 */
	@Override
	public List<ServiceDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the service detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServiceDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of service detailses
	 * @param end the upper bound of the range of service detailses (not inclusive)
	 * @return the range of service detailses
	 */
	@Override
	public List<ServiceDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the service detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServiceDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of service detailses
	 * @param end the upper bound of the range of service detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of service detailses
	 */
	@Override
	public List<ServiceDetails> findAll(
		int start, int end,
		OrderByComparator<ServiceDetails> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the service detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServiceDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of service detailses
	 * @param end the upper bound of the range of service detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of service detailses
	 */
	@Override
	public List<ServiceDetails> findAll(
		int start, int end, OrderByComparator<ServiceDetails> orderByComparator,
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

		List<ServiceDetails> list = null;

		if (useFinderCache) {
			list = (List<ServiceDetails>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SERVICEDETAILS);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SERVICEDETAILS;

				sql = sql.concat(ServiceDetailsModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ServiceDetails>)QueryUtil.list(
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
	 * Removes all the service detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ServiceDetails serviceDetails : findAll()) {
			remove(serviceDetails);
		}
	}

	/**
	 * Returns the number of service detailses.
	 *
	 * @return the number of service detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SERVICEDETAILS);

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
		return "serviceDetailsId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SERVICEDETAILS;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ServiceDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the service details persistence.
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

		_finderPathFetchByserviceDetailsId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByserviceDetailsId",
			new String[] {Long.class.getName()},
			new String[] {"serviceDetailsId"}, true);

		_finderPathCountByserviceDetailsId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByserviceDetailsId", new String[] {Long.class.getName()},
			new String[] {"serviceDetailsId"}, false);

		ServiceDetailsUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		ServiceDetailsUtil.setPersistence(null);

		entityCache.removeCache(ServiceDetailsImpl.class.getName());
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

	private static final String _SQL_SELECT_SERVICEDETAILS =
		"SELECT serviceDetails FROM ServiceDetails serviceDetails";

	private static final String _SQL_SELECT_SERVICEDETAILS_WHERE =
		"SELECT serviceDetails FROM ServiceDetails serviceDetails WHERE ";

	private static final String _SQL_COUNT_SERVICEDETAILS =
		"SELECT COUNT(serviceDetails) FROM ServiceDetails serviceDetails";

	private static final String _SQL_COUNT_SERVICEDETAILS_WHERE =
		"SELECT COUNT(serviceDetails) FROM ServiceDetails serviceDetails WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "serviceDetails.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ServiceDetails exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No ServiceDetails exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ServiceDetailsPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}