import java.lang.ref.SoftReference;
import java.util.*;

public class LeetCode_Easy_1 {
    public static void main(String[] args) {
        int[] nums = new int[]{3,2,2,3};
        int val = 3; //Output: 5, nums = [0,1,4,0,3,_,_,_]
        int res = removeElement(nums, val);
        for (int i:  nums) {
            System.out.println(i);
        }
        System.out.println("count..." + res);
    }

    //JR 34 SoftReference
    public static void jr34(){

        JR34 s = new JR34();
        SoftReference<JR34> sRef = new SoftReference<JR34>(s);

        System.out.println(s);
        System.out.println(sRef);

        s = null;
        //теперь на объект ссылается еще и обычная переменная cat
        s = sRef.get();

        System.out.println(s);
        System.out.println(sRef);

    }

    static class JR34{ }
    //9
    public static boolean isPalindrome(int x) {
         int[] ints = Integer.valueOf(x).toString().chars().toArray();

        for (int i = 0; i < ints.length; i++) {
            if ( ints[i] != ints[ints.length - 1 - i] )
                return false;
        }
        return true;
    }

    //13 converts roman To arabic Int
    public static int romanToInt(String s) {
        String S = s.toLowerCase();
        int n = 0;
        char ch;
        boolean isNotEnd;

        if (S.length()>15 || S.length()==0)
            throw  new RuntimeException();

        for (int i = 0; i < S.length(); i++) {
            ch = S.charAt(i);
            isNotEnd = (i + 1) < S.length();

            if (ch == 'i'){
                if (isNotEnd){
                    if ( S.charAt(i + 1) == 'x'){
                        n+=9;
                        i++;
                        continue;
                    }
                    if ( S.charAt(i + 1) == 'v'){
                        n+=4;
                        i++;
                        continue;
                    }
                }
                n+=1;
            } else if (ch == 'v'){
                n+=5;
            } else if (ch == 'x'){
                if (isNotEnd){
                    if ( S.charAt(i + 1) == 'l'){
                        n+=40;
                        i++;
                        continue;
                    }
                    if ( S.charAt(i + 1) == 'c'){
                        n+=90;
                        i++;
                        continue;
                    }
                }
                n+=10;
            } else if (ch == 'l'){
                n +=50;
            } else if (ch == 'c'){
                if (isNotEnd){
                    if ( S.charAt(i + 1) == 'd'){
                        n+=400;
                        i++;
                        continue;
                    }
                    if ( S.charAt(i + 1) == 'm'){
                        n+=900;
                        i++;
                        continue;
                    }
                }
                n+=100;
            } else if (ch == 'd'){
              n +=500;
            }  else if (ch == 'm'){
                n+=1000;
            }
        }

        return n;
    }

    //14 longestCommonPrefix
    public static String longestCommonPrefix(String[] strs) {

        String common = "";
        String currentPrefix = "";
        boolean prefixExists;
        int min = Arrays.stream(strs)
                .min(Comparator.comparing(String::length))
                .get()
                .length();

        for (int i = 0; i < min; i++) {
            currentPrefix = strs[0].substring(0, i + 1);
            prefixExists = true;
            for (int j = 1; j < strs.length; j++) {
                    if (!strs[j].substring(0, i + 1).equals(currentPrefix)) {
                        prefixExists = false;
                        break;
                    }
            }
            if (prefixExists)
                common = currentPrefix;
        }

        return common;
    }

    //20 Valid Parentheses using Stack
    public static boolean isValidParanteses(String s) {
        Map<Character, Character> hashMap = Map.of(']', '[', '}', '{', ')', '(');
        List<Character> open = List.of('[', '(', '{');
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            char charAt = s.charAt(i);
            if (open.contains(charAt)) {
                stack.push(charAt);
            } else {
                if (stack.isEmpty() || hashMap.get(charAt) != stack.pop()){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    //26.Not mine. Remove Duplicates from Sorted Array
    public static int removeDubs(int[] nums){
        int left = 0, right = 1, k=0, count=0;
        if(nums.length==1){
            return 1;
        }
        if(nums.length==2 && nums[0]!=nums[1]){
            return 2;
        }
        if(nums.length==0)
            return 0;
        while(right<nums.length){
            if(nums[left]!=nums[right]){
                nums[k] = nums[left];
                k++;
                count++;
            }
            left++;
            right++;
        }
        count++;
        nums[k] = nums[left];
        return count;
    }

    //26.Not mine. Remove Duplicates from Sorted Array
    public static int removeDuplicates(int[] nums) {
        //1. way
        /*TreeSet<Integer> treeSet = new TreeSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            treeSet.add(nums[i]);
        }
        int i =0;
        for (Integer el: treeSet ) {
            nums[i] = el;
            i ++;
        }
        return treeSet.size();*/

        //2/ way
        //set to -101 duplicates
        for (int i = 0; i < nums.length; i++) {
            for (int j = i +1; j < nums.length; j++) {
                if (nums[i] == nums[j])
                    nums[j] = -101;
                else
                    break;
            }
        }

        boolean notNullFound;

        for (int i = 0; i < nums.length; i++) {
            if ( nums[i] == -101){
                 notNullFound = false;
                for (int j = i +1; j < nums.length ; j++) {
                    if (nums[j] == -101)
                        continue;
                    else {
                        notNullFound = true;
                        nums[i] = nums[j];
                        nums[j] = -101;
                        break;
                    }
                }
            }
        }

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if ( nums[i] != -101)
                res++;
        }
        return res;
    }

    /**27. Remove Element
     * @param nums -array to operatte
     * @param val - int value to delete
     * @return - first k elements not removed.
     * More formally, if there are k elements after removing the duplicates,
     * then the first k elements of nums should hold the final result. It does not
     * matter what you leave beyond the first k elements.
    Input: nums = [0,1,2,2,3,0,4,2], val = 2
    Output: 5, nums = [0,1,4,0,3,_,_,_]
     */
    public static int removeElement(int[] nums, int val) {
        int count = 0;
        int valIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val){
                valIndex = i;
                for (int j = i + 1; j < nums.length ; j++) {
                    if (nums[j] != val){
                        valIndex = j;
                        nums[i] = nums[valIndex];
                        nums[valIndex] = val;
                        count++;
                        break;
                    } else {
                        valIndex++;
                    }
                }
            } else {
                count++;
            }
        }
        return count;
    }

    /**
     *
     */
    public int strStr(String haystack, String needle) {
        int index = -1;

        return index;
    }
}
