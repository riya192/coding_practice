//package org.coding.practice.wayfair;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.SortedSet;
//
//public class CouponCategory {
//
//    private static final Coupon not_found = new Coupon("Not Found", null);
//
//    public static void main(String[] args)
//    {
//        String number = "200,000";
//        System.out.println(number.replaceAll(",", ""));
//        System.out.println(String.format("%,d", 2000000));
//        Map<String, Category> categoryMap = new HashMap<>();
//        Map<String, SortedSet<Coupon>> couponCategoryMap = new HashMap<>();
//        categoryMap.put("Bed and Bath", new Category("Bed and Bath", null));
//        categoryMap.put("Bedding", new Category("Bedding", "Bed and Bath"));
//        categoryMap.put("Comforter Set", new Category("Comforter Set", "Bedding"));
//        categoryMap.put("Toy organizer", new Category("Toy organizer", "Baby and Kids"));
//        categoryMap.put("Baby and Kids", new Category("Baby and Kids", null));
//        addCouponToCategory("comforter coupon" , "Comforter Set", categoryMap, couponCategoryMap);
//        addCouponToCategory("bed bath coupon", "Bed and Bath", categoryMap, couponCategoryMap);
//
//        Coupon c1 = getCoupon("Bed and Bath", couponCategoryMap, categoryMap);
//        Coupon c2 = getCoupon("Bedding", couponCategoryMap, categoryMap);
//        Coupon c3 = getCoupon("Comforter Set", couponCategoryMap, categoryMap);
//        Coupon c4 = getCoupon("Toy organizer", couponCategoryMap, categoryMap);
//
//    }
//
//
//    private static Coupon getCoupon(String category, Map<String, Coupon> couponCategoryMap, Map<String, Category> categoryMap)
//    {
//        if(!categoryMap.containsKey(category)) throw new RuntimeException("unknown category");
//        if(couponCategoryMap.containsKey(category)) {
//            System.out.println("coupon for catergory - " + category + " - " + couponCategoryMap.get(category).name);
//            return couponCategoryMap.get(category);
//        }
//        Category currCategory = categoryMap.get(category);
//        while(currCategory.getParent() != null)
//        {
//            Category parent = categoryMap.get(categoryMap.get(category).getParent());
//            if(parent.getCoupon() != null) {
//                couponCategoryMap.put(category, parent.getCoupon());
//                System.out.println("coupon for catergory - " + category + " - " + parent.getCoupon().name);
//                return parent.getCoupon();
//            }
//            currCategory = parent;
//        }
//        System.out.println("coupon for catergory - " + category + " - " + not_found.name);
//        return not_found;
//    }
//
//    private static void addCouponToCategory(String name, String category, Map<String, Category> categoryMap, Map<String, Coupon> couponCategoryMap)
//    {
//        Coupon newCoupon = new Coupon(name, category);
//        if(!categoryMap.containsKey(category)) throw new RuntimeException("unknown category");
//        categoryMap.get(category).setCoupon(newCoupon);
//        couponCategoryMap.put(category, newCoupon);
//    }
//}
//
//class Coupon implements Comparable<Coupon>{
//    String name;
//    String category;
//
//    Date date;
//
//
//    Coupon(String  s, String c, Date d)
//    {
//        this.name = s;
//        this.category = c;
//        this.date = d;
//    }
//
//    public Date getDate()
//    {
//        return this.date;
//    }
//
//    @Override
//    public int compareTo(Coupon o) {
//        return o.getDate().compareTo(this.date);
//    }
//
//}
//
//class Category {
//    String name;
//    String parent;
//
//    Coupon coupon;
//
//    Category(String n, String parent)
//    {
//        this.name = n;
//        this.parent = parent;
//        this.coupon = null;
//
//    }
//
//    public String getName()
//    {
//        return this.name;
//    }
//
//    public String getParent()
//    {
//        return this.parent;
//    }
//
//    public void setCoupon(Coupon c)
//    {
//        this.coupon =c;
//    }
//
//    public Coupon getCoupon()
//    {
//        return this.coupon;
//    }
//    //getters and setters
//}
//
//
