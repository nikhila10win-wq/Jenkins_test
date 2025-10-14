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

import com.mhdsys.schema.exception.NoSuchDivisionMasterException;
import com.mhdsys.schema.model.DivisionMaster;
import com.mhdsys.schema.model.DivisionMasterTable;
import com.mhdsys.schema.model.impl.DivisionMasterImpl;
import com.mhdsys.schema.model.impl.DivisionMasterModelImpl;
import com.mhdsys.schema.service.persistence.DivisionMasterPersistence;
import com.mhdsys.schema.service.persistence.DivisionMasterUtil;
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
 * The persistence implementation for the division master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = DivisionMasterPersistence.class)
public class DivisionMasterPersistenceImpl
	extends BasePersistenceImpl<DivisionMaster>
	implements DivisionMasterPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>DivisionMasterUtil</code> to access the division master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		DivisionMasterImpl.class.getName();

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
	 * Returns all the division masters where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @return the matching division masters
	 */
	@Override
	public List<DivisionMaster> findByActiveState(boolean isActive) {
		return findByActiveState(
			isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the division masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DivisionMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of division masters
	 * @param end the upper bound of the range of division masters (not inclusive)
	 * @return the range of matching division masters
	 */
	@Override
	public List<DivisionMaster> findByActiveState(
		boolean isActive, int start, int end) {

		return findByActiveState(isActive, start, end, null);
	}

	/**
	 * Returns an ordered range of all the division masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DivisionMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of division masters
	 * @param end the upper bound of the range of division masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching division masters
	 */
	@Override
	public List<DivisionMaster> findByActiveState(
		boolean isActive, int start, int end,
		OrderByComparator<DivisionMaster> orderByComparator) {

		return findByActiveState(isActive, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the division masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DivisionMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of division masters
	 * @param end the upper bound of the range of division masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching division masters
	 */
	@Override
	public List<DivisionMaster> findByActiveState(
		boolean isActive, int start, int end,
		OrderByComparator<DivisionMaster> orderByComparator,
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

		List<DivisionMaster> list = null;

		if (useFinderCache) {
			list = (List<DivisionMaster>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DivisionMaster divisionMaster : list) {
					if (isActive != divisionMaster.isIsActive()) {
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

			sb.append(_SQL_SELECT_DIVISIONMASTER_WHERE);

			sb.append(_FINDER_COLUMN_ACTIVESTATE_ISACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DivisionMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(isActive);

				list = (List<DivisionMaster>)QueryUtil.list(
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
	 * Returns the first division master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching division master
	 * @throws NoSuchDivisionMasterException if a matching division master could not be found
	 */
	@Override
	public DivisionMaster findByActiveState_First(
			boolean isActive,
			OrderByComparator<DivisionMaster> orderByComparator)
		throws NoSuchDivisionMasterException {

		DivisionMaster divisionMaster = fetchByActiveState_First(
			isActive, orderByComparator);

		if (divisionMaster != null) {
			return divisionMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isActive=");
		sb.append(isActive);

		sb.append("}");

		throw new NoSuchDivisionMasterException(sb.toString());
	}

	/**
	 * Returns the first division master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching division master, or <code>null</code> if a matching division master could not be found
	 */
	@Override
	public DivisionMaster fetchByActiveState_First(
		boolean isActive, OrderByComparator<DivisionMaster> orderByComparator) {

		List<DivisionMaster> list = findByActiveState(
			isActive, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last division master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching division master
	 * @throws NoSuchDivisionMasterException if a matching division master could not be found
	 */
	@Override
	public DivisionMaster findByActiveState_Last(
			boolean isActive,
			OrderByComparator<DivisionMaster> orderByComparator)
		throws NoSuchDivisionMasterException {

		DivisionMaster divisionMaster = fetchByActiveState_Last(
			isActive, orderByComparator);

		if (divisionMaster != null) {
			return divisionMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isActive=");
		sb.append(isActive);

		sb.append("}");

		throw new NoSuchDivisionMasterException(sb.toString());
	}

	/**
	 * Returns the last division master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching division master, or <code>null</code> if a matching division master could not be found
	 */
	@Override
	public DivisionMaster fetchByActiveState_Last(
		boolean isActive, OrderByComparator<DivisionMaster> orderByComparator) {

		int count = countByActiveState(isActive);

		if (count == 0) {
			return null;
		}

		List<DivisionMaster> list = findByActiveState(
			isActive, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the division masters before and after the current division master in the ordered set where isActive = &#63;.
	 *
	 * @param divisionId the primary key of the current division master
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next division master
	 * @throws NoSuchDivisionMasterException if a division master with the primary key could not be found
	 */
	@Override
	public DivisionMaster[] findByActiveState_PrevAndNext(
			long divisionId, boolean isActive,
			OrderByComparator<DivisionMaster> orderByComparator)
		throws NoSuchDivisionMasterException {

		DivisionMaster divisionMaster = findByPrimaryKey(divisionId);

		Session session = null;

		try {
			session = openSession();

			DivisionMaster[] array = new DivisionMasterImpl[3];

			array[0] = getByActiveState_PrevAndNext(
				session, divisionMaster, isActive, orderByComparator, true);

			array[1] = divisionMaster;

			array[2] = getByActiveState_PrevAndNext(
				session, divisionMaster, isActive, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected DivisionMaster getByActiveState_PrevAndNext(
		Session session, DivisionMaster divisionMaster, boolean isActive,
		OrderByComparator<DivisionMaster> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_DIVISIONMASTER_WHERE);

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
			sb.append(DivisionMasterModelImpl.ORDER_BY_JPQL);
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
						divisionMaster)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DivisionMaster> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the division masters where isActive = &#63; from the database.
	 *
	 * @param isActive the is active
	 */
	@Override
	public void removeByActiveState(boolean isActive) {
		for (DivisionMaster divisionMaster :
				findByActiveState(
					isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(divisionMaster);
		}
	}

	/**
	 * Returns the number of division masters where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @return the number of matching division masters
	 */
	@Override
	public int countByActiveState(boolean isActive) {
		FinderPath finderPath = _finderPathCountByActiveState;

		Object[] finderArgs = new Object[] {isActive};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DIVISIONMASTER_WHERE);

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
		"divisionMaster.isActive = ?";

	public DivisionMasterPersistenceImpl() {
		setModelClass(DivisionMaster.class);

		setModelImplClass(DivisionMasterImpl.class);
		setModelPKClass(long.class);

		setTable(DivisionMasterTable.INSTANCE);
	}

	/**
	 * Caches the division master in the entity cache if it is enabled.
	 *
	 * @param divisionMaster the division master
	 */
	@Override
	public void cacheResult(DivisionMaster divisionMaster) {
		entityCache.putResult(
			DivisionMasterImpl.class, divisionMaster.getPrimaryKey(),
			divisionMaster);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the division masters in the entity cache if it is enabled.
	 *
	 * @param divisionMasters the division masters
	 */
	@Override
	public void cacheResult(List<DivisionMaster> divisionMasters) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (divisionMasters.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (DivisionMaster divisionMaster : divisionMasters) {
			if (entityCache.getResult(
					DivisionMasterImpl.class, divisionMaster.getPrimaryKey()) ==
						null) {

				cacheResult(divisionMaster);
			}
		}
	}

	/**
	 * Clears the cache for all division masters.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DivisionMasterImpl.class);

		finderCache.clearCache(DivisionMasterImpl.class);
	}

	/**
	 * Clears the cache for the division master.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DivisionMaster divisionMaster) {
		entityCache.removeResult(DivisionMasterImpl.class, divisionMaster);
	}

	@Override
	public void clearCache(List<DivisionMaster> divisionMasters) {
		for (DivisionMaster divisionMaster : divisionMasters) {
			entityCache.removeResult(DivisionMasterImpl.class, divisionMaster);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(DivisionMasterImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(DivisionMasterImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new division master with the primary key. Does not add the division master to the database.
	 *
	 * @param divisionId the primary key for the new division master
	 * @return the new division master
	 */
	@Override
	public DivisionMaster create(long divisionId) {
		DivisionMaster divisionMaster = new DivisionMasterImpl();

		divisionMaster.setNew(true);
		divisionMaster.setPrimaryKey(divisionId);

		return divisionMaster;
	}

	/**
	 * Removes the division master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param divisionId the primary key of the division master
	 * @return the division master that was removed
	 * @throws NoSuchDivisionMasterException if a division master with the primary key could not be found
	 */
	@Override
	public DivisionMaster remove(long divisionId)
		throws NoSuchDivisionMasterException {

		return remove((Serializable)divisionId);
	}

	/**
	 * Removes the division master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the division master
	 * @return the division master that was removed
	 * @throws NoSuchDivisionMasterException if a division master with the primary key could not be found
	 */
	@Override
	public DivisionMaster remove(Serializable primaryKey)
		throws NoSuchDivisionMasterException {

		Session session = null;

		try {
			session = openSession();

			DivisionMaster divisionMaster = (DivisionMaster)session.get(
				DivisionMasterImpl.class, primaryKey);

			if (divisionMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDivisionMasterException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(divisionMaster);
		}
		catch (NoSuchDivisionMasterException noSuchEntityException) {
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
	protected DivisionMaster removeImpl(DivisionMaster divisionMaster) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(divisionMaster)) {
				divisionMaster = (DivisionMaster)session.get(
					DivisionMasterImpl.class,
					divisionMaster.getPrimaryKeyObj());
			}

			if (divisionMaster != null) {
				session.delete(divisionMaster);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (divisionMaster != null) {
			clearCache(divisionMaster);
		}

		return divisionMaster;
	}

	@Override
	public DivisionMaster updateImpl(DivisionMaster divisionMaster) {
		boolean isNew = divisionMaster.isNew();

		if (!(divisionMaster instanceof DivisionMasterModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(divisionMaster.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					divisionMaster);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in divisionMaster proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DivisionMaster implementation " +
					divisionMaster.getClass());
		}

		DivisionMasterModelImpl divisionMasterModelImpl =
			(DivisionMasterModelImpl)divisionMaster;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(divisionMaster);
			}
			else {
				divisionMaster = (DivisionMaster)session.merge(divisionMaster);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			DivisionMasterImpl.class, divisionMasterModelImpl, false, true);

		if (isNew) {
			divisionMaster.setNew(false);
		}

		divisionMaster.resetOriginalValues();

		return divisionMaster;
	}

	/**
	 * Returns the division master with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the division master
	 * @return the division master
	 * @throws NoSuchDivisionMasterException if a division master with the primary key could not be found
	 */
	@Override
	public DivisionMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDivisionMasterException {

		DivisionMaster divisionMaster = fetchByPrimaryKey(primaryKey);

		if (divisionMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDivisionMasterException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return divisionMaster;
	}

	/**
	 * Returns the division master with the primary key or throws a <code>NoSuchDivisionMasterException</code> if it could not be found.
	 *
	 * @param divisionId the primary key of the division master
	 * @return the division master
	 * @throws NoSuchDivisionMasterException if a division master with the primary key could not be found
	 */
	@Override
	public DivisionMaster findByPrimaryKey(long divisionId)
		throws NoSuchDivisionMasterException {

		return findByPrimaryKey((Serializable)divisionId);
	}

	/**
	 * Returns the division master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param divisionId the primary key of the division master
	 * @return the division master, or <code>null</code> if a division master with the primary key could not be found
	 */
	@Override
	public DivisionMaster fetchByPrimaryKey(long divisionId) {
		return fetchByPrimaryKey((Serializable)divisionId);
	}

	/**
	 * Returns all the division masters.
	 *
	 * @return the division masters
	 */
	@Override
	public List<DivisionMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the division masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DivisionMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of division masters
	 * @param end the upper bound of the range of division masters (not inclusive)
	 * @return the range of division masters
	 */
	@Override
	public List<DivisionMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the division masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DivisionMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of division masters
	 * @param end the upper bound of the range of division masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of division masters
	 */
	@Override
	public List<DivisionMaster> findAll(
		int start, int end,
		OrderByComparator<DivisionMaster> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the division masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DivisionMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of division masters
	 * @param end the upper bound of the range of division masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of division masters
	 */
	@Override
	public List<DivisionMaster> findAll(
		int start, int end, OrderByComparator<DivisionMaster> orderByComparator,
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

		List<DivisionMaster> list = null;

		if (useFinderCache) {
			list = (List<DivisionMaster>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_DIVISIONMASTER);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_DIVISIONMASTER;

				sql = sql.concat(DivisionMasterModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<DivisionMaster>)QueryUtil.list(
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
	 * Removes all the division masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DivisionMaster divisionMaster : findAll()) {
			remove(divisionMaster);
		}
	}

	/**
	 * Returns the number of division masters.
	 *
	 * @return the number of division masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_DIVISIONMASTER);

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
		return "divisionId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_DIVISIONMASTER;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return DivisionMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the division master persistence.
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

		DivisionMasterUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		DivisionMasterUtil.setPersistence(null);

		entityCache.removeCache(DivisionMasterImpl.class.getName());
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

	private static final String _SQL_SELECT_DIVISIONMASTER =
		"SELECT divisionMaster FROM DivisionMaster divisionMaster";

	private static final String _SQL_SELECT_DIVISIONMASTER_WHERE =
		"SELECT divisionMaster FROM DivisionMaster divisionMaster WHERE ";

	private static final String _SQL_COUNT_DIVISIONMASTER =
		"SELECT COUNT(divisionMaster) FROM DivisionMaster divisionMaster";

	private static final String _SQL_COUNT_DIVISIONMASTER_WHERE =
		"SELECT COUNT(divisionMaster) FROM DivisionMaster divisionMaster WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "divisionMaster.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No DivisionMaster exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No DivisionMaster exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		DivisionMasterPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}