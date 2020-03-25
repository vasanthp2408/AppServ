package com.app.serv.work.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.app.serv.work.model.TestData;
import com.app.serv.work.service.ExcelWriterService;

@RestController
public class AppServController {

	@Autowired
	private ExcelWriterService testExcelsrvimpl;
	
	
	@RequestMapping(value="/")
	public ModelAndView getHome(ModelAndView model) {
		model.addObject("testdata", new TestData());
		model.setViewName("testform");
		return model;
	}

	@RequestMapping(value = "/addNewtest", method = RequestMethod.POST)
	public ModelAndView processRequest(@ModelAttribute("testdata") TestData testdata,BindingResult bb) {
		System.out.println("test--"+testdata.toString());
		testExcelsrvimpl.saveData(testdata);
		return new ModelAndView("report");
	}

	@RequestMapping(value = "/reportgenerate",method=RequestMethod.GET)
	public ModelAndView excelEmployee2() {
		System.out.println("get report....");
		testExcelsrvimpl.doTestExcel();
		return new ModelAndView("welcome");
	}
}
