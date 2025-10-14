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

import com.mhdsys.schema.exception.NoSuchTrainingCentreException;
import com.mhdsys.schema.model.TrainingCentre;
import com.mhdsys.schema.model.TrainingCentreTable;
import com.mhdsys.schema.model.impl.TrainingCentreImpl;
import com.mhdsys.schema.model.impl.TrainingCentreModelImpl;
import com.mhdsys.schema.service.persistence.TrainingCentrePersistence;
import com.mhdsys.schema.service.persistence.TrainingCentreUtil;
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
 * The persistence implementation for the training centre service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = TrainingCentrePersistence.class)
public class TrainingCentrePersistenceImpl
	extends BasePersistenceImpl<TrainingCentre>
	implements TrainingCentrePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>TrainingCentreUtil</code> to access the training centre persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		TrainingCentreImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByDivision;
	private FinderPath _finderPathWithoutPaginationFindByDivision;
	private FinderPath _finderPathCountByDivision;

	/**
	 * Returns all the training centres where division = &#63;.
	 *
	 * @param division the division
	 * @return the matching training centres
	 */
	@Override
	public List<TrainingCentre> findByDivision(String division) {
		return findByDivision(
			division, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training centres where division = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingCentreModelImpl</code>.
	 * </p>
	 *
	 * @param division the division
	 * @param start the lower bound of the range of training centres
	 * @param end the upper bound of the range of training centres (not inclusive)
	 * @return the range of matching training centres
	 */
	@Override
	public List<TrainingCentre> findByDivision(
		String division, int start, int end) {

		return findByDivision(division, start, end, null);
	}

	/**
	 * Returns an ordered range of all the training centres where division = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingCentreModelImpl</code>.
	 * </p>
	 *
	 * @param division the division
	 * @param start the lower bound of the range of training centres
	 * @param end the upper bound of the range of training centres (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching training centres
	 */
	@Override
	public List<TrainingCentre> findByDivision(
		String division, int start, int end,
		OrderByComparator<TrainingCentre> orderByComparator) {

		return findByDivision(division, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the training centres where division = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingCentreModelImpl</code>.
	 * </p>
	 *
	 * @param division the division
	 * @param start the lower bound of the range of training centres
	 * @param end the upper bound of the range of training centres (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching training centres
	 */
	@Override
	public List<TrainingCentre> findByDivision(
		String division, int start, int end,
		OrderByComparator<TrainingCentre> orderByComparator,
		boolean useFinderCache) {

		division = Objects.toString(division, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByDivision;
				finderArgs = new Object[] {division};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByDivision;
			finderArgs = new Object[] {division, start, end, orderByComparator};
		}

		List<TrainingCentre> list = null;

		if (useFinderCache) {
			list = (List<TrainingCentre>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TrainingCentre trainingCentre : list) {
					if (!division.equals(trainingCentre.getDivision())) {
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

			sb.append(_SQL_SELECT_TRAININGCENTRE_WHERE);

			boolean bindDivision = false;

			if (division.isEmpty()) {
				sb.append(_FINDER_COLUMN_DIVISION_DIVISION_3);
			}
			else {
				bindDivision = true;

				sb.append(_FINDER_COLUMN_DIVISION_DIVISION_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(TrainingCentreModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindDivision) {
					queryPos.add(division);
				}

				list = (List<TrainingCentre>)QueryUtil.list(
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
	 * Returns the first training centre in the ordered set where division = &#63;.
	 *
	 * @param division the division
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training centre
	 * @throws NoSuchTrainingCentreException if a matching training centre could not be found
	 */
	@Override
	public TrainingCentre findByDivision_First(
			String division,
			OrderByComparator<TrainingCentre> orderByComparator)
		throws NoSuchTrainingCentreException {

		TrainingCentre trainingCentre = fetchByDivision_First(
			division, orderByComparator);

		if (trainingCentre != null) {
			return trainingCentre;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("division=");
		sb.append(division);

		sb.append("}");

		throw new NoSuchTrainingCentreException(sb.toString());
	}

	/**
	 * Returns the first training centre in the ordered set where division = &#63;.
	 *
	 * @param division the division
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training centre, or <code>null</code> if a matching training centre could not be found
	 */
	@Override
	public TrainingCentre fetchByDivision_First(
		String division, OrderByComparator<TrainingCentre> orderByComparator) {

		List<TrainingCentre> list = findByDivision(
			division, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last training centre in the ordered set where division = &#63;.
	 *
	 * @param division the division
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training centre
	 * @throws NoSuchTrainingCentreException if a matching training centre could not be found
	 */
	@Override
	public TrainingCentre findByDivision_Last(
			String division,
			OrderByComparator<TrainingCentre> orderByComparator)
		throws NoSuchTrainingCentreException {

		TrainingCentre trainingCentre = fetchByDivision_Last(
			division, orderByComparator);

		if (trainingCentre != null) {
			return trainingCentre;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("division=");
		sb.append(division);

		sb.append("}");

		throw new NoSuchTrainingCentreException(sb.toString());
	}

	/**
	 * Returns the last training centre in the ordered set where division = &#63;.
	 *
	 * @param division the division
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training centre, or <code>null</code> if a matching training centre could not be found
	 */
	@Override
	public TrainingCentre fetchByDivision_Last(
		String division, OrderByComparator<TrainingCentre> orderByComparator) {

		int count = countByDivision(division);

		if (count == 0) {
			return null;
		}

		List<TrainingCentre> list = findByDivision(
			division, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the training centres before and after the current training centre in the ordered set where division = &#63;.
	 *
	 * @param trainingCentreId the primary key of the current training centre
	 * @param division the division
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next training centre
	 * @throws NoSuchTrainingCentreException if a training centre with the primary key could not be found
	 */
	@Override
	public TrainingCentre[] findByDivision_PrevAndNext(
			long trainingCentreId, String division,
			OrderByComparator<TrainingCentre> orderByComparator)
		throws NoSuchTrainingCentreException {

		division = Objects.toString(division, "");

		TrainingCentre trainingCentre = findByPrimaryKey(trainingCentreId);

		Session session = null;

		try {
			session = openSession();

			TrainingCentre[] array = new TrainingCentreImpl[3];

			array[0] = getByDivision_PrevAndNext(
				session, trainingCentre, division, orderByComparator, true);

			array[1] = trainingCentre;

			array[2] = getByDivision_PrevAndNext(
				session, trainingCentre, division, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected TrainingCentre getByDivision_PrevAndNext(
		Session session, TrainingCentre trainingCentre, String division,
		OrderByComparator<TrainingCentre> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_TRAININGCENTRE_WHERE);

		boolean bindDivision = false;

		if (division.isEmpty()) {
			sb.append(_FINDER_COLUMN_DIVISION_DIVISION_3);
		}
		else {
			bindDivision = true;

			sb.append(_FINDER_COLUMN_DIVISION_DIVISION_2);
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
			sb.append(TrainingCentreModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindDivision) {
			queryPos.add(division);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						trainingCentre)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<TrainingCentre> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the training centres where division = &#63; from the database.
	 *
	 * @param division the division
	 */
	@Override
	public void removeByDivision(String division) {
		for (TrainingCentre trainingCentre :
				findByDivision(
					division, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(trainingCentre);
		}
	}

	/**
	 * Returns the number of training centres where division = &#63;.
	 *
	 * @param division the division
	 * @return the number of matching training centres
	 */
	@Override
	public int countByDivision(String division) {
		division = Objects.toString(division, "");

		FinderPath finderPath = _finderPathCountByDivision;

		Object[] finderArgs = new Object[] {division};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_TRAININGCENTRE_WHERE);

			boolean bindDivision = false;

			if (division.isEmpty()) {
				sb.append(_FINDER_COLUMN_DIVISION_DIVISION_3);
			}
			else {
				bindDivision = true;

				sb.append(_FINDER_COLUMN_DIVISION_DIVISION_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindDivision) {
					queryPos.add(division);
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

	private static final String _FINDER_COLUMN_DIVISION_DIVISION_2 =
		"trainingCentre.division = ?";

	private static final String _FINDER_COLUMN_DIVISION_DIVISION_3 =
		"(trainingCentre.division IS NULL OR trainingCentre.division = '')";

	private FinderPath _finderPathWithPaginationFindByDistrict;
	private FinderPath _finderPathWithoutPaginationFindByDistrict;
	private FinderPath _finderPathCountByDistrict;

	/**
	 * Returns all the training centres where district = &#63;.
	 *
	 * @param district the district
	 * @return the matching training centres
	 */
	@Override
	public List<TrainingCentre> findByDistrict(String district) {
		return findByDistrict(
			district, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training centres where district = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingCentreModelImpl</code>.
	 * </p>
	 *
	 * @param district the district
	 * @param start the lower bound of the range of training centres
	 * @param end the upper bound of the range of training centres (not inclusive)
	 * @return the range of matching training centres
	 */
	@Override
	public List<TrainingCentre> findByDistrict(
		String district, int start, int end) {

		return findByDistrict(district, start, end, null);
	}

	/**
	 * Returns an ordered range of all the training centres where district = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingCentreModelImpl</code>.
	 * </p>
	 *
	 * @param district the district
	 * @param start the lower bound of the range of training centres
	 * @param end the upper bound of the range of training centres (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching training centres
	 */
	@Override
	public List<TrainingCentre> findByDistrict(
		String district, int start, int end,
		OrderByComparator<TrainingCentre> orderByComparator) {

		return findByDistrict(district, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the training centres where district = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingCentreModelImpl</code>.
	 * </p>
	 *
	 * @param district the district
	 * @param start the lower bound of the range of training centres
	 * @param end the upper bound of the range of training centres (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching training centres
	 */
	@Override
	public List<TrainingCentre> findByDistrict(
		String district, int start, int end,
		OrderByComparator<TrainingCentre> orderByComparator,
		boolean useFinderCache) {

		district = Objects.toString(district, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByDistrict;
				finderArgs = new Object[] {district};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByDistrict;
			finderArgs = new Object[] {district, start, end, orderByComparator};
		}

		List<TrainingCentre> list = null;

		if (useFinderCache) {
			list = (List<TrainingCentre>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TrainingCentre trainingCentre : list) {
					if (!district.equals(trainingCentre.getDistrict())) {
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

			sb.append(_SQL_SELECT_TRAININGCENTRE_WHERE);

			boolean bindDistrict = false;

			if (district.isEmpty()) {
				sb.append(_FINDER_COLUMN_DISTRICT_DISTRICT_3);
			}
			else {
				bindDistrict = true;

				sb.append(_FINDER_COLUMN_DISTRICT_DISTRICT_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(TrainingCentreModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindDistrict) {
					queryPos.add(district);
				}

				list = (List<TrainingCentre>)QueryUtil.list(
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
	 * Returns the first training centre in the ordered set where district = &#63;.
	 *
	 * @param district the district
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training centre
	 * @throws NoSuchTrainingCentreException if a matching training centre could not be found
	 */
	@Override
	public TrainingCentre findByDistrict_First(
			String district,
			OrderByComparator<TrainingCentre> orderByComparator)
		throws NoSuchTrainingCentreException {

		TrainingCentre trainingCentre = fetchByDistrict_First(
			district, orderByComparator);

		if (trainingCentre != null) {
			return trainingCentre;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("district=");
		sb.append(district);

		sb.append("}");

		throw new NoSuchTrainingCentreException(sb.toString());
	}

	/**
	 * Returns the first training centre in the ordered set where district = &#63;.
	 *
	 * @param district the district
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training centre, or <code>null</code> if a matching training centre could not be found
	 */
	@Override
	public TrainingCentre fetchByDistrict_First(
		String district, OrderByComparator<TrainingCentre> orderByComparator) {

		List<TrainingCentre> list = findByDistrict(
			district, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last training centre in the ordered set where district = &#63;.
	 *
	 * @param district the district
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training centre
	 * @throws NoSuchTrainingCentreException if a matching training centre could not be found
	 */
	@Override
	public TrainingCentre findByDistrict_Last(
			String district,
			OrderByComparator<TrainingCentre> orderByComparator)
		throws NoSuchTrainingCentreException {

		TrainingCentre trainingCentre = fetchByDistrict_Last(
			district, orderByComparator);

		if (trainingCentre != null) {
			return trainingCentre;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("district=");
		sb.append(district);

		sb.append("}");

		throw new NoSuchTrainingCentreException(sb.toString());
	}

	/**
	 * Returns the last training centre in the ordered set where district = &#63;.
	 *
	 * @param district the district
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training centre, or <code>null</code> if a matching training centre could not be found
	 */
	@Override
	public TrainingCentre fetchByDistrict_Last(
		String district, OrderByComparator<TrainingCentre> orderByComparator) {

		int count = countByDistrict(district);

		if (count == 0) {
			return null;
		}

		List<TrainingCentre> list = findByDistrict(
			district, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the training centres before and after the current training centre in the ordered set where district = &#63;.
	 *
	 * @param trainingCentreId the primary key of the current training centre
	 * @param district the district
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next training centre
	 * @throws NoSuchTrainingCentreException if a training centre with the primary key could not be found
	 */
	@Override
	public TrainingCentre[] findByDistrict_PrevAndNext(
			long trainingCentreId, String district,
			OrderByComparator<TrainingCentre> orderByComparator)
		throws NoSuchTrainingCentreException {

		district = Objects.toString(district, "");

		TrainingCentre trainingCentre = findByPrimaryKey(trainingCentreId);

		Session session = null;

		try {
			session = openSession();

			TrainingCentre[] array = new TrainingCentreImpl[3];

			array[0] = getByDistrict_PrevAndNext(
				session, trainingCentre, district, orderByComparator, true);

			array[1] = trainingCentre;

			array[2] = getByDistrict_PrevAndNext(
				session, trainingCentre, district, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected TrainingCentre getByDistrict_PrevAndNext(
		Session session, TrainingCentre trainingCentre, String district,
		OrderByComparator<TrainingCentre> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_TRAININGCENTRE_WHERE);

		boolean bindDistrict = false;

		if (district.isEmpty()) {
			sb.append(_FINDER_COLUMN_DISTRICT_DISTRICT_3);
		}
		else {
			bindDistrict = true;

			sb.append(_FINDER_COLUMN_DISTRICT_DISTRICT_2);
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
			sb.append(TrainingCentreModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindDistrict) {
			queryPos.add(district);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						trainingCentre)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<TrainingCentre> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the training centres where district = &#63; from the database.
	 *
	 * @param district the district
	 */
	@Override
	public void removeByDistrict(String district) {
		for (TrainingCentre trainingCentre :
				findByDistrict(
					district, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(trainingCentre);
		}
	}

	/**
	 * Returns the number of training centres where district = &#63;.
	 *
	 * @param district the district
	 * @return the number of matching training centres
	 */
	@Override
	public int countByDistrict(String district) {
		district = Objects.toString(district, "");

		FinderPath finderPath = _finderPathCountByDistrict;

		Object[] finderArgs = new Object[] {district};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_TRAININGCENTRE_WHERE);

			boolean bindDistrict = false;

			if (district.isEmpty()) {
				sb.append(_FINDER_COLUMN_DISTRICT_DISTRICT_3);
			}
			else {
				bindDistrict = true;

				sb.append(_FINDER_COLUMN_DISTRICT_DISTRICT_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindDistrict) {
					queryPos.add(district);
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

	private static final String _FINDER_COLUMN_DISTRICT_DISTRICT_2 =
		"trainingCentre.district = ?";

	private static final String _FINDER_COLUMN_DISTRICT_DISTRICT_3 =
		"(trainingCentre.district IS NULL OR trainingCentre.district = '')";

	private FinderPath _finderPathFetchByGetByUserIdAndFormStatus;
	private FinderPath _finderPathCountByGetByUserIdAndFormStatus;

	/**
	 * Returns the training centre where userid = &#63; and formstatus = &#63; or throws a <code>NoSuchTrainingCentreException</code> if it could not be found.
	 *
	 * @param userid the userid
	 * @param formstatus the formstatus
	 * @return the matching training centre
	 * @throws NoSuchTrainingCentreException if a matching training centre could not be found
	 */
	@Override
	public TrainingCentre findByGetByUserIdAndFormStatus(
			long userid, String formstatus)
		throws NoSuchTrainingCentreException {

		TrainingCentre trainingCentre = fetchByGetByUserIdAndFormStatus(
			userid, formstatus);

		if (trainingCentre == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("userid=");
			sb.append(userid);

			sb.append(", formstatus=");
			sb.append(formstatus);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchTrainingCentreException(sb.toString());
		}

		return trainingCentre;
	}

	/**
	 * Returns the training centre where userid = &#63; and formstatus = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userid the userid
	 * @param formstatus the formstatus
	 * @return the matching training centre, or <code>null</code> if a matching training centre could not be found
	 */
	@Override
	public TrainingCentre fetchByGetByUserIdAndFormStatus(
		long userid, String formstatus) {

		return fetchByGetByUserIdAndFormStatus(userid, formstatus, true);
	}

	/**
	 * Returns the training centre where userid = &#63; and formstatus = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userid the userid
	 * @param formstatus the formstatus
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching training centre, or <code>null</code> if a matching training centre could not be found
	 */
	@Override
	public TrainingCentre fetchByGetByUserIdAndFormStatus(
		long userid, String formstatus, boolean useFinderCache) {

		formstatus = Objects.toString(formstatus, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {userid, formstatus};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByGetByUserIdAndFormStatus, finderArgs, this);
		}

		if (result instanceof TrainingCentre) {
			TrainingCentre trainingCentre = (TrainingCentre)result;

			if ((userid != trainingCentre.getUserid()) ||
				!Objects.equals(formstatus, trainingCentre.getFormstatus())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_TRAININGCENTRE_WHERE);

			sb.append(_FINDER_COLUMN_GETBYUSERIDANDFORMSTATUS_USERID_2);

			boolean bindFormstatus = false;

			if (formstatus.isEmpty()) {
				sb.append(_FINDER_COLUMN_GETBYUSERIDANDFORMSTATUS_FORMSTATUS_3);
			}
			else {
				bindFormstatus = true;

				sb.append(_FINDER_COLUMN_GETBYUSERIDANDFORMSTATUS_FORMSTATUS_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userid);

				if (bindFormstatus) {
					queryPos.add(formstatus);
				}

				List<TrainingCentre> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByGetByUserIdAndFormStatus,
							finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {userid, formstatus};
							}

							_log.warn(
								"TrainingCentrePersistenceImpl.fetchByGetByUserIdAndFormStatus(long, String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					TrainingCentre trainingCentre = list.get(0);

					result = trainingCentre;

					cacheResult(trainingCentre);
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
			return (TrainingCentre)result;
		}
	}

	/**
	 * Removes the training centre where userid = &#63; and formstatus = &#63; from the database.
	 *
	 * @param userid the userid
	 * @param formstatus the formstatus
	 * @return the training centre that was removed
	 */
	@Override
	public TrainingCentre removeByGetByUserIdAndFormStatus(
			long userid, String formstatus)
		throws NoSuchTrainingCentreException {

		TrainingCentre trainingCentre = findByGetByUserIdAndFormStatus(
			userid, formstatus);

		return remove(trainingCentre);
	}

	/**
	 * Returns the number of training centres where userid = &#63; and formstatus = &#63;.
	 *
	 * @param userid the userid
	 * @param formstatus the formstatus
	 * @return the number of matching training centres
	 */
	@Override
	public int countByGetByUserIdAndFormStatus(long userid, String formstatus) {
		formstatus = Objects.toString(formstatus, "");

		FinderPath finderPath = _finderPathCountByGetByUserIdAndFormStatus;

		Object[] finderArgs = new Object[] {userid, formstatus};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_TRAININGCENTRE_WHERE);

			sb.append(_FINDER_COLUMN_GETBYUSERIDANDFORMSTATUS_USERID_2);

			boolean bindFormstatus = false;

			if (formstatus.isEmpty()) {
				sb.append(_FINDER_COLUMN_GETBYUSERIDANDFORMSTATUS_FORMSTATUS_3);
			}
			else {
				bindFormstatus = true;

				sb.append(_FINDER_COLUMN_GETBYUSERIDANDFORMSTATUS_FORMSTATUS_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userid);

				if (bindFormstatus) {
					queryPos.add(formstatus);
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
		_FINDER_COLUMN_GETBYUSERIDANDFORMSTATUS_USERID_2 =
			"trainingCentre.userid = ? AND ";

	private static final String
		_FINDER_COLUMN_GETBYUSERIDANDFORMSTATUS_FORMSTATUS_2 =
			"trainingCentre.formstatus = ?";

	private static final String
		_FINDER_COLUMN_GETBYUSERIDANDFORMSTATUS_FORMSTATUS_3 =
			"(trainingCentre.formstatus IS NULL OR trainingCentre.formstatus = '')";

	public TrainingCentrePersistenceImpl() {
		setModelClass(TrainingCentre.class);

		setModelImplClass(TrainingCentreImpl.class);
		setModelPKClass(long.class);

		setTable(TrainingCentreTable.INSTANCE);
	}

	/**
	 * Caches the training centre in the entity cache if it is enabled.
	 *
	 * @param trainingCentre the training centre
	 */
	@Override
	public void cacheResult(TrainingCentre trainingCentre) {
		entityCache.putResult(
			TrainingCentreImpl.class, trainingCentre.getPrimaryKey(),
			trainingCentre);

		finderCache.putResult(
			_finderPathFetchByGetByUserIdAndFormStatus,
			new Object[] {
				trainingCentre.getUserid(), trainingCentre.getFormstatus()
			},
			trainingCentre);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the training centres in the entity cache if it is enabled.
	 *
	 * @param trainingCentres the training centres
	 */
	@Override
	public void cacheResult(List<TrainingCentre> trainingCentres) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (trainingCentres.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (TrainingCentre trainingCentre : trainingCentres) {
			if (entityCache.getResult(
					TrainingCentreImpl.class, trainingCentre.getPrimaryKey()) ==
						null) {

				cacheResult(trainingCentre);
			}
		}
	}

	/**
	 * Clears the cache for all training centres.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TrainingCentreImpl.class);

		finderCache.clearCache(TrainingCentreImpl.class);
	}

	/**
	 * Clears the cache for the training centre.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TrainingCentre trainingCentre) {
		entityCache.removeResult(TrainingCentreImpl.class, trainingCentre);
	}

	@Override
	public void clearCache(List<TrainingCentre> trainingCentres) {
		for (TrainingCentre trainingCentre : trainingCentres) {
			entityCache.removeResult(TrainingCentreImpl.class, trainingCentre);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(TrainingCentreImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(TrainingCentreImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		TrainingCentreModelImpl trainingCentreModelImpl) {

		Object[] args = new Object[] {
			trainingCentreModelImpl.getUserid(),
			trainingCentreModelImpl.getFormstatus()
		};

		finderCache.putResult(
			_finderPathCountByGetByUserIdAndFormStatus, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByGetByUserIdAndFormStatus, args,
			trainingCentreModelImpl);
	}

	/**
	 * Creates a new training centre with the primary key. Does not add the training centre to the database.
	 *
	 * @param trainingCentreId the primary key for the new training centre
	 * @return the new training centre
	 */
	@Override
	public TrainingCentre create(long trainingCentreId) {
		TrainingCentre trainingCentre = new TrainingCentreImpl();

		trainingCentre.setNew(true);
		trainingCentre.setPrimaryKey(trainingCentreId);

		return trainingCentre;
	}

	/**
	 * Removes the training centre with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param trainingCentreId the primary key of the training centre
	 * @return the training centre that was removed
	 * @throws NoSuchTrainingCentreException if a training centre with the primary key could not be found
	 */
	@Override
	public TrainingCentre remove(long trainingCentreId)
		throws NoSuchTrainingCentreException {

		return remove((Serializable)trainingCentreId);
	}

	/**
	 * Removes the training centre with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the training centre
	 * @return the training centre that was removed
	 * @throws NoSuchTrainingCentreException if a training centre with the primary key could not be found
	 */
	@Override
	public TrainingCentre remove(Serializable primaryKey)
		throws NoSuchTrainingCentreException {

		Session session = null;

		try {
			session = openSession();

			TrainingCentre trainingCentre = (TrainingCentre)session.get(
				TrainingCentreImpl.class, primaryKey);

			if (trainingCentre == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTrainingCentreException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(trainingCentre);
		}
		catch (NoSuchTrainingCentreException noSuchEntityException) {
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
	protected TrainingCentre removeImpl(TrainingCentre trainingCentre) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(trainingCentre)) {
				trainingCentre = (TrainingCentre)session.get(
					TrainingCentreImpl.class,
					trainingCentre.getPrimaryKeyObj());
			}

			if (trainingCentre != null) {
				session.delete(trainingCentre);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (trainingCentre != null) {
			clearCache(trainingCentre);
		}

		return trainingCentre;
	}

	@Override
	public TrainingCentre updateImpl(TrainingCentre trainingCentre) {
		boolean isNew = trainingCentre.isNew();

		if (!(trainingCentre instanceof TrainingCentreModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(trainingCentre.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					trainingCentre);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in trainingCentre proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom TrainingCentre implementation " +
					trainingCentre.getClass());
		}

		TrainingCentreModelImpl trainingCentreModelImpl =
			(TrainingCentreModelImpl)trainingCentre;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (trainingCentre.getCreateDate() == null)) {
			if (serviceContext == null) {
				trainingCentre.setCreateDate(date);
			}
			else {
				trainingCentre.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!trainingCentreModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				trainingCentre.setModifiedDate(date);
			}
			else {
				trainingCentre.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(trainingCentre);
			}
			else {
				trainingCentre = (TrainingCentre)session.merge(trainingCentre);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			TrainingCentreImpl.class, trainingCentreModelImpl, false, true);

		cacheUniqueFindersCache(trainingCentreModelImpl);

		if (isNew) {
			trainingCentre.setNew(false);
		}

		trainingCentre.resetOriginalValues();

		return trainingCentre;
	}

	/**
	 * Returns the training centre with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the training centre
	 * @return the training centre
	 * @throws NoSuchTrainingCentreException if a training centre with the primary key could not be found
	 */
	@Override
	public TrainingCentre findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTrainingCentreException {

		TrainingCentre trainingCentre = fetchByPrimaryKey(primaryKey);

		if (trainingCentre == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTrainingCentreException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return trainingCentre;
	}

	/**
	 * Returns the training centre with the primary key or throws a <code>NoSuchTrainingCentreException</code> if it could not be found.
	 *
	 * @param trainingCentreId the primary key of the training centre
	 * @return the training centre
	 * @throws NoSuchTrainingCentreException if a training centre with the primary key could not be found
	 */
	@Override
	public TrainingCentre findByPrimaryKey(long trainingCentreId)
		throws NoSuchTrainingCentreException {

		return findByPrimaryKey((Serializable)trainingCentreId);
	}

	/**
	 * Returns the training centre with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param trainingCentreId the primary key of the training centre
	 * @return the training centre, or <code>null</code> if a training centre with the primary key could not be found
	 */
	@Override
	public TrainingCentre fetchByPrimaryKey(long trainingCentreId) {
		return fetchByPrimaryKey((Serializable)trainingCentreId);
	}

	/**
	 * Returns all the training centres.
	 *
	 * @return the training centres
	 */
	@Override
	public List<TrainingCentre> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training centres.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingCentreModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training centres
	 * @param end the upper bound of the range of training centres (not inclusive)
	 * @return the range of training centres
	 */
	@Override
	public List<TrainingCentre> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the training centres.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingCentreModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training centres
	 * @param end the upper bound of the range of training centres (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of training centres
	 */
	@Override
	public List<TrainingCentre> findAll(
		int start, int end,
		OrderByComparator<TrainingCentre> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the training centres.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TrainingCentreModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of training centres
	 * @param end the upper bound of the range of training centres (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of training centres
	 */
	@Override
	public List<TrainingCentre> findAll(
		int start, int end, OrderByComparator<TrainingCentre> orderByComparator,
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

		List<TrainingCentre> list = null;

		if (useFinderCache) {
			list = (List<TrainingCentre>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_TRAININGCENTRE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_TRAININGCENTRE;

				sql = sql.concat(TrainingCentreModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<TrainingCentre>)QueryUtil.list(
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
	 * Removes all the training centres from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TrainingCentre trainingCentre : findAll()) {
			remove(trainingCentre);
		}
	}

	/**
	 * Returns the number of training centres.
	 *
	 * @return the number of training centres
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_TRAININGCENTRE);

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
		return "trainingCentreId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_TRAININGCENTRE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return TrainingCentreModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the training centre persistence.
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

		_finderPathWithPaginationFindByDivision = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByDivision",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"division"}, true);

		_finderPathWithoutPaginationFindByDivision = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByDivision",
			new String[] {String.class.getName()}, new String[] {"division"},
			true);

		_finderPathCountByDivision = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDivision",
			new String[] {String.class.getName()}, new String[] {"division"},
			false);

		_finderPathWithPaginationFindByDistrict = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByDistrict",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"district"}, true);

		_finderPathWithoutPaginationFindByDistrict = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByDistrict",
			new String[] {String.class.getName()}, new String[] {"district"},
			true);

		_finderPathCountByDistrict = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDistrict",
			new String[] {String.class.getName()}, new String[] {"district"},
			false);

		_finderPathFetchByGetByUserIdAndFormStatus = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByGetByUserIdAndFormStatus",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"userid", "formstatus"}, true);

		_finderPathCountByGetByUserIdAndFormStatus = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGetByUserIdAndFormStatus",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"userid", "formstatus"}, false);

		TrainingCentreUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		TrainingCentreUtil.setPersistence(null);

		entityCache.removeCache(TrainingCentreImpl.class.getName());
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

	private static final String _SQL_SELECT_TRAININGCENTRE =
		"SELECT trainingCentre FROM TrainingCentre trainingCentre";

	private static final String _SQL_SELECT_TRAININGCENTRE_WHERE =
		"SELECT trainingCentre FROM TrainingCentre trainingCentre WHERE ";

	private static final String _SQL_COUNT_TRAININGCENTRE =
		"SELECT COUNT(trainingCentre) FROM TrainingCentre trainingCentre";

	private static final String _SQL_COUNT_TRAININGCENTRE_WHERE =
		"SELECT COUNT(trainingCentre) FROM TrainingCentre trainingCentre WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "trainingCentre.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No TrainingCentre exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No TrainingCentre exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		TrainingCentrePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}