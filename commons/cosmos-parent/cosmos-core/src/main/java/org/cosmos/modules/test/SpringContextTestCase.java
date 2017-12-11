package org.cosmos.modules.test;

import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * User: mew <p />
 * Time: 17/11/6 14:54  <p />
 * Version: V1.0  <p />
 * Description: Spring的支持依赖注入的JUnit4 集成测试基类, 相比Spring原基类名字更短.
 * <p>
 * 子类需要定义applicationContext文件的位置,如: <br/>
 *
 * @ContextConfiguration(locations = { "/applicationContext-test.xml" }) <p />
 */
public abstract class SpringContextTestCase extends AbstractJUnit4SpringContextTests {

}
