package com.ruoyi.products.controller;

import java.util.*;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.brand.domain.Brand;
import com.ruoyi.brand.service.IBrandService;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.currency.domain.Currency;
import com.ruoyi.currency.service.ICurrencyService;
import com.ruoyi.sales_status.domain.ProductSalesStatus;
import com.ruoyi.sales_status.service.IProductSalesStatusService;
import com.ruoyi.supplier.domain.Supplier;
import com.ruoyi.supplier.service.ISupplierService;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.unit.domain.Unit;
import com.ruoyi.unit.service.IUnitService;
import com.ruoyi.varieties.domain.Varieties;
import com.ruoyi.varieties.service.IVarietiesService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.products.domain.Products;
import com.ruoyi.products.service.IProductsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 产品资料管理Controller
 * 
 * @author ruoyi
 * @date 2022-02-21
 */
@RestController
@RequestMapping("/rchives/products")
public class ProductsController extends BaseController
{
    @Autowired
    private IProductsService productsService;
    @Autowired
    private IBrandService brandService;
    @Autowired
    private ISupplierService supplierService;
    @Autowired
    private IVarietiesService varietiesService;
    @Autowired
    private ICurrencyService currencyService;
    @Autowired
    private IUnitService unitService;
    @Autowired
    private IProductSalesStatusService productSalesStatusService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysDeptService sysDeptService;

    /**
     * 查询产品资料管理列表
     */
    @PreAuthorize("@ss.hasPermi('rchives:products:list')")
    @PostMapping("/list")
    public TableDataInfo list(Products products)
    {
        startPage();
        List<Products> list = productsService.selectProductsList(products);
        return getDataTable(list);
    }

    /**
     * 导出产品资料管理列表
     */
    @PreAuthorize("@ss.hasPermi('rchives:products:export')")
    @Log(title = "产品资料管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Products products)
    {
        List<Products> list = productsService.selectProductsList(products);
        ExcelUtil<Products> util = new ExcelUtil<Products>(Products.class);
        util.exportExcel(response, list, "产品资料管理数据");
    }

    /**
     * 获取产品资料管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('rchives:products:query')")
    @PostMapping("/getInfo")
    public AjaxResult getInfo(@RequestBody Long id)
    {
        return AjaxResult.success(productsService.selectProductsById(id));
    }

    /**
     * 新增产品资料管理
     */
    @PreAuthorize("@ss.hasPermi('rchives:products:add')")
    @Log(title = "产品资料管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Products products)
    {
        return toAjax(productsService.insertProducts(products));
    }

    /**
     * 修改产品资料管理
     */
    @PreAuthorize("@ss.hasPermi('rchives:products:edit')")
    @Log(title = "产品资料管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody Products products)
    {
        return toAjax(productsService.updateProducts(products));
    }

    /**
     * 删除产品资料管理
     */
    @PreAuthorize("@ss.hasPermi('rchives:products:remove')")
    @Log(title = "产品资料管理", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
    public AjaxResult remove(@RequestBody Long[] ids)
    {
        return toAjax(productsService.deleteProductsByIds(ids));
    }

    /**
     * 修改产品是否可用
     */
    @PreAuthorize("@ss.hasPermi('rchives:products:edit')")
    @Log(title = "产品资料管理", businessType = BusinessType.UPDATE)
    @PostMapping("/changeUsable")
    public AjaxResult changeUsable(@RequestBody Products products)
    {
        return toAjax(productsService.updateProductUsable(products));
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<Products> util = new ExcelUtil<Products>(Products.class);
        List<Supplier> supplierList = supplierService.selectSupplierList(new Supplier());
        List<Brand> brandList = brandService.selectBrandList(new Brand());
        List<Varieties> varietiesList = varietiesService.selectVarietiesList(new Varieties());
        List<Currency> currencyList = currencyService.selectCurrencyList(new Currency());
        List<Unit> unitList = unitService.selectUnitList(new Unit());
        List<ProductSalesStatus> productSalesStatusList = productSalesStatusService.selectProductSalesStatusList(new ProductSalesStatus());
        List<SysUser> userList = sysUserService.selectDeptUserList(new SysUser());
        List<SysDept> deptList = sysDeptService.selectUserDeptList(new SysDept());

        Map<String, List> listData = new HashMap<>();
        listData.put("Supplier",supplierList);
        listData.put("Brand",brandList);
        listData.put("Varieties",varietiesList);
        listData.put("Currency",currencyList);
        listData.put("Unit",unitList);
        listData.put("Status",productSalesStatusList);
        listData.put("User",userList);
        listData.put("Dept",deptList);
        util.importTemplatesExcel(response, "产品数据",listData,"products");
    }

    @Log(title = "产品资料管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('rchives:products:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<Products> util = new ExcelUtil<Products>(Products.class);
        List<Products> productsList = util.importExcel(file.getInputStream());
        Long operatorId = getUserId();
        String message = productsService.importProducts(productsList, updateSupport, operatorId);
        return AjaxResult.success(message);
    }
}
