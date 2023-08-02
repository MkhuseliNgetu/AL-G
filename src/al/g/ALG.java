/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package al.g;

import com.sun.tools.javac.Main;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author brajo
 */
public class ALG {

    
    private static int MaxWidth;
    private static int MaxHeight;
    private static String WindowName;
    
    private static GridBagLayout gbl;
    private static GridBagConstraints gbc;
    private static JLabel MainLogo;
    
    //Home Screen Related
    private static JFrame HomeWindow;
    private static Container HomeScreenContent;
    private static JButton GetHardDriveReport;
    private static JButton CheckIfHardDriveNASStatus;
    
    //Hard Drive Report Related
    private static JFrame HardDriveReportWindow;
    private static String[] TableHeaders;
    private static String[] TableData;
    private static JTable Report;
    private static DefaultTableModel ReportTemplate;
    private static JButton ReturnButton;
    private static String[] Inputs;
    private static File GettingDriveOutputs;
    private static Scanner ReadingDriveOutputs; 
    private static Stack DriveNames;
    private static Container HardDriveReportContent;
    private static JScrollPane TableView;
    private static JScrollBar ScrollingBar;
    
    //Hard Drive NAS Status Related
    private static JFrame NASStatus;
    private static Container NASStatusContent;
    private static JTextArea NasStatusVerdict;
    private static String FinalVerdictConverted;
    private static HashMap<String, String> HealthAttributes;
        
    
    private static Stack<JButton> AvailableDrive;
    private static int SimpleCounter;
    private static List DefaultName;
    private static List DefaultMountPoints;
    private static JLabel DrivesNotFound;
    
    private static JButton UNRAID;
    private static JButton TrueNAS;
    private static JButton ProxMox;
    private static StringBuilder MergeDriveNamesAndMountPoints;
    
    private static File Script;
    private static Set<PosixFilePermission> Permissions;
    private static Path PathToScript; 
    private static long ScriptOutputs;

    public static void main(String[] args) throws InterruptedException {
        
        StartUpCheck();
        MainWindow();
      
    }
    //User Interfaces Startup and Layout
    private static void StartUpCheck() throws InterruptedException{

        try {
           // TODO code application logic here
           //Get All Unlocked & Decrypted Drives
           FindDrives();
      
        } catch (InterruptedException ex) {
            Logger.getLogger(ALG.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    private static File MakeScriptsExecutable(URL UnexecutableScript){
            Script = new File(UnexecutableScript.getPath()); 
            
           
            Permissions = new HashSet<>();
            Permissions.add(PosixFilePermission.OWNER_READ);
            Permissions.add(PosixFilePermission.OWNER_WRITE);
            Permissions.add(PosixFilePermission.OWNER_EXECUTE);
            
            
            PathToScript = Paths.get(Script.getName());
            
        try {
            Files.setPosixFilePermissions(Script.toPath(), Permissions);
        } catch (IOException ex) {
            Logger.getLogger(ALG.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Script;
    }
    private static void SetupLayout(){
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        
        gbc.weighty=0.5;
    }
    //User Interfaces
  
    
    public static JFrame MainWindow(){
        
             
        MainLogo = new JLabel();
        //This programming statement was adapted from StackOverflow:
        //Link: https://stackoverflow.com/questions/55450014/class-path-resource-for-jlabel-imageicon
        //Author(s): Depak Sharma, Andrew Thompson
        //Author Profile Link: https://stackoverflow.com/users/11289224/deepak-sharma, https://stackoverflow.com/users/418556/andrew-thompson
        MainLogo.setIcon( new ImageIcon(ALG.class.getResource("/Resources/Al-G SplashScreenImage V8.png")));
        
        
        WindowName = "AL-G: Home";
        MaxWidth = 750;
        MaxHeight = 450;
        
        HomeWindow = new JFrame(WindowName);
        HomeWindow.setSize(MaxWidth, MaxHeight);
        HomeWindow.setResizable(false);
        HomeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HomeWindow.setLocationRelativeTo(null);
        
        //This programming statement was adapted from StackOverflow:
        //Link: https://stackoverflow.com/questions/55450014/class-path-resource-for-jlabel-imageicon
        //Author(s): Depak Sharma, Andrew Thompson
        //Author Profile Link: https://stackoverflow.com/users/11289224/deepak-sharma, https://stackoverflow.com/users/418556/andrew-thompson
        GetHardDriveReport = new JButton("Get Hard Drive Report");
        GetHardDriveReport.setBackground(Color.WHITE);
        GetHardDriveReport.setOpaque(true);
        
        //This programming statement was adapted from StackOverflow:
        //Link: https://stackoverflow.com/questions/55450014/class-path-resource-for-jlabel-imageicon
        //Author(s): Depak Sharma, Andrew Thompson
        //Author Profile Link: https://stackoverflow.com/users/11289224/deepak-sharma, https://stackoverflow.com/users/418556/andrew-thompson
        CheckIfHardDriveNASStatus = new JButton("Get Hard Drive Status");
        CheckIfHardDriveNASStatus.setBackground(Color.WHITE);
        CheckIfHardDriveNASStatus.setOpaque(true);
        
        HomeScreenContent = new Container();
        
        SetupLayout();
        HomeScreenContent.setLayout(gbl);

        gbc.gridx=0;
        gbc.gridy=0;
        HomeScreenContent.add(MainLogo,gbc);
        
        gbc.gridx=0;
        gbc.gridy=1;
        HomeScreenContent.add(GetHardDriveReport,gbc);
        
        gbc.gridx=0;
        gbc.gridy=2;
        HomeScreenContent.add(CheckIfHardDriveNASStatus,gbc);

        HomeWindow.setContentPane(HomeScreenContent);
        
        HomeWindow.setBackground(Color.WHITE);
      
        HomeWindow.setVisible(true);
        
        GetHardDriveReport.addActionListener((ActionEvent e) -> {
            HomeWindow.setVisible(false);  
            HomeWindow.dispose();
            
            try {  
                GetHardDriveReport();
            } catch (InterruptedException ex) {
                Logger.getLogger(ALG.class.getName()).log(Level.SEVERE, null, ex);
            }
        });  

        CheckIfHardDriveNASStatus.addActionListener((ActionEvent e) -> {
            HomeWindow.setVisible(false);  
            HomeWindow.dispose();
            
            GetDriveNASStatus();  
        });  
        
        
        
        return HomeWindow;
    }
   
    public static JFrame GetHardDriveReport() throws InterruptedException{
        
        WindowName = "AL-G: Drive Report";
        MaxWidth = 600;
        MaxHeight = 750;
        
        HardDriveReportWindow = new JFrame(WindowName);
        HardDriveReportWindow.setSize(MaxWidth, MaxHeight);
        HardDriveReportWindow.setResizable(false);
        HardDriveReportWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HardDriveReportWindow.setLocationRelativeTo(null);

        TableHeaders = new String[]{"Raw_Read_Error_Rate",
            "Spin_Up_Time","Start_Stop_Count","Reallocated_Sector_Ct",
            "Seek_Error_Rate", "Power_On_Hours","Spin_Retry_Count","Calibration_Retry_Count",
            "Power_Cycle_Count","G-Sense_Error_Rate","Power-Off_Retract_Count","Load_Cycle_Count",
            "Temperature_Celsius","Reallocated_Event_Count","Current_Pending_Sector","Offline_Uncorrectable",
            "UDMA_CRC_Error_Count","Multi_Zone_Error_Rate","Head_Flying_Hours","Total_LBAs_Written",
            "Total_LBAs_Read","Free_Fall_Sensor"};
        //Demo Data
        TableData = new String[22];

        ReportTemplate = new DefaultTableModel();
        ReportTemplate.setRowCount(0);
        ReportTemplate.addColumn("S.M.A.R.T Attributes");
        ReportTemplate.addColumn("Values");
        
        Report = new JTable(ReportTemplate);
        Report.setOpaque(true);
        Report.setBackground(Color.WHITE);
        Report.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        ReturnButton = new JButton("Return to Home Screen");
        ReturnButton.setBackground(Color.WHITE);
        ReturnButton.setOpaque(true);
        
        HardDriveReportContent = new Container();
        
        SetupLayout();
        HardDriveReportContent.setLayout(gbl);
         
        gbc.gridx=2;
        gbc.gridy=0;
        HardDriveReportContent.add(MainLogo,gbc);
        
       for(JButton Drive: AvailableDrive){
            SimpleCounter++;
                
            gbc.gridx=2;
            gbc.gridy=SimpleCounter;
                    
            HardDriveReportContent.add( Drive ,gbc);
                
            HardDriveReportWindow.setSize(MaxWidth, MaxHeight+50);    
        }

        if(AvailableDrive.isEmpty()){
            gbc.gridx=2;
            gbc.gridy=2;
            HardDriveReportContent.add(ReturnButton ,gbc);
        }
        HardDriveReportWindow.setContentPane(HardDriveReportContent);
        
        HardDriveReportWindow.setBackground(Color.WHITE);
      
        HardDriveReportWindow.setVisible(true);
        
        ReturnButton.addActionListener((ActionEvent e) -> {
            HardDriveReportWindow.setVisible(false);  
            HardDriveReportWindow.dispose();
            
            MainWindow();  
        });  
      
        try{
            for(int MS =0; MS < AvailableDrive.size(); MS++){
                
                 AddLogicToButton(AvailableDrive.get(MS));
            }
        }catch (Exception ButtonsNotFound){
            
            Logger.getLogger(ALG.class.getName()).log(Level.SEVERE, null, ButtonsNotFound);
        }
 
       return HardDriveReportWindow;
}
    
   

    public static JFrame GetDriveNASStatus(){
        
        WindowName = "AL-G: NAS Status";
        MaxWidth = 700;
        MaxHeight = 750;
        
        NASStatus = new JFrame(WindowName);
        NASStatus.setSize(MaxWidth, MaxHeight);
        NASStatus.setResizable(false);
        NASStatus.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        NASStatus.setLocationRelativeTo(null);
       
        
        //Type of NAS build
        UNRAID = new JButton("UNRAID");
        UNRAID.setBackground(Color.WHITE);
        UNRAID.setOpaque(true);
        TrueNAS = new JButton("TrueNAS");
        TrueNAS.setBackground(Color.WHITE);
        TrueNAS.setOpaque(true);
        ProxMox = new JButton("ProxMox");
        ProxMox.setBackground(Color.WHITE);
        ProxMox.setOpaque(true);
        
        ReturnButton = new JButton("Return to Home Screen");
        ReturnButton.setBackground(Color.WHITE);
        ReturnButton.setOpaque(true);
        
        NASStatusContent = new Container();
        
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        NASStatusContent.setLayout(gbl);
        
        gbc.weighty=0.5;
        
        gbc.gridx=2;
        gbc.gridy=0;
        NASStatusContent.add(MainLogo,gbc);
        
        gbc.gridx=2;
        gbc.gridy=1;
        NASStatusContent.add(UNRAID,gbc);
        
        gbc.gridx=2;
        gbc.gridy=2;
        NASStatusContent.add(TrueNAS,gbc);
        
        gbc.gridx=2;
        gbc.gridy=3;
        NASStatusContent.add(ProxMox,gbc);
        
        gbc.gridx=2;
        gbc.gridy=4;
        NASStatusContent.add(ReturnButton ,gbc);
        
        NASStatus.setContentPane(NASStatusContent);
        
        NASStatus.setBackground(Color.WHITE);
      
        NASStatus.setVisible(true);
        
        
        UNRAID.addActionListener((ActionEvent e) -> {
            
            NASStatus.getContentPane().getComponent(1).setVisible(false);
            
            NASStatus.getContentPane().getComponent(2).setVisible(false);
              
            NASStatus.getContentPane().getComponent(3).setVisible(false);
            
            
            DetermineNASStatus();
            NasStatusVerdict = new JTextArea();
            NasStatusVerdict.setEditable(false);
            NasStatusVerdict.setFont(new Font("Arial Black", Font.BOLD, 20));
            NasStatusVerdict.append(FinalVerdictConverted);
            
            gbc.gridx=2;
            gbc.gridy=0;
            NASStatusContent.add(MainLogo,gbc);
            
            gbc.gridx=2;
            gbc.gridy=1;
            NASStatusContent.add(NasStatusVerdict,gbc);
            
            gbc.gridx=2;
            gbc.gridy=2;
            NASStatusContent.add(ReturnButton ,gbc);
            
        });  
        
        TrueNAS.addActionListener((ActionEvent e) -> {
            
            NASStatus.getContentPane().getComponent(1).setVisible(false);
            
            NASStatus.getContentPane().getComponent(2).setVisible(false);
              
            NASStatus.getContentPane().getComponent(3).setVisible(false);
            
            
            DetermineNASStatus();
            NasStatusVerdict = new JTextArea();
            NasStatusVerdict.setEditable(false);
            NasStatusVerdict.setFont(new Font("Arial Black", Font.BOLD, 20));
            NasStatusVerdict.append(FinalVerdictConverted);

            gbc.gridx=2;
            gbc.gridy=0;
            NASStatusContent.add(MainLogo,gbc);
        
            gbc.gridx=2;
            gbc.gridy=1;
            NASStatusContent.add(NasStatusVerdict,gbc);
            
            gbc.gridx=2;
            gbc.gridy=2;
            NASStatusContent.add(ReturnButton ,gbc);
           
        });  
         
        ProxMox.addActionListener((ActionEvent e) -> {
           
            NASStatus.getContentPane().getComponent(1).setVisible(false);
            
            NASStatus.getContentPane().getComponent(2).setVisible(false);
              
            NASStatus.getContentPane().getComponent(3).setVisible(false);
           
            DetermineNASStatus();
            NasStatusVerdict = new JTextArea();
            NasStatusVerdict.setEditable(false);
            NasStatusVerdict.setFont(new Font("Arial Black", Font.BOLD, 20));
            NasStatusVerdict.append(FinalVerdictConverted);
            
            gbc.gridx=2;
            gbc.gridy=0;
            NASStatusContent.add(MainLogo,gbc);
            
            gbc.gridx=2;
            gbc.gridy=1;
            NASStatusContent.add(NasStatusVerdict,gbc);
            
            gbc.gridx=2;
            gbc.gridy=2;
            NASStatusContent.add(ReturnButton ,gbc);
            
             
        });  
        
        ReturnButton.addActionListener((ActionEvent e) -> {
            NASStatus.setVisible(false);
            NASStatus.dispose();
            
            MainWindow();
        });
            
        
        return NASStatus;
        
    }
    
    
    //Functions
    private static void FindDrives() throws InterruptedException{
     
        var GetAvailableDrivesScript = ALG.class.getResource("/Scripts/GetAllAvailableDrives.sh");
        
        try {
            
            //Make Script Executable
            MakeScriptsExecutable(GetAvailableDrivesScript);
            //Stores a command
            Inputs = new String[] {"/bin/bash", "-c", Script.toString(), "&& locate DriveOutputs.txt"};
            //Excecutes a command
            //This programming statement was adapted from StackOverflow:
            //Link: https://stackoverflow.com/questions/15356405/how-to-run-a-command-at-terminal-from-java-program
            //Author: Rahul
            //Author Profile Link: https://stackoverflow.com/users/2024761/rahul
            Process GetDriveAttributes = new ProcessBuilder(Inputs).start();
            
            GetDriveAttributes.waitFor();
            //Stores output from Process execution.
            //This programming statenent was adapted from StackOverflow:
            //Link: https://stackoverflow.com/questions/5711084/java-runtime-getruntime-getting-output-from-executing-a-command-line-program
            //Author: Saptarsi Halder
            //Author Profile Link: https://stackoverflow.com/users/14101724/saptarsi-halder
            ScriptOutputs = GetDriveAttributes.getInputStream().transferTo(System.out);
            
            System.out.println(ScriptOutputs);

            GettingDriveOutputs = new File("DriveOutputs.txt");
            ReadingDriveOutputs = new Scanner(GettingDriveOutputs);
            
            
            DriveNames = new Stack();
            while(ReadingDriveOutputs.hasNextLine()){

                   //System.out.println(ReadingDriveOutputs.nextLine());
                   
                   if(DriveNames.empty()){
                   DriveNames.push(ReadingDriveOutputs.nextLine()); 
                   }else{
                    DriveNames.push(ReadingDriveOutputs.nextLine()); 
                   }

             }
       
            SimpleCounter = 0;
            AvailableDrive = new Stack<>();
            DefaultName = new List();
            DefaultMountPoints = new List();
            
            for(Object Drives: DriveNames){
                
                if(Drives.toString().contains("sdc1") || 
                   Drives.toString().contains("sdd1") ||
                   Drives.toString().contains("sde1") ||
                   Drives.toString().contains("sdb1")){
                    
                    DefaultName.add( Drives.toString().replaceAll("\\s", ""));
  
                }
    
                if(Drives.toString().contains("/run/media/")){
                    
                    String LUKIdentifier = (String) Drives.toString().subSequence(0, 46);
                    String CompleteDriveName = Drives.toString().replace(LUKIdentifier, "");
                    DefaultMountPoints.add(CompleteDriveName);
                    
                }
              
            }
            
            for(int LoopLength = 0; LoopLength < DefaultMountPoints.getItemCount(); LoopLength++){
            
                    MergeDriveNamesAndMountPoints = new StringBuilder();
                    MergeDriveNamesAndMountPoints.append(DefaultMountPoints.getItem(LoopLength)).append("(").append(DefaultName.getItem(LoopLength)).append(")");
                    
                    JButton Drive = new JButton();
                    Drive.setOpaque(true);
                    Drive.setBackground(Color.WHITE);
                    Drive.setText(MergeDriveNamesAndMountPoints.toString());
                    
                    AvailableDrive.push(Drive);
           
            }
            
           
            
        } catch (IOException DrivesCouldNotBeFoundSuccessfully) {
            Logger.getLogger(ALG.class.getName()).log(Level.SEVERE, null, DrivesCouldNotBeFoundSuccessfully);
        }
    }
    private static void GetSMARTData(String SelectedDrive) throws InterruptedException{
        
            //var DriveAttributesScript = ALG.class.getResource("/Scripts/GetDriveAttributes.sh");


            try{
                
            //Make Script Executable
            //MakeScriptsExecutable(DriveAttributesScript);
            
            //This programming statement was adapted from StackOverflow:
            //Link: https://stackoverflow.com/questions/18708087/how-to-execute-bash-command-with-sudo-privileges-in-java
            //Author: user5587563 
            Inputs = new String[] {"/bin/bash","-c","sudo smartctl -a /dev/"+SelectedDrive.substring(0,3)+" | tee Holding.txt && cat Holding.txt | sed -n '60,81p' | tee SMART.txt && locate SMART.txt"};
            
            //This programming statement was adapted from StackOverflow:
            //Link: https://stackoverflow.com/questions/15356405/how-to-run-a-command-at-terminal-from-java-program
            //Author: Rahul
            //Author Profile Link: https://stackoverflow.com/users/2024761/rahul
            Process GetDriveAttributes = new ProcessBuilder(Inputs).start();
            
            GetDriveAttributes.waitFor();
            
            ScriptOutputs = GetDriveAttributes.getInputStream().transferTo(System.out);
            
            //System.out.println(ScriptOutputs);
          
            GettingDriveOutputs  = new File("SMART.txt");
            ReadingDriveOutputs = new Scanner(GettingDriveOutputs);
            int Counter =0;
            
            while(ReadingDriveOutputs.hasNextLine()){
                
                String[] HoldingCell =ReadingDriveOutputs.nextLine().split(" ");
                TableData[Counter++]=HoldingCell[HoldingCell.length -1];
            }
        
            for(int Attribute=0; Attribute < TableData.length;Attribute++){
             ReportTemplate.addRow(new Object[]{TableHeaders[Attribute],TableData[Attribute]});
            }
            
            }catch(IOException SMARTAttributesCouldNotBeLoadedSuccessfully){
            
                Logger.getLogger(ALG.class.getName()).log(Level.SEVERE, null, SMARTAttributesCouldNotBeLoadedSuccessfully);
            }

    }  
    private static void AddLogicToButton(JButton HardDrive){
        
                  
         HardDrive.addActionListener((ActionEvent e) -> {
         HardDriveReportWindow.getContentPane().removeAll();
          
         String SelectedDriveName = HardDrive.getText().toString().substring( HardDrive.getText().toString().length() -5);
                       
         try{
         GetSMARTData(SelectedDriveName.replace(")", ""));
         } catch (InterruptedException ex) {
         Logger.getLogger(ALG.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         DisplayTable();
         
         });

    }
    private static void DisplayTable(){
        
          gbc.gridx=2;
          gbc.gridy=0;
          HardDriveReportContent.add(MainLogo,gbc);

          TableView = new JScrollPane(Report);
          TableView.setPreferredSize(new Dimension(1100,390));
          ScrollingBar = new JScrollBar();
          TableView.add(ScrollingBar);
          
          gbc.gridx=2;
          gbc.gridy=1;
          HardDriveReportContent.add(TableView,gbc);
                        
          gbc.gridx=2;
          gbc.gridy=2;
          HardDriveReportContent.add(ReturnButton, gbc);
             
          MaxWidth = 1000;
          MaxHeight = 750;
          HardDriveReportWindow.setSize(MaxWidth+=200, MaxHeight);
    }
    private static String DetermineNASStatus(){
        
        HealthAttributes = new HashMap<String,String>();
        FinalVerdictConverted = null;
        if(TableData.length != 0){
            
          //Relocated_Sector_Ct Rules
          // 0 = Good, Recommended For NAS
          // <10 = Okay, Recommended For NAS
          //> 10 = Bad, Not Recommended For NAS
          if(Integer.parseInt(TableData[3]) == 0){
              HealthAttributes.put("RelocatedSectorCountStatus", "Good");
          }else if(Integer.parseInt(TableData[3]) >=1 && Integer.parseInt(TableData[3]) <=10){
              HealthAttributes.put("RelocatedSectorCountStatus", "Okay");
          }else if(Integer.parseInt(TableData[3]) >10){
              HealthAttributes.put("RelocatedSectorCountStatus", "Bad");
          }
          //Current_Pending_Sector Rules
          // 0 = Good, Recommended For NAS
          // <10 = Okay, Recommended For NAS
          //> 10 = Bad, Not Recommended For NAS
          if(Integer.parseInt(TableData[14]) == 0){
              HealthAttributes.put("CurrentPendingSectorStatus", "Good");
          }else if(Integer.parseInt(TableData[14]) >=1 && Integer.parseInt(TableData[14]) <=10){
              HealthAttributes.put("CurrentPendingSectorStatus", "Okay");
          }else if(Integer.parseInt(TableData[14]) >10){
              HealthAttributes.put("CurrentPendingSectorStatus", "Bad");
          }
          //Offline_Uncorrectable Rules
          // 0 = Good, Recommended For NAS
          // <10 = Okay, Recommended For NAS
          //> 10 = Bad, Not Recommended For NAS
          if(Integer.parseInt(TableData[15]) == 0){
              HealthAttributes.put("OfflineUncorrectableStatus", "Good");
          }else if(Integer.parseInt(TableData[15]) >=1 && Integer.parseInt(TableData[15]) <=10){
              HealthAttributes.put("OfflineUncorrectableStatus", "Okay");
          }else if(Integer.parseInt(TableData[15]) >10){
              HealthAttributes.put("OfflineUncorrectableStatus", "Bad");
          }
          //UDMA_CRC_Error_Count Rules
          // 0 = Good, Recommended For NAS
          // <10 = Okay, Recommended For NAS
          //> 10 = Bad, Not Recommended For NAS
           if(Integer.parseInt(TableData[16]) == 0){
              HealthAttributes.put("UDMACRCErrorCountStatus", "Good");
          }else if(Integer.parseInt(TableData[16]) >=1 && Integer.parseInt(TableData[16]) <=10){
              HealthAttributes.put("UDMACRCErrorCountStatus", "Okay");
          }else if(Integer.parseInt(TableData[16]) >10){
              HealthAttributes.put("UDMACRCErrorCountStatus", "Bad");
          }
        }
        
        int NumberOfGoodDriveStatuses = 0;
        int NumberOfOKDriveStatuses = 0;
        int NumberOfBadDriveStatuses = 0;
        int FinalVerdict = 0;
        
        if(HealthAttributes.size() == 4){
            
            for(int Entry =0; Entry < HealthAttributes.size(); Entry++){
                
                if(HealthAttributes.containsValue("Good")){
                    NumberOfGoodDriveStatuses++;
                }else if(HealthAttributes.containsValue("Okay")){
                    NumberOfOKDriveStatuses++;
                }else if(HealthAttributes.containsValue("Bad")){
                    NumberOfBadDriveStatuses++;
                }
            }
            
            FinalVerdict = (NumberOfGoodDriveStatuses + NumberOfOKDriveStatuses) - NumberOfBadDriveStatuses;
            
            switch(FinalVerdict){
                
                case 0:
                  FinalVerdictConverted = "Bad"+"\n"+"(Not Recommended For NAS)";
                    break;
                case 1:
                  FinalVerdictConverted = "Bad"+"\n"+"(Not Recommended For NAS)";
                    break;
                case 2:
                  FinalVerdictConverted = "Okay"+"\n"+"(Recommended For NAS)";
                    break;
                case 3:
                  FinalVerdictConverted = "Good"+"\n"+"(Recommended For NAS)";
                    break;
                case 4:
                  FinalVerdictConverted = "Good"+"\n"+"(Recommended For NAS)";
                    break;
            }
        }
      

        
        return FinalVerdictConverted;
    }
        
    
}
