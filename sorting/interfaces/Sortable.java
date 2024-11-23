package sorting.interfaces;

public interface Sortable {
    void sort(int[] arr);
    void printArray(int[] arr);
    String getSortName(); // Menambahkan method untuk mendapatkan nama algoritma
}