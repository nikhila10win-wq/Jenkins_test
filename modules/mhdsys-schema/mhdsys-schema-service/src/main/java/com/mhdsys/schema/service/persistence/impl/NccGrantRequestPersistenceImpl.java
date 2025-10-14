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

import com.mhdsys.schema.exception.NoSuchNccGrantRequestException;
import com.mhdsys.schema.model.NccGrantRequest;
import com.mhdsys.schema.model.NccGrantRequestTable;
import com.mhdsys.schema.model.impl.NccGrantRequestImpl;
import com.mhdsys.schema.model.impl.NccGrantRequestModelImpl;
import com.mhdsys.schema.service.persistence.NccGrantRequestPersistence;
import com.mhdsys.schema.service.persistence.NccGrantRequestUtil;
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
 * The persistence implementation for the ncc grant request service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = NccGrantRequestPersistence.class)
public class NccGrantRequestPersistenceImpl
	extends BasePersistenceImpl<NccGrantRequest>
	implements NccGrantRequestPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>NccGrantRequestUtil</code> to access the ncc grant request persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		NccGrantRequestImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public NccGrantRequestPersistenceImpl() {
		setModelClass(NccGrantRequest.class);

		setModelImplClass(NccGrantRequestImpl.class);
		setModelPKClass(long.class);

		setTable(NccGrantRequestTable.INSTANCE);
	}

	/**
	 * Caches the ncc grant request in the entity cache if it is enabled.
	 *
	 * @param nccGrantRequest the ncc grant request
	 */
	@Override
	public void cacheResult(NccGrantRequest nccGrantRequest) {
		entityCache.putResult(
			NccGrantRequestImpl.class, nccGrantRequest.getPrimaryKey(),
			nccGrantRequest);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the ncc grant requests in the entity cache if it is enabled.
	 *
	 * @param nccGrantRequests the ncc grant requests
	 */
	@Override
	public void cacheResult(List<NccGrantRequest> nccGrantRequests) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (nccGrantRequests.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (NccGrantRequest nccGrantRequest : nccGrantRequests) {
			if (entityCache.getResult(
					NccGrantRequestImpl.class,
					nccGrantRequest.getPrimaryKey()) == null) {

				cacheResult(nccGrantRequest);
			}
		}
	}

	/**
	 * Clears the cache for all ncc grant requests.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(NccGrantRequestImpl.class);

		finderCache.clearCache(NccGrantRequestImpl.class);
	}

	/**
	 * Clears the cache for the ncc grant request.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(NccGrantRequest nccGrantRequest) {
		entityCache.removeResult(NccGrantRequestImpl.class, nccGrantRequest);
	}

	@Override
	public void clearCache(List<NccGrantRequest> nccGrantRequests) {
		for (NccGrantRequest nccGrantRequest : nccGrantRequests) {
			entityCache.removeResult(
				NccGrantRequestImpl.class, nccGrantRequest);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(NccGrantRequestImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(NccGrantRequestImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new ncc grant request with the primary key. Does not add the ncc grant request to the database.
	 *
	 * @param nccGrantRequestId the primary key for the new ncc grant request
	 * @return the new ncc grant request
	 */
	@Override
	public NccGrantRequest create(long nccGrantRequestId) {
		NccGrantRequest nccGrantRequest = new NccGrantRequestImpl();

		nccGrantRequest.setNew(true);
		nccGrantRequest.setPrimaryKey(nccGrantRequestId);

		return nccGrantRequest;
	}

	/**
	 * Removes the ncc grant request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param nccGrantRequestId the primary key of the ncc grant request
	 * @return the ncc grant request that was removed
	 * @throws NoSuchNccGrantRequestException if a ncc grant request with the primary key could not be found
	 */
	@Override
	public NccGrantRequest remove(long nccGrantRequestId)
		throws NoSuchNccGrantRequestException {

		return remove((Serializable)nccGrantRequestId);
	}

	/**
	 * Removes the ncc grant request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ncc grant request
	 * @return the ncc grant request that was removed
	 * @throws NoSuchNccGrantRequestException if a ncc grant request with the primary key could not be found
	 */
	@Override
	public NccGrantRequest remove(Serializable primaryKey)
		throws NoSuchNccGrantRequestException {

		Session session = null;

		try {
			session = openSession();

			NccGrantRequest nccGrantRequest = (NccGrantRequest)session.get(
				NccGrantRequestImpl.class, primaryKey);

			if (nccGrantRequest == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNccGrantRequestException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(nccGrantRequest);
		}
		catch (NoSuchNccGrantRequestException noSuchEntityException) {
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
	protected NccGrantRequest removeImpl(NccGrantRequest nccGrantRequest) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(nccGrantRequest)) {
				nccGrantRequest = (NccGrantRequest)session.get(
					NccGrantRequestImpl.class,
					nccGrantRequest.getPrimaryKeyObj());
			}

			if (nccGrantRequest != null) {
				session.delete(nccGrantRequest);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (nccGrantRequest != null) {
			clearCache(nccGrantRequest);
		}

		return nccGrantRequest;
	}

	@Override
	public NccGrantRequest updateImpl(NccGrantRequest nccGrantRequest) {
		boolean isNew = nccGrantRequest.isNew();

		if (!(nccGrantRequest instanceof NccGrantRequestModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(nccGrantRequest.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					nccGrantRequest);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in nccGrantRequest proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom NccGrantRequest implementation " +
					nccGrantRequest.getClass());
		}

		NccGrantRequestModelImpl nccGrantRequestModelImpl =
			(NccGrantRequestModelImpl)nccGrantRequest;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (nccGrantRequest.getCreateDate() == null)) {
			if (serviceContext == null) {
				nccGrantRequest.setCreateDate(date);
			}
			else {
				nccGrantRequest.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!nccGrantRequestModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				nccGrantRequest.setModifiedDate(date);
			}
			else {
				nccGrantRequest.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(nccGrantRequest);
			}
			else {
				nccGrantRequest = (NccGrantRequest)session.merge(
					nccGrantRequest);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			NccGrantRequestImpl.class, nccGrantRequest, false, true);

		if (isNew) {
			nccGrantRequest.setNew(false);
		}

		nccGrantRequest.resetOriginalValues();

		return nccGrantRequest;
	}

	/**
	 * Returns the ncc grant request with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ncc grant request
	 * @return the ncc grant request
	 * @throws NoSuchNccGrantRequestException if a ncc grant request with the primary key could not be found
	 */
	@Override
	public NccGrantRequest findByPrimaryKey(Serializable primaryKey)
		throws NoSuchNccGrantRequestException {

		NccGrantRequest nccGrantRequest = fetchByPrimaryKey(primaryKey);

		if (nccGrantRequest == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNccGrantRequestException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return nccGrantRequest;
	}

	/**
	 * Returns the ncc grant request with the primary key or throws a <code>NoSuchNccGrantRequestException</code> if it could not be found.
	 *
	 * @param nccGrantRequestId the primary key of the ncc grant request
	 * @return the ncc grant request
	 * @throws NoSuchNccGrantRequestException if a ncc grant request with the primary key could not be found
	 */
	@Override
	public NccGrantRequest findByPrimaryKey(long nccGrantRequestId)
		throws NoSuchNccGrantRequestException {

		return findByPrimaryKey((Serializable)nccGrantRequestId);
	}

	/**
	 * Returns the ncc grant request with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param nccGrantRequestId the primary key of the ncc grant request
	 * @return the ncc grant request, or <code>null</code> if a ncc grant request with the primary key could not be found
	 */
	@Override
	public NccGrantRequest fetchByPrimaryKey(long nccGrantRequestId) {
		return fetchByPrimaryKey((Serializable)nccGrantRequestId);
	}

	/**
	 * Returns all the ncc grant requests.
	 *
	 * @return the ncc grant requests
	 */
	@Override
	public List<NccGrantRequest> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ncc grant requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NccGrantRequestModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ncc grant requests
	 * @param end the upper bound of the range of ncc grant requests (not inclusive)
	 * @return the range of ncc grant requests
	 */
	@Override
	public List<NccGrantRequest> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ncc grant requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NccGrantRequestModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ncc grant requests
	 * @param end the upper bound of the range of ncc grant requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ncc grant requests
	 */
	@Override
	public List<NccGrantRequest> findAll(
		int start, int end,
		OrderByComparator<NccGrantRequest> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ncc grant requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NccGrantRequestModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ncc grant requests
	 * @param end the upper bound of the range of ncc grant requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of ncc grant requests
	 */
	@Override
	public List<NccGrantRequest> findAll(
		int start, int end,
		OrderByComparator<NccGrantRequest> orderByComparator,
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

		List<NccGrantRequest> list = null;

		if (useFinderCache) {
			list = (List<NccGrantRequest>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_NCCGRANTREQUEST);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_NCCGRANTREQUEST;

				sql = sql.concat(NccGrantRequestModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<NccGrantRequest>)QueryUtil.list(
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
	 * Removes all the ncc grant requests from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (NccGrantRequest nccGrantRequest : findAll()) {
			remove(nccGrantRequest);
		}
	}

	/**
	 * Returns the number of ncc grant requests.
	 *
	 * @return the number of ncc grant requests
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_NCCGRANTREQUEST);

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
		return "nccGrantRequestId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_NCCGRANTREQUEST;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return NccGrantRequestModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ncc grant request persistence.
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

		NccGrantRequestUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		NccGrantRequestUtil.setPersistence(null);

		entityCache.removeCache(NccGrantRequestImpl.class.getName());
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

	private static final String _SQL_SELECT_NCCGRANTREQUEST =
		"SELECT nccGrantRequest FROM NccGrantRequest nccGrantRequest";

	private static final String _SQL_COUNT_NCCGRANTREQUEST =
		"SELECT COUNT(nccGrantRequest) FROM NccGrantRequest nccGrantRequest";

	private static final String _ORDER_BY_ENTITY_ALIAS = "nccGrantRequest.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No NccGrantRequest exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		NccGrantRequestPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}