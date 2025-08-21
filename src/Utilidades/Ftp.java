/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import Conexion.ExeSql;
import Formularios.fmMain;
import static PanelForm.pfBuscaDoc.Lista;
import static Utilidades.DocElect.carpeta;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import net.sourceforge.barbecue.env.Environment;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.jfree.util.Log;


/**
 *
 * @author luvaly
 */




public class Ftp {

    
public static void baja_archivo_ftp(String server, int port,String user, String pass, String Remoto, String Local) {
    /* String server = "www.myserver.com";
        int port = 21;
        String user = "user";
        String pass = "pass";*/
 
        FTPClient ftpClient = new FTPClient();
        try {
 
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
          
            
            System.out.println(ftpClient.printWorkingDirectory());
 
            // APPROACH #1: using retrieveFile(String, OutputStream)
            String remoteFile1 = Remoto; //"/test/video.mp4";
            File downloadFile1 = new File(Local);  //new File("D:/Downloads/video.mp4");
            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
            boolean success = ftpClient.retrieveFile(remoteFile1, outputStream1);
            outputStream1.close();
 
            if (success) {
                System.out.println("Se ha descargado el archivo: " + remoteFile1);
            }
 
//            // APPROACH #2: using InputStream retrieveFileStream(String)
//            String remoteFile2 = "/test/song.mp3";
//            File downloadFile2 = new File("D:/Downloads/song.mp3");
//            OutputStream outputStream2 = new BufferedOutputStream(new FileOutputStream(downloadFile2));
//            InputStream inputStream = ftpClient.retrieveFileStream(remoteFile2);
//            byte[] bytesArray = new byte[4096];
//            int bytesRead = -1;
//            while ((bytesRead = inputStream.read(bytesArray)) != -1) {
//                outputStream2.write(bytesArray, 0, bytesRead);
//            }
// 
//            success = ftpClient.completePendingCommand();
//            if (success) {
//                System.out.println("File #2 has been downloaded successfully.");
//            }
//            outputStream2.close();
//            inputStream.close();
 
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

}

public static void busca_archivo_ftp(String server, int port,String user, String pass, String ruta_local, String archivo, final String filtro) {
    /* String server = "www.myserver.com";
        int port = 21;
        String user = "user";
        String pass = "pass";*/
        
       FTPClient ftp = new FTPClient();
       String Archivo_encontrado="";    
        // ArrayList <String> Lista  = new ArrayList();
   try {
    
     if (archivo == ""){    // Busqueda de Archivo en el FTP     
        FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX); //Este objeto nos 
        ftp.configure(conf); //permite configurar opciones de conexion como idioma y sistema de archivos
        ftp.connect(server,port); // ip del servidor ftp
        ftp.login(user, pass); // usuario y password para conectarnos al ftp 
        ftp.changeWorkingDirectory("/"); // Cambiamos de la raiz al subdirectorio uno
        ftp.enterLocalPassiveMode();
        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
           System.out.println(ftp.printWorkingDirectory());   
           System.out.println("1-Conectado");        
                    //Codigo  OK           
                    FTPFile[] archivos = ftp.listFiles();  // Obtiene los archivos del servidor y los mostramos
                    System.out.println("archivos a leer: " + archivos.length);        
                    Archivo_encontrado="";
                     for(int i =0; i<archivos.length; i++){
                          if (archivos[i].isDirectory()){
                          }
                          else
                          {
                              if(archivos[i].getName().contains(filtro)){
                                  System.out.println("Archivo Encontrado " + archivos[i].getName() );
                                    //Lista.add(archivos[i].getName());
                                    Archivo_encontrado=archivos[i].getName();
                                  break;       
                              }
                          }    
                      }
                     if (Archivo_encontrado == ""){
                            fmMain.Mensaje("Documento no Encontrado");
                            return;
                        }
                       
                      //copia el archivo a disco local y luego lo abre
                        ftp.enterLocalPassiveMode();
                        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
                        System.out.println(ftp.printWorkingDirectory());
                         // APPROACH #1: using retrieveFile(String, OutputStream)
                        String remoteFile1 ="/" +  Archivo_encontrado; //"/test/video.mp4";
                        File downloadFile1 = new File(ruta_local + Archivo_encontrado);  //new File("D:/Downloads/video.mp4");
                        OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
                        boolean success = ftp.retrieveFile(remoteFile1, outputStream1);
                        outputStream1.close();
                        if (success) {
                            System.out.println("Se ha copiado correctamnente el archivo: " + remoteFile1);
                        }
        }// end if 
        else{
                 Archivo_encontrado = archivo;            
            }
         if (Archivo_encontrado == ""){
             fmMain.Mensaje("Documento no Encontrado");
             return;
         }
         
        
   
         
            File pathCompleto = new File(ruta_local + Archivo_encontrado);
            if (!Archivo_encontrado.contains(".xml"))
            {
            Desktop.getDesktop().open(pathCompleto); 
            }
          

  
             if (ftp.isConnected()) {
                    ftp.logout();
                    ftp.disconnect();
             }

 
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftp.isConnected()) {
                    ftp.logout();
                    ftp.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

}
public static String busca_archivo_ftp_sinAbrir(String server, int port,String user, String pass, String ruta_local, String archivo, final String filtro) {
    /* String server = "www.myserver.com";
        int port = 21;
        String user = "user";
        String pass = "pass";*/
        
       FTPClient ftp = new FTPClient();
       String Archivo_encontrado="";    
        // ArrayList <String> Lista  = new ArrayList();
   try {
    
     if (archivo == ""){    // Busqueda de Archivo en el FTP     
        FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX); //Este objeto nos 
        ftp.configure(conf); //permite configurar opciones de conexion como idioma y sistema de archivos
        ftp.connect(server,port); // ip del servidor ftp
        ftp.login(user, pass); // usuario y password para conectarnos al ftp 
        ftp.changeWorkingDirectory("/"); // Cambiamos de la raiz al subdirectorio uno
        ftp.enterLocalPassiveMode();
        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
           System.out.println(ftp.printWorkingDirectory());   
           System.out.println("1-Conectado");        
                    //Codigo  OK           
                    FTPFile[] archivos = ftp.listFiles();  // Obtiene los archivos del servidor y los mostramos
                    System.out.println("archivos a leer: " + archivos.length);        
                    Archivo_encontrado="";
                     for(int i =0; i<archivos.length; i++){
                          if (archivos[i].isDirectory()){
                          }
                          else
                          {
                              if(archivos[i].getName().contains(filtro)){
                                  System.out.println("Archivo Encontrado " + archivos[i].getName() );
                                    //Lista.add(archivos[i].getName());
                                    Archivo_encontrado=archivos[i].getName();
                                  break;       
                              }
                          }    
                      }
                     if (Archivo_encontrado == ""){
                            fmMain.Mensaje("Documento no Encontrado");
                            return Archivo_encontrado;
                        }
                       
                      //copia el archivo a disco local y luego lo abre
                        ftp.enterLocalPassiveMode();
                        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
                        System.out.println(ftp.printWorkingDirectory());
                         // APPROACH #1: using retrieveFile(String, OutputStream)
                        String remoteFile1 ="/" +  Archivo_encontrado; //"/test/video.mp4";
                        File downloadFile1 = new File(ruta_local + Archivo_encontrado);  //new File("D:/Downloads/video.mp4");
                        OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
                        boolean success = ftp.retrieveFile(remoteFile1, outputStream1);
                        outputStream1.close();
                        if (success) {
                            System.out.println("Se ha copiado correctamnente el archivo: " + remoteFile1);
                            return Archivo_encontrado;
                        }
        }// end if 
        else{
                 Archivo_encontrado = archivo;  
                 return Archivo_encontrado;
            }
         if (Archivo_encontrado == ""){
             fmMain.Mensaje("Documento no Encontrado");
             return "";
         }
         
        
   
         //Abre Archivo
//            File pathCompleto = new File(ruta_local + Archivo_encontrado);
//            if (!Archivo_encontrado.contains(".xml"))
//            {
//            Desktop.getDesktop().open(pathCompleto); 
//            }
          

  
             if (ftp.isConnected()) {
                    ftp.logout();
                    ftp.disconnect();
             }

 
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
            return "";
        } finally {
            try {
                if (ftp.isConnected()) {
                    ftp.logout();
                    ftp.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
return "";
}



public static String busca_archivoFTP(String server, int port,String user, String pass, String ruta_local, String archivo, final String filtro) {
    /* String server = "www.myserver.com";
        int port = 21;
        String user = "user";
        String pass = "pass";*/
        
       FTPClient ftp = new FTPClient();
       String Archivo_encontrado="";    
        // ArrayList <String> Lista  = new ArrayList();
   try {
    
     if (archivo == ""){    // Busqueda de Archivo en el FTP     
        FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX); //Este objeto nos 
        ftp.configure(conf); //permite configurar opciones de conexion como idioma y sistema de archivos
        ftp.connect(server,port); // ip del servidor ftp
        ftp.login(user, pass); // usuario y password para conectarnos al ftp 
        ftp.changeWorkingDirectory("/"); // Cambiamos de la raiz al subdirectorio uno
        ftp.enterLocalPassiveMode();
        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
           System.out.println(ftp.printWorkingDirectory());   
           System.out.println("1-Conectado");        
                    //Codigo  OK           
                    FTPFile[] archivos = ftp.listFiles();  // Obtiene los archivos del servidor y los mostramos
                    System.out.println("archivos a leer: " + archivos.length);        
                    Archivo_encontrado="";
                     for(int i =0; i<archivos.length; i++){
                          if (archivos[i].isDirectory()){
                          }
                          else
                          {
                              if(archivos[i].getName().equals(filtro)){
                                  System.out.println("Archivo Encontrado " + archivos[i].getName() );
                                    //Lista.add(archivos[i].getName());
                                    Archivo_encontrado=archivos[i].getName();
                                  break;       
                              }
                          }    
                      }
                     if (Archivo_encontrado == ""){
                            System.out.println("Documento no Encontrado");
                            return Archivo_encontrado;
                        }
                       
                      //copia el archivo a disco local y luego lo abre
                        ftp.enterLocalPassiveMode();
                        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
                        System.out.println(ftp.printWorkingDirectory());
                         // APPROACH #1: using retrieveFile(String, OutputStream)
                        String remoteFile1 ="/" +  Archivo_encontrado; //"/test/video.mp4";
                        File downloadFile1 = new File(ruta_local + Archivo_encontrado);  //new File("D:/Downloads/video.mp4");
                        OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
                        boolean success = ftp.retrieveFile(remoteFile1, outputStream1);
                        outputStream1.close();
                        if (success) {
                            System.out.println("Se ha copiado correctamnente el archivo: " + remoteFile1);
                            return Archivo_encontrado;
                        }
        }// end if 
        else{
                 Archivo_encontrado = archivo;  
                 return Archivo_encontrado;
            }
         if (Archivo_encontrado == ""){
             fmMain.Mensaje("Documento no Encontrado");
             return "";
         }
         
        
   
         //Abre Archivo
//            File pathCompleto = new File(ruta_local + Archivo_encontrado);
//            if (!Archivo_encontrado.contains(".xml"))
//            {
//            Desktop.getDesktop().open(pathCompleto); 
//            }
          

  
             if (ftp.isConnected()) {
                    ftp.logout();
                    ftp.disconnect();
             }

 
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
            return "";
        } finally {
            try {
                if (ftp.isConnected()) {
                    ftp.logout();
                    ftp.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
return "";
}




public static String    BuscaArchivos(File ruta, String docElec){
        String nombre="";
          File archivos[]= ruta.listFiles();
          if (archivos != null){
            for(int i =0; i<archivos.length; i++){
              if (archivos[i].isDirectory()){
                  BuscaArchivos(archivos[i], docElec);
              }
              else
              {
                  Lista.add(archivos[i].getName());
                  if(archivos[i].getName().contains(docElec)){
                      nombre = archivos[i].getName();
                      System.out.println("Archivo Encontrado " + nombre );
                      break;
                  }
              }    
          } //end for
      } // end if
       return nombre;
        
    }// Fin Busca Archivos    

  public static void busca(String tip, String num){
    
       ExeSql Sql = new ExeSql();
        ResultSet Rs;
        String Query="";
        String path;
        String server,user,pass,ruta_local="";
        int puerto =21;
        String rutaimage="";
        String filtro="";
        String archivo_local = "";
     
try {

           final String Tipo;
           final String Numero;
           int cont =0; 
            
              
            Tipo=tip;
            Numero= num;
            
//---------                     Trae Datos Ftp            ----------------------
//Creado por CRM - 15-06-2017            
 //trae datos ftp
            server="";
            puerto=0;
            user=""; 
            pass="";
            	Rs = Sql.Select("SELECT * from conexiones where tipo='ftp_dte_d'");
                if (Rs.next())
                {
                    if(!fmMain.GetInternet()){
                        server = Rs.getString("serv");
                        user  = Rs.getString("usu");
                        puerto  = Rs.getInt("port");
                        pass = Rs.getString("pass");
                    }
                    else {
                        server = "179.57.172.101";
                        user  = Rs.getString("usu");
                        puerto  = 21;
                        pass = Rs.getString("pass");
                    }
                }
              
//                  //Verifica SO
                    String sistema = System.getProperty("os.name").toLowerCase();
                    File folder = new File("");
                    if (sistema.contains("win"))
                    {
                         ruta_local = "c:/" + carpeta + "/disosur/";
                         folder = new File(ruta_local.substring(0,ruta_local.length()-1));
                    }
                    else{
                          ruta_local = "/" + carpeta + "/disosur/";
                          folder = new File(ruta_local);
                    }
                    if (!folder.exists())
                    {
                        folder.mkdir();
                    }
                    
                     System.out.println("Carpeta Asignada " + ruta_local );
            
                    // Primer buscara el archivo en ruta local
                    System.out.println(Numero);
                    String archivo ="DTE"+Tipo+"" + Numero + "-nc.PDF";
                    System.out.println("Busca en carpeta  local " + ruta_local +"/"+archivo );
                    archivo_local = BuscaArchivos(new File(ruta_local),archivo);
                    System.out.println("Busca archivo en FTP -->" + ruta_local +"/"+archivo );
                    Ftp.busca_archivo_ftp(server, puerto, user, pass, ruta_local,archivo_local, archivo);
                    System.out.println("Sale de Busca Archivo" + filtro );
                        }
        catch (Exception e) {
            fmMain.Mensaje("Existe una inconsistencia en la información.");
            System.out.println(e.getMessage());
        }finally{
            Sql.Close();
        }
}

  public static String  busca_SinAbrir(String tip, String num,String carpeta,String usuario ){
    
       ExeSql Sql = new ExeSql();
        ResultSet Rs;
        String Query="";
        String path;
        String server,user,pass,ruta_local="";
        int puerto =21;
        String rutaimage="";
        String filtro="";
        String archivo_local = "";
        String Archivo_Encontrado="";
        String Arch = "";
     
try {

           final String Tipo;
           final String Numero;
           int cont =0; 
            
              
            Tipo=tip;
            Numero= num;
            
//---------                     Trae Datos Ftp            ----------------------
//Creado por CRM - 15-06-2017            
 //trae datos ftp
            server="";
            puerto=0;
            user=""; 
            pass="";
            	Rs = Sql.Select("SELECT * from conexiones where usu='" + usuario +"'");
                if (Rs.next())
                {
                    if(!fmMain.GetInternet()){
                        server = Rs.getString("serv");
                        user  = Rs.getString("usu");
                        puerto  = Rs.getInt("port");
                        pass = Rs.getString("pass");
                    }
                    else {
                        server = "179.57.172.101";
                        user  = Rs.getString("usu");
                        puerto  = 21;
                        pass = Rs.getString("pass");
                    }
                }
              
//                  //Verifica SO
                    String sistema = System.getProperty("os.name").toLowerCase();
                    
                    File f = new File("");
                    if (sistema.contains("win"))
                    {
                         ruta_local = "c:/" + carpeta + "/disosur/";
                         f = new File(ruta_local.substring(0,ruta_local.length()-1));
                    }
                    else{
                          ruta_local = "/" + carpeta + "/disosur/";
                          //folder = new File(ruta_local);
                          f = new File(ruta_local);
                    }
//                    if (!folder.exists())
//                    {
//                        folder.mkdir();
//                    }
                    if(!f.isDirectory()) {
                    String newFolder = ruta_local; //cualquierCarpeta es el nombre de la Carpeta que vamos a crear
                    
                    File myNewFolder = new File(newFolder);
                    myNewFolder.mkdirs(); //creamos la carpeta
                   }
                    else
                   {
                          System.out.println("La carpeta ya estaba creada");
                   }
                    
                     System.out.println("Carpeta Asignada " + ruta_local );
            
                     
                     
                     
                     
                     
                     
                     
                    // Primer buscara el archivo en ruta local
                    if (usuario.equals("dte_dis")) {
                        filtro = "DTE"+Tipo + "-F" + Numero + "-nc.PDF";
                    }
                    else if (carpeta.equals("tnt"))
                    {
                        filtro = Numero + ".pdf";
                    }    
                    else if (carpeta.equals("blue"))
                    {
                        filtro = Numero + ".gif";
                    }    
                    
                    
                    
                    System.out.println("Busca en carpeta  local  -->" + ruta_local +"/"+filtro );
                    archivo_local = BuscaArchivos(new File(ruta_local),filtro);
                    System.out.println("Busca archivo en FTP -->" + ruta_local +"/"+filtro );
                    Archivo_Encontrado =Ftp.busca_archivo_ftp_sinAbrir(server, puerto, user, pass, ruta_local,archivo_local, filtro);
                    System.out.println("Sale de Busca Archivo" + filtro );
                    
                    if (Archivo_Encontrado.equals(""))
                    {
                         Arch = Archivo_Encontrado;
                    }
                    else
                    {
                         Arch = ruta_local + Archivo_Encontrado;
                    }    
                    
                    System.out.println("Achivo encontrado:-->  " + Arch);
                    return Arch;
                    }
catch (Exception e) {
            fmMain.Mensaje("Existe una inconsistencia en la información.");
            return "";
        }finally{
            Sql.Close();
        }
}




}  // FIN CLASE Ftp


