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

import com.mhdsys.schema.exception.NoSuchAwardWinnerException;
import com.mhdsys.schema.model.AwardWinner;
import com.mhdsys.schema.model.AwardWinnerTable;
import com.mhdsys.schema.model.impl.AwardWinnerImpl;
import com.mhdsys.schema.model.impl.AwardWinnerModelImpl;
import com.mhdsys.schema.service.persistence.AwardWinnerPersistence;
import com.mhdsys.schema.service.persistence.AwardWinnerUtil;
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
 * The persistence implementation for the award winner service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = AwardWinnerPersistence.class)
public class AwardWinnerPersistenceImpl
	extends BasePersistenceImpl<AwardWinner> implements AwardWinnerPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>AwardWinnerUtil</code> to access the award winner persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		AwardWinnerImpl.class.getName();

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
	 * Returns all the award winners where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching award winners
	 */
	@Override
	public List<AwardWinner> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the award winners where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardWinnerModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of award winners
	 * @param end the upper bound of the range of award winners (not inclusive)
	 * @return the range of matching award winners
	 */
	@Override
	public List<AwardWinner> findByUserId(long userId, int start, int end) {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the award winners where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardWinnerModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of award winners
	 * @param end the upper bound of the range of award winners (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching award winners
	 */
	@Override
	public List<AwardWinner> findByUserId(
		long userId, int start, int end,
		OrderByComparator<AwardWinner> orderByComparator) {

		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the award winners where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardWinnerModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of award winners
	 * @param end the upper bound of the range of award winners (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching award winners
	 */
	@Override
	public List<AwardWinner> findByUserId(
		long userId, int start, int end,
		OrderByComparator<AwardWinner> orderByComparator,
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

		List<AwardWinner> list = null;

		if (useFinderCache) {
			list = (List<AwardWinner>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AwardWinner awardWinner : list) {
					if (userId != awardWinner.getUserId()) {
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

			sb.append(_SQL_SELECT_AWARDWINNER_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(AwardWinnerModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				list = (List<AwardWinner>)QueryUtil.list(
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
	 * Returns the first award winner in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching award winner
	 * @throws NoSuchAwardWinnerException if a matching award winner could not be found
	 */
	@Override
	public AwardWinner findByUserId_First(
			long userId, OrderByComparator<AwardWinner> orderByComparator)
		throws NoSuchAwardWinnerException {

		AwardWinner awardWinner = fetchByUserId_First(
			userId, orderByComparator);

		if (awardWinner != null) {
			return awardWinner;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchAwardWinnerException(sb.toString());
	}

	/**
	 * Returns the first award winner in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching award winner, or <code>null</code> if a matching award winner could not be found
	 */
	@Override
	public AwardWinner fetchByUserId_First(
		long userId, OrderByComparator<AwardWinner> orderByComparator) {

		List<AwardWinner> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last award winner in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching award winner
	 * @throws NoSuchAwardWinnerException if a matching award winner could not be found
	 */
	@Override
	public AwardWinner findByUserId_Last(
			long userId, OrderByComparator<AwardWinner> orderByComparator)
		throws NoSuchAwardWinnerException {

		AwardWinner awardWinner = fetchByUserId_Last(userId, orderByComparator);

		if (awardWinner != null) {
			return awardWinner;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchAwardWinnerException(sb.toString());
	}

	/**
	 * Returns the last award winner in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching award winner, or <code>null</code> if a matching award winner could not be found
	 */
	@Override
	public AwardWinner fetchByUserId_Last(
		long userId, OrderByComparator<AwardWinner> orderByComparator) {

		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<AwardWinner> list = findByUserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the award winners before and after the current award winner in the ordered set where userId = &#63;.
	 *
	 * @param awardWinnerId the primary key of the current award winner
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next award winner
	 * @throws NoSuchAwardWinnerException if a award winner with the primary key could not be found
	 */
	@Override
	public AwardWinner[] findByUserId_PrevAndNext(
			long awardWinnerId, long userId,
			OrderByComparator<AwardWinner> orderByComparator)
		throws NoSuchAwardWinnerException {

		AwardWinner awardWinner = findByPrimaryKey(awardWinnerId);

		Session session = null;

		try {
			session = openSession();

			AwardWinner[] array = new AwardWinnerImpl[3];

			array[0] = getByUserId_PrevAndNext(
				session, awardWinner, userId, orderByComparator, true);

			array[1] = awardWinner;

			array[2] = getByUserId_PrevAndNext(
				session, awardWinner, userId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected AwardWinner getByUserId_PrevAndNext(
		Session session, AwardWinner awardWinner, long userId,
		OrderByComparator<AwardWinner> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_AWARDWINNER_WHERE);

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
			sb.append(AwardWinnerModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(userId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(awardWinner)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<AwardWinner> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the award winners where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (AwardWinner awardWinner :
				findByUserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(awardWinner);
		}
	}

	/**
	 * Returns the number of award winners where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching award winners
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_AWARDWINNER_WHERE);

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
		"awardWinner.userId = ?";

	public AwardWinnerPersistenceImpl() {
		setModelClass(AwardWinner.class);

		setModelImplClass(AwardWinnerImpl.class);
		setModelPKClass(long.class);

		setTable(AwardWinnerTable.INSTANCE);
	}

	/**
	 * Caches the award winner in the entity cache if it is enabled.
	 *
	 * @param awardWinner the award winner
	 */
	@Override
	public void cacheResult(AwardWinner awardWinner) {
		entityCache.putResult(
			AwardWinnerImpl.class, awardWinner.getPrimaryKey(), awardWinner);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the award winners in the entity cache if it is enabled.
	 *
	 * @param awardWinners the award winners
	 */
	@Override
	public void cacheResult(List<AwardWinner> awardWinners) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (awardWinners.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (AwardWinner awardWinner : awardWinners) {
			if (entityCache.getResult(
					AwardWinnerImpl.class, awardWinner.getPrimaryKey()) ==
						null) {

				cacheResult(awardWinner);
			}
		}
	}

	/**
	 * Clears the cache for all award winners.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AwardWinnerImpl.class);

		finderCache.clearCache(AwardWinnerImpl.class);
	}

	/**
	 * Clears the cache for the award winner.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AwardWinner awardWinner) {
		entityCache.removeResult(AwardWinnerImpl.class, awardWinner);
	}

	@Override
	public void clearCache(List<AwardWinner> awardWinners) {
		for (AwardWinner awardWinner : awardWinners) {
			entityCache.removeResult(AwardWinnerImpl.class, awardWinner);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(AwardWinnerImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(AwardWinnerImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new award winner with the primary key. Does not add the award winner to the database.
	 *
	 * @param awardWinnerId the primary key for the new award winner
	 * @return the new award winner
	 */
	@Override
	public AwardWinner create(long awardWinnerId) {
		AwardWinner awardWinner = new AwardWinnerImpl();

		awardWinner.setNew(true);
		awardWinner.setPrimaryKey(awardWinnerId);

		return awardWinner;
	}

	/**
	 * Removes the award winner with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param awardWinnerId the primary key of the award winner
	 * @return the award winner that was removed
	 * @throws NoSuchAwardWinnerException if a award winner with the primary key could not be found
	 */
	@Override
	public AwardWinner remove(long awardWinnerId)
		throws NoSuchAwardWinnerException {

		return remove((Serializable)awardWinnerId);
	}

	/**
	 * Removes the award winner with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the award winner
	 * @return the award winner that was removed
	 * @throws NoSuchAwardWinnerException if a award winner with the primary key could not be found
	 */
	@Override
	public AwardWinner remove(Serializable primaryKey)
		throws NoSuchAwardWinnerException {

		Session session = null;

		try {
			session = openSession();

			AwardWinner awardWinner = (AwardWinner)session.get(
				AwardWinnerImpl.class, primaryKey);

			if (awardWinner == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAwardWinnerException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(awardWinner);
		}
		catch (NoSuchAwardWinnerException noSuchEntityException) {
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
	protected AwardWinner removeImpl(AwardWinner awardWinner) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(awardWinner)) {
				awardWinner = (AwardWinner)session.get(
					AwardWinnerImpl.class, awardWinner.getPrimaryKeyObj());
			}

			if (awardWinner != null) {
				session.delete(awardWinner);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (awardWinner != null) {
			clearCache(awardWinner);
		}

		return awardWinner;
	}

	@Override
	public AwardWinner updateImpl(AwardWinner awardWinner) {
		boolean isNew = awardWinner.isNew();

		if (!(awardWinner instanceof AwardWinnerModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(awardWinner.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(awardWinner);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in awardWinner proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom AwardWinner implementation " +
					awardWinner.getClass());
		}

		AwardWinnerModelImpl awardWinnerModelImpl =
			(AwardWinnerModelImpl)awardWinner;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (awardWinner.getCreateDate() == null)) {
			if (serviceContext == null) {
				awardWinner.setCreateDate(date);
			}
			else {
				awardWinner.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!awardWinnerModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				awardWinner.setModifiedDate(date);
			}
			else {
				awardWinner.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(awardWinner);
			}
			else {
				awardWinner = (AwardWinner)session.merge(awardWinner);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			AwardWinnerImpl.class, awardWinnerModelImpl, false, true);

		if (isNew) {
			awardWinner.setNew(false);
		}

		awardWinner.resetOriginalValues();

		return awardWinner;
	}

	/**
	 * Returns the award winner with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the award winner
	 * @return the award winner
	 * @throws NoSuchAwardWinnerException if a award winner with the primary key could not be found
	 */
	@Override
	public AwardWinner findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAwardWinnerException {

		AwardWinner awardWinner = fetchByPrimaryKey(primaryKey);

		if (awardWinner == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAwardWinnerException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return awardWinner;
	}

	/**
	 * Returns the award winner with the primary key or throws a <code>NoSuchAwardWinnerException</code> if it could not be found.
	 *
	 * @param awardWinnerId the primary key of the award winner
	 * @return the award winner
	 * @throws NoSuchAwardWinnerException if a award winner with the primary key could not be found
	 */
	@Override
	public AwardWinner findByPrimaryKey(long awardWinnerId)
		throws NoSuchAwardWinnerException {

		return findByPrimaryKey((Serializable)awardWinnerId);
	}

	/**
	 * Returns the award winner with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param awardWinnerId the primary key of the award winner
	 * @return the award winner, or <code>null</code> if a award winner with the primary key could not be found
	 */
	@Override
	public AwardWinner fetchByPrimaryKey(long awardWinnerId) {
		return fetchByPrimaryKey((Serializable)awardWinnerId);
	}

	/**
	 * Returns all the award winners.
	 *
	 * @return the award winners
	 */
	@Override
	public List<AwardWinner> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the award winners.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardWinnerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of award winners
	 * @param end the upper bound of the range of award winners (not inclusive)
	 * @return the range of award winners
	 */
	@Override
	public List<AwardWinner> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the award winners.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardWinnerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of award winners
	 * @param end the upper bound of the range of award winners (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of award winners
	 */
	@Override
	public List<AwardWinner> findAll(
		int start, int end, OrderByComparator<AwardWinner> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the award winners.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardWinnerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of award winners
	 * @param end the upper bound of the range of award winners (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of award winners
	 */
	@Override
	public List<AwardWinner> findAll(
		int start, int end, OrderByComparator<AwardWinner> orderByComparator,
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

		List<AwardWinner> list = null;

		if (useFinderCache) {
			list = (List<AwardWinner>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_AWARDWINNER);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_AWARDWINNER;

				sql = sql.concat(AwardWinnerModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<AwardWinner>)QueryUtil.list(
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
	 * Removes all the award winners from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AwardWinner awardWinner : findAll()) {
			remove(awardWinner);
		}
	}

	/**
	 * Returns the number of award winners.
	 *
	 * @return the number of award winners
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_AWARDWINNER);

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
		return "awardWinnerId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_AWARDWINNER;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return AwardWinnerModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the award winner persistence.
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

		AwardWinnerUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		AwardWinnerUtil.setPersistence(null);

		entityCache.removeCache(AwardWinnerImpl.class.getName());
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

	private static final String _SQL_SELECT_AWARDWINNER =
		"SELECT awardWinner FROM AwardWinner awardWinner";

	private static final String _SQL_SELECT_AWARDWINNER_WHERE =
		"SELECT awardWinner FROM AwardWinner awardWinner WHERE ";

	private static final String _SQL_COUNT_AWARDWINNER =
		"SELECT COUNT(awardWinner) FROM AwardWinner awardWinner";

	private static final String _SQL_COUNT_AWARDWINNER_WHERE =
		"SELECT COUNT(awardWinner) FROM AwardWinner awardWinner WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "awardWinner.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No AwardWinner exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No AwardWinner exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		AwardWinnerPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}