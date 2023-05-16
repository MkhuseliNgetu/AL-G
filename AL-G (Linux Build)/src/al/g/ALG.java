/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package al.g;

import java.awt.Color;
import java.awt.Container;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;

/**
 *
 * @author brajo
 */
public class ALG {

    private static JFrame SplashScreen;
    private static int MaxWidth;
    private static int MaxHeight;
    private static String WindowName;
    private static String LogoLocation;
    private static ImageIcon Logo;
    private static Container SplashScreenContent;
    private static JLabel MainLogo;
    private static JLabel MainLoadingLogo;
    private static GridBagLayout gbl;
    private static GridBagConstraints gbc;
    
    private static JFrame HomeWindow;
    private static Container HomeScreenContent;
    
    private static JFrame HardDriveReportWindow;
    private static Stack DriveNames;
    private static Container HardDriveReportContent;
    
    private static JFrame NASStatus;
    private static Container NASStatusContent;

    public static void main(String[] args) {
        // TODO code application logic here
        
        WindowName = "AL-G";
        MaxWidth = 500;
        MaxHeight = 500;
        
        SplashScreen = new JFrame(WindowName);
        SplashScreen.setSize(MaxWidth, MaxHeight);
        SplashScreen.setResizable(false);
        SplashScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        LogoLocation ="/home/brajo/Pictures/Al-G SpalshScreenImage V8.png";
                 
        
        MainLogo = new JLabel();
        MainLogo.setIcon( new ImageIcon(LogoLocation));
        
        MainLoadingLogo = new JLabel();
        MainLoadingLogo.setIcon(new ImageIcon("/home/brajo/Downloads/Ellipsis-3.4s-200px(1).gif"));
        
        SplashScreenContent = new Container();
        
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        SplashScreenContent.setLayout(gbl);
        
        gbc.weighty=0.5;
        
        gbc.gridx=1;
        gbc.gridy=1;
        SplashScreenContent.add(MainLogo,gbc);
        
        gbc.gridx=1;
        gbc.gridy=2;
        SplashScreenContent.add(MainLoadingLogo,gbc);
        
        SplashScreen.setContentPane(SplashScreenContent);
        
        SplashScreen.setBackground(Color.WHITE);
        
        try{
            
            UIManager.put("JFrame.activeTitleBackground", Color.WHITE);
            
        }catch(Exception CannotSetColour){
            
            CannotSetColour.printStackTrace();
            
        }
        
        SplashScreen.setVisible(true);
        
        MainLoadingLogo.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                   SplashScreen.setVisible(false);
                   SplashScreen.dispose();
                   MainWindow();
                }

            });
        
    }
    
    public static JFrame MainWindow(){
        
        
        WindowName = "AL-G: Home";
        MaxWidth = 750;
        MaxHeight = 450;
        
        HomeWindow = new JFrame(WindowName);
        HomeWindow.setSize(MaxWidth, MaxHeight);
        HomeWindow.setResizable(false);
        HomeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JButton GetHardDriveReport = new JButton("Get Hard Drive Report");
        JButton CheckIfHardDriveNASStatus = new JButton("Get Drive NAS Status");
        
        HomeScreenContent = new Container();
        
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        HomeScreenContent.setLayout(gbl);
        
        gbc.weighty=0.5;
        
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
    
    public static JFrame GetHardDriveReport(){
        
        WindowName = "AL-G: Drive Report";
        MaxWidth = 600;
        MaxHeight = 750;
        
        HardDriveReportWindow = new JFrame(WindowName);
        HardDriveReportWindow.setSize(MaxWidth, MaxHeight);
        HardDriveReportWindow.setResizable(false);
        HardDriveReportWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //String GetSMARTFromDrive = "/usr/bin/gnome-terminal sudo smartctl -a /dev/sde > SnakeEater.txt";
        //Runtime ExecuteSearch = Runtime.getRuntime();
        
        
        String[] TableHeaders = new String[]{"Raw_Read_Error_Rate",
            "Spin_Up_Time","Start_Stop_Count","Reallocated_Sector_Ct",
            "Seek_Error_Rate", "Power_On_Hours","Spin_Retry_Count",
            "Power_Cycle_Count", "End-to-End_Error", "Reported_Uncorrect",
            "Command_Timeout", "High_Fly_Writes", "Airflow_Temperature_Cel",
            "G-Sense_Error_Rate", "Power-Off_Retract_Count","Load_Cycle_Count",
            "Temperature_Celsius", "Current_Pending_Sector", "Offline_Uncorrectable",
            "UDMA_CRC_Error_Count", "Head_Flying_Hours", "Total_LBAs_Written",
            "Total_LBAs_Read","Free_Fall_Sensor"};
        //Demo Data
        int[] TableData = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        
        
        JTable Report = new JTable();
        
        JButton ReturnButton = new JButton("Return to Home");

        
        HardDriveReportContent = new Container();
        
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        HardDriveReportContent.setLayout(gbl);
        
        gbc.weighty=0.5;
        
        gbc.gridx=2;
        gbc.gridy=0;
        HardDriveReportContent.add(MainLogo,gbc);
        
        
        
        try {
            //Stores a command
            String[] Inputs = new String[] {"/bin/bash", "-c","lsblk -o NAME,MOUNTPOINTS > DriveOutputs.txt"};
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
            //var Outputs = GetDriveAttributes.getInputStream().transferTo(System.out);
            //Displaying output (Temporarily).
            
            
            File GettingDriveOutputs = new File("DriveOutputs.txt");
            Scanner ReadingDriveOutputs = new Scanner(GettingDriveOutputs);
            
            DriveNames = new Stack();
            while(ReadingDriveOutputs.hasNextLine()){
                
                DriveNames.push(ReadingDriveOutputs.nextLine());
                
            }
            
            gbc.gridx=2;
            gbc.gridy=1;
            HardDriveReportContent.add(Report ,gbc);
            
           
            
        } catch (IOException ex) {
            Logger.getLogger(ALG.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        gbc.gridx=2;
        gbc.gridy=2;
        HardDriveReportContent.add(ReturnButton ,gbc);
            
        HardDriveReportWindow.setContentPane(HardDriveReportContent);
        
        HardDriveReportWindow.setBackground(Color.WHITE);
      
        HardDriveReportWindow.setVisible(true);
        
        
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
        
      
        JTable Report = new JTable();
        
        //Type of NAS build
        JButton UNRAID = new JButton("UNRAID");
        JButton TrueNAS = new JButton("TrueNAS");
        JButton ProxMox = new JButton("ProxMox");
       
        JButton ReturnButton = new JButton("Return to Home");

        
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
             File GettingDriveOutputs = new File("DriveOutputs.txt");
             Scanner ReadingDriveOutputs = new Scanner(GettingDriveOutputs);
            
             DriveNames = new Stack();
             while(ReadingDriveOutputs.hasNextLine()){
                
             DriveNames.push(ReadingDriveOutputs.nextLine());

             }
                
             System.out.println("Loading Data... Please Wait");
                
             if(DriveNames.empty()){
            
           
             NASStatus.setVisible(false);
             NASStatus.dispose();
            
             GetHardDriveReport();
            
            }else{
             
             
                 /*for(Object Line: DriveNames){
                     
                     NASStatusContent.add(new JButton((String) Line),gbc);
                     break;
                 }*/
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
        
        
        return NASStatus;
        
    }
    
}
