package com.sapient.perCapitalIncome.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.perCapitalIncome.service.EntryService;

@RestController
public class PerCapitalIncomeController {
	@Autowired
	EntryService entryService;

	@GetMapping(value = "/execute")
	public void  execute() {
		entryService.execute();
	}

}
