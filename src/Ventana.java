import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("unused")
public class Ventana extends JFrame implements ActionListener {
	
	private Container panel;
	private JButton reiniciar;
	
	private JPanel tableroC;
	private JButton[][] tableroB;
	
	private boolean turno;
	private int terminar;
	private JLabel turnoText;
	
	
	public Ventana() {
		setTitle("TATETI");
		setSize(300,300);
		panel=getContentPane();
		panel.setLayout(new BorderLayout());
		
		
		reiniciar=new JButton("Reiniciar");
		reiniciar.addActionListener(this);
		
		//uso un BorderLayout porque el FlowLayout no me deja cambiar de tama�o a los elementos
		panel.add(reiniciar,BorderLayout.PAGE_START);
		
		
		tableroC=new JPanel();
		tableroC.setLayout(new GridLayout(3,3));
		
		tableroB=new JButton[3][3];
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				tableroB[i][j]=new JButton();
				tableroB[i][j].addActionListener(this);
				tableroC.add(tableroB[i][j]);
			}
		}
		
		panel.add(tableroC, BorderLayout.CENTER);
		
		
		
		turnoText=new JLabel();
		panel.add(turnoText, BorderLayout.PAGE_END);
		
		reiniciar();
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	private void reiniciar() {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				//Les pongo nombre para que no haya error al comprobar ganadores
				//setName pone un texto INVISIBLE al boton mientras que setText pone un texto VISIBLE
				
				//tableroB[i][j].setText(""+(i+j));
				tableroB[i][j].setName(""+(i+j));
				
				//Habilito los botones de vuelta
				tableroB[i][j].setEnabled(true);
				
				//Elimino la imagen que pueda tener guardada
				tableroB[i][j].setIcon(null);

			}
		}
		//Siempre empieza con el jugador 1
		turnoText.setText("Turno Jugador 1");
		turno=true;
		
		//reinicio el contador de botones
		terminar=9;
	}
	
	
	private void gano() {

		if(	//COMPRUEBA FILAS
			(tableroB[0][0].getName()==tableroB[0][1].getName() && tableroB[0][0].getName()==tableroB[0][2].getName()) ||
			(tableroB[1][0].getName()==tableroB[1][1].getName() && tableroB[1][0].getName()==tableroB[1][2].getName()) ||
			(tableroB[2][0].getName()==tableroB[2][1].getName() && tableroB[2][0].getName()==tableroB[2][2].getName()) ||
			//COMPRUEBA COLUMNAS
			(tableroB[0][0].getName()==tableroB[1][0].getName() && tableroB[0][0].getName()==tableroB[2][0].getName()) ||
			(tableroB[0][1].getName()==tableroB[1][1].getName() && tableroB[0][1].getName()==tableroB[2][1].getName()) ||
			(tableroB[0][2].getName()==tableroB[1][2].getName() && tableroB[0][2].getName()==tableroB[2][2].getName()) ||
			//COMPRUEBA DIAGONALES
			(tableroB[0][0].getName()==tableroB[1][1].getName() && tableroB[0][0].getName()==tableroB[2][2].getName()) ||
			(tableroB[2][0].getName()==tableroB[1][1].getName() && tableroB[2][0].getName()==tableroB[0][2].getName())
			) {
			
			if(turno) {
				turnoText.setText("GANADOR Jugador 1");
			}
			else {
				turnoText.setText("GANADOR Jugador 2");
			}
			
			terminar=0;
			
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					tableroB[i][j].setEnabled(false);
				}
			}
		}
		else if(terminar==0) {
			turnoText.setText("EMPATE");
		}
		
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == reiniciar) {
			reiniciar();
			
		}
		else if(terminar>0){
			JButton aux = (JButton)arg0.getSource();
			if(turno) {
				aux.setName("x");
				//aux.setText("X");
				aux.setIcon(new ImageIcon("images\\X.png"));
				turnoText.setText("Turno Jugador 2");
			}
			else {
				aux.setName("o");
				//aux.setText("O");
				aux.setIcon(new ImageIcon("images\\O.png"));
				turnoText.setText("Turno Jugador 1");
			}
			aux.setEnabled(false);
			terminar--;
			gano();
			turno=!turno;
		}
	}

}
