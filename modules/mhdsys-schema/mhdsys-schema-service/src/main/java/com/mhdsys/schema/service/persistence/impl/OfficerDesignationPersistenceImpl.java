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

import com.mhdsys.schema.exception.NoSuchOfficerDesignationException;
import com.mhdsys.schema.model.OfficerDesignation;
import com.mhdsys.schema.model.OfficerDesignationTable;
import com.mhdsys.schema.model.impl.OfficerDesignationImpl;
import com.mhdsys.schema.model.impl.OfficerDesignationModelImpl;
import com.mhdsys.schema.service.persistence.OfficerDesignationPersistence;
import com.mhdsys.schema.service.persistence.OfficerDesignationUtil;
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
 * The persistence implementation for the officer designation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = OfficerDesignationPersistence.class)
public class OfficerDesignationPersistenceImpl
	extends BasePersistenceImpl<OfficerDesignation>
	implements OfficerDesignationPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>OfficerDesignationUtil</code> to access the officer designation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		OfficerDesignationImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public OfficerDesignationPersistenceImpl() {
		setModelClass(OfficerDesignation.class);

		setModelImplClass(OfficerDesignationImpl.class);
		setModelPKClass(long.class);

		setTable(OfficerDesignationTable.INSTANCE);
	}

	/**
	 * Caches the officer designation in the entity cache if it is enabled.
	 *
	 * @param officerDesignation the officer designation
	 */
	@Override
	public void cacheResult(OfficerDesignation officerDesignation) {
		entityCache.putResult(
			OfficerDesignationImpl.class, officerDesignation.getPrimaryKey(),
			officerDesignation);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the officer designations in the entity cache if it is enabled.
	 *
	 * @param officerDesignations the officer designations
	 */
	@Override
	public void cacheResult(List<OfficerDesignation> officerDesignations) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (officerDesignations.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (OfficerDesignation officerDesignation : officerDesignations) {
			if (entityCache.getResult(
					OfficerDesignationImpl.class,
					officerDesignation.getPrimaryKey()) == null) {

				cacheResult(officerDesignation);
			}
		}
	}

	/**
	 * Clears the cache for all officer designations.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(OfficerDesignationImpl.class);

		finderCache.clearCache(OfficerDesignationImpl.class);
	}

	/**
	 * Clears the cache for the officer designation.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OfficerDesignation officerDesignation) {
		entityCache.removeResult(
			OfficerDesignationImpl.class, officerDesignation);
	}

	@Override
	public void clearCache(List<OfficerDesignation> officerDesignations) {
		for (OfficerDesignation officerDesignation : officerDesignations) {
			entityCache.removeResult(
				OfficerDesignationImpl.class, officerDesignation);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(OfficerDesignationImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(OfficerDesignationImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new officer designation with the primary key. Does not add the officer designation to the database.
	 *
	 * @param officerDesignationId the primary key for the new officer designation
	 * @return the new officer designation
	 */
	@Override
	public OfficerDesignation create(long officerDesignationId) {
		OfficerDesignation officerDesignation = new OfficerDesignationImpl();

		officerDesignation.setNew(true);
		officerDesignation.setPrimaryKey(officerDesignationId);

		return officerDesignation;
	}

	/**
	 * Removes the officer designation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param officerDesignationId the primary key of the officer designation
	 * @return the officer designation that was removed
	 * @throws NoSuchOfficerDesignationException if a officer designation with the primary key could not be found
	 */
	@Override
	public OfficerDesignation remove(long officerDesignationId)
		throws NoSuchOfficerDesignationException {

		return remove((Serializable)officerDesignationId);
	}

	/**
	 * Removes the officer designation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the officer designation
	 * @return the officer designation that was removed
	 * @throws NoSuchOfficerDesignationException if a officer designation with the primary key could not be found
	 */
	@Override
	public OfficerDesignation remove(Serializable primaryKey)
		throws NoSuchOfficerDesignationException {

		Session session = null;

		try {
			session = openSession();

			OfficerDesignation officerDesignation =
				(OfficerDesignation)session.get(
					OfficerDesignationImpl.class, primaryKey);

			if (officerDesignation == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOfficerDesignationException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(officerDesignation);
		}
		catch (NoSuchOfficerDesignationException noSuchEntityException) {
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
	protected OfficerDesignation removeImpl(
		OfficerDesignation officerDesignation) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(officerDesignation)) {
				officerDesignation = (OfficerDesignation)session.get(
					OfficerDesignationImpl.class,
					officerDesignation.getPrimaryKeyObj());
			}

			if (officerDesignation != null) {
				session.delete(officerDesignation);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (officerDesignation != null) {
			clearCache(officerDesignation);
		}

		return officerDesignation;
	}

	@Override
	public OfficerDesignation updateImpl(
		OfficerDesignation officerDesignation) {

		boolean isNew = officerDesignation.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(officerDesignation);
			}
			else {
				officerDesignation = (OfficerDesignation)session.merge(
					officerDesignation);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			OfficerDesignationImpl.class, officerDesignation, false, true);

		if (isNew) {
			officerDesignation.setNew(false);
		}

		officerDesignation.resetOriginalValues();

		return officerDesignation;
	}

	/**
	 * Returns the officer designation with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the officer designation
	 * @return the officer designation
	 * @throws NoSuchOfficerDesignationException if a officer designation with the primary key could not be found
	 */
	@Override
	public OfficerDesignation findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOfficerDesignationException {

		OfficerDesignation officerDesignation = fetchByPrimaryKey(primaryKey);

		if (officerDesignation == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOfficerDesignationException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return officerDesignation;
	}

	/**
	 * Returns the officer designation with the primary key or throws a <code>NoSuchOfficerDesignationException</code> if it could not be found.
	 *
	 * @param officerDesignationId the primary key of the officer designation
	 * @return the officer designation
	 * @throws NoSuchOfficerDesignationException if a officer designation with the primary key could not be found
	 */
	@Override
	public OfficerDesignation findByPrimaryKey(long officerDesignationId)
		throws NoSuchOfficerDesignationException {

		return findByPrimaryKey((Serializable)officerDesignationId);
	}

	/**
	 * Returns the officer designation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param officerDesignationId the primary key of the officer designation
	 * @return the officer designation, or <code>null</code> if a officer designation with the primary key could not be found
	 */
	@Override
	public OfficerDesignation fetchByPrimaryKey(long officerDesignationId) {
		return fetchByPrimaryKey((Serializable)officerDesignationId);
	}

	/**
	 * Returns all the officer designations.
	 *
	 * @return the officer designations
	 */
	@Override
	public List<OfficerDesignation> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the officer designations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficerDesignationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of officer designations
	 * @param end the upper bound of the range of officer designations (not inclusive)
	 * @return the range of officer designations
	 */
	@Override
	public List<OfficerDesignation> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the officer designations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficerDesignationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of officer designations
	 * @param end the upper bound of the range of officer designations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of officer designations
	 */
	@Override
	public List<OfficerDesignation> findAll(
		int start, int end,
		OrderByComparator<OfficerDesignation> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the officer designations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficerDesignationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of officer designations
	 * @param end the upper bound of the range of officer designations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of officer designations
	 */
	@Override
	public List<OfficerDesignation> findAll(
		int start, int end,
		OrderByComparator<OfficerDesignation> orderByComparator,
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

		List<OfficerDesignation> list = null;

		if (useFinderCache) {
			list = (List<OfficerDesignation>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_OFFICERDESIGNATION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_OFFICERDESIGNATION;

				sql = sql.concat(OfficerDesignationModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<OfficerDesignation>)QueryUtil.list(
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
	 * Removes all the officer designations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (OfficerDesignation officerDesignation : findAll()) {
			remove(officerDesignation);
		}
	}

	/**
	 * Returns the number of officer designations.
	 *
	 * @return the number of officer designations
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
					_SQL_COUNT_OFFICERDESIGNATION);

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
		return "officerDesignationId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_OFFICERDESIGNATION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return OfficerDesignationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the officer designation persistence.
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

		OfficerDesignationUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		OfficerDesignationUtil.setPersistence(null);

		entityCache.removeCache(OfficerDesignationImpl.class.getName());
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

	private static final String _SQL_SELECT_OFFICERDESIGNATION =
		"SELECT officerDesignation FROM OfficerDesignation officerDesignation";

	private static final String _SQL_COUNT_OFFICERDESIGNATION =
		"SELECT COUNT(officerDesignation) FROM OfficerDesignation officerDesignation";

	private static final String _ORDER_BY_ENTITY_ALIAS = "officerDesignation.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No OfficerDesignation exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		OfficerDesignationPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}