package com.ruoyi.common.utils.sheet;

import java.util.HashMap;
import java.util.Map;

public class WarehouseUtil {
    public final static Map<Integer,String > statusCell = new HashMap<>();
    static {
        statusCell.put(0,"仓库状态ID");
        statusCell.put(1,"仓库状态名称");
    }

    public final static Map<Integer,String > typeCell = new HashMap<>();
    static {
        typeCell.put(3,"仓库类型ID");
        typeCell.put(4,"仓库类型名称");
    }

    public final static Map<Integer,String > operateCell = new HashMap<>();
    static {
        operateCell.put(6,"运营方式ID");
        operateCell.put(7,"运营方式名称");
    }

    public final static Map<Integer,String > countryCell = new HashMap<>();
    static {
        countryCell.put(9,"国家地区ID");
        countryCell.put(10,"国家地区名称");
    }

    public final static Map<Integer,String > deptCell = new HashMap<>();
    static {
        deptCell.put(12,"部门ID");
        deptCell.put(13,"部门名称");
    }

    public final static Map<String, Object> mapData = new HashMap();
    static {
        mapData.put("DictStatus", statusCell);
        mapData.put("DictType", typeCell);
        mapData.put("DictOperate", operateCell);
        mapData.put("DictCountry", countryCell);
        mapData.put("Dept", deptCell);
    }

}
