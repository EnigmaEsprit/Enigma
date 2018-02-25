/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationpanier;

import com.stripe.model.Charge;
import entites.StripePayement;

/**
 *
 * @author jean
 */
public class test {
    
    public static void main(String[] args) {
        Charge ch= StripePayement.ChargePayement("rk_test_oGfrFNOjpnRPklUVzjelPHgf", "usd", "tok_visa", 100,"sk_test_kXdQWE7YYBfcaZK9OoH63KaR", "4242424242424242", 2, 2019, "314", "cheunanthonycedric.da@gmail.com");
    }
}
