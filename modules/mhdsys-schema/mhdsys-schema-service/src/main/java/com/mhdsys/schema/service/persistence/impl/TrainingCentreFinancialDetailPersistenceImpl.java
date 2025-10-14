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

import com.mhdsys.schema.exception.NoSuchTrainingCentreFinancialDetailException;
import com.mhdsys.schema.model.TrainingCentreFinancialDetail;
import com.mhdsys.schema.model.TrainingCentreFinancialDetailTable;
import com.mhdsys.schema.model.impl.TrainingCentreFinancialDetailImpl;
import com.mhdsys.schema.model.impl.TrainingCentreFinancialDetailModelImpl;
import com.mhdsys.schema.service.persistence.TrainingCentreFinancialDetailPersistence;
import com.mhdsys.schema.service.persistence.TrainingCentreFinancialDetailUtil;
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
 * The persistence implementation for the training centre financial detail service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = TrainingCentreFinancialDetailPersistence.class)
public class TrainingCentreFinancialDetailPersistenceImpl
	extends BasePersistenceImpl<TrainingCentreFinancialDetail>
	implements TrainingCentreFinancialDetailPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>TrainingCentreFinancialDetailUtil</code> to access the training centre financial detail persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		TrainingCentreFinancialDetailImpl.class.getName();

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
	 * Returns all the training centre financial details where trainingCentreId = &#63;.
	 *
	 * @param trainingCentreId the training centre ID
	 * @return the matching training centre financial details
	 */
	@Override
	public List<TrainingCentreFinancialDetail> findByTrainingCentreId(
		long trainingCentreId) {

		return findByTrainingCentreId(
			trainingCentreId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training centre financial details where trainingCentreId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingCentreFinancialDetailModelImpl</code>.
	 * </p>
	 *
	 * @param trainingCentreId the training centre ID
	 * @param start the lower bound of the range of training centre financial details
	 * @param end the upper bound of the range of training centre financial details (not inclusive)
	 * @return the range of matching training centre financial details
	 */
	@Override
	public List<TrainingCentreFinancialDetail> findByTrainingCentreId(
		long trainingCentreId, int start, int end) {

		return findByTrainingCentreId(trainingCentreId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the training centre financial details where trainingCentreId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingCentreFinancialDetailModelImpl</code>.
	 * </p>
	 *
	 * @param trainingCentreId the training centre ID
	 * @param start the lower bound of the range of training centre financial details
	 * @param end the upper bound of the range of training centre financial details (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching training centre financial details
	 */
	@Override
	public List<TrainingCentreFinancialDetail> findByTrainingCentreId(
		long trainingCentreId, int start, int end,
		OrderByComparator<TrainingCentreFinancialDetail> orderByComparator) {

		return findByTrainingCentreId(
			trainingCentreId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the training centre financial details where trainingCentreId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingCentreFinancialDetailModelImpl</code>.
	 * </p>
	 *
	 * @param trainingCentreId the training centre ID
	 * @param start the lower bound of the range of training centre financial details
	 * @param end the upper bound of the range of training centre financial details (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching training centre financial details
	 */
	@Override
	public List<TrainingCentreFinancialDetail> findByTrainingCentreId(
		long trainingCentreId, int start, int end,
		OrderByComparator<TrainingCentreFinancialDetail> orderByComparator,
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

		List<TrainingCentreFinancialDetail> list = null;

		if (useFinderCache) {
			list = (List<TrainingCentreFinancialDetail>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TrainingCentreFinancialDetail
						trainingCentreFinancialDetail : list) {

					if (trainingCentreId !=
							trainingCentreFinancialDetail.
								getTrainingCentreId()) {

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

			sb.append(_SQL_SELECT_TRAININGCENTREFINANCIALDETAIL_WHERE);

			sb.append(_FINDER_COLUMN_TRAININGCENTREID_TRAININGCENTREID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(TrainingCentreFinancialDetailModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(trainingCentreId);

				list = (List<TrainingCentreFinancialDetail>)QueryUtil.list(
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
	 * Returns the first training centre financial detail in the ordered set where trainingCentreId = &#63;.
	 *
	 * @param trainingCentreId the training centre ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training centre financial detail
	 * @throws NoSuchTrainingCentreFinancialDetailException if a matching training centre financial detail could not be found
	 */
	@Override
	public TrainingCentreFinancialDetail findByTrainingCentreId_First(
			long trainingCentreId,
			OrderByComparator<TrainingCentreFinancialDetail> orderByComparator)
		throws NoSuchTrainingCentreFinancialDetailException {

		TrainingCentreFinancialDetail trainingCentreFinancialDetail =
			fetchByTrainingCentreId_First(trainingCentreId, orderByComparator);

		if (trainingCentreFinancialDetail != null) {
			return trainingCentreFinancialDetail;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("trainingCentreId=");
		sb.append(trainingCentreId);

		sb.append("}");

		throw new NoSuchTrainingCentreFinancialDetailException(sb.toString());
	}

	/**
	 * Returns the first training centre financial detail in the ordered set where trainingCentreId = &#63;.
	 *
	 * @param trainingCentreId the training centre ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training centre financial detail, or <code>null</code> if a matching training centre financial detail could not be found
	 */
	@Override
	public TrainingCentreFinancialDetail fetchByTrainingCentreId_First(
		long trainingCentreId,
		OrderByComparator<TrainingCentreFinancialDetail> orderByComparator) {

		List<TrainingCentreFinancialDetail> list = findByTrainingCentreId(
			trainingCentreId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last training centre financial detail in the ordered set where trainingCentreId = &#63;.
	 *
	 * @param trainingCentreId the training centre ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training centre financial detail
	 * @throws NoSuchTrainingCentreFinancialDetailException if a matching training centre financial detail could not be found
	 */
	@Override
	public TrainingCentreFinancialDetail findByTrainingCentreId_Last(
			long trainingCentreId,
			OrderByComparator<TrainingCentreFinancialDetail> orderByComparator)
		throws NoSuchTrainingCentreFinancialDetailException {

		TrainingCentreFinancialDetail trainingCentreFinancialDetail =
			fetchByTrainingCentreId_Last(trainingCentreId, orderByComparator);

		if (trainingCentreFinancialDetail != null) {
			return trainingCentreFinancialDetail;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("trainingCentreId=");
		sb.append(trainingCentreId);

		sb.append("}");

		throw new NoSuchTrainingCentreFinancialDetailException(sb.toString());
	}

	/**
	 * Returns the last training centre financial detail in the ordered set where trainingCentreId = &#63;.
	 *
	 * @param trainingCentreId the training centre ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training centre financial detail, or <code>null</code> if a matching training centre financial detail could not be found
	 */
	@Override
	public TrainingCentreFinancialDetail fetchByTrainingCentreId_Last(
		long trainingCentreId,
		OrderByComparator<TrainingCentreFinancialDetail> orderByComparator) {

		int count = countByTrainingCentreId(trainingCentreId);

		if (count == 0) {
			return null;
		}

		List<TrainingCentreFinancialDetail> list = findByTrainingCentreId(
			trainingCentreId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the training centre financial details before and after the current training centre financial detail in the ordered set where trainingCentreId = &#63;.
	 *
	 * @param financialDetailId the primary key of the current training centre financial detail
	 * @param trainingCentreId the training centre ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next training centre financial detail
	 * @throws NoSuchTrainingCentreFinancialDetailException if a training centre financial detail with the primary key could not be found
	 */
	@Override
	public TrainingCentreFinancialDetail[] findByTrainingCentreId_PrevAndNext(
			long financialDetailId, long trainingCentreId,
			OrderByComparator<TrainingCentreFinancialDetail> orderByComparator)
		throws NoSuchTrainingCentreFinancialDetailException {

		TrainingCentreFinancialDetail trainingCentreFinancialDetail =
			findByPrimaryKey(financialDetailId);

		Session session = null;

		try {
			session = openSession();

			TrainingCentreFinancialDetail[] array =
				new TrainingCentreFinancialDetailImpl[3];

			array[0] = getByTrainingCentreId_PrevAndNext(
				session, trainingCentreFinancialDetail, trainingCentreId,
				orderByComparator, true);

			array[1] = trainingCentreFinancialDetail;

			array[2] = getByTrainingCentreId_PrevAndNext(
				session, trainingCentreFinancialDetail, trainingCentreId,
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

	protected TrainingCentreFinancialDetail getByTrainingCentreId_PrevAndNext(
		Session session,
		TrainingCentreFinancialDetail trainingCentreFinancialDetail,
		long trainingCentreId,
		OrderByComparator<TrainingCentreFinancialDetail> orderByComparator,
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

		sb.append(_SQL_SELECT_TRAININGCENTREFINANCIALDETAIL_WHERE);

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
			sb.append(TrainingCentreFinancialDetailModelImpl.ORDER_BY_JPQL);
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
						trainingCentreFinancialDetail)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<TrainingCentreFinancialDetail> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the training centre financial details where trainingCentreId = &#63; from the database.
	 *
	 * @param trainingCentreId the training centre ID
	 */
	@Override
	public void removeByTrainingCentreId(long trainingCentreId) {
		for (TrainingCentreFinancialDetail trainingCentreFinancialDetail :
				findByTrainingCentreId(
					trainingCentreId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(trainingCentreFinancialDetail);
		}
	}

	/**
	 * Returns the number of training centre financial details where trainingCentreId = &#63;.
	 *
	 * @param trainingCentreId the training centre ID
	 * @return the number of matching training centre financial details
	 */
	@Override
	public int countByTrainingCentreId(long trainingCentreId) {
		FinderPath finderPath = _finderPathCountByTrainingCentreId;

		Object[] finderArgs = new Object[] {trainingCentreId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_TRAININGCENTREFINANCIALDETAIL_WHERE);

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
			"trainingCentreFinancialDetail.trainingCentreId = ?";

	public TrainingCentreFinancialDetailPersistenceImpl() {
		setModelClass(TrainingCentreFinancialDetail.class);

		setModelImplClass(TrainingCentreFinancialDetailImpl.class);
		setModelPKClass(long.class);

		setTable(TrainingCentreFinancialDetailTable.INSTANCE);
	}

	/**
	 * Caches the training centre financial detail in the entity cache if it is enabled.
	 *
	 * @param trainingCentreFinancialDetail the training centre financial detail
	 */
	@Override
	public void cacheResult(
		TrainingCentreFinancialDetail trainingCentreFinancialDetail) {

		entityCache.putResult(
			TrainingCentreFinancialDetailImpl.class,
			trainingCentreFinancialDetail.getPrimaryKey(),
			trainingCentreFinancialDetail);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the training centre financial details in the entity cache if it is enabled.
	 *
	 * @param trainingCentreFinancialDetails the training centre financial details
	 */
	@Override
	public void cacheResult(
		List<TrainingCentreFinancialDetail> trainingCentreFinancialDetails) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (trainingCentreFinancialDetails.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (TrainingCentreFinancialDetail trainingCentreFinancialDetail :
				trainingCentreFinancialDetails) {

			if (entityCache.getResult(
					TrainingCentreFinancialDetailImpl.class,
					trainingCentreFinancialDetail.getPrimaryKey()) == null) {

				cacheResult(trainingCentreFinancialDetail);
			}
		}
	}

	/**
	 * Clears the cache for all training centre financial details.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TrainingCentreFinancialDetailImpl.class);

		finderCache.clearCache(TrainingCentreFinancialDetailImpl.class);
	}

	/**
	 * Clears the cache for the training centre financial detail.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		TrainingCentreFinancialDetail trainingCentreFinancialDetail) {

		entityCache.removeResult(
			TrainingCentreFinancialDetailImpl.class,
			trainingCentreFinancialDetail);
	}

	@Override
	public void clearCache(
		List<TrainingCentreFinancialDetail> trainingCentreFinancialDetails) {

		for (TrainingCentreFinancialDetail trainingCentreFinancialDetail :
				trainingCentreFinancialDetails) {

			entityCache.removeResult(
				TrainingCentreFinancialDetailImpl.class,
				trainingCentreFinancialDetail);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(TrainingCentreFinancialDetailImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				TrainingCentreFinancialDetailImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new training centre financial detail with the primary key. Does not add the training centre financial detail to the database.
	 *
	 * @param financialDetailId the primary key for the new training centre financial detail
	 * @return the new training centre financial detail
	 */
	@Override
	public TrainingCentreFinancialDetail create(long financialDetailId) {
		TrainingCentreFinancialDetail trainingCentreFinancialDetail =
			new TrainingCentreFinancialDetailImpl();

		trainingCentreFinancialDetail.setNew(true);
		trainingCentreFinancialDetail.setPrimaryKey(financialDetailId);

		return trainingCentreFinancialDetail;
	}

	/**
	 * Removes the training centre financial detail with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param financialDetailId the primary key of the training centre financial detail
	 * @return the training centre financial detail that was removed
	 * @throws NoSuchTrainingCentreFinancialDetailException if a training centre financial detail with the primary key could not be found
	 */
	@Override
	public TrainingCentreFinancialDetail remove(long financialDetailId)
		throws NoSuchTrainingCentreFinancialDetailException {

		return remove((Serializable)financialDetailId);
	}

	/**
	 * Removes the training centre financial detail with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the training centre financial detail
	 * @return the training centre financial detail that was removed
	 * @throws NoSuchTrainingCentreFinancialDetailException if a training centre financial detail with the primary key could not be found
	 */
	@Override
	public TrainingCentreFinancialDetail remove(Serializable primaryKey)
		throws NoSuchTrainingCentreFinancialDetailException {

		Session session = null;

		try {
			session = openSession();

			TrainingCentreFinancialDetail trainingCentreFinancialDetail =
				(TrainingCentreFinancialDetail)session.get(
					TrainingCentreFinancialDetailImpl.class, primaryKey);

			if (trainingCentreFinancialDetail == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTrainingCentreFinancialDetailException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(trainingCentreFinancialDetail);
		}
		catch (NoSuchTrainingCentreFinancialDetailException
					noSuchEntityException) {

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
	protected TrainingCentreFinancialDetail removeImpl(
		TrainingCentreFinancialDetail trainingCentreFinancialDetail) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(trainingCentreFinancialDetail)) {
				trainingCentreFinancialDetail =
					(TrainingCentreFinancialDetail)session.get(
						TrainingCentreFinancialDetailImpl.class,
						trainingCentreFinancialDetail.getPrimaryKeyObj());
			}

			if (trainingCentreFinancialDetail != null) {
				session.delete(trainingCentreFinancialDetail);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (trainingCentreFinancialDetail != null) {
			clearCache(trainingCentreFinancialDetail);
		}

		return trainingCentreFinancialDetail;
	}

	@Override
	public TrainingCentreFinancialDetail updateImpl(
		TrainingCentreFinancialDetail trainingCentreFinancialDetail) {

		boolean isNew = trainingCentreFinancialDetail.isNew();

		if (!(trainingCentreFinancialDetail instanceof
				TrainingCentreFinancialDetailModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(
					trainingCentreFinancialDetail.getClass())) {

				invocationHandler = ProxyUtil.getInvocationHandler(
					trainingCentreFinancialDetail);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in trainingCentreFinancialDetail proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom TrainingCentreFinancialDetail implementation " +
					trainingCentreFinancialDetail.getClass());
		}

		TrainingCentreFinancialDetailModelImpl
			trainingCentreFinancialDetailModelImpl =
				(TrainingCentreFinancialDetailModelImpl)
					trainingCentreFinancialDetail;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (trainingCentreFinancialDetail.getCreateDate() == null)) {
			if (serviceContext == null) {
				trainingCentreFinancialDetail.setCreateDate(date);
			}
			else {
				trainingCentreFinancialDetail.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!trainingCentreFinancialDetailModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				trainingCentreFinancialDetail.setModifiedDate(date);
			}
			else {
				trainingCentreFinancialDetail.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(trainingCentreFinancialDetail);
			}
			else {
				trainingCentreFinancialDetail =
					(TrainingCentreFinancialDetail)session.merge(
						trainingCentreFinancialDetail);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			TrainingCentreFinancialDetailImpl.class,
			trainingCentreFinancialDetailModelImpl, false, true);

		if (isNew) {
			trainingCentreFinancialDetail.setNew(false);
		}

		trainingCentreFinancialDetail.resetOriginalValues();

		return trainingCentreFinancialDetail;
	}

	/**
	 * Returns the training centre financial detail with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the training centre financial detail
	 * @return the training centre financial detail
	 * @throws NoSuchTrainingCentreFinancialDetailException if a training centre financial detail with the primary key could not be found
	 */
	@Override
	public TrainingCentreFinancialDetail findByPrimaryKey(
			Serializable primaryKey)
		throws NoSuchTrainingCentreFinancialDetailException {

		TrainingCentreFinancialDetail trainingCentreFinancialDetail =
			fetchByPrimaryKey(primaryKey);

		if (trainingCentreFinancialDetail == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTrainingCentreFinancialDetailException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return trainingCentreFinancialDetail;
	}

	/**
	 * Returns the training centre financial detail with the primary key or throws a <code>NoSuchTrainingCentreFinancialDetailException</code> if it could not be found.
	 *
	 * @param financialDetailId the primary key of the training centre financial detail
	 * @return the training centre financial detail
	 * @throws NoSuchTrainingCentreFinancialDetailException if a training centre financial detail with the primary key could not be found
	 */
	@Override
	public TrainingCentreFinancialDetail findByPrimaryKey(
			long financialDetailId)
		throws NoSuchTrainingCentreFinancialDetailException {

		return findByPrimaryKey((Serializable)financialDetailId);
	}

	/**
	 * Returns the training centre financial detail with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param financialDetailId the primary key of the training centre financial detail
	 * @return the training centre financial detail, or <code>null</code> if a training centre financial detail with the primary key could not be found
	 */
	@Override
	public TrainingCentreFinancialDetail fetchByPrimaryKey(
		long financialDetailId) {

		return fetchByPrimaryKey((Serializable)financialDetailId);
	}

	/**
	 * Returns all the training centre financial details.
	 *
	 * @return the training centre financial details
	 */
	@Override
	public List<TrainingCentreFinancialDetail> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training centre financial details.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingCentreFinancialDetailModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training centre financial details
	 * @param end the upper bound of the range of training centre financial details (not inclusive)
	 * @return the range of training centre financial details
	 */
	@Override
	public List<TrainingCentreFinancialDetail> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the training centre financial details.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingCentreFinancialDetailModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training centre financial details
	 * @param end the upper bound of the range of training centre financial details (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of training centre financial details
	 */
	@Override
	public List<TrainingCentreFinancialDetail> findAll(
		int start, int end,
		OrderByComparator<TrainingCentreFinancialDetail> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the training centre financial details.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingCentreFinancialDetailModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training centre financial details
	 * @param end the upper bound of the range of training centre financial details (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of training centre financial details
	 */
	@Override
	public List<TrainingCentreFinancialDetail> findAll(
		int start, int end,
		OrderByComparator<TrainingCentreFinancialDetail> orderByComparator,
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

		List<TrainingCentreFinancialDetail> list = null;

		if (useFinderCache) {
			list = (List<TrainingCentreFinancialDetail>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_TRAININGCENTREFINANCIALDETAIL);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_TRAININGCENTREFINANCIALDETAIL;

				sql = sql.concat(
					TrainingCentreFinancialDetailModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<TrainingCentreFinancialDetail>)QueryUtil.list(
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
	 * Removes all the training centre financial details from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TrainingCentreFinancialDetail trainingCentreFinancialDetail :
				findAll()) {

			remove(trainingCentreFinancialDetail);
		}
	}

	/**
	 * Returns the number of training centre financial details.
	 *
	 * @return the number of training centre financial details
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
					_SQL_COUNT_TRAININGCENTREFINANCIALDETAIL);

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
		return "financialDetailId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_TRAININGCENTREFINANCIALDETAIL;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return TrainingCentreFinancialDetailModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the training centre financial detail persistence.
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

		TrainingCentreFinancialDetailUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		TrainingCentreFinancialDetailUtil.setPersistence(null);

		entityCache.removeCache(
			TrainingCentreFinancialDetailImpl.class.getName());
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

	private static final String _SQL_SELECT_TRAININGCENTREFINANCIALDETAIL =
		"SELECT trainingCentreFinancialDetail FROM TrainingCentreFinancialDetail trainingCentreFinancialDetail";

	private static final String
		_SQL_SELECT_TRAININGCENTREFINANCIALDETAIL_WHERE =
			"SELECT trainingCentreFinancialDetail FROM TrainingCentreFinancialDetail trainingCentreFinancialDetail WHERE ";

	private static final String _SQL_COUNT_TRAININGCENTREFINANCIALDETAIL =
		"SELECT COUNT(trainingCentreFinancialDetail) FROM TrainingCentreFinancialDetail trainingCentreFinancialDetail";

	private static final String _SQL_COUNT_TRAININGCENTREFINANCIALDETAIL_WHERE =
		"SELECT COUNT(trainingCentreFinancialDetail) FROM TrainingCentreFinancialDetail trainingCentreFinancialDetail WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"trainingCentreFinancialDetail.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No TrainingCentreFinancialDetail exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No TrainingCentreFinancialDetail exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		TrainingCentreFinancialDetailPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}