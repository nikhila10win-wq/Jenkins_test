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

import com.mhdsys.schema.exception.NoSuchCoachException;
import com.mhdsys.schema.model.Coach;
import com.mhdsys.schema.model.CoachTable;
import com.mhdsys.schema.model.impl.CoachImpl;
import com.mhdsys.schema.model.impl.CoachModelImpl;
import com.mhdsys.schema.service.persistence.CoachPersistence;
import com.mhdsys.schema.service.persistence.CoachUtil;
import com.mhdsys.schema.service.persistence.impl.constants.mhdsysPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the coach service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = CoachPersistence.class)
public class CoachPersistenceImpl
	extends BasePersistenceImpl<Coach> implements CoachPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CoachUtil</code> to access the coach persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CoachImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByTrainingCentreId;
	private FinderPath _finderPathWithoutPaginationFindByTrainingCentreId;
	private FinderPath _finderPathCountByTrainingCentreId;

	/**
	 * Returns all the coaches where trainingCentreId = &#63;.
	 *
	 * @param trainingCentreId the training centre ID
	 * @return the matching coaches
	 */
	@Override
	public List<Coach> findByTrainingCentreId(long trainingCentreId) {
		return findByTrainingCentreId(
			trainingCentreId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the coaches where trainingCentreId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CoachModelImpl</code>.
	 * </p>
	 *
	 * @param trainingCentreId the training centre ID
	 * @param start the lower bound of the range of coaches
	 * @param end the upper bound of the range of coaches (not inclusive)
	 * @return the range of matching coaches
	 */
	@Override
	public List<Coach> findByTrainingCentreId(
		long trainingCentreId, int start, int end) {

		return findByTrainingCentreId(trainingCentreId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the coaches where trainingCentreId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CoachModelImpl</code>.
	 * </p>
	 *
	 * @param trainingCentreId the training centre ID
	 * @param start the lower bound of the range of coaches
	 * @param end the upper bound of the range of coaches (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching coaches
	 */
	@Override
	public List<Coach> findByTrainingCentreId(
		long trainingCentreId, int start, int end,
		OrderByComparator<Coach> orderByComparator) {

		return findByTrainingCentreId(
			trainingCentreId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the coaches where trainingCentreId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CoachModelImpl</code>.
	 * </p>
	 *
	 * @param trainingCentreId the training centre ID
	 * @param start the lower bound of the range of coaches
	 * @param end the upper bound of the range of coaches (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching coaches
	 */
	@Override
	public List<Coach> findByTrainingCentreId(
		long trainingCentreId, int start, int end,
		OrderByComparator<Coach> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByTrainingCentreId;
				finderArgs = new Object[] {trainingCentreId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByTrainingCentreId;
			finderArgs = new Object[] {
				trainingCentreId, start, end, orderByComparator
			};
		}

		List<Coach> list = null;

		if (useFinderCache) {
			list = (List<Coach>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Coach coach : list) {
					if (trainingCentreId != coach.getTrainingCentreId()) {
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

			sb.append(_SQL_SELECT_COACH_WHERE);

			sb.append(_FINDER_COLUMN_TRAININGCENTREID_TRAININGCENTREID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CoachModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(trainingCentreId);

				list = (List<Coach>)QueryUtil.list(
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
	 * Returns the first coach in the ordered set where trainingCentreId = &#63;.
	 *
	 * @param trainingCentreId the training centre ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching coach
	 * @throws NoSuchCoachException if a matching coach could not be found
	 */
	@Override
	public Coach findByTrainingCentreId_First(
			long trainingCentreId, OrderByComparator<Coach> orderByComparator)
		throws NoSuchCoachException {

		Coach coach = fetchByTrainingCentreId_First(
			trainingCentreId, orderByComparator);

		if (coach != null) {
			return coach;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("trainingCentreId=");
		sb.append(trainingCentreId);

		sb.append("}");

		throw new NoSuchCoachException(sb.toString());
	}

	/**
	 * Returns the first coach in the ordered set where trainingCentreId = &#63;.
	 *
	 * @param trainingCentreId the training centre ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching coach, or <code>null</code> if a matching coach could not be found
	 */
	@Override
	public Coach fetchByTrainingCentreId_First(
		long trainingCentreId, OrderByComparator<Coach> orderByComparator) {

		List<Coach> list = findByTrainingCentreId(
			trainingCentreId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last coach in the ordered set where trainingCentreId = &#63;.
	 *
	 * @param trainingCentreId the training centre ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching coach
	 * @throws NoSuchCoachException if a matching coach could not be found
	 */
	@Override
	public Coach findByTrainingCentreId_Last(
			long trainingCentreId, OrderByComparator<Coach> orderByComparator)
		throws NoSuchCoachException {

		Coach coach = fetchByTrainingCentreId_Last(
			trainingCentreId, orderByComparator);

		if (coach != null) {
			return coach;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("trainingCentreId=");
		sb.append(trainingCentreId);

		sb.append("}");

		throw new NoSuchCoachException(sb.toString());
	}

	/**
	 * Returns the last coach in the ordered set where trainingCentreId = &#63;.
	 *
	 * @param trainingCentreId the training centre ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching coach, or <code>null</code> if a matching coach could not be found
	 */
	@Override
	public Coach fetchByTrainingCentreId_Last(
		long trainingCentreId, OrderByComparator<Coach> orderByComparator) {

		int count = countByTrainingCentreId(trainingCentreId);

		if (count == 0) {
			return null;
		}

		List<Coach> list = findByTrainingCentreId(
			trainingCentreId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the coaches before and after the current coach in the ordered set where trainingCentreId = &#63;.
	 *
	 * @param coachId the primary key of the current coach
	 * @param trainingCentreId the training centre ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next coach
	 * @throws NoSuchCoachException if a coach with the primary key could not be found
	 */
	@Override
	public Coach[] findByTrainingCentreId_PrevAndNext(
			long coachId, long trainingCentreId,
			OrderByComparator<Coach> orderByComparator)
		throws NoSuchCoachException {

		Coach coach = findByPrimaryKey(coachId);

		Session session = null;

		try {
			session = openSession();

			Coach[] array = new CoachImpl[3];

			array[0] = getByTrainingCentreId_PrevAndNext(
				session, coach, trainingCentreId, orderByComparator, true);

			array[1] = coach;

			array[2] = getByTrainingCentreId_PrevAndNext(
				session, coach, trainingCentreId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Coach getByTrainingCentreId_PrevAndNext(
		Session session, Coach coach, long trainingCentreId,
		OrderByComparator<Coach> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_COACH_WHERE);

		sb.append(_FINDER_COLUMN_TRAININGCENTREID_TRAININGCENTREID_2);

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
			sb.append(CoachModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(trainingCentreId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(coach)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Coach> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the coaches where trainingCentreId = &#63; from the database.
	 *
	 * @param trainingCentreId the training centre ID
	 */
	@Override
	public void removeByTrainingCentreId(long trainingCentreId) {
		for (Coach coach :
				findByTrainingCentreId(
					trainingCentreId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(coach);
		}
	}

	/**
	 * Returns the number of coaches where trainingCentreId = &#63;.
	 *
	 * @param trainingCentreId the training centre ID
	 * @return the number of matching coaches
	 */
	@Override
	public int countByTrainingCentreId(long trainingCentreId) {
		FinderPath finderPath = _finderPathCountByTrainingCentreId;

		Object[] finderArgs = new Object[] {trainingCentreId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COACH_WHERE);

			sb.append(_FINDER_COLUMN_TRAININGCENTREID_TRAININGCENTREID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(trainingCentreId);

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
		_FINDER_COLUMN_TRAININGCENTREID_TRAININGCENTREID_2 =
			"coach.trainingCentreId = ?";

	private FinderPath _finderPathFetchByEmail;
	private FinderPath _finderPathCountByEmail;

	/**
	 * Returns the coach where email = &#63; or throws a <code>NoSuchCoachException</code> if it could not be found.
	 *
	 * @param email the email
	 * @return the matching coach
	 * @throws NoSuchCoachException if a matching coach could not be found
	 */
	@Override
	public Coach findByEmail(String email) throws NoSuchCoachException {
		Coach coach = fetchByEmail(email);

		if (coach == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("email=");
			sb.append(email);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchCoachException(sb.toString());
		}

		return coach;
	}

	/**
	 * Returns the coach where email = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param email the email
	 * @return the matching coach, or <code>null</code> if a matching coach could not be found
	 */
	@Override
	public Coach fetchByEmail(String email) {
		return fetchByEmail(email, true);
	}

	/**
	 * Returns the coach where email = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param email the email
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching coach, or <code>null</code> if a matching coach could not be found
	 */
	@Override
	public Coach fetchByEmail(String email, boolean useFinderCache) {
		email = Objects.toString(email, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {email};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByEmail, finderArgs, this);
		}

		if (result instanceof Coach) {
			Coach coach = (Coach)result;

			if (!Objects.equals(email, coach.getEmail())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_COACH_WHERE);

			boolean bindEmail = false;

			if (email.isEmpty()) {
				sb.append(_FINDER_COLUMN_EMAIL_EMAIL_3);
			}
			else {
				bindEmail = true;

				sb.append(_FINDER_COLUMN_EMAIL_EMAIL_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindEmail) {
					queryPos.add(email);
				}

				List<Coach> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByEmail, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {email};
							}

							_log.warn(
								"CoachPersistenceImpl.fetchByEmail(String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Coach coach = list.get(0);

					result = coach;

					cacheResult(coach);
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
			return (Coach)result;
		}
	}

	/**
	 * Removes the coach where email = &#63; from the database.
	 *
	 * @param email the email
	 * @return the coach that was removed
	 */
	@Override
	public Coach removeByEmail(String email) throws NoSuchCoachException {
		Coach coach = findByEmail(email);

		return remove(coach);
	}

	/**
	 * Returns the number of coaches where email = &#63;.
	 *
	 * @param email the email
	 * @return the number of matching coaches
	 */
	@Override
	public int countByEmail(String email) {
		email = Objects.toString(email, "");

		FinderPath finderPath = _finderPathCountByEmail;

		Object[] finderArgs = new Object[] {email};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COACH_WHERE);

			boolean bindEmail = false;

			if (email.isEmpty()) {
				sb.append(_FINDER_COLUMN_EMAIL_EMAIL_3);
			}
			else {
				bindEmail = true;

				sb.append(_FINDER_COLUMN_EMAIL_EMAIL_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindEmail) {
					queryPos.add(email);
				}

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

	private static final String _FINDER_COLUMN_EMAIL_EMAIL_2 =
		"coach.email = ?";

	private static final String _FINDER_COLUMN_EMAIL_EMAIL_3 =
		"(coach.email IS NULL OR coach.email = '')";

	public CoachPersistenceImpl() {
		setModelClass(Coach.class);

		setModelImplClass(CoachImpl.class);
		setModelPKClass(long.class);

		setTable(CoachTable.INSTANCE);
	}

	/**
	 * Caches the coach in the entity cache if it is enabled.
	 *
	 * @param coach the coach
	 */
	@Override
	public void cacheResult(Coach coach) {
		entityCache.putResult(CoachImpl.class, coach.getPrimaryKey(), coach);

		finderCache.putResult(
			_finderPathFetchByEmail, new Object[] {coach.getEmail()}, coach);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the coaches in the entity cache if it is enabled.
	 *
	 * @param coaches the coaches
	 */
	@Override
	public void cacheResult(List<Coach> coaches) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (coaches.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Coach coach : coaches) {
			if (entityCache.getResult(CoachImpl.class, coach.getPrimaryKey()) ==
					null) {

				cacheResult(coach);
			}
		}
	}

	/**
	 * Clears the cache for all coaches.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CoachImpl.class);

		finderCache.clearCache(CoachImpl.class);
	}

	/**
	 * Clears the cache for the coach.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Coach coach) {
		entityCache.removeResult(CoachImpl.class, coach);
	}

	@Override
	public void clearCache(List<Coach> coaches) {
		for (Coach coach : coaches) {
			entityCache.removeResult(CoachImpl.class, coach);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(CoachImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(CoachImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(CoachModelImpl coachModelImpl) {
		Object[] args = new Object[] {coachModelImpl.getEmail()};

		finderCache.putResult(_finderPathCountByEmail, args, Long.valueOf(1));
		finderCache.putResult(_finderPathFetchByEmail, args, coachModelImpl);
	}

	/**
	 * Creates a new coach with the primary key. Does not add the coach to the database.
	 *
	 * @param coachId the primary key for the new coach
	 * @return the new coach
	 */
	@Override
	public Coach create(long coachId) {
		Coach coach = new CoachImpl();

		coach.setNew(true);
		coach.setPrimaryKey(coachId);

		return coach;
	}

	/**
	 * Removes the coach with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param coachId the primary key of the coach
	 * @return the coach that was removed
	 * @throws NoSuchCoachException if a coach with the primary key could not be found
	 */
	@Override
	public Coach remove(long coachId) throws NoSuchCoachException {
		return remove((Serializable)coachId);
	}

	/**
	 * Removes the coach with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the coach
	 * @return the coach that was removed
	 * @throws NoSuchCoachException if a coach with the primary key could not be found
	 */
	@Override
	public Coach remove(Serializable primaryKey) throws NoSuchCoachException {
		Session session = null;

		try {
			session = openSession();

			Coach coach = (Coach)session.get(CoachImpl.class, primaryKey);

			if (coach == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCoachException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(coach);
		}
		catch (NoSuchCoachException noSuchEntityException) {
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
	protected Coach removeImpl(Coach coach) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(coach)) {
				coach = (Coach)session.get(
					CoachImpl.class, coach.getPrimaryKeyObj());
			}

			if (coach != null) {
				session.delete(coach);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (coach != null) {
			clearCache(coach);
		}

		return coach;
	}

	@Override
	public Coach updateImpl(Coach coach) {
		boolean isNew = coach.isNew();

		if (!(coach instanceof CoachModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(coach.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(coach);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in coach proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Coach implementation " +
					coach.getClass());
		}

		CoachModelImpl coachModelImpl = (CoachModelImpl)coach;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (coach.getCreateDate() == null)) {
			if (serviceContext == null) {
				coach.setCreateDate(date);
			}
			else {
				coach.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!coachModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				coach.setModifiedDate(date);
			}
			else {
				coach.setModifiedDate(serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(coach);
			}
			else {
				coach = (Coach)session.merge(coach);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(CoachImpl.class, coachModelImpl, false, true);

		cacheUniqueFindersCache(coachModelImpl);

		if (isNew) {
			coach.setNew(false);
		}

		coach.resetOriginalValues();

		return coach;
	}

	/**
	 * Returns the coach with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the coach
	 * @return the coach
	 * @throws NoSuchCoachException if a coach with the primary key could not be found
	 */
	@Override
	public Coach findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCoachException {

		Coach coach = fetchByPrimaryKey(primaryKey);

		if (coach == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCoachException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return coach;
	}

	/**
	 * Returns the coach with the primary key or throws a <code>NoSuchCoachException</code> if it could not be found.
	 *
	 * @param coachId the primary key of the coach
	 * @return the coach
	 * @throws NoSuchCoachException if a coach with the primary key could not be found
	 */
	@Override
	public Coach findByPrimaryKey(long coachId) throws NoSuchCoachException {
		return findByPrimaryKey((Serializable)coachId);
	}

	/**
	 * Returns the coach with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param coachId the primary key of the coach
	 * @return the coach, or <code>null</code> if a coach with the primary key could not be found
	 */
	@Override
	public Coach fetchByPrimaryKey(long coachId) {
		return fetchByPrimaryKey((Serializable)coachId);
	}

	/**
	 * Returns all the coaches.
	 *
	 * @return the coaches
	 */
	@Override
	public List<Coach> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the coaches.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CoachModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of coaches
	 * @param end the upper bound of the range of coaches (not inclusive)
	 * @return the range of coaches
	 */
	@Override
	public List<Coach> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the coaches.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CoachModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of coaches
	 * @param end the upper bound of the range of coaches (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of coaches
	 */
	@Override
	public List<Coach> findAll(
		int start, int end, OrderByComparator<Coach> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the coaches.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CoachModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of coaches
	 * @param end the upper bound of the range of coaches (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of coaches
	 */
	@Override
	public List<Coach> findAll(
		int start, int end, OrderByComparator<Coach> orderByComparator,
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

		List<Coach> list = null;

		if (useFinderCache) {
			list = (List<Coach>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_COACH);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_COACH;

				sql = sql.concat(CoachModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Coach>)QueryUtil.list(
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
	 * Removes all the coaches from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Coach coach : findAll()) {
			remove(coach);
		}
	}

	/**
	 * Returns the number of coaches.
	 *
	 * @return the number of coaches
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_COACH);

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
		return "coachId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_COACH;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CoachModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the coach persistence.
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

		_finderPathWithPaginationFindByTrainingCentreId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTrainingCentreId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"trainingCentreId"}, true);

		_finderPathWithoutPaginationFindByTrainingCentreId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTrainingCentreId",
			new String[] {Long.class.getName()},
			new String[] {"trainingCentreId"}, true);

		_finderPathCountByTrainingCentreId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByTrainingCentreId", new String[] {Long.class.getName()},
			new String[] {"trainingCentreId"}, false);

		_finderPathFetchByEmail = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByEmail",
			new String[] {String.class.getName()}, new String[] {"email"},
			true);

		_finderPathCountByEmail = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByEmail",
			new String[] {String.class.getName()}, new String[] {"email"},
			false);

		CoachUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		CoachUtil.setPersistence(null);

		entityCache.removeCache(CoachImpl.class.getName());
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

	private static final String _SQL_SELECT_COACH =
		"SELECT coach FROM Coach coach";

	private static final String _SQL_SELECT_COACH_WHERE =
		"SELECT coach FROM Coach coach WHERE ";

	private static final String _SQL_COUNT_COACH =
		"SELECT COUNT(coach) FROM Coach coach";

	private static final String _SQL_COUNT_COACH_WHERE =
		"SELECT COUNT(coach) FROM Coach coach WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "coach.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Coach exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Coach exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CoachPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}