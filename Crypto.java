package embedjava;




import java.io.File;

import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;




import java.io.IOException;




import java.security.InvalidKeyException;

import java.security.NoSuchAlgorithmException;




import javax.crypto.Cipher;

import javax.crypto.CipherInputStream;

import javax.crypto.CipherOutputStream;

import javax.crypto.NoSuchPaddingException;

import javax.crypto.spec.SecretKeySpec;




public class Crypto{

    

    private String algo;

    private File file;

    private int file_no;

 

    public Crypto(String algo,String path) {

     this.algo=algo; //setting algo

     this.file=new File(path); //settong file

    }

    

     public void encrypt(int i) throws Exception{

         //opening streams

         FileInputStream fis =new FileInputStream(file);

         file=new File("C:\\JDeveloper\\mywork\\TestSOAUnzipFile\\SourceFolder\\Archive\\Citizen_" + i + ".zip");

         FileOutputStream fos =new FileOutputStream(file);

         

         byte k[] = ("bb5"+"1860"+ "17a74"+ "213f").getBytes();

         SecretKeySpec key = new SecretKeySpec(k, "AES");

         Cipher encrypt = Cipher.getInstance("AES");

         

         //generating key

        // byte k[] = "HignDlPs".getBytes();   

         //SecretKeySpec key = new SecretKeySpec(k,algo.split("/")[0]);  

         //creating and initialising cipher and cipher streams

         //Cipher encrypt =  Cipher.getInstance(algo);  

         encrypt.init(Cipher.ENCRYPT_MODE, key);  

         CipherOutputStream cout=new CipherOutputStream(fos, encrypt);

         

         byte[] buf = new byte[1024];

         int read;

         while((read=fis.read(buf))!=-1)  //reading data

             cout.write(buf,0,read);  //writing encrypted data

         //closing streams

         fis.close();

         cout.flush();

         cout.close();

     }

     

     public void decrypt() {

         //opening streams

         FileInputStream fis = null;

         FileOutputStream fos = null;

        try {

            fis = new FileInputStream(file);

        

        file=new File("C:\\JDeveloper\\mywork\\TestSOAUnzipFile\\SourceFolder\\Decrypted\\both.zip");

         fos =new FileOutputStream(file);  

         } catch (FileNotFoundException e) {

         }

         byte k[] = ("bb5"+"1860"+ "17a74"+ "213f").getBytes();

         SecretKeySpec key = new SecretKeySpec(k, "AES");

         Cipher decrypt = null;

         

         

         //generating same key

//         byte k[] = "HignDlPs".getBytes();   

//         SecretKeySpec key = new SecretKeySpec(k,algo.split("/")[0]);  

//         //creating and initialising cipher and cipher streams

//         Cipher decrypt =  Cipher.getInstance(algo);

        try {

            decrypt = Cipher.getInstance("AES");

        } catch (NoSuchAlgorithmException e) {

        } catch (NoSuchPaddingException e) {

        }

        try {

            decrypt.init(Cipher.DECRYPT_MODE, key);

        } catch (InvalidKeyException e) {

        }

        CipherInputStream cin=new CipherInputStream(fis, decrypt);

              

         byte[] buf = new byte[1024];

         int read=0; //writing decrypted data

         //closing streams

        try {

            while((read=cin.read(buf))!=-1)  //reading encrypted data

              fos.write(buf,0,read);

        } catch (IOException e) {

        }

        try {

            cin.close();

            fos.flush();

            fos.close();

        } catch (IOException e) {

        }

        

     }

     

     public static void main (String[] args)throws Exception {

         for (int i=101; i<=111; i++) {     

         new Crypto("AES/ECB/PKCS5Padding","C:\\JDeveloper\\mywork\\TestSOAUnzipFile\\SourceFolder\\Citizen_" + i + ".zip").encrypt(i);

         //new Crypto("AES/ECB/PKCS5Padding","C:\\JDeveloper\\mywork\\TestSOAUnzipFile\\SourceFolder\\Encrypted\\both.zip").decrypt();

         }

  }

}