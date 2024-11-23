package sorting.main;

import sorting.algorithms.BubbleSort;
import sorting.algorithms.SelectionSort;
import sorting.interfaces.Sortable;
import sorting.utils.SortingUtility;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\nProgram Sorting");
            System.out.println("==============");
            System.out.println("1. Generate array acak dan uji kedua metode sorting");
            System.out.println("2. Masukkan array manual");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu (1-3): ");
            
            int choice = scanner.nextInt();
            
            if (choice == 3) {
                System.out.println("Program selesai.");
                break;
            }
            
            int[] arr;
            if (choice == 1) {
                System.out.print("Masukkan jumlah elemen array: ");
                int size = scanner.nextInt();
                arr = generateRandomArray(size);
            } else if (choice == 2) {
                System.out.print("Masukkan jumlah elemen array: ");
                int size = scanner.nextInt();
                arr = new int[size];
                System.out.println("Masukkan " + size + " angka:");
                for (int i = 0; i < size; i++) {
                    arr[i] = scanner.nextInt();
                }
            } else {
                System.out.println("Pilihan tidak valid!");
                continue;
            }
            
            System.out.println("\nArray Original:");
            System.out.println("==============");
            for (int num : arr) {
                System.out.print(num + " ");
            }
            System.out.println();
            
            // Membuat instance dari algoritma sorting
            Sortable[] sorters = {new BubbleSort(), new SelectionSort()};
            
            // Menjalankan setiap algoritma sorting
            for (Sortable sorter : sorters) {
                SortingUtility.performSort(sorter, arr);
            }
        }
        
        scanner.close();
    }
    
    private static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(100); // Generate numbers between 0 and 99
        }
        return arr;
    }
}