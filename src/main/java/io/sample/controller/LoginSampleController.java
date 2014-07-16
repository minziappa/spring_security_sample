package io.sample.controller;

import io.sample.bean.model.SampleModel;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

/***
 * The <code>LoginSampleController</code> class represents action controller.
 * 
 * @adminor  Woong-joon Kim
 * @version 0.1, 14/07/15
 * @see     io.sample.controller.LoginSampleController#login()
 * @see     io.sample.controller.LoginSampleController#logout()
 * @see     io.sample.controller.LoginSampleController#denied()
 * 
 * @since   JDK1.7
 */
@Controller
@RequestMapping("/login")
public class LoginSampleController extends AbstractBaseController {

	final Logger logger = LoggerFactory.getLogger(LoginSampleController.class);

    @Autowired
    private Validator validator;
	@Autowired
	private MessageSource message;

	@RequestMapping(value = {"login.do"})
	public String login(@RequestParam(value="error", required=false) boolean error, ModelMap model, HttpSession session) throws Exception {

		if (error == true) {
		   // Assign an error message
			model.put("error", "You have entered an invalid username or password!");
		} else {
			model.put("error", "");
		}

		return "login/login";
	}

	@RequestMapping(value = {"logout.do"})
	public String logout(ModelMap model, SessionStatus sessionStatus, HttpSession session) throws Exception {

		SampleModel hadoopModel = new SampleModel();
		hadoopModel.setNavi("login");

		// Clear data in the session.
		sessionStatus.isComplete();

		model.addAttribute("model", hadoopModel);

		return "index";
	}

	@RequestMapping(value = {"denied.do"})
	public String denied(ModelMap model, HttpSession session) throws Exception {

		return "login/denied";
	}

}