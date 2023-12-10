package carreraMultihilos;

import javax.swing.JLabel;

public class Corredor implements Runnable{
    private JLabel corredor;
    private String nombre;

    public Corredor(JLabel corredor, String nombre) {
        this.corredor = corredor;
        this.nombre = nombre;
    }

    @Override
    public void run() {
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
    }
}
