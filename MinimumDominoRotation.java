class Solution {

    //Time Complexity: 0(n) where n is the no of element in tops array
    //Space Complexity: 0(1)
    //Did it successfully run on leetcode: Yes
    //Did you face any problem while coding: No

    //In short explain your approach:

    public int minDominoRotations(int[] tops, int[] bottoms) {
        if(tops == null || tops.length == 0){
            return 0;
        }
        int ans = Rotation(tops, bottoms, tops[0]); //I am calling my rotation function to find the minimum no. of rotations required by top or bottom to have same digit
        if(ans != -1){  //if the function does not return -1, I return my ans as that is the lowest no. of rotations required
            return ans;
        }
        return Rotation(tops, bottoms, bottoms[0]); //if it returns -1, I need to check with the bottom[0] as target as well. If still there is an integer missing during any of the rotations it will return -1 meaning no rotation is possible as the target digit is missing.
    }//or it will return the minimum no. of rotations
    public int Rotation(int [] tops, int [] bottoms, int target){
        int topRot = 0; //initializing top rotation and bottom totation
        int botRot = 0;
        for(int i = 0; i < tops.length; i++){   //going through the elements in tops and bottoms array
            if(tops[i] != target && bottoms[i] != target){  //if the target digit is not present in both, it will return -1
                return -1;
            }
            if(tops[i] != target){  //else rotate top to once to get the target
                topRot++;
            }
        }
        for(int i = 0; i < tops.length; i++){   ////once the top rotations are found, I do the same for bottom rotations
            if(bottoms[i] != target){
                botRot++;
            }
        }
        return Math.min(topRot, botRot);    //to return the minimum of both the rotations
    }

 //Using HashMap
 class Solution {
     public int minDominoRotations(int[] tops, int[] bottoms) {
         if(tops == null || tops.length == 0){
             return 0;
         }
         int target = -1;
         HashMap<Integer, Integer> map = new HashMap<>();   //In this case I use a hashmap to store the no. of occurence of a particular character. If the ocurrence is same as length of the tops array, it can be that element that is present in both the array and can be found by rotating the domino. So i perform the rotations on that and check if it is possible or not
         for(int i = 0; i < tops.length; i++){
             int top = tops[i];
             map.put(top, map.getOrDefault(top, 0) + 1);
             if(map.get(top) == tops.length){
                 target = top;
                 break;
             }
             int bot = bottoms[i];
             map.put(bot, map.getOrDefault(bot, 0) + 1);
             if(map.get(bot) == tops.length){
                 target = bot;
                 break;
             }
         }
         if(target == -1){
             return -1;
         }
         return Rotation(tops, bottoms, target);
     }
     public int Rotation(int [] tops, int [] bottoms, int target){
         int topRot = 0;
         int botRot = 0;
         for(int i = 0; i < tops.length; i++){
             if(tops[i] != target && bottoms[i] != target){
                 return -1;
             }
             if(tops[i] != target){
                 topRot++;
             }
         }
         for(int i = 0; i < tops.length; i++){
             if(tops[i] != target && bottoms[i] != target){
                 return -1;
             }
             if(bottoms[i] != target){
                 botRot++;
             }
         }
         return Math.min(topRot, botRot);
     }
 }
}