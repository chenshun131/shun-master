package com.sample.jcaptcha.servlet;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.octo.captcha.service.sound.SoundCaptchaService;
import com.sample.jcaptcha.service.SampleImageCaptchaService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class CaptchaValidationServlet extends HttpServlet {

    private static String className = CaptchaValidationServlet.class.getName();

    private static Logger logger = Logger.getLogger(className);

    private static final String USER_INPUT_NAME = "captcha_input";

    public static ImageCaptchaService service = SampleImageCaptchaService.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userCaptchaResponse = request.getParameter(USER_INPUT_NAME);
        System.out.println("user input=" + userCaptchaResponse);
        boolean isValid = validateResponse(request, userCaptchaResponse);
        if (isValid) {
            System.out.println("==========success!");
            this.getServletConfig().getServletContext().getRequestDispatcher("/success.jsp").forward(request, response);
        } else {
            request.setAttribute("ERROR", "抱歉，验证码输入错误，请重新输入!");
            this.getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    private static boolean validateResponse(HttpServletRequest request, String userCaptchaResponse) {
        logger.entering(className, "validateResponse");
        boolean validated = false;
        try {
            request.getSession().removeAttribute("stream");
            logger.fine("Going to validate user's input, user's input=" + userCaptchaResponse);
            System.out.println("------------------------service object validation" + service);
            System.out.println("------------------------Session id for validation " + request.getSession(true).getId());
            validated = service.validateResponseForID(request.getSession(true).getId(), userCaptchaResponse);
            if (!validated) {
                validated = validateSoundResponse(request, userCaptchaResponse);
            }
            logger.fine("End of validating, result=" + validated);
        } catch (Exception e) {
            logger.severe("[Error]: Exception occurred when validating user's input, message=" + e.getMessage());
            e.printStackTrace();
        }
        logger.exiting(className, "validateResponse");
        return validated;
    }

    /**
     * Validate user's input
     *
     * @param request
     * @param userCaptchaResponse
     * @return
     */
    public static boolean validateSoundResponse(HttpServletRequest request, String userCaptchaResponse) {
        logger.entering(className, "validateResponse");
        SoundCaptchaService service = (SoundCaptchaService) request.getSession().getAttribute("soundService");
        if (service == null) {
            // no need sound validation
            logger.info("no sound service in session,return.");
            return false;
        }

        boolean validated = false;
        try {
            logger.info("Going to validate user's input, user's input=" + userCaptchaResponse);
            validated = service.validateResponseForID(request.getSession(true).getId(), userCaptchaResponse);
            logger.info("End of validating, result=" + validated);
        } catch (CaptchaServiceException e) {
            logger.severe("[Error]: Exception occurred when validating user's input, message=" + e.getMessage());
            e.printStackTrace();
        }
        // remove sound service in the session
        request.getSession().removeAttribute("soundService");

        logger.exiting(className, "validateResponse");
        return validated;
    }

}
