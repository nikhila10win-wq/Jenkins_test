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

import com.mhdsys.schema.exception.NoSuchGrievanceException;
import com.mhdsys.schema.model.Grievance;
import com.mhdsys.schema.model.GrievanceTable;
import com.mhdsys.schema.model.impl.GrievanceImpl;
import com.mhdsys.schema.model.impl.GrievanceModelImpl;
import com.mhdsys.schema.service.persistence.GrievancePersistence;
import com.mhdsys.schema.service.persistence.GrievanceUtil;
import com.mhdsys.schema.service.persistence.impl.constants.mhdsysPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

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
 * The persistence implementation for the grievance service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = GrievancePersistence.class)
public class GrievancePersistenceImpl
	extends BasePersistenceImpl<Grievance> implements GrievancePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>GrievanceUtil</code> to access the grievance persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		GrievanceImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByStatus;
	private FinderPath _finderPathWithoutPaginationFindByStatus;
	private FinderPath _finderPathCountByStatus;

	/**
	 * Returns all the grievances where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching grievances
	 */
	@Override
	public List<Grievance> findByStatus(String status) {
		return findByStatus(status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the grievances where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GrievanceModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of grievances
	 * @param end the upper bound of the range of grievances (not inclusive)
	 * @return the range of matching grievances
	 */
	@Override
	public List<Grievance> findByStatus(String status, int start, int end) {
		return findByStatus(status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the grievances where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GrievanceModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of grievances
	 * @param end the upper bound of the range of grievances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching grievances
	 */
	@Override
	public List<Grievance> findByStatus(
		String status, int start, int end,
		OrderByComparator<Grievance> orderByComparator) {

		return findByStatus(status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the grievances where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GrievanceModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of grievances
	 * @param end the upper bound of the range of grievances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching grievances
	 */
	@Override
	public List<Grievance> findByStatus(
		String status, int start, int end,
		OrderByComparator<Grievance> orderByComparator,
		boolean useFinderCache) {

		status = Objects.toString(status, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByStatus;
				finderArgs = new Object[] {status};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByStatus;
			finderArgs = new Object[] {status, start, end, orderByComparator};
		}

		List<Grievance> list = null;

		if (useFinderCache) {
			list = (List<Grievance>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Grievance grievance : list) {
					if (!status.equals(grievance.getStatus())) {
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

			sb.append(_SQL_SELECT_GRIEVANCE_WHERE);

			boolean bindStatus = false;

			if (status.isEmpty()) {
				sb.append(_FINDER_COLUMN_STATUS_STATUS_3);
			}
			else {
				bindStatus = true;

				sb.append(_FINDER_COLUMN_STATUS_STATUS_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(GrievanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindStatus) {
					queryPos.add(status);
				}

				list = (List<Grievance>)QueryUtil.list(
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
	 * Returns the first grievance in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching grievance
	 * @throws NoSuchGrievanceException if a matching grievance could not be found
	 */
	@Override
	public Grievance findByStatus_First(
			String status, OrderByComparator<Grievance> orderByComparator)
		throws NoSuchGrievanceException {

		Grievance grievance = fetchByStatus_First(status, orderByComparator);

		if (grievance != null) {
			return grievance;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchGrievanceException(sb.toString());
	}

	/**
	 * Returns the first grievance in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching grievance, or <code>null</code> if a matching grievance could not be found
	 */
	@Override
	public Grievance fetchByStatus_First(
		String status, OrderByComparator<Grievance> orderByComparator) {

		List<Grievance> list = findByStatus(status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last grievance in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching grievance
	 * @throws NoSuchGrievanceException if a matching grievance could not be found
	 */
	@Override
	public Grievance findByStatus_Last(
			String status, OrderByComparator<Grievance> orderByComparator)
		throws NoSuchGrievanceException {

		Grievance grievance = fetchByStatus_Last(status, orderByComparator);

		if (grievance != null) {
			return grievance;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchGrievanceException(sb.toString());
	}

	/**
	 * Returns the last grievance in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching grievance, or <code>null</code> if a matching grievance could not be found
	 */
	@Override
	public Grievance fetchByStatus_Last(
		String status, OrderByComparator<Grievance> orderByComparator) {

		int count = countByStatus(status);

		if (count == 0) {
			return null;
		}

		List<Grievance> list = findByStatus(
			status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the grievances before and after the current grievance in the ordered set where status = &#63;.
	 *
	 * @param grievanceId the primary key of the current grievance
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next grievance
	 * @throws NoSuchGrievanceException if a grievance with the primary key could not be found
	 */
	@Override
	public Grievance[] findByStatus_PrevAndNext(
			long grievanceId, String status,
			OrderByComparator<Grievance> orderByComparator)
		throws NoSuchGrievanceException {

		status = Objects.toString(status, "");

		Grievance grievance = findByPrimaryKey(grievanceId);

		Session session = null;

		try {
			session = openSession();

			Grievance[] array = new GrievanceImpl[3];

			array[0] = getByStatus_PrevAndNext(
				session, grievance, status, orderByComparator, true);

			array[1] = grievance;

			array[2] = getByStatus_PrevAndNext(
				session, grievance, status, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Grievance getByStatus_PrevAndNext(
		Session session, Grievance grievance, String status,
		OrderByComparator<Grievance> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_GRIEVANCE_WHERE);

		boolean bindStatus = false;

		if (status.isEmpty()) {
			sb.append(_FINDER_COLUMN_STATUS_STATUS_3);
		}
		else {
			bindStatus = true;

			sb.append(_FINDER_COLUMN_STATUS_STATUS_2);
		}

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
			sb.append(GrievanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindStatus) {
			queryPos.add(status);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(grievance)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Grievance> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the grievances where status = &#63; from the database.
	 *
	 * @param status the status
	 */
	@Override
	public void removeByStatus(String status) {
		for (Grievance grievance :
				findByStatus(
					status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(grievance);
		}
	}

	/**
	 * Returns the number of grievances where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching grievances
	 */
	@Override
	public int countByStatus(String status) {
		status = Objects.toString(status, "");

		FinderPath finderPath = _finderPathCountByStatus;

		Object[] finderArgs = new Object[] {status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_GRIEVANCE_WHERE);

			boolean bindStatus = false;

			if (status.isEmpty()) {
				sb.append(_FINDER_COLUMN_STATUS_STATUS_3);
			}
			else {
				bindStatus = true;

				sb.append(_FINDER_COLUMN_STATUS_STATUS_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindStatus) {
					queryPos.add(status);
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

	private static final String _FINDER_COLUMN_STATUS_STATUS_2 =
		"grievance.status = ?";

	private static final String _FINDER_COLUMN_STATUS_STATUS_3 =
		"(grievance.status IS NULL OR grievance.status = '')";

	private FinderPath _finderPathWithPaginationFindByStatusAndUserId;
	private FinderPath _finderPathWithoutPaginationFindByStatusAndUserId;
	private FinderPath _finderPathCountByStatusAndUserId;

	/**
	 * Returns all the grievances where status = &#63; and userId = &#63;.
	 *
	 * @param status the status
	 * @param userId the user ID
	 * @return the matching grievances
	 */
	@Override
	public List<Grievance> findByStatusAndUserId(String status, long userId) {
		return findByStatusAndUserId(
			status, userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the grievances where status = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GrievanceModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param userId the user ID
	 * @param start the lower bound of the range of grievances
	 * @param end the upper bound of the range of grievances (not inclusive)
	 * @return the range of matching grievances
	 */
	@Override
	public List<Grievance> findByStatusAndUserId(
		String status, long userId, int start, int end) {

		return findByStatusAndUserId(status, userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the grievances where status = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GrievanceModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param userId the user ID
	 * @param start the lower bound of the range of grievances
	 * @param end the upper bound of the range of grievances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching grievances
	 */
	@Override
	public List<Grievance> findByStatusAndUserId(
		String status, long userId, int start, int end,
		OrderByComparator<Grievance> orderByComparator) {

		return findByStatusAndUserId(
			status, userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the grievances where status = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GrievanceModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param userId the user ID
	 * @param start the lower bound of the range of grievances
	 * @param end the upper bound of the range of grievances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching grievances
	 */
	@Override
	public List<Grievance> findByStatusAndUserId(
		String status, long userId, int start, int end,
		OrderByComparator<Grievance> orderByComparator,
		boolean useFinderCache) {

		status = Objects.toString(status, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByStatusAndUserId;
				finderArgs = new Object[] {status, userId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByStatusAndUserId;
			finderArgs = new Object[] {
				status, userId, start, end, orderByComparator
			};
		}

		List<Grievance> list = null;

		if (useFinderCache) {
			list = (List<Grievance>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Grievance grievance : list) {
					if (!status.equals(grievance.getStatus()) ||
						(userId != grievance.getUserId())) {

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
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_GRIEVANCE_WHERE);

			boolean bindStatus = false;

			if (status.isEmpty()) {
				sb.append(_FINDER_COLUMN_STATUSANDUSERID_STATUS_3);
			}
			else {
				bindStatus = true;

				sb.append(_FINDER_COLUMN_STATUSANDUSERID_STATUS_2);
			}

			sb.append(_FINDER_COLUMN_STATUSANDUSERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(GrievanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindStatus) {
					queryPos.add(status);
				}

				queryPos.add(userId);

				list = (List<Grievance>)QueryUtil.list(
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
	 * Returns the first grievance in the ordered set where status = &#63; and userId = &#63;.
	 *
	 * @param status the status
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching grievance
	 * @throws NoSuchGrievanceException if a matching grievance could not be found
	 */
	@Override
	public Grievance findByStatusAndUserId_First(
			String status, long userId,
			OrderByComparator<Grievance> orderByComparator)
		throws NoSuchGrievanceException {

		Grievance grievance = fetchByStatusAndUserId_First(
			status, userId, orderByComparator);

		if (grievance != null) {
			return grievance;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("status=");
		sb.append(status);

		sb.append(", userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchGrievanceException(sb.toString());
	}

	/**
	 * Returns the first grievance in the ordered set where status = &#63; and userId = &#63;.
	 *
	 * @param status the status
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching grievance, or <code>null</code> if a matching grievance could not be found
	 */
	@Override
	public Grievance fetchByStatusAndUserId_First(
		String status, long userId,
		OrderByComparator<Grievance> orderByComparator) {

		List<Grievance> list = findByStatusAndUserId(
			status, userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last grievance in the ordered set where status = &#63; and userId = &#63;.
	 *
	 * @param status the status
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching grievance
	 * @throws NoSuchGrievanceException if a matching grievance could not be found
	 */
	@Override
	public Grievance findByStatusAndUserId_Last(
			String status, long userId,
			OrderByComparator<Grievance> orderByComparator)
		throws NoSuchGrievanceException {

		Grievance grievance = fetchByStatusAndUserId_Last(
			status, userId, orderByComparator);

		if (grievance != null) {
			return grievance;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("status=");
		sb.append(status);

		sb.append(", userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchGrievanceException(sb.toString());
	}

	/**
	 * Returns the last grievance in the ordered set where status = &#63; and userId = &#63;.
	 *
	 * @param status the status
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching grievance, or <code>null</code> if a matching grievance could not be found
	 */
	@Override
	public Grievance fetchByStatusAndUserId_Last(
		String status, long userId,
		OrderByComparator<Grievance> orderByComparator) {

		int count = countByStatusAndUserId(status, userId);

		if (count == 0) {
			return null;
		}

		List<Grievance> list = findByStatusAndUserId(
			status, userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the grievances before and after the current grievance in the ordered set where status = &#63; and userId = &#63;.
	 *
	 * @param grievanceId the primary key of the current grievance
	 * @param status the status
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next grievance
	 * @throws NoSuchGrievanceException if a grievance with the primary key could not be found
	 */
	@Override
	public Grievance[] findByStatusAndUserId_PrevAndNext(
			long grievanceId, String status, long userId,
			OrderByComparator<Grievance> orderByComparator)
		throws NoSuchGrievanceException {

		status = Objects.toString(status, "");

		Grievance grievance = findByPrimaryKey(grievanceId);

		Session session = null;

		try {
			session = openSession();

			Grievance[] array = new GrievanceImpl[3];

			array[0] = getByStatusAndUserId_PrevAndNext(
				session, grievance, status, userId, orderByComparator, true);

			array[1] = grievance;

			array[2] = getByStatusAndUserId_PrevAndNext(
				session, grievance, status, userId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Grievance getByStatusAndUserId_PrevAndNext(
		Session session, Grievance grievance, String status, long userId,
		OrderByComparator<Grievance> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_GRIEVANCE_WHERE);

		boolean bindStatus = false;

		if (status.isEmpty()) {
			sb.append(_FINDER_COLUMN_STATUSANDUSERID_STATUS_3);
		}
		else {
			bindStatus = true;

			sb.append(_FINDER_COLUMN_STATUSANDUSERID_STATUS_2);
		}

		sb.append(_FINDER_COLUMN_STATUSANDUSERID_USERID_2);

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
			sb.append(GrievanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindStatus) {
			queryPos.add(status);
		}

		queryPos.add(userId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(grievance)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Grievance> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the grievances where status = &#63; and userId = &#63; from the database.
	 *
	 * @param status the status
	 * @param userId the user ID
	 */
	@Override
	public void removeByStatusAndUserId(String status, long userId) {
		for (Grievance grievance :
				findByStatusAndUserId(
					status, userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(grievance);
		}
	}

	/**
	 * Returns the number of grievances where status = &#63; and userId = &#63;.
	 *
	 * @param status the status
	 * @param userId the user ID
	 * @return the number of matching grievances
	 */
	@Override
	public int countByStatusAndUserId(String status, long userId) {
		status = Objects.toString(status, "");

		FinderPath finderPath = _finderPathCountByStatusAndUserId;

		Object[] finderArgs = new Object[] {status, userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_GRIEVANCE_WHERE);

			boolean bindStatus = false;

			if (status.isEmpty()) {
				sb.append(_FINDER_COLUMN_STATUSANDUSERID_STATUS_3);
			}
			else {
				bindStatus = true;

				sb.append(_FINDER_COLUMN_STATUSANDUSERID_STATUS_2);
			}

			sb.append(_FINDER_COLUMN_STATUSANDUSERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindStatus) {
					queryPos.add(status);
				}

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

	private static final String _FINDER_COLUMN_STATUSANDUSERID_STATUS_2 =
		"grievance.status = ? AND ";

	private static final String _FINDER_COLUMN_STATUSANDUSERID_STATUS_3 =
		"(grievance.status IS NULL OR grievance.status = '') AND ";

	private static final String _FINDER_COLUMN_STATUSANDUSERID_USERID_2 =
		"grievance.userId = ?";

	public GrievancePersistenceImpl() {
		setModelClass(Grievance.class);

		setModelImplClass(GrievanceImpl.class);
		setModelPKClass(long.class);

		setTable(GrievanceTable.INSTANCE);
	}

	/**
	 * Caches the grievance in the entity cache if it is enabled.
	 *
	 * @param grievance the grievance
	 */
	@Override
	public void cacheResult(Grievance grievance) {
		entityCache.putResult(
			GrievanceImpl.class, grievance.getPrimaryKey(), grievance);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the grievances in the entity cache if it is enabled.
	 *
	 * @param grievances the grievances
	 */
	@Override
	public void cacheResult(List<Grievance> grievances) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (grievances.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Grievance grievance : grievances) {
			if (entityCache.getResult(
					GrievanceImpl.class, grievance.getPrimaryKey()) == null) {

				cacheResult(grievance);
			}
		}
	}

	/**
	 * Clears the cache for all grievances.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(GrievanceImpl.class);

		finderCache.clearCache(GrievanceImpl.class);
	}

	/**
	 * Clears the cache for the grievance.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Grievance grievance) {
		entityCache.removeResult(GrievanceImpl.class, grievance);
	}

	@Override
	public void clearCache(List<Grievance> grievances) {
		for (Grievance grievance : grievances) {
			entityCache.removeResult(GrievanceImpl.class, grievance);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(GrievanceImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(GrievanceImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new grievance with the primary key. Does not add the grievance to the database.
	 *
	 * @param grievanceId the primary key for the new grievance
	 * @return the new grievance
	 */
	@Override
	public Grievance create(long grievanceId) {
		Grievance grievance = new GrievanceImpl();

		grievance.setNew(true);
		grievance.setPrimaryKey(grievanceId);

		return grievance;
	}

	/**
	 * Removes the grievance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param grievanceId the primary key of the grievance
	 * @return the grievance that was removed
	 * @throws NoSuchGrievanceException if a grievance with the primary key could not be found
	 */
	@Override
	public Grievance remove(long grievanceId) throws NoSuchGrievanceException {
		return remove((Serializable)grievanceId);
	}

	/**
	 * Removes the grievance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the grievance
	 * @return the grievance that was removed
	 * @throws NoSuchGrievanceException if a grievance with the primary key could not be found
	 */
	@Override
	public Grievance remove(Serializable primaryKey)
		throws NoSuchGrievanceException {

		Session session = null;

		try {
			session = openSession();

			Grievance grievance = (Grievance)session.get(
				GrievanceImpl.class, primaryKey);

			if (grievance == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchGrievanceException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(grievance);
		}
		catch (NoSuchGrievanceException noSuchEntityException) {
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
	protected Grievance removeImpl(Grievance grievance) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(grievance)) {
				grievance = (Grievance)session.get(
					GrievanceImpl.class, grievance.getPrimaryKeyObj());
			}

			if (grievance != null) {
				session.delete(grievance);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (grievance != null) {
			clearCache(grievance);
		}

		return grievance;
	}

	@Override
	public Grievance updateImpl(Grievance grievance) {
		boolean isNew = grievance.isNew();

		if (!(grievance instanceof GrievanceModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(grievance.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(grievance);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in grievance proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Grievance implementation " +
					grievance.getClass());
		}

		GrievanceModelImpl grievanceModelImpl = (GrievanceModelImpl)grievance;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (grievance.getCreateDate() == null)) {
			if (serviceContext == null) {
				grievance.setCreateDate(date);
			}
			else {
				grievance.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!grievanceModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				grievance.setModifiedDate(date);
			}
			else {
				grievance.setModifiedDate(serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(grievance);
			}
			else {
				grievance = (Grievance)session.merge(grievance);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			GrievanceImpl.class, grievanceModelImpl, false, true);

		if (isNew) {
			grievance.setNew(false);
		}

		grievance.resetOriginalValues();

		return grievance;
	}

	/**
	 * Returns the grievance with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the grievance
	 * @return the grievance
	 * @throws NoSuchGrievanceException if a grievance with the primary key could not be found
	 */
	@Override
	public Grievance findByPrimaryKey(Serializable primaryKey)
		throws NoSuchGrievanceException {

		Grievance grievance = fetchByPrimaryKey(primaryKey);

		if (grievance == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchGrievanceException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return grievance;
	}

	/**
	 * Returns the grievance with the primary key or throws a <code>NoSuchGrievanceException</code> if it could not be found.
	 *
	 * @param grievanceId the primary key of the grievance
	 * @return the grievance
	 * @throws NoSuchGrievanceException if a grievance with the primary key could not be found
	 */
	@Override
	public Grievance findByPrimaryKey(long grievanceId)
		throws NoSuchGrievanceException {

		return findByPrimaryKey((Serializable)grievanceId);
	}

	/**
	 * Returns the grievance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param grievanceId the primary key of the grievance
	 * @return the grievance, or <code>null</code> if a grievance with the primary key could not be found
	 */
	@Override
	public Grievance fetchByPrimaryKey(long grievanceId) {
		return fetchByPrimaryKey((Serializable)grievanceId);
	}

	/**
	 * Returns all the grievances.
	 *
	 * @return the grievances
	 */
	@Override
	public List<Grievance> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the grievances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GrievanceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of grievances
	 * @param end the upper bound of the range of grievances (not inclusive)
	 * @return the range of grievances
	 */
	@Override
	public List<Grievance> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the grievances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GrievanceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of grievances
	 * @param end the upper bound of the range of grievances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of grievances
	 */
	@Override
	public List<Grievance> findAll(
		int start, int end, OrderByComparator<Grievance> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the grievances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GrievanceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of grievances
	 * @param end the upper bound of the range of grievances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of grievances
	 */
	@Override
	public List<Grievance> findAll(
		int start, int end, OrderByComparator<Grievance> orderByComparator,
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

		List<Grievance> list = null;

		if (useFinderCache) {
			list = (List<Grievance>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_GRIEVANCE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_GRIEVANCE;

				sql = sql.concat(GrievanceModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Grievance>)QueryUtil.list(
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
	 * Removes all the grievances from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Grievance grievance : findAll()) {
			remove(grievance);
		}
	}

	/**
	 * Returns the number of grievances.
	 *
	 * @return the number of grievances
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_GRIEVANCE);

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
		return "grievanceId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_GRIEVANCE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return GrievanceModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the grievance persistence.
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

		_finderPathWithPaginationFindByStatus = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByStatus",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"status"}, true);

		_finderPathWithoutPaginationFindByStatus = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStatus",
			new String[] {String.class.getName()}, new String[] {"status"},
			true);

		_finderPathCountByStatus = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStatus",
			new String[] {String.class.getName()}, new String[] {"status"},
			false);

		_finderPathWithPaginationFindByStatusAndUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByStatusAndUserId",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"status", "userId"}, true);

		_finderPathWithoutPaginationFindByStatusAndUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStatusAndUserId",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"status", "userId"}, true);

		_finderPathCountByStatusAndUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStatusAndUserId",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"status", "userId"}, false);

		GrievanceUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		GrievanceUtil.setPersistence(null);

		entityCache.removeCache(GrievanceImpl.class.getName());
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

	private static final String _SQL_SELECT_GRIEVANCE =
		"SELECT grievance FROM Grievance grievance";

	private static final String _SQL_SELECT_GRIEVANCE_WHERE =
		"SELECT grievance FROM Grievance grievance WHERE ";

	private static final String _SQL_COUNT_GRIEVANCE =
		"SELECT COUNT(grievance) FROM Grievance grievance";

	private static final String _SQL_COUNT_GRIEVANCE_WHERE =
		"SELECT COUNT(grievance) FROM Grievance grievance WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "grievance.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Grievance exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Grievance exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		GrievancePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}