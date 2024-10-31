/*
 * Nombre : Jose Enrique Felix Esparragoza
 * NoControl : 21170315
 * Materia : Topicos avanzados de programacion
 * Unidad : 2
 * Proyecto :  COMPONENTE IMAGENES DE RIESGO
 * Fecha : 25, octubre, 2024
 * Maestro : Clemente Garcia Gerardo
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ComponenteImagenesRiesgo extends JPanel implements ComponentListener, KeyListener, ActionListener {

    private JTextField texto;
    private Font fontOriginal;
    private Font[] fontsAleatorias;
    private JLabel label;
    private JRadioButton btnOriginal, btnAleatorio;
    private ButtonGroup grupo;
    private String leyenda;
    private boolean leyendaActiva;
    private ManejadorMySQLImagenesRiesgo manejador;
    private final static String DIRECTORIO_IMAGENES = "imagenes/";

    public ComponenteImagenesRiesgo() {
        this("Posible Imagen");
    }

    public ComponenteImagenesRiesgo(String leyenda) {
        this.leyenda = leyenda;
        hazInterfaz();
        leyendaActiva = true;
        listeners();
        manejador = new ManejadorMySQLImagenesRiesgo();
    }

    private void listeners() {
        addComponentListener(this);
        texto.addKeyListener(this);
        btnAleatorio.addActionListener(this);
        btnOriginal.addActionListener(this);
    }

    private void hazInterfaz() {
        setLayout(null);
        texto = new JTextField();
        btnOriginal = new JRadioButton("Font original");
        btnAleatorio = new JRadioButton("Font aleatoria");
        btnOriginal.setForeground(Color.BLUE);
        btnAleatorio.setForeground(Color.BLUE);
        grupo = new ButtonGroup();
        grupo.add(btnOriginal);
        grupo.add(btnAleatorio);
        texto.setText(leyenda);
        label = new JLabel();
        label.setForeground(Color.RED);
        fontOriginal = getFont();
        fontsAleatorias = new Font[] { new Font("Arial", Font.PLAIN, 12), new Font("Times New Roman", Font.BOLD, 14),
                new Font("Courier New", Font.ITALIC, 16), new Font("Verdana", Font.BOLD + Font.ITALIC, 18),
                new Font("Impact", Font.PLAIN, 22), new Font("Century Schoolbook", Font.PLAIN, 24) };

    }

    @Override
    public void componentResized(ComponentEvent evt) {
        int w = this.getWidth();
        int h = this.getHeight();
        int x = (int) (w * 0.10);
        int y = (int) (h * 0.20);
        int ancho = (int) (w * 0.50);
        int alto = (int) (h * 0.20);
        texto.setBounds(x, y, ancho, alto);
        label.setBounds(x, 0, ancho, alto);
        x = (int) (w * 0.65);
        y = (int) (h * 0.20);
        ancho = (int) (w * 0.20);
        alto = (int) (h * 0.10);
        btnOriginal.setBounds(x, y, ancho, alto);
        x = (int) (w * 0.65);
        y = (int) (h * 0.30);
        ancho = (int) (w * 0.20);
        alto = (int) (h * 0.10);
        btnAleatorio.setBounds(x, y, ancho, alto);
        add(texto);
        add(btnOriginal);
        add(btnAleatorio);
        add(label);
    }

    @Override
    public void componentMoved(ComponentEvent evt) {
    }

    @Override
    public void componentShown(ComponentEvent evt) {
    }

    @Override
    public void componentHidden(ComponentEvent evt) {
    }

    public String getText() {
        if (!leyendaActiva)
            return texto.getText();
        return "null";
    }

    @Override
    public void keyTyped(KeyEvent e) {
        String s = texto.getText();
        char car = e.getKeyChar();
        if (leyendaActiva){
            label.setText(leyenda);
            texto.setText("");
            leyendaActiva = false;
            return;
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.isControlDown()) {
            e.consume();
            return;
        }

        if (e.getKeyCode() == 36) {
            e.consume();
            return;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String s = texto.getText();
        if (texto.getText().equals("")){
            leyendaActiva = true;
            texto.setText(leyenda);
            label.setText("");
            return;
        }
        if (manejador.evaluarExistencia(s)){
            String nombreImagen = manejador.query(s);
            int w = this.getWidth();
            int h = this.getHeight();
            int ancho = (int) (w * 0.50);
            int alto = (int) (h * 0.20);
            ImageIcon imagen = Rutinas.AjustarImagen(DIRECTORIO_IMAGENES + nombreImagen, ancho / 4, alto);
            System.out.println(DIRECTORIO_IMAGENES + nombreImagen);
            label.setIcon(imagen);
            return;
        }
        label.setIcon(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnOriginal)
            texto.setFont(fontOriginal);
        if (e.getSource() == btnAleatorio)
            texto.setFont(fontsAleatorias[Rutinas.nextInt(fontsAleatorias.length)]);
    }

}