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

import com.mhdsys.schema.exception.NoSuchIntSportsCompException;
import com.mhdsys.schema.model.IntSportsComp;
import com.mhdsys.schema.model.IntSportsCompTable;
import com.mhdsys.schema.model.impl.IntSportsCompImpl;
import com.mhdsys.schema.model.impl.IntSportsCompModelImpl;
import com.mhdsys.schema.service.persistence.IntSportsCompPersistence;
import com.mhdsys.schema.service.persistence.IntSportsCompUtil;
import com.mhdsys.schema.service.persistence.impl.constants.mhdsysPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

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
 * The persistence implementation for the int sports comp service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = IntSportsCompPersistence.class)
public class IntSportsCompPersistenceImpl
	extends BasePersistenceImpl<IntSportsComp>
	implements IntSportsCompPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>IntSportsCompUtil</code> to access the int sports comp persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		IntSportsCompImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUserId;
	private FinderPath _finderPathWithoutPaginationFindByUserId;
	private FinderPath _finderPathCountByUserId;

	/**
	 * Returns all the int sports comps where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching int sports comps
	 */
	@Override
	public List<IntSportsComp> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the int sports comps where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>IntSportsCompModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of int sports comps
	 * @param end the upper bound of the range of int sports comps (not inclusive)
	 * @return the range of matching int sports comps
	 */
	@Override
	public List<IntSportsComp> findByUserId(long userId, int start, int end) {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the int sports comps where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>IntSportsCompModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of int sports comps
	 * @param end the upper bound of the range of int sports comps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching int sports comps
	 */
	@Override
	public List<IntSportsComp> findByUserId(
		long userId, int start, int end,
		OrderByComparator<IntSportsComp> orderByComparator) {

		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the int sports comps where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>IntSportsCompModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of int sports comps
	 * @param end the upper bound of the range of int sports comps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching int sports comps
	 */
	@Override
	public List<IntSportsComp> findByUserId(
		long userId, int start, int end,
		OrderByComparator<IntSportsComp> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUserId;
				finderArgs = new Object[] {userId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUserId;
			finderArgs = new Object[] {userId, start, end, orderByComparator};
		}

		List<IntSportsComp> list = null;

		if (useFinderCache) {
			list = (List<IntSportsComp>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (IntSportsComp intSportsComp : list) {
					if (userId != intSportsComp.getUserId()) {
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

			sb.append(_SQL_SELECT_INTSPORTSCOMP_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(IntSportsCompModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				list = (List<IntSportsComp>)QueryUtil.list(
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
	 * Returns the first int sports comp in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching int sports comp
	 * @throws NoSuchIntSportsCompException if a matching int sports comp could not be found
	 */
	@Override
	public IntSportsComp findByUserId_First(
			long userId, OrderByComparator<IntSportsComp> orderByComparator)
		throws NoSuchIntSportsCompException {

		IntSportsComp intSportsComp = fetchByUserId_First(
			userId, orderByComparator);

		if (intSportsComp != null) {
			return intSportsComp;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchIntSportsCompException(sb.toString());
	}

	/**
	 * Returns the first int sports comp in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching int sports comp, or <code>null</code> if a matching int sports comp could not be found
	 */
	@Override
	public IntSportsComp fetchByUserId_First(
		long userId, OrderByComparator<IntSportsComp> orderByComparator) {

		List<IntSportsComp> list = findByUserId(
			userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last int sports comp in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching int sports comp
	 * @throws NoSuchIntSportsCompException if a matching int sports comp could not be found
	 */
	@Override
	public IntSportsComp findByUserId_Last(
			long userId, OrderByComparator<IntSportsComp> orderByComparator)
		throws NoSuchIntSportsCompException {

		IntSportsComp intSportsComp = fetchByUserId_Last(
			userId, orderByComparator);

		if (intSportsComp != null) {
			return intSportsComp;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchIntSportsCompException(sb.toString());
	}

	/**
	 * Returns the last int sports comp in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching int sports comp, or <code>null</code> if a matching int sports comp could not be found
	 */
	@Override
	public IntSportsComp fetchByUserId_Last(
		long userId, OrderByComparator<IntSportsComp> orderByComparator) {

		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<IntSportsComp> list = findByUserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the int sports comps before and after the current int sports comp in the ordered set where userId = &#63;.
	 *
	 * @param intSportsCompId the primary key of the current int sports comp
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next int sports comp
	 * @throws NoSuchIntSportsCompException if a int sports comp with the primary key could not be found
	 */
	@Override
	public IntSportsComp[] findByUserId_PrevAndNext(
			long intSportsCompId, long userId,
			OrderByComparator<IntSportsComp> orderByComparator)
		throws NoSuchIntSportsCompException {

		IntSportsComp intSportsComp = findByPrimaryKey(intSportsCompId);

		Session session = null;

		try {
			session = openSession();

			IntSportsComp[] array = new IntSportsCompImpl[3];

			array[0] = getByUserId_PrevAndNext(
				session, intSportsComp, userId, orderByComparator, true);

			array[1] = intSportsComp;

			array[2] = getByUserId_PrevAndNext(
				session, intSportsComp, userId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected IntSportsComp getByUserId_PrevAndNext(
		Session session, IntSportsComp intSportsComp, long userId,
		OrderByComparator<IntSportsComp> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_INTSPORTSCOMP_WHERE);

		sb.append(_FINDER_COLUMN_USERID_USERID_2);

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
			sb.append(IntSportsCompModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(userId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						intSportsComp)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<IntSportsComp> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the int sports comps where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (IntSportsComp intSportsComp :
				findByUserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(intSportsComp);
		}
	}

	/**
	 * Returns the number of int sports comps where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching int sports comps
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_INTSPORTSCOMP_WHERE);

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
		"intSportsComp.userId = ?";

	public IntSportsCompPersistenceImpl() {
		setModelClass(IntSportsComp.class);

		setModelImplClass(IntSportsCompImpl.class);
		setModelPKClass(long.class);

		setTable(IntSportsCompTable.INSTANCE);
	}

	/**
	 * Caches the int sports comp in the entity cache if it is enabled.
	 *
	 * @param intSportsComp the int sports comp
	 */
	@Override
	public void cacheResult(IntSportsComp intSportsComp) {
		entityCache.putResult(
			IntSportsCompImpl.class, intSportsComp.getPrimaryKey(),
			intSportsComp);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the int sports comps in the entity cache if it is enabled.
	 *
	 * @param intSportsComps the int sports comps
	 */
	@Override
	public void cacheResult(List<IntSportsComp> intSportsComps) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (intSportsComps.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (IntSportsComp intSportsComp : intSportsComps) {
			if (entityCache.getResult(
					IntSportsCompImpl.class, intSportsComp.getPrimaryKey()) ==
						null) {

				cacheResult(intSportsComp);
			}
		}
	}

	/**
	 * Clears the cache for all int sports comps.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IntSportsCompImpl.class);

		finderCache.clearCache(IntSportsCompImpl.class);
	}

	/**
	 * Clears the cache for the int sports comp.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IntSportsComp intSportsComp) {
		entityCache.removeResult(IntSportsCompImpl.class, intSportsComp);
	}

	@Override
	public void clearCache(List<IntSportsComp> intSportsComps) {
		for (IntSportsComp intSportsComp : intSportsComps) {
			entityCache.removeResult(IntSportsCompImpl.class, intSportsComp);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(IntSportsCompImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(IntSportsCompImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new int sports comp with the primary key. Does not add the int sports comp to the database.
	 *
	 * @param intSportsCompId the primary key for the new int sports comp
	 * @return the new int sports comp
	 */
	@Override
	public IntSportsComp create(long intSportsCompId) {
		IntSportsComp intSportsComp = new IntSportsCompImpl();

		intSportsComp.setNew(true);
		intSportsComp.setPrimaryKey(intSportsCompId);

		return intSportsComp;
	}

	/**
	 * Removes the int sports comp with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param intSportsCompId the primary key of the int sports comp
	 * @return the int sports comp that was removed
	 * @throws NoSuchIntSportsCompException if a int sports comp with the primary key could not be found
	 */
	@Override
	public IntSportsComp remove(long intSportsCompId)
		throws NoSuchIntSportsCompException {

		return remove((Serializable)intSportsCompId);
	}

	/**
	 * Removes the int sports comp with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the int sports comp
	 * @return the int sports comp that was removed
	 * @throws NoSuchIntSportsCompException if a int sports comp with the primary key could not be found
	 */
	@Override
	public IntSportsComp remove(Serializable primaryKey)
		throws NoSuchIntSportsCompException {

		Session session = null;

		try {
			session = openSession();

			IntSportsComp intSportsComp = (IntSportsComp)session.get(
				IntSportsCompImpl.class, primaryKey);

			if (intSportsComp == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIntSportsCompException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(intSportsComp);
		}
		catch (NoSuchIntSportsCompException noSuchEntityException) {
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
	protected IntSportsComp removeImpl(IntSportsComp intSportsComp) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(intSportsComp)) {
				intSportsComp = (IntSportsComp)session.get(
					IntSportsCompImpl.class, intSportsComp.getPrimaryKeyObj());
			}

			if (intSportsComp != null) {
				session.delete(intSportsComp);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (intSportsComp != null) {
			clearCache(intSportsComp);
		}

		return intSportsComp;
	}

	@Override
	public IntSportsComp updateImpl(IntSportsComp intSportsComp) {
		boolean isNew = intSportsComp.isNew();

		if (!(intSportsComp instanceof IntSportsCompModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(intSportsComp.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					intSportsComp);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in intSportsComp proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom IntSportsComp implementation " +
					intSportsComp.getClass());
		}

		IntSportsCompModelImpl intSportsCompModelImpl =
			(IntSportsCompModelImpl)intSportsComp;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (intSportsComp.getCreateDate() == null)) {
			if (serviceContext == null) {
				intSportsComp.setCreateDate(date);
			}
			else {
				intSportsComp.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!intSportsCompModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				intSportsComp.setModifiedDate(date);
			}
			else {
				intSportsComp.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(intSportsComp);
			}
			else {
				intSportsComp = (IntSportsComp)session.merge(intSportsComp);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			IntSportsCompImpl.class, intSportsCompModelImpl, false, true);

		if (isNew) {
			intSportsComp.setNew(false);
		}

		intSportsComp.resetOriginalValues();

		return intSportsComp;
	}

	/**
	 * Returns the int sports comp with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the int sports comp
	 * @return the int sports comp
	 * @throws NoSuchIntSportsCompException if a int sports comp with the primary key could not be found
	 */
	@Override
	public IntSportsComp findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIntSportsCompException {

		IntSportsComp intSportsComp = fetchByPrimaryKey(primaryKey);

		if (intSportsComp == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIntSportsCompException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return intSportsComp;
	}

	/**
	 * Returns the int sports comp with the primary key or throws a <code>NoSuchIntSportsCompException</code> if it could not be found.
	 *
	 * @param intSportsCompId the primary key of the int sports comp
	 * @return the int sports comp
	 * @throws NoSuchIntSportsCompException if a int sports comp with the primary key could not be found
	 */
	@Override
	public IntSportsComp findByPrimaryKey(long intSportsCompId)
		throws NoSuchIntSportsCompException {

		return findByPrimaryKey((Serializable)intSportsCompId);
	}

	/**
	 * Returns the int sports comp with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param intSportsCompId the primary key of the int sports comp
	 * @return the int sports comp, or <code>null</code> if a int sports comp with the primary key could not be found
	 */
	@Override
	public IntSportsComp fetchByPrimaryKey(long intSportsCompId) {
		return fetchByPrimaryKey((Serializable)intSportsCompId);
	}

	/**
	 * Returns all the int sports comps.
	 *
	 * @return the int sports comps
	 */
	@Override
	public List<IntSportsComp> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the int sports comps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>IntSportsCompModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of int sports comps
	 * @param end the upper bound of the range of int sports comps (not inclusive)
	 * @return the range of int sports comps
	 */
	@Override
	public List<IntSportsComp> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the int sports comps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>IntSportsCompModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of int sports comps
	 * @param end the upper bound of the range of int sports comps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of int sports comps
	 */
	@Override
	public List<IntSportsComp> findAll(
		int start, int end,
		OrderByComparator<IntSportsComp> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the int sports comps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>IntSportsCompModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of int sports comps
	 * @param end the upper bound of the range of int sports comps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of int sports comps
	 */
	@Override
	public List<IntSportsComp> findAll(
		int start, int end, OrderByComparator<IntSportsComp> orderByComparator,
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

		List<IntSportsComp> list = null;

		if (useFinderCache) {
			list = (List<IntSportsComp>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_INTSPORTSCOMP);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_INTSPORTSCOMP;

				sql = sql.concat(IntSportsCompModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<IntSportsComp>)QueryUtil.list(
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
	 * Removes all the int sports comps from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IntSportsComp intSportsComp : findAll()) {
			remove(intSportsComp);
		}
	}

	/**
	 * Returns the number of int sports comps.
	 *
	 * @return the number of int sports comps
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_INTSPORTSCOMP);

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
		return "intSportsCompId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_INTSPORTSCOMP;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return IntSportsCompModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the int sports comp persistence.
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

		_finderPathWithPaginationFindByUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"userId"}, true);

		_finderPathWithoutPaginationFindByUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] {Long.class.getName()}, new String[] {"userId"}, true);

		_finderPathCountByUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] {Long.class.getName()}, new String[] {"userId"},
			false);

		IntSportsCompUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		IntSportsCompUtil.setPersistence(null);

		entityCache.removeCache(IntSportsCompImpl.class.getName());
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

	private static final String _SQL_SELECT_INTSPORTSCOMP =
		"SELECT intSportsComp FROM IntSportsComp intSportsComp";

	private static final String _SQL_SELECT_INTSPORTSCOMP_WHERE =
		"SELECT intSportsComp FROM IntSportsComp intSportsComp WHERE ";

	private static final String _SQL_COUNT_INTSPORTSCOMP =
		"SELECT COUNT(intSportsComp) FROM IntSportsComp intSportsComp";

	private static final String _SQL_COUNT_INTSPORTSCOMP_WHERE =
		"SELECT COUNT(intSportsComp) FROM IntSportsComp intSportsComp WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "intSportsComp.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No IntSportsComp exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No IntSportsComp exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		IntSportsCompPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}