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
import com.liferay.portal.kernel.util.SetUtil;

import com.mhdsys.schema.exception.NoSuchSportsCompLvlMasterException;
import com.mhdsys.schema.model.SportsCompLvlMaster;
import com.mhdsys.schema.model.SportsCompLvlMasterTable;
import com.mhdsys.schema.model.impl.SportsCompLvlMasterImpl;
import com.mhdsys.schema.model.impl.SportsCompLvlMasterModelImpl;
import com.mhdsys.schema.service.persistence.SportsCompLvlMasterPersistence;
import com.mhdsys.schema.service.persistence.SportsCompLvlMasterUtil;
import com.mhdsys.schema.service.persistence.impl.constants.mhdsysPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

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
 * The persistence implementation for the sports comp lvl master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = SportsCompLvlMasterPersistence.class)
public class SportsCompLvlMasterPersistenceImpl
	extends BasePersistenceImpl<SportsCompLvlMaster>
	implements SportsCompLvlMasterPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SportsCompLvlMasterUtil</code> to access the sports comp lvl master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SportsCompLvlMasterImpl.class.getName();

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
	 * Returns all the sports comp lvl masters where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @return the matching sports comp lvl masters
	 */
	@Override
	public List<SportsCompLvlMaster> findByActiveState(boolean isActive) {
		return findByActiveState(
			isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sports comp lvl masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsCompLvlMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of sports comp lvl masters
	 * @param end the upper bound of the range of sports comp lvl masters (not inclusive)
	 * @return the range of matching sports comp lvl masters
	 */
	@Override
	public List<SportsCompLvlMaster> findByActiveState(
		boolean isActive, int start, int end) {

		return findByActiveState(isActive, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sports comp lvl masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsCompLvlMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of sports comp lvl masters
	 * @param end the upper bound of the range of sports comp lvl masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sports comp lvl masters
	 */
	@Override
	public List<SportsCompLvlMaster> findByActiveState(
		boolean isActive, int start, int end,
		OrderByComparator<SportsCompLvlMaster> orderByComparator) {

		return findByActiveState(isActive, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sports comp lvl masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsCompLvlMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of sports comp lvl masters
	 * @param end the upper bound of the range of sports comp lvl masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching sports comp lvl masters
	 */
	@Override
	public List<SportsCompLvlMaster> findByActiveState(
		boolean isActive, int start, int end,
		OrderByComparator<SportsCompLvlMaster> orderByComparator,
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

		List<SportsCompLvlMaster> list = null;

		if (useFinderCache) {
			list = (List<SportsCompLvlMaster>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SportsCompLvlMaster sportsCompLvlMaster : list) {
					if (isActive != sportsCompLvlMaster.isIsActive()) {
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

			sb.append(_SQL_SELECT_SPORTSCOMPLVLMASTER_WHERE);

			sb.append(_FINDER_COLUMN_ACTIVESTATE_ISACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SportsCompLvlMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(isActive);

				list = (List<SportsCompLvlMaster>)QueryUtil.list(
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
	 * Returns the first sports comp lvl master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sports comp lvl master
	 * @throws NoSuchSportsCompLvlMasterException if a matching sports comp lvl master could not be found
	 */
	@Override
	public SportsCompLvlMaster findByActiveState_First(
			boolean isActive,
			OrderByComparator<SportsCompLvlMaster> orderByComparator)
		throws NoSuchSportsCompLvlMasterException {

		SportsCompLvlMaster sportsCompLvlMaster = fetchByActiveState_First(
			isActive, orderByComparator);

		if (sportsCompLvlMaster != null) {
			return sportsCompLvlMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isActive=");
		sb.append(isActive);

		sb.append("}");

		throw new NoSuchSportsCompLvlMasterException(sb.toString());
	}

	/**
	 * Returns the first sports comp lvl master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sports comp lvl master, or <code>null</code> if a matching sports comp lvl master could not be found
	 */
	@Override
	public SportsCompLvlMaster fetchByActiveState_First(
		boolean isActive,
		OrderByComparator<SportsCompLvlMaster> orderByComparator) {

		List<SportsCompLvlMaster> list = findByActiveState(
			isActive, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sports comp lvl master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sports comp lvl master
	 * @throws NoSuchSportsCompLvlMasterException if a matching sports comp lvl master could not be found
	 */
	@Override
	public SportsCompLvlMaster findByActiveState_Last(
			boolean isActive,
			OrderByComparator<SportsCompLvlMaster> orderByComparator)
		throws NoSuchSportsCompLvlMasterException {

		SportsCompLvlMaster sportsCompLvlMaster = fetchByActiveState_Last(
			isActive, orderByComparator);

		if (sportsCompLvlMaster != null) {
			return sportsCompLvlMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isActive=");
		sb.append(isActive);

		sb.append("}");

		throw new NoSuchSportsCompLvlMasterException(sb.toString());
	}

	/**
	 * Returns the last sports comp lvl master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sports comp lvl master, or <code>null</code> if a matching sports comp lvl master could not be found
	 */
	@Override
	public SportsCompLvlMaster fetchByActiveState_Last(
		boolean isActive,
		OrderByComparator<SportsCompLvlMaster> orderByComparator) {

		int count = countByActiveState(isActive);

		if (count == 0) {
			return null;
		}

		List<SportsCompLvlMaster> list = findByActiveState(
			isActive, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sports comp lvl masters before and after the current sports comp lvl master in the ordered set where isActive = &#63;.
	 *
	 * @param id the primary key of the current sports comp lvl master
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sports comp lvl master
	 * @throws NoSuchSportsCompLvlMasterException if a sports comp lvl master with the primary key could not be found
	 */
	@Override
	public SportsCompLvlMaster[] findByActiveState_PrevAndNext(
			long id, boolean isActive,
			OrderByComparator<SportsCompLvlMaster> orderByComparator)
		throws NoSuchSportsCompLvlMasterException {

		SportsCompLvlMaster sportsCompLvlMaster = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			SportsCompLvlMaster[] array = new SportsCompLvlMasterImpl[3];

			array[0] = getByActiveState_PrevAndNext(
				session, sportsCompLvlMaster, isActive, orderByComparator,
				true);

			array[1] = sportsCompLvlMaster;

			array[2] = getByActiveState_PrevAndNext(
				session, sportsCompLvlMaster, isActive, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected SportsCompLvlMaster getByActiveState_PrevAndNext(
		Session session, SportsCompLvlMaster sportsCompLvlMaster,
		boolean isActive,
		OrderByComparator<SportsCompLvlMaster> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_SPORTSCOMPLVLMASTER_WHERE);

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
			sb.append(SportsCompLvlMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(isActive);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						sportsCompLvlMaster)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SportsCompLvlMaster> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sports comp lvl masters where isActive = &#63; from the database.
	 *
	 * @param isActive the is active
	 */
	@Override
	public void removeByActiveState(boolean isActive) {
		for (SportsCompLvlMaster sportsCompLvlMaster :
				findByActiveState(
					isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(sportsCompLvlMaster);
		}
	}

	/**
	 * Returns the number of sports comp lvl masters where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @return the number of matching sports comp lvl masters
	 */
	@Override
	public int countByActiveState(boolean isActive) {
		FinderPath finderPath = _finderPathCountByActiveState;

		Object[] finderArgs = new Object[] {isActive};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SPORTSCOMPLVLMASTER_WHERE);

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
		"sportsCompLvlMaster.isActive = ?";

	public SportsCompLvlMasterPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("id", "id_");

		setDBColumnNames(dbColumnNames);

		setModelClass(SportsCompLvlMaster.class);

		setModelImplClass(SportsCompLvlMasterImpl.class);
		setModelPKClass(long.class);

		setTable(SportsCompLvlMasterTable.INSTANCE);
	}

	/**
	 * Caches the sports comp lvl master in the entity cache if it is enabled.
	 *
	 * @param sportsCompLvlMaster the sports comp lvl master
	 */
	@Override
	public void cacheResult(SportsCompLvlMaster sportsCompLvlMaster) {
		entityCache.putResult(
			SportsCompLvlMasterImpl.class, sportsCompLvlMaster.getPrimaryKey(),
			sportsCompLvlMaster);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the sports comp lvl masters in the entity cache if it is enabled.
	 *
	 * @param sportsCompLvlMasters the sports comp lvl masters
	 */
	@Override
	public void cacheResult(List<SportsCompLvlMaster> sportsCompLvlMasters) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (sportsCompLvlMasters.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (SportsCompLvlMaster sportsCompLvlMaster : sportsCompLvlMasters) {
			if (entityCache.getResult(
					SportsCompLvlMasterImpl.class,
					sportsCompLvlMaster.getPrimaryKey()) == null) {

				cacheResult(sportsCompLvlMaster);
			}
		}
	}

	/**
	 * Clears the cache for all sports comp lvl masters.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SportsCompLvlMasterImpl.class);

		finderCache.clearCache(SportsCompLvlMasterImpl.class);
	}

	/**
	 * Clears the cache for the sports comp lvl master.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SportsCompLvlMaster sportsCompLvlMaster) {
		entityCache.removeResult(
			SportsCompLvlMasterImpl.class, sportsCompLvlMaster);
	}

	@Override
	public void clearCache(List<SportsCompLvlMaster> sportsCompLvlMasters) {
		for (SportsCompLvlMaster sportsCompLvlMaster : sportsCompLvlMasters) {
			entityCache.removeResult(
				SportsCompLvlMasterImpl.class, sportsCompLvlMaster);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(SportsCompLvlMasterImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(SportsCompLvlMasterImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new sports comp lvl master with the primary key. Does not add the sports comp lvl master to the database.
	 *
	 * @param id the primary key for the new sports comp lvl master
	 * @return the new sports comp lvl master
	 */
	@Override
	public SportsCompLvlMaster create(long id) {
		SportsCompLvlMaster sportsCompLvlMaster = new SportsCompLvlMasterImpl();

		sportsCompLvlMaster.setNew(true);
		sportsCompLvlMaster.setPrimaryKey(id);

		return sportsCompLvlMaster;
	}

	/**
	 * Removes the sports comp lvl master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the sports comp lvl master
	 * @return the sports comp lvl master that was removed
	 * @throws NoSuchSportsCompLvlMasterException if a sports comp lvl master with the primary key could not be found
	 */
	@Override
	public SportsCompLvlMaster remove(long id)
		throws NoSuchSportsCompLvlMasterException {

		return remove((Serializable)id);
	}

	/**
	 * Removes the sports comp lvl master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the sports comp lvl master
	 * @return the sports comp lvl master that was removed
	 * @throws NoSuchSportsCompLvlMasterException if a sports comp lvl master with the primary key could not be found
	 */
	@Override
	public SportsCompLvlMaster remove(Serializable primaryKey)
		throws NoSuchSportsCompLvlMasterException {

		Session session = null;

		try {
			session = openSession();

			SportsCompLvlMaster sportsCompLvlMaster =
				(SportsCompLvlMaster)session.get(
					SportsCompLvlMasterImpl.class, primaryKey);

			if (sportsCompLvlMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSportsCompLvlMasterException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(sportsCompLvlMaster);
		}
		catch (NoSuchSportsCompLvlMasterException noSuchEntityException) {
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
	protected SportsCompLvlMaster removeImpl(
		SportsCompLvlMaster sportsCompLvlMaster) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(sportsCompLvlMaster)) {
				sportsCompLvlMaster = (SportsCompLvlMaster)session.get(
					SportsCompLvlMasterImpl.class,
					sportsCompLvlMaster.getPrimaryKeyObj());
			}

			if (sportsCompLvlMaster != null) {
				session.delete(sportsCompLvlMaster);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (sportsCompLvlMaster != null) {
			clearCache(sportsCompLvlMaster);
		}

		return sportsCompLvlMaster;
	}

	@Override
	public SportsCompLvlMaster updateImpl(
		SportsCompLvlMaster sportsCompLvlMaster) {

		boolean isNew = sportsCompLvlMaster.isNew();

		if (!(sportsCompLvlMaster instanceof SportsCompLvlMasterModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(sportsCompLvlMaster.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					sportsCompLvlMaster);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in sportsCompLvlMaster proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SportsCompLvlMaster implementation " +
					sportsCompLvlMaster.getClass());
		}

		SportsCompLvlMasterModelImpl sportsCompLvlMasterModelImpl =
			(SportsCompLvlMasterModelImpl)sportsCompLvlMaster;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(sportsCompLvlMaster);
			}
			else {
				sportsCompLvlMaster = (SportsCompLvlMaster)session.merge(
					sportsCompLvlMaster);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			SportsCompLvlMasterImpl.class, sportsCompLvlMasterModelImpl, false,
			true);

		if (isNew) {
			sportsCompLvlMaster.setNew(false);
		}

		sportsCompLvlMaster.resetOriginalValues();

		return sportsCompLvlMaster;
	}

	/**
	 * Returns the sports comp lvl master with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the sports comp lvl master
	 * @return the sports comp lvl master
	 * @throws NoSuchSportsCompLvlMasterException if a sports comp lvl master with the primary key could not be found
	 */
	@Override
	public SportsCompLvlMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSportsCompLvlMasterException {

		SportsCompLvlMaster sportsCompLvlMaster = fetchByPrimaryKey(primaryKey);

		if (sportsCompLvlMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSportsCompLvlMasterException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return sportsCompLvlMaster;
	}

	/**
	 * Returns the sports comp lvl master with the primary key or throws a <code>NoSuchSportsCompLvlMasterException</code> if it could not be found.
	 *
	 * @param id the primary key of the sports comp lvl master
	 * @return the sports comp lvl master
	 * @throws NoSuchSportsCompLvlMasterException if a sports comp lvl master with the primary key could not be found
	 */
	@Override
	public SportsCompLvlMaster findByPrimaryKey(long id)
		throws NoSuchSportsCompLvlMasterException {

		return findByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns the sports comp lvl master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the sports comp lvl master
	 * @return the sports comp lvl master, or <code>null</code> if a sports comp lvl master with the primary key could not be found
	 */
	@Override
	public SportsCompLvlMaster fetchByPrimaryKey(long id) {
		return fetchByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns all the sports comp lvl masters.
	 *
	 * @return the sports comp lvl masters
	 */
	@Override
	public List<SportsCompLvlMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sports comp lvl masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsCompLvlMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sports comp lvl masters
	 * @param end the upper bound of the range of sports comp lvl masters (not inclusive)
	 * @return the range of sports comp lvl masters
	 */
	@Override
	public List<SportsCompLvlMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the sports comp lvl masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsCompLvlMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sports comp lvl masters
	 * @param end the upper bound of the range of sports comp lvl masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sports comp lvl masters
	 */
	@Override
	public List<SportsCompLvlMaster> findAll(
		int start, int end,
		OrderByComparator<SportsCompLvlMaster> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sports comp lvl masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsCompLvlMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sports comp lvl masters
	 * @param end the upper bound of the range of sports comp lvl masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of sports comp lvl masters
	 */
	@Override
	public List<SportsCompLvlMaster> findAll(
		int start, int end,
		OrderByComparator<SportsCompLvlMaster> orderByComparator,
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

		List<SportsCompLvlMaster> list = null;

		if (useFinderCache) {
			list = (List<SportsCompLvlMaster>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SPORTSCOMPLVLMASTER);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SPORTSCOMPLVLMASTER;

				sql = sql.concat(SportsCompLvlMasterModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<SportsCompLvlMaster>)QueryUtil.list(
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
	 * Removes all the sports comp lvl masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SportsCompLvlMaster sportsCompLvlMaster : findAll()) {
			remove(sportsCompLvlMaster);
		}
	}

	/**
	 * Returns the number of sports comp lvl masters.
	 *
	 * @return the number of sports comp lvl masters
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
					_SQL_COUNT_SPORTSCOMPLVLMASTER);

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
		return "id_";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SPORTSCOMPLVLMASTER;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SportsCompLvlMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the sports comp lvl master persistence.
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

		SportsCompLvlMasterUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		SportsCompLvlMasterUtil.setPersistence(null);

		entityCache.removeCache(SportsCompLvlMasterImpl.class.getName());
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

	private static final String _SQL_SELECT_SPORTSCOMPLVLMASTER =
		"SELECT sportsCompLvlMaster FROM SportsCompLvlMaster sportsCompLvlMaster";

	private static final String _SQL_SELECT_SPORTSCOMPLVLMASTER_WHERE =
		"SELECT sportsCompLvlMaster FROM SportsCompLvlMaster sportsCompLvlMaster WHERE ";

	private static final String _SQL_COUNT_SPORTSCOMPLVLMASTER =
		"SELECT COUNT(sportsCompLvlMaster) FROM SportsCompLvlMaster sportsCompLvlMaster";

	private static final String _SQL_COUNT_SPORTSCOMPLVLMASTER_WHERE =
		"SELECT COUNT(sportsCompLvlMaster) FROM SportsCompLvlMaster sportsCompLvlMaster WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "sportsCompLvlMaster.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SportsCompLvlMaster exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No SportsCompLvlMaster exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		SportsCompLvlMasterPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"id"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}