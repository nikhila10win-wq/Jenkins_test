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

import com.mhdsys.schema.exception.NoSuchServantException;
import com.mhdsys.schema.model.Servant;
import com.mhdsys.schema.model.ServantTable;
import com.mhdsys.schema.model.impl.ServantImpl;
import com.mhdsys.schema.model.impl.ServantModelImpl;
import com.mhdsys.schema.service.persistence.ServantPersistence;
import com.mhdsys.schema.service.persistence.ServantUtil;
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
 * The persistence implementation for the servant service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = ServantPersistence.class)
public class ServantPersistenceImpl
	extends BasePersistenceImpl<Servant> implements ServantPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ServantUtil</code> to access the servant persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ServantImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByTrainingCentreId;
	private FinderPath _finderPathWithoutPaginationFindByTrainingCentreId;
	private FinderPath _finderPathCountByTrainingCentreId;

	/**
	 * Returns all the servants where trainingCentreId = &#63;.
	 *
	 * @param trainingCentreId the training centre ID
	 * @return the matching servants
	 */
	@Override
	public List<Servant> findByTrainingCentreId(long trainingCentreId) {
		return findByTrainingCentreId(
			trainingCentreId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the servants where trainingCentreId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServantModelImpl</code>.
	 * </p>
	 *
	 * @param trainingCentreId the training centre ID
	 * @param start the lower bound of the range of servants
	 * @param end the upper bound of the range of servants (not inclusive)
	 * @return the range of matching servants
	 */
	@Override
	public List<Servant> findByTrainingCentreId(
		long trainingCentreId, int start, int end) {

		return findByTrainingCentreId(trainingCentreId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the servants where trainingCentreId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServantModelImpl</code>.
	 * </p>
	 *
	 * @param trainingCentreId the training centre ID
	 * @param start the lower bound of the range of servants
	 * @param end the upper bound of the range of servants (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching servants
	 */
	@Override
	public List<Servant> findByTrainingCentreId(
		long trainingCentreId, int start, int end,
		OrderByComparator<Servant> orderByComparator) {

		return findByTrainingCentreId(
			trainingCentreId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the servants where trainingCentreId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServantModelImpl</code>.
	 * </p>
	 *
	 * @param trainingCentreId the training centre ID
	 * @param start the lower bound of the range of servants
	 * @param end the upper bound of the range of servants (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching servants
	 */
	@Override
	public List<Servant> findByTrainingCentreId(
		long trainingCentreId, int start, int end,
		OrderByComparator<Servant> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByTrainingCentreId;
				finderArgs = new Object[] {trainingCentreId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByTrainingCentreId;
			finderArgs = new Object[] {
				trainingCentreId, start, end, orderByComparator
			};
		}

		List<Servant> list = null;

		if (useFinderCache) {
			list = (List<Servant>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Servant servant : list) {
					if (trainingCentreId != servant.getTrainingCentreId()) {
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

			sb.append(_SQL_SELECT_SERVANT_WHERE);

			sb.append(_FINDER_COLUMN_TRAININGCENTREID_TRAININGCENTREID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ServantModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(trainingCentreId);

				list = (List<Servant>)QueryUtil.list(
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
	 * Returns the first servant in the ordered set where trainingCentreId = &#63;.
	 *
	 * @param trainingCentreId the training centre ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching servant
	 * @throws NoSuchServantException if a matching servant could not be found
	 */
	@Override
	public Servant findByTrainingCentreId_First(
			long trainingCentreId, OrderByComparator<Servant> orderByComparator)
		throws NoSuchServantException {

		Servant servant = fetchByTrainingCentreId_First(
			trainingCentreId, orderByComparator);

		if (servant != null) {
			return servant;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("trainingCentreId=");
		sb.append(trainingCentreId);

		sb.append("}");

		throw new NoSuchServantException(sb.toString());
	}

	/**
	 * Returns the first servant in the ordered set where trainingCentreId = &#63;.
	 *
	 * @param trainingCentreId the training centre ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching servant, or <code>null</code> if a matching servant could not be found
	 */
	@Override
	public Servant fetchByTrainingCentreId_First(
		long trainingCentreId, OrderByComparator<Servant> orderByComparator) {

		List<Servant> list = findByTrainingCentreId(
			trainingCentreId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last servant in the ordered set where trainingCentreId = &#63;.
	 *
	 * @param trainingCentreId the training centre ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching servant
	 * @throws NoSuchServantException if a matching servant could not be found
	 */
	@Override
	public Servant findByTrainingCentreId_Last(
			long trainingCentreId, OrderByComparator<Servant> orderByComparator)
		throws NoSuchServantException {

		Servant servant = fetchByTrainingCentreId_Last(
			trainingCentreId, orderByComparator);

		if (servant != null) {
			return servant;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("trainingCentreId=");
		sb.append(trainingCentreId);

		sb.append("}");

		throw new NoSuchServantException(sb.toString());
	}

	/**
	 * Returns the last servant in the ordered set where trainingCentreId = &#63;.
	 *
	 * @param trainingCentreId the training centre ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching servant, or <code>null</code> if a matching servant could not be found
	 */
	@Override
	public Servant fetchByTrainingCentreId_Last(
		long trainingCentreId, OrderByComparator<Servant> orderByComparator) {

		int count = countByTrainingCentreId(trainingCentreId);

		if (count == 0) {
			return null;
		}

		List<Servant> list = findByTrainingCentreId(
			trainingCentreId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the servants before and after the current servant in the ordered set where trainingCentreId = &#63;.
	 *
	 * @param servantId the primary key of the current servant
	 * @param trainingCentreId the training centre ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next servant
	 * @throws NoSuchServantException if a servant with the primary key could not be found
	 */
	@Override
	public Servant[] findByTrainingCentreId_PrevAndNext(
			long servantId, long trainingCentreId,
			OrderByComparator<Servant> orderByComparator)
		throws NoSuchServantException {

		Servant servant = findByPrimaryKey(servantId);

		Session session = null;

		try {
			session = openSession();

			Servant[] array = new ServantImpl[3];

			array[0] = getByTrainingCentreId_PrevAndNext(
				session, servant, trainingCentreId, orderByComparator, true);

			array[1] = servant;

			array[2] = getByTrainingCentreId_PrevAndNext(
				session, servant, trainingCentreId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Servant getByTrainingCentreId_PrevAndNext(
		Session session, Servant servant, long trainingCentreId,
		OrderByComparator<Servant> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_SERVANT_WHERE);

		sb.append(_FINDER_COLUMN_TRAININGCENTREID_TRAININGCENTREID_2);

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
			sb.append(ServantModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(trainingCentreId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(servant)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Servant> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the servants where trainingCentreId = &#63; from the database.
	 *
	 * @param trainingCentreId the training centre ID
	 */
	@Override
	public void removeByTrainingCentreId(long trainingCentreId) {
		for (Servant servant :
				findByTrainingCentreId(
					trainingCentreId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(servant);
		}
	}

	/**
	 * Returns the number of servants where trainingCentreId = &#63;.
	 *
	 * @param trainingCentreId the training centre ID
	 * @return the number of matching servants
	 */
	@Override
	public int countByTrainingCentreId(long trainingCentreId) {
		FinderPath finderPath = _finderPathCountByTrainingCentreId;

		Object[] finderArgs = new Object[] {trainingCentreId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SERVANT_WHERE);

			sb.append(_FINDER_COLUMN_TRAININGCENTREID_TRAININGCENTREID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(trainingCentreId);

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
		_FINDER_COLUMN_TRAININGCENTREID_TRAININGCENTREID_2 =
			"servant.trainingCentreId = ?";

	public ServantPersistenceImpl() {
		setModelClass(Servant.class);

		setModelImplClass(ServantImpl.class);
		setModelPKClass(long.class);

		setTable(ServantTable.INSTANCE);
	}

	/**
	 * Caches the servant in the entity cache if it is enabled.
	 *
	 * @param servant the servant
	 */
	@Override
	public void cacheResult(Servant servant) {
		entityCache.putResult(
			ServantImpl.class, servant.getPrimaryKey(), servant);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the servants in the entity cache if it is enabled.
	 *
	 * @param servants the servants
	 */
	@Override
	public void cacheResult(List<Servant> servants) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (servants.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Servant servant : servants) {
			if (entityCache.getResult(
					ServantImpl.class, servant.getPrimaryKey()) == null) {

				cacheResult(servant);
			}
		}
	}

	/**
	 * Clears the cache for all servants.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ServantImpl.class);

		finderCache.clearCache(ServantImpl.class);
	}

	/**
	 * Clears the cache for the servant.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Servant servant) {
		entityCache.removeResult(ServantImpl.class, servant);
	}

	@Override
	public void clearCache(List<Servant> servants) {
		for (Servant servant : servants) {
			entityCache.removeResult(ServantImpl.class, servant);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ServantImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ServantImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new servant with the primary key. Does not add the servant to the database.
	 *
	 * @param servantId the primary key for the new servant
	 * @return the new servant
	 */
	@Override
	public Servant create(long servantId) {
		Servant servant = new ServantImpl();

		servant.setNew(true);
		servant.setPrimaryKey(servantId);

		return servant;
	}

	/**
	 * Removes the servant with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param servantId the primary key of the servant
	 * @return the servant that was removed
	 * @throws NoSuchServantException if a servant with the primary key could not be found
	 */
	@Override
	public Servant remove(long servantId) throws NoSuchServantException {
		return remove((Serializable)servantId);
	}

	/**
	 * Removes the servant with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the servant
	 * @return the servant that was removed
	 * @throws NoSuchServantException if a servant with the primary key could not be found
	 */
	@Override
	public Servant remove(Serializable primaryKey)
		throws NoSuchServantException {

		Session session = null;

		try {
			session = openSession();

			Servant servant = (Servant)session.get(
				ServantImpl.class, primaryKey);

			if (servant == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchServantException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(servant);
		}
		catch (NoSuchServantException noSuchEntityException) {
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
	protected Servant removeImpl(Servant servant) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(servant)) {
				servant = (Servant)session.get(
					ServantImpl.class, servant.getPrimaryKeyObj());
			}

			if (servant != null) {
				session.delete(servant);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (servant != null) {
			clearCache(servant);
		}

		return servant;
	}

	@Override
	public Servant updateImpl(Servant servant) {
		boolean isNew = servant.isNew();

		if (!(servant instanceof ServantModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(servant.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(servant);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in servant proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Servant implementation " +
					servant.getClass());
		}

		ServantModelImpl servantModelImpl = (ServantModelImpl)servant;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (servant.getCreateDate() == null)) {
			if (serviceContext == null) {
				servant.setCreateDate(date);
			}
			else {
				servant.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!servantModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				servant.setModifiedDate(date);
			}
			else {
				servant.setModifiedDate(serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(servant);
			}
			else {
				servant = (Servant)session.merge(servant);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(ServantImpl.class, servantModelImpl, false, true);

		if (isNew) {
			servant.setNew(false);
		}

		servant.resetOriginalValues();

		return servant;
	}

	/**
	 * Returns the servant with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the servant
	 * @return the servant
	 * @throws NoSuchServantException if a servant with the primary key could not be found
	 */
	@Override
	public Servant findByPrimaryKey(Serializable primaryKey)
		throws NoSuchServantException {

		Servant servant = fetchByPrimaryKey(primaryKey);

		if (servant == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchServantException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return servant;
	}

	/**
	 * Returns the servant with the primary key or throws a <code>NoSuchServantException</code> if it could not be found.
	 *
	 * @param servantId the primary key of the servant
	 * @return the servant
	 * @throws NoSuchServantException if a servant with the primary key could not be found
	 */
	@Override
	public Servant findByPrimaryKey(long servantId)
		throws NoSuchServantException {

		return findByPrimaryKey((Serializable)servantId);
	}

	/**
	 * Returns the servant with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param servantId the primary key of the servant
	 * @return the servant, or <code>null</code> if a servant with the primary key could not be found
	 */
	@Override
	public Servant fetchByPrimaryKey(long servantId) {
		return fetchByPrimaryKey((Serializable)servantId);
	}

	/**
	 * Returns all the servants.
	 *
	 * @return the servants
	 */
	@Override
	public List<Servant> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the servants.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServantModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of servants
	 * @param end the upper bound of the range of servants (not inclusive)
	 * @return the range of servants
	 */
	@Override
	public List<Servant> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the servants.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServantModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of servants
	 * @param end the upper bound of the range of servants (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of servants
	 */
	@Override
	public List<Servant> findAll(
		int start, int end, OrderByComparator<Servant> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the servants.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServantModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of servants
	 * @param end the upper bound of the range of servants (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of servants
	 */
	@Override
	public List<Servant> findAll(
		int start, int end, OrderByComparator<Servant> orderByComparator,
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

		List<Servant> list = null;

		if (useFinderCache) {
			list = (List<Servant>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SERVANT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SERVANT;

				sql = sql.concat(ServantModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Servant>)QueryUtil.list(
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
	 * Removes all the servants from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Servant servant : findAll()) {
			remove(servant);
		}
	}

	/**
	 * Returns the number of servants.
	 *
	 * @return the number of servants
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SERVANT);

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
		return "servantId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SERVANT;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ServantModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the servant persistence.
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

		_finderPathWithPaginationFindByTrainingCentreId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTrainingCentreId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"trainingCentreId"}, true);

		_finderPathWithoutPaginationFindByTrainingCentreId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTrainingCentreId",
			new String[] {Long.class.getName()},
			new String[] {"trainingCentreId"}, true);

		_finderPathCountByTrainingCentreId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByTrainingCentreId", new String[] {Long.class.getName()},
			new String[] {"trainingCentreId"}, false);

		ServantUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		ServantUtil.setPersistence(null);

		entityCache.removeCache(ServantImpl.class.getName());
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

	private static final String _SQL_SELECT_SERVANT =
		"SELECT servant FROM Servant servant";

	private static final String _SQL_SELECT_SERVANT_WHERE =
		"SELECT servant FROM Servant servant WHERE ";

	private static final String _SQL_COUNT_SERVANT =
		"SELECT COUNT(servant) FROM Servant servant";

	private static final String _SQL_COUNT_SERVANT_WHERE =
		"SELECT COUNT(servant) FROM Servant servant WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "servant.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Servant exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Servant exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ServantPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}