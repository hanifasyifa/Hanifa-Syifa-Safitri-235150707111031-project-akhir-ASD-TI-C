// lebih sedikit pertukaran dibanding buble sort
// selalu melakukan scan lengkap untuk mencari minimum
package sorting.algorithms;

import sorting.interfaces.Sortable;
// algoritma membagi array menjadi 2 bagian: terurut dan tidak terurut
// kemudian membandingkan 2 bagian tersebut dan memasukkan ke dalam array

public class SelectionSort implements Sortable {
    @Override
    // loop luar (i) menandai awal dari bagian yg tidak terurut
    // loop dalam (j) mencari elemen terkecil di bagian tidak terurut
    // setelah menemukan minimum, tukar dengan elemen pertama di bagian tidak terurut
    // setiap iterasi loop luar menambah satu elemen ke bagian terurut
    public void sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            int min_idx = i;
            for (int j = i+1; j < n; j++) {
                if (arr[j] < arr[min_idx]) {
                    min_idx = j;
                }
            }
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
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
        return "Selection Sort";
    }
}