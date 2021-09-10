package factura.newpackagefactura;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;

public class frmFactura extends JFrame {

    public frmFactura() {
        cargar();
    }
    //ingresamos los elementos
    JLabel lblNombreEmpresa, lblRucFactura, lblNumFactura;
    JLabel lblNomCliente, lblFechaFac;
    JTextField txtNomCliente, txtFactura;
    JTable tblDetalle;
    JLabel lblSubTotal, lblIva, lblTotal;
    JTextField txtSubTotal, txtIva, txtTotal;
    JLabel lblProducto, lblCantidad, lblPreUnitario, lblImporte;
    JTextField txtProducto, txtCantidad, txtPreUnitario, txtImporte;
    JButton btnAgregar, btntotal;
    DefaultTableModel modelo;
    String[] nombresColumnas = {"Cantidad", "Producto", "PreUnitario", "Importe"};

    private void cargar() {
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        lblNombreEmpresa = new JLabel("COMPAÑIA");
        lblRucFactura = new JLabel("1750924381");
        lblNumFactura = new JLabel("000-000-005");
        lblNomCliente = new JLabel("CLIENTE");
        lblFechaFac = new JLabel("FECHA");
        txtNomCliente = new JTextField(20);
        txtFactura = new JTextField(10);
        modelo = new DefaultTableModel();
        modelo.addColumn("Cantidad");
        modelo.addColumn("Produtoc");
        modelo.addColumn("PreUnitario");
        modelo.addColumn("Importe");
        modelo.addRow(nombresColumnas);

        tblDetalle = new JTable();
        tblDetalle.setModel(modelo);

        lblSubTotal = new JLabel("SUBTOTAL");
        lblIva = new JLabel("iva");
        lblTotal = new JLabel("TOTAL");
        txtSubTotal = new JTextField();
        txtIva = new JTextField();
        txtTotal = new JTextField();
        lblProducto = new JLabel("PRODUCTO");
        lblCantidad = new JLabel("CANTIDAD");
        lblPreUnitario = new JLabel("PRE.UNITARIO");
        lblImporte = new JLabel("IMPORTE");
        txtProducto = new JTextField(20);
        txtCantidad = new JTextField(20);
        txtPreUnitario = new JTextField(20);
        txtPreUnitario.addMouseListener(new Calcular());
        txtImporte = new JTextField(20);
        btnAgregar = new JButton("AGREGAR");
        btnAgregar.addMouseListener(new Agregar());

        btntotal = new JButton("CALCULARR");
        btntotal.addMouseListener(new total());
        lblNombreEmpresa.setBounds(150, 10, 40, 10);
        lblRucFactura.setBounds(150, 30, 100, 20);
        lblNumFactura.setBounds(150, 50, 150, 20);

        lblNomCliente.setBounds(20, 70, 100, 20);
        txtNomCliente.setBounds(20, 90, 100, 20);

        lblFechaFac.setBounds(200, 70, 100, 20);
        txtFactura.setBounds(200, 90, 100, 20);

        lblCantidad.setBounds(20, 110, 100, 20);
        txtCantidad.setBounds(20, 130, 100, 20);

        lblProducto.setBounds(140, 110, 100, 20);
        txtProducto.setBounds(140, 130, 100, 20);

        lblPreUnitario.setBounds(260, 110, 100, 20);
        txtPreUnitario.setBounds(260, 130, 100, 20);

        lblImporte.setBounds(380, 110, 100, 20);
        txtImporte.setBounds(380, 130, 100, 20);

        btnAgregar.setBounds(400, 170, 90, 30);
        btntotal.setBounds(400, 210, 105, 30);

        tblDetalle.setBounds(20, 170, 360, 200);

        lblSubTotal.setBounds(220, 400, 100, 20);
        lblIva.setBounds(220, 420, 100, 20);
        lblTotal.setBounds(220, 440, 100, 20);

        txtSubTotal.setBounds(280, 400, 100, 20);
        txtIva.setBounds(280, 420, 100, 20);
        txtTotal.setBounds(280, 440, 100, 20);

        add(lblNombreEmpresa);
        add(lblRucFactura);
        add(lblNumFactura);
        add(lblNomCliente);
        add(txtNomCliente);
        add(lblFechaFac);
        add(txtFactura);
        add(tblDetalle);
        add(lblCantidad);
        add(lblProducto);
        add(lblPreUnitario);
        add(lblImporte);
        add(txtCantidad);
        add(txtProducto);
        add(txtPreUnitario);
        add(txtImporte);
        add(btnAgregar);
        add(btntotal);
        add(lblSubTotal);
        add(lblIva);
        add(lblTotal);
        add(txtSubTotal);
        add(txtIva);
        add(txtTotal);
        setVisible(true);

    }

    private class Calcular extends MouseAdapter {

        public void mouseExited(MouseEvent event) {
            double cant = Double.parseDouble(txtCantidad.getText());
            double pu = Double.parseDouble(txtPreUnitario.getText());
            txtImporte.setText(String.valueOf(cant * pu));

        }

    }

    private class Agregar extends MouseAdapter {

        public void mouseExited(MouseEvent event) {

            DefaultTableModel temp = (DefaultTableModel) tblDetalle.getModel();
            String[] datos = new String[4];
            datos[0] = txtCantidad.getText();
            datos[1] = txtProducto.getText();
            datos[2] = txtPreUnitario.getText();
            datos[3] = txtImporte.getText();
            temp.addRow(datos);
            tblDetalle.setModel(temp);

        }

    }

    private class total extends MouseAdapter {

        public void mouseExited(MouseEvent event) {
            float subtotal = 0;
            float total = 0;
            DecimalFormat formato1 = new DecimalFormat();
            for (int i = 1; i < tblDetalle.getRowCount(); i++) {
                subtotal += Double.parseDouble(tblDetalle.getModel().getValueAt(i, 3).toString());
            }
            txtSubTotal.setText(String.valueOf(subtotal));
            txtIva.setText(String.valueOf(subtotal * 0.12));
            total = (float) (subtotal * 1.12);
            txtTotal.setText(formato1.format(total));

        }

    }
}
