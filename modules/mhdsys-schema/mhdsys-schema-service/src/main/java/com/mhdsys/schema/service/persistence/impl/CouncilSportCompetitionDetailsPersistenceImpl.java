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

import com.mhdsys.schema.exception.NoSuchCouncilSportCompetitionDetailsException;
import com.mhdsys.schema.model.CouncilSportCompetitionDetails;
import com.mhdsys.schema.model.CouncilSportCompetitionDetailsTable;
import com.mhdsys.schema.model.impl.CouncilSportCompetitionDetailsImpl;
import com.mhdsys.schema.model.impl.CouncilSportCompetitionDetailsModelImpl;
import com.mhdsys.schema.service.persistence.CouncilSportCompetitionDetailsPersistence;
import com.mhdsys.schema.service.persistence.CouncilSportCompetitionDetailsUtil;
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
 * The persistence implementation for the council sport competition details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = CouncilSportCompetitionDetailsPersistence.class)
public class CouncilSportCompetitionDetailsPersistenceImpl
	extends BasePersistenceImpl<CouncilSportCompetitionDetails>
	implements CouncilSportCompetitionDetailsPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CouncilSportCompetitionDetailsUtil</code> to access the council sport competition details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CouncilSportCompetitionDetailsImpl.class.getName();

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
	 * Returns all the council sport competition detailses where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching council sport competition detailses
	 */
	@Override
	public List<CouncilSportCompetitionDetails> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the council sport competition detailses where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CouncilSportCompetitionDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of council sport competition detailses
	 * @param end the upper bound of the range of council sport competition detailses (not inclusive)
	 * @return the range of matching council sport competition detailses
	 */
	@Override
	public List<CouncilSportCompetitionDetails> findByUserId(
		long userId, int start, int end) {

		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the council sport competition detailses where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CouncilSportCompetitionDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of council sport competition detailses
	 * @param end the upper bound of the range of council sport competition detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching council sport competition detailses
	 */
	@Override
	public List<CouncilSportCompetitionDetails> findByUserId(
		long userId, int start, int end,
		OrderByComparator<CouncilSportCompetitionDetails> orderByComparator) {

		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the council sport competition detailses where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CouncilSportCompetitionDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of council sport competition detailses
	 * @param end the upper bound of the range of council sport competition detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching council sport competition detailses
	 */
	@Override
	public List<CouncilSportCompetitionDetails> findByUserId(
		long userId, int start, int end,
		OrderByComparator<CouncilSportCompetitionDetails> orderByComparator,
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

		List<CouncilSportCompetitionDetails> list = null;

		if (useFinderCache) {
			list = (List<CouncilSportCompetitionDetails>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CouncilSportCompetitionDetails
						councilSportCompetitionDetails : list) {

					if (userId != councilSportCompetitionDetails.getUserId()) {
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

			sb.append(_SQL_SELECT_COUNCILSPORTCOMPETITIONDETAILS_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(
					CouncilSportCompetitionDetailsModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				list = (List<CouncilSportCompetitionDetails>)QueryUtil.list(
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
	 * Returns the first council sport competition details in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching council sport competition details
	 * @throws NoSuchCouncilSportCompetitionDetailsException if a matching council sport competition details could not be found
	 */
	@Override
	public CouncilSportCompetitionDetails findByUserId_First(
			long userId,
			OrderByComparator<CouncilSportCompetitionDetails> orderByComparator)
		throws NoSuchCouncilSportCompetitionDetailsException {

		CouncilSportCompetitionDetails councilSportCompetitionDetails =
			fetchByUserId_First(userId, orderByComparator);

		if (councilSportCompetitionDetails != null) {
			return councilSportCompetitionDetails;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchCouncilSportCompetitionDetailsException(sb.toString());
	}

	/**
	 * Returns the first council sport competition details in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching council sport competition details, or <code>null</code> if a matching council sport competition details could not be found
	 */
	@Override
	public CouncilSportCompetitionDetails fetchByUserId_First(
		long userId,
		OrderByComparator<CouncilSportCompetitionDetails> orderByComparator) {

		List<CouncilSportCompetitionDetails> list = findByUserId(
			userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last council sport competition details in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching council sport competition details
	 * @throws NoSuchCouncilSportCompetitionDetailsException if a matching council sport competition details could not be found
	 */
	@Override
	public CouncilSportCompetitionDetails findByUserId_Last(
			long userId,
			OrderByComparator<CouncilSportCompetitionDetails> orderByComparator)
		throws NoSuchCouncilSportCompetitionDetailsException {

		CouncilSportCompetitionDetails councilSportCompetitionDetails =
			fetchByUserId_Last(userId, orderByComparator);

		if (councilSportCompetitionDetails != null) {
			return councilSportCompetitionDetails;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchCouncilSportCompetitionDetailsException(sb.toString());
	}

	/**
	 * Returns the last council sport competition details in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching council sport competition details, or <code>null</code> if a matching council sport competition details could not be found
	 */
	@Override
	public CouncilSportCompetitionDetails fetchByUserId_Last(
		long userId,
		OrderByComparator<CouncilSportCompetitionDetails> orderByComparator) {

		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<CouncilSportCompetitionDetails> list = findByUserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the council sport competition detailses before and after the current council sport competition details in the ordered set where userId = &#63;.
	 *
	 * @param councilSportCompetitionDetailsId the primary key of the current council sport competition details
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next council sport competition details
	 * @throws NoSuchCouncilSportCompetitionDetailsException if a council sport competition details with the primary key could not be found
	 */
	@Override
	public CouncilSportCompetitionDetails[] findByUserId_PrevAndNext(
			long councilSportCompetitionDetailsId, long userId,
			OrderByComparator<CouncilSportCompetitionDetails> orderByComparator)
		throws NoSuchCouncilSportCompetitionDetailsException {

		CouncilSportCompetitionDetails councilSportCompetitionDetails =
			findByPrimaryKey(councilSportCompetitionDetailsId);

		Session session = null;

		try {
			session = openSession();

			CouncilSportCompetitionDetails[] array =
				new CouncilSportCompetitionDetailsImpl[3];

			array[0] = getByUserId_PrevAndNext(
				session, councilSportCompetitionDetails, userId,
				orderByComparator, true);

			array[1] = councilSportCompetitionDetails;

			array[2] = getByUserId_PrevAndNext(
				session, councilSportCompetitionDetails, userId,
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

	protected CouncilSportCompetitionDetails getByUserId_PrevAndNext(
		Session session,
		CouncilSportCompetitionDetails councilSportCompetitionDetails,
		long userId,
		OrderByComparator<CouncilSportCompetitionDetails> orderByComparator,
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

		sb.append(_SQL_SELECT_COUNCILSPORTCOMPETITIONDETAILS_WHERE);

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
			sb.append(CouncilSportCompetitionDetailsModelImpl.ORDER_BY_JPQL);
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
						councilSportCompetitionDetails)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CouncilSportCompetitionDetails> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the council sport competition detailses where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (CouncilSportCompetitionDetails councilSportCompetitionDetails :
				findByUserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(councilSportCompetitionDetails);
		}
	}

	/**
	 * Returns the number of council sport competition detailses where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching council sport competition detailses
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COUNCILSPORTCOMPETITIONDETAILS_WHERE);

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
		"councilSportCompetitionDetails.userId = ?";

	public CouncilSportCompetitionDetailsPersistenceImpl() {
		setModelClass(CouncilSportCompetitionDetails.class);

		setModelImplClass(CouncilSportCompetitionDetailsImpl.class);
		setModelPKClass(long.class);

		setTable(CouncilSportCompetitionDetailsTable.INSTANCE);
	}

	/**
	 * Caches the council sport competition details in the entity cache if it is enabled.
	 *
	 * @param councilSportCompetitionDetails the council sport competition details
	 */
	@Override
	public void cacheResult(
		CouncilSportCompetitionDetails councilSportCompetitionDetails) {

		entityCache.putResult(
			CouncilSportCompetitionDetailsImpl.class,
			councilSportCompetitionDetails.getPrimaryKey(),
			councilSportCompetitionDetails);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the council sport competition detailses in the entity cache if it is enabled.
	 *
	 * @param councilSportCompetitionDetailses the council sport competition detailses
	 */
	@Override
	public void cacheResult(
		List<CouncilSportCompetitionDetails> councilSportCompetitionDetailses) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (councilSportCompetitionDetailses.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (CouncilSportCompetitionDetails councilSportCompetitionDetails :
				councilSportCompetitionDetailses) {

			if (entityCache.getResult(
					CouncilSportCompetitionDetailsImpl.class,
					councilSportCompetitionDetails.getPrimaryKey()) == null) {

				cacheResult(councilSportCompetitionDetails);
			}
		}
	}

	/**
	 * Clears the cache for all council sport competition detailses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CouncilSportCompetitionDetailsImpl.class);

		finderCache.clearCache(CouncilSportCompetitionDetailsImpl.class);
	}

	/**
	 * Clears the cache for the council sport competition details.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		CouncilSportCompetitionDetails councilSportCompetitionDetails) {

		entityCache.removeResult(
			CouncilSportCompetitionDetailsImpl.class,
			councilSportCompetitionDetails);
	}

	@Override
	public void clearCache(
		List<CouncilSportCompetitionDetails> councilSportCompetitionDetailses) {

		for (CouncilSportCompetitionDetails councilSportCompetitionDetails :
				councilSportCompetitionDetailses) {

			entityCache.removeResult(
				CouncilSportCompetitionDetailsImpl.class,
				councilSportCompetitionDetails);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(CouncilSportCompetitionDetailsImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				CouncilSportCompetitionDetailsImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new council sport competition details with the primary key. Does not add the council sport competition details to the database.
	 *
	 * @param councilSportCompetitionDetailsId the primary key for the new council sport competition details
	 * @return the new council sport competition details
	 */
	@Override
	public CouncilSportCompetitionDetails create(
		long councilSportCompetitionDetailsId) {

		CouncilSportCompetitionDetails councilSportCompetitionDetails =
			new CouncilSportCompetitionDetailsImpl();

		councilSportCompetitionDetails.setNew(true);
		councilSportCompetitionDetails.setPrimaryKey(
			councilSportCompetitionDetailsId);

		return councilSportCompetitionDetails;
	}

	/**
	 * Removes the council sport competition details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param councilSportCompetitionDetailsId the primary key of the council sport competition details
	 * @return the council sport competition details that was removed
	 * @throws NoSuchCouncilSportCompetitionDetailsException if a council sport competition details with the primary key could not be found
	 */
	@Override
	public CouncilSportCompetitionDetails remove(
			long councilSportCompetitionDetailsId)
		throws NoSuchCouncilSportCompetitionDetailsException {

		return remove((Serializable)councilSportCompetitionDetailsId);
	}

	/**
	 * Removes the council sport competition details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the council sport competition details
	 * @return the council sport competition details that was removed
	 * @throws NoSuchCouncilSportCompetitionDetailsException if a council sport competition details with the primary key could not be found
	 */
	@Override
	public CouncilSportCompetitionDetails remove(Serializable primaryKey)
		throws NoSuchCouncilSportCompetitionDetailsException {

		Session session = null;

		try {
			session = openSession();

			CouncilSportCompetitionDetails councilSportCompetitionDetails =
				(CouncilSportCompetitionDetails)session.get(
					CouncilSportCompetitionDetailsImpl.class, primaryKey);

			if (councilSportCompetitionDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCouncilSportCompetitionDetailsException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(councilSportCompetitionDetails);
		}
		catch (NoSuchCouncilSportCompetitionDetailsException
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
	protected CouncilSportCompetitionDetails removeImpl(
		CouncilSportCompetitionDetails councilSportCompetitionDetails) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(councilSportCompetitionDetails)) {
				councilSportCompetitionDetails =
					(CouncilSportCompetitionDetails)session.get(
						CouncilSportCompetitionDetailsImpl.class,
						councilSportCompetitionDetails.getPrimaryKeyObj());
			}

			if (councilSportCompetitionDetails != null) {
				session.delete(councilSportCompetitionDetails);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (councilSportCompetitionDetails != null) {
			clearCache(councilSportCompetitionDetails);
		}

		return councilSportCompetitionDetails;
	}

	@Override
	public CouncilSportCompetitionDetails updateImpl(
		CouncilSportCompetitionDetails councilSportCompetitionDetails) {

		boolean isNew = councilSportCompetitionDetails.isNew();

		if (!(councilSportCompetitionDetails instanceof
				CouncilSportCompetitionDetailsModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(
					councilSportCompetitionDetails.getClass())) {

				invocationHandler = ProxyUtil.getInvocationHandler(
					councilSportCompetitionDetails);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in councilSportCompetitionDetails proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CouncilSportCompetitionDetails implementation " +
					councilSportCompetitionDetails.getClass());
		}

		CouncilSportCompetitionDetailsModelImpl
			councilSportCompetitionDetailsModelImpl =
				(CouncilSportCompetitionDetailsModelImpl)
					councilSportCompetitionDetails;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (councilSportCompetitionDetails.getCreateDate() == null)) {
			if (serviceContext == null) {
				councilSportCompetitionDetails.setCreateDate(date);
			}
			else {
				councilSportCompetitionDetails.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!councilSportCompetitionDetailsModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				councilSportCompetitionDetails.setModifiedDate(date);
			}
			else {
				councilSportCompetitionDetails.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(councilSportCompetitionDetails);
			}
			else {
				councilSportCompetitionDetails =
					(CouncilSportCompetitionDetails)session.merge(
						councilSportCompetitionDetails);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			CouncilSportCompetitionDetailsImpl.class,
			councilSportCompetitionDetailsModelImpl, false, true);

		if (isNew) {
			councilSportCompetitionDetails.setNew(false);
		}

		councilSportCompetitionDetails.resetOriginalValues();

		return councilSportCompetitionDetails;
	}

	/**
	 * Returns the council sport competition details with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the council sport competition details
	 * @return the council sport competition details
	 * @throws NoSuchCouncilSportCompetitionDetailsException if a council sport competition details with the primary key could not be found
	 */
	@Override
	public CouncilSportCompetitionDetails findByPrimaryKey(
			Serializable primaryKey)
		throws NoSuchCouncilSportCompetitionDetailsException {

		CouncilSportCompetitionDetails councilSportCompetitionDetails =
			fetchByPrimaryKey(primaryKey);

		if (councilSportCompetitionDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCouncilSportCompetitionDetailsException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return councilSportCompetitionDetails;
	}

	/**
	 * Returns the council sport competition details with the primary key or throws a <code>NoSuchCouncilSportCompetitionDetailsException</code> if it could not be found.
	 *
	 * @param councilSportCompetitionDetailsId the primary key of the council sport competition details
	 * @return the council sport competition details
	 * @throws NoSuchCouncilSportCompetitionDetailsException if a council sport competition details with the primary key could not be found
	 */
	@Override
	public CouncilSportCompetitionDetails findByPrimaryKey(
			long councilSportCompetitionDetailsId)
		throws NoSuchCouncilSportCompetitionDetailsException {

		return findByPrimaryKey((Serializable)councilSportCompetitionDetailsId);
	}

	/**
	 * Returns the council sport competition details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param councilSportCompetitionDetailsId the primary key of the council sport competition details
	 * @return the council sport competition details, or <code>null</code> if a council sport competition details with the primary key could not be found
	 */
	@Override
	public CouncilSportCompetitionDetails fetchByPrimaryKey(
		long councilSportCompetitionDetailsId) {

		return fetchByPrimaryKey(
			(Serializable)councilSportCompetitionDetailsId);
	}

	/**
	 * Returns all the council sport competition detailses.
	 *
	 * @return the council sport competition detailses
	 */
	@Override
	public List<CouncilSportCompetitionDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the council sport competition detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CouncilSportCompetitionDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of council sport competition detailses
	 * @param end the upper bound of the range of council sport competition detailses (not inclusive)
	 * @return the range of council sport competition detailses
	 */
	@Override
	public List<CouncilSportCompetitionDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the council sport competition detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CouncilSportCompetitionDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of council sport competition detailses
	 * @param end the upper bound of the range of council sport competition detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of council sport competition detailses
	 */
	@Override
	public List<CouncilSportCompetitionDetails> findAll(
		int start, int end,
		OrderByComparator<CouncilSportCompetitionDetails> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the council sport competition detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CouncilSportCompetitionDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of council sport competition detailses
	 * @param end the upper bound of the range of council sport competition detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of council sport competition detailses
	 */
	@Override
	public List<CouncilSportCompetitionDetails> findAll(
		int start, int end,
		OrderByComparator<CouncilSportCompetitionDetails> orderByComparator,
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

		List<CouncilSportCompetitionDetails> list = null;

		if (useFinderCache) {
			list = (List<CouncilSportCompetitionDetails>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_COUNCILSPORTCOMPETITIONDETAILS);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_COUNCILSPORTCOMPETITIONDETAILS;

				sql = sql.concat(
					CouncilSportCompetitionDetailsModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<CouncilSportCompetitionDetails>)QueryUtil.list(
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
	 * Removes all the council sport competition detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CouncilSportCompetitionDetails councilSportCompetitionDetails :
				findAll()) {

			remove(councilSportCompetitionDetails);
		}
	}

	/**
	 * Returns the number of council sport competition detailses.
	 *
	 * @return the number of council sport competition detailses
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
					_SQL_COUNT_COUNCILSPORTCOMPETITIONDETAILS);

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
		return "councilSportCompetitionDetailsId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_COUNCILSPORTCOMPETITIONDETAILS;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CouncilSportCompetitionDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the council sport competition details persistence.
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

		CouncilSportCompetitionDetailsUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		CouncilSportCompetitionDetailsUtil.setPersistence(null);

		entityCache.removeCache(
			CouncilSportCompetitionDetailsImpl.class.getName());
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

	private static final String _SQL_SELECT_COUNCILSPORTCOMPETITIONDETAILS =
		"SELECT councilSportCompetitionDetails FROM CouncilSportCompetitionDetails councilSportCompetitionDetails";

	private static final String
		_SQL_SELECT_COUNCILSPORTCOMPETITIONDETAILS_WHERE =
			"SELECT councilSportCompetitionDetails FROM CouncilSportCompetitionDetails councilSportCompetitionDetails WHERE ";

	private static final String _SQL_COUNT_COUNCILSPORTCOMPETITIONDETAILS =
		"SELECT COUNT(councilSportCompetitionDetails) FROM CouncilSportCompetitionDetails councilSportCompetitionDetails";

	private static final String
		_SQL_COUNT_COUNCILSPORTCOMPETITIONDETAILS_WHERE =
			"SELECT COUNT(councilSportCompetitionDetails) FROM CouncilSportCompetitionDetails councilSportCompetitionDetails WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"councilSportCompetitionDetails.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CouncilSportCompetitionDetails exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CouncilSportCompetitionDetails exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CouncilSportCompetitionDetailsPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}