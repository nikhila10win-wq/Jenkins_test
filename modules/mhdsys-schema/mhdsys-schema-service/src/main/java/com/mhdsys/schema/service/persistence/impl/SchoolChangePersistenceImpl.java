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

import com.mhdsys.schema.exception.NoSuchSchoolChangeException;
import com.mhdsys.schema.model.SchoolChange;
import com.mhdsys.schema.model.SchoolChangeTable;
import com.mhdsys.schema.model.impl.SchoolChangeImpl;
import com.mhdsys.schema.model.impl.SchoolChangeModelImpl;
import com.mhdsys.schema.service.persistence.SchoolChangePersistence;
import com.mhdsys.schema.service.persistence.SchoolChangeUtil;
import com.mhdsys.schema.service.persistence.impl.constants.mhdsysPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the school change service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = SchoolChangePersistence.class)
public class SchoolChangePersistenceImpl
	extends BasePersistenceImpl<SchoolChange>
	implements SchoolChangePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SchoolChangeUtil</code> to access the school change persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SchoolChangeImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchBySchoolChangeId;
	private FinderPath _finderPathCountBySchoolChangeId;

	/**
	 * Returns the school change where schoolChangeId = &#63; or throws a <code>NoSuchSchoolChangeException</code> if it could not be found.
	 *
	 * @param schoolChangeId the school change ID
	 * @return the matching school change
	 * @throws NoSuchSchoolChangeException if a matching school change could not be found
	 */
	@Override
	public SchoolChange findBySchoolChangeId(long schoolChangeId)
		throws NoSuchSchoolChangeException {

		SchoolChange schoolChange = fetchBySchoolChangeId(schoolChangeId);

		if (schoolChange == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("schoolChangeId=");
			sb.append(schoolChangeId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchSchoolChangeException(sb.toString());
		}

		return schoolChange;
	}

	/**
	 * Returns the school change where schoolChangeId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param schoolChangeId the school change ID
	 * @return the matching school change, or <code>null</code> if a matching school change could not be found
	 */
	@Override
	public SchoolChange fetchBySchoolChangeId(long schoolChangeId) {
		return fetchBySchoolChangeId(schoolChangeId, true);
	}

	/**
	 * Returns the school change where schoolChangeId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param schoolChangeId the school change ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching school change, or <code>null</code> if a matching school change could not be found
	 */
	@Override
	public SchoolChange fetchBySchoolChangeId(
		long schoolChangeId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {schoolChangeId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchBySchoolChangeId, finderArgs, this);
		}

		if (result instanceof SchoolChange) {
			SchoolChange schoolChange = (SchoolChange)result;

			if (schoolChangeId != schoolChange.getSchoolChangeId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_SCHOOLCHANGE_WHERE);

			sb.append(_FINDER_COLUMN_SCHOOLCHANGEID_SCHOOLCHANGEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(schoolChangeId);

				List<SchoolChange> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchBySchoolChangeId, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {schoolChangeId};
							}

							_log.warn(
								"SchoolChangePersistenceImpl.fetchBySchoolChangeId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					SchoolChange schoolChange = list.get(0);

					result = schoolChange;

					cacheResult(schoolChange);
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
			return (SchoolChange)result;
		}
	}

	/**
	 * Removes the school change where schoolChangeId = &#63; from the database.
	 *
	 * @param schoolChangeId the school change ID
	 * @return the school change that was removed
	 */
	@Override
	public SchoolChange removeBySchoolChangeId(long schoolChangeId)
		throws NoSuchSchoolChangeException {

		SchoolChange schoolChange = findBySchoolChangeId(schoolChangeId);

		return remove(schoolChange);
	}

	/**
	 * Returns the number of school changes where schoolChangeId = &#63;.
	 *
	 * @param schoolChangeId the school change ID
	 * @return the number of matching school changes
	 */
	@Override
	public int countBySchoolChangeId(long schoolChangeId) {
		FinderPath finderPath = _finderPathCountBySchoolChangeId;

		Object[] finderArgs = new Object[] {schoolChangeId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SCHOOLCHANGE_WHERE);

			sb.append(_FINDER_COLUMN_SCHOOLCHANGEID_SCHOOLCHANGEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(schoolChangeId);

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

	private static final String _FINDER_COLUMN_SCHOOLCHANGEID_SCHOOLCHANGEID_2 =
		"schoolChange.schoolChangeId = ?";

	public SchoolChangePersistenceImpl() {
		setModelClass(SchoolChange.class);

		setModelImplClass(SchoolChangeImpl.class);
		setModelPKClass(long.class);

		setTable(SchoolChangeTable.INSTANCE);
	}

	/**
	 * Caches the school change in the entity cache if it is enabled.
	 *
	 * @param schoolChange the school change
	 */
	@Override
	public void cacheResult(SchoolChange schoolChange) {
		entityCache.putResult(
			SchoolChangeImpl.class, schoolChange.getPrimaryKey(), schoolChange);

		finderCache.putResult(
			_finderPathFetchBySchoolChangeId,
			new Object[] {schoolChange.getSchoolChangeId()}, schoolChange);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the school changes in the entity cache if it is enabled.
	 *
	 * @param schoolChanges the school changes
	 */
	@Override
	public void cacheResult(List<SchoolChange> schoolChanges) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (schoolChanges.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (SchoolChange schoolChange : schoolChanges) {
			if (entityCache.getResult(
					SchoolChangeImpl.class, schoolChange.getPrimaryKey()) ==
						null) {

				cacheResult(schoolChange);
			}
		}
	}

	/**
	 * Clears the cache for all school changes.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SchoolChangeImpl.class);

		finderCache.clearCache(SchoolChangeImpl.class);
	}

	/**
	 * Clears the cache for the school change.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SchoolChange schoolChange) {
		entityCache.removeResult(SchoolChangeImpl.class, schoolChange);
	}

	@Override
	public void clearCache(List<SchoolChange> schoolChanges) {
		for (SchoolChange schoolChange : schoolChanges) {
			entityCache.removeResult(SchoolChangeImpl.class, schoolChange);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(SchoolChangeImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(SchoolChangeImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		SchoolChangeModelImpl schoolChangeModelImpl) {

		Object[] args = new Object[] {
			schoolChangeModelImpl.getSchoolChangeId()
		};

		finderCache.putResult(
			_finderPathCountBySchoolChangeId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchBySchoolChangeId, args, schoolChangeModelImpl);
	}

	/**
	 * Creates a new school change with the primary key. Does not add the school change to the database.
	 *
	 * @param schoolChangeId the primary key for the new school change
	 * @return the new school change
	 */
	@Override
	public SchoolChange create(long schoolChangeId) {
		SchoolChange schoolChange = new SchoolChangeImpl();

		schoolChange.setNew(true);
		schoolChange.setPrimaryKey(schoolChangeId);

		return schoolChange;
	}

	/**
	 * Removes the school change with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param schoolChangeId the primary key of the school change
	 * @return the school change that was removed
	 * @throws NoSuchSchoolChangeException if a school change with the primary key could not be found
	 */
	@Override
	public SchoolChange remove(long schoolChangeId)
		throws NoSuchSchoolChangeException {

		return remove((Serializable)schoolChangeId);
	}

	/**
	 * Removes the school change with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the school change
	 * @return the school change that was removed
	 * @throws NoSuchSchoolChangeException if a school change with the primary key could not be found
	 */
	@Override
	public SchoolChange remove(Serializable primaryKey)
		throws NoSuchSchoolChangeException {

		Session session = null;

		try {
			session = openSession();

			SchoolChange schoolChange = (SchoolChange)session.get(
				SchoolChangeImpl.class, primaryKey);

			if (schoolChange == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSchoolChangeException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(schoolChange);
		}
		catch (NoSuchSchoolChangeException noSuchEntityException) {
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
	protected SchoolChange removeImpl(SchoolChange schoolChange) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(schoolChange)) {
				schoolChange = (SchoolChange)session.get(
					SchoolChangeImpl.class, schoolChange.getPrimaryKeyObj());
			}

			if (schoolChange != null) {
				session.delete(schoolChange);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (schoolChange != null) {
			clearCache(schoolChange);
		}

		return schoolChange;
	}

	@Override
	public SchoolChange updateImpl(SchoolChange schoolChange) {
		boolean isNew = schoolChange.isNew();

		if (!(schoolChange instanceof SchoolChangeModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(schoolChange.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					schoolChange);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in schoolChange proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SchoolChange implementation " +
					schoolChange.getClass());
		}

		SchoolChangeModelImpl schoolChangeModelImpl =
			(SchoolChangeModelImpl)schoolChange;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (schoolChange.getCreateDate() == null)) {
			if (serviceContext == null) {
				schoolChange.setCreateDate(date);
			}
			else {
				schoolChange.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!schoolChangeModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				schoolChange.setModifiedDate(date);
			}
			else {
				schoolChange.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(schoolChange);
			}
			else {
				schoolChange = (SchoolChange)session.merge(schoolChange);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			SchoolChangeImpl.class, schoolChangeModelImpl, false, true);

		cacheUniqueFindersCache(schoolChangeModelImpl);

		if (isNew) {
			schoolChange.setNew(false);
		}

		schoolChange.resetOriginalValues();

		return schoolChange;
	}

	/**
	 * Returns the school change with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the school change
	 * @return the school change
	 * @throws NoSuchSchoolChangeException if a school change with the primary key could not be found
	 */
	@Override
	public SchoolChange findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSchoolChangeException {

		SchoolChange schoolChange = fetchByPrimaryKey(primaryKey);

		if (schoolChange == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSchoolChangeException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return schoolChange;
	}

	/**
	 * Returns the school change with the primary key or throws a <code>NoSuchSchoolChangeException</code> if it could not be found.
	 *
	 * @param schoolChangeId the primary key of the school change
	 * @return the school change
	 * @throws NoSuchSchoolChangeException if a school change with the primary key could not be found
	 */
	@Override
	public SchoolChange findByPrimaryKey(long schoolChangeId)
		throws NoSuchSchoolChangeException {

		return findByPrimaryKey((Serializable)schoolChangeId);
	}

	/**
	 * Returns the school change with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param schoolChangeId the primary key of the school change
	 * @return the school change, or <code>null</code> if a school change with the primary key could not be found
	 */
	@Override
	public SchoolChange fetchByPrimaryKey(long schoolChangeId) {
		return fetchByPrimaryKey((Serializable)schoolChangeId);
	}

	/**
	 * Returns all the school changes.
	 *
	 * @return the school changes
	 */
	@Override
	public List<SchoolChange> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the school changes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoolChangeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of school changes
	 * @param end the upper bound of the range of school changes (not inclusive)
	 * @return the range of school changes
	 */
	@Override
	public List<SchoolChange> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the school changes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoolChangeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of school changes
	 * @param end the upper bound of the range of school changes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of school changes
	 */
	@Override
	public List<SchoolChange> findAll(
		int start, int end, OrderByComparator<SchoolChange> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the school changes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SchoolChangeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of school changes
	 * @param end the upper bound of the range of school changes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of school changes
	 */
	@Override
	public List<SchoolChange> findAll(
		int start, int end, OrderByComparator<SchoolChange> orderByComparator,
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

		List<SchoolChange> list = null;

		if (useFinderCache) {
			list = (List<SchoolChange>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SCHOOLCHANGE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SCHOOLCHANGE;

				sql = sql.concat(SchoolChangeModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<SchoolChange>)QueryUtil.list(
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
	 * Removes all the school changes from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SchoolChange schoolChange : findAll()) {
			remove(schoolChange);
		}
	}

	/**
	 * Returns the number of school changes.
	 *
	 * @return the number of school changes
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SCHOOLCHANGE);

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
		return "schoolChangeId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SCHOOLCHANGE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SchoolChangeModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the school change persistence.
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

		_finderPathFetchBySchoolChangeId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchBySchoolChangeId",
			new String[] {Long.class.getName()},
			new String[] {"schoolChangeId"}, true);

		_finderPathCountBySchoolChangeId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySchoolChangeId",
			new String[] {Long.class.getName()},
			new String[] {"schoolChangeId"}, false);

		SchoolChangeUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		SchoolChangeUtil.setPersistence(null);

		entityCache.removeCache(SchoolChangeImpl.class.getName());
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

	private static final String _SQL_SELECT_SCHOOLCHANGE =
		"SELECT schoolChange FROM SchoolChange schoolChange";

	private static final String _SQL_SELECT_SCHOOLCHANGE_WHERE =
		"SELECT schoolChange FROM SchoolChange schoolChange WHERE ";

	private static final String _SQL_COUNT_SCHOOLCHANGE =
		"SELECT COUNT(schoolChange) FROM SchoolChange schoolChange";

	private static final String _SQL_COUNT_SCHOOLCHANGE_WHERE =
		"SELECT COUNT(schoolChange) FROM SchoolChange schoolChange WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "schoolChange.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SchoolChange exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No SchoolChange exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		SchoolChangePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}