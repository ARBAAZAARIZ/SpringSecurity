package InterviewOn28_04_24;

import java.util.Arrays;

public class p3 {

    // move zero to right

    public static void main(String[] args) {
        int [] arr={1,0,2,0,3,0,4,6,7,8,0,2,4};
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]==0){
                    if(arr[j]!=0){
                        int temp=arr[i];
                        arr[i]=arr[j];
                        arr[j]=temp;
                    }
                }
            }
        }

        System.out.println(Arrays.toString(arr));
    }

}
