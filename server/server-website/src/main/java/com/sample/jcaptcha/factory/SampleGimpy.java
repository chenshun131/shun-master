package com.sample.jcaptcha.factory;

import com.octo.captcha.image.ImageCaptcha;

import java.awt.image.BufferedImage;

public class SampleGimpy extends ImageCaptcha {

    private static final long serialVersionUID = 1L;

    private String response;

    private boolean caseSensitive = true;

    public SampleGimpy(String question, BufferedImage challenge, String response) {
        super(question, challenge);
        this.response = response;
    }

    @Override
    public Boolean validateResponse(Object response) {
        return (null != response && response instanceof String) ? validateResponse((String) response) : Boolean.FALSE;
    }

    /**
     * Very simple validation routine that compares the given response to the
     * internal string.
     *
     * @return true if the given response equals the internal response, false
     * otherwise.
     */
    private Boolean validateResponse(final String response) {
        return caseSensitive ? response.equals(this.response) : response.equalsIgnoreCase(this.response);
    }

}
