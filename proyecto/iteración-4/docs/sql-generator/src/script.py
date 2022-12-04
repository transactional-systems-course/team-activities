from faker import Faker
import random
import csv
import numpy

NUM_ENTRIES = 100000
fake = Faker()
documents = ['6515366485', '2466663244', '4659401782',
             '1441956743', '0061136859', '3077934302', '9192144667']
bar_codes = []
suppliers = []


def generate_sucursal():
    ret = []
    ret.append([
        "ID",
        "CIUDAD",
        "DIRECCION",
        "NOMBRE",
        "SIZE_SUCURSAL",
        "SEGMENTO_OBJETIVO"
    ])
    for i in range(NUM_ENTRIES):
        ret.append([
            str(i),
            fake.city(),
            fake.address(),
            fake.street_name(),
            random.choice(["Small", "Medium", "Big"]),
            fake.text(max_nb_chars=10)
        ])
    return numpy.array(ret, dtype=object)


def generate_usuario():
    ret = []
    ret.append([
        "NUMERO_DOCUMENTO",
        "NOMBRE",
        "TIPO_CLIENTE",
        "TIPO_DOCUMENTO",
        "PALABRA_CLAVE",
        "CORREO",
        "ID_ROL",
        "DIRECCION",
        "PUNTOS_ACUMULADOS",
        "ID_SUCURSAL"
    ])
    for i in range(NUM_ENTRIES):
        ret.append([
            fake.ean(length=8),
            fake.name(),
            random.choice(['PersonaNatural', 'Empresa']),
            random.choice(['CC', 'TI', 'NIT', 'CE']),
            fake.password(),
            fake.ascii_free_email(),
            str(random.randint(1, 6)),
            fake.address(),
            str(random.randint(1, 1000)),
            str(random.randint(1, 1000)),
        ])
    return numpy.array(ret, dtype=object)


def generate_bodega():
    ret = []
    ret.append([
        "ID",
        "TIPO_CONTENEDOR",
        "TIPO_PRODUCTO",
        "CAPACIDAD_MAXIMA_VOLUMEN",
        "CAPACIDAD_MAXIMA_PESO",
        "NIVEL_ABASTECIMIENTO",
        "ID_SUCURSAL"
    ])
    for i in range(NUM_ENTRIES):
        ret.append([
            str(i),
            'Bodega',
            'NoPerecedero',
            str(random.randint(1, 1000000)),
            str(random.randint(1, 1000000)),
            str(random.randint(1, 10000)),
            str(random.randint(1, 1000)),
        ])
    return numpy.array(ret, dtype=object)


def generate_estante():
    ret = []
    ret.append([
        "ID",
        "TIPO_CONTENEDOR",
        "TIPO_PRODUCTO",
        "CAPACIDAD_MAXIMA_VOLUMEN",
        "CAPACIDAD_MAXIMA_PESO",
        "NIVEL_ABASTECIMIENTO",
        "ID_SUCURSAL"
    ])
    for i in range(NUM_ENTRIES):
        ret.append([
            str(i),
            'Estante',
            'Mueble',
            str(random.randint(1, 1000000)),
            str(random.randint(1, 1000000)),
            str(random.randint(1, 10000)),
            str(random.randint(1, 1000)),
        ])
    return numpy.array(ret, dtype=object)


def generate_proveedor():
    ret = []
    ret.append([
        "NIT",
        "NOMBRE",
        "CALIFICACION_PROMEDIO"
    ])
    for i in range(NUM_ENTRIES):
        nit = fake.ean(length=8)
        suppliers.append(nit)
        ret.append([
            nit,
            fake.company(),
            str(random.randint(1, 5))
        ])
    return numpy.array(ret, dtype=object)


def generate_producto_almacenamiento():
    ret = []
    ret.append([
        "ID",
        "ID_CONTENEDOR_ACTUAL",
        "EXISTENCIAS_ACTUALES",
        "NIVEL_REORDEN"
    ])
    for i in range(NUM_ENTRIES):
        ret.append([
            str(i),
            str(i),
            str(random.randint(1, 10000000)),
            str(random.randint(1, 100000))
        ])
    return numpy.array(ret, dtype=object)


def generate_producto_detalles():
    ret = []
    ret.append([
        "ID",
        "PRECIO",
        "PRECIO_POR_UNIDAD_MEDIDA",
        "PRESENTACION",
        "CANTIDAD_PRESENTACION"
    ])
    for i in range(NUM_ENTRIES):
        ret.append([
            str(i),
            str(random.randint(1000, 10000000)),
            str(random.randint(1, 100000)),
            fake.company(),
            str(random.randint(1, 1000)),
        ])
    return numpy.array(ret, dtype=object)


def generate_producto_medicion():
    ret = []
    ret.append([
        "ID",
        "MEDIDA",
        "UNIDAD_MEDIDA",
        "ESPECIFICACION_EMPACADO",
        "UNIDAD_ESPECIFICACION_EMPACADO"
    ])
    for i in range(NUM_ENTRIES):
        ret.append([
            str(i),
            str(random.randint(1, 1000)),
            random.choice(['gr', 'ml']),
            str(random.randint(1, 1000000)),
            random.choice(['pesoGR', 'volumenCm3'])
        ])
    return numpy.array(ret, dtype=object)


def generate_producto():
    ret = []
    ret.append([
        "CODIGO_BARRAS",
        "NOMBRE",
        "MARCA",
        "CATEGORIA",
        "TIPO_PRODUCTO",
        "FECHA_VENCIMIENTO",
        "ID_PEDIDO",
        "ID_SUCURSAL",
        "INFO_DETALLES",
        "INFO_MEDICION",
        "INFO_ALMACENAMIENTO"
    ])
    for i in range(NUM_ENTRIES):
        bar_code = fake.ean(length=13)
        bar_codes.append(bar_code)
        ret.append([
            bar_code,
            fake.slug(),
            fake.slug(),
            fake.company(),
            random.choice(['Perecedero', 'NoPerecedero',
                          'Aseo', 'Electrodomestico']),
            fake.date(),
            str(i),
            str(i),
            str(i),
            str(i),
            str(i),
        ])
    return numpy.array(ret, dtype=object)


def generate_cliente():
    ret = []
    ret.append([
        "NUMERO_DOCUMENTO",
        "NOMBRE",
        "TIPO_CLIENTE",
        "TIPO_DOCUMENTO",
        "PALABRA_CLAVE",
        "CORREO",
        "ID_ROL",
        "DIRECCION",
        "PUNTOS_ACUMULADOS",
        "ID_SUCURSAL"
    ])
    for i in range(NUM_ENTRIES):
        ret.append([
            fake.ean(length=8),
            fake.name(),
            random.choice(['PersonaNatural', 'Empresa']),
            random.choice(['CC', 'TI', 'NIT', 'CE']),
            fake.password(),
            fake.ascii_free_email(),
            str(6),
            fake.address(),
            str(random.randint(1, 1000)),
            str(random.randint(1, 1000)),
        ])
    return numpy.array(ret, dtype=object)


def generate_promocion():
    ret = []
    ret.append([
        "ID",
        "REBAJA_EN_PRECIO",
        "TIPO_PROMOCION",
        "FECHA_INICIO",
        "FECHA_FIN",
        "ID_PRODUCTO",
        "CANT_UNIDADES_DISPONIBLES",
        "TOTAL_UNIDADES_OFRECIDAS"
    ])
    for i in range(NUM_ENTRIES):
        ret.append([
            str(i),
            str(random.randint(10, 35000)),
            random.choice(
                ['Combo', "Pague1LleveDescuento2", "DescuentoPorcentaje"]),
            fake.date(),
            fake.date(),
            random.choice(bar_codes),
            str(random.randint(1, 100)),
            str(random.randint(100, 1000)),
        ])
    return numpy.array(ret, dtype=object)


def generate_pedido_proveedor():
    ret = []
    ret.append([
        "ID_PEDIDO",
        "ID_PROVEEDOR"
    ])
    for i in range(NUM_ENTRIES):
        ret.append([
            str(i),
            random.choice(suppliers)
        ])
    return numpy.array(ret, dtype=object)


def generate_compra():
    ret = []
    states = ['CREADA', 'PAGADA', 'CANCELADA']
    ret.append([
        "ID",
        "VALOR_COMPRA_TOTAL",
        "URL_FACTURA_ELECTRONICA",
        "ESTADO_COMPRA",
        "COMPRADOR",
        "FECHA_COMPRA"
    ])
    for i in range(NUM_ENTRIES):
        ret.append([
            str(i),
            str(random.randint(1000, 3500000)),
            fake.url(),
            random.choice(states),
            random.choice(documents),
            fake.date()
        ])
    return numpy.array(ret, dtype=object)


def generate_pedido():
    ret = []
    ret.append([
        "ID",
        "CANTIDAD_RECOMPRA",
        "PRECIO_COMPRA_PRODUCTO",
        "PRECIO_TOTAL_PEDIDO",
        "FECHA_ESPERADA_ENTREGA",
        "FECHA_ENTREGA",
        "ESTADO",
        "ID_SUCURSAL"
    ])
    for i in range(NUM_ENTRIES):
        ret.append([
            str(i),
            str(random.randint(1, 1000)),
            str(random.randint(100, 350000)),
            str(random.randint(1000, 3500000)),
            fake.date(),
            fake.date(),
            random.choice(['CREADO', 'EN_PROCESO', 'ENTREGADO']),
            str(random.randint(1, 1000))
        ])
    return numpy.array(ret, dtype=object)


def generate_producto_compra():
    ret = []
    ret.append([
        "ID_COMPRA",
        "ID_PRODUCTO",
        "CANT_UNIDADES_COMPRADAS"
    ])
    for i in range(NUM_ENTRIES):
        ret.append([
            str(random.randint(1, 1000)),
            random.choice(bar_codes),
            str(random.randint(1, 20))
        ])
    return numpy.array(ret, dtype=object)


def generate_review_pedido():
    ret = []
    ret.append([
        "ID",
        "PORCENTAJE_CALIFICACION_CALIDAD",
        "COMENTARIOS_CALIFICACION_CALIDAD",
        "ID_PEDIDO",
        "ID_PROVEEDOR"
    ])
    for i in range(NUM_ENTRIES):
        ret.append([
            str(i),
            str(random.randint(1, 100)),
            fake.text(max_nb_chars=80),
            str(random.randint(1, 1000)),
            str(random.randint(1, 1000))
        ])
    return numpy.array(ret, dtype=object)


def array_to_csv(matrix):
    for item, array in matrix.items():
        print(f"Writing {item}...")
        with open(f'../build/{item}.csv', 'w+', newline='') as file:
            mywriter = csv.writer(file, delimiter=',')
            mywriter.writerows(array)


def main():
    print("Generating your mock data...")
    matrix = {}
    # Generate arrays of mock data
    print("---------------------")
    print("Creating sucursales...")
    matrix['sucursales'] = generate_sucursal()
    print("Creating usuarios...")
    matrix['usuarios'] = generate_usuario()
    print("Creating bodegas...")
    matrix['bodegas'] = generate_bodega()
    print("Creating estantes...")
    matrix['estantes'] = generate_estante()
    print("Creating proveedores...")
    matrix['proveedores'] = generate_proveedor()
    print("Creating producto-almacenamiento...")
    matrix['producto-almacenamiento'] = generate_producto_almacenamiento()
    print("Creating producto-detalles...")
    matrix['producto-detalles'] = generate_producto_detalles()
    print("Creating producto-medicion...")
    matrix['producto-medicion'] = generate_producto_medicion()
    print("Creating productos...")
    matrix['productos'] = generate_producto()
    print("Creating clientes...")
    matrix['clientes'] = generate_cliente()
    print("Creating promociones...")
    matrix['promociones'] = generate_promocion()
    print("Creating pedido-proveedor...")
    matrix['pedido-proveedor'] = generate_pedido_proveedor()
    print("Creating compras...")
    matrix['compras'] = generate_compra()
    print("Creating pedidos...")
    matrix['pedidos'] = generate_pedido()
    print("Creating producto-compra...")
    matrix['producto-compra'] = generate_producto_compra()
    print("Creating review-pedido...")
    matrix['review-pedido'] = generate_review_pedido()
    print("---------------------")
    # Write mock data to CSV files
    array_to_csv(matrix)
    print("---------------------")
    print("Done! Enjoy.")


if __name__ == "__main__":
    main()
