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

import com.mhdsys.schema.exception.NoSuchDistrictGrantSchemeException;
import com.mhdsys.schema.model.DistrictGrantScheme;
import com.mhdsys.schema.model.DistrictGrantSchemeTable;
import com.mhdsys.schema.model.impl.DistrictGrantSchemeImpl;
import com.mhdsys.schema.model.impl.DistrictGrantSchemeModelImpl;
import com.mhdsys.schema.service.persistence.DistrictGrantSchemePersistence;
import com.mhdsys.schema.service.persistence.DistrictGrantSchemeUtil;
import com.mhdsys.schema.service.persistence.impl.constants.mhdsysPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

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
 * The persistence implementation for the district grant scheme service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = DistrictGrantSchemePersistence.class)
public class DistrictGrantSchemePersistenceImpl
	extends BasePersistenceImpl<DistrictGrantScheme>
	implements DistrictGrantSchemePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>DistrictGrantSchemeUtil</code> to access the district grant scheme persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		DistrictGrantSchemeImpl.class.getName();

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
	 * Returns all the district grant schemes where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching district grant schemes
	 */
	@Override
	public List<DistrictGrantScheme> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the district grant schemes where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DistrictGrantSchemeModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of district grant schemes
	 * @param end the upper bound of the range of district grant schemes (not inclusive)
	 * @return the range of matching district grant schemes
	 */
	@Override
	public List<DistrictGrantScheme> findByUserId(
		long userId, int start, int end) {

		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the district grant schemes where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DistrictGrantSchemeModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of district grant schemes
	 * @param end the upper bound of the range of district grant schemes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching district grant schemes
	 */
	@Override
	public List<DistrictGrantScheme> findByUserId(
		long userId, int start, int end,
		OrderByComparator<DistrictGrantScheme> orderByComparator) {

		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the district grant schemes where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DistrictGrantSchemeModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of district grant schemes
	 * @param end the upper bound of the range of district grant schemes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching district grant schemes
	 */
	@Override
	public List<DistrictGrantScheme> findByUserId(
		long userId, int start, int end,
		OrderByComparator<DistrictGrantScheme> orderByComparator,
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

		List<DistrictGrantScheme> list = null;

		if (useFinderCache) {
			list = (List<DistrictGrantScheme>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DistrictGrantScheme districtGrantScheme : list) {
					if (userId != districtGrantScheme.getUserId()) {
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

			sb.append(_SQL_SELECT_DISTRICTGRANTSCHEME_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DistrictGrantSchemeModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				list = (List<DistrictGrantScheme>)QueryUtil.list(
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
	 * Returns the first district grant scheme in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching district grant scheme
	 * @throws NoSuchDistrictGrantSchemeException if a matching district grant scheme could not be found
	 */
	@Override
	public DistrictGrantScheme findByUserId_First(
			long userId,
			OrderByComparator<DistrictGrantScheme> orderByComparator)
		throws NoSuchDistrictGrantSchemeException {

		DistrictGrantScheme districtGrantScheme = fetchByUserId_First(
			userId, orderByComparator);

		if (districtGrantScheme != null) {
			return districtGrantScheme;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchDistrictGrantSchemeException(sb.toString());
	}

	/**
	 * Returns the first district grant scheme in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching district grant scheme, or <code>null</code> if a matching district grant scheme could not be found
	 */
	@Override
	public DistrictGrantScheme fetchByUserId_First(
		long userId, OrderByComparator<DistrictGrantScheme> orderByComparator) {

		List<DistrictGrantScheme> list = findByUserId(
			userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last district grant scheme in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching district grant scheme
	 * @throws NoSuchDistrictGrantSchemeException if a matching district grant scheme could not be found
	 */
	@Override
	public DistrictGrantScheme findByUserId_Last(
			long userId,
			OrderByComparator<DistrictGrantScheme> orderByComparator)
		throws NoSuchDistrictGrantSchemeException {

		DistrictGrantScheme districtGrantScheme = fetchByUserId_Last(
			userId, orderByComparator);

		if (districtGrantScheme != null) {
			return districtGrantScheme;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchDistrictGrantSchemeException(sb.toString());
	}

	/**
	 * Returns the last district grant scheme in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching district grant scheme, or <code>null</code> if a matching district grant scheme could not be found
	 */
	@Override
	public DistrictGrantScheme fetchByUserId_Last(
		long userId, OrderByComparator<DistrictGrantScheme> orderByComparator) {

		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<DistrictGrantScheme> list = findByUserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the district grant schemes before and after the current district grant scheme in the ordered set where userId = &#63;.
	 *
	 * @param districtGrantSchemeId the primary key of the current district grant scheme
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next district grant scheme
	 * @throws NoSuchDistrictGrantSchemeException if a district grant scheme with the primary key could not be found
	 */
	@Override
	public DistrictGrantScheme[] findByUserId_PrevAndNext(
			long districtGrantSchemeId, long userId,
			OrderByComparator<DistrictGrantScheme> orderByComparator)
		throws NoSuchDistrictGrantSchemeException {

		DistrictGrantScheme districtGrantScheme = findByPrimaryKey(
			districtGrantSchemeId);

		Session session = null;

		try {
			session = openSession();

			DistrictGrantScheme[] array = new DistrictGrantSchemeImpl[3];

			array[0] = getByUserId_PrevAndNext(
				session, districtGrantScheme, userId, orderByComparator, true);

			array[1] = districtGrantScheme;

			array[2] = getByUserId_PrevAndNext(
				session, districtGrantScheme, userId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected DistrictGrantScheme getByUserId_PrevAndNext(
		Session session, DistrictGrantScheme districtGrantScheme, long userId,
		OrderByComparator<DistrictGrantScheme> orderByComparator,
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

		sb.append(_SQL_SELECT_DISTRICTGRANTSCHEME_WHERE);

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
			sb.append(DistrictGrantSchemeModelImpl.ORDER_BY_JPQL);
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
						districtGrantScheme)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DistrictGrantScheme> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the district grant schemes where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (DistrictGrantScheme districtGrantScheme :
				findByUserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(districtGrantScheme);
		}
	}

	/**
	 * Returns the number of district grant schemes where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching district grant schemes
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DISTRICTGRANTSCHEME_WHERE);

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
		"districtGrantScheme.userId = ?";

	public DistrictGrantSchemePersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("type", "type_");

		setDBColumnNames(dbColumnNames);

		setModelClass(DistrictGrantScheme.class);

		setModelImplClass(DistrictGrantSchemeImpl.class);
		setModelPKClass(long.class);

		setTable(DistrictGrantSchemeTable.INSTANCE);
	}

	/**
	 * Caches the district grant scheme in the entity cache if it is enabled.
	 *
	 * @param districtGrantScheme the district grant scheme
	 */
	@Override
	public void cacheResult(DistrictGrantScheme districtGrantScheme) {
		entityCache.putResult(
			DistrictGrantSchemeImpl.class, districtGrantScheme.getPrimaryKey(),
			districtGrantScheme);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the district grant schemes in the entity cache if it is enabled.
	 *
	 * @param districtGrantSchemes the district grant schemes
	 */
	@Override
	public void cacheResult(List<DistrictGrantScheme> districtGrantSchemes) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (districtGrantSchemes.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (DistrictGrantScheme districtGrantScheme : districtGrantSchemes) {
			if (entityCache.getResult(
					DistrictGrantSchemeImpl.class,
					districtGrantScheme.getPrimaryKey()) == null) {

				cacheResult(districtGrantScheme);
			}
		}
	}

	/**
	 * Clears the cache for all district grant schemes.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DistrictGrantSchemeImpl.class);

		finderCache.clearCache(DistrictGrantSchemeImpl.class);
	}

	/**
	 * Clears the cache for the district grant scheme.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DistrictGrantScheme districtGrantScheme) {
		entityCache.removeResult(
			DistrictGrantSchemeImpl.class, districtGrantScheme);
	}

	@Override
	public void clearCache(List<DistrictGrantScheme> districtGrantSchemes) {
		for (DistrictGrantScheme districtGrantScheme : districtGrantSchemes) {
			entityCache.removeResult(
				DistrictGrantSchemeImpl.class, districtGrantScheme);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(DistrictGrantSchemeImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(DistrictGrantSchemeImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new district grant scheme with the primary key. Does not add the district grant scheme to the database.
	 *
	 * @param districtGrantSchemeId the primary key for the new district grant scheme
	 * @return the new district grant scheme
	 */
	@Override
	public DistrictGrantScheme create(long districtGrantSchemeId) {
		DistrictGrantScheme districtGrantScheme = new DistrictGrantSchemeImpl();

		districtGrantScheme.setNew(true);
		districtGrantScheme.setPrimaryKey(districtGrantSchemeId);

		return districtGrantScheme;
	}

	/**
	 * Removes the district grant scheme with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param districtGrantSchemeId the primary key of the district grant scheme
	 * @return the district grant scheme that was removed
	 * @throws NoSuchDistrictGrantSchemeException if a district grant scheme with the primary key could not be found
	 */
	@Override
	public DistrictGrantScheme remove(long districtGrantSchemeId)
		throws NoSuchDistrictGrantSchemeException {

		return remove((Serializable)districtGrantSchemeId);
	}

	/**
	 * Removes the district grant scheme with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the district grant scheme
	 * @return the district grant scheme that was removed
	 * @throws NoSuchDistrictGrantSchemeException if a district grant scheme with the primary key could not be found
	 */
	@Override
	public DistrictGrantScheme remove(Serializable primaryKey)
		throws NoSuchDistrictGrantSchemeException {

		Session session = null;

		try {
			session = openSession();

			DistrictGrantScheme districtGrantScheme =
				(DistrictGrantScheme)session.get(
					DistrictGrantSchemeImpl.class, primaryKey);

			if (districtGrantScheme == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDistrictGrantSchemeException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(districtGrantScheme);
		}
		catch (NoSuchDistrictGrantSchemeException noSuchEntityException) {
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
	protected DistrictGrantScheme removeImpl(
		DistrictGrantScheme districtGrantScheme) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(districtGrantScheme)) {
				districtGrantScheme = (DistrictGrantScheme)session.get(
					DistrictGrantSchemeImpl.class,
					districtGrantScheme.getPrimaryKeyObj());
			}

			if (districtGrantScheme != null) {
				session.delete(districtGrantScheme);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (districtGrantScheme != null) {
			clearCache(districtGrantScheme);
		}

		return districtGrantScheme;
	}

	@Override
	public DistrictGrantScheme updateImpl(
		DistrictGrantScheme districtGrantScheme) {

		boolean isNew = districtGrantScheme.isNew();

		if (!(districtGrantScheme instanceof DistrictGrantSchemeModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(districtGrantScheme.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					districtGrantScheme);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in districtGrantScheme proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DistrictGrantScheme implementation " +
					districtGrantScheme.getClass());
		}

		DistrictGrantSchemeModelImpl districtGrantSchemeModelImpl =
			(DistrictGrantSchemeModelImpl)districtGrantScheme;

		if (isNew && (districtGrantScheme.getCreateDate() == null)) {
			ServiceContext serviceContext =
				ServiceContextThreadLocal.getServiceContext();

			Date date = new Date();

			if (serviceContext == null) {
				districtGrantScheme.setCreateDate(date);
			}
			else {
				districtGrantScheme.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(districtGrantScheme);
			}
			else {
				districtGrantScheme = (DistrictGrantScheme)session.merge(
					districtGrantScheme);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			DistrictGrantSchemeImpl.class, districtGrantSchemeModelImpl, false,
			true);

		if (isNew) {
			districtGrantScheme.setNew(false);
		}

		districtGrantScheme.resetOriginalValues();

		return districtGrantScheme;
	}

	/**
	 * Returns the district grant scheme with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the district grant scheme
	 * @return the district grant scheme
	 * @throws NoSuchDistrictGrantSchemeException if a district grant scheme with the primary key could not be found
	 */
	@Override
	public DistrictGrantScheme findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDistrictGrantSchemeException {

		DistrictGrantScheme districtGrantScheme = fetchByPrimaryKey(primaryKey);

		if (districtGrantScheme == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDistrictGrantSchemeException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return districtGrantScheme;
	}

	/**
	 * Returns the district grant scheme with the primary key or throws a <code>NoSuchDistrictGrantSchemeException</code> if it could not be found.
	 *
	 * @param districtGrantSchemeId the primary key of the district grant scheme
	 * @return the district grant scheme
	 * @throws NoSuchDistrictGrantSchemeException if a district grant scheme with the primary key could not be found
	 */
	@Override
	public DistrictGrantScheme findByPrimaryKey(long districtGrantSchemeId)
		throws NoSuchDistrictGrantSchemeException {

		return findByPrimaryKey((Serializable)districtGrantSchemeId);
	}

	/**
	 * Returns the district grant scheme with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param districtGrantSchemeId the primary key of the district grant scheme
	 * @return the district grant scheme, or <code>null</code> if a district grant scheme with the primary key could not be found
	 */
	@Override
	public DistrictGrantScheme fetchByPrimaryKey(long districtGrantSchemeId) {
		return fetchByPrimaryKey((Serializable)districtGrantSchemeId);
	}

	/**
	 * Returns all the district grant schemes.
	 *
	 * @return the district grant schemes
	 */
	@Override
	public List<DistrictGrantScheme> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the district grant schemes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DistrictGrantSchemeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of district grant schemes
	 * @param end the upper bound of the range of district grant schemes (not inclusive)
	 * @return the range of district grant schemes
	 */
	@Override
	public List<DistrictGrantScheme> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the district grant schemes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DistrictGrantSchemeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of district grant schemes
	 * @param end the upper bound of the range of district grant schemes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of district grant schemes
	 */
	@Override
	public List<DistrictGrantScheme> findAll(
		int start, int end,
		OrderByComparator<DistrictGrantScheme> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the district grant schemes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DistrictGrantSchemeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of district grant schemes
	 * @param end the upper bound of the range of district grant schemes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of district grant schemes
	 */
	@Override
	public List<DistrictGrantScheme> findAll(
		int start, int end,
		OrderByComparator<DistrictGrantScheme> orderByComparator,
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

		List<DistrictGrantScheme> list = null;

		if (useFinderCache) {
			list = (List<DistrictGrantScheme>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_DISTRICTGRANTSCHEME);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_DISTRICTGRANTSCHEME;

				sql = sql.concat(DistrictGrantSchemeModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<DistrictGrantScheme>)QueryUtil.list(
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
	 * Removes all the district grant schemes from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DistrictGrantScheme districtGrantScheme : findAll()) {
			remove(districtGrantScheme);
		}
	}

	/**
	 * Returns the number of district grant schemes.
	 *
	 * @return the number of district grant schemes
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
					_SQL_COUNT_DISTRICTGRANTSCHEME);

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
		return "districtGrantSchemeId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_DISTRICTGRANTSCHEME;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return DistrictGrantSchemeModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the district grant scheme persistence.
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

		DistrictGrantSchemeUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		DistrictGrantSchemeUtil.setPersistence(null);

		entityCache.removeCache(DistrictGrantSchemeImpl.class.getName());
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

	private static final String _SQL_SELECT_DISTRICTGRANTSCHEME =
		"SELECT districtGrantScheme FROM DistrictGrantScheme districtGrantScheme";

	private static final String _SQL_SELECT_DISTRICTGRANTSCHEME_WHERE =
		"SELECT districtGrantScheme FROM DistrictGrantScheme districtGrantScheme WHERE ";

	private static final String _SQL_COUNT_DISTRICTGRANTSCHEME =
		"SELECT COUNT(districtGrantScheme) FROM DistrictGrantScheme districtGrantScheme";

	private static final String _SQL_COUNT_DISTRICTGRANTSCHEME_WHERE =
		"SELECT COUNT(districtGrantScheme) FROM DistrictGrantScheme districtGrantScheme WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "districtGrantScheme.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No DistrictGrantScheme exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No DistrictGrantScheme exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		DistrictGrantSchemePersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"type"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}