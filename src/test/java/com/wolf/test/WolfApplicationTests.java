package com.wolf.test;

import com.wolf.Domain.*;
import com.wolf.Repository.*;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

//import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class WolfApplicationTests {

	@Autowired
	LogRepository logRepository;

	@Autowired
	LogFilesRepository logFilesRepository;

	@Autowired
	ParameterRepository paramaterRepository;

	@Autowired
	TextsRepository textsRepository;

	@Autowired
	LogItemConvRepository logItemConvRepository;

	/*
	@Before
	public void setUp() throws Exception {
		gameRepository.deleteAll();
		Game pandemic = new Game("Pandemic", "Co-op game for wannabe disease control specailists");
		Game werewolf = new Game("Werewolf", "You must find out who's secretly the werewolf, before it's too late");
		Game camelUp = new Game("Camel Up", "A high stakes game of gambling and camel racing");
		gameRepository.save(pandemic);
		gameRepository.save(werewolf);
		gameRepository.save(camelUp);
	}

	@Test
	public void testLoadGames() {
		List<Game> games = (ArrayList<Game>) gameRepository.findAll();
		assertEquals("Did not get all games", 3, games.size());
	}

	@Test
	public void testFindGame() throws Exception {
		List<Game> camelUpList = gameRepository.findByName("Camel Up");
		assertEquals("Found wrong number of Camel Ups", 1, camelUpList.size());
		assertEquals("Found wrong name", "Camel Up", camelUpList.get(0).getName());
	}

	@Test
	public void testFindGame2() throws Exception {
		List<Game> camelUpList = gameRepository.findByName2("Camel Up");
		assertEquals("Found wrong number of Camel Ups", 1, camelUpList.size());
		assertEquals("Found wrong name", "Camel Up", camelUpList.get(0).getName());
	}

	@Test
	public void testFindGameNativ() throws Exception {
		List<Game> camelUpList = gameRepository.findByNameNative("Camel Up");
		assertEquals("Found wrong number of Camel Ups", 1, camelUpList.size());
		assertEquals("Found wrong name", "Camel Up", camelUpList.get(0).getName());
	}
	*/

	@Test
	public void testLogItems() throws Exception {
		DateTime dtp = ISODateTimeFormat.dateTimeNoMillis().parseDateTime("2016-12-30T21:39:26+01:00");
		LogItem log = new LogItem(0, 0, dtp.toDate(), "15");
		logRepository.save(log);

		List<LogItem> logList = (ArrayList<LogItem>) logRepository.findAll();
		assertEquals("Did not get logs", 3, logList.size());
	}

	@Test
	public void testLogFiles() throws Exception {
		List<LogFile> logFiles = (ArrayList<LogFile>) logFilesRepository.findAll();
		assertEquals("Did not get logs", 3, logFiles.size());
	}

	@Test
	public void testParameters() throws Exception {
		List<Parameter> parameters = (ArrayList<Parameter>) paramaterRepository.findAll();
		assertEquals("Did not get parameters", 3, parameters.size());
	}

	@Test
	public void testTexts() throws Exception {
		List<Texts> texts = (ArrayList<Texts>) textsRepository.findAll();
		assertEquals("Did not get texts", 3, texts.size());
	}

	@Test
	public void testLogItemConv() throws Exception {
		List<LogItemConv> lics = (ArrayList<LogItemConv>) logItemConvRepository.findAll();
		assertNotEquals("Did not get texts", 3, lics.size());

		LogItemConv[] logArray = lics.toArray(new LogItemConv[lics.size()]);
		String arr = logArray.toString();

		String result = "?([";
		for (LogItemConv log : lics) {
			result = result + '['
					+ log.getTimestamp().toInstant().toEpochMilli() + ','
					+ log.getP00().toString() + ','
					+ log.getP01().toString() + ','
					+ log.getP02().toString() + ','
					+ log.getP03().toString() + ','
					+ log.getP04().toString() + "],";
		}
		result = result + "]);";


		//LogItemConv lic = logItemConvRepository.getLastTS();
		//assertEquals("EMPTY table", 1, lic.getTimestamp());
	}
}
