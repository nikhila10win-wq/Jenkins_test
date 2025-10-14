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

import com.mhdsys.schema.exception.NoSuchCertificateVerificationException;
import com.mhdsys.schema.model.CertificateVerification;
import com.mhdsys.schema.model.CertificateVerificationTable;
import com.mhdsys.schema.model.impl.CertificateVerificationImpl;
import com.mhdsys.schema.model.impl.CertificateVerificationModelImpl;
import com.mhdsys.schema.service.persistence.CertificateVerificationPersistence;
import com.mhdsys.schema.service.persistence.CertificateVerificationUtil;
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
 * The persistence implementation for the certificate verification service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = CertificateVerificationPersistence.class)
public class CertificateVerificationPersistenceImpl
	extends BasePersistenceImpl<CertificateVerification>
	implements CertificateVerificationPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CertificateVerificationUtil</code> to access the certificate verification persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CertificateVerificationImpl.class.getName();

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
	 * Returns the certificate verification where userId = &#63; or throws a <code>NoSuchCertificateVerificationException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching certificate verification
	 * @throws NoSuchCertificateVerificationException if a matching certificate verification could not be found
	 */
	@Override
	public CertificateVerification findByUserId(long userId)
		throws NoSuchCertificateVerificationException {

		CertificateVerification certificateVerification = fetchByUserId(userId);

		if (certificateVerification == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("userId=");
			sb.append(userId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchCertificateVerificationException(sb.toString());
		}

		return certificateVerification;
	}

	/**
	 * Returns the certificate verification where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching certificate verification, or <code>null</code> if a matching certificate verification could not be found
	 */
	@Override
	public CertificateVerification fetchByUserId(long userId) {
		return fetchByUserId(userId, true);
	}

	/**
	 * Returns the certificate verification where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching certificate verification, or <code>null</code> if a matching certificate verification could not be found
	 */
	@Override
	public CertificateVerification fetchByUserId(
		long userId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {userId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUserId, finderArgs, this);
		}

		if (result instanceof CertificateVerification) {
			CertificateVerification certificateVerification =
				(CertificateVerification)result;

			if (userId != certificateVerification.getUserId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_CERTIFICATEVERIFICATION_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				List<CertificateVerification> list = query.list();

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
								"CertificateVerificationPersistenceImpl.fetchByUserId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CertificateVerification certificateVerification = list.get(
						0);

					result = certificateVerification;

					cacheResult(certificateVerification);
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
			return (CertificateVerification)result;
		}
	}

	/**
	 * Removes the certificate verification where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the certificate verification that was removed
	 */
	@Override
	public CertificateVerification removeByUserId(long userId)
		throws NoSuchCertificateVerificationException {

		CertificateVerification certificateVerification = findByUserId(userId);

		return remove(certificateVerification);
	}

	/**
	 * Returns the number of certificate verifications where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching certificate verifications
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_CERTIFICATEVERIFICATION_WHERE);

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
		"certificateVerification.userId = ?";

	public CertificateVerificationPersistenceImpl() {
		setModelClass(CertificateVerification.class);

		setModelImplClass(CertificateVerificationImpl.class);
		setModelPKClass(long.class);

		setTable(CertificateVerificationTable.INSTANCE);
	}

	/**
	 * Caches the certificate verification in the entity cache if it is enabled.
	 *
	 * @param certificateVerification the certificate verification
	 */
	@Override
	public void cacheResult(CertificateVerification certificateVerification) {
		entityCache.putResult(
			CertificateVerificationImpl.class,
			certificateVerification.getPrimaryKey(), certificateVerification);

		finderCache.putResult(
			_finderPathFetchByUserId,
			new Object[] {certificateVerification.getUserId()},
			certificateVerification);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the certificate verifications in the entity cache if it is enabled.
	 *
	 * @param certificateVerifications the certificate verifications
	 */
	@Override
	public void cacheResult(
		List<CertificateVerification> certificateVerifications) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (certificateVerifications.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (CertificateVerification certificateVerification :
				certificateVerifications) {

			if (entityCache.getResult(
					CertificateVerificationImpl.class,
					certificateVerification.getPrimaryKey()) == null) {

				cacheResult(certificateVerification);
			}
		}
	}

	/**
	 * Clears the cache for all certificate verifications.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CertificateVerificationImpl.class);

		finderCache.clearCache(CertificateVerificationImpl.class);
	}

	/**
	 * Clears the cache for the certificate verification.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CertificateVerification certificateVerification) {
		entityCache.removeResult(
			CertificateVerificationImpl.class, certificateVerification);
	}

	@Override
	public void clearCache(
		List<CertificateVerification> certificateVerifications) {

		for (CertificateVerification certificateVerification :
				certificateVerifications) {

			entityCache.removeResult(
				CertificateVerificationImpl.class, certificateVerification);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(CertificateVerificationImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				CertificateVerificationImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		CertificateVerificationModelImpl certificateVerificationModelImpl) {

		Object[] args = new Object[] {
			certificateVerificationModelImpl.getUserId()
		};

		finderCache.putResult(_finderPathCountByUserId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByUserId, args, certificateVerificationModelImpl);
	}

	/**
	 * Creates a new certificate verification with the primary key. Does not add the certificate verification to the database.
	 *
	 * @param certificateVerificationId the primary key for the new certificate verification
	 * @return the new certificate verification
	 */
	@Override
	public CertificateVerification create(long certificateVerificationId) {
		CertificateVerification certificateVerification =
			new CertificateVerificationImpl();

		certificateVerification.setNew(true);
		certificateVerification.setPrimaryKey(certificateVerificationId);

		return certificateVerification;
	}

	/**
	 * Removes the certificate verification with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param certificateVerificationId the primary key of the certificate verification
	 * @return the certificate verification that was removed
	 * @throws NoSuchCertificateVerificationException if a certificate verification with the primary key could not be found
	 */
	@Override
	public CertificateVerification remove(long certificateVerificationId)
		throws NoSuchCertificateVerificationException {

		return remove((Serializable)certificateVerificationId);
	}

	/**
	 * Removes the certificate verification with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the certificate verification
	 * @return the certificate verification that was removed
	 * @throws NoSuchCertificateVerificationException if a certificate verification with the primary key could not be found
	 */
	@Override
	public CertificateVerification remove(Serializable primaryKey)
		throws NoSuchCertificateVerificationException {

		Session session = null;

		try {
			session = openSession();

			CertificateVerification certificateVerification =
				(CertificateVerification)session.get(
					CertificateVerificationImpl.class, primaryKey);

			if (certificateVerification == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCertificateVerificationException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(certificateVerification);
		}
		catch (NoSuchCertificateVerificationException noSuchEntityException) {
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
	protected CertificateVerification removeImpl(
		CertificateVerification certificateVerification) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(certificateVerification)) {
				certificateVerification = (CertificateVerification)session.get(
					CertificateVerificationImpl.class,
					certificateVerification.getPrimaryKeyObj());
			}

			if (certificateVerification != null) {
				session.delete(certificateVerification);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (certificateVerification != null) {
			clearCache(certificateVerification);
		}

		return certificateVerification;
	}

	@Override
	public CertificateVerification updateImpl(
		CertificateVerification certificateVerification) {

		boolean isNew = certificateVerification.isNew();

		if (!(certificateVerification instanceof
				CertificateVerificationModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(certificateVerification.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					certificateVerification);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in certificateVerification proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CertificateVerification implementation " +
					certificateVerification.getClass());
		}

		CertificateVerificationModelImpl certificateVerificationModelImpl =
			(CertificateVerificationModelImpl)certificateVerification;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (certificateVerification.getCreateDate() == null)) {
			if (serviceContext == null) {
				certificateVerification.setCreateDate(date);
			}
			else {
				certificateVerification.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!certificateVerificationModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				certificateVerification.setModifiedDate(date);
			}
			else {
				certificateVerification.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(certificateVerification);
			}
			else {
				certificateVerification =
					(CertificateVerification)session.merge(
						certificateVerification);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			CertificateVerificationImpl.class, certificateVerificationModelImpl,
			false, true);

		cacheUniqueFindersCache(certificateVerificationModelImpl);

		if (isNew) {
			certificateVerification.setNew(false);
		}

		certificateVerification.resetOriginalValues();

		return certificateVerification;
	}

	/**
	 * Returns the certificate verification with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the certificate verification
	 * @return the certificate verification
	 * @throws NoSuchCertificateVerificationException if a certificate verification with the primary key could not be found
	 */
	@Override
	public CertificateVerification findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCertificateVerificationException {

		CertificateVerification certificateVerification = fetchByPrimaryKey(
			primaryKey);

		if (certificateVerification == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCertificateVerificationException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return certificateVerification;
	}

	/**
	 * Returns the certificate verification with the primary key or throws a <code>NoSuchCertificateVerificationException</code> if it could not be found.
	 *
	 * @param certificateVerificationId the primary key of the certificate verification
	 * @return the certificate verification
	 * @throws NoSuchCertificateVerificationException if a certificate verification with the primary key could not be found
	 */
	@Override
	public CertificateVerification findByPrimaryKey(
			long certificateVerificationId)
		throws NoSuchCertificateVerificationException {

		return findByPrimaryKey((Serializable)certificateVerificationId);
	}

	/**
	 * Returns the certificate verification with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param certificateVerificationId the primary key of the certificate verification
	 * @return the certificate verification, or <code>null</code> if a certificate verification with the primary key could not be found
	 */
	@Override
	public CertificateVerification fetchByPrimaryKey(
		long certificateVerificationId) {

		return fetchByPrimaryKey((Serializable)certificateVerificationId);
	}

	/**
	 * Returns all the certificate verifications.
	 *
	 * @return the certificate verifications
	 */
	@Override
	public List<CertificateVerification> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the certificate verifications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CertificateVerificationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of certificate verifications
	 * @param end the upper bound of the range of certificate verifications (not inclusive)
	 * @return the range of certificate verifications
	 */
	@Override
	public List<CertificateVerification> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the certificate verifications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CertificateVerificationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of certificate verifications
	 * @param end the upper bound of the range of certificate verifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of certificate verifications
	 */
	@Override
	public List<CertificateVerification> findAll(
		int start, int end,
		OrderByComparator<CertificateVerification> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the certificate verifications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CertificateVerificationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of certificate verifications
	 * @param end the upper bound of the range of certificate verifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of certificate verifications
	 */
	@Override
	public List<CertificateVerification> findAll(
		int start, int end,
		OrderByComparator<CertificateVerification> orderByComparator,
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

		List<CertificateVerification> list = null;

		if (useFinderCache) {
			list = (List<CertificateVerification>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_CERTIFICATEVERIFICATION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_CERTIFICATEVERIFICATION;

				sql = sql.concat(
					CertificateVerificationModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<CertificateVerification>)QueryUtil.list(
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
	 * Removes all the certificate verifications from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CertificateVerification certificateVerification : findAll()) {
			remove(certificateVerification);
		}
	}

	/**
	 * Returns the number of certificate verifications.
	 *
	 * @return the number of certificate verifications
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
					_SQL_COUNT_CERTIFICATEVERIFICATION);

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
		return "certificateVerificationId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_CERTIFICATEVERIFICATION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CertificateVerificationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the certificate verification persistence.
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

		CertificateVerificationUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		CertificateVerificationUtil.setPersistence(null);

		entityCache.removeCache(CertificateVerificationImpl.class.getName());
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

	private static final String _SQL_SELECT_CERTIFICATEVERIFICATION =
		"SELECT certificateVerification FROM CertificateVerification certificateVerification";

	private static final String _SQL_SELECT_CERTIFICATEVERIFICATION_WHERE =
		"SELECT certificateVerification FROM CertificateVerification certificateVerification WHERE ";

	private static final String _SQL_COUNT_CERTIFICATEVERIFICATION =
		"SELECT COUNT(certificateVerification) FROM CertificateVerification certificateVerification";

	private static final String _SQL_COUNT_CERTIFICATEVERIFICATION_WHERE =
		"SELECT COUNT(certificateVerification) FROM CertificateVerification certificateVerification WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"certificateVerification.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CertificateVerification exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CertificateVerification exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CertificateVerificationPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}