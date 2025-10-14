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

import com.mhdsys.schema.exception.NoSuchCommittedMasterException;
import com.mhdsys.schema.model.CommittedMaster;
import com.mhdsys.schema.model.CommittedMasterTable;
import com.mhdsys.schema.model.impl.CommittedMasterImpl;
import com.mhdsys.schema.model.impl.CommittedMasterModelImpl;
import com.mhdsys.schema.service.persistence.CommittedMasterPersistence;
import com.mhdsys.schema.service.persistence.CommittedMasterUtil;
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
 * The persistence implementation for the committed master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = CommittedMasterPersistence.class)
public class CommittedMasterPersistenceImpl
	extends BasePersistenceImpl<CommittedMaster>
	implements CommittedMasterPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CommittedMasterUtil</code> to access the committed master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CommittedMasterImpl.class.getName();

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
	 * Returns all the committed masters where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @return the matching committed masters
	 */
	@Override
	public List<CommittedMaster> findByActiveState(boolean isActive) {
		return findByActiveState(
			isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the committed masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommittedMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of committed masters
	 * @param end the upper bound of the range of committed masters (not inclusive)
	 * @return the range of matching committed masters
	 */
	@Override
	public List<CommittedMaster> findByActiveState(
		boolean isActive, int start, int end) {

		return findByActiveState(isActive, start, end, null);
	}

	/**
	 * Returns an ordered range of all the committed masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommittedMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of committed masters
	 * @param end the upper bound of the range of committed masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching committed masters
	 */
	@Override
	public List<CommittedMaster> findByActiveState(
		boolean isActive, int start, int end,
		OrderByComparator<CommittedMaster> orderByComparator) {

		return findByActiveState(isActive, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the committed masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommittedMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of committed masters
	 * @param end the upper bound of the range of committed masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching committed masters
	 */
	@Override
	public List<CommittedMaster> findByActiveState(
		boolean isActive, int start, int end,
		OrderByComparator<CommittedMaster> orderByComparator,
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

		List<CommittedMaster> list = null;

		if (useFinderCache) {
			list = (List<CommittedMaster>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommittedMaster committedMaster : list) {
					if (isActive != committedMaster.isIsActive()) {
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

			sb.append(_SQL_SELECT_COMMITTEDMASTER_WHERE);

			sb.append(_FINDER_COLUMN_ACTIVESTATE_ISACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CommittedMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(isActive);

				list = (List<CommittedMaster>)QueryUtil.list(
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
	 * Returns the first committed master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching committed master
	 * @throws NoSuchCommittedMasterException if a matching committed master could not be found
	 */
	@Override
	public CommittedMaster findByActiveState_First(
			boolean isActive,
			OrderByComparator<CommittedMaster> orderByComparator)
		throws NoSuchCommittedMasterException {

		CommittedMaster committedMaster = fetchByActiveState_First(
			isActive, orderByComparator);

		if (committedMaster != null) {
			return committedMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isActive=");
		sb.append(isActive);

		sb.append("}");

		throw new NoSuchCommittedMasterException(sb.toString());
	}

	/**
	 * Returns the first committed master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching committed master, or <code>null</code> if a matching committed master could not be found
	 */
	@Override
	public CommittedMaster fetchByActiveState_First(
		boolean isActive,
		OrderByComparator<CommittedMaster> orderByComparator) {

		List<CommittedMaster> list = findByActiveState(
			isActive, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last committed master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching committed master
	 * @throws NoSuchCommittedMasterException if a matching committed master could not be found
	 */
	@Override
	public CommittedMaster findByActiveState_Last(
			boolean isActive,
			OrderByComparator<CommittedMaster> orderByComparator)
		throws NoSuchCommittedMasterException {

		CommittedMaster committedMaster = fetchByActiveState_Last(
			isActive, orderByComparator);

		if (committedMaster != null) {
			return committedMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isActive=");
		sb.append(isActive);

		sb.append("}");

		throw new NoSuchCommittedMasterException(sb.toString());
	}

	/**
	 * Returns the last committed master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching committed master, or <code>null</code> if a matching committed master could not be found
	 */
	@Override
	public CommittedMaster fetchByActiveState_Last(
		boolean isActive,
		OrderByComparator<CommittedMaster> orderByComparator) {

		int count = countByActiveState(isActive);

		if (count == 0) {
			return null;
		}

		List<CommittedMaster> list = findByActiveState(
			isActive, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the committed masters before and after the current committed master in the ordered set where isActive = &#63;.
	 *
	 * @param committedMasterId the primary key of the current committed master
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next committed master
	 * @throws NoSuchCommittedMasterException if a committed master with the primary key could not be found
	 */
	@Override
	public CommittedMaster[] findByActiveState_PrevAndNext(
			long committedMasterId, boolean isActive,
			OrderByComparator<CommittedMaster> orderByComparator)
		throws NoSuchCommittedMasterException {

		CommittedMaster committedMaster = findByPrimaryKey(committedMasterId);

		Session session = null;

		try {
			session = openSession();

			CommittedMaster[] array = new CommittedMasterImpl[3];

			array[0] = getByActiveState_PrevAndNext(
				session, committedMaster, isActive, orderByComparator, true);

			array[1] = committedMaster;

			array[2] = getByActiveState_PrevAndNext(
				session, committedMaster, isActive, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommittedMaster getByActiveState_PrevAndNext(
		Session session, CommittedMaster committedMaster, boolean isActive,
		OrderByComparator<CommittedMaster> orderByComparator,
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

		sb.append(_SQL_SELECT_COMMITTEDMASTER_WHERE);

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
			sb.append(CommittedMasterModelImpl.ORDER_BY_JPQL);
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
						committedMaster)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CommittedMaster> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the committed masters where isActive = &#63; from the database.
	 *
	 * @param isActive the is active
	 */
	@Override
	public void removeByActiveState(boolean isActive) {
		for (CommittedMaster committedMaster :
				findByActiveState(
					isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(committedMaster);
		}
	}

	/**
	 * Returns the number of committed masters where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @return the number of matching committed masters
	 */
	@Override
	public int countByActiveState(boolean isActive) {
		FinderPath finderPath = _finderPathCountByActiveState;

		Object[] finderArgs = new Object[] {isActive};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COMMITTEDMASTER_WHERE);

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
		"committedMaster.isActive = ?";

	public CommittedMasterPersistenceImpl() {
		setModelClass(CommittedMaster.class);

		setModelImplClass(CommittedMasterImpl.class);
		setModelPKClass(long.class);

		setTable(CommittedMasterTable.INSTANCE);
	}

	/**
	 * Caches the committed master in the entity cache if it is enabled.
	 *
	 * @param committedMaster the committed master
	 */
	@Override
	public void cacheResult(CommittedMaster committedMaster) {
		entityCache.putResult(
			CommittedMasterImpl.class, committedMaster.getPrimaryKey(),
			committedMaster);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the committed masters in the entity cache if it is enabled.
	 *
	 * @param committedMasters the committed masters
	 */
	@Override
	public void cacheResult(List<CommittedMaster> committedMasters) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (committedMasters.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (CommittedMaster committedMaster : committedMasters) {
			if (entityCache.getResult(
					CommittedMasterImpl.class,
					committedMaster.getPrimaryKey()) == null) {

				cacheResult(committedMaster);
			}
		}
	}

	/**
	 * Clears the cache for all committed masters.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommittedMasterImpl.class);

		finderCache.clearCache(CommittedMasterImpl.class);
	}

	/**
	 * Clears the cache for the committed master.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommittedMaster committedMaster) {
		entityCache.removeResult(CommittedMasterImpl.class, committedMaster);
	}

	@Override
	public void clearCache(List<CommittedMaster> committedMasters) {
		for (CommittedMaster committedMaster : committedMasters) {
			entityCache.removeResult(
				CommittedMasterImpl.class, committedMaster);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(CommittedMasterImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(CommittedMasterImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new committed master with the primary key. Does not add the committed master to the database.
	 *
	 * @param committedMasterId the primary key for the new committed master
	 * @return the new committed master
	 */
	@Override
	public CommittedMaster create(long committedMasterId) {
		CommittedMaster committedMaster = new CommittedMasterImpl();

		committedMaster.setNew(true);
		committedMaster.setPrimaryKey(committedMasterId);

		return committedMaster;
	}

	/**
	 * Removes the committed master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param committedMasterId the primary key of the committed master
	 * @return the committed master that was removed
	 * @throws NoSuchCommittedMasterException if a committed master with the primary key could not be found
	 */
	@Override
	public CommittedMaster remove(long committedMasterId)
		throws NoSuchCommittedMasterException {

		return remove((Serializable)committedMasterId);
	}

	/**
	 * Removes the committed master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the committed master
	 * @return the committed master that was removed
	 * @throws NoSuchCommittedMasterException if a committed master with the primary key could not be found
	 */
	@Override
	public CommittedMaster remove(Serializable primaryKey)
		throws NoSuchCommittedMasterException {

		Session session = null;

		try {
			session = openSession();

			CommittedMaster committedMaster = (CommittedMaster)session.get(
				CommittedMasterImpl.class, primaryKey);

			if (committedMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCommittedMasterException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(committedMaster);
		}
		catch (NoSuchCommittedMasterException noSuchEntityException) {
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
	protected CommittedMaster removeImpl(CommittedMaster committedMaster) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(committedMaster)) {
				committedMaster = (CommittedMaster)session.get(
					CommittedMasterImpl.class,
					committedMaster.getPrimaryKeyObj());
			}

			if (committedMaster != null) {
				session.delete(committedMaster);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (committedMaster != null) {
			clearCache(committedMaster);
		}

		return committedMaster;
	}

	@Override
	public CommittedMaster updateImpl(CommittedMaster committedMaster) {
		boolean isNew = committedMaster.isNew();

		if (!(committedMaster instanceof CommittedMasterModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(committedMaster.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					committedMaster);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in committedMaster proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommittedMaster implementation " +
					committedMaster.getClass());
		}

		CommittedMasterModelImpl committedMasterModelImpl =
			(CommittedMasterModelImpl)committedMaster;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(committedMaster);
			}
			else {
				committedMaster = (CommittedMaster)session.merge(
					committedMaster);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			CommittedMasterImpl.class, committedMasterModelImpl, false, true);

		if (isNew) {
			committedMaster.setNew(false);
		}

		committedMaster.resetOriginalValues();

		return committedMaster;
	}

	/**
	 * Returns the committed master with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the committed master
	 * @return the committed master
	 * @throws NoSuchCommittedMasterException if a committed master with the primary key could not be found
	 */
	@Override
	public CommittedMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCommittedMasterException {

		CommittedMaster committedMaster = fetchByPrimaryKey(primaryKey);

		if (committedMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCommittedMasterException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return committedMaster;
	}

	/**
	 * Returns the committed master with the primary key or throws a <code>NoSuchCommittedMasterException</code> if it could not be found.
	 *
	 * @param committedMasterId the primary key of the committed master
	 * @return the committed master
	 * @throws NoSuchCommittedMasterException if a committed master with the primary key could not be found
	 */
	@Override
	public CommittedMaster findByPrimaryKey(long committedMasterId)
		throws NoSuchCommittedMasterException {

		return findByPrimaryKey((Serializable)committedMasterId);
	}

	/**
	 * Returns the committed master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param committedMasterId the primary key of the committed master
	 * @return the committed master, or <code>null</code> if a committed master with the primary key could not be found
	 */
	@Override
	public CommittedMaster fetchByPrimaryKey(long committedMasterId) {
		return fetchByPrimaryKey((Serializable)committedMasterId);
	}

	/**
	 * Returns all the committed masters.
	 *
	 * @return the committed masters
	 */
	@Override
	public List<CommittedMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the committed masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommittedMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of committed masters
	 * @param end the upper bound of the range of committed masters (not inclusive)
	 * @return the range of committed masters
	 */
	@Override
	public List<CommittedMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the committed masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommittedMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of committed masters
	 * @param end the upper bound of the range of committed masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of committed masters
	 */
	@Override
	public List<CommittedMaster> findAll(
		int start, int end,
		OrderByComparator<CommittedMaster> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the committed masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommittedMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of committed masters
	 * @param end the upper bound of the range of committed masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of committed masters
	 */
	@Override
	public List<CommittedMaster> findAll(
		int start, int end,
		OrderByComparator<CommittedMaster> orderByComparator,
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

		List<CommittedMaster> list = null;

		if (useFinderCache) {
			list = (List<CommittedMaster>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_COMMITTEDMASTER);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_COMMITTEDMASTER;

				sql = sql.concat(CommittedMasterModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<CommittedMaster>)QueryUtil.list(
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
	 * Removes all the committed masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommittedMaster committedMaster : findAll()) {
			remove(committedMaster);
		}
	}

	/**
	 * Returns the number of committed masters.
	 *
	 * @return the number of committed masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_COMMITTEDMASTER);

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
		return "committedMasterId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_COMMITTEDMASTER;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CommittedMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the committed master persistence.
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

		CommittedMasterUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		CommittedMasterUtil.setPersistence(null);

		entityCache.removeCache(CommittedMasterImpl.class.getName());
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

	private static final String _SQL_SELECT_COMMITTEDMASTER =
		"SELECT committedMaster FROM CommittedMaster committedMaster";

	private static final String _SQL_SELECT_COMMITTEDMASTER_WHERE =
		"SELECT committedMaster FROM CommittedMaster committedMaster WHERE ";

	private static final String _SQL_COUNT_COMMITTEDMASTER =
		"SELECT COUNT(committedMaster) FROM CommittedMaster committedMaster";

	private static final String _SQL_COUNT_COMMITTEDMASTER_WHERE =
		"SELECT COUNT(committedMaster) FROM CommittedMaster committedMaster WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "committedMaster.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CommittedMaster exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CommittedMaster exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CommittedMasterPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}