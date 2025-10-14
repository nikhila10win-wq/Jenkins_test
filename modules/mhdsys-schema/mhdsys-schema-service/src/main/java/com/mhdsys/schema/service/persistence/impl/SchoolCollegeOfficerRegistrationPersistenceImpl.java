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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;

import com.mhdsys.schema.exception.NoSuchSchoolCollegeOfficerRegistrationException;
import com.mhdsys.schema.model.SchoolCollegeOfficerRegistration;
import com.mhdsys.schema.model.SchoolCollegeOfficerRegistrationTable;
import com.mhdsys.schema.model.impl.SchoolCollegeOfficerRegistrationImpl;
import com.mhdsys.schema.model.impl.SchoolCollegeOfficerRegistrationModelImpl;
import com.mhdsys.schema.service.persistence.SchoolCollegeOfficerRegistrationPersistence;
import com.mhdsys.schema.service.persistence.SchoolCollegeOfficerRegistrationUtil;
import com.mhdsys.schema.service.persistence.impl.constants.mhdsysPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the school college officer registration service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = SchoolCollegeOfficerRegistrationPersistence.class)
public class SchoolCollegeOfficerRegistrationPersistenceImpl
	extends BasePersistenceImpl<SchoolCollegeOfficerRegistration>
	implements SchoolCollegeOfficerRegistrationPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SchoolCollegeOfficerRegistrationUtil</code> to access the school college officer registration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SchoolCollegeOfficerRegistrationImpl.class.getName();

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
	 * Returns the school college officer registration where userId = &#63; or throws a <code>NoSuchSchoolCollegeOfficerRegistrationException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching school college officer registration
	 * @throws NoSuchSchoolCollegeOfficerRegistrationException if a matching school college officer registration could not be found
	 */
	@Override
	public SchoolCollegeOfficerRegistration findByUserId(long userId)
		throws NoSuchSchoolCollegeOfficerRegistrationException {

		SchoolCollegeOfficerRegistration schoolCollegeOfficerRegistration =
			fetchByUserId(userId);

		if (schoolCollegeOfficerRegistration == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("userId=");
			sb.append(userId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchSchoolCollegeOfficerRegistrationException(
				sb.toString());
		}

		return schoolCollegeOfficerRegistration;
	}

	/**
	 * Returns the school college officer registration where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching school college officer registration, or <code>null</code> if a matching school college officer registration could not be found
	 */
	@Override
	public SchoolCollegeOfficerRegistration fetchByUserId(long userId) {
		return fetchByUserId(userId, true);
	}

	/**
	 * Returns the school college officer registration where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching school college officer registration, or <code>null</code> if a matching school college officer registration could not be found
	 */
	@Override
	public SchoolCollegeOfficerRegistration fetchByUserId(
		long userId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {userId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUserId, finderArgs, this);
		}

		if (result instanceof SchoolCollegeOfficerRegistration) {
			SchoolCollegeOfficerRegistration schoolCollegeOfficerRegistration =
				(SchoolCollegeOfficerRegistration)result;

			if (userId != schoolCollegeOfficerRegistration.getUserId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_SCHOOLCOLLEGEOFFICERREGISTRATION_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				List<SchoolCollegeOfficerRegistration> list = query.list();

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
								"SchoolCollegeOfficerRegistrationPersistenceImpl.fetchByUserId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					SchoolCollegeOfficerRegistration
						schoolCollegeOfficerRegistration = list.get(0);

					result = schoolCollegeOfficerRegistration;

					cacheResult(schoolCollegeOfficerRegistration);
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
			return (SchoolCollegeOfficerRegistration)result;
		}
	}

	/**
	 * Removes the school college officer registration where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the school college officer registration that was removed
	 */
	@Override
	public SchoolCollegeOfficerRegistration removeByUserId(long userId)
		throws NoSuchSchoolCollegeOfficerRegistrationException {

		SchoolCollegeOfficerRegistration schoolCollegeOfficerRegistration =
			findByUserId(userId);

		return remove(schoolCollegeOfficerRegistration);
	}

	/**
	 * Returns the number of school college officer registrations where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching school college officer registrations
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SCHOOLCOLLEGEOFFICERREGISTRATION_WHERE);

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
		"schoolCollegeOfficerRegistration.userId = ?";

	private FinderPath _finderPathFetchByUserIdAndCurrentDesignation;
	private FinderPath _finderPathCountByUserIdAndCurrentDesignation;

	/**
	 * Returns the school college officer registration where userId = &#63; and currentDesignation = &#63; or throws a <code>NoSuchSchoolCollegeOfficerRegistrationException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @param currentDesignation the current designation
	 * @return the matching school college officer registration
	 * @throws NoSuchSchoolCollegeOfficerRegistrationException if a matching school college officer registration could not be found
	 */
	@Override
	public SchoolCollegeOfficerRegistration findByUserIdAndCurrentDesignation(
			long userId, long currentDesignation)
		throws NoSuchSchoolCollegeOfficerRegistrationException {

		SchoolCollegeOfficerRegistration schoolCollegeOfficerRegistration =
			fetchByUserIdAndCurrentDesignation(userId, currentDesignation);

		if (schoolCollegeOfficerRegistration == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("userId=");
			sb.append(userId);

			sb.append(", currentDesignation=");
			sb.append(currentDesignation);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchSchoolCollegeOfficerRegistrationException(
				sb.toString());
		}

		return schoolCollegeOfficerRegistration;
	}

	/**
	 * Returns the school college officer registration where userId = &#63; and currentDesignation = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param currentDesignation the current designation
	 * @return the matching school college officer registration, or <code>null</code> if a matching school college officer registration could not be found
	 */
	@Override
	public SchoolCollegeOfficerRegistration fetchByUserIdAndCurrentDesignation(
		long userId, long currentDesignation) {

		return fetchByUserIdAndCurrentDesignation(
			userId, currentDesignation, true);
	}

	/**
	 * Returns the school college officer registration where userId = &#63; and currentDesignation = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param currentDesignation the current designation
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching school college officer registration, or <code>null</code> if a matching school college officer registration could not be found
	 */
	@Override
	public SchoolCollegeOfficerRegistration fetchByUserIdAndCurrentDesignation(
		long userId, long currentDesignation, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {userId, currentDesignation};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUserIdAndCurrentDesignation, finderArgs,
				this);
		}

		if (result instanceof SchoolCollegeOfficerRegistration) {
			SchoolCollegeOfficerRegistration schoolCollegeOfficerRegistration =
				(SchoolCollegeOfficerRegistration)result;

			if ((userId != schoolCollegeOfficerRegistration.getUserId()) ||
				(currentDesignation !=
					schoolCollegeOfficerRegistration.getCurrentDesignation())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_SCHOOLCOLLEGEOFFICERREGISTRATION_WHERE);

			sb.append(_FINDER_COLUMN_USERIDANDCURRENTDESIGNATION_USERID_2);

			sb.append(
				_FINDER_COLUMN_USERIDANDCURRENTDESIGNATION_CURRENTDESIGNATION_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				queryPos.add(currentDesignation);

				List<SchoolCollegeOfficerRegistration> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUserIdAndCurrentDesignation,
							finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {
									userId, currentDesignation
								};
							}

							_log.warn(
								"SchoolCollegeOfficerRegistrationPersistenceImpl.fetchByUserIdAndCurrentDesignation(long, long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					SchoolCollegeOfficerRegistration
						schoolCollegeOfficerRegistration = list.get(0);

					result = schoolCollegeOfficerRegistration;

					cacheResult(schoolCollegeOfficerRegistration);
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
			return (SchoolCollegeOfficerRegistration)result;
		}
	}

	/**
	 * Removes the school college officer registration where userId = &#63; and currentDesignation = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param currentDesignation the current designation
	 * @return the school college officer registration that was removed
	 */
	@Override
	public SchoolCollegeOfficerRegistration removeByUserIdAndCurrentDesignation(
			long userId, long currentDesignation)
		throws NoSuchSchoolCollegeOfficerRegistrationException {

		SchoolCollegeOfficerRegistration schoolCollegeOfficerRegistration =
			findByUserIdAndCurrentDesignation(userId, currentDesignation);

		return remove(schoolCollegeOfficerRegistration);
	}

	/**
	 * Returns the number of school college officer registrations where userId = &#63; and currentDesignation = &#63;.
	 *
	 * @param userId the user ID
	 * @param currentDesignation the current designation
	 * @return the number of matching school college officer registrations
	 */
	@Override
	public int countByUserIdAndCurrentDesignation(
		long userId, long currentDesignation) {

		FinderPath finderPath = _finderPathCountByUserIdAndCurrentDesignation;

		Object[] finderArgs = new Object[] {userId, currentDesignation};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_SCHOOLCOLLEGEOFFICERREGISTRATION_WHERE);

			sb.append(_FINDER_COLUMN_USERIDANDCURRENTDESIGNATION_USERID_2);

			sb.append(
				_FINDER_COLUMN_USERIDANDCURRENTDESIGNATION_CURRENTDESIGNATION_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				queryPos.add(currentDesignation);

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
		_FINDER_COLUMN_USERIDANDCURRENTDESIGNATION_USERID_2 =
			"schoolCollegeOfficerRegistration.userId = ? AND ";

	private static final String
		_FINDER_COLUMN_USERIDANDCURRENTDESIGNATION_CURRENTDESIGNATION_2 =
			"schoolCollegeOfficerRegistration.currentDesignation = ?";

	public SchoolCollegeOfficerRegistrationPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("type", "type_");

		setDBColumnNames(dbColumnNames);

		setModelClass(SchoolCollegeOfficerRegistration.class);

		setModelImplClass(SchoolCollegeOfficerRegistrationImpl.class);
		setModelPKClass(long.class);

		setTable(SchoolCollegeOfficerRegistrationTable.INSTANCE);
	}

	/**
	 * Caches the school college officer registration in the entity cache if it is enabled.
	 *
	 * @param schoolCollegeOfficerRegistration the school college officer registration
	 */
	@Override
	public void cacheResult(
		SchoolCollegeOfficerRegistration schoolCollegeOfficerRegistration) {

		entityCache.putResult(
			SchoolCollegeOfficerRegistrationImpl.class,
			schoolCollegeOfficerRegistration.getPrimaryKey(),
			schoolCollegeOfficerRegistration);

		finderCache.putResult(
			_finderPathFetchByUserId,
			new Object[] {schoolCollegeOfficerRegistration.getUserId()},
			schoolCollegeOfficerRegistration);

		finderCache.putResult(
			_finderPathFetchByUserIdAndCurrentDesignation,
			new Object[] {
				schoolCollegeOfficerRegistration.getUserId(),
				schoolCollegeOfficerRegistration.getCurrentDesignation()
			},
			schoolCollegeOfficerRegistration);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the school college officer registrations in the entity cache if it is enabled.
	 *
	 * @param schoolCollegeOfficerRegistrations the school college officer registrations
	 */
	@Override
	public void cacheResult(
		List<SchoolCollegeOfficerRegistration>
			schoolCollegeOfficerRegistrations) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (schoolCollegeOfficerRegistrations.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (SchoolCollegeOfficerRegistration schoolCollegeOfficerRegistration :
				schoolCollegeOfficerRegistrations) {

			if (entityCache.getResult(
					SchoolCollegeOfficerRegistrationImpl.class,
					schoolCollegeOfficerRegistration.getPrimaryKey()) == null) {

				cacheResult(schoolCollegeOfficerRegistration);
			}
		}
	}

	/**
	 * Clears the cache for all school college officer registrations.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SchoolCollegeOfficerRegistrationImpl.class);

		finderCache.clearCache(SchoolCollegeOfficerRegistrationImpl.class);
	}

	/**
	 * Clears the cache for the school college officer registration.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		SchoolCollegeOfficerRegistration schoolCollegeOfficerRegistration) {

		entityCache.removeResult(
			SchoolCollegeOfficerRegistrationImpl.class,
			schoolCollegeOfficerRegistration);
	}

	@Override
	public void clearCache(
		List<SchoolCollegeOfficerRegistration>
			schoolCollegeOfficerRegistrations) {

		for (SchoolCollegeOfficerRegistration schoolCollegeOfficerRegistration :
				schoolCollegeOfficerRegistrations) {

			entityCache.removeResult(
				SchoolCollegeOfficerRegistrationImpl.class,
				schoolCollegeOfficerRegistration);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(SchoolCollegeOfficerRegistrationImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				SchoolCollegeOfficerRegistrationImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		SchoolCollegeOfficerRegistrationModelImpl
			schoolCollegeOfficerRegistrationModelImpl) {

		Object[] args = new Object[] {
			schoolCollegeOfficerRegistrationModelImpl.getUserId()
		};

		finderCache.putResult(_finderPathCountByUserId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByUserId, args,
			schoolCollegeOfficerRegistrationModelImpl);

		args = new Object[] {
			schoolCollegeOfficerRegistrationModelImpl.getUserId(),
			schoolCollegeOfficerRegistrationModelImpl.getCurrentDesignation()
		};

		finderCache.putResult(
			_finderPathCountByUserIdAndCurrentDesignation, args,
			Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByUserIdAndCurrentDesignation, args,
			schoolCollegeOfficerRegistrationModelImpl);
	}

	/**
	 * Creates a new school college officer registration with the primary key. Does not add the school college officer registration to the database.
	 *
	 * @param schoolCollegeOfficerRegistrationId the primary key for the new school college officer registration
	 * @return the new school college officer registration
	 */
	@Override
	public SchoolCollegeOfficerRegistration create(
		long schoolCollegeOfficerRegistrationId) {

		SchoolCollegeOfficerRegistration schoolCollegeOfficerRegistration =
			new SchoolCollegeOfficerRegistrationImpl();

		schoolCollegeOfficerRegistration.setNew(true);
		schoolCollegeOfficerRegistration.setPrimaryKey(
			schoolCollegeOfficerRegistrationId);

		return schoolCollegeOfficerRegistration;
	}

	/**
	 * Removes the school college officer registration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param schoolCollegeOfficerRegistrationId the primary key of the school college officer registration
	 * @return the school college officer registration that was removed
	 * @throws NoSuchSchoolCollegeOfficerRegistrationException if a school college officer registration with the primary key could not be found
	 */
	@Override
	public SchoolCollegeOfficerRegistration remove(
			long schoolCollegeOfficerRegistrationId)
		throws NoSuchSchoolCollegeOfficerRegistrationException {

		return remove((Serializable)schoolCollegeOfficerRegistrationId);
	}

	/**
	 * Removes the school college officer registration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the school college officer registration
	 * @return the school college officer registration that was removed
	 * @throws NoSuchSchoolCollegeOfficerRegistrationException if a school college officer registration with the primary key could not be found
	 */
	@Override
	public SchoolCollegeOfficerRegistration remove(Serializable primaryKey)
		throws NoSuchSchoolCollegeOfficerRegistrationException {

		Session session = null;

		try {
			session = openSession();

			SchoolCollegeOfficerRegistration schoolCollegeOfficerRegistration =
				(SchoolCollegeOfficerRegistration)session.get(
					SchoolCollegeOfficerRegistrationImpl.class, primaryKey);

			if (schoolCollegeOfficerRegistration == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSchoolCollegeOfficerRegistrationException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(schoolCollegeOfficerRegistration);
		}
		catch (NoSuchSchoolCollegeOfficerRegistrationException
					noSuchEntityException) {

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
	protected SchoolCollegeOfficerRegistration removeImpl(
		SchoolCollegeOfficerRegistration schoolCollegeOfficerRegistration) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(schoolCollegeOfficerRegistration)) {
				schoolCollegeOfficerRegistration =
					(SchoolCollegeOfficerRegistration)session.get(
						SchoolCollegeOfficerRegistrationImpl.class,
						schoolCollegeOfficerRegistration.getPrimaryKeyObj());
			}

			if (schoolCollegeOfficerRegistration != null) {
				session.delete(schoolCollegeOfficerRegistration);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (schoolCollegeOfficerRegistration != null) {
			clearCache(schoolCollegeOfficerRegistration);
		}

		return schoolCollegeOfficerRegistration;
	}

	@Override
	public SchoolCollegeOfficerRegistration updateImpl(
		SchoolCollegeOfficerRegistration schoolCollegeOfficerRegistration) {

		boolean isNew = schoolCollegeOfficerRegistration.isNew();

		if (!(schoolCollegeOfficerRegistration instanceof
				SchoolCollegeOfficerRegistrationModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(
					schoolCollegeOfficerRegistration.getClass())) {

				invocationHandler = ProxyUtil.getInvocationHandler(
					schoolCollegeOfficerRegistration);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in schoolCollegeOfficerRegistration proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SchoolCollegeOfficerRegistration implementation " +
					schoolCollegeOfficerRegistration.getClass());
		}

		SchoolCollegeOfficerRegistrationModelImpl
			schoolCollegeOfficerRegistrationModelImpl =
				(SchoolCollegeOfficerRegistrationModelImpl)
					schoolCollegeOfficerRegistration;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew &&
			(schoolCollegeOfficerRegistration.getCreateDate() == null)) {

			if (serviceContext == null) {
				schoolCollegeOfficerRegistration.setCreateDate(date);
			}
			else {
				schoolCollegeOfficerRegistration.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!schoolCollegeOfficerRegistrationModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				schoolCollegeOfficerRegistration.setModifiedDate(date);
			}
			else {
				schoolCollegeOfficerRegistration.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(schoolCollegeOfficerRegistration);
			}
			else {
				schoolCollegeOfficerRegistration =
					(SchoolCollegeOfficerRegistration)session.merge(
						schoolCollegeOfficerRegistration);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			SchoolCollegeOfficerRegistrationImpl.class,
			schoolCollegeOfficerRegistrationModelImpl, false, true);

		cacheUniqueFindersCache(schoolCollegeOfficerRegistrationModelImpl);

		if (isNew) {
			schoolCollegeOfficerRegistration.setNew(false);
		}

		schoolCollegeOfficerRegistration.resetOriginalValues();

		return schoolCollegeOfficerRegistration;
	}

	/**
	 * Returns the school college officer registration with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the school college officer registration
	 * @return the school college officer registration
	 * @throws NoSuchSchoolCollegeOfficerRegistrationException if a school college officer registration with the primary key could not be found
	 */
	@Override
	public SchoolCollegeOfficerRegistration findByPrimaryKey(
			Serializable primaryKey)
		throws NoSuchSchoolCollegeOfficerRegistrationException {

		SchoolCollegeOfficerRegistration schoolCollegeOfficerRegistration =
			fetchByPrimaryKey(primaryKey);

		if (schoolCollegeOfficerRegistration == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSchoolCollegeOfficerRegistrationException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return schoolCollegeOfficerRegistration;
	}

	/**
	 * Returns the school college officer registration with the primary key or throws a <code>NoSuchSchoolCollegeOfficerRegistrationException</code> if it could not be found.
	 *
	 * @param schoolCollegeOfficerRegistrationId the primary key of the school college officer registration
	 * @return the school college officer registration
	 * @throws NoSuchSchoolCollegeOfficerRegistrationException if a school college officer registration with the primary key could not be found
	 */
	@Override
	public SchoolCollegeOfficerRegistration findByPrimaryKey(
			long schoolCollegeOfficerRegistrationId)
		throws NoSuchSchoolCollegeOfficerRegistrationException {

		return findByPrimaryKey(
			(Serializable)schoolCollegeOfficerRegistrationId);
	}

	/**
	 * Returns the school college officer registration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param schoolCollegeOfficerRegistrationId the primary key of the school college officer registration
	 * @return the school college officer registration, or <code>null</code> if a school college officer registration with the primary key could not be found
	 */
	@Override
	public SchoolCollegeOfficerRegistration fetchByPrimaryKey(
		long schoolCollegeOfficerRegistrationId) {

		return fetchByPrimaryKey(
			(Serializable)schoolCollegeOfficerRegistrationId);
	}

	/**
	 * Returns all the school college officer registrations.
	 *
	 * @return the school college officer registrations
	 */
	@Override
	public List<SchoolCollegeOfficerRegistration> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the school college officer registrations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoolCollegeOfficerRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of school college officer registrations
	 * @param end the upper bound of the range of school college officer registrations (not inclusive)
	 * @return the range of school college officer registrations
	 */
	@Override
	public List<SchoolCollegeOfficerRegistration> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the school college officer registrations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoolCollegeOfficerRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of school college officer registrations
	 * @param end the upper bound of the range of school college officer registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of school college officer registrations
	 */
	@Override
	public List<SchoolCollegeOfficerRegistration> findAll(
		int start, int end,
		OrderByComparator<SchoolCollegeOfficerRegistration> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the school college officer registrations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoolCollegeOfficerRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of school college officer registrations
	 * @param end the upper bound of the range of school college officer registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of school college officer registrations
	 */
	@Override
	public List<SchoolCollegeOfficerRegistration> findAll(
		int start, int end,
		OrderByComparator<SchoolCollegeOfficerRegistration> orderByComparator,
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

		List<SchoolCollegeOfficerRegistration> list = null;

		if (useFinderCache) {
			list =
				(List<SchoolCollegeOfficerRegistration>)finderCache.getResult(
					finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SCHOOLCOLLEGEOFFICERREGISTRATION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SCHOOLCOLLEGEOFFICERREGISTRATION;

				sql = sql.concat(
					SchoolCollegeOfficerRegistrationModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<SchoolCollegeOfficerRegistration>)QueryUtil.list(
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
	 * Removes all the school college officer registrations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SchoolCollegeOfficerRegistration schoolCollegeOfficerRegistration :
				findAll()) {

			remove(schoolCollegeOfficerRegistration);
		}
	}

	/**
	 * Returns the number of school college officer registrations.
	 *
	 * @return the number of school college officer registrations
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_SCHOOLCOLLEGEOFFICERREGISTRATION);

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
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "schoolCollegeOfficerRegistrationId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SCHOOLCOLLEGEOFFICERREGISTRATION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SchoolCollegeOfficerRegistrationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the school college officer registration persistence.
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

		_finderPathFetchByUserIdAndCurrentDesignation = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByUserIdAndCurrentDesignation",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"userId", "currentDesignation"}, true);

		_finderPathCountByUserIdAndCurrentDesignation = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUserIdAndCurrentDesignation",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"userId", "currentDesignation"}, false);

		SchoolCollegeOfficerRegistrationUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		SchoolCollegeOfficerRegistrationUtil.setPersistence(null);

		entityCache.removeCache(
			SchoolCollegeOfficerRegistrationImpl.class.getName());
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

	private static final String _SQL_SELECT_SCHOOLCOLLEGEOFFICERREGISTRATION =
		"SELECT schoolCollegeOfficerRegistration FROM SchoolCollegeOfficerRegistration schoolCollegeOfficerRegistration";

	private static final String
		_SQL_SELECT_SCHOOLCOLLEGEOFFICERREGISTRATION_WHERE =
			"SELECT schoolCollegeOfficerRegistration FROM SchoolCollegeOfficerRegistration schoolCollegeOfficerRegistration WHERE ";

	private static final String _SQL_COUNT_SCHOOLCOLLEGEOFFICERREGISTRATION =
		"SELECT COUNT(schoolCollegeOfficerRegistration) FROM SchoolCollegeOfficerRegistration schoolCollegeOfficerRegistration";

	private static final String
		_SQL_COUNT_SCHOOLCOLLEGEOFFICERREGISTRATION_WHERE =
			"SELECT COUNT(schoolCollegeOfficerRegistration) FROM SchoolCollegeOfficerRegistration schoolCollegeOfficerRegistration WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"schoolCollegeOfficerRegistration.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SchoolCollegeOfficerRegistration exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No SchoolCollegeOfficerRegistration exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		SchoolCollegeOfficerRegistrationPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"type"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}