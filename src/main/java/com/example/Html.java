package com.example;

/**
 * Minimal HTML escaping. turismo's {@code print()} writes text as-is, so
 * any request data written into an HTML response must be escaped first.
 */
public final class Html {

    private Html() {
    }

    /**
     * Escapes {@code & < > " '} so the value is safe inside HTML text and
     * quoted attributes. Returns an empty string for {@code null}.
     *
     * @param s the value to escape
     * @return the escaped value
     */
    public static String htmlEscape(String s) {
        if (s == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '&': sb.append("&amp;"); break;
                case '<': sb.append("&lt;"); break;
                case '>': sb.append("&gt;"); break;
                case '"': sb.append("&quot;"); break;
                case '\'': sb.append("&#x27;"); break;
                default: sb.append(c);
            }
        }
        return sb.toString();
    }

}
