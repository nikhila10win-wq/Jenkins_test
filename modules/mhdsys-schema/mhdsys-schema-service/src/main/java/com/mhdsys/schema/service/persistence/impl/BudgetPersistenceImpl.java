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
import com.liferay.portal.kernel.util.StringUtil;

import com.mhdsys.schema.exception.NoSuchBudgetException;
import com.mhdsys.schema.model.Budget;
import com.mhdsys.schema.model.BudgetTable;
import com.mhdsys.schema.model.impl.BudgetImpl;
import com.mhdsys.schema.model.impl.BudgetModelImpl;
import com.mhdsys.schema.service.persistence.BudgetPersistence;
import com.mhdsys.schema.service.persistence.BudgetUtil;
import com.mhdsys.schema.service.persistence.impl.constants.mhdsysPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
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
 * The persistence implementation for the budget service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = BudgetPersistence.class)
public class BudgetPersistenceImpl
	extends BasePersistenceImpl<Budget> implements BudgetPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>BudgetUtil</code> to access the budget persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		BudgetImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByUserId;
	private FinderPath _finderPathCountByUserId;

	/**
	 * Returns the budget where userId = &#63; or throws a <code>NoSuchBudgetException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching budget
	 * @throws NoSuchBudgetException if a matching budget could not be found
	 */
	@Override
	public Budget findByUserId(long userId) throws NoSuchBudgetException {
		Budget budget = fetchByUserId(userId);

		if (budget == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("userId=");
			sb.append(userId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchBudgetException(sb.toString());
		}

		return budget;
	}

	/**
	 * Returns the budget where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching budget, or <code>null</code> if a matching budget could not be found
	 */
	@Override
	public Budget fetchByUserId(long userId) {
		return fetchByUserId(userId, true);
	}

	/**
	 * Returns the budget where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching budget, or <code>null</code> if a matching budget could not be found
	 */
	@Override
	public Budget fetchByUserId(long userId, boolean useFinderCache) {
		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {userId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUserId, finderArgs, this);
		}

		if (result instanceof Budget) {
			Budget budget = (Budget)result;

			if (userId != budget.getUserId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_BUDGET_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				List<Budget> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUserId, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {userId};
							}

							_log.warn(
								"BudgetPersistenceImpl.fetchByUserId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Budget budget = list.get(0);

					result = budget;

					cacheResult(budget);
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
			return (Budget)result;
		}
	}

	/**
	 * Removes the budget where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the budget that was removed
	 */
	@Override
	public Budget removeByUserId(long userId) throws NoSuchBudgetException {
		Budget budget = findByUserId(userId);

		return remove(budget);
	}

	/**
	 * Returns the number of budgets where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching budgets
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_BUDGET_WHERE);

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
		"budget.userId = ?";

	private FinderPath _finderPathWithPaginationFindByFinancialYear;
	private FinderPath _finderPathWithoutPaginationFindByFinancialYear;
	private FinderPath _finderPathCountByFinancialYear;

	/**
	 * Returns all the budgets where financialYear = &#63;.
	 *
	 * @param financialYear the financial year
	 * @return the matching budgets
	 */
	@Override
	public List<Budget> findByFinancialYear(String financialYear) {
		return findByFinancialYear(
			financialYear, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the budgets where financialYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetModelImpl</code>.
	 * </p>
	 *
	 * @param financialYear the financial year
	 * @param start the lower bound of the range of budgets
	 * @param end the upper bound of the range of budgets (not inclusive)
	 * @return the range of matching budgets
	 */
	@Override
	public List<Budget> findByFinancialYear(
		String financialYear, int start, int end) {

		return findByFinancialYear(financialYear, start, end, null);
	}

	/**
	 * Returns an ordered range of all the budgets where financialYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetModelImpl</code>.
	 * </p>
	 *
	 * @param financialYear the financial year
	 * @param start the lower bound of the range of budgets
	 * @param end the upper bound of the range of budgets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budgets
	 */
	@Override
	public List<Budget> findByFinancialYear(
		String financialYear, int start, int end,
		OrderByComparator<Budget> orderByComparator) {

		return findByFinancialYear(
			financialYear, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budgets where financialYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetModelImpl</code>.
	 * </p>
	 *
	 * @param financialYear the financial year
	 * @param start the lower bound of the range of budgets
	 * @param end the upper bound of the range of budgets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching budgets
	 */
	@Override
	public List<Budget> findByFinancialYear(
		String financialYear, int start, int end,
		OrderByComparator<Budget> orderByComparator, boolean useFinderCache) {

		financialYear = Objects.toString(financialYear, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByFinancialYear;
				finderArgs = new Object[] {financialYear};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByFinancialYear;
			finderArgs = new Object[] {
				financialYear, start, end, orderByComparator
			};
		}

		List<Budget> list = null;

		if (useFinderCache) {
			list = (List<Budget>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Budget budget : list) {
					if (!financialYear.equals(budget.getFinancialYear())) {
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

			sb.append(_SQL_SELECT_BUDGET_WHERE);

			boolean bindFinancialYear = false;

			if (financialYear.isEmpty()) {
				sb.append(_FINDER_COLUMN_FINANCIALYEAR_FINANCIALYEAR_3);
			}
			else {
				bindFinancialYear = true;

				sb.append(_FINDER_COLUMN_FINANCIALYEAR_FINANCIALYEAR_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(BudgetModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindFinancialYear) {
					queryPos.add(financialYear);
				}

				list = (List<Budget>)QueryUtil.list(
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
	 * Returns the first budget in the ordered set where financialYear = &#63;.
	 *
	 * @param financialYear the financial year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget
	 * @throws NoSuchBudgetException if a matching budget could not be found
	 */
	@Override
	public Budget findByFinancialYear_First(
			String financialYear, OrderByComparator<Budget> orderByComparator)
		throws NoSuchBudgetException {

		Budget budget = fetchByFinancialYear_First(
			financialYear, orderByComparator);

		if (budget != null) {
			return budget;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("financialYear=");
		sb.append(financialYear);

		sb.append("}");

		throw new NoSuchBudgetException(sb.toString());
	}

	/**
	 * Returns the first budget in the ordered set where financialYear = &#63;.
	 *
	 * @param financialYear the financial year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget, or <code>null</code> if a matching budget could not be found
	 */
	@Override
	public Budget fetchByFinancialYear_First(
		String financialYear, OrderByComparator<Budget> orderByComparator) {

		List<Budget> list = findByFinancialYear(
			financialYear, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last budget in the ordered set where financialYear = &#63;.
	 *
	 * @param financialYear the financial year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget
	 * @throws NoSuchBudgetException if a matching budget could not be found
	 */
	@Override
	public Budget findByFinancialYear_Last(
			String financialYear, OrderByComparator<Budget> orderByComparator)
		throws NoSuchBudgetException {

		Budget budget = fetchByFinancialYear_Last(
			financialYear, orderByComparator);

		if (budget != null) {
			return budget;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("financialYear=");
		sb.append(financialYear);

		sb.append("}");

		throw new NoSuchBudgetException(sb.toString());
	}

	/**
	 * Returns the last budget in the ordered set where financialYear = &#63;.
	 *
	 * @param financialYear the financial year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget, or <code>null</code> if a matching budget could not be found
	 */
	@Override
	public Budget fetchByFinancialYear_Last(
		String financialYear, OrderByComparator<Budget> orderByComparator) {

		int count = countByFinancialYear(financialYear);

		if (count == 0) {
			return null;
		}

		List<Budget> list = findByFinancialYear(
			financialYear, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the budgets before and after the current budget in the ordered set where financialYear = &#63;.
	 *
	 * @param budgetId the primary key of the current budget
	 * @param financialYear the financial year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next budget
	 * @throws NoSuchBudgetException if a budget with the primary key could not be found
	 */
	@Override
	public Budget[] findByFinancialYear_PrevAndNext(
			long budgetId, String financialYear,
			OrderByComparator<Budget> orderByComparator)
		throws NoSuchBudgetException {

		financialYear = Objects.toString(financialYear, "");

		Budget budget = findByPrimaryKey(budgetId);

		Session session = null;

		try {
			session = openSession();

			Budget[] array = new BudgetImpl[3];

			array[0] = getByFinancialYear_PrevAndNext(
				session, budget, financialYear, orderByComparator, true);

			array[1] = budget;

			array[2] = getByFinancialYear_PrevAndNext(
				session, budget, financialYear, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Budget getByFinancialYear_PrevAndNext(
		Session session, Budget budget, String financialYear,
		OrderByComparator<Budget> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_BUDGET_WHERE);

		boolean bindFinancialYear = false;

		if (financialYear.isEmpty()) {
			sb.append(_FINDER_COLUMN_FINANCIALYEAR_FINANCIALYEAR_3);
		}
		else {
			bindFinancialYear = true;

			sb.append(_FINDER_COLUMN_FINANCIALYEAR_FINANCIALYEAR_2);
		}

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
			sb.append(BudgetModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindFinancialYear) {
			queryPos.add(financialYear);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(budget)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Budget> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the budgets where financialYear = &#63; from the database.
	 *
	 * @param financialYear the financial year
	 */
	@Override
	public void removeByFinancialYear(String financialYear) {
		for (Budget budget :
				findByFinancialYear(
					financialYear, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(budget);
		}
	}

	/**
	 * Returns the number of budgets where financialYear = &#63;.
	 *
	 * @param financialYear the financial year
	 * @return the number of matching budgets
	 */
	@Override
	public int countByFinancialYear(String financialYear) {
		financialYear = Objects.toString(financialYear, "");

		FinderPath finderPath = _finderPathCountByFinancialYear;

		Object[] finderArgs = new Object[] {financialYear};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_BUDGET_WHERE);

			boolean bindFinancialYear = false;

			if (financialYear.isEmpty()) {
				sb.append(_FINDER_COLUMN_FINANCIALYEAR_FINANCIALYEAR_3);
			}
			else {
				bindFinancialYear = true;

				sb.append(_FINDER_COLUMN_FINANCIALYEAR_FINANCIALYEAR_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindFinancialYear) {
					queryPos.add(financialYear);
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

	private static final String _FINDER_COLUMN_FINANCIALYEAR_FINANCIALYEAR_2 =
		"budget.financialYear = ?";

	private static final String _FINDER_COLUMN_FINANCIALYEAR_FINANCIALYEAR_3 =
		"(budget.financialYear IS NULL OR budget.financialYear = '')";

	private FinderPath _finderPathWithPaginationFindByFinancialYearAndCategory;
	private FinderPath
		_finderPathWithoutPaginationFindByFinancialYearAndCategory;
	private FinderPath _finderPathCountByFinancialYearAndCategory;

	/**
	 * Returns all the budgets where financialYear = &#63; and category = &#63;.
	 *
	 * @param financialYear the financial year
	 * @param category the category
	 * @return the matching budgets
	 */
	@Override
	public List<Budget> findByFinancialYearAndCategory(
		String financialYear, String category) {

		return findByFinancialYearAndCategory(
			financialYear, category, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the budgets where financialYear = &#63; and category = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetModelImpl</code>.
	 * </p>
	 *
	 * @param financialYear the financial year
	 * @param category the category
	 * @param start the lower bound of the range of budgets
	 * @param end the upper bound of the range of budgets (not inclusive)
	 * @return the range of matching budgets
	 */
	@Override
	public List<Budget> findByFinancialYearAndCategory(
		String financialYear, String category, int start, int end) {

		return findByFinancialYearAndCategory(
			financialYear, category, start, end, null);
	}

	/**
	 * Returns an ordered range of all the budgets where financialYear = &#63; and category = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetModelImpl</code>.
	 * </p>
	 *
	 * @param financialYear the financial year
	 * @param category the category
	 * @param start the lower bound of the range of budgets
	 * @param end the upper bound of the range of budgets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budgets
	 */
	@Override
	public List<Budget> findByFinancialYearAndCategory(
		String financialYear, String category, int start, int end,
		OrderByComparator<Budget> orderByComparator) {

		return findByFinancialYearAndCategory(
			financialYear, category, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budgets where financialYear = &#63; and category = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetModelImpl</code>.
	 * </p>
	 *
	 * @param financialYear the financial year
	 * @param category the category
	 * @param start the lower bound of the range of budgets
	 * @param end the upper bound of the range of budgets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching budgets
	 */
	@Override
	public List<Budget> findByFinancialYearAndCategory(
		String financialYear, String category, int start, int end,
		OrderByComparator<Budget> orderByComparator, boolean useFinderCache) {

		financialYear = Objects.toString(financialYear, "");
		category = Objects.toString(category, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByFinancialYearAndCategory;
				finderArgs = new Object[] {financialYear, category};
			}
		}
		else if (useFinderCache) {
			finderPath =
				_finderPathWithPaginationFindByFinancialYearAndCategory;
			finderArgs = new Object[] {
				financialYear, category, start, end, orderByComparator
			};
		}

		List<Budget> list = null;

		if (useFinderCache) {
			list = (List<Budget>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Budget budget : list) {
					if (!financialYear.equals(budget.getFinancialYear()) ||
						!category.equals(budget.getCategory())) {

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
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_BUDGET_WHERE);

			boolean bindFinancialYear = false;

			if (financialYear.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_FINANCIALYEARANDCATEGORY_FINANCIALYEAR_3);
			}
			else {
				bindFinancialYear = true;

				sb.append(
					_FINDER_COLUMN_FINANCIALYEARANDCATEGORY_FINANCIALYEAR_2);
			}

			boolean bindCategory = false;

			if (category.isEmpty()) {
				sb.append(_FINDER_COLUMN_FINANCIALYEARANDCATEGORY_CATEGORY_3);
			}
			else {
				bindCategory = true;

				sb.append(_FINDER_COLUMN_FINANCIALYEARANDCATEGORY_CATEGORY_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(BudgetModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindFinancialYear) {
					queryPos.add(financialYear);
				}

				if (bindCategory) {
					queryPos.add(category);
				}

				list = (List<Budget>)QueryUtil.list(
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
	 * Returns the first budget in the ordered set where financialYear = &#63; and category = &#63;.
	 *
	 * @param financialYear the financial year
	 * @param category the category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget
	 * @throws NoSuchBudgetException if a matching budget could not be found
	 */
	@Override
	public Budget findByFinancialYearAndCategory_First(
			String financialYear, String category,
			OrderByComparator<Budget> orderByComparator)
		throws NoSuchBudgetException {

		Budget budget = fetchByFinancialYearAndCategory_First(
			financialYear, category, orderByComparator);

		if (budget != null) {
			return budget;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("financialYear=");
		sb.append(financialYear);

		sb.append(", category=");
		sb.append(category);

		sb.append("}");

		throw new NoSuchBudgetException(sb.toString());
	}

	/**
	 * Returns the first budget in the ordered set where financialYear = &#63; and category = &#63;.
	 *
	 * @param financialYear the financial year
	 * @param category the category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget, or <code>null</code> if a matching budget could not be found
	 */
	@Override
	public Budget fetchByFinancialYearAndCategory_First(
		String financialYear, String category,
		OrderByComparator<Budget> orderByComparator) {

		List<Budget> list = findByFinancialYearAndCategory(
			financialYear, category, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last budget in the ordered set where financialYear = &#63; and category = &#63;.
	 *
	 * @param financialYear the financial year
	 * @param category the category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget
	 * @throws NoSuchBudgetException if a matching budget could not be found
	 */
	@Override
	public Budget findByFinancialYearAndCategory_Last(
			String financialYear, String category,
			OrderByComparator<Budget> orderByComparator)
		throws NoSuchBudgetException {

		Budget budget = fetchByFinancialYearAndCategory_Last(
			financialYear, category, orderByComparator);

		if (budget != null) {
			return budget;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("financialYear=");
		sb.append(financialYear);

		sb.append(", category=");
		sb.append(category);

		sb.append("}");

		throw new NoSuchBudgetException(sb.toString());
	}

	/**
	 * Returns the last budget in the ordered set where financialYear = &#63; and category = &#63;.
	 *
	 * @param financialYear the financial year
	 * @param category the category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget, or <code>null</code> if a matching budget could not be found
	 */
	@Override
	public Budget fetchByFinancialYearAndCategory_Last(
		String financialYear, String category,
		OrderByComparator<Budget> orderByComparator) {

		int count = countByFinancialYearAndCategory(financialYear, category);

		if (count == 0) {
			return null;
		}

		List<Budget> list = findByFinancialYearAndCategory(
			financialYear, category, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the budgets before and after the current budget in the ordered set where financialYear = &#63; and category = &#63;.
	 *
	 * @param budgetId the primary key of the current budget
	 * @param financialYear the financial year
	 * @param category the category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next budget
	 * @throws NoSuchBudgetException if a budget with the primary key could not be found
	 */
	@Override
	public Budget[] findByFinancialYearAndCategory_PrevAndNext(
			long budgetId, String financialYear, String category,
			OrderByComparator<Budget> orderByComparator)
		throws NoSuchBudgetException {

		financialYear = Objects.toString(financialYear, "");
		category = Objects.toString(category, "");

		Budget budget = findByPrimaryKey(budgetId);

		Session session = null;

		try {
			session = openSession();

			Budget[] array = new BudgetImpl[3];

			array[0] = getByFinancialYearAndCategory_PrevAndNext(
				session, budget, financialYear, category, orderByComparator,
				true);

			array[1] = budget;

			array[2] = getByFinancialYearAndCategory_PrevAndNext(
				session, budget, financialYear, category, orderByComparator,
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

	protected Budget getByFinancialYearAndCategory_PrevAndNext(
		Session session, Budget budget, String financialYear, String category,
		OrderByComparator<Budget> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_BUDGET_WHERE);

		boolean bindFinancialYear = false;

		if (financialYear.isEmpty()) {
			sb.append(_FINDER_COLUMN_FINANCIALYEARANDCATEGORY_FINANCIALYEAR_3);
		}
		else {
			bindFinancialYear = true;

			sb.append(_FINDER_COLUMN_FINANCIALYEARANDCATEGORY_FINANCIALYEAR_2);
		}

		boolean bindCategory = false;

		if (category.isEmpty()) {
			sb.append(_FINDER_COLUMN_FINANCIALYEARANDCATEGORY_CATEGORY_3);
		}
		else {
			bindCategory = true;

			sb.append(_FINDER_COLUMN_FINANCIALYEARANDCATEGORY_CATEGORY_2);
		}

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
			sb.append(BudgetModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindFinancialYear) {
			queryPos.add(financialYear);
		}

		if (bindCategory) {
			queryPos.add(category);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(budget)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Budget> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the budgets where financialYear = &#63; and category = &#63; from the database.
	 *
	 * @param financialYear the financial year
	 * @param category the category
	 */
	@Override
	public void removeByFinancialYearAndCategory(
		String financialYear, String category) {

		for (Budget budget :
				findByFinancialYearAndCategory(
					financialYear, category, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(budget);
		}
	}

	/**
	 * Returns the number of budgets where financialYear = &#63; and category = &#63;.
	 *
	 * @param financialYear the financial year
	 * @param category the category
	 * @return the number of matching budgets
	 */
	@Override
	public int countByFinancialYearAndCategory(
		String financialYear, String category) {

		financialYear = Objects.toString(financialYear, "");
		category = Objects.toString(category, "");

		FinderPath finderPath = _finderPathCountByFinancialYearAndCategory;

		Object[] finderArgs = new Object[] {financialYear, category};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_BUDGET_WHERE);

			boolean bindFinancialYear = false;

			if (financialYear.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_FINANCIALYEARANDCATEGORY_FINANCIALYEAR_3);
			}
			else {
				bindFinancialYear = true;

				sb.append(
					_FINDER_COLUMN_FINANCIALYEARANDCATEGORY_FINANCIALYEAR_2);
			}

			boolean bindCategory = false;

			if (category.isEmpty()) {
				sb.append(_FINDER_COLUMN_FINANCIALYEARANDCATEGORY_CATEGORY_3);
			}
			else {
				bindCategory = true;

				sb.append(_FINDER_COLUMN_FINANCIALYEARANDCATEGORY_CATEGORY_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindFinancialYear) {
					queryPos.add(financialYear);
				}

				if (bindCategory) {
					queryPos.add(category);
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

	private static final String
		_FINDER_COLUMN_FINANCIALYEARANDCATEGORY_FINANCIALYEAR_2 =
			"budget.financialYear = ? AND ";

	private static final String
		_FINDER_COLUMN_FINANCIALYEARANDCATEGORY_FINANCIALYEAR_3 =
			"(budget.financialYear IS NULL OR budget.financialYear = '') AND ";

	private static final String
		_FINDER_COLUMN_FINANCIALYEARANDCATEGORY_CATEGORY_2 =
			"budget.category = ?";

	private static final String
		_FINDER_COLUMN_FINANCIALYEARANDCATEGORY_CATEGORY_3 =
			"(budget.category IS NULL OR budget.category = '')";

	public BudgetPersistenceImpl() {
		setModelClass(Budget.class);

		setModelImplClass(BudgetImpl.class);
		setModelPKClass(long.class);

		setTable(BudgetTable.INSTANCE);
	}

	/**
	 * Caches the budget in the entity cache if it is enabled.
	 *
	 * @param budget the budget
	 */
	@Override
	public void cacheResult(Budget budget) {
		entityCache.putResult(BudgetImpl.class, budget.getPrimaryKey(), budget);

		finderCache.putResult(
			_finderPathFetchByUserId, new Object[] {budget.getUserId()},
			budget);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the budgets in the entity cache if it is enabled.
	 *
	 * @param budgets the budgets
	 */
	@Override
	public void cacheResult(List<Budget> budgets) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (budgets.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Budget budget : budgets) {
			if (entityCache.getResult(
					BudgetImpl.class, budget.getPrimaryKey()) == null) {

				cacheResult(budget);
			}
		}
	}

	/**
	 * Clears the cache for all budgets.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(BudgetImpl.class);

		finderCache.clearCache(BudgetImpl.class);
	}

	/**
	 * Clears the cache for the budget.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Budget budget) {
		entityCache.removeResult(BudgetImpl.class, budget);
	}

	@Override
	public void clearCache(List<Budget> budgets) {
		for (Budget budget : budgets) {
			entityCache.removeResult(BudgetImpl.class, budget);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(BudgetImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(BudgetImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(BudgetModelImpl budgetModelImpl) {
		Object[] args = new Object[] {budgetModelImpl.getUserId()};

		finderCache.putResult(_finderPathCountByUserId, args, Long.valueOf(1));
		finderCache.putResult(_finderPathFetchByUserId, args, budgetModelImpl);
	}

	/**
	 * Creates a new budget with the primary key. Does not add the budget to the database.
	 *
	 * @param budgetId the primary key for the new budget
	 * @return the new budget
	 */
	@Override
	public Budget create(long budgetId) {
		Budget budget = new BudgetImpl();

		budget.setNew(true);
		budget.setPrimaryKey(budgetId);

		return budget;
	}

	/**
	 * Removes the budget with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param budgetId the primary key of the budget
	 * @return the budget that was removed
	 * @throws NoSuchBudgetException if a budget with the primary key could not be found
	 */
	@Override
	public Budget remove(long budgetId) throws NoSuchBudgetException {
		return remove((Serializable)budgetId);
	}

	/**
	 * Removes the budget with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the budget
	 * @return the budget that was removed
	 * @throws NoSuchBudgetException if a budget with the primary key could not be found
	 */
	@Override
	public Budget remove(Serializable primaryKey) throws NoSuchBudgetException {
		Session session = null;

		try {
			session = openSession();

			Budget budget = (Budget)session.get(BudgetImpl.class, primaryKey);

			if (budget == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBudgetException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(budget);
		}
		catch (NoSuchBudgetException noSuchEntityException) {
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
	protected Budget removeImpl(Budget budget) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(budget)) {
				budget = (Budget)session.get(
					BudgetImpl.class, budget.getPrimaryKeyObj());
			}

			if (budget != null) {
				session.delete(budget);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (budget != null) {
			clearCache(budget);
		}

		return budget;
	}

	@Override
	public Budget updateImpl(Budget budget) {
		boolean isNew = budget.isNew();

		if (!(budget instanceof BudgetModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(budget.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(budget);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in budget proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Budget implementation " +
					budget.getClass());
		}

		BudgetModelImpl budgetModelImpl = (BudgetModelImpl)budget;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (budget.getCreateDate() == null)) {
			if (serviceContext == null) {
				budget.setCreateDate(date);
			}
			else {
				budget.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!budgetModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				budget.setModifiedDate(date);
			}
			else {
				budget.setModifiedDate(serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(budget);
			}
			else {
				budget = (Budget)session.merge(budget);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(BudgetImpl.class, budgetModelImpl, false, true);

		cacheUniqueFindersCache(budgetModelImpl);

		if (isNew) {
			budget.setNew(false);
		}

		budget.resetOriginalValues();

		return budget;
	}

	/**
	 * Returns the budget with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the budget
	 * @return the budget
	 * @throws NoSuchBudgetException if a budget with the primary key could not be found
	 */
	@Override
	public Budget findByPrimaryKey(Serializable primaryKey)
		throws NoSuchBudgetException {

		Budget budget = fetchByPrimaryKey(primaryKey);

		if (budget == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchBudgetException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return budget;
	}

	/**
	 * Returns the budget with the primary key or throws a <code>NoSuchBudgetException</code> if it could not be found.
	 *
	 * @param budgetId the primary key of the budget
	 * @return the budget
	 * @throws NoSuchBudgetException if a budget with the primary key could not be found
	 */
	@Override
	public Budget findByPrimaryKey(long budgetId) throws NoSuchBudgetException {
		return findByPrimaryKey((Serializable)budgetId);
	}

	/**
	 * Returns the budget with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param budgetId the primary key of the budget
	 * @return the budget, or <code>null</code> if a budget with the primary key could not be found
	 */
	@Override
	public Budget fetchByPrimaryKey(long budgetId) {
		return fetchByPrimaryKey((Serializable)budgetId);
	}

	/**
	 * Returns all the budgets.
	 *
	 * @return the budgets
	 */
	@Override
	public List<Budget> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the budgets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of budgets
	 * @param end the upper bound of the range of budgets (not inclusive)
	 * @return the range of budgets
	 */
	@Override
	public List<Budget> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the budgets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of budgets
	 * @param end the upper bound of the range of budgets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of budgets
	 */
	@Override
	public List<Budget> findAll(
		int start, int end, OrderByComparator<Budget> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the budgets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of budgets
	 * @param end the upper bound of the range of budgets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of budgets
	 */
	@Override
	public List<Budget> findAll(
		int start, int end, OrderByComparator<Budget> orderByComparator,
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

		List<Budget> list = null;

		if (useFinderCache) {
			list = (List<Budget>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_BUDGET);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_BUDGET;

				sql = sql.concat(BudgetModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Budget>)QueryUtil.list(
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
	 * Removes all the budgets from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Budget budget : findAll()) {
			remove(budget);
		}
	}

	/**
	 * Returns the number of budgets.
	 *
	 * @return the number of budgets
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_BUDGET);

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
		return "budgetId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_BUDGET;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return BudgetModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the budget persistence.
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

		_finderPathFetchByUserId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByUserId",
			new String[] {Long.class.getName()}, new String[] {"userId"}, true);

		_finderPathCountByUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] {Long.class.getName()}, new String[] {"userId"},
			false);

		_finderPathWithPaginationFindByFinancialYear = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByFinancialYear",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"financialYear"}, true);

		_finderPathWithoutPaginationFindByFinancialYear = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByFinancialYear",
			new String[] {String.class.getName()},
			new String[] {"financialYear"}, true);

		_finderPathCountByFinancialYear = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFinancialYear",
			new String[] {String.class.getName()},
			new String[] {"financialYear"}, false);

		_finderPathWithPaginationFindByFinancialYearAndCategory =
			new FinderPath(
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findByFinancialYearAndCategory",
				new String[] {
					String.class.getName(), String.class.getName(),
					Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				},
				new String[] {"financialYear", "category"}, true);

		_finderPathWithoutPaginationFindByFinancialYearAndCategory =
			new FinderPath(
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByFinancialYearAndCategory",
				new String[] {String.class.getName(), String.class.getName()},
				new String[] {"financialYear", "category"}, true);

		_finderPathCountByFinancialYearAndCategory = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByFinancialYearAndCategory",
			new String[] {String.class.getName(), String.class.getName()},
			new String[] {"financialYear", "category"}, false);

		BudgetUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		BudgetUtil.setPersistence(null);

		entityCache.removeCache(BudgetImpl.class.getName());
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

	private static final String _SQL_SELECT_BUDGET =
		"SELECT budget FROM Budget budget";

	private static final String _SQL_SELECT_BUDGET_WHERE =
		"SELECT budget FROM Budget budget WHERE ";

	private static final String _SQL_COUNT_BUDGET =
		"SELECT COUNT(budget) FROM Budget budget";

	private static final String _SQL_COUNT_BUDGET_WHERE =
		"SELECT COUNT(budget) FROM Budget budget WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "budget.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Budget exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Budget exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		BudgetPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}