package com.netgroup.exceldemo.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.netgroup.exceldemo.data.dao.Excel;
import com.netgroup.exceldemo.service.ExcelService;


@RestController
@RequestMapping("/api/home")
public class ExcelController {

    @Autowired
    ExcelService excelService;

    @PostMapping(value="/save")
    public Excel salvaExcel(@RequestBody Excel excel) {
        return excelService.salva(excel);
    }

}