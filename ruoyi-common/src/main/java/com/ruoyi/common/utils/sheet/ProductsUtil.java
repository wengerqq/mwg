package com.ruoyi.common.utils.sheet;

import java.util.HashMap;
import java.util.Map;

public class ProductsUtil {
    public final static Map<Integer,String > supplierCell = new HashMap<>();
    static {
        supplierCell.put(0,"供应商ID");
        supplierCell.put(1,"供应商名称");
    }

    public final static Map<Integer,String > brandCell = new HashMap<>();
    static {
        brandCell.put(3,"品牌ID");
        brandCell.put(4,"品牌名称");
    }

    public final static Map<Integer,String > varietiesCell = new HashMap<>();
    static {
        varietiesCell.put(6,"品类ID");
        varietiesCell.put(7,"品类名称");
    }

    public final static Map<Integer,String > currencyCell = new HashMap<>();
    static {
        currencyCell.put(9,"币种ID");
        currencyCell.put(10,"币种名称");
    }

    public final static Map<Integer,String > unitCell = new HashMap<>();
    static {
        unitCell.put(12,"单位ID");
        unitCell.put(13,"单位名称");
    }

    public final static Map<Integer,String > productSalesStatusCell = new HashMap<>();
    static {
        productSalesStatusCell.put(15,"产品销售状态ID");
        productSalesStatusCell.put(16,"产品销售状态名称");
    }

    public final static Map<Integer,String > userCell = new HashMap<>();
    static {
        userCell.put(18,"默认采购员ID");
        userCell.put(19,"默认采购员名称");
    }

    public final static Map<Integer,String > deptCell = new HashMap<>();
    static {
        deptCell.put(21,"部门ID");
        deptCell.put(22,"部门名称");
    }

    public final static Map<String, Object> mapData = new HashMap();
    static {
        mapData.put("Supplier", supplierCell);
        mapData.put("Brand", brandCell);
        mapData.put("Varieties", varietiesCell);
        mapData.put("Currency", currencyCell);
        mapData.put("Unit", unitCell);
        mapData.put("Status", productSalesStatusCell);
        mapData.put("User", userCell);
        mapData.put("Dept", deptCell);
    }

}
