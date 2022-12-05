package uniandes.isis2304.superandes.interfazApp;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 * Clase de interfaz para mostrar los resultados de la ejecución de las
 * operaciones realizadas por el usuario
 */
@SuppressWarnings("serial")
public class PanelDatos extends JPanel {
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------
    /**
     * Area de texto con barras de deslizamiento
     */
    private JTextArea textArea;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel
     *
     */
    public PanelDatos() {
        setBorder(new TitledBorder("Panel de información"));
        setLayout(new BorderLayout());

        textArea = new JTextArea("Aquí sale el resultado de las operaciones solicitadas");
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza el panel con la información recibida por parámetro.
     *
     * @param texto El texto con el que actualiza el área
     */
    public void actualizarInterfaz(String texto) {
        textArea.setText(texto);
    }
}
