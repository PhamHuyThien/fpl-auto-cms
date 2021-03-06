/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class Permutation {

    private final int k;
    private final int n;
    private boolean allResult;

    private ArrayList<ArrayList<Integer>> alIntegers;
    private boolean build;

    public Permutation(int k, int n) {
        this.k = k;
        this.n = n;
    }

    public Permutation(int k, int n, boolean allResult) {
        this.k = k;
        this.n = n;
        this.allResult = allResult;
    }

    public ArrayList<ArrayList<Integer>> getResult() {
        build();
        return alIntegers;
    }

    public void build()  {
        if (build) {
            return;
        }
        build = !build;
        init();
        for (int i = k; i <= (allResult ? n : k); i++) {
            boolean[] check = createBoolArrayTrue(n);
            int[] arr = new int[i+1];
            permutation(i, n, arr, 0, check);
        }
    }

    private void permutation(int k, int n, int arr[], int i, boolean check[]) {
        for (int j = 0; j < n; j++) {
            if (check[j]) {
                arr[i] = j;
                check[j] = false;
                if (k - 1 == i) {
                    ArrayList<Integer> alInt = new ArrayList<>();
                    for (int m = 0; m < k; m++) {
                        alInt.add(arr[m]);
                    }
                    alIntegers.add(alInt);
                } else {
                    permutation(k, n, arr, i+1, check);
                }
                check[j] = true;
            }
        }

    }

    private static boolean[] createBoolArrayTrue(int count) {
        boolean bool[] = new boolean[count];
        for (int i = 0; i < count; i++) {
            bool[i] = true;
        }
        return bool;
    }

    private void init() {
        alIntegers = new ArrayList<>();
    }
}
