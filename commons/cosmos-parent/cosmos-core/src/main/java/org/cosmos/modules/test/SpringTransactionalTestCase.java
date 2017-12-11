package org.cosmos.modules.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.sql.DataSource;

/**
 * User: mew <p />
 * Time: 17/11/6 14:56  <p />
 * Version: V1.0  <p />
 * Description:
 * Spring的支持数据库访问, 事务控制和依赖注入的JUnit4 集成测试基类.
 * 相比Spring原基类名字更短并保存了dataSource变量.
 * <p>
 * 子类需要定义applicationContext文件的位置, 如:
 *
 * @ContextConfiguration(locations = { "/applicationContext.xml" })
 * <p/>
 */
public abstract class SpringTransactionalTestCase extends AbstractTransactionalJUnit4SpringContextTests {

    protected DataSource dataSource;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
        this.dataSource = dataSource;
    }

}
