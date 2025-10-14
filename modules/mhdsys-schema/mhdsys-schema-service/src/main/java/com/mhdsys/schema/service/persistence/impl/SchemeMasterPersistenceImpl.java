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

import com.mhdsys.schema.exception.NoSuchSchemeMasterException;
import com.mhdsys.schema.model.SchemeMaster;
import com.mhdsys.schema.model.SchemeMasterTable;
import com.mhdsys.schema.model.impl.SchemeMasterImpl;
import com.mhdsys.schema.model.impl.SchemeMasterModelImpl;
import com.mhdsys.schema.service.persistence.SchemeMasterPersistence;
import com.mhdsys.schema.service.persistence.SchemeMasterUtil;
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
 * The persistence implementation for the scheme master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = SchemeMasterPersistence.class)
public class SchemeMasterPersistenceImpl
	extends BasePersistenceImpl<SchemeMaster>
	implements SchemeMasterPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SchemeMasterUtil</code> to access the scheme master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SchemeMasterImpl.class.getName();

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
	 * Returns all the scheme masters where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @return the matching scheme masters
	 */
	@Override
	public List<SchemeMaster> findByActiveState(boolean isActive) {
		return findByActiveState(
			isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the scheme masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchemeMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of scheme masters
	 * @param end the upper bound of the range of scheme masters (not inclusive)
	 * @return the range of matching scheme masters
	 */
	@Override
	public List<SchemeMaster> findByActiveState(
		boolean isActive, int start, int end) {

		return findByActiveState(isActive, start, end, null);
	}

	/**
	 * Returns an ordered range of all the scheme masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchemeMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of scheme masters
	 * @param end the upper bound of the range of scheme masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching scheme masters
	 */
	@Override
	public List<SchemeMaster> findByActiveState(
		boolean isActive, int start, int end,
		OrderByComparator<SchemeMaster> orderByComparator) {

		return findByActiveState(isActive, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the scheme masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchemeMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of scheme masters
	 * @param end the upper bound of the range of scheme masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching scheme masters
	 */
	@Override
	public List<SchemeMaster> findByActiveState(
		boolean isActive, int start, int end,
		OrderByComparator<SchemeMaster> orderByComparator,
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

		List<SchemeMaster> list = null;

		if (useFinderCache) {
			list = (List<SchemeMaster>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SchemeMaster schemeMaster : list) {
					if (isActive != schemeMaster.isIsActive()) {
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

			sb.append(_SQL_SELECT_SCHEMEMASTER_WHERE);

			sb.append(_FINDER_COLUMN_ACTIVESTATE_ISACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SchemeMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(isActive);

				list = (List<SchemeMaster>)QueryUtil.list(
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
	 * Returns the first scheme master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching scheme master
	 * @throws NoSuchSchemeMasterException if a matching scheme master could not be found
	 */
	@Override
	public SchemeMaster findByActiveState_First(
			boolean isActive, OrderByComparator<SchemeMaster> orderByComparator)
		throws NoSuchSchemeMasterException {

		SchemeMaster schemeMaster = fetchByActiveState_First(
			isActive, orderByComparator);

		if (schemeMaster != null) {
			return schemeMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isActive=");
		sb.append(isActive);

		sb.append("}");

		throw new NoSuchSchemeMasterException(sb.toString());
	}

	/**
	 * Returns the first scheme master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching scheme master, or <code>null</code> if a matching scheme master could not be found
	 */
	@Override
	public SchemeMaster fetchByActiveState_First(
		boolean isActive, OrderByComparator<SchemeMaster> orderByComparator) {

		List<SchemeMaster> list = findByActiveState(
			isActive, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last scheme master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching scheme master
	 * @throws NoSuchSchemeMasterException if a matching scheme master could not be found
	 */
	@Override
	public SchemeMaster findByActiveState_Last(
			boolean isActive, OrderByComparator<SchemeMaster> orderByComparator)
		throws NoSuchSchemeMasterException {

		SchemeMaster schemeMaster = fetchByActiveState_Last(
			isActive, orderByComparator);

		if (schemeMaster != null) {
			return schemeMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isActive=");
		sb.append(isActive);

		sb.append("}");

		throw new NoSuchSchemeMasterException(sb.toString());
	}

	/**
	 * Returns the last scheme master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching scheme master, or <code>null</code> if a matching scheme master could not be found
	 */
	@Override
	public SchemeMaster fetchByActiveState_Last(
		boolean isActive, OrderByComparator<SchemeMaster> orderByComparator) {

		int count = countByActiveState(isActive);

		if (count == 0) {
			return null;
		}

		List<SchemeMaster> list = findByActiveState(
			isActive, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the scheme masters before and after the current scheme master in the ordered set where isActive = &#63;.
	 *
	 * @param schemeMasterId the primary key of the current scheme master
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next scheme master
	 * @throws NoSuchSchemeMasterException if a scheme master with the primary key could not be found
	 */
	@Override
	public SchemeMaster[] findByActiveState_PrevAndNext(
			long schemeMasterId, boolean isActive,
			OrderByComparator<SchemeMaster> orderByComparator)
		throws NoSuchSchemeMasterException {

		SchemeMaster schemeMaster = findByPrimaryKey(schemeMasterId);

		Session session = null;

		try {
			session = openSession();

			SchemeMaster[] array = new SchemeMasterImpl[3];

			array[0] = getByActiveState_PrevAndNext(
				session, schemeMaster, isActive, orderByComparator, true);

			array[1] = schemeMaster;

			array[2] = getByActiveState_PrevAndNext(
				session, schemeMaster, isActive, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected SchemeMaster getByActiveState_PrevAndNext(
		Session session, SchemeMaster schemeMaster, boolean isActive,
		OrderByComparator<SchemeMaster> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_SCHEMEMASTER_WHERE);

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
			sb.append(SchemeMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(isActive);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(schemeMaster)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SchemeMaster> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the scheme masters where isActive = &#63; from the database.
	 *
	 * @param isActive the is active
	 */
	@Override
	public void removeByActiveState(boolean isActive) {
		for (SchemeMaster schemeMaster :
				findByActiveState(
					isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(schemeMaster);
		}
	}

	/**
	 * Returns the number of scheme masters where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @return the number of matching scheme masters
	 */
	@Override
	public int countByActiveState(boolean isActive) {
		FinderPath finderPath = _finderPathCountByActiveState;

		Object[] finderArgs = new Object[] {isActive};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SCHEMEMASTER_WHERE);

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
		"schemeMaster.isActive = ?";

	public SchemeMasterPersistenceImpl() {
		setModelClass(SchemeMaster.class);

		setModelImplClass(SchemeMasterImpl.class);
		setModelPKClass(long.class);

		setTable(SchemeMasterTable.INSTANCE);
	}

	/**
	 * Caches the scheme master in the entity cache if it is enabled.
	 *
	 * @param schemeMaster the scheme master
	 */
	@Override
	public void cacheResult(SchemeMaster schemeMaster) {
		entityCache.putResult(
			SchemeMasterImpl.class, schemeMaster.getPrimaryKey(), schemeMaster);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the scheme masters in the entity cache if it is enabled.
	 *
	 * @param schemeMasters the scheme masters
	 */
	@Override
	public void cacheResult(List<SchemeMaster> schemeMasters) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (schemeMasters.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (SchemeMaster schemeMaster : schemeMasters) {
			if (entityCache.getResult(
					SchemeMasterImpl.class, schemeMaster.getPrimaryKey()) ==
						null) {

				cacheResult(schemeMaster);
			}
		}
	}

	/**
	 * Clears the cache for all scheme masters.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SchemeMasterImpl.class);

		finderCache.clearCache(SchemeMasterImpl.class);
	}

	/**
	 * Clears the cache for the scheme master.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SchemeMaster schemeMaster) {
		entityCache.removeResult(SchemeMasterImpl.class, schemeMaster);
	}

	@Override
	public void clearCache(List<SchemeMaster> schemeMasters) {
		for (SchemeMaster schemeMaster : schemeMasters) {
			entityCache.removeResult(SchemeMasterImpl.class, schemeMaster);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(SchemeMasterImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(SchemeMasterImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new scheme master with the primary key. Does not add the scheme master to the database.
	 *
	 * @param schemeMasterId the primary key for the new scheme master
	 * @return the new scheme master
	 */
	@Override
	public SchemeMaster create(long schemeMasterId) {
		SchemeMaster schemeMaster = new SchemeMasterImpl();

		schemeMaster.setNew(true);
		schemeMaster.setPrimaryKey(schemeMasterId);

		return schemeMaster;
	}

	/**
	 * Removes the scheme master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param schemeMasterId the primary key of the scheme master
	 * @return the scheme master that was removed
	 * @throws NoSuchSchemeMasterException if a scheme master with the primary key could not be found
	 */
	@Override
	public SchemeMaster remove(long schemeMasterId)
		throws NoSuchSchemeMasterException {

		return remove((Serializable)schemeMasterId);
	}

	/**
	 * Removes the scheme master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the scheme master
	 * @return the scheme master that was removed
	 * @throws NoSuchSchemeMasterException if a scheme master with the primary key could not be found
	 */
	@Override
	public SchemeMaster remove(Serializable primaryKey)
		throws NoSuchSchemeMasterException {

		Session session = null;

		try {
			session = openSession();

			SchemeMaster schemeMaster = (SchemeMaster)session.get(
				SchemeMasterImpl.class, primaryKey);

			if (schemeMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSchemeMasterException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(schemeMaster);
		}
		catch (NoSuchSchemeMasterException noSuchEntityException) {
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
	protected SchemeMaster removeImpl(SchemeMaster schemeMaster) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(schemeMaster)) {
				schemeMaster = (SchemeMaster)session.get(
					SchemeMasterImpl.class, schemeMaster.getPrimaryKeyObj());
			}

			if (schemeMaster != null) {
				session.delete(schemeMaster);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (schemeMaster != null) {
			clearCache(schemeMaster);
		}

		return schemeMaster;
	}

	@Override
	public SchemeMaster updateImpl(SchemeMaster schemeMaster) {
		boolean isNew = schemeMaster.isNew();

		if (!(schemeMaster instanceof SchemeMasterModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(schemeMaster.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					schemeMaster);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in schemeMaster proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SchemeMaster implementation " +
					schemeMaster.getClass());
		}

		SchemeMasterModelImpl schemeMasterModelImpl =
			(SchemeMasterModelImpl)schemeMaster;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(schemeMaster);
			}
			else {
				schemeMaster = (SchemeMaster)session.merge(schemeMaster);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			SchemeMasterImpl.class, schemeMasterModelImpl, false, true);

		if (isNew) {
			schemeMaster.setNew(false);
		}

		schemeMaster.resetOriginalValues();

		return schemeMaster;
	}

	/**
	 * Returns the scheme master with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the scheme master
	 * @return the scheme master
	 * @throws NoSuchSchemeMasterException if a scheme master with the primary key could not be found
	 */
	@Override
	public SchemeMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSchemeMasterException {

		SchemeMaster schemeMaster = fetchByPrimaryKey(primaryKey);

		if (schemeMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSchemeMasterException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return schemeMaster;
	}

	/**
	 * Returns the scheme master with the primary key or throws a <code>NoSuchSchemeMasterException</code> if it could not be found.
	 *
	 * @param schemeMasterId the primary key of the scheme master
	 * @return the scheme master
	 * @throws NoSuchSchemeMasterException if a scheme master with the primary key could not be found
	 */
	@Override
	public SchemeMaster findByPrimaryKey(long schemeMasterId)
		throws NoSuchSchemeMasterException {

		return findByPrimaryKey((Serializable)schemeMasterId);
	}

	/**
	 * Returns the scheme master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param schemeMasterId the primary key of the scheme master
	 * @return the scheme master, or <code>null</code> if a scheme master with the primary key could not be found
	 */
	@Override
	public SchemeMaster fetchByPrimaryKey(long schemeMasterId) {
		return fetchByPrimaryKey((Serializable)schemeMasterId);
	}

	/**
	 * Returns all the scheme masters.
	 *
	 * @return the scheme masters
	 */
	@Override
	public List<SchemeMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the scheme masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchemeMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of scheme masters
	 * @param end the upper bound of the range of scheme masters (not inclusive)
	 * @return the range of scheme masters
	 */
	@Override
	public List<SchemeMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the scheme masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchemeMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of scheme masters
	 * @param end the upper bound of the range of scheme masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of scheme masters
	 */
	@Override
	public List<SchemeMaster> findAll(
		int start, int end, OrderByComparator<SchemeMaster> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the scheme masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchemeMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of scheme masters
	 * @param end the upper bound of the range of scheme masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of scheme masters
	 */
	@Override
	public List<SchemeMaster> findAll(
		int start, int end, OrderByComparator<SchemeMaster> orderByComparator,
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

		List<SchemeMaster> list = null;

		if (useFinderCache) {
			list = (List<SchemeMaster>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SCHEMEMASTER);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SCHEMEMASTER;

				sql = sql.concat(SchemeMasterModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<SchemeMaster>)QueryUtil.list(
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
	 * Removes all the scheme masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SchemeMaster schemeMaster : findAll()) {
			remove(schemeMaster);
		}
	}

	/**
	 * Returns the number of scheme masters.
	 *
	 * @return the number of scheme masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SCHEMEMASTER);

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
		return "schemeMasterId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SCHEMEMASTER;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SchemeMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the scheme master persistence.
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

		SchemeMasterUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		SchemeMasterUtil.setPersistence(null);

		entityCache.removeCache(SchemeMasterImpl.class.getName());
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

	private static final String _SQL_SELECT_SCHEMEMASTER =
		"SELECT schemeMaster FROM SchemeMaster schemeMaster";

	private static final String _SQL_SELECT_SCHEMEMASTER_WHERE =
		"SELECT schemeMaster FROM SchemeMaster schemeMaster WHERE ";

	private static final String _SQL_COUNT_SCHEMEMASTER =
		"SELECT COUNT(schemeMaster) FROM SchemeMaster schemeMaster";

	private static final String _SQL_COUNT_SCHEMEMASTER_WHERE =
		"SELECT COUNT(schemeMaster) FROM SchemeMaster schemeMaster WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "schemeMaster.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SchemeMaster exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No SchemeMaster exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		SchemeMasterPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}