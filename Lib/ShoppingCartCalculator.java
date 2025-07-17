package Lib;

import java.util.ArrayList;

public class ShoppingCartCalculator {
    /**
     * เขียน Javadoc ที่นี่เพื่ออธิบายกฎการทำงานและกรณีพิเศษ:
     * - จะทำอย่างไรถ้า items เป็น null หรือ empty?
     * - Items >= 0 || <= 500
     * - กฎส่วนลด BOGO (ซื้อ 1 แถม 1) ตรวจสอบโค้ดแล้วเข้าเงื่อนไข ซื้อ 1 แถม 1, ซื้อ 2 จ่าย 1 และ ซื้อ 3 จ่าย 2, ซื้อ 4 จ่าย 2
     * - กฎส่วนลด BULK (ซื้อ >= 6 ชิ้น ลด 10%)
     * @param ArrayList<CartItem> ราคาและจำนวณห้ามเป็น null, empty 
     * @return ราคาสินค้าทั้งหมดที่คำนวณส่วนลดและไม่มีส่วนลด
     */
    public static double calculateTotalPrice(ArrayList<CartItem> items) {

        if (items == null || items.isEmpty() || items.size() < 0 || items.size() > 500) {
            return 0.0 ;
        }

        double total = 0 ;
        
        for (CartItem item : items) {
            String sku = item.sku();
            double price = item.price();
            int quantity = item.quantity();

            if (quantity < 0 || price < 0) {
                return 0.0 ;
            }
            else if (sku.equals("BOGO")) {
                quantity = quantity-(quantity/2);
                total = total+(price*quantity);
            }
            else if (sku.equals("BULK")) {
                if (item.quantity() >= 6) {
                    total = total+((price*quantity)*0.9);
                } else {
                    total = total+(price*quantity);
                }
            }
            else {
                total = total+(price*quantity);
            }
        }

        return total;
    }
}
