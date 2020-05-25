/**
 * 
 */
package it.polito.ezgas;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import exception.GPSDataException;
import exception.InvalidGasTypeException;
import exception.PriceException;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.GasStationService;
import it.polito.ezgas.service.impl.GasStationServiceimpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class GasStationServiceimplIntegrationTest {
	
	@TestConfiguration
    static class GasStationServiceimplTestContextConfiguration {
		
		@Autowired
		GasStationRepository gasStationRepository;
		@Autowired
		UserRepository userRepository;
  
        @Bean
        public GasStationService gasStationService() {
            return new GasStationServiceimpl(gasStationRepository, userRepository);
        }
    }
	
	@Autowired
	private GasStationService gasStationService;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link it.polito.ezgas.service.impl.GasStationServiceimpl#GasStationServiceimpl(it.polito.ezgas.repository.GasStationRepository, it.polito.ezgas.repository.UserRepository)}.
	 */
	@Test
	public final void testGasStationServiceimplGasStationRepositoryUserRepository() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link it.polito.ezgas.service.impl.GasStationServiceimpl#GasStationServiceimpl()}.
	 */
	@Test
	public final void testGasStationServiceimpl() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link it.polito.ezgas.service.impl.GasStationServiceimpl#getGasStationById(java.lang.Integer)}.
	 */
	@Test
	public final void testGetGasStationById() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link it.polito.ezgas.service.impl.GasStationServiceimpl#saveGasStation(it.polito.ezgas.dto.GasStationDto)}.
	 */
	@Test
	public final void testSaveGasStation() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link it.polito.ezgas.service.impl.GasStationServiceimpl#getAllGasStations()}.
	 */
	@Test
	public final void testGetAllGasStations() {
		GasStationDto gsdto = new GasStationDto();
		List<GasStationDto> list = null;
		try {
			gasStationService.saveGasStation(gsdto);
		} catch (PriceException | GPSDataException e) {
			fail();
		}
		list=gasStationService.getAllGasStations();
		assertEquals(list.size(),1);
	}

	/**
	 * Test method for {@link it.polito.ezgas.service.impl.GasStationServiceimpl#deleteGasStation(java.lang.Integer)}.
	 */
	@Test
	public final void testDeleteGasStation() {
		
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link it.polito.ezgas.service.impl.GasStationServiceimpl#getGasStationsByGasolineType(java.lang.String)}.
	 * valid, gas stations exists with this type in the db
	 */
	@Test
	public final void testGetGasStationsByGasolineType() {
		double price = 1.50;
		Random rand = new Random();
		List<GasStationDto> inserted = new ArrayList<GasStationDto>();
		List<GasStationDto> result = null;
		for(int i = 0; i < 5; i++)
			inserted.add(new GasStationDto(null, "Name", "Address", true, true, false, false, false, "engioi", 0.0, 0.0, price+rand.nextDouble(), 1.0, 0.0, 0.0, 0.0, 1, "timestamp", 0.50));
		for(int i = 0; i < 5; i++)
			inserted.add(new GasStationDto(null, "Name", "Address", false, true, true, false, false, "engioi", 0.0, 0.0, 0.0, 1.0, price+rand.nextDouble(), 0.0, 0.0, 1, "timestamp", 0.50));
		
		for(GasStationDto g : inserted) {
			try {
				gasStationService.saveGasStation(g);
			} catch (PriceException | GPSDataException e) {
				fail();
			}
		}
		
		try {
			result=gasStationService.getGasStationsByGasolineType("Diesel");
		} catch (InvalidGasTypeException e) {
			fail();
		}
		double previous = -1;
		for(GasStationDto r : result) {
			assert(r.getHasDiesel());
			assert(r.getDieselPrice()>previous);
			previous = r.getDieselPrice();
		}
	}
	
	/**
	 * Test method for {@link it.polito.ezgas.service.impl.GasStationServiceimpl#getGasStationsByGasolineType(java.lang.String)}.
	 * valid, NO gas stations exists with this type in the db
	 */
	@Test
	public final void testGetGasStationsByGasolineTypeAbsent() {
		double price = 1.50;
		Random rand = new Random();
		List<GasStationDto> inserted = new ArrayList<GasStationDto>();
		List<GasStationDto> result = null;
		for(int i = 0; i < 5; i++)
			inserted.add(new GasStationDto(null, "Name", "Address", true, true, false, false, false, "engioi", 0.0, 0.0, price+rand.nextDouble(), 1.0, 0.0, 0.0, 0.0, 1, "timestamp", 0.50));
		for(int i = 0; i < 5; i++)
			inserted.add(new GasStationDto(null, "Name", "Address", false, true, true, false, false, "engioi", 0.0, 0.0, 0.0, 1.0, price+rand.nextDouble(), 0.0, 0.0, 1, "timestamp", 0.50));
		
		for(GasStationDto g : inserted) {
			try {
				gasStationService.saveGasStation(g);
			} catch (PriceException | GPSDataException e) {
				fail();
			}
		}
		
		try {
			result=gasStationService.getGasStationsByGasolineType("LPG");
		} catch (InvalidGasTypeException e) {
			fail();
		}
		assertEquals(0,result.size());
	}
	
	/**
	 * Test method for {@link it.polito.ezgas.service.impl.GasStationServiceimpl#getGasStationsByGasolineType(java.lang.String)}.
	 * invalid
	 */
	@Test
	public final void testGetGasStationsByGasolineTypeInvalid() {
		double price = 1.50;
		Random rand = new Random();
		List<GasStationDto> inserted = new ArrayList<GasStationDto>();
		for(int i = 0; i < 5; i++)
			inserted.add(new GasStationDto(null, "Name", "Address", true, true, false, false, false, "engioi", 0.0, 0.0, price+rand.nextDouble(), 1.0, 0.0, 0.0, 0.0, 1, "timestamp", 0.50));
		for(int i = 0; i < 5; i++)
			inserted.add(new GasStationDto(null, "Name", "Address", false, true, true, false, false, "engioi", 0.0, 0.0, 0.0, 1.0, price+rand.nextDouble(), 0.0, 0.0, 1, "timestamp", 0.50));
		
		for(GasStationDto g : inserted) {
			try {
				gasStationService.saveGasStation(g);
			} catch (PriceException | GPSDataException e) {
				fail();
			}
		}
		
		try {
			gasStationService.getGasStationsByGasolineType("Coke");
		} catch (InvalidGasTypeException e) {
			return;
		}
		fail();
	}

	/**
	 * Test method for {@link it.polito.ezgas.service.impl.GasStationServiceimpl#getGasStationsByProximity(double, double)}.
	 * Correct input, gas stations exists in range.
	 * Returns all gas stations within 1km from the GeoPoint whose latitude and longitude are passed as parameters.
	 * Specific order?
	 */
	@Test
	public final void testGetGasStationsByProximity() {
		List<GasStationDto> result = null;
		Random rand = new Random();
		List<GasStationDto> inserted = new ArrayList<GasStationDto>();
		for(int i = 0; i < 5; i++)
			inserted.add(new GasStationDto(null, "CLOSE ENOUGH", "Address", true, true, false, false, false, "engioi", 42.42, 42.42, 0.0, 0.0, 1.0, 1.0, 0.0, 1, "timestamp", 0.50));
		for(int i = 0; i < 7; i++)
			inserted.add(new GasStationDto(null, "Name", "Address", false, true, true, false, false, "engioi", 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1, "timestamp", 0.50));
		
		for(GasStationDto g : inserted) {
			try {
				gasStationService.saveGasStation(g);
			} catch (PriceException | GPSDataException e) {
				fail();
			}
		}
		
		try {
			result = gasStationService.getGasStationsByProximity(42.424, 42.424);
		} catch (GPSDataException e) {
			fail();
		}
		assertEquals(5,result.size());
	}
	
	/**
	 * Test method for {@link it.polito.ezgas.service.impl.GasStationServiceimpl#getGasStationsByProximity(double, double)}.
	 * Correct input, gas stations exists in range.
	 * Returns all gas stations within 1km from the GeoPoint whose latitude and longitude are passed as parameters.
	 * Specific order? Boundary in
	 */
	@Test
	public final void testGetGasStationsByProximityBoundaryIn() {
		List<GasStationDto> result = null;
		Random rand = new Random();
		List<GasStationDto> inserted = new ArrayList<GasStationDto>();
		for(int i = 0; i < 5; i++)
			inserted.add(new GasStationDto(null, "CLOSE ENOUGH", "Address", true, true, false, false, false, "engioi", 42.42, 42.42, 0.0, 0.0, 1.0, 1.0, 0.0, 1, "timestamp", 0.50));
		for(int i = 0; i < 7; i++)
			inserted.add(new GasStationDto(null, "Name", "Address", false, true, true, false, false, "engioi", 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1, "timestamp", 0.50));
		
		for(GasStationDto g : inserted) {
			try {
				gasStationService.saveGasStation(g);
			} catch (PriceException | GPSDataException e) {
				fail();
			}
		}
		
		try {
			// distance 0.99km
			result = gasStationService.getGasStationsByProximity(42.427, 42.4275);
		} catch (GPSDataException e) {
			fail();
		}
		assertEquals(5,result.size());
	}
	
	/**
	 * Test method for {@link it.polito.ezgas.service.impl.GasStationServiceimpl#getGasStationsByProximity(double, double)}.
	 * Correct input, NO gas stations exists in range. Returns an empty ArrayList.
	 */
	@Test
	public final void testGetGasStationsByProximityAbsent() {
		List<GasStationDto> result = null;
		List<GasStationDto> inserted = new ArrayList<GasStationDto>();
		for(int i = 0; i < 5; i++)
			inserted.add(new GasStationDto(null, "CLOSE ENOUGH", "Address", true, true, false, false, false, "engioi", 42.42, 42.42, 0.0, 0.0, 1.0, 1.0, 0.0, 1, "timestamp", 0.50));
		for(int i = 0; i < 7; i++)
			inserted.add(new GasStationDto(null, "Name", "Address", false, true, true, false, false, "engioi", 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1, "timestamp", 0.50));
		
		for(GasStationDto g : inserted) {
			try {
				gasStationService.saveGasStation(g);
			} catch (PriceException | GPSDataException e) {
				fail();
			}
		}
		
		try {
			// distance 1.01km
			result = gasStationService.getGasStationsByProximity(42.4273, 42.4273);
		} catch (GPSDataException e) {
			fail();
		}
		assertEquals(0,result.size());
	}

	/**
	 * Test method for {@link it.polito.ezgas.service.impl.GasStationServiceimpl#getGasStationsWithCoordinates(double, double, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testGetGasStationsWithCoordinatesOnlyCoordinates() {
		List<GasStationDto> result = null;
		Random rand = new Random();
		List<GasStationDto> inserted = new ArrayList<GasStationDto>();
		for(int i = 0; i < 5; i++)
			inserted.add(new GasStationDto(null, "CLOSE ENOUGH", "Address", true, true, false, false, false, "engioi", 42.42, 42.42, 0.0, 0.0, 1.0, 1.0, 0.0, 1, "timestamp", 0.50));
		for(int i = 0; i < 7; i++)
			inserted.add(new GasStationDto(null, "Name", "Address", false, true, true, false, false, "engioi", 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1, "timestamp", 0.50));
		
		for(GasStationDto g : inserted) {
			try {
				gasStationService.saveGasStation(g);
			} catch (PriceException | GPSDataException e) {
				fail();
			}
		}
		
		try {
			result = gasStationService.getGasStationsWithCoordinates(42.424, 42.424, null, null);
		} catch (InvalidGasTypeException | GPSDataException e) {
			fail();
		}
		
		assertEquals(5,result.size());
	}
	
	/**
	 * Test method for {@link it.polito.ezgas.service.impl.GasStationServiceimpl#getGasStationsWithCoordinates(double, double, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testGetGasStationsWithCoordinates() {
		List<GasStationDto> result = null;
		Random rand = new Random();
		List<GasStationDto> inserted = new ArrayList<GasStationDto>();
		for(int i = 0; i < 5; i++)
			inserted.add(new GasStationDto(null, "CLOSE ENOUGH", "Address", true, true, false, false, false, "engioi", 42.42, 42.42, 0.0, 0.0, 1.0, 1.0, 0.0, 1, "timestamp", 0.50));
		for(int i = 0; i < 7; i++)
			inserted.add(new GasStationDto(null, "Name", "Address", false, true, true, false, false, "engioi", 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1, "timestamp", 0.50));
		for(int i = 0; i < 6; i++)
			inserted.add(new GasStationDto(null, "CLOSE ENOUGH", "Address", true, true, false, false, false, "car2go", 42.42, 42.42, 0.0, 0.0, 1.0, 1.0, 0.0, 1, "timestamp", 0.50));
		for(int i = 0; i < 7; i++)
			inserted.add(new GasStationDto(null, "Name", "Address", false, true, true, false, false, "engioi", 42.42, 42.42, 0.0, 1.0, 1.0, 0.0, 0.0, 1, "timestamp", 0.50));
		
		for(GasStationDto g : inserted) {
			try {
				gasStationService.saveGasStation(g);
			} catch (PriceException | GPSDataException e) {
				fail();
			}
		}
		
		try {
			result = gasStationService.getGasStationsWithCoordinates(42.424, 42.424, "Diesel", "engioi");
		} catch (InvalidGasTypeException | GPSDataException e) {
			fail();
		}
		
		assertEquals(5, result.size());
	}


	/**
	 * Test method for {@link it.polito.ezgas.service.impl.GasStationServiceimpl#getGasStationsWithoutCoordinates(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testGetGasStationsWithoutCoordinates() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link it.polito.ezgas.service.impl.GasStationServiceimpl#setReport(java.lang.Integer, double, double, double, double, double, java.lang.Integer)}.
	 */
	@Test
	public final void testSetReport() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link it.polito.ezgas.service.impl.GasStationServiceimpl#getGasStationByCarSharing(java.lang.String)}.
	 */
	@Test
	public final void testGetGasStationByCarSharing() {
		List<GasStationDto> inserted = new ArrayList<GasStationDto>();
		List<GasStationDto> result = null;
		for(int i = 0; i < 5; i++)
			inserted.add(new GasStationDto(null, "Name", "Address", true, true, false, false, false, "cartugo", 0.0, 0.0,1.2, 1.0, 0.0, 0.0, 0.0, 1, "timestamp", 0.50));
		for(int i = 0; i < 6; i++)
			inserted.add(new GasStationDto(null, "Name", "Address", false, true, true, false, false, "engioi", 0.0, 0.0, 0.0, 1.0, 1.19, 0.0, 0.0, 1, "timestamp", 0.50));
		
		for(GasStationDto g : inserted) {
			try {
				gasStationService.saveGasStation(g);
			} catch (PriceException | GPSDataException e) {
				fail();
			}
		}
		result=gasStationService.getGasStationByCarSharing("engioi");
		assertEquals(result.size(), 6);
		assertEquals(result.get(0).getCarSharing(), "engioi");
	}
	
	/**
	 * Test method for {@link it.polito.ezgas.service.impl.GasStationServiceimpl#getGasStationByCarSharing(java.lang.String)}.
	 */
	@Test
	public final void testGetGasStationByCarSharingAbsent() {
		List<GasStationDto> inserted = new ArrayList<GasStationDto>();
		List<GasStationDto> result = null;
		for(int i = 0; i < 5; i++)
			inserted.add(new GasStationDto(null, "Name", "Address", true, true, false, false, false, "cartugo", 0.0, 0.0, 1.2, 1.0, 0.0, 0.0, 0.0, 1, "timestamp", 0.50));
		for(int i = 0; i < 6; i++)
			inserted.add(new GasStationDto(null, "Name", "Address", false, true, true, false, false, "engioi", 0.0, 0.0, 0.0, 1.0, 1.19, 0.0, 0.0, 1, "timestamp", 0.50));
		
		for(GasStationDto g : inserted) {
			try {
				gasStationService.saveGasStation(g);
			} catch (PriceException | GPSDataException e) {
				fail();
			}
		}
		result=gasStationService.getGasStationByCarSharing("fake");
		assertEquals(result.size(), 0);
	}

}
