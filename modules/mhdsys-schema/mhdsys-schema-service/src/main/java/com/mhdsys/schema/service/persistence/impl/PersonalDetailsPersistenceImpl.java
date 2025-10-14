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

import com.mhdsys.schema.exception.NoSuchPersonalDetailsException;
import com.mhdsys.schema.model.PersonalDetails;
import com.mhdsys.schema.model.PersonalDetailsTable;
import com.mhdsys.schema.model.impl.PersonalDetailsImpl;
import com.mhdsys.schema.model.impl.PersonalDetailsModelImpl;
import com.mhdsys.schema.service.persistence.PersonalDetailsPersistence;
import com.mhdsys.schema.service.persistence.PersonalDetailsUtil;
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
 * The persistence implementation for the personal details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = PersonalDetailsPersistence.class)
public class PersonalDetailsPersistenceImpl
	extends BasePersistenceImpl<PersonalDetails>
	implements PersonalDetailsPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>PersonalDetailsUtil</code> to access the personal details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		PersonalDetailsImpl.class.getName();

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
	 * Returns the personal details where userId = &#63; or throws a <code>NoSuchPersonalDetailsException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching personal details
	 * @throws NoSuchPersonalDetailsException if a matching personal details could not be found
	 */
	@Override
	public PersonalDetails findByUserId(long userId)
		throws NoSuchPersonalDetailsException {

		PersonalDetails personalDetails = fetchByUserId(userId);

		if (personalDetails == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("userId=");
			sb.append(userId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchPersonalDetailsException(sb.toString());
		}

		return personalDetails;
	}

	/**
	 * Returns the personal details where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching personal details, or <code>null</code> if a matching personal details could not be found
	 */
	@Override
	public PersonalDetails fetchByUserId(long userId) {
		return fetchByUserId(userId, true);
	}

	/**
	 * Returns the personal details where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching personal details, or <code>null</code> if a matching personal details could not be found
	 */
	@Override
	public PersonalDetails fetchByUserId(long userId, boolean useFinderCache) {
		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {userId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUserId, finderArgs, this);
		}

		if (result instanceof PersonalDetails) {
			PersonalDetails personalDetails = (PersonalDetails)result;

			if (userId != personalDetails.getUserId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_PERSONALDETAILS_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				List<PersonalDetails> list = query.list();

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
								"PersonalDetailsPersistenceImpl.fetchByUserId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					PersonalDetails personalDetails = list.get(0);

					result = personalDetails;

					cacheResult(personalDetails);
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
			return (PersonalDetails)result;
		}
	}

	/**
	 * Removes the personal details where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the personal details that was removed
	 */
	@Override
	public PersonalDetails removeByUserId(long userId)
		throws NoSuchPersonalDetailsException {

		PersonalDetails personalDetails = findByUserId(userId);

		return remove(personalDetails);
	}

	/**
	 * Returns the number of personal detailses where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching personal detailses
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PERSONALDETAILS_WHERE);

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
		"personalDetails.userId = ?";

	private FinderPath _finderPathFetchByEstablishmentDetailId;
	private FinderPath _finderPathCountByEstablishmentDetailId;

	/**
	 * Returns the personal details where establishmentDetailId = &#63; or throws a <code>NoSuchPersonalDetailsException</code> if it could not be found.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @return the matching personal details
	 * @throws NoSuchPersonalDetailsException if a matching personal details could not be found
	 */
	@Override
	public PersonalDetails findByEstablishmentDetailId(
			long establishmentDetailId)
		throws NoSuchPersonalDetailsException {

		PersonalDetails personalDetails = fetchByEstablishmentDetailId(
			establishmentDetailId);

		if (personalDetails == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("establishmentDetailId=");
			sb.append(establishmentDetailId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchPersonalDetailsException(sb.toString());
		}

		return personalDetails;
	}

	/**
	 * Returns the personal details where establishmentDetailId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @return the matching personal details, or <code>null</code> if a matching personal details could not be found
	 */
	@Override
	public PersonalDetails fetchByEstablishmentDetailId(
		long establishmentDetailId) {

		return fetchByEstablishmentDetailId(establishmentDetailId, true);
	}

	/**
	 * Returns the personal details where establishmentDetailId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching personal details, or <code>null</code> if a matching personal details could not be found
	 */
	@Override
	public PersonalDetails fetchByEstablishmentDetailId(
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

		if (result instanceof PersonalDetails) {
			PersonalDetails personalDetails = (PersonalDetails)result;

			if (establishmentDetailId !=
					personalDetails.getEstablishmentDetailId()) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_PERSONALDETAILS_WHERE);

			sb.append(
				_FINDER_COLUMN_ESTABLISHMENTDETAILID_ESTABLISHMENTDETAILID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(establishmentDetailId);

				List<PersonalDetails> list = query.list();

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
								"PersonalDetailsPersistenceImpl.fetchByEstablishmentDetailId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					PersonalDetails personalDetails = list.get(0);

					result = personalDetails;

					cacheResult(personalDetails);
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
			return (PersonalDetails)result;
		}
	}

	/**
	 * Removes the personal details where establishmentDetailId = &#63; from the database.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @return the personal details that was removed
	 */
	@Override
	public PersonalDetails removeByEstablishmentDetailId(
			long establishmentDetailId)
		throws NoSuchPersonalDetailsException {

		PersonalDetails personalDetails = findByEstablishmentDetailId(
			establishmentDetailId);

		return remove(personalDetails);
	}

	/**
	 * Returns the number of personal detailses where establishmentDetailId = &#63;.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @return the number of matching personal detailses
	 */
	@Override
	public int countByEstablishmentDetailId(long establishmentDetailId) {
		FinderPath finderPath = _finderPathCountByEstablishmentDetailId;

		Object[] finderArgs = new Object[] {establishmentDetailId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PERSONALDETAILS_WHERE);

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
			"personalDetails.establishmentDetailId = ?";

	private FinderPath _finderPathFetchBypersonalDetailsId;
	private FinderPath _finderPathCountBypersonalDetailsId;

	/**
	 * Returns the personal details where personalDetailsId = &#63; or throws a <code>NoSuchPersonalDetailsException</code> if it could not be found.
	 *
	 * @param personalDetailsId the personal details ID
	 * @return the matching personal details
	 * @throws NoSuchPersonalDetailsException if a matching personal details could not be found
	 */
	@Override
	public PersonalDetails findBypersonalDetailsId(long personalDetailsId)
		throws NoSuchPersonalDetailsException {

		PersonalDetails personalDetails = fetchBypersonalDetailsId(
			personalDetailsId);

		if (personalDetails == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("personalDetailsId=");
			sb.append(personalDetailsId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchPersonalDetailsException(sb.toString());
		}

		return personalDetails;
	}

	/**
	 * Returns the personal details where personalDetailsId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param personalDetailsId the personal details ID
	 * @return the matching personal details, or <code>null</code> if a matching personal details could not be found
	 */
	@Override
	public PersonalDetails fetchBypersonalDetailsId(long personalDetailsId) {
		return fetchBypersonalDetailsId(personalDetailsId, true);
	}

	/**
	 * Returns the personal details where personalDetailsId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param personalDetailsId the personal details ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching personal details, or <code>null</code> if a matching personal details could not be found
	 */
	@Override
	public PersonalDetails fetchBypersonalDetailsId(
		long personalDetailsId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {personalDetailsId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchBypersonalDetailsId, finderArgs, this);
		}

		if (result instanceof PersonalDetails) {
			PersonalDetails personalDetails = (PersonalDetails)result;

			if (personalDetailsId != personalDetails.getPersonalDetailsId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_PERSONALDETAILS_WHERE);

			sb.append(_FINDER_COLUMN_PERSONALDETAILSID_PERSONALDETAILSID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(personalDetailsId);

				List<PersonalDetails> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchBypersonalDetailsId, finderArgs,
							list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {personalDetailsId};
							}

							_log.warn(
								"PersonalDetailsPersistenceImpl.fetchBypersonalDetailsId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					PersonalDetails personalDetails = list.get(0);

					result = personalDetails;

					cacheResult(personalDetails);
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
			return (PersonalDetails)result;
		}
	}

	/**
	 * Removes the personal details where personalDetailsId = &#63; from the database.
	 *
	 * @param personalDetailsId the personal details ID
	 * @return the personal details that was removed
	 */
	@Override
	public PersonalDetails removeBypersonalDetailsId(long personalDetailsId)
		throws NoSuchPersonalDetailsException {

		PersonalDetails personalDetails = findBypersonalDetailsId(
			personalDetailsId);

		return remove(personalDetails);
	}

	/**
	 * Returns the number of personal detailses where personalDetailsId = &#63;.
	 *
	 * @param personalDetailsId the personal details ID
	 * @return the number of matching personal detailses
	 */
	@Override
	public int countBypersonalDetailsId(long personalDetailsId) {
		FinderPath finderPath = _finderPathCountBypersonalDetailsId;

		Object[] finderArgs = new Object[] {personalDetailsId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PERSONALDETAILS_WHERE);

			sb.append(_FINDER_COLUMN_PERSONALDETAILSID_PERSONALDETAILSID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(personalDetailsId);

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
		_FINDER_COLUMN_PERSONALDETAILSID_PERSONALDETAILSID_2 =
			"personalDetails.personalDetailsId = ?";

	public PersonalDetailsPersistenceImpl() {
		setModelClass(PersonalDetails.class);

		setModelImplClass(PersonalDetailsImpl.class);
		setModelPKClass(long.class);

		setTable(PersonalDetailsTable.INSTANCE);
	}

	/**
	 * Caches the personal details in the entity cache if it is enabled.
	 *
	 * @param personalDetails the personal details
	 */
	@Override
	public void cacheResult(PersonalDetails personalDetails) {
		entityCache.putResult(
			PersonalDetailsImpl.class, personalDetails.getPrimaryKey(),
			personalDetails);

		finderCache.putResult(
			_finderPathFetchByUserId,
			new Object[] {personalDetails.getUserId()}, personalDetails);

		finderCache.putResult(
			_finderPathFetchByEstablishmentDetailId,
			new Object[] {personalDetails.getEstablishmentDetailId()},
			personalDetails);

		finderCache.putResult(
			_finderPathFetchBypersonalDetailsId,
			new Object[] {personalDetails.getPersonalDetailsId()},
			personalDetails);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the personal detailses in the entity cache if it is enabled.
	 *
	 * @param personalDetailses the personal detailses
	 */
	@Override
	public void cacheResult(List<PersonalDetails> personalDetailses) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (personalDetailses.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (PersonalDetails personalDetails : personalDetailses) {
			if (entityCache.getResult(
					PersonalDetailsImpl.class,
					personalDetails.getPrimaryKey()) == null) {

				cacheResult(personalDetails);
			}
		}
	}

	/**
	 * Clears the cache for all personal detailses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PersonalDetailsImpl.class);

		finderCache.clearCache(PersonalDetailsImpl.class);
	}

	/**
	 * Clears the cache for the personal details.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PersonalDetails personalDetails) {
		entityCache.removeResult(PersonalDetailsImpl.class, personalDetails);
	}

	@Override
	public void clearCache(List<PersonalDetails> personalDetailses) {
		for (PersonalDetails personalDetails : personalDetailses) {
			entityCache.removeResult(
				PersonalDetailsImpl.class, personalDetails);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(PersonalDetailsImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(PersonalDetailsImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		PersonalDetailsModelImpl personalDetailsModelImpl) {

		Object[] args = new Object[] {personalDetailsModelImpl.getUserId()};

		finderCache.putResult(_finderPathCountByUserId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByUserId, args, personalDetailsModelImpl);

		args = new Object[] {
			personalDetailsModelImpl.getEstablishmentDetailId()
		};

		finderCache.putResult(
			_finderPathCountByEstablishmentDetailId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByEstablishmentDetailId, args,
			personalDetailsModelImpl);

		args = new Object[] {personalDetailsModelImpl.getPersonalDetailsId()};

		finderCache.putResult(
			_finderPathCountBypersonalDetailsId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchBypersonalDetailsId, args,
			personalDetailsModelImpl);
	}

	/**
	 * Creates a new personal details with the primary key. Does not add the personal details to the database.
	 *
	 * @param personalDetailsId the primary key for the new personal details
	 * @return the new personal details
	 */
	@Override
	public PersonalDetails create(long personalDetailsId) {
		PersonalDetails personalDetails = new PersonalDetailsImpl();

		personalDetails.setNew(true);
		personalDetails.setPrimaryKey(personalDetailsId);

		return personalDetails;
	}

	/**
	 * Removes the personal details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param personalDetailsId the primary key of the personal details
	 * @return the personal details that was removed
	 * @throws NoSuchPersonalDetailsException if a personal details with the primary key could not be found
	 */
	@Override
	public PersonalDetails remove(long personalDetailsId)
		throws NoSuchPersonalDetailsException {

		return remove((Serializable)personalDetailsId);
	}

	/**
	 * Removes the personal details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the personal details
	 * @return the personal details that was removed
	 * @throws NoSuchPersonalDetailsException if a personal details with the primary key could not be found
	 */
	@Override
	public PersonalDetails remove(Serializable primaryKey)
		throws NoSuchPersonalDetailsException {

		Session session = null;

		try {
			session = openSession();

			PersonalDetails personalDetails = (PersonalDetails)session.get(
				PersonalDetailsImpl.class, primaryKey);

			if (personalDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPersonalDetailsException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(personalDetails);
		}
		catch (NoSuchPersonalDetailsException noSuchEntityException) {
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
	protected PersonalDetails removeImpl(PersonalDetails personalDetails) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(personalDetails)) {
				personalDetails = (PersonalDetails)session.get(
					PersonalDetailsImpl.class,
					personalDetails.getPrimaryKeyObj());
			}

			if (personalDetails != null) {
				session.delete(personalDetails);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (personalDetails != null) {
			clearCache(personalDetails);
		}

		return personalDetails;
	}

	@Override
	public PersonalDetails updateImpl(PersonalDetails personalDetails) {
		boolean isNew = personalDetails.isNew();

		if (!(personalDetails instanceof PersonalDetailsModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(personalDetails.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					personalDetails);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in personalDetails proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom PersonalDetails implementation " +
					personalDetails.getClass());
		}

		PersonalDetailsModelImpl personalDetailsModelImpl =
			(PersonalDetailsModelImpl)personalDetails;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (personalDetails.getCreateDate() == null)) {
			if (serviceContext == null) {
				personalDetails.setCreateDate(date);
			}
			else {
				personalDetails.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!personalDetailsModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				personalDetails.setModifiedDate(date);
			}
			else {
				personalDetails.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(personalDetails);
			}
			else {
				personalDetails = (PersonalDetails)session.merge(
					personalDetails);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			PersonalDetailsImpl.class, personalDetailsModelImpl, false, true);

		cacheUniqueFindersCache(personalDetailsModelImpl);

		if (isNew) {
			personalDetails.setNew(false);
		}

		personalDetails.resetOriginalValues();

		return personalDetails;
	}

	/**
	 * Returns the personal details with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the personal details
	 * @return the personal details
	 * @throws NoSuchPersonalDetailsException if a personal details with the primary key could not be found
	 */
	@Override
	public PersonalDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPersonalDetailsException {

		PersonalDetails personalDetails = fetchByPrimaryKey(primaryKey);

		if (personalDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPersonalDetailsException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return personalDetails;
	}

	/**
	 * Returns the personal details with the primary key or throws a <code>NoSuchPersonalDetailsException</code> if it could not be found.
	 *
	 * @param personalDetailsId the primary key of the personal details
	 * @return the personal details
	 * @throws NoSuchPersonalDetailsException if a personal details with the primary key could not be found
	 */
	@Override
	public PersonalDetails findByPrimaryKey(long personalDetailsId)
		throws NoSuchPersonalDetailsException {

		return findByPrimaryKey((Serializable)personalDetailsId);
	}

	/**
	 * Returns the personal details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param personalDetailsId the primary key of the personal details
	 * @return the personal details, or <code>null</code> if a personal details with the primary key could not be found
	 */
	@Override
	public PersonalDetails fetchByPrimaryKey(long personalDetailsId) {
		return fetchByPrimaryKey((Serializable)personalDetailsId);
	}

	/**
	 * Returns all the personal detailses.
	 *
	 * @return the personal detailses
	 */
	@Override
	public List<PersonalDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the personal detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PersonalDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of personal detailses
	 * @param end the upper bound of the range of personal detailses (not inclusive)
	 * @return the range of personal detailses
	 */
	@Override
	public List<PersonalDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the personal detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PersonalDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of personal detailses
	 * @param end the upper bound of the range of personal detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of personal detailses
	 */
	@Override
	public List<PersonalDetails> findAll(
		int start, int end,
		OrderByComparator<PersonalDetails> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the personal detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PersonalDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of personal detailses
	 * @param end the upper bound of the range of personal detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of personal detailses
	 */
	@Override
	public List<PersonalDetails> findAll(
		int start, int end,
		OrderByComparator<PersonalDetails> orderByComparator,
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

		List<PersonalDetails> list = null;

		if (useFinderCache) {
			list = (List<PersonalDetails>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_PERSONALDETAILS);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_PERSONALDETAILS;

				sql = sql.concat(PersonalDetailsModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<PersonalDetails>)QueryUtil.list(
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
	 * Removes all the personal detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PersonalDetails personalDetails : findAll()) {
			remove(personalDetails);
		}
	}

	/**
	 * Returns the number of personal detailses.
	 *
	 * @return the number of personal detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_PERSONALDETAILS);

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
		return "personalDetailsId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_PERSONALDETAILS;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return PersonalDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the personal details persistence.
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

		_finderPathFetchBypersonalDetailsId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchBypersonalDetailsId",
			new String[] {Long.class.getName()},
			new String[] {"personalDetailsId"}, true);

		_finderPathCountBypersonalDetailsId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBypersonalDetailsId", new String[] {Long.class.getName()},
			new String[] {"personalDetailsId"}, false);

		PersonalDetailsUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		PersonalDetailsUtil.setPersistence(null);

		entityCache.removeCache(PersonalDetailsImpl.class.getName());
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

	private static final String _SQL_SELECT_PERSONALDETAILS =
		"SELECT personalDetails FROM PersonalDetails personalDetails";

	private static final String _SQL_SELECT_PERSONALDETAILS_WHERE =
		"SELECT personalDetails FROM PersonalDetails personalDetails WHERE ";

	private static final String _SQL_COUNT_PERSONALDETAILS =
		"SELECT COUNT(personalDetails) FROM PersonalDetails personalDetails";

	private static final String _SQL_COUNT_PERSONALDETAILS_WHERE =
		"SELECT COUNT(personalDetails) FROM PersonalDetails personalDetails WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "personalDetails.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No PersonalDetails exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No PersonalDetails exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		PersonalDetailsPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}