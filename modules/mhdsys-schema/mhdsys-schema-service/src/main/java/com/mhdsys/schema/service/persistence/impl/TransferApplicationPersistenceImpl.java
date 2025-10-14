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

import com.mhdsys.schema.exception.NoSuchTransferApplicationException;
import com.mhdsys.schema.model.TransferApplication;
import com.mhdsys.schema.model.TransferApplicationTable;
import com.mhdsys.schema.model.impl.TransferApplicationImpl;
import com.mhdsys.schema.model.impl.TransferApplicationModelImpl;
import com.mhdsys.schema.service.persistence.TransferApplicationPersistence;
import com.mhdsys.schema.service.persistence.TransferApplicationUtil;
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
 * The persistence implementation for the transfer application service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = TransferApplicationPersistence.class)
public class TransferApplicationPersistenceImpl
	extends BasePersistenceImpl<TransferApplication>
	implements TransferApplicationPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>TransferApplicationUtil</code> to access the transfer application persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		TransferApplicationImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByTransfer;
	private FinderPath _finderPathWithoutPaginationFindByTransfer;
	private FinderPath _finderPathCountByTransfer;

	/**
	 * Returns all the transfer applications where transfer = &#63;.
	 *
	 * @param transfer the transfer
	 * @return the matching transfer applications
	 */
	@Override
	public List<TransferApplication> findByTransfer(String transfer) {
		return findByTransfer(
			transfer, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the transfer applications where transfer = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TransferApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param transfer the transfer
	 * @param start the lower bound of the range of transfer applications
	 * @param end the upper bound of the range of transfer applications (not inclusive)
	 * @return the range of matching transfer applications
	 */
	@Override
	public List<TransferApplication> findByTransfer(
		String transfer, int start, int end) {

		return findByTransfer(transfer, start, end, null);
	}

	/**
	 * Returns an ordered range of all the transfer applications where transfer = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TransferApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param transfer the transfer
	 * @param start the lower bound of the range of transfer applications
	 * @param end the upper bound of the range of transfer applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching transfer applications
	 */
	@Override
	public List<TransferApplication> findByTransfer(
		String transfer, int start, int end,
		OrderByComparator<TransferApplication> orderByComparator) {

		return findByTransfer(transfer, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the transfer applications where transfer = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TransferApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param transfer the transfer
	 * @param start the lower bound of the range of transfer applications
	 * @param end the upper bound of the range of transfer applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching transfer applications
	 */
	@Override
	public List<TransferApplication> findByTransfer(
		String transfer, int start, int end,
		OrderByComparator<TransferApplication> orderByComparator,
		boolean useFinderCache) {

		transfer = Objects.toString(transfer, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByTransfer;
				finderArgs = new Object[] {transfer};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByTransfer;
			finderArgs = new Object[] {transfer, start, end, orderByComparator};
		}

		List<TransferApplication> list = null;

		if (useFinderCache) {
			list = (List<TransferApplication>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TransferApplication transferApplication : list) {
					if (!transfer.equals(transferApplication.getTransfer())) {
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

			sb.append(_SQL_SELECT_TRANSFERAPPLICATION_WHERE);

			boolean bindTransfer = false;

			if (transfer.isEmpty()) {
				sb.append(_FINDER_COLUMN_TRANSFER_TRANSFER_3);
			}
			else {
				bindTransfer = true;

				sb.append(_FINDER_COLUMN_TRANSFER_TRANSFER_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(TransferApplicationModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindTransfer) {
					queryPos.add(transfer);
				}

				list = (List<TransferApplication>)QueryUtil.list(
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
	 * Returns the first transfer application in the ordered set where transfer = &#63;.
	 *
	 * @param transfer the transfer
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching transfer application
	 * @throws NoSuchTransferApplicationException if a matching transfer application could not be found
	 */
	@Override
	public TransferApplication findByTransfer_First(
			String transfer,
			OrderByComparator<TransferApplication> orderByComparator)
		throws NoSuchTransferApplicationException {

		TransferApplication transferApplication = fetchByTransfer_First(
			transfer, orderByComparator);

		if (transferApplication != null) {
			return transferApplication;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("transfer=");
		sb.append(transfer);

		sb.append("}");

		throw new NoSuchTransferApplicationException(sb.toString());
	}

	/**
	 * Returns the first transfer application in the ordered set where transfer = &#63;.
	 *
	 * @param transfer the transfer
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching transfer application, or <code>null</code> if a matching transfer application could not be found
	 */
	@Override
	public TransferApplication fetchByTransfer_First(
		String transfer,
		OrderByComparator<TransferApplication> orderByComparator) {

		List<TransferApplication> list = findByTransfer(
			transfer, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last transfer application in the ordered set where transfer = &#63;.
	 *
	 * @param transfer the transfer
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching transfer application
	 * @throws NoSuchTransferApplicationException if a matching transfer application could not be found
	 */
	@Override
	public TransferApplication findByTransfer_Last(
			String transfer,
			OrderByComparator<TransferApplication> orderByComparator)
		throws NoSuchTransferApplicationException {

		TransferApplication transferApplication = fetchByTransfer_Last(
			transfer, orderByComparator);

		if (transferApplication != null) {
			return transferApplication;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("transfer=");
		sb.append(transfer);

		sb.append("}");

		throw new NoSuchTransferApplicationException(sb.toString());
	}

	/**
	 * Returns the last transfer application in the ordered set where transfer = &#63;.
	 *
	 * @param transfer the transfer
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching transfer application, or <code>null</code> if a matching transfer application could not be found
	 */
	@Override
	public TransferApplication fetchByTransfer_Last(
		String transfer,
		OrderByComparator<TransferApplication> orderByComparator) {

		int count = countByTransfer(transfer);

		if (count == 0) {
			return null;
		}

		List<TransferApplication> list = findByTransfer(
			transfer, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the transfer applications before and after the current transfer application in the ordered set where transfer = &#63;.
	 *
	 * @param transferApplicationId the primary key of the current transfer application
	 * @param transfer the transfer
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next transfer application
	 * @throws NoSuchTransferApplicationException if a transfer application with the primary key could not be found
	 */
	@Override
	public TransferApplication[] findByTransfer_PrevAndNext(
			long transferApplicationId, String transfer,
			OrderByComparator<TransferApplication> orderByComparator)
		throws NoSuchTransferApplicationException {

		transfer = Objects.toString(transfer, "");

		TransferApplication transferApplication = findByPrimaryKey(
			transferApplicationId);

		Session session = null;

		try {
			session = openSession();

			TransferApplication[] array = new TransferApplicationImpl[3];

			array[0] = getByTransfer_PrevAndNext(
				session, transferApplication, transfer, orderByComparator,
				true);

			array[1] = transferApplication;

			array[2] = getByTransfer_PrevAndNext(
				session, transferApplication, transfer, orderByComparator,
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

	protected TransferApplication getByTransfer_PrevAndNext(
		Session session, TransferApplication transferApplication,
		String transfer,
		OrderByComparator<TransferApplication> orderByComparator,
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

		sb.append(_SQL_SELECT_TRANSFERAPPLICATION_WHERE);

		boolean bindTransfer = false;

		if (transfer.isEmpty()) {
			sb.append(_FINDER_COLUMN_TRANSFER_TRANSFER_3);
		}
		else {
			bindTransfer = true;

			sb.append(_FINDER_COLUMN_TRANSFER_TRANSFER_2);
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
			sb.append(TransferApplicationModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindTransfer) {
			queryPos.add(transfer);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						transferApplication)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<TransferApplication> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the transfer applications where transfer = &#63; from the database.
	 *
	 * @param transfer the transfer
	 */
	@Override
	public void removeByTransfer(String transfer) {
		for (TransferApplication transferApplication :
				findByTransfer(
					transfer, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(transferApplication);
		}
	}

	/**
	 * Returns the number of transfer applications where transfer = &#63;.
	 *
	 * @param transfer the transfer
	 * @return the number of matching transfer applications
	 */
	@Override
	public int countByTransfer(String transfer) {
		transfer = Objects.toString(transfer, "");

		FinderPath finderPath = _finderPathCountByTransfer;

		Object[] finderArgs = new Object[] {transfer};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_TRANSFERAPPLICATION_WHERE);

			boolean bindTransfer = false;

			if (transfer.isEmpty()) {
				sb.append(_FINDER_COLUMN_TRANSFER_TRANSFER_3);
			}
			else {
				bindTransfer = true;

				sb.append(_FINDER_COLUMN_TRANSFER_TRANSFER_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindTransfer) {
					queryPos.add(transfer);
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

	private static final String _FINDER_COLUMN_TRANSFER_TRANSFER_2 =
		"transferApplication.transfer = ?";

	private static final String _FINDER_COLUMN_TRANSFER_TRANSFER_3 =
		"(transferApplication.transfer IS NULL OR transferApplication.transfer = '')";

	private FinderPath _finderPathFetchByGrievanceId;
	private FinderPath _finderPathCountByGrievanceId;

	/**
	 * Returns the transfer application where grievanceId = &#63; or throws a <code>NoSuchTransferApplicationException</code> if it could not be found.
	 *
	 * @param grievanceId the grievance ID
	 * @return the matching transfer application
	 * @throws NoSuchTransferApplicationException if a matching transfer application could not be found
	 */
	@Override
	public TransferApplication findByGrievanceId(long grievanceId)
		throws NoSuchTransferApplicationException {

		TransferApplication transferApplication = fetchByGrievanceId(
			grievanceId);

		if (transferApplication == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("grievanceId=");
			sb.append(grievanceId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchTransferApplicationException(sb.toString());
		}

		return transferApplication;
	}

	/**
	 * Returns the transfer application where grievanceId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param grievanceId the grievance ID
	 * @return the matching transfer application, or <code>null</code> if a matching transfer application could not be found
	 */
	@Override
	public TransferApplication fetchByGrievanceId(long grievanceId) {
		return fetchByGrievanceId(grievanceId, true);
	}

	/**
	 * Returns the transfer application where grievanceId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param grievanceId the grievance ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching transfer application, or <code>null</code> if a matching transfer application could not be found
	 */
	@Override
	public TransferApplication fetchByGrievanceId(
		long grievanceId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {grievanceId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByGrievanceId, finderArgs, this);
		}

		if (result instanceof TransferApplication) {
			TransferApplication transferApplication =
				(TransferApplication)result;

			if (grievanceId != transferApplication.getGrievanceId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_TRANSFERAPPLICATION_WHERE);

			sb.append(_FINDER_COLUMN_GRIEVANCEID_GRIEVANCEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(grievanceId);

				List<TransferApplication> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByGrievanceId, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {grievanceId};
							}

							_log.warn(
								"TransferApplicationPersistenceImpl.fetchByGrievanceId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					TransferApplication transferApplication = list.get(0);

					result = transferApplication;

					cacheResult(transferApplication);
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
			return (TransferApplication)result;
		}
	}

	/**
	 * Removes the transfer application where grievanceId = &#63; from the database.
	 *
	 * @param grievanceId the grievance ID
	 * @return the transfer application that was removed
	 */
	@Override
	public TransferApplication removeByGrievanceId(long grievanceId)
		throws NoSuchTransferApplicationException {

		TransferApplication transferApplication = findByGrievanceId(
			grievanceId);

		return remove(transferApplication);
	}

	/**
	 * Returns the number of transfer applications where grievanceId = &#63;.
	 *
	 * @param grievanceId the grievance ID
	 * @return the number of matching transfer applications
	 */
	@Override
	public int countByGrievanceId(long grievanceId) {
		FinderPath finderPath = _finderPathCountByGrievanceId;

		Object[] finderArgs = new Object[] {grievanceId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_TRANSFERAPPLICATION_WHERE);

			sb.append(_FINDER_COLUMN_GRIEVANCEID_GRIEVANCEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(grievanceId);

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

	private static final String _FINDER_COLUMN_GRIEVANCEID_GRIEVANCEID_2 =
		"transferApplication.grievanceId = ?";

	public TransferApplicationPersistenceImpl() {
		setModelClass(TransferApplication.class);

		setModelImplClass(TransferApplicationImpl.class);
		setModelPKClass(long.class);

		setTable(TransferApplicationTable.INSTANCE);
	}

	/**
	 * Caches the transfer application in the entity cache if it is enabled.
	 *
	 * @param transferApplication the transfer application
	 */
	@Override
	public void cacheResult(TransferApplication transferApplication) {
		entityCache.putResult(
			TransferApplicationImpl.class, transferApplication.getPrimaryKey(),
			transferApplication);

		finderCache.putResult(
			_finderPathFetchByGrievanceId,
			new Object[] {transferApplication.getGrievanceId()},
			transferApplication);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the transfer applications in the entity cache if it is enabled.
	 *
	 * @param transferApplications the transfer applications
	 */
	@Override
	public void cacheResult(List<TransferApplication> transferApplications) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (transferApplications.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (TransferApplication transferApplication : transferApplications) {
			if (entityCache.getResult(
					TransferApplicationImpl.class,
					transferApplication.getPrimaryKey()) == null) {

				cacheResult(transferApplication);
			}
		}
	}

	/**
	 * Clears the cache for all transfer applications.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TransferApplicationImpl.class);

		finderCache.clearCache(TransferApplicationImpl.class);
	}

	/**
	 * Clears the cache for the transfer application.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TransferApplication transferApplication) {
		entityCache.removeResult(
			TransferApplicationImpl.class, transferApplication);
	}

	@Override
	public void clearCache(List<TransferApplication> transferApplications) {
		for (TransferApplication transferApplication : transferApplications) {
			entityCache.removeResult(
				TransferApplicationImpl.class, transferApplication);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(TransferApplicationImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(TransferApplicationImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		TransferApplicationModelImpl transferApplicationModelImpl) {

		Object[] args = new Object[] {
			transferApplicationModelImpl.getGrievanceId()
		};

		finderCache.putResult(
			_finderPathCountByGrievanceId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByGrievanceId, args, transferApplicationModelImpl);
	}

	/**
	 * Creates a new transfer application with the primary key. Does not add the transfer application to the database.
	 *
	 * @param transferApplicationId the primary key for the new transfer application
	 * @return the new transfer application
	 */
	@Override
	public TransferApplication create(long transferApplicationId) {
		TransferApplication transferApplication = new TransferApplicationImpl();

		transferApplication.setNew(true);
		transferApplication.setPrimaryKey(transferApplicationId);

		return transferApplication;
	}

	/**
	 * Removes the transfer application with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param transferApplicationId the primary key of the transfer application
	 * @return the transfer application that was removed
	 * @throws NoSuchTransferApplicationException if a transfer application with the primary key could not be found
	 */
	@Override
	public TransferApplication remove(long transferApplicationId)
		throws NoSuchTransferApplicationException {

		return remove((Serializable)transferApplicationId);
	}

	/**
	 * Removes the transfer application with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the transfer application
	 * @return the transfer application that was removed
	 * @throws NoSuchTransferApplicationException if a transfer application with the primary key could not be found
	 */
	@Override
	public TransferApplication remove(Serializable primaryKey)
		throws NoSuchTransferApplicationException {

		Session session = null;

		try {
			session = openSession();

			TransferApplication transferApplication =
				(TransferApplication)session.get(
					TransferApplicationImpl.class, primaryKey);

			if (transferApplication == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTransferApplicationException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(transferApplication);
		}
		catch (NoSuchTransferApplicationException noSuchEntityException) {
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
	protected TransferApplication removeImpl(
		TransferApplication transferApplication) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(transferApplication)) {
				transferApplication = (TransferApplication)session.get(
					TransferApplicationImpl.class,
					transferApplication.getPrimaryKeyObj());
			}

			if (transferApplication != null) {
				session.delete(transferApplication);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (transferApplication != null) {
			clearCache(transferApplication);
		}

		return transferApplication;
	}

	@Override
	public TransferApplication updateImpl(
		TransferApplication transferApplication) {

		boolean isNew = transferApplication.isNew();

		if (!(transferApplication instanceof TransferApplicationModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(transferApplication.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					transferApplication);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in transferApplication proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom TransferApplication implementation " +
					transferApplication.getClass());
		}

		TransferApplicationModelImpl transferApplicationModelImpl =
			(TransferApplicationModelImpl)transferApplication;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (transferApplication.getCreateDate() == null)) {
			if (serviceContext == null) {
				transferApplication.setCreateDate(date);
			}
			else {
				transferApplication.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!transferApplicationModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				transferApplication.setModifiedDate(date);
			}
			else {
				transferApplication.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(transferApplication);
			}
			else {
				transferApplication = (TransferApplication)session.merge(
					transferApplication);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			TransferApplicationImpl.class, transferApplicationModelImpl, false,
			true);

		cacheUniqueFindersCache(transferApplicationModelImpl);

		if (isNew) {
			transferApplication.setNew(false);
		}

		transferApplication.resetOriginalValues();

		return transferApplication;
	}

	/**
	 * Returns the transfer application with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the transfer application
	 * @return the transfer application
	 * @throws NoSuchTransferApplicationException if a transfer application with the primary key could not be found
	 */
	@Override
	public TransferApplication findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTransferApplicationException {

		TransferApplication transferApplication = fetchByPrimaryKey(primaryKey);

		if (transferApplication == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTransferApplicationException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return transferApplication;
	}

	/**
	 * Returns the transfer application with the primary key or throws a <code>NoSuchTransferApplicationException</code> if it could not be found.
	 *
	 * @param transferApplicationId the primary key of the transfer application
	 * @return the transfer application
	 * @throws NoSuchTransferApplicationException if a transfer application with the primary key could not be found
	 */
	@Override
	public TransferApplication findByPrimaryKey(long transferApplicationId)
		throws NoSuchTransferApplicationException {

		return findByPrimaryKey((Serializable)transferApplicationId);
	}

	/**
	 * Returns the transfer application with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param transferApplicationId the primary key of the transfer application
	 * @return the transfer application, or <code>null</code> if a transfer application with the primary key could not be found
	 */
	@Override
	public TransferApplication fetchByPrimaryKey(long transferApplicationId) {
		return fetchByPrimaryKey((Serializable)transferApplicationId);
	}

	/**
	 * Returns all the transfer applications.
	 *
	 * @return the transfer applications
	 */
	@Override
	public List<TransferApplication> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the transfer applications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TransferApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of transfer applications
	 * @param end the upper bound of the range of transfer applications (not inclusive)
	 * @return the range of transfer applications
	 */
	@Override
	public List<TransferApplication> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the transfer applications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TransferApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of transfer applications
	 * @param end the upper bound of the range of transfer applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of transfer applications
	 */
	@Override
	public List<TransferApplication> findAll(
		int start, int end,
		OrderByComparator<TransferApplication> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the transfer applications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TransferApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of transfer applications
	 * @param end the upper bound of the range of transfer applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of transfer applications
	 */
	@Override
	public List<TransferApplication> findAll(
		int start, int end,
		OrderByComparator<TransferApplication> orderByComparator,
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

		List<TransferApplication> list = null;

		if (useFinderCache) {
			list = (List<TransferApplication>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_TRANSFERAPPLICATION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_TRANSFERAPPLICATION;

				sql = sql.concat(TransferApplicationModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<TransferApplication>)QueryUtil.list(
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
	 * Removes all the transfer applications from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TransferApplication transferApplication : findAll()) {
			remove(transferApplication);
		}
	}

	/**
	 * Returns the number of transfer applications.
	 *
	 * @return the number of transfer applications
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
					_SQL_COUNT_TRANSFERAPPLICATION);

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
		return "transferApplicationId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_TRANSFERAPPLICATION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return TransferApplicationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the transfer application persistence.
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

		_finderPathWithPaginationFindByTransfer = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTransfer",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"transfer"}, true);

		_finderPathWithoutPaginationFindByTransfer = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTransfer",
			new String[] {String.class.getName()}, new String[] {"transfer"},
			true);

		_finderPathCountByTransfer = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTransfer",
			new String[] {String.class.getName()}, new String[] {"transfer"},
			false);

		_finderPathFetchByGrievanceId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByGrievanceId",
			new String[] {Long.class.getName()}, new String[] {"grievanceId"},
			true);

		_finderPathCountByGrievanceId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGrievanceId",
			new String[] {Long.class.getName()}, new String[] {"grievanceId"},
			false);

		TransferApplicationUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		TransferApplicationUtil.setPersistence(null);

		entityCache.removeCache(TransferApplicationImpl.class.getName());
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

	private static final String _SQL_SELECT_TRANSFERAPPLICATION =
		"SELECT transferApplication FROM TransferApplication transferApplication";

	private static final String _SQL_SELECT_TRANSFERAPPLICATION_WHERE =
		"SELECT transferApplication FROM TransferApplication transferApplication WHERE ";

	private static final String _SQL_COUNT_TRANSFERAPPLICATION =
		"SELECT COUNT(transferApplication) FROM TransferApplication transferApplication";

	private static final String _SQL_COUNT_TRANSFERAPPLICATION_WHERE =
		"SELECT COUNT(transferApplication) FROM TransferApplication transferApplication WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "transferApplication.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No TransferApplication exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No TransferApplication exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		TransferApplicationPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}