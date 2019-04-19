package demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestApi {

	private static final String SERVICE_LOCATION_UPDATER = "fleet-location-updater";
	private Logger log = LoggerFactory.getLogger( getClass() );

	@Autowired
	private DiscoveryClient discoveryClient;

	@Value("${stomp.url:}")
	private String stompUrl;

	@RequestMapping("/clientConfig")
	@ResponseBody
	public String config() throws Exception {
	    log.info( "/clientConfig" );
		String url = null;
		try {
			url = getServiceUrl(SERVICE_LOCATION_UPDATER);
		} catch (Exception t) {
		    log.info( "Exception in getServiceUrl : " + t.getCause() );
		}

		log.info( "url after getServiceUrl : " + url );

		return url == null || url.isEmpty() ? this.stompUrl : url + "/stomp";
	}

	private String getServiceUrl(String service) {
		if (this.discoveryClient != null) {

			List<ServiceInstance> instances = this.discoveryClient.getInstances(service);

			if( instances.isEmpty() ) log.info( "discovery client instances are empty " );

            for (ServiceInstance instance : instances) {
                log.info( "instance : " + instance.getHost() );
            }

			if ( instances != null && !instances.isEmpty() ) {
				ServiceInstance instance = instances.get(0);
				String host = instance.getHost();
				return host+(instance.getPort()==80 ? "" : ":" + instance.getPort());
			}
		}
		else log.info( "** DISCOVERY CLIENT IS NULL" );
		return null;
	}

}
