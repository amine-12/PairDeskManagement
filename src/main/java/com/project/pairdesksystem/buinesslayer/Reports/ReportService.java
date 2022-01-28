package com.project.pairdesksystem.buinesslayer.Reports;

import com.project.pairdesksystem.datalayer.Feature.Feature;
import com.project.pairdesksystem.datalayer.Feature.FeaturesRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import sun.security.util.Resources;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
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
        String userDownloadsPath = System.getProperty("user.home") + "/Downloads";

//        JLabel l = new JLabel("No location selected");
//
//        JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
//        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//
//        int r = jFileChooser.showSaveDialog(null);
//
//        if (r == JFileChooser.APPROVE_OPTION) {
//            l.setText(jFileChooser.getSelectedFile().getAbsolutePath());
//        }

        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint, userDownloadsPath + "\\featuresSummaryReport.html");
        }
        if(reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint, userDownloadsPath + "\\featuresSummaryReport.pdf");
        }

        return "Report was generated and saved in your downloads folder";
    }
}
