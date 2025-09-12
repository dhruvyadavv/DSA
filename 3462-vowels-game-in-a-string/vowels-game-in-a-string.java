class Solution {
    public boolean doesAliceWin(String s) {
        String vowels = "aeiou";
        int count = 0;
        for (char ch : s.toCharArray()) {
            if (vowels.contains(String.valueOf(ch))) {
                count += 1;
            }
        }
        return count > 0;
    }
}