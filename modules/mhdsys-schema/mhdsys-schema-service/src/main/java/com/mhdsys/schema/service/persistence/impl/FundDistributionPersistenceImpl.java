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

import com.mhdsys.schema.exception.NoSuchFundDistributionException;
import com.mhdsys.schema.model.FundDistribution;
import com.mhdsys.schema.model.FundDistributionTable;
import com.mhdsys.schema.model.impl.FundDistributionImpl;
import com.mhdsys.schema.model.impl.FundDistributionModelImpl;
import com.mhdsys.schema.service.persistence.FundDistributionPersistence;
import com.mhdsys.schema.service.persistence.FundDistributionUtil;
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
 * The persistence implementation for the fund distribution service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = FundDistributionPersistence.class)
public class FundDistributionPersistenceImpl
	extends BasePersistenceImpl<FundDistribution>
	implements FundDistributionPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>FundDistributionUtil</code> to access the fund distribution persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		FundDistributionImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public FundDistributionPersistenceImpl() {
		setModelClass(FundDistribution.class);

		setModelImplClass(FundDistributionImpl.class);
		setModelPKClass(long.class);

		setTable(FundDistributionTable.INSTANCE);
	}

	/**
	 * Caches the fund distribution in the entity cache if it is enabled.
	 *
	 * @param fundDistribution the fund distribution
	 */
	@Override
	public void cacheResult(FundDistribution fundDistribution) {
		entityCache.putResult(
			FundDistributionImpl.class, fundDistribution.getPrimaryKey(),
			fundDistribution);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the fund distributions in the entity cache if it is enabled.
	 *
	 * @param fundDistributions the fund distributions
	 */
	@Override
	public void cacheResult(List<FundDistribution> fundDistributions) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (fundDistributions.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (FundDistribution fundDistribution : fundDistributions) {
			if (entityCache.getResult(
					FundDistributionImpl.class,
					fundDistribution.getPrimaryKey()) == null) {

				cacheResult(fundDistribution);
			}
		}
	}

	/**
	 * Clears the cache for all fund distributions.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(FundDistributionImpl.class);

		finderCache.clearCache(FundDistributionImpl.class);
	}

	/**
	 * Clears the cache for the fund distribution.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(FundDistribution fundDistribution) {
		entityCache.removeResult(FundDistributionImpl.class, fundDistribution);
	}

	@Override
	public void clearCache(List<FundDistribution> fundDistributions) {
		for (FundDistribution fundDistribution : fundDistributions) {
			entityCache.removeResult(
				FundDistributionImpl.class, fundDistribution);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FundDistributionImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(FundDistributionImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new fund distribution with the primary key. Does not add the fund distribution to the database.
	 *
	 * @param fundDistributionId the primary key for the new fund distribution
	 * @return the new fund distribution
	 */
	@Override
	public FundDistribution create(long fundDistributionId) {
		FundDistribution fundDistribution = new FundDistributionImpl();

		fundDistribution.setNew(true);
		fundDistribution.setPrimaryKey(fundDistributionId);

		return fundDistribution;
	}

	/**
	 * Removes the fund distribution with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fundDistributionId the primary key of the fund distribution
	 * @return the fund distribution that was removed
	 * @throws NoSuchFundDistributionException if a fund distribution with the primary key could not be found
	 */
	@Override
	public FundDistribution remove(long fundDistributionId)
		throws NoSuchFundDistributionException {

		return remove((Serializable)fundDistributionId);
	}

	/**
	 * Removes the fund distribution with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the fund distribution
	 * @return the fund distribution that was removed
	 * @throws NoSuchFundDistributionException if a fund distribution with the primary key could not be found
	 */
	@Override
	public FundDistribution remove(Serializable primaryKey)
		throws NoSuchFundDistributionException {

		Session session = null;

		try {
			session = openSession();

			FundDistribution fundDistribution = (FundDistribution)session.get(
				FundDistributionImpl.class, primaryKey);

			if (fundDistribution == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFundDistributionException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(fundDistribution);
		}
		catch (NoSuchFundDistributionException noSuchEntityException) {
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
	protected FundDistribution removeImpl(FundDistribution fundDistribution) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(fundDistribution)) {
				fundDistribution = (FundDistribution)session.get(
					FundDistributionImpl.class,
					fundDistribution.getPrimaryKeyObj());
			}

			if (fundDistribution != null) {
				session.delete(fundDistribution);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (fundDistribution != null) {
			clearCache(fundDistribution);
		}

		return fundDistribution;
	}

	@Override
	public FundDistribution updateImpl(FundDistribution fundDistribution) {
		boolean isNew = fundDistribution.isNew();

		if (!(fundDistribution instanceof FundDistributionModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(fundDistribution.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					fundDistribution);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in fundDistribution proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom FundDistribution implementation " +
					fundDistribution.getClass());
		}

		FundDistributionModelImpl fundDistributionModelImpl =
			(FundDistributionModelImpl)fundDistribution;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (fundDistribution.getCreateDate() == null)) {
			if (serviceContext == null) {
				fundDistribution.setCreateDate(date);
			}
			else {
				fundDistribution.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!fundDistributionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				fundDistribution.setModifiedDate(date);
			}
			else {
				fundDistribution.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(fundDistribution);
			}
			else {
				fundDistribution = (FundDistribution)session.merge(
					fundDistribution);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			FundDistributionImpl.class, fundDistribution, false, true);

		if (isNew) {
			fundDistribution.setNew(false);
		}

		fundDistribution.resetOriginalValues();

		return fundDistribution;
	}

	/**
	 * Returns the fund distribution with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the fund distribution
	 * @return the fund distribution
	 * @throws NoSuchFundDistributionException if a fund distribution with the primary key could not be found
	 */
	@Override
	public FundDistribution findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFundDistributionException {

		FundDistribution fundDistribution = fetchByPrimaryKey(primaryKey);

		if (fundDistribution == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFundDistributionException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return fundDistribution;
	}

	/**
	 * Returns the fund distribution with the primary key or throws a <code>NoSuchFundDistributionException</code> if it could not be found.
	 *
	 * @param fundDistributionId the primary key of the fund distribution
	 * @return the fund distribution
	 * @throws NoSuchFundDistributionException if a fund distribution with the primary key could not be found
	 */
	@Override
	public FundDistribution findByPrimaryKey(long fundDistributionId)
		throws NoSuchFundDistributionException {

		return findByPrimaryKey((Serializable)fundDistributionId);
	}

	/**
	 * Returns the fund distribution with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fundDistributionId the primary key of the fund distribution
	 * @return the fund distribution, or <code>null</code> if a fund distribution with the primary key could not be found
	 */
	@Override
	public FundDistribution fetchByPrimaryKey(long fundDistributionId) {
		return fetchByPrimaryKey((Serializable)fundDistributionId);
	}

	/**
	 * Returns all the fund distributions.
	 *
	 * @return the fund distributions
	 */
	@Override
	public List<FundDistribution> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the fund distributions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FundDistributionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fund distributions
	 * @param end the upper bound of the range of fund distributions (not inclusive)
	 * @return the range of fund distributions
	 */
	@Override
	public List<FundDistribution> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the fund distributions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FundDistributionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fund distributions
	 * @param end the upper bound of the range of fund distributions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of fund distributions
	 */
	@Override
	public List<FundDistribution> findAll(
		int start, int end,
		OrderByComparator<FundDistribution> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the fund distributions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FundDistributionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fund distributions
	 * @param end the upper bound of the range of fund distributions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of fund distributions
	 */
	@Override
	public List<FundDistribution> findAll(
		int start, int end,
		OrderByComparator<FundDistribution> orderByComparator,
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

		List<FundDistribution> list = null;

		if (useFinderCache) {
			list = (List<FundDistribution>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_FUNDDISTRIBUTION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_FUNDDISTRIBUTION;

				sql = sql.concat(FundDistributionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<FundDistribution>)QueryUtil.list(
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
	 * Removes all the fund distributions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (FundDistribution fundDistribution : findAll()) {
			remove(fundDistribution);
		}
	}

	/**
	 * Returns the number of fund distributions.
	 *
	 * @return the number of fund distributions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_FUNDDISTRIBUTION);

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
		return "fundDistributionId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_FUNDDISTRIBUTION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return FundDistributionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the fund distribution persistence.
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

		FundDistributionUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		FundDistributionUtil.setPersistence(null);

		entityCache.removeCache(FundDistributionImpl.class.getName());
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

	private static final String _SQL_SELECT_FUNDDISTRIBUTION =
		"SELECT fundDistribution FROM FundDistribution fundDistribution";

	private static final String _SQL_COUNT_FUNDDISTRIBUTION =
		"SELECT COUNT(fundDistribution) FROM FundDistribution fundDistribution";

	private static final String _ORDER_BY_ENTITY_ALIAS = "fundDistribution.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No FundDistribution exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		FundDistributionPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}