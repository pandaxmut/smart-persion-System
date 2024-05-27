package com.edu.smartpersionsys;

import com.edu.smartpersionsys.mapper.FamilyMapper;
import com.edu.smartpersionsys.mapper.SymptomRecordMapper;
import com.edu.smartpersionsys.pojo.Family;
import com.edu.smartpersionsys.pojo.Symptoms;
import com.edu.smartpersionsys.service.OlderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class SmartPersionSysApplicationTests {

	@Autowired
	private FamilyMapper familyMapper;
	@Autowired
	private SymptomRecordMapper symptomRecordMapper;

	@Autowired
	private OlderService olderService;

	@Test
	void contextLoads() {
	}

	@Test
	void selectFamilyByUserIdTest(){
		Family family = new Family();
		family.setOlderId(12);
		List<Family> families = familyMapper.allFamilyByOlderId(family);
		families.forEach(System.out::println);
	}

	@Test
	void getSymptomNameAndCountByDateTest(){
		Date date = new Date();
		SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sdf.format(date);
		List<Symptoms> symptoms = symptomRecordMapper.getSymptomNameAndCountByDate(format);
	}

	@Test
	void getIncrease(){
		olderService.getIncrease(7);
	}

}
