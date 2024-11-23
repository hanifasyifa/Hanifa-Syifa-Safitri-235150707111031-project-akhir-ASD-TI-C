// membandingkan dua elemen bersebelahan
// lebih sederhana diimplementasikan

package sorting.algorithms;

import sorting.interfaces.Sortable;

public class BubbleSort implements Sortable {
    @Override
    // looping luar (i) menentukan berapa kali harus mengulang proses
    // looping dalam (j) melakukan perbandingan antar elemen
    // jika elemen kiri lebih besar dari kanan, tukar posisinya
    // setelah iterasi loop luar, elemen terbesar akan berada di posisi paling kanan

    public void sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    @Override
    public void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    @Override
    public String getSortName() {
        return "Bubble Sort";
    }
}

