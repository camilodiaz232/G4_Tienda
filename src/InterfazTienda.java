

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Mundo.Tienda.*;


public class InterfazTienda {

    // Objetos de la Interfaz
    private JPanel mainPanel;
    private JTextField tfNombre;
    private JTextField tfValor;
    private JTextField tfCantidad;
    private JButton btAceptar;
    private JButton btActualizar;
    private JRadioButton btComprar;
    private JRadioButton btVender;
    private JTable tableInv;
    private JComboBox cBoxProductos;
    private JButton btEliminar;
    private DefaultTableModel tableModel;
    private  DefaultComboBoxModel comboBoxModel;

    Object [] dataNombre;
    Object [] dataCantidad;
    Object[] dataValor;


    // Getter de los Botones tipo Radio
    public JRadioButton getBtComprar() {
        return btComprar;
    }
    public JRadioButton getBtVender() {
        return btVender;
    }


    public InterfazTienda() {   //Metodo de Interface Principal

        // Declaracion de la Accion en el boton ACEPTAR REGISTRO

        btAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Captura de valor de los campos de texto en las variables
                    String gNombre = tfNombre.getText();
                    int gCantidad = Integer.parseInt(tfCantidad.getText());
                    Float gValor = Float.parseFloat(tfValor.getText());

                // Validacion del Boton seleccionado del grupo BotonMovimietno
                    int gMovimiento;
                    if (getBtComprar().isSelected()){
                        gMovimiento = 1;
                    }else{
                        gMovimiento= 0;
                    }

                  // Registro de las variable en metodo de la clase Tienda
                    ingresarMovimiento(gNombre, gMovimiento, gCantidad, gValor);


                  // Borrar los valores de los campos de texto
                    tfNombre.setText("");
                    tfCantidad.setText("");
                    tfValor.setText("");


            }
        });


        
        //Declaracion de la Accion en el boton ACTUALIZAR INVENTARIO
        btActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

              // Obtencion de las listas de datos para cada columna
                dataNombre = actualizarInventario("Producto");
                dataCantidad = actualizarInventario("Cantidad");
                dataValor = actualizarInventario("Valor");
                //System.out.println("lista nombre"+dataNombre[1]);


              // Creación de tabla
                String [] nombreColumnas = {"Nombre","unidad","valor"};
                tableModel = new DefaultTableModel(1,0);
                //tableModel.addRow(nombreColumnas);
                tableModel.addColumn("nombre",dataNombre);
                tableModel.addColumn("Cantidad",dataCantidad);
                tableModel.addColumn("Cantidad",dataValor);
                tableInv.setModel(tableModel);

                // Cargue de producto al combo box
                comboBoxModel = new DefaultComboBoxModel(dataNombre);
                cBoxProductos.setModel(comboBoxModel);


            }
        });

        btEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Seleccion de producto elegido en el combo box
                String productoElimiar= (String) cBoxProductos.getSelectedItem();

                // Eliminacion de producto
                eliminarProducto(productoElimiar);


            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tienda");
        frame.setContentPane(new InterfazTienda().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(350,500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
