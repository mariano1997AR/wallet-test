package com.example;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;

class Wallets{
    protected String user;
    protected String pass;
    public Wallets(String user, String pass){
        this.user = user;
        this.pass = pass;
    }
    
    public String ingresaDato(Scanner sc, String frase){
        String frases = String.format("%s",frase);
        System.out.println(frases);
        String dato = sc.nextLine();
        return dato;
    }

    public boolean verificado(String dato1,String dato2){
         boolean aux;
         if(this.user.equals(dato1) && this.pass.equals(dato2)){
              aux = true;
         }else{
              aux = false;
         }
         return aux;

    }
    //es para saber si esta logueado  o no 
    public void logging() throws InterruptedException{
        // Definir la duracion del contador en minutos (5 minutos)
        int minutosDuracion = 1;

        //Obtener la fecha y hora actuañ
        LocalDateTime fechaInicio = LocalDateTime.now();

        //Calcular la fecha y hora de finalizacion sumando los minutos de duracion
        LocalDateTime fechaFin = fechaInicio.plusMinutes(minutosDuracion);
        
        //bucle para actualizar el contador cada segundo
        boolean estado = true;
        while(estado){
            //Obtener la fecha y hora actual
            LocalDateTime fechaActual = LocalDateTime.now();

            //calcular la diferencia entre la fecha final y la actual
            Duration duracionRestante = Duration.between(fechaActual, fechaFin);

            //Obtener los minutos y segundos restantes
            long minutosRestantes = duracionRestante.toMinutes();
            long segundosRestantes = duracionRestante.getSeconds();

            //Mostrar el tiempo restante
            System.out.printf("Tiempo restante: %d minutos, %d segundos \n",
              minutosRestantes,segundosRestantes);


            //Detener el ciclo si ya hemos alcanzado o pasado la fecha final
            if(duracionRestante.isNegative() || duracionRestante.isZero()){
                  System.out.println("tiempo de loggeo terminado");
                  estado = false;
                  break;
            }
            //Esperar 1 segundo antes de actualizar el contador
            Thread.sleep(1000);
        }

        

       
    }

      
    public void run(){
       try {
         Scanner sc = new Scanner(System.in);
         String user,pass;
         boolean estado = true;
         int contador = 0;
         user = ingresaDato(sc, "Ingresa el usuario: ");
         pass = ingresaDato(sc, "Ingresa el password: ");
         
         while (estado == true) {
              if(verificado(user, pass)){
                estado = false;
               // System.out.println("Verificado");
                logging();
                break;
             }
             user = ingresaDato(sc, "Ingresa el usuario: ");
             pass = ingresaDato(sc, "Ingresa el password: ");
             contador = contador + 1;
             System.out.println("Cantidad de veces que se probo la contrasenia fue de " + contador);  
        }
        sc.close();
     
       
       } catch (Exception e) {
            System.out.println("Error" + e);
       }
        
    }       

    
    
}



public class Main {
    public static void main(String[] args) {
        Wallets wallet = new Wallets("mariano", "pass");
        wallet.run();
        
    
  }
}