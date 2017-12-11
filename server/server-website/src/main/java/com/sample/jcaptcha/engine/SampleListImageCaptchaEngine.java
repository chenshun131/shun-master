package com.sample.jcaptcha.engine;

import com.octo.captcha.component.image.backgroundgenerator.FunkyBackgroundGenerator;
import com.octo.captcha.component.image.color.SingleColorGenerator;
import com.octo.captcha.component.image.fontgenerator.TwistedRandomFontGenerator;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.textdecorator.BaffleTextDecorator;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.ImageCaptchaFactory;
import com.sample.jcaptcha.factory.SampleGimpyFactory;
import com.sample.jcaptcha.service.WordBridge;

import java.awt.*;

/**
 * Sample Image Engine
 *
 * @author guangqingzhong
 */
public class SampleListImageCaptchaEngine extends ListImageCaptchaEngine {

    private WordBridge wordBridge;

    public SampleListImageCaptchaEngine() {
        super();
    }

    @Override
    protected void buildInitialFactories() {
        //create text parser
        TextPaster randomPaster = new DecoratedRandomTextPaster(8, 10,
                new SingleColorGenerator(Color.BLACK),
                new TextDecorator[]{new BaffleTextDecorator(1, Color.WHITE)});
        //create image captcha factory
        ImageCaptchaFactory factory = new SampleGimpyFactory(
                new RandomWordGenerator("abcdefghijklmnopqrstuvwxyz"),
                new ComposedWordToImage(new TwistedRandomFontGenerator(34, 40),
                        new FunkyBackgroundGenerator(260, 70),
                        randomPaster));
        wordBridge = ((SampleGimpyFactory) factory).getWordBridge();

        ImageCaptchaFactory characterFactory[] = {factory};
        this.addFactories(characterFactory);
    }

    public WordBridge getWordBridge() {
        return wordBridge;
    }

}
