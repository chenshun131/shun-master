package org.cosmos.modules.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * User: mew <p />
 * Time: 17/11/6 14:43  <p />
 * Version: V1.0  <p />
 * Description: Collections工具集 <p />
 */
public class CollectionUtils extends org.apache.commons.collections.CollectionUtils {

    /**
     * 判断是否为空
     */
    public static boolean isEmpty(Collection collection) {
        return ((collection == null) || collection.isEmpty());
    }

    /**
     * 判断是否为空
     */
    public static boolean isNotEmpty(Collection collection) {
        return ((collection != null) && !(collection.isEmpty()));
    }

    /**
     * 取得Collection的第一个元素，如果collection为空返回null
     */
    public static <T> T getFirst(Collection<T> collection) {
        if (isEmpty(collection)) {
            return null;
        }
        return collection.iterator().next();
    }

    /**
     * 获取Collection的最后一个元素 ，如果collection为空返回null
     */
    public static <T> T getLast(Collection<T> collection) {
        if (isEmpty(collection)) {
            return null;
        }
        // 当类型为List时，直接取得最后一个元素
        if (collection instanceof List) {
            List<T> list = (List<T>) collection;
            return list.get(list.size() - 1);
        }
        // 其他类型通过iterator滚动到最后一个元素
        Iterator<T> iterator = collection.iterator();
        while (true) {
            T current = iterator.next();
            if (!iterator.hasNext()) {
                return current;
            }
        }
    }

}
