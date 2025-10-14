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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import com.mhdsys.schema.exception.NoSuchUnitRegistrationException;
import com.mhdsys.schema.model.UnitRegistration;
import com.mhdsys.schema.model.UnitRegistrationTable;
import com.mhdsys.schema.model.impl.UnitRegistrationImpl;
import com.mhdsys.schema.model.impl.UnitRegistrationModelImpl;
import com.mhdsys.schema.service.persistence.UnitRegistrationPersistence;
import com.mhdsys.schema.service.persistence.UnitRegistrationUtil;
import com.mhdsys.schema.service.persistence.impl.constants.mhdsysPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

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
 * The persistence implementation for the unit registration service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = UnitRegistrationPersistence.class)
public class UnitRegistrationPersistenceImpl
	extends BasePersistenceImpl<UnitRegistration>
	implements UnitRegistrationPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>UnitRegistrationUtil</code> to access the unit registration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		UnitRegistrationImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public UnitRegistrationPersistenceImpl() {
		setModelClass(UnitRegistration.class);

		setModelImplClass(UnitRegistrationImpl.class);
		setModelPKClass(long.class);

		setTable(UnitRegistrationTable.INSTANCE);
	}

	/**
	 * Caches the unit registration in the entity cache if it is enabled.
	 *
	 * @param unitRegistration the unit registration
	 */
	@Override
	public void cacheResult(UnitRegistration unitRegistration) {
		entityCache.putResult(
			UnitRegistrationImpl.class, unitRegistration.getPrimaryKey(),
			unitRegistration);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the unit registrations in the entity cache if it is enabled.
	 *
	 * @param unitRegistrations the unit registrations
	 */
	@Override
	public void cacheResult(List<UnitRegistration> unitRegistrations) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (unitRegistrations.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (UnitRegistration unitRegistration : unitRegistrations) {
			if (entityCache.getResult(
					UnitRegistrationImpl.class,
					unitRegistration.getPrimaryKey()) == null) {

				cacheResult(unitRegistration);
			}
		}
	}

	/**
	 * Clears the cache for all unit registrations.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(UnitRegistrationImpl.class);

		finderCache.clearCache(UnitRegistrationImpl.class);
	}

	/**
	 * Clears the cache for the unit registration.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(UnitRegistration unitRegistration) {
		entityCache.removeResult(UnitRegistrationImpl.class, unitRegistration);
	}

	@Override
	public void clearCache(List<UnitRegistration> unitRegistrations) {
		for (UnitRegistration unitRegistration : unitRegistrations) {
			entityCache.removeResult(
				UnitRegistrationImpl.class, unitRegistration);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(UnitRegistrationImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(UnitRegistrationImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new unit registration with the primary key. Does not add the unit registration to the database.
	 *
	 * @param unitRegistrationId the primary key for the new unit registration
	 * @return the new unit registration
	 */
	@Override
	public UnitRegistration create(long unitRegistrationId) {
		UnitRegistration unitRegistration = new UnitRegistrationImpl();

		unitRegistration.setNew(true);
		unitRegistration.setPrimaryKey(unitRegistrationId);

		return unitRegistration;
	}

	/**
	 * Removes the unit registration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param unitRegistrationId the primary key of the unit registration
	 * @return the unit registration that was removed
	 * @throws NoSuchUnitRegistrationException if a unit registration with the primary key could not be found
	 */
	@Override
	public UnitRegistration remove(long unitRegistrationId)
		throws NoSuchUnitRegistrationException {

		return remove((Serializable)unitRegistrationId);
	}

	/**
	 * Removes the unit registration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the unit registration
	 * @return the unit registration that was removed
	 * @throws NoSuchUnitRegistrationException if a unit registration with the primary key could not be found
	 */
	@Override
	public UnitRegistration remove(Serializable primaryKey)
		throws NoSuchUnitRegistrationException {

		Session session = null;

		try {
			session = openSession();

			UnitRegistration unitRegistration = (UnitRegistration)session.get(
				UnitRegistrationImpl.class, primaryKey);

			if (unitRegistration == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUnitRegistrationException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(unitRegistration);
		}
		catch (NoSuchUnitRegistrationException noSuchEntityException) {
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
	protected UnitRegistration removeImpl(UnitRegistration unitRegistration) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(unitRegistration)) {
				unitRegistration = (UnitRegistration)session.get(
					UnitRegistrationImpl.class,
					unitRegistration.getPrimaryKeyObj());
			}

			if (unitRegistration != null) {
				session.delete(unitRegistration);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (unitRegistration != null) {
			clearCache(unitRegistration);
		}

		return unitRegistration;
	}

	@Override
	public UnitRegistration updateImpl(UnitRegistration unitRegistration) {
		boolean isNew = unitRegistration.isNew();

		if (!(unitRegistration instanceof UnitRegistrationModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(unitRegistration.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					unitRegistration);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in unitRegistration proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom UnitRegistration implementation " +
					unitRegistration.getClass());
		}

		UnitRegistrationModelImpl unitRegistrationModelImpl =
			(UnitRegistrationModelImpl)unitRegistration;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (unitRegistration.getCreateDate() == null)) {
			if (serviceContext == null) {
				unitRegistration.setCreateDate(date);
			}
			else {
				unitRegistration.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!unitRegistrationModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				unitRegistration.setModifiedDate(date);
			}
			else {
				unitRegistration.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(unitRegistration);
			}
			else {
				unitRegistration = (UnitRegistration)session.merge(
					unitRegistration);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			UnitRegistrationImpl.class, unitRegistration, false, true);

		if (isNew) {
			unitRegistration.setNew(false);
		}

		unitRegistration.resetOriginalValues();

		return unitRegistration;
	}

	/**
	 * Returns the unit registration with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the unit registration
	 * @return the unit registration
	 * @throws NoSuchUnitRegistrationException if a unit registration with the primary key could not be found
	 */
	@Override
	public UnitRegistration findByPrimaryKey(Serializable primaryKey)
		throws NoSuchUnitRegistrationException {

		UnitRegistration unitRegistration = fetchByPrimaryKey(primaryKey);

		if (unitRegistration == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchUnitRegistrationException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return unitRegistration;
	}

	/**
	 * Returns the unit registration with the primary key or throws a <code>NoSuchUnitRegistrationException</code> if it could not be found.
	 *
	 * @param unitRegistrationId the primary key of the unit registration
	 * @return the unit registration
	 * @throws NoSuchUnitRegistrationException if a unit registration with the primary key could not be found
	 */
	@Override
	public UnitRegistration findByPrimaryKey(long unitRegistrationId)
		throws NoSuchUnitRegistrationException {

		return findByPrimaryKey((Serializable)unitRegistrationId);
	}

	/**
	 * Returns the unit registration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param unitRegistrationId the primary key of the unit registration
	 * @return the unit registration, or <code>null</code> if a unit registration with the primary key could not be found
	 */
	@Override
	public UnitRegistration fetchByPrimaryKey(long unitRegistrationId) {
		return fetchByPrimaryKey((Serializable)unitRegistrationId);
	}

	/**
	 * Returns all the unit registrations.
	 *
	 * @return the unit registrations
	 */
	@Override
	public List<UnitRegistration> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the unit registrations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UnitRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of unit registrations
	 * @param end the upper bound of the range of unit registrations (not inclusive)
	 * @return the range of unit registrations
	 */
	@Override
	public List<UnitRegistration> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the unit registrations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UnitRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of unit registrations
	 * @param end the upper bound of the range of unit registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of unit registrations
	 */
	@Override
	public List<UnitRegistration> findAll(
		int start, int end,
		OrderByComparator<UnitRegistration> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the unit registrations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UnitRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of unit registrations
	 * @param end the upper bound of the range of unit registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of unit registrations
	 */
	@Override
	public List<UnitRegistration> findAll(
		int start, int end,
		OrderByComparator<UnitRegistration> orderByComparator,
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

		List<UnitRegistration> list = null;

		if (useFinderCache) {
			list = (List<UnitRegistration>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_UNITREGISTRATION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_UNITREGISTRATION;

				sql = sql.concat(UnitRegistrationModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<UnitRegistration>)QueryUtil.list(
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
	 * Removes all the unit registrations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (UnitRegistration unitRegistration : findAll()) {
			remove(unitRegistration);
		}
	}

	/**
	 * Returns the number of unit registrations.
	 *
	 * @return the number of unit registrations
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_UNITREGISTRATION);

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
		return "unitRegistrationId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_UNITREGISTRATION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return UnitRegistrationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the unit registration persistence.
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

		UnitRegistrationUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		UnitRegistrationUtil.setPersistence(null);

		entityCache.removeCache(UnitRegistrationImpl.class.getName());
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

	private static final String _SQL_SELECT_UNITREGISTRATION =
		"SELECT unitRegistration FROM UnitRegistration unitRegistration";

	private static final String _SQL_COUNT_UNITREGISTRATION =
		"SELECT COUNT(unitRegistration) FROM UnitRegistration unitRegistration";

	private static final String _ORDER_BY_ENTITY_ALIAS = "unitRegistration.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No UnitRegistration exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		UnitRegistrationPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}