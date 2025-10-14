package com.mhdsys.language.resourcebundle.portlet;

import com.liferay.portal.kernel.language.UTF8Control;

import java.util.Enumeration;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
	    property = { "language.id=mr_IN" }, 
	    service = ResourceBundle.class
	)
public class MarathiResourceBundle extends ResourceBundle {
	    ResourceBundle bundle = ResourceBundle.getBundle("content.Language_mr_IN",UTF8Control.INSTANCE);
		@Override
		protected Object handleGetObject(String key) {
			return bundle.getObject(key);
		}

		@Override
		public Enumeration<String> getKeys() {
			return bundle.getKeys();
		}

}
