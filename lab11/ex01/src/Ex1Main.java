package ex01.src;

public class Ex1Main {
    public static void main(String[] args) {
        Gestor g = new Gestor("Gestor1");
        Cliente c1 = new Cliente("Cliente1");
        Cliente c2 = new Cliente("Cliente2");
        Cliente c3 = new Cliente("Cliente3");
        Produto p1 = new Produto("Produto1", 10, g);
        Produto p2 = new Produto("Produto2", 20, g);
        Produto p3 = new Produto("Produto3", 30, g);
        Produto p4 = new Produto("Produto4", 40, g);
        Produto p5 = new Produto("Produto5", 50, g);

        g.addProductStock(p1);
        g.addProductStock(p2);
        g.addProductStock(p3);
        g.addProductStock(p4);
        g.addProductStock(p5);

        System.out.println(g+"is checking stock");
        g.checkStock();

        for (Produto p : g.getProductsStock()) {
            p.startAuction(100);
                c1.addProductAuction(p);
                c2.addProductAuction(p);
                c3.addProductAuction(p);
        }

        System.out.println();
        System.out.println();
        System.out.println("------------ Test checks ------------");

        System.out.println("Checking for available products in auction for client " + c1.getName());
        c1.checkAuction();
        System.out.println();

        System.out.println("Checking products in auction for manager " + g.getName());
        g.checkAuction();
        System.out.println();

        System.out.println("----------------- Test bids -----------------");

        c1.bid(p1, 15);
        c2.bid(p1, 20);
        c3.bid(p1, 25);
        c1.bid(p1, 30);
        c2.bid(p1, 25);

        try {
            Thread.sleep(100); // Sleep for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        c3.bid(p1, 35);

        g.checkAuction();

        p2.endAuction();
        p3.endAuction();
        p4.endAuction();
        p5.endAuction();

        g.checkAuction();

        System.out.println();

        c1.checkAuction();

        c1.bid(p2, 25);
        c1.bid(p3, 1);
        c1.bid(p4, 50);
        c1.bid(p5, 60);

        c1.checkAuction();

        System.out.println();

        g.checkSold();

        System.out.println();

        g.checkStock();



    }  
}
