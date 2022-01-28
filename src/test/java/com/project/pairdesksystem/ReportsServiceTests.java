package com.project.pairdesksystem;

import com.project.pairdesksystem.buinesslayer.Reports.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class ReportsServiceTests {

    @Autowired
    private ReportService reportService;

    @Test
    void check_Jasper_Report_Is_Filled() throws FileNotFoundException, JRException {

        String successfulExport = "Report was generated and saved in your downloads folder";

        String methodResult = reportService.exportReport("pdf");

        assertEquals(successfulExport, methodResult);
    }
}
