package Lib;

import java.util.ArrayList;

public class ShoppingCartManualTest {
    public static void run() {
        System.out.println("--- Starting Shopping Cart Calculator Tests ---");
        System.out.println(); // for spacing

        int passedCount = 0;
        int failedCount = 0;

        // Test 1: ตะกร้าเป็น null
        try {
            double total1 = ShoppingCartCalculator.calculateTotalPrice(null);
            if (total1 == 0.0) {
                System.out.println("PASSED: Null cart should return 0.0");
                passedCount++;
            } else {
                System.out.println("FAILED: Null cart expected 0.0 but got " + total1);
                failedCount++;
            }
        } catch (Exception e) {
            System.out.println("FAILED: Null cart caused an exception: " + e.getMessage());
            failedCount++;
        }

        // Test 2: ตะกร้าว่าง
        ArrayList<CartItem> emptyCart = new ArrayList<>();
        double total2 = ShoppingCartCalculator.calculateTotalPrice(emptyCart);
        if (total2 == 0.0) {
            System.out.println("PASSED: Empty cart should return 0.0");
            passedCount++;
        } else {
            System.out.println("FAILED: Empty cart expected 0.0 but got " + total2);
            failedCount++;
        }

        // Test 3: คำนวณปกติ ไม่มีส่วนลด
        ArrayList<CartItem> simpleCart = new ArrayList<>();
        simpleCart.add(new CartItem("NORMAL", "Bread", 25.0, 2)); // 50
        simpleCart.add(new CartItem("NORMAL", "Milk", 15.0, 1));      // 15
        double total3 = ShoppingCartCalculator.calculateTotalPrice(simpleCart);
        if (total3 == 65.0) {
            System.out.println("PASSED: Simple cart total is correct 65.0");
            passedCount++;
        } else {
            System.out.println("FAILED: Simple cart total expected 65.0 but got " + total3);
            failedCount++;
        }

        // Test 4: คำนวณมีส่วนลด BOGO
        ArrayList<CartItem> BOGOCart = new ArrayList<>();
        BOGOCart.add(new CartItem("BOGO", "Bread", 25.0, 2)); // 50
        BOGOCart.add(new CartItem("BOGO", "Milk", 15.0, 1));      // 15
        double total4 = ShoppingCartCalculator.calculateTotalPrice(BOGOCart);
        if (total4 == 40.0) {
            System.out.println("PASSED: BOGO cart total is correct 40.0");
            passedCount++;
        } else {
            System.out.println("FAILED: BOGO cart total expected 40.0 but got " + total4);
            failedCount++;
        }

        // Test 5: คำนวณมีส่วนลด BULK
        ArrayList<CartItem> BULKCart = new ArrayList<>();
        BULKCart.add(new CartItem("BULK", "Bread", 25.0, 6)); // 150*0.9 = 135
        BULKCart.add(new CartItem("BULK", "Milk", 15.0, 1));      // 15
        double total5 = ShoppingCartCalculator.calculateTotalPrice(BULKCart);
        if (total5 == 150.0) {
            System.out.println("PASSED: BULK cart total is correct 150.0");
            passedCount++;
        } else {
            System.out.println("FAILED: BULK cart total expected 150.0 but got " + total5);
            failedCount++;
        }

        // Test 6: คำนวณมีส่วนลด BOGO+BULK
        ArrayList<CartItem> MIXEDCart = new ArrayList<>();
        MIXEDCart.add(new CartItem("BULK", "Bread", 25.0, 6)); // 150*0.9 = 135
        MIXEDCart.add(new CartItem("BOGO", "Milk", 15.0, 3));      // 30
        double total6 = ShoppingCartCalculator.calculateTotalPrice(MIXEDCart);
        if (total6 == 165.0) {
            System.out.println("PASSED: MIXED cart total is correct 165.0");
            passedCount++;
        } else {
            System.out.println("FAILED: MIXED cart total expected 165.0 but got " + total6);
            failedCount++;
        }

        // Test 7: quantity ติดลบ
        ArrayList<CartItem> INVALIDQuantityCart = new ArrayList<>();
        INVALIDQuantityCart.add(new CartItem("BULK", "Bread", 25.0, 6)); // 150*0.9 = 135
        INVALIDQuantityCart.add(new CartItem("BOGO", "Milk", 15.0, -3));      // 30
        double total7= ShoppingCartCalculator.calculateTotalPrice(INVALIDQuantityCart);
        if (total7 == 0.0) {
            System.out.println("PASSED: INVALID cart should return 0.0");
            passedCount++;
        } else {
            System.out.println("FAILED: INVALID cart total expected 0.0 but got " + total7);
            failedCount++;
        }

        // Test 8: price ติดลบ
        ArrayList<CartItem> INVALIDPriceCart = new ArrayList<>();
        INVALIDPriceCart.add(new CartItem("BULK", "Bread", 25.0, 6)); // 150*0.9 = 135
        INVALIDPriceCart.add(new CartItem("BOGO", "Milk", 15.0, -3));      // INVALID Should retun 0.0
        double total8= ShoppingCartCalculator.calculateTotalPrice(INVALIDPriceCart);
        if (total8 == 0.0) {
            System.out.println("PASSED: INVALID cart should return 0.0");
            passedCount++;
        } else {
            System.out.println("FAILED: INVALID cart total expected 0.0 but got " + total8);
            failedCount++;
        }

        // --- Test Summary ---
        System.out.println("\n--------------------");
        System.out.println("--- Test Summary ---");
        System.out.println("Passed: " + passedCount + ", Failed: " + failedCount);
        if (failedCount == 0) {
            System.out.println("Excellent! All tests passed!");
        } else {
            System.out.println("Some tests failed.");
        }
    }
}
