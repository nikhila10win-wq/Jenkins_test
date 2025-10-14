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

import com.mhdsys.schema.exception.NoSuchMobileSessionException;
import com.mhdsys.schema.model.MobileSession;
import com.mhdsys.schema.model.MobileSessionTable;
import com.mhdsys.schema.model.impl.MobileSessionImpl;
import com.mhdsys.schema.model.impl.MobileSessionModelImpl;
import com.mhdsys.schema.service.persistence.MobileSessionPersistence;
import com.mhdsys.schema.service.persistence.MobileSessionUtil;
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
 * The persistence implementation for the mobile session service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = MobileSessionPersistence.class)
public class MobileSessionPersistenceImpl
	extends BasePersistenceImpl<MobileSession>
	implements MobileSessionPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>MobileSessionUtil</code> to access the mobile session persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		MobileSessionImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchBysidAndUserId;
	private FinderPath _finderPathCountBysidAndUserId;

	/**
	 * Returns the mobile session where sid = &#63; and userId = &#63; or throws a <code>NoSuchMobileSessionException</code> if it could not be found.
	 *
	 * @param sid the sid
	 * @param userId the user ID
	 * @return the matching mobile session
	 * @throws NoSuchMobileSessionException if a matching mobile session could not be found
	 */
	@Override
	public MobileSession findBysidAndUserId(long sid, long userId)
		throws NoSuchMobileSessionException {

		MobileSession mobileSession = fetchBysidAndUserId(sid, userId);

		if (mobileSession == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("sid=");
			sb.append(sid);

			sb.append(", userId=");
			sb.append(userId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchMobileSessionException(sb.toString());
		}

		return mobileSession;
	}

	/**
	 * Returns the mobile session where sid = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param sid the sid
	 * @param userId the user ID
	 * @return the matching mobile session, or <code>null</code> if a matching mobile session could not be found
	 */
	@Override
	public MobileSession fetchBysidAndUserId(long sid, long userId) {
		return fetchBysidAndUserId(sid, userId, true);
	}

	/**
	 * Returns the mobile session where sid = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param sid the sid
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching mobile session, or <code>null</code> if a matching mobile session could not be found
	 */
	@Override
	public MobileSession fetchBysidAndUserId(
		long sid, long userId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {sid, userId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchBysidAndUserId, finderArgs, this);
		}

		if (result instanceof MobileSession) {
			MobileSession mobileSession = (MobileSession)result;

			if ((sid != mobileSession.getSid()) ||
				(userId != mobileSession.getUserId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_MOBILESESSION_WHERE);

			sb.append(_FINDER_COLUMN_SIDANDUSERID_SID_2);

			sb.append(_FINDER_COLUMN_SIDANDUSERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(sid);

				queryPos.add(userId);

				List<MobileSession> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchBysidAndUserId, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {sid, userId};
							}

							_log.warn(
								"MobileSessionPersistenceImpl.fetchBysidAndUserId(long, long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					MobileSession mobileSession = list.get(0);

					result = mobileSession;

					cacheResult(mobileSession);
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
			return (MobileSession)result;
		}
	}

	/**
	 * Removes the mobile session where sid = &#63; and userId = &#63; from the database.
	 *
	 * @param sid the sid
	 * @param userId the user ID
	 * @return the mobile session that was removed
	 */
	@Override
	public MobileSession removeBysidAndUserId(long sid, long userId)
		throws NoSuchMobileSessionException {

		MobileSession mobileSession = findBysidAndUserId(sid, userId);

		return remove(mobileSession);
	}

	/**
	 * Returns the number of mobile sessions where sid = &#63; and userId = &#63;.
	 *
	 * @param sid the sid
	 * @param userId the user ID
	 * @return the number of matching mobile sessions
	 */
	@Override
	public int countBysidAndUserId(long sid, long userId) {
		FinderPath finderPath = _finderPathCountBysidAndUserId;

		Object[] finderArgs = new Object[] {sid, userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_MOBILESESSION_WHERE);

			sb.append(_FINDER_COLUMN_SIDANDUSERID_SID_2);

			sb.append(_FINDER_COLUMN_SIDANDUSERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(sid);

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

	private static final String _FINDER_COLUMN_SIDANDUSERID_SID_2 =
		"mobileSession.sid = ? AND ";

	private static final String _FINDER_COLUMN_SIDANDUSERID_USERID_2 =
		"mobileSession.userId = ?";

	private FinderPath _finderPathWithPaginationFindBystatusAndUserId;
	private FinderPath _finderPathWithoutPaginationFindBystatusAndUserId;
	private FinderPath _finderPathCountBystatusAndUserId;

	/**
	 * Returns all the mobile sessions where sid = &#63; and userId = &#63;.
	 *
	 * @param sid the sid
	 * @param userId the user ID
	 * @return the matching mobile sessions
	 */
	@Override
	public List<MobileSession> findBystatusAndUserId(long sid, long userId) {
		return findBystatusAndUserId(
			sid, userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the mobile sessions where sid = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileSessionModelImpl</code>.
	 * </p>
	 *
	 * @param sid the sid
	 * @param userId the user ID
	 * @param start the lower bound of the range of mobile sessions
	 * @param end the upper bound of the range of mobile sessions (not inclusive)
	 * @return the range of matching mobile sessions
	 */
	@Override
	public List<MobileSession> findBystatusAndUserId(
		long sid, long userId, int start, int end) {

		return findBystatusAndUserId(sid, userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the mobile sessions where sid = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileSessionModelImpl</code>.
	 * </p>
	 *
	 * @param sid the sid
	 * @param userId the user ID
	 * @param start the lower bound of the range of mobile sessions
	 * @param end the upper bound of the range of mobile sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching mobile sessions
	 */
	@Override
	public List<MobileSession> findBystatusAndUserId(
		long sid, long userId, int start, int end,
		OrderByComparator<MobileSession> orderByComparator) {

		return findBystatusAndUserId(
			sid, userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the mobile sessions where sid = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileSessionModelImpl</code>.
	 * </p>
	 *
	 * @param sid the sid
	 * @param userId the user ID
	 * @param start the lower bound of the range of mobile sessions
	 * @param end the upper bound of the range of mobile sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching mobile sessions
	 */
	@Override
	public List<MobileSession> findBystatusAndUserId(
		long sid, long userId, int start, int end,
		OrderByComparator<MobileSession> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBystatusAndUserId;
				finderArgs = new Object[] {sid, userId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBystatusAndUserId;
			finderArgs = new Object[] {
				sid, userId, start, end, orderByComparator
			};
		}

		List<MobileSession> list = null;

		if (useFinderCache) {
			list = (List<MobileSession>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MobileSession mobileSession : list) {
					if ((sid != mobileSession.getSid()) ||
						(userId != mobileSession.getUserId())) {

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
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_MOBILESESSION_WHERE);

			sb.append(_FINDER_COLUMN_STATUSANDUSERID_SID_2);

			sb.append(_FINDER_COLUMN_STATUSANDUSERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(MobileSessionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(sid);

				queryPos.add(userId);

				list = (List<MobileSession>)QueryUtil.list(
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
	 * Returns the first mobile session in the ordered set where sid = &#63; and userId = &#63;.
	 *
	 * @param sid the sid
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mobile session
	 * @throws NoSuchMobileSessionException if a matching mobile session could not be found
	 */
	@Override
	public MobileSession findBystatusAndUserId_First(
			long sid, long userId,
			OrderByComparator<MobileSession> orderByComparator)
		throws NoSuchMobileSessionException {

		MobileSession mobileSession = fetchBystatusAndUserId_First(
			sid, userId, orderByComparator);

		if (mobileSession != null) {
			return mobileSession;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("sid=");
		sb.append(sid);

		sb.append(", userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchMobileSessionException(sb.toString());
	}

	/**
	 * Returns the first mobile session in the ordered set where sid = &#63; and userId = &#63;.
	 *
	 * @param sid the sid
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mobile session, or <code>null</code> if a matching mobile session could not be found
	 */
	@Override
	public MobileSession fetchBystatusAndUserId_First(
		long sid, long userId,
		OrderByComparator<MobileSession> orderByComparator) {

		List<MobileSession> list = findBystatusAndUserId(
			sid, userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last mobile session in the ordered set where sid = &#63; and userId = &#63;.
	 *
	 * @param sid the sid
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mobile session
	 * @throws NoSuchMobileSessionException if a matching mobile session could not be found
	 */
	@Override
	public MobileSession findBystatusAndUserId_Last(
			long sid, long userId,
			OrderByComparator<MobileSession> orderByComparator)
		throws NoSuchMobileSessionException {

		MobileSession mobileSession = fetchBystatusAndUserId_Last(
			sid, userId, orderByComparator);

		if (mobileSession != null) {
			return mobileSession;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("sid=");
		sb.append(sid);

		sb.append(", userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchMobileSessionException(sb.toString());
	}

	/**
	 * Returns the last mobile session in the ordered set where sid = &#63; and userId = &#63;.
	 *
	 * @param sid the sid
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mobile session, or <code>null</code> if a matching mobile session could not be found
	 */
	@Override
	public MobileSession fetchBystatusAndUserId_Last(
		long sid, long userId,
		OrderByComparator<MobileSession> orderByComparator) {

		int count = countBystatusAndUserId(sid, userId);

		if (count == 0) {
			return null;
		}

		List<MobileSession> list = findBystatusAndUserId(
			sid, userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Removes all the mobile sessions where sid = &#63; and userId = &#63; from the database.
	 *
	 * @param sid the sid
	 * @param userId the user ID
	 */
	@Override
	public void removeBystatusAndUserId(long sid, long userId) {
		for (MobileSession mobileSession :
				findBystatusAndUserId(
					sid, userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(mobileSession);
		}
	}

	/**
	 * Returns the number of mobile sessions where sid = &#63; and userId = &#63;.
	 *
	 * @param sid the sid
	 * @param userId the user ID
	 * @return the number of matching mobile sessions
	 */
	@Override
	public int countBystatusAndUserId(long sid, long userId) {
		FinderPath finderPath = _finderPathCountBystatusAndUserId;

		Object[] finderArgs = new Object[] {sid, userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_MOBILESESSION_WHERE);

			sb.append(_FINDER_COLUMN_STATUSANDUSERID_SID_2);

			sb.append(_FINDER_COLUMN_STATUSANDUSERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(sid);

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

	private static final String _FINDER_COLUMN_STATUSANDUSERID_SID_2 =
		"mobileSession.sid = ? AND ";

	private static final String _FINDER_COLUMN_STATUSANDUSERID_USERID_2 =
		"mobileSession.userId = ?";

	public MobileSessionPersistenceImpl() {
		setModelClass(MobileSession.class);

		setModelImplClass(MobileSessionImpl.class);
		setModelPKClass(long.class);

		setTable(MobileSessionTable.INSTANCE);
	}

	/**
	 * Caches the mobile session in the entity cache if it is enabled.
	 *
	 * @param mobileSession the mobile session
	 */
	@Override
	public void cacheResult(MobileSession mobileSession) {
		entityCache.putResult(
			MobileSessionImpl.class, mobileSession.getPrimaryKey(),
			mobileSession);

		finderCache.putResult(
			_finderPathFetchBysidAndUserId,
			new Object[] {mobileSession.getSid(), mobileSession.getUserId()},
			mobileSession);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the mobile sessions in the entity cache if it is enabled.
	 *
	 * @param mobileSessions the mobile sessions
	 */
	@Override
	public void cacheResult(List<MobileSession> mobileSessions) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (mobileSessions.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (MobileSession mobileSession : mobileSessions) {
			if (entityCache.getResult(
					MobileSessionImpl.class, mobileSession.getPrimaryKey()) ==
						null) {

				cacheResult(mobileSession);
			}
		}
	}

	/**
	 * Clears the cache for all mobile sessions.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MobileSessionImpl.class);

		finderCache.clearCache(MobileSessionImpl.class);
	}

	/**
	 * Clears the cache for the mobile session.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MobileSession mobileSession) {
		entityCache.removeResult(MobileSessionImpl.class, mobileSession);
	}

	@Override
	public void clearCache(List<MobileSession> mobileSessions) {
		for (MobileSession mobileSession : mobileSessions) {
			entityCache.removeResult(MobileSessionImpl.class, mobileSession);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(MobileSessionImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(MobileSessionImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		MobileSessionModelImpl mobileSessionModelImpl) {

		Object[] args = new Object[] {
			mobileSessionModelImpl.getSid(), mobileSessionModelImpl.getUserId()
		};

		finderCache.putResult(
			_finderPathCountBysidAndUserId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchBysidAndUserId, args, mobileSessionModelImpl);
	}

	/**
	 * Creates a new mobile session with the primary key. Does not add the mobile session to the database.
	 *
	 * @param sid the primary key for the new mobile session
	 * @return the new mobile session
	 */
	@Override
	public MobileSession create(long sid) {
		MobileSession mobileSession = new MobileSessionImpl();

		mobileSession.setNew(true);
		mobileSession.setPrimaryKey(sid);

		return mobileSession;
	}

	/**
	 * Removes the mobile session with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sid the primary key of the mobile session
	 * @return the mobile session that was removed
	 * @throws NoSuchMobileSessionException if a mobile session with the primary key could not be found
	 */
	@Override
	public MobileSession remove(long sid) throws NoSuchMobileSessionException {
		return remove((Serializable)sid);
	}

	/**
	 * Removes the mobile session with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the mobile session
	 * @return the mobile session that was removed
	 * @throws NoSuchMobileSessionException if a mobile session with the primary key could not be found
	 */
	@Override
	public MobileSession remove(Serializable primaryKey)
		throws NoSuchMobileSessionException {

		Session session = null;

		try {
			session = openSession();

			MobileSession mobileSession = (MobileSession)session.get(
				MobileSessionImpl.class, primaryKey);

			if (mobileSession == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMobileSessionException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(mobileSession);
		}
		catch (NoSuchMobileSessionException noSuchEntityException) {
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
	protected MobileSession removeImpl(MobileSession mobileSession) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(mobileSession)) {
				mobileSession = (MobileSession)session.get(
					MobileSessionImpl.class, mobileSession.getPrimaryKeyObj());
			}

			if (mobileSession != null) {
				session.delete(mobileSession);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (mobileSession != null) {
			clearCache(mobileSession);
		}

		return mobileSession;
	}

	@Override
	public MobileSession updateImpl(MobileSession mobileSession) {
		boolean isNew = mobileSession.isNew();

		if (!(mobileSession instanceof MobileSessionModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(mobileSession.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					mobileSession);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in mobileSession proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom MobileSession implementation " +
					mobileSession.getClass());
		}

		MobileSessionModelImpl mobileSessionModelImpl =
			(MobileSessionModelImpl)mobileSession;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (mobileSession.getCreateDate() == null)) {
			if (serviceContext == null) {
				mobileSession.setCreateDate(date);
			}
			else {
				mobileSession.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!mobileSessionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				mobileSession.setModifiedDate(date);
			}
			else {
				mobileSession.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(mobileSession);
			}
			else {
				mobileSession = (MobileSession)session.merge(mobileSession);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			MobileSessionImpl.class, mobileSessionModelImpl, false, true);

		cacheUniqueFindersCache(mobileSessionModelImpl);

		if (isNew) {
			mobileSession.setNew(false);
		}

		mobileSession.resetOriginalValues();

		return mobileSession;
	}

	/**
	 * Returns the mobile session with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the mobile session
	 * @return the mobile session
	 * @throws NoSuchMobileSessionException if a mobile session with the primary key could not be found
	 */
	@Override
	public MobileSession findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMobileSessionException {

		MobileSession mobileSession = fetchByPrimaryKey(primaryKey);

		if (mobileSession == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMobileSessionException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return mobileSession;
	}

	/**
	 * Returns the mobile session with the primary key or throws a <code>NoSuchMobileSessionException</code> if it could not be found.
	 *
	 * @param sid the primary key of the mobile session
	 * @return the mobile session
	 * @throws NoSuchMobileSessionException if a mobile session with the primary key could not be found
	 */
	@Override
	public MobileSession findByPrimaryKey(long sid)
		throws NoSuchMobileSessionException {

		return findByPrimaryKey((Serializable)sid);
	}

	/**
	 * Returns the mobile session with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param sid the primary key of the mobile session
	 * @return the mobile session, or <code>null</code> if a mobile session with the primary key could not be found
	 */
	@Override
	public MobileSession fetchByPrimaryKey(long sid) {
		return fetchByPrimaryKey((Serializable)sid);
	}

	/**
	 * Returns all the mobile sessions.
	 *
	 * @return the mobile sessions
	 */
	@Override
	public List<MobileSession> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the mobile sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileSessionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of mobile sessions
	 * @param end the upper bound of the range of mobile sessions (not inclusive)
	 * @return the range of mobile sessions
	 */
	@Override
	public List<MobileSession> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the mobile sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileSessionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of mobile sessions
	 * @param end the upper bound of the range of mobile sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of mobile sessions
	 */
	@Override
	public List<MobileSession> findAll(
		int start, int end,
		OrderByComparator<MobileSession> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the mobile sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileSessionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of mobile sessions
	 * @param end the upper bound of the range of mobile sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of mobile sessions
	 */
	@Override
	public List<MobileSession> findAll(
		int start, int end, OrderByComparator<MobileSession> orderByComparator,
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

		List<MobileSession> list = null;

		if (useFinderCache) {
			list = (List<MobileSession>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_MOBILESESSION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_MOBILESESSION;

				sql = sql.concat(MobileSessionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<MobileSession>)QueryUtil.list(
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
	 * Removes all the mobile sessions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MobileSession mobileSession : findAll()) {
			remove(mobileSession);
		}
	}

	/**
	 * Returns the number of mobile sessions.
	 *
	 * @return the number of mobile sessions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_MOBILESESSION);

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
		return "sid";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_MOBILESESSION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return MobileSessionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the mobile session persistence.
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

		_finderPathFetchBysidAndUserId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchBysidAndUserId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"sid", "userId"}, true);

		_finderPathCountBysidAndUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBysidAndUserId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"sid", "userId"}, false);

		_finderPathWithPaginationFindBystatusAndUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBystatusAndUserId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"sid", "userId"}, true);

		_finderPathWithoutPaginationFindBystatusAndUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBystatusAndUserId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"sid", "userId"}, true);

		_finderPathCountBystatusAndUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBystatusAndUserId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"sid", "userId"}, false);

		MobileSessionUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		MobileSessionUtil.setPersistence(null);

		entityCache.removeCache(MobileSessionImpl.class.getName());
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

	private static final String _SQL_SELECT_MOBILESESSION =
		"SELECT mobileSession FROM MobileSession mobileSession";

	private static final String _SQL_SELECT_MOBILESESSION_WHERE =
		"SELECT mobileSession FROM MobileSession mobileSession WHERE ";

	private static final String _SQL_COUNT_MOBILESESSION =
		"SELECT COUNT(mobileSession) FROM MobileSession mobileSession";

	private static final String _SQL_COUNT_MOBILESESSION_WHERE =
		"SELECT COUNT(mobileSession) FROM MobileSession mobileSession WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "mobileSession.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No MobileSession exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No MobileSession exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		MobileSessionPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}