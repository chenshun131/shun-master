package com.sample.jcaptcha.servlet;

import com.octo.captcha.service.sound.SoundCaptchaService;
import com.sample.jcaptcha.service.SampleSoundCaptchaService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class SoundCaptchaServlet extends HttpServlet {

    private static String className = SoundCaptchaServlet.class.getName();

    private static Logger logger = Logger.getLogger(className);

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        logger.entering(className, "doGet");
        httpServletResponse.setContentType("audio/x-wav");
        // create SoundCaptchaService everytime the doGet is called
        logger.fine("tring to get sound captcha service");
        logger.info("Going to start sound service");
        SoundCaptchaService service = null;
        if (httpServletRequest.getSession().getAttribute("soundService") != null) {
            logger.info("get service from session");
            service = (SoundCaptchaService) httpServletRequest.getSession().getAttribute("soundService");
        } else {
            String word = "";
            if (httpServletRequest.getSession().getAttribute("generatedWord") != null) {
                word = (String) httpServletRequest.getSession().getAttribute("generatedWord");
                logger.info("Get generated word from the session, word=" + word);
            }
            service = new SampleSoundCaptchaService(word);
        }
        // get AudioInputStream using session
        logger.fine("tring to get AudioInputStream");

        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        if (httpServletRequest.getSession().getAttribute("stream") == null) {
            AudioInputStream audioInputStream = service.getSoundChallengeForID(
                    httpServletRequest.getSession().getId(), httpServletRequest.getLocale());
            AudioSystem.write(audioInputStream, javax.sound.sampled.AudioFileFormat.Type.WAVE, byteOutputStream);
        } else {
            byteOutputStream = (ByteArrayOutputStream) httpServletRequest.getSession().getAttribute("stream");
        }

        // output to servlet
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        logger.fine("write ByteArrayOutputStream to ServletOutputStream");
        servletOutputStream.write(byteOutputStream.toByteArray());
        // save the service object to session, will use it for validation
        // purpose
        httpServletRequest.getSession().setAttribute("soundService", service);
        httpServletRequest.getSession().setAttribute("stream", byteOutputStream);
        // output to servlet response stream
        try {
            servletOutputStream.flush();
        } finally {
            servletOutputStream.close();
        }
        logger.exiting(className, "doGet");
    }

}
