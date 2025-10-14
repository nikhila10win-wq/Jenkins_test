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

import com.mhdsys.schema.exception.NoSuchTrainingCentreReportException;
import com.mhdsys.schema.model.TrainingCentreReport;
import com.mhdsys.schema.model.TrainingCentreReportTable;
import com.mhdsys.schema.model.impl.TrainingCentreReportImpl;
import com.mhdsys.schema.model.impl.TrainingCentreReportModelImpl;
import com.mhdsys.schema.service.persistence.TrainingCentreReportPersistence;
import com.mhdsys.schema.service.persistence.TrainingCentreReportUtil;
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
 * The persistence implementation for the training centre report service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = TrainingCentreReportPersistence.class)
public class TrainingCentreReportPersistenceImpl
	extends BasePersistenceImpl<TrainingCentreReport>
	implements TrainingCentreReportPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>TrainingCentreReportUtil</code> to access the training centre report persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		TrainingCentreReportImpl.class.getName();

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
	 * Returns all the training centre reports where trainingCentreId = &#63;.
	 *
	 * @param trainingCentreId the training centre ID
	 * @return the matching training centre reports
	 */
	@Override
	public List<TrainingCentreReport> findByTrainingCentreId(
		long trainingCentreId) {

		return findByTrainingCentreId(
			trainingCentreId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training centre reports where trainingCentreId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingCentreReportModelImpl</code>.
	 * </p>
	 *
	 * @param trainingCentreId the training centre ID
	 * @param start the lower bound of the range of training centre reports
	 * @param end the upper bound of the range of training centre reports (not inclusive)
	 * @return the range of matching training centre reports
	 */
	@Override
	public List<TrainingCentreReport> findByTrainingCentreId(
		long trainingCentreId, int start, int end) {

		return findByTrainingCentreId(trainingCentreId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the training centre reports where trainingCentreId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingCentreReportModelImpl</code>.
	 * </p>
	 *
	 * @param trainingCentreId the training centre ID
	 * @param start the lower bound of the range of training centre reports
	 * @param end the upper bound of the range of training centre reports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching training centre reports
	 */
	@Override
	public List<TrainingCentreReport> findByTrainingCentreId(
		long trainingCentreId, int start, int end,
		OrderByComparator<TrainingCentreReport> orderByComparator) {

		return findByTrainingCentreId(
			trainingCentreId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the training centre reports where trainingCentreId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingCentreReportModelImpl</code>.
	 * </p>
	 *
	 * @param trainingCentreId the training centre ID
	 * @param start the lower bound of the range of training centre reports
	 * @param end the upper bound of the range of training centre reports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching training centre reports
	 */
	@Override
	public List<TrainingCentreReport> findByTrainingCentreId(
		long trainingCentreId, int start, int end,
		OrderByComparator<TrainingCentreReport> orderByComparator,
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

		List<TrainingCentreReport> list = null;

		if (useFinderCache) {
			list = (List<TrainingCentreReport>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TrainingCentreReport trainingCentreReport : list) {
					if (trainingCentreId !=
							trainingCentreReport.getTrainingCentreId()) {

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

			sb.append(_SQL_SELECT_TRAININGCENTREREPORT_WHERE);

			sb.append(_FINDER_COLUMN_TRAININGCENTREID_TRAININGCENTREID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(TrainingCentreReportModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(trainingCentreId);

				list = (List<TrainingCentreReport>)QueryUtil.list(
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
	 * Returns the first training centre report in the ordered set where trainingCentreId = &#63;.
	 *
	 * @param trainingCentreId the training centre ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training centre report
	 * @throws NoSuchTrainingCentreReportException if a matching training centre report could not be found
	 */
	@Override
	public TrainingCentreReport findByTrainingCentreId_First(
			long trainingCentreId,
			OrderByComparator<TrainingCentreReport> orderByComparator)
		throws NoSuchTrainingCentreReportException {

		TrainingCentreReport trainingCentreReport =
			fetchByTrainingCentreId_First(trainingCentreId, orderByComparator);

		if (trainingCentreReport != null) {
			return trainingCentreReport;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("trainingCentreId=");
		sb.append(trainingCentreId);

		sb.append("}");

		throw new NoSuchTrainingCentreReportException(sb.toString());
	}

	/**
	 * Returns the first training centre report in the ordered set where trainingCentreId = &#63;.
	 *
	 * @param trainingCentreId the training centre ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training centre report, or <code>null</code> if a matching training centre report could not be found
	 */
	@Override
	public TrainingCentreReport fetchByTrainingCentreId_First(
		long trainingCentreId,
		OrderByComparator<TrainingCentreReport> orderByComparator) {

		List<TrainingCentreReport> list = findByTrainingCentreId(
			trainingCentreId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last training centre report in the ordered set where trainingCentreId = &#63;.
	 *
	 * @param trainingCentreId the training centre ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training centre report
	 * @throws NoSuchTrainingCentreReportException if a matching training centre report could not be found
	 */
	@Override
	public TrainingCentreReport findByTrainingCentreId_Last(
			long trainingCentreId,
			OrderByComparator<TrainingCentreReport> orderByComparator)
		throws NoSuchTrainingCentreReportException {

		TrainingCentreReport trainingCentreReport =
			fetchByTrainingCentreId_Last(trainingCentreId, orderByComparator);

		if (trainingCentreReport != null) {
			return trainingCentreReport;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("trainingCentreId=");
		sb.append(trainingCentreId);

		sb.append("}");

		throw new NoSuchTrainingCentreReportException(sb.toString());
	}

	/**
	 * Returns the last training centre report in the ordered set where trainingCentreId = &#63;.
	 *
	 * @param trainingCentreId the training centre ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training centre report, or <code>null</code> if a matching training centre report could not be found
	 */
	@Override
	public TrainingCentreReport fetchByTrainingCentreId_Last(
		long trainingCentreId,
		OrderByComparator<TrainingCentreReport> orderByComparator) {

		int count = countByTrainingCentreId(trainingCentreId);

		if (count == 0) {
			return null;
		}

		List<TrainingCentreReport> list = findByTrainingCentreId(
			trainingCentreId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the training centre reports before and after the current training centre report in the ordered set where trainingCentreId = &#63;.
	 *
	 * @param reportId the primary key of the current training centre report
	 * @param trainingCentreId the training centre ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next training centre report
	 * @throws NoSuchTrainingCentreReportException if a training centre report with the primary key could not be found
	 */
	@Override
	public TrainingCentreReport[] findByTrainingCentreId_PrevAndNext(
			long reportId, long trainingCentreId,
			OrderByComparator<TrainingCentreReport> orderByComparator)
		throws NoSuchTrainingCentreReportException {

		TrainingCentreReport trainingCentreReport = findByPrimaryKey(reportId);

		Session session = null;

		try {
			session = openSession();

			TrainingCentreReport[] array = new TrainingCentreReportImpl[3];

			array[0] = getByTrainingCentreId_PrevAndNext(
				session, trainingCentreReport, trainingCentreId,
				orderByComparator, true);

			array[1] = trainingCentreReport;

			array[2] = getByTrainingCentreId_PrevAndNext(
				session, trainingCentreReport, trainingCentreId,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected TrainingCentreReport getByTrainingCentreId_PrevAndNext(
		Session session, TrainingCentreReport trainingCentreReport,
		long trainingCentreId,
		OrderByComparator<TrainingCentreReport> orderByComparator,
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

		sb.append(_SQL_SELECT_TRAININGCENTREREPORT_WHERE);

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
			sb.append(TrainingCentreReportModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(trainingCentreId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						trainingCentreReport)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<TrainingCentreReport> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the training centre reports where trainingCentreId = &#63; from the database.
	 *
	 * @param trainingCentreId the training centre ID
	 */
	@Override
	public void removeByTrainingCentreId(long trainingCentreId) {
		for (TrainingCentreReport trainingCentreReport :
				findByTrainingCentreId(
					trainingCentreId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(trainingCentreReport);
		}
	}

	/**
	 * Returns the number of training centre reports where trainingCentreId = &#63;.
	 *
	 * @param trainingCentreId the training centre ID
	 * @return the number of matching training centre reports
	 */
	@Override
	public int countByTrainingCentreId(long trainingCentreId) {
		FinderPath finderPath = _finderPathCountByTrainingCentreId;

		Object[] finderArgs = new Object[] {trainingCentreId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_TRAININGCENTREREPORT_WHERE);

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
			"trainingCentreReport.trainingCentreId = ?";

	public TrainingCentreReportPersistenceImpl() {
		setModelClass(TrainingCentreReport.class);

		setModelImplClass(TrainingCentreReportImpl.class);
		setModelPKClass(long.class);

		setTable(TrainingCentreReportTable.INSTANCE);
	}

	/**
	 * Caches the training centre report in the entity cache if it is enabled.
	 *
	 * @param trainingCentreReport the training centre report
	 */
	@Override
	public void cacheResult(TrainingCentreReport trainingCentreReport) {
		entityCache.putResult(
			TrainingCentreReportImpl.class,
			trainingCentreReport.getPrimaryKey(), trainingCentreReport);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the training centre reports in the entity cache if it is enabled.
	 *
	 * @param trainingCentreReports the training centre reports
	 */
	@Override
	public void cacheResult(List<TrainingCentreReport> trainingCentreReports) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (trainingCentreReports.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (TrainingCentreReport trainingCentreReport :
				trainingCentreReports) {

			if (entityCache.getResult(
					TrainingCentreReportImpl.class,
					trainingCentreReport.getPrimaryKey()) == null) {

				cacheResult(trainingCentreReport);
			}
		}
	}

	/**
	 * Clears the cache for all training centre reports.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TrainingCentreReportImpl.class);

		finderCache.clearCache(TrainingCentreReportImpl.class);
	}

	/**
	 * Clears the cache for the training centre report.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TrainingCentreReport trainingCentreReport) {
		entityCache.removeResult(
			TrainingCentreReportImpl.class, trainingCentreReport);
	}

	@Override
	public void clearCache(List<TrainingCentreReport> trainingCentreReports) {
		for (TrainingCentreReport trainingCentreReport :
				trainingCentreReports) {

			entityCache.removeResult(
				TrainingCentreReportImpl.class, trainingCentreReport);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(TrainingCentreReportImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				TrainingCentreReportImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new training centre report with the primary key. Does not add the training centre report to the database.
	 *
	 * @param reportId the primary key for the new training centre report
	 * @return the new training centre report
	 */
	@Override
	public TrainingCentreReport create(long reportId) {
		TrainingCentreReport trainingCentreReport =
			new TrainingCentreReportImpl();

		trainingCentreReport.setNew(true);
		trainingCentreReport.setPrimaryKey(reportId);

		return trainingCentreReport;
	}

	/**
	 * Removes the training centre report with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param reportId the primary key of the training centre report
	 * @return the training centre report that was removed
	 * @throws NoSuchTrainingCentreReportException if a training centre report with the primary key could not be found
	 */
	@Override
	public TrainingCentreReport remove(long reportId)
		throws NoSuchTrainingCentreReportException {

		return remove((Serializable)reportId);
	}

	/**
	 * Removes the training centre report with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the training centre report
	 * @return the training centre report that was removed
	 * @throws NoSuchTrainingCentreReportException if a training centre report with the primary key could not be found
	 */
	@Override
	public TrainingCentreReport remove(Serializable primaryKey)
		throws NoSuchTrainingCentreReportException {

		Session session = null;

		try {
			session = openSession();

			TrainingCentreReport trainingCentreReport =
				(TrainingCentreReport)session.get(
					TrainingCentreReportImpl.class, primaryKey);

			if (trainingCentreReport == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTrainingCentreReportException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(trainingCentreReport);
		}
		catch (NoSuchTrainingCentreReportException noSuchEntityException) {
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
	protected TrainingCentreReport removeImpl(
		TrainingCentreReport trainingCentreReport) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(trainingCentreReport)) {
				trainingCentreReport = (TrainingCentreReport)session.get(
					TrainingCentreReportImpl.class,
					trainingCentreReport.getPrimaryKeyObj());
			}

			if (trainingCentreReport != null) {
				session.delete(trainingCentreReport);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (trainingCentreReport != null) {
			clearCache(trainingCentreReport);
		}

		return trainingCentreReport;
	}

	@Override
	public TrainingCentreReport updateImpl(
		TrainingCentreReport trainingCentreReport) {

		boolean isNew = trainingCentreReport.isNew();

		if (!(trainingCentreReport instanceof TrainingCentreReportModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(trainingCentreReport.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					trainingCentreReport);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in trainingCentreReport proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom TrainingCentreReport implementation " +
					trainingCentreReport.getClass());
		}

		TrainingCentreReportModelImpl trainingCentreReportModelImpl =
			(TrainingCentreReportModelImpl)trainingCentreReport;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (trainingCentreReport.getCreateDate() == null)) {
			if (serviceContext == null) {
				trainingCentreReport.setCreateDate(date);
			}
			else {
				trainingCentreReport.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!trainingCentreReportModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				trainingCentreReport.setModifiedDate(date);
			}
			else {
				trainingCentreReport.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(trainingCentreReport);
			}
			else {
				trainingCentreReport = (TrainingCentreReport)session.merge(
					trainingCentreReport);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			TrainingCentreReportImpl.class, trainingCentreReportModelImpl,
			false, true);

		if (isNew) {
			trainingCentreReport.setNew(false);
		}

		trainingCentreReport.resetOriginalValues();

		return trainingCentreReport;
	}

	/**
	 * Returns the training centre report with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the training centre report
	 * @return the training centre report
	 * @throws NoSuchTrainingCentreReportException if a training centre report with the primary key could not be found
	 */
	@Override
	public TrainingCentreReport findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTrainingCentreReportException {

		TrainingCentreReport trainingCentreReport = fetchByPrimaryKey(
			primaryKey);

		if (trainingCentreReport == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTrainingCentreReportException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return trainingCentreReport;
	}

	/**
	 * Returns the training centre report with the primary key or throws a <code>NoSuchTrainingCentreReportException</code> if it could not be found.
	 *
	 * @param reportId the primary key of the training centre report
	 * @return the training centre report
	 * @throws NoSuchTrainingCentreReportException if a training centre report with the primary key could not be found
	 */
	@Override
	public TrainingCentreReport findByPrimaryKey(long reportId)
		throws NoSuchTrainingCentreReportException {

		return findByPrimaryKey((Serializable)reportId);
	}

	/**
	 * Returns the training centre report with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param reportId the primary key of the training centre report
	 * @return the training centre report, or <code>null</code> if a training centre report with the primary key could not be found
	 */
	@Override
	public TrainingCentreReport fetchByPrimaryKey(long reportId) {
		return fetchByPrimaryKey((Serializable)reportId);
	}

	/**
	 * Returns all the training centre reports.
	 *
	 * @return the training centre reports
	 */
	@Override
	public List<TrainingCentreReport> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training centre reports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingCentreReportModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training centre reports
	 * @param end the upper bound of the range of training centre reports (not inclusive)
	 * @return the range of training centre reports
	 */
	@Override
	public List<TrainingCentreReport> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the training centre reports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingCentreReportModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training centre reports
	 * @param end the upper bound of the range of training centre reports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of training centre reports
	 */
	@Override
	public List<TrainingCentreReport> findAll(
		int start, int end,
		OrderByComparator<TrainingCentreReport> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the training centre reports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingCentreReportModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training centre reports
	 * @param end the upper bound of the range of training centre reports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of training centre reports
	 */
	@Override
	public List<TrainingCentreReport> findAll(
		int start, int end,
		OrderByComparator<TrainingCentreReport> orderByComparator,
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

		List<TrainingCentreReport> list = null;

		if (useFinderCache) {
			list = (List<TrainingCentreReport>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_TRAININGCENTREREPORT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_TRAININGCENTREREPORT;

				sql = sql.concat(TrainingCentreReportModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<TrainingCentreReport>)QueryUtil.list(
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
	 * Removes all the training centre reports from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TrainingCentreReport trainingCentreReport : findAll()) {
			remove(trainingCentreReport);
		}
	}

	/**
	 * Returns the number of training centre reports.
	 *
	 * @return the number of training centre reports
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
					_SQL_COUNT_TRAININGCENTREREPORT);

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
		return "reportId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_TRAININGCENTREREPORT;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return TrainingCentreReportModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the training centre report persistence.
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

		TrainingCentreReportUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		TrainingCentreReportUtil.setPersistence(null);

		entityCache.removeCache(TrainingCentreReportImpl.class.getName());
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

	private static final String _SQL_SELECT_TRAININGCENTREREPORT =
		"SELECT trainingCentreReport FROM TrainingCentreReport trainingCentreReport";

	private static final String _SQL_SELECT_TRAININGCENTREREPORT_WHERE =
		"SELECT trainingCentreReport FROM TrainingCentreReport trainingCentreReport WHERE ";

	private static final String _SQL_COUNT_TRAININGCENTREREPORT =
		"SELECT COUNT(trainingCentreReport) FROM TrainingCentreReport trainingCentreReport";

	private static final String _SQL_COUNT_TRAININGCENTREREPORT_WHERE =
		"SELECT COUNT(trainingCentreReport) FROM TrainingCentreReport trainingCentreReport WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"trainingCentreReport.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No TrainingCentreReport exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No TrainingCentreReport exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		TrainingCentreReportPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}