/*A�a��daki yaz�l�mda Java ile yaz�lm�� bir say� tahmin oyunu verilmi�tir.
 * Bu oyun iki a�amadan olu�maktad�r, ilk a�amada bilgisayar rastgele, rakamlar� farkl� 
 * bir say� tutmaktad�r ve kullan�c� bunu tahmin etmeye �al��maktad�r, ikinci a�amada ise 
 * kullan�c� rastgele,akl�ndan bir say� tutmaktad�r(bunu herhangi bir �ekilde input olarak girmemeli ve
 * payla�mamal�d�r) ve bilgisayar kullan�c�n�n tuttu�u say�y� tahmin etmeye �al��maktad�r.Her iki taraf 
 * da tahminler yap�ld�ktan sonra, bir sonraki tahminin yap�labilmesi i�in ipu�lar� vermektedir.�pu�lar� 
 * e�er say�da do�ru basamak varsa(ka� tane do�ru basamak oldu�una g�re) +, e�er say� do�ruysa ama bulundu�u
 * basamak yanl��sa(ka� tane yanl�� yerde do�ru say� oldu�una g�re) - �eklinde verilmektedir. Her iki taraftan 
 * biri di�erinin say�s�n� tahmin etti�inde oyun sonlanmaktad�r.
*/
//Yazan G�zde Etiz
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class SayiTahmin {

     public static void main(String[] args) {
          runGame();
     }

     public static int[] randomNumber(int lengthOfNumber, int[] array) {
         /* Random say�n�n �reten method. �lk rakam�n 0'dan ba�lamamas� i�in 
          * �ncelikle while d�ng�s� ile kontrol ediyoruz.For d�ng�s�n�n i�inde dizimizi basamak say�s�
          * kadar d�nd�r�p 0-9 aral���nda random de�er atamas� yap�yoruz. Di�er for d�ng�s�nde 
          * rakamlar�n birbirinden farkl� olmas�n� kontrol ediyoruz ve bu kontrol i�leminin dizinin hep ilk eleman�ndan ba�lamas�
          * i�in j de�erini d�ng�den ��kmadan �nce s�rekli s�f�rl�yoruz.
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
    	 /* Dizinin ekrana yazd�r�ld��� metot */
         int i=0;
          for (i=0;i<array.length;i++) {
               System.out.print(array[i]);
          }

     }

     public static int[] decompose(int lengthOfNumber, int number, int[] array) {
         /*  Kullan�c�n girdi�i say�n�n basamaklar�na ayr�ld��� metot. Buradaki
          *  for d�ng�s�nde say�n�n s�rekli 10'a modu al�n�p devam�nda 10'a b�l�nerek 
          *  dizinin son eleman�ndan ilk eleman�na kadar atama i�lemi yap�l�r. */

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

          /* �ki tane dizi yarat�yoruz.Bilgisayar�n belirledi�imiz basamak 
          * say�s�na g�re random de�erle �retti�i say�y� bu dizi i�inde tutaca��z = computerArray
          * Kullan�c�n girdi�i say�y� bu dizi i�inde tutaca��z = myArray
          */
          int[] computerArray = new int[lengthOfNumber];
          int[] myArray = new int[lengthOfNumber];
          int[] userArray=new int[lengthOfNumber];

          computerArray = randomNumber(lengthOfNumber, computerArray);
          userArray=randomNumber(lengthOfNumber,computerArray);

          //System.out.print("Bilgisayar�n tuttu�u say�: ");
          //writeArray(computerArray);
          
          //Bilgisayar random say� �reterek kullan�c�n�n say�s�n� tahmin etmeye �al���r.
          System.out.println();
          System.out.print("Kullan�c�n�n tuttu�u say� bu mu?: ");
          writeArray(userArray);

          /* �ki rakam e�le�iyorsa ve s�ra nolar� ayn� ise countForSameOrder
          * integer'� ile bu de�eri tutuyoruz. �leride bu de�eri tahmine g�re
          * artt�rabiliriz.
          */
          /* �ki rakam e�le�iyorsa ama s�ra nolar� farkl� ise
          * countForDifferentOrder integer'� ile bu de�eri tutuyoruz. �leride
          * bu de�eri tahmine g�re azaltabiliriz.
          */
          
          String str = null;
          while (!Arrays.equals(computerArray, myArray)) {
               int countForSameOrder = 0;
               int countForDifferentOrder = 0;
               System.out.println();
               
               String str1,str2,str3;
               

               str1=JOptionPane.showInputDialog("L�tfen 4 basamakl� tahmininizi girin: ");
               int guess = Integer.parseInt(str1);

               int lenghtOfGuess = Integer.valueOf(guess).toString().length();   
               
               //E�er 4 basamaktan fazla say� girilirse uyar� verir.

               while (lenghtOfGuess != lengthOfNumber) {
                    System.out.print("L�tfen belirledi�iniz basamak say�s�nda tahmininizi giriniz ("+ lengthOfNumber + " basamakl�) :");

                    guess = scan.nextInt();
                    lenghtOfGuess = Integer.valueOf(guess).toString().length();

               }  
               //E�er say� yerine harf girilirse uyar� verir
               while(guess!=Integer.parseInt(str1)) {
            	   System.out.println("Harf girdiniz.L�tfen ge�erli bir tahmin girin: ");
            	   guess = scan.nextInt();
            	   }             

               //Girilen tahmin say�s�n� basamaklar�na ay�r�r
               decompose(lengthOfNumber, guess, myArray);
               
               //Tahmin say�s�n�n ilk basam��nda 0 olursa uyaru verir
               while(myArray[0]==0) {
            	   System.out.println("�lk basamakta 0 olamaz.L�tfen ge�erli bir tahmin girin: ");
            	   guess = scan.nextInt();
               }               
               
               for (int i = 0; i < lengthOfNumber; i++) {
                    for (int j = 0; j < lengthOfNumber; j++) {
                         if (computerArray[i] == myArray[j] && i == j)
                              countForSameOrder++;  //say�lar e�le�iyorsa ve basamak no lar� da do�ruysa +1 artt�t�yoruz
                         if (computerArray[i] == myArray[j] && i != j)
                              countForDifferentOrder--;  //say�lar e�le�iyorsa ama basamak no lar� farkl�ysa -1 azalt�yoruz
                    }
               }               
               JOptionPane.showMessageDialog(null,"Tahmin yap�ld�: "+guess,"Tahmin",JOptionPane.INFORMATION_MESSAGE);

               /*Tahmin yap�l�r ve g�sterilir.E�er do�ruysa tebrik mesaj� verilir ve program sonlan�r
                * Yap�lan tahmin do�ru de�ilse kullan�c�n�n bir sonraki tahmini yapabilmesi i�in ipucu verilir
                */
               if (countForSameOrder == lengthOfNumber) {
            	   JOptionPane.showMessageDialog(null,"Tebrikler + "+lengthOfNumber+" Do�ru cevap bulundu" ,"Do�ru Tahmin",JOptionPane.INFORMATION_MESSAGE);
            	   break;

               } else {
            	   JOptionPane.showMessageDialog(null,guess+"\n"+countForSameOrder+" "+countForDifferentOrder,"Tahmin",JOptionPane.INFORMATION_MESSAGE);

               }
               
               //Bilgisayar kullan�c�n�n say�s�n� tahmin etmeye �al���r.Do�ru tahmin ederse program sonlan�r
               int reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
               if (reply == JOptionPane.YES_OPTION)
               {
                 JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                 break;
               }
               //Yap�lan tahmin do�ru de�ilse kullan�c�dan ipu�lar�(+ ve -) vermesini ister.
               else {
            	   str2=JOptionPane.showInputDialog("�pucu giriniz:");
                   int ipucu1=Integer.parseInt(str2);
                   str3=JOptionPane.showInputDialog("Di�er ipucunu giriniz:");
                   int ipucu2=Integer.parseInt(str3);
                   
                   //Girilen ipu�lar� ge�ersiz ise uyar� verir
                   while (ipucu1>4||ipucu1<0||ipucu2>0||ipucu2<-4) {
                	   str2=JOptionPane.showInputDialog("�pucu giriniz:");
                       ipucu1=Integer.parseInt(str2);
                       str3=JOptionPane.showInputDialog("Di�er ipucunu giriniz:");
                       ipucu2=Integer.parseInt(str3);
                  }
                   //�pucu olarak say� yerine harf girilirse uyar� verir
                   while(ipucu1!=Integer.parseInt(str2)||ipucu2!=Integer.parseInt(str3)) {
                	   System.out.println("Ge�ersiz formatta ipucu girdiniz l�tfen tekrar girin: ");
                	   ipucu1=scan.nextInt();
                	   ipucu2=scan.nextInt();
                   }
                   
                   /*Bilgisayar�n kullan�c�n�n say�s�n� tahmin etmeye �al��t��� k�s�m.
                    * Bu k�s�m �ok uzun oldu�u i�in a��klamakta yarar var. Kullan�c� bilgisayara yard�mc� olmak 
                    * i�in 0,1,2,3,4 yada 0,-1,-2,-3,-4 �eklinde ipucu giriyor( e�er do�ru say� ve basamak say�s� varsa
                    * ipucu1 +, e�er do�ru say� yanl�� yerdeyse ipucu2 -). Girilen ipucu1 ve ipucu2 say�s�na g�re bilgisayar
                    * ka� tane do�ru basamak veya ka� tane yanl�� yerde say� varsa o ipucu say�s�na denk gelen d�ng�ye giriyor 
                    * ve o d�ng� i�erisindeki b�t�n olas�l�klar� deneyerek do�ru say�y� bulmaya �al���yor. �rne�in +1 girildi�i zaman
                    * bilgisayar yapt��� tahmindeki bir tane eleman�n do�ru basamakta oldu�unu anl�yor ve her seferinde bir tane basama��
                    * sabit tutarak di�er elemanlar� tekrardan rastgele �retiyor.Ya da �rne�in -2 ipucu olarak girildi�i zaman iki say�n�n
                    * do�ru oldu�unu ama yanl�� yerde oldu�unu anl�yor ve b�t�n olas�l�klar� deneyerek iki say�y� ayn� tutup yerlerini 
                    * de�i�tirerek di�er say�lar� rastgele de�i�tiriyor ve tekrar tahmin yap�yor. Burda her ipucuna denk gelen say� olas�l�klar�n�
                    * kendim hesaplay�p d���nerek yerle�tirdim.
                    */
                   
                   //E�er ipucu 0 ise hi�bir basamaktaki eleman do�ru denk gelmemi� oldu�u i�in say� tekrardan �retiliyor.
                   if(ipucu1==0) {
                       userArray=randomNumber(lengthOfNumber,computerArray);
                	   System.out.print("Kullan�c�n�n tuttu�u say� bu mu?: ");
                       writeArray(userArray);
                       reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);                       
                       if (reply == JOptionPane.YES_OPTION)
                       {
                         JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                         break;
                       }
                       else {
                    	   continue;
                       }
                   }
                   
                   /*�pucu 1 oldu�u zaman tahmin edilen say�da 1 tane say� ve basamak no su do�ru demek oldu�u i�in 
                    * bilgisayar b�t�n olas�l�klar� deneyerek hangi basamak ve say�n�n do�ru oldu�unu bulmaya �al���yor ve 
                    * her seferinde kullan�c�ya do�ru olup olmad���n� soruyor
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
                	   System.out.print("Kullan�c�n�n tuttu�u say� bu mu?: ");
                       writeArray(userArray2);
                       reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);                       
                       if (reply == JOptionPane.YES_OPTION)
                       {
                         JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                         break;
                       }
                       else {
                    	   int[] userArray3=new int[4];
                    	   userArray3=userArray;
                    	   userArray3[0]=r.nextInt(10);
                    	   userArray3[1]=userArray[1];
                    	   userArray3[2]=r1.nextInt(10);
                    	   userArray3[3]=r2.nextInt(10);
                    	   
                       	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                           writeArray(userArray3);
                           
                           if (reply == JOptionPane.YES_OPTION)
                           {
                             JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                             break;
                           }
                           else {
                        	   int[] userArray4=new int[4];
                        	   userArray4=userArray;
                        	   userArray4[0]=r.nextInt(10);
                        	   userArray4[1]=r1.nextInt(10);
                        	   userArray4[2]=userArray[2];
                        	   userArray4[3]=r2.nextInt(10);
                        	   
                           	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                               writeArray(userArray4);
                               
                               if (reply == JOptionPane.YES_OPTION)
                               {
                                 JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                                 break;
                               }
                               else {
                            	   int[] userArray5=new int[4];
                            	   userArray5=userArray;
                            	   userArray5[0]=r.nextInt(10);
                            	   userArray5[1]=r1.nextInt(10);
                            	   userArray5[2]=r2.nextInt(10);
                            	   userArray4[3]=userArray[3];
                            	   
                               	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                                   writeArray(userArray5);
                                   
                                   if (reply == JOptionPane.YES_OPTION)
                                   {
                                     JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                                     break;
                                   }                                                                      
                               }                               
                           }                  	                 	   
                    	                      	   
                       }    
                       
                       /*�pucu 2 oldu�u zaman tahmin edilen say�da 2 tane say� ve basamak no su do�ru demek oldu�u i�in 
                        * bilgisayar b�t�n olas�l�klar� deneyerek hangi basamaktaki hangi say�lar�n do�ru oldu�unu bulmaya �al���yor ve 
                        * her seferinde kullan�c�ya do�ru olup olmad���n� soruyor
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
                	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                       writeArray(userArray2);
                       reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);                       
                       if (reply == JOptionPane.YES_OPTION)
                       {
                         JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
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
                    	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                           writeArray(userArray3);
                           
                           if (reply == JOptionPane.YES_OPTION)
                           {
                             JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                             break;
                           }else {
                        	   int[] userArray4=new int[4];
                        	   userArray4=userArray;
                        	   userArray4[0]=userArray[0];
                        	   userArray4[1]=r1.nextInt(10);
                        	   userArray4[2]=r2.nextInt(10);
                        	   userArray4[3]=userArray[3];
                        	   //writeArray(userArray);
                        	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                               writeArray(userArray4);
                               
                               if (reply == JOptionPane.YES_OPTION)
                               {
                                 JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                                 break;
                               }else {
                            	   int[] userArray5=new int[4];
                            	   userArray5=userArray;
                            	   userArray5[0]=r1.nextInt(10);
                            	   userArray5[1]=userArray[1];
                            	   userArray5[2]=userArray[2];
                            	   userArray5[3]=r2.nextInt(10);
                            	   //writeArray(userArray);
                            	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                                   writeArray(userArray5);
                                   
                                   if (reply == JOptionPane.YES_OPTION)
                                   {
                                     JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                                     break;
                                   }else {
                                	   int[] userArray6=new int[4];
                                	   userArray6=userArray;
                                	   userArray6[0]=r1.nextInt(10);
                                	   userArray6[1]=userArray[1];
                                	   userArray6[2]=r2.nextInt(10);
                                	   userArray6[3]=userArray[3];
                                	   //writeArray(userArray);
                                	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                                       writeArray(userArray6);
                                       
                                       if (reply == JOptionPane.YES_OPTION)
                                       {
                                         JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                                         break;
                                       }else {
                                    	   int[] userArray7=new int[4];
                                    	   userArray7=userArray;
                                    	   userArray7[0]=r1.nextInt(10);
                                    	   userArray7[1]=r2.nextInt(10);
                                    	   userArray7[2]=userArray[2];
                                    	   userArray7[3]=userArray[3];
                                    	   //writeArray(userArray);
                                    	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                                           writeArray(userArray7);
                                       }
                                       if (reply == JOptionPane.YES_OPTION)
                                       {
                                         JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                                         break;
                                       }
                                   }                                  
                                   
                               }                               
                               
                           }
                          
                       }
                       /*�pucu 3 oldu�u zaman tahmin edilen say�da 3 tane say� ve basamak no su do�ru demek oldu�u i�in 
                        * bilgisayar b�t�n olas�l�klar� deneyerek hangi basamaktaki hangi say�lar�n do�ru oldu�unu bulmaya �al���yor ve 
                        * her seferinde kullan�c�ya do�ru olup olmad���n� soruyor
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
                	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                       writeArray(userArray2);
                       reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                       if (reply == JOptionPane.YES_OPTION)
                       {
                         JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                         break;
                       }else {
                    	   int[] userArray3=new int[4];
                    	   userArray3=userArray;
                    	   userArray3[0]=userArray[0];
                    	   userArray3[1]=userArray[1];
                    	   userArray3[2]=r1.nextInt(10);
                    	   userArray3[3]=userArray[3];
                    	   //writeArray(userArray);
                    	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                           writeArray(userArray3);
                           reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                           if (reply == JOptionPane.YES_OPTION)
                           {
                             JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                             break;
                           }else {
                        	   int[] userArray4=new int[4];
                        	   userArray4=userArray;
                        	   userArray4[0]=userArray[0];
                        	   userArray4[1]=r1.nextInt(10);
                        	   userArray4[2]=userArray[2];
                        	   userArray4[3]=userArray[3];
                        	   //writeArray(userArray);
                        	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                               writeArray(userArray4);
                               
                               reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                               if (reply == JOptionPane.YES_OPTION)
                               {
                                 JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                                 break;
                               }else {
                            	   int[] userArray5=new int[4];
                            	   userArray5=userArray;
                            	   userArray5[0]=r1.nextInt(10);
                            	   userArray5[1]=userArray[1];
                            	   userArray5[2]=userArray[2];
                            	   userArray5[3]=userArray[3];
                            	   //writeArray(userArray);
                            	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                                   writeArray(userArray5);
                                   
                                   reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                                   if (reply == JOptionPane.YES_OPTION)
                                   {
                                     JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                                     break;
                                   }
                                   
                               }
                           }
                           
                       }
                	   //�pucu 4 oldu�u zaman b�t�n basamaklar do�ru demek oldu�u i�in program sonlam�yor
                   }else if(ipucu1==4) {
                	   JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                       break;
                   }
                   /*�pucu -1 oldu�u zaman tahmin yap�lan say�n�n i�indeki 1 say�n�n do�ru say� ama yanl�� basamakta 
                    * oldu�unu anl�yoruz, bilgisayar b�t�n olas�l�klar� deneyerek hangi say�n�n hangi basamakta yer 
                    * almas� gerekti�ini bulmaya �al���yor.                  
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
                	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                       writeArray(userArray2);
                       
                       reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                       if (reply == JOptionPane.YES_OPTION)
                       {
                         JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                         break;
                       }else {
                    	   int[] userArray3=new int[4];
                    	   userArray3=userArray;
                    	   userArray3[0]=userArray[2];
                    	   userArray3[1]=r.nextInt(10);
                    	   userArray3[2]=r1.nextInt(10);
                    	   userArray3[3]=r2.nextInt(10);
                    	   //writeArray(userArray);
                    	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                           writeArray(userArray3);
                           
                           reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                           if (reply == JOptionPane.YES_OPTION)
                           {
                             JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                             break;
                           }else {
                        	   int[] userArray4=new int[4];
                        	   userArray4=userArray;
                        	   userArray4[0]=userArray[2];
                        	   userArray4[1]=r.nextInt(10);
                        	   userArray4[2]=r1.nextInt(10);
                        	   userArray4[3]=r2.nextInt(10);
                        	   //writeArray(userArray);
                        	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                               writeArray(userArray4);
                               
                               reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                               if (reply == JOptionPane.YES_OPTION)
                               {
                                 JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                                 break;
                               }else {
                            	   int[] userArray5=new int[4];
                            	   userArray5=userArray;
                            	   userArray5[0]=r.nextInt(10);;
                            	   userArray5[1]=userArray[0];
                            	   userArray5[2]=r1.nextInt(10);
                            	   userArray5[3]=r2.nextInt(10);
                            	   //writeArray(userArray);
                            	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                                   writeArray(userArray5);
                                   
                                   reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                                   if (reply == JOptionPane.YES_OPTION)
                                   {
                                     JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                                     break;
                                   }else {
                                	   int[] userArray6=new int[4];
                                	   userArray6=userArray;
                                	   userArray6[0]=r.nextInt(10);;
                                	   userArray6[1]=userArray[2];
                                	   userArray6[2]=r1.nextInt(10);
                                	   userArray6[3]=r2.nextInt(10);
                                	   //writeArray(userArray);
                                	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                                       writeArray(userArray6);
                                       
                                       reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                                       if (reply == JOptionPane.YES_OPTION)
                                       {
                                         JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
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
                                    	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                                           writeArray(userArray7);
                                           
                                           reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                                           if (reply == JOptionPane.YES_OPTION)
                                           {
                                             JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
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
                                        	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                                               writeArray(userArray8);
                                               
                                               reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                                               if (reply == JOptionPane.YES_OPTION)
                                               {
                                                 JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
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
                                            	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                                                   writeArray(userArray9);
                                                   
                                                   reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                                                   if (reply == JOptionPane.YES_OPTION)
                                                   {
                                                     JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                                                     break;
                                                   }else {
                                                	   int[] userArray10=new int[4];
                                                	   userArray10=userArray;
                                                	   userArray10[0]=r.nextInt(10);
                                                	   userArray10[1]=r1.nextInt(10);
                                                	   userArray10[2]=userArray[1];
                                                	   userArray10[3]=r2.nextInt(10);
                                                	   //writeArray(userArray);
                                                	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                                                       writeArray(userArray10);
                                                       
                                                       reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                                                       if (reply == JOptionPane.YES_OPTION)
                                                       {
                                                         JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                                                         break;
                                                       }else {
                                                    	   int[] userArray11=new int[4];
                                                    	   userArray11=userArray;
                                                    	   userArray11[0]=r.nextInt(10);
                                                    	   userArray11[1]=r1.nextInt(10);
                                                    	   userArray11[2]=userArray[3];
                                                    	   userArray11[3]=r2.nextInt(10);
                                                    	   //writeArray(userArray);
                                                    	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                                                           writeArray(userArray11);
                                                           
                                                           reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                                                           if (reply == JOptionPane.YES_OPTION)
                                                           {
                                                             JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                                                             break;
                                                           }else {
                                                        	   int[] userArray12=new int[4];
                                                        	   userArray12=userArray;
                                                        	   userArray12[0]=r.nextInt(10);
                                                        	   userArray12[1]=r1.nextInt(10);
                                                        	   userArray12[2]=r2.nextInt(10);
                                                        	   userArray12[3]=userArray[0];
                                                        	   //writeArray(userArray);
                                                        	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                                                               writeArray(userArray12);
                                                               
                                                               reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                                                               if (reply == JOptionPane.YES_OPTION)
                                                               {
                                                                 JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                                                                 break;
                                                               }else {
                                                            	   int[] userArray13=new int[4];
                                                            	   userArray13=userArray;
                                                            	   userArray13[0]=r.nextInt(10);
                                                            	   userArray13[1]=r1.nextInt(10);
                                                            	   userArray13[2]=r2.nextInt(10);
                                                            	   userArray13[3]=userArray[1];
                                                            	   //writeArray(userArray);
                                                            	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                                                                   writeArray(userArray13);
                                                                   
                                                                   reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                                                                   if (reply == JOptionPane.YES_OPTION)
                                                                   {
                                                                     JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                                                                     break;
                                                                   }else {
                                                                	   int[] userArray14=new int[4];
                                                                	   userArray14=userArray;
                                                                	   userArray14[0]=r.nextInt(10);
                                                                	   userArray14[1]=r1.nextInt(10);
                                                                	   userArray14[2]=r2.nextInt(10);
                                                                	   userArray14[3]=userArray[2];
                                                                	   //writeArray(userArray);
                                                                	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                                                                       writeArray(userArray14);
                                                                       
                                                                       reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                                                                       if (reply == JOptionPane.YES_OPTION)
                                                                       {
                                                                         JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
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
                       /*�pucu -2 oldu�u zaman tahmin yap�lan say�n�n i�indeki 1 say�n�n do�ru say� ama yanl�� basamakta 
                        * oldu�unu anl�yoruz, bilgisayar b�t�n olas�l�klar� deneyerek hangi say�lar�n hangi basamakta yer 
                        * almas� gerekti�ini bulmaya �al���yor.                  
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
                	   
                	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                       writeArray(userArray2);
                       
                       reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                       if (reply == JOptionPane.YES_OPTION)
                       {
                         JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                         break;
                       }else {
                    	   int[] userArray3=new int[4];
                    	   userArray3=userArray;
                    	   userArray3[0]=userArray[2];
                    	   userArray3[1]=r1.nextInt(10);
                    	   userArray3[2]=userArray[0];
                    	   userArray3[3]=r2.nextInt(10);
                    	   
                    	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                           writeArray(userArray3);
                           
                           reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                           if (reply == JOptionPane.YES_OPTION)
                           {
                             JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                             break;
                           }else {
                        	   int[] userArray4=new int[4];
                        	   userArray4=userArray;
                        	   userArray4[0]=userArray[3];
                        	   userArray4[1]=r1.nextInt(10);
                        	   userArray4[2]=r2.nextInt(10);
                        	   userArray4[3]=userArray[0];
                        	   
                        	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                               writeArray(userArray4);
                               
                               reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                               if (reply == JOptionPane.YES_OPTION)
                               {
                                 JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                                 break;
                               }else {
                            	   int[] userArray5=new int[4];
                            	   userArray5=userArray;
                            	   userArray5[0]=r1.nextInt(10);
                            	   userArray5[1]=userArray[2];
                            	   userArray5[2]=userArray[1];
                            	   userArray5[3]=r2.nextInt(10);
                            	   
                            	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                                   writeArray(userArray5);
                                   
                                   reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                                   if (reply == JOptionPane.YES_OPTION)
                                   {
                                     JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                                     break;
                                   }else {
                                	   int[] userArray6=new int[4];
                                	   userArray6=userArray;
                                	   userArray6[0]=r1.nextInt(10);
                                	   userArray6[1]=userArray[3];
                                	   userArray6[2]=r2.nextInt(10);
                                	   userArray6[3]=userArray[1];
                                	   
                                	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                                       writeArray(userArray6);
                                       
                                       reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                                       if (reply == JOptionPane.YES_OPTION)
                                       {
                                         JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                                         break;
                                       }else {
                                    	   int[] userArray7=new int[4];
                                    	   userArray7=userArray;
                                    	   userArray7[0]=r1.nextInt(10);
                                    	   userArray7[1]=r2.nextInt(10);
                                    	   userArray7[2]=userArray[3];
                                    	   userArray7[3]=userArray[2];
                                    	   
                                    	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                                           writeArray(userArray7);
                                           
                                           reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                                           if (reply == JOptionPane.YES_OPTION)
                                           {
                                             JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                                             break;
                                           }
                                           continue;
                                       }                                       
                                   }
                               }
                           }
                       }
                       /*�pucu -3 oldu�u zaman tahmin yap�lan say�n�n i�indeki 1 say�n�n do�ru say� ama yanl�� basamakta 
                        * oldu�unu anl�yoruz, bilgisayar b�t�n olas�l�klar� deneyerek hangi say�lar�n hangi basamakta yer 
                        * almas� gerekti�ini bulmaya �al���yor.                  
                        */
                   }else if(ipucu2==-3) {
                	   Random r1=new Random();
                	   
                	   int[] userArray2=new int[4];
                	   userArray2=userArray;
                	   userArray2[0]=userArray[3];
                	   userArray2[1]=userArray[0];
                	   userArray2[2]=r1.nextInt(10);
                	   userArray2[3]=userArray[1];
                	   
                	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                       writeArray(userArray2);
                       
                       reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                       if (reply == JOptionPane.YES_OPTION)
                       {
                         JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                         break;
                       }else {

                    	   int[] userArray3=new int[4];
                    	   userArray3=userArray;
                    	   userArray3[0]=userArray[2];
                    	   userArray3[1]=userArray[0];
                    	   userArray3[2]=userArray[1];
                    	   userArray3[3]=r1.nextInt(10);
                    	   
                    	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                           writeArray(userArray3);
                           
                           reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                           if (reply == JOptionPane.YES_OPTION)
                           {
                             JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                             break;
                           }else {
                        	   int[] userArray4=new int[4];
                        	   userArray4=userArray;
                        	   userArray4[0]=userArray[2];
                        	   userArray4[1]=r1.nextInt(10);
                        	   userArray4[2]=userArray[3];
                        	   userArray4[3]=userArray[0];
                        	   
                        	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                               writeArray(userArray4);
                               
                               reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                               if (reply == JOptionPane.YES_OPTION)
                               {
                                 JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                                 break;
                               }else {
                            	   int[] userArray5=new int[4];
                            	   userArray5=userArray;
                            	   userArray5[0]=userArray[2];
                            	   userArray5[1]=r1.nextInt(10);
                            	   userArray5[2]=userArray[3];
                            	   userArray5[3]=userArray[0];
                            	   
                            	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                                   writeArray(userArray5);
                                   
                                   reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                                   if (reply == JOptionPane.YES_OPTION)
                                   {
                                     JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                                     break;
                                   }else {
                                	   int[] userArray6=new int[4];
                                	   userArray6=userArray;
                                	   userArray6[0]=r1.nextInt(10);
                                	   userArray6[1]=userArray[2];
                                	   userArray6[2]=userArray[3];
                                	   userArray6[3]=userArray[1];
                                	   
                                	   System.out.println("Kullan�c�n�n tuttu�u say� bu mu?: ");
                                       writeArray(userArray5);
                                       
                                       reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);
                                       if (reply == JOptionPane.YES_OPTION)
                                       {
                                         JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
                                         break;
                                       }
                                       continue;
                                   } 
                               }
                           }
                       }
                       /*�pucu -4 oldu�u zaman say�lar do�ru ama hepsinin yeri yanl�� anlam�na geliyor
                        * bu y�zden bilgisayar say�n�n elemanlar�n� kar��t�rarak do�ru say�y� nulmaya
                        * �al���yor
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
                   //�pucunun s�f�r olmas� yanl�� yerde do�ru say� olmamas� anlam�na geldi�i i�in say�lar tekrardan rastgele �retiliyor
                   else if(ipucu2==0) {
                       userArray=randomNumber(lengthOfNumber,computerArray);
                	   System.out.print("Kullan�c�n�n tuttu�u say� bu mu?: ");
                       writeArray(userArray);
                       reply = JOptionPane.showConfirmDialog(null, "Tahmin edilen say� do�ru mu?", "Kapat?",  JOptionPane.YES_NO_OPTION);                       
                       if (reply == JOptionPane.YES_OPTION)
                       {
                         JOptionPane.showMessageDialog(null, " Kullan�c�n�n say�s� do�ru tahmin edildi!","Sonu�",JOptionPane.INFORMATION_MESSAGE);
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
