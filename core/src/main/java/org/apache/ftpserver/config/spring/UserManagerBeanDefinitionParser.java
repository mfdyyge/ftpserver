package org.apache.ftpserver.config.spring;

import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.usermanager.DbUserManager;
import org.apache.ftpserver.usermanager.PropertiesUserManager;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * Parses the FtpServer "file-user-manager" or "db-user-manager" elements into a Spring
 * bean graph
 */
public class UserManagerBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
    
    @Override
    protected Class<? extends UserManager> getBeanClass(Element element) {
        if(element.getLocalName().equals("file-user-manager")) {
            return PropertiesUserManager.class;
        } else {
            return DbUserManager.class;
        }
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        if(getBeanClass(element) == PropertiesUserManager.class) {
            builder.addPropertyValue("propFile", element.getAttribute("file"));
            if(StringUtils.hasText(element.getAttribute("encrypt-passwords")) &&
                    element.getAttribute("encrypt-passwords").equals("true")) {
                builder.addPropertyValue("encryptPasswords", true);
            }
        } else {
            Element dsElm = SpringUtil.getChildElement(element, 
                    FtpServerNamespaceHandler.FTPSERVER_NS, "data-source");
            
            // schema ensure we get the right type of element
            Element springElm = SpringUtil.getChildElement(dsElm, null, null);
            Object o;
            if("bean".equals(springElm.getLocalName())) {
                o = parserContext.getDelegate().parseBeanDefinitionElement(springElm, builder.getBeanDefinition());
            } else {
                // ref
                o = parserContext.getDelegate().parsePropertySubElement(springElm, builder.getBeanDefinition());

            }
            builder.addPropertyValue("dataSource", o);
        }
    }
}