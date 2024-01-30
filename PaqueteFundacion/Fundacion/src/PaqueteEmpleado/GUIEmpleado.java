package PaqueteEmpleado;

import PaqueteConectaDB.BusEmpleado;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIEmpleado extends JFrame{
    BusEmpleado busEmpleado = new BusEmpleado();
    private JPanel panel;
    private JRadioButton radioProf, radioAdmin;
    private JTextField ingresaSala;
    private JTextField idNuevo,nombreNuevo,salarioNuevo,telefonoNuevo, cargoNuevo;
    private JTable areaHistorial,areaEmpleado;

    public GUIEmpleado(){Configuracion();}

    public String empleadoSelect (int columna){
        String id = null;
        try{
            int fila = areaEmpleado.getSelectedRow();
            id = areaEmpleado.getValueAt(fila, columna).toString();

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Elige una fila de la tabla");
        }
        return id;
    }

    public void Configuracion(){
        setTitle("Empleados");
        setSize(570,500);
        componentesVentana();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void componentesVentana(){
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        getContentPane().add(panel);

        colocarLabel();
        colocarMenu();
        colocarText();
        colocarRadioButton();
        colocarBoton();
        colocarTable();
    }

    public void colocarLabel (){
        JLabel label = new JLabel("Empleados Registrados:");
        label.setBounds(25,10,170,20);
        panel.add(label);

        JLabel label1 = new JLabel("Ingresar nuevo salario:");
        label1.setBounds(25,240,200,20);
        panel.add(label1);

        JLabel label2 = new JLabel("Identificación:");
        label2.setBounds(25,310,150,20);
        panel.add(label2);

        JLabel label3 = new JLabel("Nombre:");
        label3.setBounds(175,310,100,20);
        panel.add(label3);

        JLabel label4 = new JLabel("Salario:");
        label4.setBounds(395,310,100,20);
        panel.add(label4);

        JLabel label5 = new JLabel("Teléfono:");
        label5.setBounds(25,370,100,20);
        panel.add(label5);

        JLabel label6 = new JLabel("Nombre del cargo:");
        label6.setBounds(340,370,140,20);
        panel.add(label6);
    }

    public void colocarMenu(){
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menuHisto = new JMenu("Historial");
        menuBar.add(menuHisto);
        JMenuItem verSala = new JMenuItem("Salarios");
        menuHisto.add(verSala);

        verSala.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ind=0;
                String [] columna = {"Historial Actualizaciones"};
                String [][] filas = new String [busEmpleado.triggerActualizacion().size()][1];
                areaHistorial = new JTable(filas,columna);

                JScrollPane scrollPane = new JScrollPane(areaHistorial);
                scrollPane.setSize(10,10);

                for (String datos : busEmpleado.triggerActualizacion()){
                    filas[ind][0] = datos;
                    ind++;
                }
                JOptionPane.showMessageDialog(null,scrollPane);
            }
        });
    }

    public void colocarText(){
        ingresaSala = new JTextField();
        ingresaSala.setBounds(25,265,120,20);
        panel.add(ingresaSala);

        idNuevo = new JTextField();
        idNuevo.setBounds(25,330,100,20);
        panel.add(idNuevo);

        nombreNuevo = new JTextField();
        nombreNuevo.setBounds(175,330,170,20);
        panel.add(nombreNuevo);

        salarioNuevo = new JTextField();
        salarioNuevo.setBounds(395,330,130,20);
        panel.add(salarioNuevo);

        telefonoNuevo = new JTextField();
        telefonoNuevo.setBounds(25,390,100,20);
        panel.add(telefonoNuevo);

        cargoNuevo = new JTextField();
        cargoNuevo.setBounds(340,390,140,20);
        panel.add(cargoNuevo);
    }

    public void colocarRadioButton(){
        radioAdmin = new JRadioButton("Administrativo");
        radioAdmin.setBounds(175,380,130,20);
        panel.add(radioAdmin);

        radioProf = new JRadioButton("Profesional");
        radioProf.setBounds(175,400,100,20);
        panel.add(radioProf);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioAdmin);
        buttonGroup.add(radioProf);
    }

    public void colocarBoton(){
        JButton btnSalario = new JButton("Actualizar Salarios");
        btnSalario.setBounds(160,265,170,20);
        panel.add(btnSalario);

        btnSalario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int salario = Integer.parseInt(ingresaSala.getText());
                    busEmpleado.setSalario(salario, empleadoSelect(0));
                    busEmpleado.actualizarSalario();
                    ingresaSala.setText("");
                }catch (Exception io){
                    JOptionPane.showMessageDialog(null, "Verifica el salario ingresado");
                }
            }
        });

        JButton btnNuevEmp = new JButton("Agregar Empleado");
        btnNuevEmp.setBounds(387,265,140,20);
        panel.add(btnNuevEmp);
        btnNuevEmp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    for (Empleado empleado : busEmpleado.empleadosTbl()){
                        int id = empleado.getId();

                        if(idNuevo.equals(id)){
                            JOptionPane.showMessageDialog(null,"El código está siendo usado");
                        }
                        else{
                            int cod = Integer.parseInt(idNuevo.getText());
                            String nom = nombreNuevo.getText();
                            int pago = Integer.parseInt(salarioNuevo.getText());
                            String tele = telefonoNuevo.getText();
                            String nomCargo = cargoNuevo.getText();

                            if(radioProf.isSelected()){
                                System.out.println("Validado1");
                                busEmpleado.nuevoEmpleado(new Empleado(cod,nom,pago,tele),nomCargo,"profesional");
                            }
                            if(radioAdmin.isSelected()){
                                System.out.println("Validado2");
                                busEmpleado.nuevoEmpleado(new Empleado(cod,nom,pago,tele),nomCargo,"administrativo");
                            }
                            break;
                        }
                    }
                }catch (Exception io){
                    JOptionPane.showMessageDialog(null,"Verifica los datos que ingresaste");
                }
            }
        });
    }

    public void colocarTable(){
        int indice = 0;
        String [] columna = {"Id", "Nombre", "Salario", "Teléfono"};
        String [][] filas = new String [busEmpleado.empleadosTbl().size()][4];
        areaEmpleado = new JTable(filas,columna);

        for (Empleado empleado : busEmpleado.empleadosTbl()){
            filas[indice][0] = String.valueOf(empleado.getId());
            filas[indice][1] = empleado.getNombre();
            filas[indice][2] = String.valueOf(empleado.getSalario());
            filas[indice][3] = empleado.getTelefono();
            indice++;
        }
        JScrollPane scrollPane = new JScrollPane(areaEmpleado);
        scrollPane.setBounds(25,35,500,200);
        panel.add(scrollPane);
    }
}
