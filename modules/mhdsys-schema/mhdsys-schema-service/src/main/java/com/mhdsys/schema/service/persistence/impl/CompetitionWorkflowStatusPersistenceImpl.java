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

import com.mhdsys.schema.exception.NoSuchCompetitionWorkflowStatusException;
import com.mhdsys.schema.model.CompetitionWorkflowStatus;
import com.mhdsys.schema.model.CompetitionWorkflowStatusTable;
import com.mhdsys.schema.model.impl.CompetitionWorkflowStatusImpl;
import com.mhdsys.schema.model.impl.CompetitionWorkflowStatusModelImpl;
import com.mhdsys.schema.service.persistence.CompetitionWorkflowStatusPersistence;
import com.mhdsys.schema.service.persistence.CompetitionWorkflowStatusUtil;
import com.mhdsys.schema.service.persistence.impl.constants.mhdsysPersistenceConstants;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the competition workflow status service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = CompetitionWorkflowStatusPersistence.class)
public class CompetitionWorkflowStatusPersistenceImpl
	extends BasePersistenceImpl<CompetitionWorkflowStatus>
	implements CompetitionWorkflowStatusPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CompetitionWorkflowStatusUtil</code> to access the competition workflow status persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CompetitionWorkflowStatusImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public CompetitionWorkflowStatusPersistenceImpl() {
		setModelClass(CompetitionWorkflowStatus.class);

		setModelImplClass(CompetitionWorkflowStatusImpl.class);
		setModelPKClass(long.class);

		setTable(CompetitionWorkflowStatusTable.INSTANCE);
	}

	/**
	 * Caches the competition workflow status in the entity cache if it is enabled.
	 *
	 * @param competitionWorkflowStatus the competition workflow status
	 */
	@Override
	public void cacheResult(
		CompetitionWorkflowStatus competitionWorkflowStatus) {

		entityCache.putResult(
			CompetitionWorkflowStatusImpl.class,
			competitionWorkflowStatus.getPrimaryKey(),
			competitionWorkflowStatus);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the competition workflow statuses in the entity cache if it is enabled.
	 *
	 * @param competitionWorkflowStatuses the competition workflow statuses
	 */
	@Override
	public void cacheResult(
		List<CompetitionWorkflowStatus> competitionWorkflowStatuses) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (competitionWorkflowStatuses.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (CompetitionWorkflowStatus competitionWorkflowStatus :
				competitionWorkflowStatuses) {

			if (entityCache.getResult(
					CompetitionWorkflowStatusImpl.class,
					competitionWorkflowStatus.getPrimaryKey()) == null) {

				cacheResult(competitionWorkflowStatus);
			}
		}
	}

	/**
	 * Clears the cache for all competition workflow statuses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CompetitionWorkflowStatusImpl.class);

		finderCache.clearCache(CompetitionWorkflowStatusImpl.class);
	}

	/**
	 * Clears the cache for the competition workflow status.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		CompetitionWorkflowStatus competitionWorkflowStatus) {

		entityCache.removeResult(
			CompetitionWorkflowStatusImpl.class, competitionWorkflowStatus);
	}

	@Override
	public void clearCache(
		List<CompetitionWorkflowStatus> competitionWorkflowStatuses) {

		for (CompetitionWorkflowStatus competitionWorkflowStatus :
				competitionWorkflowStatuses) {

			entityCache.removeResult(
				CompetitionWorkflowStatusImpl.class, competitionWorkflowStatus);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(CompetitionWorkflowStatusImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				CompetitionWorkflowStatusImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new competition workflow status with the primary key. Does not add the competition workflow status to the database.
	 *
	 * @param competitionWorkflowStatusId the primary key for the new competition workflow status
	 * @return the new competition workflow status
	 */
	@Override
	public CompetitionWorkflowStatus create(long competitionWorkflowStatusId) {
		CompetitionWorkflowStatus competitionWorkflowStatus =
			new CompetitionWorkflowStatusImpl();

		competitionWorkflowStatus.setNew(true);
		competitionWorkflowStatus.setPrimaryKey(competitionWorkflowStatusId);

		return competitionWorkflowStatus;
	}

	/**
	 * Removes the competition workflow status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param competitionWorkflowStatusId the primary key of the competition workflow status
	 * @return the competition workflow status that was removed
	 * @throws NoSuchCompetitionWorkflowStatusException if a competition workflow status with the primary key could not be found
	 */
	@Override
	public CompetitionWorkflowStatus remove(long competitionWorkflowStatusId)
		throws NoSuchCompetitionWorkflowStatusException {

		return remove((Serializable)competitionWorkflowStatusId);
	}

	/**
	 * Removes the competition workflow status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the competition workflow status
	 * @return the competition workflow status that was removed
	 * @throws NoSuchCompetitionWorkflowStatusException if a competition workflow status with the primary key could not be found
	 */
	@Override
	public CompetitionWorkflowStatus remove(Serializable primaryKey)
		throws NoSuchCompetitionWorkflowStatusException {

		Session session = null;

		try {
			session = openSession();

			CompetitionWorkflowStatus competitionWorkflowStatus =
				(CompetitionWorkflowStatus)session.get(
					CompetitionWorkflowStatusImpl.class, primaryKey);

			if (competitionWorkflowStatus == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCompetitionWorkflowStatusException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(competitionWorkflowStatus);
		}
		catch (NoSuchCompetitionWorkflowStatusException noSuchEntityException) {
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
	protected CompetitionWorkflowStatus removeImpl(
		CompetitionWorkflowStatus competitionWorkflowStatus) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(competitionWorkflowStatus)) {
				competitionWorkflowStatus =
					(CompetitionWorkflowStatus)session.get(
						CompetitionWorkflowStatusImpl.class,
						competitionWorkflowStatus.getPrimaryKeyObj());
			}

			if (competitionWorkflowStatus != null) {
				session.delete(competitionWorkflowStatus);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (competitionWorkflowStatus != null) {
			clearCache(competitionWorkflowStatus);
		}

		return competitionWorkflowStatus;
	}

	@Override
	public CompetitionWorkflowStatus updateImpl(
		CompetitionWorkflowStatus competitionWorkflowStatus) {

		boolean isNew = competitionWorkflowStatus.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(competitionWorkflowStatus);
			}
			else {
				competitionWorkflowStatus =
					(CompetitionWorkflowStatus)session.merge(
						competitionWorkflowStatus);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			CompetitionWorkflowStatusImpl.class, competitionWorkflowStatus,
			false, true);

		if (isNew) {
			competitionWorkflowStatus.setNew(false);
		}

		competitionWorkflowStatus.resetOriginalValues();

		return competitionWorkflowStatus;
	}

	/**
	 * Returns the competition workflow status with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the competition workflow status
	 * @return the competition workflow status
	 * @throws NoSuchCompetitionWorkflowStatusException if a competition workflow status with the primary key could not be found
	 */
	@Override
	public CompetitionWorkflowStatus findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCompetitionWorkflowStatusException {

		CompetitionWorkflowStatus competitionWorkflowStatus = fetchByPrimaryKey(
			primaryKey);

		if (competitionWorkflowStatus == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCompetitionWorkflowStatusException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return competitionWorkflowStatus;
	}

	/**
	 * Returns the competition workflow status with the primary key or throws a <code>NoSuchCompetitionWorkflowStatusException</code> if it could not be found.
	 *
	 * @param competitionWorkflowStatusId the primary key of the competition workflow status
	 * @return the competition workflow status
	 * @throws NoSuchCompetitionWorkflowStatusException if a competition workflow status with the primary key could not be found
	 */
	@Override
	public CompetitionWorkflowStatus findByPrimaryKey(
			long competitionWorkflowStatusId)
		throws NoSuchCompetitionWorkflowStatusException {

		return findByPrimaryKey((Serializable)competitionWorkflowStatusId);
	}

	/**
	 * Returns the competition workflow status with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param competitionWorkflowStatusId the primary key of the competition workflow status
	 * @return the competition workflow status, or <code>null</code> if a competition workflow status with the primary key could not be found
	 */
	@Override
	public CompetitionWorkflowStatus fetchByPrimaryKey(
		long competitionWorkflowStatusId) {

		return fetchByPrimaryKey((Serializable)competitionWorkflowStatusId);
	}

	/**
	 * Returns all the competition workflow statuses.
	 *
	 * @return the competition workflow statuses
	 */
	@Override
	public List<CompetitionWorkflowStatus> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the competition workflow statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CompetitionWorkflowStatusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of competition workflow statuses
	 * @param end the upper bound of the range of competition workflow statuses (not inclusive)
	 * @return the range of competition workflow statuses
	 */
	@Override
	public List<CompetitionWorkflowStatus> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the competition workflow statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CompetitionWorkflowStatusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of competition workflow statuses
	 * @param end the upper bound of the range of competition workflow statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of competition workflow statuses
	 */
	@Override
	public List<CompetitionWorkflowStatus> findAll(
		int start, int end,
		OrderByComparator<CompetitionWorkflowStatus> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the competition workflow statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CompetitionWorkflowStatusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of competition workflow statuses
	 * @param end the upper bound of the range of competition workflow statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of competition workflow statuses
	 */
	@Override
	public List<CompetitionWorkflowStatus> findAll(
		int start, int end,
		OrderByComparator<CompetitionWorkflowStatus> orderByComparator,
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

		List<CompetitionWorkflowStatus> list = null;

		if (useFinderCache) {
			list = (List<CompetitionWorkflowStatus>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_COMPETITIONWORKFLOWSTATUS);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_COMPETITIONWORKFLOWSTATUS;

				sql = sql.concat(
					CompetitionWorkflowStatusModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<CompetitionWorkflowStatus>)QueryUtil.list(
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
	 * Removes all the competition workflow statuses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CompetitionWorkflowStatus competitionWorkflowStatus : findAll()) {
			remove(competitionWorkflowStatus);
		}
	}

	/**
	 * Returns the number of competition workflow statuses.
	 *
	 * @return the number of competition workflow statuses
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
					_SQL_COUNT_COMPETITIONWORKFLOWSTATUS);

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
		return "competitionWorkflowStatusId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_COMPETITIONWORKFLOWSTATUS;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CompetitionWorkflowStatusModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the competition workflow status persistence.
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

		CompetitionWorkflowStatusUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		CompetitionWorkflowStatusUtil.setPersistence(null);

		entityCache.removeCache(CompetitionWorkflowStatusImpl.class.getName());
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

	private static final String _SQL_SELECT_COMPETITIONWORKFLOWSTATUS =
		"SELECT competitionWorkflowStatus FROM CompetitionWorkflowStatus competitionWorkflowStatus";

	private static final String _SQL_COUNT_COMPETITIONWORKFLOWSTATUS =
		"SELECT COUNT(competitionWorkflowStatus) FROM CompetitionWorkflowStatus competitionWorkflowStatus";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"competitionWorkflowStatus.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CompetitionWorkflowStatus exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		CompetitionWorkflowStatusPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}