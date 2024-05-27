import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Scanner;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Color;

public class juego extends JFrame {
	private final ButtonGroup buttonGroup = new ButtonGroup();
	// Variable que cambia la posicion de la letra
	int posicion=0;
		// Lisado de palabras
		String [] palabras;
		 // Guarda la palabra oculta de la partida
		 String palabrajuego;
		 // Guarda la letra que se comparara con la primera letra de la palabra oculta
		 String letrajuego;
		 // Controla la impresion del dibujo
		 int numIntento=0;
		 // Guardo los intentos correctos para mostrar en pantalla 
		 String correcto="";
		 
		 //Variable de reiniciar la PARTIDA
		 boolean fin=false;
		 
		 /* Coloco en blanco el numero de * correspondiente 
		 a las letras faltantes de la palabra oculta, ya que depende de la longitud de la palabra oculta*/
		 String pista=" ";	
		 // Numero de espacios correspondiente a la cantidad de intentos correctos. 
		 String espacios=" ";
		 
		 // Labels del diseño
		 private JLabel palabraOcul;
		 private JLabel lbLetraSigu;
		 private JLabel lbIngresa;
		 private JTextField tfIntento;
		 private JLabel lblResultado;
		 private JRadioButton rdbtSi;
		 private JTextField tfValidar;
		 private JLabel lblIntentosCor;
		 private JButton boton2;
		 private JPanel contentPane;
		 private JButton boton1;
		 private JLabel lbPista;
		 private JLabel lblNewLabel_1;
		 private JLabel lbCantidadLetras;
		 private JRadioButton rdbtnNewRadioButton;
		
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					juego frame = new juego();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 */
	public juego() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("El juego del ahorcado");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
		lblNewLabel.setBounds(271, 0, 325, 48);
		contentPane.add(lblNewLabel);
		
		JLabel lblAdivinaLa = new JLabel("¡  Adivina la palabra !");
		lblAdivinaLa.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblAdivinaLa.setBounds(20, 78, 221, 48);
		contentPane.add(lblAdivinaLa);
		
		tfIntento = new JTextField();
		tfIntento.setBounds(30, 279, 96, 19);
		contentPane.add(tfIntento);
		tfIntento.setColumns(10);
		
		lbLetraSigu = new JLabel("¿Cual letra es la siguiente?");
		lbLetraSigu.setVisible(false);
		
		lbLetraSigu.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lbLetraSigu.setBounds(20, 239, 291, 28);
		contentPane.add(lbLetraSigu);
		
		lblResultado = new JLabel("");
		lblResultado.setFont(new Font("Comic Sans MS", Font.PLAIN, 27));
		lblResultado.setBounds(454, 498, 298, 55);
		contentPane.add(lblResultado);
		
		JLabel lblNewLabel_1_1 = new JLabel("¿Conoces la palabra oculta?");
		lblNewLabel_1_1.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(20, 323, 291, 28);
		contentPane.add(lblNewLabel_1_1);
		
		rdbtSi = new JRadioButton("Si");
		rdbtSi.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		buttonGroup.add(rdbtSi);
		rdbtSi.setBounds(20, 369, 69, 24);
		contentPane.add(rdbtSi);
		
		rdbtnNewRadioButton = new JRadioButton("No",true);
		rdbtnNewRadioButton.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(20, 409, 108, 28);
		contentPane.add(rdbtnNewRadioButton);
		
		tfValidar = new JTextField();
		tfValidar.setBounds(89, 376, 96, 19);
		contentPane.add(tfValidar);
		tfValidar.setColumns(10);
		
		palabraOcul = new JLabel("Prueba");
		palabraOcul.addContainerListener(new ContainerAdapter() {
			
		});
		palabraOcul.setBounds(582, 33, 129, 28);
		palabraOcul.setVisible(false);
		contentPane.add(palabraOcul);
		
		
		/*Creacion del boton*/
		boton1 = new JButton("Jugar");
		boton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				partida();
				// Creo la pista a partir de la palabra oculta del metodo partida()
				pista();
				
				// Vuelvo a pintar con la informacion cargada
				repaint();
				// Dejo en blanco el campo de texto luego de haber hecho un intento
				tfIntento.setText(null);
			}
		});
		boton1.setBounds(60, 482, 108, 41);
		contentPane.add(boton1);
		
		lbIngresa = new JLabel("Ingresa una letra:");
		lbIngresa.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lbIngresa.setBounds(20, 192, 165, 37);
		contentPane.add(lbIngresa);
		
		lblIntentosCor = new JLabel("");
		lblIntentosCor.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblIntentosCor.setBounds(30, 179, 165, 27);
		contentPane.add(lblIntentosCor);
		
		//Boton de reinicio
		boton2 = new JButton("Reiniciar");
		boton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Muestro y oculto los botones
				boton1.setEnabled(true);
				boton2.setVisible(false);
				// creo la palabra para la nueva partida
				creaPalabra();
				// Reinicio el contador del dibujo
				numIntento=0;
				repaint();
				// Borro el contenido de los campos
				tfValidar.setText(null);
				tfIntento.setText(null);
				
				//Borro el contenido de las label de respuesta
				lblResultado.setText("");
				lblIntentosCor.setText("");
				
				// reinicio la posicion para la nueva palabra oculta 
				posicion=0;
				// Borro el contenido correcto que muestro en la pantalla
				correcto="";
				//Reinicio la cantidad de * de la pista
				pista="";
				// Genero una nueva pista segun la palabra
				pista();
				
				// Coloco la opcion del radiobutton a No para eviar sumar intentos Incorrectos. 
				rdbtnNewRadioButton.setSelected(true);
				
				
			}
		});
		boton2.setBounds(196, 482, 108, 41);
		contentPane.add(boton2);
		
		lbPista = new JLabel("");
		lbPista.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbPista.setBounds(100, 181, 130, 25);
		contentPane.add(lbPista);
		
		lblNewLabel_1 = new JLabel("Paises");
		lblNewLabel_1.setBounds(352, 41, 90, 20);
		contentPane.add(lblNewLabel_1);
		
		lbCantidadLetras = new JLabel("Tiene letras.");
		lbCantidadLetras.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lbCantidadLetras.setBounds(19, 136, 261, 21);
		contentPane.add(lbCantidadLetras);
		boton2.setVisible(false);
		
		
		
		// LLamo al metodo que generara la palabra para esta partida
		creaPalabra();
		// Llamo al meotod que generara al pista con la cantidad de * 
		pista();
		
		

	}
	
	public void pista() {
		// Verifico si es la primera vez que se imprime, por ende tiene todos los caracteres de la letra oculta
		if (correcto.length()==0 && numIntento==0){
		for (int f=0; f<palabrajuego.length();f++) {
			pista="*"+pista;
			}
		// Imprimo espacios en blanco correspondientes a los intentos correctos
		}else {
			pista=" ";
			for (int s=0 ; s<correcto.length();s++) {
				pista=pista+" ";
			}
			// resto los intentos correctos a la palabra oculta para saber cuantas letras falta imprimir
			int cantidad= palabrajuego.length()-correcto.length();
			for (int y=0; y<cantidad; y++) {
				pista=pista+ "*";
			}
		}
		
		// Muestro el valor de la pista
		lbPista.setText(pista);

	}
	
	 // Metodo que crea el listado de palabaras
	 public  void creaPalabra() {
			
			palabras = new String[19];
			palabras[0]=("España");
			palabras[1]=("Colombia");
			palabras[2]=("China");
			palabras[3]= ("Mexico");
			palabras[4]= ("Canada");
			palabras[5]=("Argentina");
			palabras[6]= ("Brasil");
			palabras[7]=("Chile");
			palabras[8]=("Peru");
			palabras[9]=("Sudan");
			palabras[10]=("Francia");
			palabras[11]=("Alemania");
			palabras[12]=("Italia");
			palabras[13]=("Rusia");
			palabras[14]=("Japon");
			palabras[15]=("Australia");
			palabras[16]=("India");																
			palabras[17]=("Sudafrica");
			palabras[18]=("Egipto");
			
			// Seleccion aleatoria de la palabra
			int a = (int) (Math.random()*18);
			
			palabrajuego = palabras[a];
			
			palabraOcul.setText(palabrajuego);
			// Muestro la cantidad de letras de la palabra oculta
			lbCantidadLetras.setText("La palabra tiene : " + palabrajuego.length() + " letras.");
					
	 }
	
	 
	 public void partida() {
		 // Coloco fin a flase ya que no hay que reiniciar aun la partida
			boolean fin=false;
				
			
			// Carga de palabra
				letrajuego =String.valueOf(palabrajuego.charAt(posicion));
				lbLetraSigu.setText("¿Cual letra es la siguiente?");
				lbLetraSigu.setVisible(true);
				lbIngresa.setVisible(false);
				
				// Verifico si la persona conoce la palabra con la opcion del radio button
				if (rdbtSi.isSelected()) {
					String IntentoPalabra = tfValidar.getText();
					if(IntentoPalabra.equalsIgnoreCase(palabrajuego)) {
						lblResultado.setForeground(Color.green);
						lblResultado.setText("¡ Ganaste ^_^ !");
						boton1.setEnabled(false);
						boton2.setVisible(true);
						tfValidar.setText(null);
					}else {
						// En caso de error, sumo 1 al numero de intentos
						lblResultado.setForeground(Color.red);
						lblResultado.setText("¡ Incorrecto !");
						numIntento=numIntento+1;
						tfValidar.setText(null);
					}
				}else {
			
				
				//verificar la primera posicion
				
				String intento1 = tfIntento.getText();
				if (intento1.equalsIgnoreCase(letrajuego)==true) {
					lblResultado.setForeground(Color.green);
					lblResultado.setText("Muy bien");
					// Incremento la posición para la siguiente letra
					posicion+=1;
					// almaceno en todos los intentos correctos para luego compararlos con la palabra oculta
					correcto=correcto+intento1;
					lblIntentosCor.setText(correcto);
					//verifico si ya se ha adivinado toda la palabra
					if (correcto.equalsIgnoreCase(palabrajuego)) {
						lblResultado.setForeground(Color.green);
						lblResultado.setText("¡ Ganaste ^_^ !");
						boton1.setEnabled(false);
						boton2.setVisible(true);
					}
					
				}else {
					// Sumo 1 al numero de intentos
					lblResultado.setForeground(Color.red);
					lblResultado.setText("Incorrecto");
					numIntento=numIntento+1;
					
				}
			}
			
		}
	 
	
	// Creacion del dibujo.
	
	public void paint(Graphics g) {
		super.paint(g);
		
		
		
		// Dibujo de area de juego
		g.setColor(Color.white);
		g.fillRect(342,110,434,401);
		g.setColor(Color.black);
		g.draw3DRect(342,110, 434,401,false);
		
		// Todo el dibujo sera blanco y si el numero de intento sube, cambia a color negro
		g.setColor(Color.white);
		
		// Dibujo de la base
		if (numIntento>0) {
		g.setColor(Color.black);
		g.drawLine(450, 450, 550, 450);
		g.drawLine(500, 450, 500, 400);
		g.setColor(Color.white);
		}
		
		
		
		
		//dibujo primer intento
		if (numIntento>1) {
		g.setColor(Color.black);
		g.drawLine(500,400, 500, 350);
		// Vuelvo a colocarlo de color blanco para que no pinte el resto
		g.setColor(Color.white);
		}
		
		
		//
		if (numIntento>2) {
		g.setColor(Color.black);
		g.drawLine(500,350, 500, 300);
		g.setColor(Color.white);
		}

		//3ro
		if (numIntento>3) {
		g.setColor(Color.black);
		g.drawLine(500,300, 500, 250);
		g.setColor(Color.white);
		}
		
		//4to
		if (numIntento>4) {
		g.setColor(Color.black);
		g.drawLine(500,250, 500, 200);
		g.setColor(Color.white);
		}
		
		//5to
		if (numIntento>5) {
		g.setColor(Color.black);
		g.drawLine(500,200, 650, 200);
		g.setColor(Color.white);
		}
		
		//6to
		if (numIntento>6) {
		g.setColor(Color.black);
		g.drawLine(650,200, 650, 228);
		g.setColor(Color.white);
		}
		
		//Cabeza
		//7mo
		if (numIntento>7) {
		g.setColor(Color.black);
		g.fillOval(630, 230, 40, 40);
		g.setColor(Color.white);
		}
		
		//Cuerpo
		//8vo
		if (numIntento>8) {
		g.setColor(Color.black);
		g.drawLine(650,240, 650, 350);
		g.setColor(Color.white);
		}
		
		//9no
		if (numIntento>9) {
		g.setColor(Color.black);
		g.drawLine(650,350, 620, 390);
		g.drawLine(650,350, 680, 390);
		g.setColor(Color.white);
		}

		//10mo
		if (numIntento>10) {
		g.setColor(Color.black);
		g.drawLine(620,300, 680, 300);
		g.setColor(Color.white);
		// mensaje final y boton de reinicio
		boton1.setEnabled(false);
		boton2.setVisible(true);
		lblResultado.setText("Perdiste (´*_*`) ");
		
		}
		
	}
}

