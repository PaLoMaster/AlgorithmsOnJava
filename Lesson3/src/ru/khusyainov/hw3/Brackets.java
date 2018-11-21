package ru.khusyainov.hw3;

public class Brackets {

    private String str;

    public Brackets(String str) {
        this.str = str;
    }

    public String check() {
        int size = str.length();
        Stack<Character> st = new Stack<>(size);
        char ch1, ch2;
        StringBuilder sb = new StringBuilder("Проверка:\n" + str);
        for (int i = 0; i < str.length(); i++) {
            ch2 = str.charAt(i);
            switch (ch2) {
                case '[':
                case '{':
                case '(':
                    st.push(ch2);
                    break;
                case ']':
                case '}':
                case ')':
                    if (!st.isEmpty()) {
                        ch1 = st.pop();
                        if ((ch1 == '{' && ch2 != '}') || (ch1 == '[' && ch2 != ']')
                                || (ch1 == '(' && ch2 != ')')) {
                            sb.append("\nОшибка: " + ch2 + " на позиции " + (i + 1));
                        }
                    } else {
                        sb.append("\nОшибка: " + ch2 + " на позиции " + (i + 1));
                    }
                    break;
                default:
                    break;
            }
        }
        if (!st.isEmpty()) {
            sb.append("\nНе закрыто скобок - " + st.size() + ": ");
            while (!st.isEmpty()) {
                sb.append(st.pop());
            }
        } else if (sb.indexOf("Ошибка") == -1) {
            sb.append("\nПоследовательность скобок правильная");
        }
        return sb.toString();
    }
}
