package com.sample.jcaptcha.service;

import com.octo.captcha.engine.CaptchaEngine;
import com.octo.captcha.service.captchastore.CaptchaStore;
import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.sound.AbstractManageableSoundCaptchaService;
import com.octo.captcha.service.sound.SoundCaptchaService;
import com.sample.jcaptcha.engine.SampleListSoundCaptchaEngine;

public class SampleSoundCaptchaService extends AbstractManageableSoundCaptchaService implements SoundCaptchaService {

    public SampleSoundCaptchaService(String word) {
        super(new FastHashMapCaptchaStore(), new SampleListSoundCaptchaEngine(word), 180, 100000, 75000);
    }

    public SampleSoundCaptchaService(CaptchaStore captchaStore, CaptchaEngine captchaEngine,
                                     int minGuarantedStorageDelayInSeconds, int maxCaptchaStoreSize,
                                     int captchaStoreLoadBeforeGarbageCollection) {
        super(captchaStore, captchaEngine, minGuarantedStorageDelayInSeconds, maxCaptchaStoreSize,
                captchaStoreLoadBeforeGarbageCollection);
        // TODO Auto-generated constructor stub
    }

}
