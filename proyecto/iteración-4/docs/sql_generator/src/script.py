from faker import Faker
import random

NUM_ENTRIES = 1000001

fake = Faker()

def generate_compra():
    ret = []

    states = ['CREADA','PAGADA','CANCELADA']
    for i in range(NUM_ENTRIES):
        ret.append("INSERT INTO COMPRA (ID,VALOR_COMPRA_TOTAL,URL_FACTURA_ELECTRONICA,ESTADO_COMPRA,COMPRADOR,FECHA_COMPRA("
        +str(i)+','
        +str(random.randint(1000,3500000))+','
        +fake.domain_name()+','
        +random.choice(states)+','
        +fake.date()+";")
    return ret

def generate_productos_compra():
    ret = []
    for i in range(NUM_ENTRIES):
        ret.append("INSERT INTO PRODUCTOS_COMPRA (ID_COMPRA,ID_PRODUCTO,CANT_UNIDADES_COMPRADAS) VALUES ("
        + str(random.randint(1, NUM_ENTRIES-1)) + ","+ fake.aba() + "," +str(random.randint(1,20))+");")
    return ret

def generate_pedido():
    ret = []
    for i in range(NUM_ENTRIES):
        ret.append("INSERT INTO SUCURSAL (ID,CIUDAD,DIRECCION,NOMBRE,SIZE_SUCURSAL,SEGMENTO_OBJETIVO) VALUES ("
        +str(i)+","
        +fake.city()+","
        +");"
        )

    return ret

def main():
    print("Processing your mock data...")    
    compras = generate_compra()
    productos = generate_productos_compra()
    
    print("Done! Enjoy.")

if __name__ == "__main__":
    main()