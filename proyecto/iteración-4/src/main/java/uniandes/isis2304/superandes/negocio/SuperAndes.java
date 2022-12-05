package uniandes.isis2304.superandes.negocio;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

import uniandes.isis2304.superandes.persistencia.PersistenciaSuperAndes;

/**
 * Clase principal del negocio
 * Satisface todos los requerimientos funcionales del negocio
 */
public class SuperAndes {
	/*
	 * ****************************************************************
	 * Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(SuperAndes.class.getName());

	/*
	 * ****************************************************************
	 * Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia
	 */
	private PersistenciaSuperAndes ps;

	/*
	 * ****************************************************************
	 * Métodos
	 *****************************************************************/
	/**
	 * El constructor por defecto
	 */
	public SuperAndes() {
		ps = PersistenciaSuperAndes.getInstance();
	}

	/**
	 * El constructor que recibe los nombres de las tablas en tableConfig
	 *
	 * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad
	 *                    de persistencia
	 */
	public SuperAndes(JsonObject tableConfig) {
		ps = PersistenciaSuperAndes.getInstance(tableConfig);
	}

	/**
	 * Cierra la conexión con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia() {
		ps.cerrarUnidadPersistencia();
	}

	/*
	 * ****************************************************************
	 * Métodos para manejar TODO
	 *****************************************************************/

	/*
	 * ****************************************************************
	 * Métodos para administración
	 *****************************************************************/

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de
	 * SuperAndes
	 *
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en
	 *         las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 *         TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public long[] limpiarSuperAndes() {
		log.info("Limpiando la BD de SuperAndes");
		long[] borrados = ps.limpiarSuperAndes();
		log.info("Limpiando la BD de SuperAndes: Listo!");
		return borrados;
	}

	public String consultarCaracteristicaProductos(String tipoBusqueda, String precio, String fechaExpPos,
			String pesoVolInf, String pesoVolSup, String proveedor, String ciudad, String tipo, String categoria,
			String precioInf, String fechaInf, String fechaSup) {
		// TODO Auto-generated method stub
		log.info("Consultando información: " + tipoBusqueda + "...");
		String caracteristicaProductos = ps.consultarCaracteristicaProductos(tipoBusqueda, precio, fechaExpPos,
				pesoVolInf, pesoVolSup, proveedor, ciudad, tipo, categoria, precioInf, fechaInf, fechaSup);
		log.info("Información consultada.");
		return caracteristicaProductos;
	}

	public String registrarPromocion(String rebajaEnPrecio, String tipoPromocion, String fechaInicio, String fechaFin,
			String idProducto, String cantUnidadesDisponibles, String totalUnidadesOfrecidas) {
		// TODO Auto-generated method stub
		log.info("Registrando información: " + rebajaEnPrecio + "...");
		String promocion = ps.registrarPromocion(rebajaEnPrecio, tipoPromocion, fechaInicio, fechaFin,
				idProducto, cantUnidadesDisponibles, totalUnidadesOfrecidas);
		log.info("Información registrada.");
		return promocion;
	}

	/**
	 * Consulta las promociones populares.
	 *
	 * @Input: null
	 * @return String promos
	 */
	public String consultarPromosPopulares() {
		// TODO Auto-generated method stub
		log.info("Consultando promos populares...");
		String promos = ps.consultarPromosPopulares();
		log.info("promos consultadas.");
		return promos;
	}

	public String darCompradoresFrecuentes(String idSucursal) {
		log.info("revisando compradores frecuentes...");
		String compradores = ps.darCompradoresFrecuentes(idSucursal);
		return compradores;
	}

	public String darDatosProductos(String idSucursal) {
		log.info("revisando datos de productos...");
		String datos = ps.darDatosProductos(idSucursal);
		return datos;
	}

	public String darEntregasInfrecuentes(String idSucursal) {
		log.info("revisando entregas infrecuentes...");
		String entregas = ps.darEntregasInfrecuentes(idSucursal);
		return entregas;
	}

	/**
	 * Crea un pedido.
	 *
	 * @param cantidadRecompra
	 * @param precioCompraProducto
	 * @param precioTotalPedido
	 * @param fechaEsperadaEntrega
	 * @param fechaEntrega
	 * @param estado
	 * @param idSucursal
	 * @return
	 */
	public String crearPedido(String cantidadRecompra, String precioCompraProducto, String precioTotalPedido,
			String fechaEsperadaEntrega, String fechaEntrega, String estado, String idSucursal) {
		// TODO Auto-generated method stub
		log.info("Creando pedido...");
		String pedido = ps.crearPedido(cantidadRecompra, precioCompraProducto, precioTotalPedido,
				fechaEsperadaEntrega, fechaEntrega, estado, idSucursal);
		log.info("Pedido creado.");
		return pedido;
	}

	/**
	 * Registra la llegada de un pedido por id
	 *
	 * @param idPedido
	 */
	public void registrarLlegadaPedido(String idPedido) {
		// TODO Auto-generated method stub
		log.info("registrando llegada de pedido " + idPedido);
		String pedido = ps.registrarLlegadaPedido(idPedido);
		log.info("pedido registrado.");

	}

	/**
	 * aprovisiona un estante con un producto.
	 *
	 * @param idEstante
	 * @param idProducto
	 * @return
	 */
	public String aprovisionarEstante(String idEstante, String idProducto) {
		// TODO Auto-generated method stub
		log.info("Aprovisionando estante " + idEstante);
		String aprov = ps.aprovisionarEstante(idEstante, idProducto);
		log.info("Estante aprovisionado.");
		return aprov;
	}

	/**
	 * Consulta el indice de ocupación de un estante.
	 *
	 * @param idEstante
	 * @return
	 */
	public String consultarIndiceOcupacionEstante(String idEstante) {
		// TODO Auto-generated method stub
		log.info("consultando indice de ocupación...");
		String consulta = ps.consultarIndiceOcupacionEstante(idEstante) + "";
		log.info("indice ocupación consultado.");
		return consulta;
	}

	/**
	 * registra una venta.
	 *
	 * @param valorCompraTotal
	 * @param urlFacturaElectronica
	 * @param estadoCompra
	 * @param comprador
	 * @param fechaCompra
	 * @return
	 */
	public String registrarVenta(String valorCompraTotal, String urlFacturaElectronica, String estadoCompra,
			String comprador, String fechaCompra) {
		// TODO Auto-generated method stub
		log.info("registrando venta...");
		String venta = ps.registrarVenta(valorCompraTotal, urlFacturaElectronica, estadoCompra, comprador, fechaCompra);
		log.info("venta registrada.");
		return venta;
	}

	/**
	 * consulta las ventas asociadas a un cliente
	 *
	 * @param idCliente
	 * @return cliente
	 */
	public String consultarVentasPorCliente(String idCliente) {
		// TODO Auto-generated method stub

		log.info("consultando ventas del cliente: " + idCliente);
		String cliente = ps.consultarVentasPorCliente(idCliente);
		log.info("ventas registradas.");
		return cliente;
	}

	/*
	 * Consulta el dinero recolectado por una sucursal.
	 * Input: String idSSucursal
	 * output: String sucursal
	 */
	public String consultarDineroRecolectado(String idSucursal) {
		// TODO Auto-generated method stub
		log.info("consultando dinero recolectado");
		String sucursal = ps.consultarDineroRecolectado(idSucursal);
		log.info("dinero consultado.");
		return sucursal;
	}

	/*
	 * Consulta el indice de Ocupación de una bodega.
	 * Input: String idBodega
	 * output: String consulta
	 */
	public String consultarIndiceOcupacionBodega(String idBodega) {
		// TODO Auto-generated method stub
		log.info("consultando indice de ocupación...");
		String consulta = ps.consultarIndiceOcupacionBodega(idBodega) + "";
		log.info("indice consultado.");
		return consulta;
	}

	/*
	 * Consulta los pedidos dados al proveedor:
	 * Input; String nombreProveedor
	 * Output; String pedidos.
	 *
	 */
	public String consultarPedidosProveedor(String nombreProveedor) {
		// TODO Auto-generated method stub
		log.info("Consultando pedidos del proveedor...");
		String pedidos = ps.consultarPedidosProveedor(nombreProveedor);
		log.info("pedidos consultados.");
		return pedidos;
	}

	/*
	 * Consulta el consumo en SuperAndes
	 */
	public String consultarConsumo(String startDate, String endDate) {
		log.info("Consultando consumo en SuperAndes...");
		String consumo = ps.consultarConsumo(startDate, endDate);
		log.info("Consumo consultado.");
		return consumo;
	}

	/*
	 * Consulta el no consumo en SuperAndes
	 */
	public String consultarNoConsumo(String startDate, String endDate) {
		log.info("Consultando NO consumo en SuperAndes...");
		String noConsumo = ps.consultarNoConsumo(startDate, endDate);
		log.info("No consumo consultado.");
		return noConsumo;
	}

	/*
	 * Consulta el funcionamiento en SuperAndes
	 */
	public String consultarFuncionamiento() {
		log.info("Consultando funcionamiento en SuperAndes...");
		String funcionamiento = ps.consultarFuncionamiento();
		log.info("Funcionamiento consultado.");
		return funcionamiento;
	}

	/*
	 * Consulta buenos clientes en SuperAndes
	 */
	public String consultarBuenosClientes() {
		log.info("Consultando buenos clientes en SuperAndes...");
		String buenosClientes = ps.consultarBuenosClientes();
		log.info("Buenos clientes consultados.");
		return buenosClientes;
	}
}
