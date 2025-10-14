package com.mhdsys.jsp.bags;

import com.liferay.portal.deploy.hot.CustomJspBag;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.url.URLContainer;

import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Lenovo
 */
@Component(
		immediate = true,
		property = {
			"context.id=UpdatePasswordJSPBags", "context.name=Update Password Custom JSP Bags",
			"service.ranking:Integer=100"
		}
	)
public class UpdatePasswordJSPBags implements CustomJspBag {

	private static Log LOGGER = LogFactoryUtil.getLog(UpdatePasswordJSPBags.class);
	
	@Override
	public String getCustomJspDir() {
		return "META-INF/jsps/";
	}
	
	@Activate
	protected void activate(BundleContext bundleContext) {
		LOGGER.error(" * activate");
		_bundle = bundleContext.getBundle();

		_customJsps = new ArrayList<>();

		if (LOGGER.isDebugEnabled()) {
			LOGGER.error("Found the following jsp/jspf files to serve:");
		}
		Enumeration<URL> entries = _bundle.findEntries(
				getCustomJspDir(), "*.jsp", true);
		if (entries != null) {
			while (entries.hasMoreElements()) {
				URL url = entries.nextElement();

				_customJsps.add(url.getPath());

				if (LOGGER.isDebugEnabled()) {
					LOGGER.error("   Path: " + url.getPath());
				}
			}
		}
		// also allow the jspf overrides
		entries = _bundle.findEntries(
				getCustomJspDir(), "*.jspf", true);

		if (entries != null) {
			while (entries.hasMoreElements()) {
				URL url = entries.nextElement();

				_customJsps.add(url.getPath());

				if (LOGGER.isDebugEnabled()) {
					LOGGER.error("   Path: " + url.getPath());
				}
			}
		}
	}
	
	@Override
	public List<String> getCustomJsps() {
		LOGGER.error(" * getCustomJsps");
		return _customJsps;
	}

	@Override
	public URLContainer getURLContainer() {
		LOGGER.error(" * url");
		return _urlContainer;
	}
	
	private final URLContainer _urlContainer = new URLContainer() {

		@Override
		public URL getResource(String name) {
			LOGGER.error(" * getResource");
			if (LOGGER.isDebugEnabled()) {
				LOGGER.error("Returning URL for [" + name + "].");
			}
			return _bundle.getEntry(name);
		}

		@Override
		public Set<String> getResources(String path) {
			LOGGER.error(" * getResources");
			Set<String> paths = new HashSet<>();

			for (String entry : _customJsps) {
				if (entry.startsWith(path)) {
					paths.add(entry);
				}
			}

			if (LOGGER.isDebugEnabled()) {
				LOGGER.error("Returning following resource paths: ");
				for (String p : paths) {
					LOGGER.error("  Path: " + p);
				}
			}

			return paths;
		}
	};

	@Override
	public boolean isCustomJspGlobal() {
		return true;
	}
	
	private Bundle _bundle;
	private List<String> _customJsps;
	
}