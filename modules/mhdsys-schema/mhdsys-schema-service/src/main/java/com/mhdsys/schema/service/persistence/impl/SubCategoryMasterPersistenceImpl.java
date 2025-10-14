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
import com.liferay.portal.kernel.util.StringUtil;

import com.mhdsys.schema.exception.NoSuchSubCategoryMasterException;
import com.mhdsys.schema.model.SubCategoryMaster;
import com.mhdsys.schema.model.SubCategoryMasterTable;
import com.mhdsys.schema.model.impl.SubCategoryMasterImpl;
import com.mhdsys.schema.model.impl.SubCategoryMasterModelImpl;
import com.mhdsys.schema.service.persistence.SubCategoryMasterPersistence;
import com.mhdsys.schema.service.persistence.SubCategoryMasterUtil;
import com.mhdsys.schema.service.persistence.impl.constants.mhdsysPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
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
 * The persistence implementation for the sub category master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = SubCategoryMasterPersistence.class)
public class SubCategoryMasterPersistenceImpl
	extends BasePersistenceImpl<SubCategoryMaster>
	implements SubCategoryMasterPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SubCategoryMasterUtil</code> to access the sub category master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SubCategoryMasterImpl.class.getName();

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
	 * Returns all the sub category masters where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @return the matching sub category masters
	 */
	@Override
	public List<SubCategoryMaster> findByActiveState(boolean isActive) {
		return findByActiveState(
			isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sub category masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubCategoryMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of sub category masters
	 * @param end the upper bound of the range of sub category masters (not inclusive)
	 * @return the range of matching sub category masters
	 */
	@Override
	public List<SubCategoryMaster> findByActiveState(
		boolean isActive, int start, int end) {

		return findByActiveState(isActive, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sub category masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubCategoryMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of sub category masters
	 * @param end the upper bound of the range of sub category masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sub category masters
	 */
	@Override
	public List<SubCategoryMaster> findByActiveState(
		boolean isActive, int start, int end,
		OrderByComparator<SubCategoryMaster> orderByComparator) {

		return findByActiveState(isActive, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sub category masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubCategoryMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of sub category masters
	 * @param end the upper bound of the range of sub category masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching sub category masters
	 */
	@Override
	public List<SubCategoryMaster> findByActiveState(
		boolean isActive, int start, int end,
		OrderByComparator<SubCategoryMaster> orderByComparator,
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

		List<SubCategoryMaster> list = null;

		if (useFinderCache) {
			list = (List<SubCategoryMaster>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SubCategoryMaster subCategoryMaster : list) {
					if (isActive != subCategoryMaster.isIsActive()) {
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

			sb.append(_SQL_SELECT_SUBCATEGORYMASTER_WHERE);

			sb.append(_FINDER_COLUMN_ACTIVESTATE_ISACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SubCategoryMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(isActive);

				list = (List<SubCategoryMaster>)QueryUtil.list(
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
	 * Returns the first sub category master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sub category master
	 * @throws NoSuchSubCategoryMasterException if a matching sub category master could not be found
	 */
	@Override
	public SubCategoryMaster findByActiveState_First(
			boolean isActive,
			OrderByComparator<SubCategoryMaster> orderByComparator)
		throws NoSuchSubCategoryMasterException {

		SubCategoryMaster subCategoryMaster = fetchByActiveState_First(
			isActive, orderByComparator);

		if (subCategoryMaster != null) {
			return subCategoryMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isActive=");
		sb.append(isActive);

		sb.append("}");

		throw new NoSuchSubCategoryMasterException(sb.toString());
	}

	/**
	 * Returns the first sub category master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sub category master, or <code>null</code> if a matching sub category master could not be found
	 */
	@Override
	public SubCategoryMaster fetchByActiveState_First(
		boolean isActive,
		OrderByComparator<SubCategoryMaster> orderByComparator) {

		List<SubCategoryMaster> list = findByActiveState(
			isActive, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sub category master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sub category master
	 * @throws NoSuchSubCategoryMasterException if a matching sub category master could not be found
	 */
	@Override
	public SubCategoryMaster findByActiveState_Last(
			boolean isActive,
			OrderByComparator<SubCategoryMaster> orderByComparator)
		throws NoSuchSubCategoryMasterException {

		SubCategoryMaster subCategoryMaster = fetchByActiveState_Last(
			isActive, orderByComparator);

		if (subCategoryMaster != null) {
			return subCategoryMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isActive=");
		sb.append(isActive);

		sb.append("}");

		throw new NoSuchSubCategoryMasterException(sb.toString());
	}

	/**
	 * Returns the last sub category master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sub category master, or <code>null</code> if a matching sub category master could not be found
	 */
	@Override
	public SubCategoryMaster fetchByActiveState_Last(
		boolean isActive,
		OrderByComparator<SubCategoryMaster> orderByComparator) {

		int count = countByActiveState(isActive);

		if (count == 0) {
			return null;
		}

		List<SubCategoryMaster> list = findByActiveState(
			isActive, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sub category masters before and after the current sub category master in the ordered set where isActive = &#63;.
	 *
	 * @param subCategoryId the primary key of the current sub category master
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sub category master
	 * @throws NoSuchSubCategoryMasterException if a sub category master with the primary key could not be found
	 */
	@Override
	public SubCategoryMaster[] findByActiveState_PrevAndNext(
			long subCategoryId, boolean isActive,
			OrderByComparator<SubCategoryMaster> orderByComparator)
		throws NoSuchSubCategoryMasterException {

		SubCategoryMaster subCategoryMaster = findByPrimaryKey(subCategoryId);

		Session session = null;

		try {
			session = openSession();

			SubCategoryMaster[] array = new SubCategoryMasterImpl[3];

			array[0] = getByActiveState_PrevAndNext(
				session, subCategoryMaster, isActive, orderByComparator, true);

			array[1] = subCategoryMaster;

			array[2] = getByActiveState_PrevAndNext(
				session, subCategoryMaster, isActive, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected SubCategoryMaster getByActiveState_PrevAndNext(
		Session session, SubCategoryMaster subCategoryMaster, boolean isActive,
		OrderByComparator<SubCategoryMaster> orderByComparator,
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

		sb.append(_SQL_SELECT_SUBCATEGORYMASTER_WHERE);

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
			sb.append(SubCategoryMasterModelImpl.ORDER_BY_JPQL);
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
						subCategoryMaster)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SubCategoryMaster> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sub category masters where isActive = &#63; from the database.
	 *
	 * @param isActive the is active
	 */
	@Override
	public void removeByActiveState(boolean isActive) {
		for (SubCategoryMaster subCategoryMaster :
				findByActiveState(
					isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(subCategoryMaster);
		}
	}

	/**
	 * Returns the number of sub category masters where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @return the number of matching sub category masters
	 */
	@Override
	public int countByActiveState(boolean isActive) {
		FinderPath finderPath = _finderPathCountByActiveState;

		Object[] finderArgs = new Object[] {isActive};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SUBCATEGORYMASTER_WHERE);

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
		"subCategoryMaster.isActive = ?";

	private FinderPath _finderPathWithPaginationFindByCategoryMasterId;
	private FinderPath _finderPathWithoutPaginationFindByCategoryMasterId;
	private FinderPath _finderPathCountByCategoryMasterId;

	/**
	 * Returns all the sub category masters where categoryMasterId = &#63;.
	 *
	 * @param categoryMasterId the category master ID
	 * @return the matching sub category masters
	 */
	@Override
	public List<SubCategoryMaster> findByCategoryMasterId(
		long categoryMasterId) {

		return findByCategoryMasterId(
			categoryMasterId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sub category masters where categoryMasterId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubCategoryMasterModelImpl</code>.
	 * </p>
	 *
	 * @param categoryMasterId the category master ID
	 * @param start the lower bound of the range of sub category masters
	 * @param end the upper bound of the range of sub category masters (not inclusive)
	 * @return the range of matching sub category masters
	 */
	@Override
	public List<SubCategoryMaster> findByCategoryMasterId(
		long categoryMasterId, int start, int end) {

		return findByCategoryMasterId(categoryMasterId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sub category masters where categoryMasterId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubCategoryMasterModelImpl</code>.
	 * </p>
	 *
	 * @param categoryMasterId the category master ID
	 * @param start the lower bound of the range of sub category masters
	 * @param end the upper bound of the range of sub category masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sub category masters
	 */
	@Override
	public List<SubCategoryMaster> findByCategoryMasterId(
		long categoryMasterId, int start, int end,
		OrderByComparator<SubCategoryMaster> orderByComparator) {

		return findByCategoryMasterId(
			categoryMasterId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sub category masters where categoryMasterId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubCategoryMasterModelImpl</code>.
	 * </p>
	 *
	 * @param categoryMasterId the category master ID
	 * @param start the lower bound of the range of sub category masters
	 * @param end the upper bound of the range of sub category masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching sub category masters
	 */
	@Override
	public List<SubCategoryMaster> findByCategoryMasterId(
		long categoryMasterId, int start, int end,
		OrderByComparator<SubCategoryMaster> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByCategoryMasterId;
				finderArgs = new Object[] {categoryMasterId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByCategoryMasterId;
			finderArgs = new Object[] {
				categoryMasterId, start, end, orderByComparator
			};
		}

		List<SubCategoryMaster> list = null;

		if (useFinderCache) {
			list = (List<SubCategoryMaster>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SubCategoryMaster subCategoryMaster : list) {
					if (categoryMasterId !=
							subCategoryMaster.getCategoryMasterId()) {

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

			sb.append(_SQL_SELECT_SUBCATEGORYMASTER_WHERE);

			sb.append(_FINDER_COLUMN_CATEGORYMASTERID_CATEGORYMASTERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SubCategoryMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(categoryMasterId);

				list = (List<SubCategoryMaster>)QueryUtil.list(
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
	 * Returns the first sub category master in the ordered set where categoryMasterId = &#63;.
	 *
	 * @param categoryMasterId the category master ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sub category master
	 * @throws NoSuchSubCategoryMasterException if a matching sub category master could not be found
	 */
	@Override
	public SubCategoryMaster findByCategoryMasterId_First(
			long categoryMasterId,
			OrderByComparator<SubCategoryMaster> orderByComparator)
		throws NoSuchSubCategoryMasterException {

		SubCategoryMaster subCategoryMaster = fetchByCategoryMasterId_First(
			categoryMasterId, orderByComparator);

		if (subCategoryMaster != null) {
			return subCategoryMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("categoryMasterId=");
		sb.append(categoryMasterId);

		sb.append("}");

		throw new NoSuchSubCategoryMasterException(sb.toString());
	}

	/**
	 * Returns the first sub category master in the ordered set where categoryMasterId = &#63;.
	 *
	 * @param categoryMasterId the category master ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sub category master, or <code>null</code> if a matching sub category master could not be found
	 */
	@Override
	public SubCategoryMaster fetchByCategoryMasterId_First(
		long categoryMasterId,
		OrderByComparator<SubCategoryMaster> orderByComparator) {

		List<SubCategoryMaster> list = findByCategoryMasterId(
			categoryMasterId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sub category master in the ordered set where categoryMasterId = &#63;.
	 *
	 * @param categoryMasterId the category master ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sub category master
	 * @throws NoSuchSubCategoryMasterException if a matching sub category master could not be found
	 */
	@Override
	public SubCategoryMaster findByCategoryMasterId_Last(
			long categoryMasterId,
			OrderByComparator<SubCategoryMaster> orderByComparator)
		throws NoSuchSubCategoryMasterException {

		SubCategoryMaster subCategoryMaster = fetchByCategoryMasterId_Last(
			categoryMasterId, orderByComparator);

		if (subCategoryMaster != null) {
			return subCategoryMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("categoryMasterId=");
		sb.append(categoryMasterId);

		sb.append("}");

		throw new NoSuchSubCategoryMasterException(sb.toString());
	}

	/**
	 * Returns the last sub category master in the ordered set where categoryMasterId = &#63;.
	 *
	 * @param categoryMasterId the category master ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sub category master, or <code>null</code> if a matching sub category master could not be found
	 */
	@Override
	public SubCategoryMaster fetchByCategoryMasterId_Last(
		long categoryMasterId,
		OrderByComparator<SubCategoryMaster> orderByComparator) {

		int count = countByCategoryMasterId(categoryMasterId);

		if (count == 0) {
			return null;
		}

		List<SubCategoryMaster> list = findByCategoryMasterId(
			categoryMasterId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sub category masters before and after the current sub category master in the ordered set where categoryMasterId = &#63;.
	 *
	 * @param subCategoryId the primary key of the current sub category master
	 * @param categoryMasterId the category master ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sub category master
	 * @throws NoSuchSubCategoryMasterException if a sub category master with the primary key could not be found
	 */
	@Override
	public SubCategoryMaster[] findByCategoryMasterId_PrevAndNext(
			long subCategoryId, long categoryMasterId,
			OrderByComparator<SubCategoryMaster> orderByComparator)
		throws NoSuchSubCategoryMasterException {

		SubCategoryMaster subCategoryMaster = findByPrimaryKey(subCategoryId);

		Session session = null;

		try {
			session = openSession();

			SubCategoryMaster[] array = new SubCategoryMasterImpl[3];

			array[0] = getByCategoryMasterId_PrevAndNext(
				session, subCategoryMaster, categoryMasterId, orderByComparator,
				true);

			array[1] = subCategoryMaster;

			array[2] = getByCategoryMasterId_PrevAndNext(
				session, subCategoryMaster, categoryMasterId, orderByComparator,
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

	protected SubCategoryMaster getByCategoryMasterId_PrevAndNext(
		Session session, SubCategoryMaster subCategoryMaster,
		long categoryMasterId,
		OrderByComparator<SubCategoryMaster> orderByComparator,
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

		sb.append(_SQL_SELECT_SUBCATEGORYMASTER_WHERE);

		sb.append(_FINDER_COLUMN_CATEGORYMASTERID_CATEGORYMASTERID_2);

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
			sb.append(SubCategoryMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(categoryMasterId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						subCategoryMaster)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SubCategoryMaster> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sub category masters where categoryMasterId = &#63; from the database.
	 *
	 * @param categoryMasterId the category master ID
	 */
	@Override
	public void removeByCategoryMasterId(long categoryMasterId) {
		for (SubCategoryMaster subCategoryMaster :
				findByCategoryMasterId(
					categoryMasterId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(subCategoryMaster);
		}
	}

	/**
	 * Returns the number of sub category masters where categoryMasterId = &#63;.
	 *
	 * @param categoryMasterId the category master ID
	 * @return the number of matching sub category masters
	 */
	@Override
	public int countByCategoryMasterId(long categoryMasterId) {
		FinderPath finderPath = _finderPathCountByCategoryMasterId;

		Object[] finderArgs = new Object[] {categoryMasterId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SUBCATEGORYMASTER_WHERE);

			sb.append(_FINDER_COLUMN_CATEGORYMASTERID_CATEGORYMASTERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(categoryMasterId);

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
		_FINDER_COLUMN_CATEGORYMASTERID_CATEGORYMASTERID_2 =
			"subCategoryMaster.categoryMasterId = ?";

	private FinderPath _finderPathFetchByname;
	private FinderPath _finderPathCountByname;

	/**
	 * Returns the sub category master where subCategoryName_en = &#63; or throws a <code>NoSuchSubCategoryMasterException</code> if it could not be found.
	 *
	 * @param subCategoryName_en the sub category name_en
	 * @return the matching sub category master
	 * @throws NoSuchSubCategoryMasterException if a matching sub category master could not be found
	 */
	@Override
	public SubCategoryMaster findByname(String subCategoryName_en)
		throws NoSuchSubCategoryMasterException {

		SubCategoryMaster subCategoryMaster = fetchByname(subCategoryName_en);

		if (subCategoryMaster == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("subCategoryName_en=");
			sb.append(subCategoryName_en);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchSubCategoryMasterException(sb.toString());
		}

		return subCategoryMaster;
	}

	/**
	 * Returns the sub category master where subCategoryName_en = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param subCategoryName_en the sub category name_en
	 * @return the matching sub category master, or <code>null</code> if a matching sub category master could not be found
	 */
	@Override
	public SubCategoryMaster fetchByname(String subCategoryName_en) {
		return fetchByname(subCategoryName_en, true);
	}

	/**
	 * Returns the sub category master where subCategoryName_en = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param subCategoryName_en the sub category name_en
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching sub category master, or <code>null</code> if a matching sub category master could not be found
	 */
	@Override
	public SubCategoryMaster fetchByname(
		String subCategoryName_en, boolean useFinderCache) {

		subCategoryName_en = Objects.toString(subCategoryName_en, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {subCategoryName_en};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByname, finderArgs, this);
		}

		if (result instanceof SubCategoryMaster) {
			SubCategoryMaster subCategoryMaster = (SubCategoryMaster)result;

			if (!Objects.equals(
					subCategoryName_en,
					subCategoryMaster.getSubCategoryName_en())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_SUBCATEGORYMASTER_WHERE);

			boolean bindSubCategoryName_en = false;

			if (subCategoryName_en.isEmpty()) {
				sb.append(_FINDER_COLUMN_NAME_SUBCATEGORYNAME_EN_3);
			}
			else {
				bindSubCategoryName_en = true;

				sb.append(_FINDER_COLUMN_NAME_SUBCATEGORYNAME_EN_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindSubCategoryName_en) {
					queryPos.add(subCategoryName_en);
				}

				List<SubCategoryMaster> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByname, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {subCategoryName_en};
							}

							_log.warn(
								"SubCategoryMasterPersistenceImpl.fetchByname(String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					SubCategoryMaster subCategoryMaster = list.get(0);

					result = subCategoryMaster;

					cacheResult(subCategoryMaster);
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
			return (SubCategoryMaster)result;
		}
	}

	/**
	 * Removes the sub category master where subCategoryName_en = &#63; from the database.
	 *
	 * @param subCategoryName_en the sub category name_en
	 * @return the sub category master that was removed
	 */
	@Override
	public SubCategoryMaster removeByname(String subCategoryName_en)
		throws NoSuchSubCategoryMasterException {

		SubCategoryMaster subCategoryMaster = findByname(subCategoryName_en);

		return remove(subCategoryMaster);
	}

	/**
	 * Returns the number of sub category masters where subCategoryName_en = &#63;.
	 *
	 * @param subCategoryName_en the sub category name_en
	 * @return the number of matching sub category masters
	 */
	@Override
	public int countByname(String subCategoryName_en) {
		subCategoryName_en = Objects.toString(subCategoryName_en, "");

		FinderPath finderPath = _finderPathCountByname;

		Object[] finderArgs = new Object[] {subCategoryName_en};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SUBCATEGORYMASTER_WHERE);

			boolean bindSubCategoryName_en = false;

			if (subCategoryName_en.isEmpty()) {
				sb.append(_FINDER_COLUMN_NAME_SUBCATEGORYNAME_EN_3);
			}
			else {
				bindSubCategoryName_en = true;

				sb.append(_FINDER_COLUMN_NAME_SUBCATEGORYNAME_EN_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindSubCategoryName_en) {
					queryPos.add(subCategoryName_en);
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

	private static final String _FINDER_COLUMN_NAME_SUBCATEGORYNAME_EN_2 =
		"subCategoryMaster.subCategoryName_en = ?";

	private static final String _FINDER_COLUMN_NAME_SUBCATEGORYNAME_EN_3 =
		"(subCategoryMaster.subCategoryName_en IS NULL OR subCategoryMaster.subCategoryName_en = '')";

	public SubCategoryMasterPersistenceImpl() {
		setModelClass(SubCategoryMaster.class);

		setModelImplClass(SubCategoryMasterImpl.class);
		setModelPKClass(long.class);

		setTable(SubCategoryMasterTable.INSTANCE);
	}

	/**
	 * Caches the sub category master in the entity cache if it is enabled.
	 *
	 * @param subCategoryMaster the sub category master
	 */
	@Override
	public void cacheResult(SubCategoryMaster subCategoryMaster) {
		entityCache.putResult(
			SubCategoryMasterImpl.class, subCategoryMaster.getPrimaryKey(),
			subCategoryMaster);

		finderCache.putResult(
			_finderPathFetchByname,
			new Object[] {subCategoryMaster.getSubCategoryName_en()},
			subCategoryMaster);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the sub category masters in the entity cache if it is enabled.
	 *
	 * @param subCategoryMasters the sub category masters
	 */
	@Override
	public void cacheResult(List<SubCategoryMaster> subCategoryMasters) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (subCategoryMasters.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (SubCategoryMaster subCategoryMaster : subCategoryMasters) {
			if (entityCache.getResult(
					SubCategoryMasterImpl.class,
					subCategoryMaster.getPrimaryKey()) == null) {

				cacheResult(subCategoryMaster);
			}
		}
	}

	/**
	 * Clears the cache for all sub category masters.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SubCategoryMasterImpl.class);

		finderCache.clearCache(SubCategoryMasterImpl.class);
	}

	/**
	 * Clears the cache for the sub category master.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SubCategoryMaster subCategoryMaster) {
		entityCache.removeResult(
			SubCategoryMasterImpl.class, subCategoryMaster);
	}

	@Override
	public void clearCache(List<SubCategoryMaster> subCategoryMasters) {
		for (SubCategoryMaster subCategoryMaster : subCategoryMasters) {
			entityCache.removeResult(
				SubCategoryMasterImpl.class, subCategoryMaster);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(SubCategoryMasterImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(SubCategoryMasterImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		SubCategoryMasterModelImpl subCategoryMasterModelImpl) {

		Object[] args = new Object[] {
			subCategoryMasterModelImpl.getSubCategoryName_en()
		};

		finderCache.putResult(_finderPathCountByname, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByname, args, subCategoryMasterModelImpl);
	}

	/**
	 * Creates a new sub category master with the primary key. Does not add the sub category master to the database.
	 *
	 * @param subCategoryId the primary key for the new sub category master
	 * @return the new sub category master
	 */
	@Override
	public SubCategoryMaster create(long subCategoryId) {
		SubCategoryMaster subCategoryMaster = new SubCategoryMasterImpl();

		subCategoryMaster.setNew(true);
		subCategoryMaster.setPrimaryKey(subCategoryId);

		return subCategoryMaster;
	}

	/**
	 * Removes the sub category master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param subCategoryId the primary key of the sub category master
	 * @return the sub category master that was removed
	 * @throws NoSuchSubCategoryMasterException if a sub category master with the primary key could not be found
	 */
	@Override
	public SubCategoryMaster remove(long subCategoryId)
		throws NoSuchSubCategoryMasterException {

		return remove((Serializable)subCategoryId);
	}

	/**
	 * Removes the sub category master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the sub category master
	 * @return the sub category master that was removed
	 * @throws NoSuchSubCategoryMasterException if a sub category master with the primary key could not be found
	 */
	@Override
	public SubCategoryMaster remove(Serializable primaryKey)
		throws NoSuchSubCategoryMasterException {

		Session session = null;

		try {
			session = openSession();

			SubCategoryMaster subCategoryMaster =
				(SubCategoryMaster)session.get(
					SubCategoryMasterImpl.class, primaryKey);

			if (subCategoryMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSubCategoryMasterException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(subCategoryMaster);
		}
		catch (NoSuchSubCategoryMasterException noSuchEntityException) {
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
	protected SubCategoryMaster removeImpl(
		SubCategoryMaster subCategoryMaster) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(subCategoryMaster)) {
				subCategoryMaster = (SubCategoryMaster)session.get(
					SubCategoryMasterImpl.class,
					subCategoryMaster.getPrimaryKeyObj());
			}

			if (subCategoryMaster != null) {
				session.delete(subCategoryMaster);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (subCategoryMaster != null) {
			clearCache(subCategoryMaster);
		}

		return subCategoryMaster;
	}

	@Override
	public SubCategoryMaster updateImpl(SubCategoryMaster subCategoryMaster) {
		boolean isNew = subCategoryMaster.isNew();

		if (!(subCategoryMaster instanceof SubCategoryMasterModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(subCategoryMaster.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					subCategoryMaster);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in subCategoryMaster proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SubCategoryMaster implementation " +
					subCategoryMaster.getClass());
		}

		SubCategoryMasterModelImpl subCategoryMasterModelImpl =
			(SubCategoryMasterModelImpl)subCategoryMaster;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(subCategoryMaster);
			}
			else {
				subCategoryMaster = (SubCategoryMaster)session.merge(
					subCategoryMaster);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			SubCategoryMasterImpl.class, subCategoryMasterModelImpl, false,
			true);

		cacheUniqueFindersCache(subCategoryMasterModelImpl);

		if (isNew) {
			subCategoryMaster.setNew(false);
		}

		subCategoryMaster.resetOriginalValues();

		return subCategoryMaster;
	}

	/**
	 * Returns the sub category master with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the sub category master
	 * @return the sub category master
	 * @throws NoSuchSubCategoryMasterException if a sub category master with the primary key could not be found
	 */
	@Override
	public SubCategoryMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSubCategoryMasterException {

		SubCategoryMaster subCategoryMaster = fetchByPrimaryKey(primaryKey);

		if (subCategoryMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSubCategoryMasterException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return subCategoryMaster;
	}

	/**
	 * Returns the sub category master with the primary key or throws a <code>NoSuchSubCategoryMasterException</code> if it could not be found.
	 *
	 * @param subCategoryId the primary key of the sub category master
	 * @return the sub category master
	 * @throws NoSuchSubCategoryMasterException if a sub category master with the primary key could not be found
	 */
	@Override
	public SubCategoryMaster findByPrimaryKey(long subCategoryId)
		throws NoSuchSubCategoryMasterException {

		return findByPrimaryKey((Serializable)subCategoryId);
	}

	/**
	 * Returns the sub category master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param subCategoryId the primary key of the sub category master
	 * @return the sub category master, or <code>null</code> if a sub category master with the primary key could not be found
	 */
	@Override
	public SubCategoryMaster fetchByPrimaryKey(long subCategoryId) {
		return fetchByPrimaryKey((Serializable)subCategoryId);
	}

	/**
	 * Returns all the sub category masters.
	 *
	 * @return the sub category masters
	 */
	@Override
	public List<SubCategoryMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sub category masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubCategoryMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sub category masters
	 * @param end the upper bound of the range of sub category masters (not inclusive)
	 * @return the range of sub category masters
	 */
	@Override
	public List<SubCategoryMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the sub category masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubCategoryMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sub category masters
	 * @param end the upper bound of the range of sub category masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sub category masters
	 */
	@Override
	public List<SubCategoryMaster> findAll(
		int start, int end,
		OrderByComparator<SubCategoryMaster> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sub category masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubCategoryMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sub category masters
	 * @param end the upper bound of the range of sub category masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of sub category masters
	 */
	@Override
	public List<SubCategoryMaster> findAll(
		int start, int end,
		OrderByComparator<SubCategoryMaster> orderByComparator,
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

		List<SubCategoryMaster> list = null;

		if (useFinderCache) {
			list = (List<SubCategoryMaster>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SUBCATEGORYMASTER);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SUBCATEGORYMASTER;

				sql = sql.concat(SubCategoryMasterModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<SubCategoryMaster>)QueryUtil.list(
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
	 * Removes all the sub category masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SubCategoryMaster subCategoryMaster : findAll()) {
			remove(subCategoryMaster);
		}
	}

	/**
	 * Returns the number of sub category masters.
	 *
	 * @return the number of sub category masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SUBCATEGORYMASTER);

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
		return "subCategoryId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SUBCATEGORYMASTER;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SubCategoryMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the sub category master persistence.
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

		_finderPathWithPaginationFindByCategoryMasterId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCategoryMasterId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"categoryMasterId"}, true);

		_finderPathWithoutPaginationFindByCategoryMasterId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCategoryMasterId",
			new String[] {Long.class.getName()},
			new String[] {"categoryMasterId"}, true);

		_finderPathCountByCategoryMasterId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCategoryMasterId", new String[] {Long.class.getName()},
			new String[] {"categoryMasterId"}, false);

		_finderPathFetchByname = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByname",
			new String[] {String.class.getName()},
			new String[] {"subCategoryName_en"}, true);

		_finderPathCountByname = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByname",
			new String[] {String.class.getName()},
			new String[] {"subCategoryName_en"}, false);

		SubCategoryMasterUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		SubCategoryMasterUtil.setPersistence(null);

		entityCache.removeCache(SubCategoryMasterImpl.class.getName());
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

	private static final String _SQL_SELECT_SUBCATEGORYMASTER =
		"SELECT subCategoryMaster FROM SubCategoryMaster subCategoryMaster";

	private static final String _SQL_SELECT_SUBCATEGORYMASTER_WHERE =
		"SELECT subCategoryMaster FROM SubCategoryMaster subCategoryMaster WHERE ";

	private static final String _SQL_COUNT_SUBCATEGORYMASTER =
		"SELECT COUNT(subCategoryMaster) FROM SubCategoryMaster subCategoryMaster";

	private static final String _SQL_COUNT_SUBCATEGORYMASTER_WHERE =
		"SELECT COUNT(subCategoryMaster) FROM SubCategoryMaster subCategoryMaster WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "subCategoryMaster.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SubCategoryMaster exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No SubCategoryMaster exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		SubCategoryMasterPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}