package com.example.myutil.beanValidator;

import com.example.myutil.base.BaseTest;
import com.example.myutil.beanValidator.bean.StockImportVo;
import org.junit.Test;

public class Validator extends BaseTest {
    @Test
    public void checkNull(){
        StockImportVo vo = new StockImportVo();
        BeanValidator.validate(vo);
    }

    @Test
    public void checkDate(){
        StockImportVo vo = new StockImportVo();
        vo.setTypeName("品名");
        vo.setGrade("材质");
        vo.setSpecName("规格");
        vo.setCount("1");
        vo.setWeight("1");

        vo.setEntryStoreTime("2020-20-20");
        BeanValidator.validate(vo);
    }

    @Test
    public void checkNum(){
        StockImportVo vo = new StockImportVo();
        vo.setTypeName("品名");
        vo.setGrade("材质");
        vo.setSpecName("规格");
        vo.setCount("1");
        vo.setWeight("1");
        vo.setEntryStoreTime("2020-01-20");
        // 报错
        //vo.setPurchasePrice("采购价");
        // 报错,采购价必须是个大于0的数字,精度为3位
        vo.setPurchasePrice("1.222222222222222");

        BeanValidator.validate(vo);
    }
}
