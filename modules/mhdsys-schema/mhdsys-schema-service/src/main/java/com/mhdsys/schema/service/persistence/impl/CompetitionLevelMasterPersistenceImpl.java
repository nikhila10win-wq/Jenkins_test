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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import com.mhdsys.schema.exception.NoSuchCompetitionLevelMasterException;
import com.mhdsys.schema.model.CompetitionLevelMaster;
import com.mhdsys.schema.model.CompetitionLevelMasterTable;
import com.mhdsys.schema.model.impl.CompetitionLevelMasterImpl;
import com.mhdsys.schema.model.impl.CompetitionLevelMasterModelImpl;
import com.mhdsys.schema.service.persistence.CompetitionLevelMasterPersistence;
import com.mhdsys.schema.service.persistence.CompetitionLevelMasterUtil;
import com.mhdsys.schema.service.persistence.impl.constants.mhdsysPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the competition level master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = CompetitionLevelMasterPersistence.class)
public class CompetitionLevelMasterPersistenceImpl
	extends BasePersistenceImpl<CompetitionLevelMaster>
	implements CompetitionLevelMasterPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CompetitionLevelMasterUtil</code> to access the competition level master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CompetitionLevelMasterImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByActiveState;
	private FinderPath _finderPathWithoutPaginationFindByActiveState;
	private FinderPath _finderPathCountByActiveState;

	/**
	 * Returns all the competition level masters where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @return the matching competition level masters
	 */
	@Override
	public List<CompetitionLevelMaster> findByActiveState(boolean isActive) {
		return findByActiveState(
			isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the competition level masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CompetitionLevelMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of competition level masters
	 * @param end the upper bound of the range of competition level masters (not inclusive)
	 * @return the range of matching competition level masters
	 */
	@Override
	public List<CompetitionLevelMaster> findByActiveState(
		boolean isActive, int start, int end) {

		return findByActiveState(isActive, start, end, null);
	}

	/**
	 * Returns an ordered range of all the competition level masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CompetitionLevelMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of competition level masters
	 * @param end the upper bound of the range of competition level masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching competition level masters
	 */
	@Override
	public List<CompetitionLevelMaster> findByActiveState(
		boolean isActive, int start, int end,
		OrderByComparator<CompetitionLevelMaster> orderByComparator) {

		return findByActiveState(isActive, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the competition level masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CompetitionLevelMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of competition level masters
	 * @param end the upper bound of the range of competition level masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching competition level masters
	 */
	@Override
	public List<CompetitionLevelMaster> findByActiveState(
		boolean isActive, int start, int end,
		OrderByComparator<CompetitionLevelMaster> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByActiveState;
				finderArgs = new Object[] {isActive};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByActiveState;
			finderArgs = new Object[] {isActive, start, end, orderByComparator};
		}

		List<CompetitionLevelMaster> list = null;

		if (useFinderCache) {
			list = (List<CompetitionLevelMaster>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CompetitionLevelMaster competitionLevelMaster : list) {
					if (isActive != competitionLevelMaster.isIsActive()) {
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

			sb.append(_SQL_SELECT_COMPETITIONLEVELMASTER_WHERE);

			sb.append(_FINDER_COLUMN_ACTIVESTATE_ISACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CompetitionLevelMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(isActive);

				list = (List<CompetitionLevelMaster>)QueryUtil.list(
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
	 * Returns the first competition level master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching competition level master
	 * @throws NoSuchCompetitionLevelMasterException if a matching competition level master could not be found
	 */
	@Override
	public CompetitionLevelMaster findByActiveState_First(
			boolean isActive,
			OrderByComparator<CompetitionLevelMaster> orderByComparator)
		throws NoSuchCompetitionLevelMasterException {

		CompetitionLevelMaster competitionLevelMaster =
			fetchByActiveState_First(isActive, orderByComparator);

		if (competitionLevelMaster != null) {
			return competitionLevelMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isActive=");
		sb.append(isActive);

		sb.append("}");

		throw new NoSuchCompetitionLevelMasterException(sb.toString());
	}

	/**
	 * Returns the first competition level master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching competition level master, or <code>null</code> if a matching competition level master could not be found
	 */
	@Override
	public CompetitionLevelMaster fetchByActiveState_First(
		boolean isActive,
		OrderByComparator<CompetitionLevelMaster> orderByComparator) {

		List<CompetitionLevelMaster> list = findByActiveState(
			isActive, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last competition level master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching competition level master
	 * @throws NoSuchCompetitionLevelMasterException if a matching competition level master could not be found
	 */
	@Override
	public CompetitionLevelMaster findByActiveState_Last(
			boolean isActive,
			OrderByComparator<CompetitionLevelMaster> orderByComparator)
		throws NoSuchCompetitionLevelMasterException {

		CompetitionLevelMaster competitionLevelMaster = fetchByActiveState_Last(
			isActive, orderByComparator);

		if (competitionLevelMaster != null) {
			return competitionLevelMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isActive=");
		sb.append(isActive);

		sb.append("}");

		throw new NoSuchCompetitionLevelMasterException(sb.toString());
	}

	/**
	 * Returns the last competition level master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching competition level master, or <code>null</code> if a matching competition level master could not be found
	 */
	@Override
	public CompetitionLevelMaster fetchByActiveState_Last(
		boolean isActive,
		OrderByComparator<CompetitionLevelMaster> orderByComparator) {

		int count = countByActiveState(isActive);

		if (count == 0) {
			return null;
		}

		List<CompetitionLevelMaster> list = findByActiveState(
			isActive, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the competition level masters before and after the current competition level master in the ordered set where isActive = &#63;.
	 *
	 * @param competitionLevelMasterId the primary key of the current competition level master
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next competition level master
	 * @throws NoSuchCompetitionLevelMasterException if a competition level master with the primary key could not be found
	 */
	@Override
	public CompetitionLevelMaster[] findByActiveState_PrevAndNext(
			long competitionLevelMasterId, boolean isActive,
			OrderByComparator<CompetitionLevelMaster> orderByComparator)
		throws NoSuchCompetitionLevelMasterException {

		CompetitionLevelMaster competitionLevelMaster = findByPrimaryKey(
			competitionLevelMasterId);

		Session session = null;

		try {
			session = openSession();

			CompetitionLevelMaster[] array = new CompetitionLevelMasterImpl[3];

			array[0] = getByActiveState_PrevAndNext(
				session, competitionLevelMaster, isActive, orderByComparator,
				true);

			array[1] = competitionLevelMaster;

			array[2] = getByActiveState_PrevAndNext(
				session, competitionLevelMaster, isActive, orderByComparator,
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

	protected CompetitionLevelMaster getByActiveState_PrevAndNext(
		Session session, CompetitionLevelMaster competitionLevelMaster,
		boolean isActive,
		OrderByComparator<CompetitionLevelMaster> orderByComparator,
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

		sb.append(_SQL_SELECT_COMPETITIONLEVELMASTER_WHERE);

		sb.append(_FINDER_COLUMN_ACTIVESTATE_ISACTIVE_2);

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
			sb.append(CompetitionLevelMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(isActive);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						competitionLevelMaster)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CompetitionLevelMaster> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the competition level masters where isActive = &#63; from the database.
	 *
	 * @param isActive the is active
	 */
	@Override
	public void removeByActiveState(boolean isActive) {
		for (CompetitionLevelMaster competitionLevelMaster :
				findByActiveState(
					isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(competitionLevelMaster);
		}
	}

	/**
	 * Returns the number of competition level masters where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @return the number of matching competition level masters
	 */
	@Override
	public int countByActiveState(boolean isActive) {
		FinderPath finderPath = _finderPathCountByActiveState;

		Object[] finderArgs = new Object[] {isActive};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COMPETITIONLEVELMASTER_WHERE);

			sb.append(_FINDER_COLUMN_ACTIVESTATE_ISACTIVE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(isActive);

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

	private static final String _FINDER_COLUMN_ACTIVESTATE_ISACTIVE_2 =
		"competitionLevelMaster.isActive = ?";

	public CompetitionLevelMasterPersistenceImpl() {
		setModelClass(CompetitionLevelMaster.class);

		setModelImplClass(CompetitionLevelMasterImpl.class);
		setModelPKClass(long.class);

		setTable(CompetitionLevelMasterTable.INSTANCE);
	}

	/**
	 * Caches the competition level master in the entity cache if it is enabled.
	 *
	 * @param competitionLevelMaster the competition level master
	 */
	@Override
	public void cacheResult(CompetitionLevelMaster competitionLevelMaster) {
		entityCache.putResult(
			CompetitionLevelMasterImpl.class,
			competitionLevelMaster.getPrimaryKey(), competitionLevelMaster);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the competition level masters in the entity cache if it is enabled.
	 *
	 * @param competitionLevelMasters the competition level masters
	 */
	@Override
	public void cacheResult(
		List<CompetitionLevelMaster> competitionLevelMasters) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (competitionLevelMasters.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (CompetitionLevelMaster competitionLevelMaster :
				competitionLevelMasters) {

			if (entityCache.getResult(
					CompetitionLevelMasterImpl.class,
					competitionLevelMaster.getPrimaryKey()) == null) {

				cacheResult(competitionLevelMaster);
			}
		}
	}

	/**
	 * Clears the cache for all competition level masters.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CompetitionLevelMasterImpl.class);

		finderCache.clearCache(CompetitionLevelMasterImpl.class);
	}

	/**
	 * Clears the cache for the competition level master.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CompetitionLevelMaster competitionLevelMaster) {
		entityCache.removeResult(
			CompetitionLevelMasterImpl.class, competitionLevelMaster);
	}

	@Override
	public void clearCache(
		List<CompetitionLevelMaster> competitionLevelMasters) {

		for (CompetitionLevelMaster competitionLevelMaster :
				competitionLevelMasters) {

			entityCache.removeResult(
				CompetitionLevelMasterImpl.class, competitionLevelMaster);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(CompetitionLevelMasterImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				CompetitionLevelMasterImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new competition level master with the primary key. Does not add the competition level master to the database.
	 *
	 * @param competitionLevelMasterId the primary key for the new competition level master
	 * @return the new competition level master
	 */
	@Override
	public CompetitionLevelMaster create(long competitionLevelMasterId) {
		CompetitionLevelMaster competitionLevelMaster =
			new CompetitionLevelMasterImpl();

		competitionLevelMaster.setNew(true);
		competitionLevelMaster.setPrimaryKey(competitionLevelMasterId);

		return competitionLevelMaster;
	}

	/**
	 * Removes the competition level master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param competitionLevelMasterId the primary key of the competition level master
	 * @return the competition level master that was removed
	 * @throws NoSuchCompetitionLevelMasterException if a competition level master with the primary key could not be found
	 */
	@Override
	public CompetitionLevelMaster remove(long competitionLevelMasterId)
		throws NoSuchCompetitionLevelMasterException {

		return remove((Serializable)competitionLevelMasterId);
	}

	/**
	 * Removes the competition level master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the competition level master
	 * @return the competition level master that was removed
	 * @throws NoSuchCompetitionLevelMasterException if a competition level master with the primary key could not be found
	 */
	@Override
	public CompetitionLevelMaster remove(Serializable primaryKey)
		throws NoSuchCompetitionLevelMasterException {

		Session session = null;

		try {
			session = openSession();

			CompetitionLevelMaster competitionLevelMaster =
				(CompetitionLevelMaster)session.get(
					CompetitionLevelMasterImpl.class, primaryKey);

			if (competitionLevelMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCompetitionLevelMasterException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(competitionLevelMaster);
		}
		catch (NoSuchCompetitionLevelMasterException noSuchEntityException) {
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
	protected CompetitionLevelMaster removeImpl(
		CompetitionLevelMaster competitionLevelMaster) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(competitionLevelMaster)) {
				competitionLevelMaster = (CompetitionLevelMaster)session.get(
					CompetitionLevelMasterImpl.class,
					competitionLevelMaster.getPrimaryKeyObj());
			}

			if (competitionLevelMaster != null) {
				session.delete(competitionLevelMaster);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (competitionLevelMaster != null) {
			clearCache(competitionLevelMaster);
		}

		return competitionLevelMaster;
	}

	@Override
	public CompetitionLevelMaster updateImpl(
		CompetitionLevelMaster competitionLevelMaster) {

		boolean isNew = competitionLevelMaster.isNew();

		if (!(competitionLevelMaster instanceof
				CompetitionLevelMasterModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(competitionLevelMaster.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					competitionLevelMaster);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in competitionLevelMaster proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CompetitionLevelMaster implementation " +
					competitionLevelMaster.getClass());
		}

		CompetitionLevelMasterModelImpl competitionLevelMasterModelImpl =
			(CompetitionLevelMasterModelImpl)competitionLevelMaster;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(competitionLevelMaster);
			}
			else {
				competitionLevelMaster = (CompetitionLevelMaster)session.merge(
					competitionLevelMaster);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			CompetitionLevelMasterImpl.class, competitionLevelMasterModelImpl,
			false, true);

		if (isNew) {
			competitionLevelMaster.setNew(false);
		}

		competitionLevelMaster.resetOriginalValues();

		return competitionLevelMaster;
	}

	/**
	 * Returns the competition level master with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the competition level master
	 * @return the competition level master
	 * @throws NoSuchCompetitionLevelMasterException if a competition level master with the primary key could not be found
	 */
	@Override
	public CompetitionLevelMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCompetitionLevelMasterException {

		CompetitionLevelMaster competitionLevelMaster = fetchByPrimaryKey(
			primaryKey);

		if (competitionLevelMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCompetitionLevelMasterException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return competitionLevelMaster;
	}

	/**
	 * Returns the competition level master with the primary key or throws a <code>NoSuchCompetitionLevelMasterException</code> if it could not be found.
	 *
	 * @param competitionLevelMasterId the primary key of the competition level master
	 * @return the competition level master
	 * @throws NoSuchCompetitionLevelMasterException if a competition level master with the primary key could not be found
	 */
	@Override
	public CompetitionLevelMaster findByPrimaryKey(
			long competitionLevelMasterId)
		throws NoSuchCompetitionLevelMasterException {

		return findByPrimaryKey((Serializable)competitionLevelMasterId);
	}

	/**
	 * Returns the competition level master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param competitionLevelMasterId the primary key of the competition level master
	 * @return the competition level master, or <code>null</code> if a competition level master with the primary key could not be found
	 */
	@Override
	public CompetitionLevelMaster fetchByPrimaryKey(
		long competitionLevelMasterId) {

		return fetchByPrimaryKey((Serializable)competitionLevelMasterId);
	}

	/**
	 * Returns all the competition level masters.
	 *
	 * @return the competition level masters
	 */
	@Override
	public List<CompetitionLevelMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the competition level masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CompetitionLevelMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of competition level masters
	 * @param end the upper bound of the range of competition level masters (not inclusive)
	 * @return the range of competition level masters
	 */
	@Override
	public List<CompetitionLevelMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the competition level masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CompetitionLevelMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of competition level masters
	 * @param end the upper bound of the range of competition level masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of competition level masters
	 */
	@Override
	public List<CompetitionLevelMaster> findAll(
		int start, int end,
		OrderByComparator<CompetitionLevelMaster> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the competition level masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CompetitionLevelMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of competition level masters
	 * @param end the upper bound of the range of competition level masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of competition level masters
	 */
	@Override
	public List<CompetitionLevelMaster> findAll(
		int start, int end,
		OrderByComparator<CompetitionLevelMaster> orderByComparator,
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

		List<CompetitionLevelMaster> list = null;

		if (useFinderCache) {
			list = (List<CompetitionLevelMaster>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_COMPETITIONLEVELMASTER);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_COMPETITIONLEVELMASTER;

				sql = sql.concat(CompetitionLevelMasterModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<CompetitionLevelMaster>)QueryUtil.list(
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
	 * Removes all the competition level masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CompetitionLevelMaster competitionLevelMaster : findAll()) {
			remove(competitionLevelMaster);
		}
	}

	/**
	 * Returns the number of competition level masters.
	 *
	 * @return the number of competition level masters
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
					_SQL_COUNT_COMPETITIONLEVELMASTER);

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
		return "competitionLevelMasterId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_COMPETITIONLEVELMASTER;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CompetitionLevelMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the competition level master persistence.
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

		_finderPathWithPaginationFindByActiveState = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByActiveState",
			new String[] {
				Boolean.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"isActive"}, true);

		_finderPathWithoutPaginationFindByActiveState = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByActiveState",
			new String[] {Boolean.class.getName()}, new String[] {"isActive"},
			true);

		_finderPathCountByActiveState = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByActiveState",
			new String[] {Boolean.class.getName()}, new String[] {"isActive"},
			false);

		CompetitionLevelMasterUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		CompetitionLevelMasterUtil.setPersistence(null);

		entityCache.removeCache(CompetitionLevelMasterImpl.class.getName());
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

	private static final String _SQL_SELECT_COMPETITIONLEVELMASTER =
		"SELECT competitionLevelMaster FROM CompetitionLevelMaster competitionLevelMaster";

	private static final String _SQL_SELECT_COMPETITIONLEVELMASTER_WHERE =
		"SELECT competitionLevelMaster FROM CompetitionLevelMaster competitionLevelMaster WHERE ";

	private static final String _SQL_COUNT_COMPETITIONLEVELMASTER =
		"SELECT COUNT(competitionLevelMaster) FROM CompetitionLevelMaster competitionLevelMaster";

	private static final String _SQL_COUNT_COMPETITIONLEVELMASTER_WHERE =
		"SELECT COUNT(competitionLevelMaster) FROM CompetitionLevelMaster competitionLevelMaster WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"competitionLevelMaster.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CompetitionLevelMaster exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CompetitionLevelMaster exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CompetitionLevelMasterPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}