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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import com.mhdsys.schema.exception.NoSuchProfileException;
import com.mhdsys.schema.model.Profile;
import com.mhdsys.schema.model.ProfileTable;
import com.mhdsys.schema.model.impl.ProfileImpl;
import com.mhdsys.schema.model.impl.ProfileModelImpl;
import com.mhdsys.schema.service.persistence.ProfilePersistence;
import com.mhdsys.schema.service.persistence.ProfileUtil;
import com.mhdsys.schema.service.persistence.impl.constants.mhdsysPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the profile service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = ProfilePersistence.class)
public class ProfilePersistenceImpl
	extends BasePersistenceImpl<Profile> implements ProfilePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ProfileUtil</code> to access the profile persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ProfileImpl.class.getName();

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
	 * Returns the profile where userId = &#63; or throws a <code>NoSuchProfileException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching profile
	 * @throws NoSuchProfileException if a matching profile could not be found
	 */
	@Override
	public Profile findByUserId(long userId) throws NoSuchProfileException {
		Profile profile = fetchByUserId(userId);

		if (profile == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("userId=");
			sb.append(userId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchProfileException(sb.toString());
		}

		return profile;
	}

	/**
	 * Returns the profile where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching profile, or <code>null</code> if a matching profile could not be found
	 */
	@Override
	public Profile fetchByUserId(long userId) {
		return fetchByUserId(userId, true);
	}

	/**
	 * Returns the profile where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching profile, or <code>null</code> if a matching profile could not be found
	 */
	@Override
	public Profile fetchByUserId(long userId, boolean useFinderCache) {
		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {userId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUserId, finderArgs, this);
		}

		if (result instanceof Profile) {
			Profile profile = (Profile)result;

			if (userId != profile.getUserId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_PROFILE_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				List<Profile> list = query.list();

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
								"ProfilePersistenceImpl.fetchByUserId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Profile profile = list.get(0);

					result = profile;

					cacheResult(profile);
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
			return (Profile)result;
		}
	}

	/**
	 * Removes the profile where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the profile that was removed
	 */
	@Override
	public Profile removeByUserId(long userId) throws NoSuchProfileException {
		Profile profile = findByUserId(userId);

		return remove(profile);
	}

	/**
	 * Returns the number of profiles where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching profiles
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PROFILE_WHERE);

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
		"profile.userId = ?";

	public ProfilePersistenceImpl() {
		setModelClass(Profile.class);

		setModelImplClass(ProfileImpl.class);
		setModelPKClass(long.class);

		setTable(ProfileTable.INSTANCE);
	}

	/**
	 * Caches the profile in the entity cache if it is enabled.
	 *
	 * @param profile the profile
	 */
	@Override
	public void cacheResult(Profile profile) {
		entityCache.putResult(
			ProfileImpl.class, profile.getPrimaryKey(), profile);

		finderCache.putResult(
			_finderPathFetchByUserId, new Object[] {profile.getUserId()},
			profile);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the profiles in the entity cache if it is enabled.
	 *
	 * @param profiles the profiles
	 */
	@Override
	public void cacheResult(List<Profile> profiles) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (profiles.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Profile profile : profiles) {
			if (entityCache.getResult(
					ProfileImpl.class, profile.getPrimaryKey()) == null) {

				cacheResult(profile);
			}
		}
	}

	/**
	 * Clears the cache for all profiles.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ProfileImpl.class);

		finderCache.clearCache(ProfileImpl.class);
	}

	/**
	 * Clears the cache for the profile.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Profile profile) {
		entityCache.removeResult(ProfileImpl.class, profile);
	}

	@Override
	public void clearCache(List<Profile> profiles) {
		for (Profile profile : profiles) {
			entityCache.removeResult(ProfileImpl.class, profile);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ProfileImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ProfileImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(ProfileModelImpl profileModelImpl) {
		Object[] args = new Object[] {profileModelImpl.getUserId()};

		finderCache.putResult(_finderPathCountByUserId, args, Long.valueOf(1));
		finderCache.putResult(_finderPathFetchByUserId, args, profileModelImpl);
	}

	/**
	 * Creates a new profile with the primary key. Does not add the profile to the database.
	 *
	 * @param profileId the primary key for the new profile
	 * @return the new profile
	 */
	@Override
	public Profile create(long profileId) {
		Profile profile = new ProfileImpl();

		profile.setNew(true);
		profile.setPrimaryKey(profileId);

		return profile;
	}

	/**
	 * Removes the profile with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param profileId the primary key of the profile
	 * @return the profile that was removed
	 * @throws NoSuchProfileException if a profile with the primary key could not be found
	 */
	@Override
	public Profile remove(long profileId) throws NoSuchProfileException {
		return remove((Serializable)profileId);
	}

	/**
	 * Removes the profile with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the profile
	 * @return the profile that was removed
	 * @throws NoSuchProfileException if a profile with the primary key could not be found
	 */
	@Override
	public Profile remove(Serializable primaryKey)
		throws NoSuchProfileException {

		Session session = null;

		try {
			session = openSession();

			Profile profile = (Profile)session.get(
				ProfileImpl.class, primaryKey);

			if (profile == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProfileException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(profile);
		}
		catch (NoSuchProfileException noSuchEntityException) {
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
	protected Profile removeImpl(Profile profile) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(profile)) {
				profile = (Profile)session.get(
					ProfileImpl.class, profile.getPrimaryKeyObj());
			}

			if (profile != null) {
				session.delete(profile);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (profile != null) {
			clearCache(profile);
		}

		return profile;
	}

	@Override
	public Profile updateImpl(Profile profile) {
		boolean isNew = profile.isNew();

		if (!(profile instanceof ProfileModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(profile.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(profile);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in profile proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Profile implementation " +
					profile.getClass());
		}

		ProfileModelImpl profileModelImpl = (ProfileModelImpl)profile;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(profile);
			}
			else {
				profile = (Profile)session.merge(profile);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(ProfileImpl.class, profileModelImpl, false, true);

		cacheUniqueFindersCache(profileModelImpl);

		if (isNew) {
			profile.setNew(false);
		}

		profile.resetOriginalValues();

		return profile;
	}

	/**
	 * Returns the profile with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the profile
	 * @return the profile
	 * @throws NoSuchProfileException if a profile with the primary key could not be found
	 */
	@Override
	public Profile findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProfileException {

		Profile profile = fetchByPrimaryKey(primaryKey);

		if (profile == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProfileException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return profile;
	}

	/**
	 * Returns the profile with the primary key or throws a <code>NoSuchProfileException</code> if it could not be found.
	 *
	 * @param profileId the primary key of the profile
	 * @return the profile
	 * @throws NoSuchProfileException if a profile with the primary key could not be found
	 */
	@Override
	public Profile findByPrimaryKey(long profileId)
		throws NoSuchProfileException {

		return findByPrimaryKey((Serializable)profileId);
	}

	/**
	 * Returns the profile with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param profileId the primary key of the profile
	 * @return the profile, or <code>null</code> if a profile with the primary key could not be found
	 */
	@Override
	public Profile fetchByPrimaryKey(long profileId) {
		return fetchByPrimaryKey((Serializable)profileId);
	}

	/**
	 * Returns all the profiles.
	 *
	 * @return the profiles
	 */
	@Override
	public List<Profile> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the profiles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProfileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of profiles
	 * @param end the upper bound of the range of profiles (not inclusive)
	 * @return the range of profiles
	 */
	@Override
	public List<Profile> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the profiles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProfileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of profiles
	 * @param end the upper bound of the range of profiles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of profiles
	 */
	@Override
	public List<Profile> findAll(
		int start, int end, OrderByComparator<Profile> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the profiles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProfileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of profiles
	 * @param end the upper bound of the range of profiles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of profiles
	 */
	@Override
	public List<Profile> findAll(
		int start, int end, OrderByComparator<Profile> orderByComparator,
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

		List<Profile> list = null;

		if (useFinderCache) {
			list = (List<Profile>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_PROFILE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_PROFILE;

				sql = sql.concat(ProfileModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Profile>)QueryUtil.list(
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
	 * Removes all the profiles from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Profile profile : findAll()) {
			remove(profile);
		}
	}

	/**
	 * Returns the number of profiles.
	 *
	 * @return the number of profiles
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_PROFILE);

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
		return "profileId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_PROFILE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ProfileModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the profile persistence.
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

		ProfileUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		ProfileUtil.setPersistence(null);

		entityCache.removeCache(ProfileImpl.class.getName());
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

	private static final String _SQL_SELECT_PROFILE =
		"SELECT profile FROM Profile profile";

	private static final String _SQL_SELECT_PROFILE_WHERE =
		"SELECT profile FROM Profile profile WHERE ";

	private static final String _SQL_COUNT_PROFILE =
		"SELECT COUNT(profile) FROM Profile profile";

	private static final String _SQL_COUNT_PROFILE_WHERE =
		"SELECT COUNT(profile) FROM Profile profile WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "profile.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Profile exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Profile exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ProfilePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}