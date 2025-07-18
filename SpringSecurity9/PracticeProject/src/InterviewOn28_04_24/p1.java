package InterviewOn28_04_24;
import java.util.Arrays;
public class p1 {

    // bubble sort
    public static void main(String[] args) {
        int arr[]={45,78,14,29,84,97,32,56,29,68};

        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]>arr[j]){
                    int temp=arr[i];
                    arr[i]=arr[j];
                    arr[j]=temp;


                }
            }
        }

        System.out.println(Arrays.toString(arr));

    }

}
