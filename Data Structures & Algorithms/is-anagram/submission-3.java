class Solution {
    public boolean isAnagram(String s, String t) {
        char[] arr = s.toCharArray();
      Arrays.sort(arr);
        String sortStr = new String(arr);

        char[] arr2 = t.toCharArray();
        Arrays.sort(arr2);
        String sortStr2 = new String(arr2);

        return (sortStr.equals(sortStr2))? true: false;
    }
}
