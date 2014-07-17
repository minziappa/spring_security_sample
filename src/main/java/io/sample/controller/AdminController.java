package io.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;

/***
 * The <code>AdminController</code> class represents action controller.
 * 1. Explain for a method .....
 * 
 * @author  Woong-joon Kim
 * @version 0.1, 14/07/17
 * @see     io.sample.controller.AdminController#admin()
 * @since   JDK1.7
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends AbstractBaseController {

	final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private Validator validator;
	@Autowired
	private MessageSource message;

    /**
     * Admin Page
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
	@RequestMapping(value = {"admin.do"})
	public String admin(ModelMap model) throws Exception {
		return "admin";
	}

}