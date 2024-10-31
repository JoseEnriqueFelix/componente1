/*
 * Nombre : Jose Enrique Felix Esparragoza
 * NoControl : 21170315
 * Materia : Topicos avanzados de programacion
 * Unidad : 2
 * Proyecto :  COMPONENTE IMAGENES DE RIESGO
 * Fecha : 25, octubre, 2024
 * Maestro : Clemente Garcia Gerardo
 */

import java.awt.*;
import javax.swing.*;

public class AppImagenesRiesgo extends JFrame{

    public AppImagenesRiesgo(){
        super("Tarea Componentes");
        hazInterfaz();
    }
    private void hazInterfaz() {
        setSize(400,300);
		this.setLocationRelativeTo(null);
		setLayout(new GridLayout(0,1));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		add( new ComponenteImagenesRiesgo());
        add( new ComponenteImagenesRiesgo("Estaciones"));
		setVisible(true);
    }
    public static void main(String[] args) {
        new AppImagenesRiesgo();
    }
}
