package sorting.utils;

import sorting.interfaces.Sortable;
import java.util.Arrays;

public class SortingUtility {
    public static void performSort(Sortable sorter, int[] arr) {
        System.out.println("\n" + sorter.getSortName() + ":");
        System.out.println("=".repeat(sorter.getSortName().length() + 1));
        
        // Membuat salinan array untuk menjaga array original
        int[] arrCopy = Arrays.copyOf(arr, arr.length);
        
        System.out.println("Sebelum pengurutan:");
        sorter.printArray(arrCopy);
        
        // Mencatat waktu mulai
        long startTime = System.nanoTime();
        
        sorter.sort(arrCopy);
        
        // Mencatat waktu selesai
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000; // Convert to microseconds
        
        System.out.println("Setelah pengurutan:");
        sorter.printArray(arrCopy);
        System.out.println("Waktu eksekusi: " + duration + " mikrodetik");
    }
}

