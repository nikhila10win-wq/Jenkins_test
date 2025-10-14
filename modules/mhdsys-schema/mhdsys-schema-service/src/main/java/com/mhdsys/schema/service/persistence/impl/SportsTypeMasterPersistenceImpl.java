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

import com.mhdsys.schema.exception.NoSuchSportsTypeMasterException;
import com.mhdsys.schema.model.SportsTypeMaster;
import com.mhdsys.schema.model.SportsTypeMasterTable;
import com.mhdsys.schema.model.impl.SportsTypeMasterImpl;
import com.mhdsys.schema.model.impl.SportsTypeMasterModelImpl;
import com.mhdsys.schema.service.persistence.SportsTypeMasterPersistence;
import com.mhdsys.schema.service.persistence.SportsTypeMasterUtil;
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
 * The persistence implementation for the sports type master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = SportsTypeMasterPersistence.class)
public class SportsTypeMasterPersistenceImpl
	extends BasePersistenceImpl<SportsTypeMaster>
	implements SportsTypeMasterPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SportsTypeMasterUtil</code> to access the sports type master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SportsTypeMasterImpl.class.getName();

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
	 * Returns all the sports type masters where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @return the matching sports type masters
	 */
	@Override
	public List<SportsTypeMaster> findByActiveState(boolean isActive) {
		return findByActiveState(
			isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sports type masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsTypeMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of sports type masters
	 * @param end the upper bound of the range of sports type masters (not inclusive)
	 * @return the range of matching sports type masters
	 */
	@Override
	public List<SportsTypeMaster> findByActiveState(
		boolean isActive, int start, int end) {

		return findByActiveState(isActive, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sports type masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsTypeMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of sports type masters
	 * @param end the upper bound of the range of sports type masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sports type masters
	 */
	@Override
	public List<SportsTypeMaster> findByActiveState(
		boolean isActive, int start, int end,
		OrderByComparator<SportsTypeMaster> orderByComparator) {

		return findByActiveState(isActive, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sports type masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsTypeMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of sports type masters
	 * @param end the upper bound of the range of sports type masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching sports type masters
	 */
	@Override
	public List<SportsTypeMaster> findByActiveState(
		boolean isActive, int start, int end,
		OrderByComparator<SportsTypeMaster> orderByComparator,
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

		List<SportsTypeMaster> list = null;

		if (useFinderCache) {
			list = (List<SportsTypeMaster>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SportsTypeMaster sportsTypeMaster : list) {
					if (isActive != sportsTypeMaster.isIsActive()) {
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

			sb.append(_SQL_SELECT_SPORTSTYPEMASTER_WHERE);

			sb.append(_FINDER_COLUMN_ACTIVESTATE_ISACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SportsTypeMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(isActive);

				list = (List<SportsTypeMaster>)QueryUtil.list(
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
	 * Returns the first sports type master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sports type master
	 * @throws NoSuchSportsTypeMasterException if a matching sports type master could not be found
	 */
	@Override
	public SportsTypeMaster findByActiveState_First(
			boolean isActive,
			OrderByComparator<SportsTypeMaster> orderByComparator)
		throws NoSuchSportsTypeMasterException {

		SportsTypeMaster sportsTypeMaster = fetchByActiveState_First(
			isActive, orderByComparator);

		if (sportsTypeMaster != null) {
			return sportsTypeMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isActive=");
		sb.append(isActive);

		sb.append("}");

		throw new NoSuchSportsTypeMasterException(sb.toString());
	}

	/**
	 * Returns the first sports type master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sports type master, or <code>null</code> if a matching sports type master could not be found
	 */
	@Override
	public SportsTypeMaster fetchByActiveState_First(
		boolean isActive,
		OrderByComparator<SportsTypeMaster> orderByComparator) {

		List<SportsTypeMaster> list = findByActiveState(
			isActive, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sports type master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sports type master
	 * @throws NoSuchSportsTypeMasterException if a matching sports type master could not be found
	 */
	@Override
	public SportsTypeMaster findByActiveState_Last(
			boolean isActive,
			OrderByComparator<SportsTypeMaster> orderByComparator)
		throws NoSuchSportsTypeMasterException {

		SportsTypeMaster sportsTypeMaster = fetchByActiveState_Last(
			isActive, orderByComparator);

		if (sportsTypeMaster != null) {
			return sportsTypeMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isActive=");
		sb.append(isActive);

		sb.append("}");

		throw new NoSuchSportsTypeMasterException(sb.toString());
	}

	/**
	 * Returns the last sports type master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sports type master, or <code>null</code> if a matching sports type master could not be found
	 */
	@Override
	public SportsTypeMaster fetchByActiveState_Last(
		boolean isActive,
		OrderByComparator<SportsTypeMaster> orderByComparator) {

		int count = countByActiveState(isActive);

		if (count == 0) {
			return null;
		}

		List<SportsTypeMaster> list = findByActiveState(
			isActive, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sports type masters before and after the current sports type master in the ordered set where isActive = &#63;.
	 *
	 * @param sportsTypeMasterId the primary key of the current sports type master
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sports type master
	 * @throws NoSuchSportsTypeMasterException if a sports type master with the primary key could not be found
	 */
	@Override
	public SportsTypeMaster[] findByActiveState_PrevAndNext(
			long sportsTypeMasterId, boolean isActive,
			OrderByComparator<SportsTypeMaster> orderByComparator)
		throws NoSuchSportsTypeMasterException {

		SportsTypeMaster sportsTypeMaster = findByPrimaryKey(
			sportsTypeMasterId);

		Session session = null;

		try {
			session = openSession();

			SportsTypeMaster[] array = new SportsTypeMasterImpl[3];

			array[0] = getByActiveState_PrevAndNext(
				session, sportsTypeMaster, isActive, orderByComparator, true);

			array[1] = sportsTypeMaster;

			array[2] = getByActiveState_PrevAndNext(
				session, sportsTypeMaster, isActive, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected SportsTypeMaster getByActiveState_PrevAndNext(
		Session session, SportsTypeMaster sportsTypeMaster, boolean isActive,
		OrderByComparator<SportsTypeMaster> orderByComparator,
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

		sb.append(_SQL_SELECT_SPORTSTYPEMASTER_WHERE);

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
			sb.append(SportsTypeMasterModelImpl.ORDER_BY_JPQL);
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
						sportsTypeMaster)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SportsTypeMaster> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sports type masters where isActive = &#63; from the database.
	 *
	 * @param isActive the is active
	 */
	@Override
	public void removeByActiveState(boolean isActive) {
		for (SportsTypeMaster sportsTypeMaster :
				findByActiveState(
					isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(sportsTypeMaster);
		}
	}

	/**
	 * Returns the number of sports type masters where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @return the number of matching sports type masters
	 */
	@Override
	public int countByActiveState(boolean isActive) {
		FinderPath finderPath = _finderPathCountByActiveState;

		Object[] finderArgs = new Object[] {isActive};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SPORTSTYPEMASTER_WHERE);

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
		"sportsTypeMaster.isActive = ?";

	public SportsTypeMasterPersistenceImpl() {
		setModelClass(SportsTypeMaster.class);

		setModelImplClass(SportsTypeMasterImpl.class);
		setModelPKClass(long.class);

		setTable(SportsTypeMasterTable.INSTANCE);
	}

	/**
	 * Caches the sports type master in the entity cache if it is enabled.
	 *
	 * @param sportsTypeMaster the sports type master
	 */
	@Override
	public void cacheResult(SportsTypeMaster sportsTypeMaster) {
		entityCache.putResult(
			SportsTypeMasterImpl.class, sportsTypeMaster.getPrimaryKey(),
			sportsTypeMaster);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the sports type masters in the entity cache if it is enabled.
	 *
	 * @param sportsTypeMasters the sports type masters
	 */
	@Override
	public void cacheResult(List<SportsTypeMaster> sportsTypeMasters) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (sportsTypeMasters.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (SportsTypeMaster sportsTypeMaster : sportsTypeMasters) {
			if (entityCache.getResult(
					SportsTypeMasterImpl.class,
					sportsTypeMaster.getPrimaryKey()) == null) {

				cacheResult(sportsTypeMaster);
			}
		}
	}

	/**
	 * Clears the cache for all sports type masters.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SportsTypeMasterImpl.class);

		finderCache.clearCache(SportsTypeMasterImpl.class);
	}

	/**
	 * Clears the cache for the sports type master.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SportsTypeMaster sportsTypeMaster) {
		entityCache.removeResult(SportsTypeMasterImpl.class, sportsTypeMaster);
	}

	@Override
	public void clearCache(List<SportsTypeMaster> sportsTypeMasters) {
		for (SportsTypeMaster sportsTypeMaster : sportsTypeMasters) {
			entityCache.removeResult(
				SportsTypeMasterImpl.class, sportsTypeMaster);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(SportsTypeMasterImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(SportsTypeMasterImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new sports type master with the primary key. Does not add the sports type master to the database.
	 *
	 * @param sportsTypeMasterId the primary key for the new sports type master
	 * @return the new sports type master
	 */
	@Override
	public SportsTypeMaster create(long sportsTypeMasterId) {
		SportsTypeMaster sportsTypeMaster = new SportsTypeMasterImpl();

		sportsTypeMaster.setNew(true);
		sportsTypeMaster.setPrimaryKey(sportsTypeMasterId);

		return sportsTypeMaster;
	}

	/**
	 * Removes the sports type master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sportsTypeMasterId the primary key of the sports type master
	 * @return the sports type master that was removed
	 * @throws NoSuchSportsTypeMasterException if a sports type master with the primary key could not be found
	 */
	@Override
	public SportsTypeMaster remove(long sportsTypeMasterId)
		throws NoSuchSportsTypeMasterException {

		return remove((Serializable)sportsTypeMasterId);
	}

	/**
	 * Removes the sports type master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the sports type master
	 * @return the sports type master that was removed
	 * @throws NoSuchSportsTypeMasterException if a sports type master with the primary key could not be found
	 */
	@Override
	public SportsTypeMaster remove(Serializable primaryKey)
		throws NoSuchSportsTypeMasterException {

		Session session = null;

		try {
			session = openSession();

			SportsTypeMaster sportsTypeMaster = (SportsTypeMaster)session.get(
				SportsTypeMasterImpl.class, primaryKey);

			if (sportsTypeMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSportsTypeMasterException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(sportsTypeMaster);
		}
		catch (NoSuchSportsTypeMasterException noSuchEntityException) {
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
	protected SportsTypeMaster removeImpl(SportsTypeMaster sportsTypeMaster) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(sportsTypeMaster)) {
				sportsTypeMaster = (SportsTypeMaster)session.get(
					SportsTypeMasterImpl.class,
					sportsTypeMaster.getPrimaryKeyObj());
			}

			if (sportsTypeMaster != null) {
				session.delete(sportsTypeMaster);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (sportsTypeMaster != null) {
			clearCache(sportsTypeMaster);
		}

		return sportsTypeMaster;
	}

	@Override
	public SportsTypeMaster updateImpl(SportsTypeMaster sportsTypeMaster) {
		boolean isNew = sportsTypeMaster.isNew();

		if (!(sportsTypeMaster instanceof SportsTypeMasterModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(sportsTypeMaster.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					sportsTypeMaster);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in sportsTypeMaster proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SportsTypeMaster implementation " +
					sportsTypeMaster.getClass());
		}

		SportsTypeMasterModelImpl sportsTypeMasterModelImpl =
			(SportsTypeMasterModelImpl)sportsTypeMaster;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(sportsTypeMaster);
			}
			else {
				sportsTypeMaster = (SportsTypeMaster)session.merge(
					sportsTypeMaster);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			SportsTypeMasterImpl.class, sportsTypeMasterModelImpl, false, true);

		if (isNew) {
			sportsTypeMaster.setNew(false);
		}

		sportsTypeMaster.resetOriginalValues();

		return sportsTypeMaster;
	}

	/**
	 * Returns the sports type master with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the sports type master
	 * @return the sports type master
	 * @throws NoSuchSportsTypeMasterException if a sports type master with the primary key could not be found
	 */
	@Override
	public SportsTypeMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSportsTypeMasterException {

		SportsTypeMaster sportsTypeMaster = fetchByPrimaryKey(primaryKey);

		if (sportsTypeMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSportsTypeMasterException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return sportsTypeMaster;
	}

	/**
	 * Returns the sports type master with the primary key or throws a <code>NoSuchSportsTypeMasterException</code> if it could not be found.
	 *
	 * @param sportsTypeMasterId the primary key of the sports type master
	 * @return the sports type master
	 * @throws NoSuchSportsTypeMasterException if a sports type master with the primary key could not be found
	 */
	@Override
	public SportsTypeMaster findByPrimaryKey(long sportsTypeMasterId)
		throws NoSuchSportsTypeMasterException {

		return findByPrimaryKey((Serializable)sportsTypeMasterId);
	}

	/**
	 * Returns the sports type master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param sportsTypeMasterId the primary key of the sports type master
	 * @return the sports type master, or <code>null</code> if a sports type master with the primary key could not be found
	 */
	@Override
	public SportsTypeMaster fetchByPrimaryKey(long sportsTypeMasterId) {
		return fetchByPrimaryKey((Serializable)sportsTypeMasterId);
	}

	/**
	 * Returns all the sports type masters.
	 *
	 * @return the sports type masters
	 */
	@Override
	public List<SportsTypeMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sports type masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsTypeMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sports type masters
	 * @param end the upper bound of the range of sports type masters (not inclusive)
	 * @return the range of sports type masters
	 */
	@Override
	public List<SportsTypeMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the sports type masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsTypeMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sports type masters
	 * @param end the upper bound of the range of sports type masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sports type masters
	 */
	@Override
	public List<SportsTypeMaster> findAll(
		int start, int end,
		OrderByComparator<SportsTypeMaster> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sports type masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsTypeMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sports type masters
	 * @param end the upper bound of the range of sports type masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of sports type masters
	 */
	@Override
	public List<SportsTypeMaster> findAll(
		int start, int end,
		OrderByComparator<SportsTypeMaster> orderByComparator,
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

		List<SportsTypeMaster> list = null;

		if (useFinderCache) {
			list = (List<SportsTypeMaster>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SPORTSTYPEMASTER);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SPORTSTYPEMASTER;

				sql = sql.concat(SportsTypeMasterModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<SportsTypeMaster>)QueryUtil.list(
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
	 * Removes all the sports type masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SportsTypeMaster sportsTypeMaster : findAll()) {
			remove(sportsTypeMaster);
		}
	}

	/**
	 * Returns the number of sports type masters.
	 *
	 * @return the number of sports type masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SPORTSTYPEMASTER);

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
		return "sportsTypeMasterId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SPORTSTYPEMASTER;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SportsTypeMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the sports type master persistence.
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

		SportsTypeMasterUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		SportsTypeMasterUtil.setPersistence(null);

		entityCache.removeCache(SportsTypeMasterImpl.class.getName());
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

	private static final String _SQL_SELECT_SPORTSTYPEMASTER =
		"SELECT sportsTypeMaster FROM SportsTypeMaster sportsTypeMaster";

	private static final String _SQL_SELECT_SPORTSTYPEMASTER_WHERE =
		"SELECT sportsTypeMaster FROM SportsTypeMaster sportsTypeMaster WHERE ";

	private static final String _SQL_COUNT_SPORTSTYPEMASTER =
		"SELECT COUNT(sportsTypeMaster) FROM SportsTypeMaster sportsTypeMaster";

	private static final String _SQL_COUNT_SPORTSTYPEMASTER_WHERE =
		"SELECT COUNT(sportsTypeMaster) FROM SportsTypeMaster sportsTypeMaster WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "sportsTypeMaster.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SportsTypeMaster exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No SportsTypeMaster exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		SportsTypeMasterPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}