package com.mhdsys.notification.events.configuration;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationConfiguration;
import com.liferay.portal.kernel.messaging.DestinationFactory;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;

import java.util.Dictionary;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Lenovo
 */
@Component(immediate = true)
public class EmailNotificationMessageConfiguration {
	private static Log LOGGER = LogFactoryUtil.getLog(EmailNotificationMessageConfiguration.class);
	
public static final String DESTINATION_MESSAGE="mhdsys/email-parallel-message";
	
	BundleContext _bundleContext;
	
	@Activate
	private void _activate(BundleContext bundleContext) {
		this._bundleContext = bundleContext;
		DestinationConfiguration destinationConfiguration =
				new DestinationConfiguration(
						DestinationConfiguration.DESTINATION_TYPE_PARALLEL, DESTINATION_MESSAGE);
		destinationConfiguration.setWorkersCoreSize(5);
		destinationConfiguration.setWorkersMaxSize(15);
		LOGGER.debug("CURRENT_MAXIMUM_QUEUE_SIZE :  " + destinationConfiguration.getMaximumQueueSize());
		LOGGER.debug("CURRENT_WORKERS_CORE_SIZE  :  " + destinationConfiguration.getWorkersCoreSize());
		LOGGER.debug("CURRENT_WORKERS_MAX_SIZE   :  " + destinationConfiguration.getWorkersMaxSize());

		// Create the destination

		Destination destination = _destinationFactory.createDestination(destinationConfiguration);
		Dictionary<String, Object> destinationProperties =
				HashMapDictionaryBuilder.<String, Object>put(
						"destination.name", destination.getName()).build();
		_serviceRegistration = bundleContext.registerService(
				Destination.class, destination, destinationProperties);
	}
	
	
	@Deactivate
	protected void deactivate() {
		if (_serviceRegistration != null) {
			Destination destination = _bundleContext.getService(
					_serviceRegistration.getReference());
			_serviceRegistration.unregister();
			destination.destroy();
		}
	}
	
	@Reference
	private DestinationFactory _destinationFactory;
	private ServiceRegistration<Destination> _serviceRegistration;
}