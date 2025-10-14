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

import com.mhdsys.schema.exception.NoSuchSportsPersonException;
import com.mhdsys.schema.model.SportsPerson;
import com.mhdsys.schema.model.SportsPersonTable;
import com.mhdsys.schema.model.impl.SportsPersonImpl;
import com.mhdsys.schema.model.impl.SportsPersonModelImpl;
import com.mhdsys.schema.service.persistence.SportsPersonPersistence;
import com.mhdsys.schema.service.persistence.SportsPersonUtil;
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
 * The persistence implementation for the sports person service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = SportsPersonPersistence.class)
public class SportsPersonPersistenceImpl
	extends BasePersistenceImpl<SportsPerson>
	implements SportsPersonPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SportsPersonUtil</code> to access the sports person persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SportsPersonImpl.class.getName();

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
	 * Returns all the sports persons where trainingCentreId = &#63;.
	 *
	 * @param trainingCentreId the training centre ID
	 * @return the matching sports persons
	 */
	@Override
	public List<SportsPerson> findByTrainingCentreId(long trainingCentreId) {
		return findByTrainingCentreId(
			trainingCentreId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sports persons where trainingCentreId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsPersonModelImpl</code>.
	 * </p>
	 *
	 * @param trainingCentreId the training centre ID
	 * @param start the lower bound of the range of sports persons
	 * @param end the upper bound of the range of sports persons (not inclusive)
	 * @return the range of matching sports persons
	 */
	@Override
	public List<SportsPerson> findByTrainingCentreId(
		long trainingCentreId, int start, int end) {

		return findByTrainingCentreId(trainingCentreId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sports persons where trainingCentreId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsPersonModelImpl</code>.
	 * </p>
	 *
	 * @param trainingCentreId the training centre ID
	 * @param start the lower bound of the range of sports persons
	 * @param end the upper bound of the range of sports persons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sports persons
	 */
	@Override
	public List<SportsPerson> findByTrainingCentreId(
		long trainingCentreId, int start, int end,
		OrderByComparator<SportsPerson> orderByComparator) {

		return findByTrainingCentreId(
			trainingCentreId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sports persons where trainingCentreId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsPersonModelImpl</code>.
	 * </p>
	 *
	 * @param trainingCentreId the training centre ID
	 * @param start the lower bound of the range of sports persons
	 * @param end the upper bound of the range of sports persons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching sports persons
	 */
	@Override
	public List<SportsPerson> findByTrainingCentreId(
		long trainingCentreId, int start, int end,
		OrderByComparator<SportsPerson> orderByComparator,
		boolean useFinderCache) {

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

		List<SportsPerson> list = null;

		if (useFinderCache) {
			list = (List<SportsPerson>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SportsPerson sportsPerson : list) {
					if (trainingCentreId !=
							sportsPerson.getTrainingCentreId()) {

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

			sb.append(_SQL_SELECT_SPORTSPERSON_WHERE);

			sb.append(_FINDER_COLUMN_TRAININGCENTREID_TRAININGCENTREID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SportsPersonModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(trainingCentreId);

				list = (List<SportsPerson>)QueryUtil.list(
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
	 * Returns the first sports person in the ordered set where trainingCentreId = &#63;.
	 *
	 * @param trainingCentreId the training centre ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sports person
	 * @throws NoSuchSportsPersonException if a matching sports person could not be found
	 */
	@Override
	public SportsPerson findByTrainingCentreId_First(
			long trainingCentreId,
			OrderByComparator<SportsPerson> orderByComparator)
		throws NoSuchSportsPersonException {

		SportsPerson sportsPerson = fetchByTrainingCentreId_First(
			trainingCentreId, orderByComparator);

		if (sportsPerson != null) {
			return sportsPerson;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("trainingCentreId=");
		sb.append(trainingCentreId);

		sb.append("}");

		throw new NoSuchSportsPersonException(sb.toString());
	}

	/**
	 * Returns the first sports person in the ordered set where trainingCentreId = &#63;.
	 *
	 * @param trainingCentreId the training centre ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sports person, or <code>null</code> if a matching sports person could not be found
	 */
	@Override
	public SportsPerson fetchByTrainingCentreId_First(
		long trainingCentreId,
		OrderByComparator<SportsPerson> orderByComparator) {

		List<SportsPerson> list = findByTrainingCentreId(
			trainingCentreId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sports person in the ordered set where trainingCentreId = &#63;.
	 *
	 * @param trainingCentreId the training centre ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sports person
	 * @throws NoSuchSportsPersonException if a matching sports person could not be found
	 */
	@Override
	public SportsPerson findByTrainingCentreId_Last(
			long trainingCentreId,
			OrderByComparator<SportsPerson> orderByComparator)
		throws NoSuchSportsPersonException {

		SportsPerson sportsPerson = fetchByTrainingCentreId_Last(
			trainingCentreId, orderByComparator);

		if (sportsPerson != null) {
			return sportsPerson;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("trainingCentreId=");
		sb.append(trainingCentreId);

		sb.append("}");

		throw new NoSuchSportsPersonException(sb.toString());
	}

	/**
	 * Returns the last sports person in the ordered set where trainingCentreId = &#63;.
	 *
	 * @param trainingCentreId the training centre ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sports person, or <code>null</code> if a matching sports person could not be found
	 */
	@Override
	public SportsPerson fetchByTrainingCentreId_Last(
		long trainingCentreId,
		OrderByComparator<SportsPerson> orderByComparator) {

		int count = countByTrainingCentreId(trainingCentreId);

		if (count == 0) {
			return null;
		}

		List<SportsPerson> list = findByTrainingCentreId(
			trainingCentreId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sports persons before and after the current sports person in the ordered set where trainingCentreId = &#63;.
	 *
	 * @param sportsPersonId the primary key of the current sports person
	 * @param trainingCentreId the training centre ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sports person
	 * @throws NoSuchSportsPersonException if a sports person with the primary key could not be found
	 */
	@Override
	public SportsPerson[] findByTrainingCentreId_PrevAndNext(
			long sportsPersonId, long trainingCentreId,
			OrderByComparator<SportsPerson> orderByComparator)
		throws NoSuchSportsPersonException {

		SportsPerson sportsPerson = findByPrimaryKey(sportsPersonId);

		Session session = null;

		try {
			session = openSession();

			SportsPerson[] array = new SportsPersonImpl[3];

			array[0] = getByTrainingCentreId_PrevAndNext(
				session, sportsPerson, trainingCentreId, orderByComparator,
				true);

			array[1] = sportsPerson;

			array[2] = getByTrainingCentreId_PrevAndNext(
				session, sportsPerson, trainingCentreId, orderByComparator,
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

	protected SportsPerson getByTrainingCentreId_PrevAndNext(
		Session session, SportsPerson sportsPerson, long trainingCentreId,
		OrderByComparator<SportsPerson> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_SPORTSPERSON_WHERE);

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
			sb.append(SportsPersonModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(trainingCentreId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(sportsPerson)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SportsPerson> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sports persons where trainingCentreId = &#63; from the database.
	 *
	 * @param trainingCentreId the training centre ID
	 */
	@Override
	public void removeByTrainingCentreId(long trainingCentreId) {
		for (SportsPerson sportsPerson :
				findByTrainingCentreId(
					trainingCentreId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(sportsPerson);
		}
	}

	/**
	 * Returns the number of sports persons where trainingCentreId = &#63;.
	 *
	 * @param trainingCentreId the training centre ID
	 * @return the number of matching sports persons
	 */
	@Override
	public int countByTrainingCentreId(long trainingCentreId) {
		FinderPath finderPath = _finderPathCountByTrainingCentreId;

		Object[] finderArgs = new Object[] {trainingCentreId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SPORTSPERSON_WHERE);

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
			"sportsPerson.trainingCentreId = ?";

	public SportsPersonPersistenceImpl() {
		setModelClass(SportsPerson.class);

		setModelImplClass(SportsPersonImpl.class);
		setModelPKClass(long.class);

		setTable(SportsPersonTable.INSTANCE);
	}

	/**
	 * Caches the sports person in the entity cache if it is enabled.
	 *
	 * @param sportsPerson the sports person
	 */
	@Override
	public void cacheResult(SportsPerson sportsPerson) {
		entityCache.putResult(
			SportsPersonImpl.class, sportsPerson.getPrimaryKey(), sportsPerson);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the sports persons in the entity cache if it is enabled.
	 *
	 * @param sportsPersons the sports persons
	 */
	@Override
	public void cacheResult(List<SportsPerson> sportsPersons) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (sportsPersons.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (SportsPerson sportsPerson : sportsPersons) {
			if (entityCache.getResult(
					SportsPersonImpl.class, sportsPerson.getPrimaryKey()) ==
						null) {

				cacheResult(sportsPerson);
			}
		}
	}

	/**
	 * Clears the cache for all sports persons.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SportsPersonImpl.class);

		finderCache.clearCache(SportsPersonImpl.class);
	}

	/**
	 * Clears the cache for the sports person.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SportsPerson sportsPerson) {
		entityCache.removeResult(SportsPersonImpl.class, sportsPerson);
	}

	@Override
	public void clearCache(List<SportsPerson> sportsPersons) {
		for (SportsPerson sportsPerson : sportsPersons) {
			entityCache.removeResult(SportsPersonImpl.class, sportsPerson);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(SportsPersonImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(SportsPersonImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new sports person with the primary key. Does not add the sports person to the database.
	 *
	 * @param sportsPersonId the primary key for the new sports person
	 * @return the new sports person
	 */
	@Override
	public SportsPerson create(long sportsPersonId) {
		SportsPerson sportsPerson = new SportsPersonImpl();

		sportsPerson.setNew(true);
		sportsPerson.setPrimaryKey(sportsPersonId);

		return sportsPerson;
	}

	/**
	 * Removes the sports person with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sportsPersonId the primary key of the sports person
	 * @return the sports person that was removed
	 * @throws NoSuchSportsPersonException if a sports person with the primary key could not be found
	 */
	@Override
	public SportsPerson remove(long sportsPersonId)
		throws NoSuchSportsPersonException {

		return remove((Serializable)sportsPersonId);
	}

	/**
	 * Removes the sports person with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the sports person
	 * @return the sports person that was removed
	 * @throws NoSuchSportsPersonException if a sports person with the primary key could not be found
	 */
	@Override
	public SportsPerson remove(Serializable primaryKey)
		throws NoSuchSportsPersonException {

		Session session = null;

		try {
			session = openSession();

			SportsPerson sportsPerson = (SportsPerson)session.get(
				SportsPersonImpl.class, primaryKey);

			if (sportsPerson == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSportsPersonException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(sportsPerson);
		}
		catch (NoSuchSportsPersonException noSuchEntityException) {
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
	protected SportsPerson removeImpl(SportsPerson sportsPerson) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(sportsPerson)) {
				sportsPerson = (SportsPerson)session.get(
					SportsPersonImpl.class, sportsPerson.getPrimaryKeyObj());
			}

			if (sportsPerson != null) {
				session.delete(sportsPerson);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (sportsPerson != null) {
			clearCache(sportsPerson);
		}

		return sportsPerson;
	}

	@Override
	public SportsPerson updateImpl(SportsPerson sportsPerson) {
		boolean isNew = sportsPerson.isNew();

		if (!(sportsPerson instanceof SportsPersonModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(sportsPerson.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					sportsPerson);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in sportsPerson proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SportsPerson implementation " +
					sportsPerson.getClass());
		}

		SportsPersonModelImpl sportsPersonModelImpl =
			(SportsPersonModelImpl)sportsPerson;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (sportsPerson.getCreateDate() == null)) {
			if (serviceContext == null) {
				sportsPerson.setCreateDate(date);
			}
			else {
				sportsPerson.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!sportsPersonModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				sportsPerson.setModifiedDate(date);
			}
			else {
				sportsPerson.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(sportsPerson);
			}
			else {
				sportsPerson = (SportsPerson)session.merge(sportsPerson);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			SportsPersonImpl.class, sportsPersonModelImpl, false, true);

		if (isNew) {
			sportsPerson.setNew(false);
		}

		sportsPerson.resetOriginalValues();

		return sportsPerson;
	}

	/**
	 * Returns the sports person with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the sports person
	 * @return the sports person
	 * @throws NoSuchSportsPersonException if a sports person with the primary key could not be found
	 */
	@Override
	public SportsPerson findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSportsPersonException {

		SportsPerson sportsPerson = fetchByPrimaryKey(primaryKey);

		if (sportsPerson == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSportsPersonException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return sportsPerson;
	}

	/**
	 * Returns the sports person with the primary key or throws a <code>NoSuchSportsPersonException</code> if it could not be found.
	 *
	 * @param sportsPersonId the primary key of the sports person
	 * @return the sports person
	 * @throws NoSuchSportsPersonException if a sports person with the primary key could not be found
	 */
	@Override
	public SportsPerson findByPrimaryKey(long sportsPersonId)
		throws NoSuchSportsPersonException {

		return findByPrimaryKey((Serializable)sportsPersonId);
	}

	/**
	 * Returns the sports person with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param sportsPersonId the primary key of the sports person
	 * @return the sports person, or <code>null</code> if a sports person with the primary key could not be found
	 */
	@Override
	public SportsPerson fetchByPrimaryKey(long sportsPersonId) {
		return fetchByPrimaryKey((Serializable)sportsPersonId);
	}

	/**
	 * Returns all the sports persons.
	 *
	 * @return the sports persons
	 */
	@Override
	public List<SportsPerson> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sports persons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsPersonModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sports persons
	 * @param end the upper bound of the range of sports persons (not inclusive)
	 * @return the range of sports persons
	 */
	@Override
	public List<SportsPerson> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the sports persons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsPersonModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sports persons
	 * @param end the upper bound of the range of sports persons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sports persons
	 */
	@Override
	public List<SportsPerson> findAll(
		int start, int end, OrderByComparator<SportsPerson> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sports persons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsPersonModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sports persons
	 * @param end the upper bound of the range of sports persons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of sports persons
	 */
	@Override
	public List<SportsPerson> findAll(
		int start, int end, OrderByComparator<SportsPerson> orderByComparator,
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

		List<SportsPerson> list = null;

		if (useFinderCache) {
			list = (List<SportsPerson>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SPORTSPERSON);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SPORTSPERSON;

				sql = sql.concat(SportsPersonModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<SportsPerson>)QueryUtil.list(
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
	 * Removes all the sports persons from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SportsPerson sportsPerson : findAll()) {
			remove(sportsPerson);
		}
	}

	/**
	 * Returns the number of sports persons.
	 *
	 * @return the number of sports persons
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SPORTSPERSON);

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
		return "sportsPersonId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SPORTSPERSON;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SportsPersonModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the sports person persistence.
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

		SportsPersonUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		SportsPersonUtil.setPersistence(null);

		entityCache.removeCache(SportsPersonImpl.class.getName());
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

	private static final String _SQL_SELECT_SPORTSPERSON =
		"SELECT sportsPerson FROM SportsPerson sportsPerson";

	private static final String _SQL_SELECT_SPORTSPERSON_WHERE =
		"SELECT sportsPerson FROM SportsPerson sportsPerson WHERE ";

	private static final String _SQL_COUNT_SPORTSPERSON =
		"SELECT COUNT(sportsPerson) FROM SportsPerson sportsPerson";

	private static final String _SQL_COUNT_SPORTSPERSON_WHERE =
		"SELECT COUNT(sportsPerson) FROM SportsPerson sportsPerson WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "sportsPerson.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SportsPerson exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No SportsPerson exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		SportsPersonPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}