import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class Solution{

    //Time Complexity: 0(m*n) where m is the no of element in source array and n in target array
    //Space Complexity: 0(m)
    //Did it successfully run on leetcode: Yes
    //Did you face any problem while coding: No

    //In short explain your approach:

    public int shortestWay(String source, String target) {
        if (source == null || source.length() == 0) {
            return -1;
        }
        HashMap<Character, Integer> map = new HashMap<>();  //storing the occurence of each character present in the source array and checking it with target array if the element exists or not
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            map.put(c, i);
        }

        int result = 1; //to count the no. of substring required to generate target
        int pos = 0;    //to navigate through source array
        int i = 0;  //to navigate through target array
        int sl = source.length();   //source kength
        int tl = target.length();   //target length
        while (i < tl) {    //the loop runs till i traverse through every character in my target array
            char c = target.charAt(i);  //extracting the character of target string 1 by 1
            if (!map.containsKey(c)) { //if the source array does not contain a character of the target, it's impossible to find the substring
                return -1;  //hence i return 1
            }
            if (source.charAt(pos) == c) {  //if the character is present in source array, I move my pointer forward in my source as well as target to capture next available substring
                i++;
                pos++;
            }
            else {
                pos++;  //if the character does not match, then I just move my pointer forward in my source array to find next available substring
            }
            if (i< tl && pos == sl) {   //if my pointer at target array has not reached the end and if the pointer in my source array has reached the end of the string, I add 1 to my result as 1 substring has been generated and also reset my pointer at source to 0 to generate next available substring
                result++;
                pos = 0;
            }
        }
        return result;  //at the end I return the result
    }
}

//Optimization
class Solution{

    //Time Complexity: 0(mlogn) where m is the no of element in source array and n in target array
    //Space Complexity: 0(1)
    //Did it successfully run on leetcode: Yes
    //Did you face any problem while coding: No

    //In short explain your approach:

    public int shortestWay(String source, String target){
        if (source == null || source.length() == 0) {
            return -1;
        }
        HashMap<Character, List<Integer>> map = new HashMap<>();  //storing the occurence of each character present in the source array along with the index of it's occurence
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            if(!map.containsKey(c)){
                map.put(c, new ArrayList<>());
            }
            map.get(c).add(i);
        }
        int result = 1; //to count the no. of substring required to generate target
        int pos = 0;    //to navigate through source array
        int i = 0;  //to navigate through target array
        int sl = source.length();   //source kength
        int tl = target.length();   //target length
        while (i < tl) {    //the loop runs till i traverse through every character in my target array
            char c = target.charAt(i);  //extracting the character of target string 1 by 1
            if (!map.containsKey(c)) { //if the source array does not contain a character of the target, it's impossible to find the substring
                return -1;  //hence i return 1
            }
            List<Integer> list = map.get(c);
            int k = Collections.binarySearch(list, pos);
            if(k < 0){  //If the pos was not found, i.e the occurence of the charater was not in the list
                k = -k - 1; //I will get a negative value to which I convert it into index that it should have been present
            }
            if(k == list.size()){   //if the value of k is equal to size of list i.e all the occurences of a character, that means I have traversed and generated all possible substring for round 1. Hence i need to add 1 to my result so that I can again generate sunstrings
                pos = 0;    //also reset my pointer in source to beginning
                result++;
            }
            else{   //if the position of the character is found in the list
                pos = list.get(k) + 1;  // I get the value at that index, and add 1 to it to move my pos pointer forward in source
                i++;    //moving my pointer in target also forward
            }
        }
        return result;
    }
}