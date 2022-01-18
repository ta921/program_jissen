import java.io.*;
import java.net.*;

class SmpNetwork01 {
   public static void main(String args[]){
      try{
         InetAddress ia[] = InetAddress.getAllByName(args[0]);
         for( int i = 0 ; i < ia.length ; i++ ){
            System.out.println("Host Name: " + ia[i].getHostName());
            System.out.println("Host Address: " + ia[i].getHostAddress());
            System.out.println();
         }
      }catch(Exception e){
         System.err.println(e);
      }
   }
}
