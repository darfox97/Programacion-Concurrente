package carreraMultiHIlosSemaforo;

import java.util.concurrent.Semaphore;

import javax.swing.JLabel;

public class Corredor implements Runnable{
    private JLabel corredor;
    private String nombre;
    private Semaphore guardia;


    public Corredor(JLabel corredor, String nombre, Semaphore guardia) {
        this.corredor = corredor;
        this.nombre = nombre;
        this.guardia = guardia;
    }
    

    @Override
    public void run() {
        try {
        	// Lo que necesitamos para bloquear (ROJO);
            guardia.acquire();
            
            while (corredor.getX() <= 1050) {
                corredor.setLocation(corredor.getX() + 6, corredor.getY());
                try {
                    int tiempoAleatorio = (int) (Math.random() * 130);
                    Thread.sleep(tiempoAleatorio);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.err.println(nombre + " ha llegado a la meta.");
        } catch (InterruptedException e) {
        	e.printStackTrace();
        } finally {
        	guardia.release();
        }     
    }
}
