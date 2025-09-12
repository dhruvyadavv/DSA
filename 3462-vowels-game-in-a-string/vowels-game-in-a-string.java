class Solution {
    public boolean doesAliceWin(String s) {
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
        for (char ch : s.toCharArray()) {
            if (vowels.contains(ch)) {
                return true;
            }
        }
        return false;
    }
}