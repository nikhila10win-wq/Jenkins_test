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
import com.liferay.portal.kernel.util.StringUtil;

import com.mhdsys.schema.exception.NoSuchObjectionException;
import com.mhdsys.schema.model.Objection;
import com.mhdsys.schema.model.ObjectionTable;
import com.mhdsys.schema.model.impl.ObjectionImpl;
import com.mhdsys.schema.model.impl.ObjectionModelImpl;
import com.mhdsys.schema.service.persistence.ObjectionPersistence;
import com.mhdsys.schema.service.persistence.ObjectionUtil;
import com.mhdsys.schema.service.persistence.impl.constants.mhdsysPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the objection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = ObjectionPersistence.class)
public class ObjectionPersistenceImpl
	extends BasePersistenceImpl<Objection> implements ObjectionPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ObjectionUtil</code> to access the objection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ObjectionImpl.class.getName();

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
	 * Returns all the objections where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching objections
	 */
	@Override
	public List<Objection> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the objections where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectionModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of objections
	 * @param end the upper bound of the range of objections (not inclusive)
	 * @return the range of matching objections
	 */
	@Override
	public List<Objection> findByUserId(long userId, int start, int end) {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the objections where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectionModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of objections
	 * @param end the upper bound of the range of objections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching objections
	 */
	@Override
	public List<Objection> findByUserId(
		long userId, int start, int end,
		OrderByComparator<Objection> orderByComparator) {

		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the objections where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectionModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of objections
	 * @param end the upper bound of the range of objections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching objections
	 */
	@Override
	public List<Objection> findByUserId(
		long userId, int start, int end,
		OrderByComparator<Objection> orderByComparator,
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

		List<Objection> list = null;

		if (useFinderCache) {
			list = (List<Objection>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Objection objection : list) {
					if (userId != objection.getUserId()) {
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

			sb.append(_SQL_SELECT_OBJECTION_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ObjectionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				list = (List<Objection>)QueryUtil.list(
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
	 * Returns the first objection in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching objection
	 * @throws NoSuchObjectionException if a matching objection could not be found
	 */
	@Override
	public Objection findByUserId_First(
			long userId, OrderByComparator<Objection> orderByComparator)
		throws NoSuchObjectionException {

		Objection objection = fetchByUserId_First(userId, orderByComparator);

		if (objection != null) {
			return objection;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchObjectionException(sb.toString());
	}

	/**
	 * Returns the first objection in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching objection, or <code>null</code> if a matching objection could not be found
	 */
	@Override
	public Objection fetchByUserId_First(
		long userId, OrderByComparator<Objection> orderByComparator) {

		List<Objection> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last objection in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching objection
	 * @throws NoSuchObjectionException if a matching objection could not be found
	 */
	@Override
	public Objection findByUserId_Last(
			long userId, OrderByComparator<Objection> orderByComparator)
		throws NoSuchObjectionException {

		Objection objection = fetchByUserId_Last(userId, orderByComparator);

		if (objection != null) {
			return objection;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchObjectionException(sb.toString());
	}

	/**
	 * Returns the last objection in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching objection, or <code>null</code> if a matching objection could not be found
	 */
	@Override
	public Objection fetchByUserId_Last(
		long userId, OrderByComparator<Objection> orderByComparator) {

		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<Objection> list = findByUserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the objections before and after the current objection in the ordered set where userId = &#63;.
	 *
	 * @param objectionId the primary key of the current objection
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next objection
	 * @throws NoSuchObjectionException if a objection with the primary key could not be found
	 */
	@Override
	public Objection[] findByUserId_PrevAndNext(
			long objectionId, long userId,
			OrderByComparator<Objection> orderByComparator)
		throws NoSuchObjectionException {

		Objection objection = findByPrimaryKey(objectionId);

		Session session = null;

		try {
			session = openSession();

			Objection[] array = new ObjectionImpl[3];

			array[0] = getByUserId_PrevAndNext(
				session, objection, userId, orderByComparator, true);

			array[1] = objection;

			array[2] = getByUserId_PrevAndNext(
				session, objection, userId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Objection getByUserId_PrevAndNext(
		Session session, Objection objection, long userId,
		OrderByComparator<Objection> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_OBJECTION_WHERE);

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
			sb.append(ObjectionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(userId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(objection)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Objection> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the objections where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (Objection objection :
				findByUserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(objection);
		}
	}

	/**
	 * Returns the number of objections where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching objections
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_OBJECTION_WHERE);

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
		"objection.userId = ?";

	private FinderPath _finderPathFetchByObjectionId;
	private FinderPath _finderPathCountByObjectionId;

	/**
	 * Returns the objection where objectionId = &#63; or throws a <code>NoSuchObjectionException</code> if it could not be found.
	 *
	 * @param objectionId the objection ID
	 * @return the matching objection
	 * @throws NoSuchObjectionException if a matching objection could not be found
	 */
	@Override
	public Objection findByObjectionId(long objectionId)
		throws NoSuchObjectionException {

		Objection objection = fetchByObjectionId(objectionId);

		if (objection == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("objectionId=");
			sb.append(objectionId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchObjectionException(sb.toString());
		}

		return objection;
	}

	/**
	 * Returns the objection where objectionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param objectionId the objection ID
	 * @return the matching objection, or <code>null</code> if a matching objection could not be found
	 */
	@Override
	public Objection fetchByObjectionId(long objectionId) {
		return fetchByObjectionId(objectionId, true);
	}

	/**
	 * Returns the objection where objectionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param objectionId the objection ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching objection, or <code>null</code> if a matching objection could not be found
	 */
	@Override
	public Objection fetchByObjectionId(
		long objectionId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {objectionId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByObjectionId, finderArgs, this);
		}

		if (result instanceof Objection) {
			Objection objection = (Objection)result;

			if (objectionId != objection.getObjectionId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_OBJECTION_WHERE);

			sb.append(_FINDER_COLUMN_OBJECTIONID_OBJECTIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(objectionId);

				List<Objection> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByObjectionId, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {objectionId};
							}

							_log.warn(
								"ObjectionPersistenceImpl.fetchByObjectionId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Objection objection = list.get(0);

					result = objection;

					cacheResult(objection);
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
			return (Objection)result;
		}
	}

	/**
	 * Removes the objection where objectionId = &#63; from the database.
	 *
	 * @param objectionId the objection ID
	 * @return the objection that was removed
	 */
	@Override
	public Objection removeByObjectionId(long objectionId)
		throws NoSuchObjectionException {

		Objection objection = findByObjectionId(objectionId);

		return remove(objection);
	}

	/**
	 * Returns the number of objections where objectionId = &#63;.
	 *
	 * @param objectionId the objection ID
	 * @return the number of matching objections
	 */
	@Override
	public int countByObjectionId(long objectionId) {
		FinderPath finderPath = _finderPathCountByObjectionId;

		Object[] finderArgs = new Object[] {objectionId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_OBJECTION_WHERE);

			sb.append(_FINDER_COLUMN_OBJECTIONID_OBJECTIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(objectionId);

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

	private static final String _FINDER_COLUMN_OBJECTIONID_OBJECTIONID_2 =
		"objection.objectionId = ?";

	public ObjectionPersistenceImpl() {
		setModelClass(Objection.class);

		setModelImplClass(ObjectionImpl.class);
		setModelPKClass(long.class);

		setTable(ObjectionTable.INSTANCE);
	}

	/**
	 * Caches the objection in the entity cache if it is enabled.
	 *
	 * @param objection the objection
	 */
	@Override
	public void cacheResult(Objection objection) {
		entityCache.putResult(
			ObjectionImpl.class, objection.getPrimaryKey(), objection);

		finderCache.putResult(
			_finderPathFetchByObjectionId,
			new Object[] {objection.getObjectionId()}, objection);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the objections in the entity cache if it is enabled.
	 *
	 * @param objections the objections
	 */
	@Override
	public void cacheResult(List<Objection> objections) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (objections.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Objection objection : objections) {
			if (entityCache.getResult(
					ObjectionImpl.class, objection.getPrimaryKey()) == null) {

				cacheResult(objection);
			}
		}
	}

	/**
	 * Clears the cache for all objections.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ObjectionImpl.class);

		finderCache.clearCache(ObjectionImpl.class);
	}

	/**
	 * Clears the cache for the objection.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Objection objection) {
		entityCache.removeResult(ObjectionImpl.class, objection);
	}

	@Override
	public void clearCache(List<Objection> objections) {
		for (Objection objection : objections) {
			entityCache.removeResult(ObjectionImpl.class, objection);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ObjectionImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ObjectionImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		ObjectionModelImpl objectionModelImpl) {

		Object[] args = new Object[] {objectionModelImpl.getObjectionId()};

		finderCache.putResult(
			_finderPathCountByObjectionId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByObjectionId, args, objectionModelImpl);
	}

	/**
	 * Creates a new objection with the primary key. Does not add the objection to the database.
	 *
	 * @param objectionId the primary key for the new objection
	 * @return the new objection
	 */
	@Override
	public Objection create(long objectionId) {
		Objection objection = new ObjectionImpl();

		objection.setNew(true);
		objection.setPrimaryKey(objectionId);

		return objection;
	}

	/**
	 * Removes the objection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param objectionId the primary key of the objection
	 * @return the objection that was removed
	 * @throws NoSuchObjectionException if a objection with the primary key could not be found
	 */
	@Override
	public Objection remove(long objectionId) throws NoSuchObjectionException {
		return remove((Serializable)objectionId);
	}

	/**
	 * Removes the objection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the objection
	 * @return the objection that was removed
	 * @throws NoSuchObjectionException if a objection with the primary key could not be found
	 */
	@Override
	public Objection remove(Serializable primaryKey)
		throws NoSuchObjectionException {

		Session session = null;

		try {
			session = openSession();

			Objection objection = (Objection)session.get(
				ObjectionImpl.class, primaryKey);

			if (objection == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchObjectionException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(objection);
		}
		catch (NoSuchObjectionException noSuchEntityException) {
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
	protected Objection removeImpl(Objection objection) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(objection)) {
				objection = (Objection)session.get(
					ObjectionImpl.class, objection.getPrimaryKeyObj());
			}

			if (objection != null) {
				session.delete(objection);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (objection != null) {
			clearCache(objection);
		}

		return objection;
	}

	@Override
	public Objection updateImpl(Objection objection) {
		boolean isNew = objection.isNew();

		if (!(objection instanceof ObjectionModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(objection.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(objection);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in objection proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Objection implementation " +
					objection.getClass());
		}

		ObjectionModelImpl objectionModelImpl = (ObjectionModelImpl)objection;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(objection);
			}
			else {
				objection = (Objection)session.merge(objection);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			ObjectionImpl.class, objectionModelImpl, false, true);

		cacheUniqueFindersCache(objectionModelImpl);

		if (isNew) {
			objection.setNew(false);
		}

		objection.resetOriginalValues();

		return objection;
	}

	/**
	 * Returns the objection with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the objection
	 * @return the objection
	 * @throws NoSuchObjectionException if a objection with the primary key could not be found
	 */
	@Override
	public Objection findByPrimaryKey(Serializable primaryKey)
		throws NoSuchObjectionException {

		Objection objection = fetchByPrimaryKey(primaryKey);

		if (objection == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchObjectionException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return objection;
	}

	/**
	 * Returns the objection with the primary key or throws a <code>NoSuchObjectionException</code> if it could not be found.
	 *
	 * @param objectionId the primary key of the objection
	 * @return the objection
	 * @throws NoSuchObjectionException if a objection with the primary key could not be found
	 */
	@Override
	public Objection findByPrimaryKey(long objectionId)
		throws NoSuchObjectionException {

		return findByPrimaryKey((Serializable)objectionId);
	}

	/**
	 * Returns the objection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param objectionId the primary key of the objection
	 * @return the objection, or <code>null</code> if a objection with the primary key could not be found
	 */
	@Override
	public Objection fetchByPrimaryKey(long objectionId) {
		return fetchByPrimaryKey((Serializable)objectionId);
	}

	/**
	 * Returns all the objections.
	 *
	 * @return the objections
	 */
	@Override
	public List<Objection> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the objections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of objections
	 * @param end the upper bound of the range of objections (not inclusive)
	 * @return the range of objections
	 */
	@Override
	public List<Objection> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the objections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of objections
	 * @param end the upper bound of the range of objections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of objections
	 */
	@Override
	public List<Objection> findAll(
		int start, int end, OrderByComparator<Objection> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the objections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of objections
	 * @param end the upper bound of the range of objections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of objections
	 */
	@Override
	public List<Objection> findAll(
		int start, int end, OrderByComparator<Objection> orderByComparator,
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

		List<Objection> list = null;

		if (useFinderCache) {
			list = (List<Objection>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_OBJECTION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_OBJECTION;

				sql = sql.concat(ObjectionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Objection>)QueryUtil.list(
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
	 * Removes all the objections from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Objection objection : findAll()) {
			remove(objection);
		}
	}

	/**
	 * Returns the number of objections.
	 *
	 * @return the number of objections
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_OBJECTION);

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
		return "objectionId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_OBJECTION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ObjectionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the objection persistence.
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

		_finderPathFetchByObjectionId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByObjectionId",
			new String[] {Long.class.getName()}, new String[] {"objectionId"},
			true);

		_finderPathCountByObjectionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByObjectionId",
			new String[] {Long.class.getName()}, new String[] {"objectionId"},
			false);

		ObjectionUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		ObjectionUtil.setPersistence(null);

		entityCache.removeCache(ObjectionImpl.class.getName());
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

	private static final String _SQL_SELECT_OBJECTION =
		"SELECT objection FROM Objection objection";

	private static final String _SQL_SELECT_OBJECTION_WHERE =
		"SELECT objection FROM Objection objection WHERE ";

	private static final String _SQL_COUNT_OBJECTION =
		"SELECT COUNT(objection) FROM Objection objection";

	private static final String _SQL_COUNT_OBJECTION_WHERE =
		"SELECT COUNT(objection) FROM Objection objection WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "objection.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Objection exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Objection exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ObjectionPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}