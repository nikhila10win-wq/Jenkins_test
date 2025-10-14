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

import com.mhdsys.schema.exception.NoSuchFinancialAssistanceException;
import com.mhdsys.schema.model.FinancialAssistance;
import com.mhdsys.schema.model.FinancialAssistanceTable;
import com.mhdsys.schema.model.impl.FinancialAssistanceImpl;
import com.mhdsys.schema.model.impl.FinancialAssistanceModelImpl;
import com.mhdsys.schema.service.persistence.FinancialAssistancePersistence;
import com.mhdsys.schema.service.persistence.FinancialAssistanceUtil;
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
 * The persistence implementation for the financial assistance service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = FinancialAssistancePersistence.class)
public class FinancialAssistancePersistenceImpl
	extends BasePersistenceImpl<FinancialAssistance>
	implements FinancialAssistancePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>FinancialAssistanceUtil</code> to access the financial assistance persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		FinancialAssistanceImpl.class.getName();

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
	 * Returns all the financial assistances where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching financial assistances
	 */
	@Override
	public List<FinancialAssistance> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the financial assistances where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinancialAssistanceModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of financial assistances
	 * @param end the upper bound of the range of financial assistances (not inclusive)
	 * @return the range of matching financial assistances
	 */
	@Override
	public List<FinancialAssistance> findByUserId(
		long userId, int start, int end) {

		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the financial assistances where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinancialAssistanceModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of financial assistances
	 * @param end the upper bound of the range of financial assistances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching financial assistances
	 */
	@Override
	public List<FinancialAssistance> findByUserId(
		long userId, int start, int end,
		OrderByComparator<FinancialAssistance> orderByComparator) {

		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the financial assistances where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinancialAssistanceModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of financial assistances
	 * @param end the upper bound of the range of financial assistances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching financial assistances
	 */
	@Override
	public List<FinancialAssistance> findByUserId(
		long userId, int start, int end,
		OrderByComparator<FinancialAssistance> orderByComparator,
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

		List<FinancialAssistance> list = null;

		if (useFinderCache) {
			list = (List<FinancialAssistance>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FinancialAssistance financialAssistance : list) {
					if (userId != financialAssistance.getUserId()) {
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

			sb.append(_SQL_SELECT_FINANCIALASSISTANCE_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(FinancialAssistanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				list = (List<FinancialAssistance>)QueryUtil.list(
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
	 * Returns the first financial assistance in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching financial assistance
	 * @throws NoSuchFinancialAssistanceException if a matching financial assistance could not be found
	 */
	@Override
	public FinancialAssistance findByUserId_First(
			long userId,
			OrderByComparator<FinancialAssistance> orderByComparator)
		throws NoSuchFinancialAssistanceException {

		FinancialAssistance financialAssistance = fetchByUserId_First(
			userId, orderByComparator);

		if (financialAssistance != null) {
			return financialAssistance;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchFinancialAssistanceException(sb.toString());
	}

	/**
	 * Returns the first financial assistance in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching financial assistance, or <code>null</code> if a matching financial assistance could not be found
	 */
	@Override
	public FinancialAssistance fetchByUserId_First(
		long userId, OrderByComparator<FinancialAssistance> orderByComparator) {

		List<FinancialAssistance> list = findByUserId(
			userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last financial assistance in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching financial assistance
	 * @throws NoSuchFinancialAssistanceException if a matching financial assistance could not be found
	 */
	@Override
	public FinancialAssistance findByUserId_Last(
			long userId,
			OrderByComparator<FinancialAssistance> orderByComparator)
		throws NoSuchFinancialAssistanceException {

		FinancialAssistance financialAssistance = fetchByUserId_Last(
			userId, orderByComparator);

		if (financialAssistance != null) {
			return financialAssistance;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchFinancialAssistanceException(sb.toString());
	}

	/**
	 * Returns the last financial assistance in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching financial assistance, or <code>null</code> if a matching financial assistance could not be found
	 */
	@Override
	public FinancialAssistance fetchByUserId_Last(
		long userId, OrderByComparator<FinancialAssistance> orderByComparator) {

		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<FinancialAssistance> list = findByUserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the financial assistances before and after the current financial assistance in the ordered set where userId = &#63;.
	 *
	 * @param financialAssistanceId the primary key of the current financial assistance
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next financial assistance
	 * @throws NoSuchFinancialAssistanceException if a financial assistance with the primary key could not be found
	 */
	@Override
	public FinancialAssistance[] findByUserId_PrevAndNext(
			long financialAssistanceId, long userId,
			OrderByComparator<FinancialAssistance> orderByComparator)
		throws NoSuchFinancialAssistanceException {

		FinancialAssistance financialAssistance = findByPrimaryKey(
			financialAssistanceId);

		Session session = null;

		try {
			session = openSession();

			FinancialAssistance[] array = new FinancialAssistanceImpl[3];

			array[0] = getByUserId_PrevAndNext(
				session, financialAssistance, userId, orderByComparator, true);

			array[1] = financialAssistance;

			array[2] = getByUserId_PrevAndNext(
				session, financialAssistance, userId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected FinancialAssistance getByUserId_PrevAndNext(
		Session session, FinancialAssistance financialAssistance, long userId,
		OrderByComparator<FinancialAssistance> orderByComparator,
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

		sb.append(_SQL_SELECT_FINANCIALASSISTANCE_WHERE);

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
			sb.append(FinancialAssistanceModelImpl.ORDER_BY_JPQL);
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
						financialAssistance)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<FinancialAssistance> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the financial assistances where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (FinancialAssistance financialAssistance :
				findByUserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(financialAssistance);
		}
	}

	/**
	 * Returns the number of financial assistances where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching financial assistances
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_FINANCIALASSISTANCE_WHERE);

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
		"financialAssistance.userId = ?";

	public FinancialAssistancePersistenceImpl() {
		setModelClass(FinancialAssistance.class);

		setModelImplClass(FinancialAssistanceImpl.class);
		setModelPKClass(long.class);

		setTable(FinancialAssistanceTable.INSTANCE);
	}

	/**
	 * Caches the financial assistance in the entity cache if it is enabled.
	 *
	 * @param financialAssistance the financial assistance
	 */
	@Override
	public void cacheResult(FinancialAssistance financialAssistance) {
		entityCache.putResult(
			FinancialAssistanceImpl.class, financialAssistance.getPrimaryKey(),
			financialAssistance);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the financial assistances in the entity cache if it is enabled.
	 *
	 * @param financialAssistances the financial assistances
	 */
	@Override
	public void cacheResult(List<FinancialAssistance> financialAssistances) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (financialAssistances.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (FinancialAssistance financialAssistance : financialAssistances) {
			if (entityCache.getResult(
					FinancialAssistanceImpl.class,
					financialAssistance.getPrimaryKey()) == null) {

				cacheResult(financialAssistance);
			}
		}
	}

	/**
	 * Clears the cache for all financial assistances.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(FinancialAssistanceImpl.class);

		finderCache.clearCache(FinancialAssistanceImpl.class);
	}

	/**
	 * Clears the cache for the financial assistance.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(FinancialAssistance financialAssistance) {
		entityCache.removeResult(
			FinancialAssistanceImpl.class, financialAssistance);
	}

	@Override
	public void clearCache(List<FinancialAssistance> financialAssistances) {
		for (FinancialAssistance financialAssistance : financialAssistances) {
			entityCache.removeResult(
				FinancialAssistanceImpl.class, financialAssistance);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FinancialAssistanceImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(FinancialAssistanceImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new financial assistance with the primary key. Does not add the financial assistance to the database.
	 *
	 * @param financialAssistanceId the primary key for the new financial assistance
	 * @return the new financial assistance
	 */
	@Override
	public FinancialAssistance create(long financialAssistanceId) {
		FinancialAssistance financialAssistance = new FinancialAssistanceImpl();

		financialAssistance.setNew(true);
		financialAssistance.setPrimaryKey(financialAssistanceId);

		return financialAssistance;
	}

	/**
	 * Removes the financial assistance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param financialAssistanceId the primary key of the financial assistance
	 * @return the financial assistance that was removed
	 * @throws NoSuchFinancialAssistanceException if a financial assistance with the primary key could not be found
	 */
	@Override
	public FinancialAssistance remove(long financialAssistanceId)
		throws NoSuchFinancialAssistanceException {

		return remove((Serializable)financialAssistanceId);
	}

	/**
	 * Removes the financial assistance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the financial assistance
	 * @return the financial assistance that was removed
	 * @throws NoSuchFinancialAssistanceException if a financial assistance with the primary key could not be found
	 */
	@Override
	public FinancialAssistance remove(Serializable primaryKey)
		throws NoSuchFinancialAssistanceException {

		Session session = null;

		try {
			session = openSession();

			FinancialAssistance financialAssistance =
				(FinancialAssistance)session.get(
					FinancialAssistanceImpl.class, primaryKey);

			if (financialAssistance == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFinancialAssistanceException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(financialAssistance);
		}
		catch (NoSuchFinancialAssistanceException noSuchEntityException) {
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
	protected FinancialAssistance removeImpl(
		FinancialAssistance financialAssistance) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(financialAssistance)) {
				financialAssistance = (FinancialAssistance)session.get(
					FinancialAssistanceImpl.class,
					financialAssistance.getPrimaryKeyObj());
			}

			if (financialAssistance != null) {
				session.delete(financialAssistance);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (financialAssistance != null) {
			clearCache(financialAssistance);
		}

		return financialAssistance;
	}

	@Override
	public FinancialAssistance updateImpl(
		FinancialAssistance financialAssistance) {

		boolean isNew = financialAssistance.isNew();

		if (!(financialAssistance instanceof FinancialAssistanceModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(financialAssistance.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					financialAssistance);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in financialAssistance proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom FinancialAssistance implementation " +
					financialAssistance.getClass());
		}

		FinancialAssistanceModelImpl financialAssistanceModelImpl =
			(FinancialAssistanceModelImpl)financialAssistance;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (financialAssistance.getCreateDate() == null)) {
			if (serviceContext == null) {
				financialAssistance.setCreateDate(date);
			}
			else {
				financialAssistance.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!financialAssistanceModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				financialAssistance.setModifiedDate(date);
			}
			else {
				financialAssistance.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(financialAssistance);
			}
			else {
				financialAssistance = (FinancialAssistance)session.merge(
					financialAssistance);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			FinancialAssistanceImpl.class, financialAssistanceModelImpl, false,
			true);

		if (isNew) {
			financialAssistance.setNew(false);
		}

		financialAssistance.resetOriginalValues();

		return financialAssistance;
	}

	/**
	 * Returns the financial assistance with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the financial assistance
	 * @return the financial assistance
	 * @throws NoSuchFinancialAssistanceException if a financial assistance with the primary key could not be found
	 */
	@Override
	public FinancialAssistance findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFinancialAssistanceException {

		FinancialAssistance financialAssistance = fetchByPrimaryKey(primaryKey);

		if (financialAssistance == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFinancialAssistanceException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return financialAssistance;
	}

	/**
	 * Returns the financial assistance with the primary key or throws a <code>NoSuchFinancialAssistanceException</code> if it could not be found.
	 *
	 * @param financialAssistanceId the primary key of the financial assistance
	 * @return the financial assistance
	 * @throws NoSuchFinancialAssistanceException if a financial assistance with the primary key could not be found
	 */
	@Override
	public FinancialAssistance findByPrimaryKey(long financialAssistanceId)
		throws NoSuchFinancialAssistanceException {

		return findByPrimaryKey((Serializable)financialAssistanceId);
	}

	/**
	 * Returns the financial assistance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param financialAssistanceId the primary key of the financial assistance
	 * @return the financial assistance, or <code>null</code> if a financial assistance with the primary key could not be found
	 */
	@Override
	public FinancialAssistance fetchByPrimaryKey(long financialAssistanceId) {
		return fetchByPrimaryKey((Serializable)financialAssistanceId);
	}

	/**
	 * Returns all the financial assistances.
	 *
	 * @return the financial assistances
	 */
	@Override
	public List<FinancialAssistance> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the financial assistances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinancialAssistanceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of financial assistances
	 * @param end the upper bound of the range of financial assistances (not inclusive)
	 * @return the range of financial assistances
	 */
	@Override
	public List<FinancialAssistance> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the financial assistances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinancialAssistanceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of financial assistances
	 * @param end the upper bound of the range of financial assistances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of financial assistances
	 */
	@Override
	public List<FinancialAssistance> findAll(
		int start, int end,
		OrderByComparator<FinancialAssistance> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the financial assistances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinancialAssistanceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of financial assistances
	 * @param end the upper bound of the range of financial assistances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of financial assistances
	 */
	@Override
	public List<FinancialAssistance> findAll(
		int start, int end,
		OrderByComparator<FinancialAssistance> orderByComparator,
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

		List<FinancialAssistance> list = null;

		if (useFinderCache) {
			list = (List<FinancialAssistance>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_FINANCIALASSISTANCE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_FINANCIALASSISTANCE;

				sql = sql.concat(FinancialAssistanceModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<FinancialAssistance>)QueryUtil.list(
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
	 * Removes all the financial assistances from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (FinancialAssistance financialAssistance : findAll()) {
			remove(financialAssistance);
		}
	}

	/**
	 * Returns the number of financial assistances.
	 *
	 * @return the number of financial assistances
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
					_SQL_COUNT_FINANCIALASSISTANCE);

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
		return "financialAssistanceId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_FINANCIALASSISTANCE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return FinancialAssistanceModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the financial assistance persistence.
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

		FinancialAssistanceUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		FinancialAssistanceUtil.setPersistence(null);

		entityCache.removeCache(FinancialAssistanceImpl.class.getName());
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

	private static final String _SQL_SELECT_FINANCIALASSISTANCE =
		"SELECT financialAssistance FROM FinancialAssistance financialAssistance";

	private static final String _SQL_SELECT_FINANCIALASSISTANCE_WHERE =
		"SELECT financialAssistance FROM FinancialAssistance financialAssistance WHERE ";

	private static final String _SQL_COUNT_FINANCIALASSISTANCE =
		"SELECT COUNT(financialAssistance) FROM FinancialAssistance financialAssistance";

	private static final String _SQL_COUNT_FINANCIALASSISTANCE_WHERE =
		"SELECT COUNT(financialAssistance) FROM FinancialAssistance financialAssistance WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "financialAssistance.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No FinancialAssistance exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No FinancialAssistance exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		FinancialAssistancePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}