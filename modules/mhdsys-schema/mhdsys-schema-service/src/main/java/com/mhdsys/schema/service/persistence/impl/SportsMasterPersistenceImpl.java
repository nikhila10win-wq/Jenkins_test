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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import com.mhdsys.schema.exception.NoSuchSportsMasterException;
import com.mhdsys.schema.model.SportsMaster;
import com.mhdsys.schema.model.SportsMasterTable;
import com.mhdsys.schema.model.impl.SportsMasterImpl;
import com.mhdsys.schema.model.impl.SportsMasterModelImpl;
import com.mhdsys.schema.service.persistence.SportsMasterPersistence;
import com.mhdsys.schema.service.persistence.SportsMasterUtil;
import com.mhdsys.schema.service.persistence.impl.constants.mhdsysPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the sports master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = SportsMasterPersistence.class)
public class SportsMasterPersistenceImpl
	extends BasePersistenceImpl<SportsMaster>
	implements SportsMasterPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SportsMasterUtil</code> to access the sports master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SportsMasterImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByActiveState;
	private FinderPath _finderPathWithoutPaginationFindByActiveState;
	private FinderPath _finderPathCountByActiveState;

	/**
	 * Returns all the sports masters where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @return the matching sports masters
	 */
	@Override
	public List<SportsMaster> findByActiveState(boolean isActive) {
		return findByActiveState(
			isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sports masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of sports masters
	 * @param end the upper bound of the range of sports masters (not inclusive)
	 * @return the range of matching sports masters
	 */
	@Override
	public List<SportsMaster> findByActiveState(
		boolean isActive, int start, int end) {

		return findByActiveState(isActive, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sports masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of sports masters
	 * @param end the upper bound of the range of sports masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sports masters
	 */
	@Override
	public List<SportsMaster> findByActiveState(
		boolean isActive, int start, int end,
		OrderByComparator<SportsMaster> orderByComparator) {

		return findByActiveState(isActive, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sports masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of sports masters
	 * @param end the upper bound of the range of sports masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching sports masters
	 */
	@Override
	public List<SportsMaster> findByActiveState(
		boolean isActive, int start, int end,
		OrderByComparator<SportsMaster> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByActiveState;
				finderArgs = new Object[] {isActive};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByActiveState;
			finderArgs = new Object[] {isActive, start, end, orderByComparator};
		}

		List<SportsMaster> list = null;

		if (useFinderCache) {
			list = (List<SportsMaster>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SportsMaster sportsMaster : list) {
					if (isActive != sportsMaster.isIsActive()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_SPORTSMASTER_WHERE);

			sb.append(_FINDER_COLUMN_ACTIVESTATE_ISACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SportsMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(isActive);

				list = (List<SportsMaster>)QueryUtil.list(
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
	 * Returns the first sports master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sports master
	 * @throws NoSuchSportsMasterException if a matching sports master could not be found
	 */
	@Override
	public SportsMaster findByActiveState_First(
			boolean isActive, OrderByComparator<SportsMaster> orderByComparator)
		throws NoSuchSportsMasterException {

		SportsMaster sportsMaster = fetchByActiveState_First(
			isActive, orderByComparator);

		if (sportsMaster != null) {
			return sportsMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isActive=");
		sb.append(isActive);

		sb.append("}");

		throw new NoSuchSportsMasterException(sb.toString());
	}

	/**
	 * Returns the first sports master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sports master, or <code>null</code> if a matching sports master could not be found
	 */
	@Override
	public SportsMaster fetchByActiveState_First(
		boolean isActive, OrderByComparator<SportsMaster> orderByComparator) {

		List<SportsMaster> list = findByActiveState(
			isActive, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sports master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sports master
	 * @throws NoSuchSportsMasterException if a matching sports master could not be found
	 */
	@Override
	public SportsMaster findByActiveState_Last(
			boolean isActive, OrderByComparator<SportsMaster> orderByComparator)
		throws NoSuchSportsMasterException {

		SportsMaster sportsMaster = fetchByActiveState_Last(
			isActive, orderByComparator);

		if (sportsMaster != null) {
			return sportsMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isActive=");
		sb.append(isActive);

		sb.append("}");

		throw new NoSuchSportsMasterException(sb.toString());
	}

	/**
	 * Returns the last sports master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sports master, or <code>null</code> if a matching sports master could not be found
	 */
	@Override
	public SportsMaster fetchByActiveState_Last(
		boolean isActive, OrderByComparator<SportsMaster> orderByComparator) {

		int count = countByActiveState(isActive);

		if (count == 0) {
			return null;
		}

		List<SportsMaster> list = findByActiveState(
			isActive, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sports masters before and after the current sports master in the ordered set where isActive = &#63;.
	 *
	 * @param sportMasterId the primary key of the current sports master
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sports master
	 * @throws NoSuchSportsMasterException if a sports master with the primary key could not be found
	 */
	@Override
	public SportsMaster[] findByActiveState_PrevAndNext(
			long sportMasterId, boolean isActive,
			OrderByComparator<SportsMaster> orderByComparator)
		throws NoSuchSportsMasterException {

		SportsMaster sportsMaster = findByPrimaryKey(sportMasterId);

		Session session = null;

		try {
			session = openSession();

			SportsMaster[] array = new SportsMasterImpl[3];

			array[0] = getByActiveState_PrevAndNext(
				session, sportsMaster, isActive, orderByComparator, true);

			array[1] = sportsMaster;

			array[2] = getByActiveState_PrevAndNext(
				session, sportsMaster, isActive, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected SportsMaster getByActiveState_PrevAndNext(
		Session session, SportsMaster sportsMaster, boolean isActive,
		OrderByComparator<SportsMaster> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_SPORTSMASTER_WHERE);

		sb.append(_FINDER_COLUMN_ACTIVESTATE_ISACTIVE_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(SportsMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(isActive);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(sportsMaster)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SportsMaster> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sports masters where isActive = &#63; from the database.
	 *
	 * @param isActive the is active
	 */
	@Override
	public void removeByActiveState(boolean isActive) {
		for (SportsMaster sportsMaster :
				findByActiveState(
					isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(sportsMaster);
		}
	}

	/**
	 * Returns the number of sports masters where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @return the number of matching sports masters
	 */
	@Override
	public int countByActiveState(boolean isActive) {
		FinderPath finderPath = _finderPathCountByActiveState;

		Object[] finderArgs = new Object[] {isActive};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SPORTSMASTER_WHERE);

			sb.append(_FINDER_COLUMN_ACTIVESTATE_ISACTIVE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(isActive);

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

	private static final String _FINDER_COLUMN_ACTIVESTATE_ISACTIVE_2 =
		"sportsMaster.isActive = ?";

	public SportsMasterPersistenceImpl() {
		setModelClass(SportsMaster.class);

		setModelImplClass(SportsMasterImpl.class);
		setModelPKClass(long.class);

		setTable(SportsMasterTable.INSTANCE);
	}

	/**
	 * Caches the sports master in the entity cache if it is enabled.
	 *
	 * @param sportsMaster the sports master
	 */
	@Override
	public void cacheResult(SportsMaster sportsMaster) {
		entityCache.putResult(
			SportsMasterImpl.class, sportsMaster.getPrimaryKey(), sportsMaster);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the sports masters in the entity cache if it is enabled.
	 *
	 * @param sportsMasters the sports masters
	 */
	@Override
	public void cacheResult(List<SportsMaster> sportsMasters) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (sportsMasters.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (SportsMaster sportsMaster : sportsMasters) {
			if (entityCache.getResult(
					SportsMasterImpl.class, sportsMaster.getPrimaryKey()) ==
						null) {

				cacheResult(sportsMaster);
			}
		}
	}

	/**
	 * Clears the cache for all sports masters.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SportsMasterImpl.class);

		finderCache.clearCache(SportsMasterImpl.class);
	}

	/**
	 * Clears the cache for the sports master.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SportsMaster sportsMaster) {
		entityCache.removeResult(SportsMasterImpl.class, sportsMaster);
	}

	@Override
	public void clearCache(List<SportsMaster> sportsMasters) {
		for (SportsMaster sportsMaster : sportsMasters) {
			entityCache.removeResult(SportsMasterImpl.class, sportsMaster);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(SportsMasterImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(SportsMasterImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new sports master with the primary key. Does not add the sports master to the database.
	 *
	 * @param sportMasterId the primary key for the new sports master
	 * @return the new sports master
	 */
	@Override
	public SportsMaster create(long sportMasterId) {
		SportsMaster sportsMaster = new SportsMasterImpl();

		sportsMaster.setNew(true);
		sportsMaster.setPrimaryKey(sportMasterId);

		return sportsMaster;
	}

	/**
	 * Removes the sports master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sportMasterId the primary key of the sports master
	 * @return the sports master that was removed
	 * @throws NoSuchSportsMasterException if a sports master with the primary key could not be found
	 */
	@Override
	public SportsMaster remove(long sportMasterId)
		throws NoSuchSportsMasterException {

		return remove((Serializable)sportMasterId);
	}

	/**
	 * Removes the sports master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the sports master
	 * @return the sports master that was removed
	 * @throws NoSuchSportsMasterException if a sports master with the primary key could not be found
	 */
	@Override
	public SportsMaster remove(Serializable primaryKey)
		throws NoSuchSportsMasterException {

		Session session = null;

		try {
			session = openSession();

			SportsMaster sportsMaster = (SportsMaster)session.get(
				SportsMasterImpl.class, primaryKey);

			if (sportsMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSportsMasterException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(sportsMaster);
		}
		catch (NoSuchSportsMasterException noSuchEntityException) {
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
	protected SportsMaster removeImpl(SportsMaster sportsMaster) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(sportsMaster)) {
				sportsMaster = (SportsMaster)session.get(
					SportsMasterImpl.class, sportsMaster.getPrimaryKeyObj());
			}

			if (sportsMaster != null) {
				session.delete(sportsMaster);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (sportsMaster != null) {
			clearCache(sportsMaster);
		}

		return sportsMaster;
	}

	@Override
	public SportsMaster updateImpl(SportsMaster sportsMaster) {
		boolean isNew = sportsMaster.isNew();

		if (!(sportsMaster instanceof SportsMasterModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(sportsMaster.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					sportsMaster);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in sportsMaster proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SportsMaster implementation " +
					sportsMaster.getClass());
		}

		SportsMasterModelImpl sportsMasterModelImpl =
			(SportsMasterModelImpl)sportsMaster;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(sportsMaster);
			}
			else {
				sportsMaster = (SportsMaster)session.merge(sportsMaster);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			SportsMasterImpl.class, sportsMasterModelImpl, false, true);

		if (isNew) {
			sportsMaster.setNew(false);
		}

		sportsMaster.resetOriginalValues();

		return sportsMaster;
	}

	/**
	 * Returns the sports master with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the sports master
	 * @return the sports master
	 * @throws NoSuchSportsMasterException if a sports master with the primary key could not be found
	 */
	@Override
	public SportsMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSportsMasterException {

		SportsMaster sportsMaster = fetchByPrimaryKey(primaryKey);

		if (sportsMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSportsMasterException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return sportsMaster;
	}

	/**
	 * Returns the sports master with the primary key or throws a <code>NoSuchSportsMasterException</code> if it could not be found.
	 *
	 * @param sportMasterId the primary key of the sports master
	 * @return the sports master
	 * @throws NoSuchSportsMasterException if a sports master with the primary key could not be found
	 */
	@Override
	public SportsMaster findByPrimaryKey(long sportMasterId)
		throws NoSuchSportsMasterException {

		return findByPrimaryKey((Serializable)sportMasterId);
	}

	/**
	 * Returns the sports master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param sportMasterId the primary key of the sports master
	 * @return the sports master, or <code>null</code> if a sports master with the primary key could not be found
	 */
	@Override
	public SportsMaster fetchByPrimaryKey(long sportMasterId) {
		return fetchByPrimaryKey((Serializable)sportMasterId);
	}

	/**
	 * Returns all the sports masters.
	 *
	 * @return the sports masters
	 */
	@Override
	public List<SportsMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sports masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sports masters
	 * @param end the upper bound of the range of sports masters (not inclusive)
	 * @return the range of sports masters
	 */
	@Override
	public List<SportsMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the sports masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sports masters
	 * @param end the upper bound of the range of sports masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sports masters
	 */
	@Override
	public List<SportsMaster> findAll(
		int start, int end, OrderByComparator<SportsMaster> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sports masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sports masters
	 * @param end the upper bound of the range of sports masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of sports masters
	 */
	@Override
	public List<SportsMaster> findAll(
		int start, int end, OrderByComparator<SportsMaster> orderByComparator,
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

		List<SportsMaster> list = null;

		if (useFinderCache) {
			list = (List<SportsMaster>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SPORTSMASTER);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SPORTSMASTER;

				sql = sql.concat(SportsMasterModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<SportsMaster>)QueryUtil.list(
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
	 * Removes all the sports masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SportsMaster sportsMaster : findAll()) {
			remove(sportsMaster);
		}
	}

	/**
	 * Returns the number of sports masters.
	 *
	 * @return the number of sports masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SPORTSMASTER);

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
		return "sportMasterId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SPORTSMASTER;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SportsMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the sports master persistence.
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

		_finderPathWithPaginationFindByActiveState = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByActiveState",
			new String[] {
				Boolean.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"isActive"}, true);

		_finderPathWithoutPaginationFindByActiveState = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByActiveState",
			new String[] {Boolean.class.getName()}, new String[] {"isActive"},
			true);

		_finderPathCountByActiveState = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByActiveState",
			new String[] {Boolean.class.getName()}, new String[] {"isActive"},
			false);

		SportsMasterUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		SportsMasterUtil.setPersistence(null);

		entityCache.removeCache(SportsMasterImpl.class.getName());
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

	private static final String _SQL_SELECT_SPORTSMASTER =
		"SELECT sportsMaster FROM SportsMaster sportsMaster";

	private static final String _SQL_SELECT_SPORTSMASTER_WHERE =
		"SELECT sportsMaster FROM SportsMaster sportsMaster WHERE ";

	private static final String _SQL_COUNT_SPORTSMASTER =
		"SELECT COUNT(sportsMaster) FROM SportsMaster sportsMaster";

	private static final String _SQL_COUNT_SPORTSMASTER_WHERE =
		"SELECT COUNT(sportsMaster) FROM SportsMaster sportsMaster WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "sportsMaster.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SportsMaster exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No SportsMaster exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		SportsMasterPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}