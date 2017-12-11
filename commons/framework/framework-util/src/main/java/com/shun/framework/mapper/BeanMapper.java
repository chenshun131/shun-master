package com.shun.framework.mapper;

import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * User: mew <p />
 * Time: 17/11/6 16:32  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class BeanMapper {

    private static DozerBeanMapper dozer = new DozerBeanMapper();

    public BeanMapper() {
    }

    public static <T> T map(Object source, Class<T> destinationClass) {
        return dozer.map(source, destinationClass);
    }

    public static <T> List<T> mapList(Collection<T> sourceList, Class<T> destinationClass) {
        List<T> destinationList = new ArrayList();
        Iterator var4 = sourceList.iterator();
        while (var4.hasNext()) {
            Object sourceObject = var4.next();
            T destinationObject = dozer.map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }

    public static void copy(Object source, Object destinationObject) {
        dozer.map(source, destinationObject);
    }

}

