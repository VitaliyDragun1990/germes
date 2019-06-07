package org.itsimulator.germes.app.rest.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.math.NumberUtils;
import org.itsimulator.germes.app.model.entity.geography.City;
import org.itsimulator.germes.app.rest.dto.CityDTO;
import org.itsimulator.germes.app.rest.service.base.BaseResource;
import org.itsimulator.germes.app.service.transform.Transformer;
import org.itsimulator.germes.app.service.GeographicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;



/**
 * {@link CityResource} is REST web-service that handles city-related requests
 * 
 * @author Vitaly Dragun
 *
 */
@Path("cities")
@Api(value = "cities")
public class CityResource extends BaseResource {
	private static final Logger LOGGER = LoggerFactory.getLogger(CityResource.class);
	
	/**
	 * Underlying source of data
	 */
	private final GeographicService service;
	/**
	 * DTO <-> Entity transformer
	 */
	private final Transformer transformer;
	
	@Inject
	public CityResource(GeographicService service, Transformer transformer) {
		this.service = service;
		this.transformer = transformer;
	}

	/**
	 * Returns all the existing cities
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Returns all the existing cities")
	public List<CityDTO> findCities() {
		return service.findCities().stream()
				.map(city -> transformer.transform(city, CityDTO.class))
				.peek(city -> LOGGER.info("Found city {} with id: {}", city.getName(), city.getId()))
				.collect(Collectors.toList());
	}
	
	/**
	 * Saves new city instance
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void saveCity(CityDTO cityDTO) {
		service.saveCity(transformer.untransform(cityDTO, City.class));
	}
	
	/**
	 * Returns city with specified identifier
	 */
	@Path("/{cityId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Returns existing city by its identifier")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid city identifier"),
			@ApiResponse(code = 404, message = "Identifier of the non-existing city")
	})
	public Response findCityById(@ApiParam("Unique numeric city identifier") @PathParam("cityId") final String cityId) {
		if(!NumberUtils.isCreatable(cityId)) {
			return BAD_REQUEST;
		}
		
		Optional<City> city = service.findCityById(NumberUtils.toInt(cityId));
		if (!city.isPresent()) {
			return NOT_FOUND;
		}
		
		return ok(transformer.transform(city.get(), CityDTO.class));
	}

}
