package com.edu.smartpersionsys;

import com.edu.smartpersionsys.mapper.SymptomRecordMapper;
import com.edu.smartpersionsys.mapper.TaskMapper;
import com.edu.smartpersionsys.pojo.Symptoms;
import com.edu.smartpersionsys.pojo.Task;
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
	private SymptomRecordMapper symptomRecordMapper;

	@Autowired
	private OlderService olderService;

	@Test
	void contextLoads() {
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

	@Autowired
	private TaskMapper taskMapper;
	@Test
	void getTasks(){
		List<Task> all = taskMapper.findAll();
		all.forEach(System.out::println);
	}

}
