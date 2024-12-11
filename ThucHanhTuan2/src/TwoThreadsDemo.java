/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author trann
 */
public class TwoThreadsDemo {
        public static void main(String[] args)
        {
            new  SimpleThread("Dalat").start();
             new  SimpleThread("Sapa").start();
        }
}
