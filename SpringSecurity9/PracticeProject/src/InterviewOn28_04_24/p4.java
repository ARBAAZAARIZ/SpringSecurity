package InterviewOn28_04_24;

import java.util.Arrays;

public class p4 {


    // remove dublicate integer and string
    public static void main(String[] args) {

        int arr[]={1,8,3,7,8,1,7,4};

        int countDub=0;
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]==arr[j] && arr[i]!=0){
                    countDub++;
                    arr[j]=0;
                }
            }
        }

        int resultArr[]=new int[arr.length-countDub];

       int idx=0;
       int newIdx=0;
       while(idx<arr.length){
           if(arr[idx]!=0){
               resultArr[newIdx]=arr[idx];
               idx++;
               newIdx++;
           }else{
               idx++;
           }
       }
        System.out.println(Arrays.toString(resultArr));


        System.out.println(countDub);

        System.out.println("Now removing Integer");


        String str="arbaazzbc"; // ->arbz


        char[] letters =new char[str.length()];

        for(int i=0;i<letters.length;i++){
            letters[i]=str.charAt(i);
        }
        System.out.println(Arrays.toString(letters));

       int count=0;
       for(int i=0;i<letters.length;i++){
           for(int j=i+1;j<letters.length;j++){
               if(letters[i]==letters[j] && letters[i]!='-'){
                   count++;
                   letters[j]='-';
               }
           }
       }

        System.out.println(count);

       char[] newArr=new char[letters.length-count];
       idx=0;
        for(int i=0;i<letters.length;i++){
            if(letters[i]!='-'){
                newArr[idx]=letters[i];
                idx++;
            }
        }

        System.out.println(Arrays.toString(newArr));



    }
}
