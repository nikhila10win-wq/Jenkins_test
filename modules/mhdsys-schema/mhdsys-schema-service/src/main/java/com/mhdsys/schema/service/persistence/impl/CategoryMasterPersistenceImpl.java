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

import com.mhdsys.schema.exception.NoSuchCategoryMasterException;
import com.mhdsys.schema.model.CategoryMaster;
import com.mhdsys.schema.model.CategoryMasterTable;
import com.mhdsys.schema.model.impl.CategoryMasterImpl;
import com.mhdsys.schema.model.impl.CategoryMasterModelImpl;
import com.mhdsys.schema.service.persistence.CategoryMasterPersistence;
import com.mhdsys.schema.service.persistence.CategoryMasterUtil;
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
 * The persistence implementation for the category master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = CategoryMasterPersistence.class)
public class CategoryMasterPersistenceImpl
	extends BasePersistenceImpl<CategoryMaster>
	implements CategoryMasterPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CategoryMasterUtil</code> to access the category master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CategoryMasterImpl.class.getName();

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
	 * Returns all the category masters where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @return the matching category masters
	 */
	@Override
	public List<CategoryMaster> findByActiveState(boolean isActive) {
		return findByActiveState(
			isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the category masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CategoryMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of category masters
	 * @param end the upper bound of the range of category masters (not inclusive)
	 * @return the range of matching category masters
	 */
	@Override
	public List<CategoryMaster> findByActiveState(
		boolean isActive, int start, int end) {

		return findByActiveState(isActive, start, end, null);
	}

	/**
	 * Returns an ordered range of all the category masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CategoryMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of category masters
	 * @param end the upper bound of the range of category masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching category masters
	 */
	@Override
	public List<CategoryMaster> findByActiveState(
		boolean isActive, int start, int end,
		OrderByComparator<CategoryMaster> orderByComparator) {

		return findByActiveState(isActive, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the category masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CategoryMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of category masters
	 * @param end the upper bound of the range of category masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching category masters
	 */
	@Override
	public List<CategoryMaster> findByActiveState(
		boolean isActive, int start, int end,
		OrderByComparator<CategoryMaster> orderByComparator,
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

		List<CategoryMaster> list = null;

		if (useFinderCache) {
			list = (List<CategoryMaster>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CategoryMaster categoryMaster : list) {
					if (isActive != categoryMaster.isIsActive()) {
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

			sb.append(_SQL_SELECT_CATEGORYMASTER_WHERE);

			sb.append(_FINDER_COLUMN_ACTIVESTATE_ISACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CategoryMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(isActive);

				list = (List<CategoryMaster>)QueryUtil.list(
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
	 * Returns the first category master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching category master
	 * @throws NoSuchCategoryMasterException if a matching category master could not be found
	 */
	@Override
	public CategoryMaster findByActiveState_First(
			boolean isActive,
			OrderByComparator<CategoryMaster> orderByComparator)
		throws NoSuchCategoryMasterException {

		CategoryMaster categoryMaster = fetchByActiveState_First(
			isActive, orderByComparator);

		if (categoryMaster != null) {
			return categoryMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isActive=");
		sb.append(isActive);

		sb.append("}");

		throw new NoSuchCategoryMasterException(sb.toString());
	}

	/**
	 * Returns the first category master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching category master, or <code>null</code> if a matching category master could not be found
	 */
	@Override
	public CategoryMaster fetchByActiveState_First(
		boolean isActive, OrderByComparator<CategoryMaster> orderByComparator) {

		List<CategoryMaster> list = findByActiveState(
			isActive, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last category master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching category master
	 * @throws NoSuchCategoryMasterException if a matching category master could not be found
	 */
	@Override
	public CategoryMaster findByActiveState_Last(
			boolean isActive,
			OrderByComparator<CategoryMaster> orderByComparator)
		throws NoSuchCategoryMasterException {

		CategoryMaster categoryMaster = fetchByActiveState_Last(
			isActive, orderByComparator);

		if (categoryMaster != null) {
			return categoryMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isActive=");
		sb.append(isActive);

		sb.append("}");

		throw new NoSuchCategoryMasterException(sb.toString());
	}

	/**
	 * Returns the last category master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching category master, or <code>null</code> if a matching category master could not be found
	 */
	@Override
	public CategoryMaster fetchByActiveState_Last(
		boolean isActive, OrderByComparator<CategoryMaster> orderByComparator) {

		int count = countByActiveState(isActive);

		if (count == 0) {
			return null;
		}

		List<CategoryMaster> list = findByActiveState(
			isActive, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the category masters before and after the current category master in the ordered set where isActive = &#63;.
	 *
	 * @param categoryMasterId the primary key of the current category master
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next category master
	 * @throws NoSuchCategoryMasterException if a category master with the primary key could not be found
	 */
	@Override
	public CategoryMaster[] findByActiveState_PrevAndNext(
			long categoryMasterId, boolean isActive,
			OrderByComparator<CategoryMaster> orderByComparator)
		throws NoSuchCategoryMasterException {

		CategoryMaster categoryMaster = findByPrimaryKey(categoryMasterId);

		Session session = null;

		try {
			session = openSession();

			CategoryMaster[] array = new CategoryMasterImpl[3];

			array[0] = getByActiveState_PrevAndNext(
				session, categoryMaster, isActive, orderByComparator, true);

			array[1] = categoryMaster;

			array[2] = getByActiveState_PrevAndNext(
				session, categoryMaster, isActive, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CategoryMaster getByActiveState_PrevAndNext(
		Session session, CategoryMaster categoryMaster, boolean isActive,
		OrderByComparator<CategoryMaster> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_CATEGORYMASTER_WHERE);

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
			sb.append(CategoryMasterModelImpl.ORDER_BY_JPQL);
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
						categoryMaster)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CategoryMaster> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the category masters where isActive = &#63; from the database.
	 *
	 * @param isActive the is active
	 */
	@Override
	public void removeByActiveState(boolean isActive) {
		for (CategoryMaster categoryMaster :
				findByActiveState(
					isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(categoryMaster);
		}
	}

	/**
	 * Returns the number of category masters where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @return the number of matching category masters
	 */
	@Override
	public int countByActiveState(boolean isActive) {
		FinderPath finderPath = _finderPathCountByActiveState;

		Object[] finderArgs = new Object[] {isActive};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_CATEGORYMASTER_WHERE);

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
		"categoryMaster.isActive = ?";

	public CategoryMasterPersistenceImpl() {
		setModelClass(CategoryMaster.class);

		setModelImplClass(CategoryMasterImpl.class);
		setModelPKClass(long.class);

		setTable(CategoryMasterTable.INSTANCE);
	}

	/**
	 * Caches the category master in the entity cache if it is enabled.
	 *
	 * @param categoryMaster the category master
	 */
	@Override
	public void cacheResult(CategoryMaster categoryMaster) {
		entityCache.putResult(
			CategoryMasterImpl.class, categoryMaster.getPrimaryKey(),
			categoryMaster);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the category masters in the entity cache if it is enabled.
	 *
	 * @param categoryMasters the category masters
	 */
	@Override
	public void cacheResult(List<CategoryMaster> categoryMasters) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (categoryMasters.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (CategoryMaster categoryMaster : categoryMasters) {
			if (entityCache.getResult(
					CategoryMasterImpl.class, categoryMaster.getPrimaryKey()) ==
						null) {

				cacheResult(categoryMaster);
			}
		}
	}

	/**
	 * Clears the cache for all category masters.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CategoryMasterImpl.class);

		finderCache.clearCache(CategoryMasterImpl.class);
	}

	/**
	 * Clears the cache for the category master.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CategoryMaster categoryMaster) {
		entityCache.removeResult(CategoryMasterImpl.class, categoryMaster);
	}

	@Override
	public void clearCache(List<CategoryMaster> categoryMasters) {
		for (CategoryMaster categoryMaster : categoryMasters) {
			entityCache.removeResult(CategoryMasterImpl.class, categoryMaster);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(CategoryMasterImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(CategoryMasterImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new category master with the primary key. Does not add the category master to the database.
	 *
	 * @param categoryMasterId the primary key for the new category master
	 * @return the new category master
	 */
	@Override
	public CategoryMaster create(long categoryMasterId) {
		CategoryMaster categoryMaster = new CategoryMasterImpl();

		categoryMaster.setNew(true);
		categoryMaster.setPrimaryKey(categoryMasterId);

		return categoryMaster;
	}

	/**
	 * Removes the category master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param categoryMasterId the primary key of the category master
	 * @return the category master that was removed
	 * @throws NoSuchCategoryMasterException if a category master with the primary key could not be found
	 */
	@Override
	public CategoryMaster remove(long categoryMasterId)
		throws NoSuchCategoryMasterException {

		return remove((Serializable)categoryMasterId);
	}

	/**
	 * Removes the category master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the category master
	 * @return the category master that was removed
	 * @throws NoSuchCategoryMasterException if a category master with the primary key could not be found
	 */
	@Override
	public CategoryMaster remove(Serializable primaryKey)
		throws NoSuchCategoryMasterException {

		Session session = null;

		try {
			session = openSession();

			CategoryMaster categoryMaster = (CategoryMaster)session.get(
				CategoryMasterImpl.class, primaryKey);

			if (categoryMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCategoryMasterException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(categoryMaster);
		}
		catch (NoSuchCategoryMasterException noSuchEntityException) {
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
	protected CategoryMaster removeImpl(CategoryMaster categoryMaster) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(categoryMaster)) {
				categoryMaster = (CategoryMaster)session.get(
					CategoryMasterImpl.class,
					categoryMaster.getPrimaryKeyObj());
			}

			if (categoryMaster != null) {
				session.delete(categoryMaster);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (categoryMaster != null) {
			clearCache(categoryMaster);
		}

		return categoryMaster;
	}

	@Override
	public CategoryMaster updateImpl(CategoryMaster categoryMaster) {
		boolean isNew = categoryMaster.isNew();

		if (!(categoryMaster instanceof CategoryMasterModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(categoryMaster.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					categoryMaster);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in categoryMaster proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CategoryMaster implementation " +
					categoryMaster.getClass());
		}

		CategoryMasterModelImpl categoryMasterModelImpl =
			(CategoryMasterModelImpl)categoryMaster;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(categoryMaster);
			}
			else {
				categoryMaster = (CategoryMaster)session.merge(categoryMaster);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			CategoryMasterImpl.class, categoryMasterModelImpl, false, true);

		if (isNew) {
			categoryMaster.setNew(false);
		}

		categoryMaster.resetOriginalValues();

		return categoryMaster;
	}

	/**
	 * Returns the category master with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the category master
	 * @return the category master
	 * @throws NoSuchCategoryMasterException if a category master with the primary key could not be found
	 */
	@Override
	public CategoryMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCategoryMasterException {

		CategoryMaster categoryMaster = fetchByPrimaryKey(primaryKey);

		if (categoryMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCategoryMasterException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return categoryMaster;
	}

	/**
	 * Returns the category master with the primary key or throws a <code>NoSuchCategoryMasterException</code> if it could not be found.
	 *
	 * @param categoryMasterId the primary key of the category master
	 * @return the category master
	 * @throws NoSuchCategoryMasterException if a category master with the primary key could not be found
	 */
	@Override
	public CategoryMaster findByPrimaryKey(long categoryMasterId)
		throws NoSuchCategoryMasterException {

		return findByPrimaryKey((Serializable)categoryMasterId);
	}

	/**
	 * Returns the category master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param categoryMasterId the primary key of the category master
	 * @return the category master, or <code>null</code> if a category master with the primary key could not be found
	 */
	@Override
	public CategoryMaster fetchByPrimaryKey(long categoryMasterId) {
		return fetchByPrimaryKey((Serializable)categoryMasterId);
	}

	/**
	 * Returns all the category masters.
	 *
	 * @return the category masters
	 */
	@Override
	public List<CategoryMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the category masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CategoryMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of category masters
	 * @param end the upper bound of the range of category masters (not inclusive)
	 * @return the range of category masters
	 */
	@Override
	public List<CategoryMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the category masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CategoryMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of category masters
	 * @param end the upper bound of the range of category masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of category masters
	 */
	@Override
	public List<CategoryMaster> findAll(
		int start, int end,
		OrderByComparator<CategoryMaster> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the category masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CategoryMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of category masters
	 * @param end the upper bound of the range of category masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of category masters
	 */
	@Override
	public List<CategoryMaster> findAll(
		int start, int end, OrderByComparator<CategoryMaster> orderByComparator,
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

		List<CategoryMaster> list = null;

		if (useFinderCache) {
			list = (List<CategoryMaster>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_CATEGORYMASTER);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_CATEGORYMASTER;

				sql = sql.concat(CategoryMasterModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<CategoryMaster>)QueryUtil.list(
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
	 * Removes all the category masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CategoryMaster categoryMaster : findAll()) {
			remove(categoryMaster);
		}
	}

	/**
	 * Returns the number of category masters.
	 *
	 * @return the number of category masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_CATEGORYMASTER);

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
		return "categoryMasterId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_CATEGORYMASTER;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CategoryMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the category master persistence.
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

		CategoryMasterUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		CategoryMasterUtil.setPersistence(null);

		entityCache.removeCache(CategoryMasterImpl.class.getName());
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

	private static final String _SQL_SELECT_CATEGORYMASTER =
		"SELECT categoryMaster FROM CategoryMaster categoryMaster";

	private static final String _SQL_SELECT_CATEGORYMASTER_WHERE =
		"SELECT categoryMaster FROM CategoryMaster categoryMaster WHERE ";

	private static final String _SQL_COUNT_CATEGORYMASTER =
		"SELECT COUNT(categoryMaster) FROM CategoryMaster categoryMaster";

	private static final String _SQL_COUNT_CATEGORYMASTER_WHERE =
		"SELECT COUNT(categoryMaster) FROM CategoryMaster categoryMaster WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "categoryMaster.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CategoryMaster exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CategoryMaster exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CategoryMasterPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}