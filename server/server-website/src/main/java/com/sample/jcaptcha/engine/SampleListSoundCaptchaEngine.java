package com.sample.jcaptcha.engine;

import com.octo.captcha.component.sound.soundconfigurator.FreeTTSSoundConfigurator;
import com.octo.captcha.component.sound.wordtosound.FreeTTSWordToSound;
import com.octo.captcha.component.word.worddecorator.SpellerWordDecorator;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.sound.ListSoundCaptchaEngine;
import com.octo.captcha.sound.SoundCaptchaFactory;
import com.sample.jcaptcha.factory.SampleSpellerSoundFactory;

public class SampleListSoundCaptchaEngine extends ListSoundCaptchaEngine {

    private String word;

    private static String voiceName = "kevin16";

    private static String voicePackage = "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory";

    @Override
    protected void buildInitialFactories() {
        WordGenerator wordGenerator = new RandomWordGenerator("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        SpellerWordDecorator decorator = new SpellerWordDecorator(", ");
        SoundCaptchaFactory soundFactory[] = {new SampleSpellerSoundFactory(wordGenerator,
                new FreeTTSWordToSound(new FreeTTSSoundConfigurator(voiceName, voicePackage, 1.0f, 100,
                        100), 4, 10), decorator, word)};
        this.setFactories(soundFactory);
    }

    public SampleListSoundCaptchaEngine(String word) {
        this.word = word;
        buildInitialFactories();
    }

}
