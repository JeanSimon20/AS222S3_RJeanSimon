package vg.test;

import vg.model.Bienes;
import vg.service.BienesService;

import java.util.List;

public class BienesServicesTest {

    public static void main(String[] args) throws Exception {
        BienesServicesTest test = new BienesServicesTest();

        test.testGetAllActive();
       /* test.testGetAll();
        test.testGetAllInactive();
        test.testUpdate();
        test.testInsert();
        test.testDelete();
        test.testRestore(); */
    }

    public void testGetAll() throws Exception {
        BienesService service = new BienesService();
        List<Bienes> products = service.getAll();
        System.out.println("Lista de todos los Bienes:");
        for (Bienes product : products) {
            System.out.println(product);
        }
    }

    public void testGetAllActive() throws Exception {
        BienesService service = new BienesService();
        List<Bienes> activeProducts = service.getAllActive();
        System.out.println("Lista de Bienes activos:");
        for (Bienes product : activeProducts) {
            System.out.println(product);
        }
    }

    public void testGetAllInactive() throws Exception {
        BienesService service = new BienesService();
        List<Bienes> inactiveProduct = service.getAllInactive();
        System.out.println("Lista de Bienes inactivos:");
        for (Bienes product : inactiveProduct) {
            System.out.println(product);
        }
    }

    public void testInsert() throws Exception {
        BienesService service = new BienesService();
        Bienes bienes = new Bienes();
        bienes.setCantidad("10");
        bienes.setDetalle("Cama King");
        bienes.setValorlibro("800");
        bienes.setFecha_ingreso("2023-12-13");
        bienes.setFecha_depreciacion("2023-12-13");
        bienes.setStatus("Alta");
        service.insert(bienes);
        System.out.println("Nuevo bien insertado");
        System.out.println(bienes.getDetalle() + " " + bienes.getValorlibro());
    }

    public void testUpdate() throws Exception {
        BienesService service = new BienesService();
        Bienes product = service.getForId(1);
        if (product != null) {
            product.setValorlibro("600");
            service.update(product);
            System.out.println("Producto actualizado:");
            System.out.println(service.getForId(1));
        }
    }

    public void testDelete() throws Exception {
        BienesService service = new BienesService();
        Bienes productToDelete = new Bienes();
        productToDelete.setBienesid(1);
        service.delete(productToDelete);
        System.out.println("Activo eliminado lógicamente:");
        System.out.println(productToDelete);
    }

    public void testRestore() throws Exception {
        BienesService service = new BienesService();
        Bienes productToRestore = new Bienes();
        productToRestore.setBienesid(3);
        service.restore(productToRestore);
        System.out.println("Producto restaurado lógicamente ");
        System.out.println(productToRestore);
    }

}
