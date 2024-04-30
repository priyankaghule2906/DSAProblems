package arrays;

import java.util.HashMap;

public class FrequencyCounter {

    public static void count(int arr[], int N, int P)
    {
        int mod = Math.max(P+1, N+1);
        for(int i=0;i<N;i++){
            int element = arr[i]%mod;
            if(element>=1 && element<=N){
                arr[(arr[i]-1)%mod]+=mod;
            }
        }
        for(int i=0;i<N;i++){
            arr[i]/=mod;
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 3, 2, 5};
        frequencyCount(arr, arr.length, 0);
    }


    public static void frequencyCount(int arr[], int N, int P)
    {
        // code here
        HashMap<Integer, Integer>  map = new HashMap<>();

        for(int i=0;i<N;i++){
            if(!map.containsKey(arr[i])){
                map.put(arr[i], 1);
            } else {
                map.put(arr[i],map.get(arr[i]) +1);
            }
        }

        for(int i=0;i<N;i++){
            if(!map.containsKey(i)){
                arr[i]  = 0;
            } else {
                arr[i] = map.get(i);
            }
        }
    }
}
