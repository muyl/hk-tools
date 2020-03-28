package com.hk.core.utils;

import org.apache.commons.beanutils.BeanComparator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortUtil {
    /** 降序 **/
    public final static String DESC = "desc";
    /**
     * 升序
     **/
    public final static String ASC = "asc";

    /**
     * List 元素的多个属性进行排序。 例如 ListSorter.sort(list, OrderColumn("name", DESC), OrderColumn("age", ASC))，先按 name
     * 属性排序倒序，name 相同的元素按 age 属性排序升序。
     *
     * @param list        包含要排序元素的 List
     * @param orderColumn 要排序的属性。前面的值优先级高。
     */
    public static <V> void sort(List<V> list, final OrderColumn... orderColumn) {
        list.sort((o1, o2) -> {
            if (o1 == null && o2 == null) {
                return 0;
            }
            if (o1 == null) {
                return -1;
            }
            if (o2 == null) {
                return 1;
            }

            for (OrderColumn property : orderColumn) {
                Comparator c = new BeanComparator(property.getColumnName());
                int result = c.compare(o1, o2);
                if (result != 0) {
                    if (DESC.equals(property.getOrderBy())) {
                        if (result < 0) {
                            return 1;
                        } else {
                            return -1;
                        }
                    } else {
                        return result;
                    }
                }
            }
            return 0;
        });
    }
}

class OrderColumn {
    /** 排序字段 **/
    private String columnName;
    /** 排序规则：升序、降序 **/
    private String orderBy = "ASC";

    public OrderColumn(String columnName, String orderBy) {
        this.columnName = columnName;
        this.orderBy = orderBy;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}


