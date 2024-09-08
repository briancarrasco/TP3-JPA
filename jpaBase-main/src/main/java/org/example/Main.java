
package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();


        try {
            //
            entityManager.getTransaction().begin();


            Categoria perecedero = new Categoria("Perecederos");
            Categoria lacteos = new Categoria("Lacteos");
            Categoria limpieza = new Categoria("Limpieza");

            Articulo art1 = new Articulo(3,"Yogurt Ser Sabor Vainilla",1400);

            Articulo art2 = new Articulo(4,"Detergente Magistral",650);

            art1.getCategorias().add(perecedero);
            art1.getCategorias().add(lacteos);

            lacteos.getArticulos().add(art1);
            perecedero.getArticulos().add(art1);
            art2.getCategorias().add(limpieza);
            limpieza.getArticulos().add(art2);


            Factura fac1 = new Factura("08/09/2024", 40);

            Cliente cliente = new Cliente("Brian", "Carrasco", 100);
            Domicilio domicilio = new Domicilio("Ayacucho",62);

            cliente.setDomicilio(domicilio);

            fac1.setCliente(cliente);

            DetalleFactura det1 = new DetalleFactura();

            det1.setArticulo(art1);
            det1.setCantidad(5);
            det1.setSubtotal(50);

            fac1.getFacturas().add(det1);

            DetalleFactura det2 = new DetalleFactura();

            det2.setArticulo(art2);
            det2.setCantidad(1);
            det2.setSubtotal(10);

            fac1.getFacturas().add(det2);

            entityManager.persist(fac1);


            entityManager.flush();

            entityManager.getTransaction().commit();

        }catch (Exception e){

            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("No se pudo grabar la clase Factura");}

        entityManager.close();
        entityManagerFactory.close();
    }
}


