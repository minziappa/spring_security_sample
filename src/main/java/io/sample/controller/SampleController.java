package io.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/***
 * The <code>TestTblController</code> class represents action controller.
 * 1. Explain for a method .....
 * 
 * @author  Woong-joon Kim
 * @version 0.1, 14/07/15
 * @see     io.sample.security.controller.SampleController#index()
 * @since   JDK1.7
 */
@Controller
@RequestMapping("/service")
public class SampleController extends AbstractBaseController {

	final Logger logger = LoggerFactory.getLogger(SampleController.class);

    @Autowired
    private Validator validator;
	@Autowired
	private MessageSource message;

    /**
     * Index for ......
     * 
     * @param  ModelMap 
     *         model
     *         
     * @throws  Exception
     *          If a error occur, ...
     *
     * @return String
     * 		   a file name of FTL.
     * 
     * @since  1.7
     */
    @RequestMapping(value = {"/", "", "index.do"}, method=RequestMethod.GET)
	public String index(ModelMap model) throws Exception {
		return "index";
	}

}