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

import com.mhdsys.schema.exception.NoSuchEventCertificateException;
import com.mhdsys.schema.model.EventCertificate;
import com.mhdsys.schema.model.EventCertificateTable;
import com.mhdsys.schema.model.impl.EventCertificateImpl;
import com.mhdsys.schema.model.impl.EventCertificateModelImpl;
import com.mhdsys.schema.service.persistence.EventCertificatePersistence;
import com.mhdsys.schema.service.persistence.EventCertificateUtil;
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
 * The persistence implementation for the event certificate service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = EventCertificatePersistence.class)
public class EventCertificatePersistenceImpl
	extends BasePersistenceImpl<EventCertificate>
	implements EventCertificatePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>EventCertificateUtil</code> to access the event certificate persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		EventCertificateImpl.class.getName();

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
	 * Returns all the event certificates where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching event certificates
	 */
	@Override
	public List<EventCertificate> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the event certificates where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventCertificateModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of event certificates
	 * @param end the upper bound of the range of event certificates (not inclusive)
	 * @return the range of matching event certificates
	 */
	@Override
	public List<EventCertificate> findByUserId(
		long userId, int start, int end) {

		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the event certificates where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventCertificateModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of event certificates
	 * @param end the upper bound of the range of event certificates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching event certificates
	 */
	@Override
	public List<EventCertificate> findByUserId(
		long userId, int start, int end,
		OrderByComparator<EventCertificate> orderByComparator) {

		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the event certificates where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventCertificateModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of event certificates
	 * @param end the upper bound of the range of event certificates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching event certificates
	 */
	@Override
	public List<EventCertificate> findByUserId(
		long userId, int start, int end,
		OrderByComparator<EventCertificate> orderByComparator,
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

		List<EventCertificate> list = null;

		if (useFinderCache) {
			list = (List<EventCertificate>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (EventCertificate eventCertificate : list) {
					if (userId != eventCertificate.getUserId()) {
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

			sb.append(_SQL_SELECT_EVENTCERTIFICATE_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(EventCertificateModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				list = (List<EventCertificate>)QueryUtil.list(
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
	 * Returns the first event certificate in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event certificate
	 * @throws NoSuchEventCertificateException if a matching event certificate could not be found
	 */
	@Override
	public EventCertificate findByUserId_First(
			long userId, OrderByComparator<EventCertificate> orderByComparator)
		throws NoSuchEventCertificateException {

		EventCertificate eventCertificate = fetchByUserId_First(
			userId, orderByComparator);

		if (eventCertificate != null) {
			return eventCertificate;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchEventCertificateException(sb.toString());
	}

	/**
	 * Returns the first event certificate in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event certificate, or <code>null</code> if a matching event certificate could not be found
	 */
	@Override
	public EventCertificate fetchByUserId_First(
		long userId, OrderByComparator<EventCertificate> orderByComparator) {

		List<EventCertificate> list = findByUserId(
			userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last event certificate in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event certificate
	 * @throws NoSuchEventCertificateException if a matching event certificate could not be found
	 */
	@Override
	public EventCertificate findByUserId_Last(
			long userId, OrderByComparator<EventCertificate> orderByComparator)
		throws NoSuchEventCertificateException {

		EventCertificate eventCertificate = fetchByUserId_Last(
			userId, orderByComparator);

		if (eventCertificate != null) {
			return eventCertificate;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchEventCertificateException(sb.toString());
	}

	/**
	 * Returns the last event certificate in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event certificate, or <code>null</code> if a matching event certificate could not be found
	 */
	@Override
	public EventCertificate fetchByUserId_Last(
		long userId, OrderByComparator<EventCertificate> orderByComparator) {

		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<EventCertificate> list = findByUserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the event certificates before and after the current event certificate in the ordered set where userId = &#63;.
	 *
	 * @param eventCertificateId the primary key of the current event certificate
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next event certificate
	 * @throws NoSuchEventCertificateException if a event certificate with the primary key could not be found
	 */
	@Override
	public EventCertificate[] findByUserId_PrevAndNext(
			long eventCertificateId, long userId,
			OrderByComparator<EventCertificate> orderByComparator)
		throws NoSuchEventCertificateException {

		EventCertificate eventCertificate = findByPrimaryKey(
			eventCertificateId);

		Session session = null;

		try {
			session = openSession();

			EventCertificate[] array = new EventCertificateImpl[3];

			array[0] = getByUserId_PrevAndNext(
				session, eventCertificate, userId, orderByComparator, true);

			array[1] = eventCertificate;

			array[2] = getByUserId_PrevAndNext(
				session, eventCertificate, userId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected EventCertificate getByUserId_PrevAndNext(
		Session session, EventCertificate eventCertificate, long userId,
		OrderByComparator<EventCertificate> orderByComparator,
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

		sb.append(_SQL_SELECT_EVENTCERTIFICATE_WHERE);

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
			sb.append(EventCertificateModelImpl.ORDER_BY_JPQL);
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
						eventCertificate)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<EventCertificate> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the event certificates where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (EventCertificate eventCertificate :
				findByUserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(eventCertificate);
		}
	}

	/**
	 * Returns the number of event certificates where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching event certificates
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_EVENTCERTIFICATE_WHERE);

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
		"eventCertificate.userId = ?";

	public EventCertificatePersistenceImpl() {
		setModelClass(EventCertificate.class);

		setModelImplClass(EventCertificateImpl.class);
		setModelPKClass(long.class);

		setTable(EventCertificateTable.INSTANCE);
	}

	/**
	 * Caches the event certificate in the entity cache if it is enabled.
	 *
	 * @param eventCertificate the event certificate
	 */
	@Override
	public void cacheResult(EventCertificate eventCertificate) {
		entityCache.putResult(
			EventCertificateImpl.class, eventCertificate.getPrimaryKey(),
			eventCertificate);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the event certificates in the entity cache if it is enabled.
	 *
	 * @param eventCertificates the event certificates
	 */
	@Override
	public void cacheResult(List<EventCertificate> eventCertificates) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (eventCertificates.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (EventCertificate eventCertificate : eventCertificates) {
			if (entityCache.getResult(
					EventCertificateImpl.class,
					eventCertificate.getPrimaryKey()) == null) {

				cacheResult(eventCertificate);
			}
		}
	}

	/**
	 * Clears the cache for all event certificates.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(EventCertificateImpl.class);

		finderCache.clearCache(EventCertificateImpl.class);
	}

	/**
	 * Clears the cache for the event certificate.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(EventCertificate eventCertificate) {
		entityCache.removeResult(EventCertificateImpl.class, eventCertificate);
	}

	@Override
	public void clearCache(List<EventCertificate> eventCertificates) {
		for (EventCertificate eventCertificate : eventCertificates) {
			entityCache.removeResult(
				EventCertificateImpl.class, eventCertificate);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(EventCertificateImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(EventCertificateImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new event certificate with the primary key. Does not add the event certificate to the database.
	 *
	 * @param eventCertificateId the primary key for the new event certificate
	 * @return the new event certificate
	 */
	@Override
	public EventCertificate create(long eventCertificateId) {
		EventCertificate eventCertificate = new EventCertificateImpl();

		eventCertificate.setNew(true);
		eventCertificate.setPrimaryKey(eventCertificateId);

		return eventCertificate;
	}

	/**
	 * Removes the event certificate with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param eventCertificateId the primary key of the event certificate
	 * @return the event certificate that was removed
	 * @throws NoSuchEventCertificateException if a event certificate with the primary key could not be found
	 */
	@Override
	public EventCertificate remove(long eventCertificateId)
		throws NoSuchEventCertificateException {

		return remove((Serializable)eventCertificateId);
	}

	/**
	 * Removes the event certificate with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the event certificate
	 * @return the event certificate that was removed
	 * @throws NoSuchEventCertificateException if a event certificate with the primary key could not be found
	 */
	@Override
	public EventCertificate remove(Serializable primaryKey)
		throws NoSuchEventCertificateException {

		Session session = null;

		try {
			session = openSession();

			EventCertificate eventCertificate = (EventCertificate)session.get(
				EventCertificateImpl.class, primaryKey);

			if (eventCertificate == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEventCertificateException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(eventCertificate);
		}
		catch (NoSuchEventCertificateException noSuchEntityException) {
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
	protected EventCertificate removeImpl(EventCertificate eventCertificate) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(eventCertificate)) {
				eventCertificate = (EventCertificate)session.get(
					EventCertificateImpl.class,
					eventCertificate.getPrimaryKeyObj());
			}

			if (eventCertificate != null) {
				session.delete(eventCertificate);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (eventCertificate != null) {
			clearCache(eventCertificate);
		}

		return eventCertificate;
	}

	@Override
	public EventCertificate updateImpl(EventCertificate eventCertificate) {
		boolean isNew = eventCertificate.isNew();

		if (!(eventCertificate instanceof EventCertificateModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(eventCertificate.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					eventCertificate);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in eventCertificate proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom EventCertificate implementation " +
					eventCertificate.getClass());
		}

		EventCertificateModelImpl eventCertificateModelImpl =
			(EventCertificateModelImpl)eventCertificate;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (eventCertificate.getCreateDate() == null)) {
			if (serviceContext == null) {
				eventCertificate.setCreateDate(date);
			}
			else {
				eventCertificate.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!eventCertificateModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				eventCertificate.setModifiedDate(date);
			}
			else {
				eventCertificate.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(eventCertificate);
			}
			else {
				eventCertificate = (EventCertificate)session.merge(
					eventCertificate);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			EventCertificateImpl.class, eventCertificateModelImpl, false, true);

		if (isNew) {
			eventCertificate.setNew(false);
		}

		eventCertificate.resetOriginalValues();

		return eventCertificate;
	}

	/**
	 * Returns the event certificate with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the event certificate
	 * @return the event certificate
	 * @throws NoSuchEventCertificateException if a event certificate with the primary key could not be found
	 */
	@Override
	public EventCertificate findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEventCertificateException {

		EventCertificate eventCertificate = fetchByPrimaryKey(primaryKey);

		if (eventCertificate == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEventCertificateException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return eventCertificate;
	}

	/**
	 * Returns the event certificate with the primary key or throws a <code>NoSuchEventCertificateException</code> if it could not be found.
	 *
	 * @param eventCertificateId the primary key of the event certificate
	 * @return the event certificate
	 * @throws NoSuchEventCertificateException if a event certificate with the primary key could not be found
	 */
	@Override
	public EventCertificate findByPrimaryKey(long eventCertificateId)
		throws NoSuchEventCertificateException {

		return findByPrimaryKey((Serializable)eventCertificateId);
	}

	/**
	 * Returns the event certificate with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param eventCertificateId the primary key of the event certificate
	 * @return the event certificate, or <code>null</code> if a event certificate with the primary key could not be found
	 */
	@Override
	public EventCertificate fetchByPrimaryKey(long eventCertificateId) {
		return fetchByPrimaryKey((Serializable)eventCertificateId);
	}

	/**
	 * Returns all the event certificates.
	 *
	 * @return the event certificates
	 */
	@Override
	public List<EventCertificate> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the event certificates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventCertificateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of event certificates
	 * @param end the upper bound of the range of event certificates (not inclusive)
	 * @return the range of event certificates
	 */
	@Override
	public List<EventCertificate> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the event certificates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventCertificateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of event certificates
	 * @param end the upper bound of the range of event certificates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of event certificates
	 */
	@Override
	public List<EventCertificate> findAll(
		int start, int end,
		OrderByComparator<EventCertificate> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the event certificates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventCertificateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of event certificates
	 * @param end the upper bound of the range of event certificates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of event certificates
	 */
	@Override
	public List<EventCertificate> findAll(
		int start, int end,
		OrderByComparator<EventCertificate> orderByComparator,
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

		List<EventCertificate> list = null;

		if (useFinderCache) {
			list = (List<EventCertificate>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_EVENTCERTIFICATE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_EVENTCERTIFICATE;

				sql = sql.concat(EventCertificateModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<EventCertificate>)QueryUtil.list(
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
	 * Removes all the event certificates from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (EventCertificate eventCertificate : findAll()) {
			remove(eventCertificate);
		}
	}

	/**
	 * Returns the number of event certificates.
	 *
	 * @return the number of event certificates
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_EVENTCERTIFICATE);

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
		return "eventCertificateId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_EVENTCERTIFICATE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return EventCertificateModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the event certificate persistence.
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

		EventCertificateUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		EventCertificateUtil.setPersistence(null);

		entityCache.removeCache(EventCertificateImpl.class.getName());
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

	private static final String _SQL_SELECT_EVENTCERTIFICATE =
		"SELECT eventCertificate FROM EventCertificate eventCertificate";

	private static final String _SQL_SELECT_EVENTCERTIFICATE_WHERE =
		"SELECT eventCertificate FROM EventCertificate eventCertificate WHERE ";

	private static final String _SQL_COUNT_EVENTCERTIFICATE =
		"SELECT COUNT(eventCertificate) FROM EventCertificate eventCertificate";

	private static final String _SQL_COUNT_EVENTCERTIFICATE_WHERE =
		"SELECT COUNT(eventCertificate) FROM EventCertificate eventCertificate WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "eventCertificate.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No EventCertificate exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No EventCertificate exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		EventCertificatePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}