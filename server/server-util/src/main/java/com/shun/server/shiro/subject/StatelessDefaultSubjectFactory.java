package com.shun.server.shiro.subject;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * User: mew <p />
 * Time: 17/11/16 15:58  <p />
 * Version: V1.0  <p />
 * Description: 不创建 session 的 Subject <p />
 */
public class StatelessDefaultSubjectFactory extends DefaultWebSubjectFactory {

    @Override
    public Subject createSubject(SubjectContext context) {
        // 通过调用 context.setSessionCreationEnabled(false) 表示不创建 session
        // 如果之后调用 Subject.getSession()将抛出 DisabledSessionException 异常
        context.setSessionCreationEnabled(false);
        return super.createSubject(context);
    }

}
