package InterviewOn28_04_24;

import java.util.*;

public class p2 {

    // most occured element
    public static void main(String[] args) {

        int arr []= {2,4,1,2,4,6,2,4};
        HashMap<Integer,Integer> countOccurance=new HashMap<>();
        for(int i=0;i<arr.length;i++){
            countOccurance.put(arr[i],countOccurance.getOrDefault(arr[i],0)+1);
        }
        System.out.println(countOccurance);

        int max=0;

        for(Map.Entry<Integer,Integer> entry:countOccurance.entrySet()){
            if(max<entry.getValue()){
                max= entry.getValue();
            }
        }

        Set<Integer> result=new HashSet<>();


        for(Map.Entry<Integer,Integer> entry:countOccurance.entrySet()){
            if(max==entry.getValue()){
                result.add(entry.getKey());
            }
        }

        System.out.println(result);




    }
}
