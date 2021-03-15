/*Aþaðýdaki yazýlýmda Java ile yazýlmýþ bir sayý tahmin oyunu verilmiþtir.
 * Bu oyun iki aþamadan oluþmaktadýr, ilk aþamada bilgisayar rastgele, rakamlarý farklý 
 * bir sayý tutmaktadýr ve kullanýcý bunu tahmin etmeye çalýþmaktadýr, ikinci aþamada ise 
 * kullanýcý rastgele,aklýndan bir sayý tutmaktadýr(bunu herhangi bir þekilde input olarak girmemeli ve
 * paylaþmamalýdýr) ve bilgisayar kullanýcýnýn tuttuðu sayýyý tahmin etmeye çalýþmaktadýr.Her iki taraf 
 * da tahminler yapýldýktan sonra, bir sonraki tahminin yapýlabilmesi için ipuçlarý vermektedir.Ýpuçlarý 
 * eðer sayýda doðru basamak varsa(kaç tane doðru basamak olduðuna göre) +, eðer sayý doðruysa ama bulunduðu
 * basamak yanlýþsa(kaç tane yanlýþ yerde doðru sayý olduðuna göre) - þeklinde verilmektedir. Her iki taraftan 
 * biri diðerinin sayýsýný tahmin ettiðinde oyun sonlanmaktadýr.
*/
//Yazan Gözde Etiz
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class SayiTahmin {

     public static void main(String[] args) {
          runGame();
     }

     public static int[] randomNumber(int lengthOfNumber, int[] array) {
         /* Random sayýnýn üreten method. Ýlk rakamýn 0'dan baþlamamasý için 
          * öncelikle while döngüsü ile kontrol ediyoruz.For döngüsünün içinde dizimizi basamak sayýsý
          * kadar döndürüp 0-9 aralýðýnda random deðer atamasý yapýyoruz. Diðer for döngüsünde 
          * rakamlarýn birbirinden farklý olmasýný kontrol ediyoruz ve bu kontrol iþleminin dizinin hep ilk elemanýndan baþlamasý
          * için j deðerini döngüden çýkmadan önce sürekli sýfýrlýyoruz.
          */

          Random r = new Random();

          int number, randomArray[] = new int[lengthOfNumber];

          while (randomArray[0] == 0) {

               for (int i = 0; i < randomArray.length; i++) {
                    number = r.nextInt(10);

                    for (int j = 0; j < randomArray.length; j++) {
                         if (randomArray[j] == number) {
                              number = r.nextInt(10);
                              j = 0;
                         }
                    }
                    randomArray[i] = number;
               }
          }
          return randomArray;
     }

     public static void writeArray(int[] array) {
    	 /* Dizinin ekrana yazdýrýldýðý metot */
         int i=0;
          for (i=0;i<array.length;i++) {
               System.out.print(array[i]);
          }

     }

     public static int[] decompose(int lengthOfNumber, int number, int[] array) {
         /*  Kullanýcýn girdiði sayýnýn basamaklarýna ayrýldýðý metot. Buradaki
          *  for döngüsünde sayýnýn sürekli 10'a modu alýnýp devamýnda 10'a bölünerek 
          *  dizinin son elemanýndan ilk elemanýna kadar atama iþlemi yapýlýr. */

          while (number > 0) {
               for (int i = 0; i < lengthOfNumber; i++) {
                    int numberForArray = 0;
                    numberForArray = number % 10;
                    array[lengthOfNumber - i - 1] = numberForArray;
                    number = number / 10;
               }
          }
          return array;
     }

     public static String runGame() {
         
          Scanner scan = new Scanner(System.in);
          
          int lengthOfNumber = 4;

          /* Ýki tane dizi yaratýyoruz.Bilgisayarýn belirlediðimiz basamak 
          * sayýsýna göre random deðerle ürettiði sayýyý bu dizi içinde tutacaðýz = computerArray
          * Kullanýcýn girdiði sayýyý bu dizi içinde tutacaðýz = myArray
          */
          int[] computerArray = new int[lengthOfNumber];
          int[] myArray = new int[lengthOfNumber];
          int[] userArray=new int[lengthOfNumber];

          computerArray = randomNumber(lengthOfNumber, computerArray);
          userArray=randomNumber(lengthOfNumber,computerArray);

          //System.out.print("Bilgisayarýn tuttuðu sayý: ");
          //writeArray(computerArray);
          
          //Bilgisayar random sayý üreterek kullanýcýnýn sayýsýný tahmin etmeye çalýþýr.
          System.out.println();
          System.out.print("Kullanýcýnýn tuttuðu sayý bu mu?: ");
          writeArray(userArray);

          /* Ýki rakam eþleþiyorsa ve sýra nolarý ayný ise countForSameOrder
          * integer'ý ile bu deðeri tutuyoruz. Ýleride bu deðeri tahmine göre
          * arttýrabiliriz.
          */
          /* Ýki rakam eþleþiyorsa ama sýra nolarý farklý ise
          * countForDifferentOrder integer'ý ile bu deðeri tutuyoruz. Ýleride
          * bu deðeri tahmine göre azaltabiliriz.
          */
          
          String str = null;
          while (!Arrays.equals(computerArray, myArray)) {
               int countForSameOrder = 0;
               int countForDifferentOrder = 0;
               System.out.println();
               
               String str1,str2,str3;
               

               str1=JOptionPane.showInputDialog("Lütfen 4 basamaklý tahmininizi girin: ");
               int guess = Integer.parseInt(str1);

               int lenghtOfGuess = Integer.valueOf(guess).toString().length();   
               
               //Eðer 4 basamaktan fazla sayý girilirse uyarý verir.

               while (lenghtOfGuess != lengthOfNumber) {
                    System.out.print("Lütfen belirlediðiniz basamak sayýsýnda tahmininizi giriniz ("+ lengthOfNumber + " basamaklý) :");

                    guess = scan.nextInt();
                    lenghtOfGuess = Integer.valueOf(guess).toString().length();

               }  
               //Eðer sayý yerine harf girilirse uyarý verir
               while(guess!=Integer.parseInt(str1)) {
            	   System.out.println("Harf girdiniz.Lütfen geçerli bir tahmin girin: ");
            	   guess = scan.nextInt();
            	   }             

               //Girilen tahmin sayýsýný basamaklarýna ayýrýr
               decompose(lengthOfNumber, guess, myArray);
               
               //Tahmin sayýsýnýn ilk basamðýnda 0 olursa uyaru verir
               while(myArray[0]==0) {
            	   System.out.println("Ýlk basamakta 0 olamaz.Lütfen geçerli bir tahmin girin: ");
            	   guess = scan.nextInt();
               }               
               
               for (int i = 0; i < lengthOfNumber; i++) {
                    for (int j = 0; j < lengthOfNumber; j++) {
                         if (computerArray[i] == myArray[j] && i == j)
                              countForSameOrder++;  //sayýlar eþleþiyorsa ve basamak no larý da doðruysa +1 arttýtýyoruz
                         if (computerArray[i] == myArray[j] && i != j)
                              countForDifferentOrder--;  //sayýlar eþleþiyorsa ama basamak no larý farklýysa -1 azaltýyoruz
                    }
               }               
               JOptionPane.showMessageDialog(null,"Tahmin yapýldý: "+guess,"Tahmin",JOptionPane.INFORMATION_MESSAGE);

               /*Tahmin yapýlýr ve gösterilir.Eðer doðruysa tebrik mesajý verilir ve program sonlanýr
                * Yapýlan tahmin doðru deðilse kullanýcýnýn bir sonraki tahmini yapabilmesi için ipucu verilir
                */
               if (countForSameOrder == lengthOfNumber) {
            	   JOptionPane.showMessageDialog(null,"Tebrikler + "+lengthOfNumber+" Doðru cevap bulundu" ,"Doðru Tahmin",JOptionPane.INFORMATION_MESSAGE);
            	   break;

               } else {
            	   JOptionPane.showMessageDialog(null,guess+"\n"+countForSameOrder+" "+countForDifferentOrder,"Tahmin",JOptionPane.INFORMATION_MESSAGE);

               }
               
               //Bilgisayar kullanýcýnýn sayýsýný tahmin etmeye çalýþýr.Doðru tahmin ederse program sonlanýr
               int reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
               if (reply == JOptionPane.YES_OPTION)
               {
                 JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                 break;
               }
               //Yapýlan tahmin doðru deðilse kullanýcýdan ipuçlarý(+ ve -) vermesini ister.
               else {
            	   str2=JOptionPane.showInputDialog("Ýpucu giriniz:");
                   int ipucu1=Integer.parseInt(str2);
                   str3=JOptionPane.showInputDialog("Diðer ipucunu giriniz:");
                   int ipucu2=Integer.parseInt(str3);
                   
                   //Girilen ipuçlarý geçersiz ise uyarý verir
                   while (ipucu1>4||ipucu1<0||ipucu2>0||ipucu2<-4) {
                	   str2=JOptionPane.showInputDialog("Ýpucu giriniz:");
                       ipucu1=Integer.parseInt(str2);
                       str3=JOptionPane.showInputDialog("Diðer ipucunu giriniz:");
                       ipucu2=Integer.parseInt(str3);
                  }
                   //Ýpucu olarak sayý yerine harf girilirse uyarý verir
                   while(ipucu1!=Integer.parseInt(str2)||ipucu2!=Integer.parseInt(str3)) {
                	   System.out.println("Geçersiz formatta ipucu girdiniz lütfen tekrar girin: ");
                	   ipucu1=scan.nextInt();
                	   ipucu2=scan.nextInt();
                   }
                   
                   /*Bilgisayarýn kullanýcýnýn sayýsýný tahmin etmeye çalýþtýðý kýsým.
                    * Bu kýsým çok uzun olduðu için açýklamakta yarar var. Kullanýcý bilgisayara yardýmcý olmak 
                    * için 0,1,2,3,4 yada 0,-1,-2,-3,-4 þeklinde ipucu giriyor( eðer doðru sayý ve basamak sayýsý varsa
                    * ipucu1 +, eðer doðru sayý yanlýþ yerdeyse ipucu2 -). Girilen ipucu1 ve ipucu2 sayýsýna göre bilgisayar
                    * kaç tane doðru basamak veya kaç tane yanlýþ yerde sayý varsa o ipucu sayýsýna denk gelen döngüye giriyor 
                    * ve o döngü içerisindeki bütün olasýlýklarý deneyerek doðru sayýyý bulmaya çalýþýyor. Örneðin +1 girildiði zaman
                    * bilgisayar yaptýðý tahmindeki bir tane elemanýn doðru basamakta olduðunu anlýyor ve her seferinde bir tane basamaðý
                    * sabit tutarak diðer elemanlarý tekrardan rastgele üretiyor.Ya da örneðin -2 ipucu olarak girildiði zaman iki sayýnýn
                    * doðru olduðunu ama yanlýþ yerde olduðunu anlýyor ve bütün olasýlýklarý deneyerek iki sayýyý ayný tutup yerlerini 
                    * deðiþtirerek diðer sayýlarý rastgele deðiþtiriyor ve tekrar tahmin yapýyor. Burda her ipucuna denk gelen sayý olasýlýklarýný
                    * kendim hesaplayýp düþünerek yerleþtirdim.
                    */
                   
                   //Eðer ipucu 0 ise hiçbir basamaktaki eleman doðru denk gelmemiþ olduðu için sayý tekrardan üretiliyor.
                   if(ipucu1==0) {
                       userArray=randomNumber(lengthOfNumber,computerArray);
                	   System.out.print("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                       writeArray(userArray);
                       reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);                       
                       if (reply == JOptionPane.YES_OPTION)
                       {
                         JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                         break;
                       }
                       else {
                    	   continue;
                       }
                   }
                   
                   /*Ýpucu 1 olduðu zaman tahmin edilen sayýda 1 tane sayý ve basamak no su doðru demek olduðu için 
                    * bilgisayar bütün olasýlýklarý deneyerek hangi basamak ve sayýnýn doðru olduðunu bulmaya çalýþýyor ve 
                    * her seferinde kullanýcýya doðru olup olmadýðýný soruyor
                   */
                   if(ipucu1==1) {
                	   Random r=new Random();
                	   Random r1=new Random();
                	   Random r2=new Random();
                	   
                	   int[] userArray2=new int[4];
                	   userArray2=userArray;
                	   userArray2[0]=userArray[0];
                	   userArray2[1]=r.nextInt(10);
                	   userArray2[2]=r1.nextInt(10);
                	   userArray2[3]=r2.nextInt(10);
                	   //writeArray(userArray);
                	   System.out.print("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                       writeArray(userArray2);
                       reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);                       
                       if (reply == JOptionPane.YES_OPTION)
                       {
                         JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                         break;
                       }
                       else {
                    	   int[] userArray3=new int[4];
                    	   userArray3=userArray;
                    	   userArray3[0]=r.nextInt(10);
                    	   userArray3[1]=userArray[1];
                    	   userArray3[2]=r1.nextInt(10);
                    	   userArray3[3]=r2.nextInt(10);
                    	   
                       	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                           writeArray(userArray3);
                           
                           if (reply == JOptionPane.YES_OPTION)
                           {
                             JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                             break;
                           }
                           else {
                        	   int[] userArray4=new int[4];
                        	   userArray4=userArray;
                        	   userArray4[0]=r.nextInt(10);
                        	   userArray4[1]=r1.nextInt(10);
                        	   userArray4[2]=userArray[2];
                        	   userArray4[3]=r2.nextInt(10);
                        	   
                           	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                               writeArray(userArray4);
                               
                               if (reply == JOptionPane.YES_OPTION)
                               {
                                 JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                                 break;
                               }
                               else {
                            	   int[] userArray5=new int[4];
                            	   userArray5=userArray;
                            	   userArray5[0]=r.nextInt(10);
                            	   userArray5[1]=r1.nextInt(10);
                            	   userArray5[2]=r2.nextInt(10);
                            	   userArray4[3]=userArray[3];
                            	   
                               	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                                   writeArray(userArray5);
                                   
                                   if (reply == JOptionPane.YES_OPTION)
                                   {
                                     JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                                     break;
                                   }                                                                      
                               }                               
                           }                  	                 	   
                    	                      	   
                       }    
                       
                       /*Ýpucu 2 olduðu zaman tahmin edilen sayýda 2 tane sayý ve basamak no su doðru demek olduðu için 
                        * bilgisayar bütün olasýlýklarý deneyerek hangi basamaktaki hangi sayýlarýn doðru olduðunu bulmaya çalýþýyor ve 
                        * her seferinde kullanýcýya doðru olup olmadýðýný soruyor
                       */
                   }else if(ipucu1==2) {
                   	   //Random r=new Random();
                	   Random r1=new Random();
                	   Random r2=new Random();
                	   
                	   int[] userArray2=new int[4];
                	   userArray2=userArray;
                	   userArray2[0]=userArray[0];
                	   userArray2[1]=userArray[1];
                	   userArray2[2]=r1.nextInt(10);
                	   userArray2[3]=r2.nextInt(10);
                	   //writeArray(userArray);
                	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                       writeArray(userArray2);
                       reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);                       
                       if (reply == JOptionPane.YES_OPTION)
                       {
                         JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                         break;
                       }
                       else {
                    	   int[] userArray3=new int[4];
                    	   userArray3=userArray;
                    	   userArray3[0]=userArray[0];
                    	   userArray3[1]=r1.nextInt(10);
                    	   userArray3[2]=userArray[2];
                    	   userArray3[3]=r2.nextInt(10);
                    	   //writeArray(userArray);
                    	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                           writeArray(userArray3);
                           
                           if (reply == JOptionPane.YES_OPTION)
                           {
                             JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                             break;
                           }else {
                        	   int[] userArray4=new int[4];
                        	   userArray4=userArray;
                        	   userArray4[0]=userArray[0];
                        	   userArray4[1]=r1.nextInt(10);
                        	   userArray4[2]=r2.nextInt(10);
                        	   userArray4[3]=userArray[3];
                        	   //writeArray(userArray);
                        	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                               writeArray(userArray4);
                               
                               if (reply == JOptionPane.YES_OPTION)
                               {
                                 JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                                 break;
                               }else {
                            	   int[] userArray5=new int[4];
                            	   userArray5=userArray;
                            	   userArray5[0]=r1.nextInt(10);
                            	   userArray5[1]=userArray[1];
                            	   userArray5[2]=userArray[2];
                            	   userArray5[3]=r2.nextInt(10);
                            	   //writeArray(userArray);
                            	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                                   writeArray(userArray5);
                                   
                                   if (reply == JOptionPane.YES_OPTION)
                                   {
                                     JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                                     break;
                                   }else {
                                	   int[] userArray6=new int[4];
                                	   userArray6=userArray;
                                	   userArray6[0]=r1.nextInt(10);
                                	   userArray6[1]=userArray[1];
                                	   userArray6[2]=r2.nextInt(10);
                                	   userArray6[3]=userArray[3];
                                	   //writeArray(userArray);
                                	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                                       writeArray(userArray6);
                                       
                                       if (reply == JOptionPane.YES_OPTION)
                                       {
                                         JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                                         break;
                                       }else {
                                    	   int[] userArray7=new int[4];
                                    	   userArray7=userArray;
                                    	   userArray7[0]=r1.nextInt(10);
                                    	   userArray7[1]=r2.nextInt(10);
                                    	   userArray7[2]=userArray[2];
                                    	   userArray7[3]=userArray[3];
                                    	   //writeArray(userArray);
                                    	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                                           writeArray(userArray7);
                                       }
                                       if (reply == JOptionPane.YES_OPTION)
                                       {
                                         JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                                         break;
                                       }
                                   }                                  
                                   
                               }                               
                               
                           }
                          
                       }
                       /*Ýpucu 3 olduðu zaman tahmin edilen sayýda 3 tane sayý ve basamak no su doðru demek olduðu için 
                        * bilgisayar bütün olasýlýklarý deneyerek hangi basamaktaki hangi sayýlarýn doðru olduðunu bulmaya çalýþýyor ve 
                        * her seferinde kullanýcýya doðru olup olmadýðýný soruyor
                       */
                   }else if(ipucu1==3) {
                	   //Random r=new Random();
                	   Random r1=new Random();
                	   //Random r2=new Random();
                	   
                	   int[] userArray2=new int[4];
                	   userArray2=userArray;
                	   userArray2[0]=userArray[0];
                	   userArray2[1]=userArray[1];
                	   userArray2[2]=userArray[2];
                	   userArray2[3]=r1.nextInt(10);
                	   //writeArray(userArray);
                	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                       writeArray(userArray2);
                       reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                       if (reply == JOptionPane.YES_OPTION)
                       {
                         JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                         break;
                       }else {
                    	   int[] userArray3=new int[4];
                    	   userArray3=userArray;
                    	   userArray3[0]=userArray[0];
                    	   userArray3[1]=userArray[1];
                    	   userArray3[2]=r1.nextInt(10);
                    	   userArray3[3]=userArray[3];
                    	   //writeArray(userArray);
                    	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                           writeArray(userArray3);
                           reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                           if (reply == JOptionPane.YES_OPTION)
                           {
                             JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                             break;
                           }else {
                        	   int[] userArray4=new int[4];
                        	   userArray4=userArray;
                        	   userArray4[0]=userArray[0];
                        	   userArray4[1]=r1.nextInt(10);
                        	   userArray4[2]=userArray[2];
                        	   userArray4[3]=userArray[3];
                        	   //writeArray(userArray);
                        	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                               writeArray(userArray4);
                               
                               reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                               if (reply == JOptionPane.YES_OPTION)
                               {
                                 JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                                 break;
                               }else {
                            	   int[] userArray5=new int[4];
                            	   userArray5=userArray;
                            	   userArray5[0]=r1.nextInt(10);
                            	   userArray5[1]=userArray[1];
                            	   userArray5[2]=userArray[2];
                            	   userArray5[3]=userArray[3];
                            	   //writeArray(userArray);
                            	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                                   writeArray(userArray5);
                                   
                                   reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                                   if (reply == JOptionPane.YES_OPTION)
                                   {
                                     JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                                     break;
                                   }
                                   
                               }
                           }
                           
                       }
                	   //Ýpucu 4 olduðu zaman bütün basamaklar doðru demek olduðu için program sonlamýyor
                   }else if(ipucu1==4) {
                	   JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                       break;
                   }
                   /*Ýpucu -1 olduðu zaman tahmin yapýlan sayýnýn içindeki 1 sayýnýn doðru sayý ama yanlýþ basamakta 
                    * olduðunu anlýyoruz, bilgisayar bütün olasýlýklarý deneyerek hangi sayýnýn hangi basamakta yer 
                    * almasý gerektiðini bulmaya çalýþýyor.                  
                    */
                   if(ipucu2==-1) {
                	   Random r=new Random();
                	   Random r1=new Random();
                	   Random r2=new Random();
                	   
                	   int[] userArray2=new int[4];
                	   userArray2=userArray;
                	   userArray2[0]=userArray[1];
                	   userArray2[1]=r.nextInt(10);
                	   userArray2[2]=r1.nextInt(10);
                	   userArray2[3]=r2.nextInt(10);
                	   //writeArray(userArray);
                	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                       writeArray(userArray2);
                       
                       reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                       if (reply == JOptionPane.YES_OPTION)
                       {
                         JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                         break;
                       }else {
                    	   int[] userArray3=new int[4];
                    	   userArray3=userArray;
                    	   userArray3[0]=userArray[2];
                    	   userArray3[1]=r.nextInt(10);
                    	   userArray3[2]=r1.nextInt(10);
                    	   userArray3[3]=r2.nextInt(10);
                    	   //writeArray(userArray);
                    	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                           writeArray(userArray3);
                           
                           reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                           if (reply == JOptionPane.YES_OPTION)
                           {
                             JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                             break;
                           }else {
                        	   int[] userArray4=new int[4];
                        	   userArray4=userArray;
                        	   userArray4[0]=userArray[2];
                        	   userArray4[1]=r.nextInt(10);
                        	   userArray4[2]=r1.nextInt(10);
                        	   userArray4[3]=r2.nextInt(10);
                        	   //writeArray(userArray);
                        	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                               writeArray(userArray4);
                               
                               reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                               if (reply == JOptionPane.YES_OPTION)
                               {
                                 JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                                 break;
                               }else {
                            	   int[] userArray5=new int[4];
                            	   userArray5=userArray;
                            	   userArray5[0]=r.nextInt(10);;
                            	   userArray5[1]=userArray[0];
                            	   userArray5[2]=r1.nextInt(10);
                            	   userArray5[3]=r2.nextInt(10);
                            	   //writeArray(userArray);
                            	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                                   writeArray(userArray5);
                                   
                                   reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                                   if (reply == JOptionPane.YES_OPTION)
                                   {
                                     JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                                     break;
                                   }else {
                                	   int[] userArray6=new int[4];
                                	   userArray6=userArray;
                                	   userArray6[0]=r.nextInt(10);;
                                	   userArray6[1]=userArray[2];
                                	   userArray6[2]=r1.nextInt(10);
                                	   userArray6[3]=r2.nextInt(10);
                                	   //writeArray(userArray);
                                	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                                       writeArray(userArray6);
                                       
                                       reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                                       if (reply == JOptionPane.YES_OPTION)
                                       {
                                         JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                                         break;
                                       }
                                       else {
                                    	   int[] userArray7=new int[4];
                                    	   userArray7=userArray;
                                    	   userArray7[0]=r.nextInt(10);
                                    	   userArray7[1]=userArray[3];
                                    	   userArray7[2]=r1.nextInt(10);
                                    	   userArray7[3]=r2.nextInt(10);
                                    	   //writeArray(userArray);
                                    	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                                           writeArray(userArray7);
                                           
                                           reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                                           if (reply == JOptionPane.YES_OPTION)
                                           {
                                             JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                                             break;
                                           }
                                           else {
                                        	   int[] userArray8=new int[4];
                                        	   userArray8=userArray;
                                        	   userArray8[0]=r.nextInt(10);
                                        	   userArray8[1]=r1.nextInt(10);
                                        	   userArray8[2]=userArray[0];
                                        	   userArray8[3]=r2.nextInt(10);
                                        	   //writeArray(userArray);
                                        	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                                               writeArray(userArray8);
                                               
                                               reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                                               if (reply == JOptionPane.YES_OPTION)
                                               {
                                                 JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                                                 break;
                                               }
                                               else {
                                            	   int[] userArray9=new int[4];
                                            	   userArray9=userArray;
                                            	   userArray9[0]=r.nextInt(10);
                                            	   userArray9[1]=r1.nextInt(10);
                                            	   userArray9[2]=userArray[1];
                                            	   userArray9[3]=r2.nextInt(10);
                                            	   //writeArray(userArray);
                                            	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                                                   writeArray(userArray9);
                                                   
                                                   reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                                                   if (reply == JOptionPane.YES_OPTION)
                                                   {
                                                     JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                                                     break;
                                                   }else {
                                                	   int[] userArray10=new int[4];
                                                	   userArray10=userArray;
                                                	   userArray10[0]=r.nextInt(10);
                                                	   userArray10[1]=r1.nextInt(10);
                                                	   userArray10[2]=userArray[1];
                                                	   userArray10[3]=r2.nextInt(10);
                                                	   //writeArray(userArray);
                                                	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                                                       writeArray(userArray10);
                                                       
                                                       reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                                                       if (reply == JOptionPane.YES_OPTION)
                                                       {
                                                         JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                                                         break;
                                                       }else {
                                                    	   int[] userArray11=new int[4];
                                                    	   userArray11=userArray;
                                                    	   userArray11[0]=r.nextInt(10);
                                                    	   userArray11[1]=r1.nextInt(10);
                                                    	   userArray11[2]=userArray[3];
                                                    	   userArray11[3]=r2.nextInt(10);
                                                    	   //writeArray(userArray);
                                                    	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                                                           writeArray(userArray11);
                                                           
                                                           reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                                                           if (reply == JOptionPane.YES_OPTION)
                                                           {
                                                             JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                                                             break;
                                                           }else {
                                                        	   int[] userArray12=new int[4];
                                                        	   userArray12=userArray;
                                                        	   userArray12[0]=r.nextInt(10);
                                                        	   userArray12[1]=r1.nextInt(10);
                                                        	   userArray12[2]=r2.nextInt(10);
                                                        	   userArray12[3]=userArray[0];
                                                        	   //writeArray(userArray);
                                                        	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                                                               writeArray(userArray12);
                                                               
                                                               reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                                                               if (reply == JOptionPane.YES_OPTION)
                                                               {
                                                                 JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                                                                 break;
                                                               }else {
                                                            	   int[] userArray13=new int[4];
                                                            	   userArray13=userArray;
                                                            	   userArray13[0]=r.nextInt(10);
                                                            	   userArray13[1]=r1.nextInt(10);
                                                            	   userArray13[2]=r2.nextInt(10);
                                                            	   userArray13[3]=userArray[1];
                                                            	   //writeArray(userArray);
                                                            	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                                                                   writeArray(userArray13);
                                                                   
                                                                   reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                                                                   if (reply == JOptionPane.YES_OPTION)
                                                                   {
                                                                     JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                                                                     break;
                                                                   }else {
                                                                	   int[] userArray14=new int[4];
                                                                	   userArray14=userArray;
                                                                	   userArray14[0]=r.nextInt(10);
                                                                	   userArray14[1]=r1.nextInt(10);
                                                                	   userArray14[2]=r2.nextInt(10);
                                                                	   userArray14[3]=userArray[2];
                                                                	   //writeArray(userArray);
                                                                	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                                                                       writeArray(userArray14);
                                                                       
                                                                       reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                                                                       if (reply == JOptionPane.YES_OPTION)
                                                                       {
                                                                         JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                                                                         break;
                                                                       }
                                                                   }
                                                               }
                                                           }
                                                       }
                                                   }
                                               }
                                           }
                                       }
                                   }
                               }
                           }
                       }
                       /*Ýpucu -2 olduðu zaman tahmin yapýlan sayýnýn içindeki 1 sayýnýn doðru sayý ama yanlýþ basamakta 
                        * olduðunu anlýyoruz, bilgisayar bütün olasýlýklarý deneyerek hangi sayýlarýn hangi basamakta yer 
                        * almasý gerektiðini bulmaya çalýþýyor.                  
                        */
                   } else if(ipucu2==-2) {

                	   Random r1=new Random();
                	   Random r2=new Random();
                	   
                	   int[] userArray2=new int[4];
                	   userArray2=userArray;
                	   userArray2[0]=userArray[1];
                	   userArray2[1]=userArray[0];
                	   userArray2[2]=r1.nextInt(10);
                	   userArray2[3]=r2.nextInt(10);
                	   
                	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                       writeArray(userArray2);
                       
                       reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                       if (reply == JOptionPane.YES_OPTION)
                       {
                         JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                         break;
                       }else {
                    	   int[] userArray3=new int[4];
                    	   userArray3=userArray;
                    	   userArray3[0]=userArray[2];
                    	   userArray3[1]=r1.nextInt(10);
                    	   userArray3[2]=userArray[0];
                    	   userArray3[3]=r2.nextInt(10);
                    	   
                    	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                           writeArray(userArray3);
                           
                           reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                           if (reply == JOptionPane.YES_OPTION)
                           {
                             JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                             break;
                           }else {
                        	   int[] userArray4=new int[4];
                        	   userArray4=userArray;
                        	   userArray4[0]=userArray[3];
                        	   userArray4[1]=r1.nextInt(10);
                        	   userArray4[2]=r2.nextInt(10);
                        	   userArray4[3]=userArray[0];
                        	   
                        	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                               writeArray(userArray4);
                               
                               reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                               if (reply == JOptionPane.YES_OPTION)
                               {
                                 JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                                 break;
                               }else {
                            	   int[] userArray5=new int[4];
                            	   userArray5=userArray;
                            	   userArray5[0]=r1.nextInt(10);
                            	   userArray5[1]=userArray[2];
                            	   userArray5[2]=userArray[1];
                            	   userArray5[3]=r2.nextInt(10);
                            	   
                            	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                                   writeArray(userArray5);
                                   
                                   reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                                   if (reply == JOptionPane.YES_OPTION)
                                   {
                                     JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                                     break;
                                   }else {
                                	   int[] userArray6=new int[4];
                                	   userArray6=userArray;
                                	   userArray6[0]=r1.nextInt(10);
                                	   userArray6[1]=userArray[3];
                                	   userArray6[2]=r2.nextInt(10);
                                	   userArray6[3]=userArray[1];
                                	   
                                	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                                       writeArray(userArray6);
                                       
                                       reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                                       if (reply == JOptionPane.YES_OPTION)
                                       {
                                         JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                                         break;
                                       }else {
                                    	   int[] userArray7=new int[4];
                                    	   userArray7=userArray;
                                    	   userArray7[0]=r1.nextInt(10);
                                    	   userArray7[1]=r2.nextInt(10);
                                    	   userArray7[2]=userArray[3];
                                    	   userArray7[3]=userArray[2];
                                    	   
                                    	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                                           writeArray(userArray7);
                                           
                                           reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                                           if (reply == JOptionPane.YES_OPTION)
                                           {
                                             JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                                             break;
                                           }
                                           continue;
                                       }                                       
                                   }
                               }
                           }
                       }
                       /*Ýpucu -3 olduðu zaman tahmin yapýlan sayýnýn içindeki 1 sayýnýn doðru sayý ama yanlýþ basamakta 
                        * olduðunu anlýyoruz, bilgisayar bütün olasýlýklarý deneyerek hangi sayýlarýn hangi basamakta yer 
                        * almasý gerektiðini bulmaya çalýþýyor.                  
                        */
                   }else if(ipucu2==-3) {
                	   Random r1=new Random();
                	   
                	   int[] userArray2=new int[4];
                	   userArray2=userArray;
                	   userArray2[0]=userArray[3];
                	   userArray2[1]=userArray[0];
                	   userArray2[2]=r1.nextInt(10);
                	   userArray2[3]=userArray[1];
                	   
                	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                       writeArray(userArray2);
                       
                       reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                       if (reply == JOptionPane.YES_OPTION)
                       {
                         JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                         break;
                       }else {

                    	   int[] userArray3=new int[4];
                    	   userArray3=userArray;
                    	   userArray3[0]=userArray[2];
                    	   userArray3[1]=userArray[0];
                    	   userArray3[2]=userArray[1];
                    	   userArray3[3]=r1.nextInt(10);
                    	   
                    	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                           writeArray(userArray3);
                           
                           reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                           if (reply == JOptionPane.YES_OPTION)
                           {
                             JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                             break;
                           }else {
                        	   int[] userArray4=new int[4];
                        	   userArray4=userArray;
                        	   userArray4[0]=userArray[2];
                        	   userArray4[1]=r1.nextInt(10);
                        	   userArray4[2]=userArray[3];
                        	   userArray4[3]=userArray[0];
                        	   
                        	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                               writeArray(userArray4);
                               
                               reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                               if (reply == JOptionPane.YES_OPTION)
                               {
                                 JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                                 break;
                               }else {
                            	   int[] userArray5=new int[4];
                            	   userArray5=userArray;
                            	   userArray5[0]=userArray[2];
                            	   userArray5[1]=r1.nextInt(10);
                            	   userArray5[2]=userArray[3];
                            	   userArray5[3]=userArray[0];
                            	   
                            	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                                   writeArray(userArray5);
                                   
                                   reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                                   if (reply == JOptionPane.YES_OPTION)
                                   {
                                     JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                                     break;
                                   }else {
                                	   int[] userArray6=new int[4];
                                	   userArray6=userArray;
                                	   userArray6[0]=r1.nextInt(10);
                                	   userArray6[1]=userArray[2];
                                	   userArray6[2]=userArray[3];
                                	   userArray6[3]=userArray[1];
                                	   
                                	   System.out.println("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                                       writeArray(userArray5);
                                       
                                       reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                                       if (reply == JOptionPane.YES_OPTION)
                                       {
                                         JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                                         break;
                                       }
                                       continue;
                                   } 
                               }
                           }
                       }
                       /*Ýpucu -4 olduðu zaman sayýlar doðru ama hepsinin yeri yanlýþ anlamýna geliyor
                        * bu yüzden bilgisayar sayýnýn elemanlarýný karýþtýrarak doðru sayýyý nulmaya
                        * çalýþýyor
                        * 
                        */                    		   
                   }else if(ipucu2==-4) {

            		Random r1 = new Random();
            		
            		for (int i = 0; i <userArray.length; i++) {
            			int randomIndexToSwap=r1.nextInt(userArray.length);
            			int temp=userArray[randomIndexToSwap];
            			userArray[randomIndexToSwap] = userArray[i];
            			userArray[i] = temp;
            		}
            		System.out.println(Arrays.toString(userArray));
                   }
                   //Ýpucunun sýfýr olmasý yanlýþ yerde doðru sayý olmamasý anlamýna geldiði için sayýlar tekrardan rastgele üretiliyor
                   else if(ipucu2==0) {
                       userArray=randomNumber(lengthOfNumber,computerArray);
                	   System.out.print("Kullanýcýnýn tuttuðu sayý bu mu?: ");
                       writeArray(userArray);
                       reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen sayý doðru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);                       
                       if (reply == JOptionPane.YES_OPTION)
                       {
                         JOptionPane.showMessageDialog(null, " Kullanýcýnýn sayýsý doðru tahmin edildi!","Sonuç",JOptionPane.INFORMATION_MESSAGE);
                         break;
                       }
                       else {
                    	   continue;
                       }
                   }

               }
          }

          scan.close();
          return str;
     }
    }
