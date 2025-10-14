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

import com.mhdsys.schema.exception.NoSuchCompetitionInitiationException;
import com.mhdsys.schema.model.CompetitionInitiation;
import com.mhdsys.schema.model.CompetitionInitiationTable;
import com.mhdsys.schema.model.impl.CompetitionInitiationImpl;
import com.mhdsys.schema.model.impl.CompetitionInitiationModelImpl;
import com.mhdsys.schema.service.persistence.CompetitionInitiationPersistence;
import com.mhdsys.schema.service.persistence.CompetitionInitiationUtil;
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
 * The persistence implementation for the competition initiation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = CompetitionInitiationPersistence.class)
public class CompetitionInitiationPersistenceImpl
	extends BasePersistenceImpl<CompetitionInitiation>
	implements CompetitionInitiationPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CompetitionInitiationUtil</code> to access the competition initiation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CompetitionInitiationImpl.class.getName();

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
	 * Returns all the competition initiations where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching competition initiations
	 */
	@Override
	public List<CompetitionInitiation> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the competition initiations where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CompetitionInitiationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of competition initiations
	 * @param end the upper bound of the range of competition initiations (not inclusive)
	 * @return the range of matching competition initiations
	 */
	@Override
	public List<CompetitionInitiation> findByUserId(
		long userId, int start, int end) {

		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the competition initiations where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CompetitionInitiationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of competition initiations
	 * @param end the upper bound of the range of competition initiations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching competition initiations
	 */
	@Override
	public List<CompetitionInitiation> findByUserId(
		long userId, int start, int end,
		OrderByComparator<CompetitionInitiation> orderByComparator) {

		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the competition initiations where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CompetitionInitiationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of competition initiations
	 * @param end the upper bound of the range of competition initiations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching competition initiations
	 */
	@Override
	public List<CompetitionInitiation> findByUserId(
		long userId, int start, int end,
		OrderByComparator<CompetitionInitiation> orderByComparator,
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

		List<CompetitionInitiation> list = null;

		if (useFinderCache) {
			list = (List<CompetitionInitiation>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CompetitionInitiation competitionInitiation : list) {
					if (userId != competitionInitiation.getUserId()) {
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

			sb.append(_SQL_SELECT_COMPETITIONINITIATION_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CompetitionInitiationModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				list = (List<CompetitionInitiation>)QueryUtil.list(
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
	 * Returns the first competition initiation in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching competition initiation
	 * @throws NoSuchCompetitionInitiationException if a matching competition initiation could not be found
	 */
	@Override
	public CompetitionInitiation findByUserId_First(
			long userId,
			OrderByComparator<CompetitionInitiation> orderByComparator)
		throws NoSuchCompetitionInitiationException {

		CompetitionInitiation competitionInitiation = fetchByUserId_First(
			userId, orderByComparator);

		if (competitionInitiation != null) {
			return competitionInitiation;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchCompetitionInitiationException(sb.toString());
	}

	/**
	 * Returns the first competition initiation in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching competition initiation, or <code>null</code> if a matching competition initiation could not be found
	 */
	@Override
	public CompetitionInitiation fetchByUserId_First(
		long userId,
		OrderByComparator<CompetitionInitiation> orderByComparator) {

		List<CompetitionInitiation> list = findByUserId(
			userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last competition initiation in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching competition initiation
	 * @throws NoSuchCompetitionInitiationException if a matching competition initiation could not be found
	 */
	@Override
	public CompetitionInitiation findByUserId_Last(
			long userId,
			OrderByComparator<CompetitionInitiation> orderByComparator)
		throws NoSuchCompetitionInitiationException {

		CompetitionInitiation competitionInitiation = fetchByUserId_Last(
			userId, orderByComparator);

		if (competitionInitiation != null) {
			return competitionInitiation;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchCompetitionInitiationException(sb.toString());
	}

	/**
	 * Returns the last competition initiation in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching competition initiation, or <code>null</code> if a matching competition initiation could not be found
	 */
	@Override
	public CompetitionInitiation fetchByUserId_Last(
		long userId,
		OrderByComparator<CompetitionInitiation> orderByComparator) {

		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<CompetitionInitiation> list = findByUserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the competition initiations before and after the current competition initiation in the ordered set where userId = &#63;.
	 *
	 * @param competitionInitiationId the primary key of the current competition initiation
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next competition initiation
	 * @throws NoSuchCompetitionInitiationException if a competition initiation with the primary key could not be found
	 */
	@Override
	public CompetitionInitiation[] findByUserId_PrevAndNext(
			long competitionInitiationId, long userId,
			OrderByComparator<CompetitionInitiation> orderByComparator)
		throws NoSuchCompetitionInitiationException {

		CompetitionInitiation competitionInitiation = findByPrimaryKey(
			competitionInitiationId);

		Session session = null;

		try {
			session = openSession();

			CompetitionInitiation[] array = new CompetitionInitiationImpl[3];

			array[0] = getByUserId_PrevAndNext(
				session, competitionInitiation, userId, orderByComparator,
				true);

			array[1] = competitionInitiation;

			array[2] = getByUserId_PrevAndNext(
				session, competitionInitiation, userId, orderByComparator,
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

	protected CompetitionInitiation getByUserId_PrevAndNext(
		Session session, CompetitionInitiation competitionInitiation,
		long userId, OrderByComparator<CompetitionInitiation> orderByComparator,
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

		sb.append(_SQL_SELECT_COMPETITIONINITIATION_WHERE);

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
			sb.append(CompetitionInitiationModelImpl.ORDER_BY_JPQL);
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
						competitionInitiation)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CompetitionInitiation> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the competition initiations where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (CompetitionInitiation competitionInitiation :
				findByUserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(competitionInitiation);
		}
	}

	/**
	 * Returns the number of competition initiations where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching competition initiations
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COMPETITIONINITIATION_WHERE);

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
		"competitionInitiation.userId = ?";

	public CompetitionInitiationPersistenceImpl() {
		setModelClass(CompetitionInitiation.class);

		setModelImplClass(CompetitionInitiationImpl.class);
		setModelPKClass(long.class);

		setTable(CompetitionInitiationTable.INSTANCE);
	}

	/**
	 * Caches the competition initiation in the entity cache if it is enabled.
	 *
	 * @param competitionInitiation the competition initiation
	 */
	@Override
	public void cacheResult(CompetitionInitiation competitionInitiation) {
		entityCache.putResult(
			CompetitionInitiationImpl.class,
			competitionInitiation.getPrimaryKey(), competitionInitiation);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the competition initiations in the entity cache if it is enabled.
	 *
	 * @param competitionInitiations the competition initiations
	 */
	@Override
	public void cacheResult(
		List<CompetitionInitiation> competitionInitiations) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (competitionInitiations.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (CompetitionInitiation competitionInitiation :
				competitionInitiations) {

			if (entityCache.getResult(
					CompetitionInitiationImpl.class,
					competitionInitiation.getPrimaryKey()) == null) {

				cacheResult(competitionInitiation);
			}
		}
	}

	/**
	 * Clears the cache for all competition initiations.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CompetitionInitiationImpl.class);

		finderCache.clearCache(CompetitionInitiationImpl.class);
	}

	/**
	 * Clears the cache for the competition initiation.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CompetitionInitiation competitionInitiation) {
		entityCache.removeResult(
			CompetitionInitiationImpl.class, competitionInitiation);
	}

	@Override
	public void clearCache(List<CompetitionInitiation> competitionInitiations) {
		for (CompetitionInitiation competitionInitiation :
				competitionInitiations) {

			entityCache.removeResult(
				CompetitionInitiationImpl.class, competitionInitiation);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(CompetitionInitiationImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				CompetitionInitiationImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new competition initiation with the primary key. Does not add the competition initiation to the database.
	 *
	 * @param competitionInitiationId the primary key for the new competition initiation
	 * @return the new competition initiation
	 */
	@Override
	public CompetitionInitiation create(long competitionInitiationId) {
		CompetitionInitiation competitionInitiation =
			new CompetitionInitiationImpl();

		competitionInitiation.setNew(true);
		competitionInitiation.setPrimaryKey(competitionInitiationId);

		return competitionInitiation;
	}

	/**
	 * Removes the competition initiation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param competitionInitiationId the primary key of the competition initiation
	 * @return the competition initiation that was removed
	 * @throws NoSuchCompetitionInitiationException if a competition initiation with the primary key could not be found
	 */
	@Override
	public CompetitionInitiation remove(long competitionInitiationId)
		throws NoSuchCompetitionInitiationException {

		return remove((Serializable)competitionInitiationId);
	}

	/**
	 * Removes the competition initiation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the competition initiation
	 * @return the competition initiation that was removed
	 * @throws NoSuchCompetitionInitiationException if a competition initiation with the primary key could not be found
	 */
	@Override
	public CompetitionInitiation remove(Serializable primaryKey)
		throws NoSuchCompetitionInitiationException {

		Session session = null;

		try {
			session = openSession();

			CompetitionInitiation competitionInitiation =
				(CompetitionInitiation)session.get(
					CompetitionInitiationImpl.class, primaryKey);

			if (competitionInitiation == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCompetitionInitiationException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(competitionInitiation);
		}
		catch (NoSuchCompetitionInitiationException noSuchEntityException) {
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
	protected CompetitionInitiation removeImpl(
		CompetitionInitiation competitionInitiation) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(competitionInitiation)) {
				competitionInitiation = (CompetitionInitiation)session.get(
					CompetitionInitiationImpl.class,
					competitionInitiation.getPrimaryKeyObj());
			}

			if (competitionInitiation != null) {
				session.delete(competitionInitiation);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (competitionInitiation != null) {
			clearCache(competitionInitiation);
		}

		return competitionInitiation;
	}

	@Override
	public CompetitionInitiation updateImpl(
		CompetitionInitiation competitionInitiation) {

		boolean isNew = competitionInitiation.isNew();

		if (!(competitionInitiation instanceof
				CompetitionInitiationModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(competitionInitiation.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					competitionInitiation);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in competitionInitiation proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CompetitionInitiation implementation " +
					competitionInitiation.getClass());
		}

		CompetitionInitiationModelImpl competitionInitiationModelImpl =
			(CompetitionInitiationModelImpl)competitionInitiation;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (competitionInitiation.getCreateDate() == null)) {
			if (serviceContext == null) {
				competitionInitiation.setCreateDate(date);
			}
			else {
				competitionInitiation.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!competitionInitiationModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				competitionInitiation.setModifiedDate(date);
			}
			else {
				competitionInitiation.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(competitionInitiation);
			}
			else {
				competitionInitiation = (CompetitionInitiation)session.merge(
					competitionInitiation);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			CompetitionInitiationImpl.class, competitionInitiationModelImpl,
			false, true);

		if (isNew) {
			competitionInitiation.setNew(false);
		}

		competitionInitiation.resetOriginalValues();

		return competitionInitiation;
	}

	/**
	 * Returns the competition initiation with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the competition initiation
	 * @return the competition initiation
	 * @throws NoSuchCompetitionInitiationException if a competition initiation with the primary key could not be found
	 */
	@Override
	public CompetitionInitiation findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCompetitionInitiationException {

		CompetitionInitiation competitionInitiation = fetchByPrimaryKey(
			primaryKey);

		if (competitionInitiation == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCompetitionInitiationException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return competitionInitiation;
	}

	/**
	 * Returns the competition initiation with the primary key or throws a <code>NoSuchCompetitionInitiationException</code> if it could not be found.
	 *
	 * @param competitionInitiationId the primary key of the competition initiation
	 * @return the competition initiation
	 * @throws NoSuchCompetitionInitiationException if a competition initiation with the primary key could not be found
	 */
	@Override
	public CompetitionInitiation findByPrimaryKey(long competitionInitiationId)
		throws NoSuchCompetitionInitiationException {

		return findByPrimaryKey((Serializable)competitionInitiationId);
	}

	/**
	 * Returns the competition initiation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param competitionInitiationId the primary key of the competition initiation
	 * @return the competition initiation, or <code>null</code> if a competition initiation with the primary key could not be found
	 */
	@Override
	public CompetitionInitiation fetchByPrimaryKey(
		long competitionInitiationId) {

		return fetchByPrimaryKey((Serializable)competitionInitiationId);
	}

	/**
	 * Returns all the competition initiations.
	 *
	 * @return the competition initiations
	 */
	@Override
	public List<CompetitionInitiation> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the competition initiations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CompetitionInitiationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of competition initiations
	 * @param end the upper bound of the range of competition initiations (not inclusive)
	 * @return the range of competition initiations
	 */
	@Override
	public List<CompetitionInitiation> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the competition initiations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CompetitionInitiationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of competition initiations
	 * @param end the upper bound of the range of competition initiations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of competition initiations
	 */
	@Override
	public List<CompetitionInitiation> findAll(
		int start, int end,
		OrderByComparator<CompetitionInitiation> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the competition initiations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CompetitionInitiationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of competition initiations
	 * @param end the upper bound of the range of competition initiations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of competition initiations
	 */
	@Override
	public List<CompetitionInitiation> findAll(
		int start, int end,
		OrderByComparator<CompetitionInitiation> orderByComparator,
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

		List<CompetitionInitiation> list = null;

		if (useFinderCache) {
			list = (List<CompetitionInitiation>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_COMPETITIONINITIATION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_COMPETITIONINITIATION;

				sql = sql.concat(CompetitionInitiationModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<CompetitionInitiation>)QueryUtil.list(
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
	 * Removes all the competition initiations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CompetitionInitiation competitionInitiation : findAll()) {
			remove(competitionInitiation);
		}
	}

	/**
	 * Returns the number of competition initiations.
	 *
	 * @return the number of competition initiations
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
					_SQL_COUNT_COMPETITIONINITIATION);

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
		return "competitionInitiationId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_COMPETITIONINITIATION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CompetitionInitiationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the competition initiation persistence.
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

		CompetitionInitiationUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		CompetitionInitiationUtil.setPersistence(null);

		entityCache.removeCache(CompetitionInitiationImpl.class.getName());
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

	private static final String _SQL_SELECT_COMPETITIONINITIATION =
		"SELECT competitionInitiation FROM CompetitionInitiation competitionInitiation";

	private static final String _SQL_SELECT_COMPETITIONINITIATION_WHERE =
		"SELECT competitionInitiation FROM CompetitionInitiation competitionInitiation WHERE ";

	private static final String _SQL_COUNT_COMPETITIONINITIATION =
		"SELECT COUNT(competitionInitiation) FROM CompetitionInitiation competitionInitiation";

	private static final String _SQL_COUNT_COMPETITIONINITIATION_WHERE =
		"SELECT COUNT(competitionInitiation) FROM CompetitionInitiation competitionInitiation WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"competitionInitiation.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CompetitionInitiation exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CompetitionInitiation exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CompetitionInitiationPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}