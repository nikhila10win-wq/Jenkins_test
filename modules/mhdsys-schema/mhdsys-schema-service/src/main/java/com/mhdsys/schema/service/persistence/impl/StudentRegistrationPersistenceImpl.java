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

import com.mhdsys.schema.exception.NoSuchStudentRegistrationException;
import com.mhdsys.schema.model.StudentRegistration;
import com.mhdsys.schema.model.StudentRegistrationTable;
import com.mhdsys.schema.model.impl.StudentRegistrationImpl;
import com.mhdsys.schema.model.impl.StudentRegistrationModelImpl;
import com.mhdsys.schema.service.persistence.StudentRegistrationPersistence;
import com.mhdsys.schema.service.persistence.StudentRegistrationUtil;
import com.mhdsys.schema.service.persistence.impl.constants.mhdsysPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

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
 * The persistence implementation for the student registration service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = StudentRegistrationPersistence.class)
public class StudentRegistrationPersistenceImpl
	extends BasePersistenceImpl<StudentRegistration>
	implements StudentRegistrationPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>StudentRegistrationUtil</code> to access the student registration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		StudentRegistrationImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByGender;
	private FinderPath _finderPathWithoutPaginationFindByGender;
	private FinderPath _finderPathCountByGender;

	/**
	 * Returns all the student registrations where gender = &#63;.
	 *
	 * @param gender the gender
	 * @return the matching student registrations
	 */
	@Override
	public List<StudentRegistration> findByGender(String gender) {
		return findByGender(gender, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the student registrations where gender = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param gender the gender
	 * @param start the lower bound of the range of student registrations
	 * @param end the upper bound of the range of student registrations (not inclusive)
	 * @return the range of matching student registrations
	 */
	@Override
	public List<StudentRegistration> findByGender(
		String gender, int start, int end) {

		return findByGender(gender, start, end, null);
	}

	/**
	 * Returns an ordered range of all the student registrations where gender = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param gender the gender
	 * @param start the lower bound of the range of student registrations
	 * @param end the upper bound of the range of student registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching student registrations
	 */
	@Override
	public List<StudentRegistration> findByGender(
		String gender, int start, int end,
		OrderByComparator<StudentRegistration> orderByComparator) {

		return findByGender(gender, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the student registrations where gender = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param gender the gender
	 * @param start the lower bound of the range of student registrations
	 * @param end the upper bound of the range of student registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching student registrations
	 */
	@Override
	public List<StudentRegistration> findByGender(
		String gender, int start, int end,
		OrderByComparator<StudentRegistration> orderByComparator,
		boolean useFinderCache) {

		gender = Objects.toString(gender, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByGender;
				finderArgs = new Object[] {gender};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByGender;
			finderArgs = new Object[] {gender, start, end, orderByComparator};
		}

		List<StudentRegistration> list = null;

		if (useFinderCache) {
			list = (List<StudentRegistration>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (StudentRegistration studentRegistration : list) {
					if (!gender.equals(studentRegistration.getGender())) {
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

			sb.append(_SQL_SELECT_STUDENTREGISTRATION_WHERE);

			boolean bindGender = false;

			if (gender.isEmpty()) {
				sb.append(_FINDER_COLUMN_GENDER_GENDER_3);
			}
			else {
				bindGender = true;

				sb.append(_FINDER_COLUMN_GENDER_GENDER_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(StudentRegistrationModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindGender) {
					queryPos.add(gender);
				}

				list = (List<StudentRegistration>)QueryUtil.list(
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
	 * Returns the first student registration in the ordered set where gender = &#63;.
	 *
	 * @param gender the gender
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching student registration
	 * @throws NoSuchStudentRegistrationException if a matching student registration could not be found
	 */
	@Override
	public StudentRegistration findByGender_First(
			String gender,
			OrderByComparator<StudentRegistration> orderByComparator)
		throws NoSuchStudentRegistrationException {

		StudentRegistration studentRegistration = fetchByGender_First(
			gender, orderByComparator);

		if (studentRegistration != null) {
			return studentRegistration;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("gender=");
		sb.append(gender);

		sb.append("}");

		throw new NoSuchStudentRegistrationException(sb.toString());
	}

	/**
	 * Returns the first student registration in the ordered set where gender = &#63;.
	 *
	 * @param gender the gender
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching student registration, or <code>null</code> if a matching student registration could not be found
	 */
	@Override
	public StudentRegistration fetchByGender_First(
		String gender,
		OrderByComparator<StudentRegistration> orderByComparator) {

		List<StudentRegistration> list = findByGender(
			gender, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last student registration in the ordered set where gender = &#63;.
	 *
	 * @param gender the gender
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching student registration
	 * @throws NoSuchStudentRegistrationException if a matching student registration could not be found
	 */
	@Override
	public StudentRegistration findByGender_Last(
			String gender,
			OrderByComparator<StudentRegistration> orderByComparator)
		throws NoSuchStudentRegistrationException {

		StudentRegistration studentRegistration = fetchByGender_Last(
			gender, orderByComparator);

		if (studentRegistration != null) {
			return studentRegistration;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("gender=");
		sb.append(gender);

		sb.append("}");

		throw new NoSuchStudentRegistrationException(sb.toString());
	}

	/**
	 * Returns the last student registration in the ordered set where gender = &#63;.
	 *
	 * @param gender the gender
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching student registration, or <code>null</code> if a matching student registration could not be found
	 */
	@Override
	public StudentRegistration fetchByGender_Last(
		String gender,
		OrderByComparator<StudentRegistration> orderByComparator) {

		int count = countByGender(gender);

		if (count == 0) {
			return null;
		}

		List<StudentRegistration> list = findByGender(
			gender, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the student registrations before and after the current student registration in the ordered set where gender = &#63;.
	 *
	 * @param studentRegistrationId the primary key of the current student registration
	 * @param gender the gender
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next student registration
	 * @throws NoSuchStudentRegistrationException if a student registration with the primary key could not be found
	 */
	@Override
	public StudentRegistration[] findByGender_PrevAndNext(
			long studentRegistrationId, String gender,
			OrderByComparator<StudentRegistration> orderByComparator)
		throws NoSuchStudentRegistrationException {

		gender = Objects.toString(gender, "");

		StudentRegistration studentRegistration = findByPrimaryKey(
			studentRegistrationId);

		Session session = null;

		try {
			session = openSession();

			StudentRegistration[] array = new StudentRegistrationImpl[3];

			array[0] = getByGender_PrevAndNext(
				session, studentRegistration, gender, orderByComparator, true);

			array[1] = studentRegistration;

			array[2] = getByGender_PrevAndNext(
				session, studentRegistration, gender, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected StudentRegistration getByGender_PrevAndNext(
		Session session, StudentRegistration studentRegistration, String gender,
		OrderByComparator<StudentRegistration> orderByComparator,
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

		sb.append(_SQL_SELECT_STUDENTREGISTRATION_WHERE);

		boolean bindGender = false;

		if (gender.isEmpty()) {
			sb.append(_FINDER_COLUMN_GENDER_GENDER_3);
		}
		else {
			bindGender = true;

			sb.append(_FINDER_COLUMN_GENDER_GENDER_2);
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
			sb.append(StudentRegistrationModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindGender) {
			queryPos.add(gender);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						studentRegistration)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<StudentRegistration> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the student registrations where gender = &#63; from the database.
	 *
	 * @param gender the gender
	 */
	@Override
	public void removeByGender(String gender) {
		for (StudentRegistration studentRegistration :
				findByGender(
					gender, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(studentRegistration);
		}
	}

	/**
	 * Returns the number of student registrations where gender = &#63;.
	 *
	 * @param gender the gender
	 * @return the number of matching student registrations
	 */
	@Override
	public int countByGender(String gender) {
		gender = Objects.toString(gender, "");

		FinderPath finderPath = _finderPathCountByGender;

		Object[] finderArgs = new Object[] {gender};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_STUDENTREGISTRATION_WHERE);

			boolean bindGender = false;

			if (gender.isEmpty()) {
				sb.append(_FINDER_COLUMN_GENDER_GENDER_3);
			}
			else {
				bindGender = true;

				sb.append(_FINDER_COLUMN_GENDER_GENDER_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindGender) {
					queryPos.add(gender);
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

	private static final String _FINDER_COLUMN_GENDER_GENDER_2 =
		"studentRegistration.gender = ?";

	private static final String _FINDER_COLUMN_GENDER_GENDER_3 =
		"(studentRegistration.gender IS NULL OR studentRegistration.gender = '')";

	private FinderPath _finderPathWithPaginationFindByRegisterTo;
	private FinderPath _finderPathWithoutPaginationFindByRegisterTo;
	private FinderPath _finderPathCountByRegisterTo;

	/**
	 * Returns all the student registrations where registerTo = &#63;.
	 *
	 * @param registerTo the register to
	 * @return the matching student registrations
	 */
	@Override
	public List<StudentRegistration> findByRegisterTo(String registerTo) {
		return findByRegisterTo(
			registerTo, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the student registrations where registerTo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param registerTo the register to
	 * @param start the lower bound of the range of student registrations
	 * @param end the upper bound of the range of student registrations (not inclusive)
	 * @return the range of matching student registrations
	 */
	@Override
	public List<StudentRegistration> findByRegisterTo(
		String registerTo, int start, int end) {

		return findByRegisterTo(registerTo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the student registrations where registerTo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param registerTo the register to
	 * @param start the lower bound of the range of student registrations
	 * @param end the upper bound of the range of student registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching student registrations
	 */
	@Override
	public List<StudentRegistration> findByRegisterTo(
		String registerTo, int start, int end,
		OrderByComparator<StudentRegistration> orderByComparator) {

		return findByRegisterTo(
			registerTo, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the student registrations where registerTo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param registerTo the register to
	 * @param start the lower bound of the range of student registrations
	 * @param end the upper bound of the range of student registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching student registrations
	 */
	@Override
	public List<StudentRegistration> findByRegisterTo(
		String registerTo, int start, int end,
		OrderByComparator<StudentRegistration> orderByComparator,
		boolean useFinderCache) {

		registerTo = Objects.toString(registerTo, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByRegisterTo;
				finderArgs = new Object[] {registerTo};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByRegisterTo;
			finderArgs = new Object[] {
				registerTo, start, end, orderByComparator
			};
		}

		List<StudentRegistration> list = null;

		if (useFinderCache) {
			list = (List<StudentRegistration>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (StudentRegistration studentRegistration : list) {
					if (!registerTo.equals(
							studentRegistration.getRegisterTo())) {

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

			sb.append(_SQL_SELECT_STUDENTREGISTRATION_WHERE);

			boolean bindRegisterTo = false;

			if (registerTo.isEmpty()) {
				sb.append(_FINDER_COLUMN_REGISTERTO_REGISTERTO_3);
			}
			else {
				bindRegisterTo = true;

				sb.append(_FINDER_COLUMN_REGISTERTO_REGISTERTO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(StudentRegistrationModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindRegisterTo) {
					queryPos.add(registerTo);
				}

				list = (List<StudentRegistration>)QueryUtil.list(
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
	 * Returns the first student registration in the ordered set where registerTo = &#63;.
	 *
	 * @param registerTo the register to
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching student registration
	 * @throws NoSuchStudentRegistrationException if a matching student registration could not be found
	 */
	@Override
	public StudentRegistration findByRegisterTo_First(
			String registerTo,
			OrderByComparator<StudentRegistration> orderByComparator)
		throws NoSuchStudentRegistrationException {

		StudentRegistration studentRegistration = fetchByRegisterTo_First(
			registerTo, orderByComparator);

		if (studentRegistration != null) {
			return studentRegistration;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("registerTo=");
		sb.append(registerTo);

		sb.append("}");

		throw new NoSuchStudentRegistrationException(sb.toString());
	}

	/**
	 * Returns the first student registration in the ordered set where registerTo = &#63;.
	 *
	 * @param registerTo the register to
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching student registration, or <code>null</code> if a matching student registration could not be found
	 */
	@Override
	public StudentRegistration fetchByRegisterTo_First(
		String registerTo,
		OrderByComparator<StudentRegistration> orderByComparator) {

		List<StudentRegistration> list = findByRegisterTo(
			registerTo, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last student registration in the ordered set where registerTo = &#63;.
	 *
	 * @param registerTo the register to
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching student registration
	 * @throws NoSuchStudentRegistrationException if a matching student registration could not be found
	 */
	@Override
	public StudentRegistration findByRegisterTo_Last(
			String registerTo,
			OrderByComparator<StudentRegistration> orderByComparator)
		throws NoSuchStudentRegistrationException {

		StudentRegistration studentRegistration = fetchByRegisterTo_Last(
			registerTo, orderByComparator);

		if (studentRegistration != null) {
			return studentRegistration;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("registerTo=");
		sb.append(registerTo);

		sb.append("}");

		throw new NoSuchStudentRegistrationException(sb.toString());
	}

	/**
	 * Returns the last student registration in the ordered set where registerTo = &#63;.
	 *
	 * @param registerTo the register to
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching student registration, or <code>null</code> if a matching student registration could not be found
	 */
	@Override
	public StudentRegistration fetchByRegisterTo_Last(
		String registerTo,
		OrderByComparator<StudentRegistration> orderByComparator) {

		int count = countByRegisterTo(registerTo);

		if (count == 0) {
			return null;
		}

		List<StudentRegistration> list = findByRegisterTo(
			registerTo, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the student registrations before and after the current student registration in the ordered set where registerTo = &#63;.
	 *
	 * @param studentRegistrationId the primary key of the current student registration
	 * @param registerTo the register to
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next student registration
	 * @throws NoSuchStudentRegistrationException if a student registration with the primary key could not be found
	 */
	@Override
	public StudentRegistration[] findByRegisterTo_PrevAndNext(
			long studentRegistrationId, String registerTo,
			OrderByComparator<StudentRegistration> orderByComparator)
		throws NoSuchStudentRegistrationException {

		registerTo = Objects.toString(registerTo, "");

		StudentRegistration studentRegistration = findByPrimaryKey(
			studentRegistrationId);

		Session session = null;

		try {
			session = openSession();

			StudentRegistration[] array = new StudentRegistrationImpl[3];

			array[0] = getByRegisterTo_PrevAndNext(
				session, studentRegistration, registerTo, orderByComparator,
				true);

			array[1] = studentRegistration;

			array[2] = getByRegisterTo_PrevAndNext(
				session, studentRegistration, registerTo, orderByComparator,
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

	protected StudentRegistration getByRegisterTo_PrevAndNext(
		Session session, StudentRegistration studentRegistration,
		String registerTo,
		OrderByComparator<StudentRegistration> orderByComparator,
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

		sb.append(_SQL_SELECT_STUDENTREGISTRATION_WHERE);

		boolean bindRegisterTo = false;

		if (registerTo.isEmpty()) {
			sb.append(_FINDER_COLUMN_REGISTERTO_REGISTERTO_3);
		}
		else {
			bindRegisterTo = true;

			sb.append(_FINDER_COLUMN_REGISTERTO_REGISTERTO_2);
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
			sb.append(StudentRegistrationModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindRegisterTo) {
			queryPos.add(registerTo);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						studentRegistration)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<StudentRegistration> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the student registrations where registerTo = &#63; from the database.
	 *
	 * @param registerTo the register to
	 */
	@Override
	public void removeByRegisterTo(String registerTo) {
		for (StudentRegistration studentRegistration :
				findByRegisterTo(
					registerTo, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(studentRegistration);
		}
	}

	/**
	 * Returns the number of student registrations where registerTo = &#63;.
	 *
	 * @param registerTo the register to
	 * @return the number of matching student registrations
	 */
	@Override
	public int countByRegisterTo(String registerTo) {
		registerTo = Objects.toString(registerTo, "");

		FinderPath finderPath = _finderPathCountByRegisterTo;

		Object[] finderArgs = new Object[] {registerTo};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_STUDENTREGISTRATION_WHERE);

			boolean bindRegisterTo = false;

			if (registerTo.isEmpty()) {
				sb.append(_FINDER_COLUMN_REGISTERTO_REGISTERTO_3);
			}
			else {
				bindRegisterTo = true;

				sb.append(_FINDER_COLUMN_REGISTERTO_REGISTERTO_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindRegisterTo) {
					queryPos.add(registerTo);
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

	private static final String _FINDER_COLUMN_REGISTERTO_REGISTERTO_2 =
		"studentRegistration.registerTo = ?";

	private static final String _FINDER_COLUMN_REGISTERTO_REGISTERTO_3 =
		"(studentRegistration.registerTo IS NULL OR studentRegistration.registerTo = '')";

	public StudentRegistrationPersistenceImpl() {
		setModelClass(StudentRegistration.class);

		setModelImplClass(StudentRegistrationImpl.class);
		setModelPKClass(long.class);

		setTable(StudentRegistrationTable.INSTANCE);
	}

	/**
	 * Caches the student registration in the entity cache if it is enabled.
	 *
	 * @param studentRegistration the student registration
	 */
	@Override
	public void cacheResult(StudentRegistration studentRegistration) {
		entityCache.putResult(
			StudentRegistrationImpl.class, studentRegistration.getPrimaryKey(),
			studentRegistration);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the student registrations in the entity cache if it is enabled.
	 *
	 * @param studentRegistrations the student registrations
	 */
	@Override
	public void cacheResult(List<StudentRegistration> studentRegistrations) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (studentRegistrations.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (StudentRegistration studentRegistration : studentRegistrations) {
			if (entityCache.getResult(
					StudentRegistrationImpl.class,
					studentRegistration.getPrimaryKey()) == null) {

				cacheResult(studentRegistration);
			}
		}
	}

	/**
	 * Clears the cache for all student registrations.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StudentRegistrationImpl.class);

		finderCache.clearCache(StudentRegistrationImpl.class);
	}

	/**
	 * Clears the cache for the student registration.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(StudentRegistration studentRegistration) {
		entityCache.removeResult(
			StudentRegistrationImpl.class, studentRegistration);
	}

	@Override
	public void clearCache(List<StudentRegistration> studentRegistrations) {
		for (StudentRegistration studentRegistration : studentRegistrations) {
			entityCache.removeResult(
				StudentRegistrationImpl.class, studentRegistration);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(StudentRegistrationImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(StudentRegistrationImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new student registration with the primary key. Does not add the student registration to the database.
	 *
	 * @param studentRegistrationId the primary key for the new student registration
	 * @return the new student registration
	 */
	@Override
	public StudentRegistration create(long studentRegistrationId) {
		StudentRegistration studentRegistration = new StudentRegistrationImpl();

		studentRegistration.setNew(true);
		studentRegistration.setPrimaryKey(studentRegistrationId);

		return studentRegistration;
	}

	/**
	 * Removes the student registration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param studentRegistrationId the primary key of the student registration
	 * @return the student registration that was removed
	 * @throws NoSuchStudentRegistrationException if a student registration with the primary key could not be found
	 */
	@Override
	public StudentRegistration remove(long studentRegistrationId)
		throws NoSuchStudentRegistrationException {

		return remove((Serializable)studentRegistrationId);
	}

	/**
	 * Removes the student registration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the student registration
	 * @return the student registration that was removed
	 * @throws NoSuchStudentRegistrationException if a student registration with the primary key could not be found
	 */
	@Override
	public StudentRegistration remove(Serializable primaryKey)
		throws NoSuchStudentRegistrationException {

		Session session = null;

		try {
			session = openSession();

			StudentRegistration studentRegistration =
				(StudentRegistration)session.get(
					StudentRegistrationImpl.class, primaryKey);

			if (studentRegistration == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStudentRegistrationException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(studentRegistration);
		}
		catch (NoSuchStudentRegistrationException noSuchEntityException) {
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
	protected StudentRegistration removeImpl(
		StudentRegistration studentRegistration) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(studentRegistration)) {
				studentRegistration = (StudentRegistration)session.get(
					StudentRegistrationImpl.class,
					studentRegistration.getPrimaryKeyObj());
			}

			if (studentRegistration != null) {
				session.delete(studentRegistration);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (studentRegistration != null) {
			clearCache(studentRegistration);
		}

		return studentRegistration;
	}

	@Override
	public StudentRegistration updateImpl(
		StudentRegistration studentRegistration) {

		boolean isNew = studentRegistration.isNew();

		if (!(studentRegistration instanceof StudentRegistrationModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(studentRegistration.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					studentRegistration);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in studentRegistration proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom StudentRegistration implementation " +
					studentRegistration.getClass());
		}

		StudentRegistrationModelImpl studentRegistrationModelImpl =
			(StudentRegistrationModelImpl)studentRegistration;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (studentRegistration.getCreateDate() == null)) {
			if (serviceContext == null) {
				studentRegistration.setCreateDate(date);
			}
			else {
				studentRegistration.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!studentRegistrationModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				studentRegistration.setModifiedDate(date);
			}
			else {
				studentRegistration.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(studentRegistration);
			}
			else {
				studentRegistration = (StudentRegistration)session.merge(
					studentRegistration);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			StudentRegistrationImpl.class, studentRegistrationModelImpl, false,
			true);

		if (isNew) {
			studentRegistration.setNew(false);
		}

		studentRegistration.resetOriginalValues();

		return studentRegistration;
	}

	/**
	 * Returns the student registration with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the student registration
	 * @return the student registration
	 * @throws NoSuchStudentRegistrationException if a student registration with the primary key could not be found
	 */
	@Override
	public StudentRegistration findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStudentRegistrationException {

		StudentRegistration studentRegistration = fetchByPrimaryKey(primaryKey);

		if (studentRegistration == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStudentRegistrationException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return studentRegistration;
	}

	/**
	 * Returns the student registration with the primary key or throws a <code>NoSuchStudentRegistrationException</code> if it could not be found.
	 *
	 * @param studentRegistrationId the primary key of the student registration
	 * @return the student registration
	 * @throws NoSuchStudentRegistrationException if a student registration with the primary key could not be found
	 */
	@Override
	public StudentRegistration findByPrimaryKey(long studentRegistrationId)
		throws NoSuchStudentRegistrationException {

		return findByPrimaryKey((Serializable)studentRegistrationId);
	}

	/**
	 * Returns the student registration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param studentRegistrationId the primary key of the student registration
	 * @return the student registration, or <code>null</code> if a student registration with the primary key could not be found
	 */
	@Override
	public StudentRegistration fetchByPrimaryKey(long studentRegistrationId) {
		return fetchByPrimaryKey((Serializable)studentRegistrationId);
	}

	/**
	 * Returns all the student registrations.
	 *
	 * @return the student registrations
	 */
	@Override
	public List<StudentRegistration> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the student registrations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of student registrations
	 * @param end the upper bound of the range of student registrations (not inclusive)
	 * @return the range of student registrations
	 */
	@Override
	public List<StudentRegistration> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the student registrations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of student registrations
	 * @param end the upper bound of the range of student registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of student registrations
	 */
	@Override
	public List<StudentRegistration> findAll(
		int start, int end,
		OrderByComparator<StudentRegistration> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the student registrations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>StudentRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of student registrations
	 * @param end the upper bound of the range of student registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of student registrations
	 */
	@Override
	public List<StudentRegistration> findAll(
		int start, int end,
		OrderByComparator<StudentRegistration> orderByComparator,
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

		List<StudentRegistration> list = null;

		if (useFinderCache) {
			list = (List<StudentRegistration>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_STUDENTREGISTRATION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_STUDENTREGISTRATION;

				sql = sql.concat(StudentRegistrationModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<StudentRegistration>)QueryUtil.list(
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
	 * Removes all the student registrations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StudentRegistration studentRegistration : findAll()) {
			remove(studentRegistration);
		}
	}

	/**
	 * Returns the number of student registrations.
	 *
	 * @return the number of student registrations
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
					_SQL_COUNT_STUDENTREGISTRATION);

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
		return "studentRegistrationId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_STUDENTREGISTRATION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return StudentRegistrationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the student registration persistence.
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

		_finderPathWithPaginationFindByGender = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGender",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"gender"}, true);

		_finderPathWithoutPaginationFindByGender = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGender",
			new String[] {String.class.getName()}, new String[] {"gender"},
			true);

		_finderPathCountByGender = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGender",
			new String[] {String.class.getName()}, new String[] {"gender"},
			false);

		_finderPathWithPaginationFindByRegisterTo = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByRegisterTo",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"registerTo"}, true);

		_finderPathWithoutPaginationFindByRegisterTo = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByRegisterTo",
			new String[] {String.class.getName()}, new String[] {"registerTo"},
			true);

		_finderPathCountByRegisterTo = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRegisterTo",
			new String[] {String.class.getName()}, new String[] {"registerTo"},
			false);

		StudentRegistrationUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		StudentRegistrationUtil.setPersistence(null);

		entityCache.removeCache(StudentRegistrationImpl.class.getName());
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

	private static final String _SQL_SELECT_STUDENTREGISTRATION =
		"SELECT studentRegistration FROM StudentRegistration studentRegistration";

	private static final String _SQL_SELECT_STUDENTREGISTRATION_WHERE =
		"SELECT studentRegistration FROM StudentRegistration studentRegistration WHERE ";

	private static final String _SQL_COUNT_STUDENTREGISTRATION =
		"SELECT COUNT(studentRegistration) FROM StudentRegistration studentRegistration";

	private static final String _SQL_COUNT_STUDENTREGISTRATION_WHERE =
		"SELECT COUNT(studentRegistration) FROM StudentRegistration studentRegistration WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "studentRegistration.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No StudentRegistration exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No StudentRegistration exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		StudentRegistrationPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}