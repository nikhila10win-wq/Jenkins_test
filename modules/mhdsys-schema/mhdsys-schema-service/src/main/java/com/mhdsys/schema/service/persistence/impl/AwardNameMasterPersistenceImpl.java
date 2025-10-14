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

import com.mhdsys.schema.exception.NoSuchAwardNameMasterException;
import com.mhdsys.schema.model.AwardNameMaster;
import com.mhdsys.schema.model.AwardNameMasterTable;
import com.mhdsys.schema.model.impl.AwardNameMasterImpl;
import com.mhdsys.schema.model.impl.AwardNameMasterModelImpl;
import com.mhdsys.schema.service.persistence.AwardNameMasterPersistence;
import com.mhdsys.schema.service.persistence.AwardNameMasterUtil;
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
 * The persistence implementation for the award name master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = AwardNameMasterPersistence.class)
public class AwardNameMasterPersistenceImpl
	extends BasePersistenceImpl<AwardNameMaster>
	implements AwardNameMasterPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>AwardNameMasterUtil</code> to access the award name master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		AwardNameMasterImpl.class.getName();

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
	 * Returns all the award name masters where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @return the matching award name masters
	 */
	@Override
	public List<AwardNameMaster> findByActiveState(boolean isActive) {
		return findByActiveState(
			isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the award name masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardNameMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of award name masters
	 * @param end the upper bound of the range of award name masters (not inclusive)
	 * @return the range of matching award name masters
	 */
	@Override
	public List<AwardNameMaster> findByActiveState(
		boolean isActive, int start, int end) {

		return findByActiveState(isActive, start, end, null);
	}

	/**
	 * Returns an ordered range of all the award name masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardNameMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of award name masters
	 * @param end the upper bound of the range of award name masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching award name masters
	 */
	@Override
	public List<AwardNameMaster> findByActiveState(
		boolean isActive, int start, int end,
		OrderByComparator<AwardNameMaster> orderByComparator) {

		return findByActiveState(isActive, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the award name masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardNameMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of award name masters
	 * @param end the upper bound of the range of award name masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching award name masters
	 */
	@Override
	public List<AwardNameMaster> findByActiveState(
		boolean isActive, int start, int end,
		OrderByComparator<AwardNameMaster> orderByComparator,
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

		List<AwardNameMaster> list = null;

		if (useFinderCache) {
			list = (List<AwardNameMaster>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AwardNameMaster awardNameMaster : list) {
					if (isActive != awardNameMaster.isIsActive()) {
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

			sb.append(_SQL_SELECT_AWARDNAMEMASTER_WHERE);

			sb.append(_FINDER_COLUMN_ACTIVESTATE_ISACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(AwardNameMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(isActive);

				list = (List<AwardNameMaster>)QueryUtil.list(
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
	 * Returns the first award name master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching award name master
	 * @throws NoSuchAwardNameMasterException if a matching award name master could not be found
	 */
	@Override
	public AwardNameMaster findByActiveState_First(
			boolean isActive,
			OrderByComparator<AwardNameMaster> orderByComparator)
		throws NoSuchAwardNameMasterException {

		AwardNameMaster awardNameMaster = fetchByActiveState_First(
			isActive, orderByComparator);

		if (awardNameMaster != null) {
			return awardNameMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isActive=");
		sb.append(isActive);

		sb.append("}");

		throw new NoSuchAwardNameMasterException(sb.toString());
	}

	/**
	 * Returns the first award name master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching award name master, or <code>null</code> if a matching award name master could not be found
	 */
	@Override
	public AwardNameMaster fetchByActiveState_First(
		boolean isActive,
		OrderByComparator<AwardNameMaster> orderByComparator) {

		List<AwardNameMaster> list = findByActiveState(
			isActive, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last award name master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching award name master
	 * @throws NoSuchAwardNameMasterException if a matching award name master could not be found
	 */
	@Override
	public AwardNameMaster findByActiveState_Last(
			boolean isActive,
			OrderByComparator<AwardNameMaster> orderByComparator)
		throws NoSuchAwardNameMasterException {

		AwardNameMaster awardNameMaster = fetchByActiveState_Last(
			isActive, orderByComparator);

		if (awardNameMaster != null) {
			return awardNameMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isActive=");
		sb.append(isActive);

		sb.append("}");

		throw new NoSuchAwardNameMasterException(sb.toString());
	}

	/**
	 * Returns the last award name master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching award name master, or <code>null</code> if a matching award name master could not be found
	 */
	@Override
	public AwardNameMaster fetchByActiveState_Last(
		boolean isActive,
		OrderByComparator<AwardNameMaster> orderByComparator) {

		int count = countByActiveState(isActive);

		if (count == 0) {
			return null;
		}

		List<AwardNameMaster> list = findByActiveState(
			isActive, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the award name masters before and after the current award name master in the ordered set where isActive = &#63;.
	 *
	 * @param awardNameMasterId the primary key of the current award name master
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next award name master
	 * @throws NoSuchAwardNameMasterException if a award name master with the primary key could not be found
	 */
	@Override
	public AwardNameMaster[] findByActiveState_PrevAndNext(
			long awardNameMasterId, boolean isActive,
			OrderByComparator<AwardNameMaster> orderByComparator)
		throws NoSuchAwardNameMasterException {

		AwardNameMaster awardNameMaster = findByPrimaryKey(awardNameMasterId);

		Session session = null;

		try {
			session = openSession();

			AwardNameMaster[] array = new AwardNameMasterImpl[3];

			array[0] = getByActiveState_PrevAndNext(
				session, awardNameMaster, isActive, orderByComparator, true);

			array[1] = awardNameMaster;

			array[2] = getByActiveState_PrevAndNext(
				session, awardNameMaster, isActive, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected AwardNameMaster getByActiveState_PrevAndNext(
		Session session, AwardNameMaster awardNameMaster, boolean isActive,
		OrderByComparator<AwardNameMaster> orderByComparator,
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

		sb.append(_SQL_SELECT_AWARDNAMEMASTER_WHERE);

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
			sb.append(AwardNameMasterModelImpl.ORDER_BY_JPQL);
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
						awardNameMaster)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<AwardNameMaster> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the award name masters where isActive = &#63; from the database.
	 *
	 * @param isActive the is active
	 */
	@Override
	public void removeByActiveState(boolean isActive) {
		for (AwardNameMaster awardNameMaster :
				findByActiveState(
					isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(awardNameMaster);
		}
	}

	/**
	 * Returns the number of award name masters where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @return the number of matching award name masters
	 */
	@Override
	public int countByActiveState(boolean isActive) {
		FinderPath finderPath = _finderPathCountByActiveState;

		Object[] finderArgs = new Object[] {isActive};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_AWARDNAMEMASTER_WHERE);

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
		"awardNameMaster.isActive = ?";

	public AwardNameMasterPersistenceImpl() {
		setModelClass(AwardNameMaster.class);

		setModelImplClass(AwardNameMasterImpl.class);
		setModelPKClass(long.class);

		setTable(AwardNameMasterTable.INSTANCE);
	}

	/**
	 * Caches the award name master in the entity cache if it is enabled.
	 *
	 * @param awardNameMaster the award name master
	 */
	@Override
	public void cacheResult(AwardNameMaster awardNameMaster) {
		entityCache.putResult(
			AwardNameMasterImpl.class, awardNameMaster.getPrimaryKey(),
			awardNameMaster);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the award name masters in the entity cache if it is enabled.
	 *
	 * @param awardNameMasters the award name masters
	 */
	@Override
	public void cacheResult(List<AwardNameMaster> awardNameMasters) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (awardNameMasters.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (AwardNameMaster awardNameMaster : awardNameMasters) {
			if (entityCache.getResult(
					AwardNameMasterImpl.class,
					awardNameMaster.getPrimaryKey()) == null) {

				cacheResult(awardNameMaster);
			}
		}
	}

	/**
	 * Clears the cache for all award name masters.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AwardNameMasterImpl.class);

		finderCache.clearCache(AwardNameMasterImpl.class);
	}

	/**
	 * Clears the cache for the award name master.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AwardNameMaster awardNameMaster) {
		entityCache.removeResult(AwardNameMasterImpl.class, awardNameMaster);
	}

	@Override
	public void clearCache(List<AwardNameMaster> awardNameMasters) {
		for (AwardNameMaster awardNameMaster : awardNameMasters) {
			entityCache.removeResult(
				AwardNameMasterImpl.class, awardNameMaster);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(AwardNameMasterImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(AwardNameMasterImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new award name master with the primary key. Does not add the award name master to the database.
	 *
	 * @param awardNameMasterId the primary key for the new award name master
	 * @return the new award name master
	 */
	@Override
	public AwardNameMaster create(long awardNameMasterId) {
		AwardNameMaster awardNameMaster = new AwardNameMasterImpl();

		awardNameMaster.setNew(true);
		awardNameMaster.setPrimaryKey(awardNameMasterId);

		return awardNameMaster;
	}

	/**
	 * Removes the award name master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param awardNameMasterId the primary key of the award name master
	 * @return the award name master that was removed
	 * @throws NoSuchAwardNameMasterException if a award name master with the primary key could not be found
	 */
	@Override
	public AwardNameMaster remove(long awardNameMasterId)
		throws NoSuchAwardNameMasterException {

		return remove((Serializable)awardNameMasterId);
	}

	/**
	 * Removes the award name master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the award name master
	 * @return the award name master that was removed
	 * @throws NoSuchAwardNameMasterException if a award name master with the primary key could not be found
	 */
	@Override
	public AwardNameMaster remove(Serializable primaryKey)
		throws NoSuchAwardNameMasterException {

		Session session = null;

		try {
			session = openSession();

			AwardNameMaster awardNameMaster = (AwardNameMaster)session.get(
				AwardNameMasterImpl.class, primaryKey);

			if (awardNameMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAwardNameMasterException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(awardNameMaster);
		}
		catch (NoSuchAwardNameMasterException noSuchEntityException) {
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
	protected AwardNameMaster removeImpl(AwardNameMaster awardNameMaster) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(awardNameMaster)) {
				awardNameMaster = (AwardNameMaster)session.get(
					AwardNameMasterImpl.class,
					awardNameMaster.getPrimaryKeyObj());
			}

			if (awardNameMaster != null) {
				session.delete(awardNameMaster);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (awardNameMaster != null) {
			clearCache(awardNameMaster);
		}

		return awardNameMaster;
	}

	@Override
	public AwardNameMaster updateImpl(AwardNameMaster awardNameMaster) {
		boolean isNew = awardNameMaster.isNew();

		if (!(awardNameMaster instanceof AwardNameMasterModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(awardNameMaster.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					awardNameMaster);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in awardNameMaster proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom AwardNameMaster implementation " +
					awardNameMaster.getClass());
		}

		AwardNameMasterModelImpl awardNameMasterModelImpl =
			(AwardNameMasterModelImpl)awardNameMaster;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(awardNameMaster);
			}
			else {
				awardNameMaster = (AwardNameMaster)session.merge(
					awardNameMaster);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			AwardNameMasterImpl.class, awardNameMasterModelImpl, false, true);

		if (isNew) {
			awardNameMaster.setNew(false);
		}

		awardNameMaster.resetOriginalValues();

		return awardNameMaster;
	}

	/**
	 * Returns the award name master with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the award name master
	 * @return the award name master
	 * @throws NoSuchAwardNameMasterException if a award name master with the primary key could not be found
	 */
	@Override
	public AwardNameMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAwardNameMasterException {

		AwardNameMaster awardNameMaster = fetchByPrimaryKey(primaryKey);

		if (awardNameMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAwardNameMasterException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return awardNameMaster;
	}

	/**
	 * Returns the award name master with the primary key or throws a <code>NoSuchAwardNameMasterException</code> if it could not be found.
	 *
	 * @param awardNameMasterId the primary key of the award name master
	 * @return the award name master
	 * @throws NoSuchAwardNameMasterException if a award name master with the primary key could not be found
	 */
	@Override
	public AwardNameMaster findByPrimaryKey(long awardNameMasterId)
		throws NoSuchAwardNameMasterException {

		return findByPrimaryKey((Serializable)awardNameMasterId);
	}

	/**
	 * Returns the award name master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param awardNameMasterId the primary key of the award name master
	 * @return the award name master, or <code>null</code> if a award name master with the primary key could not be found
	 */
	@Override
	public AwardNameMaster fetchByPrimaryKey(long awardNameMasterId) {
		return fetchByPrimaryKey((Serializable)awardNameMasterId);
	}

	/**
	 * Returns all the award name masters.
	 *
	 * @return the award name masters
	 */
	@Override
	public List<AwardNameMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the award name masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardNameMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of award name masters
	 * @param end the upper bound of the range of award name masters (not inclusive)
	 * @return the range of award name masters
	 */
	@Override
	public List<AwardNameMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the award name masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardNameMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of award name masters
	 * @param end the upper bound of the range of award name masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of award name masters
	 */
	@Override
	public List<AwardNameMaster> findAll(
		int start, int end,
		OrderByComparator<AwardNameMaster> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the award name masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardNameMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of award name masters
	 * @param end the upper bound of the range of award name masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of award name masters
	 */
	@Override
	public List<AwardNameMaster> findAll(
		int start, int end,
		OrderByComparator<AwardNameMaster> orderByComparator,
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

		List<AwardNameMaster> list = null;

		if (useFinderCache) {
			list = (List<AwardNameMaster>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_AWARDNAMEMASTER);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_AWARDNAMEMASTER;

				sql = sql.concat(AwardNameMasterModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<AwardNameMaster>)QueryUtil.list(
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
	 * Removes all the award name masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AwardNameMaster awardNameMaster : findAll()) {
			remove(awardNameMaster);
		}
	}

	/**
	 * Returns the number of award name masters.
	 *
	 * @return the number of award name masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_AWARDNAMEMASTER);

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
		return "awardNameMasterId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_AWARDNAMEMASTER;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return AwardNameMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the award name master persistence.
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

		AwardNameMasterUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		AwardNameMasterUtil.setPersistence(null);

		entityCache.removeCache(AwardNameMasterImpl.class.getName());
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

	private static final String _SQL_SELECT_AWARDNAMEMASTER =
		"SELECT awardNameMaster FROM AwardNameMaster awardNameMaster";

	private static final String _SQL_SELECT_AWARDNAMEMASTER_WHERE =
		"SELECT awardNameMaster FROM AwardNameMaster awardNameMaster WHERE ";

	private static final String _SQL_COUNT_AWARDNAMEMASTER =
		"SELECT COUNT(awardNameMaster) FROM AwardNameMaster awardNameMaster";

	private static final String _SQL_COUNT_AWARDNAMEMASTER_WHERE =
		"SELECT COUNT(awardNameMaster) FROM AwardNameMaster awardNameMaster WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "awardNameMaster.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No AwardNameMaster exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No AwardNameMaster exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		AwardNameMasterPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}