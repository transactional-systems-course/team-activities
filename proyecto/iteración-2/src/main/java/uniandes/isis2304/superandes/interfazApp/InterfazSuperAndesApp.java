package uniandes.isis2304.superandes.interfazApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.superandes.negocio.Proveedor;
import uniandes.isis2304.superandes.negocio.SuperAndes;

/**
 * Clase principal de la interfaz
 */
@SuppressWarnings("serial")

public class InterfazSuperAndesApp extends JFrame implements ActionListener {
	/*
	 * ****************************************************************
	 * Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfazSuperAndesApp.class.getName());

	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigApp.json";

	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos
	 */
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD.json";

	/*
	 * ****************************************************************
	 * Atributos
	 *****************************************************************/
	/**
	 * Objeto JSON con los nombres de las tablas de la base de datos que se quieren
	 * utilizar
	 */
	private JsonObject tableConfig;

	/**
	 * Asociación a la clase principal del negocio.
	 */
	private SuperAndes superandes;

	/*
	 * ****************************************************************
	 * Atributos de interfaz
	 *****************************************************************/
	/**
	 * Objeto JSON con la configuración de interfaz de la app.
	 */
	private JsonObject guiConfig;

	/**
	 * Panel de despliegue de interacción para los requerimientos
	 */
	private PanelDatos panelDatos;

	/**
	 * Menú de la aplicación
	 */
	private JMenuBar menuBar;

	/*
	 * ****************************************************************
	 * Métodos
	 *****************************************************************/
	/**
	 * Construye la ventana principal de la aplicación. <br>
	 * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
	 */
	public InterfazSuperAndesApp() {
		// Carga la configuración de la interfaz desde un archivo JSON
		guiConfig = openConfig("Interfaz", CONFIG_INTERFAZ);

		// Configura la apariencia del frame que contiene la interfaz gráfica
		configurarFrame();
		if (guiConfig != null) {
			crearMenu(guiConfig.getAsJsonArray("menuBar"));
		}

		tableConfig = openConfig("Tablas BD", CONFIG_TABLAS);
		superandes = new SuperAndes(tableConfig);

		String path = guiConfig.get("bannerPath").getAsString();
		panelDatos = new PanelDatos();

		setLayout(new BorderLayout());
		add(new JLabel(new ImageIcon(path)), BorderLayout.NORTH);
		add(panelDatos, BorderLayout.CENTER);
	}

	/*
	 * ****************************************************************
	 * Métodos de configuración de la interfaz
	 *****************************************************************/
	/**
	 * Lee datos de configuración para la aplicació, a partir de un archivo JSON o
	 * con valores por defecto si hay errores.
	 *
	 * @param tipo       - El tipo de configuración deseada
	 * @param archConfig - Archivo Json que contiene la configuración
	 * @return Un objeto JSON con la configuración del tipo especificado
	 *         NULL si hay un error en el archivo.
	 */
	private JsonObject openConfig(String tipo, String archConfig) {
		JsonObject config = null;
		try {
			Gson gson = new Gson();
			FileReader file = new FileReader(archConfig);
			JsonReader reader = new JsonReader(file);
			config = gson.fromJson(reader, JsonObject.class);
			log.info("Se encontró un archivo de configuración válido: " + tipo);
		} catch (Exception e) {
			// e.printStackTrace ();
			log.info("NO se encontró un archivo de configuración válido");
			JOptionPane.showMessageDialog(null,
					"No se encontró un archivo de configuración de interfaz válido: " + tipo, "SuperAndes App",
					JOptionPane.ERROR_MESSAGE);
		}
		return config;
	}

	/**
	 * Método para configurar el frame principal de la aplicación
	 */
	private void configurarFrame() {
		int alto = 0;
		int ancho = 0;
		String titulo = "";

		if (guiConfig == null) {
			log.info("Se aplica configuración por defecto");
			titulo = "Super Andes APP Default";
			alto = 300;
			ancho = 500;
		} else {
			log.info("Se aplica configuración indicada en el archivo de configuración");
			titulo = guiConfig.get("title").getAsString();
			alto = guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(50, 50);
		setResizable(true);
		setBackground(Color.WHITE);

		setTitle(titulo);
		setSize(ancho, alto);
	}

	/**
	 * Método para crear el menú de la aplicación con base em el objeto JSON leído
	 * Genera una barra de menú y los menús con sus respectivas opciones
	 *
	 * @param jsonMenu - Arreglo Json con los menùs deseados
	 */
	private void crearMenu(JsonArray jsonMenu) {
		// Creación de la barra de menús
		menuBar = new JMenuBar();
		for (JsonElement men : jsonMenu) {
			// Creación de cada uno de los menús
			JsonObject jom = men.getAsJsonObject();

			String menuTitle = jom.get("menuTitle").getAsString();
			JsonArray opciones = jom.getAsJsonArray("options");

			JMenu menu = new JMenu(menuTitle);

			for (JsonElement op : opciones) {
				// Creación de cada una de las opciones del menú
				JsonObject jo = op.getAsJsonObject();
				String lb = jo.get("label").getAsString();
				String event = jo.get("event").getAsString();

				JMenuItem mItem = new JMenuItem(lb);
				mItem.addActionListener(this);
				mItem.setActionCommand(event);

				menu.add(mItem);
			}
			menuBar.add(menu);
		}
		setJMenuBar(menuBar);
	}

	/*
	 * ****************************************************************
	 * CRUD de PRODUCTO
	 *****************************************************************/
	/**
	 * Consulta productos por característica
	 */
	public void consultarCaracteristicaProductos() {
		try {
			String tipoBusqueda = JOptionPane.showInputDialog(this,
					"¿Tipo de búsqueda? (Número de 0 a 7 de acuerdo al tipo)",
					"Consultar productos por característica (Precio, fecha expiración posterior, Rango Peso o volumen, Proveedor, Disponible en ciudad, Disponible en sucursal, Por tipo o categoria, Ventas mayores a valor en ciertas fechas)",
					JOptionPane.QUESTION_MESSAGE);
			String precio = JOptionPane.showInputDialog(this, "¿Precio? (dejar en blanco, sí no es de interés)",
					"Consultar productos por característica",
					JOptionPane.QUESTION_MESSAGE);
			String fechaExpPos = JOptionPane.showInputDialog(this,
					"¿Fecha expiración mínima? (dejar en blanco, sí no es de interés)",
					"Consultar productos por característica",
					JOptionPane.QUESTION_MESSAGE);
			String pesoVolInf = JOptionPane.showInputDialog(this,
					"¿Peso o volumen mínimo? (dejar en blanco, sí no es de interés)",
					"Consultar productos por característica",
					JOptionPane.QUESTION_MESSAGE);
			String pesoVolSup = JOptionPane.showInputDialog(this,
					"¿Peso o volumen máximo? (dejar en blanco, sí no es de interés)",
					"Consultar productos por característica",
					JOptionPane.QUESTION_MESSAGE);
			String proveedor = JOptionPane.showInputDialog(this, "¿Proveedor? (dejar en blanco, sí no es de interés)",
					"Consultar productos por característica",
					JOptionPane.QUESTION_MESSAGE);
			String ciudad = JOptionPane.showInputDialog(this, "¿Ciudad? (dejar en blanco, sí no es de interés)",
					"Consultar productos por característica",
					JOptionPane.QUESTION_MESSAGE);
			String tipo = JOptionPane.showInputDialog(this, "¿Tipo producto? (dejar en blanco, sí no es de interés)",
					"Consultar productos por característica",
					JOptionPane.QUESTION_MESSAGE);
			String categoria = JOptionPane.showInputDialog(this,
					"¿Categoria producto? (dejar en blanco, sí no es de interés)",
					"Consultar productos por característica",
					JOptionPane.QUESTION_MESSAGE);
			String precioInf = JOptionPane.showInputDialog(this,
					"Límite inferior de precio de venta? (dejar en blanco, sí no es de interés)",
					"Consultar productos por característica",
					JOptionPane.QUESTION_MESSAGE);
			String fechaInf = JOptionPane.showInputDialog(this,
					"Fecha de venta mínima? (dejar en blanco, sí no es de interés)",
					"Consultar productos por característica",
					JOptionPane.QUESTION_MESSAGE);
			String fechaSup = JOptionPane.showInputDialog(this,
					"Fecha de venta máxima? (dejar en blanco, sí no es de interés)",
					"Consultar productos por característica",
					JOptionPane.QUESTION_MESSAGE);
			if (tipoBusqueda != null) {
				String resultado = "En consultar productos por característica\n\n";
				resultado += "\n" + superandes.consultarCaracteristicaProductos(tipoBusqueda, precio, fechaExpPos,
						pesoVolInf, pesoVolSup, proveedor, ciudad, tipo, categoria, precioInf, fechaInf, fechaSup);
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);

			} else {
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} catch (Exception e) {
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/*
	 * ****************************************************************
	 * CRUD de PROMOCION
	 *****************************************************************/
	/*
	 * Registrar nueva promoción
	 */
	public void registrarPromocion() {
		try {
			String rebajaEnPrecio = JOptionPane.showInputDialog(this, "¿Precio de rebaja?",
					"Registrar promoción", JOptionPane.QUESTION_MESSAGE);
			String tipoPromocion = JOptionPane.showInputDialog(this, "¿Tipo promocion?",
					"Registrar promoción", JOptionPane.QUESTION_MESSAGE);
			String fechaInicio = JOptionPane.showInputDialog(this, "¿Fecha inicio?",
					"Registrar promoción", JOptionPane.QUESTION_MESSAGE);
			String fechaFin = JOptionPane.showInputDialog(this, "¿Fecha fin?",
					"Registrar promoción", JOptionPane.QUESTION_MESSAGE);
			String idProducto = JOptionPane.showInputDialog(this, "¿ID producto?",
					"Registrar promoción", JOptionPane.QUESTION_MESSAGE);
			String cantUnidadesDisponibles = JOptionPane.showInputDialog(this, "¿Cantidades disponibles?",
					"Registrar promoción", JOptionPane.QUESTION_MESSAGE);
			String totalUnidadesOfrecidas = JOptionPane.showInputDialog(this, "¿Total unidades ofrecidas?",
					"Registrar promoción", JOptionPane.QUESTION_MESSAGE);

			String resultado = "En registrar promocion\n\n";
			resultado += "\n" + superandes.registrarPromocion(rebajaEnPrecio, tipoPromocion, fechaInicio, fechaFin,
					idProducto, cantUnidadesDisponibles, totalUnidadesOfrecidas);
			resultado += "\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);

		} catch (Exception e) {
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/*
	 * Consulta las 20 promociones más populares
	 */
	public void consultarPromosPopulares() {
		try {
			String resultado = "En consultar promociones populares\n\n";
			resultado += "\n" + superandes.consultarPromosPopulares();
			resultado += "\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);
		} catch (Exception e) {
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/*
	 * ****************************************************************
	 * CRUD de PEDIDO
	 *****************************************************************/
	/*
	 * Crea una nueva orden de pedido
	 */
	public void crearPedido() {
		try {
			String cantidadRecompra = JOptionPane.showInputDialog(this, "¿Cantidad recompra?",
					"Crear nuevo pedido", JOptionPane.QUESTION_MESSAGE);
			String precioCompraProducto = JOptionPane.showInputDialog(this, "¿Precio compra producto?",
					"Crear nuevo pedido", JOptionPane.QUESTION_MESSAGE);
			String precioTotalPedido = JOptionPane.showInputDialog(this, "¿Precio total?",
					"Crear nuevo pedido", JOptionPane.QUESTION_MESSAGE);
			String fechaEsperadaEntrega = JOptionPane.showInputDialog(this, "¿FECHA ENTREGA? (dd-Mon-yyyy)",
					"Crear nuevo pedido", JOptionPane.QUESTION_MESSAGE);
			String FechaEntrega = "NULL";
			String estado = "CREADO";
			String idSucursal = JOptionPane.showInputDialog(this, "¿ID Sucursal?",
					"Crear nuevo pedido", JOptionPane.QUESTION_MESSAGE);

			String resultado = "En crear pedido\n\n";
			resultado += "\n" + superandes.crearPedido(cantidadRecompra, precioCompraProducto, precioTotalPedido,
					fechaEsperadaEntrega, FechaEntrega, estado, idSucursal);
			resultado += "\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);
		} catch (Exception e) {
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/*
	 * Registra la llegada de un pedido, al cambiar el estado a "ENTREGADO"
	 */
	public void registrarLlegadaPedido() {
		try {
			String idPedido = JOptionPane.showInputDialog(this, "¿ID del pedido?",
					"Registrar llegada de un pedido", JOptionPane.QUESTION_MESSAGE);
			superandes.registrarLlegadaPedido(idPedido);
			String resultado = "\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);
		} catch (Exception e) {
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/*
	 * ****************************************************************
	 * CRUD de ESTANTE
	 *****************************************************************/
	/*
	 * Registra el aprovisionamiento de productos a un estante
	 */
	public void aprovisionarEstante() {
		try {
			String idEstante = JOptionPane.showInputDialog(this, "¿Id del estante?",
					"Aprovisionar estante", JOptionPane.QUESTION_MESSAGE);
			String idProducto = JOptionPane.showInputDialog(this, "¿Id del producto a colocar?",
					"Aprovisionar estante", JOptionPane.QUESTION_MESSAGE);

			String resultado = "En aprovisionar estante\n\n";
			resultado += "\n" + superandes.aprovisionarEstante(idEstante, idProducto);
			resultado += "\n Operación terminada";
		} catch (Exception e) {
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/*
	 * Informa el índice de ocupación de un estante por ID
	 */
	public void consultarIndiceOcupacionEstante() {
		try {
			String idEstante = JOptionPane.showInputDialog(this, "¿ID estante?",
					"Consultar índice ocupación estante", JOptionPane.QUESTION_MESSAGE);

			String resultado = "En indice de ocupación de estante\n\n";
			resultado += "\n" + superandes.consultarIndiceOcupacionEstante(idEstante);
			resultado += "\n Operación terminada";
		} catch (Exception e) {
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/*
	 * ****************************************************************
	 * CRUD de VENTA
	 *****************************************************************/
	/*
	 * Registra una nueva venta
	 */
	public void registrarVenta() {
		try {
			String valorCompraTotal = JOptionPane.showInputDialog(this, "¿Valor compra total?",
					"Registrar nueva venta", JOptionPane.QUESTION_MESSAGE);
			String urlFacturaElectronica = JOptionPane.showInputDialog(this, "¿URL e-factura?",
					"Registrar nueva venta", JOptionPane.QUESTION_MESSAGE);
			String estadoCompra = "PAGADA";
			String comprador = JOptionPane.showInputDialog(this, "¿ID comprador?",
					"Registrar nueva venta", JOptionPane.QUESTION_MESSAGE);
			String fechaCompra = JOptionPane.showInputDialog(this, "¿Fecha compra?",
					"Registrar nueva venta", JOptionPane.QUESTION_MESSAGE);
			String resultado = "En crear pedido\n\n";
			resultado += "\n" + superandes.crearPedido(valorCompraTotal, urlFacturaElectronica, estadoCompra, comprador,
					fechaCompra);
			resultado += "\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);
		} catch (Exception e) {
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/*
	 * Consulta las ventas de un solo cliente
	 */
	public void consultarVentasPorCliente() {
		try {
			String idCliente = JOptionPane.showInputDialog(this, "¿ID cliente?",
					"Consultar ventas de cliente", JOptionPane.QUESTION_MESSAGE);

			String resultado = "En consultar ventas de cliente\n\n";
			resultado += "\n" + superandes.consultarVentasPorCliente(idCliente);
			resultado += "\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);
		} catch (Exception e) {
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/*
	 * ****************************************************************
	 * CRUD de SUCURSAL
	 *****************************************************************/
	public void consultarDineroRecolectado() {
		try {
			String nombreTb = JOptionPane.showInputDialog(this, "Nombre del tipo de bedida?",
					"Consultar dinero recolectado", JOptionPane.QUESTION_MESSAGE);
			if (nombreTb != null) {
				VOTipoBebida tipoBebida = parranderos.darTipoBebidaPorNombre(nombreTb);
				String resultado = "En buscar Tipo Bebida por nombre\n\n";
				if (tipoBebida != null) {
					resultado += "El tipo de bebida es: " + tipoBebida;
				} else {
					resultado += "Un tipo de bebida con nombre: " + nombreTb + " NO EXISTE\n";
				}
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			} else {
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} catch (Exception e) {
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/*
	 * ****************************************************************
	 * CRUD de BODEGA
	 *****************************************************************/
	/*
	 * Informa el índice de ocupación de una bodega por ID
	 */
	public void consultarIndiceOcupacionBodega() {
		try {
			String idBodega = JOptionPane.showInputDialog(this, "¿ID bodega?",
					"Consultar índice ocupación bodega", JOptionPane.QUESTION_MESSAGE);

			String resultado = "En indice de ocupación de bodega\n\n";
			resultado += "\n" + superandes.consultarIndiceOcupacionBodega(idBodega);
			resultado += "\n Operación terminada";
		} catch (Exception e) {
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/*
	 * ****************************************************************
	 * CRUD de PROVEEDOR
	 *****************************************************************/
	/**
	 * Consulta las compras realizadas por SuperAndes a un proveedor
	 */
	public void consultarPedidosProveedor() {
		try {
			String nombreProveedor = JOptionPane.showInputDialog(this, "¿Nombre del proveedor?",
					"Consultar compras a un proveedor", JOptionPane.QUESTION_MESSAGE);
			if (nombreProveedor != null) {
				String resultado = "En compras a un proveedor por nombre\n\n";
				resultado += "\n" + superandes.consultarPedidosProveedor(nombreProveedor);
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);

			} else {
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} catch (Exception e) {
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/*
	 * ****************************************************************
	 * Métodos administrativos
	 *****************************************************************/
	/**
	 * Muestra el log de SuperAndes
	 */
	public void mostrarLogSuperAndes() {
		mostrarArchivo("superandes.log");
	}

	/**
	 * Muestra el log de datanucleus
	 */
	public void mostrarLogDatanuecleus() {
		mostrarArchivo("datanucleus.log");
	}

	/**
	 * Limpia el contenido del log de parranderos
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogSuperAndes() {
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo("superandes.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de parranderos ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogDatanucleus() {
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo("datanucleus.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia todas las tuplas de todas las tablas de la base de datos de
	 * parranderos
	 * Muestra en el panel de datos el número de tuplas eliminadas de cada tabla
	 */
	public void limpiarBD() {
		try {
			// Ejecución de la demo y recolección de los resultados
			long eliminados[] = superandes.limpiarSuperAndes();

			// Generación de la cadena de caracteres con la traza de la ejecución
			String resultado = "\n\n************ Limpiando la base de datos ************ \n";
			resultado += "\nLimpieza terminada";

			panelDatos.actualizarInterfaz(resultado);
		} catch (Exception e) {
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Muestra el modelo conceptual de Parranderos
	 */
	public void mostrarModeloConceptual() {
		mostrarArchivo("docs/modelos/modelo-conceptual.pdf");
	}

	/**
	 * Muestra el esquema de la base de datos de Parranderos
	 */
	public void mostrarEsquemaBD() {
		mostrarArchivo("docs/modelos/modelo-relacional.pdf");
	}

	/**
	 * Muestra el script de creación de la base de datos
	 */
	public void mostrarScriptBD() {
		mostrarArchivo("docs/sql/data/EsquemaSuperAndes.sql");
	}

	/**
	 * Muestra la documentación Javadoc del proyectp
	 */
	public void mostrarJavadoc() {
		mostrarArchivo("doc/index.html");
	}

	/**
	 * Muestra la información acerca del desarrollo de esta apicación
	 */
	public void acercaDe() {
		String resultado = "\n\n ************************************\n\n";
		resultado += " * Universidad de	los	Andes (Bogotá	- Colombia)\n";
		resultado += " * Departamento de	Ingeniería	de	Sistemas y Computación\n";
		resultado += " * \n";
		resultado += " * Curso: ISIS2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: SuperAndes Uniandes\n";
		resultado += " * @version 1.0\n";
		resultado += " * @author B-03\n";
		resultado += "\n ************************************\n\n";

		panelDatos.actualizarInterfaz(resultado);
	}

	/*
	 * ****************************************************************
	 * Métodos privados para la presentación de resultados y otras operaciones
	 *****************************************************************/
	/**
	 * Genera una cadena de caracteres con la descripción de la excepcion e,
	 * haciendo énfasis en las excepcionsde JDO
	 *
	 * @param e - La excepción recibida
	 * @return La descripción de la excepción, cuando es
	 *         javax.jdo.JDODataStoreException, "" de lo contrario
	 */
	private String darDetalleException(Exception e) {
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException")) {
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions()[0].getMessage();
		}
		return resp;
	}

	/**
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicación
	 *
	 * @param e - La excepción generada
	 * @return La cadena con la información de la excepción y detalles adicionales
	 */
	private String generarMensajeError(Exception e) {
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y parranderos.log para más detalles";
		return resultado;
	}

	/**
	 * Limpia el contenido de un archivo dado su nombre
	 *
	 * @param nombreArchivo - El nombre del archivo que se quiere borrar
	 * @return true si se pudo limpiar
	 */
	private boolean limpiarArchivo(String nombreArchivo) {
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(new File(nombreArchivo)));
			bw.write("");
			bw.close();
			return true;
		} catch (IOException e) {
			// e.printStackTrace();
			return false;
		}
	}

	/**
	 * Abre el archivo dado como parámetro con la aplicación por defecto del sistema
	 *
	 * @param nombreArchivo - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo(String nombreArchivo) {
		try {
			Desktop.getDesktop().open(new File(nombreArchivo));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * ****************************************************************
	 * Métodos de la Interacción
	 *****************************************************************/
	/**
	 * Método para la ejecución de los eventos que enlazan el menú con los métodos
	 * de negocio
	 * Invoca al método correspondiente según el evento recibido
	 *
	 * @param pEvento - El evento del usuario
	 */
	@Override
	public void actionPerformed(ActionEvent pEvento) {
		String evento = pEvento.getActionCommand();
		try {
			Method req = InterfazSuperAndesApp.class.getMethod(evento);
			req.invoke(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * ****************************************************************
	 * Programa principal
	 *****************************************************************/
	/**
	 * Este método ejecuta la aplicación, creando una nueva interfaz
	 *
	 * @param args Arreglo de argumentos que se recibe por línea de comandos
	 */
	public static void main(String[] args) {
		try {

			// Unifica la interfaz para Mac y para Windows.
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			InterfazSuperAndesApp interfaz = new InterfazSuperAndesApp();
			interfaz.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
