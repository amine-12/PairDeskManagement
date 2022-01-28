package com.project.pairdesksystem.buinesslayer.Reports;

import com.project.pairdesksystem.datalayer.Feature.Feature;
import com.project.pairdesksystem.datalayer.Feature.FeaturesRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import sun.security.util.Resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private FeaturesRepository featuresRepository;

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        List<Feature> features = featuresRepository.findAll();

        File file = ResourceUtils.getFile("src/main/resources/User-interface/pairdesk-ui/src/features.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getPath());

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Features Summary");

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(features);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        String userDesktopPath = System.getProperty("user.home") + "/Desktop";

        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint, userDesktopPath + "\\featuresSummaryReport.html");
        }
        if(reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint, userDesktopPath + "\\featuresSummaryReport.pdf");
        }

        return "Report was generated and saved on the desktop";
    }
}
