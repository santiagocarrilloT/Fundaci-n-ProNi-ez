package PrincipalGUI;

import PaqueteConectaDB.BusComunidad;
import PaqueteConectaDB.BusEmpleado;
import PaqueteConectaDB.BusProyecto;
import PaqueteEmpleado.GUIEmpleado;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PlantillaGUI extends JFrame {
    private JPanel panel;
    private JTextField actualizaPres;
    private JComboBox comboEsplz = new JComboBox();
    private JTable areaProyecto;
    private JTextArea areaProfesional;
    public PlantillaGUI(){
        configuracionR();
    }


    public String proyectSelec(int columna){
        String nomProyect = null;
        try{
            int fila = areaProyecto.getSelectedRow();
            nomProyect = areaProyecto.getValueAt(fila, columna).toString();

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Verifica si has elegido un proyecto");
        }
        return nomProyect;
    }

    public void configuracionR(){
        setTitle("Fundación Pro Niñez");
        setSize(700,600);
        componentesVentana();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void componentesVentana(){
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        getContentPane().add(panel);

        colocarLabel();
        colocarCombo();
        colocarText();
        colocarButton();
        colocarMenu();
        colocarTable();
    }
    public void colocarLabel(){
       JLabel label1 = new JLabel("Especializaciones:");
       label1.setBounds(50,290,160,20);
       panel.add(label1);

       JLabel label2 = new JLabel("Cambiar presupuestos:");
       label2.setBounds(160,19,160,20);
       panel.add(label2);
    }

    public void colocarText(){
        actualizaPres = new JTextField();
        actualizaPres.setBounds(305,20,130,20);
        panel.add(actualizaPres);
    }

    public void colocarButton(){
        JButton consultaProf = new JButton("Consultar Profesionales");
        consultaProf.setBounds(235,310, 175, 20);
        panel.add(consultaProf);

        consultaProf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               areaProfesional.setText("");
                BusEmpleado busEmpleado = new BusEmpleado();
                busEmpleado.tipoEspz(String.valueOf(comboEsplz.getSelectedItem()));

                for (String s : busEmpleado.especiaProf()) {
                    areaProfesional.append(s);
                    areaProfesional.append("\n");
                }
            }
        });

        JButton actualizarDatos = new JButton("Actualizar presupuesto");
        actualizarDatos.setBounds(455,20,179,20);
        panel.add(actualizarDatos);

        actualizarDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    double tomarPres = Double.parseDouble(actualizaPres.getText());
                    BusProyecto busProyecto = new BusProyecto();
                    busProyecto.actualizaPresu(tomarPres,proyectSelec(0));
                    busProyecto.modificaTabla();
                    actualizaPres.setText("");
                }catch (Exception i){
                    JOptionPane.showMessageDialog(null,"Verifica haber ingresado un caracter numérico");
                }
            }
        });

        JButton actuaEmpleado = new JButton("Actualiza empleados");
        actuaEmpleado.setBounds(235,410,160,20);
        panel.add(actuaEmpleado);

        actuaEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GUIEmpleado();
            }
        });
    }

    public void colocarMenu(){
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menuProyect = new JMenu("Información proyectos");
        menuBar.add(menuProyect);
        JMenuItem menuNota = new JMenuItem("Buscar calificaciones");
        menuProyect.add(menuNota);
        JMenuItem menuProfesionales = new JMenuItem("Ver profesionales");
        menuProyect.add(menuProfesionales);
        JMenuItem menuObjetivo = new JMenuItem("Ver objetivos");
        menuProyect.add(menuObjetivo);

        menuNota.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double rango = Double.parseDouble(JOptionPane.showInputDialog(null,"Rango de notas a visualizar (Entre 0 y 5)"));
                if (rango>=1 && rango<=5){
                    BusProyecto busProyecto = new BusProyecto();
                    busProyecto.nota(rango);
                    JOptionPane.showMessageDialog(null, busProyecto.notaP());
                }
                else{
                    JOptionPane.showMessageDialog(null, "Verifica el rango ingresado");
                }
            }
        });

        menuProfesionales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BusProyecto busProyecto = new BusProyecto();
                busProyecto.nombreProyect(proyectSelec(0));
                JOptionPane.showMessageDialog(null, busProyecto.participaProf());
            }
        });

        menuObjetivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BusProyecto busProyecto = new BusProyecto();
                busProyecto.nombreProyect(proyectSelec(0));
                JOptionPane.showMessageDialog(null, busProyecto.objetivoP());
            }
        });

        JMenu menuComunidad = new JMenu("Información Comunidad");
        menuBar.add(menuComunidad);
        JMenuItem menuJoven = new JMenuItem("Ver jóvenes");
        menuComunidad.add(menuJoven);
        menuJoven.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BusComunidad busComunidad = new BusComunidad();
                busComunidad.nomComunidad(proyectSelec(1));
                JOptionPane.showMessageDialog(null, busComunidad.ninosComuni());
            }
        });

        JMenuItem menuFecha = new JMenuItem("Por fechas");
        menuProyect.add(menuFecha);

        menuFecha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {String fechaInicioStr = JOptionPane.showInputDialog(null, "Fecha de inicio (YYYY-MM-DD)");
                String fechaFinStr = JOptionPane.showInputDialog(null, "Fecha de fin (YYYY-MM-DD)");

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaInicio = null;
                Date fechaFin = null;

                try {
                    fechaInicio = new Date(dateFormat.parse(fechaInicioStr).getTime());
                    fechaFin = new Date(dateFormat.parse(fechaFinStr).getTime());
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

                String[] opciones = {"Consulta general", "Consulta por fecha de inicio", "Consulta por fecha de fin"};
                int seleccion = JOptionPane.showOptionDialog(null, "Seleccione el tipo de consulta", "Tipo de consulta", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

                boolean consultaGeneral = false;
                boolean consultaFechaInicio = false;
                boolean consultaFechaFin = false;

                if (seleccion == 0) {
                    consultaGeneral = true;
                } else if (seleccion == 1) {
                    consultaFechaInicio = true;
                } else if (seleccion == 2) {
                    consultaFechaFin = true;
                }

                BusProyecto busProyecto = new BusProyecto();
                ArrayList<String> proyectos = busProyecto.consultarProyectosPorFechas(fechaInicio, fechaFin, consultaGeneral, consultaFechaInicio, consultaFechaFin);


                if (proyectos.isEmpty()) {
                    areaProfesional.setText("No se encontraron proyectos en el rango de fechas especificado.");
                } else {
                    StringBuilder mensaje = new StringBuilder("Proyectos encontrados:\n");
                    for (String proyecto : proyectos) {
                        mensaje.append("- ").append(proyecto).append("\n");
                    }
                    areaProfesional.setText(mensaje.toString());
                }
            }
        });

        JMenu menuNinos = new JMenu("Informacion niños");
        menuBar.add(menuNinos);
        JMenuItem menuProedad = new JMenuItem("Edad promedio por proyecto");
        menuNinos.add(menuProedad);

        menuProedad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BusProyecto busProyecto = new BusProyecto();
                String proyectoSeleccionado = proyectSelec(0); // Obtener el proyecto seleccionado desde el método proyectSelec()

                if (proyectoSeleccionado != null) {
                    busProyecto.nombreProyect(proyectoSeleccionado); // Pasar el proyecto seleccionado a la instancia de BusProyecto
                    double edadPromedio = busProyecto.calcularEdadPromedio(); // Llamar al método calcularEdadPromedio()

                    JOptionPane.showMessageDialog(null, "El promedio de edad para el proyecto " + proyectoSeleccionado + " es: " + edadPromedio);
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún proyecto en la tabla");
                }

            }
        });

        JMenuItem menuedadcomu = new JMenuItem("Edad promedio por comunidad");
        menuNinos.add(menuedadcomu);

        menuedadcomu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BusProyecto busProyecto = new BusProyecto();
                String comunidadSele = proyectSelec(1); // Obtener el proyecto seleccionado desde el método proyectSelec()

                if (comunidadSele != null) {
                    busProyecto.nombreProyect(comunidadSele); // Pasar el proyecto seleccionado a la instancia de BusProyecto
                    double edadPromedio = busProyecto.calcularEdadPromedioComuni();

                    JOptionPane.showMessageDialog(null, "El promedio de edad para la comunidad " + comunidadSele + " es: " + edadPromedio);
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha seleccionado ningúna comunidad en la tabla");
                }

            }
        });
    }

    public void colocarCombo(){
        comboEsplz.setBounds(50,310,150,20);

        areaProfesional = new JTextArea();
        areaProfesional.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaProfesional);
        scrollPane.setBounds(30,340,200, 170);
        scrollPane.setBackground(Color.LIGHT_GRAY);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(areaProfesional.getBorder(), BorderFactory.createEmptyBorder(5,5,5,5)));

        BusEmpleado busEmpleado = new BusEmpleado();
        for (String s : busEmpleado.especializacion()) {
            comboEsplz.addItem(s);
        }
        panel.add(scrollPane);
        panel.add(comboEsplz);
    }

    public void colocarTable(){
        BusProyecto proyecto = new BusProyecto();
        BusComunidad comunidad = new BusComunidad();
        BusEmpleado empleado = new BusEmpleado();
        int contador = 0;

        String [] columna = {"Proyecto", "Comunidad", "Responsable"};
        String [][] infoTabla= new String[proyecto.beneficiadosP().size()][3];
        areaProyecto = new JTable(infoTabla, columna);

        for (String nombreP : proyecto.beneficiadosP()) {
            infoTabla[contador][0] = nombreP;
            contador++;
        }

        contador=0;

        for (String nombreC : comunidad.beneficiadosC()) {
            infoTabla[contador][1] = nombreC;
            contador++;
        }

        contador=0;

        for (String nombreR : empleado.responsableP()) {
            infoTabla[contador][2] = nombreR;
            contador++;
        }

        JScrollPane scrollPane = new JScrollPane(areaProyecto);
        scrollPane.setBounds(35, 50, 600, 200);
        panel.add(scrollPane);
    }

    public static void main(String[] args) {
        new PlantillaGUI();
    }

}