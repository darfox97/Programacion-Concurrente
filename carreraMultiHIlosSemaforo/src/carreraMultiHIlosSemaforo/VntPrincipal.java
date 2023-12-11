package carreraMultiHIlosSemaforo;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;
import java.awt.event.ActionEvent;

public class VntPrincipal extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public VntPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1094, 781);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelPrincipal = new JPanel();
		getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(null);
		
		JLabel lblTitulo = new JLabel("CARRERA");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblTitulo.setBounds(487, 11, 112, 49);
		panelPrincipal.add(lblTitulo);
		
        // Usar un JLayeredPane para superponer lblCorredor1 sobre lblPista
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(28, 71, 1020, 579);
        panelPrincipal.add(layeredPane);

        JLabel lblPista = new JLabel("");
        lblPista.setBounds(0, 0, 1020, 579);
        lblPista.setIcon(new ImageIcon("C:\\Users\\dario\\eclipse-workspace\\carreraMultihilos\\assets\\pistaCarrera.jpg"));
        layeredPane.add(lblPista, JLayeredPane.DEFAULT_LAYER);

        JLabel lblCorredor1 = new JLabel("");
        lblCorredor1.setIcon(new ImageIcon("C:\\Users\\dario\\eclipse-workspace\\carreraMultihilos\\assets\\corrida-nike.gif"));
        lblCorredor1.setBounds(53, 40, 132, 192);
        layeredPane.add(lblCorredor1, JLayeredPane.PALETTE_LAYER);
        
        JLabel lblCorredor2 = new JLabel("");
        layeredPane.setLayer(lblCorredor2, 100);
        lblCorredor2.setIcon(new ImageIcon("C:\\Users\\dario\\eclipse-workspace\\carreraMultihilos\\assets\\corredor2.gif"));
        lblCorredor2.setBounds(53, 351, 132, 192);
        layeredPane.add(lblCorredor2);
        
        
		JButton btnDetener = new JButton("Detener");
		btnDetener.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDetener.setBounds(405, 694, 105, 37);
		panelPrincipal.add(btnDetener);
		
		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Semaphore guardia = new Semaphore(1);
                // Inicia dos hilos, uno para lblCorredor1 y otro para lblCorredor2
                Thread hiloCorredor1 = new Thread(new Corredor(lblCorredor1, "Usain", guardia));
                Thread hiloCorredor2 = new Thread(new Corredor(lblCorredor2, "Bea", guardia));

                hiloCorredor1.start();
                hiloCorredor2.start();
			}
		});
		btnIniciar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnIniciar.setBounds(520, 694, 105, 37);
		panelPrincipal.add(btnIniciar);
		
		JButton btnResetear = new JButton("Resetear");
		btnResetear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnResetear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblCorredor1.setBounds(53, 40, 132, 192);
				lblCorredor2.setBounds(53, 351, 132, 192);
			}
		});
		btnResetear.setBounds(635, 694, 112, 37);
		panelPrincipal.add(btnResetear);
	}
}