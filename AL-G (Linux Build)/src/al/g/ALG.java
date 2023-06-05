/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package al.g;

import com.sun.tools.javac.Main;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
      
    private static JFrame SplashScreen;
    private static String LogoLocation;
    private static String LoadingLogoLocation;
    private static ImageIcon Logo;
    private static Container SplashScreenContent;
    private static JLabel MainLogo;
    private static JLabel MainLoadingLogo;
    
    //Home Screen Related
    private static JFrame HomeWindow;
    private static Container HomeScreenContent;
    private static JButton GetHardDriveReport;
    private static JButton CheckIfHardDriveNASStatus;
    //Hard Drive Report Related
    private static JFrame HardDriveReportWindow;
    private static String[] TableHeaders;
    private static int[] TableData;
    private static JTable Report;
    private static JButton ReturnButton;
    private static String[] Inputs;
    private static File GettingDriveOutputs;
    private static Scanner ReadingDriveOutputs; 
    private static Stack DriveNames;
    private static Container HardDriveReportContent;
    
    //Hard Drive NAS Status Related
    private static JFrame NASStatus;
    private static Container NASStatusContent;
    
    private static JButton[] AvailableDrive;
    private static JLabel DrivesNotFound;
    
    private static JButton UNRAID;
    private static JButton TrueNAS;
    private static JButton ProxMox;

    public static void main(String[] args) {
        
        StartUpCheck();
         ChangeUser();
    }
    private static void StartUpCheck(){
        
        try {
           // TODO code application logic here

          SplashScreen();
        } catch (InterruptedException ex) {
            Logger.getLogger(ALG.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    private static void SetupLayout(){
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        
        gbc.weighty=0.5;
    }
    
    public static JFrame SplashScreen() throws InterruptedException{
        
        WindowName = "AL-G";
        MaxWidth = 500;
        MaxHeight = 500;
        
        SplashScreen = new JFrame(WindowName);
        SplashScreen.setSize(MaxWidth, MaxHeight);
        SplashScreen.setResizable(false);
        SplashScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SplashScreen.setLocationRelativeTo(null);
        
             
        MainLogo = new JLabel();
        //This programming statement was adapted from StackOverflow:
        //Link: https://stackoverflow.com/questions/55450014/class-path-resource-for-jlabel-imageicon
        //Author(s): Depak Sharma, Andrew Thompson
        //Author Profile Link: https://stackoverflow.com/users/11289224/deepak-sharma, https://stackoverflow.com/users/418556/andrew-thompson
        MainLogo.setIcon( new ImageIcon(ALG.class.getResource("/Resources/Al-G SplashScreenImage V8.png")));
        
        MainLoadingLogo = new JLabel();
        //This programming statement was adapted from StackOverflow:
        //Link: https://stackoverflow.com/questions/55450014/class-path-resource-for-jlabel-imageicon
        //Author(s): Depak Sharma, Andrew Thompson
        //Author Profile Link: https://stackoverflow.com/users/11289224/deepak-sharma, https://stackoverflow.com/users/418556/andrew-thompson
        MainLoadingLogo.setIcon(new ImageIcon(ALG.class.getResource("/Resources/Loading.gif")));
        
        SplashScreenContent = new Container();
        
        SetupLayout();
        SplashScreenContent.setLayout(gbl);

        gbc.gridx=1;
        gbc.gridy=1;
        SplashScreenContent.add(MainLogo,gbc);
        
        gbc.gridx=1;
        gbc.gridy=2;
        SplashScreenContent.add(MainLoadingLogo,gbc);
        
        SplashScreen.setContentPane(SplashScreenContent);
        
        SplashScreen.setBackground(Color.WHITE);
        
        try{
            //This programming statement was adapted from CodeSpeedy:
            //Link: https://www.codespeedy.com/how-to-change-the-color-of-title-bar-in-jframe-in-java/
            //Author: Subhojeet Ghosh
            //Author Profile Link: https://www.codespeedy.com/author/subhojeet_ghosh/
            UIManager.put("JFrame.activeTitleBackground", Color.WHITE);
            
        }catch(Exception CannotSetColour){
            
            CannotSetColour.printStackTrace();
            
        }
        
        SplashScreen.setVisible(true);
       
       /* MainLoadingLogo.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    
                    
                  
                }

            });*/
        
        if(System.currentTimeMillis() > 10000){
            
         SplashScreen.setVisible(false);
         SplashScreen.dispose();
         MainWindow();
        }
         
         
        return SplashScreen;
    }
    
    public static JFrame MainWindow(){
        
        
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
        GetHardDriveReport = new JButton(new ImageIcon(ALG.class.getResource("/Resources/Hard Drive Report ImageButton V2.png")));
        GetHardDriveReport.setBackground(Color.WHITE);
        GetHardDriveReport.setOpaque(true);
        
        //This programming statement was adapted from StackOverflow:
        //Link: https://stackoverflow.com/questions/55450014/class-path-resource-for-jlabel-imageicon
        //Author(s): Depak Sharma, Andrew Thompson
        //Author Profile Link: https://stackoverflow.com/users/11289224/deepak-sharma, https://stackoverflow.com/users/418556/andrew-thompson
        CheckIfHardDriveNASStatus = new JButton(new ImageIcon(ALG.class.getResource("/Resources/Hard Drive Status ImageButton V2.png")));
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
        
        GetHardDriveReport.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
            
             HomeWindow.setVisible(false);
             HomeWindow.dispose();
            
             GetHardDriveReport();
            }  
        });  
        
         
        
        CheckIfHardDriveNASStatus.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
            
             HomeWindow.setVisible(false);
             HomeWindow.dispose();
            
             GetDriveNASStatus();
            }  
        });  
        
        
        
        return HomeWindow;
    }
    
    private static void FindDrives(){
     
        var GetAvailableDrivesScript = ALG.class.getResource("/Scripts/GetAllAvailableDrives.sh");
        try {
            //Stores a command
            Inputs = new String[] {"/bin/bash", "-c"," bash ./ ",GetAvailableDrivesScript.toString(), "&&", "locate DriveOutputs.txt"};
            //Excetures a command
            //This programming statement was adapted from StackOverflow:
            //Link: https://stackoverflow.com/questions/15356405/how-to-run-a-command-at-terminal-from-java-program
            //Author: Rahul
            //Author Profile Link: https://stackoverflow.com/users/2024761/rahul
            Process GetDriveAttributes = new ProcessBuilder(Inputs).start();
            //Stores output from Process execution.
            //This programming statenent was adapted from StackOverflow:
            //Link: https://stackoverflow.com/questions/5711084/java-runtime-getruntime-getting-output-from-executing-a-command-line-program
            //Author: Saptarsi Halder
            //Author Profile Link: https://stackoverflow.com/users/14101724/saptarsi-halder
            var Outputs = GetDriveAttributes.getInputStream().transferTo(System.out);
         
            
            
            GettingDriveOutputs = new File("DriveOutputs.txt");
            ReadingDriveOutputs = new Scanner(GettingDriveOutputs);
            
            DriveNames = new Stack();
            while(ReadingDriveOutputs.hasNextLine()){
                
                DriveNames.push(ReadingDriveOutputs.nextLine());
                
            }
            
            int SimpleCounter = 0;
            AvailableDrive = new JButton[10];
            
            for(Object DriveName: DriveNames){
    
                if(DriveName.toString().contains("/run/media/")){

                    SimpleCounter++;
                    
                    AvailableDrive[SimpleCounter]= new JButton(DriveName.toString());
                    AvailableDrive[SimpleCounter].setOpaque(true);
                    AvailableDrive[SimpleCounter].setBackground(Color.WHITE);
                    
                    gbc.gridx=2;
                    gbc.gridy=SimpleCounter;
                    
                    HardDriveReportContent.add( AvailableDrive[SimpleCounter] ,gbc);
                 
                    HardDriveReportWindow.setSize(MaxWidth, MaxHeight+50);
                }
              
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ALG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void GetSMARTData(){
        
          var DriveAttributesScript = ALG.class.getResource("/Scripts/GetDriveAttributes.sh");
          var MoveSMARTDataScript = ALG.class.getResource("/Scripts/MoveSmartData.sh");
          var MoveCloserScript = ALG.class.getResource("/Scripts/MoveDataCloser.sh");
          
            try{
            //This progranmming statement was adapted from StackOverflow:
            //Link: https://stackoverflow.com/questions/18708087/how-to-execute-bash-command-with-sudo-privileges-in-java
            //Author: user5587563 
            String[] Inputs2 = new String[] {"/bin/bash", "-c","./ ", DriveAttributesScript.toString()," sdd1", "&& ./ ", MoveSMARTDataScript.toString(),"&& ./ ", 
                                            MoveCloserScript.toString(), "&& locate SMART.txt"};
            //Excetures a command
            //This programming statement was adapted from StackOverflow:
            //Link: https://stackoverflow.com/questions/15356405/how-to-run-a-command-at-terminal-from-java-program
            //Author: Rahul
            //Author Profile Link: https://stackoverflow.com/users/2024761/rahul
            Process GetDriveAttributes = new ProcessBuilder(Inputs2).start();
            //Stores output from Process execution.
            //This programming statenent was adapted from StackOverflow:
            //Link: https://stackoverflow.com/questions/5711084/java-runtime-getruntime-getting-output-from-executing-a-command-line-program
            //Author: Saptarsi Halder
            //Author Profile Link: https://stackoverflow.com/users/14101724/saptarsi-halder
            var Outputs = GetDriveAttributes.getInputStream().transferTo(System.out);

            
            //GettingDriveOutputs  = new File("SMART.txt");
            //ReadingDriveOutputs = new Scanner(GettingDriveOutputs );
            
            GettingDriveOutputs = new File("DriveOutputs.txt");
            ReadingDriveOutputs = new Scanner(GettingDriveOutputs);
            
            while(ReadingDriveOutputs.hasNextLine()){
                
                System.out.println(ReadingDriveOutputs.nextLine());
            }

                 
            }catch(Exception SMARTAttributesCouldNotBeLoadedSuccessfully){}

             
            
            }  
        
    private static void ChangeUser(){
        
       String ChangeToALGUser = "echo \"173ff75ecfe6526f7996a38c0ba4f0cd\" | sudo -S smartctl -a /dev/sdd1";
        try {
            //Stores a command
            Inputs = new String[] {"/bin/bash", "-c",ChangeToALGUser," && echo ","${id -nu}"};
            //Excetures a command
            //This programming statement was adapted from StackOverflow:
            //Link: https://stackoverflow.com/questions/15356405/how-to-run-a-command-at-terminal-from-java-program
            //Author: Rahul
            //Author Profile Link: https://stackoverflow.com/users/2024761/rahul
            Process GetDriveAttributes = new ProcessBuilder(Inputs).start();
            //Stores output from Process execution.
            //This programming statenent was adapted from StackOverflow:
            //Link: https://stackoverflow.com/questions/5711084/java-runtime-getruntime-getting-output-from-executing-a-command-line-program
            //Author: Saptarsi Halder
            //Author Profile Link: https://stackoverflow.com/users/14101724/saptarsi-halder
            var Outputs = GetDriveAttributes.getInputStream().transferTo(System.out);
            
            System.out.println(Outputs);
            
        }catch(Exception CannotChangeToUser){
            
            
        }
        
    }
    public static JFrame GetHardDriveReport(){
        
        WindowName = "AL-G: Drive Report";
        MaxWidth = 600;
        MaxHeight = 750;
        
        HardDriveReportWindow = new JFrame(WindowName);
        HardDriveReportWindow.setSize(MaxWidth, MaxHeight);
        HardDriveReportWindow.setResizable(false);
        HardDriveReportWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HardDriveReportWindow.setLocationRelativeTo(null);
        
        //String GetSMARTFromDrive = "/usr/bin/gnome-terminal sudo smartctl -a /dev/sde > SnakeEater.txt";
        //Runtime ExecuteSearch = Runtime.getRuntime();
        
        
       TableHeaders = new String[]{"Raw_Read_Error_Rate",
            "Spin_Up_Time","Start_Stop_Count","Reallocated_Sector_Ct",
            "Seek_Error_Rate", "Power_On_Hours","Spin_Retry_Count",
            "Power_Cycle_Count", "End-to-End_Error", "Reported_Uncorrect",
            "Command_Timeout", "High_Fly_Writes", "Airflow_Temperature_Cel",
            "G-Sense_Error_Rate", "Power-Off_Retract_Count","Load_Cycle_Count",
            "Temperature_Celsius", "Current_Pending_Sector", "Offline_Uncorrectable",
            "UDMA_CRC_Error_Count", "Head_Flying_Hours", "Total_LBAs_Written",
            "Total_LBAs_Read","Free_Fall_Sensor"};
        //Demo Data
        TableData = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        
        DefaultTableModel ReportTemplate = new DefaultTableModel();
        int NumberOfHeadersAvailable = 1;
        for(String Header:TableHeaders){
            
            NumberOfHeadersAvailable++;
            ReportTemplate.addColumn(Header);
           
        }
        
        Report = new JTable(ReportTemplate);
        Report.setOpaque(true);
        Report.setBackground(Color.WHITE);
        //Report.getColumn(1).setPreferredWidth(5);
        
        
        ReturnButton = new JButton("Return to Home");
        ReturnButton.setBackground(Color.WHITE);
        ReturnButton.setOpaque(true);
        
        HardDriveReportContent = new Container();
        
        SetupLayout();
        HardDriveReportContent.setLayout(gbl);
         
        gbc.gridx=2;
        gbc.gridy=0;
        HardDriveReportContent.add(MainLogo,gbc);

        FindDrives();

        gbc.gridx=2;
        gbc.gridy=2;
        HardDriveReportContent.add(ReturnButton ,gbc);
              
        HardDriveReportWindow.setContentPane(HardDriveReportContent);
        
        HardDriveReportWindow.setBackground(Color.WHITE);
      
        HardDriveReportWindow.setVisible(true);
        
        ReturnButton.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
            
             HardDriveReportWindow.setVisible(false);
             HardDriveReportWindow.dispose();
             
             MainWindow();
          
            }  
        });  
        
        for(JButton HardDriveOptions: AvailableDrive){
            
            HardDriveOptions.addActionListener(new ActionListener(){  
            @Override
            public void actionPerformed(ActionEvent e){  
                
           
              
            HardDriveOptions.setVisible(false);
             
            gbc.weightx = 1.0;
            
            
             
            
        } 
      });
          
            JScrollPane TableView = new JScrollPane(Report);
          
            GetSMARTData();
            gbc.gridx=2;
            gbc.gridy=1;
            HardDriveReportContent.add(TableView,gbc);
             
            MaxWidth = 1000;
            MaxHeight = 750;
            HardDriveReportWindow.setSize(MaxWidth, MaxHeight);
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
        
        ReturnButton = new JButton("Return to Home");
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
        
         //Selection of Connected Hard Drives.
        try{
             File GettingSMARTDriveOutputs = new File("SMART.txt");
             ReadingDriveOutputs = new Scanner(GettingSMARTDriveOutputs);
            
            Stack SMARTReport = new Stack();
            while(ReadingDriveOutputs.hasNextLine()){
                
             SMARTReport.push(ReadingDriveOutputs.nextLine());

            }
                
            System.out.println("Loading Data... Please Wait");
                
            if(DriveNames.empty()){
            
           
             NASStatus.setVisible(false);
             NASStatus.dispose();
            
             GetHardDriveReport();
            
            }else{
             
             
                 for(Object Line: DriveNames){
                     
                     NASStatusContent.add(new JButton((String) Line),gbc);
                     break;
                 }
            }
                
            }catch(Exception DrivesCouldNotBeFound){
                
                System.out.println("A minimum of 1 hard drive is required to proceed. Please connected a hard drive");
            }
        
        gbc.gridx=2;
        gbc.gridy=4;
        NASStatusContent.add(ReturnButton ,gbc);
        
        NASStatus.setContentPane(NASStatusContent);
        
        NASStatus.setBackground(Color.WHITE);
      
        NASStatus.setVisible(true);
        
        ReturnButton.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                
                NASStatus.setVisible(false);
                NASStatus.dispose();
                
                MainWindow();
            }});
            
        
        return NASStatus;
        
    }
    
}
