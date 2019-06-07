package org.itsimulator.germes.app.rest.service.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.itsimulator.germes.app.rest.config.ComponentFeature;

import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

/**
 * REST web-service configuration for Jersey
 * 
 * @author Vitaly Dragun
 *
 */
@ApplicationPath("api")
@SwaggerDefinition(info = @Info(description = "Booking and purchasing API definition",
								title = "Germes project",
								version = "0.7.4")
)
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		super(ComponentFeature.class);
		packages("org.itsimulator.germes.app.rest");

		initBeanConfig();

		register(ApiListingResource.class);
		register(SwaggerSerializers.class);
	}

	private void initBeanConfig() {
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion("1.0.0");
		beanConfig.setSchemes(new String[] { "http" });
		beanConfig.setHost("localhost:8080");
		beanConfig.setBasePath("/");
		beanConfig.setResourcePackage("org.itsimulator.germes.app.rest.service");
		beanConfig.setScan(true);

	}

}
